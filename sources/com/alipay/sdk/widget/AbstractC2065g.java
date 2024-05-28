package com.alipay.sdk.widget;

import android.app.Activity;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.CookieSyncManager;
import android.widget.FrameLayout;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC2065g extends FrameLayout {

    /* renamed from: a */
    protected Activity f3940a;

    /* renamed from: a */
    public abstract void mo20605a();

    /* renamed from: a */
    public abstract void mo20597a(String str);

    /* renamed from: b */
    public abstract boolean mo20593b();

    public AbstractC2065g(Activity activity) {
        super(activity);
        this.f3940a = activity;
    }

    /* renamed from: a */
    public void m20607a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        CookieSyncManager.createInstance(this.f3940a.getApplicationContext()).sync();
        CookieManager.getInstance().setCookie(str, str2);
        CookieSyncManager.getInstance().sync();
    }
}
