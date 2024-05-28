package com.sinovatech.unicom.separatemodule.videocenter.fragment;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveBanner;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveBannerEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.audience.view.LivePvResultDialog;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout;
import com.sinovatech.unicom.separatemodule.videocenter.view.LiveSimpleLoadMoreView;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.apache.commons.lang3.RandomStringUtils;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ZhiBoFragment extends BaseVideoFragment {
    private static final String TAG = "zhibofragment";
    private VideoCenterActivity activityContext;
    private AppBarLayout appbar;
    private CollapsingToolbarLayout collapsingToolbar;
    private CoordinatorLayout coordinatorLayout;
    private View errorView;
    private ImageView imageView;
    private boolean isResult;
    private boolean isYuGaoResult;
    private ImageView ivBannerBg;
    private View ivLoading;
    private Disposable loader;
    private View loadingBg;
    private View loadingView;
    private BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> mAdapter;
    private List<LiveOrFengGuangInfoEntity.LiveInfoItem> mData;
    private RecyclerView mRecyclerView;
    private ManagerAudienceLoadData managerAudienceLoadData;

    /* renamed from: pd */
    private ProgressDialog f18637pd;
    private ProgressBar progressBar;
    private RelativeLayout rl_loading;
    private SuperSwipeRefreshLayout superSwipeRefreshLayout;
    private TabLayout tabLayout;
    private List<String> tabList;
    private TextView textView;
    private BannerViewPager vpBanner;
    private TextView zhibo_chakan;
    private ImageView zhibo_img;
    private TextView zhibo_tv;
    private List<LiveOrFengGuangInfoEntity.LiveInfoItem> mData0 = new ArrayList();
    private List<LiveOrFengGuangInfoEntity.LiveInfoItem> mData1 = new ArrayList();
    private List<LiveOrFengGuangInfoEntity.LiveInfoItem> mData2 = new ArrayList();
    private List<LiveOrFengGuangInfoEntity.LiveInfoItem> mData3 = new ArrayList();
    private List<LiveOrFengGuangInfoEntity.LiveInfoItem> mData4 = new ArrayList();
    private Gson gson = new Gson();
    private int indexPage = 0;
    private String pageCodeString = "0";
    private String pageNumPlayBack = "0";
    private boolean isCloseRefresh = false;
    private int spanCount = 2;
    private Handler loadDataHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$ljBXUVIHvkCsRDsgqRHke5pZUHg
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return ZhiBoFragment.lambda$new$4(ZhiBoFragment.this, message);
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setLog$33(String str) throws Exception {
    }

    static /* synthetic */ VideoCenterActivity access$000(ZhiBoFragment zhiBoFragment) {
        return zhiBoFragment.activityContext;
    }

    static /* synthetic */ int access$100(ZhiBoFragment zhiBoFragment) {
        return zhiBoFragment.indexPage;
    }

    static /* synthetic */ TextView access$200(ZhiBoFragment zhiBoFragment) {
        return zhiBoFragment.textView;
    }

    static /* synthetic */ ImageView access$300(ZhiBoFragment zhiBoFragment) {
        return zhiBoFragment.imageView;
    }

    static /* synthetic */ ProgressBar access$400(ZhiBoFragment zhiBoFragment) {
        return zhiBoFragment.progressBar;
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected int setContentView() {
        UIUtils.logD(TAG, "setContentView");
        return 2131493128;
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        UIUtils.logD(TAG, "onCreate");
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected void initView() {
        UIUtils.logD(TAG, "initView");
        this.activityContext = (VideoCenterActivity) getActivity();
        this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        this.f18637pd = new CustomePorgressDialog(this.activityContext);
        this.f18637pd.setCancelable(true);
        this.f18637pd.setCanceledOnTouchOutside(false);
        this.tabList = new ArrayList();
        this.mData = new ArrayList();
        initEmptyView();
        this.superSwipeRefreshLayout = (SuperSwipeRefreshLayout) findViewById(2131298722);
        this.superSwipeRefreshLayout.setVisibility(8);
        this.collapsingToolbar = (CollapsingToolbarLayout) findViewById(2131296682);
        this.coordinatorLayout = (CoordinatorLayout) findViewById(2131296712);
        this.tabLayout = (TabLayout) findViewById(2131298726);
        this.appbar = (AppBarLayout) findViewById(2131296374);
        this.vpBanner = (BannerViewPager) findViewById(2131299542);
        this.ivBannerBg = (ImageView) findViewById(2131297347);
        this.tabLayout.setTabMode(1);
        this.tabLayout.setSelectedTabIndicatorHeight(0);
        this.appbar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$rILEAC6UKjeGn9_6DoKjt7iwI90
            @Override // android.support.design.widget.AppBarLayout.OnOffsetChangedListener, android.support.design.widget.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                ZhiBoFragment.lambda$initView$0(ZhiBoFragment.this, appBarLayout, i);
            }
        });
        this.mRecyclerView = (RecyclerView) findViewById(2131297989);
        if (UIUtils.getScreenWidth((Activity) this.activityContext) * 1.5d > UIUtils.getScreenHeight((Activity) this.activityContext) && getResources().getDisplayMetrics().density <= 3.0f) {
            this.spanCount = 4;
        } else {
            this.spanCount = 2;
        }
        this.mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), this.spanCount));
        this.mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.1
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
                super.onScrollStateChanged(recyclerView, i);
                if (i == 0) {
                    if (ZhiBoFragment.this.activityContext != null) {
                        ZhiBoFragment.this.activityContext.showRefreshBtnIn();
                    }
                } else if (ZhiBoFragment.this.activityContext != null) {
                    ZhiBoFragment.this.activityContext.showRefreshBtnOut();
                }
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
                super.onScrolled(recyclerView, i, i2);
            }
        });
        RecyclerView recyclerView = this.mRecyclerView;
        BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder>(2131493257, this.mData) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.2
            /*  JADX ERROR: Dependency scan failed at insn: 0x040F: INVOKE_CUSTOM_RANGE r63076, r63077, r63078, r63079, r63080, r63081, r63082, r63083, r63084, r63085, r63086, r63087, r63088, r63089, r63090, r63091, r63092, r63093, r63094, r63095, r63096, r63097, r63098, r63099, r63100, r63101, r63102, r63103, r63104, r63105, r63106, r63107, r63108, r63109, r63110, r63111, r63112, r63113, r63114, r63115, r63116, r63117, r63118, r63119, r63120, r63121, r63122, r63123, r63124, r63125, r63126, r63127, r63128, r63129, r63130, r63131, r63132, r63133, r63134, r63135, r63136, r63137, r63138, r63139, r63140, r63141, r63142, r63143, r63144, r63145, r63146, r63147, r63148, r63149, r63150, r63151, r63152, r63153, r63154, r63155, r63156, r63157, r63158, r63159, r63160, r63161, r63162, r63163, r63164, r63165, r63166, r63167, r63168, r63169, r63170, r63171, r63172, r63173, r63174, r63175, r63176, r63177, r63178, r63179, r63180, r63181, r63182, r63183, r63184, r63185, r63186, r63187, r63188, r63189, r63190, r63191, r63192, r63193, r63194, r63195, r63196, r63197, r63198, r63199, r63200, r63201, r63202, r63203, r63204
                jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x1
                	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
                	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
                	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
                	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:128)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:84)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:82)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:67)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:56)
                	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:41)
                	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:282)
                */
            /*  JADX ERROR: Failed to decode insn: 0x040F: INVOKE_CUSTOM_RANGE r63076, r63077, r63078, r63079, r63080, r63081, r63082, r63083, r63084, r63085, r63086, r63087, r63088, r63089, r63090, r63091, r63092, r63093, r63094, r63095, r63096, r63097, r63098, r63099, r63100, r63101, r63102, r63103, r63104, r63105, r63106, r63107, r63108, r63109, r63110, r63111, r63112, r63113, r63114, r63115, r63116, r63117, r63118, r63119, r63120, r63121, r63122, r63123, r63124, r63125, r63126, r63127, r63128, r63129, r63130, r63131, r63132, r63133, r63134, r63135, r63136, r63137, r63138, r63139, r63140, r63141, r63142, r63143, r63144, r63145, r63146, r63147, r63148, r63149, r63150, r63151, r63152, r63153, r63154, r63155, r63156, r63157, r63158, r63159, r63160, r63161, r63162, r63163, r63164, r63165, r63166, r63167, r63168, r63169, r63170, r63171, r63172, r63173, r63174, r63175, r63176, r63177, r63178, r63179, r63180, r63181, r63182, r63183, r63184, r63185, r63186, r63187, r63188, r63189, r63190, r63191, r63192, r63193, r63194, r63195, r63196, r63197, r63198, r63199, r63200, r63201, r63202, r63203, r63204, method: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.2.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity$LiveInfoItem):void
                jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0x1
                	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
                	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
                	at jadx.core.ProcessClass.process(ProcessClass.java:67)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
                Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x1
                	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
                	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
                	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
                	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
                	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
                	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
                	... 12 more
                */
            /*  JADX ERROR: Failed to decode insn: 0x0422: UNKNOWN(0x53E4), method: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.2.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity$LiveInfoItem):void
                jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0422: UNKNOWN(0x53E4)'
                	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
                	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
                	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
                	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
                	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
                	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
                	at jadx.core.ProcessClass.process(ProcessClass.java:67)
                	at jadx.core.ProcessClass.generateCode(ProcessClass.java:107)
                	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
                	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
                */
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder r17, com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity.LiveInfoItem r18) {
                /*
                    Method dump skipped, instructions count: 1148
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.C96402.convert(com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder, com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity$LiveInfoItem):void");
            }
        };
        this.mAdapter = baseQuickAdapter;
        recyclerView.setAdapter(baseQuickAdapter);
        this.mAdapter.setLoadMoreView(new LiveSimpleLoadMoreView());
        this.mAdapter.openLoadAnimation();
        this.mAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$PVrkky-N2smndiViFPlvtiBp500
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                ZhiBoFragment.lambda$initView$2(ZhiBoFragment.this, baseQuickAdapter2, view, i);
            }
        });
        this.mAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$YJHmk5B0fW5bwaZ_fn1OqSv_v68
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
            public final void onLoadMoreRequested() {
                ZhiBoFragment.lambda$initView$3(ZhiBoFragment.this);
            }
        }, this.mRecyclerView);
        initAdapterEmptyView();
        this.superSwipeRefreshLayout.setHeaderView(createHeaderView());
        this.superSwipeRefreshLayout.setTargetScrollWithLayout(true);
        this.superSwipeRefreshLayout.setHeaderViewBackgroundColor(8421504);
        this.superSwipeRefreshLayout.setOnPullRefreshListener(new C96413());
        this.tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.4
            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabReselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override // android.support.design.widget.TabLayout.BaseOnTabSelectedListener
            public void onTabSelected(TabLayout.Tab tab) {
                ZhiBoFragment.this.changeDataOnTabSelected(tab);
            }
        });
    }

    public static /* synthetic */ void lambda$initView$0(ZhiBoFragment zhiBoFragment, AppBarLayout appBarLayout, int i) {
        SuperSwipeRefreshLayout superSwipeRefreshLayout = zhiBoFragment.superSwipeRefreshLayout;
        if (superSwipeRefreshLayout != null) {
            if (i >= 0) {
                superSwipeRefreshLayout.setEnabled(true);
            } else {
                superSwipeRefreshLayout.setEnabled(false);
            }
        }
    }

    public static /* synthetic */ void lambda$initView$2(final ZhiBoFragment zhiBoFragment, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        try {
            UIUtils.logD(TAG, "点击其中的item,下标为:" + i);
            Gson gson = zhiBoFragment.gson;
            List data = baseQuickAdapter.getData();
            if (gson instanceof Gson) {
                NBSGsonInstrumentation.toJson(gson, data);
            } else {
                gson.toJson(data);
            }
            int id = view.getId();
            if (id == 2131297635) {
                if (zhiBoFragment.mAdapter.getData().size() <= 0 || zhiBoFragment.mAdapter.getData().get(i) == null || TextUtils.isEmpty(zhiBoFragment.mAdapter.getData().get(i).getUserLinkUrl())) {
                    return;
                }
                try {
                    String userLinkUrl = zhiBoFragment.mAdapter.getData().get(i).getUserLinkUrl();
                    if (TextUtils.isEmpty(userLinkUrl)) {
                        return;
                    }
                    zhiBoFragment.IntentGo(zhiBoFragment.addChannel(userLinkUrl, "直播"));
                } catch (Exception e) {
                    MsLogUtil.m7979d(TAG, e.getMessage());
                }
            } else if (id == 2131299153) {
                if (zhiBoFragment.mAdapter.getData().size() <= 0 || zhiBoFragment.mAdapter.getData().get(i) == null || "1".equals(zhiBoFragment.mAdapter.getData().get(i).getVideoCloseState())) {
                    return;
                }
                zhiBoFragment.managerAudienceLoadData.reserveLivePv("1", zhiBoFragment.mAdapter.getData().get(i).getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$33kp1G3ALIHaegUTFehGsnlj8MA
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        ZhiBoFragment.lambda$initView$1(ZhiBoFragment.this, i, (LivePvInfoEntity) obj);
                    }
                });
            } else {
                if (zhiBoFragment.indexPage != 0 && zhiBoFragment.indexPage != 1) {
                    if (zhiBoFragment.indexPage == 2) {
                        if (zhiBoFragment.mAdapter.getData().size() <= 0 || zhiBoFragment.mAdapter.getData().get(i) == null) {
                            return;
                        }
                        zhiBoFragment.IntentGo(URLSet.getFuYiPingYuGaoUrl(zhiBoFragment.mAdapter.getData().get(i).getUserId()));
                        return;
                    } else if (zhiBoFragment.indexPage != 3 || zhiBoFragment.mAdapter.getData().size() <= 0 || zhiBoFragment.mAdapter.getData().get(i) == null) {
                        return;
                    } else {
                        zhiBoFragment.IntentGo(URLSet.getFuYiPingHuiFangUrl(zhiBoFragment.mAdapter.getData().get(i).getUserId(), zhiBoFragment.mAdapter.getData().get(i).getVideoId()));
                        return;
                    }
                }
                if (zhiBoFragment.mAdapter.getData().size() > 0) {
                    Intent intent = new Intent(zhiBoFragment.activityContext, AudienceActivity.class);
                    intent.putExtra("listStr", zhiBoFragment.getLiveList());
                    intent.putExtra("from", "fromMain");
                    if (zhiBoFragment.indexPage == 1) {
                        intent.putExtra("isToSlowLive", "Y");
                    }
                    intent.putExtra("pageNum", zhiBoFragment.pageCodeString);
                    intent.putExtra("shareUserNumSc", "");
                    intent.putExtra("index", String.valueOf(i));
                    zhiBoFragment.activityContext.startActivity(intent);
                }
            }
        } catch (Exception e2) {
            MsLogUtil.m7979d(TAG, e2.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initView$1(ZhiBoFragment zhiBoFragment, int i, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            new LivePvResultDialog(zhiBoFragment.activityContext, zhiBoFragment.mAdapter.getData().get(i).getVideoTime().replace(" 开播", "")).show();
            zhiBoFragment.mAdapter.getData().get(i).setVideoCloseState("1");
            zhiBoFragment.mAdapter.notifyItemChanged(i);
            return;
        }
        UIUtils.showCenterOnlyTextToast(zhiBoFragment.activityContext, "出了一点儿小问题，请稍后再试", 1000);
    }

    public static /* synthetic */ void lambda$initView$3(ZhiBoFragment zhiBoFragment) {
        int i = zhiBoFragment.indexPage;
        if (i == 0) {
            zhiBoFragment.getLivingList("CHOICE", zhiBoFragment.pageCodeString);
        } else if (i == 1) {
            zhiBoFragment.getLivingList("6", zhiBoFragment.pageCodeString);
        } else if (i == 2) {
            zhiBoFragment.getLivingList("YuGao", zhiBoFragment.pageCodeString);
        } else if (i == 3) {
            zhiBoFragment.getLivingList("ALLPLAYBACK", zhiBoFragment.pageCodeString);
        } else if (i == 4) {
            if ("-1".equals(zhiBoFragment.pageCodeString)) {
                zhiBoFragment.getMultiViewPB(zhiBoFragment.pageNumPlayBack);
            } else {
                zhiBoFragment.getLivingList("MultiView", zhiBoFragment.pageCodeString);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C96413 implements SuperSwipeRefreshLayout.OnPullRefreshListener {
        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPullRefreshListener
        public void onPullDistance(int i) {
        }

        C96413() {
        }

        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPullRefreshListener
        public void onRefresh() {
            ZhiBoFragment.this.textView.setText("正在刷新...");
            ZhiBoFragment.this.imageView.setVisibility(8);
            ZhiBoFragment.this.progressBar.setVisibility(0);
            ZhiBoFragment.this.pageCodeString = "0";
            ZhiBoFragment.this.isCloseRefresh = true;
            ZhiBoFragment.this.mData.clear();
            if (ZhiBoFragment.this.indexPage != 0) {
                if (ZhiBoFragment.this.indexPage != 1) {
                    if (ZhiBoFragment.this.indexPage != 2) {
                        if (ZhiBoFragment.this.indexPage == 3) {
                            ZhiBoFragment.this.pageCodeString = "1";
                            ZhiBoFragment zhiBoFragment = ZhiBoFragment.this;
                            zhiBoFragment.getLivingList("ALLPLAYBACK", zhiBoFragment.pageCodeString);
                            return;
                        } else if (ZhiBoFragment.this.indexPage == 4) {
                            ZhiBoFragment zhiBoFragment2 = ZhiBoFragment.this;
                            zhiBoFragment2.getLivingList("MultiView", zhiBoFragment2.pageCodeString);
                            return;
                        } else {
                            return;
                        }
                    }
                    ZhiBoFragment zhiBoFragment3 = ZhiBoFragment.this;
                    zhiBoFragment3.getLivingList("YuGao", zhiBoFragment3.pageCodeString);
                    return;
                }
                ZhiBoFragment zhiBoFragment4 = ZhiBoFragment.this;
                zhiBoFragment4.getLivingList("6", zhiBoFragment4.pageCodeString);
                return;
            }
            ZhiBoFragment zhiBoFragment5 = ZhiBoFragment.this;
            zhiBoFragment5.getLivingList("CHOICE", zhiBoFragment5.pageCodeString);
        }

        @Override // com.sinovatech.unicom.separatemodule.videocenter.refresh.SuperSwipeRefreshLayout.OnPullRefreshListener
        public void onPullEnable(boolean z) {
            ZhiBoFragment.this.textView.setText(z ? "松开刷新" : "下拉刷新");
            ZhiBoFragment.this.imageView.setVisibility(8);
            ZhiBoFragment.this.imageView.setRotation(z ? 180.0f : 0.0f);
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public void changeDataOnTabSelected(TabLayout.Tab tab) {
        try {
            String charSequence = tab.getText().toString();
            UIUtils.logD(TAG, "tab选中的时候," + ((Object) tab.getText()));
            try {
                if (this.f18637pd == null) {
                    this.f18637pd = new CustomePorgressDialog(this.activityContext);
                }
                this.f18637pd.setMessage("加载中");
                UIUtils.showDialog(this.activityContext, this.f18637pd);
            } catch (Exception unused) {
                MsLogUtil.m7980d("加载中报错");
            }
            this.loadDataHandler.removeCallbacksAndMessages(null);
            Message message = new Message();
            message.obj = charSequence;
            this.loadDataHandler.sendMessageDelayed(message, 300L);
            setLog("点击按钮", "19", charSequence, "", "", "", "1", "2", "", charSequence, "", "", "", "", "");
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ boolean lambda$new$4(ZhiBoFragment zhiBoFragment, Message message) {
        try {
            zhiBoFragment.changeData((String) message.obj);
            return false;
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
            return false;
        }
    }

    @Override // com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onDestroy() {
        Handler handler = this.loadDataHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            this.loadDataHandler = null;
        }
        super.onDestroy();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int getSelectedIndex(String str) {
        if (this.tabList != null) {
            for (int i = 0; i < this.tabList.size(); i++) {
                if (str.equals(this.tabList.get(i))) {
                    return i;
                }
            }
        }
        return 0;
    }

    private String getTabName() {
        switch (this.indexPage) {
            case 1:
                return "点击慢直播";
            case 2:
                return "点击直播预告";
            case 3:
                return "点击直播回放";
            case 4:
                return "点击多视角";
            default:
                return "点击精品直播";
        }
    }

    private void initEmptyView() {
        this.loadingView = findViewById(2131298335);
        this.loadingBg = findViewById(2131299475);
        this.ivLoading = findViewById(2131297370);
        this.errorView = findViewById(2131297706);
        this.errorView.setVisibility(8);
        this.loadingView.setVisibility(0);
        this.loadingBg.setVisibility(0);
        this.ivLoading.setAnimation(AnimationUtils.loadAnimation(this.activityContext, 2130772001));
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void changeData(String str) {
        char c;
        this.pageCodeString = "0";
        switch (str.hashCode()) {
            case 23049062:
                if (str.equals("多视角")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 24927515:
                if (str.equals("慢直播")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 932678681:
                if (str.equals("直播回放")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case 933195327:
                if (str.equals("直播预告")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 973166396:
                if (str.equals("精品直播")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        switch (c) {
            case 0:
                this.indexPage = 4;
                getLivingList("MultiView", this.pageCodeString);
                return;
            case 1:
                this.indexPage = 0;
                getLivingList("CHOICE", this.pageCodeString);
                return;
            case 2:
                this.indexPage = 1;
                getLivingList("6", this.pageCodeString);
                return;
            case 3:
                this.indexPage = 2;
                getLivingList("YuGao", this.pageCodeString);
                return;
            case 4:
                this.indexPage = 3;
                this.pageCodeString = "1";
                getLivingList("ALLPLAYBACK", this.pageCodeString);
                return;
            default:
                return;
        }
    }

    private void getBannerList() {
        if (!SystemServiceUtils.netIsAvailable()) {
            VideoCenterActivity.isFlag = true;
            this.errorView.setVisibility(0);
            this.loadingView.setVisibility(8);
            this.superSwipeRefreshLayout.setVisibility(8);
            if (this.isCloseRefresh) {
                this.textView.setText("刷新完成");
                this.superSwipeRefreshLayout.setRefreshing(false);
                this.progressBar.setVisibility(8);
                this.isCloseRefresh = false;
                return;
            }
            return;
        }
        this.managerAudienceLoadData.getBannerList().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$y4Yw5GNjlN67xy5dnmecKbsNOuM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$getBannerList$5(ZhiBoFragment.this, (LiveBanner) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$5NO3bTOx-mSdwR3-Tw1e-Xp43Yo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$getBannerList$6(ZhiBoFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getBannerList$5(ZhiBoFragment zhiBoFragment, LiveBanner liveBanner) throws Exception {
        if (liveBanner.getStatusCode().equals("0000")) {
            if (liveBanner.getData() != null && liveBanner.getData().size() > 0) {
                zhiBoFragment.ivBannerBg.setVisibility(8);
                zhiBoFragment.vpBanner.setVisibility(0);
                zhiBoFragment.initBanner(liveBanner.getData());
                return;
            }
            zhiBoFragment.ivBannerBg.setVisibility(8);
            zhiBoFragment.vpBanner.setVisibility(8);
            return;
        }
        zhiBoFragment.ivBannerBg.setVisibility(8);
        zhiBoFragment.vpBanner.setVisibility(8);
    }

    public static /* synthetic */ void lambda$getBannerList$6(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        UIUtils.logD("xcyTest", "banner错误-->" + th.getMessage());
        th.printStackTrace();
        zhiBoFragment.ivBannerBg.setVisibility(8);
        zhiBoFragment.vpBanner.setVisibility(8);
    }

    private void getMultiViewPB(String str) {
        if (!SystemServiceUtils.netIsAvailable()) {
            VideoCenterActivity.isFlag = true;
            this.errorView.setVisibility(0);
            this.loadingView.setVisibility(8);
            this.superSwipeRefreshLayout.setVisibility(8);
            if (this.isCloseRefresh) {
                this.textView.setText("刷新完成");
                this.superSwipeRefreshLayout.setRefreshing(false);
                this.progressBar.setVisibility(8);
                this.isCloseRefresh = false;
                return;
            }
            return;
        }
        if (this.f18637pd == null) {
            this.f18637pd = new CustomePorgressDialog(this.activityContext);
        }
        this.f18637pd.setMessage("加载中");
        UIUtils.showDialog(this.activityContext, this.f18637pd);
        this.managerAudienceLoadData.getAngleMorePlayBackList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$asR9Iu2TreVAMZJXYKHUeHhsboo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$getMultiViewPB$7(ZhiBoFragment.this, (AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$87EP9RI0uMpZR1P1n4NimliFYGA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$getMultiViewPB$8(ZhiBoFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getMultiViewPB$7(ZhiBoFragment zhiBoFragment, AudienceDataEntity audienceDataEntity) throws Exception {
        zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
        LiveOrFengGuangInfoEntity multiView4FYP = zhiBoFragment.multiView4FYP(audienceDataEntity, "2");
        if ("0000".equals(multiView4FYP.getStatusCode())) {
            UIUtils.logD(TAG, "请求多视角直播回放返回的list个数：" + multiView4FYP.getData().size());
            zhiBoFragment.pageNumPlayBack = multiView4FYP.getNextPageNum();
            zhiBoFragment.mAdapter.addData(multiView4FYP.getData());
            if ("-1".equals(zhiBoFragment.pageNumPlayBack)) {
                zhiBoFragment.mAdapter.loadMoreEnd();
            } else {
                zhiBoFragment.mAdapter.loadMoreComplete();
            }
            if (multiView4FYP.getData() == null || multiView4FYP.getData().size() <= 0) {
                zhiBoFragment.setErrorView(0, 0, "暂无主播进行直播，可查看预告列表", 2131232737);
            }
        } else {
            zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        }
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getMultiViewPB$8(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getLivingList(String str, final String str2) {
        if (!SystemServiceUtils.netIsAvailable()) {
            VideoCenterActivity.isFlag = true;
            this.errorView.setVisibility(0);
            this.loadingView.setVisibility(8);
            this.superSwipeRefreshLayout.setVisibility(8);
            if (this.isCloseRefresh) {
                this.textView.setText("刷新完成");
                this.superSwipeRefreshLayout.setRefreshing(false);
                this.progressBar.setVisibility(8);
                this.isCloseRefresh = false;
                return;
            }
            return;
        }
        try {
            if (this.f18637pd == null) {
                this.f18637pd = new CustomePorgressDialog(this.activityContext);
            }
            this.f18637pd.setMessage("加载中");
            UIUtils.showDialog(this.activityContext, this.f18637pd);
        } catch (Exception unused) {
            MsLogUtil.m7980d("加载中报错");
        }
        if ("YuGao".equals(str)) {
            this.managerAudienceLoadData.getYuGaoList(str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$ZMOpwjO3GBFLxrTsCBDo9puRzdQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$9(ZhiBoFragment.this, str2, (LiveOrFengGuangInfoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$FHCvbipoUT_l7HmbspRyn3DrBZE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$10(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        } else if ("ALLPLAYBACK".equals(str)) {
            this.managerAudienceLoadData.getAllLiveList(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$Lr2Kly1qyesMES2m_b7U1TwwCuU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$11(ZhiBoFragment.this, str2, (LiveOrFengGuangInfoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$sC2kHPCJb0dyyAKDIXGSfjLDxfI
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$12(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        } else if ("6".equals(str)) {
            this.managerAudienceLoadData.getLivingList(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$O9jeefWQfY8ZNZDThIImvX2aBrk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$13(ZhiBoFragment.this, str2, (LiveOrFengGuangInfoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$JX16Cg8mezLbiwzT6kWfgBQegwk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$14(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        } else if ("MultiView".equals(str)) {
            this.managerAudienceLoadData.getAngleMoreLiveList(str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$Av-pah-YBQPM2fpbqgLWyJ2eTFQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$17(ZhiBoFragment.this, str2, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$MrpEFK549cZB4GdHJqsfuru8tkw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$18(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        } else {
            this.managerAudienceLoadData.getLivingList(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$VyrkU0bko03GyVgu6qOvgbojcdY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$19(ZhiBoFragment.this, str2, (LiveOrFengGuangInfoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$ziCt9iH0jtmHTUJffYkJavRFVBw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$20(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getLivingList$9(ZhiBoFragment zhiBoFragment, String str, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 2) {
            MsLogUtil.m7979d(TAG, "当前页不为预告时，舍弃");
            return;
        }
        zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            UIUtils.logD(TAG, "请求预告的list个数：" + liveOrFengGuangInfoEntity.getData().size());
            zhiBoFragment.pageCodeString = liveOrFengGuangInfoEntity.getNextPageNum();
            if ("0".equals(str)) {
                zhiBoFragment.mAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
            } else {
                zhiBoFragment.mAdapter.addData(liveOrFengGuangInfoEntity.getData());
            }
            if ("-1".equals(zhiBoFragment.pageCodeString)) {
                zhiBoFragment.mAdapter.loadMoreEnd();
            } else {
                zhiBoFragment.mAdapter.loadMoreComplete();
            }
            if (liveOrFengGuangInfoEntity.getData() == null || liveOrFengGuangInfoEntity.getData().size() <= 0) {
                zhiBoFragment.setErrorView(0, 8, "暂无预告，快去观看其他视频吧", 2131232738);
            }
        } else {
            zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        }
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getLivingList$10(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 2) {
            MsLogUtil.m7979d(TAG, "当前页不为预告时，舍弃");
            return;
        }
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getLivingList$11(ZhiBoFragment zhiBoFragment, String str, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 3) {
            MsLogUtil.m7979d(TAG, "当前页不为回放时，舍弃");
            return;
        }
        zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            UIUtils.logD(TAG, "请求回放返回的list个数：" + liveOrFengGuangInfoEntity.getData().size());
            zhiBoFragment.pageCodeString = liveOrFengGuangInfoEntity.getNextPageNum();
            if ("1".equals(str)) {
                zhiBoFragment.mAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
            } else {
                zhiBoFragment.mAdapter.addData(liveOrFengGuangInfoEntity.getData());
            }
            if ("-1".equals(zhiBoFragment.pageCodeString)) {
                zhiBoFragment.mAdapter.loadMoreEnd();
            } else {
                zhiBoFragment.mAdapter.loadMoreComplete();
            }
            if (liveOrFengGuangInfoEntity.getData() == null || liveOrFengGuangInfoEntity.getData().size() <= 0) {
                zhiBoFragment.setErrorView(0, 8, "主播暂无回放视频", 2131232736);
            }
        } else {
            zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        }
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getLivingList$12(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 3) {
            MsLogUtil.m7979d(TAG, "当前页不为回放时，舍弃");
            return;
        }
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getLivingList$13(ZhiBoFragment zhiBoFragment, String str, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 1) {
            MsLogUtil.m7979d(TAG, "当前页不为慢直播时，舍弃");
            return;
        }
        zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            UIUtils.logD(TAG, "请求风光返回的list个数：" + liveOrFengGuangInfoEntity.getData().size());
            zhiBoFragment.pageCodeString = liveOrFengGuangInfoEntity.getNextPageNum();
            if ("0".equals(str)) {
                zhiBoFragment.mAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
            } else {
                zhiBoFragment.mAdapter.addData(liveOrFengGuangInfoEntity.getData());
            }
            if ("-1".equals(zhiBoFragment.pageCodeString)) {
                zhiBoFragment.mAdapter.loadMoreEnd();
            } else {
                zhiBoFragment.mAdapter.loadMoreComplete();
            }
            if (liveOrFengGuangInfoEntity.getData() == null || liveOrFengGuangInfoEntity.getData().size() <= 0) {
                zhiBoFragment.setErrorView(0, 8, "暂无内容，快去观看其他视频吧", 2131232749);
            }
        } else {
            zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        }
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
        VideoCenterActivity.isFlag = true;
    }

    public static /* synthetic */ void lambda$getLivingList$14(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 1) {
            MsLogUtil.m7979d(TAG, "当前页不为慢直播时，舍弃");
            return;
        }
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
        VideoCenterActivity.isFlag = true;
    }

    public static /* synthetic */ void lambda$getLivingList$17(final ZhiBoFragment zhiBoFragment, final String str, final AudienceDataEntity audienceDataEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 4) {
            MsLogUtil.m7979d(TAG, "当前页不为多视角时，舍弃");
            return;
        }
        zhiBoFragment.pageCodeString = audienceDataEntity.getNextPageNum();
        if ("-1".equals(audienceDataEntity.getNextPageNum())) {
            zhiBoFragment.managerAudienceLoadData.getAngleMorePlayBackList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$3T1WkS7zi3Rm3lKS7HWyBmDOwGk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$15(ZhiBoFragment.this, audienceDataEntity, str, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$t6TGF6scDNkcLuUC8X5PSGU50D8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$getLivingList$16(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        } else {
            zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
            LiveOrFengGuangInfoEntity multiView4FYP = zhiBoFragment.multiView4FYP(audienceDataEntity, "1");
            if ("0000".equals(multiView4FYP.getStatusCode())) {
                UIUtils.logD(TAG, "请求多视角直播返回的list个数：" + multiView4FYP.getData().size());
                zhiBoFragment.pageCodeString = multiView4FYP.getNextPageNum();
                if ("0".equals(str)) {
                    zhiBoFragment.mAdapter.setNewData(multiView4FYP.getData());
                } else {
                    zhiBoFragment.mAdapter.addData(multiView4FYP.getData());
                }
                if ("-1".equals(zhiBoFragment.pageCodeString)) {
                    zhiBoFragment.mAdapter.loadMoreEnd();
                } else {
                    zhiBoFragment.mAdapter.loadMoreComplete();
                }
                if (multiView4FYP.getData() == null || multiView4FYP.getData().size() <= 0) {
                    zhiBoFragment.setErrorView(0, 0, "暂无主播进行直播，可查看预告列表", 2131232737);
                }
            } else {
                zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
            }
            zhiBoFragment.loadingView.setVisibility(8);
            zhiBoFragment.errorView.setVisibility(8);
            ProgressDialog progressDialog = zhiBoFragment.f18637pd;
            if (progressDialog != null && progressDialog.isShowing()) {
                UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
            }
            if (zhiBoFragment.isCloseRefresh) {
                zhiBoFragment.textView.setText("刷新完成");
                zhiBoFragment.isCloseRefresh = false;
                zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
                zhiBoFragment.progressBar.setVisibility(8);
            }
        }
        VideoCenterActivity.isFlag = true;
    }

    public static /* synthetic */ void lambda$getLivingList$15(ZhiBoFragment zhiBoFragment, AudienceDataEntity audienceDataEntity, String str, AudienceDataEntity audienceDataEntity2) throws Exception {
        zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
        LiveOrFengGuangInfoEntity multiView4FYP = zhiBoFragment.multiView4FYP(audienceDataEntity, "1");
        LiveOrFengGuangInfoEntity multiView4FYP2 = zhiBoFragment.multiView4FYP(audienceDataEntity2, "2");
        if (multiView4FYP.getData() != null && multiView4FYP2.getData() != null) {
            multiView4FYP.getData().addAll(multiView4FYP2.getData());
        }
        if ("0000".equals(multiView4FYP.getStatusCode())) {
            UIUtils.logD(TAG, "请求多视角直播及回放返回的list个数：" + multiView4FYP.getData().size());
            zhiBoFragment.pageNumPlayBack = multiView4FYP.getNextPageNum();
            if ("0".equals(str)) {
                zhiBoFragment.mAdapter.setNewData(multiView4FYP.getData());
            } else {
                zhiBoFragment.mAdapter.addData(multiView4FYP.getData());
            }
            if ("-1".equals(zhiBoFragment.pageNumPlayBack)) {
                zhiBoFragment.mAdapter.loadMoreEnd();
            } else {
                zhiBoFragment.mAdapter.loadMoreComplete();
            }
            if (zhiBoFragment.mAdapter.getData().size() <= 0) {
                zhiBoFragment.setErrorView(0, 0, "暂无主播进行直播，可查看预告列表", 2131232737);
            }
        } else {
            zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        }
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getLivingList$16(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 4) {
            MsLogUtil.m7979d(TAG, "当前页不为多视角时，舍弃");
            return;
        }
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getLivingList$18(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 4) {
            MsLogUtil.m7979d(TAG, "当前页不为多视角时，舍弃");
            return;
        }
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
        VideoCenterActivity.isFlag = true;
    }

    public static /* synthetic */ void lambda$getLivingList$19(ZhiBoFragment zhiBoFragment, String str, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 0) {
            MsLogUtil.m7979d(TAG, "当前页不为直播时，舍弃");
            return;
        }
        zhiBoFragment.superSwipeRefreshLayout.setVisibility(0);
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            UIUtils.logD(TAG, "请求直播返回的list个数：" + liveOrFengGuangInfoEntity.getData().size());
            zhiBoFragment.pageCodeString = liveOrFengGuangInfoEntity.getNextPageNum();
            if ("0".equals(str)) {
                zhiBoFragment.mAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
            } else {
                zhiBoFragment.mAdapter.addData(liveOrFengGuangInfoEntity.getData());
            }
            if ("-1".equals(zhiBoFragment.pageCodeString)) {
                zhiBoFragment.mAdapter.loadMoreEnd();
            } else {
                zhiBoFragment.mAdapter.loadMoreComplete();
            }
            if (liveOrFengGuangInfoEntity.getData() == null || liveOrFengGuangInfoEntity.getData().size() <= 0) {
                zhiBoFragment.setErrorView(0, 0, "暂无主播进行直播，可查看预告列表", 2131232737);
            }
        } else {
            zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        }
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
        VideoCenterActivity.isFlag = true;
    }

    public static /* synthetic */ void lambda$getLivingList$20(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        VideoCenterActivity.isFlag = true;
        if (zhiBoFragment.indexPage != 0) {
            MsLogUtil.m7979d(TAG, "当前页不为直播时，舍弃");
            return;
        }
        zhiBoFragment.setErrorView(0, 8, "接口异常，请稍后再试", 2131232749);
        zhiBoFragment.loadingView.setVisibility(8);
        zhiBoFragment.errorView.setVisibility(8);
        ProgressDialog progressDialog = zhiBoFragment.f18637pd;
        if (progressDialog != null && progressDialog.isShowing()) {
            UIUtils.dismissDialog(zhiBoFragment.activityContext, zhiBoFragment.f18637pd);
        }
        if (zhiBoFragment.isCloseRefresh) {
            zhiBoFragment.textView.setText("刷新完成");
            zhiBoFragment.isCloseRefresh = false;
            zhiBoFragment.superSwipeRefreshLayout.setRefreshing(false);
            zhiBoFragment.progressBar.setVisibility(8);
        }
        VideoCenterActivity.isFlag = true;
    }

    private void getTabList(boolean z, int i) {
        UIUtils.logD(TAG, "切换tab isSelect=" + z + "|pos=" + i);
        this.tabList.clear();
        this.tabLayout.removeAllTabs();
        if (OptionValveUtil.INSTENCE.isShowMultiView()) {
            this.tabList.add("多视角");
        }
        this.tabList.add("精品直播");
        this.tabList.add("慢直播");
        this.tabList.add("直播预告");
        this.tabList.add("直播回放");
        this.tabLayout.setTabMode(this.tabList.size() > 4 ? 0 : 1);
        for (int i2 = 0; i2 < this.tabList.size(); i2++) {
            TabLayout tabLayout = this.tabLayout;
            tabLayout.addTab(tabLayout.newTab().setText(this.tabList.get(i2)));
        }
        if (this.tabList.size() <= 0 || !z) {
            return;
        }
        this.tabLayout.getTabAt(i).select();
    }

    private void qiehuanInit() {
        UIUtils.logD(TAG, "切换Init");
        if (!SystemServiceUtils.netIsAvailable()) {
            VideoCenterActivity.isFlag = true;
            this.errorView.setVisibility(0);
            this.loadingView.setVisibility(8);
            this.superSwipeRefreshLayout.setVisibility(8);
            if (this.isCloseRefresh) {
                this.textView.setText("刷新完成");
                this.superSwipeRefreshLayout.setRefreshing(false);
                this.progressBar.setVisibility(8);
                this.isCloseRefresh = false;
                return;
            }
            return;
        }
        this.mData0.clear();
        this.mData1.clear();
        this.mData2.clear();
        this.mData3.clear();
        this.mData4.clear();
        if (OptionValveUtil.INSTENCE.isShowMultiView()) {
            this.managerAudienceLoadData.getAngleMoreLiveList("0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$dxtHGheZQATRN89dCSFPrc9mHKM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$qiehuanInit$23(ZhiBoFragment.this, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$js5w8hwb1iHIx0HBjJWqRsywkGA
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$qiehuanInit$24(ZhiBoFragment.this, (Throwable) obj);
                }
            });
        } else {
            autoLoadInit();
        }
    }

    public static /* synthetic */ void lambda$qiehuanInit$23(final ZhiBoFragment zhiBoFragment, AudienceDataEntity audienceDataEntity) throws Exception {
        LiveOrFengGuangInfoEntity multiView4FYP = zhiBoFragment.multiView4FYP(audienceDataEntity, "1");
        if ("0000".equals(multiView4FYP.getStatusCode()) && multiView4FYP.getData() != null && multiView4FYP.getData().size() > 0) {
            zhiBoFragment.mData0.addAll(multiView4FYP.getData());
        }
        zhiBoFragment.managerAudienceLoadData.getAngleMorePlayBackList("0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$RrTTgExdPQL9nOdntDodzERc0PE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$qiehuanInit$21(ZhiBoFragment.this, (AudienceDataEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$kq6hOztk0Hpr2shAEFjYauGpDP4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$qiehuanInit$22(ZhiBoFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$qiehuanInit$21(ZhiBoFragment zhiBoFragment, AudienceDataEntity audienceDataEntity) throws Exception {
        LiveOrFengGuangInfoEntity multiView4FYP = zhiBoFragment.multiView4FYP(audienceDataEntity, "2");
        if ("0000".equals(multiView4FYP.getStatusCode()) && multiView4FYP.getData() != null && multiView4FYP.getData().size() > 0) {
            zhiBoFragment.mData0.addAll(multiView4FYP.getData());
        }
        if (zhiBoFragment.mData0.size() <= 0) {
            zhiBoFragment.autoLoadInit();
        } else {
            zhiBoFragment.jiazaiLuoJi();
        }
    }

    public static /* synthetic */ void lambda$qiehuanInit$22(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        zhiBoFragment.indexPage = 4;
        zhiBoFragment.getTabList(true, 0);
    }

    public static /* synthetic */ void lambda$qiehuanInit$24(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        zhiBoFragment.indexPage = 4;
        zhiBoFragment.getTabList(true, 0);
    }

    private void autoLoadInit() {
        this.managerAudienceLoadData.getLivingList("CHOICE", "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$8oMPrEK3yY8cBrhUt4Dqd9isows
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$autoLoadInit$28(ZhiBoFragment.this, (LiveOrFengGuangInfoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$HfSk5f5wKMjt4AaQfKpZaVaT26k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$autoLoadInit$29(ZhiBoFragment.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$autoLoadInit$28(final ZhiBoFragment zhiBoFragment, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && liveOrFengGuangInfoEntity.getData() != null && liveOrFengGuangInfoEntity.getData().size() > 0) {
            zhiBoFragment.mData1.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (zhiBoFragment.mData1.size() <= 0) {
            zhiBoFragment.managerAudienceLoadData.getLivingList("6", "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$tu8FdJf5F2HVpqRGQ9Z1n-6-NoA
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$autoLoadInit$27(ZhiBoFragment.this, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        } else {
            zhiBoFragment.jiazaiLuoJi();
        }
    }

    public static /* synthetic */ void lambda$autoLoadInit$27(final ZhiBoFragment zhiBoFragment, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && liveOrFengGuangInfoEntity.getData() != null && liveOrFengGuangInfoEntity.getData().size() > 0) {
            zhiBoFragment.mData2.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (zhiBoFragment.mData2.size() <= 0) {
            zhiBoFragment.managerAudienceLoadData.getYuGaoList("0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$qL1WsF1IM-DmVF8phJce_i7-_Tw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$autoLoadInit$26(ZhiBoFragment.this, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        } else {
            zhiBoFragment.jiazaiLuoJi();
        }
    }

    public static /* synthetic */ void lambda$autoLoadInit$26(final ZhiBoFragment zhiBoFragment, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && liveOrFengGuangInfoEntity.getData() != null && liveOrFengGuangInfoEntity.getData().size() > 0) {
            zhiBoFragment.mData3.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (zhiBoFragment.mData3.size() <= 0) {
            zhiBoFragment.managerAudienceLoadData.getAllLiveList("ALLPLAYBACK", "1").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$ubJ_JNUeaByq0IwTi5PCexT1Uto
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$autoLoadInit$25(ZhiBoFragment.this, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        } else {
            zhiBoFragment.jiazaiLuoJi();
        }
    }

    public static /* synthetic */ void lambda$autoLoadInit$25(ZhiBoFragment zhiBoFragment, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && (liveOrFengGuangInfoEntity.getData() != null || liveOrFengGuangInfoEntity.getData().size() > 0)) {
            zhiBoFragment.mData4.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (zhiBoFragment.mData4.size() <= 0) {
            zhiBoFragment.indexPage = OptionValveUtil.INSTENCE.isShowMultiView() ? 4 : 0;
            zhiBoFragment.getTabList(true, 0);
            return;
        }
        zhiBoFragment.jiazaiLuoJi();
    }

    public static /* synthetic */ void lambda$autoLoadInit$29(ZhiBoFragment zhiBoFragment, Throwable th) throws Exception {
        zhiBoFragment.indexPage = OptionValveUtil.INSTENCE.isShowMultiView() ? 4 : 0;
        zhiBoFragment.getTabList(true, 0);
    }

    private String getLiveList() {
        List<LiveOrFengGuangInfoEntity.LiveInfoItem> data = this.mAdapter.getData();
        ArrayList arrayList = new ArrayList();
        for (LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem : data) {
            ListDataEntity listDataEntity = new ListDataEntity();
            listDataEntity.setDesc(liveInfoItem.getVideoTitle());
            listDataEntity.setCoverImg(liveInfoItem.getVideoBgImg());
            listDataEntity.setNickName(liveInfoItem.getUserNickName());
            listDataEntity.setJobNumber(liveInfoItem.getUserId());
            listDataEntity.setHeadImg(liveInfoItem.getUserHeadImg());
            listDataEntity.setViewNum(liveInfoItem.getVideoNum());
            arrayList.add(listDataEntity);
        }
        String str = System.currentTimeMillis() + "json";
        SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
        Gson gson = new Gson();
        sharePreferenceUtil.putString(AudienceActivity.JSON_LIST_STR_FILE, str, !(gson instanceof Gson) ? gson.toJson(arrayList) : NBSGsonInstrumentation.toJson(gson, arrayList));
        return str;
    }

    private LiveOrFengGuangInfoEntity multiView4FYP(AudienceDataEntity audienceDataEntity, String str) {
        LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity = new LiveOrFengGuangInfoEntity();
        if (audienceDataEntity != null) {
            liveOrFengGuangInfoEntity.setMessage(audienceDataEntity.getMessage());
            liveOrFengGuangInfoEntity.setStatusCode(audienceDataEntity.getStatusCode());
            liveOrFengGuangInfoEntity.setNextPageNum(audienceDataEntity.getNextPageNum());
            ArrayList arrayList = new ArrayList();
            if (audienceDataEntity.getList() != null && audienceDataEntity.getList().size() > 0) {
                for (ListDataEntity listDataEntity : audienceDataEntity.getList()) {
                    LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem = new LiveOrFengGuangInfoEntity.LiveInfoItem();
                    liveInfoItem.setVideoTitle("2".equals(str) ? listDataEntity.getViewAngleName() : listDataEntity.getDesc());
                    liveInfoItem.setUserNickName(listDataEntity.getNickName());
                    liveInfoItem.setUserHeadImg(listDataEntity.getHeadImg());
                    liveInfoItem.setVideoTime(listDataEntity.getPrevueTime());
                    liveInfoItem.setVideoBgImg("2".equals(str) ? listDataEntity.getViewAngleCover() : listDataEntity.getCoverImg());
                    liveInfoItem.setUserId(listDataEntity.getJobNumber());
                    liveInfoItem.setUserLinkUrl("");
                    liveInfoItem.setVideoNum(listDataEntity.getLiveRoundPraise());
                    liveInfoItem.setVideoLinkUrl("2".equals(str) ? listDataEntity.getViewAngleUrl() : listDataEntity.getLiveUrl());
                    liveInfoItem.setVideoType(str);
                    if ("2".equals(str)) {
                        liveInfoItem.setVideoId(listDataEntity.getId());
                    }
                    liveInfoItem.setMoreViewAngle(listDataEntity.getMoreViewAngle());
                    arrayList.add(liveInfoItem);
                }
            }
            liveOrFengGuangInfoEntity.setData(arrayList);
        }
        return liveOrFengGuangInfoEntity;
    }

    private void jiazaiLuoJi() {
        int i = !OptionValveUtil.INSTENCE.isShowMultiView();
        if (this.mData0.size() > 0 && OptionValveUtil.INSTENCE.isShowMultiView()) {
            this.indexPage = 4;
            getTabList(true, 0);
        } else if (this.mData1.size() > 0) {
            this.indexPage = 0;
            getTabList(true, 1 - i);
        } else if (this.mData2.size() > 0) {
            this.indexPage = 1;
            getTabList(true, 2 - i);
        } else if (this.mData3.size() > 0) {
            this.indexPage = 2;
            getTabList(true, 3 - i);
        } else if (this.mData4.size() > 0) {
            this.indexPage = 3;
            getTabList(true, 4 - i);
        }
    }

    private void initBanner(final List<LiveBannerEntity> list) {
        this.vpBanner.setHolderCreator(new HolderCreator() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$3fM87GZKr2tWnjds1oiFgYimZ94
            @Override // com.zhpan.bannerview.holder.HolderCreator
            public final ViewHolder createViewHolder() {
                return ZhiBoFragment.lambda$initBanner$30(ZhiBoFragment.this);
            }
        });
        this.vpBanner.setIndicatorWidth(BannerUtils.dp2px(5.0f), BannerUtils.dp2px(5.0f));
        this.vpBanner.setIndicatorHeight(BannerUtils.dp2px(3.0f));
        this.vpBanner.setIndicatorGap(BannerUtils.dp2px(4.0f));
        this.vpBanner.setIndicatorColor(Color.parseColor("#EEEEEE"), Color.parseColor("#E60027"));
        this.vpBanner.setInterval(3000);
        this.vpBanner.setPageStyle(0);
        this.vpBanner.setOnPageClickListener(new BannerViewPager.OnPageClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$NnhFPHLLnG3iwiicAnn1z4vILA8
            @Override // com.zhpan.bannerview.BannerViewPager.OnPageClickListener
            public final void onPageClick(int i) {
                ZhiBoFragment.lambda$initBanner$31(ZhiBoFragment.this, list, i);
            }
        });
        this.vpBanner.create(list);
    }

    public static /* synthetic */ ViewHolder lambda$initBanner$30(ZhiBoFragment zhiBoFragment) {
        return new BannerViewHolder();
    }

    public static /* synthetic */ void lambda$initBanner$31(ZhiBoFragment zhiBoFragment, List list, int i) {
        zhiBoFragment.IntentGo(((LiveBannerEntity) list.get(i)).getLinkUrl());
        zhiBoFragment.setLog("点击banner", "18", "banner", "", "", "", "1", "2", "", "banner", "", "", "", "", "");
    }

    private View createHeaderView() {
        View inflate = LayoutInflater.from(this.superSwipeRefreshLayout.getContext()).inflate(2131493602, (ViewGroup) null);
        this.progressBar = (ProgressBar) inflate.findViewById(2131298189);
        this.textView = (TextView) inflate.findViewById(2131298766);
        this.textView.setText("下拉刷新");
        this.imageView = (ImageView) inflate.findViewById(2131297282);
        this.imageView.setVisibility(8);
        this.imageView.setImageResource(2131231261);
        this.progressBar.setVisibility(8);
        return inflate;
    }

    private void initAdapterEmptyView() {
        View inflate = LayoutInflater.from(this.activityContext).inflate(2131493254, (ViewGroup) null);
        this.rl_loading = (RelativeLayout) inflate.findViewById(2131298356);
        this.zhibo_img = (ImageView) inflate.findViewById(2131299867);
        this.zhibo_tv = (TextView) inflate.findViewById(2131299868);
        this.zhibo_chakan = (TextView) inflate.findViewById(2131299865);
        this.zhibo_chakan.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.ZhiBoFragment.5
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                ZhiBoFragment.this.tabLayout.getTabAt(ZhiBoFragment.this.getSelectedIndex("直播预告")).select();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.rl_loading.setVisibility(8);
        this.mAdapter.setEmptyView(inflate);
    }

    private void setErrorView(int i, int i2, String str, int i3) {
        this.rl_loading.setVisibility(i);
        this.zhibo_chakan.setVisibility(i2);
        this.zhibo_tv.setText(str);
        this.zhibo_img.setBackgroundResource(i3);
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected void lazyLoad() {
        UIUtils.logD(TAG, "lazyLoad");
        Disposable disposable = this.loader;
        if (disposable != null) {
            disposable.dispose();
            this.loader = null;
        }
        this.loader = Observable.timer(100L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$vhuX7dAJWG5eLlCYJ3iYSpI5chw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ZhiBoFragment.lambda$lazyLoad$32(ZhiBoFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$lazyLoad$32(ZhiBoFragment zhiBoFragment, Long l) throws Exception {
        boolean z;
        if (zhiBoFragment.isResult) {
            zhiBoFragment.isResult = false;
        } else if (!zhiBoFragment.activityContext.getCurrentShowCode().equals("zhibo")) {
            UIUtils.logD(TAG, "直播==拦截");
        } else {
            zhiBoFragment.setLog("浏览页面", "1", "", "", "", "", "1", "2", "负一屏直播首页", OptionValveUtil.INSTENCE.isShowMultiView() ? "多视角" : "精品直播", "", "", "", "", "");
            BannerViewPager bannerViewPager = zhiBoFragment.vpBanner;
            if (bannerViewPager != null && bannerViewPager.getList() != null && zhiBoFragment.vpBanner.getList().size() == 0) {
                zhiBoFragment.getBannerList();
            }
            List<String> list = zhiBoFragment.tabList;
            if (list != null && list.size() == 0) {
                zhiBoFragment.qiehuanInit();
            }
            if (zhiBoFragment.isYuGaoResult && zhiBoFragment.indexPage == 2) {
                zhiBoFragment.textView.setText("正在刷新...");
                zhiBoFragment.imageView.setVisibility(8);
                z = false;
                zhiBoFragment.progressBar.setVisibility(0);
                zhiBoFragment.pageCodeString = "0";
                zhiBoFragment.isCloseRefresh = true;
                zhiBoFragment.mData.clear();
                zhiBoFragment.getLivingList("YuGao", zhiBoFragment.pageCodeString);
            } else {
                z = false;
            }
            zhiBoFragment.isYuGaoResult = z;
            Disposable disposable = zhiBoFragment.loader;
            if (disposable != null) {
                disposable.dispose();
                zhiBoFragment.loader = null;
            }
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent.hasExtra(AudienceMainActivity.RING_LIST)) {
            this.isResult = true;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BannerViewHolder implements ViewHolder<LiveBannerEntity> {
        private ImageView bannerView;

        public BannerViewHolder() {
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public View createView(ViewGroup viewGroup, Context context, int i) {
            View inflate = LayoutInflater.from(context).inflate(2131493541, viewGroup, false);
            this.bannerView = (ImageView) inflate.findViewById(2131296501);
            return inflate;
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public void onBind(Context context, LiveBannerEntity liveBannerEntity, int i, int i2) {
            Glide.with(context).load(liveBannerEntity.getImgSrc()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131232739).error(2131232739).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(UIUtils.dip2px(5.0f), 0, RoundedCornersTransformation.CornerType.ALL)))).into(this.bannerView);
        }
    }

    private void setLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15) {
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
            hashMap.put("content_position", "3");
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
            hashMap.put("tab_id", "zhibo");
            hashMap.put("tab_name", "直播");
            hashMap.put("operateDesc", "");
            hashMap.put("tjpara", "");
            MsLogUtil.m7979d("日志检验", simpleDateFormat.format(new Date(SystemTimeUtil.currentTimeMillis())));
        } catch (Resources.NotFoundException e) {
            e = e;
        }
        try {
            this.managerAudienceLoadData.setLogPoit(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$ZhiBoFragment$_z1hB315mKM3eoEh0e0PobWMEf4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ZhiBoFragment.lambda$setLog$33((String) obj);
                }
            });
        } catch (Resources.NotFoundException e2) {
            e = e2;
            e.printStackTrace();
        }
    }

    public void refresh() {
        setLog("点击按钮", "19", "刷新", "", "", "", "1", "2", "", "", "", "", "", "", "");
        this.textView.setText("正在刷新...");
        this.imageView.setVisibility(8);
        this.progressBar.setVisibility(0);
        this.superSwipeRefreshLayout.setRefreshing(true);
        CoordinatorLayout.Behavior behavior = ((CoordinatorLayout.LayoutParams) this.appbar.getLayoutParams()).getBehavior();
        if (behavior instanceof AppBarLayout.Behavior) {
            this.tabLayout.getY();
            ((AppBarLayout.Behavior) behavior).setTopAndBottomOffset(2);
        }
        this.mRecyclerView.scrollToPosition(0);
        this.pageCodeString = "0";
        this.isCloseRefresh = true;
        this.mData.clear();
        int i = this.indexPage;
        if (i == 0) {
            getLivingList("CHOICE", this.pageCodeString);
        } else if (i == 1) {
            getLivingList("6", this.pageCodeString);
        } else if (i == 2) {
            getLivingList("YuGao", this.pageCodeString);
        } else if (i == 3) {
            this.pageCodeString = "1";
            getLivingList("ALLPLAYBACK", this.pageCodeString);
        } else if (i == 4) {
            getLivingList("MultiView", this.pageCodeString);
        }
    }

    private void IntentGo(String str) {
        if (str.startsWith(URLSet.getLanjieZhiboUrl()) || str.contains("zhibo/index.html#/huifangplayer?")) {
            IntentManager.handleLocal(this.activityContext, "", str);
            return;
        }
        Intent intent = new Intent(getActivity(), WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        startActivityForResult(intent, 4000);
        this.isYuGaoResult = true;
    }
}
