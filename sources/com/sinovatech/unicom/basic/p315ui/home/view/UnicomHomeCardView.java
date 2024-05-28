package com.sinovatech.unicom.basic.p315ui.home.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import com.sinovatech.unicom.basic.p314po.HeaderEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager;
import com.sinovatech.unicom.basic.p315ui.utils.UnicomHomeConstants;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.UnicomHomeCardView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomHomeCardView extends LinearLayout {
    private static final String TAG = "UnicomHomeCardView";
    private HomeCardRuanDengLuView bottomView;
    private HeaderEntity headerEntity;
    private HomeHuaFeiView huaFeiView;
    private UnicomJinGangQuView jinGangQuView;
    private AppCompatActivity mActivity;
    private ServiceView serviceView;
    private HomeUnLoginView unLoginView;

    public UnicomHomeCardView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.mActivity = (AppCompatActivity) context;
    }

    public void initView() {
        UnicomHomeLogUtils.getInstance().removeAll();
        this.headerEntity = HomeCardDataManager.getInstance().getHeaderEntity();
        if (!App.hasLogined()) {
            initUnLoginState();
        } else if (UserManager.getInstance().isYiwang()) {
            initYwState();
        } else {
            initBwState();
        }
    }

    public void loadResume() {
        if (!App.hasLogined() || UserManager.getInstance().isYiwang()) {
            return;
        }
        MsLogUtil.m7979d(UnicomHomeConstants.LAZY_TAG_DATA, "resume 刷新服务提醒");
        loadServiceView();
    }

    public void loadCardData() {
        try {
            this.headerEntity = HomeCardDataManager.getInstance().getHeaderEntity();
            if (!App.hasLogined() || UserManager.getInstance().isYiwang()) {
                return;
            }
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "刷新软登录账号");
            loadHomeCardBottomView();
            MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "刷新话费");
            loadHuaFeiView();
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "刷和话费相关的view异常:" + e.getMessage());
        }
    }

    public void loadData() {
        try {
            if (!App.hasLogined()) {
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "刷新金刚区");
                loadJGQView(2);
            } else if (UserManager.getInstance().isYiwang()) {
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "刷新金刚区");
                loadJGQView(3);
            } else {
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "刷新金刚区");
                loadJGQView(4);
                MsLogUtil.m7979d(UnicomHomeConstants.JIA_TAG, "刷新服务提醒");
                loadServiceView();
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "加载和话费无关的view异常:" + e.getMessage());
        }
    }

    private void initUnLoginState() {
        try {
            initUnLoginView();
            initHuaFeiView();
            initJGQView(1);
            removeAllViews();
            addView(this.unLoginView);
            addView(this.huaFeiView);
            addView(this.jinGangQuView);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化未登录状态异常:" + e.getMessage());
        }
    }

    private void initBwState() {
        try {
            initHomeCardBottomView();
            initHuaFeiView();
            initJGQView(2);
            initServiceView();
            removeAllViews();
            addView(this.bottomView);
            addView(this.huaFeiView);
            addView(this.jinGangQuView);
            addView(this.serviceView);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化本网状态异常:" + e.getMessage());
        }
    }

    private void initYwState() {
        try {
            initJGQView(3);
            removeAllViews();
            addView(this.jinGangQuView);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始异网状态异常:" + e.getMessage());
        }
    }

    private void initHomeCardBottomView() {
        try {
            if (this.bottomView == null) {
                this.bottomView = new HomeCardRuanDengLuView(this.mActivity, this.headerEntity);
            }
            this.bottomView.initData(this.headerEntity);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化首页卡片底部日期view异常:" + e.getMessage());
        }
    }

    public void loadHomeCardBottomView() {
        try {
            if (this.bottomView == null) {
                this.bottomView = new HomeCardRuanDengLuView(this.mActivity, this.headerEntity);
            }
            this.bottomView.setData(this.headerEntity);
            this.bottomView.updateMemberInfo();
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化首页卡片底部日期view异常:" + e.getMessage());
        }
    }

    private void initHuaFeiView() {
        try {
            if (this.huaFeiView == null) {
                this.huaFeiView = new HomeHuaFeiView(this.mActivity, this.headerEntity);
            }
            this.huaFeiView.initData(this.headerEntity);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化话费view异常:" + e.getMessage());
        }
    }

    private void loadHuaFeiView() {
        try {
            if (this.huaFeiView == null) {
                this.huaFeiView = new HomeHuaFeiView(this.mActivity, this.headerEntity);
            }
            this.huaFeiView.setHeaderEntity(this.headerEntity);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化话费view异常:" + e.getMessage());
        }
    }

    private void initServiceView() {
        if (this.serviceView == null) {
            this.serviceView = new ServiceView(this.mActivity, ServiceView.HOME);
        }
    }

    public void loadServiceView() {
        ServiceView serviceView = this.serviceView;
        if (serviceView == null) {
            this.serviceView = new ServiceView(this.mActivity, ServiceView.HOME);
        } else {
            serviceView.getData(this.mActivity);
        }
    }

    private void initJGQView(int i) {
        try {
            MsLogUtil.m7979d("金刚区初始化", "initJGQView == " + i);
            if (this.jinGangQuView == null) {
                MsLogUtil.m7979d("金刚区初始化", "initJGQView == 重新创建 view");
                this.jinGangQuView = new UnicomJinGangQuView(this.mActivity);
            } else {
                MsLogUtil.m7979d("金刚区初始化", "initJGQView == 刷新数据");
                this.jinGangQuView.changeIndicatorState();
                this.jinGangQuView.notifyData();
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化金刚区异常:" + e.getMessage());
        }
    }

    public void loadJGQView(int i) {
        MsLogUtil.m7979d("金刚区初始化", "loadJGQView == " + i);
        if (this.jinGangQuView == null) {
            MsLogUtil.m7979d("金刚区初始化", "loadJGQView == 重新创建 view" + i);
            this.jinGangQuView = new UnicomJinGangQuView(this.mActivity);
            return;
        }
        MsLogUtil.m7979d("金刚区初始化", "loadJGQView == 加载接口数据" + i);
        this.jinGangQuView.loadMenuJinGangQu();
    }

    public void updateJinGangQuMoreText() {
        try {
            if (this.jinGangQuView != null) {
                this.jinGangQuView.updateMoreText();
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "设置金刚区文案异常:" + e.getMessage());
        }
    }

    public void initUnLoginView() {
        try {
            if (this.unLoginView == null) {
                this.unLoginView = new HomeUnLoginView(this.mActivity);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化未登录布局异常:" + e.getMessage());
        }
    }

    public boolean huaFeiIsVisiable() {
        return UIUtils.checkIsVisible(this.mActivity, this.huaFeiView).booleanValue();
    }

    public boolean ruanDengLuIsVisiable() {
        return UIUtils.checkIsVisible(this.mActivity, this.bottomView).booleanValue();
    }

    public boolean unLoginIsVisiable() {
        return UIUtils.checkIsVisible(this.mActivity, this.unLoginView).booleanValue();
    }

    public boolean jgqIsVisiable() {
        return UIUtils.checkIsVisible(this.mActivity, this.jinGangQuView).booleanValue();
    }
}
