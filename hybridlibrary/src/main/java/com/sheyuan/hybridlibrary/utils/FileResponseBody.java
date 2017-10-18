package com.sheyuan.hybridlibrary.utils;

//public class FileResponseBody extends ResponseBody {

//    Response originalResponse;
//
//    public FileResponseBody(Response originalResponse) {
//        this.originalResponse = originalResponse;
//    }
//
//    @Override
//    public MediaType contentType() {
//        return originalResponse.body().contentType();
//    }
//
//    @Override
//    public long contentLength() {
//        return originalResponse.body().contentLength();
//    }
//
//    @Override
//    public BufferedSource source() {
//        return Okio.buffer(new ForwardingSource(originalResponse.body().source()) {
//            long bytesReaded = 0;
//
//            @Override
//            public long read(Buffer sink, long byteCount) throws IOException {
//                long bytesRead = super.read(sink, byteCount);
//                bytesReaded += bytesRead == -1 ? 0 : bytesRead;
//                RxBus.getDefault().post(new FileLoadEvent(contentLength(), bytesReaded));
//                return bytesRead;
//            }
//        });
//    }

//}
