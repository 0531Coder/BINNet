package com.sheyuan.hybridlibrary.utils;

/**
 * Created by xiaoma on 2016/12/5.
 */

public class PlusUpdateUtil {
//    private static Context mContext;
//    private static int mType;
//    private static List<SysConfig.DataBean> mLocalVersion;
//    private static List<SysConfig.DataBean> mNewVersion;
//    private static List<SysConfig.DataBean> downLoadList = new ArrayList<>();
//    private static int totalSize;
//    private static int currentSize;
//    private static boolean popisShown;
//
//    private static class CompareVersion {
//        HybridVersionEntity localVersion;
//        HybridVersionEntity remoteVersion;
//
//        public CompareVersion(HybridVersionEntity localVersion, HybridVersionEntity remoteVersion) {
//            this.localVersion = localVersion;
//            this.remoteVersion = remoteVersion;
//        }
//    }
//
//    public static void checkVersion(final Context context, List<SysConfig.DataBean> data, int type) {
//        mContext = context;
//        mType = type;
//        //2.对比本地保存是版本信息和服务器的版本信息是否一致
//        HybridVersionEntity localVersion;
//        HybridVersionEntity remoteVersion;
//
//        File version = new File(FileUtil.getRootDir(mContext), HybridConfig.FILE_HYBRID_DATA_VERSION);
//        if (!version.exists() || version.isDirectory() || TextUtils.isEmpty(FileUtil.readFile(version))) {
//            localVersion = null;
//            //3.本地保存版本信息
//            File target = FileUtil.rebuildFile(FileUtil.getRootDir(mContext), HybridConfig.FILE_HYBRID_DATA_VERSION);
//            FileUtil.writeFile(target, HybridAction.mGson.toJson(data));
//            String versionStr = FileUtil.readFile(target);
//            remoteVersion = new Gson().fromJson(versionStr, HybridVersionEntity.class);
//        } else {
//            localVersion = new Gson().fromJson(FileUtil.readFile(version), HybridVersionEntity.class);
//            remoteVersion = new HybridVersionEntity(data);
//        }
//        CompareVersion compareVersion = new CompareVersion(localVersion, remoteVersion);
//        compareVersion(compareVersion.localVersion, compareVersion.remoteVersion);
//    }
//
//    /**
//     * 下载web业务包
//     */
//    private static boolean showDialog = true;
//
//    private static void compareVersion(final HybridVersionEntity localVersion, final HybridVersionEntity remoteVersion) {
//        if (null == remoteVersion) return;
//        List<SysConfig.DataBean> data = remoteVersion.getHybridInfo();
//        if (null == data || data.isEmpty()) return;
//        int size = data.size();
//        mLocalVersion = localVersion.getHybridInfo();
//        mNewVersion = new ArrayList<>();
//        int localSize = mLocalVersion.size();
//   /*     for (int i = 0; i < size; i++) {
//            SysConfig.DataBean dataBean = data.get(i);
//            //用来处理在本地没有的业务模块下载
//            boolean localHas = false;
//            for (int j = 0; j < localSize; j++) {
//                SysConfig.DataBean localDateBean = mLocalVersion.get(j);
//                if (dataBean.getChannel().equals(localDateBean.getChannel())) {
//                    if (!dataBean.getUpdateTime().equals(localDateBean.getUpdateTime())) {
//                        switch (mType) {
//                            case 1:
//                                if (showDialog) {
//                                    //提示更新
//                                    showDialog = false;
//                                    PopUtil.showDialog((BaseActivity) mContext, "是否升级本地资源文件", "升级本地资源文件。", "取消", "升级", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            PopUtil.dismissPop();
//                                        }
//                                    }, new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            //开始下载所有业务模块的zip包
//                                            PopUtil.dismissPop();
//
//                                            zipToSdcard(dataBean.getSrc(), dataBean.getChannel() + ".zip", dataBean.getChannel(), dataBean.getMd5(), dataBean);
//                                        }
//                                    });
//                                } else {
//                                    zipToSdcard(dataBean.getSrc(), dataBean.getChannel() + ".zip", dataBean.getChannel(), dataBean.getMd5(), dataBean);
//                                }
//                                break;
//                            case 2:
//                                //强制更新
//                                if (showDialog) {
//                                    //提示更新
//                                    showDialog = false;
//                                    PopUtil.showDialog((BaseActivity)mContext, "是否升级本地资源文件", "升级本地资源文件。", "取消", "升级", new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            PopUtil.dismissPop();
//                                        }
//                                    }, new View.OnClickListener() {
//                                        @Override
//                                        public void onClick(View v) {
//                                            //开始下载所有业务模块的zip包
//                                            PopUtil.dismissPop();
//
//                                            zipToSdcard(dataBean.getSrc(), dataBean.getChannel() + ".zip", dataBean.getChannel(), dataBean.getMd5(), dataBean);
//                                        }
//                                    });
//                                } else {
//                                    zipToSdcard(dataBean.getSrc(), dataBean.getChannel() + ".zip", dataBean.getChannel(), dataBean.getMd5(), dataBean);
//                                }
//
//                                break;
//                            case 0:
//                                //静默更新
//                                zipToSdcard(dataBean.getSrc(), dataBean.getChannel() + ".zip", dataBean.getChannel(), dataBean.getMd5(), dataBean);
//                                break;
//                        }
//                    } else {
//                        mNewVersion.add(dataBean);
//                    }
//                    localHas = true;
//                }
//            }
//
//            if (!localHas) {
//                zipToSdcard(dataBean.getSrc(), dataBean.getChannel() + ".zip", dataBean.getChannel(), dataBean.getMd5(), dataBean);
//            }
//        }*/
//
//        for (int i = 0; i < size; i++) {
//            boolean localHas = false;
//            SysConfig.DataBean dataBean = data.get(i);
//            for (int j = 0; j < localSize; j++) {
//                SysConfig.DataBean localDateBean = mLocalVersion.get(j);
//                if (dataBean.channel.equals(localDateBean.channel)) {
//                    localHas = true;
//                    if (!dataBean.getUpdateTime().equals(localDateBean.getUpdateTime())) {
//                        /**
//                         * 下载全部完成以后记得清零
//                         */
//                        downLoadList.add(dataBean);
//                        totalSize += Integer.parseInt(dataBean.getFileSize());
//                    }
//                }
//            }
//            if (!localHas) {
//                downLoadList.add(dataBean);
//                totalSize += Integer.parseInt(dataBean.getFileSize());
//            }
//
//        }
//        if (downLoadList.size() > 0) {
//            switch (mType) {
//                //提示更新
//                case 1:
//                    //提示更新
//                    PopUtil.showDialog((BaseActivity) mContext, "是否升级本地资源文件", "升级本地资源文件。", "取消", "升级", new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            PopUtil.dismissPop();
//                        }
//                    }, new View.OnClickListener() {
//
//                        @Override
//                        public void onClick(View v) {
//                            //开始下载所有业务模块的zip包
//                            PopUtil.dismissPop();
//                            PopUtil.showDownLoadProgress((BaseActivity) mContext);
//                            popisShown = true;
//                            for (int i = 0; i < downLoadList.size(); i++) {
//                                zipToSdcard(downLoadList.get(i).getSrc(), downLoadList.get(i).getChannel() + ".zip", downLoadList.get(i).getChannel(), downLoadList.get(i).getMd5(), downLoadList.get(i));
//                            }
//                        }
//                    });
//                    break;
//                //强制更新
//                case 2:
//                    PopUtil.showDownLoadProgress((BaseActivity) mContext);
//                    popisShown = true;
//                    for (int i = 0; i < downLoadList.size(); i++) {
//                        zipToSdcard(downLoadList.get(i).getSrc(), downLoadList.get(i).getChannel() + ".zip", downLoadList.get(i).getChannel(), downLoadList.get(i).getMd5(), downLoadList.get(i));
//                    }
//                    break;
//                //静默更新
//                case 0:
//                    if (popisShown) {
//                        PopUtil.dismissPop();
//                    }
//                    for (int i = 0; i < downLoadList.size(); i++) {
//                        zipToSdcard(downLoadList.get(i).getSrc(), downLoadList.get(i).getChannel() + ".zip", downLoadList.get(i).getChannel(), downLoadList.get(i).getMd5(), downLoadList.get(i));
//                    }
//                    break;
//            }
//        }else {
//            //没有要更新的内容时，显示广告页
//            if (BaseActivity.sysConfigInfo != null) {
//                BaseActivity.sysConfigInfo.showAd();
//            }
//        }
//    }
//
//    private static synchronized void zipToSdcard(String url, final String zipFileName, final String zipFolderName, final String md5, SysConfig.DataBean remoteVersion) {
//        final Uri uri = Uri.parse(url);
//        HybridAjaxService.IApiService service = HybridAjaxService.getService(uri);
//        Call<ResponseBody> call = service.download(url);
//        boolean hasDowned = false;
//        call.enqueue(new Callback<ResponseBody>() {
//            @Override
//
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                currentSize+=1;
//                if(popisShown) {
//                    PopUtil.downLoadProgressBar.setMax(1);
//                    PopUtil.downLoadProgressBar.setProgress(currentSize / downLoadList.size());
//                    int progress = currentSize / downLoadList.size() * 1000 / 10;
//                    PopUtil.downLoadText.setText(progress + "%");
//                    if (downLoadList.size() == currentSize) {
//                        new Handler().postDelayed(new Runnable() {
//                            public void run() {
//                                PopUtil.dismissPop();
//                                if (BaseActivity.sysConfigInfo != null) {
//                                    BaseActivity.sysConfigInfo.showAd();
//                                }
//                            }
//                        }, 1000);
//                        totalSize = 0;
//                        currentSize = 0;
//                        downLoadList.clear();
//                    }
//                }else {
//                    if (downLoadList.size() == currentSize) {
//                        totalSize = 0;
//                        currentSize = 0;
//                        downLoadList.clear();
//                    }
//                }
//
//                new AsyncTask<Void, Integer, Void>() {
//                    @Override
//                    protected Void doInBackground(Void... params) {
//                        if(null!=response.body().byteStream()){
//                            unZipFile(response.body().byteStream(), zipFileName, zipFolderName, md5, remoteVersion);
//                        }
//                        return null;
//                    }
//
//                    @Override
//                    protected void onPostExecute(Void aVoid) {
//                        super.onPostExecute(aVoid);
//                    }
//                }.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//                call.cancel();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                //下载失败后在重新请求下载
//                call.cancel();
//            }
//        });
//    }
//
//
//    private static synchronized void unZipFile(InputStream response, String zipFileName, String zipFolderName, String md5, SysConfig.DataBean remoteVersion) {
//        File storageDirectory = new File(FileUtil.getRootDir(mContext), HybridConfig.FILE_HYBRID_DATA_TEMP_PATH);
//        if (!storageDirectory.exists()) {
//            storageDirectory.mkdirs();
//        }
//        File zip = FileUtil.rebuildFile(storageDirectory, zipFileName);
//        if (FileUtil.writeFile(zip, response)) {
////            Log.e("xiaomage= 写入后", zipFolderName + "//" + zip.length() / 1024);
//            String zipMD5 = Md5Utils.getMD5(zip);
//            if (zipMD5.equals(md5)) {
//                File unZip = new File(storageDirectory, zipFolderName);
//                if (unZip.exists()) {
//                    FileUtil.clearFolder(unZip);
//                } else {
//                    unZip.mkdirs();
//                }
//                FileUtil.unZip(zip, zip.getAbsolutePath(), unZip.getAbsolutePath());
//                mNewVersion.add(remoteVersion);
//                //zip下载完成后把 新的配置文件放到配置文件
//                File target = FileUtil.rebuildFile(FileUtil.getRootDir(mContext), HybridConfig.FILE_HYBRID_DATA_VERSION);
//                HybridVersionEntity versionEntity = new HybridVersionEntity(mNewVersion);
//                FileUtil.writeFile(target, HybridAction.mGson.toJson(versionEntity));
//            } else {
//                //上传服务器
//                ((BaseActivity) mContext).addSubscription(RetrofitSingleton.getInstance().getRetrofit().create(OterApi.class).
//                        loadToServer(zipFileName, 0, PrefereUtils.getInstance().getjiguangToken(), zipMD5, "farm", md5, HybridConstant.HYBRID_VERSION).
//                        compose(RxUtils.rxSchedulerHelper()).subscribe(new Observer<HybridFailInfo>() {
//
//                    @Override
//                    public void onCompleted() {
//
//                    }
//
//                    @Override
//                    public void onError(Throwable e) {
//                    }
//
//                    @Override
//                    public void onNext(HybridFailInfo hybridFailInfo) {
//                        if (!hybridFailInfo.successed()) {
//                            ToastUtil.show(hybridFailInfo.getMsg());
//                        }
//                    }
//                }));
//            }
//        }
//    }
}
