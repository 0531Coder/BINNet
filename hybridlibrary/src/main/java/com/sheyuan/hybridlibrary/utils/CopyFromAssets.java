package com.sheyuan.hybridlibrary.utils;

import java.io.IOException;

/**
 * 从assets目录解压zip到本地
 */
public class CopyFromAssets {

    /**
     * 解压assets的zip压缩文件到指定目录
     *
     * @param //context上下文对象
     * @param //assetName压缩文件名
     * @param //outputDirectory输出目录
     * @param //isReWrite是否覆盖
     * @throws IOException
     */
//    public static void copy2Files(Context context, boolean isReWrite) throws IOException {
//
//        // 打开压缩文件
//        InputStream inputStream = context.getAssets().open(HybridConfig.FILE_HYBRID_DATA_VERSION);
//
//        File outFile = new File(context.getFilesDir(), HybridConfig.FILE_HYBRID_DATA_VERSION);
//
//        if(!outFile.exists()){
//           FileUtil.rebuildFile(context.getFilesDir(), HybridConfig.FILE_HYBRID_DATA_VERSION);
//        }
//        OutputStream out = new FileOutputStream(outFile);
//
//        // 使用1Mbuffer
//        byte[] buffer = new byte[1024 * 1024];
//        // 解压时字节计数
//        int len = 0;
//        // 如果进入点为空说明已经遍历完所有压缩包中文件和目录
//        while ((len = inputStream.read(buffer)) > 0) {
//            out.write(buffer, 0, len);
//        }
//        inputStream.close();
//        out.close();
//    }


}