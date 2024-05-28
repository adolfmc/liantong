package com.sinovatech.unicom.basic.p315ui.activity;

import android.os.Bundle;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p315ui.adapter.UserServiceGridAdapter;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeCardInfoManager;
import com.sinovatech.unicom.basic.p315ui.home.view.HomeYWJingZhunView;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.ExtranetUserActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ExtranetUserActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private ImageView back_imagebutton;
    private HomeCardInfoManager cardInfoManager;
    private HomeYWJingZhunView homeYWJingZhunView;
    private String infoUrl;
    private String nickName;
    private RequestOptions options;
    private TextView title_textview;
    private UserManager userManager;
    private ViewPager user_main_viewpager;
    private TextView user_title_tv;
    private CircularImage user_touxiang_imageview;
    private LinearLayout user_yewu_viewpager_indicate;
    private LinearLayout yw_jingzhun_layout;
    private LinearLayout zjsy_layout;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 49);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        try {
            bundle.putString("infoUrl", this.infoUrl);
            bundle.putString("nickName", this.nickName);
            super.onSaveInstanceState(bundle);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    private void getExtranetUserExtras(Bundle bundle) {
        try {
            if (bundle != null) {
                this.infoUrl = bundle.getString("infoUrl");
                this.nickName = bundle.getString("nickName");
            } else {
                Bundle extras = getIntent().getExtras();
                if (extras != null) {
                    this.infoUrl = extras.getString("infoUrl");
                    this.nickName = extras.getString("nickName");
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7978e("获取Bundle数据异常:" + e.getMessage());
        }
    }

    private void initData() {
        try {
            this.cardInfoManager = new HomeCardInfoManager(this.activityContext);
            this.homeYWJingZhunView = new HomeYWJingZhunView(this, this.cardInfoManager, 1);
            this.homeYWJingZhunView.getData();
            if (this.yw_jingzhun_layout.getChildCount() > 0) {
                this.yw_jingzhun_layout.removeAllViews();
            }
            this.yw_jingzhun_layout.addView(this.homeYWJingZhunView);
            List<MenuEntity> zuiJinShiYongMenu = MenuDataCenter.getInstance().getZuiJinShiYongMenu(8);
            if (zuiJinShiYongMenu.size() == 0) {
                this.zjsy_layout.setVisibility(8);
            } else {
                this.zjsy_layout.setVisibility(0);
            }
            new UserServiceGridAdapter(this.activityContext, new ArrayList(), 0, UserServiceGridAdapter.YWUSER).initGridView(zuiJinShiYongMenu, this.user_main_viewpager, this.user_yewu_viewpager_indicate, 4, UserServiceGridAdapter.YWUSER);
            this.userManager = UserManager.getInstance();
            Glide.with((FragmentActivity) this.activityContext).load(this.userManager.getUserPhotoURL(this.userManager.getCurrentPhoneNumber())).apply((BaseRequestOptions<?>) this.options).into(this.user_touxiang_imageview);
            this.user_title_tv.setText(this.nickName);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    private void initView() {
        try {
            this.user_touxiang_imageview = (CircularImage) findViewById(2131299451);
            this.back_imagebutton = (ImageView) findViewById(2131296473);
            this.title_textview = (TextView) findViewById(2131298800);
            this.user_title_tv = (TextView) findViewById(2131299447);
            this.yw_jingzhun_layout = (LinearLayout) findViewById(2131299863);
            this.user_main_viewpager = (ViewPager) findViewById(2131299420);
            this.user_yewu_viewpager_indicate = (LinearLayout) findViewById(2131299457);
            this.zjsy_layout = (LinearLayout) findViewById(2131299887);
            this.options = new RequestOptions().placeholder(2131232482).error(2131232482);
            this.title_textview.setText("个人信息");
            this.back_imagebutton.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ExtranetUserActivity.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    ExtranetUserActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.user_title_tv.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ExtranetUserActivity.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    IntentManager.gotoWebViewActivity(ExtranetUserActivity.this.activityContext, ExtranetUserActivity.this.infoUrl, "个人中心");
                    PvCurrencyLogUtils.sendYwUserPvLog("5010101", "异网个人信息页-昵称", "My page", "昵称", ExtranetUserActivity.this.infoUrl, "2", "1", "");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.user_touxiang_imageview.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.ExtranetUserActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    IntentManager.gotoWebViewActivity(ExtranetUserActivity.this.activityContext, ExtranetUserActivity.this.infoUrl, "个人中心");
                    PvCurrencyLogUtils.sendYwUserPvLog("5010101", "异网个人信息页-头像", "My page", "头像", ExtranetUserActivity.this.infoUrl, "2", "1", "");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
