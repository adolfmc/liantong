package com.sinovatech.unicom.basic.p315ui.home.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.home.entity.HomeLogEntity;
import com.sinovatech.unicom.basic.p315ui.home.entity.ServiceViewEntity;
import com.sinovatech.unicom.basic.p315ui.home.manager.ServiceZuJianManager;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import io.reactivex.functions.Consumer;
import java.util.List;

/* renamed from: com.sinovatech.unicom.basic.ui.home.view.ServiceView */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ServiceView extends LinearLayout {
    public static int HOME = 0;
    public static int USER = 1;
    private AppCompatActivity activity;
    private TextView home_first_text;
    private MarqueeTextView home_long_text;
    private TextView home_service_btn;
    private View home_service_yuandian;
    private TextView user_first_text;
    private MarqueeTextView user_long_text;
    private Button user_service_btn;
    private View user_service_yuandian;

    public ServiceView(Context context, int i) {
        super(context);
        init(context, i);
    }

    public ServiceView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        init(context, i);
    }

    private void init(Context context, int i) {
        try {
            this.activity = (AppCompatActivity) context;
            View inflate = View.inflate(context, 2131493430, this);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131297157);
            LinearLayout linearLayout2 = (LinearLayout) inflate.findViewById(2131299400);
            this.home_first_text = (TextView) inflate.findViewById(2131297135);
            this.home_long_text = (MarqueeTextView) inflate.findViewById(2131297158);
            this.home_long_text.setFocus(true);
            this.home_service_btn = (TextView) inflate.findViewById(2131297194);
            this.home_service_yuandian = inflate.findViewById(2131297195);
            this.user_first_text = (TextView) inflate.findViewById(2131299364);
            this.user_long_text = (MarqueeTextView) inflate.findViewById(2131299419);
            this.user_long_text.setFocus(true);
            this.user_service_btn = (Button) inflate.findViewById(2131299422);
            this.user_service_yuandian = inflate.findViewById(2131299423);
            setVisibility(8);
            if (HOME == i) {
                linearLayout.setVisibility(0);
                linearLayout2.setVisibility(8);
            } else {
                linearLayout.setVisibility(8);
                linearLayout2.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
            setVisibility(8);
        }
    }

    public void getData(AppCompatActivity appCompatActivity) {
        try {
            new ServiceZuJianManager(appCompatActivity).getService().subscribe(new Consumer<ServiceViewEntity>() { // from class: com.sinovatech.unicom.basic.ui.home.view.ServiceView.1
                @Override // io.reactivex.functions.Consumer
                public void accept(ServiceViewEntity serviceViewEntity) throws Exception {
                    if (serviceViewEntity != null) {
                        ServiceView.this.setViewData(serviceViewEntity.getDatas());
                        return;
                    }
                    ServiceView.this.setVisibility(8);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.home.view.ServiceView.2
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    ServiceView.this.setVisibility(8);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setViewData(List<ServiceViewEntity.Datas> list) {
        try {
            if (list == null) {
                UnicomHomeLogUtils.getInstance().removeLog(UnicomHomeLogUtils.LOG_TYPE_SERVICE);
                setVisibility(8);
                return;
            }
            final ServiceViewEntity.Datas datas = list.get(0);
            if (datas == null) {
                UnicomHomeLogUtils.getInstance().removeLog(UnicomHomeLogUtils.LOG_TYPE_SERVICE);
                setVisibility(8);
                return;
            }
            this.home_first_text.setText(datas.getRecommended());
            this.user_first_text.setText(datas.getRecommended());
            this.user_service_btn.setText(datas.getShowTab());
            this.home_service_btn.setText(datas.getShowTab());
            String title = datas.getTitle();
            String recommendContent = datas.getRecommendContent();
            String str = title + " " + recommendContent;
            if (!TextUtils.isEmpty(recommendContent)) {
                int indexOf = str.indexOf(recommendContent);
                UIUtils.setTextViewColor(this.user_long_text, "#999999", str, indexOf, str.length());
                UIUtils.setTextViewColor(this.home_long_text, "#999999", str, indexOf, str.length());
            } else {
                this.user_long_text.setText(str);
                this.home_long_text.setText(str);
            }
            this.user_service_btn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.ServiceView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    IntentManager.gotoWebViewActivity(ServiceView.this.activity, datas.getGoodsUrl(), "");
                    String actType = TextUtils.isEmpty(datas.getActType()) ? "" : datas.getActType();
                    String actId = TextUtils.isEmpty(datas.getActId()) ? "" : datas.getActId();
                    String goodsId = TextUtils.isEmpty(datas.getGoodsId()) ? "" : datas.getGoodsId();
                    String id = TextUtils.isEmpty(datas.getId()) ? "" : datas.getId();
                    String position = TextUtils.isEmpty(datas.getPosition()) ? "" : datas.getPosition();
                    PvCurrencyLogUtils.sendServiceViewPvLog("5050401", "我的服务提醒", "myPage", ServiceView.this.home_service_btn.getText().toString(), datas.getGoodsUrl(), "2", "1", actType + "," + actId + "," + goodsId + "," + id + "," + position);
                    ServiceView.this.clickCollect("5050401", datas.getShowTab(), datas.getGoodsUrl(), actType, actId, goodsId, id);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.home_service_btn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.home.view.ServiceView.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    IntentManager.gotoWebViewActivity(ServiceView.this.activity, datas.getGoodsUrl(), "");
                    String actType = TextUtils.isEmpty(datas.getActType()) ? "" : datas.getActType();
                    String actId = TextUtils.isEmpty(datas.getActId()) ? "" : datas.getActId();
                    String goodsId = TextUtils.isEmpty(datas.getGoodsId()) ? "" : datas.getGoodsId();
                    String id = TextUtils.isEmpty(datas.getId()) ? "" : datas.getId();
                    String position = TextUtils.isEmpty(datas.getPosition()) ? "" : datas.getPosition();
                    PvCurrencyLogUtils.sendServiceViewPvLog("1130101", "首页服务提醒", "homePage", ServiceView.this.home_service_btn.getText().toString(), datas.getGoodsUrl(), "2", "1", actType + "," + actId + "," + goodsId + "," + id + "," + position);
                    ServiceView.this.clickCollect("1130101", datas.getShowTab(), datas.getGoodsUrl(), actType, actId, goodsId, id);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            changeBg();
            setVisibility(0);
            UnicomHomeLogUtils.getInstance().putLogData(UnicomHomeLogUtils.LOG_TYPE_SERVICE, new HomeLogEntity("1130101", "首页服务提醒"));
        } catch (Exception e) {
            e.printStackTrace();
            setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCollect(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("5050401".equals(str) ? "我的" : "首页").setTargetUrl(str3).setCodeId(str).setPbName(str2).setActId(str5).setActType(str4).setGoodSid(str6).setCommodityId(str7).build());
    }

    public void changeBg() {
        try {
            if (UserManager.getInstance().isTopLevel()) {
                this.home_service_btn.setBackground(this.activity.getResources().getDrawable(2131231473));
            } else {
                this.home_service_btn.setBackground(this.activity.getResources().getDrawable(2131232161));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
