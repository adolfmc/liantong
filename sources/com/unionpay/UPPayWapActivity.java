package com.unionpay;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.unionpay.utils.C10919f;
import com.unionpay.utils.C10920g;
import com.unionpay.utils.C10921h;
import com.unionpay.utils.C10924k;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class UPPayWapActivity extends Activity {

    /* renamed from: a */
    private static String f20626a = "ex_mode";
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private WebView f20627b;

    /* renamed from: c */
    private WebViewJavascriptBridge f20628c;

    /* renamed from: d */
    private AlertDialog f20629d;

    /* renamed from: e */
    private boolean f20630e = false;

    /* renamed from: f */
    private String f20631f = "";

    /* renamed from: g */
    private String f20632g;

    /* renamed from: h */
    private View f20633h;

    /* renamed from: i */
    private InterfaceC10741ab f20634i;

    /* renamed from: a */
    private View m5979a(RelativeLayout relativeLayout, View.OnClickListener onClickListener) {
        ImageView imageView = new ImageView(this);
        imageView.setBackgroundDrawable(C10920g.m5837a(C10921h.f20830b));
        int m5838a = C10919f.m5838a(this, 24.0f);
        int m5838a2 = C10919f.m5838a(this, 18.0f);
        int m5838a3 = C10919f.m5838a(this, 14.0f);
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(m5838a, m5838a);
        layoutParams.addRule(9, -1);
        layoutParams.addRule(10, -1);
        layoutParams.setMargins(m5838a2, m5838a3, 0, 0);
        relativeLayout.addView(imageView, layoutParams);
        if (onClickListener == null) {
            imageView.setOnClickListener(new View$OnClickListenerC10763m(this));
        } else {
            imageView.setOnClickListener(onClickListener);
        }
        return imageView;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5978a(UPPayWapActivity uPPayWapActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(uPPayWapActivity);
        uPPayWapActivity.f20629d = builder.create();
        builder.setMessage(C10924k.m5827a().f20833a);
        builder.setTitle(C10924k.m5827a().f20836d);
        builder.setPositiveButton(C10924k.m5827a().f20834b, new DialogInterface$OnClickListenerC10765o(uPPayWapActivity));
        builder.setNegativeButton(C10924k.m5827a().f20835c, new DialogInterface$OnClickListenerC10766p(uPPayWapActivity));
        builder.create().show();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m5975a(UPPayWapActivity uPPayWapActivity, boolean z) {
        View view = uPPayWapActivity.f20633h;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m5974a(String str, String str2) {
        Intent intent = new Intent();
        intent.putExtra("pay_result", str);
        intent.putExtra("result_data", str2);
        setResult(-1, intent);
        finish();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m5970b(String str, String str2, String str3) {
        try {
            JSONObject jSONObject = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject.put("code", str);
            }
            if (str2 != null) {
                jSONObject.put("msg", str2);
            }
            if (str3 != null) {
                jSONObject.put("value", str3);
            }
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static String m5969b(String str, String str2, JSONObject jSONObject) {
        try {
            JSONObject jSONObject2 = new JSONObject("{\"code\":\"0\",\"msg\":\"success\"}");
            if (str != null) {
                jSONObject2.put("code", str);
            }
            if (str2 != null) {
                jSONObject2.put("msg", str2);
            }
            if (jSONObject != null) {
                jSONObject2.put("value", jSONObject);
            }
            return !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    public void finish() {
        try {
            super.finish();
        } catch (Exception unused) {
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x0034  */
    /* JADX WARN: Removed duplicated region for block: B:21:0x003e A[Catch: Exception -> 0x007d, TryCatch #0 {Exception -> 0x007d, blocks: (B:7:0x000a, B:9:0x0010, B:12:0x001e, B:16:0x002e, B:19:0x0036, B:21:0x003e, B:22:0x0044, B:25:0x004c, B:27:0x005f, B:13:0x0023, B:28:0x006b, B:30:0x006f), top: B:37:0x000a }] */
    /* JADX WARN: Removed duplicated region for block: B:24:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x005f A[Catch: Exception -> 0x007d, TryCatch #0 {Exception -> 0x007d, blocks: (B:7:0x000a, B:9:0x0010, B:12:0x001e, B:16:0x002e, B:19:0x0036, B:21:0x003e, B:22:0x0044, B:25:0x004c, B:27:0x005f, B:13:0x0023, B:28:0x006b, B:30:0x006f), top: B:37:0x000a }] */
    @Override // android.app.Activity
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    protected void onActivityResult(int r3, int r4, android.content.Intent r5) {
        /*
            r2 = this;
            super.onActivityResult(r3, r4, r5)
            r0 = 1
            if (r3 != r0) goto L8e
            r3 = -1
            if (r4 != r3) goto L8e
            r3 = 0
            android.os.Bundle r4 = r5.getExtras()     // Catch: java.lang.Exception -> L7d
            if (r4 == 0) goto L6b
            java.lang.String r5 = ""
            java.lang.String r0 = ""
            java.lang.String r1 = "pay_result"
            boolean r1 = r4.containsKey(r1)     // Catch: java.lang.Exception -> L7d
            if (r1 == 0) goto L23
            java.lang.String r5 = "pay_result"
        L1e:
            java.lang.String r5 = r4.getString(r5)     // Catch: java.lang.Exception -> L7d
            goto L2e
        L23:
            java.lang.String r1 = "code"
            boolean r1 = r4.containsKey(r1)     // Catch: java.lang.Exception -> L7d
            if (r1 == 0) goto L2e
            java.lang.String r5 = "code"
            goto L1e
        L2e:
            boolean r1 = android.text.TextUtils.isEmpty(r5)     // Catch: java.lang.Exception -> L7d
            if (r1 == 0) goto L36
            java.lang.String r5 = ""
        L36:
            java.lang.String r1 = "data"
            boolean r1 = r4.containsKey(r1)     // Catch: java.lang.Exception -> L7d
            if (r1 == 0) goto L44
            java.lang.String r0 = "data"
            java.lang.String r0 = r4.getString(r0)     // Catch: java.lang.Exception -> L7d
        L44:
            boolean r4 = android.text.TextUtils.isEmpty(r0)     // Catch: java.lang.Exception -> L7d
            if (r4 == 0) goto L4c
            java.lang.String r0 = ""
        L4c:
            org.json.JSONObject r4 = new org.json.JSONObject     // Catch: java.lang.Exception -> L7d
            r4.<init>()     // Catch: java.lang.Exception -> L7d
            java.lang.String r1 = "code"
            r4.put(r1, r5)     // Catch: java.lang.Exception -> L7d
            java.lang.String r5 = "data"
            r4.put(r5, r0)     // Catch: java.lang.Exception -> L7d
            com.unionpay.ab r5 = r2.f20634i     // Catch: java.lang.Exception -> L7d
            if (r5 == 0) goto L8c
            com.unionpay.ab r5 = r2.f20634i     // Catch: java.lang.Exception -> L7d
            java.lang.String r0 = "0"
            java.lang.String r4 = m5969b(r0, r3, r4)     // Catch: java.lang.Exception -> L7d
            r5.mo5826a(r4)     // Catch: java.lang.Exception -> L7d
            goto L8c
        L6b:
            com.unionpay.ab r4 = r2.f20634i     // Catch: java.lang.Exception -> L7d
            if (r4 == 0) goto L8c
            com.unionpay.ab r4 = r2.f20634i     // Catch: java.lang.Exception -> L7d
            java.lang.String r5 = "1"
            java.lang.String r0 = "No pay result"
            java.lang.String r5 = m5970b(r5, r0, r3)     // Catch: java.lang.Exception -> L7d
            r4.mo5826a(r5)     // Catch: java.lang.Exception -> L7d
            goto L8c
        L7d:
            com.unionpay.ab r4 = r2.f20634i
            if (r4 == 0) goto L8c
            java.lang.String r5 = "1"
            java.lang.String r0 = "No pay result"
            java.lang.String r5 = m5970b(r5, r0, r3)
            r4.mo5826a(r5)
        L8c:
            r2.f20634i = r3
        L8e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayWapActivity.onActivityResult(int, int, android.content.Intent):void");
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 409);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            if (this.f20630e) {
                WebView webView = this.f20627b;
                if (webView != null && webView.canGoBack()) {
                    this.f20627b.goBack();
                    return true;
                }
                m5974a("cancel", (String) null);
            } else {
                onPause();
            }
            return true;
        }
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
