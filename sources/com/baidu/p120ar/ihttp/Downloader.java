package com.baidu.p120ar.ihttp;

import com.baidu.p120ar.callback.ICallbackWith;
import com.baidu.p120ar.utils.FileUtils;
import com.baidu.p120ar.utils.IoUtils;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.ihttp.Downloader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class Downloader {
    private int mFileSize = 0;
    private String mUrl;

    public Downloader(String str) {
        this.mUrl = str;
    }

    public int getFileSize() throws HttpException {
        if (this.mFileSize == 0) {
            this.mFileSize = getNetFileSize(this.mUrl);
        }
        return this.mFileSize;
    }

    public int download(String str, IProgressCallback iProgressCallback) throws HttpException, IOException {
        int fileSize = getFileSize();
        IHttpResponse execute = HttpFactory.newRequest().setUrl(this.mUrl).setMethod("GET").execute();
        if (execute.isSuccess()) {
            save(execute.getStream(), str, fileSize, iProgressCallback);
            return fileSize;
        }
        throw new HttpException(4, "下载失败");
    }

    public IHttpRequest downloadAsync(final String str, final ICallbackWith<Integer> iCallbackWith, final ICallbackWith<Exception> iCallbackWith2, final IProgressCallback iProgressCallback) {
        IHttpRequest newRequest = HttpFactory.newRequest();
        newRequest.setUrl(this.mUrl).setMethod("HEAD").enqueue(new IHttpCallback() { // from class: com.baidu.ar.ihttp.Downloader.1
            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onResponse(IHttpResponse iHttpResponse) {
                Downloader.this.mFileSize = iHttpResponse.getContentLength();
                try {
                    Downloader.this.download(str, iProgressCallback);
                    iCallbackWith.run(Integer.valueOf(Downloader.this.mFileSize));
                } catch (Exception e) {
                    e.printStackTrace();
                    iCallbackWith2.run(e);
                }
            }

            @Override // com.baidu.p120ar.ihttp.IHttpCallback
            public void onFail(HttpException httpException) {
                iCallbackWith2.run(httpException);
            }
        });
        return newRequest;
    }

    public IHttpRequest downloadAsync(String str, ICallbackWith<Integer> iCallbackWith, ICallbackWith<Exception> iCallbackWith2) {
        return downloadAsync(str, iCallbackWith, iCallbackWith2, null);
    }

    public static int getNetFileSize(String str) throws HttpException {
        return HttpFactory.newRequest().setUrl(str).setMethod("HEAD").execute().getContentLength();
    }

    private static void save(InputStream inputStream, String str, int i, IProgressCallback iProgressCallback) throws IOException {
        RandomAccessFile randomAccessFile;
        FileUtils.ensureParent(new File(str));
        byte[] bArr = new byte[8192];
        BufferedInputStream bufferedInputStream = null;
        try {
            randomAccessFile = new RandomAccessFile(str, "rw");
            try {
                randomAccessFile.seek(0L);
                BufferedInputStream bufferedInputStream2 = new BufferedInputStream(inputStream, 8192);
                int i2 = 0;
                while (true) {
                    try {
                        int read = bufferedInputStream2.read(bArr, 0, 8192);
                        if (read != -1) {
                            randomAccessFile.write(bArr, 0, read);
                            i2 += read;
                            if (iProgressCallback != null) {
                                iProgressCallback.onProgress(i2, i);
                            }
                        } else {
                            IoUtils.closeQuietly(bufferedInputStream2);
                            IoUtils.closeQuietly(randomAccessFile);
                            return;
                        }
                    } catch (Throwable th) {
                        th = th;
                        bufferedInputStream = bufferedInputStream2;
                        IoUtils.closeQuietly(bufferedInputStream);
                        IoUtils.closeQuietly(randomAccessFile);
                        throw th;
                    }
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            randomAccessFile = null;
        }
    }
}
