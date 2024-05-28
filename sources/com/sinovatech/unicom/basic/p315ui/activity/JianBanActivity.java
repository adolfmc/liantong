package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Process;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.FinishActivityEvent;
import com.sinovatech.unicom.basic.p315ui.home.util.DialogUtils;
import com.sinovatech.unicom.basic.p315ui.utils.UpDownTextView;
import com.sinovatech.unicom.basic.server.ManagerLocation;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.LoginStateConst;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.UserFragment;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.JianBanActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class JianBanActivity extends AppCompatActivity {
    private static final String TAG = "简版-JianBanActivity";
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activity;
    private ViewGroup mContainer;
    private Handler mHandler;
    private List<String> mNewsList;
    private View rl_view_click;
    private Runnable runnable;
    private TextView textView;
    private TextView tv_jinrubiaozhunbanben;
    private TextView tv_tiyanbiaozhunban;
    private long exitTime = 0;
    private int mCurrentIndex = 0;

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        this.activity = this;
        UIUtils.setNavBarVisibility((Activity) this, false);
        setContentView(2131492924);
        this.runnable = new Runnable() { // from class: com.sinovatech.unicom.basic.ui.activity.JianBanActivity.1
            @Override // java.lang.Runnable
            public void run() {
                JianBanActivity.this.startAnimation();
            }
        };
        this.tv_tiyanbiaozhunban = (TextView) findViewById(2131299114);
        this.tv_jinrubiaozhunbanben = (TextView) findViewById(2131298994);
        this.rl_view_click = findViewById(2131298394);
        this.mContainer = (ViewGroup) findViewById(2131296699);
        this.textView = new TextView(this);
        this.textView.setTextSize(1, 12.0f);
        this.textView.setTextColor(getResources().getColor(2131099854));
        this.textView.setMaxLines(1);
        this.textView.setEllipsize(TextUtils.TruncateAt.END);
        this.mContainer.addView(this.textView);
        this.mNewsList = new ArrayList();
        this.mNewsList.add("话费都花哪儿了？历史账单给您答案");
        this.mNewsList.add("便民服务再升级，远程办理零距离！");
        this.mNewsList.add("联通5G专区，带您畅享不一样的智慧生活！");
        this.mNewsList.add("话费流量总是用超？快来免费开通预警短信提醒！");
        this.mNewsList.add("足不出户也能办宽带？快来中国联通APP！");
        this.mCurrentIndex = (this.mCurrentIndex + 1) % this.mNewsList.size();
        this.textView.setText(this.mNewsList.get(this.mCurrentIndex));
        this.mHandler = new Handler();
        this.mHandler.postDelayed(this.runnable, 100L);
        this.tv_tiyanbiaozhunban.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.JianBanActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                DialogUtils.checkYinsiTiShiDialog(JianBanActivity.this.activity, "3");
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.tv_jinrubiaozhunbanben.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.JianBanActivity.3
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JianBanActivity.this.tv_tiyanbiaozhunban.performClick();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.rl_view_click.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.JianBanActivity.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                JianBanActivity.this.tv_tiyanbiaozhunban.performClick();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        fun();
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public void fun() {
        UpDownTextView upDownTextView = (UpDownTextView) findViewById(2131298763);
        upDownTextView.setTextColor(getResources().getColor(2131099698));
        upDownTextView.setTextSize(12);
        upDownTextView.setSingleLine();
        upDownTextView.setText(this.mNewsList.get(0));
        upDownTextView.setTextList(this.mNewsList);
        upDownTextView.startAutoScroll();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            exit();
            return false;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void exit() {
        if (System.currentTimeMillis() - this.exitTime > 2000) {
            UIUtils.toast("再按一次退出中国联通APP");
            this.exitTime = System.currentTimeMillis();
            return;
        }
        App.setLogined(LoginStateConst.UNLOGIN);
        try {
            App.realexit = true;
            App.homeCardBg = false;
            App.cardBgMap.clear();
            UserFragment.currentPhone = "";
            ManagerLocation.releaseManagerLocation();
            App.isCityToHome = false;
            App.isShowFingerdialog = false;
            App.getSharePreferenceUtil().putString("lat", "");
            App.getSharePreferenceUtil().putString("long", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            finish();
            EventBusUtils.post(new FinishActivityEvent(0));
            Process.killProcess(Process.myPid());
            System.exit(0);
        } catch (Exception e2) {
            e2.printStackTrace();
            MsLogUtil.m7977e(TAG, "简版程序退出异常：" + e2.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAnimation() {
        if (this.mNewsList.isEmpty()) {
            return;
        }
        this.textView.setText(this.mNewsList.get(this.mCurrentIndex));
        AnimationSet animationSet = new AnimationSet(true);
        TranslateAnimation translateAnimation = new TranslateAnimation(1, 0.0f, 1, 0.0f, 1, 1.0f, 1, 0.0f);
        translateAnimation.setDuration(300L);
        translateAnimation.setFillAfter(true);
        translateAnimation.setInterpolator(new AccelerateInterpolator());
        animationSet.addAnimation(translateAnimation);
        this.textView.startAnimation(animationSet);
        animationSet.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.basic.ui.activity.JianBanActivity.5
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }
        });
        this.mCurrentIndex = (this.mCurrentIndex + 1) % this.mNewsList.size();
        this.mHandler.postDelayed(this.runnable, 3000L);
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            if (this.mHandler != null) {
                this.mHandler.removeCallbacks(this.runnable);
            }
        } catch (Exception unused) {
        }
    }
}
