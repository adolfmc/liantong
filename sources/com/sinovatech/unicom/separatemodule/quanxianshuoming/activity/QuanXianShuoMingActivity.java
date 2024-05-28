package com.sinovatech.unicom.separatemodule.quanxianshuoming.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.p083v4.app.Fragment;
import android.support.p083v4.app.FragmentManager;
import android.support.p083v4.app.FragmentPagerAdapter;
import android.support.p083v4.content.ContextCompat;
import android.support.p083v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.LeftFragment;
import com.sinovatech.unicom.separatemodule.quanxianshuoming.fragment.RightFragment;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class QuanXianShuoMingActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "QuanXianShuoMingActivity";
    public NBSTraceUnit _nbs_trace;
    private ImageButton backButton;
    LeftFragment leftFragment;
    RightFragment rightFragment;
    private TabLayout tabLayout;
    private TextView titleView;
    private ViewPager viewPager;
    private String[] titles = {"APP授权权限信息", "业务授权信息"};
    private int pos = 0;
    private String title = "";

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 107);
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

    /* renamed from: com.sinovatech.unicom.separatemodule.quanxianshuoming.activity.QuanXianShuoMingActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C93511 implements TabLayout.OnTabSelectedListener {
        @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
        public void onTabReselected(TabLayout.Tab tab) {
        }

        @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
        public void onTabUnselected(TabLayout.Tab tab) {
        }

        C93511() {
        }

        @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
        public void onTabSelected(TabLayout.Tab tab) {
            QuanXianShuoMingActivity.this.viewPager.setCurrentItem(tab.getPosition(), true);
            QuanXianShuoMingActivity.this.pos = tab.getPosition();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        if (view.getId() == 2131296473) {
            back();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public CharSequence getPageTitle(int i) {
            return QuanXianShuoMingActivity.this.titles[i];
        }

        @Override // android.support.p083v4.app.FragmentPagerAdapter
        public Fragment getItem(int i) {
            if (i == 0) {
                QuanXianShuoMingActivity.this.leftFragment = new LeftFragment();
                return QuanXianShuoMingActivity.this.leftFragment;
            } else if (i == 1) {
                QuanXianShuoMingActivity.this.rightFragment = new RightFragment();
                return QuanXianShuoMingActivity.this.rightFragment;
            } else {
                return null;
            }
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return QuanXianShuoMingActivity.this.titles.length;
        }
    }

    @Override // android.support.p086v7.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        if (i == 4) {
            back();
            return true;
        }
        return super.onKeyDown(i, keyEvent);
    }

    private void back() {
        try {
            if (this.pos == 0) {
                if (this.leftFragment.getPbWebView().canGoBack()) {
                    this.leftFragment.getPbWebView().goBack();
                } else {
                    finish();
                }
            } else if (this.pos == 1) {
                finish();
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "处理返回按钮逻辑异常:" + e.getMessage());
            finish();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        super.onSaveInstanceState(bundle);
        try {
            bundle.putString("title", this.title);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "权限说明页面保存页面状态异常:" + e.getMessage());
        }
    }

    private void initTab() {
        for (int i = 0; i < this.titles.length; i++) {
            TabLayout.Tab newTab = this.tabLayout.newTab();
            newTab.setText(this.titles[i]);
            newTab.setCustomView(getTabView(i, this.titles));
            this.tabLayout.addTab(newTab);
        }
        TabLayout tabLayout = this.tabLayout;
        updateTabTextView(tabLayout.getTabAt(tabLayout.getSelectedTabPosition()), true, 2131099854, 2131099853);
        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.sinovatech.unicom.separatemodule.quanxianshuoming.activity.QuanXianShuoMingActivity.2
            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                QuanXianShuoMingActivity.this.updateTabTextView(tab, true, 2131099854, 2131099853);
            }

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
                QuanXianShuoMingActivity.this.updateTabTextView(tab, false, 2131099854, 2131099853);
            }
        });
    }

    private View getTabView(int i, String[] strArr) {
        View inflate = LayoutInflater.from(this).inflate(2131493268, (ViewGroup) null);
        ((TextView) inflate.findViewById(2131298730)).setText(strArr[i]);
        return inflate;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTabTextView(TabLayout.Tab tab, boolean z, int i, int i2) {
        if (z) {
            TextView textView = (TextView) tab.getCustomView().findViewById(2131298730);
            textView.setTypeface(Typeface.defaultFromStyle(0));
            textView.setTextColor(ContextCompat.getColor(this, i));
            textView.setText(tab.getText());
            return;
        }
        TextView textView2 = (TextView) tab.getCustomView().findViewById(2131298730);
        textView2.setTypeface(Typeface.defaultFromStyle(0));
        textView2.setTextColor(ContextCompat.getColor(this, i2));
        textView2.setText(tab.getText());
    }
}
