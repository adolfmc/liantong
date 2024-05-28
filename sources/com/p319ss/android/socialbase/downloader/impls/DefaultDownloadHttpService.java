package com.p319ss.android.socialbase.downloader.impls;

import android.net.Uri;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.model.HttpHeader;
import com.p319ss.android.socialbase.downloader.network.IDefaultDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpService;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import com.p319ss.android.socialbase.downloader.utils.LruCache;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collections;
import java.util.List;
import java.util.zip.GZIPInputStream;
import okhttp3.Call;
import okhttp3.Dns;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultDownloadHttpService implements IDownloadHttpService {
    private final LruCache<String, OkHttpClient> hostIpClientCache = new LruCache<>(4, 8);

    @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHttpService
    public IDownloadHttpConnection downloadWithConnection(int i, String str, List<HttpHeader> list) throws IOException {
        String str2;
        OkHttpClient downloadClient;
        Request.Builder url = new Request.Builder().url(str);
        if (list == null || list.size() <= 0) {
            str2 = null;
        } else {
            str2 = null;
            for (HttpHeader httpHeader : list) {
                String name = httpHeader.getName();
                if (str2 == null && "ss_d_request_host_ip_114".equals(name)) {
                    str2 = httpHeader.getValue();
                } else {
                    url.addHeader(name, DownloadUtils.getEncodedStr(httpHeader.getValue()));
                }
            }
        }
        if (!TextUtils.isEmpty(str2)) {
            downloadClient = createSpecialHostIpClient(str, str2);
        } else {
            downloadClient = DownloadComponentManager.getDownloadClient();
        }
        if (downloadClient == null) {
            throw new IOException("can't get httpClient");
        }
        final Call newCall = downloadClient.newCall(url.build());
        final Response execute = newCall.execute();
        if (execute == null) {
            throw new IOException("can't get response");
        }
        final ResponseBody body = execute.body();
        if (body != null) {
            InputStream byteStream = body.byteStream();
            String header = execute.header("Content-Encoding");
            final GZIPInputStream gZIPInputStream = (header == null || !"gzip".equalsIgnoreCase(header) || (byteStream instanceof GZIPInputStream)) ? byteStream : new GZIPInputStream(byteStream);
            return new IDefaultDownloadHttpConnection() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService.1
                @Override // com.p319ss.android.socialbase.downloader.network.AbsDownloadHttpConnection
                public String getHostIp() {
                    return "";
                }

                @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection
                public InputStream getInputStream() throws IOException {
                    return gZIPInputStream;
                }

                @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHeadHttpConnection
                public String getResponseHeaderField(String str3) {
                    return execute.header(str3);
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

                @Override // com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection
                public void end() {
                    try {
                        if (body != null) {
                            body.close();
                        }
                        if (newCall == null || newCall.isCanceled()) {
                            return;
                        }
                        newCall.cancel();
                    } catch (Throwable unused) {
                    }
                }
            };
        }
        return null;
    }

    private OkHttpClient createSpecialHostIpClient(String str, final String str2) {
        try {
            final String host = Uri.parse(str).getHost();
            if (!TextUtils.isEmpty(host) && !TextUtils.isEmpty(str2)) {
                String str3 = host + "_" + str2;
                synchronized (this.hostIpClientCache) {
                    OkHttpClient okHttpClient = this.hostIpClientCache.get(str3);
                    if (okHttpClient != null) {
                        return okHttpClient;
                    }
                    OkHttpClient.Builder createDownloadClientBuilder = DownloadComponentManager.createDownloadClientBuilder();
                    createDownloadClientBuilder.dns(new Dns() { // from class: com.ss.android.socialbase.downloader.impls.DefaultDownloadHttpService.2
                        @Override // okhttp3.Dns
                        public List<InetAddress> lookup(String str4) throws UnknownHostException {
                            if (TextUtils.equals(host, str4)) {
                                return Collections.singletonList(InetAddress.getByName(str2));
                            }
                            return Dns.SYSTEM.lookup(str4);
                        }
                    });
                    OkHttpClient build = !(createDownloadClientBuilder instanceof OkHttpClient.Builder) ? createDownloadClientBuilder.build() : NBSOkHttp3Instrumentation.builderInit(createDownloadClientBuilder);
                    synchronized (this.hostIpClientCache) {
                        this.hostIpClientCache.put(str3, build);
                    }
                    return build;
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return DownloadComponentManager.getDownloadClient();
    }
}
