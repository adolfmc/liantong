package com.sinovatech.unicom.basic.p315ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.boxcenter.MenuDataCenter;
import com.sinovatech.unicom.basic.eventbus.PopWebViewEvent;
import com.sinovatech.unicom.basic.eventbus.ServiceMenuEvent;
import com.sinovatech.unicom.basic.p314po.HomeTabEntity;
import com.sinovatech.unicom.basic.p314po.MenuEntity;
import com.sinovatech.unicom.basic.p314po.ServiceListItem;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.adapter.CustomOneAdapter;
import com.sinovatech.unicom.basic.p315ui.adapter.MainAdapter;
import com.sinovatech.unicom.basic.p315ui.entity.MainBean;
import com.sinovatech.unicom.basic.p315ui.fuwu.adapter.DragRecyclerBaiViewAdapter;
import com.sinovatech.unicom.basic.p315ui.fuwu.adapter.DragRecyclerViewAdapter;
import com.sinovatech.unicom.basic.p315ui.fuwu.adapter.MyPagerAdapter;
import com.sinovatech.unicom.basic.p315ui.fuwu.contants.FuWuConstant;
import com.sinovatech.unicom.basic.p315ui.fuwu.entity.MarketingBitsListEntity;
import com.sinovatech.unicom.basic.p315ui.fuwu.fragment.ViewPagerFragment;
import com.sinovatech.unicom.basic.p315ui.fuwu.manager.MarketingBitsManager;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuTopVerticalSpacingItemDecoration;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.FuWuUtils;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.PagerIndicator;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.SimpleItemTouchHelperCallback;
import com.sinovatech.unicom.basic.p315ui.fuwu.utils.VerticalSpacingItemDecoration;
import com.sinovatech.unicom.basic.p315ui.fuwu.view.FuWuOneView;
import com.sinovatech.unicom.basic.p315ui.fuwu.view.FuWuSearchLayoutView;
import com.sinovatech.unicom.basic.p315ui.fuwu.view.FuWuTwoView;
import com.sinovatech.unicom.basic.p315ui.fuwu.view.MyRelativeLayout;
import com.sinovatech.unicom.basic.p315ui.home.manager.HomeTabManager;
import com.sinovatech.unicom.basic.p315ui.home.view.UnicomJinGangQuView;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.recentmenu.RecentBoxManager;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* renamed from: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ServiceFragment extends BaseFragment implements View.OnClickListener {
    private static final long IDLE_DELAY = 3000;
    private static final int MIN_DELAY_TIME = 1000;
    private static final int MSG_IDLE = 1;
    public static String currentPhone = "0";
    public static boolean isSearchAdapterSX = true;
    private static long lastClickTime;
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private MainAdapter adapter;
    private AppBarLayout appBarLayout;
    private TextView cancelBianJi;
    private CustomOneAdapter customOneAdapter;
    private RecyclerView dragRecyclerView;
    private RecyclerView dragRecyclerView2;
    private DragRecyclerViewAdapter dragRecyclerViewAdapter;
    private DragRecyclerBaiViewAdapter dragRecyclerViewAdapter2;
    private Handler handler;
    private ImageView img_fuwu_down_up;
    private ImageView iv_back;
    private ImageView iv_search_close;
    private RelativeLayout lin_fuwu1;
    private LinearLayout lin_jiantou;
    private LinearLayout lin_taocan_all;
    private LinearLayout lin_top_all;
    private LinearLayout lin_top_back;
    private LinearLayout lin_viewpager;
    private LinearLayout lin_youjiantou;
    private LinearLayoutManager manager;
    private MarketingBitsManager marketingBitsManager;
    private MenuDataCenter menuDataCenter;
    private MyPagerAdapter myPagerAdapter;
    private PagerIndicator pagerIndicator;
    private int position;
    private RecyclerView recyclerView;
    private RelativeLayout rl_child_main;
    private RelativeLayout rl_drag_view;
    private MyRelativeLayout rl_main;
    private RecyclerView rl_one;
    private LinearLayout searchLayout;
    private FuWuSearchLayoutView searchLayoutView;
    private LinearLayout searrchBgLayout;
    private TextView service_viewFlipper_text;
    private TabLayout tabLayout;
    private EditText tv_bendisearchtext;
    private TextView tv_btn_search;
    private TextView tv_fuwu_bianji;
    private TextView tv_fuwu_text;
    private TextView tv_huifumoren;
    private TextView tv_search;
    private TextView tv_syyy;
    private TextView tv_yy_desc;
    private ViewPager viewPager;
    private final String TAG = "服务页面";
    private List<MainBean> bottomList = new ArrayList();
    private List<ServiceListItem> tabList = new ArrayList();
    private List<MenuEntity> choseList = new ArrayList();
    private List<Integer> tPosition = new ArrayList();
    private boolean isScrolled = false;
    private FuWuTwoView viewTwo = null;
    private FuWuOneView viewOne = null;
    public int fuwuDownUp = 0;
    private String bigFont = "";
    private View fragmentCacheView = null;
    private List<MenuEntity> fuWuItemBeanList = new ArrayList();
    private MarketingBitsListEntity marketingBitsListEntity = null;
    private HomeTabEntity homeTabEntity = null;
    private boolean isExpend = true;
    private boolean isFirstLog = false;
    private int previousSelectedTabPosition = 0;
    private boolean isHuaDong = true;
    private boolean isJiaZai = true;
    private int searchPosition = 0;
    private int lastverticalOffset = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment$SaveCallback */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SaveCallback {
        void onSaveFailed(boolean z);

        void onSaveSuccess(boolean z);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: APUT  (r186 I:byte[] A[IMMUTABLE_TYPE]), (r0 I:??[int, short, byte, char]), (r84 I:byte A[IMMUTABLE_TYPE]), expected to be less than 13
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    public void logIdle() {
        /*
            Method dump skipped, instructions count: 1282
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment.logIdle():void");
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment");
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (AppCompatActivity) context;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityCreated(Bundle bundle) {
        super.onActivityCreated(bundle);
        this.searrchBgLayout.setVisibility(8);
        this.searchLayoutView.setVisibility(0);
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        EventBusUtils.register(this);
        this.bigFont = App.getSharePreferenceUtil().getString(ConfigManager.config_font_Size);
        currentPhone = UserManager.getInstance().getCurrentPhoneNumber();
        this.menuDataCenter = MenuDataCenter.getInstance();
        this.marketingBitsManager = new MarketingBitsManager(this.activityContext);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.endViewTransition(this.fragmentCacheView);
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131493424, viewGroup, false);
        initData();
        init(inflate);
        initTab();
        initGuangGao("2");
        this.fragmentCacheView = inflate;
        this.handler = new Handler(Looper.getMainLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$ServiceFragment$PbkpofiMSFPnjZiKy1GHjtVN5yE
            @Override // android.os.Handler.Callback
            public final boolean handleMessage(Message message) {
                return ServiceFragment.lambda$onCreateView$0(ServiceFragment.this, message);
            }
        });
        addTouch(this.rl_one);
        addTouch(this.dragRecyclerView);
        addTouch(this.rl_child_main);
        addTouch(this.searchLayout);
        addTouch(this.lin_top_all);
        addTouch(this.appBarLayout);
        addTouch(this.recyclerView);
        if (!this.isFirstLog) {
            this.isFirstLog = true;
            startIdleTimer();
        }
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment");
        return view3;
    }

    public static /* synthetic */ boolean lambda$onCreateView$0(ServiceFragment serviceFragment, Message message) {
        if (message.what == 1) {
            serviceFragment.logIdle();
        }
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getHeight() {
        try {
            if (this.recyclerView != null && this.isJiaZai) {
                this.isJiaZai = false;
                this.recyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.1
                    @Override // java.lang.Runnable
                    public void run() {
                        try {
                            int height = ServiceFragment.this.recyclerView.getHeight();
                            RecyclerView.ViewHolder findViewHolderForAdapterPosition = ServiceFragment.this.recyclerView.findViewHolderForAdapterPosition(ServiceFragment.this.bottomList.size() - 1);
                            if (ServiceFragment.this.manager == null || findViewHolderForAdapterPosition == null) {
                                ServiceFragment.this.isJiaZai = true;
                            } else {
                                int decoratedMeasuredHeight = ServiceFragment.this.manager.getDecoratedMeasuredHeight(findViewHolderForAdapterPosition.itemView);
                                if (decoratedMeasuredHeight < height) {
                                    FuWuConstant.lastH = height - decoratedMeasuredHeight;
                                }
                            }
                        } catch (Exception e) {
                            ServiceFragment.this.isJiaZai = true;
                            MsLogUtil.m7977e("服务页面", "获取高度时出现异常：" + e.getMessage());
                        }
                    }
                });
            }
        } catch (Exception unused) {
        } catch (Throwable th) {
            this.isJiaZai = true;
            throw th;
        }
        this.isJiaZai = true;
    }

    public void addTouch(View view) {
        view.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case 0:
                    case 2:
                        ServiceFragment.this.resetIdleTimer();
                        return false;
                    case 1:
                    case 3:
                        ServiceFragment.this.startIdleTimer();
                        return false;
                    default:
                        return false;
                }
            }
        });
    }

    public void startIdleTimer() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    public void resetIdleTimer() {
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    public boolean isViewVisibleInFragment(View view) {
        if (view == null || view.getParent() == null || !(view.getParent() instanceof ViewGroup) || view.getVisibility() != 0) {
            return false;
        }
        View view2 = getView();
        int[] iArr = new int[2];
        view2.getLocationOnScreen(iArr);
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        int[] iArr2 = new int[2];
        view.getLocationOnScreen(iArr2);
        return iArr2[1] + view.getHeight() >= iArr[1] && iArr2[1] <= iArr[1] + view2.getHeight();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroyView() {
        super.onDestroyView();
        Handler handler = this.handler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        FuWuConstant.isBianJiState = false;
        FuWuConstant.isShouYe = false;
        FuWuConstant.isRefreshList = false;
        FuWuConstant.isRefshMenu = false;
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment");
        super.onResume();
        if (!currentPhone.equals(UserManager.getInstance().getCurrentPhoneNumber())) {
            FuWuConstant.isBianJiState = false;
            FuWuConstant.isShouYe = false;
            FuWuConstant.isRefreshList = false;
            FuWuConstant.isRefshMenu = false;
            this.isScrolled = false;
            this.position = 0;
            this.fuwuDownUp = 0;
            this.isExpend = true;
            this.previousSelectedTabPosition = 0;
            this.isHuaDong = true;
            this.isJiaZai = true;
            this.searchPosition = 0;
            isSearchAdapterSX = true;
            this.lastverticalOffset = 0;
            LinearLayout linearLayout = this.searrchBgLayout;
            if (linearLayout != null) {
                linearLayout.setVisibility(8);
            }
            FuWuSearchLayoutView fuWuSearchLayoutView = this.searchLayoutView;
            if (fuWuSearchLayoutView != null) {
                fuWuSearchLayoutView.setVisibility(0);
            }
            LinearLayout linearLayout2 = this.lin_taocan_all;
            if (linearLayout2 != null) {
                linearLayout2.setVisibility(8);
            }
            MsLogUtil.m7979d("触发切换账号机制：", "已触发");
            currentPhone = UserManager.getInstance().getCurrentPhoneNumber();
            this.marketingBitsListEntity = new MarketingBitsListEntity();
            this.marketingBitsListEntity.setHiddenBottom(true);
            this.marketingBitsListEntity.setHiddenTop(true);
            this.fuwuDownUp = 0;
            this.img_fuwu_down_up.setImageResource(2131231323);
            this.rl_one.setVisibility(0);
            this.tv_yy_desc.setVisibility(8);
            this.rl_drag_view.setVisibility(8);
            removeAppBarLayoutView();
            initData();
            refresh();
        }
        TabLayout tabLayout = this.tabLayout;
        if (tabLayout != null && tabLayout.getTabAt(this.position) != null) {
            this.tabLayout.getTabAt(this.position).select();
        }
        try {
            String string = App.getSharePreferenceUtil().getString("config_big_font");
            String string2 = App.getSharePreferenceUtil().getString(ConfigManager.config_font_Size);
            if (TextUtils.equals("1", string) && !TextUtils.equals(this.bigFont, string2)) {
                this.bigFont = string2;
                this.searchLayoutView.update();
            }
        } catch (Exception e) {
            UIUtils.logE(e.getMessage());
        }
        initGuangGao("1");
        FuWuConstant.isRefshMenu = false;
        PopWebViewEvent.currentType = 5;
        refreshTitle();
        if (this.isFirstLog) {
            startIdleTimer();
        }
        if (FuWuConstant.isRefreshList) {
            MsLogUtil.m7979d("服务页面", "需要更新服务页面数据");
            initData();
            refresh();
            FuWuConstant.isRefreshList = false;
        }
        try {
            scanLight();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
        if (!FuWuConstant.isBianJiState) {
            this.fuwuDownUp = 1;
            this.img_fuwu_down_up.performClick();
        }
        initBack(true);
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.basic.ui.fragment.ServiceFragment");
    }

    private void scanLight() {
        try {
            UnicomCollectManager.getInstance().scanCollect(CollectDataEntity.newBuilder().setPageName("服务").build());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        EventBusUtils.unregister(this);
        FuWuConstant.topList = null;
    }

    private void initTab() {
        TabLayout tabLayout = this.tabLayout;
        if (tabLayout != null) {
            tabLayout.removeAllTabs();
            List<ServiceListItem> list = this.tabList;
            if (list != null && list.size() > 0) {
                for (int i = 0; i < this.tabList.size(); i++) {
                    TabLayout.Tab newTab = this.tabLayout.newTab();
                    View inflate = LayoutInflater.from(this.activityContext).inflate(2131493141, (ViewGroup) this.tabLayout, false);
                    TextView textView = (TextView) inflate.findViewById(2131299093);
                    textView.setText(this.tabList.get(i).getTitle());
                    if (i == 0) {
                        textView.setTypeface(Typeface.DEFAULT_BOLD);
                        textView.setTextSize(1, 17.0f);
                    } else {
                        textView.setTypeface(Typeface.DEFAULT);
                        textView.setTextSize(2, 14.0f);
                    }
                    View findViewById = inflate.findViewById(2131299529);
                    findViewById.setVisibility(0);
                    if (i == this.tabList.size() - 1) {
                        findViewById.setVisibility(8);
                    }
                    View findViewById2 = inflate.findViewById(2131298736);
                    findViewById2.setVisibility(4);
                    if (i == 0) {
                        findViewById2.setVisibility(0);
                    }
                    newTab.setCustomView(inflate);
                    this.tabLayout.addTab(newTab);
                }
            }
            this.tabLayout.setTabMode(0);
            this.tabLayout.setSelectedTabIndicatorHeight(0);
            this.lin_youjiantou.setVisibility(0);
            this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.3
                @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
                public void onTabSelected(TabLayout.Tab tab) {
                    int tabCount;
                    ServiceFragment.this.position = tab.getPosition();
                    if (ServiceFragment.this.tabLayout != null && (tabCount = ServiceFragment.this.tabLayout.getTabCount()) > 0) {
                        if (ServiceFragment.this.position == tabCount - 1) {
                            ServiceFragment.this.lin_youjiantou.setVisibility(8);
                        } else {
                            ServiceFragment.this.lin_youjiantou.setVisibility(0);
                        }
                    }
                    if (ServiceFragment.this.position == 0) {
                        ServiceFragment.this.isExpend = true;
                    } else {
                        ServiceFragment.this.isExpend = false;
                    }
                    if (!ServiceFragment.this.isScrolled) {
                        ServiceFragment.this.manager.scrollToPositionWithOffset(ServiceFragment.this.position, 0);
                        ServiceFragment.this.appBarLayout.setExpanded(ServiceFragment.this.isExpend);
                    }
                    if (ServiceFragment.this.position != 0) {
                        ServiceFragment.this.appBarLayout.setExpanded(ServiceFragment.this.isExpend);
                    }
                    View customView = tab.getCustomView();
                    customView.setBackgroundColor(ServiceFragment.this.getResources().getColor(17170445));
                    TextView textView2 = (TextView) customView.findViewById(2131299093);
                    textView2.setTextColor(ServiceFragment.this.getResources().getColor(2131099977));
                    textView2.setTypeface(Typeface.DEFAULT_BOLD);
                    textView2.setTextSize(1, 17.0f);
                    customView.findViewById(2131298736).setVisibility(0);
                    int position = tab.getPosition();
                    if (position != ServiceFragment.this.previousSelectedTabPosition) {
                        ServiceFragment.this.previousSelectedTabPosition = position;
                        if (!ServiceFragment.this.isHuaDong) {
                            ServiceFragment.this.isHuaDong = true;
                            return;
                        }
                        MsLogUtil.m7979d("新的服务页-->【落落落落落落落落落落落】", "第" + ServiceFragment.this.previousSelectedTabPosition + "个");
                        FuWuUtils.clickCollect(PvCurrencyLogUtils.getPostion("20401", ServiceFragment.this.position), textView2.getText().toString(), "");
                        String postion = PvCurrencyLogUtils.getPostion("20401", ServiceFragment.this.position);
                        PvCurrencyLogUtils.pvLogFuWu(postion, "服务页Tab签标题" + (ServiceFragment.this.position + 1), "", textView2.getText().toString(), "");
                    }
                }

                @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
                public void onTabUnselected(TabLayout.Tab tab) {
                    View customView = tab.getCustomView();
                    customView.setBackgroundColor(ServiceFragment.this.getResources().getColor(17170445));
                    TextView textView2 = (TextView) customView.findViewById(2131299093);
                    textView2.setTextColor(ServiceFragment.this.getResources().getColor(2131099977));
                    textView2.setTypeface(Typeface.DEFAULT);
                    textView2.setTextSize(2, 14.0f);
                    customView.findViewById(2131298736).setVisibility(4);
                }

                @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
                public void onTabReselected(TabLayout.Tab tab) {
                    ServiceFragment.this.position = tab.getPosition();
                    if (ServiceFragment.this.position == 0) {
                        ServiceFragment.this.isExpend = true;
                    } else {
                        ServiceFragment.this.isExpend = false;
                    }
                    if (ServiceFragment.this.isScrolled) {
                        return;
                    }
                    ServiceFragment.this.appBarLayout.setExpanded(ServiceFragment.this.isExpend);
                    if (ServiceFragment.this.position == 0) {
                        ServiceFragment.this.manager.scrollToPositionWithOffset(ServiceFragment.this.position, 0);
                    }
                }
            });
        }
    }

    private void addTopList(MenuEntity menuEntity) {
        FuWuConstant.topList.add(menuEntity);
        isSearchAdapterSX = false;
        MsLogUtil.m7980d("改变------>搜索模块的下标: isSearchAdapterSX = false   add");
        refresh();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBack(boolean z) {
        if (this.lin_top_back == null || this.iv_back == null) {
            return;
        }
        if (FuWuConstant.isShouYe && z) {
            this.iv_back.setVisibility(0);
            this.lin_top_back.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    EventBusUtils.post(new ServiceMenuEvent(EventBusUtils.EVENT_SHOUYE_TIAOZHUAN));
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            return;
        }
        this.iv_back.setVisibility(8);
        this.lin_top_back.setOnClickListener(null);
    }

    private void init(View view) {
        this.lin_youjiantou = (LinearLayout) view.findViewById(2131297620);
        this.lin_youjiantou.setVisibility(8);
        this.lin_top_all = (LinearLayout) view.findViewById(2131297615);
        this.rl_main = new MyRelativeLayout(this.activityContext);
        this.lin_top_back = (LinearLayout) view.findViewById(2131297616);
        this.iv_back = (ImageView) view.findViewById(2131297344);
        initBack(true);
        this.rl_child_main = (RelativeLayout) view.findViewById(2131298328);
        this.viewPager = (ViewPager) view.findViewById(2131298496);
        this.viewPager.setSaveEnabled(false);
        this.lin_viewpager = (LinearLayout) view.findViewById(2131297618);
        this.pagerIndicator = (PagerIndicator) view.findViewById(2131298184);
        this.myPagerAdapter = new MyPagerAdapter(getChildFragmentManager());
        this.viewPager.setAdapter(this.myPagerAdapter);
        this.pagerIndicator.setNumIndicators(this.viewPager.getAdapter().getCount());
        this.viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.5
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i, float f, int i2) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i) {
                NBSActionInstrumentation.onPageSelectedEnter(i, this);
                MsLogUtil.m7980d("搜索模块的下标:" + ServiceFragment.this.searchPosition);
                if (ServiceFragment.isSearchAdapterSX) {
                    ServiceFragment.this.searchPosition = i;
                    MsLogUtil.m7980d("改变------>搜索模块的下标:" + ServiceFragment.this.searchPosition);
                } else {
                    MsLogUtil.m7980d("改变------>搜索模块的下标: isSearchAdapterSX = true");
                    ServiceFragment.isSearchAdapterSX = true;
                }
                ServiceFragment.this.pagerIndicator.setCurrentIndex(i);
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
        this.tv_fuwu_text = (TextView) view.findViewById(2131298949);
        this.tv_syyy = (TextView) view.findViewById(2131299092);
        this.appBarLayout = (AppBarLayout) view.findViewById(2131296373);
        this.lin_fuwu1 = (RelativeLayout) view.findViewById(2131297604);
        this.appBarLayout.setExpanded(true);
        this.searchLayout = (LinearLayout) view.findViewById(2131297203);
        this.searchLayoutView = (FuWuSearchLayoutView) view.findViewById(2131297206);
        this.searrchBgLayout = (LinearLayout) view.findViewById(2131298537);
        this.service_viewFlipper_text = (TextView) view.findViewById(2131298541);
        this.searchLayout.setVisibility(0);
        this.cancelBianJi = (TextView) view.findViewById(2131296570);
        this.cancelBianJi.setOnClickListener(this);
        this.cancelBianJi.setVisibility(8);
        this.tv_huifumoren = (TextView) view.findViewById(2131298970);
        this.tv_huifumoren.setOnClickListener(this);
        this.tv_search = (TextView) view.findViewById(2131299072);
        this.lin_taocan_all = (LinearLayout) view.findViewById(2131297613);
        this.lin_taocan_all.setVisibility(8);
        this.tv_btn_search = (TextView) view.findViewById(2131298902);
        this.iv_search_close = (ImageView) view.findViewById(2131297491);
        this.iv_search_close.setOnClickListener(this);
        this.tv_btn_search.setOnClickListener(this);
        this.tv_bendisearchtext = (EditText) view.findViewById(2131298878);
        this.tv_bendisearchtext.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view2, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    ServiceFragment.this.tv_bendisearchtext.setCursorVisible(true);
                    FuWuUtils.clickCollect("2070101", "触发搜索", "");
                    PvCurrencyLogUtils.pvLogFuWu("2070101", "触发搜索", "", "触发搜索", "");
                    return false;
                }
                return false;
            }
        });
        this.tv_bendisearchtext.addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.7
            @Override // android.text.TextWatcher
            public void afterTextChanged(Editable editable) {
            }

            @Override // android.text.TextWatcher
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            @Override // android.text.TextWatcher
            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
                ServiceFragment.this.searchShow(charSequence.toString());
            }
        });
        this.rl_one = (RecyclerView) view.findViewById(2131298366);
        this.img_fuwu_down_up = (ImageView) view.findViewById(2131297294);
        this.img_fuwu_down_up.setOnClickListener(this);
        this.tv_yy_desc = (TextView) view.findViewById(2131299156);
        this.tv_yy_desc.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                if (!FuWuConstant.isBianJiState) {
                    ServiceFragment serviceFragment = ServiceFragment.this;
                    serviceFragment.fuwuDownUp = 1;
                    serviceFragment.img_fuwu_down_up.performClick();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.customOneAdapter = new CustomOneAdapter(this.activityContext, FuWuConstant.topList);
        this.customOneAdapter.setOnItemClickListener(new CustomOneAdapter.onItemClickListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.9
            @Override // com.sinovatech.unicom.basic.p315ui.adapter.CustomOneAdapter.onItemClickListener
            public void onItemClickListener() {
                ServiceFragment serviceFragment = ServiceFragment.this;
                serviceFragment.fuwuDownUp = 0;
                serviceFragment.img_fuwu_down_up.performClick();
            }
        });
        this.rl_one.setAdapter(this.customOneAdapter);
        this.rl_one.setLayoutManager(new LinearLayoutManager(this.activityContext, 0, false));
        this.recyclerView = (RecyclerView) view.findViewById(2131298065);
        this.tabLayout = (TabLayout) view.findViewById(2131298001);
        this.lin_jiantou = (LinearLayout) view.findViewById(2131297607);
        this.lin_jiantou.setOnClickListener(this);
        this.manager = new LinearLayoutManager(this.activityContext);
        this.manager.setReverseLayout(false);
        this.recyclerView.setLayoutManager(this.manager);
        this.adapter = new MainAdapter(this.activityContext, this.bottomList);
        this.recyclerView.setAdapter(this.adapter);
        int dimensionPixelSize = getResources().getDimensionPixelSize(2131166082);
        int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131166077);
        int dimensionPixelSize3 = getResources().getDimensionPixelSize(2131166083);
        this.recyclerView.addItemDecoration(new VerticalSpacingItemDecoration(dimensionPixelSize2, dimensionPixelSize));
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.10
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    ServiceFragment.this.isScrolled = false;
                    ServiceFragment.this.isHuaDong = true;
                    return;
                }
                ServiceFragment.this.isScrolled = true;
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
                if (ServiceFragment.this.isScrolled) {
                    try {
                        ServiceFragment.this.position = ServiceFragment.this.manager.findFirstVisibleItemPosition();
                        if (ServiceFragment.this.tabLayout != null) {
                            ServiceFragment.this.tabLayout.setScrollPosition(ServiceFragment.this.position, 0.0f, true);
                            ServiceFragment.this.isHuaDong = false;
                            ServiceFragment.this.tabLayout.getTabAt(ServiceFragment.this.position).select();
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7977e("服务页面", "滑动异常：" + e.getMessage());
                    }
                }
                ServiceFragment.this.getHeight();
            }
        });
        this.rl_drag_view = (RelativeLayout) view.findViewById(2131298336);
        this.rl_drag_view.setVisibility(8);
        this.dragRecyclerView = (RecyclerView) view.findViewById(2131296909);
        this.dragRecyclerView.setNestedScrollingEnabled(false);
        this.dragRecyclerViewAdapter = new DragRecyclerViewAdapter(this.activityContext, FuWuConstant.topList);
        this.dragRecyclerViewAdapter.setOnItemTouchUpListener(new DragRecyclerViewAdapter.OnItemTouchUpListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.11
            @Override // com.sinovatech.unicom.basic.p315ui.fuwu.adapter.DragRecyclerViewAdapter.OnItemTouchUpListener
            public void onItemTouchUp(RecyclerView.ViewHolder viewHolder) {
                ServiceFragment.this.dragRecyclerViewAdapter.onItemClear(viewHolder);
            }

            @Override // com.sinovatech.unicom.basic.p315ui.fuwu.adapter.DragRecyclerViewAdapter.OnItemTouchUpListener
            public void onItemClick(int i, DragRecyclerViewAdapter.DragHolder dragHolder) {
                if (FuWuConstant.isBianJiState || FuWuConstant.topList == null || FuWuConstant.topList.size() <= 0 || i >= FuWuConstant.topList.size()) {
                    ServiceFragment.this.dragRecyclerViewAdapter.onItemDissmiss(dragHolder);
                    MenuDataCenter.getInstance().logList("删除之后");
                    return;
                }
                StringBuilder sb = new StringBuilder();
                sb.append("202010");
                int i2 = i + 2;
                sb.append(i2);
                FuWuUtils.clickCollect(sb.toString(), FuWuConstant.topList.get(i).getMenuTitle(), FuWuConstant.topList.get(i).getMenuURL());
                PvCurrencyLogUtils.pvLogFuWu("202010" + i2, "服务页应用编辑-点击" + (i + 1), FuWuConstant.topList.get(i).getMenuURL(), FuWuConstant.topList.get(i).getMenuTitle(), FuWuConstant.topList.get(i).getGroupTitle());
                RecentBoxManager.getInstance().put(FuWuConstant.topList.get(i));
                IntentManager.generateIntentAndGo(ServiceFragment.this.activityContext, FuWuConstant.topList.get(i), "get");
            }
        });
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.activityContext, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.12
            @Override // android.support.p086v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return 1;
            }
        });
        this.dragRecyclerView.setLayoutManager(gridLayoutManager);
        int dimensionPixelSize4 = getResources().getDimensionPixelSize(2131166075);
        this.dragRecyclerView.addItemDecoration(new FuWuTopVerticalSpacingItemDecoration(4, dimensionPixelSize3, false, dimensionPixelSize4));
        this.dragRecyclerView.setAdapter(this.dragRecyclerViewAdapter);
        this.dragRecyclerView2 = (RecyclerView) view.findViewById(2131296908);
        this.dragRecyclerView2.setNestedScrollingEnabled(false);
        this.dragRecyclerViewAdapter2 = new DragRecyclerBaiViewAdapter(this.activityContext);
        GridLayoutManager gridLayoutManager2 = new GridLayoutManager(this.activityContext, 4);
        gridLayoutManager2.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.13
            @Override // android.support.p086v7.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int i) {
                return 1;
            }
        });
        this.dragRecyclerView2.setLayoutManager(gridLayoutManager2);
        this.dragRecyclerView2.addItemDecoration(new FuWuTopVerticalSpacingItemDecoration(4, dimensionPixelSize3, false, dimensionPixelSize4));
        this.dragRecyclerView2.setAdapter(this.dragRecyclerViewAdapter2);
        new ItemTouchHelper(new SimpleItemTouchHelperCallback(this.dragRecyclerViewAdapter)).attachToRecyclerView(this.dragRecyclerView);
        this.tv_fuwu_bianji = (TextView) view.findViewById(2131298948);
        this.tv_fuwu_bianji.setOnClickListener(this);
        final int dimension = (int) this.activityContext.getResources().getDimension(2131165942);
        this.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.14
            @Override // android.support.design.widget.AppBarLayout.OnOffsetChangedListener, android.support.design.widget.AppBarLayout.BaseOnOffsetChangedListener
            public void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                boolean z;
                if (i == ServiceFragment.this.lastverticalOffset) {
                    return;
                }
                ServiceFragment.this.lastverticalOffset = i;
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                if (totalScrollRange != -1 && ServiceFragment.this.marketingBitsListEntity != null) {
                    if (ServiceFragment.this.marketingBitsListEntity.isHiddenTop() && ServiceFragment.this.marketingBitsListEntity.isHiddenBottom()) {
                        z = false;
                    } else if (ServiceFragment.this.marketingBitsListEntity.isHiddenTop()) {
                        z = true;
                    } else if (ServiceFragment.this.marketingBitsListEntity.isHiddenBottom()) {
                        if (ServiceFragment.this.viewOne != null) {
                            ViewGroup.MarginLayoutParams marginLayoutParams = (ViewGroup.MarginLayoutParams) ServiceFragment.this.viewOne.getLayoutParams();
                            marginLayoutParams.bottomMargin = dimension;
                            ServiceFragment.this.viewOne.setLayoutParams(marginLayoutParams);
                        }
                        z = false;
                    } else {
                        z = true;
                    }
                    ViewGroup.MarginLayoutParams marginLayoutParams2 = (ViewGroup.MarginLayoutParams) ServiceFragment.this.lin_fuwu1.getLayoutParams();
                    if (z) {
                        marginLayoutParams2.bottomMargin = 0;
                    } else if (z) {
                        float abs = (totalScrollRange - Math.abs(i)) / totalScrollRange;
                        marginLayoutParams2.bottomMargin = (int) ((dimension * abs) + ((1.0f - abs) * 0.0f));
                    } else {
                        marginLayoutParams2.bottomMargin = dimension;
                    }
                    ServiceFragment.this.lin_fuwu1.setLayoutParams(marginLayoutParams2);
                }
                ServiceFragment.this.getHeight();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void searchShow(final String str) {
        try {
            this.activityContext.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.15
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        if (TextUtils.isEmpty(str)) {
                            ServiceFragment.this.tv_search.setVisibility(8);
                            ServiceFragment.this.lin_viewpager.setVisibility(8);
                            ServiceFragment.this.iv_search_close.setVisibility(8);
                            return;
                        }
                        ServiceFragment.this.iv_search_close.setVisibility(0);
                        ArrayList arrayList = new ArrayList();
                        if (ServiceFragment.this.bottomList != null && ServiceFragment.this.bottomList.size() > 0) {
                            for (int i = 0; i < ServiceFragment.this.bottomList.size(); i++) {
                                MainBean mainBean = (MainBean) ServiceFragment.this.bottomList.get(i);
                                if (mainBean != null && mainBean.getList() != null && mainBean.getList().size() > 0) {
                                    for (int i2 = 0; i2 < mainBean.getList().size(); i2++) {
                                        MenuEntity menuEntity = mainBean.getList().get(i2);
                                        if (menuEntity != null) {
                                            String menuTitle = menuEntity.getMenuTitle();
                                            String menuAliasName = menuEntity.getMenuAliasName();
                                            if (!TextUtils.isEmpty(menuTitle) && menuTitle.toLowerCase().contains(str.toLowerCase())) {
                                                arrayList.add(menuEntity);
                                            } else if (!TextUtils.isEmpty(menuAliasName) && menuAliasName.toLowerCase().contains(str.toLowerCase())) {
                                                arrayList.add(menuEntity);
                                            }
                                        }
                                    }
                                }
                            }
                        }
                        ServiceFragment.this.fuWuItemBeanList.clear();
                        ServiceFragment.this.fuWuItemBeanList.addAll(arrayList);
                        if (ServiceFragment.this.fuWuItemBeanList == null || ServiceFragment.this.fuWuItemBeanList.size() <= 0) {
                            ServiceFragment.this.tv_search.setVisibility(0);
                            ServiceFragment.this.lin_viewpager.setVisibility(8);
                            return;
                        }
                        ServiceFragment.this.tv_search.setVisibility(8);
                        ServiceFragment.this.lin_viewpager.setVisibility(0);
                        ServiceFragment.this.refreshSearchRecycleview();
                    } catch (Exception unused) {
                        ServiceFragment.this.tv_search.setVisibility(0);
                        ServiceFragment.this.lin_viewpager.setVisibility(8);
                    }
                }
            });
        } catch (Exception unused) {
            this.tv_search.setVisibility(0);
            this.lin_viewpager.setVisibility(8);
        }
    }

    public void initData() {
        int intValue;
        this.searchPosition = 0;
        this.bottomList.clear();
        this.tPosition.clear();
        this.tabList = this.menuDataCenter.getServiceListData();
        this.choseList = this.menuDataCenter.getServiceRecylerData();
        for (int i = 0; i < this.choseList.size(); i++) {
            if (this.choseList.get(i).isFirst()) {
                this.tPosition.add(Integer.valueOf(i));
            }
        }
        int i2 = 0;
        while (i2 < this.tabList.size()) {
            this.tabList.get(i2).getTitle();
            ArrayList arrayList = new ArrayList();
            int i3 = i2 + 1;
            if (i3 == this.tPosition.size()) {
                intValue = this.choseList.size();
            } else {
                intValue = this.tPosition.get(i3).intValue();
            }
            for (int intValue2 = this.tPosition.get(i2).intValue(); intValue2 < intValue; intValue2++) {
                MenuEntity menuEntity = this.choseList.get(intValue2);
                if ((TextUtils.isEmpty(menuEntity.getMenuURL()) || TextUtils.isEmpty(menuEntity.getMenuTitle())) ? false : true) {
                    arrayList.add(menuEntity);
                }
            }
            this.bottomList.add(new MainBean(this.tabList.get(i2).getTitle(), arrayList));
            i2 = i3;
        }
        FuWuConstant.topList = this.menuDataCenter.getHomeDataNew(true);
        refreshTitle();
        initTab();
        isSearchAdapterSX = true;
    }

    private void refreshTitle() {
        try {
            new HomeTabManager(this.activityContext).getHomeTab("2").subscribe(new Consumer() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$ServiceFragment$48fTWLZTuU7pFaK3B26szFEaqT4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ServiceFragment.lambda$refreshTitle$1(ServiceFragment.this, (HomeTabEntity) obj);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.16
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    MsLogUtil.m7979d("loadHomeTab", "请求结果：" + th.getMessage());
                    th.printStackTrace();
                    ServiceFragment.this.homeTabEntity = FuWuUtils.defaultText();
                    ServiceFragment.this.fuwuTitle();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e("获取标题缓存失败：", e.getMessage());
            this.homeTabEntity = FuWuUtils.defaultText();
            fuwuTitle();
        }
    }

    public static /* synthetic */ void lambda$refreshTitle$1(ServiceFragment serviceFragment, HomeTabEntity homeTabEntity) throws Exception {
        try {
            if ("0000".equals(homeTabEntity.getRespCode()) && homeTabEntity.getData() != null && homeTabEntity.getData().getServicePageCfgCW() != null) {
                String headTitle = homeTabEntity.getData().getServicePageCfgCW().getHeadTitle();
                String appMenuTip = homeTabEntity.getData().getServicePageCfgCW().getAppMenuTip();
                String moveMenuTip = homeTabEntity.getData().getServicePageCfgCW().getMoveMenuTip();
                String editMenuTip = homeTabEntity.getData().getServicePageCfgCW().getEditMenuTip();
                if (TextUtils.isEmpty(headTitle)) {
                    headTitle = "服务大厅";
                }
                if (TextUtils.isEmpty(appMenuTip)) {
                    appMenuTip = "首页应用";
                }
                if (TextUtils.isEmpty(moveMenuTip)) {
                    moveMenuTip = "（按住拖动调整顺序）";
                }
                if (TextUtils.isEmpty(editMenuTip)) {
                    editMenuTip = "（点击编辑进入首页应用自定义）";
                }
                homeTabEntity.getData().getServicePageCfgCW().setHeadTitle(headTitle);
                homeTabEntity.getData().getServicePageCfgCW().setAppMenuTip(appMenuTip);
                homeTabEntity.getData().getServicePageCfgCW().setMoveMenuTip(moveMenuTip);
                homeTabEntity.getData().getServicePageCfgCW().setEditMenuTip(editMenuTip);
                serviceFragment.homeTabEntity.setData(homeTabEntity.getData());
                serviceFragment.homeTabEntity.setCacheTime(homeTabEntity.getCacheTime());
                serviceFragment.homeTabEntity.setRespCode(homeTabEntity.getRespCode());
                serviceFragment.homeTabEntity.setRespTime(homeTabEntity.getRespTime());
                serviceFragment.homeTabEntity.setTransId(homeTabEntity.getTransId());
                serviceFragment.homeTabEntity.setRespMessage(homeTabEntity.getRespMessage());
            }
        } catch (Exception unused) {
            serviceFragment.homeTabEntity = FuWuUtils.defaultText();
        }
        serviceFragment.fuwuTitle();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void fuwuTitle() {
        String editMenuTip;
        try {
            String headTitle = this.homeTabEntity.getData().getServicePageCfgCW().getHeadTitle();
            String appMenuTip = this.homeTabEntity.getData().getServicePageCfgCW().getAppMenuTip();
            if (this.tv_fuwu_text != null) {
                FuWuUtils.setUpdateFuWuTitle(this.tv_fuwu_text, headTitle);
            }
            if (this.tv_syyy != null) {
                FuWuUtils.setUpdateFuWuTitle(this.tv_syyy, appMenuTip);
            }
            if (this.tv_yy_desc != null) {
                if (FuWuConstant.isBianJiState) {
                    editMenuTip = this.homeTabEntity.getData().getServicePageCfgCW().getMoveMenuTip();
                } else {
                    editMenuTip = this.homeTabEntity.getData().getServicePageCfgCW().getEditMenuTip();
                }
                this.tv_yy_desc.setText(editMenuTip);
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("服务页面", e.getMessage());
        }
    }

    private void initGuangGao(String str) {
        try {
            this.marketingBitsManager.getServiceHomeMarketingBits(str).subscribe(new Consumer<MarketingBitsListEntity>() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.17
                @Override // io.reactivex.functions.Consumer
                public void accept(MarketingBitsListEntity marketingBitsListEntity) throws Exception {
                    ServiceFragment.this.marketingBitsListEntity = marketingBitsListEntity;
                    if ("0000".equals(marketingBitsListEntity.getRespCode())) {
                        ServiceFragment serviceFragment = ServiceFragment.this;
                        serviceFragment.addAppBarLayoutView(serviceFragment.marketingBitsListEntity);
                    }
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.basic.ui.fragment.-$$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ((Throwable) obj).printStackTrace();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7977e("服务页面", "首页业务办理接口异常:" + e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAppBarLayoutView(MarketingBitsListEntity marketingBitsListEntity) {
        View childAt;
        try {
            if (FuWuConstant.isBianJiState || this.fuwuDownUp != 0 || marketingBitsListEntity == null) {
                return;
            }
            if (this.viewOne == null) {
                this.viewOne = new FuWuOneView(this.activityContext);
            }
            this.viewOne.initUI(marketingBitsListEntity);
            if (this.viewTwo == null) {
                this.viewTwo = new FuWuTwoView(this.activityContext);
            }
            this.viewTwo.initUI(marketingBitsListEntity);
            if (this.appBarLayout != null) {
                ViewGroup viewGroup = (ViewGroup) this.viewOne.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(this.viewOne);
                }
                ViewGroup viewGroup2 = (ViewGroup) this.viewTwo.getParent();
                if (viewGroup2 != null) {
                    viewGroup2.removeView(this.viewTwo);
                }
                int childCount = this.lin_top_all.getChildCount();
                if (childCount > 0 && (childAt = this.lin_top_all.getChildAt(childCount - 1)) != null && (childAt instanceof FuWuTwoView)) {
                    this.lin_top_all.removeView(childAt);
                }
                boolean z = !marketingBitsListEntity.isHiddenTop();
                boolean z2 = !marketingBitsListEntity.isHiddenBottom();
                if (z && z2) {
                    AppBarLayout.LayoutParams layoutParams = new AppBarLayout.LayoutParams(-1, -2);
                    layoutParams.setScrollFlags(1);
                    this.viewTwo.setLayoutParams(layoutParams);
                    this.appBarLayout.addView(this.viewTwo, 0);
                    AppBarLayout.LayoutParams layoutParams2 = new AppBarLayout.LayoutParams(-1, -2);
                    layoutParams2.setScrollFlags(3);
                    this.viewOne.setLayoutParams(layoutParams2);
                    this.appBarLayout.addView(this.viewOne, 0);
                } else if (z) {
                    AppBarLayout.LayoutParams layoutParams3 = new AppBarLayout.LayoutParams(-1, -2);
                    layoutParams3.setScrollFlags(3);
                    this.viewOne.setLayoutParams(layoutParams3);
                    this.appBarLayout.addView(this.viewOne, 0);
                } else if (z2) {
                    RelativeLayout.LayoutParams layoutParams4 = new RelativeLayout.LayoutParams(-1, -2);
                    layoutParams4.addRule(3, this.lin_top_all.getId());
                    this.viewTwo.setLayoutParams(layoutParams4);
                    this.lin_top_all.addView(this.viewTwo);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("服务页面", "添加add滑动view:" + e.getMessage());
        }
    }

    private void removeAppBarLayoutView() {
        ViewParent parent;
        try {
            if (this.appBarLayout != null) {
                int childCount = this.appBarLayout.getChildCount();
                Log.d("服务页面", "服务页面的子页面有几个：" + childCount);
                if (childCount >= 3) {
                    View childAt = this.appBarLayout.getChildAt(0);
                    View childAt2 = this.appBarLayout.getChildAt(1);
                    View childAt3 = this.appBarLayout.getChildAt(2);
                    if ((childAt instanceof FuWuOneView) || (childAt instanceof FuWuTwoView)) {
                        this.appBarLayout.removeView(childAt);
                    }
                    if ((childAt2 instanceof FuWuOneView) || (childAt2 instanceof FuWuTwoView)) {
                        this.appBarLayout.removeView(childAt2);
                    }
                    if ((childAt3 instanceof FuWuOneView) || (childAt3 instanceof FuWuTwoView)) {
                        this.appBarLayout.removeView(childAt3);
                    }
                } else if (childCount >= 2) {
                    View childAt4 = this.appBarLayout.getChildAt(0);
                    View childAt5 = this.appBarLayout.getChildAt(1);
                    if ((childAt4 instanceof FuWuOneView) || (childAt4 instanceof FuWuTwoView)) {
                        this.appBarLayout.removeView(childAt4);
                    }
                    if ((childAt5 instanceof FuWuOneView) || (childAt5 instanceof FuWuTwoView)) {
                        this.appBarLayout.removeView(childAt5);
                    }
                } else if (childCount >= 1) {
                    View childAt6 = this.appBarLayout.getChildAt(0);
                    if (childAt6 instanceof FuWuOneView) {
                        this.appBarLayout.removeView(childAt6);
                    }
                    if (this.viewTwo == null || (parent = this.viewTwo.getParent()) == null || parent != this.lin_top_all) {
                        return;
                    }
                    this.lin_top_all.removeView(this.viewTwo);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7977e("服务页面", "移除appBarLayoutView子布局UI:" + e.getMessage());
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131296570:
                FuWuUtils.clickCollect("2050101", "取消编辑", "");
                PvCurrencyLogUtils.pvLogFuWu("2050101", "取消编辑", "", "取消编辑", "");
                showSave(false, new SaveCallback() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.18
                    @Override // com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment.SaveCallback
                    public void onSaveSuccess(boolean z) {
                        ServiceFragment.this.initBack(true);
                        if (z) {
                            ServiceFragment.this.searchShow("");
                        }
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.fragment.ServiceFragment.SaveCallback
                    public void onSaveFailed(boolean z) {
                        ServiceFragment.this.initBack(true);
                        if (z) {
                            ServiceFragment.this.searchShow("");
                        }
                        ServiceFragment serviceFragment = ServiceFragment.this;
                        serviceFragment.fuwuDownUp = 1;
                        serviceFragment.img_fuwu_down_up.performClick();
                    }
                });
                break;
            case 2131297294:
            case 2131297607:
                if (this.fuwuDownUp == 0) {
                    this.fuwuDownUp = 1;
                    this.img_fuwu_down_up.setImageResource(2131231324);
                    this.rl_one.setVisibility(8);
                    this.tv_yy_desc.setVisibility(0);
                    this.tv_yy_desc.setText(this.homeTabEntity.getData().getServicePageCfgCW().getEditMenuTip());
                    this.rl_drag_view.setVisibility(0);
                    removeAppBarLayoutView();
                    break;
                } else {
                    this.fuwuDownUp = 0;
                    this.img_fuwu_down_up.setImageResource(2131231323);
                    this.rl_one.setVisibility(0);
                    this.tv_yy_desc.setVisibility(8);
                    this.rl_drag_view.setVisibility(8);
                    addAppBarLayoutView(this.marketingBitsListEntity);
                    try {
                        this.appBarLayout.setExpanded(this.isExpend);
                        if (this.position == 0) {
                            this.manager.scrollToPositionWithOffset(this.position, 0);
                            break;
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7977e("服务页面", e.getMessage());
                        break;
                    }
                }
                break;
            case 2131297491:
                this.tv_bendisearchtext.setText("");
                this.iv_search_close.setVisibility(8);
                break;
            case 2131298902:
                searchShow(this.tv_bendisearchtext.getText().toString());
                break;
            case 2131298948:
                if (!App.hasLogined()) {
                    this.activityContext.startActivity(new Intent(this.activityContext, LoginBindActivity.class));
                    break;
                } else {
                    this.fuwuDownUp = 1;
                    this.img_fuwu_down_up.setImageResource(2131231324);
                    this.rl_one.setVisibility(8);
                    this.tv_yy_desc.setVisibility(0);
                    this.rl_drag_view.setVisibility(0);
                    if (this.tv_fuwu_bianji.getText().toString().equals("编辑")) {
                        this.lastverticalOffset = 0;
                        FuWuUtils.clickCollect("2020101", "编辑", "");
                        PvCurrencyLogUtils.pvLogFuWu("2020101", "服务页应用编辑", "", "编辑", "");
                        FuWuConstant.isBianJiState = true;
                        this.searchLayout.setVisibility(8);
                        this.cancelBianJi.setVisibility(0);
                        this.img_fuwu_down_up.setVisibility(8);
                        this.tv_yy_desc.setText(this.homeTabEntity.getData().getServicePageCfgCW().getMoveMenuTip());
                        this.tv_huifumoren.setVisibility(0);
                        this.lin_taocan_all.setVisibility(0);
                        this.tv_fuwu_bianji.setText("保存");
                        this.dragRecyclerViewAdapter.setContentList(this.activityContext, FuWuConstant.topList, true);
                        removeAppBarLayoutView();
                        this.dragRecyclerViewAdapter2.notifyDataSetChanged();
                        initBack(false);
                    } else {
                        FuWuUtils.clickCollect("2060201", "自定义菜单保存", "");
                        PvCurrencyLogUtils.pvLogFuWu("2060201", "自定义菜单保存", "", "保存", "");
                        FuWuConstant.isBianJiState = false;
                        this.searchLayout.setVisibility(0);
                        this.cancelBianJi.setVisibility(8);
                        this.img_fuwu_down_up.setVisibility(0);
                        this.img_fuwu_down_up.setImageResource(2131231324);
                        this.tv_yy_desc.setText(this.homeTabEntity.getData().getServicePageCfgCW().getEditMenuTip());
                        this.tv_huifumoren.setVisibility(8);
                        this.lin_taocan_all.setVisibility(8);
                        this.tv_fuwu_bianji.setText("编辑");
                        this.dragRecyclerViewAdapter.setContentList(this.activityContext, FuWuConstant.topList, false);
                        this.dragRecyclerViewAdapter2.notifyDataSetChanged();
                        addAppBarLayoutView(this.marketingBitsListEntity);
                        if (FuWuConstant.topList == null || FuWuConstant.topList.size() <= 0) {
                            FuWuConstant.topList = MenuDataCenter.getInstance().getHomeDataNew(false);
                        }
                        Gson gson = new Gson();
                        if (FuWuConstant.topList.size() <= 0) {
                            CacheDataCenter.getInstance().setFuWuTopList("", UserManager.getInstance().getCurrentPhoneNumber());
                        } else {
                            List<MenuEntity> list = FuWuConstant.topList;
                            CacheDataCenter.getInstance().setFuWuTopList(!(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list), UserManager.getInstance().getCurrentPhoneNumber());
                        }
                        MenuDataCenter.getInstance().logList("保存按钮");
                        UnicomJinGangQuView.clickCustomMenu = true;
                        UIUtils.showCenterOnlyTextFuWuToast(this.activityContext, "保存成功", 1000, 189);
                        initBack(true);
                        this.fuwuDownUp = 1;
                        this.img_fuwu_down_up.performClick();
                    }
                    resetSearchView();
                    refresh();
                    break;
                }
            case 2131298970:
                FuWuUtils.clickCollect("2060101", "恢复默认", "");
                PvCurrencyLogUtils.pvLogFuWu("2060101", "恢复默认", "", "恢复默认", "");
                FuWuConstant.topList = this.menuDataCenter.getHomeDataNew(false);
                refresh();
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public void refresh() {
        MainAdapter mainAdapter = this.adapter;
        if (mainAdapter != null) {
            mainAdapter.setData(this.bottomList);
        }
        refreshSearchRecycleview();
        DragRecyclerViewAdapter dragRecyclerViewAdapter = this.dragRecyclerViewAdapter;
        if (dragRecyclerViewAdapter != null) {
            dragRecyclerViewAdapter.setData(FuWuConstant.topList);
        }
        DragRecyclerBaiViewAdapter dragRecyclerBaiViewAdapter = this.dragRecyclerViewAdapter2;
        if (dragRecyclerBaiViewAdapter != null) {
            dragRecyclerBaiViewAdapter.notifyDataSetChanged();
        }
        CustomOneAdapter customOneAdapter = this.customOneAdapter;
        if (customOneAdapter != null) {
            customOneAdapter.setData(FuWuConstant.topList);
        }
        isSearchAdapterSX = true;
    }

    public static boolean isFastClick() {
        long currentTimeMillis = System.currentTimeMillis();
        boolean z = currentTimeMillis - lastClickTime < 1000;
        lastClickTime = currentTimeMillis;
        return z;
    }

    public void showSave(final boolean z, final SaveCallback saveCallback) {
        if (isFastClick()) {
            return;
        }
        CustomDialogManager.showFuWu(this.activityContext, new CustomDialogManager.SimpleCustomeDialogListener2() { // from class: com.sinovatech.unicom.basic.ui.fragment.ServiceFragment.19
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
            public void onClickOk() {
                ServiceFragment.this.tv_fuwu_bianji.performClick();
                saveCallback.onSaveSuccess(z);
            }

            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener2
            public void onCancel() {
                FuWuConstant.topList = ServiceFragment.this.menuDataCenter.getHomeDataNew(true);
                FuWuConstant.isBianJiState = false;
                ServiceFragment.this.searchLayout.setVisibility(0);
                ServiceFragment.this.cancelBianJi.setVisibility(8);
                ServiceFragment.this.img_fuwu_down_up.setVisibility(0);
                ServiceFragment.this.img_fuwu_down_up.setImageResource(2131231324);
                ServiceFragment.this.tv_yy_desc.setText(ServiceFragment.this.homeTabEntity.getData().getServicePageCfgCW().getEditMenuTip());
                ServiceFragment.this.tv_huifumoren.setVisibility(8);
                ServiceFragment.this.lin_taocan_all.setVisibility(8);
                ServiceFragment.this.tv_fuwu_bianji.setText("编辑");
                ServiceFragment.this.dragRecyclerViewAdapter.setContentList(ServiceFragment.this.activityContext, FuWuConstant.topList, false);
                ServiceFragment.this.dragRecyclerViewAdapter2.notifyDataSetChanged();
                ServiceFragment serviceFragment = ServiceFragment.this;
                serviceFragment.addAppBarLayoutView(serviceFragment.marketingBitsListEntity);
                ServiceFragment.this.refresh();
                saveCallback.onSaveFailed(z);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void refreshSearchRecycleview() {
        List<MenuEntity> subList;
        try {
            if (this.fuWuItemBeanList == null || this.fuWuItemBeanList.size() <= 0) {
                return;
            }
            MsLogUtil.m7979d("服务页面", "服务页面的myPagerAdapter count = " + this.myPagerAdapter.getCount());
            this.myPagerAdapter.removeFragment();
            int dimensionPixelSize = getResources().getDimensionPixelSize(2131166081);
            int dimensionPixelSize2 = getResources().getDimensionPixelSize(2131166078);
            if (this.fuWuItemBeanList.size() > 5) {
                dimensionPixelSize = dimensionPixelSize2;
            }
            int size = ((this.fuWuItemBeanList.size() + 10) - 1) / 10;
            int i = 0;
            for (int i2 = 1; i2 <= size; i2++) {
                if (i2 == size) {
                    subList = this.fuWuItemBeanList.subList(i, this.fuWuItemBeanList.size());
                } else {
                    subList = this.fuWuItemBeanList.subList(i, i2 * 10);
                }
                this.myPagerAdapter.addFragment(new ViewPagerFragment().newInstance(this.activityContext, subList), "");
                i = i2 * 10;
            }
            this.myPagerAdapter.notifyDataSetChanged();
            this.viewPager.setAdapter(this.myPagerAdapter);
            ViewGroup.LayoutParams layoutParams = this.viewPager.getLayoutParams();
            layoutParams.height = dimensionPixelSize;
            this.viewPager.setLayoutParams(layoutParams);
            if (this.viewPager.getAdapter().getCount() == 1) {
                this.pagerIndicator.setVisibility(8);
            } else {
                this.pagerIndicator.setVisibility(0);
                this.pagerIndicator.setNumIndicators(this.viewPager.getAdapter().getCount());
            }
            this.viewPager.setCurrentItem(this.searchPosition);
        } catch (Exception e) {
            MsLogUtil.m7977e("服务页面", "搜索列表异常：" + e.getMessage());
        }
    }

    private void resetSearchView() {
        try {
            this.tv_bendisearchtext.setText("");
            this.iv_search_close.setVisibility(8);
            this.myPagerAdapter.removeFragment();
            this.myPagerAdapter.notifyDataSetChanged();
            this.tv_search.setVisibility(8);
            this.lin_viewpager.setVisibility(8);
        } catch (Exception unused) {
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void refreshMenu(ServiceMenuEvent serviceMenuEvent) {
        if (serviceMenuEvent.getCode() == EventBusUtils.EVENT_FUWU_REFRESHINIT) {
            initData();
            refresh();
        } else if (serviceMenuEvent.getCode() == EventBusUtils.EVENT_FUWU_REFRESHADAPTER) {
            refresh();
        } else if (serviceMenuEvent.getCode() == EventBusUtils.EVENT_FUWU_ADDTOPLIST) {
            addTopList(serviceMenuEvent.getMenuEntity());
        } else {
            serviceMenuEvent.getCode();
            int i = EventBusUtils.EVENT_FUWU_SHOWTAB;
        }
    }
}
