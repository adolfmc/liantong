package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.eventbus.ChangeMainTabIconEvent;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeCardBgEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeCardBgEntity_;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.live.capture.util.TimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.search.ShowImageUtils;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import io.objectbox.Box;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UnicomHomeTopBgManager {
    private static final String TAG = "UnicomHomeTopBgManager";
    private Activity activityContext;
    private OnToolBarBackgroundColorListener barChangeColorListener;
    private RelativeLayout.LayoutParams cacheBgParams;
    private ImageView mImgCardBg;
    private View mImgCardBgMengCeng;
    private View mRootView;
    private boolean isBigCard = false;
    private boolean isTuiJian = true;
    private final int smallBgHeight = 158;
    private Box<HomeCardBgEntity> cacheBox = App.getBoxStore().boxFor(HomeCardBgEntity.class);

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager$OnToolBarBackgroundColorListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnToolBarBackgroundColorListener {
        void toolBarBackgroundColor(String str);
    }

    public UnicomHomeTopBgManager(View view, Activity activity) {
        this.mRootView = view;
        this.activityContext = activity;
        setTuiJian(true, 10);
    }

    public void setBarChangeColorListener(OnToolBarBackgroundColorListener onToolBarBackgroundColorListener) {
        this.barChangeColorListener = onToolBarBackgroundColorListener;
        initView();
    }

    public void initView() {
        this.mImgCardBg = (ImageView) this.mRootView.findViewById(2131297116);
        this.mImgCardBgMengCeng = this.mRootView.findViewById(2131297118);
        setCardBg(getCache(), 4);
    }

    public void setBgCacheAndShow(HomeCardBgEntity homeCardBgEntity) {
        try {
            MsLogUtil.m7979d("TUIJIAN_TAB", "设置" + isTuiJian());
            if (isTuiJian()) {
                setCardBg(homeCardBgEntity, 5);
            }
            List<HomeCardBgEntity> find = this.cacheBox.query().equal(HomeCardBgEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find();
            if (find != null && find.size() > 0) {
                this.cacheBox.remove(find);
            }
            if (homeCardBgEntity != null) {
                this.cacheBox.put((Box<HomeCardBgEntity>) homeCardBgEntity);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "设置动态感知缓存数据异常 : " + e.getMessage());
        }
    }

    public HomeCardBgEntity getCache() {
        try {
            List<HomeCardBgEntity> find = this.cacheBox.query().equal(HomeCardBgEntity_.mobile, UserManager.getInstance().getCurrentPhoneNumber()).build().find();
            if (find == null || find.size() <= 0) {
                return null;
            }
            HomeCardBgEntity homeCardBgEntity = find.get(0);
            if (homeCardBgEntity == null || !TextUtils.equals(TimeUtil.getCurrentDate(), homeCardBgEntity.getCacheTime())) {
                this.cacheBox.remove(find);
                return null;
            }
            return homeCardBgEntity;
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "获取首页卡片动态感知缓存异常:" + e.getMessage());
            return null;
        }
    }

    public void loadCardCacheData() {
        MsLogUtil.m7979d("TUIJIAN_TAB", "加载缓存背景数据 tuijian" + isTuiJian());
        if (isTuiJian()) {
            setCardBg(getCache(), 6);
        }
    }

    public void setCardBg(HomeCardBgEntity homeCardBgEntity, int i) {
        MsLogUtil.m7979d("TUIJIAN_TAB", "加载背景数据 = " + isTuiJian() + " 位置 = " + i);
        this.isBigCard = false;
        ImageView imageView = this.mImgCardBg;
        if (imageView == null || this.activityContext == null) {
            return;
        }
        try {
            imageView.setColorFilter((ColorFilter) null);
            ChangeMainTabIconEvent changeMainTabIconEvent = new ChangeMainTabIconEvent(EventBusUtils.EVENT_CHANGE_MAIN_TAB_ICON);
            changeMainTabIconEvent.setTab_code(ChangeMainTabIconEvent.TAB_HOME);
            this.mImgCardBgMengCeng.setVisibility(8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mImgCardBg.getLayoutParams();
            this.cacheBgParams = layoutParams;
            BackgroundSkinBean customSkinBean = CacheDataCenter.getInstance().getCustomSkinBean();
            if (homeCardBgEntity == null) {
                if (customSkinBean != null) {
                    loadCustomCard(customSkinBean, layoutParams, changeMainTabIconEvent);
                } else {
                    loadDefaultCard(layoutParams, changeMainTabIconEvent);
                }
            } else if (TextUtils.equals("5", homeCardBgEntity.getBackgroundPriority())) {
                loadNetCard(homeCardBgEntity, layoutParams, changeMainTabIconEvent);
            } else if (customSkinBean != null) {
                loadCustomCard(customSkinBean, layoutParams, changeMainTabIconEvent);
            } else {
                loadNetCard(homeCardBgEntity, layoutParams, changeMainTabIconEvent);
            }
        } catch (Exception e) {
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mImgCardBg.getLayoutParams();
            ChangeMainTabIconEvent changeMainTabIconEvent2 = new ChangeMainTabIconEvent(EventBusUtils.EVENT_CHANGE_MAIN_TAB_ICON);
            changeMainTabIconEvent2.setTab_code(ChangeMainTabIconEvent.TAB_HOME);
            BackgroundSkinBean customSkinBean2 = CacheDataCenter.getInstance().getCustomSkinBean();
            if (customSkinBean2 != null) {
                loadCustomCard(customSkinBean2, layoutParams2, changeMainTabIconEvent2);
            } else {
                loadDefaultCard(layoutParams2, changeMainTabIconEvent2);
            }
            String str = TAG;
            MsLogUtil.m7977e(str, "设置首页卡片背景异常:" + e.getMessage());
        }
    }

    public void loadDefaultCard(RelativeLayout.LayoutParams layoutParams, ChangeMainTabIconEvent changeMainTabIconEvent) {
        loadDefaultCard(layoutParams, changeMainTabIconEvent, 0);
    }

    public void loadDefaultCard(RelativeLayout.LayoutParams layoutParams, ChangeMainTabIconEvent changeMainTabIconEvent, int i) {
        try {
            if (i == 0) {
                if (this.barChangeColorListener != null) {
                    this.barChangeColorListener.toolBarBackgroundColor("");
                }
                changeSmallBackGroundParams(layoutParams);
            } else {
                changeSmallBackGroundParams(layoutParams, i);
                if (this.barChangeColorListener != null) {
                    this.barChangeColorListener.toolBarBackgroundColor("#00000000");
                }
            }
            this.mImgCardBg.setImageResource(2131231215);
            this.mImgCardBgMengCeng.setVisibility(8);
            if (changeMainTabIconEvent != null) {
                changeMainTabIconEvent.setIconUrl("");
                changeMainTabIconEvent.setTextPictureColor("");
            }
            this.mImgCardBg.setOnClickListener(null);
            if (changeMainTabIconEvent != null) {
                changeMainTabIconEvent.setTab_code(ChangeMainTabIconEvent.TAB_HOME);
                EventBusUtils.post(changeMainTabIconEvent);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "加载兜底卡片数据异常:" + e.getMessage());
        }
    }

    public void loadCustomCard(BackgroundSkinBean backgroundSkinBean, RelativeLayout.LayoutParams layoutParams, ChangeMainTabIconEvent changeMainTabIconEvent) {
        try {
            changeSmallBackGroundParams(layoutParams);
            showMengCeng(backgroundSkinBean);
            if (changeMainTabIconEvent != null) {
                String bottomIcon = backgroundSkinBean.getBottomIcon();
                String textPictureColor = backgroundSkinBean.getTextPictureColor();
                changeMainTabIconEvent.setIconUrl(bottomIcon);
                changeMainTabIconEvent.setTextPictureColor(textPictureColor);
            }
            if (this.barChangeColorListener != null) {
                this.barChangeColorListener.toolBarBackgroundColor(backgroundSkinBean.getTitleColor());
            }
            ShowImageUtils.showImageView(this.activityContext, backgroundSkinBean.getProductImgUrl(), 2131231215, 2131231215, this.mImgCardBg);
            this.mImgCardBg.setOnClickListener(null);
            if (changeMainTabIconEvent != null) {
                EventBusUtils.post(changeMainTabIconEvent);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "加载自定义皮肤异常:" + e.getMessage());
        }
    }

    public void loadNetCard(final HomeCardBgEntity homeCardBgEntity, RelativeLayout.LayoutParams layoutParams, ChangeMainTabIconEvent changeMainTabIconEvent) {
        try {
            if (this.barChangeColorListener != null) {
                this.barChangeColorListener.toolBarBackgroundColor(homeCardBgEntity.getCornerDesc());
            }
            if (changeMainTabIconEvent != null) {
                String productDesc = homeCardBgEntity.getProductDesc();
                String textPictureColor = homeCardBgEntity.getTextPictureColor();
                changeMainTabIconEvent.setIconUrl(productDesc);
                changeMainTabIconEvent.setTextPictureColor(textPictureColor);
            }
            if (TextUtils.equals("1", homeCardBgEntity.getProductName())) {
                changeBigBackGroundParams(layoutParams);
                this.mImgCardBg.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        IntentManager.gotoWebViewActivity(UnicomHomeTopBgManager.this.activityContext, homeCardBgEntity.getProductRedirecturl(), "");
                        try {
                            String goodsId = homeCardBgEntity.getGoodsId();
                            String actId = homeCardBgEntity.getActId();
                            String actType = homeCardBgEntity.getActType();
                            PvCurrencyLogUtils.pvLogMainDJ("1010401", homeCardBgEntity.getProductRedirecturl() + "", homeCardBgEntity.getProductUrl() + "", goodsId + "," + actType + "," + actId, "背景主题组件", "", homeCardBgEntity.getCornerDesc() + "");
                            UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setCodeId("1010401").setPageName("首页").setPbName("背景主题组件").setActId(actId).setStoreyCode("101").setGoodSid(goodsId).setActType(actType).build());
                            UnicomHomeLogUtils.getInstance().putLogData(UnicomHomeLogUtils.LOG_TYPE_BG, new HomeLogEntity("1010401", "背景主题组件"));
                        } catch (Exception e) {
                            MsLogUtil.m7978e(e.getMessage());
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                changeSmallBackGroundParams(layoutParams);
                this.mImgCardBg.setOnClickListener(null);
            }
            if (TextUtils.equals("1", homeCardBgEntity.getImgDynamic())) {
                ShowImageUtils.showImageViewGIF(this.activityContext, homeCardBgEntity.getProductUrl(), 2131231215, 2131231215, this.mImgCardBg, new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager.2
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                        MsLogUtil.m7979d("TUIJIAN_TAB", "加载失败 GIF 推荐 = " + UnicomHomeTopBgManager.this.isTuiJian());
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                        MsLogUtil.m7979d("TUIJIAN_TAB", "加载成功 GIF 推荐 = " + UnicomHomeTopBgManager.this.isTuiJian());
                        if (UnicomHomeTopBgManager.this.isTuiJian()) {
                            return false;
                        }
                        MsLogUtil.m7979d("TUIJIAN_TAB", "加载成功 GIF 不是推荐替换成默认图片");
                        UnicomHomeTopBgManager.this.mImgCardBg.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager.2.1
                            @Override // java.lang.Runnable
                            public void run() {
                                UnicomHomeTopBgManager.this.mImgCardBg.setImageResource(2131231215);
                            }
                        }, 5L);
                        return false;
                    }
                });
            } else {
                ShowImageUtils.showImageViewImg(this.activityContext, homeCardBgEntity.getProductUrl(), 2131231215, 2131231215, this.mImgCardBg, new RequestListener<Bitmap>() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager.3
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Bitmap> target, boolean z) {
                        MsLogUtil.m7979d("TUIJIAN_TAB", "加载失败 推荐 = " + UnicomHomeTopBgManager.this.isTuiJian());
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Bitmap bitmap, Object obj, Target<Bitmap> target, DataSource dataSource, boolean z) {
                        MsLogUtil.m7979d("TUIJIAN_TAB", "加载成功 推荐 = " + UnicomHomeTopBgManager.this.isTuiJian());
                        if (UnicomHomeTopBgManager.this.isTuiJian()) {
                            return false;
                        }
                        MsLogUtil.m7979d("TUIJIAN_TAB", "加载成功 不是推荐替换成默认图片");
                        UnicomHomeTopBgManager.this.mImgCardBg.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.manager.UnicomHomeTopBgManager.3.1
                            @Override // java.lang.Runnable
                            public void run() {
                                UnicomHomeTopBgManager.this.mImgCardBg.setImageResource(2131231215);
                            }
                        }, 5L);
                        return false;
                    }
                });
            }
            if (changeMainTabIconEvent != null) {
                EventBusUtils.post(changeMainTabIconEvent);
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "动态感知加载网络数据异常:" + e.getMessage());
        }
    }

    public void restBgState(int i) {
        MsLogUtil.m7979d("TUIJIAN_TAB", "重置动态感知背景 位置标识 = " + i);
        setTuiJian(true, 1);
        setCardBg(getCache(), 1);
    }

    public void loadTabDefaultBg() {
        setTuiJian(false, 2);
        loadDefaultCard(this.cacheBgParams, null);
    }

    public void changeTopBgStatus() {
        setTuiJian(false, 11);
        loadDefaultCard(this.cacheBgParams, null, 120);
    }

    private void changeSmallBackGroundParams(RelativeLayout.LayoutParams layoutParams, int i) {
        try {
            this.isBigCard = false;
            float f = i;
            layoutParams.height = UIUtils.dip2px(this.activityContext, f);
            this.mImgCardBg.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mImgCardBgMengCeng.getLayoutParams();
            layoutParams2.height = UIUtils.dip2px(this.activityContext, f);
            this.mImgCardBgMengCeng.setLayoutParams(layoutParams2);
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "修改小图卡片参数异常:" + e.getMessage());
        }
    }

    private void changeSmallBackGroundParams(RelativeLayout.LayoutParams layoutParams) {
        try {
            this.isBigCard = false;
            layoutParams.height = UIUtils.dip2px(this.activityContext, 158.0f);
            this.mImgCardBg.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mImgCardBgMengCeng.getLayoutParams();
            layoutParams2.height = UIUtils.dip2px(this.activityContext, 158.0f);
            this.mImgCardBgMengCeng.setLayoutParams(layoutParams2);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "修改小图卡片参数异常:" + e.getMessage());
        }
    }

    private void changeBigBackGroundParams(RelativeLayout.LayoutParams layoutParams) {
        try {
            this.isBigCard = true;
            layoutParams.height = UIUtils.dip2px(this.activityContext, 237.0f);
            this.mImgCardBg.setLayoutParams(layoutParams);
            RelativeLayout.LayoutParams layoutParams2 = (RelativeLayout.LayoutParams) this.mImgCardBgMengCeng.getLayoutParams();
            layoutParams2.height = UIUtils.dip2px(this.activityContext, 237.0f);
            this.mImgCardBgMengCeng.setLayoutParams(layoutParams2);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "修改大图卡片参数异常:" + e.getMessage());
        }
    }

    public void showMengCeng(BackgroundSkinBean backgroundSkinBean) {
        if (TextUtils.equals("custom_group", backgroundSkinBean.getShowType())) {
            this.mImgCardBgMengCeng.setVisibility(0);
        }
    }

    public void updateHomeSkin() {
        if (isTuiJian()) {
            setCardBg(getCache(), 3);
        }
    }

    public boolean isTuiJian() {
        return this.isTuiJian;
    }

    public void setTuiJian(boolean z, int i) {
        MsLogUtil.m7979d("TUIJIAN_TAB", "设置是否推荐标识 = " + z + " 位置 = " + i);
        this.isTuiJian = z;
    }

    public boolean isBgVisialbe() {
        return UIUtils.checkIsVisible(this.activityContext, this.mImgCardBg).booleanValue();
    }
}
