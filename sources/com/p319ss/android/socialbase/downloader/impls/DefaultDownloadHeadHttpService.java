package com.p319ss.android.socialbase.downloader.impls;

import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpService;
import com.p319ss.android.socialbase.downloader.utils.DownloadExpSwitchCode;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.io.IOException;
import java.util.List;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultDownloadHeadHttpService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultDownloadHeadHttpService implements IDownloadHeadHttpService {
    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpService
    public IDownloadHeadHttpConnection downloadWithConnection(String str, List<HttpHeader> list) throws IOException {
        OkHttpClient downloadClient = DownloadComponentManager.getDownloadClient();
        if (downloadClient == null) {
            throw new IOException("can't get httpClient");
        }
        Request.Builder head = new Request.Builder().url(str).head();
        if (list != null && list.size() > 0) {
            for (HttpHeader httpHeader : list) {
                head.addHeader(httpHeader.getName(), DownloadUtils.getEncodedStr(httpHeader.getValue()));
            }
        }
        final Call newCall = downloadClient.newCall(head.build());
        final Response execute = newCall.execute();
        if (execute == null) {
            throw new IOException("can't get response");
        }
        if (DownloadExpSwitchCode.isSwitchEnable(2097152)) {
            execute.close();
        }
        return new IDownloadHeadHttpConnection() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHeadHttpService.1
            @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public String getResponseHeaderField(String str2) {
                return execute.header(str2);
            }

            @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public int getResponseCode() throws IOException {
                return execute.code();
            }

            @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
            public void cancel() {
                Call call = newCall;
                if (call == null || call.isCanceled()) {
                    return;
                }
                newCall.cancel();
            }
        };
    }
}
