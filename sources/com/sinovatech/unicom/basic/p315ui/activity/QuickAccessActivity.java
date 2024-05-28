package com.sinovatech.unicom.basic.p315ui.activity;

import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.support.p083v4.widget.NestedScrollView;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.adapter.QuickAccessAdapter;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectConfig;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.QuickAccessActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class QuickAccessActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private ImageButton back_imagebutton;
    private JSONArray bottomData;
    private RecyclerView bottom_menu_rv;
    private QuickAccessAdapter quickBottomAccessAdapter;
    private QuickAccessAdapter quickTopAccessAdapter;
    private TextView quick_accress_content_tv;
    private NestedScrollView quick_accress_scroll;
    private int shortcutInfoImg;
    private TextView title_textview;
    private JSONArray topData;
    private RecyclerView top_menu_rv;

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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        setContentView(2131492938);
        UIUtils.setStatusBarMode(this, true, true);
        if (!TextUtils.isEmpty(UserManager.getInstance().getOnlineToken(UserManager.getInstance().getDefaultPhoneNumber()))) {
            App.setLogined(LoginStateConst.DID_LOGIN);
        }
        UnicomCollectManager unicomCollectManager = UnicomCollectManager.getInstance();
        unicomCollectManager.setTransId("S2ndpage1024" + CollectConfig.montageTag1 + "3DTouch");
        initView();
        if (App.hasLogined()) {
            getMenu();
            initTopQuickAdapter();
            initBottomQuickAdapter();
        } else {
            new AvoidOnResult(this).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.basic.ui.activity.QuickAccessActivity.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (App.hasLogined()) {
                        QuickAccessActivity.this.getMenu();
                        QuickAccessActivity.this.initTopQuickAdapter();
                        QuickAccessActivity.this.initBottomQuickAdapter();
                        return;
                    }
                    QuickAccessActivity.this.finish();
                }
            }, false);
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void initView() {
        this.back_imagebutton = (ImageButton) findViewById(2131296473);
        this.title_textview = (TextView) findViewById(2131298800);
        this.quick_accress_content_tv = (TextView) findViewById(2131298249);
        this.top_menu_rv = (RecyclerView) findViewById(2131298818);
        this.bottom_menu_rv = (RecyclerView) findViewById(2131296530);
        this.quick_accress_scroll = (NestedScrollView) findViewById(2131298250);
        this.back_imagebutton.setOnClickListener(this);
        this.top_menu_rv.setNestedScrollingEnabled(false);
        this.bottom_menu_rv.setNestedScrollingEnabled(false);
        initTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initTopQuickAdapter() {
        this.topData = returnTopData();
        refreshView(this.topData);
        saveData();
        this.quickTopAccessAdapter = new QuickAccessAdapter(0, this.topData, this);
        this.top_menu_rv.setLayoutManager(new LinearLayoutManager(this, 1, false));
        this.top_menu_rv.setAdapter(this.quickTopAccessAdapter);
        this.quickTopAccessAdapter.setOnItemTopClickListenr(new QuickAccessAdapter.OnItemClickLister() { // from class: com.sinovatech.unicom.basic.ui.activity.QuickAccessActivity.2
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.QuickAccessAdapter.OnItemClickLister
            public void setOnItemClick(int i, int i2) {
                QuickAccessActivity quickAccessActivity = QuickAccessActivity.this;
                quickAccessActivity.upLoadPvLog(quickAccessActivity.topData.optJSONObject(i).optString("title"), QuickAccessActivity.this.topData.optJSONObject(i).optString("linkUrl"), 0);
                QuickAccessActivity.this.bottomData.put(QuickAccessActivity.this.topData.optJSONObject(i));
                QuickAccessActivity.this.topData.remove(i);
                CacheDataCenter.getInstance().setFirstOpen("1");
                QuickAccessActivity quickAccessActivity2 = QuickAccessActivity.this;
                quickAccessActivity2.refreshView(quickAccessActivity2.topData);
                QuickAccessActivity.this.saveData();
                QuickAccessActivity.this.quickTopAccessAdapter.notifyDataSetChanged();
                QuickAccessActivity.this.quickBottomAccessAdapter.notifyDataSetChanged();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getMenu() {
        RequestParams requestParams = new RequestParams();
        requestParams.put("version", getString(2131886969));
        App.getAsyncHttpClient(5, 5, 5).post(URLSet.getQuickAccessUrl(), requestParams, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.basic.ui.activity.QuickAccessActivity.3
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onStart() {
                super.onStart();
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str) {
                super.onSuccess(i, str);
                try {
                    JSONObject jSONObject = new JSONObject(str);
                    if (!jSONObject.getString("code").equals("0000")) {
                        QuickAccessActivity.this.upLoadPvLog();
                    } else {
                        CacheDataCenter.getInstance().setQuickAccessData(str);
                        QuickAccessActivity.this.filterData(jSONObject.optJSONArray("data"));
                        if (CacheDataCenter.getInstance().getFirstOpen()) {
                            QuickAccessActivity.this.initTopQuickAdapter();
                            QuickAccessActivity.this.initBottomQuickAdapter();
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str) {
                super.onFailure(th, str);
                QuickAccessActivity.this.upLoadPvLog();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadPvLog() {
        StatisticsUploadUtils.upload(this, "S2ndpage1024", "启动图标快捷入口", "快捷入口", "", "", "", "", "2");
    }

    public void filterData(JSONArray jSONArray) {
        JSONArray jSONArray2 = new JSONArray();
        String useQuickData = CacheDataCenter.getInstance().getUseQuickData();
        if (useQuickData.isEmpty()) {
            return;
        }
        try {
            JSONArray jSONArray3 = new JSONObject(useQuickData).getJSONArray("data");
            if (jSONArray3.length() == 0) {
                return;
            }
            for (int i = 0; i < jSONArray.length(); i++) {
                for (int i2 = 0; i2 < jSONArray3.length(); i2++) {
                    if (jSONArray.getJSONObject(i).getString("title").equals(jSONArray3.getJSONObject(i2).getString("title"))) {
                        jSONArray2.put(jSONArray.getJSONObject(i));
                    }
                }
            }
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("data", jSONArray2);
            CacheDataCenter.getInstance().setUseQuickData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void upLoadPvLog(String str, String str2, int i) {
        StatisticsUploadUtils.upload(this, "S2ndpage1024", "启动图标快捷入口", "快捷入口", "", str, str2, "", String.valueOf(i));
    }

    private void saveQuickData(JSONArray jSONArray) {
        if (Build.VERSION.SDK_INT >= 25) {
            try {
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < jSONArray.length(); i++) {
                    Intent intent = new Intent();
                    intent.setAction("");
                    intent.putExtra("url", jSONArray.optJSONObject(i).optString("linkUrl"));
                    intent.putExtra("title", jSONArray.optJSONObject(i).optString("title"));
                    intent.putExtra("enterType", "quickAccessType");
                    intent.setClassName("com.sinovatech.unicom.ui", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
                    if (Integer.parseInt(jSONArray.optJSONObject(i).optString("iconType")) <= this.quickResources.length) {
                        this.shortcutInfoImg = this.quickResources[Integer.parseInt(jSONArray.optJSONObject(i).optString("iconType")) - 1];
                    } else {
                        this.shortcutInfoImg = this.quickResources[0];
                    }
                    arrayList.add(new ShortcutInfo.Builder(this, jSONArray.optJSONObject(i).optString("title")).setIntent(intent).setRank(i).setShortLabel(jSONArray.optJSONObject(i).optString("title")).setIcon(Icon.createWithResource(this, this.shortcutInfoImg)).build());
                }
                Intent intent2 = new Intent();
                intent2.setAction("");
                intent2.putExtra("url", "QuickAccessActivity");
                intent2.putExtra("enterType", "quickAccessType");
                intent2.setClassName("com.sinovatech.unicom.ui", "com.sinovatech.unicom.basic.ui.activity.WelcomeClient");
                arrayList.add(new ShortcutInfo.Builder(this, "setting").setIntent(intent2).setShortLabel("更多设置").setRank(3).setIcon(Icon.createWithResource(this, 2131232081)).build());
                if (arrayList.size() > 0) {
                    ShortcutManager shortcutManager = (ShortcutManager) getSystemService(ShortcutManager.class);
                    shortcutManager.removeAllDynamicShortcuts();
                    shortcutManager.addDynamicShortcuts(arrayList);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveData() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("data", this.topData);
            CacheDataCenter.getInstance().setUseQuickData(!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        saveQuickData(this.topData);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshView(JSONArray jSONArray) {
        if (jSONArray == null || jSONArray.length() <= 0) {
            this.top_menu_rv.setVisibility(8);
            this.quick_accress_content_tv.setVisibility(0);
            return;
        }
        this.top_menu_rv.setVisibility(0);
        this.quick_accress_content_tv.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBottomQuickAdapter() {
        try {
            this.bottomData = returnBottomData(new JSONObject(CacheDataCenter.getInstance().getQuickAccessData()).getJSONArray("data"));
            this.quickBottomAccessAdapter = new QuickAccessAdapter(1, this.bottomData, this);
            this.bottom_menu_rv.setLayoutManager(new LinearLayoutManager(this, 1, false));
            this.bottom_menu_rv.setAdapter(this.quickBottomAccessAdapter);
            this.quickBottomAccessAdapter.setOnItemBottomClickListenr(new QuickAccessAdapter.OnItemClickLister() { // from class: com.sinovatech.unicom.basic.ui.activity.QuickAccessActivity.4
                @Override // com.sinovatech.unicom.basic.p315ui.adapter.QuickAccessAdapter.OnItemClickLister
                public void setOnItemClick(int i, int i2) {
                    if (QuickAccessActivity.this.topData.length() == 3) {
                        UIUtils.toast("添加数量已达上线");
                        return;
                    }
                    QuickAccessActivity quickAccessActivity = QuickAccessActivity.this;
                    quickAccessActivity.upLoadPvLog(quickAccessActivity.bottomData.optJSONObject(i).optString("title"), QuickAccessActivity.this.bottomData.optJSONObject(i).optString("linkUrl"), 1);
                    CacheDataCenter.getInstance().setFirstOpen("1");
                    QuickAccessActivity.this.topData.put(QuickAccessActivity.this.bottomData.optJSONObject(i));
                    QuickAccessActivity.this.bottomData.remove(i);
                    QuickAccessActivity.this.saveData();
                    QuickAccessActivity quickAccessActivity2 = QuickAccessActivity.this;
                    quickAccessActivity2.refreshView(quickAccessActivity2.topData);
                    QuickAccessActivity.this.quickTopAccessAdapter.notifyDataSetChanged();
                    QuickAccessActivity.this.quickBottomAccessAdapter.notifyDataSetChanged();
                }
            });
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0078 A[Catch: JSONException -> 0x0093, TryCatch #0 {JSONException -> 0x0093, blocks: (B:5:0x001b, B:6:0x002c, B:8:0x0032, B:25:0x0074, B:27:0x0078, B:28:0x0080, B:29:0x0088, B:15:0x0052, B:18:0x005d, B:21:0x0068), top: B:50:0x001b }] */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0080 A[Catch: JSONException -> 0x0093, TryCatch #0 {JSONException -> 0x0093, blocks: (B:5:0x001b, B:6:0x002c, B:8:0x0032, B:25:0x0074, B:27:0x0078, B:28:0x0080, B:29:0x0088, B:15:0x0052, B:18:0x005d, B:21:0x0068), top: B:50:0x001b }] */
    /* JADX WARN: Removed duplicated region for block: B:29:0x0088 A[Catch: JSONException -> 0x0093, TRY_LEAVE, TryCatch #0 {JSONException -> 0x0093, blocks: (B:5:0x001b, B:6:0x002c, B:8:0x0032, B:25:0x0074, B:27:0x0078, B:28:0x0080, B:29:0x0088, B:15:0x0052, B:18:0x005d, B:21:0x0068), top: B:50:0x001b }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private org.json.JSONArray returnTopData() {
        /*
            Method dump skipped, instructions count: 268
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.activity.QuickAccessActivity.returnTopData():org.json.JSONArray");
    }

    private JSONArray returnBottomData(JSONArray jSONArray) {
        try {
            JSONArray jSONArray2 = new JSONObject(CacheDataCenter.getInstance().getUseQuickData()).getJSONArray("data");
            if (jSONArray2.length() > 0) {
                for (int i = 0; i < jSONArray2.length(); i++) {
                    for (int i2 = 0; i2 < jSONArray.length(); i2++) {
                        if (jSONArray2.getJSONObject(i).getString("title").equals(jSONArray.getJSONObject(i2).getString("title"))) {
                            jSONArray.remove(i2);
                        }
                    }
                }
                return jSONArray;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jSONArray;
    }

    private void initTitle() {
        this.title_textview.setText("快捷入口设置");
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            finish();
        }
        return super.onKeyDown(i, keyEvent);
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
}
