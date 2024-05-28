package com.sinovatech.unicom.separatemodule.language.activity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
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
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.language.adapter.LanguageAdapter;
import com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LanguageActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LanguageActivity";
    public NBSTraceUnit _nbs_trace;
    private ImageButton backButton;
    private LanguageAdapter mAdapter;
    private RecyclerView mRvLanguage;

    /* renamed from: pd */
    private ProgressDialog f18542pd;
    private TextView titleBaoCun;
    private TextView titleView;
    private String selectLanguage = LanguageUtil.CHN_CN;
    private String selectLanguageLinkurl = "";
    private String languageDirection = "left";
    private List<LanguageEntity> mList = new ArrayList();

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 88);
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

    /* renamed from: com.sinovatech.unicom.separatemodule.language.activity.LanguageActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C89101 implements LanguageAdapter.onItemClickListener {
        C89101() {
        }

        @Override // com.sinovatech.unicom.separatemodule.language.adapter.LanguageAdapter.onItemClickListener
        public void onItemClickListener(int i, LanguageEntity languageEntity) {
            LanguageActivity.this.selectLanguage = languageEntity.getLanguageCode();
            LanguageActivity.this.languageDirection = languageEntity.getRightToleft();
            LanguageActivity.this.selectLanguageLinkurl = languageEntity.getUrl();
            LanguageActivity.this.changeLanguageDialog(languageEntity);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeLanguageDialog(LanguageEntity languageEntity) {
        if (!TextUtils.isEmpty(languageEntity.getSwitchLanguagePop())) {
            LanguageDialog.show(this, languageEntity, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.language.activity.LanguageActivity.2
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    StatisticsUploadUtils.upload("7", "设置-多语言", "", "", LanguageActivity.this.selectLanguage, "");
                    LanguageUtil.getInstance().applyLanguage(LanguageActivity.this.selectLanguage, LanguageActivity.this.languageDirection, LanguageActivity.this.selectLanguageLinkurl);
                }
            });
            return;
        }
        CustomDialogManager.show((Activity) this, "温馨提示", "切换至" + languageEntity.getLanguageName() + "版?", true, "取消", "确定", false, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.language.activity.LanguageActivity.3
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
                StatisticsUploadUtils.upload("7", "设置-多语言", "", "", LanguageActivity.this.selectLanguage, "");
                LanguageUtil.getInstance().applyLanguage(LanguageActivity.this.selectLanguage, LanguageActivity.this.languageDirection, LanguageActivity.this.selectLanguageLinkurl);
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131296473) {
            finish();
        } else if (id == 2131298791) {
            StatisticsUploadUtils.upload("7", "设置-多语言", "", "", this.selectLanguage, "");
            LanguageUtil.getInstance().applyLanguage(this.selectLanguage, this.languageDirection, this.selectLanguageLinkurl);
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public void getLanguageList() {
        ProgressDialog progressDialog = this.f18542pd;
        if (progressDialog != null) {
            progressDialog.setMessage("加载中");
        } else {
            this.f18542pd = new CustomePorgressDialog(this);
            this.f18542pd.setMessage("加载中");
            this.f18542pd.setCanceledOnTouchOutside(false);
            this.f18542pd.setCancelable(false);
        }
        if (this.f18542pd != null && !isDestroyed() && !isFinishing() && !this.f18542pd.isShowing()) {
            this.f18542pd.show();
        }
        LanguageUtil.getInstance().getLanguageList().subscribe(new Consumer<List<LanguageEntity>>() { // from class: com.sinovatech.unicom.separatemodule.language.activity.LanguageActivity.4
            @Override // io.reactivex.functions.Consumer
            public void accept(List<LanguageEntity> list) throws Exception {
                if (LanguageActivity.this.f18542pd != null && !LanguageActivity.this.isDestroyed() && !LanguageActivity.this.isFinishing() && LanguageActivity.this.f18542pd.isShowing()) {
                    LanguageActivity.this.f18542pd.dismiss();
                }
                LanguageActivity.this.mList.clear();
                if (list == null || list.size() <= 0) {
                    LanguageActivity.this.mList.addAll(LanguageActivity.this.getDefaultList());
                } else {
                    LanguageActivity.this.mList.addAll(list);
                }
                LanguageActivity.this.mAdapter.notifyDataSetChanged();
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.language.activity.LanguageActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                if (LanguageActivity.this.f18542pd != null && !LanguageActivity.this.isDestroyed() && !LanguageActivity.this.isFinishing() && LanguageActivity.this.f18542pd.isShowing()) {
                    LanguageActivity.this.f18542pd.dismiss();
                }
                LanguageActivity.this.mList.clear();
                LanguageActivity.this.mList.addAll(LanguageActivity.this.getDefaultList());
                LanguageActivity.this.mAdapter.notifyDataSetChanged();
            }
        });
    }

    public void resetList(int i, LanguageEntity languageEntity) {
        for (LanguageEntity languageEntity2 : this.mList) {
            languageEntity2.setSelect(false);
        }
        languageEntity.setSelect(true);
        this.mAdapter.setCurrentLanuage(this.selectLanguage);
        this.mList.set(i, languageEntity);
        this.mAdapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<LanguageEntity> getDefaultList() {
        LanguageUtil.maxLanguageLengthName = "中文（简体）";
        LanguageEntity languageEntity = new LanguageEntity();
        languageEntity.setLanguageName("中文（简体）");
        languageEntity.setLanguageDesc("");
        languageEntity.setLanguageCode(LanguageUtil.CHN_CN);
        languageEntity.setSelect(TextUtils.equals(this.selectLanguage, LanguageUtil.CHN_CN));
        LanguageEntity languageEntity2 = new LanguageEntity();
        languageEntity2.setLanguageName("英文");
        languageEntity2.setLanguageDesc("English");
        languageEntity2.setLanguageCode(LanguageUtil.USA);
        languageEntity2.setSwitchLanguagePop("Switch to English version?");
        languageEntity2.setConfirmBtnPop("Confirm");
        languageEntity2.setCancelBtnPop("Cancel");
        languageEntity2.setReminderPop("Reminder");
        languageEntity2.setShowDirectionFlag("0");
        languageEntity2.setSelect(TextUtils.equals(this.selectLanguage, LanguageUtil.USA));
        ArrayList arrayList = new ArrayList();
        arrayList.add(languageEntity);
        arrayList.add(languageEntity2);
        return arrayList;
    }
}
