package com.bytedance.applog.util;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.TextView;
import com.bytedance.applog.AppLog;
import com.bytedance.applog.C3527R;
import com.bytedance.applog.C3535a;
import com.bytedance.applog.C3557c;
import com.bytedance.applog.C3607j1;
import com.bytedance.applog.C3704u2;
import com.bytedance.applog.IPicker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class SimulateLaunchActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public String f8847a;

    /* renamed from: b */
    public int f8848b;

    /* renamed from: c */
    public int f8849c;

    /* renamed from: d */
    public String f8850d;

    /* renamed from: e */
    public String f8851e;

    /* renamed from: f */
    public String f8852f;

    /* renamed from: g */
    public String f8853g;

    /* renamed from: h */
    public TextView f8854h;

    @NBSInstrumented
    /* renamed from: com.bytedance.applog.util.SimulateLaunchActivity$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class AsyncTaskC3708b extends AsyncTask<Void, Void, JSONObject> {
        public /* synthetic */ AsyncTaskC3708b(C3707a c3707a) {
        }

        @Override // android.os.AsyncTask
        public JSONObject doInBackground(Void[] voidArr) {
            SimulateLaunchActivity simulateLaunchActivity = SimulateLaunchActivity.this;
            return C3607j1.m17264a(simulateLaunchActivity.f8847a, simulateLaunchActivity.f8851e, simulateLaunchActivity.f8848b, simulateLaunchActivity.f8849c, simulateLaunchActivity.f8852f, simulateLaunchActivity.f8850d);
        }

        @Override // android.os.AsyncTask
        public void onPostExecute(JSONObject jSONObject) {
            JSONObject jSONObject2 = jSONObject;
            if (jSONObject2 == null) {
                SimulateLaunchActivity.this.f8854h.setText("启动失败,请按电脑提示检查原因然后重新扫码(response is null)");
                return;
            }
            String optString = jSONObject2.optString("message");
            String optString2 = jSONObject2.optString("Set-Cookie");
            int optInt = jSONObject2.optInt("status");
            if (optString2 != null) {
                optString2 = optString2.substring(0, optString2.indexOf(";"));
            }
            if ("debug_log".equals(SimulateLaunchActivity.this.f8853g) && optInt == 0 && !TextUtils.isEmpty(optString2)) {
                AppLog.setRangersEventVerifyEnable(true, optString2);
                SimulateLaunchActivity.this.finish();
            } else if (!"OK".equals(optString) || TextUtils.isEmpty(optString2)) {
                TextView textView = SimulateLaunchActivity.this.f8854h;
                StringBuilder m17349a = C3535a.m17349a("启动失败,请按电脑提示检查原因然后重新扫码(");
                m17349a.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
                m17349a.append(")");
                textView.setText(m17349a.toString());
            } else {
                Intent launchIntentForPackage = SimulateLaunchActivity.this.getPackageManager().getLaunchIntentForPackage(SimulateLaunchActivity.this.getApplicationInfo().packageName);
                C3557c.f8385h = "bind_query".equals(SimulateLaunchActivity.this.f8853g);
                if (launchIntentForPackage != null) {
                    IPicker iPicker = null;
                    launchIntentForPackage.setPackage(null);
                    SimulateLaunchActivity.this.startActivity(launchIntentForPackage);
                    if (AppLog.getInitConfig() != null && AppLog.getInitConfig().getPicker() != null) {
                        iPicker = AppLog.getInitConfig().getPicker();
                    }
                    if (iPicker != null) {
                        iPicker.setMarqueeCookie(optString2);
                    }
                    AppLog.startSimulator(optString2);
                    SimulateLaunchActivity.this.finish();
                }
            }
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        TextView textView;
        String str;
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C3527R.C3531layout.activity_simulate);
        this.f8854h = (TextView) findViewById(C3527R.C3530id.text_tip);
        if (AppLog.hasStarted()) {
            Uri data = getIntent().getData();
            if (data != null) {
                if (AppLog.getAid().equals(data.getQueryParameter("aid"))) {
                    this.f8853g = data.getQueryParameter("type");
                    "debug_log".equals(this.f8853g);
                    String queryParameter = data.getQueryParameter("url_prefix");
                    C3704u2.m17108a("urlPrefix=" + queryParameter, (Throwable) null);
                    if (TextUtils.isEmpty(queryParameter)) {
                        textView = this.f8854h;
                        str = "启动失败,无url_prefix参数";
                    } else {
                        C3607j1.f8526f = queryParameter;
                        this.f8850d = data.getQueryParameter("qr_param");
                        String str2 = (String) AppLog.getHeaderValue("resolution", null);
                        if (!TextUtils.isEmpty(str2)) {
                            String[] split = str2.split("x");
                            this.f8849c = Integer.valueOf(split[0]).intValue();
                            this.f8848b = Integer.valueOf(split[1]).intValue();
                        }
                        this.f8847a = AppLog.getAid();
                        this.f8852f = AppLog.getDid();
                        try {
                            this.f8851e = getPackageManager().getPackageInfo(getApplicationInfo().packageName, 0).versionName;
                        } catch (PackageManager.NameNotFoundException unused) {
                            this.f8851e = "1.0.0";
                        }
                        new AsyncTaskC3708b(null).execute(new Void[0]);
                    }
                } else {
                    this.f8854h.setText("启动失败,请按电脑提示检查原因然后重新扫码(aid错误)");
                }
            }
            NBSAppInstrumentation.activityCreateEndIns();
            return;
        }
        textView = this.f8854h;
        str = "启动失败,请按电脑提示检查原因然后重新扫码(AppLog未初始化)";
        textView.setText(str);
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }
}
