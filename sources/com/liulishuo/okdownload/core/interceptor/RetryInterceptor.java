package com.liulishuo.okdownload.core.interceptor;

import android.support.annotation.NonNull;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.download.DownloadCache;
import com.liulishuo.okdownload.core.download.DownloadChain;
import com.liulishuo.okdownload.core.exception.InterruptException;
import com.liulishuo.okdownload.core.exception.RetryException;
import com.liulishuo.okdownload.core.interceptor.Interceptor;
import java.io.IOException;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RetryInterceptor implements Interceptor.Connect, Interceptor.Fetch {
    @Override // com.liulishuo.okdownload.core.interceptor.Interceptor.Connect
    @NonNull
    public DownloadConnection.Connected interceptConnect(DownloadChain downloadChain) throws IOException {
        DownloadCache cache = downloadChain.getCache();
        while (true) {
            try {
                if (cache.isInterrupt()) {
                    throw InterruptException.SIGNAL;
                }
                return downloadChain.processConnect();
            } catch (IOException e) {
                if (e instanceof RetryException) {
                    downloadChain.resetConnectForRetry();
                } else {
                    downloadChain.getCache().catchException(e);
                    downloadChain.getOutputStream().catchBlockConnectException(downloadChain.getBlockIndex());
                    throw e;
                }
            }
        }
    }

    @Override // com.liulishuo.okdownload.core.interceptor.Interceptor.Fetch
    public long interceptFetch(DownloadChain downloadChain) throws IOException {
        try {
            return downloadChain.processFetch();
        } catch (IOException e) {
            downloadChain.getCache().catchException(e);
            throw e;
        }
    }
}
