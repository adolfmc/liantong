package p000;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: -$$Lambda$LQhMso8r2WUnkkClsuVLHXgVK8A */
/* loaded from: E:\567196_dexfile_execute.dex */
public final /* synthetic */ class lambda implements HostnameVerifier {
    public static final /* synthetic */ lambda INSTANCE = new lambda();

    private /* synthetic */ lambda() {
    }

    @Override // javax.net.ssl.HostnameVerifier
    public final boolean verify(String str, SSLSession sSLSession) {
        return ApiBase.m195a(str, sSLSession);
    }
}
