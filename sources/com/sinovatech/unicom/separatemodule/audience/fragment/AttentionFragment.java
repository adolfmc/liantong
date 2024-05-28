package com.sinovatech.unicom.separatemodule.audience.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.SimpleItemAnimator;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.SlidingConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionAnchorVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.HelpBtnInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.PlayBackPayInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoMoreConfigEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.util.ADCallBack;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.util.SpringScaleInterpolator;
import com.sinovatech.unicom.separatemodule.audience.view.AttentionAnchorHeader;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsDialog;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import com.sinovatech.unicom.separatemodule.audience.view.LivePvResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.MyGestureView;
import com.sinovatech.unicom.separatemodule.audience.view.OrderVideoCallingResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.ToastDialogManager;
import com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog;
import com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentActivity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener;
import com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AttentionFragment extends BaseFragment {
    private static final String ANCHOR_KEY = "anchorsInfo";
    public static final int REQUEST_CODE = 1000;
    private static final String STATUS_Y = "Y";
    private static final String SUCCESS_STATUS = "0000";
    private static final String TAG = "AttentionFragment";
    public NBSTraceUnit _nbs_trace;
    private AudienceMainActivity activityContext;
    private View anchorNews;
    private AttentionAnchorVideoEntity anchors;
    private Disposable autoJion;
    private View barRoot;
    private FrameLayout baseLayout;
    private BDCloudVideoView bdCloudVideoView;
    private SlidingConsumer consumer;
    private List<GoodListEntity> currentShopList;
    private LikeView.TouchCallBack doubleClickListener;
    private FrameLayout flAdPauseView;
    private FrameLayout flAdPauseViewLandscape;
    private FrameLayout flAnchorList;
    private FrameLayout flArea;
    private View flInputPassword;
    private FrameLayout flProgressBarAreaLandscape;
    private View fragmentCacheView;
    private AttentionAnchorHeader header;
    private boolean isFlag;
    private boolean isLoadMore;
    private boolean isNeedCode;
    private boolean isSetting;
    private boolean isShowMoreBtn;
    private boolean isStartTrackingTouch;
    private ImageView ivBack;
    private ImageView ivBarLandscapeBtn;
    private View ivBtnPlay;
    private View ivBtnPlay2;
    private ImageView ivPlayIcon;
    private String jobNumber;
    private LikeView likeView;
    private String liveTypeText;
    private String liveUrl;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private LinearLayout llEmpty;
    private View llLandscapeTitle;
    private LinearLayout llPayInfo;
    private LinearLayout llPayTipe;
    private LinearLayout llProgressBar;
    private LinearLayout llProgressTextLine;
    private LinearLayout llTrySeeDialog;
    private Disposable logTimer;
    private AttentionItemAdapter mAdapter;
    private ViewPagerLayoutManager mLayoutManager;
    private List<VideoMoreConfigEntity> mMoreConfigs;
    private RecyclerView mRecyclerView;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private View markImg;
    private int oldSlotCount;
    private int pauseCount;
    private Disposable payInfoCountDown;

    /* renamed from: pd */
    private CustomePorgressDialog f18479pd;
    private Disposable playProgressUpdater;
    private String playedTime;
    private String productName;
    private Disposable reSetVideo;
    private int realPosition;
    private RelativeLayout rlAdPauseView;
    private RelativeLayout rlAdPauseViewLandscape;
    private View rlInfoArea;
    private View roomInfo;
    private SeekBar sbProgress;
    private int slotCount;
    private String slotTime;
    private String startTime;
    private int timePayInfo;
    private TextView tvBtnPay4PB;
    private TextView tvEndTime;
    private TextView tvFreeTimeTips;
    private TextView tvNoOrder;
    private TextView tvOrder;
    private TextView tvPayTips;
    private TextView tvStartTime;
    private View vBarLandscapeBg;
    private View vBarLandscapeEnd;
    private String videoId;
    private String videoTime;
    private String pageNum = "1";
    private int mCurrentItem = 0;
    private List<AttentionItemEntity> list = new ArrayList();
    private String fromType = "audienceMain";
    public String helpUrl = "https://m.10155.com/h5/mactivity/woapphall.html#/instructions";
    public boolean isShowHelpBtn = true;
    private Handler addPlayer2UIHandler = new Handler(new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$pLkNsidoW1Ldud65_YwZa8Lu49g
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return AttentionFragment.lambda$new$33(AttentionFragment.this, message);
        }
    });
    private Handler playTime = new Handler(new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.7
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i != 3241) {
                if (i != 5000 || AttentionFragment.this.llLandscapeTitle == null || AttentionFragment.this.flProgressBarAreaLandscape == null) {
                    return false;
                }
                AttentionFragment.this.llLandscapeTitle.setVisibility(8);
                AttentionFragment.this.flProgressBarAreaLandscape.setVisibility(8);
                return false;
            } else if (AttentionFragment.this.bdCloudVideoView != null) {
                if (AttentionFragment.this.bdCloudVideoView.isPlaying()) {
                    AttentionFragment.access$3108(AttentionFragment.this);
                }
                AttentionFragment.this.startTiming();
                return false;
            } else {
                return false;
            }
        }
    });
    private final List<View> pauseAdList = new ArrayList();
    private Handler playVideoHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.10
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            View findViewByPosition = AttentionFragment.this.mLayoutManager.findViewByPosition(AttentionFragment.this.mCurrentItem);
            if (findViewByPosition == null) {
                return false;
            }
            findViewByPosition.findViewById(2131296393).setVisibility(0);
            AttentionFragment attentionFragment = AttentionFragment.this;
            attentionFragment.bdCloudVideoView = attentionFragment.activityContext.playVideo(AttentionFragment.this.liveUrl);
            AttentionFragment.this.setVideoSizeView();
            return false;
        }
    });

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getRoomInfo$45(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$hiBoardLog$35(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$showGoods$57(Boolean bool) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$zanVideo$36(String str) throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    void lazyLoad() {
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment");
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    public void showLoading() {
    }

    static /* synthetic */ int access$3108(AttentionFragment attentionFragment) {
        int i = attentionFragment.realPosition;
        attentionFragment.realPosition = i + 1;
        return i;
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
        this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    private void getMoreConfigInfo() {
        try {
            this.managerAudienceLoadData.getVideoRingRule("smallVideo").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$ITPVO4xI6ek4goKIhtnZa593YvA
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getMoreConfigInfo$0(AttentionFragment.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$bdVfy4Bx75i2_hXOr7BD8oIUQtM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getMoreConfigInfo$1(AttentionFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getMoreConfigInfo$0(AttentionFragment attentionFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode()) && baseVideoEntity.getData() != null && ((List) baseVideoEntity.getData()).size() > 0) {
            attentionFragment.mMoreConfigs = (List) baseVideoEntity.getData();
            attentionFragment.isShowMoreBtn = true;
            AttentionItemAdapter attentionItemAdapter = attentionFragment.mAdapter;
            if (attentionItemAdapter != null) {
                attentionItemAdapter.setShowMoreBtn(true);
                attentionFragment.mAdapter.notifyItemChanged(attentionFragment.mCurrentItem);
                return;
            }
            return;
        }
        AttentionItemAdapter attentionItemAdapter2 = attentionFragment.mAdapter;
        if (attentionItemAdapter2 != null) {
            attentionItemAdapter2.setShowMoreBtn(false);
        }
    }

    public static /* synthetic */ void lambda$getMoreConfigInfo$1(AttentionFragment attentionFragment, Throwable th) throws Exception {
        AttentionItemAdapter attentionItemAdapter = attentionFragment.mAdapter;
        if (attentionItemAdapter != null) {
            attentionItemAdapter.setShowMoreBtn(false);
        }
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment", viewGroup);
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment");
            return view2;
        }
        View inflate = layoutInflater.inflate(2131492973, viewGroup, false);
        this.baseLayout = (FrameLayout) inflate.findViewById(2131297006);
        this.anchorNews = inflate.findViewById(2131298865);
        this.markImg = inflate.findViewById(2131297428);
        GlideApp.with(this).load((Integer) 2131232734).into((ImageView) inflate.findViewById(2131297427));
        getMoreConfigInfo();
        getHelpBtnInfo();
        this.llEmpty = (LinearLayout) inflate.findViewById(2131297711);
        this.llEmpty.setVisibility(8);
        this.mRecyclerView = (RecyclerView) inflate.findViewById(2131296463);
        this.flAnchorList = (FrameLayout) inflate.findViewById(2131296986);
        RecyclerView.RecycledViewPool recycledViewPool = new RecyclerView.RecycledViewPool();
        recycledViewPool.setMaxRecycledViews(0, 10);
        this.mRecyclerView.setRecycledViewPool(recycledViewPool);
        this.mRecyclerView.getItemAnimator().setChangeDuration(0L);
        ((SimpleItemAnimator) this.mRecyclerView.getItemAnimator()).setSupportsChangeAnimations(false);
        this.mLayoutManager = new ViewPagerLayoutManager(this.activityContext, 1);
        this.mRecyclerView.setLayoutManager(this.mLayoutManager);
        this.mAdapter = new AttentionItemAdapter(this.activityContext, this.list, new C83241(), new AttentionItemAdapter.PositionCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$eonMZUjWtrDqaCHliIRuf17yVhk
            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.AttentionItemAdapter.PositionCallBack
            public final void playVideo(int i, AttentionItemEntity attentionItemEntity) {
                AttentionFragment.this.startPlay(i);
            }
        });
        this.mAdapter.setShowHelpBtn(this.isShowHelpBtn);
        this.mAdapter.setShowMoreBtn(this.isShowMoreBtn);
        this.mRecyclerView.setAdapter(this.mAdapter);
        this.mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.2
            @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
            public void onLayoutComplete() {
            }

            @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
            public void onPageRelease(boolean z, int i) {
            }

            @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
            public void onPageSelected(int i, boolean z) {
                if (AttentionFragment.this.mCurrentItem != i) {
                    AttentionFragment.this.mCurrentItem = i;
                    AttentionFragment.this.leaveRoom();
                    AttentionFragment.this.mAdapter.setCurrentPosition(i);
                }
                if ("-1".equals(AttentionFragment.this.pageNum) || !z || AttentionFragment.this.isLoadMore) {
                    return;
                }
                AttentionFragment.this.isLoadMore = true;
                AttentionFragment.this.loadData();
            }
        });
        this.f18479pd = new CustomePorgressDialog(this.activityContext);
        this.f18479pd.setMessage("加载中");
        this.f18479pd.setCanceledOnTouchOutside(true);
        this.f18479pd.setCancelable(true);
        this.fragmentCacheView = inflate;
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment");
        return view3;
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C83241 implements SmallVideoAdapter.ItemClickedListener {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$shagnpinInfo$0(Boolean bool) throws Exception {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void back() {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void clicked(int i, SmallVideoEntity.Data data) {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void guanzhu(int i, SmallVideoEntity.Data data) {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void toWelfareCenter() {
        }

        C83241() {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void dianzan(int i, SmallVideoEntity.Data data) {
            TextView textView;
            try {
                View findViewByPosition = AttentionFragment.this.mLayoutManager.findViewByPosition(i);
                ImageView imageView = null;
                if (findViewByPosition != null) {
                    imageView = (ImageView) findViewByPosition.findViewById(2131297529);
                    textView = (TextView) findViewByPosition.findViewById(2131299157);
                } else {
                    textView = null;
                }
                if (data.isHasZan()) {
                    AttentionFragment.this.zanVideo(data.getVideoId(), "N");
                    if (imageView != null) {
                        imageView.setImageResource(2131231683);
                    }
                    data.setHasZan(false);
                    data.setVideoPraiseNum((Integer.parseInt(data.getVideoPraiseNum()) - 1) + "");
                    AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "取消点赞", i);
                } else {
                    AttentionFragment.this.zanVideo(data.getVideoId(), AttentionFragment.STATUS_Y);
                    if (imageView != null) {
                        imageView.setImageResource(2131231684);
                    }
                    AttentionFragment.this.showZanAnimation(imageView);
                    data.setHasZan(true);
                    String videoPraiseNum = data.getVideoPraiseNum();
                    if (TextUtils.isEmpty(videoPraiseNum)) {
                        videoPraiseNum = "0";
                    }
                    data.setVideoPraiseNum((Integer.parseInt(videoPraiseNum) + 1) + "");
                    AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "点赞", i);
                }
                if (textView != null) {
                    textView.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void pinglun(int i, SmallVideoEntity.Data data) {
            try {
                Intent intent = new Intent(AttentionFragment.this.activityContext, LiveCommentActivity.class);
                intent.putExtra("id", data.getVideoId());
                intent.putExtra("newsTitle", data.getViewTitle());
                intent.putExtra("subTitle", "");
                AttentionFragment.this.startActivityForResult(intent, 2000);
                AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "点击评论", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void zhuanfa(int i, SmallVideoEntity.Data data, boolean z) {
            JSONObject jSONObject = new JSONObject();
            String viewTitle = data.getViewTitle();
            String str = "来自中国联通APP-" + data.getUserName() + "主播";
            data.getVideoUrl();
            String videoImg = data.getVideoImg();
            if (TextUtils.isEmpty(videoImg)) {
                videoImg = data.getTranscodeImg();
            }
            String videoTag = TextUtils.isEmpty(data.getVideoTag()) ? "创新·与智慧同行" : data.getVideoTag();
            String str2 = null;
            try {
                String shareVideo = URLSet.shareVideo(data.getVideoId());
                str2 = "wechat,wechatmoments,qq,qzone";
                jSONObject.put("shareType", "url");
                jSONObject.put("shareTitle", viewTitle);
                jSONObject.put("shareContent", videoTag);
                jSONObject.put("shareURL", shareVideo);
                jSONObject.put("shareIconURL", videoImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            try {
                ShareManager.ShowShareDialog(AttentionFragment.this.activityContext, str2, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.1.1
                    @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                    public void onCancel(String str3) {
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                    public void onComplete(String str3) {
                    }

                    @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                    public void onError(String str3, String str4) {
                    }
                });
                AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "分享", i);
                AttentionFragment.this.hiBoardLog("5", i);
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }

        /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment$1$2 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C83262 implements OnDialogBtnClickListener {
            final /* synthetic */ SmallVideoEntity.Data val$entity;

            /* JADX INFO: Access modifiers changed from: package-private */
            public static /* synthetic */ void lambda$onClickGoodsLook$0(Boolean bool) throws Exception {
            }

            C83262(SmallVideoEntity.Data data) {
                this.val$entity = data;
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickGoodsLook(GoodListEntity goodListEntity) {
                AttentionFragment.this.managerAudienceLoadData.goodsLog(this.val$entity.getUserId(), goodListEntity.getId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$1$2$vZGH7jbHXSgicLiJkW72SCAn6qk
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.C83241.C83262.lambda$onClickGoodsLook$0((Boolean) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                Intent intent = new Intent(AttentionFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(goodListEntity.getGoodsUrl());
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                AttentionFragment.this.startActivityForResult(intent, 3001);
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickOrder() {
                IntentManager.gotoWebViewActivity(AttentionFragment.this.activityContext, URLSet.liveOrderMenu(), "我的订单");
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void shangpin(int i, SmallVideoEntity.Data data) {
            if (data.getGoodsData() == null || data.getGoodsData().size() <= 0) {
                return;
            }
            AudienceGoodsDialog.show(AttentionFragment.this.activityContext, data.getGoodsData(), new C83262(data));
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void shagnpinInfo(int i, SmallVideoEntity.Data data) {
            try {
                AttentionFragment.this.managerAudienceLoadData.goodsLog(data.getUserId(), data.getGoodsId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$1$ecuSuavfP7kY0ISqML6BbG0pmfI
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.C83241.lambda$shagnpinInfo$0((Boolean) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                Intent intent = new Intent(AttentionFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(data.getGoodsLink());
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                AttentionFragment.this.startActivityForResult(intent, 3001);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void zhuboxiangqing(int i, SmallVideoEntity.Data data) {
            try {
                String userIndexUrl = data.getUserIndexUrl();
                int videoType = data.getVideoType();
                if (TextUtils.isEmpty(userIndexUrl)) {
                    return;
                }
                Intent intent = new Intent(AttentionFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(AttentionFragment.this.addChannel(userIndexUrl, videoType == 1 ? "视频彩铃" : "小视频"));
                webParamsEntity.setType(AttentionFragment.this.fromType);
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                AttentionFragment.this.startActivityForResult(intent, 1221);
                AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "进入个人中心", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void backPortraitScreen() {
            AttentionFragment attentionFragment = AttentionFragment.this;
            attentionFragment.toggleFullScreen(attentionFragment.activityContext, AttentionFragment.this.mCurrentItem);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void setRing(int i, SmallVideoEntity.Data data, String str) {
            try {
                AttentionFragment.this.setVideoRingtone(data.getVideoId(), str, data);
                AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "设置彩铃", i);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void onPreview(boolean z, SmallVideoEntity.Data data) {
            try {
                int i = 8;
                if (!AttentionFragment.this.bdCloudVideoView.isPlaying()) {
                    AttentionFragment.this.bdCloudVideoView.start();
                    AttentionFragment.this.ivBtnPlay.setVisibility(8);
                    AttentionFragment.this.ivBtnPlay2.setVisibility(8);
                    AttentionFragment.this.rlAdPauseView.setVisibility(8);
                    AttentionFragment.this.rlAdPauseViewLandscape.setVisibility(8);
                    AttentionFragment.this.setLogPoit(AttentionFragment.this.bdCloudVideoView.getTag().toString(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "点击播放", AttentionFragment.this.list.indexOf(data));
                }
                AttentionFragment.this.activityContext.setTabVisibility(z ? 8 : 0);
                AttentionFragment.this.mLayoutManager.setScrollEnabled(!z);
                if (AttentionFragment.this.ivBack != null) {
                    LikeView likeView = AttentionFragment.this.likeView;
                    if (!z) {
                        i = 0;
                    }
                    likeView.setVisibility(i);
                }
                if (z) {
                    AttentionFragment.this.hiBoardLog("19", AttentionFragment.this.list.indexOf(data));
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void showMore(final SmallVideoEntity.Data data) {
            if (AttentionFragment.this.mMoreConfigs != null) {
                VideoRingMoreDialog.show(AttentionFragment.this.activityContext, AttentionFragment.this.mMoreConfigs, AttentionFragment.this.fromType, new VideoRingMoreDialog.CallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.1.3
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void click(String str) {
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void pingLun() {
                        try {
                            Intent intent = new Intent(AttentionFragment.this.activityContext, LiveCommentActivity.class);
                            intent.putExtra("id", data.getVideoId());
                            intent.putExtra("newsTitle", data.getViewTitle());
                            intent.putExtra("subTitle", "");
                            AttentionFragment.this.startActivityForResult(intent, 2000);
                            AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "点击评论", AttentionFragment.this.mAdapter.getData().indexOf(data));
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void share() {
                        JSONObject jSONObject = new JSONObject();
                        String viewTitle = data.getViewTitle();
                        String str = "来自中国联通APP-" + data.getUserName() + "主播";
                        data.getVideoUrl();
                        String videoImg = data.getVideoImg();
                        if (TextUtils.isEmpty(videoImg)) {
                            videoImg = data.getTranscodeImg();
                        }
                        String videoTag = TextUtils.isEmpty(data.getVideoTag()) ? "创新·与智慧同行" : data.getVideoTag();
                        String str2 = null;
                        try {
                            String shareVideo = URLSet.shareVideo(data.getVideoId());
                            str2 = "wechat,wechatmoments,qq,qzone";
                            jSONObject.put("shareType", "url");
                            jSONObject.put("shareTitle", viewTitle);
                            jSONObject.put("shareContent", videoTag);
                            jSONObject.put("shareURL", shareVideo);
                            jSONObject.put("shareIconURL", videoImg);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        try {
                            ShareManager.ShowShareDialog(AttentionFragment.this.activityContext, str2, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.1.3.1
                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onCancel(String str3) {
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onComplete(String str3) {
                                }

                                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                                public void onError(String str3, String str4) {
                                }
                            });
                            int indexOf = AttentionFragment.this.mAdapter.getData().indexOf(data);
                            AttentionFragment.this.setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "分享", indexOf);
                            AttentionFragment.this.hiBoardLog("5", indexOf);
                        } catch (Exception e2) {
                            e2.printStackTrace();
                        }
                    }
                });
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void showHelp() {
            IntentManager.gotoWebViewActivity(AttentionFragment.this.activityContext, AttentionFragment.this.helpUrl, "");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void playVideo(int i) {
            AttentionFragment.this.startPlay(i);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String addChannel(String str, String str2) {
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

    public void setAttentionAnchors(final AttentionAnchorVideoEntity attentionAnchorVideoEntity) {
        try {
            this.anchors = attentionAnchorVideoEntity;
            if (this.anchorNews != null) {
                this.anchorNews.setVisibility(8);
                if (attentionAnchorVideoEntity == null || attentionAnchorVideoEntity.getAnchors() == null || attentionAnchorVideoEntity.getAnchors().size() <= 0) {
                    return;
                }
                this.header = new AttentionAnchorHeader(this.activityContext, attentionAnchorVideoEntity.getAnchors());
                ViewGroup viewGroup = (ViewGroup) this.header.getView().getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.flAnchorList.addView(this.header.getView());
                this.flAnchorList.setVisibility(8);
                View inflate = LayoutInflater.from(this.activityContext).inflate(2131493303, (ViewGroup) null);
                this.flArea = (FrameLayout) inflate.findViewById(2131296987);
                this.consumer = new SlidingConsumer();
                ((SlidingConsumer) SmartSwipe.wrap(this.mRecyclerView).addConsumer(this.consumer)).setRelativeMoveFactor(0.0f).setTopDrawerView(inflate).setScrimColor(16777216).addListener(new SimpleSwipeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.3
                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        AttentionFragment.this.anchorNews.setVisibility(0);
                    }

                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        if (AttentionFragment.this.flArea.getChildCount() == 0) {
                            ViewGroup viewGroup2 = (ViewGroup) AttentionFragment.this.header.getView().getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeAllViews();
                            }
                            AttentionFragment.this.flArea.addView(AttentionFragment.this.header.getView());
                        }
                    }

                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeStart(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        AttentionFragment.this.anchorNews.setVisibility(8);
                        if (AttentionFragment.this.flAnchorList.getChildCount() == 0) {
                            ViewGroup viewGroup2 = (ViewGroup) AttentionFragment.this.header.getView().getParent();
                            if (viewGroup2 != null) {
                                viewGroup2.removeAllViews();
                            }
                            AttentionFragment.this.flAnchorList.addView(AttentionFragment.this.header.getView());
                            AttentionFragment.this.flAnchorList.setVisibility(0);
                        }
                    }
                });
                this.anchorNews.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$izQScw05RFet3-XH1raaIVv5kwo
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AttentionFragment.lambda$setAttentionAnchors$3(AttentionFragment.this, attentionAnchorVideoEntity, view);
                    }
                });
                this.header.getAdapter().setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$yzq2-oFHw2K2uTBwtOFvRQNYfRM
                    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                        AttentionFragment.lambda$setAttentionAnchors$4(AttentionFragment.this, attentionAnchorVideoEntity, baseQuickAdapter, view, i);
                    }
                });
                this.mRecyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$1-B57mcxUDkMq0BVCVqQQw8hoLU
                    @Override // java.lang.Runnable
                    public final void run() {
                        AttentionFragment.this.consumer.setTopOpen();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setAttentionAnchors$3(AttentionFragment attentionFragment, AttentionAnchorVideoEntity attentionAnchorVideoEntity, View view) {
        attentionFragment.consumer.open(true, 4);
        PvCurrencyLogUtils.pvLogLive("", 2, attentionAnchorVideoEntity.getAnchors().get(0).getNikeName(), "新内容", "直播预览页", attentionAnchorVideoEntity.getAnchors().get(0).getNikeName(), "关注");
    }

    public static /* synthetic */ void lambda$setAttentionAnchors$4(AttentionFragment attentionFragment, AttentionAnchorVideoEntity attentionAnchorVideoEntity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        App.getSharePreferenceUtil().putString(AudienceActivity.JSON_LIST_STR_FILE, ANCHOR_KEY, attentionFragment.getAttentionAnchorsJson(i));
        Intent intent = new Intent(attentionFragment.getActivity(), AudienceActivity.class);
        intent.putExtra("from", "AttentionAnchors");
        intent.putExtra("listStr", ANCHOR_KEY);
        intent.putExtra("shareUserNumSc", "");
        intent.putExtra("index", i + "");
        intent.putExtra("pageNum", attentionAnchorVideoEntity.getNextPageNum());
        attentionFragment.startActivityForResult(intent, 3001);
        String nikeName = attentionAnchorVideoEntity.getAnchors().get(i).getNikeName();
        PvCurrencyLogUtils.pvLogLive("", 2, nikeName, "新内容-" + attentionAnchorVideoEntity.getAnchors().get(i).getNikeName(), "直播预览页", attentionAnchorVideoEntity.getAnchors().get(i).getNikeName(), "关注");
    }

    private String getAttentionAnchorsJson(int i) {
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("position", i);
            jSONObject.put("nextPageNum", this.anchors.getNextPageNum());
            JSONArray jSONArray = new JSONArray();
            for (int i2 = 0; i2 < this.anchors.getAnchors().size(); i2++) {
                JSONObject jSONObject2 = new JSONObject();
                AttentionVideoEntity attentionVideoEntity = this.anchors.getAnchors().get(i2);
                jSONObject2.put("dataType", attentionVideoEntity.getDataType());
                jSONObject2.put("jobNumber", attentionVideoEntity.getJobNumber());
                JSONArray jSONArray2 = new JSONArray();
                for (int i3 = 0; i3 < attentionVideoEntity.getIds().length; i3++) {
                    jSONArray2.put(attentionVideoEntity.getIds()[i3]);
                }
                jSONObject2.put("ids", jSONArray2);
                jSONArray.put(i2, jSONObject2);
            }
            jSONObject.put("data", jSONArray);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setVideoRingtone(final String str, String str2, final SmallVideoEntity.Data data) {
        try {
            if (this.isSetting) {
                return;
            }
            this.isSetting = true;
            this.f18479pd.show();
            this.managerAudienceLoadData.setVideoRing(str, "1").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$scvEWLkM9l8ktU6W-gN2Tz21tJ0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$setVideoRingtone$6(AttentionFragment.this, str, data, (Map) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$G8OrRP4oiVPf-6bPv1Ppa5IxpGo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$setVideoRingtone$7(AttentionFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$setVideoRingtone$6(AttentionFragment attentionFragment, String str, SmallVideoEntity.Data data, Map map) throws Exception {
        char c;
        attentionFragment.isSetting = false;
        attentionFragment.f18479pd.dismiss();
        String str2 = (String) map.get("statusCode");
        String str3 = (String) map.get("message");
        int hashCode = str2.hashCode();
        if (hashCode != 1627548) {
            switch (hashCode) {
                case 1477632:
                    if (str2.equals("0000")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1477633:
                    if (str2.equals("0001")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                default:
                    c = 65535;
                    break;
            }
        } else {
            if (str2.equals("5100")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                attentionFragment.showSetRingResult(str, data.getViewTitle());
                return;
            case 1:
                attentionFragment.showOrderRingDialog(data);
                return;
            case 2:
                Toast toast = new Toast(attentionFragment.activityContext);
                View inflate = LayoutInflater.from(attentionFragment.activityContext).inflate(2131493392, (ViewGroup) null);
                ((TextView) inflate.findViewById(2131296893)).setText("请求频繁，请稍后再试");
                toast.setView(inflate);
                toast.setGravity(17, 0, 0);
                toast.show();
                break;
        }
        ToastUtil.showToast(str3);
    }

    public static /* synthetic */ void lambda$setVideoRingtone$7(AttentionFragment attentionFragment, Throwable th) throws Exception {
        attentionFragment.isSetting = false;
        MsLogUtil.m7979d(TAG, th.getMessage());
        attentionFragment.f18479pd.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRingtoneLibraryFull() {
        try {
            CustomDialogManager.show(this.activityContext, "您设置的铃音库已满,建议您进行清理", "去清理", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$_yUoguFLpHlNOs-pHL-1qA4YxvY
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public final void onClickOk() {
                    AttentionFragment.lambda$showRingtoneLibraryFull$8(AttentionFragment.this);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showRingtoneLibraryFull$8(AttentionFragment attentionFragment) {
        Intent intent = new Intent(attentionFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl("https://m.10155.com/h5/mactivity/woapphall.html#/my/ring");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        attentionFragment.startActivityForResult(intent, 3001);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getSmsCode(EditText editText, SmallVideoEntity.Data data) {
        try {
            ObservableSubscribeProxy<String> videoRingCode = this.managerAudienceLoadData.getVideoRingCode();
            Objects.requireNonNull(editText);
            videoRingCode.subscribe(new $$Lambda$A5_pOEfwd7wMXr3XVpBvI8lmIak(editText));
            setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "点击获取短信验证码", this.list.indexOf(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showSetRingResult(String str, String str2) {
        try {
            CustomDialogManager.show(this.activityContext, new ADCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$3SwXeum2kxsM-waEPu5ImH3oojQ
                @Override // com.sinovatech.unicom.separatemodule.audience.util.ADCallBack
                public final void addAdView(View view) {
                    AttentionFragment.lambda$showSetRingResult$9(AttentionFragment.this, view);
                }
            }, new C83344(str, str2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showSetRingResult$9(AttentionFragment attentionFragment, final View view) {
        if (view == null || !OptionValveUtil.INSTENCE.isShowOrderAD()) {
            return;
        }
        attentionFragment.dismissPauseAd();
        UIUtils.logD("xcyBanner", "开始加载banner广告");
        AdConfigEntity adConfigEntity = new AdConfigEntity();
        adConfigEntity.setAdType("PANGLE");
        adConfigEntity.setCodeId("946709155");
        adConfigEntity.setBannerWidth(302);
        adConfigEntity.setScale(0.85d);
        AdFactory.getAd(attentionFragment.activityContext, adConfigEntity).loadBanner(new IAdInterface.IBannerAdCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.5
            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
            public void onResult(int i, View view2) {
                StringBuilder sb = new StringBuilder();
                sb.append("code=");
                sb.append(i);
                sb.append(i == 11 ? "加载失败" : "加载成功");
                UIUtils.logD("xcyBanner", sb.toString());
                if (view2 != null) {
                    try {
                        View findViewById = view.findViewById(2131298304);
                        FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296981);
                        findViewById.setVisibility(0);
                        ViewGroup viewGroup = (ViewGroup) view2.getParent();
                        if (viewGroup != null) {
                            viewGroup.removeAllViews();
                        }
                        frameLayout.removeAllViews();
                        frameLayout.addView(view2);
                        TextView textView = new TextView(frameLayout.getContext());
                        textView.setText("广告由第三方合作提供");
                        textView.setTextColor(-6710887);
                        textView.setTextSize(8.0f);
                        textView.setGravity(8388613);
                        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                        layoutParams.gravity = 80;
                        layoutParams.topMargin = UIUtils.dip2px(AttentionFragment.this.activityContext, 5.0f);
                        frameLayout.addView(textView, layoutParams);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
            public void onClose() {
                view.findViewById(2131298304).setVisibility(8);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83344 implements CustomDialogManager.SetCallingDialogListener {
        final /* synthetic */ String val$ringId;
        final /* synthetic */ String val$viewTiltle;

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SetCallingDialogListener
        public void close() {
        }

        C83344(String str, String str2) {
            this.val$ringId = str;
            this.val$viewTiltle = str2;
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SetCallingDialogListener
        public void onClick(boolean z) {
            final String[] strArr = {" . ", " . . ", " . . ."};
            final Toast toast = new Toast(AttentionFragment.this.activityContext);
            View inflate = LayoutInflater.from(AttentionFragment.this.activityContext).inflate(2131493391, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(2131296893);
            ValueAnimator ofInt = ValueAnimator.ofInt(0, 3);
            ofInt.setRepeatCount(-1);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.4.1
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public void onAnimationUpdate(ValueAnimator valueAnimator) {
                    int intValue = ((Integer) valueAnimator.getAnimatedValue()).intValue();
                    TextView textView2 = textView;
                    String[] strArr2 = strArr;
                    textView2.setText(strArr2[intValue % strArr2.length]);
                }
            });
            ofInt.start();
            toast.setView(inflate);
            toast.setGravity(17, 0, 0);
            toast.show();
            ObservableSubscribeProxy<Map<String, String>> ringSetting = AttentionFragment.this.managerAudienceLoadData.getRingSetting("1", this.val$ringId, z ? "1" : "0");
            final String str = this.val$viewTiltle;
            ringSetting.subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$4$Ue8Q-Uq7ViCbs2Bzi68cVYq3C5k
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.C83344.lambda$onClick$0(AttentionFragment.C83344.this, toast, str, (Map) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$4$5U9w-xT-6PRJfhe0_deSSSY77tU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.C83344.lambda$onClick$1(toast, (Throwable) obj);
                }
            });
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static /* synthetic */ void lambda$onClick$0(C83344 c83344, Toast toast, String str, Map map) throws Exception {
            char c;
            toast.cancel();
            Toast toast2 = new Toast(AttentionFragment.this.activityContext);
            View inflate = LayoutInflater.from(AttentionFragment.this.activityContext).inflate(2131493392, (ViewGroup) null);
            String str2 = (String) map.get("statusCode");
            String str3 = (String) map.get("message");
            switch (str2.hashCode()) {
                case 1477632:
                    if (str2.equals("0000")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1507457:
                    if (str2.equals("1013")) {
                        c = 4;
                        break;
                    }
                    c = 65535;
                    break;
                case 1537214:
                    if (str2.equals("2000")) {
                        c = 1;
                        break;
                    }
                    c = 65535;
                    break;
                case 1567005:
                    if (str2.equals("3000")) {
                        c = 2;
                        break;
                    }
                    c = 65535;
                    break;
                case 1567006:
                    if (str2.equals("3001")) {
                        c = 3;
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
                    OrderVideoCallingResultDialog.show(AttentionFragment.this.activityContext, "");
                    if (TextUtils.isEmpty(str3)) {
                        return;
                    }
                    ToastUtil.showToast(str3);
                    return;
                case 1:
                case 2:
                    toast2.setView(inflate);
                    toast2.setGravity(17, 0, 0);
                    toast2.show();
                    if (str3.equals("设置成功,奖励2积分")) {
                        ToastUtil.showToast(str3);
                        return;
                    }
                    return;
                case 3:
                    if (!TextUtils.isEmpty(str)) {
                        OrderVideoCallingResultDialog.show(AttentionFragment.this.activityContext, str);
                    }
                    if (str3.equals("设置成功,奖励2积分")) {
                        ToastUtil.showToast(str3);
                        return;
                    }
                    return;
                case 4:
                    AttentionFragment.this.showRingtoneLibraryFull();
                    if (TextUtils.isEmpty(str3)) {
                        return;
                    }
                    ToastUtil.showToast(str3);
                    return;
                default:
                    ((TextView) inflate.findViewById(2131296893)).setText("设置失败");
                    toast2.setView(inflate);
                    toast2.setGravity(17, 0, 0);
                    toast2.show();
                    return;
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onClick$1(Toast toast, Throwable th) throws Exception {
            toast.cancel();
            ToastUtil.showToast("设置失败");
        }
    }

    private void showOrderRingDialog(SmallVideoEntity.Data data) {
        try {
            getOrderRingInstruction(data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getOrderRingInstruction(final SmallVideoEntity.Data data) {
        try {
            this.managerAudienceLoadData.getOderRingExplain("1", data.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$H4NYQL3_UiM1Y51pY4kI3hE64_g
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getOrderRingInstruction$10(AttentionFragment.this, data, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$ENyjdOyjmG9rpOCX464OeS8c2S0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UIUtils.logD("lln", ((Throwable) obj).getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:23:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:28:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void lambda$getOrderRingInstruction$10(com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment r11, final com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity.Data r12, final com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity r13) throws java.lang.Exception {
        /*
            java.lang.Object r0 = r13.getData()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L88
            java.lang.Object r0 = r13.getData()
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            if (r0 <= 0) goto L88
            r0 = r1
            r3 = r0
            r4 = r3
        L17:
            java.lang.Object r5 = r13.getData()
            java.util.List r5 = (java.util.List) r5
            int r5 = r5.size()
            if (r0 >= r5) goto L62
            java.lang.Object r5 = r13.getData()
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = r5.get(r0)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r5 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r5
            java.lang.String r5 = r5.getRecommendedStatus()
            java.lang.String r6 = "1"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L4b
            java.lang.Object r4 = r13.getData()
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r4 = r4.get(r0)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r4 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r4
            r4.setSelected(r2)
            r4 = r0
        L4b:
            java.lang.Object r5 = r13.getData()
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = r5.get(r0)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r5 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r5
            boolean r5 = r5.isSelected()
            if (r5 != 0) goto L5f
            int r3 = r3 + 1
        L5f:
            int r0 = r0 + 1
            goto L17
        L62:
            java.lang.Object r0 = r13.getData()
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            if (r3 != r0) goto L7f
            java.lang.Object r0 = r13.getData()
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r1)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r0 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r0
            r0.setSelected(r2)
            r9 = r2
            goto L89
        L7f:
            java.lang.Object r0 = r13.getData()
            java.util.List r0 = (java.util.List) r0
            java.util.Collections.swap(r0, r4, r2)
        L88:
            r9 = r1
        L89:
            java.lang.String r0 = "0000"
            java.lang.String r1 = r13.getStatusCode()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Laa
            com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity r5 = r11.activityContext
            java.lang.String r6 = "1"
            java.lang.Object r0 = r13.getData()
            r7 = r0
            java.util.List r7 = (java.util.List) r7
            boolean r8 = r11.isNeedCode
            com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment$6 r10 = new com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment$6
            r10.<init>()
            com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.show(r5, r6, r7, r8, r9, r10)
        Laa:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.lambda$getOrderRingInstruction$10(com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment, com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity$Data, com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity):void");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void OrderVideoTingNoneCode(String str, String str2, String str3, String str4, final String str5, final String str6, SmallVideoEntity.Data data) {
        try {
            this.managerAudienceLoadData.getVideoRing(str, str2, str3, str4).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$VIS83ENqpT7QAvBdrNASkSbrBnE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$OrderVideoTingNoneCode$12(AttentionFragment.this, str5, str6, (BaseVideoEntity) obj);
                }
            });
            setLogPoit(data.getVideoUrl(), "", data.getViewTitle(), "2", "点击按钮", "19", data.getViewTitle(), "确认订购", this.list.indexOf(data));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$OrderVideoTingNoneCode$12(AttentionFragment attentionFragment, String str, String str2, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            if (!"0000".equals(baseVideoEntity.getStatusCode())) {
                str = baseVideoEntity.getMessage();
            }
            OrderVideoRingResultDialog.show(attentionFragment.activityContext, "0000".equals(baseVideoEntity.getStatusCode()), str, str2, "1");
            if ("0000".equals(baseVideoEntity.getStatusCode())) {
                String message = baseVideoEntity.getMessage();
                if (TextUtils.isEmpty(message)) {
                    return;
                }
                ToastUtil.showToast(message);
            }
        }
    }

    public void getSDKAccessCode() {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$UcMA5p_G2_GV1-b6WyEx84i8E_s
            @Override // java.lang.Runnable
            public final void run() {
                AttentionFragment.lambda$getSDKAccessCode$14(AttentionFragment.this);
            }
        }).start();
    }

    public static /* synthetic */ void lambda$getSDKAccessCode$14(final AttentionFragment attentionFragment) {
        try {
            if (attentionFragment.activityContext != null) {
                GetNetAccessCode.getInstance().getSDKAccessCode(attentionFragment.activityContext, "zhibo", new GetNetAccessCode.GetNetAccessCodeClick() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$dMLR3wQD-x2orFCyiOMcbzr0IQI
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode.GetNetAccessCodeClick
                    public final void onGetData(String str) {
                        AttentionFragment.lambda$getSDKAccessCode$13(AttentionFragment.this, str);
                    }
                });
            }
        } catch (Exception unused) {
            MsLogUtil.m7979d(TAG, "net取号初始化问题。");
            attentionFragment.isNeedCode = false;
        }
    }

    public static /* synthetic */ void lambda$getSDKAccessCode$13(AttentionFragment attentionFragment, String str) {
        if (!TextUtils.isEmpty(str)) {
            attentionFragment.isNeedCode = "1".equals(str);
        } else {
            attentionFragment.isNeedCode = false;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        try {
            showLoading();
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
            }
            this.managerAudienceLoadData.getAttentionAnchors(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$Xc4_7KZwGqvOxRzS3sBmn10CrOY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$loadData$15(AttentionFragment.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$q1r0t5LjGppsvD77R7DiVNJBHcE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$loadData$16(AttentionFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$loadData$15(AttentionFragment attentionFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        attentionFragment.dismissLoading();
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            attentionFragment.llEmpty.setVisibility(8);
            attentionFragment.pageNum = baseVideoEntity.getNextPageNum();
            if (attentionFragment.isLoadMore) {
                attentionFragment.mAdapter.addData((Collection) baseVideoEntity.getData());
                attentionFragment.isLoadMore = false;
                return;
            }
            attentionFragment.list = (List) baseVideoEntity.getData();
            attentionFragment.mAdapter.setNewData(attentionFragment.list);
            if (attentionFragment.mAdapter.getData().size() == 0) {
                attentionFragment.llEmpty.setVisibility(0);
                return;
            }
            return;
        }
        attentionFragment.llEmpty.setVisibility(0);
    }

    public static /* synthetic */ void lambda$loadData$16(AttentionFragment attentionFragment, Throwable th) throws Exception {
        th.printStackTrace();
        attentionFragment.llEmpty.setVisibility(0);
        attentionFragment.dismissLoading();
    }

    public void toggleFullScreen(Context context, int i) {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
            LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297726);
            TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
            ImageView imageView = (ImageView) findViewByPosition.findViewById(2131296393);
            ImageView imageView2 = (ImageView) findViewByPosition.findViewById(2131296394);
            LinearLayout linearLayout2 = (LinearLayout) findViewByPosition.findViewById(2131297733);
            FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131297007);
            this.flProgressBarAreaLandscape = (FrameLayout) findViewByPosition.findViewById(2131297008);
            this.llLandscapeTitle = findViewByPosition.findViewById(2131297735);
            this.activityContext.fullScreen();
            if (this.activityContext.getScreenOrientation(context) == 0) {
                linearLayout.setVisibility(0);
                textView.setVisibility(0);
                isFullView(textView);
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
                linearLayout2.setVisibility(8);
                this.mLayoutManager.setScrollEnabled(true);
                frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
                this.bdCloudVideoView.setVideoScalingMode(2);
                setProgressBarLandscape(false);
                addProgressTo(frameLayout2);
            } else {
                this.mLayoutManager.setScrollEnabled(false);
                linearLayout.setVisibility(8);
                textView.setVisibility(8);
                imageView.setVisibility(8);
                imageView2.setVisibility(8);
                linearLayout2.setVisibility(0);
                this.bdCloudVideoView.setVideoScalingMode(1);
                setProgressBarLandscape(true);
                if (this.flProgressBarAreaLandscape != null) {
                    addProgressTo(this.flProgressBarAreaLandscape);
                }
            }
            ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            frameLayout.addView(this.bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void getHelpBtnInfo() {
        try {
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
            }
            this.managerAudienceLoadData.getHelpBtnInfo().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$3kSR1YlAt9sU-qnIkbNwPTLpvJ4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getHelpBtnInfo$17(AttentionFragment.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$4Xnza5z15ZeEec0Z2kzMeJOfZS4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UIUtils.logD("xcyTest", ((Throwable) obj).getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getHelpBtnInfo$17(AttentionFragment attentionFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            attentionFragment.helpUrl = ((HelpBtnInfoEntity) baseVideoEntity.getData()).getHelpUrl();
            attentionFragment.isShowHelpBtn = ((HelpBtnInfoEntity) baseVideoEntity.getData()).isShowHelpBtn();
            AttentionItemAdapter attentionItemAdapter = attentionFragment.mAdapter;
            if (attentionItemAdapter != null) {
                attentionItemAdapter.setShowHelpBtn(attentionFragment.isShowHelpBtn);
                attentionFragment.mAdapter.notifyDataSetChanged();
            }
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
    public void clearData() {
        try {
            if (this.mAdapter != null && this.list != null) {
                this.list.clear();
                this.mAdapter.setNewData(this.list);
            }
            if (this.markImg != null) {
                this.markImg.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playLive() {
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playLive(String str) {
        try {
            uploadLog("");
            this.pageNum = str;
            this.mCurrentItem = 0;
            getSDKAccessCode();
            playLive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            leaveRoom();
            this.mAdapter.setCurrentPosition(this.mCurrentItem);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isCurrentRoom(String str, int i) {
        try {
            String jobNumber = this.list.get(i).getLiveData().getJobNumber();
            UIUtils.logD("fufeizhibo", "roomId=" + str + "|currentRoomId=" + jobNumber);
            return str.equals(jobNumber);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private void getSharpnessInfo(final int i) {
        try {
            final String jobNumber = this.list.get(i).getLiveData().getJobNumber();
            this.managerAudienceLoadData.getSharpnessInfo(jobNumber).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$smMA1UX75CUF82R99D1ZuI9RO1I
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getSharpnessInfo$20(AttentionFragment.this, jobNumber, i, (SharpnessEntity) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getSharpnessInfo$20(final AttentionFragment attentionFragment, String str, final int i, SharpnessEntity sharpnessEntity) throws Exception {
        if ("0000".equals(sharpnessEntity.getStatusCode()) && attentionFragment.isCurrentRoom(str, i)) {
            if (STATUS_Y.equals(sharpnessEntity.getLiveViewAngleMore())) {
                attentionFragment.liveTypeText = "正在直播-多视角";
                View findViewByPosition = attentionFragment.mLayoutManager.findViewByPosition(i);
                if (findViewByPosition != null) {
                    ((TextView) findViewByPosition.findViewById(2131299146)).setText("正在直播-多视角");
                }
            }
            AudienceMainActivity.isNeedPay = false;
            if (!TextUtils.isEmpty(sharpnessEntity.getPromptText())) {
                new ToastDialogManager(attentionFragment.getActivity()).show(sharpnessEntity.getPromptText());
            }
            if (STATUS_Y.equals(sharpnessEntity.getPaidLive()) && !STATUS_Y.equals(sharpnessEntity.getPayingUser())) {
                attentionFragment.initPayInfo(sharpnessEntity.getPaidLinks(), sharpnessEntity.getPaidAd(), false);
                attentionFragment.clearPayInfo();
                try {
                    attentionFragment.timePayInfo = Integer.parseInt(sharpnessEntity.getFreeTime());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (attentionFragment.timePayInfo < 10) {
                    attentionFragment.showPayInfo();
                } else {
                    attentionFragment.llPayInfo.setVisibility(0);
                    attentionFragment.llPayTipe.setVisibility(0);
                    attentionFragment.tvFreeTimeTips.setText("可试看" + attentionFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
                    attentionFragment.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$ooLgAhzQvqV--S1TO2oVm_-NQwo
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AttentionFragment.lambda$getSharpnessInfo$19(AttentionFragment.this, i, (Long) obj);
                        }
                    });
                }
            }
            List<SharpnessEntity.LiveUrlBean> list = attentionFragment.liveUrlList;
            if (list == null) {
                attentionFragment.liveUrlList = sharpnessEntity.getData();
            } else {
                list.clear();
                attentionFragment.liveUrlList.addAll(sharpnessEntity.getData());
            }
            if (sharpnessEntity.getData() == null || sharpnessEntity.getData().size() <= 0) {
                return;
            }
            if (sharpnessEntity.getData().size() > 2) {
                attentionFragment.setPlayerUrl(sharpnessEntity.getData().get(1));
            } else {
                attentionFragment.setPlayerUrl(sharpnessEntity.getData().get(0));
            }
        }
    }

    public static /* synthetic */ void lambda$getSharpnessInfo$19(AttentionFragment attentionFragment, int i, Long l) throws Exception {
        attentionFragment.timePayInfo--;
        if (attentionFragment.timePayInfo % 10 == 0) {
            attentionFragment.consumeFreeTime(attentionFragment.list.get(i).getLiveData().getJobNumber());
        }
        if (attentionFragment.timePayInfo == 0) {
            attentionFragment.showPayInfo();
            return;
        }
        attentionFragment.tvFreeTimeTips.setText("可试看" + attentionFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
    }

    private void consumeFreeTime(String str) {
        try {
            UIUtils.logD("LiveFragment", "扣减时间==" + str);
            this.managerAudienceLoadData.consumeFreeTime(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$e5s79Ue7lC2L4D61-Q78AA8OIsc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UIUtils.logD("LiveFragment", ((String) obj) + "===========");
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearPayInfo() {
        try {
            if (this.payInfoCountDown != null) {
                this.payInfoCountDown.dispose();
                this.payInfoCountDown = null;
            }
            this.timePayInfo = 0;
            if (this.llPayInfo != null) {
                this.llPayInfo.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPBPayInfo() {
        try {
            clearPayInfo();
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(0);
            this.tvBtnPay4PB.setVisibility(0);
            this.llTrySeeDialog.setVisibility(0);
            AudienceMainActivity.isNeedPay = true;
            this.activityContext.stopPlay();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showPayInfo() {
        try {
            clearPayInfo();
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(8);
            this.llTrySeeDialog.setVisibility(0);
            AudienceMainActivity.isNeedPay = true;
            if (this.bdCloudVideoView != null) {
                this.bdCloudVideoView.stopPlayback();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissPayInfo() {
        try {
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(8);
            this.llTrySeeDialog.setVisibility(8);
            this.ivPlayIcon.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initPayInfo(final String str, String str2, final boolean z) {
        try {
            this.llPayInfo = (LinearLayout) this.roomInfo.findViewById(2131297753);
            this.tvFreeTimeTips = (TextView) this.roomInfo.findViewById(2131298945);
            this.ivPlayIcon = (ImageView) this.roomInfo.findViewById(2131297450);
            this.llTrySeeDialog = (LinearLayout) this.roomInfo.findViewById(2131297789);
            this.tvPayTips = (TextView) this.roomInfo.findViewById(2131299043);
            this.tvPayTips.setText(str2);
            this.tvNoOrder = (TextView) this.roomInfo.findViewById(2131299028);
            this.tvOrder = (TextView) this.roomInfo.findViewById(2131299036);
            this.llPayTipe = (LinearLayout) this.roomInfo.findViewById(2131297754);
            this.tvBtnPay4PB = (TextView) this.roomInfo.findViewById(2131299041);
            this.llPayTipe.setVisibility(8);
            this.tvBtnPay4PB.setVisibility(0);
            this.tvBtnPay4PB.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$Wp9SN2aX0FHBlgqeNkKCtM3KCdo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$initPayInfo$22(AttentionFragment.this, str, view);
                }
            });
            this.llPayInfo.setVisibility(8);
            this.ivPlayIcon.setVisibility(8);
            this.llTrySeeDialog.setVisibility(8);
            this.ivPlayIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$EsEw_PLTSfVdrUyl1XiHFSUofFM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$initPayInfo$23(AttentionFragment.this, z, view);
                }
            });
            this.tvNoOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$beK1uMT6Tk3j9p9eKPoJxEG1kxQ
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.this.dismissPayInfo();
                }
            });
            this.tvOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$UpYYoTv53jM9SWNWomnU7zoRztM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$initPayInfo$25(AttentionFragment.this, str, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initPayInfo$22(AttentionFragment attentionFragment, String str, View view) {
        if (attentionFragment.llTrySeeDialog.getVisibility() == 8) {
            Intent intent = new Intent(attentionFragment.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(str);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            attentionFragment.startActivityForResult(intent, 4001);
        }
    }

    public static /* synthetic */ void lambda$initPayInfo$23(AttentionFragment attentionFragment, boolean z, View view) {
        if (!z) {
            attentionFragment.showPayInfo();
        } else {
            attentionFragment.showPBPayInfo();
        }
    }

    public static /* synthetic */ void lambda$initPayInfo$25(AttentionFragment attentionFragment, String str, View view) {
        Intent intent = new Intent(attentionFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        attentionFragment.startActivityForResult(intent, 4001);
    }

    private void setPlayerUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        try {
            showBgImg();
            String switchUrl = getSwitchUrl(liveUrlBean);
            MsLogUtil.m7979d(TAG, "setPlayerUrl=" + switchUrl);
            this.bdCloudVideoView = this.activityContext.playVideo(switchUrl);
            setVideoSizeView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getSwitchUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        if (liveUrlBean == null) {
            return "";
        }
        try {
            return !"Wifi".equals(DeviceHelper.getNETType(this.activityContext)) ? liveUrlBean.getLiveFreePullUrlByFlv() : liveUrlBean.getLivePullUrlByFlv();
        } catch (Exception e) {
            try {
                e.printStackTrace();
                return "";
            } catch (Exception e2) {
                e2.printStackTrace();
                return "";
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPlay(int i) {
        try {
            if (i < this.list.size()) {
                AudienceMainActivity.isLock = false;
                AttentionItemEntity attentionItemEntity = this.list.get(i);
                int type = attentionItemEntity.getType();
                UIUtils.logD(TAG, "播放视频：类型" + type);
                if (type != 3) {
                    this.jobNumber = attentionItemEntity.getLiveData().getJobNumber();
                } else {
                    this.jobNumber = attentionItemEntity.getSmallVideo().getUserId();
                }
                switch (type) {
                    case 2:
                        this.liveUrl = attentionItemEntity.getLiveData().getLivePvUrl();
                        getPlayBackInfo(attentionItemEntity.getLiveData().getVideoId(), attentionItemEntity.getLiveData().getDataType(), i);
                        return;
                    case 3:
                        playVideo(i);
                        return;
                    default:
                        getRoomInfo(attentionItemEntity.getLiveData().getJobNumber(), attentionItemEntity.getLiveData().getDataType(), i, "MZB".equals(attentionItemEntity.getLiveData().getTypeCode()));
                        return;
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    private void playVideo(final int i) {
        try {
            MsLogUtil.m7979d(TAG, "小视频开始播放");
            final SmallVideoEntity.Data smallVideo = this.list.get(i).getSmallVideo();
            setVideoNum(smallVideo);
            if (smallVideo.getVideoType() == 1 && !smallVideo.isHasAddCount()) {
                getVideoNum(smallVideo, i);
            }
            getAnchorInfo(i);
            setZanStatus(smallVideo, i);
            this.managerAudienceLoadData.getCommentList(smallVideo.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$1_iKlJ552jdf9EWfsQ3bH1ikVzw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$playVideo$26(AttentionFragment.this, smallVideo, i, (LiveCommentEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$9VwOPA9_bN-DKQdJjoFuaDHIJl8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$playVideo$27((Throwable) obj);
                }
            });
            if (this.bdCloudVideoView != null && this.bdCloudVideoView.getTag() != null && (this.bdCloudVideoView.getTag() instanceof String)) {
                if (!((String) this.bdCloudVideoView.getTag()).equals(smallVideo.getVideoUrl())) {
                    this.bdCloudVideoView = this.activityContext.playVideo(smallVideo.getVideoUrl());
                }
            } else {
                this.bdCloudVideoView = this.activityContext.playVideo(smallVideo.getVideoUrl());
            }
            if (this.playProgressUpdater == null) {
                initProgressBar();
            }
            setVideoSizeView4Video(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$playVideo$26(AttentionFragment attentionFragment, SmallVideoEntity.Data data, int i, LiveCommentEntity liveCommentEntity) throws Exception {
        if ("0000".equals(liveCommentEntity.getCode())) {
            data.setCommentNum(liveCommentEntity.getCommentList().size() + "");
            View findViewByPosition = attentionFragment.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition != null) {
                ((TextView) findViewByPosition.findViewById(2131298917)).setText(liveCommentEntity.getCommentList().size() + "");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$playVideo$27(Throwable th) throws Exception {
        UIUtils.logD(TAG, "获取评论数量错误");
        th.printStackTrace();
    }

    private void setZanStatus(final SmallVideoEntity.Data data, final int i) {
        try {
            this.managerAudienceLoadData.getVideoZan(data.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$hxm5CkXry8FLpHHEpApI7kiLelU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$setZanStatus$28(AttentionFragment.this, data, i, (Map) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setZanStatus$28(AttentionFragment attentionFragment, SmallVideoEntity.Data data, int i, Map map) throws Exception {
        String str = (String) map.get("thumbsUp");
        String str2 = (String) map.get("thumbsNum");
        data.setHasZan(STATUS_Y.equals(str));
        data.setVideoPraiseNum(str2);
        View findViewByPosition = attentionFragment.mLayoutManager.findViewByPosition(i);
        if (findViewByPosition != null) {
            ImageView imageView = (ImageView) findViewByPosition.findViewById(2131297529);
            ((TextView) findViewByPosition.findViewById(2131299157)).setText(str2);
            imageView.setImageResource(STATUS_Y.equals(str) ? 2131231684 : 2131231683);
        }
    }

    private void getAnchorInfo(int i) {
        final SmallVideoEntity.Data smallVideo;
        try {
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = this.activityContext.getLoadDataManager();
            }
            if (this.mAdapter.getItem(i) == 0 || (smallVideo = ((AttentionItemEntity) Objects.requireNonNull((AttentionItemEntity) this.mAdapter.getItem(i))).getSmallVideo()) == null) {
                return;
            }
            this.managerAudienceLoadData.getVideoInfo(smallVideo.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$TPdyKhm5yX_EY3R4rtoujpbTTSI
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoEntity.Data.this.setUserIndexUrl(((SmallVideoInfoEntity) obj).getData().getUserIndexUrl());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    private void getVideoNum(final SmallVideoEntity.Data data, final int i) {
        try {
            this.managerAudienceLoadData.getVideoNum(data.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$EKbLqFsNsPzKWCMXGzwvLFM-xxg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getVideoNum$30(AttentionFragment.this, data, i, (Map) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getVideoNum$30(AttentionFragment attentionFragment, SmallVideoEntity.Data data, int i, Map map) throws Exception {
        int i2 = 0;
        if ("0000".equals(map.get("statusCode")) && !TextUtils.isEmpty((CharSequence) map.get("data"))) {
            i2 = Integer.parseInt((String) map.get("data"));
        }
        int parseInt = Integer.parseInt(data.getViewNum()) + i2;
        data.setViewNum(parseInt + "");
        data.setHasAddCount(true);
        ((TextView) ((View) Objects.requireNonNull(attentionFragment.mLayoutManager.findViewByPosition(i))).findViewById(2131299147)).setText(FormatUtils.getShowString(parseInt) + "人看过");
    }

    private void setVideoNum(final SmallVideoEntity.Data data) {
        try {
            this.managerAudienceLoadData.setVideoNum(data.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$DhZ5b9IVU5AjJfoAhX_1PWPRYKY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$setVideoNum$31(AttentionFragment.this, data, (String) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setVideoNum$31(AttentionFragment attentionFragment, SmallVideoEntity.Data data, String str) throws Exception {
        int i;
        String viewNum = data.getViewNum();
        try {
            i = Integer.parseInt(viewNum);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d(TAG, "result=" + str + "|countStr=" + viewNum);
            i = 0;
        }
        data.setViewNum((i + 1) + "");
        attentionFragment.mAdapter.notifyItemChanged(attentionFragment.list.indexOf(data));
    }

    public void setVideoSizeView4Video(final int i) {
        try {
            MsLogUtil.m7979d(TAG, "setVideoSizeView");
            try {
                if (this.bdCloudVideoView == null) {
                    MsLogUtil.m7979d(TAG, "setVideoSizeView，播放器为空");
                    return;
                }
                ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
                if (findViewByPosition == null) {
                    this.reSetVideo = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$pjbmJU3PK5radmUhrba2tjUnDOw
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AttentionFragment.lambda$setVideoSizeView4Video$32(AttentionFragment.this, i, (Long) obj);
                        }
                    });
                    return;
                }
                int videoWidth = this.bdCloudVideoView.getVideoWidth();
                int videoHeight = this.bdCloudVideoView.getVideoHeight();
                SmallVideoEntity.Data smallVideo = ((AttentionItemEntity) this.mAdapter.getItem(i)).getSmallVideo();
                if (smallVideo != null && smallVideo.getImgWidth() > 0 && !this.bdCloudVideoView.isPlaying()) {
                    this.addPlayer2UIHandler.sendEmptyMessage(33);
                }
                MsLogUtil.m7979d(TAG, "视频宽：" + videoWidth + "|视频高：" + videoHeight);
                if (videoWidth < 1) {
                    ViewGroup viewGroup2 = (ViewGroup) this.bdCloudVideoView.getParent();
                    if (viewGroup2 != null && viewGroup2 != this.baseLayout) {
                        viewGroup2.removeView(this.bdCloudVideoView);
                    }
                    this.baseLayout.addView(this.bdCloudVideoView);
                    return;
                }
                this.bdCloudVideoView.setVideoScalingMode(2);
                FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
                this.ivBtnPlay = findViewByPosition.findViewById(2131297359);
                this.ivBtnPlay.setVisibility(8);
                this.ivBtnPlay2 = findViewByPosition.findViewById(2131297360);
                this.ivBtnPlay2.setVisibility(8);
                this.flAdPauseView = (FrameLayout) findViewByPosition.findViewById(2131296983);
                this.rlAdPauseView = (RelativeLayout) findViewByPosition.findViewById(2131298305);
                this.flAdPauseViewLandscape = (FrameLayout) findViewByPosition.findViewById(2131296984);
                this.rlAdPauseViewLandscape = (RelativeLayout) findViewByPosition.findViewById(2131298306);
                this.rlInfoArea = findViewByPosition.findViewById(2131298344);
                this.rlAdPauseView.setVisibility(8);
                this.rlAdPauseViewLandscape.setVisibility(8);
                TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
                textView.setVisibility(8);
                LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297764);
                if (videoWidth > videoHeight && this.activityContext.getScreenOrientation(this.activityContext) == 1) {
                    frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
                    if (this.bdCloudVideoView.isPlaying() && linearLayout.getVisibility() == 8) {
                        textView.setVisibility(0);
                        isFullView(textView);
                        ((AttentionItemEntity) this.mAdapter.getData().get(i)).getSmallVideo().setShowFull(textView.getVisibility() == 0);
                    }
                    this.bdCloudVideoView.setVideoScalingMode(1);
                }
                if (frameLayout != null) {
                    frameLayout.addView(this.bdCloudVideoView);
                    MsLogUtil.m7979d(TAG, "播放器加入布局");
                }
                initLikeView(findViewByPosition);
                this.ivBack = (ImageView) findViewByPosition.findViewById(2131297344);
                setProgressBarLandscape(false);
                addProgressTo((FrameLayout) findViewByPosition.findViewById(2131297007));
            } catch (Exception e) {
                MsLogUtil.m7979d(TAG, e.getMessage());
                e.printStackTrace();
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setVideoSizeView4Video$32(AttentionFragment attentionFragment, int i, Long l) throws Exception {
        attentionFragment.setVideoSizeView4Video(i);
        Disposable disposable = attentionFragment.reSetVideo;
        if (disposable != null) {
            disposable.dispose();
            attentionFragment.reSetVideo = null;
        }
    }

    public static /* synthetic */ boolean lambda$new$33(AttentionFragment attentionFragment, Message message) {
        try {
            MsLogUtil.m7979d(TAG, "what=" + message.what);
            switch (message.what) {
                case 32:
                    View findViewByPosition = attentionFragment.mLayoutManager.findViewByPosition(attentionFragment.mCurrentItem);
                    if (findViewByPosition != null) {
                        View findViewById = findViewByPosition.findViewById(2131296393);
                        if (findViewById != null) {
                            findViewById.setVisibility(8);
                        }
                        View findViewById2 = findViewByPosition.findViewById(2131296394);
                        if (findViewById2 != null) {
                            findViewById2.setVisibility(8);
                        }
                        View findViewById3 = findViewByPosition.findViewById(2131299478);
                        if (findViewById3 != null) {
                            findViewById3.setVisibility(8);
                            break;
                        }
                    }
                    break;
                case 33:
                    View findViewByPosition2 = attentionFragment.mLayoutManager.findViewByPosition(attentionFragment.mCurrentItem);
                    if (findViewByPosition2 != null) {
                        SmallVideoEntity.Data smallVideo = ((AttentionItemEntity) attentionFragment.mAdapter.getItem(attentionFragment.mCurrentItem)).getSmallVideo();
                        View findViewById4 = findViewByPosition2.findViewById(2131296393);
                        if (findViewById4 != null) {
                            findViewById4.setVisibility(smallVideo.getImgWidth() >= smallVideo.getImgHeight() ? 8 : 0);
                        }
                        View findViewById5 = findViewByPosition2.findViewById(2131296394);
                        if (findViewById5 != null) {
                            findViewById5.setVisibility(smallVideo.getImgWidth() > smallVideo.getImgHeight() ? 0 : 8);
                        }
                        View findViewById6 = findViewByPosition2.findViewById(2131299478);
                        if (findViewById6 != null) {
                            findViewById6.setVisibility(smallVideo.getImgWidth() > smallVideo.getImgHeight() ? 0 : 8);
                            break;
                        }
                    }
                    break;
                default:
                    try {
                        if (attentionFragment.activityContext != null) {
                            if (attentionFragment.activityContext.getScreenOrientation(attentionFragment.activityContext) == 0) {
                                attentionFragment.setLandscapePlayer();
                            } else {
                                attentionFragment.setVideoSizeView4Video(attentionFragment.mCurrentItem);
                            }
                        }
                        break;
                    } catch (Exception e) {
                        MsLogUtil.m7980d(e.getMessage());
                        break;
                    }
            }
        } catch (Exception e2) {
            MsLogUtil.m7979d(TAG, e2.getMessage());
        }
        return false;
    }

    private void setLandscapePlayer() {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
            FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131296988);
            this.flProgressBarAreaLandscape = (FrameLayout) findViewByPosition.findViewById(2131297008);
            this.llLandscapeTitle = findViewByPosition.findViewById(2131297735);
            this.ivBtnPlay2.setVisibility(8);
            this.ivBtnPlay.setVisibility(8);
            this.mLayoutManager.setScrollEnabled(false);
            ((LinearLayout) findViewByPosition.findViewById(2131297726)).setVisibility(8);
            ((TextView) findViewByPosition.findViewById(2131298899)).setVisibility(8);
            ((LinearLayout) findViewByPosition.findViewById(2131297733)).setVisibility(0);
            final boolean z = App.getSharePreferenceUtil().getBoolean("bright_volume_show");
            final View findViewById = findViewByPosition.findViewById(2131298334);
            findViewById.setVisibility(z ? 8 : 0);
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$seXX4EKKEgCoicLqsuamrUgQ35A
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$setLandscapePlayer$34(findViewById, z, view);
                }
            });
            if (!z) {
                App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
            }
            this.bdCloudVideoView.setVideoScalingMode(1);
            findViewByPosition.findViewById(2131297750).setVisibility(8);
            MyGestureView myGestureView = new MyGestureView(this.activityContext);
            myGestureView.setActivity(this.activityContext);
            myGestureView.setListener(this.doubleClickListener);
            ViewGroup viewGroup = (ViewGroup) myGestureView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            frameLayout2.addView(myGestureView, new FrameLayout.LayoutParams(-1, -1));
            setProgressBarLandscape(true);
            if (this.flProgressBarAreaLandscape != null) {
                addProgressTo(this.flProgressBarAreaLandscape);
            }
            if (this.llLandscapeTitle != null && this.flProgressBarAreaLandscape != null) {
                this.llLandscapeTitle.setVisibility(0);
                this.flProgressBarAreaLandscape.setVisibility(0);
            }
            autoHideLandscape();
            ViewGroup viewGroup2 = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            frameLayout.addView(this.bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setLandscapePlayer$34(View view, boolean z, View view2) {
        view.setVisibility(8);
        if (z) {
            return;
        }
        App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startTiming() {
        if (this.playTime != null) {
            int i = this.realPosition;
            if (i == 0) {
                this.realPosition = i + 1;
            }
            this.playTime.sendEmptyMessageDelayed(3241, 1000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void autoHideLandscape() {
        if (this.playTime != null) {
            MsLogUtil.m7979d(TAG, "自动隐藏");
            this.playTime.removeMessages(5000);
            this.playTime.sendEmptyMessageDelayed(5000, 5000L);
        }
    }

    private void isFullView(TextView textView) {
        try {
            if ((this.activityContext.getResources().getConfiguration().screenLayout & 15) != 3 || Build.VERSION.SDK_INT < 24 || this.activityContext.isInMultiWindowMode()) {
                return;
            }
            textView.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLikeView(final View view) {
        try {
            this.likeView = (LikeView) view.findViewById(2131297988);
            this.doubleClickListener = new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.8
                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void onClick(long j) {
                    try {
                        MsLogUtil.m7979d(AttentionFragment.TAG, "单击");
                        if (AttentionFragment.this.activityContext.getScreenOrientation(AttentionFragment.this.activityContext) == 0) {
                            if (AttentionFragment.this.llLandscapeTitle == null || AttentionFragment.this.flProgressBarAreaLandscape == null) {
                                return;
                            }
                            int visibility = AttentionFragment.this.llLandscapeTitle.getVisibility();
                            int i = 8;
                            if (visibility == 8) {
                                AttentionFragment.this.autoHideLandscape();
                            }
                            AttentionFragment.this.llLandscapeTitle.setVisibility(visibility == 0 ? 8 : 0);
                            FrameLayout frameLayout = AttentionFragment.this.flProgressBarAreaLandscape;
                            if (visibility != 0) {
                                i = 0;
                            }
                            frameLayout.setVisibility(i);
                            return;
                        }
                        AttentionFragment.this.clickPlayOrPause();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void doubleTapCallback() {
                    try {
                        MsLogUtil.m7979d(AttentionFragment.TAG, "双击");
                        SmallVideoEntity.Data smallVideo = ((AttentionItemEntity) AttentionFragment.this.mAdapter.getData().get(AttentionFragment.this.mCurrentItem)).getSmallVideo();
                        if (smallVideo.isHasZan()) {
                            return;
                        }
                        ImageView imageView = (ImageView) view.findViewById(2131297529);
                        ImageView imageView2 = (ImageView) view.findViewById(2131297530);
                        TextView textView = (TextView) view.findViewById(2131299157);
                        TextView textView2 = (TextView) view.findViewById(2131299158);
                        AttentionFragment.this.zanVideo(smallVideo.getVideoId(), AttentionFragment.STATUS_Y);
                        if (imageView != null) {
                            imageView.setImageResource(2131231684);
                        }
                        if (imageView2 != null) {
                            imageView2.setImageResource(2131231615);
                        }
                        AttentionFragment.this.showZanAnimation(imageView);
                        AttentionFragment.this.showZanAnimation(imageView2);
                        smallVideo.setHasZan(true);
                        int i = 0;
                        try {
                            i = Integer.parseInt(smallVideo.getVideoPraiseNum()) + 1;
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                        smallVideo.setVideoPraiseNum(i + "");
                        if (textView != null) {
                            textView.setText(FormatUtils.getShowString(smallVideo.getVideoPraiseNum()));
                        }
                        if (textView2 != null) {
                            textView2.setText(FormatUtils.getShowString(smallVideo.getVideoPraiseNum()));
                        }
                    } catch (NumberFormatException e2) {
                        e2.printStackTrace();
                    }
                }
            };
            if (this.likeView != null) {
                this.likeView.removeAllViews();
                this.likeView.setListener(this.doubleClickListener);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void loadPauseAdView() {
        try {
            if (OptionValveUtil.INSTENCE.isShowPauseAD()) {
                if (this.pauseAdList.size() > 0) {
                    displayPauseAd(getAdView());
                }
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("946588762");
                adConfigEntity.setBannerWidth(265);
                adConfigEntity.setBannerHeight(94);
                AdFactory.getAd(this.activityContext, adConfigEntity).loadInteraction(new IAdInterface.IInteractionCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.9
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onClose() {
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onResult(int i, View view) {
                        UIUtils.logD("xcy", "code=" + i);
                        try {
                            if (AttentionFragment.this.bdCloudVideoView == null || AttentionFragment.this.bdCloudVideoView.isPlaying()) {
                                UIUtils.logD("xcy", "视频正在播放");
                            } else if (view != null) {
                                if (AttentionFragment.this.pauseAdList.size() == 0) {
                                    UIUtils.logD("xcy", "加载网络随机广告");
                                    AttentionFragment.this.displayPauseAd(view);
                                } else {
                                    UIUtils.logD("xcy", "已显示随机广告");
                                }
                            } else {
                                UIUtils.logD("xcy", "ad view == null");
                                if (AttentionFragment.this.pauseAdList.size() == 0) {
                                    AttentionFragment.this.rlAdPauseView.setVisibility(8);
                                    AttentionFragment.this.rlAdPauseViewLandscape.setVisibility(8);
                                }
                            }
                            if (view != null) {
                                AttentionFragment.this.pauseAdList.add(view);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void displayPauseAd(View view) {
        try {
            if (this.activityContext.getScreenOrientation(this.activityContext) == 0) {
                ViewGroup viewGroup = (ViewGroup) view.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                this.flAdPauseViewLandscape.removeAllViews();
                this.rlAdPauseViewLandscape.setVisibility(0);
                this.flAdPauseViewLandscape.addView(view);
                TextView textView = new TextView(this.flAdPauseViewLandscape.getContext());
                textView.setText("广告由第三方合作提供");
                textView.setTextColor(-6710887);
                textView.setTextSize(8.0f);
                textView.setGravity(8388613);
                FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2);
                layoutParams.gravity = 80;
                layoutParams.topMargin = UIUtils.dip2px(this.activityContext, 5.0f);
                this.flAdPauseViewLandscape.addView(textView, layoutParams);
                return;
            }
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            this.flAdPauseView.removeAllViews();
            this.rlAdPauseView.setVisibility(0);
            this.flAdPauseView.addView(view);
            TextView textView2 = new TextView(this.flAdPauseView.getContext());
            textView2.setText("广告由第三方合作提供");
            textView2.setTextColor(-6710887);
            textView2.setTextSize(8.0f);
            textView2.setGravity(8388613);
            FrameLayout.LayoutParams layoutParams2 = new FrameLayout.LayoutParams(-1, -2);
            layoutParams2.gravity = 80;
            layoutParams2.topMargin = UIUtils.dip2px(this.activityContext, 5.0f);
            this.flAdPauseView.addView(textView2, layoutParams2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dismissPauseAd() {
        try {
            if (this.flAdPauseViewLandscape != null) {
                this.flAdPauseViewLandscape.setVisibility(8);
            }
            if (this.flAdPauseView != null) {
                this.flAdPauseView.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private View getAdView() {
        try {
            return this.pauseAdList.get(new Random().nextInt(this.pauseAdList.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLogPoit(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, int i) {
        try {
            SmallVideoEntity.Data smallVideo = this.list.get(i).getSmallVideo();
            if (smallVideo != null && smallVideo.getVideoType() != 0) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                HashMap hashMap = new HashMap(64);
                hashMap.put("system_time", simpleDateFormat.format(new Date(System.currentTimeMillis())));
                hashMap.put("local_time", simpleDateFormat.format(new Date(System.currentTimeMillis())));
                hashMap.put("client_time", simpleDateFormat.format(new Date(System.currentTimeMillis())));
                String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
                if ("0".equals(currentPhoneNumber)) {
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
                hashMap.put("url", str);
                hashMap.put("urlref", str2);
                hashMap.put("last_page_name", str3);
                hashMap.put("activity_type", "2");
                hashMap.put("action_type", str4);
                hashMap.put("action_name", str5);
                hashMap.put("action_id", str6);
                hashMap.put("page_name", str7);
                hashMap.put("storey", "");
                hashMap.put("pb_name", str8);
                hashMap.put("ip", SystemServiceUtils.getLocalIpAddress());
                hashMap.put("time_spent", this.playedTime);
                hashMap.put("os", "AND");
                hashMap.put("device_model", DeviceHelper.getDeviceModel());
                hashMap.put("os_version", DeviceHelper.getDeviceOSVersion().replace("android", ""));
                hashMap.put("content_id", smallVideo.getVideoId());
                hashMap.put("position", this.slotTime);
                hashMap.put("realposition", this.slotTime);
                hashMap.put("duration", this.videoTime);
                hashMap.put("channel_id", "3000011091");
                hashMap.put("content_position", i + "");
                hashMap.put("app_version", getResources().getString(2131886969));
                hashMap.put("tjpara", smallVideo.getTjpara());
            }
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hiBoardLog(String str, int i) {
        try {
            SmallVideoEntity.Data smallVideo = this.list.get(i).getSmallVideo();
            if (smallVideo == null || smallVideo.getVideoType() != 1) {
                return;
            }
            this.managerAudienceLoadData.hiBoardLog(str, smallVideo.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$wd7RY5M6plM67bykHBoE87sSGJE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$hiBoardLog$35((String) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void zanVideo(String str, String str2) {
        this.managerAudienceLoadData.zanVideo(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$NQkBoV0T5jT73paRUNSuHQ_nzAI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AttentionFragment.lambda$zanVideo$36((String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showZanAnimation(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.8f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1200L);
        animatorSet.setInterpolator(new SpringScaleInterpolator(0.4f));
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.start();
    }

    public void setVideoSizeView() {
        try {
            if (this.bdCloudVideoView == null) {
                return;
            }
            if (((AttentionItemEntity) Objects.requireNonNull((AttentionItemEntity) this.mAdapter.getItem(this.mCurrentItem))).getSmallVideo() == null) {
                this.bdCloudVideoView.setVideoScalingMode(2);
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
                return;
            }
            try {
                if (this.addPlayer2UIHandler != null) {
                    this.addPlayer2UIHandler.sendEmptyMessageDelayed(200, 500L);
                }
            } catch (Exception e) {
                MsLogUtil.m7979d(TAG, e.getMessage());
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void getPlayBackInfo(String str, final String str2, final int i) {
        try {
            this.managerAudienceLoadData.getAngleMoreVideoInfo(str, "videoBack").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$9GsoOLjlIN3zjWwCLWbdgkWymLw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getPlayBackInfo$39(AttentionFragment.this, str2, i, (SharpnessEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$IgUS-z276k1Z-f8cnKSd7P9bRf4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getPlayBackInfo$40(AttentionFragment.this, i, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getPlayBackInfo$39(final AttentionFragment attentionFragment, final String str, int i, SharpnessEntity sharpnessEntity) throws Exception {
        if ("0000".equals(sharpnessEntity.getStatusCode())) {
            final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = sharpnessEntity.getUserInfo().getAnchorInfoBean();
            attentionFragment.roomInfo = LayoutInflater.from(attentionFragment.activityContext).inflate(2131493228, (ViewGroup) null);
            GlideApp.with((FragmentActivity) attentionFragment.activityContext).asGif().load((Integer) 2131232744).into((ImageView) attentionFragment.roomInfo.findViewById(2131297281));
            attentionFragment.roomInfo.findViewById(2131297726).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$K_Wz-T08zetdkGXthKYho3HXM20
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$getPlayBackInfo$37(AttentionFragment.this, anchorInfoBean, str, view);
                }
            });
            GlideApp.with((FragmentActivity) attentionFragment.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into((ImageView) attentionFragment.roomInfo.findViewById(2131297332));
            ((TextView) attentionFragment.roomInfo.findViewById(2131298863)).setText(anchorInfoBean.getUserName());
            ((TextView) attentionFragment.roomInfo.findViewById(2131298862)).setText(sharpnessEntity.getVideoPraiseNum());
            ((TextView) attentionFragment.roomInfo.findViewById(2131299144)).setText(anchorInfoBean.getLiveTitle());
            attentionFragment.roomInfo.findViewById(2131299144).setVisibility(0);
            attentionFragment.roomInfo.findViewById(2131297685).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$oMI85shA-X2jhgZ5lq1hwbpay4o
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$getPlayBackInfo$38(AttentionFragment.this, anchorInfoBean, view);
                }
            });
            attentionFragment.roomInfo.findViewById(2131297390).setVisibility(8);
            attentionFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str) ? 8 : 0);
            ((TextView) attentionFragment.roomInfo.findViewById(2131299029)).setText("点击进入直播回放");
            TextView textView = (TextView) attentionFragment.roomInfo.findViewById(2131299141);
            String str2 = attentionFragment.list.get(i).isMoreViewAngle() ? "-多视角" : "";
            textView.setText("精彩回放" + str2);
            textView.setVisibility(0);
            attentionFragment.roomInfo.findViewById(2131298391).setVisibility(8);
            attentionFragment.roomInfo.findViewById(2131297802).setVisibility(8);
            AttentionItemEntity attentionItemEntity = attentionFragment.list.get(i);
            if (attentionItemEntity != null && attentionItemEntity.getGoods() != null) {
                attentionFragment.showGoods(attentionItemEntity.getGoods());
            } else {
                attentionFragment.roomInfo.findViewById(2131297720).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131296461).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131298338).setVisibility(8);
            }
            if (attentionItemEntity != null && attentionItemEntity.getShopList() != null) {
                attentionFragment.showShopList(attentionItemEntity.getShopList());
            } else {
                attentionFragment.roomInfo.findViewById(2131297720).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131296461).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131298338).setVisibility(8);
            }
            attentionFragment.roomInfo.findViewById(2131298355).setVisibility(0);
            attentionFragment.roomInfo.setVisibility(0);
            attentionFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str) ? 8 : 0);
            attentionFragment.handlePayInfo(sharpnessEntity, attentionFragment.jobNumber);
            View findViewByPosition = attentionFragment.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296434);
            frameLayout.removeAllViews();
            frameLayout.addView(attentionFragment.roomInfo);
            attentionFragment.flInputPassword = attentionFragment.roomInfo.findViewById(2131297752);
            attentionFragment.flInputPassword.setVisibility(8);
            String isNeedCheck = sharpnessEntity.getIsNeedCheck();
            MsLogUtil.m7979d(TAG, "密码状态：" + isNeedCheck);
            if (STATUS_Y.equals(isNeedCheck)) {
                attentionFragment.flInputPassword.setVisibility(0);
                ((TextView) attentionFragment.flInputPassword.findViewById(2131299040)).setText("当前为加密直播回放");
                AudienceMainActivity.isLock = true;
                return;
            }
            attentionFragment.playVideoHandler.sendEmptyMessage(0);
            return;
        }
        attentionFragment.list.get(i).getLiveData().setLiving("N");
        attentionFragment.mAdapter.notifyItemChanged(i);
    }

    public static /* synthetic */ void lambda$getPlayBackInfo$37(AttentionFragment attentionFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str, View view) {
        if (!"N".equals(anchorInfoBean.getLiveStatus()) || "3".equals(str)) {
            attentionFragment.jumpLiveInfo();
        }
    }

    public static /* synthetic */ void lambda$getPlayBackInfo$38(AttentionFragment attentionFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, View view) {
        String userIndexUrl = anchorInfoBean.getUserIndexUrl();
        if (TextUtils.isEmpty(userIndexUrl)) {
            return;
        }
        Intent intent = new Intent(attentionFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(attentionFragment.addChannel(userIndexUrl, "直播回放"));
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        attentionFragment.startActivityForResult(intent, 3001);
    }

    public static /* synthetic */ void lambda$getPlayBackInfo$40(AttentionFragment attentionFragment, int i, Throwable th) throws Exception {
        attentionFragment.list.get(i).getLiveData().setLiving("N");
        attentionFragment.mAdapter.notifyItemChanged(i);
    }

    private void getRoomInfo(String str, final String str2, final int i, final boolean z) {
        try {
            this.managerAudienceLoadData.loadZhuboData(str, "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$xWn5-8WqMcXNs7AVsj30NT7-dEw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getRoomInfo$54(AttentionFragment.this, str2, z, i, (ZhuboDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$S2--Ptbxcz5ntL1zcAdJ4wYlQdE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getRoomInfo$55(AttentionFragment.this, i, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$54(final AttentionFragment attentionFragment, final String str, boolean z, int i, final ZhuboDataEntity zhuboDataEntity) throws Exception {
        if ("0000".equals(zhuboDataEntity.getStatusCode())) {
            UIUtils.logD(TAG, "直播间详情数据返回");
            final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
            attentionFragment.roomInfo = LayoutInflater.from(attentionFragment.activityContext).inflate(2131493228, (ViewGroup) null);
            GlideApp.with((FragmentActivity) attentionFragment.activityContext).asGif().load((Integer) 2131232744).into((ImageView) attentionFragment.roomInfo.findViewById(2131297281));
            attentionFragment.roomInfo.findViewById(2131297726).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$xPTpZ6o7LD1y7VqwK4igrm-5NVo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$getRoomInfo$41(AttentionFragment.this, anchorInfoBean, str, zhuboDataEntity, view);
                }
            });
            GlideApp.with((FragmentActivity) attentionFragment.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into((ImageView) attentionFragment.roomInfo.findViewById(2131297332));
            ((TextView) attentionFragment.roomInfo.findViewById(2131298863)).setText(anchorInfoBean.getUserName());
            ((TextView) attentionFragment.roomInfo.findViewById(2131299144)).setText(anchorInfoBean.getLiveTitle());
            attentionFragment.roomInfo.findViewById(2131299144).setVisibility(0);
            attentionFragment.roomInfo.findViewById(2131297685).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$FOfSSb5qDTUXQPmiEJ7g_JiCAVI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$getRoomInfo$42(AttentionFragment.this, anchorInfoBean, zhuboDataEntity, view);
                }
            });
            attentionFragment.roomInfo.findViewById(2131297390).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$_RrOZyWYTJTUD33_eXHYGDzszuA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    r0.managerAudienceLoadData.guanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$BrJHCj44-hCmPtvxS1Smj-8Sqaw
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AttentionFragment.lambda$getRoomInfo$43(AttentionFragment.this, (String) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
            });
            attentionFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str) ? 8 : 0);
            TextView textView = (TextView) attentionFragment.roomInfo.findViewById(2131299146);
            StringBuffer stringBuffer = new StringBuffer("正在直播");
            if (z) {
                stringBuffer.append("-慢直播");
            } else if (!TextUtils.isEmpty(attentionFragment.liveTypeText)) {
                stringBuffer.append("-多视角");
            }
            textView.setText(stringBuffer.toString());
            View findViewByPosition = attentionFragment.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296434);
            frameLayout.removeAllViews();
            frameLayout.addView(attentionFragment.roomInfo);
            if (!"3".equals(str)) {
                attentionFragment.managerAudienceLoadData.addRenqi(attentionFragment.list.get(i).getLiveData().getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$SUjD6zWuWhawgcxbcBQ0x0twhOA
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.lambda$getRoomInfo$45((String) obj);
                    }
                });
                attentionFragment.managerAudienceLoadData.loadShopData(attentionFragment.list.get(i).getLiveData().getJobNumber(), STATUS_Y).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$nf_7dF6BEf2x23o28dEbNbdHtYg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.lambda$getRoomInfo$46(AttentionFragment.this, (ShopEntity) obj);
                    }
                });
                attentionFragment.managerAudienceLoadData.loadShopList(attentionFragment.list.get(i).getLiveData().getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$8OJTIGzKpEqix2xf_gdbJ8x1S-M
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.lambda$getRoomInfo$47(AttentionFragment.this, anchorInfoBean, zhuboDataEntity, (List) obj);
                    }
                });
                attentionFragment.managerAudienceLoadData.isGuanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$ruoPF0n_OPtZLfdmj8VgwSm2N30
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.lambda$getRoomInfo$48(AttentionFragment.this, (Boolean) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$WEFE2UdbPqi_jkxUVfvGVWAUXoQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AttentionFragment.lambda$getRoomInfo$49(AttentionFragment.this, (Throwable) obj);
                    }
                });
            } else {
                AttentionItemEntity attentionItemEntity = attentionFragment.list.get(i);
                if (attentionItemEntity != null && attentionItemEntity.getGoods() != null) {
                    attentionFragment.showGoods(attentionItemEntity.getGoods());
                } else {
                    attentionFragment.roomInfo.findViewById(2131297720).setVisibility(8);
                    attentionFragment.roomInfo.findViewById(2131296461).setVisibility(8);
                    attentionFragment.roomInfo.findViewById(2131298338).setVisibility(8);
                }
                if (attentionItemEntity != null && attentionItemEntity.getShopList() != null) {
                    attentionFragment.showShopList(attentionItemEntity.getShopList());
                } else {
                    attentionFragment.roomInfo.findViewById(2131297720).setVisibility(8);
                    attentionFragment.roomInfo.findViewById(2131296461).setVisibility(8);
                    attentionFragment.roomInfo.findViewById(2131298338).setVisibility(8);
                }
            }
            if ("2".equals(str)) {
                attentionFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131297802).setVisibility(0);
                attentionFragment.roomInfo.findViewById(2131299141).setVisibility(8);
                attentionFragment.managerAudienceLoadData.reserveLivePv("2", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$bNe50pmzYQXw76CtBn6qRV8g81c
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        r0.managerAudienceLoadData.getLivePvInfo(r1.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$ZtYc-Z6N8MuJwSAx42H8TZZQh9E
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj2) {
                                AttentionFragment.lambda$getRoomInfo$52(AttentionFragment.this, r2, r3, (LivePvInfoEntity) obj2);
                            }
                        });
                    }
                });
            } else if ("3".equals(str)) {
                ((TextView) attentionFragment.roomInfo.findViewById(2131299029)).setText("点击进入直播回放");
                TextView textView2 = (TextView) attentionFragment.roomInfo.findViewById(2131299141);
                String str2 = attentionFragment.list.get(i).isMoreViewAngle() ? "-多视角" : "";
                textView2.setText("精彩回放" + str2);
                textView2.setVisibility(0);
                attentionFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131297802).setVisibility(8);
            } else {
                attentionFragment.roomInfo.findViewById(2131298391).setVisibility(0);
                attentionFragment.roomInfo.findViewById(2131297802).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131299141).setVisibility(8);
            }
            attentionFragment.roomInfo.findViewById(2131298355).setVisibility(0);
            attentionFragment.roomInfo.setVisibility(0);
            attentionFragment.roomInfo.findViewById(2131298995).setVisibility("2".equals(str) ? 8 : 0);
            if ("N".equals(anchorInfoBean.getLiveStatus()) && "1".equals(str)) {
                attentionFragment.roomInfo.findViewById(2131298391).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131297802).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131298995).setVisibility(8);
                attentionFragment.roomInfo.findViewById(2131299141).setVisibility(8);
                attentionFragment.stopJumpInfo();
            }
            if ("3".equals(str)) {
                attentionFragment.roomInfo.findViewById(2131297390).setVisibility(8);
                attentionFragment.getPlayBackVideoInfo(i);
                return;
            }
            String forcePassword = anchorInfoBean.getForcePassword();
            String isNeedCheck = anchorInfoBean.getIsNeedCheck();
            if (STATUS_Y.equals(forcePassword) && STATUS_Y.equals(isNeedCheck)) {
                attentionFragment.flInputPassword = attentionFragment.roomInfo.findViewById(2131297752);
                attentionFragment.flInputPassword.setVisibility(8);
                attentionFragment.flInputPassword.setVisibility(0);
                ((TextView) attentionFragment.flInputPassword.findViewById(2131299040)).setText("当前为加密直播间");
                AudienceMainActivity.isLock = true;
                return;
            }
            attentionFragment.getSharpnessInfo(i);
            return;
        }
        UIUtils.logD(TAG, "数据返回异常-->code=" + zhuboDataEntity.getStatusCode() + "|" + zhuboDataEntity.getMessage());
        attentionFragment.list.get(i).getLiveData().setLiving("N");
        attentionFragment.mAdapter.notifyItemChanged(i);
    }

    public static /* synthetic */ void lambda$getRoomInfo$41(AttentionFragment attentionFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str, ZhuboDataEntity zhuboDataEntity, View view) {
        if (!"N".equals(anchorInfoBean.getLiveStatus()) || "3".equals(str)) {
            attentionFragment.jumpLiveInfo();
            if (anchorInfoBean != null) {
                PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "点击进入直播间", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "关注");
            }
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$42(AttentionFragment attentionFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ZhuboDataEntity zhuboDataEntity, View view) {
        try {
            String userIndexUrl = anchorInfoBean.getUserIndexUrl();
            if (!TextUtils.isEmpty(userIndexUrl)) {
                Intent intent = new Intent(attentionFragment.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(attentionFragment.addChannel(userIndexUrl, "直播"));
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                attentionFragment.startActivityForResult(intent, 3001);
            }
            PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "头像", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "关注");
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$43(AttentionFragment attentionFragment, String str) throws Exception {
        if ("0000".equals(str)) {
            UIUtils.toastCenter("关注成功");
            attentionFragment.roomInfo.findViewById(2131297390).setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$46(AttentionFragment attentionFragment, ShopEntity shopEntity) throws Exception {
        ShopEntity.DataBean data;
        if (!"0000".equals(shopEntity.getStatusCode()) || (data = shopEntity.getData()) == null) {
            return;
        }
        try {
            ((TextView) attentionFragment.roomInfo.findViewById(2131298862)).setText(FormatUtils.getShowString(data.getRoundPraise()));
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (STATUS_Y.equalsIgnoreCase(data.getHaveGoods())) {
            attentionFragment.showGoods(data);
            return;
        }
        attentionFragment.roomInfo.findViewById(2131297720).setVisibility(8);
        attentionFragment.roomInfo.findViewById(2131296461).setVisibility(8);
        attentionFragment.roomInfo.findViewById(2131298338).setVisibility(8);
    }

    public static /* synthetic */ void lambda$getRoomInfo$47(AttentionFragment attentionFragment, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ZhuboDataEntity zhuboDataEntity, List list) throws Exception {
        attentionFragment.showShopList(list);
        PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "购物车", "直播预览页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "关注");
    }

    public static /* synthetic */ void lambda$getRoomInfo$48(AttentionFragment attentionFragment, Boolean bool) throws Exception {
        attentionFragment.roomInfo.findViewById(2131297390).setVisibility(bool.booleanValue() ? 8 : 0);
        attentionFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$49(AttentionFragment attentionFragment, Throwable th) throws Exception {
        attentionFragment.roomInfo.findViewById(2131297390).setVisibility(0);
        attentionFragment.roomInfo.findViewById(2131297685).setVisibility(0);
    }

    public static /* synthetic */ void lambda$getRoomInfo$52(final AttentionFragment attentionFragment, LivePvInfoEntity livePvInfoEntity, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, LivePvInfoEntity livePvInfoEntity2) throws Exception {
        if ("0000".equals(livePvInfoEntity2.getStatusCode())) {
            LivePvInfoEntity.InfoEntity data = livePvInfoEntity2.getData();
            final TextView textView = (TextView) attentionFragment.roomInfo.findViewById(2131299154);
            final TextView textView2 = (TextView) attentionFragment.roomInfo.findViewById(2131299155);
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
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$8x2OIwfyHdXbVkcFiGOvWaJi9GA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$getRoomInfo$51(AttentionFragment.this, textView, anchorInfoBean, textView2, view);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$51(final AttentionFragment attentionFragment, final TextView textView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final TextView textView2, View view) {
        if ("预约".equals(textView.getText())) {
            attentionFragment.managerAudienceLoadData.reserveLivePv("1", anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$G0wV3vWtvAI73RXjMpt4fovl5cA
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getRoomInfo$50(AttentionFragment.this, textView2, textView, (LivePvInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$50(AttentionFragment attentionFragment, TextView textView, TextView textView2, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            LivePvResultDialog livePvResultDialog = new LivePvResultDialog(attentionFragment.activityContext, textView.getText().toString().replace(" 开播", ""));
            textView2.setText("已预约");
            livePvResultDialog.show();
            return;
        }
        textView2.setText("预约");
        ToastUtil.showToast("出了一点儿小问题，请稍后再试");
    }

    public static /* synthetic */ void lambda$getRoomInfo$55(AttentionFragment attentionFragment, int i, Throwable th) throws Exception {
        UIUtils.logD(TAG, "主播接口信息异常-->" + th.getMessage());
        attentionFragment.list.get(i).getLiveData().setLiving("N");
        attentionFragment.mAdapter.notifyItemChanged(i);
    }

    private void showShopList(List<GoodListEntity> list) {
        try {
            this.currentShopList = list;
            ((TextView) this.roomInfo.findViewById(2131298965)).setText("" + list.size());
            this.roomInfo.findViewById(2131297720).setVisibility(0);
            this.roomInfo.findViewById(2131298338).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$kp-I4eAXE0EQMVvJTkjcTDeYA0Q
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceGoodsDialog.show(r0.activityContext, r0.currentShopList, new OnDialogBtnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.11
                        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
                        public void onClickGoodsLook(GoodListEntity goodListEntity) {
                            Intent intent = new Intent(AttentionFragment.this.activityContext, WebDetailActivity.class);
                            WebParamsEntity webParamsEntity = new WebParamsEntity();
                            webParamsEntity.setUrl(goodListEntity.getGoodsUrl());
                            webParamsEntity.setType("audienceMain");
                            webParamsEntity.setNeedTitle(true);
                            webParamsEntity.setRequestType("get");
                            intent.putExtra(WebFragment.webParams, webParamsEntity);
                            AttentionFragment.this.startActivityForResult(intent, 4001);
                        }

                        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
                        public void onClickOrder() {
                            IntentManager.gotoWebViewActivity(AttentionFragment.this.activityContext, URLSet.liveOrderMenu(), "我的订单");
                        }
                    });
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showGoods(ShopEntity.DataBean dataBean) {
        if (dataBean == null) {
            return;
        }
        try {
            this.roomInfo.findViewById(2131297720).setVisibility(0);
            this.roomInfo.findViewById(2131296461).setVisibility(0);
            this.roomInfo.findViewById(2131298338).setVisibility(0);
            final ShopEntity.DataBean.GoodsBean goods = dataBean.getGoods();
            if (goods == null) {
                return;
            }
            GlideApp.with((FragmentActivity) this.activityContext).load(goods.getCoverImgUrl()).into((ImageView) this.roomInfo.findViewById(2131296407));
            ((TextView) this.roomInfo.findViewById(2131296419)).setText(goods.getName());
            ((TextView) this.roomInfo.findViewById(2131296406)).setText(goods.getDesc());
            ((TextView) this.roomInfo.findViewById(2131296418)).setText(TextUtils.isEmpty(goods.getPrice()) ? goods.getOriginalPrice() : goods.getPrice());
            TextView textView = (TextView) this.roomInfo.findViewById(2131296417);
            if (TextUtils.isEmpty(goods.getOriginalPrice())) {
                textView.setVisibility(8);
            } else {
                textView.setVisibility(0);
                textView.getPaint().setFlags(16);
            }
            this.roomInfo.findViewById(2131296461).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$IWhydODkqmTlfB9BNlefd802QCc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AttentionFragment.lambda$showGoods$58(AttentionFragment.this, goods, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showGoods$58(AttentionFragment attentionFragment, ShopEntity.DataBean.GoodsBean goodsBean, View view) {
        AudienceActivity.IntentGo(attentionFragment.activityContext, goodsBean.getGoodsUrl(), "0", goodsBean.getId(), 4000);
        if (!TextUtils.isEmpty("0")) {
            attentionFragment.managerAudienceLoadData.goodsLog(attentionFragment.jobNumber, goodsBean.getId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$L9k6OyxjpmWPTXikCGN6BIQNyPg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$showGoods$57((Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }
        PvCurrencyLogUtils.pvLogLive("", 2, "", "商品卡片", "直播预览页", "", "关注");
        Intent intent = new Intent(attentionFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(goodsBean.getGoodsUrl());
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        attentionFragment.startActivityForResult(intent, 3001);
    }

    private void getPlayBackVideoInfo(int i) {
        try {
            final ListDataEntity liveData = this.list.get(i).getLiveData();
            UIUtils.logD("多视角测试", "多视角回放主视频播放");
            this.managerAudienceLoadData.getAngleMoreVideoInfo(liveData.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$ctwk7x533lf2gd_GWZ6ZgYia6uo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getPlayBackVideoInfo$59(AttentionFragment.this, liveData, (SharpnessEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$iYLxCThVrSphdwDmBM59GHcssP8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$getPlayBackVideoInfo$60((Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getPlayBackVideoInfo$59(AttentionFragment attentionFragment, ListDataEntity listDataEntity, SharpnessEntity sharpnessEntity) throws Exception {
        UIUtils.logD("多视角测试", "多视角回放请求成功");
        attentionFragment.handlePayInfo(sharpnessEntity, listDataEntity.getJobNumber());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$getPlayBackVideoInfo$60(Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("多视角测试", "多视角回放请求错误：" + th.getMessage());
    }

    private void handlePBPayInfo(final PlayBackPayInfoEntity playBackPayInfoEntity) {
        try {
            if (!TextUtils.isEmpty(playBackPayInfoEntity.getPromptText())) {
                UIUtils.toastCenter(playBackPayInfoEntity.getPromptText());
            }
            if (STATUS_Y.equals(playBackPayInfoEntity.getPaidLiveBackPlay()) && !STATUS_Y.equals(playBackPayInfoEntity.getUserIsPaid())) {
                initPayInfo(playBackPayInfoEntity.getPayUrl(), playBackPayInfoEntity.getPaidAd(), true);
                if (STATUS_Y.equals(playBackPayInfoEntity.getTryLook())) {
                    AudienceMainActivity.isNeedPay = false;
                    TextView textView = this.tvFreeTimeTips;
                    textView.setText("可试看" + playBackPayInfoEntity.getFreeTime() + "秒，订购权益包后可观看全部内容");
                    this.llPayInfo.setVisibility(0);
                    this.llPayTipe.setVisibility(0);
                    this.tvBtnPay4PB.setVisibility(0);
                    this.payInfoCountDown = Observable.interval(0L, 500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$BGbcnVUk4eKfGFLN_geEquD6kX8
                        @Override // io.reactivex.functions.Function
                        public final Object apply(Object obj) {
                            return AttentionFragment.lambda$handlePBPayInfo$61(AttentionFragment.this, playBackPayInfoEntity, (Long) obj);
                        }
                    }).observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$oKqhvxm3_KFD0B0LZWfXC5_hERQ
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AttentionFragment.lambda$handlePBPayInfo$62(AttentionFragment.this, (Boolean) obj);
                        }
                    });
                    return;
                }
                this.tvFreeTimeTips.setText("订购权益包后可观看全部内容");
                showPBPayInfo();
                return;
            }
            AudienceMainActivity.isNeedPay = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ Boolean lambda$handlePBPayInfo$61(AttentionFragment attentionFragment, PlayBackPayInfoEntity playBackPayInfoEntity, Long l) throws Exception {
        BDCloudVideoView bDCloudVideoView = attentionFragment.bdCloudVideoView;
        boolean z = false;
        if (bDCloudVideoView != null && bDCloudVideoView.getCurrentPosition() >= playBackPayInfoEntity.getFreeTime4MillisSeconds()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static /* synthetic */ void lambda$handlePBPayInfo$62(AttentionFragment attentionFragment, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            attentionFragment.showPBPayInfo();
        } else if (AudienceMainActivity.isNeedPay) {
            AudienceMainActivity.isNeedPay = false;
        }
    }

    private void doNotTry() {
        try {
            if (this.bdCloudVideoView != null) {
                if (this.bdCloudVideoView.isPlaying() || !AudienceMainActivity.isNeedPay) {
                    showPBPayInfo();
                    if (this.bdCloudVideoView.isPlaying()) {
                        return;
                    }
                    showBgImg();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handlePayInfo(SharpnessEntity sharpnessEntity, final String str) {
        if (sharpnessEntity != null) {
            try {
                if (sharpnessEntity.getPbPayInfo() != null) {
                    handlePBPayInfo(sharpnessEntity.getPbPayInfo());
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (!TextUtils.isEmpty(sharpnessEntity.getPromptText())) {
            new ToastDialogManager(getActivity()).show(sharpnessEntity.getPromptText());
        }
        if (!STATUS_Y.equals(sharpnessEntity.getPaidLive()) || STATUS_Y.equals(sharpnessEntity.getPayingUser())) {
            return;
        }
        initPayInfo(sharpnessEntity.getPaidLinks(), sharpnessEntity.getPaidAd(), false);
        clearPayInfo();
        try {
            this.timePayInfo = Integer.valueOf(sharpnessEntity.getFreeTime()).intValue();
        } catch (Exception unused) {
        }
        if (this.timePayInfo < 10) {
            showPayInfo();
            return;
        }
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(0);
        TextView textView = this.tvFreeTimeTips;
        textView.setText("可试看" + this.timePayInfo + "秒，订购权益包后可观看全部内容");
        this.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$enQfLeOiilTUanXa4iNBOFlTAE0
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AttentionFragment.lambda$handlePayInfo$63(AttentionFragment.this, str, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$handlePayInfo$63(AttentionFragment attentionFragment, String str, Long l) throws Exception {
        attentionFragment.timePayInfo--;
        if (attentionFragment.timePayInfo % 10 == 0) {
            attentionFragment.consumeFreeTime(str);
        }
        if (attentionFragment.timePayInfo == 0) {
            attentionFragment.showPayInfo();
            return;
        }
        attentionFragment.tvFreeTimeTips.setText("可试看" + attentionFragment.timePayInfo + "秒，订购权益包后可观看全部内容");
    }

    public void dismissLoading() {
        try {
            if (this.markImg != null) {
                this.markImg.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dismissImg() {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            if (this.list.get(this.mCurrentItem).getType() != 3) {
                autoJoinLiveInfo();
                findViewByPosition.findViewById(2131296393).setVisibility(8);
            } else if (this.addPlayer2UIHandler != null) {
                this.addPlayer2UIHandler.removeMessages(32);
                this.addPlayer2UIHandler.sendEmptyMessageDelayed(32, 500L);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showBgImg() {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition != null) {
                findViewByPosition.findViewById(2131296393).setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLiveList() {
        String str = "";
        try {
            ArrayList arrayList = new ArrayList();
            for (AttentionItemEntity attentionItemEntity : this.list) {
                if (attentionItemEntity.getType() < 3) {
                    arrayList.add(attentionItemEntity.getLiveData());
                }
            }
            str = System.currentTimeMillis() + "json";
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            Gson gson = new Gson();
            sharePreferenceUtil.putString(AudienceActivity.JSON_LIST_STR_FILE, str, !(gson instanceof Gson) ? gson.toJson(arrayList) : NBSGsonInstrumentation.toJson(gson, arrayList));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void leaveRoom() {
        ViewGroup viewGroup;
        try {
            this.liveTypeText = "";
            if (this.roomInfo != null && (viewGroup = (ViewGroup) this.roomInfo.getParent()) != null) {
                viewGroup.removeAllViews();
            }
            if (this.bdCloudVideoView != null) {
                this.bdCloudVideoView.stopPlayback();
            }
            showBgImg();
            stopJumpInfo();
            clearPayInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void autoJoinLiveInfo() {
        try {
            stopJumpInfo();
            this.autoJion = Observable.timer(30000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$WYtznCkFRupRDypLyICLf3yUEUc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AttentionFragment.lambda$autoJoinLiveInfo$64(AttentionFragment.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$autoJoinLiveInfo$64(AttentionFragment attentionFragment, Long l) throws Exception {
        if (attentionFragment.autoJion != null) {
            attentionFragment.jumpLiveInfo();
        }
        attentionFragment.autoJion.dispose();
        attentionFragment.autoJion = null;
    }

    private void jumpLiveInfo() {
        try {
            leaveRoom();
            Intent intent = new Intent(getActivity(), AudienceActivity.class);
            intent.putExtra("listStr", getLiveList());
            intent.putExtra("from", "fromMain");
            intent.putExtra("fromList", "fromAttention");
            intent.putExtra("shareUserNumSc", "");
            intent.putExtra("index", this.mCurrentItem + "");
            intent.putExtra("pageNum", this.pageNum);
            startActivityForResult(intent, 1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void stopJumpInfo() {
        try {
            if (this.autoJion != null) {
                this.autoJion.dispose();
                this.autoJion = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment", this);
        AudienceMainActivity audienceMainActivity = this.activityContext;
        if (audienceMainActivity.getScreenOrientation(audienceMainActivity) == 0) {
            toggleFullScreen(this.activityContext, this.mCurrentItem);
        }
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStop() {
        super.onStop();
        try {
            stopJumpInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        try {
            EventBusUtils.unregister(this);
            if (this.logTimer != null) {
                this.logTimer.dispose();
                this.logTimer = null;
            }
            if (this.reSetVideo != null) {
                this.reSetVideo.dispose();
                this.reSetVideo = null;
            }
            if (this.playProgressUpdater != null) {
                this.playProgressUpdater.dispose();
                this.playProgressUpdater = null;
            }
            if (this.playTime != null) {
                this.playTime.removeCallbacksAndMessages(null);
            }
            if (this.addPlayer2UIHandler != null) {
                this.addPlayer2UIHandler.removeCallbacksAndMessages(null);
            }
            if (this.playVideoHandler != null) {
                this.playVideoHandler.removeCallbacksAndMessages(null);
            }
            stopJumpInfo();
            clearPayInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
        try {
            stopJumpInfo();
            clearPayInfo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void uploadLog(String str) {
        try {
            StatisticsUploadUtils.uploadRealTime2(this.activityContext, "szhibo0001", "直播LIVE", "03", "", "关注", "", "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initProgressBar() {
        this.barRoot = LayoutInflater.from(this.activityContext).inflate(2131493436, (ViewGroup) null);
        this.llProgressBar = (LinearLayout) this.barRoot.findViewById(2131297760);
        this.llProgressTextLine = (LinearLayout) this.barRoot.findViewById(2131297761);
        this.tvStartTime = (TextView) this.barRoot.findViewById(2131299050);
        this.tvEndTime = (TextView) this.barRoot.findViewById(2131299052);
        this.sbProgress = (SeekBar) this.barRoot.findViewById(2131298453);
        this.vBarLandscapeBg = this.barRoot.findViewById(2131299464);
        this.vBarLandscapeEnd = this.barRoot.findViewById(2131299465);
        this.ivBarLandscapeBtn = (ImageView) this.barRoot.findViewById(2131297348);
        this.ivBarLandscapeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$VVK6_hz-zAcEk8Wy_ZN5waAZfOQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AttentionFragment.this.clickPlayOrPause();
            }
        });
        setProgressBarLandscape(false);
        clearProgress();
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.AttentionFragment.12
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (AttentionFragment.this.tvStartTime != null) {
                    AttentionFragment.this.tvStartTime.setText(NumUtils.stringForTime(i));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                AttentionFragment.this.isStartTrackingTouch = true;
                AttentionFragment.this.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(AttentionFragment.this.activityContext, 2131232586));
                AttentionFragment.this.llProgressTextLine.setVisibility(0);
                if (AttentionFragment.this.rlInfoArea != null) {
                    AttentionFragment.this.rlInfoArea.setVisibility(8);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Tracker.onStopTrackingTouch(seekBar);
                AttentionFragment.this.isStartTrackingTouch = false;
                AttentionFragment.this.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(AttentionFragment.this.activityContext, 2131232576));
                AttentionFragment.this.llProgressTextLine.setVisibility(8);
                if (AttentionFragment.this.rlInfoArea != null) {
                    AttentionFragment.this.rlInfoArea.setVisibility(0);
                }
                if (AttentionFragment.this.bdCloudVideoView != null) {
                    AttentionFragment.this.bdCloudVideoView.seekTo(AttentionFragment.this.sbProgress.getProgress());
                }
            }
        });
        this.playProgressUpdater = Observable.interval(200L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$AttentionFragment$QuiBzfx5FttM_AAc4FWRgbkFGmA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AttentionFragment.lambda$initProgressBar$66(AttentionFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$initProgressBar$66(AttentionFragment attentionFragment, Long l) throws Exception {
        BDCloudVideoView bDCloudVideoView;
        SeekBar seekBar = attentionFragment.sbProgress;
        if (seekBar != null && seekBar.getMax() > 0 && (bDCloudVideoView = attentionFragment.bdCloudVideoView) != null) {
            if (attentionFragment.isStartTrackingTouch) {
                return;
            }
            attentionFragment.sbProgress.setProgress(bDCloudVideoView.getCurrentPosition());
            return;
        }
        attentionFragment.setProgressBarMax();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayOrPause() {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            if (bDCloudVideoView.isPlaying()) {
                this.bdCloudVideoView.pause();
                AudienceMainActivity audienceMainActivity = this.activityContext;
                if (audienceMainActivity.getScreenOrientation(audienceMainActivity) == 0) {
                    this.ivBtnPlay2.setVisibility(0);
                    this.ivBtnPlay.setVisibility(8);
                } else if (this.bdCloudVideoView.getVideoWidth() > this.bdCloudVideoView.getVideoHeight()) {
                    this.ivBtnPlay2.setVisibility(8);
                    this.ivBtnPlay.setVisibility(0);
                } else {
                    this.ivBtnPlay2.setVisibility(0);
                    this.ivBtnPlay.setVisibility(8);
                }
                loadPauseAdView();
                uploadLog("点击暂停");
                this.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(this.activityContext, 2131232592));
                return;
            }
            this.bdCloudVideoView.start();
            this.ivBtnPlay.setVisibility(8);
            this.ivBtnPlay2.setVisibility(8);
            this.rlAdPauseView.setVisibility(8);
            this.rlAdPauseViewLandscape.setVisibility(8);
            uploadLog("点击播放");
            this.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(this.activityContext, 2131232593));
        }
    }

    private void addProgressTo(FrameLayout frameLayout) {
        View view;
        MsLogUtil.m7979d(TAG, "进度条加入布局");
        if (frameLayout == null || (view = this.barRoot) == null) {
            return;
        }
        ViewGroup viewGroup = (ViewGroup) view.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        this.sbProgress.setMax(0);
        frameLayout.addView(this.barRoot);
    }

    private void setProgressBarLandscape(boolean z) {
        View view = this.vBarLandscapeBg;
        if (view != null) {
            view.setVisibility(z ? 0 : 8);
            this.vBarLandscapeEnd.setVisibility(z ? 0 : 8);
            this.ivBarLandscapeBtn.setVisibility(z ? 0 : 8);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llProgressBar.getLayoutParams();
            if (z) {
                layoutParams.height = UIUtils.dip2px(70.0f);
            } else {
                layoutParams.height = -2;
            }
            this.llProgressBar.setLayoutParams(layoutParams);
        }
    }

    private void setProgressBarMax() {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView == null || this.sbProgress == null || this.tvEndTime == null || bDCloudVideoView.getDuration() <= 0) {
            return;
        }
        int duration = this.bdCloudVideoView.getDuration();
        this.sbProgress.setMax(duration);
        this.tvEndTime.setText(NumUtils.stringForTime(duration));
    }

    private void clearProgress() {
        SeekBar seekBar = this.sbProgress;
        if (seekBar == null || this.tvStartTime == null || this.tvEndTime == null) {
            return;
        }
        seekBar.setProgress(0);
        this.sbProgress.setMax(0);
        this.tvEndTime.setText("00:00");
        this.tvStartTime.setText("00:00");
    }
}
