package com.sinovatech.unicom.p318ui;

import java.io.IOException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.util.List;
import okhttp3.Call;
import okhttp3.Connection;
import okhttp3.Handshake;
import okhttp3.HttpUrl;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.ui.HttpCallBackListener */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface HttpCallBackListener {
    void callEnd(Call call, String str);

    void callFailed(@NotNull Call call, @NotNull IOException iOException, String str);

    void callFinish(Call call, String str, String str2, String str3);

    void callStart(Call call, String str);

    void canceled(Call call, String str);

    void connectEnd(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, String str);

    void connectFailed(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, @Nullable Protocol protocol, @NotNull IOException iOException, String str);

    void connectStart(@NotNull Call call, @NotNull InetSocketAddress inetSocketAddress, @NotNull Proxy proxy, String str);

    void connectionAcquired(@NotNull Call call, @NotNull Connection connection, String str);

    void connectionReleased(@NotNull Call call, @NotNull Connection connection, String str);

    void dnsEnd(@NotNull Call call, @NotNull String str, @NotNull List<InetAddress> list, String str2);

    void dnsStart(@NotNull Call call, @NotNull String str, String str2);

    void proxySelectEnd(@NotNull Call call, @NotNull HttpUrl httpUrl, @NotNull List<Proxy> list, String str);

    void proxySelectStart(@NotNull Call call, @NotNull HttpUrl httpUrl, String str);

    void requestBodyEnd(@NotNull Call call, long j, String str);

    void requestBodyStart(@NotNull Call call, String str);

    void requestFailed(@NotNull Call call, @NotNull IOException iOException, String str);

    void requestHeadersEnd(@NotNull Call call, @NotNull Request request, String str);

    void requestHeadersStart(@NotNull Call call, String str);

    void responseBodyEnd(@NotNull Call call, long j, String str);

    void responseBodyStart(@NotNull Call call, String str);

    void responseFailed(@NotNull Call call, @NotNull IOException iOException, String str);

    void responseHeadersEnd(@NotNull Call call, @NotNull Response response, String str);

    void responseHeadersStart(Call call, String str);

    void secureConnectEnd(@NotNull Call call, @Nullable Handshake handshake, String str);

    void secureConnectStart(@NotNull Call call, String str);
}
