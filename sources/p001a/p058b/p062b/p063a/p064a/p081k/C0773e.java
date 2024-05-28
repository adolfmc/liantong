package p001a.p058b.p062b.p063a.p064a.p081k;

import com.baidu.uaq.agent.android.UAQ;
import java.io.FileNotFoundException;
import java.net.ConnectException;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import javax.net.ssl.SSLException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.conn.ConnectTimeoutException;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0751c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.k.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0773e {
    static {
        C0751c c0751c = C0749a.f2299a;
        UAQ.getInstance();
    }

    /* renamed from: a */
    public static int m22239a(Exception exc) {
        if (exc instanceof ClientProtocolException) {
            return -1011;
        }
        if (exc instanceof UnknownHostException) {
            return -1006;
        }
        if ((exc instanceof SocketTimeoutException) || (exc instanceof ConnectTimeoutException)) {
            return -1001;
        }
        if (exc instanceof ConnectException) {
            return -1004;
        }
        if (exc instanceof MalformedURLException) {
            return -1000;
        }
        if (exc instanceof SocketException) {
            return -2001;
        }
        if (exc instanceof ProtocolException) {
            return -3001;
        }
        if (exc instanceof FileNotFoundException) {
            return -4001;
        }
        return !(exc instanceof SSLException) ? -1 : -1200;
    }
}
