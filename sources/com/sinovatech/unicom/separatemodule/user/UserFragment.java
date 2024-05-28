package com.sinovatech.unicom.separatemodule.user;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.sinovatech.unicom.basic.boxcenter.AdvDataCenter;
import com.sinovatech.unicom.basic.eventbus.ChangeNoticNumberEvent;
import com.sinovatech.unicom.basic.eventbus.FragmentOnResumeEvent;
import com.sinovatech.unicom.basic.eventbus.NoticEvent;
import com.sinovatech.unicom.basic.eventbus.PopWebViewEvent;
import com.sinovatech.unicom.basic.eventbus.QiandaoEvent2;
import com.sinovatech.unicom.basic.eventbus.XiaoheitiaoEvent;
import com.sinovatech.unicom.basic.eventbus.YouxiangEvent;
import com.sinovatech.unicom.basic.p314po.HeaderEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment;
import com.sinovatech.unicom.basic.p315ui.home.fragment.UnicomHomeTuiJianFragment;
import com.sinovatech.unicom.basic.p315ui.manager.HomeCardDataManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.dialog.UserYunpanDialog;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import com.sinovatech.unicom.separatemodule.templateholder.RVAdapter;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.user.eventbus.UserHeaderEvent;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUser;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import io.reactivex.functions.Consumer;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserFragment extends BaseFragment {
    private static final int MIN_DELAY_TIME = 200;
    private static final String TAG = "UserFragment";
    public static String currentPhone = "";
    private static long lastClickTime = 0;
    public static String libaoNumnber = null;
    public static boolean scrollState = true;
    public static boolean wodelibaoClick;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private RVAdapter adapter;
    private List<RVItemEntity> dataSource;
    private UserDataSourceManager dataSourceManager;
    private View fragmentCacheView;
    private boolean isLoadRecycleData;
    private boolean isReCreateView;
    private GridLayoutManager linearLayoutManager;
    private HomeCardDataManager managerHomeInfo;
    private ManagerUser managerUser;
    private ImageView message2Image;
    private TextView message2Text;
    private LinearLayout nickNameLayout;
    private TextView nickNameText;
    private ImageView qiandao2Image;
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;
    private ImageView setting2Image;
    private LinearLayout titleLayout;
    private boolean isRefresh = false;
    private boolean reqidongFalg = false;
    private boolean isChangeState = false;
    private CountDownTimer userTimer = new CountDownTimer(3000, 1) { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.10
        @Override // android.os.CountDownTimer
        public void onTick(long j) {
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            ManagerUserLightCollect.getInstance().lightCollect();
        }
    };

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    public static UserFragment newInstance() {
        return new UserFragment();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (AppCompatActivity) context;
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onCreate");
        this.dataSourceManager = new UserDataSourceManager();
        this.dataSource = this.dataSourceManager.getDataSource();
        this.managerUser = new ManagerUser(this.activityContext);
        this.managerHomeInfo = HomeCardDataManager.getInstance();
        EventBusUtils.register(this);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment", viewGroup);
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onCreateView");
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.endViewTransition(this.fragmentCacheView);
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131493513, viewGroup, false);
        this.recyclerView = (RecyclerView) inflate.findViewById(2131299377);
        this.refreshLayout = (RefreshLayout) inflate.findViewById(2131299421);
        this.refreshLayout.setEnableOverScrollBounce(false);
        this.nickNameText = (TextView) inflate.findViewById(2131299443);
        this.nickNameLayout = (LinearLayout) inflate.findViewById(2131299444);
        this.message2Image = (ImageView) inflate.findViewById(2131299448);
        this.message2Text = (TextView) inflate.findViewById(2131299450);
        this.setting2Image = (ImageView) inflate.findViewById(2131299446);
        this.qiandao2Image = (ImageView) inflate.findViewById(2131299445);
        this.titleLayout = (LinearLayout) inflate.findViewById(2131299442);
        this.titleLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view3) {
                NBSActionInstrumentation.onClickEventEnter(view3, this);
                Tracker.onClick(view3);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.titleLayout.setPadding(0, UIUtils.getStatusBarHeight(this.activityContext), 0, 0);
        RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.titleLayout.getLayoutParams();
        layoutParams.height = UIUtils.getStatusBarHeight(this.activityContext) + UIUtils.dip2px(this.activityContext, 40.0f);
        this.titleLayout.setLayoutParams(layoutParams);
        this.fragmentCacheView = inflate;
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment");
        return view3;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment", this);
        super.onStart();
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onStart");
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(@Nullable Bundle bundle) {
        super.onActivityCreated(bundle);
        if (this.isReCreateView) {
            return;
        }
        this.isReCreateView = true;
        currentPhone = "";
        this.linearLayoutManager = new GridLayoutManager(this.activityContext, 2);
        this.linearLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.2
            @Override // android.support.p086v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return ((RVItemEntity) UserFragment.this.dataSource.get(i)).spanCount;
            }
        });
        this.recyclerView.setLayoutManager(this.linearLayoutManager);
        this.adapter = new RVAdapter(this.activityContext, this.dataSource);
        this.recyclerView.setAdapter(this.adapter);
        this.refreshLayout.setOnRefreshListener(new OnRefreshListener() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.3
            @Override // com.scwang.smartrefresh.layout.listener.OnRefreshListener
            public void onRefresh(@NonNull final RefreshLayout refreshLayout) {
                new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        refreshLayout.finishRefresh();
                        UserFragment.this.isRefresh = true;
                        UserFragment.this.loadData();
                        UserFragment.this.loadHeader();
                        UserFragment.this.loadLibao(false);
                        UserFragment.this.loadFootPrint();
                        UserFragment.this.loadOrderService();
                        UserFragment.this.loadUserDevice();
                    }
                }, 1500L);
            }
        });
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.4
            private int scrollDistance = 0;

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i != 0) {
                    if (UserFragment.this.userTimer != null) {
                        UserFragment.this.userTimer.cancel();
                    }
                    UserFragment.scrollState = false;
                    return;
                }
                UserFragment.scrollState = true;
                if (UserFragment.this.userTimer != null) {
                    UserFragment.this.userTimer.start();
                }
                UserFragment.this.adapter.notifyDataSetChanged();
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (recyclerView.computeVerticalScrollOffset() > 100) {
                    UserFragment.this.titleLayout.setVisibility(0);
                } else {
                    UserFragment.this.titleLayout.setVisibility(8);
                }
            }
        });
        this.message2Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.-$$Lambda$UserFragment$rE4Nh_ZCINAx7ac2TRoGH9VGJ8Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntentManager.handleLocal(UserFragment.this.activityContext, "", "LOCAL_MESSAGE");
            }
        });
        this.setting2Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.-$$Lambda$UserFragment$wweRVafGxHJ_Bhgmz5Kx9zr8Do0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntentManager.handleLocal(UserFragment.this.activityContext, "", "LOCAL_SETTING");
            }
        });
        this.qiandao2Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.-$$Lambda$UserFragment$-e14OQpMjhEuuf5jJ4QwmIQalkI
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserFragment.lambda$onActivityCreated$2(UserFragment.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$onActivityCreated$2(UserFragment userFragment, View view) {
        if (App.hasLogined()) {
            return;
        }
        AppCompatActivity appCompatActivity = userFragment.activityContext;
        appCompatActivity.startActivity(new Intent(appCompatActivity, LoginBindActivity.class));
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onPause");
        CountDownTimer countDownTimer = this.userTimer;
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment");
        super.onResume();
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onResume" + App.isTopProcess);
        UIUtils.logD("currentPhone", "currentPhone:" + currentPhone + "---" + UserManager.getInstance().getCurrentPhoneNumber());
        if (!currentPhone.equals(UserManager.getInstance().getCurrentPhoneNumber())) {
            this.isChangeState = true;
            currentPhone = UserManager.getInstance().getCurrentPhoneNumber();
            libaoNumnber = "";
            this.isLoadRecycleData = true;
            loadData();
        }
        loadUserDevice();
        MsLogUtil.m7979d("热启动标识", "是否热启动：" + this.reqidongFalg);
        if (!this.reqidongFalg || this.isChangeState) {
            loadLibao(this.isChangeState);
            loadFootPrint();
            loadOrderService();
            this.recyclerView.scrollTo(0, 0);
        }
        this.reqidongFalg = false;
        this.isChangeState = false;
        if (App.hasLogined()) {
            this.nickNameLayout.setVisibility(0);
        } else {
            this.nickNameLayout.setVisibility(8);
        }
        if (this.recyclerView.computeVerticalScrollOffset() > 100 && !UserManager.getInstance().isYiwang()) {
            this.titleLayout.setVisibility(0);
        } else {
            this.titleLayout.setVisibility(8);
        }
        PopWebViewEvent.currentType = 8;
        try {
            PvCurrencyLogUtils.pvLogUserLL("500", "", "", "", "我的-浏览");
            if (this.userTimer != null) {
                this.userTimer.start();
            }
            UnicomCollectManager.getInstance().scanCollect(CollectDataEntity.newBuilder().setPageName("我的").build());
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
        this.isLoadRecycleData = false;
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.user.UserFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
        if (!App.isTopProcess) {
            this.reqidongFalg = true;
        }
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onStop" + App.isTopProcess);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onDestroy");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        MsLogUtil.m7979d(UnicomHomeTuiJianFragment.TAG, "我的>>>>>onDestroyView");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        try {
            loadHeader();
            loadHuaFei();
            loadMyActivity();
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.5
                @Override // java.lang.Runnable
                public void run() {
                    UserFragment.this.recevierNoticNum(new NoticEvent(EventBusUtils.EVENT_NOTIC));
                }
            }, 500L);
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadOrderService() {
        try {
            if (this.adapter == null) {
                return;
            }
            this.dataSourceManager.getRVItemEntity(UserDataSourceManager.USERSERVICEORDER).refresh = true;
            this.adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadUserDevice() {
        try {
            if (this.adapter == null) {
                return;
            }
            this.dataSourceManager.getRVItemEntity(UserDataSourceManager.USERDEVICE).refresh = true;
            this.adapter.notifyDataSetChanged();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadHuaFei() {
        this.dataSourceManager.getRVItemEntity(UserDataSourceManager.HUAFEI).refresh = true;
        this.adapter.notifyDataSetChanged();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadHeader() {
        this.dataSourceManager.getRVItemEntity(UserDataSourceManager.HEADER).refresh = true;
        this.adapter.notifyDataSetChanged();
        MsLogUtil.m7979d("loadHeader", "加载头部");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadLibao(boolean z) {
        RVItemEntity rVItemEntity = this.dataSourceManager.getRVItemEntity(UserDataSourceManager.LIBAO);
        rVItemEntity.refresh = true;
        rVItemEntity.isLibaoChangeState = z;
        this.adapter.notifyDataSetChanged();
        MsLogUtil.m7979d("loadHeader", "加载礼包");
    }

    private void loadMyActivity() {
        try {
            if (this.adapter == null) {
                return;
            }
            this.dataSourceManager.getRVItemEntity(UserDataSourceManager.USERACTIVITY).refresh = true;
            this.adapter.notifyDataSetChanged();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFootPrint() {
        try {
            if (this.adapter == null) {
                return;
            }
            this.dataSourceManager.getRVItemEntity(UserDataSourceManager.USERFOOT).refresh = true;
            this.adapter.notifyDataSetChanged();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setTitleHeader(final UserHeaderEvent userHeaderEvent) {
        UIUtils.logD(UserFragment.class.getSimpleName(), "刷新设置");
        if (App.hasLogined()) {
            if (!TextUtils.isEmpty(userHeaderEvent.getNickName())) {
                TextView textView = this.nickNameText;
                if (textView != null) {
                    textView.setText(userHeaderEvent.getNickName());
                }
                LinearLayout linearLayout = this.nickNameLayout;
                if (linearLayout != null) {
                    linearLayout.setVisibility(0);
                }
            } else {
                LinearLayout linearLayout2 = this.nickNameLayout;
                if (linearLayout2 != null) {
                    linearLayout2.setVisibility(8);
                }
            }
            if (!TextUtils.isEmpty(userHeaderEvent.getQiandaoUrl())) {
                ImageView imageView = this.qiandao2Image;
                if (imageView != null) {
                    imageView.setVisibility(0);
                }
            } else {
                ImageView imageView2 = this.qiandao2Image;
                if (imageView2 != null) {
                    imageView2.setVisibility(8);
                }
            }
        } else {
            LinearLayout linearLayout3 = this.nickNameLayout;
            if (linearLayout3 != null) {
                linearLayout3.setVisibility(8);
            }
            ImageView imageView3 = this.qiandao2Image;
            if (imageView3 != null) {
                imageView3.setVisibility(8);
            }
        }
        LinearLayout linearLayout4 = this.nickNameLayout;
        if (linearLayout4 != null) {
            linearLayout4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.-$$Lambda$UserFragment$RyBcZFtx7HK9MFW6Hj_5SGX0d6o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    IntentManager.handleLocal(UserFragment.this.activityContext, "", "LOCAL_LOGIN_BIND");
                }
            });
        }
        ImageView imageView4 = this.qiandao2Image;
        if (imageView4 != null) {
            imageView4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.-$$Lambda$UserFragment$QDUceXPl3_EP1XPHcX20THiUsl0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UserFragment.lambda$setTitleHeader$4(UserFragment.this, userHeaderEvent, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$setTitleHeader$4(UserFragment userFragment, UserHeaderEvent userHeaderEvent, View view) {
        if (TextUtils.isEmpty(userHeaderEvent.getQiandaoUrl())) {
            return;
        }
        IntentManager.generateIntentAndGo(userFragment.activityContext, userHeaderEvent.getQiandaoUrl(), "签到", true, "get");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setQiandaoImage(QiandaoEvent2 qiandaoEvent2) {
        this.recyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    View childAt = UserFragment.this.linearLayoutManager.getChildAt(0);
                    if (childAt == null) {
                        return;
                    }
                    HeaderEntity headerEntity = UserFragment.this.managerHomeInfo.getHeaderEntity();
                    ImageView imageView = (ImageView) childAt.findViewById(2131299415);
                    if (!App.hasLogined()) {
                        GlideApp.with(App.getInstance()).load((Integer) 2131232548).into(UserFragment.this.qiandao2Image);
                        GlideApp.with(App.getInstance()).load((Integer) 2131232548).into(imageView);
                    } else if (!"1".equals(headerEntity.getSigninState()) || AdvDataCenter.getInstance().getQiandaoHasClicked()) {
                        GlideApp.with(App.getInstance()).load((Integer) 2131232549).into(UserFragment.this.qiandao2Image);
                        GlideApp.with(App.getInstance()).load((Integer) 2131232549).into(imageView);
                    } else {
                        GlideApp.with(App.getInstance()).load((Integer) 2131232548).into(UserFragment.this.qiandao2Image);
                        GlideApp.with(App.getInstance()).load((Integer) 2131232548).into(imageView);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 100L);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void recevierNoticNum(NoticEvent noticEvent) {
        if (isFastExcute()) {
            return;
        }
        AppCompatActivity appCompatActivity = this.activityContext;
        if (appCompatActivity instanceof MainActivity) {
            ((MainActivity) appCompatActivity).setTabUserView(new Consumer<Integer>() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Integer num) throws Exception {
                    try {
                        if (UserFragment.this.linearLayoutManager.getChildAt(0) == null) {
                            return;
                        }
                        UserFragment.this.setMsgNumber(new PushMsgDao(UserFragment.this.activityContext).getPushMessageRecordSize("0", "0"), num.intValue());
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    public static boolean isFastExcute() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 200;
        lastClickTime = currentTimeMillis;
        return z;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeNoticNum(ChangeNoticNumberEvent changeNoticNumberEvent) {
        try {
            if (EventBusUtils.EVENT_CHAGNE_NOTIC_NUMBER == changeNoticNumberEvent.getCode() && (this.activityContext instanceof MainActivity)) {
                setMsgNumber(changeNoticNumberEvent.getNumber(), changeNoticNumberEvent.getLiuyanNum());
            }
        } catch (Exception e) {
            String str = TAG;
            MsLogUtil.m7977e(str, "通知修改我的页面顶部消息数量异常:" + e.getMessage());
        }
    }

    public void setMsgNumber(int i, int i2) {
        if (i > 3) {
            i = 3;
        }
        final boolean z = false;
        if (i2 > 3) {
            z = true;
            i2 = 3;
        }
        final int i3 = i + i2;
        this.recyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.8
            @Override // java.lang.Runnable
            public void run() {
                TextView textView;
                try {
                    View childAt = UserFragment.this.linearLayoutManager.getChildAt(0);
                    if (childAt == null || UserFragment.this.message2Text == null || (textView = (TextView) childAt.findViewById(2131299449)) == null) {
                        return;
                    }
                    if (i3 > 0) {
                        textView.setVisibility(0);
                        UserFragment.this.message2Text.setVisibility(0);
                        String valueOf = String.valueOf(i3);
                        if (z) {
                            valueOf = valueOf + "+";
                        }
                        textView.setText(valueOf);
                        UserFragment.this.message2Text.setText(valueOf);
                        return;
                    }
                    textView.setVisibility(4);
                    UserFragment.this.message2Text.setVisibility(4);
                    textView.setText("0");
                    UserFragment.this.message2Text.setText("0");
                } catch (Exception e) {
                    MsLogUtil.m7977e(UserFragment.TAG, "修改我的页面顶部消息数据异常:" + e.getMessage());
                }
            }
        }, 100L);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadYouxiangDenglu(YouxiangEvent youxiangEvent) {
        loadData();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeMemberInfo(FragmentOnResumeEvent fragmentOnResumeEvent) {
        if (fragmentOnResumeEvent.getType() == FragmentOnResumeEvent.USER && isResumed()) {
            onResume();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void changeXiaoheitiaoHeight(XiaoheitiaoEvent xiaoheitiaoEvent) {
        try {
            if (xiaoheitiaoEvent.getCode() == 0) {
                RVItemEntity rVItemEntity = this.dataSourceManager.getRVItemEntity(UserDataSourceManager.HEADER);
                rVItemEntity.isGengxinXiaoheitiao = true;
                int parseInt = Integer.parseInt(xiaoheitiaoEvent.getData());
                App.getSharePreferenceUtil().putInt(PreferenceConstUtils.getXiaoheitiaoHeightKey(), parseInt);
                rVItemEntity.xiaoheiTiaoHight = parseInt;
                this.adapter.notifyDataSetChanged();
                EventBusUtils.remove(xiaoheitiaoEvent);
            } else if (xiaoheitiaoEvent.getCode() == 1) {
                CustomDialogManager.showUserDialog(this.activityContext, xiaoheitiaoEvent.getTitle(), xiaoheitiaoEvent.getMsg(), xiaoheitiaoEvent.getConfirm(), new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.user.UserFragment.9
                    @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                    public void onClickOk() {
                    }
                });
            } else if (xiaoheitiaoEvent.getCode() == 2) {
                new UserYunpanDialog.Builder(this.activityContext).setMsgText(xiaoheitiaoEvent.getMsg()).setCancelText(xiaoheitiaoEvent.getCancel()).setOkText(xiaoheitiaoEvent.getConfirm()).show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
