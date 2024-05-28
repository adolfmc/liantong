package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.authorize.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class SSOProcessor {

    /* renamed from: a */
    protected SSOAuthorizeActivity f2859a;

    /* renamed from: b */
    protected int f2860b;

    /* renamed from: c */
    protected SSOListener f2861c;

    /* renamed from: a */
    public abstract void mo21356a();

    /* renamed from: a */
    public void mo21355a(int i, int i2, Intent intent) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m21870a(Intent intent) {
    }

    public SSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        this.f2859a = sSOAuthorizeActivity;
        this.f2861c = sSOAuthorizeActivity.getHelper().getSSOListener();
    }

    /* renamed from: a */
    public void m21871a(int i) {
        this.f2860b = i;
    }
}
