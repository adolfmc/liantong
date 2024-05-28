package com.sina.weibo.sdk.web.p313b;

import android.content.Context;
import android.os.Bundle;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.web.WebData;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sina.weibo.sdk.web.b.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC7125b {

    /* renamed from: Z */
    protected Context f18361Z;

    /* renamed from: aC */
    protected WebData f18362aC;

    /* renamed from: aD */
    protected String f18363aD;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sina.weibo.sdk.web.b.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC7126a {
        void onComplete();

        void onError(String str);
    }

    /* renamed from: a */
    protected abstract void mo8007a(Bundle bundle);

    /* renamed from: a */
    public void mo8006a(InterfaceC7126a interfaceC7126a) {
    }

    /* renamed from: b */
    protected abstract void mo8005b(Bundle bundle);

    public abstract String getUrl();

    /* renamed from: t */
    public boolean mo8004t() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractC7125b() {
    }

    public AbstractC7125b(AuthInfo authInfo, int i, String str, String str2) {
        this.f18362aC = new WebData(authInfo, i, str, str2);
        StringBuilder sb = new StringBuilder();
        sb.append(System.currentTimeMillis());
        this.f18363aD = sb.toString();
    }

    /* renamed from: u */
    public final WebData m8008u() {
        return this.f18362aC;
    }

    public final void setContext(Context context) {
        this.f18361Z = context;
    }

    public final Bundle writeToBundle(Bundle bundle) {
        bundle.putParcelable("web_data", this.f18362aC);
        switch (this.f18362aC.getType()) {
            case 1:
                bundle.putInt("web_type", 1);
                break;
            case 2:
                bundle.putInt("web_type", 2);
                break;
            case 3:
                bundle.putInt("web_type", 3);
                break;
        }
        bundle.putString("_weibo_transaction", this.f18363aD);
        mo8007a(bundle);
        return bundle;
    }

    public final void readFromBundle(Bundle bundle) {
        this.f18362aC = (WebData) bundle.getParcelable("web_data");
        this.f18363aD = bundle.getString("_weibo_transaction");
        mo8005b(bundle);
    }
}
