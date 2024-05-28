package com.sinovatech.unicom.separatemodule.recentmenu;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.FastToast;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback;
import com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.CollectEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentMiniEntity;
import com.sinovatech.unicom.separatemodule.recentmenu.entity.RecentStateEntity;
import com.sinovatech.unicom.separatemodule.search.SearchEntity;
import com.sinovatech.unicom.separatemodule.search.SearchHuoDongEntity;
import com.sinovatech.unicom.separatemodule.search.SearchManager;
import group.pals.android.lib.p392ui.lockpattern.util.ShakeUtils;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomRecentActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private CustomRecentActivity activityContext;
    private ImageView backImageView;
    private MiniGridAdpater collectGridAdpater;
    private RecyclerView collectGridView;
    private DragHelperCallback collectHelperCalBack;
    private LinearLayout collectMengCengLayout;
    private TextView collectMengcengText;
    private LinearLayout collectMore;
    private CustomSearchView customSearchView;
    private LinearLayout deleteLayout;
    private TextView deleteText;
    private LinearLayout emptyActivityLayout;
    private LinearLayout emptyCollectLayout;
    private LinearLayout mLinFeature;

    /* renamed from: pd */
    private ProgressDialog f18597pd;
    private RecentCustomManager recentCustomManager;
    private MiniGridAdpater recentGridAdpater;
    private RecyclerView recentGridView;
    private ItemTouchHelper recentHelper;
    private DragHelperCallback recentHelperCalBack;
    private LinearLayout recentMore;
    private LinearLayout recentTitleLayout;
    private SmartRefreshLayout refreshLayout;
    private SearchManager searchManager;
    private List<String> collectOrderList = new ArrayList();
    private boolean isCreate = false;
    private boolean isButtonClickable = true;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 109);
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

    public static /* synthetic */ void lambda$onCreate$0(CustomRecentActivity customRecentActivity, ManagerLocation.LocationEntity locationEntity) {
        MsLogUtil.m7979d("百度定位--->", "CustomRecentActivity 加载搜索 定位回调" + locationEntity.toString());
        customRecentActivity.loadSearch(locationEntity);
    }

    public void initView() {
        try {
            this.f18597pd = new CustomePorgressDialog(this.activityContext);
            this.recentCustomManager = new RecentCustomManager(this.activityContext);
            this.customSearchView = (CustomSearchView) findViewById(2131299847);
            this.searchManager = new SearchManager(this.activityContext);
            this.mLinFeature = (LinearLayout) findViewById(2131298260);
            this.refreshLayout = (SmartRefreshLayout) findViewById(2131299421);
            this.refreshLayout.setEnabled(true);
            this.refreshLayout.setEnableOverScrollBounce(true);
            this.deleteLayout = (LinearLayout) findViewById(2131296798);
            this.deleteText = (TextView) findViewById(2131296799);
            this.recentMore = (LinearLayout) findViewById(2131296802);
            this.collectMore = (LinearLayout) findViewById(2131296778);
            this.recentGridView = (RecyclerView) findViewById(2131296800);
            this.collectGridView = (RecyclerView) findViewById(2131296775);
            this.collectMengCengLayout = (LinearLayout) findViewById(2131296776);
            this.collectMengcengText = (TextView) findViewById(2131296777);
            this.emptyCollectLayout = (LinearLayout) findViewById(2131296805);
            this.emptyActivityLayout = (LinearLayout) findViewById(2131296790);
            this.backImageView = (ImageView) findViewById(2131298259);
            this.recentTitleLayout = (LinearLayout) findViewById(2131296803);
            this.recentGridAdpater = new MiniGridAdpater(new ArrayList());
            this.recentGridView.setAdapter(this.recentGridAdpater);
            this.recentHelperCalBack = new DragHelperCallback(this.recentGridAdpater, this.deleteLayout, this.collectMengCengLayout);
            this.recentHelperCalBack.setmIsCanMove(false);
            this.recentGridView.setLayoutManager(new GridLayoutManager(this.activityContext, 4));
            this.recentHelper = new ItemTouchHelper(this.recentHelperCalBack);
            this.recentHelper.attachToRecyclerView(this.recentGridView);
            this.collectGridAdpater = new MiniGridAdpater(new ArrayList());
            this.collectGridView.setLayoutManager(new GridLayoutManager(this.activityContext, 4));
            this.collectGridView.setAdapter(this.collectGridAdpater);
            this.collectHelperCalBack = new DragHelperCallback(this.collectGridAdpater, this.deleteLayout, this.collectMengCengLayout);
            new ItemTouchHelper(this.collectHelperCalBack).attachToRecyclerView(this.collectGridView);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void initListener() {
        try {
            this.mLinFeature.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    CustomRecentActivity customRecentActivity = CustomRecentActivity.this;
                    customRecentActivity.startActivity(new Intent(customRecentActivity, FeatureActivity.class));
                    CustomRecentActivity.this.log("查看更多特色业务");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.refreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$CustomRecentActivity$-fJj5sBr97NtuqnjtFSqYMDtI-0
                @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
                public final void onRefresh(RefreshLayout refreshLayout) {
                    new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.2
                        @Override // java.lang.Runnable
                        public void run() {
                            refreshLayout.finishRefresh();
                            CustomRecentActivity.this.notifyRecent();
                            CustomRecentActivity.this.getCollectionData();
                        }
                    }, 1500L);
                }
            });
            this.emptyActivityLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    CustomRecentActivity.this.mLinFeature.performClick();
                    CustomRecentActivity.this.log("去发现特色业务");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.recentHelperCalBack.setDragListener(new DragHelperCallback.DragListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.4
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragStart(int i) {
                    try {
                        CustomRecentActivity.this.refreshLayout.setEnabled(false);
                        CustomRecentActivity.this.collectMengCengLayout.setVisibility(0);
                        CustomRecentActivity.this.emptyCollectLayout.setBackgroundResource(2131231127);
                        ShakeUtils.vibrate(CustomRecentActivity.this.activityContext, 200L);
                        CustomRecentActivity.this.deleteLayout.setVisibility(0);
                        CustomRecentActivity.this.mLinFeature.setVisibility(8);
                    } catch (Exception e) {
                        MsLogUtil.m7978e("最近使用拖动时异常:" + e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragAreaChange(boolean z, boolean z2) {
                    if (z2) {
                        return;
                    }
                    try {
                        if (z) {
                            CustomRecentActivity.this.deleteText.setText("松手即可删除");
                        } else {
                            CustomRecentActivity.this.deleteText.setText("删除");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("拖动回调异常:" + e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragFinish(boolean z, int i) {
                    try {
                        CustomRecentActivity.this.refreshLayout.setEnabled(true);
                        CustomRecentActivity.this.deleteLayout.setVisibility(8);
                        CustomRecentActivity.this.mLinFeature.setVisibility(0);
                        CustomRecentActivity.this.deleteText.setText("删除");
                        if (z) {
                            RecentMiniEntity recentMiniEntity = CustomRecentActivity.this.recentGridAdpater.getList().get(i);
                            RecentBoxManager.getInstance().reMove(recentMiniEntity);
                            CustomRecentActivity.this.notifyRecent();
                            UIUtils.toastCenter("删除最近使用成功");
                            CustomRecentActivity customRecentActivity = CustomRecentActivity.this;
                            customRecentActivity.log("最近使用" + recentMiniEntity.getAppName() + "删除");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("最近使用删除回调异常:" + e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onCollectAreaChange(boolean z, boolean z2) {
                    if (z2) {
                        return;
                    }
                    try {
                        if (z) {
                            CustomRecentActivity.this.collectMengcengText.setText("松开即可添加");
                            CustomRecentActivity.this.collectMengCengLayout.setBackgroundResource(2131231221);
                        } else {
                            CustomRecentActivity.this.collectMengcengText.setText("拖动到此处添加为我的收藏");
                            CustomRecentActivity.this.collectMengCengLayout.setBackgroundResource(2131231222);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("最近使用收藏拖动异常:" + e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onCollectFinish(boolean z, int i) {
                    try {
                        CustomRecentActivity.this.collectMengCengLayout.setVisibility(8);
                        CustomRecentActivity.this.emptyCollectLayout.setBackgroundResource(2131231130);
                        if (z) {
                            if (CustomRecentActivity.this.isButtonClickable) {
                                CustomRecentActivity.this.isButtonClickable = false;
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.4.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        CustomRecentActivity.this.isButtonClickable = true;
                                    }
                                }, 2000L);
                                final RecentMiniEntity recentMiniEntity = CustomRecentActivity.this.recentGridAdpater.getList().get(i);
                                CustomRecentActivity.this.pdShow();
                                CustomRecentActivity.this.recentCustomManager.deleteOrAddMenu(RecentCustomManager.ADD, recentMiniEntity.getAppId(), recentMiniEntity.getProductId(), new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.4.2
                                    @Override // io.reactivex.functions.Consumer
                                    public void accept(RecentStateEntity recentStateEntity) throws Exception {
                                        try {
                                            CustomRecentActivity.this.pdDissmiss();
                                            if (recentStateEntity.isSuccess()) {
                                                CustomRecentActivity.this.collectGridAdpater.addItem(recentMiniEntity);
                                                CustomRecentActivity.this.getCollectionData();
                                            }
                                            FastToast.showText(CustomRecentActivity.this.activityContext, recentStateEntity.getRespMsg());
                                        } catch (Exception e) {
                                            e.printStackTrace();
                                            FastToast.showText(CustomRecentActivity.this.activityContext, "收藏失败,请稍后再试");
                                        }
                                    }
                                });
                                CustomRecentActivity customRecentActivity = CustomRecentActivity.this;
                                customRecentActivity.log(recentMiniEntity.getAppName() + "添加我的收藏");
                            } else {
                                FastToast.showText(CustomRecentActivity.this.activityContext, "操作过于频繁，请稍后再试");
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.recentGridAdpater.setAdapterClickInterface(new MiniGridAdpater.AdapterClickInterface() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.5
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater.AdapterClickInterface
                public void onClick(int i, RecentMiniEntity recentMiniEntity) {
                    MenuEntity menuEntity = new MenuEntity();
                    menuEntity.setMenuIconURL(recentMiniEntity.getAppImg());
                    menuEntity.setMenuURL(recentMiniEntity.getAppletUrl());
                    menuEntity.setMenuTitle(recentMiniEntity.getAppName());
                    IntentManager.generateIntentAndGo(CustomRecentActivity.this.activityContext, menuEntity, "get");
                    RecentBoxManager.getInstance().put(recentMiniEntity);
                    CustomRecentActivity.this.log(recentMiniEntity.getAppName());
                }
            });
            this.collectHelperCalBack.setDragListener(new DragHelperCallback.DragListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.6
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onCollectAreaChange(boolean z, boolean z2) {
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onCollectFinish(boolean z, int i) {
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragStart(int i) {
                    try {
                        ShakeUtils.vibrate(CustomRecentActivity.this.activityContext, 200L);
                        CustomRecentActivity.this.deleteLayout.setVisibility(0);
                        CustomRecentActivity.this.mLinFeature.setVisibility(8);
                        CustomRecentActivity.this.refreshLayout.setEnabled(false);
                        CustomRecentActivity.this.collectOrderList.clear();
                        for (RecentMiniEntity recentMiniEntity : CustomRecentActivity.this.collectGridAdpater.getList()) {
                            CustomRecentActivity.this.collectOrderList.add(recentMiniEntity.getAppId());
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("我的收藏拖动初始化异常:" + e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragAreaChange(boolean z, boolean z2) {
                    if (z2) {
                        return;
                    }
                    try {
                        if (z) {
                            CustomRecentActivity.this.deleteText.setText("松手即可删除");
                        } else {
                            CustomRecentActivity.this.deleteText.setText("删除");
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7978e("我的收藏拖动到收藏回调异常:" + e.getMessage());
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.recentmenu.DragHelperCallback.DragListener
                public void onDragFinish(boolean z, final int i) {
                    try {
                        CustomRecentActivity.this.refreshLayout.setEnabled(true);
                        CustomRecentActivity.this.deleteLayout.setVisibility(8);
                        CustomRecentActivity.this.mLinFeature.setVisibility(0);
                        CustomRecentActivity.this.deleteText.setText("删除");
                        if (z) {
                            if (CustomRecentActivity.this.isButtonClickable) {
                                CustomRecentActivity.this.isButtonClickable = false;
                                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.6.1
                                    @Override // java.lang.Runnable
                                    public void run() {
                                        CustomRecentActivity.this.isButtonClickable = true;
                                    }
                                }, 2000L);
                                CustomRecentActivity.this.pdShow();
                                RecentMiniEntity recentMiniEntity = CustomRecentActivity.this.collectGridAdpater.getList().get(i);
                                CustomRecentActivity.this.recentCustomManager.deleteOrAddMenu(RecentCustomManager.CANCEL, recentMiniEntity.getAppId(), recentMiniEntity.getProductId(), new Consumer<RecentStateEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.6.2
                                    @Override // io.reactivex.functions.Consumer
                                    public void accept(RecentStateEntity recentStateEntity) throws Exception {
                                        CustomRecentActivity.this.pdDissmiss();
                                        try {
                                            if (recentStateEntity.isSuccess()) {
                                                CustomRecentActivity.this.collectGridAdpater.removeItem(i);
                                                CustomRecentActivity.this.getCollectionData();
                                                FastToast.showText(CustomRecentActivity.this.activityContext, "删除收藏成功");
                                            } else {
                                                FastToast.showText(CustomRecentActivity.this.activityContext, "删除收藏失败");
                                            }
                                        } catch (Exception unused) {
                                            FastToast.showText(CustomRecentActivity.this.activityContext, "删除收藏失败");
                                        }
                                    }
                                });
                                CustomRecentActivity customRecentActivity = CustomRecentActivity.this;
                                customRecentActivity.log("我的收藏" + recentMiniEntity.getAppName() + "删除");
                            } else {
                                FastToast.showText(CustomRecentActivity.this.activityContext, "操作过于频繁，请稍后再试");
                            }
                        } else {
                            List<RecentMiniEntity> list = CustomRecentActivity.this.collectGridAdpater.getList();
                            if (!CustomRecentActivity.this.isListEqual(list)) {
                                CustomRecentActivity.this.pdShow();
                                CustomRecentActivity.this.recentCustomManager.handSOrtCollect(list, new Consumer<Boolean>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.6.3
                                    @Override // io.reactivex.functions.Consumer
                                    public void accept(Boolean bool) throws Exception {
                                        CustomRecentActivity.this.pdDissmiss();
                                        if (bool.booleanValue()) {
                                            UIUtils.toastCenter("排序成功");
                                        } else {
                                            UIUtils.toastCenter("排序失败");
                                        }
                                    }
                                });
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.collectGridAdpater.setAdapterClickInterface(new MiniGridAdpater.AdapterClickInterface() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.7
                @Override // com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater.AdapterClickInterface
                public void onClick(int i, RecentMiniEntity recentMiniEntity) {
                    MenuEntity menuEntity = new MenuEntity();
                    menuEntity.setMenuIconURL(recentMiniEntity.getAppImg());
                    menuEntity.setMenuURL(recentMiniEntity.getAppletUrl());
                    menuEntity.setMenuTitle(recentMiniEntity.getAppName());
                    IntentManager.generateIntentAndGo(CustomRecentActivity.this.activityContext, menuEntity, "get");
                    RecentBoxManager.getInstance().put(recentMiniEntity);
                    CustomRecentActivity.this.log(recentMiniEntity.getAppName());
                }
            });
            this.backImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$CustomRecentActivity$VSD0nqvMe0qa_dCDQSD_KVpTYSU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomRecentActivity.lambda$initListener$2(CustomRecentActivity.this, view);
                }
            });
            this.recentMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$CustomRecentActivity$9Kqa4Ud5n6Pqeky2TS82n-Tu4A4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomRecentActivity.lambda$initListener$3(CustomRecentActivity.this, view);
                }
            });
            this.collectMore.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.-$$Lambda$CustomRecentActivity$n0mLG2Es6knBFqLuxLYxoCwFjmc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomRecentActivity.lambda$initListener$4(CustomRecentActivity.this, view);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initListener$2(CustomRecentActivity customRecentActivity, View view) {
        customRecentActivity.finish();
        customRecentActivity.log("回到首页");
    }

    public static /* synthetic */ void lambda$initListener$3(CustomRecentActivity customRecentActivity, View view) {
        CollectionMoreActivity.newStartActivity(customRecentActivity.activityContext, 1);
        customRecentActivity.log("最近使用更多");
    }

    public static /* synthetic */ void lambda$initListener$4(CustomRecentActivity customRecentActivity, View view) {
        CollectionMoreActivity.newStartActivity(customRecentActivity.activityContext, 2);
        customRecentActivity.log("我的收藏更多");
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        try {
            notifyRecent();
            if (this.isCreate) {
                notifyCollect(this.recentCustomManager.parserCollectEntity(CacheDataCenter.getInstance().getXialaCollectData("1_"), "1"));
                this.isCreate = false;
            }
            getCollectionData();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    public void getCollectionData() {
        this.recentCustomManager.getCollect("1", "1", new Consumer<CollectEntity>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.8
            @Override // io.reactivex.functions.Consumer
            public void accept(CollectEntity collectEntity) throws Exception {
                CustomRecentActivity.this.pdDissmiss();
                CustomRecentActivity.this.notifyCollect(collectEntity);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.9
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                try {
                    CustomRecentActivity.this.pdDissmiss();
                    CollectEntity collectEntity = new CollectEntity();
                    collectEntity.setAppList(CustomRecentActivity.this.collectGridAdpater.getList());
                    collectEntity.setCollectCount(CustomRecentActivity.this.collectGridAdpater.getList().size() + "");
                    CustomRecentActivity.this.notifyCollect(collectEntity);
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyRecent() {
        List<RecentMiniEntity> list = RecentBoxManager.getInstance().get();
        this.recentMore.setVisibility(8);
        if (list.size() > 0) {
            this.recentGridView.setVisibility(0);
            this.recentGridAdpater.update(list);
            if (list.size() > 8) {
                this.recentMore.setVisibility(0);
            }
            this.recentTitleLayout.setVisibility(0);
        } else {
            this.recentGridAdpater.update(new ArrayList());
            this.recentGridView.setVisibility(8);
            this.recentTitleLayout.setVisibility(8);
        }
        changeEmptyUI();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyCollect(CollectEntity collectEntity) {
        int i;
        try {
            List<RecentMiniEntity> appList = collectEntity.getAppList();
            try {
                i = Integer.parseInt(collectEntity.getCollectCount());
            } catch (Exception e) {
                e.printStackTrace();
                i = 0;
            }
            this.collectGridAdpater.update(appList);
            if (appList.size() > 0) {
                this.emptyCollectLayout.setVisibility(8);
                this.emptyActivityLayout.setVisibility(8);
                if (i > 8) {
                    this.collectMore.setVisibility(0);
                } else {
                    this.collectMore.setVisibility(8);
                }
                if (i > 4) {
                    RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.collectMengCengLayout.getLayoutParams();
                    layoutParams.height = UIUtils.dip2px(this.activityContext, 150.0f);
                    this.collectMengCengLayout.setLayoutParams(layoutParams);
                } else {
                    RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.collectMengCengLayout.getLayoutParams();
                    layoutParams2.height = UIUtils.dip2px(this.activityContext, 75.0f);
                    this.collectMengCengLayout.setLayoutParams(layoutParams2);
                }
            } else {
                RelativeLayout.LayoutParams layoutParams3 = (RelativeLayout.LayoutParams) this.collectMengCengLayout.getLayoutParams();
                layoutParams3.height = UIUtils.dip2px(this.activityContext, 150.0f);
                this.collectMengCengLayout.setLayoutParams(layoutParams3);
                this.collectMore.setVisibility(8);
                CacheDataCenter.getInstance().setXialaCollectData("", "1_");
            }
            changeEmptyUI();
        } catch (Exception e2) {
            MsLogUtil.m7978e(e2.getMessage());
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x003d, code lost:
        if (r1.size() != 0) goto L21;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0040, code lost:
        r4.emptyActivityLayout.setVisibility(8);
        r4.emptyCollectLayout.setVisibility(8);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void changeEmptyUI() {
        /*
            r4 = this;
            com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater r0 = r4.recentGridAdpater     // Catch: java.lang.Exception -> L56
            java.util.List r0 = r0.getList()     // Catch: java.lang.Exception -> L56
            com.sinovatech.unicom.separatemodule.recentmenu.MiniGridAdpater r1 = r4.collectGridAdpater     // Catch: java.lang.Exception -> L56
            java.util.List r1 = r1.getList()     // Catch: java.lang.Exception -> L56
            r2 = 0
            r3 = 8
            if (r0 == 0) goto L37
            int r0 = r0.size()     // Catch: java.lang.Exception -> L56
            if (r0 != 0) goto L18
            goto L37
        L18:
            if (r1 == 0) goto L2c
            int r0 = r1.size()     // Catch: java.lang.Exception -> L56
            if (r0 != 0) goto L21
            goto L2c
        L21:
            android.widget.LinearLayout r0 = r4.emptyActivityLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L56
            android.widget.LinearLayout r0 = r4.emptyCollectLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L56
            goto L5e
        L2c:
            android.widget.LinearLayout r0 = r4.emptyActivityLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L56
            android.widget.LinearLayout r0 = r4.emptyCollectLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r2)     // Catch: java.lang.Exception -> L56
            goto L5e
        L37:
            if (r1 == 0) goto L4b
            int r0 = r1.size()     // Catch: java.lang.Exception -> L56
            if (r0 != 0) goto L40
            goto L4b
        L40:
            android.widget.LinearLayout r0 = r4.emptyActivityLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L56
            android.widget.LinearLayout r0 = r4.emptyCollectLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L56
            goto L5e
        L4b:
            android.widget.LinearLayout r0 = r4.emptyActivityLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r2)     // Catch: java.lang.Exception -> L56
            android.widget.LinearLayout r0 = r4.emptyCollectLayout     // Catch: java.lang.Exception -> L56
            r0.setVisibility(r3)     // Catch: java.lang.Exception -> L56
            goto L5e
        L56:
            r0 = move-exception
            java.lang.String r0 = r0.getMessage()
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7978e(r0)
        L5e:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.changeEmptyUI():void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isListEqual(List<RecentMiniEntity> list) {
        if (this.collectOrderList.size() == list.size()) {
            for (int i = 0; i < this.collectOrderList.size(); i++) {
                if (!this.collectOrderList.get(i).equals(list.get(i).getAppId())) {
                    return false;
                }
            }
            return true;
        }
        return true;
    }

    public void loadSearch(ManagerLocation.LocationEntity locationEntity) {
        this.customSearchView.setBackGroundColor(-1);
        Observable.zip(this.searchManager.loadScrollKeywordFromNetwork(), this.searchManager.loadHuoDongFromNetwork(locationEntity), new BiFunction<List<SearchEntity>, List<SearchHuoDongEntity>, List<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.11
            @Override // io.reactivex.functions.BiFunction
            public List<SearchEntity> apply(List<SearchEntity> list, List<SearchHuoDongEntity> list2) throws Exception {
                try {
                    CustomRecentActivity.this.customSearchView.setData(list, list2);
                    return null;
                } catch (Exception e) {
                    UIUtils.logE(e.getMessage());
                    return null;
                }
            }
        }).subscribe(new Observer<List<SearchEntity>>() { // from class: com.sinovatech.unicom.separatemodule.recentmenu.CustomRecentActivity.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(List<SearchEntity> list) {
                UIUtils.logD("HomeFragment", "onNext: =" + list);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdDissmiss() {
        try {
            if (isDestroyed() || isFinishing() || this.f18597pd == null || !this.f18597pd.isShowing()) {
                return;
            }
            this.f18597pd.dismiss();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void pdShow() {
        try {
            if (isFinishing() || this.f18597pd == null || this.f18597pd.isShowing()) {
                return;
            }
            this.f18597pd.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void log(String str) {
        PvCurrencyLogUtils.pvXiala("我的服务页面", str, "下拉进入小程序");
    }
}
