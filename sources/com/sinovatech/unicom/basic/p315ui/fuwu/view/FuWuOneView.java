package com.sinovatech.unicom.basic.p315ui.fuwu.view;

import android.app.Activity;
import android.content.Context;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.fuwu.entity.MarketingBitsListEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuOneView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FuWuOneView extends RelativeLayout {
    public static final String TAG = "FuWuOneView";
    private AppCompatActivity activityContext;
    private ImageView iv_right1;
    private ImageView iv_right2;
    private ImageView iv_right3;
    private LinearLayout lin_all1;
    private LinearLayout lin_all2;
    private LinearLayout lin_all3;
    private View mView;
    private TextView tv_showTab1;
    private TextView tv_showTab2;
    private TextView tv_showTab3;
    private TextView tv_title1;
    private TextView tv_title2;
    private TextView tv_title3;
    private TextView tv_viceTitle1;
    private TextView tv_viceTitle2;
    private TextView tv_viceTitle3;

    public FuWuOneView(Context context) {
        super(context);
        this.activityContext = (AppCompatActivity) context;
        this.mView = LayoutInflater.from(this.activityContext).inflate(2131493137, this);
        this.tv_title1 = (TextView) this.mView.findViewById(2131299109);
        this.tv_viceTitle1 = (TextView) this.mView.findViewById(2131299134);
        this.tv_showTab1 = (TextView) this.mView.findViewById(2131299076);
        this.iv_right1 = (ImageView) this.mView.findViewById(2131297470);
        this.lin_all1 = (LinearLayout) this.mView.findViewById(2131297599);
        this.tv_title2 = (TextView) this.mView.findViewById(2131299110);
        this.tv_viceTitle2 = (TextView) this.mView.findViewById(2131299135);
        this.tv_showTab2 = (TextView) this.mView.findViewById(2131299077);
        this.iv_right2 = (ImageView) this.mView.findViewById(2131297471);
        this.lin_all2 = (LinearLayout) this.mView.findViewById(2131297600);
        this.tv_title3 = (TextView) this.mView.findViewById(2131299111);
        this.tv_viceTitle3 = (TextView) this.mView.findViewById(2131299136);
        this.tv_showTab3 = (TextView) this.mView.findViewById(2131299078);
        this.iv_right3 = (ImageView) this.mView.findViewById(2131297472);
        this.lin_all3 = (LinearLayout) this.mView.findViewById(2131297601);
    }

    public void initUI(final MarketingBitsListEntity marketingBitsListEntity) {
        if (marketingBitsListEntity != null) {
            try {
                if (marketingBitsListEntity.isHiddenTop() || marketingBitsListEntity.getTopList().size() < 3 || marketingBitsListEntity.getTopList().get(0) == null || marketingBitsListEntity.getTopList().get(1) == null || marketingBitsListEntity.getTopList().get(2) == null) {
                    return;
                }
                final String returnNoNull = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(0).getTitle());
                String returnNoNull2 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(0).getViceTitle());
                String returnNoNull3 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(0).getShowTab());
                String returnNoNull4 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(0).getImgSrc());
                final String returnNoNull5 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(0).getGoodsUrl());
                final String returnNoNull6 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(1).getTitle());
                String returnNoNull7 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(1).getViceTitle());
                String returnNoNull8 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(1).getShowTab());
                String returnNoNull9 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(1).getImgSrc());
                final String returnNoNull10 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(1).getGoodsUrl());
                final String returnNoNull11 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(2).getTitle());
                String returnNoNull12 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(2).getViceTitle());
                String returnNoNull13 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(2).getShowTab());
                String returnNoNull14 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(2).getImgSrc());
                final String returnNoNull15 = FuWuUtils.returnNoNull(marketingBitsListEntity.getTopList().get(2).getGoodsUrl());
                this.tv_title1.setText(returnNoNull);
                this.tv_viceTitle1.setText(returnNoNull2);
                this.tv_showTab1.setText(returnNoNull3);
                if (TextUtils.isEmpty(returnNoNull3)) {
                    this.tv_showTab1.setVisibility(4);
                } else {
                    this.tv_showTab1.setVisibility(0);
                }
                GlideApp.with((FragmentActivity) this.activityContext).load(returnNoNull4).placeholder(2131231795).error(2131231795).into(this.iv_right1);
                this.lin_all1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuOneView.1
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FuWuUtils.ganZhiLog("2030101", returnNoNull, returnNoNull5, marketingBitsListEntity.getTopList().get(0));
                        PvCurrencyLogUtils.pvLogFuWu("2030101", "服务页广告位1", returnNoNull5, returnNoNull, "");
                        IntentManager.gotoWebViewActivity(FuWuOneView.this.activityContext, returnNoNull5, "");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                this.tv_title2.setText(returnNoNull6);
                this.tv_viceTitle2.setText(returnNoNull7);
                this.tv_showTab2.setText(returnNoNull8);
                if (TextUtils.isEmpty(returnNoNull8)) {
                    this.tv_showTab2.setVisibility(4);
                } else {
                    this.tv_showTab2.setVisibility(0);
                }
                GlideApp.with((FragmentActivity) this.activityContext).load(returnNoNull9).placeholder(2131231795).error(2131231795).into(this.iv_right2);
                this.lin_all2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuOneView.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FuWuUtils.ganZhiLog("2030102", returnNoNull6, returnNoNull10, marketingBitsListEntity.getTopList().get(1));
                        PvCurrencyLogUtils.pvLogFuWu("2030102", "服务页广告位2", returnNoNull10, returnNoNull6, "");
                        IntentManager.gotoWebViewActivity(FuWuOneView.this.activityContext, returnNoNull10, "");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                this.tv_title3.setText(returnNoNull11);
                this.tv_viceTitle3.setText(returnNoNull12);
                this.tv_showTab3.setText(returnNoNull13);
                if (TextUtils.isEmpty(returnNoNull13)) {
                    this.tv_showTab3.setVisibility(4);
                } else {
                    this.tv_showTab3.setVisibility(0);
                }
                GlideApp.with((FragmentActivity) this.activityContext).load(returnNoNull14).placeholder(2131231795).error(2131231795).into(this.iv_right3);
                this.lin_all3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fuwu.view.FuWuOneView.3
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FuWuUtils.ganZhiLog("2030103", returnNoNull11, returnNoNull15, marketingBitsListEntity.getTopList().get(2));
                        PvCurrencyLogUtils.pvLogFuWu("2030103", "服务页广告位3", returnNoNull15, returnNoNull11, "");
                        IntentManager.gotoWebViewActivity(FuWuOneView.this.activityContext, returnNoNull15, "");
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
