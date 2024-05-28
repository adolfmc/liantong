package com.sinovatech.unicom.separatemodule.videocenter.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.billy.android.swipe.refresh.ClassicHeader;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.sinovatech.unicom.basic.p315ui.view.HomeMengcengView;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.BoardEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoBannerEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoEntity;
import com.sinovatech.unicom.separatemodule.audience.function.RecommendListFunction;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.sinovatech.unicom.separatemodule.videocenter.VideoTodayActivity;
import com.sinovatech.unicom.separatemodule.videocenter.adapter.VideoRingAdapter;
import com.sinovatech.unicom.separatemodule.videocenter.utils.FormatUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoRingFragment extends BaseVideoFragment {
    private static final String KEY_B = "key_Banner_video_ring";
    private static final String KEY_L = "key_list_video_ring";
    private static final String KEY_T = "key_today_video_ring";
    private static final String KEY_TAB = "key_tab_video_ring";
    private static final String TAG = "VideoRingFragment";
    private VideoCenterActivity activityContext;
    private VideoRingAdapter adapter;
    private String boardId;
    private View errorView;
    private View headerView;
    private boolean isMainCall;
    private boolean isResult;
    private boolean isTabShow;
    private ImageView ivBannerBg;
    private View ivLoading;
    private GridLayoutManager layoutManager;
    private LinearLayout llTitleArea;
    private Disposable loader;
    private View loadingBg;
    private View loadingView;
    private LinearLayoutManager mTodayLayoutManager;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private String pageName;
    private RecyclerView rlvContent;
    private RecyclerView rlvTab;
    private RecyclerView rlvToday;
    private SmartSwipeRefresh smartSwipeRefresh;
    private SmartSwipeRefresh ssrView;
    private BoardEntity tab;
    private BaseQuickAdapter<BoardEntity, BaseViewHolder> tabAdapter;
    private Disposable timer;
    private LinearLayout titleLine;
    private BaseQuickAdapter<VideoEntity, BaseViewHolder> todayAdapter;
    private BannerViewPager vpBanner;
    private int pageNum = 1;
    private String currentCode = "4235";
    private Gson gson = new Gson();

    /* renamed from: sY */
    private int f18636sY = 0;
    private int spanCount = 2;
    private Map<String, String> videoIds = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLog$18(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setLog$17(String str) throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected int setContentView() {
        return 2131493131;
    }

    static /* synthetic */ int access$212(VideoRingFragment videoRingFragment, int i) {
        int i2 = videoRingFragment.f18636sY + i;
        videoRingFragment.f18636sY = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void exposureEvent(int i) {
        int i2;
        VideoRingFragment videoRingFragment;
        int i3;
        VideoRingFragment videoRingFragment2 = this;
        try {
            if (i == 0) {
                int findFirstCompletelyVisibleItemPosition = videoRingFragment2.mTodayLayoutManager.findFirstCompletelyVisibleItemPosition();
                int findLastCompletelyVisibleItemPosition = videoRingFragment2.mTodayLayoutManager.findLastCompletelyVisibleItemPosition();
                int i4 = findFirstCompletelyVisibleItemPosition;
                while (i4 < findLastCompletelyVisibleItemPosition + 1) {
                    try {
                        String id = videoRingFragment2.todayAdapter.getItem(i4).getId();
                        String tjpara = videoRingFragment2.todayAdapter.getItem(i4).getTjpara();
                        if (videoRingFragment2.videoIds.containsKey(id)) {
                            i3 = i4;
                        } else {
                            i3 = i4;
                            try {
                                setLog("曝光事件", "28", "", "", "", "", "1", "1", "今日推荐", "今日推荐", "", id, "", "", "", tjpara);
                                videoRingFragment2 = this;
                                try {
                                    videoRingFragment2.videoIds.put(id, "曝光");
                                } catch (Exception e) {
                                    e = e;
                                    e.printStackTrace();
                                    i4 = i3 + 1;
                                }
                            } catch (Exception e2) {
                                e = e2;
                                videoRingFragment2 = this;
                            }
                        }
                    } catch (Exception e3) {
                        e = e3;
                        i3 = i4;
                    }
                    i4 = i3 + 1;
                }
                return;
            }
            int findFirstCompletelyVisibleItemPosition2 = videoRingFragment2.layoutManager.findFirstCompletelyVisibleItemPosition();
            int findLastCompletelyVisibleItemPosition2 = videoRingFragment2.layoutManager.findLastCompletelyVisibleItemPosition();
            MsLogUtil.m7979d(TAG, "first=" + findFirstCompletelyVisibleItemPosition2 + "|last=" + findLastCompletelyVisibleItemPosition2);
            while (findFirstCompletelyVisibleItemPosition2 <= findLastCompletelyVisibleItemPosition2) {
                try {
                    String id2 = videoRingFragment2.adapter.getItem(findFirstCompletelyVisibleItemPosition2).getId();
                    String name = videoRingFragment2.adapter.getItem(findFirstCompletelyVisibleItemPosition2).getName();
                    String tjpara2 = videoRingFragment2.adapter.getItem(findFirstCompletelyVisibleItemPosition2).getTjpara();
                    MsLogUtil.m7979d(TAG, "index=" + findFirstCompletelyVisibleItemPosition2 + "name=" + name);
                    if (videoRingFragment2.videoIds.containsKey(id2)) {
                        i2 = findLastCompletelyVisibleItemPosition2;
                        videoRingFragment = videoRingFragment2;
                    } else {
                        String name2 = videoRingFragment2.tab == null ? "精品推荐" : videoRingFragment2.tab.getName();
                        videoRingFragment = this;
                        i2 = findLastCompletelyVisibleItemPosition2;
                        try {
                            videoRingFragment.setLog("曝光事件", "28", "", "", "", "", "1", "1", name2, name2, "", id2, "", "", "", tjpara2);
                            try {
                                videoRingFragment.videoIds.put(id2, "曝光");
                            } catch (Exception unused) {
                            }
                        } catch (Exception unused2) {
                            videoRingFragment = this;
                        }
                    }
                } catch (Exception unused3) {
                    i2 = findLastCompletelyVisibleItemPosition2;
                    videoRingFragment = videoRingFragment2;
                }
                findFirstCompletelyVisibleItemPosition2++;
                videoRingFragment2 = videoRingFragment;
                findLastCompletelyVisibleItemPosition2 = i2;
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
    }

    private void handleDataExposure() {
        try {
            if (this.activityContext.getCurrentShowCode().equals("spcl")) {
                exposureEvent(0);
                exposureEvent(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String str) {
        this.pageName = str;
    }

    public void setMainCall(boolean z) {
        this.isMainCall = z;
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected void initView() {
        this.activityContext = (VideoCenterActivity) getActivity();
        this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        this.rlvContent = (RecyclerView) findViewById(2131298400);
        if (UIUtils.getScreenWidth((Activity) this.activityContext) * 1.5d > UIUtils.getScreenHeight((Activity) this.activityContext)) {
            if (getResources().getDisplayMetrics().density <= 3.0f) {
                this.spanCount = 4;
            } else {
                this.spanCount = 2;
            }
        } else {
            this.spanCount = 2;
        }
        this.layoutManager = new GridLayoutManager(getActivity(), this.spanCount);
        this.rlvContent.setLayoutManager(this.layoutManager);
        RecyclerView recyclerView = this.rlvContent;
        VideoRingAdapter videoRingAdapter = new VideoRingAdapter(2131493249, this.activityContext);
        this.adapter = videoRingAdapter;
        recyclerView.setAdapter(videoRingAdapter);
        this.adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$V0g_a5Qd_lB-paH79vgBfrW0tm0
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                VideoRingFragment.lambda$initView$0(VideoRingFragment.this, baseQuickAdapter, view, i);
            }
        });
        this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$BcKL8Q05qkrU2y_QxmcSHXrsewM
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
            public final void onLoadMoreRequested() {
                r0.getRecommend(r0.pageNum, VideoRingFragment.this.currentCode);
            }
        }, this.rlvContent);
        this.rlvContent.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.1
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView2, int i, int i2) {
                int height = VideoRingFragment.this.headerView.getHeight();
                int height2 = VideoRingFragment.this.rlvTab.getHeight();
                VideoRingFragment.access$212(VideoRingFragment.this, i2);
                if (VideoRingFragment.this.f18636sY >= height - height2) {
                    if (VideoRingFragment.this.isTabShow) {
                        return;
                    }
                    VideoRingFragment.this.titleLine.removeView(VideoRingFragment.this.rlvTab);
                    VideoRingFragment.this.llTitleArea.addView(VideoRingFragment.this.rlvTab);
                    VideoRingFragment.this.llTitleArea.setVisibility(0);
                    VideoRingFragment.this.isTabShow = true;
                } else if (VideoRingFragment.this.isTabShow) {
                    VideoRingFragment.this.llTitleArea.removeView(VideoRingFragment.this.rlvTab);
                    VideoRingFragment.this.llTitleArea.setVisibility(8);
                    VideoRingFragment.this.titleLine.addView(VideoRingFragment.this.rlvTab);
                    VideoRingFragment.this.isTabShow = false;
                }
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i) {
                MsLogUtil.m7979d(VideoRingFragment.TAG, "滑动状态=" + i);
                if (i == 0) {
                    String name = VideoRingFragment.this.tab == null ? "精品推荐" : VideoRingFragment.this.tab.getName();
                    VideoRingFragment videoRingFragment = VideoRingFragment.this;
                    videoRingFragment.setLog("滑动", "21", name + "列表滑动", "", "", "", "1", "3", name, name, "", "", "", "", "", "");
                    VideoRingFragment.this.exposureEvent(1);
                    if (VideoRingFragment.this.activityContext != null) {
                        VideoRingFragment.this.activityContext.showRefreshBtnIn();
                    }
                } else if (VideoRingFragment.this.activityContext != null) {
                    VideoRingFragment.this.activityContext.showRefreshBtnOut();
                }
            }
        });
        this.llTitleArea = (LinearLayout) findViewById(2131297786);
        initHeader();
        initEmptyView();
        UIUtils.logD(TAG, "initView");
    }

    public static /* synthetic */ void lambda$initView$0(VideoRingFragment videoRingFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String json = toJson(videoRingFragment.adapter.getData());
        String str = videoRingFragment.currentCode;
        videoRingFragment.boardId = str;
        VideoCenterActivity videoCenterActivity = videoRingFragment.activityContext;
        int i2 = videoRingFragment.pageNum;
        BoardEntity boardEntity = videoRingFragment.tab;
        videoCenterActivity.gotoVideInfo(str, json, i, i2, boardEntity == null ? "精品推荐" : boardEntity.getName());
        BoardEntity boardEntity2 = videoRingFragment.tab;
        String name = boardEntity2 == null ? "精品推荐" : boardEntity2.getName();
        videoRingFragment.setLog("点击坑位", "26", "", "", "", "", "1", "2", name, name, "", videoRingFragment.adapter.getItem(i).getId(), "", "", "", videoRingFragment.adapter.getItem(i).getTjpara());
    }

    public static String toJson(List<VideoEntity> list) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (VideoEntity videoEntity : list) {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("contentId", videoEntity.getId());
                jSONObject.put("name", videoEntity.getName());
                jSONObject.put("picpath", videoEntity.getPicpath());
                jSONObject.put("price", videoEntity.getPrice());
                jSONObject.put("likeNum", videoEntity.getLikeNum());
                jSONObject.put("likeFlag", videoEntity.getLikeFlag());
                jSONObject.put("viewNum", videoEntity.getViewNum());
                jSONObject.put("singername", videoEntity.getSingername());
                jSONObject.put("favNum", videoEntity.getFavNum());
                jSONObject.put("favFlag", videoEntity.getFavFlag());
                jSONObject.put("nickname", videoEntity.getNickname());
                jSONObject.put("headimg", videoEntity.getHeadimg());
                jSONObject.put("tjpara", videoEntity.getTjpara());
                jSONObject.put("contentType", videoEntity.getContentType());
                jSONObject.put("singleTag", videoEntity.getSingleTag());
                jSONObject.put("identification", videoEntity.getIdentification());
                String label = videoEntity.getLabel();
                if (!TextUtils.isEmpty(label)) {
                    JSONArray jSONArray2 = new JSONArray();
                    for (String str : label.split("#")) {
                        jSONArray2.put(str);
                    }
                    jSONObject.put("label", jSONArray2);
                }
                jSONArray.put(jSONObject);
            }
            return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initEmptyView() {
        View inflate = LayoutInflater.from(this.activityContext).inflate(2131493250, (ViewGroup) null);
        this.loadingView = inflate.findViewById(2131298335);
        this.loadingBg = inflate.findViewById(2131299475);
        this.ivLoading = inflate.findViewById(2131297370);
        this.errorView = inflate.findViewById(2131297706);
        this.errorView.setVisibility(8);
        this.loadingView.setVisibility(0);
        this.loadingBg.setVisibility(8);
        this.ivLoading.setAnimation(AnimationUtils.loadAnimation(this.activityContext, 2130772001));
        this.adapter.setEmptyView(inflate);
        this.smartSwipeRefresh = SmartSwipeRefresh.behindMode(this.rlvContent, false, false).setDataLoader(new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.2
            @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
            public void onRefresh(SmartSwipeRefresh smartSwipeRefresh) {
                VideoRingFragment.this.ssrView = smartSwipeRefresh;
                VideoRingFragment.this.getBannerList();
                VideoRingFragment.this.getTabs();
                VideoRingFragment.this.getToday();
            }

            @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
            public void onLoadMore(SmartSwipeRefresh smartSwipeRefresh) {
                UIUtils.logD(VideoRingFragment.TAG, "下拉刷新");
            }
        }).disableLoadMore().setHeader(new ClassicHeader(this.activityContext) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.3
            @Override // com.billy.android.swipe.refresh.ClassicHeader, com.billy.android.swipe.SmartSwipeRefresh.RefreshView
            public void onReset() {
                setText(2131886814);
            }

            @Override // com.billy.android.swipe.refresh.ClassicHeader, com.billy.android.swipe.SmartSwipeRefresh.RefreshView
            public long onFinish(boolean z) {
                VideoCenterActivity.isFlag = true;
                return super.onFinish(z);
            }
        });
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected void lazyLoad() {
        Disposable disposable = this.loader;
        if (disposable != null) {
            disposable.dispose();
            this.loader = null;
        }
        this.loader = Observable.timer(100L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$Q5iYSn2kbqzG5KS4puRLzjkVH4s
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$lazyLoad$2(VideoRingFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$lazyLoad$2(VideoRingFragment videoRingFragment, Long l) throws Exception {
        videoRingFragment.hiBoardLog("25", "spcl");
        if (videoRingFragment.isResult) {
            videoRingFragment.isResult = false;
        } else if (!videoRingFragment.activityContext.getCurrentShowCode().equals("spcl")) {
            UIUtils.logD(TAG, "彩铃==拦截");
        } else {
            UIUtils.logD(TAG, "lazyLoad");
            videoRingFragment.setLog("浏览页面", "1", "", "", "", "", "1", "2", "负一屏视频彩铃首页", "", "", "", "", "", "", "");
            if (videoRingFragment.adapter.getData().size() == 0) {
                videoRingFragment.getBannerList();
                videoRingFragment.getTabs();
                videoRingFragment.getToday();
            }
            UIUtils.logD(TAG, "加载完数据");
            Disposable disposable = videoRingFragment.loader;
            if (disposable != null) {
                disposable.dispose();
                videoRingFragment.loader = null;
            }
        }
    }

    private void initHeader() {
        this.headerView = LayoutInflater.from(this.activityContext).inflate(2131493306, (ViewGroup) null);
        this.titleLine = (LinearLayout) this.headerView.findViewById(2131297788);
        this.vpBanner = (BannerViewPager) this.headerView.findViewById(2131299542);
        this.ivBannerBg = (ImageView) this.headerView.findViewById(2131297347);
        this.headerView.findViewById(2131299018).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$6BoiFF7ytp1qPRyZTOw08sY6ZD0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                VideoRingFragment.lambda$initHeader$3(VideoRingFragment.this, view);
            }
        });
        this.rlvToday = (RecyclerView) this.headerView.findViewById(2131298412);
        this.mTodayLayoutManager = new LinearLayoutManager(this.activityContext, 0, false);
        this.rlvToday.setLayoutManager(this.mTodayLayoutManager);
        RecyclerView recyclerView = this.rlvToday;
        BaseQuickAdapter<VideoEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<VideoEntity, BaseViewHolder>(2131493253) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.4
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, VideoEntity videoEntity) {
                baseViewHolder.setText(2131299060, videoEntity.getName()).setText(2131299140, FormatUtils.str2Wan(videoEntity.getLikeNum())).setImageResource(2131297522, videoEntity.getLikeFlag().equals("0") ? 2131231614 : 2131231615);
                Glide.with((FragmentActivity) VideoRingFragment.this.activityContext).load(videoEntity.getPicpath()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231128).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(UIUtils.dip2px(5.0f), 0, RoundedCornersTransformation.CornerType.ALL)))).into((ImageView) baseViewHolder.getView(2131297474));
            }
        };
        this.todayAdapter = baseQuickAdapter;
        recyclerView.setAdapter(baseQuickAdapter);
        this.todayAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$zGyGk6iUWA1C_HK1_-bCntzRN8Q
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                VideoRingFragment.lambda$initHeader$4(VideoRingFragment.this, baseQuickAdapter2, view, i);
            }
        });
        this.rlvToday.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.5
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView2, int i) {
                if (i == 0) {
                    VideoRingFragment.this.setLog("滑动", "21", "今日推荐列表横向滑动", "", "", "", "1", "3", "今日推荐", "今日推荐", "", "", "", "", "", "");
                    VideoRingFragment.this.exposureEvent(0);
                }
            }
        });
        this.rlvTab = (RecyclerView) this.headerView.findViewById(2131298411);
        this.rlvTab.setLayoutManager(new LinearLayoutManager(this.activityContext, 0, false));
        RecyclerView recyclerView2 = this.rlvTab;
        BaseQuickAdapter<BoardEntity, BaseViewHolder> baseQuickAdapter2 = new BaseQuickAdapter<BoardEntity, BaseViewHolder>(2131493252) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, BoardEntity boardEntity) {
                baseViewHolder.setText(2131299108, boardEntity.getName()).setTextColor(2131299108, Color.parseColor(boardEntity.isSelected() ? "#E60027" : "#666666"));
                ((TextView) baseViewHolder.getView(2131299108)).getPaint().setFakeBoldText(boardEntity.isSelected());
            }
        };
        this.tabAdapter = baseQuickAdapter2;
        recyclerView2.setAdapter(baseQuickAdapter2);
        this.tabAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$PIX3_XsA7wPnE_fYtv4oTUclpCY
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter3, View view, int i) {
                VideoRingFragment.lambda$initHeader$5(VideoRingFragment.this, baseQuickAdapter3, view, i);
            }
        });
        this.adapter.setHeaderView(this.headerView);
    }

    public static /* synthetic */ void lambda$initHeader$3(VideoRingFragment videoRingFragment, View view) {
        videoRingFragment.activityContext.startActivity(new Intent(videoRingFragment.activityContext, VideoTodayActivity.class));
        videoRingFragment.setLog("点击按钮", "19", "今日推荐-更多", "", "", "", "1", "2", "负一屏视频彩铃首页", "今日推荐", "", "", "", "", "", "");
    }

    public static /* synthetic */ void lambda$initHeader$4(VideoRingFragment videoRingFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String json = toJson(videoRingFragment.todayAdapter.getData());
        videoRingFragment.boardId = "4593";
        videoRingFragment.activityContext.gotoVideInfo(videoRingFragment.boardId, json, i, 1, "今日推荐");
        videoRingFragment.setLog("点击坑位", "26", "", "", "", "", "1", "2", "今日推荐", "今日推荐", "", videoRingFragment.todayAdapter.getItem(i).getId(), "", "", "", videoRingFragment.todayAdapter.getItem(i).getTjpara());
    }

    public static /* synthetic */ void lambda$initHeader$5(VideoRingFragment videoRingFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        for (BoardEntity boardEntity : videoRingFragment.tabAdapter.getData()) {
            boardEntity.setSelected(false);
        }
        videoRingFragment.tabAdapter.getData().get(i).setSelected(true);
        videoRingFragment.tabAdapter.notifyDataSetChanged();
        videoRingFragment.tab = videoRingFragment.tabAdapter.getData().get(i);
        videoRingFragment.currentCode = videoRingFragment.tab.getId();
        videoRingFragment.getRecommend(1, videoRingFragment.currentCode);
        videoRingFragment.setLog("点击按钮", "19", videoRingFragment.tab.getName(), "", "", "", "1", "2", "负一屏视频彩铃首页", videoRingFragment.tab.getName(), "", "", "", "", "", "");
    }

    private void loadCache() {
        String cache = getCache(KEY_B);
        if (!TextUtils.isEmpty(cache)) {
            Gson gson = this.gson;
            Type type = new TypeToken<List<VideoBannerEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.7
            }.getType();
            initBanner((List) (!(gson instanceof Gson) ? gson.fromJson(cache, type) : NBSGsonInstrumentation.fromJson(gson, cache, type)));
        }
        String cache2 = getCache(KEY_T);
        if (!TextUtils.isEmpty(cache2)) {
            BaseQuickAdapter<VideoEntity, BaseViewHolder> baseQuickAdapter = this.todayAdapter;
            Gson gson2 = this.gson;
            Type type2 = new TypeToken<List<VideoEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.8
            }.getType();
            baseQuickAdapter.setNewData((List) (!(gson2 instanceof Gson) ? gson2.fromJson(cache2, type2) : NBSGsonInstrumentation.fromJson(gson2, cache2, type2)));
        }
        String cache3 = getCache(KEY_TAB);
        if (!TextUtils.isEmpty(cache3)) {
            BaseQuickAdapter<BoardEntity, BaseViewHolder> baseQuickAdapter2 = this.tabAdapter;
            Gson gson3 = this.gson;
            Type type3 = new TypeToken<List<BoardEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.9
            }.getType();
            baseQuickAdapter2.setNewData((List) (!(gson3 instanceof Gson) ? gson3.fromJson(cache3, type3) : NBSGsonInstrumentation.fromJson(gson3, cache3, type3)));
        }
        String cache4 = getCache(KEY_L);
        if (!TextUtils.isEmpty(cache4)) {
            Gson gson4 = this.gson;
            Type type4 = new TypeToken<List<VideoEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.10
            }.getType();
            this.adapter.setNewData((List) (!(gson4 instanceof Gson) ? gson4.fromJson(cache4, type4) : NBSGsonInstrumentation.fromJson(gson4, cache4, type4)));
        }
        UIUtils.logD(TAG, "loadCache");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getBannerList() {
        this.managerAudienceLoadData.getVideoBannerList().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$oCRjdxVGUDMjbtJ6Wt-Sgib4iks
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getBannerList$6(VideoRingFragment.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$iHP9DfcIHJRTtj3cRzQHhr-bMcA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getBannerList$7((Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getBannerList$6(VideoRingFragment videoRingFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (baseVideoEntity.getStatusCode().equals("0000")) {
            videoRingFragment.ivBannerBg.setVisibility(8);
            videoRingFragment.initBanner((List) baseVideoEntity.getData());
            Gson gson = videoRingFragment.gson;
            Object data = baseVideoEntity.getData();
            videoRingFragment.saveCache(KEY_B, !(gson instanceof Gson) ? gson.toJson(data) : NBSGsonInstrumentation.toJson(gson, data));
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getBannerList$7(Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        UIUtils.logD(TAG, "banner错误-->" + th.getMessage());
        th.printStackTrace();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getToday() {
        String boardInfo = URLSet.getBoardInfo("1", "4593");
        UIUtils.logD(TAG, "今日推荐URL-->" + boardInfo);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(boardInfo, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecommendListFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$HJlenbFbP4xIJtlZyPWO1TkmCEc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getToday$8(VideoRingFragment.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$8W1v9_jUoeRzjud_dRI2SOP1HO8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getToday$9((Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getToday$8(VideoRingFragment videoRingFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            Gson gson = videoRingFragment.gson;
            Object data = baseVideoEntity.getData();
            videoRingFragment.saveCache(KEY_T, !(gson instanceof Gson) ? gson.toJson(data) : NBSGsonInstrumentation.toJson(gson, data));
            videoRingFragment.todayAdapter.setNewData((List) baseVideoEntity.getData());
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getToday$9(Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        th.printStackTrace();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRecommend(final int i, final String str) {
        String boardInfo = URLSet.getBoardInfo(i + "", str);
        HashMap hashMap = new HashMap();
        hashMap.put("version", getResources().getString(2131886969));
        if ("4235".equals(str)) {
            boardInfo = boardInfo.replace("getBoardInfo", "getInfoOfBothVideo");
            hashMap.put("provinceCode", UserManager.getInstance().getLocateProvinceCode());
            hashMap.put("cityCode", UserManager.getInstance().getLocateCityCode());
            hashMap.put("page", i == 1 ? "1" : "2");
        }
        UIUtils.logD(TAG, "精品推荐URL-->" + boardInfo);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(boardInfo, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecommendListFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$jBWUptDI41zz1z1ykC-1RFIUL0M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getRecommend$10(VideoRingFragment.this, i, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$jlTmnAj-s95WkgPxn1VoPCWw-Ig
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getRecommend$11(VideoRingFragment.this, str, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getRecommend$10(VideoRingFragment videoRingFragment, int i, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            videoRingFragment.pageNum = Integer.parseInt(baseVideoEntity.getNextPageNum());
            if (i == 1) {
                SmartSwipeRefresh smartSwipeRefresh = videoRingFragment.ssrView;
                if (smartSwipeRefresh != null) {
                    smartSwipeRefresh.finished(true);
                }
                Gson gson = videoRingFragment.gson;
                Object data = baseVideoEntity.getData();
                videoRingFragment.saveCache(KEY_L, !(gson instanceof Gson) ? gson.toJson(data) : NBSGsonInstrumentation.toJson(gson, data));
                videoRingFragment.adapter.setNewData((List) baseVideoEntity.getData());
                videoRingFragment.showMasking();
            } else {
                videoRingFragment.adapter.addData((Collection) baseVideoEntity.getData());
            }
            if (videoRingFragment.pageNum > 0) {
                videoRingFragment.adapter.loadMoreComplete();
            } else {
                videoRingFragment.adapter.loadMoreEnd();
            }
        }
    }

    public static /* synthetic */ void lambda$getRecommend$11(VideoRingFragment videoRingFragment, String str, Throwable th) throws Exception {
        UIUtils.logD(TAG, str + "接口报错-->" + th.getMessage());
        th.printStackTrace();
        videoRingFragment.errorView.setVisibility(0);
        videoRingFragment.loadingView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getTabs() {
        String boardList = URLSet.getBoardList("1", "1");
        UIUtils.logD(TAG, "tabsUrl-->" + boardList);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(boardList, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$tiryEanN3ZyIW0MjMHzOdrRbFbw
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoRingFragment.lambda$getTabs$12((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$LK6j4UkEEIa0DaEPNSFiazxWFBg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getTabs$13(VideoRingFragment.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$I_lgE-z9mMBtYr3DB9UPMfA65gM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$getTabs$14(VideoRingFragment.this, (Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getTabs$12(String str) throws Exception {
        UIUtils.logD(TAG, "tab数据-->" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                BoardEntity boardEntity = new BoardEntity();
                boardEntity.setId(optJSONObject.optString("id"));
                boardEntity.setName(optJSONObject.optString("name"));
                boardEntity.setCount(optJSONObject.optString("count"));
                boardEntity.setFeetype(optJSONObject.optString("feetype"));
                boardEntity.setModeltype(optJSONObject.optString("modeltype"));
                arrayList.add(boardEntity);
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$getTabs$13(VideoRingFragment videoRingFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        List<BoardEntity> list = "0000".equals(baseVideoEntity.getStatusCode()) ? (List) baseVideoEntity.getData() : null;
        if (list == null) {
            list = new ArrayList<>();
        }
        BoardEntity boardEntity = new BoardEntity();
        boardEntity.setId("4235");
        boardEntity.setName("精品推荐");
        boardEntity.setSelected(true);
        list.add(0, boardEntity);
        videoRingFragment.tabAdapter.setNewData(list);
        videoRingFragment.currentCode = boardEntity.getId();
        videoRingFragment.getRecommend(1, videoRingFragment.currentCode);
        Gson gson = videoRingFragment.gson;
        List<BoardEntity> data = videoRingFragment.tabAdapter.getData();
        videoRingFragment.saveCache(KEY_TAB, !(gson instanceof Gson) ? gson.toJson(data) : NBSGsonInstrumentation.toJson(gson, data));
    }

    public static /* synthetic */ void lambda$getTabs$14(VideoRingFragment videoRingFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (videoRingFragment.todayAdapter.getData().size() == 0) {
            ArrayList arrayList = new ArrayList();
            BoardEntity boardEntity = new BoardEntity();
            boardEntity.setId("4235");
            boardEntity.setName("精品推荐");
            boardEntity.setSelected(true);
            arrayList.add(0, boardEntity);
            videoRingFragment.tabAdapter.setNewData(arrayList);
            videoRingFragment.currentCode = boardEntity.getId();
        }
        videoRingFragment.getRecommend(1, videoRingFragment.currentCode);
    }

    private void initBanner(final List<VideoBannerEntity> list) {
        this.vpBanner.setHolderCreator(new HolderCreator() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$m-vDT0nQFjXF8D-E0vjw29K-Q-c
            @Override // com.zhpan.bannerview.holder.HolderCreator
            public final ViewHolder createViewHolder() {
                return VideoRingFragment.lambda$initBanner$15(VideoRingFragment.this);
            }
        });
        this.vpBanner.setIndicatorWidth(BannerUtils.dp2px(5.0f), BannerUtils.dp2px(5.0f));
        this.vpBanner.setIndicatorHeight(BannerUtils.dp2px(3.0f));
        this.vpBanner.setIndicatorGap(BannerUtils.dp2px(4.0f));
        this.vpBanner.setIndicatorColor(Color.parseColor("#EEEEEE"), Color.parseColor("#E60027"));
        this.vpBanner.setInterval(3000);
        this.vpBanner.setPageStyle(0);
        this.vpBanner.setOnPageClickListener(new BannerViewPager.OnPageClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$3LQleRovCVDRuReVVtaYVwgQMR0
            @Override // com.zhpan.bannerview.BannerViewPager.OnPageClickListener
            public final void onPageClick(int i) {
                VideoRingFragment.lambda$initBanner$16(VideoRingFragment.this, list, i);
            }
        });
        this.vpBanner.create(list);
    }

    public static /* synthetic */ ViewHolder lambda$initBanner$15(VideoRingFragment videoRingFragment) {
        return new BannerViewHolder();
    }

    public static /* synthetic */ void lambda$initBanner$16(VideoRingFragment videoRingFragment, List list, int i) {
        AudienceActivity.IntentGo(videoRingFragment.activityContext, ((VideoBannerEntity) list.get(i)).getData());
        videoRingFragment.setLog("点击banner", "18", "banner", ((VideoBannerEntity) list.get(i)).getData(), "", "", "1", "2", "负一屏视频彩铃首页", "banner", "", "", "", "", "", "");
        videoRingFragment.hiBoardLog("2", "0");
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BannerViewHolder implements ViewHolder<VideoBannerEntity> {
        private ImageView bannerView;

        public BannerViewHolder() {
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public View createView(ViewGroup viewGroup, Context context, int i) {
            View inflate = LayoutInflater.from(context).inflate(2131493538, viewGroup, false);
            this.bannerView = (ImageView) inflate.findViewById(2131296501);
            return inflate;
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public void onBind(Context context, VideoBannerEntity videoBannerEntity, int i, int i2) {
            Glide.with(context).load(videoBannerEntity.getImg()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131231710).error(2131231710).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(UIUtils.dip2px(5.0f), 0, RoundedCornersTransformation.CornerType.ALL)))).into(this.bannerView);
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        MsLogUtil.m7979d("yunCeWenTi", "Fragment-->onActivityResult-->" + intent.hasExtra(AudienceMainActivity.RING_LIST));
        if (intent.hasExtra(AudienceMainActivity.RING_LIST)) {
            Gson gson = this.gson;
            String stringExtra = intent.getStringExtra(AudienceMainActivity.RING_LIST);
            Type type = new TypeToken<List<VideoEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment.11
            }.getType();
            List<VideoEntity> list = (List) (!(gson instanceof Gson) ? gson.fromJson(stringExtra, type) : NBSGsonInstrumentation.fromJson(gson, stringExtra, type));
            MsLogUtil.m7979d("yunCeWenTi", "Fragment-->onActivityResult-->" + intent.hasExtra(AudienceMainActivity.RING_LIST) + "-->" + list.size());
            if (list.size() == 0) {
                return;
            }
            int intExtra = intent.getIntExtra(AudienceMainActivity.RING_INDEX, 0);
            if (TextUtils.equals("4593", this.boardId)) {
                this.todayAdapter.setNewData(list);
                this.rlvToday.smoothScrollToPosition(intExtra);
                Gson gson2 = this.gson;
                saveCache(KEY_T, !(gson2 instanceof Gson) ? gson2.toJson(list) : NBSGsonInstrumentation.toJson(gson2, list));
            } else {
                this.adapter.setNewData(list);
                this.rlvContent.smoothScrollToPosition(intExtra);
                Gson gson3 = this.gson;
                saveCache(KEY_L, !(gson3 instanceof Gson) ? gson3.toJson(list) : NBSGsonInstrumentation.toJson(gson3, list));
            }
            this.isResult = true;
        }
    }

    private void saveCache(String str, String str2) {
        App.getSharePreferenceUtil().putString(str, str2);
    }

    private String getCache(String str) {
        return App.getSharePreferenceUtil().getString(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        HashMap hashMap;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            hashMap = new HashMap();
            hashMap.put("action_id", str2);
            hashMap.put("action_type", str8);
            hashMap.put("action_name", str);
            hashMap.put("url", str4);
            hashMap.put("urlref", str5);
            hashMap.put("last_page_name", str6);
            hashMap.put("activity_type", str7);
            hashMap.put("storey", str10);
            hashMap.put("page_name", str9);
            hashMap.put("pb_name", str3);
            hashMap.put("time_spent", str11);
            hashMap.put("content_id", str12);
            hashMap.put("position", str13);
            hashMap.put("realposition", str14);
            hashMap.put("duration", str15);
            hashMap.put("ip", SystemServiceUtils.getLocalIpAddress());
            hashMap.put("os", "AND");
            hashMap.put("device_model", DeviceHelper.getDeviceModel());
            hashMap.put("os_version", DeviceHelper.getDeviceOSVersion().replace("android", ""));
            hashMap.put("channel_id", "3000011091");
            hashMap.put("content_position", "1");
            hashMap.put("system_time", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
            hashMap.put("local_time", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
            hashMap.put("client_time", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            if (currentPhoneNumber.equals("0")) {
                currentPhoneNumber = "";
            }
            hashMap.put("uid", currentPhoneNumber);
            if (currentPhoneNumber.length() < 11) {
                currentPhoneNumber = RandomStringUtils.randomAlphanumeric(11);
            }
            String substring = currentPhoneNumber.substring(1);
            hashMap.put("cid", "user" + substring);
            hashMap.put("province_code", UserManager.getInstance().getCurrentProvinceCode());
            hashMap.put("city_code", UserManager.getInstance().getCurrentCityCode());
            hashMap.put("app_version", getResources().getString(2131886969));
            hashMap.put("tab_id", "spcl");
            hashMap.put("tab_name", "视频彩铃");
            hashMap.put("operateDesc", "");
            hashMap.put("tjpara", str16);
        } catch (Resources.NotFoundException e) {
            e = e;
        }
        try {
            this.managerAudienceLoadData.setLogPoit(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$CARJj4psc4MM7aGjlzUVSr1zDTQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoRingFragment.lambda$setLog$17((String) obj);
                }
            });
        } catch (Resources.NotFoundException e2) {
            e = e2;
            e.printStackTrace();
        }
    }

    private void hiBoardLog(String str, String str2) {
        this.managerAudienceLoadData.hiBoardLog(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$Nur_P0e65JBxiSKJ4rKsoQ08vHU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoRingFragment.lambda$hiBoardLog$18((String) obj);
            }
        });
    }

    private void showMasking() {
        try {
            this.timer = Observable.timer(800L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoRingFragment$9npnucOHKZRYpp2LnVUQkoyxvHg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoRingFragment.lambda$showMasking$19(VideoRingFragment.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showMasking$19(VideoRingFragment videoRingFragment, Long l) throws Exception {
        try {
            videoRingFragment.handleDataExposure();
            if (!App.getSharePreferenceUtil().getBoolean("video_ring_list_masking_flag") && videoRingFragment.activityContext.getCurrentShowCode().equals("spcl")) {
                if (!"视频彩铃".equals(videoRingFragment.activityContext.getDetailsTab()) || App.getSharePreferenceUtil().getInt("video_center_count_flag") > 1) {
                    HomeMengcengView.showVideoRingListMengceng(videoRingFragment.adapter.getViewByPosition(2, 2131298361), videoRingFragment.activityContext);
                    if (videoRingFragment.timer != null) {
                        videoRingFragment.timer.dispose();
                        videoRingFragment.timer = null;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refresh() {
        setLog("点击按钮", "19", "刷新", "", "", "", "1", "2", "负一屏视频彩铃首页", "", "", "", "", "", "", "");
        SmartSwipeRefresh smartSwipeRefresh = this.smartSwipeRefresh;
        if (smartSwipeRefresh != null) {
            smartSwipeRefresh.getHeader().onReset();
            this.smartSwipeRefresh.startRefresh();
        } else {
            getBannerList();
            getTabs();
            getToday();
        }
        this.rlvContent.scrollToPosition(0);
        this.f18636sY = 0;
    }
}
