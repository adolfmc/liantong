package com.bytedance.applog;

import android.annotation.SuppressLint;
import android.app.Application;
import android.os.AsyncTask;
import android.os.Build;
import android.text.TextUtils;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import com.bytedance.applog.picker.Picker;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import org.json.JSONObject;

@NBSInstrumented
@SuppressLint({"ViewConstructor"})
/* renamed from: com.bytedance.applog.b1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class View$OnClickListenerC3552b1 extends C3727x0 implements View.OnClickListener {

    /* renamed from: e */
    public final TextView f8369e;

    /* renamed from: f */
    public final Button f8370f;

    /* renamed from: g */
    public final EditText f8371g;

    /* renamed from: h */
    public final EditText f8372h;

    /* renamed from: i */
    public final TextView f8373i;

    /* renamed from: com.bytedance.applog.b1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC3553a extends AsyncTask<String, Void, JSONObject> {
        public AsyncTaskC3553a() {
        }

        @Override // android.os.AsyncTask
        public JSONObject doInBackground(String[] strArr) {
            String[] strArr2 = strArr;
            return C3634n0.m17215a(strArr2[0], strArr2[1], AppLog.getHeader());
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            JSONObject jSONObject2 = jSONObject;
            View$OnClickListenerC3552b1.this.f8370f.setEnabled(true);
            if (jSONObject2 == null) {
                View$OnClickListenerC3552b1.this.m17043a("网络出错");
                return;
            }
            String optString = jSONObject2.optString("_I_MY_TOKEN_adjf_");
            if (!"success".equals(jSONObject2.optString("message", "")) || TextUtils.isEmpty(optString)) {
                JSONObject optJSONObject = jSONObject2.optJSONObject("data");
                View$OnClickListenerC3552b1.this.m17043a(optJSONObject != null ? optJSONObject.optString("description", "登录失败") : "登录失败");
                return;
            }
            View$OnClickListenerC3552b1 view$OnClickListenerC3552b1 = View$OnClickListenerC3552b1.this;
            view$OnClickListenerC3552b1.f8911d.m17149a(view$OnClickListenerC3552b1.f8371g.getText().toString(), optString);
            View$OnClickListenerC3552b1.this.mo17042b();
        }
    }

    public View$OnClickListenerC3552b1(Application application, Picker picker) {
        super(application, picker);
        LayoutInflater.from(application).inflate(C3527R.C3531layout.login_view, this);
        this.f8373i = (TextView) findViewById(C3527R.C3530id.titleText);
        this.f8371g = (EditText) findViewById(C3527R.C3530id.nameEdit);
        this.f8372h = (EditText) findViewById(C3527R.C3530id.pwdEdit);
        if ("HUAWEI".equalsIgnoreCase(Build.MANUFACTURER) && Build.VERSION.SDK_INT >= 27) {
            this.f8372h.setInputType(1);
            this.f8372h.setTransformationMethod(PasswordTransformationMethod.getInstance());
        }
        this.f8369e = (TextView) findViewById(C3527R.C3530id.accountText);
        this.f8370f = (Button) findViewById(C3527R.C3530id.loginButton);
        this.f8370f.setOnClickListener(this);
        setOnClickListener(this);
    }

    @Override // com.bytedance.applog.C3727x0
    /* renamed from: b */
    public void mo17042b() {
        Button button;
        int i;
        String m17153a = this.f8911d.m17153a();
        if (TextUtils.isEmpty(m17153a)) {
            this.f8373i.setText("登录");
            this.f8371g.setVisibility(0);
            this.f8371g.setText("");
            this.f8372h.setVisibility(0);
            this.f8372h.setText("");
            this.f8369e.setVisibility(8);
            this.f8370f.setText("登录");
            button = this.f8370f;
            i = C3527R.C3529drawable.picker_login_bg;
        } else {
            this.f8373i.setText("已登录");
            this.f8371g.setVisibility(8);
            this.f8372h.setVisibility(8);
            this.f8369e.setVisibility(0);
            TextView textView = this.f8369e;
            textView.setText("当前用户：" + m17153a);
            this.f8370f.setText("注销");
            button = this.f8370f;
            i = C3527R.C3529drawable.picker_logout_bg;
        }
        button.setBackgroundResource(i);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view == this) {
            this.f8911d.m17145d();
        } else if (view == this.f8370f) {
            if (this.f8369e.getVisibility() == 8) {
                String obj = this.f8371g.getText().toString();
                String obj2 = this.f8372h.getText().toString();
                if (obj.length() <= 4 || obj2.length() <= 4) {
                    m17043a("请输入帐号密码");
                } else {
                    this.f8370f.setEnabled(false);
                    new AsyncTaskC3553a().execute(obj, obj2);
                }
            } else {
                this.f8911d.m17149a(null, null);
                mo17042b();
            }
        }
        NBSActionInstrumentation.onClickEventExit();
    }
}
