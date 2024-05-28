package com.sina.weibo.sdk.p310a;

import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.net.C7098b;
import com.sina.weibo.sdk.net.C7101e;
import com.sina.weibo.sdk.net.InterfaceC7099c;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7076e extends AbstractC7066c<Void, Void, String> {

    /* renamed from: aa */
    private InterfaceC7099c<String> f18295aa;

    /* renamed from: ab */
    private Throwable f18296ab;

    /* renamed from: ad */
    private String f18297ad;

    /* renamed from: af */
    private Oauth2AccessToken f18298af;

    @Override // com.sina.weibo.sdk.p310a.AbstractC7066c
    protected final /* synthetic */ void onPostExecute(String str) {
        String str2 = str;
        Throwable th = this.f18296ab;
        if (th != null) {
            InterfaceC7099c<String> interfaceC7099c = this.f18295aa;
            if (interfaceC7099c != null) {
                interfaceC7099c.onError(th);
                return;
            }
            return;
        }
        InterfaceC7099c<String> interfaceC7099c2 = this.f18295aa;
        if (interfaceC7099c2 != null) {
            interfaceC7099c2.mo8003a(str2);
        }
    }

    public C7076e(String str, Oauth2AccessToken oauth2AccessToken, InterfaceC7099c<String> interfaceC7099c) {
        this.f18297ad = str;
        this.f18298af = oauth2AccessToken;
        this.f18295aa = interfaceC7099c;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.sina.weibo.sdk.p310a.AbstractC7066c
    /* renamed from: n */
    public String mo8096l() {
        try {
            C7101e.C7102a c7102a = new C7101e.C7102a();
            c7102a.f18318i = "https://api.weibo.com/oauth2/access_token";
            return new C7098b().mo8058a(c7102a.m8052b("client_id", this.f18297ad).m8052b("appKey", this.f18297ad).m8052b("grant_type", "refresh_token").m8052b("refresh_token", this.f18298af.getRefreshToken()).m8051e()).mo8050f();
        } catch (Throwable th) {
            th.printStackTrace();
            this.f18296ab = th;
            return null;
        }
    }
}
