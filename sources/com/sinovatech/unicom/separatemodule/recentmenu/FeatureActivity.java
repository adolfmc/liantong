package com.sinovatech.unicom.separatemodule.recentmenu;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.FileTools;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback;
import com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.BusinessEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import group.pals.android.lib.p392ui.lockpattern.util.ShakeUtils;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FeatureActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private FeatureActivity activityContext;
    private LinearLayout bottomLayout;
    private LinearLayout collectMengCengLayout;
    private TextView collectMengcengText;
    private LinearLayout deleteLayout;
    private TextView deleteText;
    private MiniGridAdpater featureAdpater;
    private RecyclerView mRlFeature;

    /* renamed from: pd */
    private ProgressDialog f18598pd;
    private RecentCustomManager recentCustomManager;
    private ItemTouchHelper recentHelper;
    private DragHelperCallback recentHelperCalBack;
    private SmartRefreshLayout refreshLayout;
    private TextView titleTextView;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 110);
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
        try {
            this.activityContext = this;
            this.f18598pd = new CustomePorgressDialog(this.activityContext);
            this.titleTextView = (TextView) findViewById(2131298796);
            this.titleTextView.setText("特色业务");
            this.refreshLayout = (SmartRefreshLayout) findViewById(2131299421);
            setRefreshLayoutEnable(true);
            this.refreshLayout.setEnableOverScrollBounce(false);
            this.refreshLayout.setEnableLoadMore(false);
            this.recentCustomManager = new RecentCustomManager(this.activityContext);
            this.deleteLayout = (LinearLayout) findViewById(2131296792);
            this.deleteText = (TextView) findViewById(2131296793);
            this.mRlFeature = (RecyclerView) findViewById(2131296794);
            this.collectMengCengLayout = (LinearLayout) findViewById(2131296795);
            this.collectMengcengText = (TextView) findViewById(2131296796);
            this.bottomLayout = (LinearLayout) findViewById(2131296791);
            this.featureAdpater = new MiniGridAdpater(new ArrayList());
            this.mRlFeature.setAdapter(this.featureAdpater);
            this.recentHelperCalBack = new DragHelperCallback(this.featureAdpater, this.deleteLayout, this.collectMengCengLayout);
            this.mRlFeature.setLayoutManager(new GridLayoutManager(this.activityContext, 4));
            this.recentHelperCalBack.setmIsCanMove(false);
            this.recentHelper = new ItemTouchHelper(this.recentHelperCalBack);
            this.recentHelper.attachToRecyclerView(this.mRlFeature);
            this.collectMengCengLayout.setVisibility(0);
            this.recentHelperCalBack.setmIsCanMove(false);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    private void initData() {
        notifyTeseyewu();
    }

    private void initListener() {
        try {
            this.recentHelperCalBack.setDragListener(new DragHelperCallback.DragListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.FeatureActivity.1
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragStart(int i) {
                    try {
                        if (FeatureActivity.this.refreshLayout.getState() != RefreshState.Loading && FeatureActivity.this.refreshLayout.getState() != RefreshState.Refreshing) {
                            FeatureActivity.this.bottomLayout.setVisibility(0);
                            ShakeUtils.vibrate(FeatureActivity.this.activityContext, 200L);
                            FeatureActivity.this.setRefreshLayoutEnable(false);
                            FeatureActivity.this.deleteLayout.setVisibility(8);
                            FeatureActivity.this.collectMengCengLayout.setVisibility(0);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragAreaChange(boolean z, boolean z2) {
                    if (z2) {
                        return;
                    }
                    try {
                        if (z) {
                            FeatureActivity.this.deleteText.setText("松手即可删除");
                        } else {
                            FeatureActivity.this.deleteText.setText("删除");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragFinish(boolean z, int i) {
                    try {
                        FeatureActivity.this.bottomLayout.setVisibility(8);
                        FeatureActivity.this.refreshLayout.setEnableRefresh(true);
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onCollectAreaChange(boolean z, boolean z2) {
                    if (z2) {
                        return;
                    }
                    try {
                        if (z) {
                            FeatureActivity.this.collectMengcengText.setText("松开即可添加");
                        } else {
                            FeatureActivity.this.collectMengcengText.setText("添加我的收藏");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onCollectFinish(boolean z, int i) {
                    try {
                        FeatureActivity.this.setRefreshLayoutEnable(true);
                        if (z) {
                            RecentMiniEntity recentMiniEntity = FeatureActivity.this.featureAdpater.getList().get(i);
                            FeatureActivity.this.pdShow();
                            FeatureActivity.this.recentCustomManager.deleteOrAddMenu(RecentCustomManager.ADD, recentMiniEntity, new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.FeatureActivity.1.1
                                @Override // io.reactivex.functions.Consumer
                                public void accept(RecentStateEntity recentStateEntity) throws Exception {
                                    FeatureActivity.this.pdDissmiss();
                                    try {
                                        if (recentStateEntity.isSuccess()) {
                                            UIUtils.toastCenter("已收藏");
                                        } else {
                                            UIUtils.toastCenter(recentStateEntity.getRespMsg());
                                        }
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        UIUtils.toastCenter("收藏失败,请稍后再试。");
                                    }
                                    FeatureActivity.this.bottomLayout.setVisibility(8);
                                    FeatureActivity.this.collectMengCengLayout.setVisibility(8);
                                }
                            });
                            FeatureActivity featureActivity = FeatureActivity.this;
                            featureActivity.log(recentMiniEntity.getAppName() + "添加我的收藏");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
            });
            this.featureAdpater.setAdapterClickInterface(new MiniGridAdpater.AdapterClickInterface() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.FeatureActivity.2
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater.AdapterClickInterface
                public void onClick(int i, RecentMiniEntity recentMiniEntity) {
                    try {
                        MenuEntity menuEntity = new MenuEntity();
                        menuEntity.setMenuIconURL(recentMiniEntity.getAppImg());
                        menuEntity.setMenuURL(recentMiniEntity.getAppletUrl());
                        menuEntity.setMenuTitle(recentMiniEntity.getAppName());
                        IntentManager.generateIntentAndGo(FeatureActivity.this.activityContext, menuEntity, "get");
                        RecentBoxManager.getInstance().put(recentMiniEntity);
                    } catch (Exception e) {
                        MsLogUtil.m7978e(e.getMessage());
                    }
                }
            });
            this.refreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.FeatureActivity.3
                @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    FeatureActivity.this.getTeSeYeWuData();
                }
            });
            this.refreshLayout.autoRefresh();
            findViewById(2131296801).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$FeatureActivity$ogEBtnbggu-vwXnGP1yj2e1d3mI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    FeatureActivity.this.finish();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getTeSeYeWuData() {
        this.recentCustomManager.getTeseYewu(new Consumer<BusinessEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.FeatureActivity.4
            @Override // io.reactivex.functions.Consumer
            public void accept(BusinessEntity businessEntity) throws Exception {
                try {
                    FeatureActivity.this.featureAdpater.update(businessEntity.getAppList(), true);
                    FeatureActivity.this.refreshLayout.finishRefresh();
                    FeatureActivity.this.refreshLayout.finishLoadMore();
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.FeatureActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                try {
                    FeatureActivity.this.refreshLayout.finishRefresh();
                    FeatureActivity.this.refreshLayout.finishLoadMore();
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            if (!isDestroyed() && !isFinishing() && this.f18598pd != null && this.f18598pd.isShowing()) {
                this.f18598pd.dismiss();
            }
            setRefreshLayoutEnable(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (isFinishing() || this.f18598pd == null || this.f18598pd.isShowing()) {
                return;
            }
            this.f18598pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        PvCurrencyLogUtils.pvXiala("特色业务页面", str, "下拉进入小程序");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setRefreshLayoutEnable(boolean z) {
        this.refreshLayout.setEnabled(z);
    }

    private void notifyTeseyewu() {
        try {
            String xialaTeseData = CacheDataCenter.getInstance().getXialaTeseData();
            if (TextUtils.isEmpty(xialaTeseData)) {
                xialaTeseData = FileTools.readInputStream(getResources().getAssets().open("mptsywDefault.json"));
            }
            this.featureAdpater.update(this.recentCustomManager.paserTeseEntity(xialaTeseData).getAppList(), true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
