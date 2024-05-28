package com.sinovatech.unicom.basic.p315ui.home.fragment;

import android.os.Bundle;
import android.os.Handler;
import com.app.hubert.guide.NewbieGuide;
import com.app.hubert.guide.core.Controller;
import com.app.hubert.guide.listener.OnGuideChangedListener;
import com.app.hubert.guide.model.GuidePage;
import com.megvii.livenesslib.util.FoldUtils;
import com.sinovatech.unicom.basic.eventbus.HomeGuidEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.fragment.HomeWebFragment;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.p315ui.home.view.UnicomHomeCardView;
import com.sinovatech.unicom.basic.p315ui.home.view.UnicomJinGangQuView;
import com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.decklayout.ConsecutiveScrollerLayout;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.home.fragment.UnicomHomeTuiJianFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomHomeTuiJianFragment extends LazyFragment {
    public static final String INTENT_PARAMS = "intent_params";
    public static final String TAG = "UnicomHomeTuiJianFragment";
    private HomeWebFragment JingXuanWebFragment;
    private UnicomHomeCardView cardView;
    private WebParamsEntity paramsEntity;
    private ConsecutiveScrollerLayout scrollerLayout;
    private String name = "";
    private boolean isFrontAndRearSwitching = false;
    public boolean isShowGuid = false;
    public boolean isResume = false;

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.BaseLazyFragment, com.sinovatech.unicom.separatemodule.miniprogram.web.BaseWebFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        UnicomHomeConstants.isBackGround = true;
        Bundle arguments = getArguments();
        if (arguments != null) {
            this.paramsEntity = (WebParamsEntity) arguments.getParcelable(INTENT_PARAMS);
            WebParamsEntity webParamsEntity = this.paramsEntity;
            if (webParamsEntity != null) {
                this.name = webParamsEntity.getTitle();
            }
        }
        MsLogUtil.m7979d(HomeWebFragment.TAG, "HomeWebFragment onCreate " + this.name);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment
    public void onCreateViewLazy(Bundle bundle) {
        super.onCreateViewLazy(bundle);
        MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 onCreateViewLazy");
        setContentView(2131493487);
        this.cardView = (UnicomHomeCardView) findViewById(2131299188);
        this.scrollerLayout = (ConsecutiveScrollerLayout) findViewById(2131298478);
        initFragment();
        initView();
        setGongJiRi();
        printLog("onCreateViewLazy()");
    }

    public void scrollTop() {
        this.scrollerLayout.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.fragment.UnicomHomeTuiJianFragment.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    UnicomHomeTuiJianFragment.this.scrollerLayout.scrollTo(0, 0);
                    UnicomHomeTuiJianFragment.this.JingXuanWebFragment.scrollTop();
                } catch (Exception e) {
                    MsLogUtil.m7977e(UnicomHomeTuiJianFragment.TAG, e.getMessage());
                }
            }
        }, 20L);
    }

    public void refreshLoginMember() {
        UnicomHomeCardView unicomHomeCardView = this.cardView;
        if (unicomHomeCardView != null) {
            unicomHomeCardView.loadHomeCardBottomView();
        }
    }

    private void initFragment() {
        try {
            if (this.paramsEntity == null) {
                this.paramsEntity = new WebParamsEntity();
            }
            WebParamsEntity webParamsEntity = this.paramsEntity;
            webParamsEntity.setTitle("" + this.paramsEntity.getTitle());
            this.JingXuanWebFragment = HomeWebFragment.newIntence(this.paramsEntity, 0, false);
            getChildFragmentManager().beginTransaction().replace(2131299189, this.JingXuanWebFragment).commitAllowingStateLoss();
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化推荐fragment异常:" + e.getMessage());
        }
    }

    public void setGongJiRi() {
        try {
            if (getContentView() != null) {
                UIUtils.setGJR(getContentView());
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("HomeFragment", "setGongJiRi() 异常:" + e.getMessage());
        }
    }

    public void initView() {
        try {
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "初始化卡片上的View");
            this.cardView.initView();
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "初始化view异常:" + e.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment
    public void onResumeLazy(boolean z) {
        boolean z2;
        super.onResumeLazy(z);
        try {
            this.isResume = true;
            printLog("onResumeLazy() isOnResume = " + z);
            if (this.JingXuanWebFragment != null) {
                this.JingXuanWebFragment.onResumeLazy(z);
            }
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 onResumeLazy");
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 刷新类型 = " + UnicomHomeConstants.tuijian_refresh_type);
            switch (UnicomHomeConstants.tuijian_refresh_type) {
                case 1:
                    MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 刷新卡片数据");
                    loadCardData();
                    z2 = true;
                    break;
                case 2:
                    refreshLoginMember();
                    MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 刷新软登录");
                    z2 = false;
                    break;
                default:
                    MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 不刷新数据");
                    z2 = false;
                    break;
            }
            MsLogUtil.m7979d("qianhoutai", "onResumeLazy  isFrontAndRearSwitching" + this.isFrontAndRearSwitching);
            if (this.isFrontAndRearSwitching) {
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 前后台切换 刷新卡片数据");
                loadCardData();
                z2 = true;
            }
            if (!z2) {
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 没有刷新卡片数据时 单独刷新 服务提醒数据");
                this.cardView.loadResume();
            }
            this.isFrontAndRearSwitching = false;
            if (App.appNeedRefreshList != null && App.appNeedRefreshList.size() > 0 && App.appNeedRefreshList.contains("home")) {
                loadCardData();
                App.appNeedRefreshList.remove("home");
            }
            UnicomHomeConstants.tuijian_refresh_type = 0;
            UnicomHomeConstants.isBackGround = false;
            if (UnicomJinGangQuView.clickCustomMenu) {
                UnicomJinGangQuView.clickCustomMenu = false;
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 自定义导航刷新金刚区数据");
                this.cardView.loadJGQView(1);
            }
            if (OptionValveUtil.isRequestFinish) {
                showGuidView();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "onResume异常:" + e.getMessage());
        }
    }

    public void updateJinGangQuMoreText() {
        try {
            if (this.cardView != null) {
                this.cardView.updateJinGangQuMoreText();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "修改金刚区文案异常:" + e.getMessage());
        }
    }

    public void updateJinGangQu() {
        try {
            if (this.cardView != null) {
                this.cardView.loadJGQView(6);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "刷新金刚区异常:" + e.getMessage());
        }
    }

    public void loadCardData() {
        try {
            MsLogUtil.m7979d(UnicomHomeConstants.LAZY_TAG_DATA, "刷新卡片数据");
            if (this.cardView != null) {
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 加载和话费无关的view");
                this.cardView.loadData();
            }
            HomeCardDataManager.getInstance().loadData(new HomeCardDataManager.HomeInfoInterface() { // from class: com.sinovatech.unicom.basic.ui.home.fragment.UnicomHomeTuiJianFragment.2
                @Override // com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager.HomeInfoInterface
                public void onStart() {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager.HomeInfoInterface
                public void onFinish() {
                    MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "推荐页面 加载和话费相关的数据");
                    MsLogUtil.m7980d("CESHI onFinish " + App.getLogined().toString());
                    if (UnicomHomeTuiJianFragment.this.cardView != null) {
                        UnicomHomeTuiJianFragment.this.cardView.loadCardData();
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "加载卡片数据异常:" + e.getMessage());
        }
    }

    public static UnicomHomeTuiJianFragment getInstance(WebParamsEntity webParamsEntity) {
        UnicomHomeTuiJianFragment unicomHomeTuiJianFragment = new UnicomHomeTuiJianFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(INTENT_PARAMS, webParamsEntity);
        bundle.putBoolean(LazyFragment.INTENT_BOOLEAN_LAZYLOAD, true);
        bundle.putString("tagName", webParamsEntity.getTitle());
        unicomHomeTuiJianFragment.setArguments(bundle);
        return unicomHomeTuiJianFragment;
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment
    public void onPauseLazy(boolean z) {
        super.onPauseLazy(z);
        this.isResume = false;
        HomeWebFragment homeWebFragment = this.JingXuanWebFragment;
        if (homeWebFragment != null) {
            homeWebFragment.onPauseLazy(z);
        }
        printLog("onPauseLazy() isOnPause = " + z);
    }

    public void removeAllView() {
        this.JingXuanWebFragment = null;
        this.cardView = null;
    }

    public void printLog(String str) {
        String str2 = UnicomHomeConstants.LAZY_TAG;
        MsLogUtil.m7979d(str2, this.name + "  " + str);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.home.fragment.LazyFragment, android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
        if (!App.isTopProcess) {
            this.isFrontAndRearSwitching = true;
        }
        MsLogUtil.m7979d("qianhoutai", "onPauseLazy  isFrontAndRearSwitching" + this.isFrontAndRearSwitching);
    }

    public void checkViewVisiable() {
        try {
            if (this.cardView != null) {
                UnicomHomeLogUtils.isHFVisiable = this.cardView.huaFeiIsVisiable();
                UnicomHomeLogUtils.isRDLVisiable = this.cardView.ruanDengLuIsVisiable();
                UnicomHomeLogUtils.isUNLOGINVisiable = this.cardView.unLoginIsVisiable();
                UnicomHomeLogUtils.isJQUVisiable = this.cardView.jgqIsVisiable();
            }
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "检查view可见状态异常:" + e.getMessage());
        }
    }

    public void showGuidView() {
        if (FoldUtils.isFold(this.activityContext) || !this.isResume || this.isShowGuid || App.getSharePreferenceUtil().getBoolean("unicom_app_guid_flag")) {
            return;
        }
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.fragment.UnicomHomeTuiJianFragment.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (!UnicomHomeTuiJianFragment.this.isResume || UserManager.getInstance().isYiwang() || UnicomHomeTuiJianFragment.this.isShowGuid || !App.hasLogined() || LanguageUtil.getInstance().isShowUSADialog() || !OptionValveUtil.INSTENCE.isShowScreenTab()) {
                        return;
                    }
                    App.getSharePreferenceUtil().putBoolean("unicom_app_guid_flag", true);
                    if (UnicomHomeTuiJianFragment.this.activityContext == null || UnicomHomeTuiJianFragment.this.activityContext.isDestroyed() || UnicomHomeTuiJianFragment.this.activityContext.isFinishing()) {
                        return;
                    }
                    NewbieGuide.with(UnicomHomeTuiJianFragment.this.activityContext).setLabel("unicom_home_guid").setOnGuideChangedListener(new OnGuideChangedListener() { // from class: com.sinovatech.unicom.basic.ui.home.fragment.UnicomHomeTuiJianFragment.3.1
                        @Override // com.app.hubert.guide.listener.OnGuideChangedListener
                        public void onShowed(Controller controller) {
                            UnicomHomeTuiJianFragment.this.isShowGuid = true;
                            EventBusUtils.post(new HomeGuidEvent(EventBusUtils.EVENT_MAIN_HOME_GUID_SHOW_DEF_BG));
                        }

                        @Override // com.app.hubert.guide.listener.OnGuideChangedListener
                        public void onRemoved(Controller controller) {
                            UnicomHomeTuiJianFragment.this.isShowGuid = false;
                            EventBusUtils.post(new HomeGuidEvent(EventBusUtils.EVENT_MAIN_HOME_GUID_RESET_BG));
                        }
                    }).addGuidePage(GuidePage.newInstance().setLayoutRes(2131493167, new int[0])).addGuidePage(GuidePage.newInstance().setLayoutRes(2131493169, new int[0])).addGuidePage(GuidePage.newInstance().setLayoutRes(2131493168, new int[0])).addGuidePage(GuidePage.newInstance().setLayoutRes(2131493166, new int[0])).setShowCounts(1).alwaysShow(false).show();
                } catch (Exception e) {
                    MsLogUtil.m7977e(UnicomHomeTuiJianFragment.TAG, "显示蒙层异常:" + e.getMessage());
                }
            }
        }, 500L);
    }

    public String getWebUrl() {
        return this.JingXuanWebFragment.getWebUrl();
    }
}
