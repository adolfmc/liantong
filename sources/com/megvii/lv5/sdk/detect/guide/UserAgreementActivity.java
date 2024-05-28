package com.megvii.lv5.sdk.detect.guide;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.megvii.lv5.C5399c3;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.C5527o2;
import com.megvii.lv5.C5667z2;
import com.megvii.lv5.sdk.C5559R;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.networkbench.agent.impl.instrumentation.NBSWebLoadInstrument;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UserAgreementActivity extends Activity {

    /* renamed from: g */
    public static final /* synthetic */ int f13509g = 0;
    public NBSTraceUnit _nbs_trace;

    /* renamed from: a */
    public WebView f13510a;

    /* renamed from: b */
    public LinearLayout f13511b;

    /* renamed from: c */
    public ImageView f13512c;

    /* renamed from: d */
    public int f13513d;

    /* renamed from: e */
    public int f13514e;

    /* renamed from: f */
    public String f13515f;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    @NBSInstrumented
    /* renamed from: com.megvii.lv5.sdk.detect.guide.UserAgreementActivity$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class View$OnClickListenerC5596a implements View.OnClickListener {
        public View$OnClickListenerC5596a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            UserAgreementActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        new WebView(this).destroy();
        requestWindowFeature(1);
        setContentView(C5559R.C5563layout.megvii_liveness_user_agreement);
        Intent intent = getIntent();
        this.f13514e = intent.getIntExtra("link_type", 0);
        String stringExtra = intent.getStringExtra("language");
        this.f13515f = stringExtra;
        C5527o2.m13236b(this, stringExtra);
        ImageView imageView = (ImageView) findViewById(C5559R.C5562id.img_bar_left);
        this.f13512c = imageView;
        imageView.setImageDrawable(C5527o2.m13237b(this, C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_agreement_close_pressed)), C5667z2.m12879a(this).m12877b(getResources().getString(C5559R.string.key_liveness_agreement_close_normal))));
        LinearLayout linearLayout = (LinearLayout) findViewById(C5559R.C5562id.ll_bar_left);
        this.f13511b = linearLayout;
        linearLayout.setOnClickListener(new View$OnClickListenerC5596a());
        this.f13510a = (WebView) findViewById(C5559R.C5562id.web_agreement);
        this.f13513d = C5527o2.m13223h(getApplicationContext()).f12973b;
        int i = this.f13514e;
        String str = null;
        r1 = null;
        JSONObject jSONObject = null;
        r1 = null;
        JSONObject jSONObject2 = null;
        if (i == 0) {
            C5402d.f12429a = "FaceIDZFAC";
            String m13256a = C5527o2.m13256a(this);
            int i2 = this.f13513d;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("type", "track");
                    jSONObject3.put("project", C5402d.f12429a);
                    jSONObject3.put("event_id", UUID.randomUUID().toString());
                    jSONObject3.put("time", System.currentTimeMillis());
                    jSONObject3.put("event", "enter_face_agreement_page");
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("liveness", i2);
                    jSONObject4.put("biz_token", m13256a);
                    jSONObject4.put("try_times", 0);
                    int i3 = C5402d.f12431c + 1;
                    C5402d.f12431c = i3;
                    jSONObject4.put("index", i3);
                    jSONObject3.put("properties", jSONObject4);
                    C5402d.f12430b = "enter_face_agreement_page";
                    jSONObject = jSONObject3;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject);
            str = C5527o2.m13223h(this).f13047z1;
        } else if (i == 1) {
            C5402d.f12429a = "FaceIDZFAC";
            String m13256a2 = C5527o2.m13256a(this);
            int i4 = this.f13513d;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject5 = new JSONObject();
                    jSONObject5.put("type", "track");
                    jSONObject5.put("project", C5402d.f12429a);
                    jSONObject5.put("event_id", UUID.randomUUID().toString());
                    jSONObject5.put("time", System.currentTimeMillis());
                    jSONObject5.put("event", "enter_credit_agreement_page");
                    JSONObject jSONObject6 = new JSONObject();
                    jSONObject6.put("liveness", i4);
                    jSONObject6.put("biz_token", m13256a2);
                    jSONObject6.put("try_times", 0);
                    int i5 = C5402d.f12431c + 1;
                    C5402d.f12431c = i5;
                    jSONObject6.put("index", i5);
                    jSONObject5.put("properties", jSONObject6);
                    C5402d.f12430b = "enter_credit_agreement_page";
                    jSONObject2 = jSONObject5;
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
            }
            C5399c3.m13606a(jSONObject2);
            str = C5527o2.m13223h(this).f12901A1;
        }
        if (str != null && !"".equals(str)) {
            WebView webView = this.f13510a;
            if (webView instanceof Object) {
                NBSWebLoadInstrument.loadUrl((Object) webView, str);
            } else {
                webView.loadUrl(str);
            }
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.app.Activity
    public void onDestroy() {
        int i = this.f13514e;
        JSONObject jSONObject = null;
        if (i == 0) {
            C5402d.f12429a = "FaceIDZFAC";
            String m13256a = C5527o2.m13256a(this);
            int i2 = this.f13513d;
            if (!C5402d.f12432d) {
                try {
                    JSONObject jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", "exit_face_agreement_page");
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", i2);
                    jSONObject3.put("biz_token", m13256a);
                    jSONObject3.put("try_times", 0);
                    int i3 = C5402d.f12431c + 1;
                    C5402d.f12431c = i3;
                    jSONObject3.put("index", i3);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = "exit_face_agreement_page";
                    jSONObject = jSONObject2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } else {
            if (i == 1) {
                C5402d.f12429a = "FaceIDZFAC";
                String m13256a2 = C5527o2.m13256a(this);
                int i4 = this.f13513d;
                if (!C5402d.f12432d) {
                    try {
                        JSONObject jSONObject4 = new JSONObject();
                        jSONObject4.put("type", "track");
                        jSONObject4.put("project", C5402d.f12429a);
                        jSONObject4.put("event_id", UUID.randomUUID().toString());
                        jSONObject4.put("time", System.currentTimeMillis());
                        jSONObject4.put("event", "exit_credit_agreement_page");
                        JSONObject jSONObject5 = new JSONObject();
                        jSONObject5.put("liveness", i4);
                        jSONObject5.put("biz_token", m13256a2);
                        jSONObject5.put("try_times", 0);
                        int i5 = C5402d.f12431c + 1;
                        C5402d.f12431c = i5;
                        jSONObject5.put("index", i5);
                        jSONObject4.put("properties", jSONObject5);
                        C5402d.f12430b = "exit_credit_agreement_page";
                        jSONObject = jSONObject4;
                    } catch (JSONException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            super.onDestroy();
        }
        C5399c3.m13606a(jSONObject);
        super.onDestroy();
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }
}
