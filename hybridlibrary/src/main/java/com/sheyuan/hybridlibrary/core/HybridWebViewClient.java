package com.sheyuan.hybridlibrary.core;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import com.sheyuan.baselibrary.base.BaseApplication;
import com.tencent.smtt.export.external.interfaces.WebResourceRequest;
import com.tencent.smtt.export.external.interfaces.WebResourceResponse;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by moutain on 17-9-20 15:46.
 */

public class HybridWebViewClient extends WebViewClient {
    private WebView mWebView;
    private File imgFile;
    private Bitmap bitmap;
    private File path;
    private String mFilterHost;
    public HybridWebViewClient(WebView webView){
        this.mWebView = webView;
    }

    public void setHostFilter(String host){
        mFilterHost = host;
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, String url) {
        WebResourceResponse webResourceResponse= requestFilter(webView,url);
        if(webResourceResponse!=null){
            return webResourceResponse;
        }
        return super.shouldInterceptRequest(webView, url);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest) {
        WebResourceResponse webResourceResponse= requestFilter(webView,webResourceRequest.getUrl().toString().trim());
        if(webResourceResponse!=null){
            return webResourceResponse;
        }
        return super.shouldInterceptRequest(webView, webResourceRequest);
    }

    @Override
    public WebResourceResponse shouldInterceptRequest(WebView webView, WebResourceRequest webResourceRequest, Bundle bundle) {
        WebResourceResponse webResourceResponse= requestFilter(webView,webResourceRequest.getUrl().toString().trim());
        if(webResourceResponse!=null){
            return webResourceResponse;
        }
        return super.shouldInterceptRequest(webView, webResourceRequest, bundle);
    }
    //根据包含不同路径的url对请求进行不同的处理
    private WebResourceResponse requestFilter(WebView webView,String url) {
        WebResourceResponse response = null;
        //对包含"webapp"的url进行拦截并在本地寻找页面资源返回
        if(url.contains("/webapp")){
            Uri uriPath = Uri.parse(url);
            String[] paths = uriPath.getPath().split("/webapp/");
            String path = paths[paths.length-1];
            File webRes = new File(webView.getContext().getFilesDir().getAbsolutePath()+"/"+HybridConfig.HYBRID_RES_PATH+"/"+path);
            if(webRes.exists()){
                try{
                    InputStream localCopy = new FileInputStream(webRes);
                    String mimeType = getMimeType(url);
                    response = new WebResourceResponse(mimeType,"UTF-8",localCopy);
                }catch (IOException e){
                    e.printStackTrace();
                }
                return response;
            }
        }
        //对加载重复的图片资源进行缓存返回
        if("syimg.sheyuan.com".equals(url)){
            String[] imgPaths = url.split("/");
            if (imgPaths.length>1){
                String imgPath = BaseApplication.cacheDir+"/"+HybridConfig.HYBRID_IMG_PATH;
                String imgName = imgPaths[imgPaths.length-1];
                File imgDir = new File(imgPath);
                File imgFile = new File(imgDir,imgName);
                if(imgFile.exists()){
                    try{
                        InputStream localCopy = new FileInputStream(imgFile);
                        String mimeType = getMimeType(url);
                        response = new WebResourceResponse(mimeType,"UTF-8",localCopy);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                    return response;
                }else{
                    //子线程下载图片或者开启服务后台下载图片
                }
            }
        }

        return null;
    }

    /**
     * 判断返回的Response的数据类型
     * @param url
     * @return
     */
    private String getMimeType(String url) {
        if(url.contains(".")){
            int index = url.lastIndexOf(".");
            if(index>-1){
                int paramsIndex = url.indexOf("?");
                String type = url.substring(index+1,paramsIndex==-1?url.length():paramsIndex);
                switch (type){
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

}
