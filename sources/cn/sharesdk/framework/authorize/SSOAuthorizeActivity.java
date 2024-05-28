package cn.sharesdk.framework.authorize;

import android.content.Intent;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class SSOAuthorizeActivity extends AbstractAuthorizeActivity {
    private static final int DEFAULT_AUTH_ACTIVITY_CODE = 32973;
    protected SSOListener listener;
    private SSOProcessor sso;

    public void setSSOListener(SSOListener sSOListener) {
        this.listener = sSOListener;
    }

    @Override // com.mob.tools.FakeActivity
    public void onCreate() {
        this.sso = this.helper.getSSOProcessor(this);
        SSOProcessor sSOProcessor = this.sso;
        if (sSOProcessor == null) {
            finish();
            AuthorizeListener authorizeListener = this.helper.getAuthorizeListener();
            if (authorizeListener != null) {
                authorizeListener.onError(new Throwable("Failed to start SSO for " + this.helper.getPlatform().getName()));
                return;
            }
            return;
        }
        sSOProcessor.m21871a(32973);
        this.sso.mo21356a();
    }

    @Override // com.mob.tools.FakeActivity
    public void onActivityResult(int i, int i2, Intent intent) {
        this.sso.mo21355a(i, i2, intent);
    }

    @Override // com.mob.tools.FakeActivity
    public void onNewIntent(Intent intent) {
        this.sso.m21870a(intent);
    }
}
