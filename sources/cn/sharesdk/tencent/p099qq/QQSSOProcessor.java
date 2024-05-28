package cn.sharesdk.tencent.p099qq;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import cn.sharesdk.framework.authorize.SSOAuthorizeActivity;
import cn.sharesdk.framework.authorize.SSOProcessor;
import cn.sharesdk.tencent.p099qq.utils.DownLoadWebPage;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.tencent.qq.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class QQSSOProcessor extends SSOProcessor {

    /* renamed from: d */
    private String f3178d;

    /* renamed from: e */
    private String f3179e;

    /* renamed from: f */
    private String f3180f;

    public QQSSOProcessor(SSOAuthorizeActivity sSOAuthorizeActivity) {
        super(sSOAuthorizeActivity);
    }

    /* renamed from: a */
    public void m21429a(String str, String str2, String str3) {
        this.f3178d = str;
        this.f3179e = str2;
        this.f3180f = str3;
    }

    @Override // cn.sharesdk.framework.authorize.SSOProcessor
    /* renamed from: a */
    public void mo21356a() {
        if (TextUtils.isEmpty(this.f3180f) || this.f3180f.equals("com.tencent.qqlite")) {
            m21428b();
            this.f2859a.finish();
            return;
        }
        Intent intent = new Intent();
        intent.setClassName(this.f3180f, "com.tencent.open.agent.AgentActivity");
        if (this.f2859a.getContext().getPackageManager().resolveActivity(intent, 0) == null) {
            this.f2859a.finish();
            if (this.f2861c != null) {
                m21428b();
                return;
            }
            return;
        }
        Bundle bundle = new Bundle();
        bundle.putString("scope", this.f3179e);
        bundle.putString("client_id", this.f3178d);
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
                    String optString = jSONObject.optString("msg");
                    if (!TextUtils.isEmpty(optString) && this.f2861c != null) {
                        this.f2861c.onFailed(new Throwable(optString));
                    } else if (this.f2861c != null) {
                        this.f2861c.onComplete(bundle);
                        this.f2861c = null;
                    }
                } catch (Throwable th) {
                    if (this.f2861c != null) {
                        this.f2861c.onFailed(th);
                    }
                }
            }
        }
    }

    /* renamed from: b */
    private void m21428b() {
        DownLoadWebPage downLoadWebPage = new DownLoadWebPage();
        downLoadWebPage.setListener(this.f2861c);
        downLoadWebPage.show(this.f2859a.getContext(), null);
    }
}
