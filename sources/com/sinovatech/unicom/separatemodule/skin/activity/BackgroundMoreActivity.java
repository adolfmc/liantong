package com.sinovatech.unicom.separatemodule.skin.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageButton;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.fort.andjni.JniLib;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.home.util.RecycleGridDivider;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.skin.adapter.BackGroundItemAdapter;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundSkinBean;
import com.sinovatech.unicom.separatemodule.skin.entity.BackgroundTongYongBean;
import com.sinovatech.unicom.separatemodule.skin.event.RefreshSkinEvent;
import com.sinovatech.unicom.separatemodule.skin.manager.HomeBackgroundInfoManager;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BackgroundMoreActivity extends BaseActivity {
    private static final String TAG = "BackgroundMoreActivity";
    public NBSTraceUnit _nbs_trace;
    private HomeBackgroundInfoManager infoManager;
    private BackGroundItemAdapter mAdapter;
    private ImageButton mImgBack;
    private List<BackgroundSkinBean> mList;
    private RecyclerView mRvMore;
    private TextView mTvTitle;
    private int pageNum = 1;
    private String showType;
    private BackgroundSkinBean skinBean;
    private SmartRefreshLayout smartRefreshLayout;
    private String titleName;
    private BackgroundTongYongBean tongYongBean;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 114);
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

    private void initView() {
        try {
            this.mImgBack = (ImageButton) findViewById(2131296473);
            this.mTvTitle = (TextView) findViewById(2131298800);
            this.smartRefreshLayout = (SmartRefreshLayout) findViewById(2131296484);
            this.mRvMore = (RecyclerView) findViewById(2131296483);
            this.smartRefreshLayout.setEnableLoadMoreWhenContentNotFull(true);
            this.smartRefreshLayout.setDisableContentWhenLoading(true);
            this.smartRefreshLayout.setEnableRefresh(true);
            this.smartRefreshLayout.setEnableLoadMore(true);
            this.smartRefreshLayout.setReboundInterpolator(new AccelerateInterpolator());
            this.smartRefreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundMoreActivity.1
                @Override // com.scwang.smartrefresh.layout.listener.OnLoadMoreListener
                public void onLoadMore(RefreshLayout refreshLayout) {
                    BackgroundMoreActivity.this.requestData();
                }
            });
            this.smartRefreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundMoreActivity.2
                @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
                public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                    BackgroundMoreActivity.this.pageNum = 1;
                    BackgroundMoreActivity.this.requestData();
                }
            });
            this.smartRefreshLayout.autoRefresh();
            this.mImgBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundMoreActivity.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    BackgroundMoreActivity.this.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化View异常:" + e.getMessage());
        }
    }

    public static void startNewIntent(Activity activity, List<BackgroundSkinBean> list, BackgroundSkinBean backgroundSkinBean, BackgroundTongYongBean backgroundTongYongBean, String str, String str2) {
        try {
            Intent intent = new Intent(activity, BackgroundMoreActivity.class);
            intent.putExtra("skinBean", backgroundSkinBean);
            intent.putExtra("tongYongBean", backgroundTongYongBean);
            intent.putExtra("titleName", str);
            intent.putExtra("showType", str2);
            intent.putParcelableArrayListExtra("beanList", (ArrayList) list);
            activity.startActivity(intent);
        } catch (Exception e) {
            String str3 = TAG;
            MsLogUtil.m7977e(str3, "跳转页面异常:" + e.getMessage());
        }
    }

    private void initData() {
        try {
            Intent intent = getIntent();
            if (intent != null) {
                this.skinBean = (BackgroundSkinBean) intent.getParcelableExtra("skinBean");
                this.tongYongBean = (BackgroundTongYongBean) intent.getParcelableExtra("tongYongBean");
                this.titleName = intent.getStringExtra("titleName");
                this.showType = intent.getStringExtra("showType");
                this.mList = intent.getParcelableArrayListExtra("beanList");
            }
            this.mTvTitle.setText(this.titleName);
            this.infoManager = new HomeBackgroundInfoManager(this);
            if (this.mList == null) {
                this.mList = new ArrayList();
            }
            this.mAdapter = new BackGroundItemAdapter(this, this.mList, true, this.skinBean, this.tongYongBean, this.showType);
            this.mRvMore.addItemDecoration(new RecycleGridDivider(UIUtils.dip2px(5.0f), false));
            this.mRvMore.setLayoutManager(new GridLayoutManager(this, 2));
            this.mRvMore.setAdapter(this.mAdapter);
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "初始化数据异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestData() {
        try {
            this.infoManager.getBackGroundMoreData(this.showType, this.titleName, this.pageNum, null).subscribe(new Consumer<List<BackgroundSkinBean>>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundMoreActivity.4
                @Override // io.reactivex.functions.Consumer
                public void accept(List<BackgroundSkinBean> list) throws Exception {
                    BackgroundMoreActivity.this.setData(list);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.skin.activity.BackgroundMoreActivity.5
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    BackgroundMoreActivity.this.smartRefreshLayout.finishRefresh();
                    BackgroundMoreActivity.this.smartRefreshLayout.finishLoadMore();
                }
            });
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "加载接口数据异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setData(List<BackgroundSkinBean> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    if (this.pageNum == 1) {
                        this.mList.clear();
                    }
                    this.pageNum++;
                    this.mList.addAll(list);
                }
            } catch (Exception e) {
                MsLogUtil.m7977e(TAG, "填充数据异常:" + e.getMessage());
                return;
            }
        }
        this.smartRefreshLayout.finishRefresh();
        this.smartRefreshLayout.finishLoadMore();
        this.mAdapter.notifyDataSetChanged();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshData(RefreshSkinEvent refreshSkinEvent) {
        try {
            if (this.mList != null) {
                this.skinBean = CacheDataCenter.getInstance().getCustomSkinBean();
                if (this.skinBean != null) {
                    this.mAdapter.setCurrentBean(this.skinBean);
                    for (int i = 0; i < this.mList.size(); i++) {
                        BackgroundSkinBean backgroundSkinBean = this.mList.get(i);
                        if (backgroundSkinBean != null) {
                            if (TextUtils.equals(this.skinBean.getProductId(), backgroundSkinBean.getProductId())) {
                                backgroundSkinBean.setIsSelect("1");
                            } else {
                                backgroundSkinBean.setIsSelect("");
                            }
                            this.mList.set(i, backgroundSkinBean);
                        }
                    }
                }
                this.mAdapter.notifyDataSetChanged();
            }
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }
}
