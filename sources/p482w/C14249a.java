package p482w;

import com.google.gson.JsonParseException;
import java.net.ConnectException;
import java.net.SocketTimeoutException;
import javax.net.ssl.SSLHandshakeException;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: w.a */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C14249a {
    /* renamed from: a */
    public static String m31a(Throwable th) {
        return th instanceof SocketTimeoutException ? "网络不给力，请稍后重试" : th instanceof ConnectException ? "系统开小差了，请重试" : th instanceof JsonParseException ? "数据解析异常，请重试" : th instanceof SSLHandshakeException ? "证书验证失败" : "网络未连接，请检查后重试";
    }
}
