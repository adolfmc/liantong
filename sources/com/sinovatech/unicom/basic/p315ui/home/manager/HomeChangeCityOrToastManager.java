package com.sinovatech.unicom.basic.p315ui.home.manager;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.CityChangeManager;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeToastEntity;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeChangeCityOrToastManager {
    private static final String TAG = "HomeChangeCityOrToastMa";
    private int bottomToastTime;
    private ImageView chahaoImageView;
    private ICityChange cityChange;
    private String cityName;
    private TextView contentTextView;
    private Animation down_anim;
    private boolean isShowBottomToast;
    private Context mContext;
    private ImageView mImgIcon;
    private ImageView mImgdismissToastView;
    private LinearLayout mLinCityView;
    private LinearLayout mLinToastView;
    private View mRootView;
    private TextView mTvContent;
    private TextView switchTextView;
    private Animation up_anim;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager$ICityChange */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface ICityChange {
        void change(String str);

        void onClose(String str);
    }

    public HomeChangeCityOrToastManager(Context context, View view) {
        this.bottomToastTime = 2000;
        this.mContext = context;
        this.mRootView = view;
        this.bottomToastTime = new ConfigManager().getHomeNewsRemindShowTime();
        initView();
    }

    public void initView() {
        this.mLinToastView = (LinearLayout) this.mRootView.findViewById(2131297993);
        this.mTvContent = (TextView) this.mRootView.findViewById(2131297992);
        this.mImgIcon = (ImageView) this.mRootView.findViewById(2131297991);
        this.mImgdismissToastView = (ImageView) this.mRootView.findViewById(2131297995);
        this.mLinCityView = (LinearLayout) this.mRootView.findViewById(2131297990);
        this.contentTextView = (TextView) this.mRootView.findViewById(2131297131);
        this.switchTextView = (TextView) this.mRootView.findViewById(2131297196);
        this.chahaoImageView = (ImageView) this.mRootView.findViewById(2131297115);
    }

    public void showBottomView(HomeToastEntity homeToastEntity, String str, ICityChange iCityChange) {
        try {
            this.cityName = str;
            this.cityChange = iCityChange;
            hideCityAndToastView();
            if (homeToastEntity != null) {
                MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "智慧推荐数据不为空 显示智慧推荐");
                showToastView(homeToastEntity);
            } else if (TextUtils.isEmpty(str)) {
            } else {
                MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "智慧推荐数据为空 展示省市弹窗");
                showChangeCityWiew(str, iCityChange);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showToastView(HomeToastEntity homeToastEntity) {
        try {
            if (this.mLinCityView != null) {
                this.mLinCityView.setVisibility(4);
            }
            final String productName = homeToastEntity.getProductName();
            String productDesc = homeToastEntity.getProductDesc();
            final String productRedirecturl = homeToastEntity.getProductRedirecturl();
            String iconUrl = homeToastEntity.getIconUrl();
            UIUtils.setGJR(this.mLinToastView);
            this.mLinToastView.setTranslationY(this.mLinToastView.getHeight());
            this.up_anim = AnimationUtils.loadAnimation(this.mContext, 2130772090);
            this.up_anim.setFillAfter(true);
            this.up_anim.setFillBefore(false);
            this.up_anim.setFillEnabled(true);
            this.up_anim.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager.1
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    try {
                        HomeChangeCityOrToastManager.this.mLinToastView.setVisibility(0);
                        HomeChangeCityOrToastManager.this.mLinToastView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager.1.1
                            @Override // java.lang.Runnable
                            public void run() {
                                HomeChangeCityOrToastManager.this.mLinToastView.setTranslationY(0.0f);
                            }
                        }, 100L);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    try {
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载推荐的弹出动画结束");
                        HomeChangeCityOrToastManager.this.mLinToastView.clearAnimation();
                        if (HomeChangeCityOrToastManager.this.isShowBottomToast) {
                            return;
                        }
                        HomeChangeCityOrToastManager.this.isShowBottomToast = true;
                        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager.1.2
                            @Override // java.lang.Runnable
                            public void run() {
                                if (!HomeChangeCityOrToastManager.this.isShowBottomToast || HomeChangeCityOrToastManager.this.mLinToastView == null) {
                                    return;
                                }
                                HomeChangeCityOrToastManager.this.mLinToastView.startAnimation(HomeChangeCityOrToastManager.this.down_anim);
                            }
                        }, HomeChangeCityOrToastManager.this.bottomToastTime);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.down_anim = AnimationUtils.loadAnimation(this.mContext, 2130772091);
            this.down_anim.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager.2
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载推荐的消失动画");
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    try {
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载推荐的弹出动画结束");
                        HomeChangeCityOrToastManager.this.mLinToastView.setVisibility(4);
                        HomeChangeCityOrToastManager.this.mLinToastView.clearAnimation();
                        HomeChangeCityOrToastManager.this.mTvContent.setEnabled(true);
                        HomeChangeCityOrToastManager.this.mImgdismissToastView.setEnabled(true);
                        if (HomeChangeCityOrToastManager.this.isShowBottomToast) {
                            HomeChangeCityOrToastManager.this.isShowBottomToast = false;
                        }
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载完推荐弹窗 cityName = " + HomeChangeCityOrToastManager.this.cityName);
                        if (TextUtils.isEmpty(HomeChangeCityOrToastManager.this.cityName)) {
                            return;
                        }
                        MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "加载完推荐弹窗 展示省市切换弹窗");
                        HomeChangeCityOrToastManager.this.showChangeCityWiew(HomeChangeCityOrToastManager.this.cityName, HomeChangeCityOrToastManager.this.cityChange);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            this.mTvContent.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    IntentManager.gotoWebViewActivity((Activity) HomeChangeCityOrToastManager.this.mContext, productRedirecturl, productName);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mTvContent.setText(productDesc);
            if (TextUtils.isEmpty(iconUrl)) {
                this.mImgIcon.setImageResource(2131231925);
            } else if (iconUrl.equals("gif")) {
                Glide.with(App.getInstance()).asGif().load(iconUrl).into(this.mImgIcon);
            } else {
                Glide.with(App.getInstance()).asDrawable().load(iconUrl).into(this.mImgIcon);
            }
            this.mLinToastView.startAnimation(this.up_anim);
            this.mImgdismissToastView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.HomeChangeCityOrToastManager.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (HomeChangeCityOrToastManager.this.mLinToastView != null) {
                        HomeChangeCityOrToastManager.this.mLinToastView.startAnimation(HomeChangeCityOrToastManager.this.down_anim);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideCityAndToastView() {
        try {
            if (this.mLinCityView != null) {
                this.mLinCityView.setVisibility(4);
            }
            if (this.mLinToastView != null) {
                this.mLinToastView.clearAnimation();
                this.mLinToastView.setVisibility(4);
            }
            this.isShowBottomToast = false;
        } catch (Exception e) {
            MsLogUtil.m7977e(TAG, "隐藏省市view异常:" + e.getMessage());
        }
    }

    public void showChangeCityWiew(final String str, final ICityChange iCityChange) {
        try {
            if (this.mLinToastView == null || this.mLinToastView.getVisibility() != 0) {
                String string = App.getSharePreferenceUtil().getString(CityChangeManager.PREFERENCE_SELECT_KEY);
                SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                StringBuilder sb = new StringBuilder();
                sb.append(CityChangeManager.PREFERENCE_SELECTCITYTIME_KEY());
                sb.append(string);
                if (System.currentTimeMillis() - sharePreferenceUtil.getLong(sb.toString()) > new ConfigManager().getCityChangeTime()) {
                    MsLogUtil.m7979d("JIA_BOTTOM_TOAST", "省市切换弹窗");
                    this.mLinCityView.setVisibility(0);
                    this.contentTextView.setTag(str);
                    TextView textView = this.contentTextView;
                    textView.setText("定位显示您在\"" + str + "\"");
                    TextView textView2 = this.switchTextView;
                    textView2.setText("切换至" + str);
                    this.switchTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeChangeCityOrToastManager$7qhIpSRMKU78kU_VQOMmalGF1k0
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HomeChangeCityOrToastManager.lambda$showChangeCityWiew$0(HomeChangeCityOrToastManager.this, iCityChange, str, view);
                        }
                    });
                    this.chahaoImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.manager.-$$Lambda$HomeChangeCityOrToastManager$P_JrqCKc4nQa1bLH9c6L9Z_F1Wc
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            HomeChangeCityOrToastManager.lambda$showChangeCityWiew$1(HomeChangeCityOrToastManager.this, iCityChange, str, view);
                        }
                    });
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showChangeCityWiew$0(HomeChangeCityOrToastManager homeChangeCityOrToastManager, ICityChange iCityChange, String str, View view) {
        if (iCityChange != null) {
            iCityChange.change(str);
        }
        LinearLayout linearLayout = homeChangeCityOrToastManager.mLinCityView;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$showChangeCityWiew$1(HomeChangeCityOrToastManager homeChangeCityOrToastManager, ICityChange iCityChange, String str, View view) {
        if (iCityChange != null) {
            iCityChange.onClose(str);
        }
        LinearLayout linearLayout = homeChangeCityOrToastManager.mLinCityView;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }
}
