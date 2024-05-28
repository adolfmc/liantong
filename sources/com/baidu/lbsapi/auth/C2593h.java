package com.baidu.lbsapi.auth;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.lbsapi.auth.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
class C2593h implements HostnameVerifier {

    /* renamed from: a */
    final /* synthetic */ C2592g f4981a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C2593h(C2592g c2592g) {
        this.f4981a = c2592g;
    }

    @Override // javax.net.ssl.HostnameVerifier
    public boolean verify(String str, SSLSession sSLSession) {
        if ("api.map.baidu.com".equals(str)) {
            return true;
        }
        return HttpsURLConnection.getDefaultHostnameVerifier().verify(str, sSLSession);
    }
}
