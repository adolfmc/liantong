package com.sinovatech.unicom.separatemodule.quanxianshuoming.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthManager;
import com.sinovatech.unicom.separatemodule.miniprogram.h5auth.H5AuthRecord;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.adapter.YeWuInfoAdapter;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class QuanXianInfoActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private YeWuInfoAdapter adapter;
    private ImageButton backButton;
    private AppCompatActivity context;
    private List<H5AuthRecord> list = new ArrayList();
    private RecyclerView rl_qx;
    private TextView titleView;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(@Nullable Bundle bundle) {
        JniLib.m15918cV(this, bundle, 106);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view.getId() == 2131296473) {
            finish();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void initDatas(String str) {
        this.list.clear();
        List<H5AuthRecord> h5AuthRecord = H5AuthManager.getInstance(this).getH5AuthRecord(str);
        if (h5AuthRecord != null && h5AuthRecord.size() > 0) {
            this.list.addAll(h5AuthRecord);
        }
        this.adapter.notifyDataSetChanged();
    }
}
