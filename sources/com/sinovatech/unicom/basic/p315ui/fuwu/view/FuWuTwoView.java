package com.sinovatech.unicom.basic.p315ui.fuwu.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.fuwu.entity.MarketingBitsListEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuTwoView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FuWuTwoView extends RelativeLayout {
    public static final String TAG = "FuWuTwoView";
    private AppCompatActivity activityContext;
    private ImageView iv_right1;
    private ImageView iv_right2;
    private ImageView iv_right3;
    private LinearLayout lin_all1;
    private LinearLayout lin_all2;
    private LinearLayout lin_all3;
    private View mView;

    public FuWuTwoView(Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        this.mView = LayoutInflater.from(this.activityContext).inflate(2131493136, this);
        this.iv_right1 = (ImageView) this.mView.findViewById(2131297470);
        this.lin_all1 = (LinearLayout) this.mView.findViewById(2131297599);
        this.iv_right2 = (ImageView) this.mView.findViewById(2131297471);
        this.lin_all2 = (LinearLayout) this.mView.findViewById(2131297600);
        this.iv_right3 = (ImageView) this.mView.findViewById(2131297472);
        this.lin_all3 = (LinearLayout) this.mView.findViewById(2131297601);
    }

    public static int dpToPx(Context context, float f) {
        return Math.round(f * context.getResources().getDisplayMetrics().density);
    }

    public void initUI(final MarketingBitsListEntity marketingBitsListEntity) {
        if (marketingBitsListEntity != null) {
            try {
                if (marketingBitsListEntity.isHiddenBottom() || marketingBitsListEntity.getBottomList().size() < 3 || marketingBitsListEntity.getBottomList().get(0) == null || marketingBitsListEntity.getBottomList().get(1) == null || marketingBitsListEntity.getBottomList().get(2) == null) {
                    return;
                }
                String returnNoNull = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(0).getImgSrc());
                final String returnNoNull2 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(0).getGoodsUrl());
                String returnNoNull3 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(1).getImgSrc());
                final String returnNoNull4 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(1).getGoodsUrl());
                String returnNoNull5 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(2).getImgSrc());
                final String returnNoNull6 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(2).getGoodsUrl());
                final String returnNoNull7 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(0).getTitle());
                final String returnNoNull8 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(1).getTitle());
                final String returnNoNull9 = FuWuUtils.returnNoNull(marketingBitsListEntity.getBottomList().get(2).getTitle());
                int dpToPx = dpToPx(this.activityContext, 7.0f);
                GlideApp.with((FragmentActivity) this.activityContext).load(returnNoNull).transform((Transformation<Bitmap>) new RoundedCorners(dpToPx)).placeholder(2131231795).error(2131231795).into(this.iv_right1);
                this.lin_all1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuTwoView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FuWuUtils.ganZhiLog("2030104", returnNoNull7, returnNoNull2, marketingBitsListEntity.getBottomList().get(0));
                        PvCurrencyLogUtils.pvLogFuWu("2030104", "服务页广告位4", returnNoNull2, "", "");
                        IntentManager.gotoWebViewActivity(FuWuTwoView.this.activityContext, returnNoNull2, "");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                GlideApp.with((FragmentActivity) this.activityContext).load(returnNoNull3).transform((Transformation<Bitmap>) new RoundedCorners(dpToPx)).placeholder(2131231795).error(2131231795).into(this.iv_right2);
                this.lin_all2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuTwoView.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FuWuUtils.ganZhiLog("2030105", returnNoNull8, returnNoNull4, marketingBitsListEntity.getBottomList().get(1));
                        PvCurrencyLogUtils.pvLogFuWu("2030105", "服务页广告位5", returnNoNull4, "", "");
                        IntentManager.gotoWebViewActivity(FuWuTwoView.this.activityContext, returnNoNull4, "");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                GlideApp.with((FragmentActivity) this.activityContext).load(returnNoNull5).transform((Transformation<Bitmap>) new RoundedCorners(dpToPx)).placeholder(2131231795).error(2131231795).into(this.iv_right3);
                this.lin_all3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuTwoView.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FuWuUtils.ganZhiLog("2030106", returnNoNull9, returnNoNull6, marketingBitsListEntity.getBottomList().get(2));
                        PvCurrencyLogUtils.pvLogFuWu("2030106", "服务页广告位6", returnNoNull6, "", "");
                        IntentManager.gotoWebViewActivity(FuWuTwoView.this.activityContext, returnNoNull6, "");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                int screenWidth = UIUtils.getScreenWidth((Activity) this.activityContext) / 3;
                LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) this.lin_all1.getLayoutParams();
                layoutParams.width = screenWidth;
                this.lin_all1.setLayoutParams(layoutParams);
                LinearLayout.LayoutParams layoutParams2 = (LinearLayout.LayoutParams) this.lin_all2.getLayoutParams();
                layoutParams2.width = screenWidth;
                this.lin_all2.setLayoutParams(layoutParams2);
                LinearLayout.LayoutParams layoutParams3 = (LinearLayout.LayoutParams) this.lin_all3.getLayoutParams();
                layoutParams3.width = screenWidth;
                this.lin_all3.setLayoutParams(layoutParams3);
            } catch (Exception e) {
                String str = TAG;
                MsLogUtil.m7977e(str, "服务页面加载数据出现异常：" + e.getMessage());
            }
        }
    }
}
