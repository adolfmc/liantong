package cn.sharesdk.tencent.qzone.utils;

import android.content.Intent;
import android.os.Bundle;
import cn.sharesdk.framework.authorize.SSOAuthorizeActivity;
import cn.sharesdk.framework.authorize.SSOProcessor;
import cn.sharesdk.framework.utils.AppUtils;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.tencent.qzone.utils.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class QZoneSSOProcessor extends SSOProcessor {

    /* renamed from: d */
    private String f3222d;

    /* renamed from: e */
    private String f3223e;

    public QZoneSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        super(sSOAuthorizeActivity);
    }

    /* renamed from: a */
    public void m21354a(String str, String str2) {
        this.f3222d = str;
        this.f3223e = str2;
    }

    @Override // cn.sharesdk.framework.authorize.SSOProcessor
    /* renamed from: a */
    public void mo21356a() {
        try {
            if (AppUtils.m21715b("com.tencent.mobileqq", 0) == null) {
                this.f2859a.finish();
                if (this.f2861c != null) {
                    this.f2861c.onFailed(new TencentSSOClientNotInstalledException());
                    return;
                }
                return;
            }
            Intent intent = new Intent();
            intent.setClassName("com.tencent.mobileqq", "com.tencent.open.agent.AgentActivity");
            if (this.f2859a.getContext().getPackageManager().resolveActivity(intent, 0) == null) {
                this.f2859a.finish();
                if (this.f2861c != null) {
                    this.f2861c.onFailed(new TencentSSOClientNotInstalledException());
                    return;
                }
                return;
            }
            Bundle bundle = new Bundle();
            bundle.putString("scope", this.f3223e);
            bundle.putString("client_id", this.f3222d);
            bundle.putString("pf", "openmobile_android");
            bundle.putString("need_pay", "1");
            intent.putExtra("key_params", bundle);
            intent.putExtra("key_request_code", this.f2860b);
            intent.putExtra("key_action", "action_login");
            try {
                this.f2859a.startActivityForResult(intent, this.f2860b);
            } catch (Throwable th) {
                this.f2859a.finish();
                if (this.f2861c != null) {
                    this.f2861c.onFailed(th);
                }
            }
        } catch (Throwable unused) {
            this.f2859a.finish();
            if (this.f2861c != null) {
                this.f2861c.onFailed(new TencentSSOClientNotInstalledException());
            }
        }
    }

    @Override // cn.sharesdk.framework.authorize.SSOProcessor
    /* renamed from: a */
    public void mo21355a(int i, int i2, Intent intent) {
        this.f2859a.finish();
        if (i2 == 0) {
            if (this.f2861c != null) {
                this.f2861c.onCancel();
            }
        } else if (intent == null) {
            if (this.f2861c != null) {
                this.f2861c.onFailed(new Throwable("response is empty"));
            }
        } else {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                if (this.f2861c != null) {
                    this.f2861c.onFailed(new Throwable("response is empty"));
                }
            } else if (!extras.containsKey("key_response")) {
                if (this.f2861c != null) {
                    this.f2861c.onFailed(new Throwable("response is empty"));
                }
            } else {
                String string = extras.getString("key_response");
                if (string == null || string.length() <= 0) {
                    if (this.f2861c != null) {
                        this.f2861c.onFailed(new Throwable("response is empty"));
                        return;
                    }
                    return;
                }
                try {
                    JSONObject jSONObject = new JSONObject(string);
                    Bundle bundle = new Bundle();
                    bundle.putInt("ret", jSONObject.optInt("ret"));
                    bundle.putString("pay_token", jSONObject.optString("pay_token"));
                    bundle.putString("pf", jSONObject.optString("pf"));
                    bundle.putString("open_id", jSONObject.optString("openid"));
                    bundle.putString("expires_in", jSONObject.optString("expires_in"));
                    bundle.putString("pfkey", jSONObject.optString("pfkey"));
                    bundle.putString("msg", jSONObject.optString("msg"));
                    bundle.putString("access_token", jSONObject.optString("access_token"));
                    if (this.f2861c != null) {
                        this.f2861c.onComplete(bundle);
                    }
                } catch (Throwable th) {
                    if (this.f2861c != null) {
                        this.f2861c.onFailed(th);
                    }
                }
            }
        }
    }
}
