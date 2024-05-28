package com.sinovatech.unicom.separatemodule.miniprogram.cump;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthRecordEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jspermission.UserAuthScopeManager;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CumpAuthRecordActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private ImageView closeImage;
    private CumpEntity cumpEntity;
    private List<UserAuthRecordEntity> recordEntityList = new ArrayList();
    private RecyclerView recyclerView;
    private TextView titleText;

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 96);
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

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpAuthRecordActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class View$OnClickListenerC89881 implements View.OnClickListener {
        View$OnClickListenerC89881() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            CumpAuthRecordActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class ItemAdapter extends RecyclerView.Adapter<ItemViewHolder> {
        private Activity activityContext;

        public ItemAdapter(Activity activity) {
            this.activityContext = activity;
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        @NonNull
        public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ItemViewHolder(this.activityContext.getLayoutInflater().inflate(2131493069, viewGroup, false));
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public void onBindViewHolder(@NonNull ItemViewHolder itemViewHolder, int i) {
            final UserAuthRecordEntity userAuthRecordEntity = (UserAuthRecordEntity) CumpAuthRecordActivity.this.recordEntityList.get(i);
            itemViewHolder.scopeText.setText(UserAuthScopeManager.getInstance(this.activityContext).getJSScopeEntity(userAuthRecordEntity.getScope()).getDesc());
            itemViewHolder.scopeToggleButton.setChecked(userAuthRecordEntity.isGrant());
            itemViewHolder.scopeToggleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.cump.CumpAuthRecordActivity.ItemAdapter.1
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                    Tracker.onCheckedChanged(compoundButton, z);
                    userAuthRecordEntity.setGrant(z);
                    UserAuthScopeManager.getInstance(ItemAdapter.this.activityContext).saveAuthRecord(UserManager.getInstance().getCurrentPhoneNumber(), CumpAuthRecordActivity.this.cumpEntity.getAppId(), userAuthRecordEntity.getScope(), z);
                }
            });
        }

        @Override // android.support.p086v7.widget.RecyclerView.Adapter
        public int getItemCount() {
            return CumpAuthRecordActivity.this.recordEntityList.size();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class ItemViewHolder extends RecyclerView.ViewHolder {
        public View lineView;
        public TextView scopeText;
        public ToggleButton scopeToggleButton;

        public ItemViewHolder(@NonNull View view) {
            super(view);
            this.scopeText = (TextView) view.findViewById(2131296736);
            this.scopeToggleButton = (ToggleButton) view.findViewById(2131296737);
            this.lineView = view.findViewById(2131296735);
        }
    }
}
