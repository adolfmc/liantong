package com.sina.weibo.sdk.p310a;

import android.content.Context;
import com.sina.weibo.sdk.net.C7098b;
import com.sina.weibo.sdk.net.C7101e;
import com.sina.weibo.sdk.net.HttpManager;
import com.sina.weibo.sdk.net.InterfaceC7099c;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.a.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C7075d extends AbstractC7066c<Void, Void, String> {

    /* renamed from: Z */
    private Context f18289Z;

    /* renamed from: aa */
    private InterfaceC7099c<String> f18290aa;

    /* renamed from: ab */
    private Throwable f18291ab;

    /* renamed from: ac */
    private String f18292ac;

    /* renamed from: ad */
    private String f18293ad;

    /* renamed from: ae */
    private String f18294ae;

    @Override // com.sina.weibo.sdk.p310a.AbstractC7066c
    protected final /* synthetic */ void onPostExecute(String str) {
        String str2 = str;
        Throwable th = this.f18291ab;
        if (th != null) {
            InterfaceC7099c<String> interfaceC7099c = this.f18290aa;
            if (interfaceC7099c != null) {
                interfaceC7099c.onError(th);
                return;
            }
            return;
        }
        InterfaceC7099c<String> interfaceC7099c2 = this.f18290aa;
        if (interfaceC7099c2 != null) {
            interfaceC7099c2.mo8003a(str2);
        }
    }

    public C7075d(Context context, String str, String str2, String str3, InterfaceC7099c<String> interfaceC7099c) {
        this.f18289Z = context;
        this.f18292ac = str;
        this.f18293ad = str2;
        this.f18294ae = str3;
        this.f18290aa = interfaceC7099c;
    }

    /* renamed from: d */
    private String m8098d(String str) {
        return HttpManager.m8059a(this.f18289Z, "", this.f18294ae, this.f18293ad, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Override // com.sina.weibo.sdk.p310a.AbstractC7066c
    /* renamed from: n */
    public String mo8096l() {
        try {
            String valueOf = String.valueOf(System.currentTimeMillis() / 1000);
            C7101e.C7102a c7102a = new C7101e.C7102a();
            c7102a.f18318i = "https://service.weibo.com/share/mobilesdk_uppic.php";
            return new C7098b().mo8058a(c7102a.m8053a("oauth_timestamp", valueOf).m8053a("oauth_sign", m8098d(valueOf)).m8052b("appKey", this.f18293ad).m8052b("oauth_timestamp", valueOf).m8052b("oauth_sign", m8098d(valueOf)).m8052b("img", this.f18292ac).m8051e()).mo8050f();
        } catch (Throwable th) {
            th.printStackTrace();
            this.f18291ab = th;
            return null;
        }
    }
}
