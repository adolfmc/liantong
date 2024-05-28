package com.p319ss.android.socialbase.appdownloader.view;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.C10125lz;
import com.p319ss.android.socialbase.appdownloader.C10140ox;
import com.p319ss.android.socialbase.appdownloader.C10174ww;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10088b;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10091jb;
import com.p319ss.android.socialbase.appdownloader.p335b.InterfaceC10092je;
import com.p319ss.android.socialbase.appdownloader.p337hj.C10117mb;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class JumpUnknownSourceActivity extends Activity {
    public NBSTraceUnit _nbs_trace;
    @Nullable

    /* renamed from: b */
    private Intent f19672b;

    /* renamed from: h */
    private JSONObject f19673h;

    /* renamed from: hj */
    private int f19674hj;

    /* renamed from: mb */
    private InterfaceC10091jb f19675mb;

    /* renamed from: ox */
    private Intent f19676ox;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // android.app.Activity
    protected void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 398);
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

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
        C10174ww.m6487mb().m6485mb(this);
    }

    /* renamed from: mb */
    private void m6502mb() {
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.alpha = 0.0f;
        window.setAttributes(attributes);
    }

    @Override // android.app.Activity
    protected void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        Intent intent = getIntent();
        this.f19676ox = intent;
        if (intent != null) {
            this.f19672b = (Intent) intent.getParcelableExtra("intent");
            this.f19674hj = intent.getIntExtra("id", -1);
            try {
                this.f19673h = new JSONObject(intent.getStringExtra("config"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (this.f19673h == null) {
            C10085b.m6920mb((Activity) this);
            NBSAppInstrumentation.activityResumeEndIns();
            return;
        }
        m6500ox();
        InterfaceC10091jb interfaceC10091jb = this.f19675mb;
        if (interfaceC10091jb != null && !interfaceC10091jb.mo6776ox()) {
            this.f19675mb.mo6777mb();
        } else if (this.f19675mb == null) {
            finish();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    /* renamed from: ox */
    private void m6500ox() {
        if (this.f19675mb != null || this.f19676ox == null) {
            return;
        }
        try {
            InterfaceC10088b m6814mb = C10112hj.m6786x().m6814mb();
            InterfaceC10092je mo6873mb = m6814mb != null ? m6814mb.mo6873mb(this) : null;
            if (mo6873mb == null) {
                mo6873mb = new C10117mb(this);
            }
            int m6745mb = C10125lz.m6745mb(this, "tt_appdownloader_tip");
            int m6745mb2 = C10125lz.m6745mb(this, "tt_appdownloader_label_ok");
            int m6745mb3 = C10125lz.m6745mb(this, "tt_appdownloader_label_cancel");
            String optString = this.f19673h.optString("jump_unknown_source_tips");
            if (TextUtils.isEmpty(optString)) {
                optString = getString(C10125lz.m6745mb(this, "tt_appdownloader_jump_unknown_source_tips"));
            }
            mo6873mb.mo6782mb(m6745mb).mo6779mb(optString).mo6781mb(m6745mb2, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.3
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tracker.onClick(dialogInterface, i);
                    JumpUnknownSourceActivity jumpUnknownSourceActivity = JumpUnknownSourceActivity.this;
                    if (C10140ox.m6718mb(jumpUnknownSourceActivity, jumpUnknownSourceActivity.f19672b, JumpUnknownSourceActivity.this.f19674hj, JumpUnknownSourceActivity.this.f19673h)) {
                        C10140ox.m6728b(JumpUnknownSourceActivity.this.f19674hj, JumpUnknownSourceActivity.this.f19673h);
                    } else {
                        JumpUnknownSourceActivity jumpUnknownSourceActivity2 = JumpUnknownSourceActivity.this;
                        C10140ox.m6715mb((Context) jumpUnknownSourceActivity2, jumpUnknownSourceActivity2.f19672b, true);
                    }
                    C10140ox.m6722mb(JumpUnknownSourceActivity.this.f19674hj, JumpUnknownSourceActivity.this.f19673h);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).mo6778ox(m6745mb3, new DialogInterface.OnClickListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.2
                @Override // android.content.DialogInterface.OnClickListener
                public void onClick(DialogInterface dialogInterface, int i) {
                    Tracker.onClick(dialogInterface, i);
                    if (JumpUnknownSourceActivity.this.f19672b != null) {
                        JumpUnknownSourceActivity jumpUnknownSourceActivity = JumpUnknownSourceActivity.this;
                        C10140ox.m6715mb((Context) jumpUnknownSourceActivity, jumpUnknownSourceActivity.f19672b, true);
                    }
                    C10140ox.m6702ox(JumpUnknownSourceActivity.this.f19674hj, JumpUnknownSourceActivity.this.f19673h);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).mo6780mb(new DialogInterface.OnCancelListener() { // from class: com.ss.android.socialbase.appdownloader.view.JumpUnknownSourceActivity.1
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    if (JumpUnknownSourceActivity.this.f19672b != null) {
                        JumpUnknownSourceActivity jumpUnknownSourceActivity = JumpUnknownSourceActivity.this;
                        C10140ox.m6715mb((Context) jumpUnknownSourceActivity, jumpUnknownSourceActivity.f19672b, true);
                    }
                    C10140ox.m6702ox(JumpUnknownSourceActivity.this.f19674hj, JumpUnknownSourceActivity.this.f19673h);
                    JumpUnknownSourceActivity.this.finish();
                }
            }).mo6871mb(false);
            this.f19675mb = mo6873mb.mo6783mb();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
