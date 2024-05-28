package com.sinovatech.unicom.separatemodule.audience.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.SlidingConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.adpter.AudienceAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsDialog;
import com.sinovatech.unicom.separatemodule.audience.view.LiveNoMoreDataFooter;
import com.sinovatech.unicom.separatemodule.audience.view.LivePvResultDialog;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener;
import com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SlowLiveFragment extends BaseFragment {
    public static final String INDEX_FOR_LIVE = "indexForLive";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 2001;
    public static final String SLOW_LIVE = "theSlowLive";
    private static final String TAG = "SlowLiveFragment";
    public NBSTraceUnit _nbs_trace;
    private AudienceMainActivity activityContext;
    private Disposable autoJion;
    private BDCloudVideoView bdCloudVideoView;
    private List<GoodListEntity> currentShopList;
    private View fragmentCacheView;
    private boolean isLoadMore;
    private boolean isXiabo;
    private ImageView ivPlayIcon;
    private String liveTypeText;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private LinearLayout llEmpty;
    private LinearLayout llPayInfo;
    private LinearLayout llPayTipe;
    private LinearLayout llTrySeeDialog;
    private AudienceAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private RecyclerView mRecyclerView;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private Disposable payInfoCountDown;
    private View roomInfo;
    private boolean slowLiveNotData;
    private SmartSwipeRefresh smartSwipeRefresh;
    private int timePayInfo;
    private TextView tvFreeTimeTips;
    private TextView tvNoOrder;
    private TextView tvOrder;
    private TextView tvPayTips;
    private String userId;
    private String pageNum = "0";
    private int mCurrentItem = 0;
    private List<ListDataEntity> list = new ArrayList();
    private Handler closer = new Handler() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment.4
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (SlowLiveFragment.this.smartSwipeRefresh == null || SlowLiveFragment.this.smartSwipeRefresh.getSwipeConsumer() == null) {
                return;
            }
            SlowLiveFragment.this.smartSwipeRefresh.getSwipeConsumer().smoothClose();
        }
    };

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRoomInfo$14(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRoomInfo$15(Boolean bool) throws Exception {
    }

    public void dismissLoading() {
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment");
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    public void showLoading() {
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
        this.activityContext = (AudienceMainActivity) context;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131492973, viewGroup, false);
        GlideApp.with(this).load((Integer) 2131232734).into((ImageView) inflate.findViewById(2131297427));
        ((TextView) inflate.findViewById(2131298933)).setText("服务器开小差，没有请求到数据。");
        this.llEmpty = (LinearLayout) inflate.findViewById(2131297711);
        this.llEmpty.setVisibility(8);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(2131296463);
        this.mLayoutManager = new ViewPagerLayoutManager(this.activityContext, 1);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new AudienceAdapter(this.list, this.activityContext);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mLayoutManager.setOnViewPagerListener(new C83471());
        this.fragmentCacheView = inflate;
        initLoadMore();
        this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment");
        return view3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83471 implements OnViewPagerListener {
        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageRelease(boolean z, int i) {
        }

        C83471() {
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageSelected(int i, boolean z) {
            UIUtils.logD("test", "liveFposition=" + i);
            if (SlowLiveFragment.this.mCurrentItem != i) {
                if (!SlowLiveFragment.this.isXiabo) {
                    SlowLiveFragment.this.mCurrentItem = i;
                } else {
                    SlowLiveFragment slowLiveFragment = SlowLiveFragment.this;
                    slowLiveFragment.filerList(slowLiveFragment.mCurrentItem);
                }
                SlowLiveFragment.this.mRecyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$1$Etpy08DxZZNPoM1yAccEoH6OcsU
                    @Override // java.lang.Runnable
                    public final void run() {
                        SlowLiveFragment.C83471.lambda$onPageSelected$0(SlowLiveFragment.C83471.this);
                    }
                }, 800L);
            }
            if (SlowLiveFragment.this.pageNum.equals("-1") || !z || SlowLiveFragment.this.isLoadMore) {
                return;
            }
            SlowLiveFragment.this.isLoadMore = true;
            SlowLiveFragment.this.loadData();
        }

        public static /* synthetic */ void lambda$onPageSelected$0(C83471 c83471) {
            SlowLiveFragment slowLiveFragment = SlowLiveFragment.this;
            slowLiveFragment.startPlay(slowLiveFragment.mCurrentItem);
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onLayoutComplete() {
            UIUtils.logD("test", "onLayoutComplete");
        }
    }

    private void initLoadMore() {
        this.smartSwipeRefresh = SmartSwipeRefresh.translateMode(this.mRecyclerView, false, false).setDataLoader(new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment.2
            @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
            public void onRefresh(SmartSwipeRefresh smartSwipeRefresh) {
            }

            @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
            public void onLoadMore(SmartSwipeRefresh smartSwipeRefresh) {
                MsLogUtil.m7980d("加载下一页数据");
                if (smartSwipeRefresh != null) {
                    smartSwipeRefresh.finished(true);
                }
            }
        }).disableRefresh().setFooter(new LiveNoMoreDataFooter(this.activityContext));
        ((SlidingConsumer) this.smartSwipeRefresh.getSwipeConsumer().m17389as(SlidingConsumer.class)).addListener(new SimpleSwipeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment.3
            @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
            public void onSwipeProcess(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, boolean z, float f) {
                if (f != 1.5d || SlowLiveFragment.this.closer == null) {
                    return;
                }
                SlowLiveFragment.this.closer.sendEmptyMessageDelayed(0, 1200L);
            }
        });
    }

    private void getSlowLive() {
        this.managerAudienceLoadData.getSlowLiveList(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$nFoAgzLYRwKC-RSC_1bjsqUW1Nk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$getSlowLive$0(SlowLiveFragment.this, (AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$MXS6rLxid_-gVm5E6LIDq22_K9Y
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$getSlowLive$1(SlowLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getSlowLive$0(SlowLiveFragment slowLiveFragment, AudienceDataEntity audienceDataEntity) throws Exception {
        if ("0000".equals(audienceDataEntity.getStatusCode())) {
            slowLiveFragment.parseLiveList(audienceDataEntity);
        } else {
            slowLiveFragment.getCommonLive();
        }
    }

    public static /* synthetic */ void lambda$getSlowLive$1(SlowLiveFragment slowLiveFragment, Throwable th) throws Exception {
        MsLogUtil.m7979d(TAG, th.getMessage());
        slowLiveFragment.getCommonLive();
    }

    private void getCommonLive() {
        this.slowLiveNotData = true;
        ToastUtil.showToast("当前无数据，已为您推荐其他热门直播");
        this.llEmpty.setVisibility(0);
        this.managerAudienceLoadData.loadListData(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$Y2AzyRD1RFFIuyh5YGPU7QozZRs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.this.parseLiveList((AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$Z4jKpgcaez_xuDSgR5ywhhbCU44
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$getCommonLive$3(SlowLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getCommonLive$3(SlowLiveFragment slowLiveFragment, Throwable th) throws Exception {
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
        slowLiveFragment.llEmpty.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void parseLiveList(AudienceDataEntity audienceDataEntity) {
        if (audienceDataEntity != null && "0000".equals(audienceDataEntity.getStatusCode())) {
            this.llEmpty.setVisibility(8);
            boolean equals = "0".equals(this.pageNum);
            this.pageNum = audienceDataEntity.getNextPageNum();
            this.smartSwipeRefresh.finished(true);
            this.smartSwipeRefresh.setNoMoreData("-1".equals(this.pageNum));
            if (TextUtils.isEmpty(this.userId)) {
                if (this.isLoadMore) {
                    this.mAdapter.loadMore(audienceDataEntity.getList());
                    this.isLoadMore = false;
                    return;
                }
                this.list = audienceDataEntity.getList();
                for (ListDataEntity listDataEntity : this.list) {
                    UIUtils.logD("ListDataEntity", listDataEntity.getJobNumber());
                }
            } else {
                ListDataEntity listDataEntity2 = null;
                for (ListDataEntity listDataEntity3 : audienceDataEntity.getList()) {
                    UIUtils.logD("ListDataEntity", listDataEntity3.getJobNumber());
                    if (listDataEntity3.getJobNumber().trim().equals(this.userId.trim())) {
                        listDataEntity2 = listDataEntity3;
                    }
                }
                if (this.isLoadMore) {
                    if (listDataEntity2 != null) {
                        audienceDataEntity.getList().remove(listDataEntity2);
                    }
                    this.mAdapter.loadMore(audienceDataEntity.getList());
                    this.isLoadMore = false;
                    return;
                }
                this.list = audienceDataEntity.getList();
                if (listDataEntity2 == null) {
                    listDataEntity2 = new ListDataEntity();
                    listDataEntity2.setJobNumber(this.userId);
                } else if (this.list.size() > 0) {
                    this.list.remove(listDataEntity2);
                }
                if (equals) {
                    this.list.add(0, listDataEntity2);
                }
            }
            this.mAdapter.updateList(audienceDataEntity.getList());
            this.mRecyclerView.scrollToPosition(this.mCurrentItem);
            startPlay(this.mCurrentItem);
            return;
        }
        UIUtils.toast("加载失败，请稍后再试");
        this.llEmpty.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        if (this.managerAudienceLoadData == null) {
            if (this.activityContext == null) {
                this.activityContext = (AudienceMainActivity) getActivity();
            }
            this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
        }
        if (this.slowLiveNotData) {
            getCommonLive();
        } else {
            getSlowLive();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    public long getFragmentId() {
        char[] charArray = TAG.toCharArray();
        StringBuffer stringBuffer = new StringBuffer();
        for (char c : charArray) {
            stringBuffer.append((int) c);
        }
        long parseLong = Long.parseLong(stringBuffer.toString());
        MsLogUtil.m7979d(TAG, "转换后：" + parseLong);
        return parseLong;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    void lazyLoad() {
        playLive();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    public void clearData() {
        AudienceAdapter audienceAdapter = this.mAdapter;
        if (audienceAdapter != null) {
            audienceAdapter.updateList(new ArrayList());
        }
    }

    public void playLive() {
        this.slowLiveNotData = false;
        this.pageNum = "0";
        loadData();
    }

    public void playLive(String str) {
        uploadLog("");
        this.pageNum = str;
        this.mCurrentItem = 0;
        playLive();
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        Exception e;
        ArrayList arrayList;
        super.onActivityResult(i, i2, intent);
        if (i != 1001 || i2 != 2001 || intent == null) {
            if (i == 4001) {
                startPlay(this.mCurrentItem);
                return;
            }
            return;
        }
        this.mCurrentItem = intent.getIntExtra("indexForLive", this.mCurrentItem);
        String stringExtra = intent.getStringExtra("liveList");
        if (!TextUtils.isEmpty(stringExtra)) {
            try {
                JSONArray jSONArray = new JSONArray(stringExtra);
                arrayList = new ArrayList();
                for (int i3 = 0; i3 < jSONArray.length(); i3++) {
                    try {
                        ListDataEntity listDataEntity = new ListDataEntity();
                        JSONObject optJSONObject = jSONArray.optJSONObject(i3);
                        listDataEntity.setCoverImg(optJSONObject.optString("coverImg"));
                        listDataEntity.setDataType(optJSONObject.optString("dataType"));
                        listDataEntity.setJobNumber(optJSONObject.optString("jobNumber"));
                        listDataEntity.setLivePvUrl(optJSONObject.optString("livePvUrl"));
                        arrayList.add(listDataEntity);
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        if (arrayList != null) {
                            this.list.clear();
                            this.list.addAll(arrayList);
                            this.mAdapter.updateList(this.list);
                        }
                        this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                        startPlay(this.mCurrentItem);
                    }
                }
            } catch (Exception e3) {
                e = e3;
                arrayList = null;
            }
            if (arrayList != null && arrayList.size() > 0) {
                this.list.clear();
                this.list.addAll(arrayList);
                this.mAdapter.updateList(this.list);
            }
        } else {
            this.mCurrentItem = 0;
        }
        this.mRecyclerView.scrollToPosition(this.mCurrentItem);
        startPlay(this.mCurrentItem);
    }

    private boolean isCurrentRoom(String str) {
        String jobNumber = this.list.get(this.mCurrentItem).getJobNumber();
        UIUtils.logD("fufeizhibo", "roomId=" + str + "|currentRoomId=" + jobNumber);
        return str.equals(jobNumber);
    }

    private void getSharpnessInfo(final int i) {
        final String jobNumber = this.list.get(i).getJobNumber();
        this.managerAudienceLoadData.getSharpnessInfo(jobNumber).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$RMTNHuTkRLVyS_O1tIfGlwf_gl4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$getSharpnessInfo$5(SlowLiveFragment.this, jobNumber, i, (SharpnessEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getSharpnessInfo$5(final SlowLiveFragment slowLiveFragment, String str, final int i, SharpnessEntity sharpnessEntity) throws Exception {
        if ("0000".equals(sharpnessEntity.getStatusCode()) && slowLiveFragment.isCurrentRoom(str)) {
            if ("Y".equals(sharpnessEntity.getLiveViewAngleMore())) {
                slowLiveFragment.liveTypeText = "正在直播-多视角";
                View findViewByPosition = slowLiveFragment.mLayoutManager.findViewByPosition(i);
                if (findViewByPosition != null) {
                    ((TextView) findViewByPosition.findViewById(2131299146)).setText("正在直播-多视角");
                }
            }
            AudienceMainActivity.isNeedPay = false;
            if ("Y".equals(sharpnessEntity.getPaidLive()) && !"Y".equals(sharpnessEntity.getPayingUser())) {
                slowLiveFragment.initPayInfo(sharpnessEntity.getPaidLinks(), sharpnessEntity.getPaidAd());
                slowLiveFragment.clearPayInfo();
                try {
                    slowLiveFragment.timePayInfo = Integer.valueOf(sharpnessEntity.getFreeTime()).intValue();
                } catch (Exception unused) {
                }
                if (slowLiveFragment.timePayInfo < 10) {
                    slowLiveFragment.showPayInfo();
                } else {
                    slowLiveFragment.llPayInfo.setVisibility(0);
                    slowLiveFragment.llPayTipe.setVisibility(0);
                    TextView textView = slowLiveFragment.tvFreeTimeTips;
                    textView.setText("可试看" + slowLiveFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
                    slowLiveFragment.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$BrFpkSeeDgrcSPv99kTosZyE4MY
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            SlowLiveFragment.lambda$getSharpnessInfo$4(SlowLiveFragment.this, i, (Long) obj);
                        }
                    });
                }
            }
            List<SharpnessEntity.LiveUrlBean> list = slowLiveFragment.liveUrlList;
            if (list == null) {
                slowLiveFragment.liveUrlList = sharpnessEntity.getData();
            } else {
                list.clear();
                slowLiveFragment.liveUrlList.addAll(sharpnessEntity.getData());
            }
            if (sharpnessEntity.getData() == null || sharpnessEntity.getData().size() <= 0) {
                return;
            }
            if (sharpnessEntity.getData().size() > 2) {
                slowLiveFragment.setPlayerUrl(sharpnessEntity.getData().get(1));
            } else {
                slowLiveFragment.setPlayerUrl(sharpnessEntity.getData().get(0));
            }
        }
    }

    public static /* synthetic */ void lambda$getSharpnessInfo$4(SlowLiveFragment slowLiveFragment, int i, Long l) throws Exception {
        slowLiveFragment.timePayInfo--;
        if (slowLiveFragment.timePayInfo % 10 == 0) {
            slowLiveFragment.consumeFreeTime(slowLiveFragment.list.get(i).getJobNumber());
        }
        if (slowLiveFragment.timePayInfo == 0) {
            slowLiveFragment.showPayInfo();
            return;
        }
        slowLiveFragment.tvFreeTimeTips.setText("可试看" + slowLiveFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
    }

    private void consumeFreeTime(String str) {
        UIUtils.logD("LiveFragment", "扣减时间==" + str);
        this.managerAudienceLoadData.consumeFreeTime(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$xWwKs5GcDPFkSlcVFXpcvcXAFZw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("LiveFragment", ((String) obj) + "===========");
            }
        });
    }

    private void clearPayInfo() {
        Disposable disposable = this.payInfoCountDown;
        if (disposable != null) {
            disposable.dispose();
            this.payInfoCountDown = null;
        }
        this.timePayInfo = 0;
        LinearLayout linearLayout = this.llPayInfo;
        if (linearLayout != null) {
            linearLayout.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPayInfo() {
        clearPayInfo();
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(8);
        this.llTrySeeDialog.setVisibility(0);
        AudienceMainActivity.isNeedPay = true;
        this.bdCloudVideoView.stopPlayback();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissPayInfo() {
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
        this.ivPlayIcon.setVisibility(0);
    }

    private void initPayInfo(final String str, String str2) {
        this.llPayInfo = (LinearLayout) this.roomInfo.findViewById(2131297753);
        this.llPayTipe = (LinearLayout) this.roomInfo.findViewById(2131297754);
        this.tvFreeTimeTips = (TextView) this.roomInfo.findViewById(2131298945);
        this.ivPlayIcon = (ImageView) this.roomInfo.findViewById(2131297450);
        this.llTrySeeDialog = (LinearLayout) this.roomInfo.findViewById(2131297789);
        this.tvPayTips = (TextView) this.roomInfo.findViewById(2131299043);
        this.tvPayTips.setText(str2);
        this.tvNoOrder = (TextView) this.roomInfo.findViewById(2131299028);
        this.tvOrder = (TextView) this.roomInfo.findViewById(2131299036);
        this.llPayInfo.setVisibility(8);
        this.llPayTipe.setVisibility(8);
        this.ivPlayIcon.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
        this.ivPlayIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$8966lPJsSJzCJgDf9r5nBaiFdr8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SlowLiveFragment.this.showPayInfo();
            }
        });
        this.tvNoOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$XSCHT2tsl0RRnesZAtYkzSfIVB8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SlowLiveFragment.this.dismissPayInfo();
            }
        });
        this.tvOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$2Wm-Qd1ig-B0YPYzFcPtJVfxpzw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SlowLiveFragment.lambda$initPayInfo$9(SlowLiveFragment.this, str, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initPayInfo$9(SlowLiveFragment slowLiveFragment, String str, View view) {
        Intent intent = new Intent(slowLiveFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        slowLiveFragment.startActivityForResult(intent, 4001);
    }

    private void setPlayerUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        UIUtils.logD("lln", "setPlayerUrl");
        showBgImg();
        this.bdCloudVideoView = this.activityContext.playVideo(getSwitchUrl(liveUrlBean));
        setVideoSizeView();
    }

    private String getSwitchUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        if (liveUrlBean == null) {
            return "";
        }
        return !"Wifi".equals(DeviceHelper.getNETType(this.activityContext)) ? liveUrlBean.getLiveFreePullUrlByFlv() : liveUrlBean.getLivePullUrlByFlv();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlay(int i) {
        leaveRoom();
        ListDataEntity listDataEntity = this.list.get(i);
        String dataType = listDataEntity.getDataType();
        if ("2".equals(dataType)) {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            findViewByPosition.findViewById(2131296393).setVisibility(0);
            this.bdCloudVideoView = this.activityContext.playVideo(listDataEntity.getLivePvUrl());
            setVideoSizeView();
        }
        getRoomInfo(listDataEntity.getJobNumber(), dataType, "MZB".equals(listDataEntity.getTypeCode()));
    }

    public void setVideoSizeView() {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView == null) {
            return;
        }
        bDCloudVideoView.setVideoScalingMode(2);
        View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
        if (findViewByPosition == null) {
            return;
        }
        FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
        if (this.bdCloudVideoView.getVideoWidth() > this.bdCloudVideoView.getVideoHeight()) {
            this.bdCloudVideoView.setVideoScalingMode(1);
            frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
        }
        ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        frameLayout.addView(this.bdCloudVideoView);
    }

    private void getRoomInfo(final String str, final String str2, final boolean z) {
        this.managerAudienceLoadData.loadZhuboData(str, "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$Z-BNqN7HawkdEafM752Bqe5RjbI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$getRoomInfo$26(SlowLiveFragment.this, str, str2, z, (ZhuboDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$bBckIoHFLyOngIB-qBUDTPRyd-c
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$getRoomInfo$27(SlowLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$26(final SlowLiveFragment slowLiveFragment, String str, final String str2, boolean z, final ZhuboDataEntity zhuboDataEntity) throws Exception {
        if ("0000".equals(zhuboDataEntity.getStatusCode())) {
            if (!TextUtils.isEmpty(slowLiveFragment.userId) && str.equals(slowLiveFragment.userId) && "N".equals(zhuboDataEntity.getAnchorInfoBean().getLiveStatus())) {
                BDCloudVideoView bDCloudVideoView = slowLiveFragment.bdCloudVideoView;
                if (bDCloudVideoView != null) {
                    bDCloudVideoView.stopPlayback();
                    slowLiveFragment.bdCloudVideoView.reSetRender();
                }
                Intent intent = new Intent(slowLiveFragment.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(zhuboDataEntity.getAnchorInfoBean().getLiveHistoryUrl(slowLiveFragment.userId));
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                slowLiveFragment.activityContext.startActivityForResult(intent, 4000);
                slowLiveFragment.activityContext.finish();
                return;
            }
            final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
            slowLiveFragment.roomInfo = LayoutInflater.from(slowLiveFragment.activityContext).inflate(2131493228, (ViewGroup) null);
            GlideApp.with((FragmentActivity) slowLiveFragment.activityContext).asGif().load((Integer) 2131232744).into((ImageView) slowLiveFragment.roomInfo.findViewById(2131297281));
            slowLiveFragment.roomInfo.findViewById(2131297726).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$D1XZ6i94IgpnZrzj7hCG7_3O-Wg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SlowLiveFragment.lambda$getRoomInfo$10(SlowLiveFragment.this, anchorInfoBean, zhuboDataEntity, view);
                }
            });
            GlideApp.with((FragmentActivity) slowLiveFragment.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into((ImageView) slowLiveFragment.roomInfo.findViewById(2131297332));
            ((TextView) slowLiveFragment.roomInfo.findViewById(2131298863)).setText(anchorInfoBean.getUserName());
            ((TextView) slowLiveFragment.roomInfo.findViewById(2131299144)).setText(anchorInfoBean.getLiveTitle());
            slowLiveFragment.roomInfo.findViewById(2131299144).setVisibility(0);
            slowLiveFragment.roomInfo.findViewById(2131297685).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$NbE_BrVD-TKf59ijdKSvFDV3NZc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SlowLiveFragment.lambda$getRoomInfo$11(SlowLiveFragment.this, anchorInfoBean, str2, zhuboDataEntity, view);
                }
            });
            slowLiveFragment.roomInfo.findViewById(2131297390).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$dueQuCcuaQDergwM0dxK7Ni-ork
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.managerAudienceLoadData.guanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$y6pbHP7YXHNG4dgBHG77QootZfc
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            SlowLiveFragment.lambda$getRoomInfo$12(SlowLiveFragment.this, (String) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
            });
            slowLiveFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str2) ? 8 : 0);
            TextView textView = (TextView) slowLiveFragment.roomInfo.findViewById(2131299146);
            StringBuffer stringBuffer = new StringBuffer("正在直播");
            if (z) {
                stringBuffer.append("-慢直播");
            } else if (!TextUtils.isEmpty(slowLiveFragment.liveTypeText)) {
                stringBuffer.append("-多视角");
            }
            textView.setText(stringBuffer.toString());
            View findViewByPosition = slowLiveFragment.mLayoutManager.findViewByPosition(slowLiveFragment.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            String liveCoverImg = anchorInfoBean.getLiveCoverImg();
            if (!TextUtils.isEmpty(liveCoverImg)) {
                slowLiveFragment.list.get(slowLiveFragment.mCurrentItem).setCoverImg(liveCoverImg);
            }
            GlideApp.with((FragmentActivity) slowLiveFragment.activityContext).load(liveCoverImg).into((ImageView) findViewByPosition.findViewById(2131296393));
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296434);
            frameLayout.removeAllViews();
            frameLayout.addView(slowLiveFragment.roomInfo);
            slowLiveFragment.managerAudienceLoadData.addRenqi(slowLiveFragment.list.get(slowLiveFragment.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$0ZAHLWQ4BEgnWNKCQCyMFMUCzR4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$14((String) obj);
                }
            });
            slowLiveFragment.managerAudienceLoadData.loadShopData(slowLiveFragment.list.get(slowLiveFragment.mCurrentItem).getJobNumber(), "Y").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$RMODksVNXFnUmhs34blMhZvkERQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$17(SlowLiveFragment.this, anchorInfoBean, zhuboDataEntity, (ShopEntity) obj);
                }
            });
            slowLiveFragment.managerAudienceLoadData.loadShopList(slowLiveFragment.list.get(slowLiveFragment.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$tw_l6-ZuvAiayLS9wI2qlAM374M
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$19(SlowLiveFragment.this, anchorInfoBean, zhuboDataEntity, (List) obj);
                }
            });
            slowLiveFragment.managerAudienceLoadData.isGuanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$ljZr9YauPTuhtkRzi2omN_utma4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$20(SlowLiveFragment.this, (Boolean) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$0XXsm6BizE1vA-B0QV4jOsebVPY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$21(SlowLiveFragment.this, (Throwable) obj);
                }
            });
            if ("2".equals(str2)) {
                slowLiveFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                slowLiveFragment.roomInfo.findViewById(2131297802).setVisibility(0);
                slowLiveFragment.managerAudienceLoadData.reserveLivePv("2", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$44u6mLp3SX-8dPR9a_78908G0Dc
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        r0.managerAudienceLoadData.getLivePvInfo(r1.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$7T07JEKkk2-ujnBDJUMXg8TeuIk
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj2) {
                                SlowLiveFragment.lambda$getRoomInfo$24(SlowLiveFragment.this, r2, r3, (LivePvInfoEntity) obj2);
                            }
                        });
                    }
                });
            } else {
                slowLiveFragment.roomInfo.findViewById(2131298391).setVisibility(0);
                slowLiveFragment.roomInfo.findViewById(2131297802).setVisibility(8);
                String forcePassword = anchorInfoBean.getForcePassword();
                String isNeedCheck = anchorInfoBean.getIsNeedCheck();
                if ("Y".equals(forcePassword) && "Y".equals(isNeedCheck)) {
                    View findViewById = slowLiveFragment.roomInfo.findViewById(2131297752);
                    findViewById.setVisibility(8);
                    findViewById.setVisibility(0);
                    ((TextView) findViewById.findViewById(2131299040)).setText("当前为加密直播间");
                    AudienceMainActivity.isLock = true;
                } else {
                    slowLiveFragment.getSharpnessInfo(slowLiveFragment.mCurrentItem);
                }
            }
            slowLiveFragment.roomInfo.findViewById(2131298355).setVisibility(0);
            slowLiveFragment.roomInfo.setVisibility(0);
            return;
        }
        UIUtils.logD("test", "数据返回异常-->code=" + zhuboDataEntity.getStatusCode() + "|" + zhuboDataEntity.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(zhuboDataEntity.getMessage());
        }
        slowLiveFragment.list.get(slowLiveFragment.mCurrentItem).setLiving("N");
        slowLiveFragment.mAdapter.updateList(slowLiveFragment.list);
    }

    public static /* synthetic */ void lambda$getRoomInfo$10(SlowLiveFragment slowLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ZhuboDataEntity zhuboDataEntity, View view) {
        slowLiveFragment.jumpLiveInfo();
        if (anchorInfoBean != null) {
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "点击进入直播间", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "慢直播");
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$11(SlowLiveFragment slowLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str, ZhuboDataEntity zhuboDataEntity, View view) {
        try {
            String userIndexUrl = anchorInfoBean.getUserIndexUrl();
            if (!TextUtils.isEmpty(userIndexUrl)) {
                Intent intent = new Intent(slowLiveFragment.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(slowLiveFragment.addChannel(userIndexUrl, "直播"));
                webParamsEntity.setNeedTitle(true);
                if (!"2".equals(str)) {
                    webParamsEntity.setType("audienceMain");
                }
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                slowLiveFragment.startActivityForResult(intent, 4001);
            }
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "头像", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "慢直播");
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$12(SlowLiveFragment slowLiveFragment, String str) throws Exception {
        if ("0000".equals(str)) {
            UIUtils.toastCenter("关注成功");
            slowLiveFragment.roomInfo.findViewById(2131297390).setVisibility(8);
            return;
        }
        UIUtils.toastCenter(str);
    }

    public static /* synthetic */ void lambda$getRoomInfo$17(final SlowLiveFragment slowLiveFragment, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final ZhuboDataEntity zhuboDataEntity, ShopEntity shopEntity) throws Exception {
        ShopEntity.DataBean data;
        if (!"0000".equals(shopEntity.getStatusCode()) || (data = shopEntity.getData()) == null) {
            return;
        }
        try {
            ((TextView) slowLiveFragment.roomInfo.findViewById(2131298862)).setText(FormatUtils.getShowString(data.getRoundPraise()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("Y".equalsIgnoreCase(data.getHaveGoods())) {
            slowLiveFragment.roomInfo.findViewById(2131296461).setVisibility(0);
            slowLiveFragment.roomInfo.findViewById(2131298338).setVisibility(0);
            final ShopEntity.DataBean.GoodsBean goods = data.getGoods();
            GlideApp.with((FragmentActivity) slowLiveFragment.activityContext).load(goods.getCoverImgUrl()).into((ImageView) slowLiveFragment.roomInfo.findViewById(2131296407));
            ((TextView) slowLiveFragment.roomInfo.findViewById(2131296419)).setText(goods.getName());
            ((TextView) slowLiveFragment.roomInfo.findViewById(2131296406)).setText(goods.getDesc());
            ((TextView) slowLiveFragment.roomInfo.findViewById(2131296418)).setText(TextUtils.isEmpty(goods.getPrice()) ? goods.getOriginalPrice() : goods.getPrice());
            TextView textView = (TextView) slowLiveFragment.roomInfo.findViewById(2131296417);
            if (TextUtils.isEmpty(goods.getOriginalPrice())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.getPaint().setFlags(16);
            }
            slowLiveFragment.roomInfo.findViewById(2131296461).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$8rhZuNRoSEcscN_b3LisGAdmgjY
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SlowLiveFragment.lambda$getRoomInfo$16(SlowLiveFragment.this, anchorInfoBean, goods, zhuboDataEntity, view);
                }
            });
            return;
        }
        slowLiveFragment.roomInfo.findViewById(2131296461).setVisibility(8);
        slowLiveFragment.roomInfo.findViewById(2131298338).setVisibility(8);
    }

    public static /* synthetic */ void lambda$getRoomInfo$16(SlowLiveFragment slowLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ShopEntity.DataBean.GoodsBean goodsBean, ZhuboDataEntity zhuboDataEntity, View view) {
        if (!TextUtils.isEmpty("0")) {
            slowLiveFragment.managerAudienceLoadData.goodsLog(anchorInfoBean.getUserId(), goodsBean.getId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$r1J5ywKEtPoz-WR9WhEmCMAlM5w
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$15((Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "商品卡片", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "慢直播");
        }
        Intent intent = new Intent(slowLiveFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(goodsBean.getGoodsUrl());
        webParamsEntity.setType("audienceMain");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        slowLiveFragment.startActivityForResult(intent, 4001);
    }

    public static /* synthetic */ void lambda$getRoomInfo$19(final SlowLiveFragment slowLiveFragment, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final ZhuboDataEntity zhuboDataEntity, List list) throws Exception {
        slowLiveFragment.currentShopList = list;
        ((TextView) slowLiveFragment.roomInfo.findViewById(2131298965)).setText("" + list.size());
        slowLiveFragment.roomInfo.findViewById(2131297720).setVisibility(0);
        slowLiveFragment.roomInfo.findViewById(2131298338).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$deL_RpEdR5bmdlsbSM2khUR30a0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SlowLiveFragment.lambda$getRoomInfo$18(SlowLiveFragment.this, anchorInfoBean, zhuboDataEntity, view);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$18(SlowLiveFragment slowLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ZhuboDataEntity zhuboDataEntity, View view) {
        AudienceGoodsDialog.show(slowLiveFragment.activityContext, slowLiveFragment.currentShopList, new OnDialogBtnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment.5
            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickGoodsLook(GoodListEntity goodListEntity) {
                Intent intent = new Intent(SlowLiveFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(goodListEntity.getGoodsUrl());
                webParamsEntity.setType("audienceMain");
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                SlowLiveFragment.this.startActivityForResult(intent, 4001);
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickOrder() {
                IntentManager.gotoWebViewActivity(SlowLiveFragment.this.activityContext, URLSet.liveOrderMenu(), "我的订单");
            }
        });
        PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "购物车", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "慢直播");
    }

    public static /* synthetic */ void lambda$getRoomInfo$20(SlowLiveFragment slowLiveFragment, Boolean bool) throws Exception {
        slowLiveFragment.roomInfo.findViewById(2131297390).setVisibility(bool.booleanValue() ? 8 : 0);
        slowLiveFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$21(SlowLiveFragment slowLiveFragment, Throwable th) throws Exception {
        slowLiveFragment.roomInfo.findViewById(2131297390).setVisibility(0);
        slowLiveFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$24(final SlowLiveFragment slowLiveFragment, LivePvInfoEntity livePvInfoEntity, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, LivePvInfoEntity livePvInfoEntity2) throws Exception {
        if ("0000".equals(livePvInfoEntity2.getStatusCode())) {
            LivePvInfoEntity.InfoEntity data = livePvInfoEntity2.getData();
            final TextView textView = (TextView) slowLiveFragment.roomInfo.findViewById(2131299154);
            final TextView textView2 = (TextView) slowLiveFragment.roomInfo.findViewById(2131299155);
            textView2.setText(data != null ? data.getPrevueTime() : "该主播未发布预告");
            String statusCode = livePvInfoEntity.getStatusCode();
            char c = 65535;
            switch (statusCode.hashCode()) {
                case 1567967:
                    if (statusCode.equals("3101")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1567968:
                    if (statusCode.equals("3102")) {
                        c = 1;
                        break;
                    }
                    break;
                case 1567969:
                    if (statusCode.equals("3103")) {
                        c = 2;
                        break;
                    }
                    break;
                case 1567970:
                    if (statusCode.equals("3104")) {
                        c = 3;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    textView.setText("已预约");
                    break;
                case 1:
                    textView.setText("预约");
                    break;
                case 2:
                case 3:
                    textView.setVisibility(8);
                    break;
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$QTCZgheCoujtpdZqWu9kU2rPKMA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SlowLiveFragment.lambda$getRoomInfo$23(SlowLiveFragment.this, textView, anchorInfoBean, textView2, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$23(final SlowLiveFragment slowLiveFragment, final TextView textView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final TextView textView2, View view) {
        if ("预约".equals(textView.getText())) {
            slowLiveFragment.managerAudienceLoadData.reserveLivePv("1", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$hNZYL9y7PGdmqt30E-4h8EFumyA
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SlowLiveFragment.lambda$getRoomInfo$22(SlowLiveFragment.this, textView2, textView, (LivePvInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$22(SlowLiveFragment slowLiveFragment, TextView textView, TextView textView2, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            LivePvResultDialog livePvResultDialog = new LivePvResultDialog(slowLiveFragment.activityContext, textView.getText().toString().replace(" 开播", ""));
            textView2.setText("已预约");
            livePvResultDialog.show();
            return;
        }
        textView2.setText("预约");
        ToastUtil.showToast("出了一点儿小问题，请稍后再试");
    }

    public static /* synthetic */ void lambda$getRoomInfo$27(SlowLiveFragment slowLiveFragment, Throwable th) throws Exception {
        UIUtils.logD("test", "主播接口信息异常-->" + th.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(th.getMessage());
        }
        slowLiveFragment.list.get(slowLiveFragment.mCurrentItem).setLiving("N");
        slowLiveFragment.mAdapter.updateList(slowLiveFragment.list);
    }

    private String addChannel(String str, String str2) {
        StringBuffer stringBuffer = new StringBuffer(str);
        if (str.contains("?")) {
            stringBuffer.append("&channel=");
            stringBuffer.append(str2);
        } else {
            stringBuffer.append("?channel=");
            stringBuffer.append(str2);
        }
        return stringBuffer.toString();
    }

    public void dismissImg() {
        try {
            autoJionLiveInfo();
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            findViewByPosition.findViewById(2131296393).setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBgImg() {
        View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
        if (findViewByPosition != null) {
            findViewByPosition.findViewById(2131296393).setVisibility(0);
        }
    }

    private String getLiveList() {
        String str = System.currentTimeMillis() + "json";
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        Gson gson = new Gson();
        List<ListDataEntity> list = this.list;
        sharePreferenceUtil.putString(AudienceActivity.JSON_LIST_STR_FILE, str, !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list));
        return str;
    }

    private void leaveRoom() {
        ViewGroup viewGroup;
        this.liveTypeText = "";
        View view = this.roomInfo;
        if (view != null && (viewGroup = (ViewGroup) view.getParent()) != null) {
            viewGroup.removeAllViews();
        }
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.stopPlayback();
        }
        showBgImg();
        stopJumpInfo();
        clearPayInfo();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void filerList(int i) {
        this.isXiabo = false;
        Iterator<ListDataEntity> it = this.list.iterator();
        while (it.hasNext()) {
            ListDataEntity next = it.next();
            if ("N".equals(next.getLiving()) && "1".equals(next.getDataType())) {
                it.remove();
            }
        }
        this.mAdapter.notifyItemRemoved(i);
    }

    public void autoJionLiveInfo() {
        stopJumpInfo();
        this.autoJion = Observable.timer(30000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SlowLiveFragment$ByjrTUo0PkjzoqAGP_pF8_3FwNY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SlowLiveFragment.lambda$autoJionLiveInfo$28(SlowLiveFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$autoJionLiveInfo$28(SlowLiveFragment slowLiveFragment, Long l) throws Exception {
        slowLiveFragment.jumpLiveInfo();
        slowLiveFragment.autoJion.dispose();
        slowLiveFragment.autoJion = null;
    }

    private void jumpLiveInfo() {
        Intent intent = new Intent(getActivity(), AudienceActivity.class);
        intent.putExtra("listStr", getLiveList());
        intent.putExtra("liveType", SLOW_LIVE);
        intent.putExtra("from", "fromMain");
        intent.putExtra("pageNum", this.pageNum);
        intent.putExtra("shareUserNumSc", "");
        intent.putExtra("index", this.mCurrentItem + "");
        startActivityForResult(intent, 1001);
    }

    public void stopJumpInfo() {
        Disposable disposable = this.autoJion;
        if (disposable != null) {
            disposable.dispose();
            this.autoJion = null;
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        stopJumpInfo();
        clearPayInfo();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
        stopJumpInfo();
        clearPayInfo();
    }

    private void uploadLog(String str) {
        StatisticsUploadUtils.uploadRealTime2(this.activityContext, "szhibo0001", "直播LIVE", "03", "", "直播预览", "", "", "");
    }
}
