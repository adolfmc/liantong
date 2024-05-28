package okhttp3.internal.http;

import java.io.IOException;
import java.net.ProtocolException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.Util;
import okhttp3.internal.connection.Exchange;
import okio.BufferedSink;
import okio.Okio;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: CallServerInterceptor.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\t"}, m1890d2 = {"Lokhttp3/internal/http/CallServerInterceptor;", "Lokhttp3/Interceptor;", "forWebSocket", "", "(Z)V", "intercept", "Lokhttp3/Response;", "chain", "Lokhttp3/Interceptor$Chain;", "okhttp"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CallServerInterceptor implements Interceptor {
    private final boolean forWebSocket;

    public CallServerInterceptor(boolean z) {
        this.forWebSocket = z;
    }

    @Override // okhttp3.Interceptor
    @NotNull
    public Response intercept(@NotNull Interceptor.Chain chain) throws IOException {
        boolean z;
        Response build;
        Intrinsics.checkParameterIsNotNull(chain, "chain");
        RealInterceptorChain realInterceptorChain = (RealInterceptorChain) chain;
        Exchange exchange$okhttp = realInterceptorChain.getExchange$okhttp();
        if (exchange$okhttp == null) {
            Intrinsics.throwNpe();
        }
        Request request$okhttp = realInterceptorChain.getRequest$okhttp();
        RequestBody body = request$okhttp.body();
        long currentTimeMillis = System.currentTimeMillis();
        exchange$okhttp.writeRequestHeaders(request$okhttp);
        Response.Builder builder = null;
        if (HttpMethod.permitsRequestBody(request$okhttp.method()) && body != null) {
            if (StringsKt.equals("100-continue", request$okhttp.header("Expect"), true)) {
                exchange$okhttp.flushRequest();
                builder = exchange$okhttp.readResponseHeaders(true);
                exchange$okhttp.responseHeadersStart();
                z = false;
            } else {
                z = true;
            }
            if (builder == null) {
                if (body.isDuplex()) {
                    exchange$okhttp.flushRequest();
                    body.writeTo(Okio.buffer(exchange$okhttp.createRequestBody(request$okhttp, true)));
                } else {
                    BufferedSink buffer = Okio.buffer(exchange$okhttp.createRequestBody(request$okhttp, false));
                    body.writeTo(buffer);
                    buffer.close();
                }
            } else {
                exchange$okhttp.noRequestBody();
                if (!exchange$okhttp.getConnection$okhttp().isMultiplexed()) {
                    exchange$okhttp.noNewExchangesOnConnection();
                }
            }
        } else {
            exchange$okhttp.noRequestBody();
            z = true;
        }
        if (body == null || !body.isDuplex()) {
            exchange$okhttp.finishRequest();
        }
        if (builder == null) {
            builder = exchange$okhttp.readResponseHeaders(false);
            if (builder == null) {
                Intrinsics.throwNpe();
            }
            if (z) {
                exchange$okhttp.responseHeadersStart();
                z = false;
            }
        }
        Response build2 = builder.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
        int code = build2.code();
        if (code == 100) {
            Response.Builder readResponseHeaders = exchange$okhttp.readResponseHeaders(false);
            if (readResponseHeaders == null) {
                Intrinsics.throwNpe();
            }
            if (z) {
                exchange$okhttp.responseHeadersStart();
            }
            build2 = readResponseHeaders.request(request$okhttp).handshake(exchange$okhttp.getConnection$okhttp().handshake()).sentRequestAtMillis(currentTimeMillis).receivedResponseAtMillis(System.currentTimeMillis()).build();
            code = build2.code();
        }
        exchange$okhttp.responseHeadersEnd(build2);
        if (this.forWebSocket && code == 101) {
            build = build2.newBuilder().body(Util.EMPTY_RESPONSE).build();
        } else {
            build = build2.newBuilder().body(exchange$okhttp.openResponseBody(build2)).build();
        }
        if (StringsKt.equals("close", build.request().header("Connection"), true) || StringsKt.equals("close", Response.header$default(build, "Connection", null, 2, null), true)) {
            exchange$okhttp.noNewExchangesOnConnection();
        }
        if (code == 204 || code == 205) {
            ResponseBody body2 = build.body();
            if ((body2 != null ? body2.contentLength() : -1L) > 0) {
                StringBuilder sb = new StringBuilder();
                sb.append("HTTP ");
                sb.append(code);
                sb.append(" had non-zero Content-Length: ");
                ResponseBody body3 = build.body();
                sb.append(body3 != null ? Long.valueOf(body3.contentLength()) : null);
                throw new ProtocolException(sb.toString());
            }
        }
        return build;
    }
}
