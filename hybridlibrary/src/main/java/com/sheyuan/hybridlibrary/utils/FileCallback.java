package com.sheyuan.hybridlibrary.utils;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public abstract class FileCallback implements Callback<ResponseBody> {

    private String destFileDir;
    private String destFileName;

   /* public FileCallback(String destFileDir, String destFileName) {
        this.destFileDir = destFileDir;
        this.destFileName = destFileName;
        subscribeLoadProgress();
    }*/

//    public FileCallback(){
//        subscribeLoadProgress();
//    }

    public abstract void onSuccess(Response response);

    public abstract void progress(long progress, long total);

    @Override
    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
       /* new Thread(() -> {
            try {
                saveFile(response);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();*/
    }

   /* public void saveFile(Response<ResponseBody> response) throws IOException {
        InputStream is = null;
        byte[] buf = new byte[2048];
        int len;
        FileOutputStream fos = null;
        try {
            is = response.body().byteStream();
            File dir = new File(destFileDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            File file = new File(dir, destFileName);
            fos = new FileOutputStream(file);
            while ((len = is.read(buf)) != -1) {
                fos.write(buf, 0, len);
            }
            fos.flush();
            unsubscribe();
            if (destFileName.endsWith(".zip"))
                unzipFile(file);
            else
                onSuccess(file);
        } finally {
            try {
                if (is != null) is.close();
                if (fos != null) fos.close();
            } catch (IOException e) {
                Log.e("saveFile", e.getMessage());
            }
        }
    }*/

//    private void subscribeLoadProgress() {
//        Subscription subscription = RxBus.getDefault().doSubscribe(FileLoadEvent.class,
//                fileLoadEvent -> progress(fileLoadEvent.getProgress(), fileLoadEvent.getTotal()),
//                throwable -> Log.e("Rxbus",throwable.getMessage()));
//        RxBus.getDefault().addSubscription(this, subscription);
//    }
//
//    private void unsubscribe() {
//        RxBus.getDefault().unSubscribe(this);
//    }

   /* private void unzipFile(File file) {
       *//* try {
            ZipUtil.unzipFolder(file, destFileDir, () -> onSuccess(file));
        } catch (Exception e) {
            e.printStackTrace();
        }*//*
    }*/

}
