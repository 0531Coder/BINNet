package com.sheyuan.hybridlibrary.core;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;

import com.sheyuan.baselibrary.glideutils.CommonImageLoader;
import com.sheyuan.hybridlibrary.action.HybridAction;
import com.tencent.smtt.export.external.interfaces.WebResourceError;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import static com.sheyuan.hybridlibrary.service.ImgService.TAG;

/**
 * Created by moutain on 17-9-20 15:46.
 */

public class HybridWebViewClient extends WebViewClient {
    private WebView mWebView;
    private File imgFile;
    private Bitmap bitmap;
    private File path;
    private String mFilterHost;
    WebResourceResponse response = null;
    private WebViewLoadListener mWebViewLoadListener;

    public HybridWebViewClient(WebView webView) {
        this.mWebView = webView;
    }

    public void setHostFilter(String host) {
        mFilterHost = host;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
        WebResourceResponse webResourceResponse = requestFilter(webView, url);
        if (webResourceResponse != null) {
            return webResourceResponse;
        }
        return super.shouldInterceptRequest(webView, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        WebResourceResponse webResourceResponse = requestFilter(webView, webResourceRequest.getUrl().toString().trim());
        if (webResourceResponse != null) {
            return webResourceResponse;
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest, Bundle bundle) {
        WebResourceResponse webResourceResponse = requestFilter(webView, webResourceRequest.getUrl().toString().trim());
        if (webResourceResponse != null) {
            return webResourceResponse;
        }
        return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
    }

    //根据包含不同路径的url对请求进行不同的处理
    private WebResourceResponse requestFilter(WebView webView, final String url) {
        //对包含"webapp"的url进行拦截并在本地寻找页面资源返回
        if (url.contains("/webapp")) {
            Uri uriPath = Uri.parse(url);
            String[] paths = uriPath.getPath().split("/webapp/");
            String path = paths[paths.length - 1];
            File webRes = new File(webView.getContext().getFilesDir().getAbsolutePath() + "/" + HybridConfig.HYBRID_RES_PATH + "/" + path);
            if (webRes.exists()) {
                try {
                    InputStream localCopy = new FileInputStream(webRes);
                    String mimeType = getMimeType(url);
                    response = new WebResourceResponse(mimeType, "UTF-8", localCopy);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                return response;
            }
        }
        //对加载重复的图片资源进行缓存返回
        // TODO: 17-9-21 这个图片的缓存效果待验证
        if ("syimg.sheyuan.com".equals(url)) {
//            String[] imgPaths = url.split("/");
//            if (imgPaths.length>1){
//                String imgPath = BaseApplication.cacheDir+"/"+HybridConfig.HYBRID_IMG_PATH;
//                String imgName = imgPaths[imgPaths.length-1];
//                File imgDir = new File(imgPath);
//                File imgFile = new File(imgDir,imgName);
//                if(imgFile.exists()){
//                    try{
//                        InputStream localCopy = new FileInputStream(imgFile);
//                        String mimeType = getMimeType(url);
//                        response = new WebResourceResponse(mimeType,"UTF-8",localCopy);
//                    } catch (FileNotFoundException e) {
//                        e.printStackTrace();
//                    }
//                    return response;
//                }else{
//                    //开启服务后台下载图片,如果效果不好就将这个图片缓存的服务搞成远程的服务,脱离对当前进程的依赖
//                    Intent intent  = new Intent(webView.getContext(), ImgService.class);
//                    intent.putExtra("imgUrl",url);
//                    webView.getContext().startService(intent);
//                }
//            }
            //使用glide来接管图片加载任务
            CommonImageLoader.getInstance().loadImageFile(webView.getContext(), url, new CommonImageLoader.getImgFlieListener() {
                @Override
                public void onSuccess(File imgFile) {
                    try {
                        InputStream localCopy = new FileInputStream(imgFile);
                        String mimeType = getMimeType(url);
                        response = new WebResourceResponse(mimeType, "UTF-8", localCopy);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }

                @Override
                public void onError(Drawable drawable) {
                    //这儿可以指定错误返回的图片
                }
            });
            return response;
        }

        return null;
    }


    @Override
    public boolean shouldOverrideUrlLoading(WebView webView, String url) {
        Uri uri = Uri.parse(url);
        String scheme = uri.getScheme();
        if (HybridConfig.SCHEME.equals(scheme)) {
            String host = uri.getHost();
            String param = uri.getQueryParameter(HybridConfig.GET_PARAM);
            String callback = uri.getQueryParameter(HybridConfig.GET_CALLBACK);
            if (null == HybridTagMapping.mapping(host)) {
                //如果host不是我们定义的host,则该次请求让webview处理
                return super.shouldOverrideUrlLoading(webView, url);
            }
            // TODO: 17-9-21 log统一管理
            Log.e(TAG, "shouldOverrideUrlLoading: " + "host==" + host + "param==" + param);
            try {
                hybridDispatcher(host, param, callback);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
            return false;
        }
        //如果scheme不是调用原生接口的scheme，那么webview直接加载页面
        webView.loadUrl(url);
        //这个return如果是false那么这个请求就是用本webview来处理,true就是系统选择程序来处理
        return false;
    }

    private void hybridDispatcher(String host, String param, String callback) throws IllegalAccessException, InstantiationException {
        Class type = HybridTagMapping.mapping(host);
        HybridAction action = (HybridAction) type.newInstance();
        action.onAction(mWebView, param, callback);
    }

    /**
     * 判断返回的Response的数据类型
     *
     * @param url
     * @return
     */
    private String getMimeType(String url) {
        if (url.contains(".")) {
            int index = url.lastIndexOf(".");
            if (index > -1) {
                int paramsIndex = url.indexOf("?");
                String type = url.substring(index + 1, paramsIndex == -1 ? url.length() : paramsIndex);
                switch (type) {
                    case "js":
                        return "text/javascript";
                    case "css":
                        return "text/css";
                    case "html":
                        return "text/html";
                    case "png":
                        return "image/png";
                    case "jpg":
                        return "image/jpg";
                    case "gif":
                        return "image/gif";
                }
            }
        }
        return "text/plain";
    }

    @Override
    public void onPageStarted(WebView webView, String s, Bitmap bitmap) {
        super.onPageStarted(webView, s, bitmap);
        if(mWebViewLoadListener!=null){
            mWebViewLoadListener.onPageStarted(webView,s,bitmap);
        }
    }

    @Override
    public void onPageFinished(WebView webView, String s) {
        super.onPageFinished(webView, s);
        if(mWebViewLoadListener!=null){
            mWebViewLoadListener.onPageFinished(webView,s);
        }
    }

    @Override
    public void onReceivedError(WebView webView, int i, String s, String s1) {
        super.onReceivedError(webView, i, s, s1);
        if (mWebViewLoadListener!=null){
            mWebViewLoadListener.onReceivedError(webView,i,s,s1);
        }
    }

    @Override
    public void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError) {
        super.onReceivedError(webView, webResourceRequest, webResourceError);
        if(mWebViewLoadListener!=null){
            mWebViewLoadListener.onReceivedError(webView,webResourceRequest,webResourceError);
        }
    }

    public void setWebviewLoadListner(WebViewLoadListener webViewLoadListener){
        mWebViewLoadListener = webViewLoadListener;
    }

    public interface WebViewLoadListener {
        void onPageStarted(WebView webView, String url, Bitmap bitmap);

        void onPageFinished(WebView webView, String url);

        void onReceivedError(WebView webView, int i, String s, String s1);

        void onReceivedError(WebView webView, WebResourceRequest webResourceRequest, WebResourceError webResourceError);
    }
}
