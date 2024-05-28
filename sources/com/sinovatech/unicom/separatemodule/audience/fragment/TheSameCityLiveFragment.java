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
import com.sinovatech.unicom.basic.server.UserManager;
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
import com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment;
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
public class TheSameCityLiveFragment extends BaseFragment {
    public static final String INDEX_FOR_LIVE = "indexForLive";
    public static final int REQUEST_CODE = 1001;
    public static final int RESULT_CODE = 2001;
    private static final String TAG = "TheSameCityLiveFragment";
    public static final String THE_SAME_CITY = "theOneCity";
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
    private Handler closer = new Handler() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment.4
        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            if (TheSameCityLiveFragment.this.smartSwipeRefresh == null || TheSameCityLiveFragment.this.smartSwipeRefresh.getSwipeConsumer() == null) {
                return;
            }
            TheSameCityLiveFragment.this.smartSwipeRefresh.getSwipeConsumer().smoothClose();
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
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment", this);
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment");
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
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment");
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
        this.mLayoutManager.setOnViewPagerListener(new C83741());
        this.fragmentCacheView = inflate;
        initLoadMore();
        this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment");
        return view3;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83741 implements OnViewPagerListener {
        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageRelease(boolean z, int i) {
        }

        C83741() {
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageSelected(int i, boolean z) {
            UIUtils.logD("test", "liveFposition=" + i);
            if (TheSameCityLiveFragment.this.mCurrentItem != i) {
                if (!TheSameCityLiveFragment.this.isXiabo) {
                    TheSameCityLiveFragment.this.mCurrentItem = i;
                } else {
                    TheSameCityLiveFragment theSameCityLiveFragment = TheSameCityLiveFragment.this;
                    theSameCityLiveFragment.filerList(theSameCityLiveFragment.mCurrentItem);
                }
                TheSameCityLiveFragment.this.mRecyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$1$eee5ePD1yad7x04zzgoaMorpGoM
                    @Override // java.lang.Runnable
                    public final void run() {
                        TheSameCityLiveFragment.C83741.lambda$onPageSelected$0(TheSameCityLiveFragment.C83741.this);
                    }
                }, 800L);
            }
            if (TheSameCityLiveFragment.this.pageNum.equals("-1") || !z || TheSameCityLiveFragment.this.isLoadMore) {
                return;
            }
            TheSameCityLiveFragment.this.isLoadMore = true;
            TheSameCityLiveFragment.this.loadData();
        }

        public static /* synthetic */ void lambda$onPageSelected$0(C83741 c83741) {
            TheSameCityLiveFragment theSameCityLiveFragment = TheSameCityLiveFragment.this;
            theSameCityLiveFragment.startPlay(theSameCityLiveFragment.mCurrentItem);
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onLayoutComplete() {
            UIUtils.logD("test", "onLayoutComplete");
        }
    }

    private void initLoadMore() {
        this.smartSwipeRefresh = SmartSwipeRefresh.translateMode(this.mRecyclerView, false, false).setDataLoader(new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment.2
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
        ((SlidingConsumer) this.smartSwipeRefresh.getSwipeConsumer().m17389as(SlidingConsumer.class)).addListener(new SimpleSwipeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment.3
            @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
            public void onSwipeProcess(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, boolean z, float f) {
                if (f != 1.5d || TheSameCityLiveFragment.this.closer == null) {
                    return;
                }
                TheSameCityLiveFragment.this.closer.sendEmptyMessageDelayed(0, 1200L);
            }
        });
    }

