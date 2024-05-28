package com.sinovatech.unicom.basic.p315ui.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.activity.GuideActivity */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GuideActivity extends AppCompatActivity {
    public NBSTraceUnit _nbs_trace;
    private Activity activityContext;
    private List<View> guideList = new ArrayList();
    private ImageView lijitiyan;
    private boolean lijitiyanHasClick;
    private TextView mTvTiaoGuo;
    private GuidePagerAdapter pagerAdapter;
    private ViewPager viewPager;

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

    @Override // android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
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
        setContentView(2131493557);
        UIUtils.setStatusBarMode(this, true, true);
        UIUtils.setNavBarVisibility((Activity) this, false);
        this.activityContext = this;
        this.lijitiyanHasClick = false;
        ImageView imageView = new ImageView(this.activityContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView imageView2 = new ImageView(this.activityContext);
        imageView2.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView imageView3 = new ImageView(this.activityContext);
        imageView3.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView3.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView imageView4 = new ImageView(this.activityContext);
        imageView4.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView4.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView imageView5 = new ImageView(this.activityContext);
        imageView5.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView5.setScaleType(ImageView.ScaleType.CENTER_CROP);
        ImageView imageView6 = new ImageView(this.activityContext);
        imageView6.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        imageView6.setScaleType(ImageView.ScaleType.CENTER_CROP);
        this.lijitiyan = (ImageView) findViewById(2131299605);
        this.lijitiyan.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.GuideActivity.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                GuideActivity.this.goMain();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (this.guideList.size() == 0) {
            goMain();
        }
        this.viewPager = (ViewPager) findViewById(2131299604);
        this.pagerAdapter = new GuidePagerAdapter();
        this.viewPager.setAdapter(this.pagerAdapter);
        this.mTvTiaoGuo = (TextView) findViewById(2131299606);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.mTvTiaoGuo.getLayoutParams();
        layoutParams.topMargin = UIUtils.getStatusBarHeight(this.activityContext) + UIUtils.dip2px(this.activityContext, 23.0f);
        this.mTvTiaoGuo.setLayoutParams(layoutParams);
        this.mTvTiaoGuo.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.activity.GuideActivity.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                GuideActivity.this.goMain();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.basic.ui.activity.GuideActivity.3
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                if (i == GuideActivity.this.guideList.size() - 1) {
                    GuideActivity.this.findViewById(2131299606).setVisibility(4);
                    GuideActivity.this.lijitiyan.setVisibility(0);
                } else {
                    GuideActivity.this.findViewById(2131299606).setVisibility(0);
                    GuideActivity.this.lijitiyan.setVisibility(8);
                }
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
        if (1 == this.guideList.size()) {
            findViewById(2131299606).setVisibility(4);
            this.lijitiyan.setVisibility(0);
        } else {
            findViewById(2131299606).setVisibility(0);
            this.lijitiyan.setVisibility(4);
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goMain() {
        if (this.lijitiyanHasClick) {
            return;
        }
        this.lijitiyanHasClick = true;
        App.getSharePreferenceUtil().putBoolean("isShowWelcomeGuide", true);
        App.getSharePreferenceUtil().putBoolean("guideIsNeddYinsi", true);
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.activity.GuideActivity$GuidePagerAdapter */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class GuidePagerAdapter extends PagerAdapter {
        @Override // android.support.p083v4.view.PagerAdapter
        public int getItemPosition(Object obj) {
            return -2;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        GuidePagerAdapter() {
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return GuideActivity.this.guideList.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            viewGroup.addView((View) GuideActivity.this.guideList.get(i));
            return GuideActivity.this.guideList.get(i);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            if (obj != null) {
                viewGroup.removeView((View) obj);
            }
        }
    }
}
