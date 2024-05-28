package cn.sharesdk.framework;

import cn.sharesdk.framework.authorize.AuthorizeHelper;
import cn.sharesdk.framework.authorize.AuthorizeListener;
import cn.sharesdk.framework.authorize.SSOAuthorizeActivity;
import cn.sharesdk.framework.authorize.SSOListener;
import cn.sharesdk.framework.authorize.SSOProcessor;
import cn.sharesdk.framework.authorize.WebAuthorizeActivity;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class PlatformHelper implements AuthorizeHelper {

    /* renamed from: a */
    public Platform f2890a;

    /* renamed from: b */
    private AuthorizeListener f2891b;

    /* renamed from: c */
    private SSOListener f2892c;

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public SSOProcessor getSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        return null;
    }

    public PlatformHelper(Platform platform) {
        this.f2890a = platform;
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public Platform getPlatform() {
        return this.f2890a;
    }

    /* renamed from: c */
    public int m21842c() {
        return this.f2890a.getPlatformId();
    }

    /* renamed from: b */
    protected void m21843b(AuthorizeListener authorizeListener) {
        this.f2891b = authorizeListener;
        WebAuthorizeActivity webAuthorizeActivity = new WebAuthorizeActivity();
        webAuthorizeActivity.setAuthorizeListener(this.f2891b);
        webAuthorizeActivity.show(this);
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public AuthorizeListener getAuthorizeListener() {
        return this.f2891b;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public void m21844a(SSOListener sSOListener) {
        this.f2892c = sSOListener;
        SSOAuthorizeActivity sSOAuthorizeActivity = new SSOAuthorizeActivity();
        sSOAuthorizeActivity.setSSOListener(sSOListener);
        sSOAuthorizeActivity.show(this);
    }

    @Override // cn.sharesdk.framework.authorize.AuthorizeHelper
    public SSOListener getSSOListener() {
        return this.f2892c;
    }
}