    private void getCommonLive() {
        this.slowLiveNotData = true;
        ToastUtil.showToast("当前无数据，已为您推荐其他热门直播");
        this.llEmpty.setVisibility(0);
        this.managerAudienceLoadData.loadListData(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$Ev90ZRG5Ps2z4MoehM2z1SSwU8A
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.this.parseLiveList((AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$e0ELcQ9ki3tMK1tWr5pq7r5mMwg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.lambda$getCommonLive$1(TheSameCityLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getCommonLive$1(TheSameCityLiveFragment theSameCityLiveFragment, Throwable th) throws Exception {
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
        theSameCityLiveFragment.llEmpty.setVisibility(0);
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
        this.managerAudienceLoadData.loadSameCityLiveList(UserManager.getInstance().getLocateCityCode(), this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$VrpYoAZq7uZOwsGR6ODFaJZzliM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.this.parseLiveList((AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$Pv1RaWqQYaNEl1coY1zMqzls-G4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.lambda$loadData$3(TheSameCityLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadData$3(TheSameCityLiveFragment theSameCityLiveFragment, Throwable th) throws Exception {
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
        theSameCityLiveFragment.llEmpty.setVisibility(0);
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
        this.managerAudienceLoadData.getSharpnessInfo(jobNumber).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$WNa7RPSNDQ0F5w_LrGWkF89XGk4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.lambda$getSharpnessInfo$5(TheSameCityLiveFragment.this, jobNumber, i, (SharpnessEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getSharpnessInfo$5(final TheSameCityLiveFragment theSameCityLiveFragment, String str, final int i, SharpnessEntity sharpnessEntity) throws Exception {
        if ("0000".equals(sharpnessEntity.getStatusCode()) && theSameCityLiveFragment.isCurrentRoom(str)) {
            if ("Y".equals(sharpnessEntity.getLiveViewAngleMore())) {
                theSameCityLiveFragment.liveTypeText = "正在直播-多视角";
                View findViewByPosition = theSameCityLiveFragment.mLayoutManager.findViewByPosition(i);
                if (findViewByPosition != null) {
                    ((TextView) findViewByPosition.findViewById(2131299146)).setText("正在直播-多视角");
                }
            }
            AudienceMainActivity.isNeedPay = false;
            if ("Y".equals(sharpnessEntity.getPaidLive()) && !"Y".equals(sharpnessEntity.getPayingUser())) {
                theSameCityLiveFragment.initPayInfo(sharpnessEntity.getPaidLinks(), sharpnessEntity.getPaidAd());
                theSameCityLiveFragment.clearPayInfo();
                try {
                    theSameCityLiveFragment.timePayInfo = Integer.valueOf(sharpnessEntity.getFreeTime()).intValue();
                } catch (Exception unused) {
                }
                if (theSameCityLiveFragment.timePayInfo < 10) {
                    theSameCityLiveFragment.showPayInfo();
                } else {
                    theSameCityLiveFragment.llPayInfo.setVisibility(0);
                    theSameCityLiveFragment.llPayTipe.setVisibility(0);
                    TextView textView = theSameCityLiveFragment.tvFreeTimeTips;
                    textView.setText("可试看" + theSameCityLiveFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
                    theSameCityLiveFragment.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$R2CnzzUW2zpt4Gf2crfzMqZlU5o
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            TheSameCityLiveFragment.lambda$getSharpnessInfo$4(TheSameCityLiveFragment.this, i, (Long) obj);
                        }
                    });
                }
            }
            List<SharpnessEntity.LiveUrlBean> list = theSameCityLiveFragment.liveUrlList;
            if (list == null) {
                theSameCityLiveFragment.liveUrlList = sharpnessEntity.getData();
            } else {
                list.clear();
                theSameCityLiveFragment.liveUrlList.addAll(sharpnessEntity.getData());
            }
            if (sharpnessEntity.getData() == null || sharpnessEntity.getData().size() <= 0) {
                return;
            }
            if (sharpnessEntity.getData().size() > 2) {
                theSameCityLiveFragment.setPlayerUrl(sharpnessEntity.getData().get(1));
            } else {
                theSameCityLiveFragment.setPlayerUrl(sharpnessEntity.getData().get(0));
            }
        }
    }

    public static /* synthetic */ void lambda$getSharpnessInfo$4(TheSameCityLiveFragment theSameCityLiveFragment, int i, Long l) throws Exception {
        theSameCityLiveFragment.timePayInfo--;
        if (theSameCityLiveFragment.timePayInfo % 10 == 0) {
            theSameCityLiveFragment.consumeFreeTime(theSameCityLiveFragment.list.get(i).getJobNumber());
        }
        if (theSameCityLiveFragment.timePayInfo == 0) {
            theSameCityLiveFragment.showPayInfo();
            return;
        }
        theSameCityLiveFragment.tvFreeTimeTips.setText("可试看" + theSameCityLiveFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
    }

    private void consumeFreeTime(String str) {
        UIUtils.logD("LiveFragment", "扣减时间==" + str);
        this.managerAudienceLoadData.consumeFreeTime(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$80vqJHCMmVYdXbPKjrJfrKupagQ
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
        this.ivPlayIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$7PWiiqGwIW4x95RBM0XSgNmyKmc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TheSameCityLiveFragment.this.showPayInfo();
            }
        });
        this.tvNoOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$aAnVU2ZsrKAXKnUqr7skli7kgrg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TheSameCityLiveFragment.this.dismissPayInfo();
            }
        });
        this.tvOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$L23-ms6E6Kt5nYIlaTLOgdCtV2E
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TheSameCityLiveFragment.lambda$initPayInfo$9(TheSameCityLiveFragment.this, str, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initPayInfo$9(TheSameCityLiveFragment theSameCityLiveFragment, String str, View view) {
        Intent intent = new Intent(theSameCityLiveFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        theSameCityLiveFragment.startActivityForResult(intent, 4001);
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
        int videoWidth = this.bdCloudVideoView.getVideoWidth();
        int videoHeight = this.bdCloudVideoView.getVideoHeight();
        MsLogUtil.m7979d("同城", "w:" + videoWidth + "|h:" + videoHeight);
        if (videoWidth > videoHeight) {
            this.bdCloudVideoView.setVideoScalingMode(1);
            frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
            MsLogUtil.m7979d("同城", "横版视频");
        }
        ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        frameLayout.addView(this.bdCloudVideoView);
        MsLogUtil.m7979d("同城", "播放器加入布局");
    }

    private void getRoomInfo(final String str, final String str2, final boolean z) {
        this.managerAudienceLoadData.loadZhuboData(str, "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$ahhlcPGZrUjE4gZC1QTpQc-SqUA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.lambda$getRoomInfo$26(TheSameCityLiveFragment.this, str, str2, z, (ZhuboDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$onrcQhQVRONxCsZeT9UNWvaImkc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.lambda$getRoomInfo$27(TheSameCityLiveFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$26(final TheSameCityLiveFragment theSameCityLiveFragment, String str, final String str2, boolean z, final ZhuboDataEntity zhuboDataEntity) throws Exception {
        if ("0000".equals(zhuboDataEntity.getStatusCode())) {
            if (!TextUtils.isEmpty(theSameCityLiveFragment.userId) && str.equals(theSameCityLiveFragment.userId) && "N".equals(zhuboDataEntity.getAnchorInfoBean().getLiveStatus())) {
                BDCloudVideoView bDCloudVideoView = theSameCityLiveFragment.bdCloudVideoView;
                if (bDCloudVideoView != null) {
                    bDCloudVideoView.stopPlayback();
                    theSameCityLiveFragment.bdCloudVideoView.reSetRender();
                }
                Intent intent = new Intent(theSameCityLiveFragment.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(zhuboDataEntity.getAnchorInfoBean().getLiveHistoryUrl(theSameCityLiveFragment.userId));
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                theSameCityLiveFragment.activityContext.startActivityForResult(intent, 4000);
                theSameCityLiveFragment.activityContext.finish();
                return;
            }
            final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
            theSameCityLiveFragment.roomInfo = LayoutInflater.from(theSameCityLiveFragment.activityContext).inflate(2131493228, (ViewGroup) null);
            GlideApp.with((FragmentActivity) theSameCityLiveFragment.activityContext).asGif().load((Integer) 2131232744).into((ImageView) theSameCityLiveFragment.roomInfo.findViewById(2131297281));
            theSameCityLiveFragment.roomInfo.findViewById(2131297726).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$DgKPkv1Oixza6fpHolQqRsZA4CM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$10(TheSameCityLiveFragment.this, anchorInfoBean, zhuboDataEntity, view);
                }
            });
            GlideApp.with((FragmentActivity) theSameCityLiveFragment.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into((ImageView) theSameCityLiveFragment.roomInfo.findViewById(2131297332));
            ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131298863)).setText(anchorInfoBean.getUserName());
            ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131299144)).setText(anchorInfoBean.getLiveTitle());
            theSameCityLiveFragment.roomInfo.findViewById(2131299144).setVisibility(0);
            theSameCityLiveFragment.roomInfo.findViewById(2131297685).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$Ku5bTx4bJ0RI0PxJ9F4jRySWWAU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$11(TheSameCityLiveFragment.this, anchorInfoBean, str2, zhuboDataEntity, view);
                }
            });
            theSameCityLiveFragment.roomInfo.findViewById(2131297390).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$VPurBhQCLmABGgQPSRbGdZJhXd8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.managerAudienceLoadData.guanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$YvWcv5bvW6XyCuf_FOJiCGpWnUA
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            TheSameCityLiveFragment.lambda$getRoomInfo$12(TheSameCityLiveFragment.this, (String) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
            });
            theSameCityLiveFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str2) ? 8 : 0);
            TextView textView = (TextView) theSameCityLiveFragment.roomInfo.findViewById(2131299146);
            StringBuffer stringBuffer = new StringBuffer("正在直播");
            if (z) {
                stringBuffer.append("-慢直播");
            } else if (!TextUtils.isEmpty(theSameCityLiveFragment.liveTypeText)) {
                stringBuffer.append("-多视角");
            }
            textView.setText(stringBuffer.toString());
            View findViewByPosition = theSameCityLiveFragment.mLayoutManager.findViewByPosition(theSameCityLiveFragment.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            String liveCoverImg = anchorInfoBean.getLiveCoverImg();
            if (!TextUtils.isEmpty(liveCoverImg)) {
                theSameCityLiveFragment.list.get(theSameCityLiveFragment.mCurrentItem).setCoverImg(liveCoverImg);
            }
            GlideApp.with((FragmentActivity) theSameCityLiveFragment.activityContext).load(liveCoverImg).into((ImageView) findViewByPosition.findViewById(2131296393));
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296434);
            frameLayout.removeAllViews();
            frameLayout.addView(theSameCityLiveFragment.roomInfo);
            theSameCityLiveFragment.managerAudienceLoadData.addRenqi(theSameCityLiveFragment.list.get(theSameCityLiveFragment.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$T43jqUNuFEX1Jja2PDlZkhbZg6o
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$14((String) obj);
                }
            });
            theSameCityLiveFragment.managerAudienceLoadData.loadShopData(theSameCityLiveFragment.list.get(theSameCityLiveFragment.mCurrentItem).getJobNumber(), "Y").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$InBykAL3Tf5N0PCHpgNUBLaZhdk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$17(TheSameCityLiveFragment.this, anchorInfoBean, zhuboDataEntity, (ShopEntity) obj);
                }
            });
            theSameCityLiveFragment.managerAudienceLoadData.loadShopList(theSameCityLiveFragment.list.get(theSameCityLiveFragment.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$jv6SXTHSP40Rb74cFYIraKdPiFQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$19(TheSameCityLiveFragment.this, anchorInfoBean, zhuboDataEntity, (List) obj);
                }
            });
            theSameCityLiveFragment.managerAudienceLoadData.isGuanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$w5u3v5GOfMPxyNxC1G3q9Fl4eQg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$20(TheSameCityLiveFragment.this, (Boolean) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$-pV0O_HBzIj7u83ffDD0p12OtDc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$21(TheSameCityLiveFragment.this, (Throwable) obj);
                }
            });
            if ("2".equals(str2)) {
                theSameCityLiveFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                theSameCityLiveFragment.roomInfo.findViewById(2131297802).setVisibility(0);
                theSameCityLiveFragment.managerAudienceLoadData.reserveLivePv("2", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$46mK7KyVfLl9i0N2GIUtwtjjzkw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        r0.managerAudienceLoadData.getLivePvInfo(r1.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$aaYOQ3ROoYsowt7Lgoe9hrf48m4
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj2) {
                                TheSameCityLiveFragment.lambda$getRoomInfo$24(TheSameCityLiveFragment.this, r2, r3, (LivePvInfoEntity) obj2);
                            }
                        });
                    }
                });
            } else {
                theSameCityLiveFragment.roomInfo.findViewById(2131298391).setVisibility(0);
                theSameCityLiveFragment.roomInfo.findViewById(2131297802).setVisibility(8);
                String forcePassword = anchorInfoBean.getForcePassword();
                String isNeedCheck = anchorInfoBean.getIsNeedCheck();
                if ("Y".equals(forcePassword) && "Y".equals(isNeedCheck)) {
                    View findViewById = theSameCityLiveFragment.roomInfo.findViewById(2131297752);
                    findViewById.setVisibility(8);
                    findViewById.setVisibility(0);
                    ((TextView) findViewById.findViewById(2131299040)).setText("当前为加密直播间");
                    AudienceMainActivity.isLock = true;
                } else {
                    theSameCityLiveFragment.getSharpnessInfo(theSameCityLiveFragment.mCurrentItem);
                }
            }
            theSameCityLiveFragment.roomInfo.findViewById(2131298355).setVisibility(0);
            theSameCityLiveFragment.roomInfo.setVisibility(0);
            return;
        }
        UIUtils.logD("test", "数据返回异常-->code=" + zhuboDataEntity.getStatusCode() + "|" + zhuboDataEntity.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(zhuboDataEntity.getMessage());
        }
        theSameCityLiveFragment.list.get(theSameCityLiveFragment.mCurrentItem).setLiving("N");
        theSameCityLiveFragment.mAdapter.updateList(theSameCityLiveFragment.list);
    }

    public static /* synthetic */ void lambda$getRoomInfo$10(TheSameCityLiveFragment theSameCityLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ZhuboDataEntity zhuboDataEntity, View view) {
        theSameCityLiveFragment.jumpLiveInfo();
        if (anchorInfoBean != null) {
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "点击进入直播间", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "同城");
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$11(TheSameCityLiveFragment theSameCityLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str, ZhuboDataEntity zhuboDataEntity, View view) {
        try {
            String userIndexUrl = anchorInfoBean.getUserIndexUrl();
            if (!TextUtils.isEmpty(userIndexUrl)) {
                Intent intent = new Intent(theSameCityLiveFragment.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(theSameCityLiveFragment.addChannel(userIndexUrl, "直播"));
                webParamsEntity.setNeedTitle(true);
                if (!"2".equals(str)) {
                    webParamsEntity.setType("audienceMain");
                }
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                theSameCityLiveFragment.startActivityForResult(intent, 4001);
            }
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "头像", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "同城");
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$12(TheSameCityLiveFragment theSameCityLiveFragment, String str) throws Exception {
        if ("0000".equals(str)) {
            UIUtils.toastCenter("关注成功");
            theSameCityLiveFragment.roomInfo.findViewById(2131297390).setVisibility(8);
            return;
        }
        UIUtils.toastCenter(str);
    }

    public static /* synthetic */ void lambda$getRoomInfo$17(final TheSameCityLiveFragment theSameCityLiveFragment, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final ZhuboDataEntity zhuboDataEntity, ShopEntity shopEntity) throws Exception {
        ShopEntity.DataBean data;
        if (!"0000".equals(shopEntity.getStatusCode()) || (data = shopEntity.getData()) == null) {
            return;
        }
        try {
            ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131298862)).setText(FormatUtils.getShowString(data.getRoundPraise()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("Y".equalsIgnoreCase(data.getHaveGoods())) {
            theSameCityLiveFragment.roomInfo.findViewById(2131296461).setVisibility(0);
            theSameCityLiveFragment.roomInfo.findViewById(2131298338).setVisibility(0);
            final ShopEntity.DataBean.GoodsBean goods = data.getGoods();
            GlideApp.with((FragmentActivity) theSameCityLiveFragment.activityContext).load(goods.getCoverImgUrl()).into((ImageView) theSameCityLiveFragment.roomInfo.findViewById(2131296407));
            ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131296419)).setText(goods.getName());
            ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131296406)).setText(goods.getDesc());
            ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131296418)).setText(TextUtils.isEmpty(goods.getPrice()) ? goods.getOriginalPrice() : goods.getPrice());
            TextView textView = (TextView) theSameCityLiveFragment.roomInfo.findViewById(2131296417);
            if (TextUtils.isEmpty(goods.getOriginalPrice())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.getPaint().setFlags(16);
            }
            theSameCityLiveFragment.roomInfo.findViewById(2131296461).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$obWc-DFEpAyHr0ipZShN0FK-CT8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$16(TheSameCityLiveFragment.this, anchorInfoBean, goods, zhuboDataEntity, view);
                }
            });
            return;
        }
        theSameCityLiveFragment.roomInfo.findViewById(2131296461).setVisibility(8);
        theSameCityLiveFragment.roomInfo.findViewById(2131298338).setVisibility(8);
    }

    public static /* synthetic */ void lambda$getRoomInfo$16(TheSameCityLiveFragment theSameCityLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ShopEntity.DataBean.GoodsBean goodsBean, ZhuboDataEntity zhuboDataEntity, View view) {
        if (!TextUtils.isEmpty("0")) {
            theSameCityLiveFragment.managerAudienceLoadData.goodsLog(anchorInfoBean.getUserId(), goodsBean.getId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$HDttP5Rjcot-sC6dgS5gujiH5xI
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$15((Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "商品卡片", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "同城");
        }
        Intent intent = new Intent(theSameCityLiveFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(goodsBean.getGoodsUrl());
        webParamsEntity.setType("audienceMain");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        theSameCityLiveFragment.startActivityForResult(intent, 4001);
    }

    public static /* synthetic */ void lambda$getRoomInfo$19(final TheSameCityLiveFragment theSameCityLiveFragment, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final ZhuboDataEntity zhuboDataEntity, List list) throws Exception {
        theSameCityLiveFragment.currentShopList = list;
        ((TextView) theSameCityLiveFragment.roomInfo.findViewById(2131298965)).setText("" + list.size());
        theSameCityLiveFragment.roomInfo.findViewById(2131297720).setVisibility(0);
        theSameCityLiveFragment.roomInfo.findViewById(2131298338).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$XMwrxO_10MkXD_7Uus2EWZrCbxw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                TheSameCityLiveFragment.lambda$getRoomInfo$18(TheSameCityLiveFragment.this, anchorInfoBean, zhuboDataEntity, view);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$18(TheSameCityLiveFragment theSameCityLiveFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ZhuboDataEntity zhuboDataEntity, View view) {
        AudienceGoodsDialog.show(theSameCityLiveFragment.activityContext, theSameCityLiveFragment.currentShopList, new OnDialogBtnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment.5
            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickGoodsLook(GoodListEntity goodListEntity) {
                Intent intent = new Intent(TheSameCityLiveFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(goodListEntity.getGoodsUrl());
                webParamsEntity.setType("audienceMain");
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                TheSameCityLiveFragment.this.startActivityForResult(intent, 4001);
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickOrder() {
                IntentManager.gotoWebViewActivity(TheSameCityLiveFragment.this.activityContext, URLSet.liveOrderMenu(), "我的订单");
            }
        });
        PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "购物车", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "同城");
    }

    public static /* synthetic */ void lambda$getRoomInfo$20(TheSameCityLiveFragment theSameCityLiveFragment, Boolean bool) throws Exception {
        theSameCityLiveFragment.roomInfo.findViewById(2131297390).setVisibility(bool.booleanValue() ? 8 : 0);
        theSameCityLiveFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$21(TheSameCityLiveFragment theSameCityLiveFragment, Throwable th) throws Exception {
        theSameCityLiveFragment.roomInfo.findViewById(2131297390).setVisibility(0);
        theSameCityLiveFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$24(final TheSameCityLiveFragment theSameCityLiveFragment, LivePvInfoEntity livePvInfoEntity, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, LivePvInfoEntity livePvInfoEntity2) throws Exception {
        if ("0000".equals(livePvInfoEntity2.getStatusCode())) {
            LivePvInfoEntity.InfoEntity data = livePvInfoEntity2.getData();
            final TextView textView = (TextView) theSameCityLiveFragment.roomInfo.findViewById(2131299154);
            final TextView textView2 = (TextView) theSameCityLiveFragment.roomInfo.findViewById(2131299155);
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
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$PAp5wv0vhTPmhJhdxUKAXFgMEcA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$23(TheSameCityLiveFragment.this, textView, anchorInfoBean, textView2, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$23(final TheSameCityLiveFragment theSameCityLiveFragment, final TextView textView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final TextView textView2, View view) {
        if ("预约".equals(textView.getText())) {
            theSameCityLiveFragment.managerAudienceLoadData.reserveLivePv("1", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$dFok-VIt1Atxm6bX-TkYtfTOks8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    TheSameCityLiveFragment.lambda$getRoomInfo$22(TheSameCityLiveFragment.this, textView2, textView, (LivePvInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$22(TheSameCityLiveFragment theSameCityLiveFragment, TextView textView, TextView textView2, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            LivePvResultDialog livePvResultDialog = new LivePvResultDialog(theSameCityLiveFragment.activityContext, textView.getText().toString().replace(" 开播", ""));
            textView2.setText("已预约");
            livePvResultDialog.show();
            return;
        }
        textView2.setText("预约");
        ToastUtil.showToast("出了一点儿小问题，请稍后再试");
    }

    public static /* synthetic */ void lambda$getRoomInfo$27(TheSameCityLiveFragment theSameCityLiveFragment, Throwable th) throws Exception {
        UIUtils.logD("test", "主播接口信息异常-->" + th.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(th.getMessage());
        }
        theSameCityLiveFragment.list.get(theSameCityLiveFragment.mCurrentItem).setLiving("N");
        theSameCityLiveFragment.mAdapter.updateList(theSameCityLiveFragment.list);
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
        this.autoJion = Observable.timer(30000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$TheSameCityLiveFragment$kpkkeXNgL_4bViJJp1hH-MRiDAw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                TheSameCityLiveFragment.lambda$autoJionLiveInfo$28(TheSameCityLiveFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$autoJionLiveInfo$28(TheSameCityLiveFragment theSameCityLiveFragment, Long l) throws Exception {
        theSameCityLiveFragment.jumpLiveInfo();
        theSameCityLiveFragment.autoJion.dispose();
        theSameCityLiveFragment.autoJion = null;
    }

    private void jumpLiveInfo() {
        Intent intent = new Intent(getActivity(), AudienceActivity.class);
        intent.putExtra("listStr", getLiveList());
        intent.putExtra("liveType", THE_SAME_CITY);
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
