package com.p319ss.android.downloadlib.addownload.compliance;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.util.Pair;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.bytedance.sdk.openadsdk.C3958R;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.p319ss.android.downloadlib.addownload.model.C9922ox;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import java.util.List;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AppDetailInfoActivity extends Activity {
    public NBSTraceUnit _nbs_trace;

    /* renamed from: b */
    private LinearLayout f18925b;

    /* renamed from: h */
    private long f18926h;

    /* renamed from: hj */
    private RecyclerView f18927hj;

    /* renamed from: ko */
    private List<Pair<String, String>> f18928ko;

    /* renamed from: mb */
    private ImageView f18929mb;

    /* renamed from: ox */
    private TextView f18930ox;

    /* renamed from: u */
    private long f18931u;

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        Tracker.dispatchTouchEvent(motionEvent);
        return super.dispatchTouchEvent(motionEvent);
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

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(C3958R.C3963layout.ttdownloader_activity_app_detail_info);
        if (m7687mb()) {
            m7684ox();
        } else {
            C10085b.m6920mb((Activity) this);
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* renamed from: mb */
    public static void m7686mb(Activity activity, long j) {
        Intent intent = new Intent(activity, AppDetailInfoActivity.class);
        intent.putExtra("app_info_id", j);
        activity.startActivity(intent);
    }

    /* renamed from: mb */
    private boolean m7687mb() {
        this.f18926h = getIntent().getLongExtra("app_info_id", 0L);
        C9922ox m7676mb = C9857b.m7677mb().m7676mb(this.f18926h);
        if (m7676mb == null) {
            return false;
        }
        this.f18931u = m7676mb.f19128ox;
        this.f18928ko = m7676mb.f19130ww;
        return true;
    }

    /* renamed from: ox */
    private void m7684ox() {
        this.f18929mb = (ImageView) findViewById(C3958R.C3961id.iv_detail_back);
        this.f18930ox = (TextView) findViewById(C3958R.C3961id.tv_empty);
        this.f18927hj = (RecyclerView) findViewById(C3958R.C3961id.permission_list);
        this.f18925b = (LinearLayout) findViewById(C3958R.C3961id.ll_download);
        if (this.f18928ko.isEmpty()) {
            this.f18927hj.setVisibility(8);
            this.f18930ox.setVisibility(0);
        } else {
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
            linearLayoutManager.setOrientation(1);
            this.f18927hj.setLayoutManager(linearLayoutManager);
            this.f18927hj.setAdapter(new C9854mb());
        }
        this.f18929mb.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                C9860h.m7670mb("lp_app_detail_click_close", AppDetailInfoActivity.this.f18931u);
                AppDetailInfoActivity.this.finish();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.f18925b.setOnClickListener(new View.OnClickListener() { // from class: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                C9860h.m7670mb("lp_app_detail_click_download", AppDetailInfoActivity.this.f18931u);
                C9873ox.m7653mb().m7645ox(AppDetailInfoActivity.this.f18931u);
                C10085b.m6920mb((Activity) AppDetailInfoActivity.this);
                C10085b.m6920mb(C9873ox.m7653mb().m7646ox());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        C9860h.m7670mb("lp_app_detail_click_close", this.f18931u);
        super.onBackPressed();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.compliance.AppDetailInfoActivity$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class C9854mb extends RecyclerView.Adapter<Object> {
        private C9854mb() {
        }
    }
}
