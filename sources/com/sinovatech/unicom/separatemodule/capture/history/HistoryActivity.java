package com.sinovatech.unicom.separatemodule.capture.history;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.p318ui.BaseActivity;
import java.util.List;

@NBSInstrumented
@SuppressLint({"NewApi"})
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HistoryActivity extends BaseActivity {
    private static final String TAG = "HistoryActivity";
    public NBSTraceUnit _nbs_trace;
    private HistoryItemAdapter adapter;
    private ImageButton backButton;
    private Button clearButton;
    private AlertDialog dialog;
    private HistoryManager historyManager;
    private List<HistoryItem> list;
    private ListView lv_history;
    private RelativeLayout rl_nohistory;
    private TextView titleView;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 78);
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

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.history.HistoryActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC86271 implements View.OnClickListener {
        View$OnClickListenerC86271() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            if (HistoryActivity.this.list.size() <= 0) {
                NBSActionInstrumentation.onClickEventExit();
                return;
            }
            CustomDialogManager.show((Activity) HistoryActivity.this, "清空历史", "是否确认清空历史扫描记录？", true, "取消", "确定", true, new CustomDialogManager.CustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.capture.history.HistoryActivity.1.1
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onBackKeyDown() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onShow() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.CustomeDialogListener
                public void onClickOk() {
                    HistoryActivity.this.historyManager.deleteAllHistoryItems();
                    HistoryActivity.this.list.clear();
                    HistoryActivity.this.adapter.setList(HistoryActivity.this.list);
                    HistoryActivity.this.adapter.notifyDataSetChanged();
                    HistoryActivity.this.rl_nohistory.setVisibility(0);
                }
            });
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.capture.history.HistoryActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC86292 implements View.OnClickListener {
        View$OnClickListenerC86292() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            HistoryActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        reloadHistoryItems();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void reloadHistoryItems() {
        this.list = this.historyManager.buildHistoryItems();
        this.adapter.setList(this.list);
        this.adapter.notifyDataSetChanged();
        if (this.list.size() > 0) {
            this.rl_nohistory.setVisibility(8);
            Button button = this.clearButton;
            button.setText("清除历史 （" + this.list.size() + "条）");
            return;
        }
        this.rl_nohistory.setVisibility(0);
    }
}
