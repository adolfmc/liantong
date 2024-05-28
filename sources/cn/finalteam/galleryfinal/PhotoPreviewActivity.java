package cn.finalteam.galleryfinal;

import android.os.Bundle;
import android.support.p083v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.finalteam.galleryfinal.adapter.PhotoPreviewAdapter;
import cn.finalteam.galleryfinal.model.PhotoInfo;
import cn.finalteam.galleryfinal.widget.GFViewPager;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class PhotoPreviewActivity extends PhotoBaseActivity implements ViewPager.OnPageChangeListener {
    static final String PHOTO_LIST = "photo_list";
    public NBSTraceUnit _nbs_trace;
    private View.OnClickListener mBackListener = new View.OnClickListener() { // from class: cn.finalteam.galleryfinal.PhotoPreviewActivity.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            PhotoPreviewActivity.this.finish();
            NBSActionInstrumentation.onClickEventExit();
        }
    };
    private ImageView mIvBack;
    private List<PhotoInfo> mPhotoList;
    private PhotoPreviewAdapter mPhotoPreviewAdapter;
    private ThemeConfig mThemeConfig;
    private RelativeLayout mTitleBar;
    private TextView mTvIndicator;
    private TextView mTvTitle;
    private GFViewPager mVpPager;

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i, KeyEvent keyEvent) {
        return super.onKeyDown(i, keyEvent);
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrollStateChanged(int i) {
    }

    @Override // android.app.Activity
    public void onRestart() {
        NBSAppInstrumentation.activityRestartBeginIns(getClass().getName());
        super.onRestart();
        NBSAppInstrumentation.activityRestartEndIns();
    }

    @Override // android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity
    protected void takeResult(PhotoInfo photoInfo) {
    }

    /* JADX INFO: Access modifiers changed from: protected */
    @Override // cn.finalteam.galleryfinal.PhotoBaseActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        this.mThemeConfig = GalleryFinal.getGalleryTheme();
        if (this.mThemeConfig == null) {
            resultFailureDelayed(getString(C1656R.string.please_reopen_gf), true);
        } else {
            setContentView(C1656R.C1660layout.gf_activity_photo_preview);
            GallryUIUtils.setStatusBarColor(this);
            findViews();
            setListener();
            setTheme();
            this.mPhotoList = (List) getIntent().getSerializableExtra(PHOTO_LIST);
            this.mPhotoPreviewAdapter = new PhotoPreviewAdapter(this, this.mPhotoList);
            this.mVpPager.setAdapter(this.mPhotoPreviewAdapter);
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    private void findViews() {
        this.mTitleBar = (RelativeLayout) findViewById(C1656R.C1659id.titlebar);
        this.mIvBack = (ImageView) findViewById(C1656R.C1659id.iv_back);
        this.mTvTitle = (TextView) findViewById(C1656R.C1659id.tv_title);
        this.mTvIndicator = (TextView) findViewById(C1656R.C1659id.tv_indicator);
        this.mVpPager = (GFViewPager) findViewById(C1656R.C1659id.vp_pager);
    }

    private void setListener() {
        this.mVpPager.addOnPageChangeListener(this);
        this.mIvBack.setOnClickListener(this.mBackListener);
    }

    private void setTheme() {
        this.mIvBack.setImageResource(this.mThemeConfig.getIconBack());
        if (this.mThemeConfig.getIconBack() == C1656R.C1658drawable.ic_gf_back) {
            this.mIvBack.setColorFilter(this.mThemeConfig.getTitleBarIconColor());
        }
        this.mTitleBar.setBackgroundColor(this.mThemeConfig.getTitleBarBgColor());
        this.mTvTitle.setTextColor(this.mThemeConfig.getTitleBarTextColor());
        if (this.mThemeConfig.getPreviewBg() != null) {
            this.mVpPager.setBackgroundDrawable(this.mThemeConfig.getPreviewBg());
        }
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageScrolled(int i, float f, int i2) {
        TextView textView = this.mTvIndicator;
        textView.setText((i + 1) + "/" + this.mPhotoList.size());
    }

    @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
    public void onPageSelected(int i) {
        NBSActionInstrumentation.onPageSelectedEnter(i, this);
        NBSActionInstrumentation.onPageSelectedExit();
    }
}
