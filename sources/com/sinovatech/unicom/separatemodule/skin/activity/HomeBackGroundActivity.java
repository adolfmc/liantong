package com.sinovatech.unicom.separatemodule.skin.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
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
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundGroupAdapter;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundBannerEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundEntity;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean_;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinEntity;
import com.sinovatech.unicom.separatemodule.skin.event.AddSkinEvent;
import com.sinovatech.unicom.separatemodule.skin.event.DeleteSkinEvent;
import com.sinovatech.unicom.separatemodule.skin.event.RefreshSkinEvent;
import com.sinovatech.unicom.separatemodule.skin.function.BackgroundFunction;
import com.sinovatech.unicom.separatemodule.skin.manager.HomeBackgroundInfoManager;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;
import io.objectbox.Box;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeBackGroundActivity extends BaseActivity {
    private static final String TAG = "HomeBackGroundActivity";
    public NBSTraceUnit _nbs_trace;
    private BannerViewPager bannerViewPager;
    private Box<BackgroundSkinBean> cacheBox;
    private BackgroundSkinBean customSkinBean;
    private HomeBackgroundInfoManager infoManager;
    private BackGroundGroupAdapter mAdapter;
    private ImageButton mImg_Back;
    private LinearLayout mLinIndicator;
    private RecyclerView mRv_MianFei;
    private TextView mTv_HF;
    private SmartRefreshLayout smartRefreshLayout;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 115);
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
            this.infoManager = new HomeBackgroundInfoManager(this);
            this.smartRefreshLayout = (SmartRefreshLayout) findViewById(2131296484);
            this.smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(false);
            this.smartRefreshLayout.setDisableContentWhenLoading(true);
            this.smartRefreshLayout.setEnableRefresh(true);
            this.smartRefreshLayout.setEnableLoadMore(false);
            this.smartRefreshLayout.setReboundInterpolator(new AccelerateInterpolator());
            this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.1
                @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    HomeBackGroundActivity.this.getSkinData();
                }
            });
            this.mImg_Back = (ImageButton) findViewById(2131296473);
            this.mTv_HF = (TextView) findViewById(2131298793);
            this.bannerViewPager = (BannerViewPager) findViewById(2131296514);
            this.mLinIndicator = (LinearLayout) findViewById(2131298632);
            this.mRv_MianFei = (RecyclerView) findViewById(2131296515);
            this.mImg_Back.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    HomeBackGroundActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mTv_HF.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    HomeBackGroundActivity.this.showChangDefaultDialog();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化view异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showChangDefaultDialog() {
        try {
            CustomDialogManager.showSkinDialog(this, "选择恢复默认后，将清除您所选中皮肤的状态，首页皮肤将展示默认推送的皮肤。", true, "取消", "恢复默认", true, new CustomDialogManager.SimpleCustomeDialogListener2() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.4
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onCancel() {
                }

                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
                public void onClickOk() {
                    try {
                        CacheDataCenter.getInstance().setCustomSkin(null);
                        EventBusUtils.post(new RefreshSkinEvent(0));
                    } catch (Exception e) {
                        String str = HomeBackGroundActivity.TAG;
                        MsLogUtil.m7977e(str, "确定恢复数据异常:" + e.getMessage());
                    }
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "恢复默认皮肤数据异常:" + e.getMessage());
        }
    }

    private void initData() {
        try {
            this.customSkinBean = CacheDataCenter.getInstance().getCustomSkinBean();
            this.cacheBox = App.getBoxStore().boxFor(BackgroundSkinBean.class);
            this.infoManager = new HomeBackgroundInfoManager(this);
            initCache();
            getSkinData();
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化数据异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSkinData() {
        this.infoManager.getBackGroundData(this.customSkinBean).subscribe(new Consumer<BackgroundEntity>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.5
            @Override // io.reactivex.functions.Consumer
            public void accept(BackgroundEntity backgroundEntity) throws Exception {
                HomeBackGroundActivity.this.setData(backgroundEntity);
            }
        }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.6
            @Override // io.reactivex.functions.Consumer
            public void accept(Throwable th) throws Exception {
                HomeBackGroundActivity.this.smartRefreshLayout.finishRefresh();
            }
        });
    }

    private void initCache() {
        try {
            this.customSkinBean = CacheDataCenter.getInstance().getCustomSkinBean();
            this.mTv_HF.setVisibility(this.customSkinBean == null ? 8 : 0);
            Observable.just(CacheDataCenter.getInstance().getSkinCache()).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new BackgroundFunction(this.customSkinBean)).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<BackgroundEntity>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.7
                @Override // io.reactivex.functions.Consumer
                public void accept(BackgroundEntity backgroundEntity) throws Exception {
                    HomeBackGroundActivity.this.setData(backgroundEntity);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.8
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    HomeBackGroundActivity.this.setData(null);
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "获取皮肤缓存数据异常:" + e.getMessage());
        }
    }

    public static /* synthetic */ ViewHolder lambda$setBannerData$0(HomeBackGroundActivity homeBackGroundActivity) {
        return new BannerViewHolder();
    }

    private void setBannerData(final List<BackgroundBannerEntity> list) {
        try {
            this.bannerViewPager.setHolderCreator(new HolderCreator() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.-$$Lambda$HomeBackGroundActivity$vXHR6_GMUKBfsBcebm_qv1377fY
                @Override // com.zhpan.bannerview.holder.HolderCreator
                public final ViewHolder createViewHolder() {
                    return HomeBackGroundActivity.lambda$setBannerData$0(HomeBackGroundActivity.this);
                }
            }).setIndicatorVisibility(8).setInterval(5000).setPageStyle(0).setOnPageClickListener(new BannerViewPager.OnPageClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.-$$Lambda$HomeBackGroundActivity$edwwWgCR7NscXvup3VDb6ZENeGQ
                @Override // com.zhpan.bannerview.BannerViewPager.OnPageClickListener
                public final void onPageClick(int i) {
                    HomeBackGroundActivity.lambda$setBannerData$1(HomeBackGroundActivity.this, list, i);
                }
            }).setOnPageChangeListener(new OnPageChangeListenerAdapter() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.HomeBackGroundActivity.9
                @Override // com.zhpan.bannerview.adapter.OnPageChangeListenerAdapter, android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                    NBSActionInstrumentation.onPageSelectedEnter(i, this);
                    super.onPageSelected(i);
                    HomeBackGroundActivity.this.changeView(list, i);
                    NBSActionInstrumentation.onPageSelectedExit();
                }
            }).create(list);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "banner异常:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setBannerData$1(HomeBackGroundActivity homeBackGroundActivity, List list, int i) {
        try {
            BackgroundBannerEntity backgroundBannerEntity = (BackgroundBannerEntity) list.get(i);
            if (backgroundBannerEntity != null) {
                IntentManager.gotoWebViewActivity(homeBackGroundActivity, backgroundBannerEntity.getUrl(), "");
                PvCurrencyLogUtils.pvLogDJ("皮肤首页", "S2ndpage1178", backgroundBannerEntity.getUrl() + "", "", backgroundBannerEntity.getProductBusinessName() + "", "轮播图", "", backgroundBannerEntity.getImg() + "", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void changeView(List<BackgroundBannerEntity> list, int i) {
        try {
            if (this.mLinIndicator == null) {
                return;
            }
            this.mLinIndicator.removeAllViews();
            if (list != null && list.size() > 1) {
                this.mLinIndicator.setVisibility(0);
                for (int i2 = 0; i2 < list.size(); i2++) {
                    View view = new View(this);
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(BannerUtils.dp2px(8.0f), BannerUtils.dp2px(3.0f));
                    if (i2 == i) {
                        view.setBackgroundResource(2131232564);
                    } else {
                        view.setBackgroundResource(2131232565);
                    }
                    layoutParams.setMargins(BannerUtils.dp2px(5.0f), 0, 0, 0);
                    view.setLayoutParams(layoutParams);
                    this.mLinIndicator.addView(view);
                }
                return;
            }
            this.mLinIndicator.setVisibility(8);
        } catch (Exception e) {
            this.mLinIndicator.setVisibility(8);
            e.printStackTrace();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BannerViewHolder implements ViewHolder<BackgroundBannerEntity> {
        private ImageView bannerView;

        public BannerViewHolder() {
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public View createView(ViewGroup viewGroup, Context context, int i) {
            View inflate = LayoutInflater.from(context).inflate(2131493435, viewGroup, false);
            this.bannerView = (ImageView) inflate.findViewById(2131296501);
            return inflate;
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public void onBind(Context context, BackgroundBannerEntity backgroundBannerEntity, int i, int i2) {
            GlideApp.with(context).load(backgroundBannerEntity.getImg()).placeholder(2131231244).error(2131231244).into(this.bannerView);
        }
    }

    public void setData(BackgroundEntity backgroundEntity) {
        try {
            ArrayList arrayList = new ArrayList();
            this.bannerViewPager.setVisibility(8);
            this.mLinIndicator.setVisibility(8);
            if (backgroundEntity == null) {
                backgroundEntity = new BackgroundEntity();
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(getCustomSkinData());
                backgroundEntity.setSkinEntities(arrayList2);
            } else {
                List<BackgroundSkinEntity> skinEntities = backgroundEntity.getSkinEntities();
                if (skinEntities == null) {
                    skinEntities = new ArrayList<>();
                }
                skinEntities.add(getCustomSkinData());
                backgroundEntity.setSkinEntities(skinEntities);
                List<BackgroundBannerEntity> bannerEntities = backgroundEntity.getBannerEntities();
                if (bannerEntities != null && bannerEntities.size() > 0) {
                    this.bannerViewPager.setVisibility(0);
                    setBannerData(bannerEntities);
                }
            }
            arrayList.addAll(backgroundEntity.getSkinEntities());
            this.mAdapter = new BackGroundGroupAdapter(this, arrayList);
            this.mAdapter.setTongYongBean(backgroundEntity.getTongYongBean());
            this.mAdapter.setCurrentBean(this.customSkinBean);
            this.mRv_MianFei.setLayoutManager(new LinearLayoutManager(this, 1, false));
            this.mRv_MianFei.setAdapter(this.mAdapter);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "设置页面数据异常:" + e.getMessage());
        }
        this.smartRefreshLayout.finishRefresh();
    }

    public BackgroundSkinEntity getCustomSkinData() {
        BackgroundSkinEntity backgroundSkinEntity = new BackgroundSkinEntity();
        backgroundSkinEntity.setGroupName("自定义皮肤");
        backgroundSkinEntity.setShowType("custom_group");
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            arrayList.addAll(this.cacheBox.query().equal(BackgroundSkinBean_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find());
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BackgroundSkinBean backgroundSkinBean = (BackgroundSkinBean) it.next();
                if (backgroundSkinBean != null) {
                    String productImgUrl = backgroundSkinBean.getProductImgUrl();
                    if (!TextUtils.isEmpty(productImgUrl) && new File(productImgUrl).exists()) {
                        if (this.customSkinBean != null && TextUtils.equals(this.customSkinBean.getProductId(), backgroundSkinBean.getProductId())) {
                            arrayList2.add(0, backgroundSkinBean);
                        } else {
                            arrayList2.add(backgroundSkinBean);
                        }
                    }
                    if (this.customSkinBean != null && TextUtils.equals(this.customSkinBean.getProductId(), backgroundSkinBean.getProductId())) {
                        CacheDataCenter.getInstance().setCustomSkin(null);
                    }
                    this.cacheBox.remove((Box<BackgroundSkinBean>) backgroundSkinBean);
                    it.remove();
                }
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "获取自定义皮肤数据异常:" + e.getMessage());
        }
        if (arrayList2.size() <= 3) {
            BackgroundSkinBean backgroundSkinBean2 = new BackgroundSkinBean();
            backgroundSkinBean2.setProductImgUrl("custom");
            backgroundSkinBean2.setProductSubtitle("APP用户专享");
            arrayList2.add(backgroundSkinBean2);
        }
        backgroundSkinEntity.setSkinList(arrayList2);
        return backgroundSkinEntity;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(RefreshSkinEvent refreshSkinEvent) {
        try {
            initCache();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void deleteSkin(DeleteSkinEvent deleteSkinEvent) {
        if (deleteSkinEvent != null) {
            try {
                delCustomSkin(deleteSkinEvent.getSkinBean());
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void adddeleteSkin(AddSkinEvent addSkinEvent) {
        if (addSkinEvent != null) {
            try {
                BackgroundSkinBean skinBean = addSkinEvent.getSkinBean();
                if (skinBean != null) {
                    this.cacheBox.put((Box<BackgroundSkinBean>) skinBean);
                    initCache();
                }
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }

    private void delCustomSkin(BackgroundSkinBean backgroundSkinBean) {
        if (backgroundSkinBean == null) {
            return;
        }
        ArrayList<BackgroundSkinBean> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        try {
            arrayList.addAll(this.cacheBox.query().equal(BackgroundSkinBean_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find());
            if (arrayList.size() > 0) {
                this.cacheBox.remove(arrayList);
                BackgroundSkinBean backgroundSkinBean2 = null;
                for (BackgroundSkinBean backgroundSkinBean3 : arrayList) {
                    if (TextUtils.equals(backgroundSkinBean3.getProductId(), backgroundSkinBean.getProductId())) {
                        backgroundSkinBean2 = backgroundSkinBean3;
                    }
                }
                if (backgroundSkinBean != null && backgroundSkinBean2 != null) {
                    if (this.customSkinBean != null && TextUtils.equals(this.customSkinBean.getProductId(), backgroundSkinBean.getProductId())) {
                        CacheDataCenter.getInstance().setCustomSkin(null);
                        EventBusUtils.post(new RefreshSkinEvent(0));
                    }
                    arrayList.remove(backgroundSkinBean2);
                    arrayList2.addAll(arrayList);
                    this.cacheBox.put(arrayList2);
                }
            }
            initCache();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
