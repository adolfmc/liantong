package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.datacenter.HistoryDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.StatisticsEntity;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.LookHistoryActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LookHistoryActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private HistoryAdapter adapter;
    private ImageButton backImageBtn;
    private Activity context;
    private String currentPhoneNum;
    private LinearLayout footLayout;
    private ListView listView;
    private List<MenuEntity> menuList;
    private List<String> netList = new ArrayList();
    private String provinceCode;
    private HistoryDataCenter statisticsDataCenter;
    private UserManager userManager;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 55);
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

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LookHistoryActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC74561 implements View.OnClickListener {
        View$OnClickListenerC74561() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            LookHistoryActivity.this.context.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LookHistoryActivity$HistoryAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class HistoryAdapter extends BaseAdapter {
        private LayoutInflater inflater;
        private List<MenuEntity> list;

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        public HistoryAdapter(List<MenuEntity> list) {
            list = list == null ? new ArrayList<>() : list;
            this.inflater = LayoutInflater.from(LookHistoryActivity.this.context);
            this.list = list;
        }

        public void updateAdapter(List<MenuEntity> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.list.size();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            Holder holder;
            final MenuEntity menuEntity = this.list.get(i);
            if (view == null) {
                view = this.inflater.inflate(2131493357, viewGroup, false);
                holder = new Holder(LookHistoryActivity.this, null);
                holder.titleTextView = (TextView) view.findViewById(2131298055);
                holder.imageView = (ImageView) view.findViewById(2131298053);
                view.setTag(holder);
            } else {
                holder = (Holder) view.getTag();
            }
            holder.titleTextView.setText(menuEntity.getMenuTitle());
            GlideApp.with(LookHistoryActivity.this.context).asDrawable().load(menuEntity.getMenuIconURL()).placeholder(2131231795).into(holder.imageView);
            view.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.LookHistoryActivity.HistoryAdapter.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    IntentManager.generateIntentAndGo(LookHistoryActivity.this.context, menuEntity, "get");
                    StatisticsEntity statisticsEntity = new StatisticsEntity();
                    statisticsEntity.setTitleName(menuEntity.getMenuTitle());
                    statisticsEntity.setUrlApp(menuEntity.getMenuURL());
                    statisticsEntity.setMobile(App.hasLogined() ? LookHistoryActivity.this.userManager.getCurrentPhoneNumber() : "");
                    statisticsEntity.setIconUrl(menuEntity.getMenuIconURL());
                    statisticsEntity.setTime(String.valueOf(System.currentTimeMillis() / 1000));
                    LookHistoryActivity.this.statisticsDataCenter.insertStatisticsRecord(statisticsEntity);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            return view;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.activity.LookHistoryActivity$Holder */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class Holder {
        private ImageView imageView;
        private TextView titleTextView;

        private Holder() {
        }

        /* synthetic */ Holder(LookHistoryActivity lookHistoryActivity, View$OnClickListenerC74561 view$OnClickListenerC74561) {
            this();
        }
    }
}
