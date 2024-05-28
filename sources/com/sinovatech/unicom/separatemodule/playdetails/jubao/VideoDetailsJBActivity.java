package com.sinovatech.unicom.separatemodule.playdetails.jubao;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoDetailsJBActivity extends BaseActivity implements View.OnClickListener, CompoundButton.OnCheckedChangeListener {
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private String contentType;
    private String groupId;
    private HashMap<String, Boolean> hashMap = new HashMap<>();
    private String json;
    private Button mBtJbConment;
    private CheckBox mCb301;
    private CheckBox mCb303;
    private CheckBox mCb316;
    private CheckBox mCb318;
    private CheckBox mCd304;
    private String reportFrom;
    private String reportType;
    private String source;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$onClick$0(String str) throws Exception {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 103);
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

    private void initView() {
        ((ImageView) findViewById(2131297412)).setOnClickListener(this);
        ((ImageView) findViewById(2131297413)).setOnClickListener(this);
        this.mBtJbConment = (Button) findViewById(2131296540);
        this.mBtJbConment.setOnClickListener(this);
        this.mCb301 = (CheckBox) findViewById(2131296581);
        this.mCb303 = (CheckBox) findViewById(2131296582);
        this.mCd304 = (CheckBox) findViewById(2131296587);
        this.mCb316 = (CheckBox) findViewById(2131296583);
        this.mCb318 = (CheckBox) findViewById(2131296584);
        this.mCb301.setOnCheckedChangeListener(this);
        this.mCb303.setOnCheckedChangeListener(this);
        this.mCd304.setOnCheckedChangeListener(this);
        this.mCb316.setOnCheckedChangeListener(this);
        this.mCb318.setOnCheckedChangeListener(this);
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        try {
            int id = view.getId();
            if (id != 2131296540) {
                switch (id) {
                    case 2131297412:
                        finish();
                        break;
                    case 2131297413:
                        this.hashMap.clear();
                        finish();
                        break;
                }
            } else {
                StringBuilder sb = new StringBuilder();
                ArrayList arrayList = new ArrayList();
                for (Map.Entry<String, Boolean> entry : this.hashMap.entrySet()) {
                    if (entry.getValue().booleanValue()) {
                        sb.append(entry.getKey());
                        sb.append(",");
                        arrayList.add(entry.getKey());
                    }
                }
                Gson gson = new Gson();
                HashMap hashMap = new HashMap();
                hashMap.put("opeatename", "举报");
                hashMap.put("menus", !(gson instanceof Gson) ? gson.toJson(arrayList) : NBSGsonInstrumentation.toJson(gson, arrayList));
                this.reportType = sb.substring(0, sb.length() - 1);
                UIUtils.logD("举报页面", "onClick: " + this.reportType);
                initData();
                String str = this.json;
                Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.jubao.VideoDetailsJBActivity.1
                }.getType();
                Map map = (Map) (!(gson instanceof Gson) ? gson.fromJson(str, type) : NBSGsonInstrumentation.fromJson(gson, str, type));
                map.put("operateDesc", !(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap));
                map.put("pb_name", "点击举报提交");
                ((ObservableSubscribeProxy) App.getAsyncHttpClient(15, 10, 10).rxPost(URLSet.setLogPoit(), !(gson instanceof Gson) ? gson.toJson(map) : NBSGsonInstrumentation.toJson(gson, map)).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.playdetails.jubao.-$$Lambda$VideoDetailsJBActivity$yLvb-u-Aa_gloHTMfTdJjYUmMuI
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        VideoDetailsJBActivity.lambda$onClick$0((String) obj);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    private void initData() {
        this.contentType = "mini_video";
        this.reportFrom = "feed";
        this.source = "207";
        String jb = URLSet.setJb(this.groupId, this.contentType, this.reportFrom, this.source, this.reportType);
        UIUtils.logD("举报页面", "initData: " + jb);
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        try {
            App.getAsyncHttpClient().rxPost(jb, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.jubao.VideoDetailsJBActivity.2
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str) {
                    UIUtils.logD("zs举报页面", "onNext: " + str);
                    try {
                        JSONObject jSONObject = new JSONObject(str);
                        if (jSONObject.getString("statusCode").equals("0000")) {
                            VideoDetailsJBActivity.this.showSuccessDialog();
                        } else {
                            ToastUtil.showToast(jSONObject.getString("message"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showSuccessDialog() {
        try {
            CustomDialogManager.show((Activity) this, "温馨提示", "举报成功", false, "取消", "确定", true, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.jubao.VideoDetailsJBActivity.3
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public void onClickOk() {
                    VideoDetailsJBActivity.this.activityContext.finish();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    @SuppressLint({"NonConstantResourceId"})
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        Tracker.onCheckedChanged(compoundButton, z);
        try {
            int id = compoundButton.getId();
            if (id != 2131296587) {
                switch (id) {
                }
            }
            this.hashMap.put("301", Boolean.valueOf(this.mCb301.isChecked()));
            this.hashMap.put("303", Boolean.valueOf(this.mCb303.isChecked()));
            this.hashMap.put("304", Boolean.valueOf(this.mCd304.isChecked()));
            this.hashMap.put("316", Boolean.valueOf(this.mCb316.isChecked()));
            this.hashMap.put("318", Boolean.valueOf(this.mCb318.isChecked()));
            if (z) {
                this.mBtJbConment.setBackgroundResource(2131231730);
                this.mBtJbConment.setEnabled(true);
            } else if (!this.mCb301.isChecked() && !this.mCb303.isChecked() && !this.mCd304.isChecked() && !this.mCb316.isChecked() && !this.mCb318.isChecked()) {
                this.mBtJbConment.setBackgroundResource(2131231731);
                this.mBtJbConment.setEnabled(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void startVideoDeailsJB(AppCompatActivity appCompatActivity, String str, String str2) {
        try {
            Intent intent = new Intent(appCompatActivity, VideoDetailsJBActivity.class);
            intent.putExtra("groupId", str);
            intent.putExtra("jsonString", str2);
            appCompatActivity.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
