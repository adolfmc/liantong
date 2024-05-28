package com.sinovatech.unicom.separatemodule.audience;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.billy.android.swipe.SmartSwipe;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.SwipeConsumerExclusiveGroup;
import com.billy.android.swipe.consumer.DrawerConsumer;
import com.billy.android.swipe.consumer.SlidingConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.jakewharton.rxbinding2.view.RxView;
import com.megvii.livenesslib.util.ConUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.AudienceLiveInfoEvent;
import com.sinovatech.unicom.basic.eventbus.NetEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLEnvironmentConfig;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMessageView;
import com.sinovatech.unicom.separatemodule.audience.adpter.AudienceAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.ActivityTimeEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AngleMoreEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionAnchorVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionItemEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AttentionVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceUser;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LianMZBEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ListDataEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveBanner;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveBannerEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveMsg;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveOrFengGuangInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveRoomUiHideEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.PlayBackPayInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.UsefulChatEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoMoreConfigEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuangXiuEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.fragment.MultiViewLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.SlowLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.fragment.TheSameCityLiveFragment;
import com.sinovatech.unicom.separatemodule.audience.interfaceImpl.OnItemShowedListener;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.audience.socket.ManagerSocket;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.util.InputDialog;
import com.sinovatech.unicom.separatemodule.audience.util.SpringScaleInterpolator;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGuanzhuDialog;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceInnerRecylerView;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView;
import com.sinovatech.unicom.separatemodule.audience.view.HelpingHandDialog;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView;
import com.sinovatech.unicom.separatemodule.audience.view.LiveNoMoreDataFooter;
import com.sinovatech.unicom.separatemodule.audience.view.LivePvResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.MyGestureView;
import com.sinovatech.unicom.separatemodule.audience.view.SmallVideoView;
import com.sinovatech.unicom.separatemodule.audience.view.ToastDialogManager;
import com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog;
import com.sinovatech.unicom.separatemodule.audience.view.WiseEditText;
import com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentActivity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.p317ui.CircularLoading;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener;
import com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.sinovatech.unicom.separatemodule.videocenter.view.LiveSimpleLoadMoreView;
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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:987)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:230)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:152)
    	at java.util.ArrayList.forEach(ArrayList.java:1259)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:62)
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceActivity extends BaseActivity {

    /* renamed from: JS */
    private static final String f18464JS = "fromJS";
    public static final String JSON_LIST_STR_FILE = "live_json_list_str_file_name";
    private static final String LIST = "fromList";
    private static final String SINGLE = "fromSingle";
    private static final String TASK = "task";
    public static boolean bdCloudIsWebPlayer;
    public static BDCloudVideoView bdCloudVideoView;
    public static boolean isTask;
    private static String userId;
    public NBSTraceUnit _nbs_trace;
    private Disposable addView2List;
    private InputDialog alivcInputTextDialog;
    private BaseQuickAdapter<AngleMoreEntity, BaseViewHolder> angleAdapter;
    private Animation animation2;
    private AttentionAnchorVideoEntity attentionAnchors;
    private ImageView bgImage;
    private long bufferEndTime;
    private long bufferStartTime;
    private long bufferTime;
    public String buyUrl;
    private Disposable changeAngleView;
    private View.OnClickListener clickVideo;
    private DrawerConsumer consumer;
    private String coverImg;
    private SharpnessEntity.LiveUrlBean currentLiveUrl;
    private String currentLogo;
    private LiveGiftView dianZanAnim;
    private Disposable disposable;
    float endX;
    float endY;
    private Disposable errorResetDely;
    private FrameLayout flInputPassword;
    private String fromList;
    private String fromType;

    /* renamed from: group */
    private SwipeConsumerExclusiveGroup f27860group;
    private HelpingHandDialog helpingDialog;
    private BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> huifangAdapter;
    private WiseEditText inputPw1;
    private WiseEditText inputPw2;
    private WiseEditText inputPw3;
    private WiseEditText inputPw4;
    private boolean isClickedLianMai;
    private boolean isFirst;
    private boolean isHorizontal;
    private boolean isLoadMore;
    private boolean isLoadPBing;
    public boolean isMulti;
    public boolean isNeedPay;
    public boolean isPB;
    private boolean isPageSelected;
    private boolean isRunning;
    private boolean isXiabo;
    private ImageView ivLogo;
    private View ivPlayBtn;
    private ImageView ivPlayIcon;
    private Disposable kadunSub;
    private AudienceLandscapeView landscapeView;
    private BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> liveAdapter;
    private String liveChannel;
    private LiveRoomUiHideEntity liveRoomUIInfo;
    private String liveType;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private View llMultiView;
    private LinearLayout llPayInfo;
    private LinearLayout llPayTipe;
    private LinearLayout llTrySeeDialog;
    private LinearLayout llTwo;
    private Dialog loadingDialog;
    private AudienceAdapter mAdapter;
    private int mCurrentItem;
    private int mItemFormMain;
    private ViewPagerLayoutManager mLayoutManager;
    private List<VideoMoreConfigEntity> mMoreConfigs;
    private RecyclerView mRecyclerView;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private ManagerSocket managerSocket;
    private AudienceMessageView messageView;
    private boolean mianLiuFlag;
    private FrameLayout mianLiuTipLayout;
    private LinearLayoutManager multiLayoutManager;
    private View multiRoot;
    private String pageCode;
    public PlayBackPayInfoEntity payInfo4PB;
    private Disposable payInfoCountDown;
    private FrameLayout payView;

    /* renamed from: pd */
    private CustomePorgressDialog f18465pd;
    private RelativeLayout rlInputPw;
    private View rlPlayBtn;
    private RelativeLayout rlTip;
    private RecyclerView rlvContent;
    private View rootView;
    private String shareCode;
    private SharpnessEntity sharpness;
    private View sideslipLayout;
    private String singleUserId;
    private SmallVideoView smallVideoView;
    private SmartSwipeRefresh smartSwipeRefresh;
    float startX;
    float startY;
    private long streamEndTime;
    private long streamStartTime;
    private TextView tabAdvance;
    private TextView tabFengguang;
    private TextView tabLive;
    private TextView tabPlayback;
    private int timePayInfo;
    private String tips;
    private ImageView toggleBack;
    private TextView tvBtnPay4PB;
    private TextView tvCancel;
    private TextView tvFreeTimeTips;
    private TextView tvNoOrder;
    private TextView tvOk;
    private TextView tvOpportunity;
    private TextView tvOrder;
    private TextView tvPayTips;
    private TextView tvReset;
    private ImageView upTipImage;
    private View v2Video;
    private View vTop1;
    private View vTop2;
    private String videoId;
    private BannerViewPager vpBanner;
    private Disposable watchTimer;
    private SmartSwipeWrapper wrapper;
    private BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> xuannifengguangAdapter;
    private BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder> yugaoAdapter;
    public ZhuboDataEntity zhuboDataEntity;
    private final String TAG = "AudienceActivity";
    private final AppCompatActivity activityContext = this;
    private List<ListDataEntity> list = new ArrayList();
    private final AudienceUser audienceUser = new AudienceUser();
    private boolean isPlaying = false;
    private String pageNum = "0";
    private String playBackPageNum = "0";
    private String AD_SHOW_TIME = "AD_SHOW_TIME";
    private String Ad_SHOW_SECOND_TIME = "Ad_SHOW_SECOND_TIME";
    private Handler closer = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$i79lWVAcEgkNqlWdY96zQRqjTVw
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return AudienceActivity.lambda$new$12(AudienceActivity.this, message);
        }
    });
    private final Map<Integer, AudienceDataEntity> temporatyList = new HashMap();
    private Handler playVideoHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.9
        {
            AudienceActivity.this = this;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            AudienceActivity audienceActivity = AudienceActivity.this;
            audienceActivity.handleSharpnessInfo(audienceActivity.sharpness, ((ListDataEntity) AudienceActivity.this.list.get(AudienceActivity.this.mCurrentItem)).getJobNumber(), AudienceActivity.this.rootView, AudienceActivity.this.coverImg);
            return false;
        }
    });
    private Handler verifyPwdHandler = new Handler(Looper.myLooper(), new C827111());
    private Handler autoNextLive = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.16
        {
            AudienceActivity.this = this;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            try {
                MsLogUtil.m7979d("AudienceActivity", "自动切换autoNextLive");
                AudienceActivity.this.isPageSelected = false;
                if (AudienceActivity.this.mRecyclerView != null) {
                    AudienceActivity.this.mRecyclerView.smoothScrollToPosition(message.what);
                    if (AudienceActivity.this.playVideo != null) {
                        AudienceActivity.this.playVideo.sendEmptyMessageDelayed(0, 500L);
                    }
                }
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
            return false;
        }
    });
    private Handler playVideo = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.17
        {
            AudienceActivity.this = this;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            try {
                if (AudienceActivity.this.isPageSelected) {
                    return false;
                }
                AudienceActivity.this.leaveRoom(AudienceActivity.this.mCurrentItem);
                if (AudienceActivity.this.isXiabo) {
                    AudienceActivity.this.filerList(AudienceActivity.this.mCurrentItem);
                }
                AudienceActivity.this.startPlay(AudienceActivity.this.mCurrentItem);
                MsLogUtil.m7979d("AudienceActivity", "处理播放playVideo 集合size=" + AudienceActivity.this.list.size());
                return false;
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                return false;
            }
        }
    });
    private String livingPN = "0";
    private String fengGuangPN = "0";
    private String yugaoPN = "0";
    private String oldListPage = "1";
    private List<BDCloudVideoView> players = new ArrayList();
    private List<BDCloudVideoView> selectedPlayer = new ArrayList();

    static {
    }

    public static /* synthetic */ void lambda$IntentGo$80(Boolean bool) throws Exception {
    }

    public static /* synthetic */ void lambda$entrance$21(List list) throws Exception {
    }

    public static /* synthetic */ void lambda$startPlay$35(String str) throws Exception {
    }

    /*  JADX ERROR: Failed to decode insn: 0x04A4: UNKNOWN(0x80E5), method: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.lambda$startPlay$43(com.sinovatech.unicom.separatemodule.audience.AudienceActivity, int, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04A4: UNKNOWN(0x80E5)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x04DE: FILLED_NEW_ARRAY_RANGE , method: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.lambda$startPlay$43(com.sinovatech.unicom.separatemodule.audience.AudienceActivity, int, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity):void
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x04E8: UNKNOWN(0x80F4), method: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.lambda$startPlay$43(com.sinovatech.unicom.separatemodule.audience.AudienceActivity, int, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x04E8: UNKNOWN(0x80F4)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    public static /* synthetic */ void lambda$startPlay$43(com.sinovatech.unicom.separatemodule.audience.AudienceActivity r10, int r11, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1288
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.lambda$startPlay$43(com.sinovatech.unicom.separatemodule.audience.AudienceActivity, int, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity):void");
    }

    public static /* synthetic */ void lambda$watchedLive5m$3(BaseVideoEntity baseVideoEntity) throws Exception {
    }

    public static /* synthetic */ void lambda$zanVideo$53(String str) throws Exception {
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

    public AudienceActivity() {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        try {
            UIUtils.logD("lln", "onCreate");
            UIUtils.setStatusBarMode(this, false, true);
            AudienceInnerRecylerView.isInnerRecylerView = false;
            AudienceInnerRecylerView.ISLANDSCAPE = false;
            setContentView(2131493006);
            GlideApp.with((FragmentActivity) this).load((Integer) 2131232734).into((ImageView) findViewById(2131297427));
            EventBusUtils.register(this);
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
            this.alivcInputTextDialog = new InputDialog(this.activityContext, "1234567");
            this.f18465pd = new CustomePorgressDialog(this.activityContext);
            this.f18465pd.setMessage("正在加载中");
            this.f18465pd.setCanceledOnTouchOutside(true);
            this.f18465pd.setCancelable(true);
            initView();
            initSocket();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
        if (!App.hasLogined()) {
            new AvoidOnResult(this).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$jhtlLZgGqWHTghLu6km6WT3hQUg
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public final void onActivityResult(int i, Intent intent) {
                    AudienceActivity.lambda$onCreate$0(AudienceActivity.this, i, intent);
                }
            });
            NBSAppInstrumentation.activityCreateEndIns();
            return;
        }
        entrance(getIntent());
        init2Video();
        getShareCode();
        initTenTask();
        if (!"AttentionAnchors".equals(this.fromType)) {
            initMoreLive();
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    public static /* synthetic */ void lambda$onCreate$0(AudienceActivity audienceActivity, int i, Intent intent) {
        if (App.hasLogined()) {
            audienceActivity.entrance(audienceActivity.getIntent());
        } else {
            audienceActivity.activityContext.finish();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onSaveInstanceState(Bundle bundle) {
        bundle.putBoolean("screenOrientation", this.isHorizontal);
        super.onSaveInstanceState(bundle);
    }

    @Override // android.app.Activity
    protected void onRestoreInstanceState(@NonNull Bundle bundle) {
        super.onRestoreInstanceState(bundle);
        this.isHorizontal = bundle.getBoolean("screenOrientation");
        if (this.isHorizontal) {
            setRequestedOrientation(1);
        }
    }

    private void initTenTask() {
        try {
            if (TASK.equals(this.fromType)) {
                this.disposable = Observable.interval(10L, TimeUnit.SECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$DN7S8imFF69PUY1A83ksarEVsEw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.lambda$initTenTask$2(AudienceActivity.this, (Long) obj);
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initTenTask$2(AudienceActivity audienceActivity, Long l) throws Exception {
        try {
            if (!audienceActivity.checkDay()) {
                long j = App.getSharePreferenceUtil().getLong(audienceActivity.Ad_SHOW_SECOND_TIME) + 10;
                if (j >= 600) {
                    audienceActivity.stopTimer();
                    audienceActivity.managerAudienceLoadData.watchlive(userId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$2Wi5VtAjI4IMvZBOXSVK6ahynbc
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity audienceActivity2 = AudienceActivity.this;
                            MsLogUtil.m7979d("AudienceActivity", ((BaseVideoEntity) obj).getMessage());
                        }
                    });
                }
                App.getSharePreferenceUtil().putLong(audienceActivity.Ad_SHOW_SECOND_TIME, j);
                return;
            }
            App.getSharePreferenceUtil().putLong(audienceActivity.Ad_SHOW_SECOND_TIME, 10L);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void stopTimer() {
        Disposable disposable = this.disposable;
        if (disposable != null) {
            disposable.dispose();
            this.disposable = null;
        }
    }

    private void watchedLive5m() {
        try {
            stopWatchTimer();
            this.watchTimer = Observable.interval(1L, TimeUnit.SECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$nxF_ukmQAeb5Ks5maTA19uu4gwg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$watchedLive5m$4(AudienceActivity.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$watchedLive5m$4(AudienceActivity audienceActivity, Long l) throws Exception {
        if (l.longValue() == 300) {
            audienceActivity.managerAudienceLoadData.watchedLive5m(userId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$rTDZLeLqSlDXKrcGFwsWUsrVk0A
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$watchedLive5m$3((BaseVideoEntity) obj);
                }
            });
            audienceActivity.stopWatchTimer();
        }
    }

    private void stopWatchTimer() {
        Disposable disposable = this.watchTimer;
        if (disposable != null) {
            disposable.dispose();
            this.watchTimer = null;
        }
    }

    @SuppressLint({"ClickableViewAccessibility"})
    private void init2Video() {
        try {
            this.v2Video = findViewById(2131299462);
            this.v2Video.setOnTouchListener(new View.OnTouchListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$xLgZbOPN63qHkPIfZKux5r2BX4o
                @Override // android.view.View.OnTouchListener
                public final boolean onTouch(View view, MotionEvent motionEvent) {
                    return AudienceActivity.lambda$init2Video$5(AudienceActivity.this, view, motionEvent);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ boolean lambda$init2Video$5(AudienceActivity audienceActivity, View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                audienceActivity.startX = motionEvent.getX();
                audienceActivity.startY = motionEvent.getY();
                return true;
            case 1:
                audienceActivity.endX = motionEvent.getX();
                audienceActivity.endY = motionEvent.getY();
                float f = audienceActivity.endX;
                float f2 = audienceActivity.startX;
                if (f - f2 > 0.0f && Math.abs(f - f2) > Math.abs(audienceActivity.endY - audienceActivity.startY)) {
                    UIUtils.logD("xcy", "===>向右滑动");
                    return true;
                }
                float f3 = audienceActivity.endX;
                float f4 = audienceActivity.startX;
                if (f3 - f4 > 0.0f || Math.abs(f3 - f4) <= Math.abs(audienceActivity.endY - audienceActivity.startY)) {
                    return true;
                }
                audienceActivity.releaseVideo();
                if (TASK.equals(audienceActivity.fromType)) {
                    return true;
                }
                Intent intent = new Intent(audienceActivity.activityContext, AudienceMainActivity.class);
                intent.putExtra("type", "smallVideo");
                audienceActivity.activityContext.startActivity(intent);
                return true;
            default:
                return true;
        }
    }

    private boolean checkDay() {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String string = App.getSharePreferenceUtil().getString(this.AD_SHOW_TIME);
            App.getSharePreferenceUtil().putString(this.AD_SHOW_TIME, simpleDateFormat.format(new Date()));
            if (TextUtils.isEmpty(string)) {
                return false;
            }
            return !string.equals(simpleDateFormat.format(new Date()));
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            return false;
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        if (bdCloudVideoView != null) {
            releaseVideo();
        }
        entrance(intent);
    }

    private void playersPorS(boolean z) {
        try {
            if (this.players == null || isFinishing()) {
                return;
            }
            for (BDCloudVideoView bDCloudVideoView : this.players) {
                if (z) {
                    bDCloudVideoView.start();
                } else {
                    bDCloudVideoView.pause();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        try {
            if (bdCloudVideoView != null && !bdCloudVideoView.isPlaying() && !this.isMulti && this.currentLiveUrl != null && !this.isXiabo) {
                playUrl(this.currentLiveUrl);
            }
            playersPorS(true);
            this.isRunning = true;
            watchedLive5m();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        if (getScreenOrientation(this) == 0) {
            if (this.smallVideoView != null) {
                toggleFullScreen4SmallVideo(this);
            } else {
                toggleFullScreen(this);
            }
        }
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
        try {
            UIUtils.logD("AudienceActivity", "onStop");
            if (bdCloudVideoView != null && bdCloudVideoView.isPlaying() && !bdCloudIsWebPlayer) {
                bdCloudVideoView.stopPlayback();
            }
            if (this.isMulti && bdCloudIsWebPlayer) {
                bdCloudVideoView.setVolume(1.0f, 1.0f);
            }
            playersPorS(false);
            this.isRunning = false;
            stopTimer();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        try {
            UIUtils.logD("AudienceActivity", "onPause");
            if (bdCloudVideoView != null && bdCloudVideoView.isPlaying() && !bdCloudIsWebPlayer) {
                bdCloudVideoView.pause();
            }
            if (isFinishing()) {
                UIUtils.logD("AudienceActivity", "isFinishing");
                try {
                    recycleRecyclerView();
                    if (this.messageView != null) {
                        this.messageView.clearMessage();
                        this.messageView.releaseTimer();
                        this.messageView.releasePkTimer();
                    }
                    releaseVideo();
                    EventBusUtils.unregister(this);
                    this.managerSocket.onSocketStop();
                    clearPayInfo();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            stopWatchTimer();
        } catch (Exception e2) {
            MsLogUtil.m7979d("AudienceActivity", e2.getMessage());
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        try {
            UIUtils.logD("AudienceActivity", "onDestroy");
            try {
                if (this.verifyPwdHandler != null) {
                    this.verifyPwdHandler.removeCallbacksAndMessages(null);
                }
                if (this.playVideoHandler != null) {
                    this.playVideoHandler.removeCallbacksAndMessages(null);
                }
                if (this.autoNextLive != null) {
                    this.autoNextLive.removeCallbacksAndMessages(null);
                }
                if (this.playVideo != null) {
                    this.playVideo.removeCallbacksAndMessages(null);
                }
                if (this.messageView != null) {
                    this.messageView.clearMessage();
                    this.messageView.releaseTimer();
                    this.messageView.releasePkTimer();
                }
                releaseVideo();
                EventBusUtils.unregister(this);
                this.managerSocket.onSocketStop();
                isTask = false;
            } catch (Exception e) {
                e.printStackTrace();
            }
            stopTimer();
        } catch (Exception e2) {
            MsLogUtil.m7979d("AudienceActivity", e2.getMessage());
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        try {
            if (getScreenOrientation(this.activityContext) == 0) {
                toggleFullScreen(this.activityContext);
            } else if (bdCloudVideoView != null && bdCloudVideoView.isPlaying()) {
                releaseVideo();
                this.managerSocket.onSocketStop();
                returnIndex();
                super.onBackPressed();
            } else {
                returnIndex();
                finish();
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            if (this.isXiabo) {
                bdCloudVideoView.stopPlayback();
                return;
            }
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            if (i != 4111 && this.currentLiveUrl != null) {
                if (i == 4000 && this.isMulti) {
                    UIUtils.logD("多视角", "onActivityResult返回多视角");
                    if (getMultiSelectedCount() != 1) {
                        bdCloudVideoView.setVolume(0.0f, 0.0f);
                        return;
                    }
                }
                if (this.currentLiveUrl != null && !this.isXiabo && !this.currentLiveUrl.getLivePullUrl().contains(".mp4")) {
                    setPlayerUrl(this.currentLiveUrl, findViewByPosition);
                }
                if (this.zhuboDataEntity != null) {
                    this.managerAudienceLoadData.isGuanzhu(this.zhuboDataEntity.getAnchorInfoBean().getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$DOu9UrC2GCKEkVU6uo1S8_NX69k
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$onActivityResult$6(AudienceActivity.this, (Boolean) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
                if (i2 == 2112) {
                    this.messageView.delChat(intent.getIntExtra("chatDelPosition", -1));
                    return;
                }
                return;
            }
            getSharpnessInfo(userId, findViewByPosition, this.list.get(this.mCurrentItem).getCoverImg(), this.mCurrentItem);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$onActivityResult$6(AudienceActivity audienceActivity, Boolean bool) throws Exception {
        AudienceMessageView audienceMessageView = audienceActivity.messageView;
        if (audienceMessageView != null) {
            audienceMessageView.setGuanzhuView(bool.booleanValue(), null);
            AudienceLandscapeView audienceLandscapeView = audienceActivity.landscapeView;
            if (audienceLandscapeView != null) {
                audienceLandscapeView.setGuanzuGone(bool.booleanValue());
            }
        }
    }

    private void initView() {
        try {
            this.upTipImage = (ImageView) findViewById(2131296444);
            this.mianLiuTipLayout = (FrameLayout) findViewById(2131296437);
            this.bgImage = (ImageView) findViewById(2131296397);
            this.toggleBack = (ImageView) findViewById(2131296464);
            this.ivLogo = (ImageView) findViewById(2131297426);
            this.messageView = (AudienceMessageView) LayoutInflater.from(this.activityContext).inflate(2131492998, (ViewGroup) null);
            this.landscapeView = (AudienceLandscapeView) LayoutInflater.from(this.activityContext).inflate(2131492990, (ViewGroup) null);
            BDCloudVideoView.setAK("3927c43912004909bf897578e93bc5f9");
            bdCloudVideoView = (BDCloudVideoView) LayoutInflater.from(this).inflate(2131492974, (ViewGroup) null);
            bdCloudVideoView.setMaxProbeTime(200);
            bdCloudVideoView.setCachingHintViewVisibility(false);
            bdCloudVideoView.setLooping(true);
            bdCloudVideoView.setBufferTimeInMs(200);
            bdCloudVideoView.setMaxCacheSizeInBytes(1048576);
            bdCloudVideoView.toggleFrameChasing(false);
            bdCloudVideoView.setVideoScalingMode(1);
            bdCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$rhm1VrLHgx1Z6gpIjvljKdqGYUY
                @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
                public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                    AudienceActivity.lambda$initView$10(AudienceActivity.this, playerState);
                }
            });
            TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "直播详情", "百度播放器", "", "com.baidu.cloud.cloudplayer", "0");
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initView$10(AudienceActivity audienceActivity, BDCloudVideoView.PlayerState playerState) {
        try {
            UIUtils.logD("bdCloudVideoView", "333333333----" + playerState);
            switch (playerState) {
                case STATE_ERROR:
                    audienceActivity.showLiveCover();
                    if (audienceActivity.isPlaying) {
                        UIUtils.logD("upKadunLog", "拉流失败");
                        audienceActivity.upKadunLog(LogFlag.STREAMERROR);
                    } else {
                        UIUtils.logD("upKadunLog", "启动失败");
                        audienceActivity.upKadunLog(LogFlag.STARTERROR);
                    }
                    if (audienceActivity.errorResetDely != null && !audienceActivity.errorResetDely.isDisposed()) {
                        audienceActivity.errorResetDely.dispose();
                    }
                    audienceActivity.errorResetDely = Observable.timer(5000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$acZIvMbyVkv2fqwR0pW5G6GOJaA
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$initView$7(AudienceActivity.this, (Long) obj);
                        }
                    });
                    return;
                case STATE_IDLE:
                    if (audienceActivity.kadunSub != null && !audienceActivity.kadunSub.isDisposed()) {
                        audienceActivity.kadunSub.dispose();
                    }
                    audienceActivity.isPlaying = false;
                    audienceActivity.streamStartTime = 0L;
                    audienceActivity.bufferStartTime = 0L;
                    audienceActivity.showLiveCover();
                    return;
                case STATE_PREPARING:
                    bdCloudVideoView.setCachingHintViewVisibility(false);
                    String currentPlayingUrl = bdCloudVideoView.getCurrentPlayingUrl();
                    audienceActivity.bufferStartTime = System.currentTimeMillis();
                    if (TextUtils.isEmpty(currentPlayingUrl) || !currentPlayingUrl.contains(".mp4")) {
                        if (audienceActivity.kadunSub != null && !audienceActivity.kadunSub.isDisposed()) {
                            audienceActivity.kadunSub.dispose();
                        }
                        audienceActivity.kadunSub = Observable.timer(8000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$5CKvTic8H01gHe2RvKqPqoX9JQs
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                AudienceActivity.lambda$initView$8(AudienceActivity.this, (Long) obj);
                            }
                        });
                        return;
                    }
                    return;
                case STATE_PREPARED:
                    audienceActivity.bufferEndTime = System.currentTimeMillis();
                    if (audienceActivity.bufferEndTime - audienceActivity.bufferStartTime < 5000 && audienceActivity.kadunSub != null && !audienceActivity.kadunSub.isDisposed()) {
                        audienceActivity.kadunSub.dispose();
                    }
                    audienceActivity.isPlaying = true;
                    audienceActivity.streamEndTime = System.currentTimeMillis();
                    if (audienceActivity.streamStartTime != 0) {
                        audienceActivity.bufferTime = audienceActivity.streamEndTime - audienceActivity.streamStartTime;
                        audienceActivity.streamStartTime = 0L;
                        audienceActivity.upKadunLog(LogFlag.TIME);
                        UIUtils.logD("upKadunLog", "缓冲超时");
                        return;
                    }
                    return;
                case STATE_PLAYING:
                    if (audienceActivity.isXiabo) {
                        bdCloudVideoView.stopPlayback();
                    }
                    bdCloudVideoView.setCachingHintViewVisibility(false);
                    if (!audienceActivity.isRunning && !bdCloudIsWebPlayer) {
                        bdCloudVideoView.pause();
                    }
                    if (audienceActivity.isNeedPay) {
                        bdCloudVideoView.pause();
                        return;
                    } else {
                        audienceActivity.hideLiveCover();
                        return;
                    }
                case STATE_PAUSED:
                    return;
                case STATE_PLAYBACK_COMPLETED:
                    if (audienceActivity.errorResetDely != null && !audienceActivity.errorResetDely.isDisposed()) {
                        audienceActivity.errorResetDely.dispose();
                    }
                    audienceActivity.errorResetDely = Observable.timer(1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$m3B6hwpQv2PksyCJuwYJvuxBTTs
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$initView$9(AudienceActivity.this, (Long) obj);
                        }
                    });
                    return;
                case STATE_VIDEOSIZE_CHANGED:
                    UIUtils.logD("bdCloudVideoView-onALL", bdCloudVideoView.getVideoHeight() + "----" + playerState + "-----" + bdCloudVideoView.getVideoWidth());
                    try {
                        View findViewByPosition = audienceActivity.mLayoutManager.findViewByPosition(audienceActivity.mCurrentItem);
                        if (findViewByPosition == null || bdCloudIsWebPlayer || audienceActivity.isNeedPay) {
                            return;
                        }
                        audienceActivity.addVideoView(findViewByPosition);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        } catch (Exception e2) {
            MsLogUtil.m7979d("AudienceActivity", e2.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initView$7(AudienceActivity audienceActivity, Long l) throws Exception {
        UIUtils.logD("upKadunLog", "启动失败");
        if (audienceActivity.isNeedPay) {
            return;
        }
        bdCloudVideoView.start();
    }

    public static /* synthetic */ void lambda$initView$8(AudienceActivity audienceActivity, Long l) throws Exception {
        audienceActivity.upKadunLog(LogFlag.PLAYINGBUFFER);
        List<SharpnessEntity.LiveUrlBean> list = audienceActivity.liveUrlList;
        if (list == null || list.size() <= 0) {
            return;
        }
        String string = App.getSharePreferenceUtil().getString("gzdqingxidu");
        UIUtils.logD("AudienceActivity", "卡顿=" + string);
        if ("L1".equals(string)) {
            return;
        }
        App.getSharePreferenceUtil().putString("gzdqingxidu", "L1");
        UIUtils.toastCenterLong("网络卡顿，已自动为您切换清晰度");
        bdCloudVideoView.stopPlayback();
        List<SharpnessEntity.LiveUrlBean> list2 = audienceActivity.liveUrlList;
        audienceActivity.playUrl(list2.get(list2.size() <= 1 ? 0 : 1));
    }

    public static /* synthetic */ void lambda$initView$9(AudienceActivity audienceActivity, Long l) throws Exception {
        if (audienceActivity.isNeedPay) {
            return;
        }
        bdCloudVideoView.start();
    }

    private void showLiveCover() {
        View findViewByPosition;
        ImageView imageView;
        try {
            if (this.mLayoutManager == null || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null || (imageView = (ImageView) findViewByPosition.findViewById(2131296393)) == null) {
                return;
            }
            UIUtils.logD("AudienceActivity", "显示直播间背景图");
            if (getMultiSelectedCount() < 2) {
                imageView.setVisibility(0);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void hideLiveCover() {
        View findViewByPosition;
        try {
            if (this.mLayoutManager == null || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null) {
                return;
            }
            ImageView imageView = (ImageView) findViewByPosition.findViewById(2131296393);
            if (imageView != null) {
                UIUtils.logD("AudienceActivity", "隐藏直播间背景图");
                imageView.setVisibility(8);
            }
            View findViewByPosition2 = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition2 != null) {
                View findViewById = findViewByPosition2.findViewById(2131296393);
                if (findViewById != null) {
                    findViewById.setVisibility(8);
                }
                View findViewById2 = findViewByPosition2.findViewById(2131296394);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(8);
                }
                View findViewById3 = findViewByPosition2.findViewById(2131299478);
                if (findViewById3 != null) {
                    findViewById3.setVisibility(8);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void resetLiveCover() {
        View findViewByPosition;
        FrameLayout frameLayout;
        ImageView imageView;
        try {
            if (this.mLayoutManager == null || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null || (frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296995)) == null || (imageView = (ImageView) findViewByPosition.findViewById(2131296393)) == null) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            if (frameLayout != viewGroup) {
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                frameLayout.addView(imageView);
                return;
            }
            UIUtils.logD("直播间详情", "封面图在原位不处理");
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void setLiveCoverLandscape() {
        View findViewByPosition;
        LinearLayout linearLayout;
        ImageView imageView;
        try {
            if (this.mLayoutManager == null || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null || (linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297734)) == null || (imageView = (ImageView) findViewByPosition.findViewById(2131296393)) == null) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) imageView.getParent();
            if (linearLayout != viewGroup) {
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                linearLayout.addView(imageView);
                return;
            }
            UIUtils.logD("直播间详情", "封面图在原位不处理");
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void initRecyclerView() {
        try {
            this.isFirst = true;
            this.mRecyclerView = (RecyclerView) findViewById(2131296463);
            if (this.mLayoutManager == null) {
                this.mLayoutManager = new ViewPagerLayoutManager(this, 1);
            }
            this.mAdapter = new AudienceAdapter(this.list, this);
            this.mRecyclerView.setLayoutManager(this.mLayoutManager);
            this.mRecyclerView.setAdapter(this.mAdapter);
            this.mAdapter.setListener(new OnItemShowedListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$wmamsFC5hYWVfIQlRYOInjWDmVs
                @Override // com.sinovatech.unicom.separatemodule.audience.interfaceImpl.OnItemShowedListener
                public final void playVideo(int i) {
                    AudienceActivity.lambda$initRecyclerView$11(AudienceActivity.this, i);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initRecyclerView$11(AudienceActivity audienceActivity, int i) {
        try {
            if (audienceActivity.isFirst && i == audienceActivity.mCurrentItem) {
                audienceActivity.isFirst = false;
                audienceActivity.startPlay(i);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void recycleRecyclerView() {
        this.mAdapter = null;
        this.mLayoutManager = null;
        if (this.mRecyclerView != null) {
            this.mRecyclerView = null;
        }
    }

    private void initLoadMore() {
        try {
            this.smartSwipeRefresh = SmartSwipeRefresh.translateMode((LinearLayout) findViewById(2131298159), false, false).setDataLoader(new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.1
                @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
                public void onRefresh(SmartSwipeRefresh smartSwipeRefresh) {
                }

                {
                    AudienceActivity.this = this;
                }

                @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
                public void onLoadMore(SmartSwipeRefresh smartSwipeRefresh) {
                    MsLogUtil.m7980d("加载下一页数据");
                    if (smartSwipeRefresh != null) {
                        smartSwipeRefresh.finished(true);
                    }
                }
            }).disableRefresh().setFooter(new LiveNoMoreDataFooter(this.activityContext));
            ((SlidingConsumer) this.smartSwipeRefresh.getSwipeConsumer().m17389as(SlidingConsumer.class)).addToExclusiveGroup(this.f27860group).addListener(new SimpleSwipeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.2
                {
                    AudienceActivity.this = this;
                }

                @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                public void onSwipeProcess(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, boolean z, float f) {
                    if (f == 1.5d) {
                        try {
                            if (AudienceActivity.this.closer != null) {
                                AudienceActivity.this.closer.sendEmptyMessageDelayed(0, 1200L);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ boolean lambda$new$12(AudienceActivity audienceActivity, Message message) {
        try {
            if (audienceActivity.smartSwipeRefresh == null || audienceActivity.smartSwipeRefresh.getSwipeConsumer() == null) {
                return false;
            }
            audienceActivity.smartSwipeRefresh.getSwipeConsumer().smoothClose();
            return false;
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            return false;
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C82953 implements OnViewPagerListener {
        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onLayoutComplete() {
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageRelease(boolean z, int i) {
        }

        C82953() {
            AudienceActivity.this = r1;
        }

        @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
        public void onPageSelected(int i, boolean z) {
            try {
                MsLogUtil.m7979d("AudienceActivity", "滑动选择了：" + i + "|mCurrentItem=" + AudienceActivity.this.mCurrentItem);
                AudienceActivity.this.isPageSelected = true;
                if (AudienceActivity.this.mCurrentItem != i) {
                    AudienceActivity.this.leaveRoom(AudienceActivity.this.mCurrentItem);
                    if (AudienceActivity.this.isXiabo) {
                        AudienceActivity.this.filerList(AudienceActivity.this.mCurrentItem);
                    } else {
                        AudienceActivity.this.mCurrentItem = i;
                    }
                    AudienceActivity.this.mRecyclerView.postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$3$Msh5IujnLpt2c7cUzzxiGQAJ4hk
                        @Override // java.lang.Runnable
                        public final void run() {
                            AudienceActivity.C82953.lambda$onPageSelected$0(AudienceActivity.C82953.this);
                        }
                    }, 500L);
                }
                if ("-1".equals(AudienceActivity.this.pageNum) || !z || AudienceActivity.this.isLoadMore) {
                    if (TextUtils.isEmpty(AudienceActivity.this.playBackPageNum) || "-1".equals(AudienceActivity.this.playBackPageNum) || !z || AudienceActivity.this.isLoadMore || AudienceActivity.this.isLoadPBing) {
                        return;
                    }
                    AudienceActivity.this.isLoadMore = true;
                    AudienceActivity.this.loadPlayBackData(AudienceActivity.this.playBackPageNum);
                    return;
                }
                AudienceActivity.this.isLoadMore = true;
                if (AudienceActivity.this.singleUserId == null) {
                    if ("fromMain".equals(AudienceActivity.this.fromType)) {
                        if ("fromAttention".equals(AudienceActivity.this.fromList)) {
                            AudienceActivity.this.loadAttentionData();
                            return;
                        } else {
                            AudienceActivity.this.loadData();
                            return;
                        }
                    } else if ("AttentionAnchors".equals(AudienceActivity.this.fromType)) {
                        AudienceActivity.this.getNextVideoInfoByAttention(Integer.parseInt(AudienceActivity.this.pageNum));
                        return;
                    } else {
                        return;
                    }
                }
                AudienceActivity.this.loadData(AudienceActivity.this.singleUserId);
            } catch (NumberFormatException e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        public static /* synthetic */ void lambda$onPageSelected$0(C82953 c82953) {
            AudienceActivity audienceActivity = AudienceActivity.this;
            audienceActivity.startPlay(audienceActivity.mCurrentItem);
        }
    }

    private void initListener() {
        try {
            this.mLayoutManager.setOnViewPagerListener(new C82953());
            RxView.clicks(this.toggleBack).throttleFirst(1L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$4uKcYOFuKcj_MFx__ucUoFT-4zM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    r0.toggleFullScreen(AudienceActivity.this.activityContext);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$8aG9Ozi77-C_-HuJ-QYkDsxsXDQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity audienceActivity = AudienceActivity.this;
                    MsLogUtil.m7979d("AudienceActivity", ((Throwable) obj).getMessage());
                }
            });
            initLoadMore();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void getSlowLive() {
        try {
            this.managerAudienceLoadData.getSlowLiveList(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$hdXHtJrvr_zREj4Zq_5k9RcwnTk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getSlowLive$15(AudienceActivity.this, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$rfvQGhRaLlbB-6mgs7RIey6dF4I
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity audienceActivity = AudienceActivity.this;
                    MsLogUtil.m7979d("AudienceActivity", ((Throwable) obj).getMessage());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getSlowLive$15(AudienceActivity audienceActivity, AudienceDataEntity audienceDataEntity) throws Exception {
        if ("0000".equals(audienceDataEntity.getStatusCode())) {
            MsLogUtil.m7979d("AudienceActivity", "慢直播已加载");
            audienceActivity.parseLiveList(audienceDataEntity);
        }
    }

    public void loadAttentionData() {
        try {
            this.managerAudienceLoadData.getAttentionAnchors(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$cVGcZCnmVWxKFtpfV7jrQEi7BDs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$loadAttentionData$17(AudienceActivity.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$Kv2jEwo6DL537vjJzhhZBv3UuAM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$loadAttentionData$18(AudienceActivity.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$loadAttentionData$17(AudienceActivity audienceActivity, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            audienceActivity.pageNum = baseVideoEntity.getNextPageNum();
            audienceActivity.smartSwipeRefresh.finished(true);
            audienceActivity.smartSwipeRefresh.setNoMoreData("-1".equals(audienceActivity.pageNum));
            for (int i = 0; i < ((List) baseVideoEntity.getData()).size(); i++) {
                if (((AttentionItemEntity) ((List) baseVideoEntity.getData()).get(i)).getType() != 3) {
                    audienceActivity.list.add(((AttentionItemEntity) ((List) baseVideoEntity.getData()).get(i)).getLiveData());
                }
            }
            if (audienceActivity.isLoadMore) {
                audienceActivity.mAdapter.loadMore(audienceActivity.list);
                audienceActivity.isLoadMore = false;
                return;
            }
            for (ListDataEntity listDataEntity : audienceActivity.list) {
                UIUtils.logD("ListDataEntity", listDataEntity.getJobNumber());
            }
            if (audienceActivity.list.size() > 1) {
                audienceActivity.startAnime();
            } else {
                audienceActivity.stopAnime();
            }
            audienceActivity.mAdapter.updateList(audienceActivity.list);
            audienceActivity.bgImage.setVisibility(8);
        }
    }

    public static /* synthetic */ void lambda$loadAttentionData$18(AudienceActivity audienceActivity, Throwable th) throws Exception {
        if (!TextUtils.isEmpty(audienceActivity.playBackPageNum) && !"-1".equals(audienceActivity.playBackPageNum) && !audienceActivity.isLoadPBing) {
            audienceActivity.loadPlayBackData(audienceActivity.playBackPageNum);
            return;
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog != null && customePorgressDialog.isShowing()) {
            audienceActivity.f18465pd.dismiss();
        }
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
    }

    public void loadPlayBackData(String str) {
        try {
            this.isLoadPBing = true;
            this.managerAudienceLoadData.getAngleMorePlayBackList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$vxJsxH-b3ABwuIu74gKa2tyGDe4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$loadPlayBackData$19(AudienceActivity.this, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$LBJhiOa3Lp9ZuoPqPdoWegYlr3U
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$loadPlayBackData$20((Throwable) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$loadPlayBackData$19(AudienceActivity audienceActivity, AudienceDataEntity audienceDataEntity) throws Exception {
        audienceActivity.isLoadPBing = false;
        audienceActivity.playBackPageNum = audienceDataEntity.getNextPageNum();
        audienceActivity.smartSwipeRefresh.finished(true);
        audienceActivity.smartSwipeRefresh.setNoMoreData("-1".equals(audienceActivity.playBackPageNum));
        audienceActivity.isLoadMore = false;
        audienceActivity.mAdapter.loadMore(audienceDataEntity.getList());
    }

    public static /* synthetic */ void lambda$loadPlayBackData$20(Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("多视角测试", "多视角回放列表报错：" + th.getMessage());
    }

    private void initSocket() {
        this.managerSocket = new ManagerSocket(this.activityContext);
        this.managerSocket.startConnect();
    }

    private void entrance(Intent intent) {
        try {
            MsLogUtil.m7979d("AudienceActivity", "entrance筛选入口");
            initRecyclerView();
            initListener();
            this.pageNum = "0";
            URLSet.setShareUserNumSc("0");
            releaseVideo();
            this.messageView.clearMessage();
            this.mLayoutManager.setScrollEnabled(true);
            dissmissMoreLive();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            return;
        }
        if (intent != null) {
            String stringExtra = intent.getStringExtra("userId");
            String stringExtra2 = intent.getStringExtra("from");
            String stringExtra3 = intent.getStringExtra("listStr");
            String str = null;
            if (!TextUtils.isEmpty(stringExtra3)) {
                str = stringExtra3.contains(",") ? stringExtra3 : App.getSharePreferenceUtil().getString(JSON_LIST_STR_FILE, stringExtra3);
            }
            App.getSharePreferenceUtil().remove(JSON_LIST_STR_FILE, stringExtra3);
            String stringExtra4 = intent.getStringExtra("index");
            this.liveType = intent.getStringExtra("liveType");
            String stringExtra5 = intent.getStringExtra("isToMultiLive");
            this.liveChannel = intent.getStringExtra("liveChannel");
            this.pageCode = intent.getStringExtra("pageNum");
            this.playBackPageNum = intent.getStringExtra("playBackPageNum");
            this.fromList = intent.getStringExtra(LIST);
            this.isMulti = "Y".equals(stringExtra5);
            if (this.isMulti && this.wrapper != null) {
                this.wrapper.removeAllConsumers();
            }
            String stringExtra6 = intent.getStringExtra("shareUserNumSc");
            if (!TextUtils.isEmpty(stringExtra6)) {
                URLSet.setShareUserNumSc(stringExtra6);
            }
            this.list.clear();
            if (f18464JS.equals(stringExtra2)) {
                this.fromType = f18464JS;
                if (!TextUtils.isEmpty(str)) {
                    for (String str2 : new ArrayList(Arrays.asList(str.split(",")))) {
                        ListDataEntity listDataEntity = new ListDataEntity();
                        listDataEntity.setJobNumber(str2);
                        this.list.add(listDataEntity);
                    }
                    this.mAdapter.updateList(this.list);
                    this.bgImage.setVisibility(8);
                    try {
                        if (!TextUtils.isEmpty(stringExtra4)) {
                            this.mCurrentItem = Integer.parseInt(stringExtra4);
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                    }
                    this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                }
            } else if ("fromMain".equals(stringExtra2)) {
                this.fromType = "fromMain";
                if (!TextUtils.isEmpty(this.pageCode)) {
                    this.pageNum = this.pageCode;
                }
                try {
                    if (!TextUtils.isEmpty(str)) {
                        try {
                            JSONArray jSONArray = new JSONArray(str);
                            for (int i = 0; i < jSONArray.length(); i++) {
                                ListDataEntity listDataEntity2 = new ListDataEntity();
                                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                                listDataEntity2.setVideoId(optJSONObject.optString("videoId"));
                                listDataEntity2.setCoverImg(optJSONObject.optString("coverImg"));
                                listDataEntity2.setDataType(optJSONObject.optString("dataType"));
                                listDataEntity2.setJobNumber(optJSONObject.optString("jobNumber"));
                                listDataEntity2.setLivePvUrl(optJSONObject.optString("livePvUrl"));
                                this.list.add(listDataEntity2);
                            }
                        } catch (JSONException e3) {
                            for (String str3 : new ArrayList(Arrays.asList(str.split(",")))) {
                                ListDataEntity listDataEntity3 = new ListDataEntity();
                                listDataEntity3.setJobNumber(str3);
                                this.list.add(listDataEntity3);
                            }
                            e3.printStackTrace();
                        }
                        this.mAdapter.updateList(this.list);
                        this.bgImage.setVisibility(8);
                        try {
                            if (!TextUtils.isEmpty(stringExtra4)) {
                                this.mCurrentItem = Integer.parseInt(stringExtra4);
                                this.mItemFormMain = this.mCurrentItem;
                            }
                        } catch (Exception e4) {
                            e4.printStackTrace();
                        }
                        this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                    }
                } catch (Exception e5) {
                    e5.printStackTrace();
                    this.fromType = LIST;
                    loadData();
                }
            } else if ("AttentionAnchors".equals(stringExtra2)) {
                MsLogUtil.m7979d("AudienceActivity", "进入模式：" + stringExtra2);
                this.fromType = "AttentionAnchors";
                if (!TextUtils.isEmpty(str)) {
                    this.attentionAnchors = getEntityByJson(str);
                    attentionAnchorVideoInfo(this.attentionAnchors.getPosition());
                } else {
                    getAttentionListNew("1");
                }
            } else if (TextUtils.isEmpty(stringExtra)) {
                if (TASK.equals(stringExtra2)) {
                    this.fromType = TASK;
                    isTask = true;
                } else {
                    this.fromType = LIST;
                }
                loadData();
            } else {
                this.fromType = SINGLE;
                this.singleUserId = stringExtra;
                loadData(stringExtra);
            }
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            return;
        }
        loadData();
        this.managerAudienceLoadData.getGiftListNew().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$9M8aDxEbao8K2BNCC3XOz9Ycz40
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$entrance$21((List) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    private void getAttentionListNew(String str) {
        try {
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = new ManagerAudienceLoadData(this);
            }
            this.managerAudienceLoadData.getTheAnchorAvatarInfo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$VrundAGAMgMEpm4m4PGcpoDL8jM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getAttentionListNew$22(AudienceActivity.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$pu_YycpBI2jxSPncdp3WkNDpS9E
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    AudienceActivity.this.loadData();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getAttentionListNew$22(AudienceActivity audienceActivity, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            AttentionAnchorVideoEntity attentionAnchorVideoEntity = audienceActivity.attentionAnchors;
            if (attentionAnchorVideoEntity == null) {
                audienceActivity.attentionAnchors = (AttentionAnchorVideoEntity) baseVideoEntity.getData();
                audienceActivity.attentionAnchorVideoInfo(audienceActivity.attentionAnchors.getPosition());
                return;
            }
            attentionAnchorVideoEntity.getAnchors().addAll(((AttentionAnchorVideoEntity) baseVideoEntity.getData()).getAnchors());
            audienceActivity.attentionAnchors.setNextPageNum(baseVideoEntity.getNextPageNum());
            return;
        }
        audienceActivity.loadData();
    }

    public void getNextVideoInfoByAttention(final int i) {
        try {
            AttentionVideoEntity attentionVideoEntity = this.attentionAnchors.getAnchors().get(i);
            HashMap hashMap = new HashMap();
            hashMap.put("jobNumber", attentionVideoEntity.getJobNumber());
            hashMap.put("dataType", attentionVideoEntity.getDataType());
            hashMap.put("ids", json2String(attentionVideoEntity.getIds()));
            this.managerAudienceLoadData.getDetailsAuthorWork(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$PsLt2le612en4_iPQRhGQ6obnfM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getNextVideoInfoByAttention$24(AudienceActivity.this, i, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$o6W0fkA9so6ZTJKN8UtLiKOc2zk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    ((Throwable) obj).printStackTrace();
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getNextVideoInfoByAttention$24(AudienceActivity audienceActivity, int i, AudienceDataEntity audienceDataEntity) throws Exception {
        int i2 = i + 1;
        if (i2 == audienceActivity.attentionAnchors.getAnchors().size()) {
            audienceDataEntity.setNextPageNum("-1");
        } else {
            audienceDataEntity.setNextPageNum("" + i2);
        }
        audienceActivity.parseLiveList(audienceDataEntity);
    }

    private void attentionAnchorVideoInfo(int i) {
        try {
            MsLogUtil.m7979d("AudienceActivity", "循环加载主播列表中的主播新内容");
            this.loadingDialog = CircularLoading.showLoadDialog(this.activityContext, "", false);
            this.temporatyList.clear();
            for (int i2 = 0; i2 <= i; i2++) {
                getItemData(i2, i);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void getItemData(final int i, final int i2) {
        String str;
        try {
            MsLogUtil.m7979d("AudienceActivity", "加载" + i + "号位主播，点击位置：" + i2);
            AttentionVideoEntity attentionVideoEntity = this.attentionAnchors.getAnchors().get(i);
            final int i3 = i2 + 1;
            if (this.attentionAnchors.getAnchors().size() == i3) {
                str = "-1";
            } else {
                str = i3 + "";
            }
            final String str2 = str;
            HashMap hashMap = new HashMap();
            hashMap.put("jobNumber", attentionVideoEntity.getJobNumber());
            hashMap.put("dataType", attentionVideoEntity.getDataType());
            hashMap.put("ids", json2String(attentionVideoEntity.getIds()));
            this.managerAudienceLoadData.getDetailsAuthorWork(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$jJ2teAAsKRtHxDFRiA1DpwDGhWU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getItemData$26(AudienceActivity.this, i, i2, i3, str2, (AudienceDataEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$mwqkbCkWXJMHlO40A9g4jzIVRJQ
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getItemData$27(AudienceActivity.this, i, i2, i3, str2, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getItemData$26(AudienceActivity audienceActivity, int i, int i2, int i3, String str, AudienceDataEntity audienceDataEntity) throws Exception {
        MsLogUtil.m7979d("AudienceActivity", "返回" + i + "号位主播数据，点击位置：" + i2);
        audienceActivity.disposalData(i, audienceDataEntity, i3, str);
    }

    public static /* synthetic */ void lambda$getItemData$27(AudienceActivity audienceActivity, int i, int i2, int i3, String str, Throwable th) throws Exception {
        th.printStackTrace();
        MsLogUtil.m7979d("AudienceActivity", "返回" + i + "号位主播数据失败，点击位置：" + i2 + "；错误信息:" + th.getMessage());
        audienceActivity.disposalData(i, null, i3, str);
    }

    private void disposalData(int i, AudienceDataEntity audienceDataEntity, int i2, String str) {
        try {
            this.temporatyList.put(Integer.valueOf(i), audienceDataEntity);
            if (this.temporatyList.size() == i2) {
                AudienceDataEntity audienceDataEntity2 = new AudienceDataEntity();
                audienceDataEntity2.setNextPageNum(str);
                audienceDataEntity2.setStatusCode("0000");
                ArrayList arrayList = new ArrayList();
                for (int i3 = 0; i3 < i2; i3++) {
                    AudienceDataEntity audienceDataEntity3 = this.temporatyList.get(Integer.valueOf(i3));
                    if (audienceDataEntity3 != null && "0000".equals(audienceDataEntity3.getStatusCode()) && audienceDataEntity3.getList() != null) {
                        arrayList.addAll(audienceDataEntity3.getList());
                    }
                }
                audienceDataEntity2.setList(arrayList);
                AudienceDataEntity audienceDataEntity4 = this.temporatyList.get(Integer.valueOf(i2 - 1));
                if (audienceDataEntity4 != null && audienceDataEntity4.getList() != null && audienceDataEntity4.getList().size() > 0) {
                    this.mCurrentItem = arrayList.indexOf(audienceDataEntity4.getList().get(0));
                }
                MsLogUtil.m7979d("AudienceActivity", "选中：" + this.mCurrentItem);
                parseLiveList(audienceDataEntity2);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private String json2String(String[] strArr) {
        try {
            JSONArray jSONArray = new JSONArray();
            for (String str : strArr) {
                jSONArray.put(str);
            }
            return !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            return "";
        }
    }

    private AttentionAnchorVideoEntity getEntityByJson(String str) {
        try {
            AttentionAnchorVideoEntity attentionAnchorVideoEntity = new AttentionAnchorVideoEntity();
            JSONObject jSONObject = new JSONObject(str);
            attentionAnchorVideoEntity.setPosition(jSONObject.optInt("position"));
            attentionAnchorVideoEntity.setNextPageNum(jSONObject.optString("nextPageNum"));
            JSONArray optJSONArray = jSONObject.optJSONArray("data");
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                AttentionVideoEntity attentionVideoEntity = new AttentionVideoEntity();
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                attentionVideoEntity.setDataType(optJSONObject.optString("dataType"));
                attentionVideoEntity.setJobNumber(optJSONObject.optString("jobNumber"));
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("ids");
                String[] strArr = new String[optJSONArray2.length()];
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    strArr[i2] = optJSONArray2.optString(i2);
                }
                attentionVideoEntity.setIds(strArr);
                arrayList.add(attentionVideoEntity);
            }
            attentionAnchorVideoEntity.setAnchors(arrayList);
            MsLogUtil.m7979d("AudienceActivity", "关注主播头像列表解析成功");
            return attentionAnchorVideoEntity;
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("AudienceActivity", "关注主播头像列表解析失败，失败原因:" + e.getMessage());
            return null;
        }
    }

    public void loadData() {
        try {
            if (SlowLiveFragment.SLOW_LIVE.equals(this.liveType)) {
                getSlowLive();
            } else if (TheSameCityLiveFragment.THE_SAME_CITY.equals(this.liveType)) {
                this.managerAudienceLoadData.loadSameCityLiveList(UserManager.getInstance().getLocateCityCode(), this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$3EnwPgUarNbt1-gMkI5EFLxK1dQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.this.parseLiveList((AudienceDataEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$kodc_m7QlZ3wDe33MHbXYW5tfAc
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.lambda$loadData$29(AudienceActivity.this, (Throwable) obj);
                    }
                });
            } else {
                this.managerAudienceLoadData.loadListData(this.pageNum).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$iRfdnu2SyEWAqg_XwulV47Jb2_w
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.this.parseLiveList((AudienceDataEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$0459qrjn5hgnnTkfjB5GeWJHOfk
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.lambda$loadData$31(AudienceActivity.this, (Throwable) obj);
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$loadData$29(AudienceActivity audienceActivity, Throwable th) throws Exception {
        if (!TextUtils.isEmpty(audienceActivity.playBackPageNum) && !"-1".equals(audienceActivity.playBackPageNum) && !audienceActivity.isLoadPBing) {
            audienceActivity.loadPlayBackData(audienceActivity.playBackPageNum);
            return;
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog != null && customePorgressDialog.isShowing()) {
            audienceActivity.f18465pd.dismiss();
        }
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
    }

    public static /* synthetic */ void lambda$loadData$31(AudienceActivity audienceActivity, Throwable th) throws Exception {
        if (!TextUtils.isEmpty(audienceActivity.playBackPageNum) && !"-1".equals(audienceActivity.playBackPageNum) && !audienceActivity.isLoadPBing) {
            audienceActivity.loadPlayBackData(audienceActivity.playBackPageNum);
            return;
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog != null && customePorgressDialog.isShowing()) {
            audienceActivity.f18465pd.dismiss();
        }
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
    }

    public void parseLiveList(AudienceDataEntity audienceDataEntity) {
        try {
            if (this.loadingDialog != null) {
                CircularLoading.closeDialog(this.activityContext, this.loadingDialog);
            }
            if ("0000".equals(audienceDataEntity.getStatusCode())) {
                MsLogUtil.m7979d("AudienceActivity", "开始解析数据，数据量：" + audienceDataEntity.getList().size());
                this.pageNum = audienceDataEntity.getNextPageNum();
                this.smartSwipeRefresh.finished(true);
                this.smartSwipeRefresh.setNoMoreData("-1".equals(this.pageNum));
                if (this.isLoadMore) {
                    this.mAdapter.loadMore(audienceDataEntity.getList());
                    this.isLoadMore = false;
                    return;
                }
                this.list = audienceDataEntity.getList();
                for (ListDataEntity listDataEntity : this.list) {
                    UIUtils.logD("AudienceActivity", listDataEntity.getJobNumber());
                }
                if (this.list.size() > 1) {
                    startAnime();
                } else {
                    stopAnime();
                }
                this.mAdapter.updateList(audienceDataEntity.getList());
                this.bgImage.setVisibility(8);
                this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                if (!"-1".equals(audienceDataEntity.getNextPageNum()) || TextUtils.isEmpty(this.playBackPageNum) || "-1".equals(this.playBackPageNum) || this.isLoadPBing) {
                    return;
                }
                loadPlayBackData(this.playBackPageNum);
            } else if ("-1".equals(audienceDataEntity.getNextPageNum()) && !TextUtils.isEmpty(this.playBackPageNum) && !"-1".equals(this.playBackPageNum) && !this.isLoadPBing) {
                loadPlayBackData(this.playBackPageNum);
            } else {
                UIUtils.toast("加载失败，请稍后再试");
                if (this.f18465pd == null || !this.f18465pd.isShowing()) {
                    return;
                }
                this.f18465pd.dismiss();
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void loadData(String str) {
        try {
            if (this.isMulti) {
                getMultiRoomList();
            } else if (SlowLiveFragment.SLOW_LIVE.equals(this.liveType)) {
                this.managerAudienceLoadData.getSlowLiveList(this.pageNum).subscribe(new $$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE(this), new $$Lambda$AudienceActivity$VGG5gDH2FeOK7eMe8d9xiOnOqaM(this));
            } else if (TheSameCityLiveFragment.THE_SAME_CITY.equals(this.liveType)) {
                this.managerAudienceLoadData.loadSameCityLiveList(UserManager.getInstance().getLocateCityCode(), this.pageNum).subscribe(new $$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE(this), new $$Lambda$AudienceActivity$VGG5gDH2FeOK7eMe8d9xiOnOqaM(this));
            } else {
                this.managerAudienceLoadData.loadListData(this.pageNum).subscribe(new $$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE(this), new $$Lambda$AudienceActivity$VGG5gDH2FeOK7eMe8d9xiOnOqaM(this));
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void getMultiRoomList() {
        this.managerAudienceLoadData.getAngleMoreLiveList(this.pageNum).subscribe(new $$Lambda$AudienceActivity$O_szAEnYqOmMcHA_G2rZcyeZzOE(this), new $$Lambda$AudienceActivity$VGG5gDH2FeOK7eMe8d9xiOnOqaM(this));
    }

    public void handleSingleList(AudienceDataEntity audienceDataEntity) {
        try {
            if ("0000".equals(audienceDataEntity.getStatusCode())) {
                boolean equals = "0".equals(this.pageNum);
                this.pageNum = audienceDataEntity.getNextPageNum();
                ListDataEntity listDataEntity = null;
                for (ListDataEntity listDataEntity2 : audienceDataEntity.getList()) {
                    UIUtils.logD("ListDataEntity", listDataEntity2.getJobNumber());
                    if (listDataEntity2.getJobNumber().trim().equals(this.singleUserId.trim()) && "1".equals(listDataEntity2.getDataType())) {
                        listDataEntity = listDataEntity2;
                    }
                }
                if (this.isLoadMore) {
                    if (listDataEntity != null) {
                        audienceDataEntity.getList().remove(listDataEntity);
                    }
                    this.mAdapter.loadMore(audienceDataEntity.getList());
                    this.isLoadMore = false;
                    return;
                }
                this.list = audienceDataEntity.getList();
                if (this.list.size() > 1) {
                    startAnime();
                } else {
                    stopAnime();
                }
                if (listDataEntity == null) {
                    listDataEntity = new ListDataEntity();
                    listDataEntity.setJobNumber(this.singleUserId);
                } else if (this.list.size() > 0) {
                    this.list.remove(listDataEntity);
                }
                if (equals) {
                    this.list.add(0, listDataEntity);
                }
                this.mAdapter.updateList(audienceDataEntity.getList());
                this.mRecyclerView.scrollToPosition(0);
                this.bgImage.setVisibility(8);
                if ("-1".equals(audienceDataEntity.getNextPageNum()) && !TextUtils.isEmpty(this.playBackPageNum) && !"-1".equals(this.playBackPageNum) && !this.isLoadPBing) {
                    loadPlayBackData(this.playBackPageNum);
                    return;
                }
                this.smartSwipeRefresh.finished(true);
                this.smartSwipeRefresh.setNoMoreData("-1".equals(this.pageNum));
            } else if ("-1".equals(audienceDataEntity.getNextPageNum()) && !TextUtils.isEmpty(this.playBackPageNum) && !"-1".equals(this.playBackPageNum) && !this.isLoadPBing) {
                loadPlayBackData(this.playBackPageNum);
            } else {
                UIUtils.toast("加载失败，请稍后再试");
                if (this.f18465pd == null || !this.f18465pd.isShowing()) {
                    return;
                }
                this.f18465pd.dismiss();
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void handleListThrowable(Throwable th) {
        try {
            if (!TextUtils.isEmpty(this.playBackPageNum) && !"-1".equals(this.playBackPageNum) && !this.isLoadPBing) {
                loadPlayBackData(this.playBackPageNum);
                return;
            }
            if (this.f18465pd != null && this.f18465pd.isShowing()) {
                this.f18465pd.dismiss();
            }
            UIUtils.toast("加载失败，请稍后再试");
            th.printStackTrace();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void clearMultiView() {
        View findViewByPosition;
        try {
            bdCloudVideoView.setVolume(1.0f, 1.0f);
            resetPlayerVoice();
            if (this.mLayoutManager == null || this.mCurrentItem < 0 || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null) {
                return;
            }
            View findViewById = findViewByPosition.findViewById(2131297749);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            View findViewById2 = findViewByPosition.findViewById(2131299019);
            if (findViewById2 != null) {
                findViewById2.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void getPlayBackVideoInfo(int i) {
        try {
            final ListDataEntity listDataEntity = this.list.get(i);
            final View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
            UIUtils.logD("多视角测试", "多视角回放主视频播放");
            if (findViewByPosition != null) {
                addVideoView(findViewByPosition);
                this.isPlaying = false;
                this.streamStartTime = System.currentTimeMillis();
                bdCloudVideoView.setVideoPath(listDataEntity.getLivePvUrl());
                bdCloudVideoView.setTag(listDataEntity.getLivePvUrl());
                if (!this.isNeedPay) {
                    bdCloudVideoView.start();
                }
            }
            this.managerAudienceLoadData.getAngleMoreVideoInfo(listDataEntity.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$LhLwi01P4gCpxJwwkR36qbj4Hl0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getPlayBackVideoInfo$32(AudienceActivity.this, listDataEntity, findViewByPosition, (SharpnessEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$DQqIX2vUHEIx9EBEvpbHio8dlfU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getPlayBackVideoInfo$33((Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            filerList(i);
        }
    }

    public static /* synthetic */ void lambda$getPlayBackVideoInfo$32(AudienceActivity audienceActivity, ListDataEntity listDataEntity, View view, SharpnessEntity sharpnessEntity) throws Exception {
        UIUtils.logD("多视角测试", "多视角回放请求成功");
        audienceActivity.handleSharpnessInfo(sharpnessEntity, listDataEntity.getJobNumber(), view, listDataEntity.getCoverImg());
    }

    public static /* synthetic */ void lambda$getPlayBackVideoInfo$33(Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("多视角测试", "多视角回放请求错误：" + th.getMessage());
    }

    public void startPlay(final int i) {
        ViewGroup viewGroup;
        try {
            clearMultiView();
            resetLiveCover();
            if (i > 0) {
                stopAnime();
            }
            if (this.messageView != null && (viewGroup = (ViewGroup) this.messageView.getParent()) != null) {
                viewGroup.removeAllViews();
            }
            if (this.list.size() == 0) {
                return;
            }
            ListDataEntity listDataEntity = this.list.get(i);
            if ("3".equals(listDataEntity.getDataType())) {
                getRoomInfo(listDataEntity.getJobNumber(), listDataEntity.getVideoId(), i);
            } else if ("4".equals(listDataEntity.getDataType())) {
                getSmallVideoInfo(listDataEntity.getVideoId(), i);
            } else {
                getUseFulChat(this.list.get(i).getJobNumber());
                getInteractionAnchorsInfo(this.list.get(i).getJobNumber());
                this.managerAudienceLoadData.loadZhuboData(this.list.get(i).getJobNumber(), this.liveChannel).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$ikGhXxIzFnanATnAR4LOAn_hpmw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.lambda$startPlay$43(AudienceActivity.this, i, (ZhuboDataEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$frhr2OyfYgmI6dtTxMX89-gWCJ8
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.lambda$startPlay$44(AudienceActivity.this, (Throwable) obj);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            UIUtils.toast("加载直播间信息失败");
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C82974 implements AudienceMessageView.IPage1Click {
        final /* synthetic */ ZhuboDataEntity val$entity;

        C82974(ZhuboDataEntity zhuboDataEntity) {
            AudienceActivity.this = r1;
            this.val$entity = zhuboDataEntity;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public boolean isNeedPay() {
            return AudienceActivity.this.isNeedPay();
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void showZanAnim() {
            if (AudienceActivity.this.dianZanAnim != null) {
                AudienceActivity.this.dianZanAnim.startLocalZanSvgaAnim();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void clickGoods(ShopEntity.DataBean.GoodsBean goodsBean) {
            if (goodsBean != null) {
                String livePullUrl = this.val$entity.getAnchorInfoBean().getLivePullUrl();
                String userName = this.val$entity.getAnchorInfoBean().getUserName();
                PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, "商品卡片" + goodsBean.getName() + goodsBean.getGoodsUrl(), "直播详情页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void clickShoppingCart() {
            PvCurrencyLogUtils.pvLogLive(this.val$entity.getAnchorInfoBean().getLivePullUrl(), 2, this.val$entity.getAnchorInfoBean().getUserName(), "购物车", "直播详情页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void userInfo() {
            try {
                String userIndexUrl = this.val$entity.getAnchorInfoBean().getUserIndexUrl();
                if (!TextUtils.isEmpty(userIndexUrl)) {
                    AudienceActivity.IntentGo(AudienceActivity.this.activityContext, AudienceActivity.this.addChannel(userIndexUrl, "直播"));
                }
                PvCurrencyLogUtils.pvLogLive(this.val$entity.getAnchorInfoBean().getLivePullUrl(), 2, this.val$entity.getAnchorInfoBean().getUserName(), "头像", "直播详情页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播回放页");
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void toggle() {
            AudienceActivity audienceActivity = AudienceActivity.this;
            audienceActivity.toggleFullScreen(audienceActivity.activityContext);
            PvCurrencyLogUtils.pvLogLive(this.val$entity.getAnchorInfoBean().getLivePullUrl(), 2, this.val$entity.getAnchorInfoBean().getUserName(), "横竖屏键位", "直播详情页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void zanNum(String str) {
            if (AudienceActivity.this.landscapeView != null) {
                AudienceActivity.this.landscapeView.setZanNum(str);
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
            AudienceActivity.bdCloudVideoView.stopPlayback();
            AudienceActivity.this.playUrl(liveUrlBean);
            PvCurrencyLogUtils.pvLogLive(this.val$entity.getAnchorInfoBean().getLivePullUrl(), 2, this.val$entity.getAnchorInfoBean().getUserName(), liveUrlBean.getSharpnessName(), "直播详情页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "5");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void showKeyboard() {
            InputDialog inputDialog = AudienceActivity.this.alivcInputTextDialog;
            final ZhuboDataEntity zhuboDataEntity = this.val$entity;
            inputDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$4$8ncuUAD6wqRNbYu65O0u_W4iEnk
                @Override // com.sinovatech.unicom.separatemodule.audience.util.InputDialog.OnTextSendListener
                public final void onTextSend(String str) {
                    AudienceActivity.C82974.lambda$showKeyboard$1(AudienceActivity.C82974.this, zhuboDataEntity, str);
                }
            });
            AudienceActivity.this.alivcInputTextDialog.show();
            PvCurrencyLogUtils.pvLogLive(this.val$entity.getAnchorInfoBean().getLivePullUrl(), 2, this.val$entity.getAnchorInfoBean().getUserName(), "聊天框", "直播详情页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
        }

        public static /* synthetic */ void lambda$showKeyboard$1(C82974 c82974, ZhuboDataEntity zhuboDataEntity, String str) {
            if (AudienceActivity.this.audienceUser.isLogin()) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setMsg(str.replace("|*|", ""));
                if (str.contains("@") && str.contains("|*|")) {
                    liveMsg.setType("atto");
                    liveMsg.setAtto("all");
                } else {
                    liveMsg.setType("chat");
                }
                liveMsg.setLevel(AudienceActivity.this.audienceUser.getLevel());
                liveMsg.setNick(AudienceActivity.this.audienceUser.getNick());
                liveMsg.setMgr(AudienceActivity.this.audienceUser.isMgr());
                AudienceActivity.this.messageView.updateMessageAdapter(liveMsg);
                if (!str.contains("@") || !str.contains("|*|")) {
                    AudienceActivity.this.managerSocket.eventFayan(str);
                } else {
                    AudienceActivity.this.managerSocket.eventAtUser(liveMsg.getAtto(), liveMsg.getMsg());
                }
            }
            if (AudienceActivity.TASK.equals(AudienceActivity.this.fromType)) {
                AudienceActivity.this.managerAudienceLoadData.sendMsg(AudienceActivity.userId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$4$iO5daPxMxlhWlZ8Q25y3G46qnFs
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.C82974 c829742 = AudienceActivity.C82974.this;
                        MsLogUtil.m7979d("AudienceActivity", ((BaseVideoEntity) obj).getMessage());
                    }
                });
            }
            String livePullUrl = zhuboDataEntity.getAnchorInfoBean().getLivePullUrl();
            String userName = zhuboDataEntity.getAnchorInfoBean().getUserName();
            PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, "聊天框-发送(" + str + ")", "直播详情页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
        }
    }

    public static /* synthetic */ void lambda$startPlay$36(AudienceActivity audienceActivity, ZhuangXiuEntity zhuangXiuEntity) throws Exception {
        audienceActivity.messageView.setMultiView(audienceActivity.isMulti);
        audienceActivity.messageView.setRenovation(zhuangXiuEntity);
    }

    public static /* synthetic */ void lambda$startPlay$37(AudienceActivity audienceActivity, ShopEntity shopEntity) throws Exception {
        audienceActivity.messageView.setGoods(shopEntity);
        audienceActivity.landscapeView.setGoods(shopEntity);
    }

    public static /* synthetic */ void lambda$startPlay$38(AudienceActivity audienceActivity, List list) throws Exception {
        audienceActivity.messageView.setGoodsList(list);
        audienceActivity.landscapeView.setGoodsList(list);
    }

    public static /* synthetic */ void lambda$startPlay$39(AudienceActivity audienceActivity, ZhuboDataEntity zhuboDataEntity, Boolean bool) throws Exception {
        audienceActivity.messageView.setGuanzhuView(bool.booleanValue(), zhuboDataEntity);
        audienceActivity.landscapeView.setGuanzuGone(bool.booleanValue());
    }

    public static /* synthetic */ void lambda$startPlay$44(AudienceActivity audienceActivity, Throwable th) throws Exception {
        UIUtils.logD("lln", "主播接口信息异常-->" + th.getMessage());
        if (URLEnvironmentConfig.isDevelopmentEnvironment()) {
            UIUtils.toast(th.getMessage());
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog != null && customePorgressDialog.isShowing()) {
            audienceActivity.f18465pd.dismiss();
        }
        audienceActivity.xiaBoStatus(false);
    }

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

    private void getSmallVideoInfo(String str, final int i) {
        this.managerAudienceLoadData.getVideoInfo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$7HIyx8VYOC9qfJVY3Bjz6tRelpU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getSmallVideoInfo$45(AudienceActivity.this, i, (SmallVideoInfoEntity) obj);
            }
        });
        getMoreConfigInfo();
    }

    public static /* synthetic */ void lambda$getSmallVideoInfo$45(AudienceActivity audienceActivity, int i, SmallVideoInfoEntity smallVideoInfoEntity) throws Exception {
        if ("0000".equals(smallVideoInfoEntity.getStatusCode())) {
            audienceActivity.createSmallVideoUI(smallVideoInfoEntity, i);
        }
    }

    private void getMoreConfigInfo() {
        this.managerAudienceLoadData.getVideoRingRule("smallVideo").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$bW9-kekZePl_TFnh9FJS9Ln_4zY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getMoreConfigInfo$46(AudienceActivity.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$vgPOHjMBlPj8yd6PP_GX7c3KXyw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public static /* synthetic */ void lambda$getMoreConfigInfo$46(AudienceActivity audienceActivity, BaseVideoEntity baseVideoEntity) throws Exception {
        if (!"0000".equals(baseVideoEntity.getStatusCode()) || baseVideoEntity.getData() == null || ((List) baseVideoEntity.getData()).size() <= 0) {
            return;
        }
        audienceActivity.mMoreConfigs = (List) baseVideoEntity.getData();
    }

    private void createSmallVideoUI(final SmallVideoInfoEntity smallVideoInfoEntity, final int i) {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition == null) {
                createSmallVideoUI(smallVideoInfoEntity, i);
                return;
            }
            this.smallVideoView = new SmallVideoView(this.activityContext, bdCloudVideoView);
            final C82985 c82985 = new C82985(smallVideoInfoEntity, findViewByPosition);
            this.smallVideoView.setDoubleClickListener(new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.6
                {
                    AudienceActivity.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void onClick(long j) {
                    if (AudienceActivity.this.smallVideoView.getPlayStatus()) {
                        AudienceActivity.this.smallVideoView.setPlayStatus(false, false);
                        AudienceActivity.bdCloudVideoView.pause();
                        return;
                    }
                    AudienceActivity.this.smallVideoView.setPlayStatus(true, false);
                    if (AudienceActivity.this.isNeedPay) {
                        return;
                    }
                    AudienceActivity.bdCloudVideoView.start();
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void doubleTapCallback() {
                    c82985.dianzan(i, smallVideoInfoEntity.getData());
                }
            });
            View initData = this.smallVideoView.initData(smallVideoInfoEntity.getData(), i, c82985);
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296434);
            frameLayout.removeAllViews();
            frameLayout.addView(initData);
            String videoId = smallVideoInfoEntity.getData().getVideoId();
            setVideoNum(videoId, initData);
            setZanStatus(videoId, initData);
            getGuanZhuStatus(smallVideoInfoEntity.getData().getUserId(), initData);
            getSmallVideoCommentCount(videoId, initData);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$5 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C82985 implements SmallVideoAdapter.ItemClickedListener {
        final /* synthetic */ SmallVideoInfoEntity val$en;
        final /* synthetic */ View val$rootView;

        public static /* synthetic */ void lambda$shagnpinInfo$1(Boolean bool) throws Exception {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void clicked(int i, SmallVideoEntity.Data data) {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void setRing(int i, SmallVideoEntity.Data data, String str) {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void shangpin(int i, SmallVideoEntity.Data data) {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void showHelp() {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void toWelfareCenter() {
        }

        C82985(SmallVideoInfoEntity smallVideoInfoEntity, View view) {
            AudienceActivity.this = r1;
            this.val$en = smallVideoInfoEntity;
            this.val$rootView = view;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void guanzhu(final int i, SmallVideoEntity.Data data) {
            try {
                AudienceActivity.this.managerAudienceLoadData.focusOnAnchor(data.getUserId(), data.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$5$XwVKG41q81LY3tsgGJegqopnHUc
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.C82985.lambda$guanzhu$0(AudienceActivity.C82985.this, i, (String) obj);
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$guanzhu$0(C82985 c82985, int i, String str) throws Exception {
            try {
                String optString = new JSONObject(str).optString("statusCode");
                if ("0000".equals(optString)) {
                    UIUtils.toast("已成功关注主播");
                } else {
                    UIUtils.toast("关注主播失败");
                }
                View findViewByPosition = AudienceActivity.this.mLayoutManager.findViewByPosition(i);
                if (findViewByPosition == null) {
                    return;
                }
                findViewByPosition.findViewById(2131297390).setVisibility("0000".equals(optString) ? 8 : 0);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void dianzan(int i, SmallVideoEntity.Data data) {
            TextView textView;
            ImageView imageView;
            TextView textView2;
            try {
                View findViewByPosition = AudienceActivity.this.mLayoutManager.findViewByPosition(i);
                ImageView imageView2 = null;
                if (findViewByPosition != null) {
                    imageView2 = (ImageView) findViewByPosition.findViewById(2131297529);
                    imageView = (ImageView) findViewByPosition.findViewById(2131297530);
                    textView2 = (TextView) findViewByPosition.findViewById(2131299157);
                    textView = (TextView) findViewByPosition.findViewById(2131299158);
                } else {
                    textView = null;
                    imageView = null;
                    textView2 = null;
                }
                if (data.isHasZan()) {
                    AudienceActivity.this.zanVideo(data.getVideoId(), "N", data.getContentType());
                    if (imageView2 != null) {
                        imageView2.setImageResource(2131231683);
                    }
                    if (imageView != null) {
                        imageView.setImageResource(2131231612);
                    }
                    data.setHasZan(false);
                    data.setVideoPraiseNum((Integer.parseInt(data.getVideoPraiseNum()) - 1) + "");
                    if (textView2 != null) {
                        textView2.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                    }
                    if (textView != null) {
                        textView.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                        return;
                    }
                    return;
                }
                AudienceActivity.this.zanVideo(data.getVideoId(), "Y", data.getContentType());
                data.setHasZan(true);
                String videoPraiseNum = data.getVideoPraiseNum();
                if (TextUtils.isEmpty(videoPraiseNum)) {
                    videoPraiseNum = "0";
                }
                data.setVideoPraiseNum((Integer.parseInt(videoPraiseNum) + 1) + "");
                if (textView2 != null) {
                    textView2.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                }
                if (textView != null) {
                    textView.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                }
                if (imageView2 != null) {
                    imageView2.setImageResource(2131231684);
                }
                if (imageView != null) {
                    imageView.setImageResource(2131231615);
                }
                AudienceActivity.this.showZanAnimation(imageView2);
                AudienceActivity.this.showZanAnimation(imageView);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void pinglun(int i, SmallVideoEntity.Data data) {
            AudienceActivity.this.pingLun(data);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void zhuanfa(int i, SmallVideoEntity.Data data, boolean z) {
            AudienceActivity.this.share(data);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void shagnpinInfo(int i, SmallVideoEntity.Data data) {
            try {
                AudienceActivity.this.managerAudienceLoadData.goodsLog(data.getUserId(), data.getGoodsId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$5$Tw3d-rGjV21xEU3qwyar17pAgc0
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceActivity.C82985.lambda$shagnpinInfo$1((Boolean) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                Intent intent = new Intent(AudienceActivity.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(data.getGoodsLink());
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                AudienceActivity.this.startActivity(intent);
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
                Intent intent = new Intent(AudienceActivity.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(AudienceActivity.this.addChannel(userIndexUrl, videoType == 1 ? "视频彩铃" : "小视频"));
                webParamsEntity.setType(AudienceActivity.this.fromType);
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                AudienceActivity.this.startActivityForResult(intent, 1221);
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void backPortraitScreen() {
            AudienceActivity audienceActivity = AudienceActivity.this;
            audienceActivity.toggleFullScreen4SmallVideo(audienceActivity.activityContext);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void onPreview(boolean z, SmallVideoEntity.Data data) {
            if (!AudienceActivity.bdCloudVideoView.isPlaying() && !AudienceActivity.this.isNeedPay) {
                AudienceActivity.bdCloudVideoView.start();
            }
            AudienceActivity.this.mLayoutManager.setScrollEnabled(!z);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void back() {
            AudienceActivity.this.onBackPressed();
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void showMore(SmallVideoEntity.Data data) {
            if (AudienceActivity.this.mMoreConfigs != null) {
                VideoRingMoreDialog.show(AudienceActivity.this.activityContext, AudienceActivity.this.mMoreConfigs, AudienceActivity.this.fromType, new VideoRingMoreDialog.CallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.5.1
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void click(String str) {
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void pingLun() {
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void share() {
                    }

                    {
                        C82985.this = this;
                    }
                });
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void playVideo(int i) {
            SharpnessEntity.LiveUrlBean liveUrlBean = new SharpnessEntity.LiveUrlBean();
            liveUrlBean.setSharpnessName("标清");
            liveUrlBean.setSharpnessLevel("L");
            liveUrlBean.setJobNumber(this.val$en.getData().getUserId());
            liveUrlBean.setLivePullUrl(this.val$en.getData().getVideoUrl());
            liveUrlBean.setLivePullUrlByFlv(this.val$en.getData().getVideoUrl());
            liveUrlBean.setLiveFreePullUrl(this.val$en.getData().getVideoUrl());
            liveUrlBean.setLiveFreePullUrlByFlv(this.val$en.getData().getVideoUrl());
            ArrayList arrayList = new ArrayList();
            arrayList.add(liveUrlBean);
            AudienceActivity.this.liveUrlList = arrayList;
            AudienceActivity.this.setPlayDate(this.val$rootView);
        }
    }

    private void getSmallVideoCommentCount(String str, final View view) {
        this.managerAudienceLoadData.getCommentList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$8RjWf41jti_-5a4a9sWflodVhpw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getSmallVideoCommentCount$48(AudienceActivity.this, view, (LiveCommentEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$zq4ctRX-bHmZvOKUHct5YnSsGJ8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getSmallVideoCommentCount$49((Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getSmallVideoCommentCount$48(AudienceActivity audienceActivity, View view, LiveCommentEntity liveCommentEntity) throws Exception {
        if ("0000".equals(liveCommentEntity.getCode())) {
            SmallVideoEntity.Data data = audienceActivity.smallVideoView.getData();
            data.setCommentNum(liveCommentEntity.getCommentList().size() + "");
            if (view != null) {
                ((TextView) view.findViewById(2131298917)).setText(liveCommentEntity.getCommentList().size() + "");
            }
        }
    }

    public static /* synthetic */ void lambda$getSmallVideoCommentCount$49(Throwable th) throws Exception {
        UIUtils.logD("test", "获取评论数量错误");
        th.printStackTrace();
    }

    private void getGuanZhuStatus(String str, final View view) {
        try {
            this.managerAudienceLoadData.isGuanzhu(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$0SiBadaxmL1Eu3-7uST2FxHrLJY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getGuanZhuStatus$50(AudienceActivity.this, view, (Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getGuanZhuStatus$50(AudienceActivity audienceActivity, View view, Boolean bool) throws Exception {
        audienceActivity.smallVideoView.getData().setFocusOn(bool.booleanValue());
        view.findViewById(2131297390).setVisibility(bool.booleanValue() ? 8 : 0);
    }

    private void setVideoNum(String str, final View view) {
        this.managerAudienceLoadData.setVideoNum(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$FJFJoDteQUDMz4hPeeXFYJYt0qQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$setVideoNum$51(AudienceActivity.this, view, (String) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    public static /* synthetic */ void lambda$setVideoNum$51(AudienceActivity audienceActivity, View view, String str) throws Exception {
        SmallVideoEntity.Data data = audienceActivity.smallVideoView.getData();
        String viewNum = data.getViewNum();
        if (TextUtils.isEmpty(viewNum)) {
            viewNum = "0";
        }
        int i = 0;
        try {
            i = Integer.parseInt(viewNum);
        } catch (Exception e) {
            e.printStackTrace();
        }
        int i2 = i + 1;
        data.setViewNum(i2 + "");
        ((TextView) view.findViewById(2131299147)).setText(FormatUtils.getShowString(i2) + "人看过");
    }

    private void setZanStatus(String str, final View view) {
        this.managerAudienceLoadData.getVideoZan(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$X0Qw6ulNa-LsKGY3gMuzFXVKjyI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$setZanStatus$52(AudienceActivity.this, view, (Map) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setZanStatus$52(AudienceActivity audienceActivity, View view, Map map) throws Exception {
        String str = (String) map.get("thumbsUp");
        String str2 = (String) map.get("thumbsNum");
        audienceActivity.smallVideoView.getData().setHasZan("Y".equals(str));
        audienceActivity.smallVideoView.getData().setVideoPraiseNum(str2);
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(2131297529);
            ((TextView) view.findViewById(2131299157)).setText(FormatUtils.getShowString(str2));
            imageView.setImageResource("Y".equals(str) ? 2131231684 : 2131231683);
        }
    }

    public void share(SmallVideoEntity.Data data) {
        try {
            JSONObject jSONObject = new JSONObject();
            String viewTitle = data.getViewTitle();
            String str = "来自中国联通APP-" + data.getUserName() + "主播";
            data.getVideoUrl();
            String videoImg = data.getVideoImg();
            if (TextUtils.isEmpty(videoImg)) {
                videoImg = data.getTranscodeImg();
            }
            String videoTag = TextUtils.isEmpty(data.getVideoTag()) ? "创新·与智慧同行" : data.getVideoTag();
            jSONObject.put("shareType", "url");
            jSONObject.put("shareTitle", viewTitle);
            jSONObject.put("shareContent", videoTag);
            jSONObject.put("shareURL", URLSet.shareVideo(data.getVideoId()) + "&business_module=" + (data.getVideoType() + 1));
            jSONObject.put("shareIconURL", videoImg);
            ShareManager.ShowShareDialog(this.activityContext, "wechat,wechatmoments,qq,qzone", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.7
                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onCancel(String str2) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onComplete(String str2) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onError(String str2, String str3) {
                }

                {
                    AudienceActivity.this = this;
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void pingLun(SmallVideoEntity.Data data) {
        try {
            Intent intent = new Intent(this.activityContext, LiveCommentActivity.class);
            intent.putExtra("id", data.getVideoId());
            intent.putExtra("newsTitle", data.getViewTitle());
            intent.putExtra("subTitle", "");
            startActivityForResult(intent, 2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void zanVideo(String str, String str2, String str3) {
        this.managerAudienceLoadData.zanVideo(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$Im6-MQvJMciT1VUBtGrFFLnQahw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$zanVideo$53((String) obj);
            }
        });
    }

    public void showZanAnimation(View view) {
        try {
            ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.8f, 1.0f);
            ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.8f, 1.0f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.setDuration(1200L);
            animatorSet.setInterpolator(new SpringScaleInterpolator(0.4f));
            animatorSet.playTogether(ofFloat, ofFloat2);
            animatorSet.start();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void getRoomInfo(String str, final String str2, final int i) {
        this.managerAudienceLoadData.getAngleMoreVideoInfo(str2, "videoBack").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$f7p_EWlkg-jCwbPn6q_lQuI-jvw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getRoomInfo$56(AudienceActivity.this, i, str2, (SharpnessEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$7H3v4HV56zWDOG1To1UBUPYbXcU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getRoomInfo$57(AudienceActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$56(AudienceActivity audienceActivity, int i, String str, SharpnessEntity sharpnessEntity) throws Exception {
        if ("0000".equals(sharpnessEntity.getStatusCode())) {
            final ZhuboDataEntity userInfo = sharpnessEntity.getUserInfo();
            audienceActivity.zhuboDataEntity = userInfo;
            audienceActivity.rootView = audienceActivity.mLayoutManager.findViewByPosition(i);
            View view = audienceActivity.rootView;
            if (view == null) {
                return;
            }
            audienceActivity.flInputPassword = (FrameLayout) view.findViewById(2131296982);
            GlideApp.with((FragmentActivity) audienceActivity.activityContext).load(userInfo.getAnchorInfoBean().getLiveCoverImg()).into((ImageView) audienceActivity.rootView.findViewById(2131296393));
            ZhuboDataEntity.AnchorInfoBean anchorInfoBean = userInfo.getAnchorInfoBean();
            audienceActivity.isMulti = "Y".equals(sharpnessEntity.getLiveViewAngleMore());
            audienceActivity.messageView.initData(userInfo, audienceActivity.managerAudienceLoadData, audienceActivity.audienceUser, new C83028(userInfo));
            FrameLayout frameLayout = (FrameLayout) audienceActivity.rootView.findViewById(2131296434);
            ViewGroup viewGroup = (ViewGroup) audienceActivity.messageView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            frameLayout.addView(audienceActivity.messageView);
            String str2 = "#/liveplayer?";
            if (audienceActivity.list.get(i).getDataType() != null && "3".equals(audienceActivity.list.get(i).getDataType())) {
                str2 = "#/huifangplayer?videoId=" + str + "&";
            } else if (SlowLiveFragment.SLOW_LIVE.equals(audienceActivity.liveType)) {
                str2 = "#/liveplayer?&isToSlowLive=Y&";
            } else if (TheSameCityLiveFragment.THE_SAME_CITY.equals(audienceActivity.liveType)) {
                str2 = "#/liveplayer?&isToOneCity=Y &";
            }
            audienceActivity.playBackPause(audienceActivity.rootView);
            audienceActivity.messageView.setClickListener(audienceActivity.clickVideo);
            audienceActivity.messageView.setShareData(anchorInfoBean, audienceActivity.managerAudienceLoadData, audienceActivity.shareCode, str2);
            audienceActivity.landscapeView.setShareData(anchorInfoBean, audienceActivity.managerAudienceLoadData, audienceActivity.shareCode, str2);
            audienceActivity.messageView.setLiveType(anchorInfoBean.getDataType());
            audienceActivity.landscapeView.setLiveType(anchorInfoBean.getDataType());
            audienceActivity.messageView.hideGiftUI();
            audienceActivity.landscapeView.hideGiftUI();
            audienceActivity.getShareCode();
            audienceActivity.managerAudienceLoadData.loadZhuboData(userId, "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$oiJ89MOVP27QVCFV3X1yiah9iu4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getRoomInfo$54(AudienceActivity.this, (ZhuboDataEntity) obj);
                }
            });
            ShopEntity shopEntity = sharpnessEntity.getShopEntity();
            audienceActivity.messageView.setGoods(shopEntity);
            audienceActivity.landscapeView.setGoods(shopEntity);
            audienceActivity.messageView.setGoodsList(sharpnessEntity.getGoodlist());
            audienceActivity.landscapeView.setGoodsList(sharpnessEntity.getGoodlist());
            audienceActivity.messageView.setZanText(sharpnessEntity.getVideoPraiseNum(), str);
            audienceActivity.landscapeView.setZanText(sharpnessEntity.getVideoPraiseNum(), str);
            audienceActivity.managerAudienceLoadData.isGuanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$Yi5TK2Yn1V7HEmv8wCQJ3Mco6Jo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getRoomInfo$55(AudienceActivity.this, userInfo, (Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            String isNeedCheck = sharpnessEntity.getIsNeedCheck();
            audienceActivity.sharpness = sharpnessEntity;
            audienceActivity.coverImg = anchorInfoBean.getLiveCoverImg();
            MsLogUtil.m7979d("AudienceActivity", "密码状态：" + isNeedCheck);
            int i2 = 0;
            if ("Y".equals(isNeedCheck)) {
                View inflate = LayoutInflater.from(audienceActivity.activityContext).inflate(2131493094, (ViewGroup) null);
                audienceActivity.flInputPassword.removeAllViews();
                audienceActivity.flInputPassword.addView(inflate);
                audienceActivity.flInputPassword.setVisibility(0);
                try {
                    i2 = Integer.parseInt(sharpnessEntity.getPasswordCheckErrorNum());
                } catch (Exception unused) {
                    MsLogUtil.m7979d("AudienceActivity", "数字转化异常");
                }
                audienceActivity.verifyPassWord(str, i2);
                return;
            }
            audienceActivity.playVideoHandler.sendEmptyMessage(0);
            return;
        }
        MsLogUtil.m7979d("AudienceActivity", "获取回放失败");
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$8 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83028 implements AudienceMessageView.IPage1Click {
        final /* synthetic */ ZhuboDataEntity val$entity;

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void clickShoppingCart() {
        }

        C83028(ZhuboDataEntity zhuboDataEntity) {
            AudienceActivity.this = r1;
            this.val$entity = zhuboDataEntity;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public boolean isNeedPay() {
            return AudienceActivity.this.isNeedPay();
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void showZanAnim() {
            if (AudienceActivity.this.dianZanAnim != null) {
                AudienceActivity.this.dianZanAnim.startLocalZanSvgaAnim();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void clickGoods(ShopEntity.DataBean.GoodsBean goodsBean) {
            if (goodsBean != null) {
                String livePullUrl = this.val$entity.getAnchorInfoBean().getLivePullUrl();
                String userName = this.val$entity.getAnchorInfoBean().getUserName();
                PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, "商品卡片" + goodsBean.getName() + goodsBean.getGoodsUrl(), "直播回放页", "", "直播回放页");
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void userInfo() {
            try {
                String userIndexUrl = this.val$entity.getAnchorInfoBean().getUserIndexUrl();
                if (TextUtils.isEmpty(userIndexUrl)) {
                    return;
                }
                AudienceActivity.this.IntentGoWithOutVideo(AudienceActivity.this.addChannel(userIndexUrl, "直播回放"));
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void toggle() {
            AudienceActivity audienceActivity = AudienceActivity.this;
            audienceActivity.toggleFullScreen(audienceActivity.activityContext);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void zanNum(String str) {
            if (AudienceActivity.this.landscapeView != null) {
                AudienceActivity.this.landscapeView.setZanNum(str);
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
            AudienceActivity.bdCloudVideoView.stopPlayback();
            AudienceActivity.this.playUrl(liveUrlBean);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void showKeyboard() {
            AudienceActivity.this.alivcInputTextDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$8$JfnRcNFsnIGKo0z9tacsXwf3Zdo
                @Override // com.sinovatech.unicom.separatemodule.audience.util.InputDialog.OnTextSendListener
                public final void onTextSend(String str) {
                    AudienceActivity.C83028.lambda$showKeyboard$0(AudienceActivity.C83028.this, str);
                }
            });
            AudienceActivity.this.alivcInputTextDialog.show();
        }

        public static /* synthetic */ void lambda$showKeyboard$0(C83028 c83028, String str) {
            if (AudienceActivity.this.audienceUser.isLogin()) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setMsg(str.replace("|*|", ""));
                if (str.contains("@") && str.contains("|*|")) {
                    liveMsg.setType("atto");
                    liveMsg.setAtto("all");
                } else {
                    liveMsg.setType("chat");
                }
                liveMsg.setLevel(AudienceActivity.this.audienceUser.getLevel());
                liveMsg.setNick(AudienceActivity.this.audienceUser.getNick());
                liveMsg.setMgr(AudienceActivity.this.audienceUser.isMgr());
                AudienceActivity.this.messageView.updateMessageAdapter(liveMsg);
            }
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$54(AudienceActivity audienceActivity, ZhuboDataEntity zhuboDataEntity) throws Exception {
        audienceActivity.messageView.updatePush(zhuboDataEntity);
        audienceActivity.landscapeView.updatePush(zhuboDataEntity);
    }

    public static /* synthetic */ void lambda$getRoomInfo$55(AudienceActivity audienceActivity, ZhuboDataEntity zhuboDataEntity, Boolean bool) throws Exception {
        if (audienceActivity.isMulti) {
            audienceActivity.messageView.setGuanzhuView(true, zhuboDataEntity);
            audienceActivity.landscapeView.setGuanzuGone(true);
            return;
        }
        audienceActivity.messageView.setGuanzhuView(bool.booleanValue(), zhuboDataEntity);
        audienceActivity.landscapeView.setGuanzuGone(bool.booleanValue());
    }

    public static /* synthetic */ void lambda$getRoomInfo$57(AudienceActivity audienceActivity, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD("AudienceActivity", "多视角回放请求错误：" + th.getMessage());
    }

    private void verifyPassWord(String str, int i) {
        try {
            this.videoId = str;
            this.inputPw1 = (WiseEditText) findViewById(2131297310);
            this.inputPw2 = (WiseEditText) findViewById(2131297311);
            this.inputPw3 = (WiseEditText) findViewById(2131297312);
            this.inputPw4 = (WiseEditText) findViewById(2131297313);
            this.tvOk = (TextView) findViewById(2131299031);
            this.tvReset = (TextView) findViewById(2131299059);
            this.tvCancel = (TextView) findViewById(2131298905);
            this.tvOpportunity = (TextView) findViewById(2131299035);
            ImageView imageView = (ImageView) findViewById(2131297269);
            ImageView imageView2 = (ImageView) findViewById(2131296427);
            this.rlInputPw = (RelativeLayout) findViewById(2131298345);
            this.rlTip = (RelativeLayout) findViewById(2131298386);
            this.llTwo = (LinearLayout) findViewById(2131297791);
            final ImageView imageView3 = (ImageView) findViewById(2131298209);
            this.rlInputPw.setVisibility(0);
            this.rlTip.setVisibility(8);
            this.inputPw1.requestFocus();
            if (i >= 3) {
                this.rlInputPw.setVisibility(8);
                this.rlTip.setVisibility(0);
                this.llTwo.setVisibility(8);
                this.tvOk.setVisibility(0);
                this.tvOpportunity.setText("当前直播回放三次输入密码均有误，今天不可重试");
                this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$C3RMJi7nzMygkhfV2hcHO45AzoE
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceActivity.this.activityContext.finish();
                    }
                });
            }
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$kmNif3W7Z9zLsOHrii0cGDUwehs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.this.activityContext.finish();
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$uAN8SUD9_bvbDWQ8zMxMjXEOUb4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.lambda$verifyPassWord$60(AudienceActivity.this, imageView3, view);
                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$t0zHWc-egOiOGa_FOP7DkQm4GZg
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.lambda$verifyPassWord$61(AudienceActivity.this, imageView3, view);
                }
            });
            final WiseEditText[] wiseEditTextArr = {this.inputPw1, this.inputPw2, this.inputPw3, this.inputPw4};
            for (final int i2 = 0; i2 < wiseEditTextArr.length; i2++) {
                wiseEditTextArr[i2].addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.10
                    private int mEnd;
                    private int mStart;
                    private CharSequence temp;

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                    }

                    {
                        AudienceActivity.this = this;
                    }

                    @Override // android.text.TextWatcher
                    public void beforeTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
                        this.temp = charSequence;
                    }

                    @Override // android.text.TextWatcher
                    public void afterTextChanged(Editable editable) {
                        try {
                            this.mStart = wiseEditTextArr[i2].getSelectionStart();
                            this.mEnd = wiseEditTextArr[i2].getSelectionEnd();
                            if (this.temp.length() == 1) {
                                String obj = wiseEditTextArr[i2].getText().toString();
                                String trim = Pattern.compile("[^a-zA-Z0-9]").matcher(obj).replaceAll("").trim();
                                if (!obj.equals(trim)) {
                                    wiseEditTextArr[i2].setText(trim);
                                    wiseEditTextArr[i2].requestFocus();
                                } else {
                                    WiseEditText[] wiseEditTextArr2 = wiseEditTextArr;
                                    int length = wiseEditTextArr2.length;
                                    int i3 = 0;
                                    while (true) {
                                        if (i3 >= length) {
                                            break;
                                        }
                                        WiseEditText wiseEditText = wiseEditTextArr2[i3];
                                        if (wiseEditText.getText().toString().trim().length() == 0) {
                                            wiseEditText.setFocusable(true);
                                            wiseEditText.setFocusableInTouchMode(true);
                                            wiseEditText.requestFocus();
                                            break;
                                        }
                                        i3++;
                                    }
                                }
                            } else if (this.temp.length() > 1) {
                                editable.delete(this.mStart - 1, this.mEnd);
                                int i4 = this.mStart;
                                wiseEditTextArr[i2].setText(editable);
                                wiseEditTextArr[i2].setSelection(i4);
                            }
                            if (AudienceActivity.this.verifyPwdHandler != null) {
                                AudienceActivity.this.verifyPwdHandler.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7979d("AudienceActivity", "密码校验发生异常:" + e.getMessage());
                        }
                    }
                });
                wiseEditTextArr[i2].setSoftKeyListener(new View.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$y68bMta4AdygGbzv7VGRS_WzpFI
                    @Override // android.view.View.OnKeyListener
                    public final boolean onKey(View view, int i3, KeyEvent keyEvent) {
                        return AudienceActivity.lambda$verifyPassWord$62(wiseEditTextArr, i2, view, i3, keyEvent);
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", "密码校验初始化失败:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$verifyPassWord$60(AudienceActivity audienceActivity, ImageView imageView, View view) {
        imageView.setVisibility(0);
        audienceActivity.rlInputPw.setVisibility(8);
        audienceActivity.rlTip.setVisibility(8);
    }

    public static /* synthetic */ void lambda$verifyPassWord$61(AudienceActivity audienceActivity, ImageView imageView, View view) {
        audienceActivity.rlInputPw.setVisibility(0);
        imageView.setVisibility(8);
    }

    public static /* synthetic */ boolean lambda$verifyPassWord$62(WiseEditText[] wiseEditTextArr, int i, View view, int i2, KeyEvent keyEvent) {
        if (i2 == 67) {
            try {
                if (keyEvent.getAction() != 0 || wiseEditTextArr[i].getText().length() > 0 || i <= 0) {
                    return false;
                }
                wiseEditTextArr[i].clearFocus();
                int i3 = i - 1;
                wiseEditTextArr[i3].setEnabled(true);
                wiseEditTextArr[i3].requestFocus();
                return false;
            } catch (Exception e) {
                MsLogUtil.m7980d(e.getMessage());
                return false;
            }
        }
        return false;
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$11 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C827111 implements Handler.Callback {
        C827111() {
            AudienceActivity.this = r1;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            try {
                String obj = AudienceActivity.this.inputPw1.getText().toString();
                String obj2 = AudienceActivity.this.inputPw2.getText().toString();
                String obj3 = AudienceActivity.this.inputPw3.getText().toString();
                String obj4 = AudienceActivity.this.inputPw4.getText().toString();
                final StringBuilder sb = new StringBuilder();
                if (TextUtils.isEmpty(obj.trim()) || TextUtils.isEmpty(obj2.trim()) || TextUtils.isEmpty(obj3.trim()) || TextUtils.isEmpty(obj4.trim())) {
                    return false;
                }
                sb.append(obj);
                sb.append(obj2);
                sb.append(obj3);
                sb.append(obj4);
                ConUtil.isGoneKeyBoard(AudienceActivity.this.activityContext);
                AudienceActivity.this.managerAudienceLoadData.verifyPwd(AudienceActivity.this.videoId, sb.toString()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$11$GzcqzX6O2z5yWM1N09EovKwSCNQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj5) {
                        AudienceActivity.C827111.lambda$handleMessage$4(AudienceActivity.C827111.this, sb, (Map) obj5);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$11$uZD9VmWjlhQ717YDwiMRaQkqMMQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj5) {
                        AudienceActivity.C827111 c827111 = AudienceActivity.C827111.this;
                        MsLogUtil.m7979d("AudienceActivity", ((Throwable) obj5).getMessage());
                    }
                });
                return false;
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                return false;
            }
        }

        public static /* synthetic */ void lambda$handleMessage$4(C827111 c827111, StringBuilder sb, Map map) throws Exception {
            int i;
            String str = (String) map.get("statusCode");
            AudienceActivity.this.rlInputPw.setVisibility(8);
            if ("0000".equals(str)) {
                AudienceActivity.this.flInputPassword.removeAllViews();
                AudienceActivity.this.flInputPassword.setVisibility(8);
                if (AudienceActivity.this.playVideoHandler != null) {
                    AudienceActivity.this.playVideoHandler.sendEmptyMessage(0);
                }
            } else if ("0004".equals(str)) {
                AudienceActivity.this.rlInputPw.setVisibility(8);
                AudienceActivity.this.rlTip.setVisibility(0);
                AudienceActivity.this.llTwo.setVisibility(8);
                AudienceActivity.this.tvOk.setVisibility(0);
                AudienceActivity.this.tvOpportunity.setText("当前直播回放三次输入密码均有误，今天不可重试。");
                AudienceActivity.this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$11$-9Muehb6yGPF2cUvdQwrO82mEsw
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceActivity.this.activityContext.finish();
                    }
                });
            } else {
                AudienceActivity.this.rlTip.setVisibility(0);
                sb.setLength(0);
                try {
                    i = Integer.parseInt((String) map.get("data"));
                } catch (NumberFormatException unused) {
                    MsLogUtil.m7979d("AudienceActivity", "已校验失败次数转化失败");
                    i = 0;
                }
                int i2 = 3 - i;
                if (i2 >= 1) {
                    AudienceActivity.this.llTwo.setVisibility(0);
                    AudienceActivity.this.tvOk.setVisibility(8);
                    AudienceActivity.this.llTwo.setVisibility(0);
                    AudienceActivity.this.tvOpportunity.setText("当前直播间每天仅限三次输入机会，您还有" + i2 + "次机会");
                    AudienceActivity.this.tvReset.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$11$PS4kBvUfUnKfClBZ66_JKzAm3OI
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AudienceActivity.C827111.lambda$handleMessage$1(AudienceActivity.C827111.this, view);
                        }
                    });
                    AudienceActivity.this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$11$K30pCkvoWYIPtQlYAVqcVH_wvlk
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AudienceActivity.this.activityContext.finish();
                        }
                    });
                    return;
                }
                AudienceActivity.this.rlInputPw.setVisibility(8);
                AudienceActivity.this.rlTip.setVisibility(0);
                AudienceActivity.this.llTwo.setVisibility(8);
                AudienceActivity.this.tvOk.setVisibility(0);
                AudienceActivity.this.tvOpportunity.setText("当前直播回放三次输入密码均有误，今天不可重试");
                AudienceActivity.this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$11$PACldANiGmfTJTi4RWMxfPfTldU
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceActivity.this.activityContext.finish();
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$handleMessage$1(C827111 c827111, View view) {
            AudienceActivity.this.rlTip.setVisibility(8);
            AudienceActivity.this.rlInputPw.setVisibility(0);
            AudienceActivity.this.inputPw1.getText().clear();
            AudienceActivity.this.inputPw2.getText().clear();
            AudienceActivity.this.inputPw3.getText().clear();
            AudienceActivity.this.inputPw4.getText().clear();
            AudienceActivity.this.inputPw1.requestFocus();
        }
    }

    private void playBackPause(View view) {
        try {
            this.clickVideo = new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$7ybDqUiyc2O8ndiXPgodalpaepo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AudienceActivity.lambda$playBackPause$63(AudienceActivity.this, view2);
                }
            };
            this.rlPlayBtn = view.findViewById(2131298368);
            this.rlPlayBtn.setVisibility(8);
            this.ivPlayBtn = view.findViewById(2131297449);
            this.ivPlayBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$NODQWEkAcPLXj-wiZbh3jHsJQak
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    AudienceActivity.lambda$playBackPause$64(AudienceActivity.this, view2);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$playBackPause$63(AudienceActivity audienceActivity, View view) {
        if (bdCloudVideoView.getDuration() > 0) {
            if (audienceActivity.rlPlayBtn.getVisibility() == 8) {
                audienceActivity.rlPlayBtn.setVisibility(0);
                BDCloudVideoView bDCloudVideoView = bdCloudVideoView;
                if (bDCloudVideoView != null) {
                    bDCloudVideoView.pause();
                }
                AudienceMessageView audienceMessageView = audienceActivity.messageView;
                if (audienceMessageView != null) {
                    audienceMessageView.setPlayBtn(false);
                }
                AudienceLandscapeView audienceLandscapeView = audienceActivity.landscapeView;
                if (audienceLandscapeView != null) {
                    audienceLandscapeView.setPlayBtn(false);
                }
                if (audienceActivity.isNeedPay) {
                    View view2 = audienceActivity.ivPlayBtn;
                    if (view2 != null) {
                        view2.setVisibility(8);
                        return;
                    }
                    return;
                }
                View view3 = audienceActivity.ivPlayBtn;
                if (view3 != null) {
                    view3.setVisibility(0);
                }
            } else if (!audienceActivity.isNeedPay) {
                audienceActivity.rlPlayBtn.setVisibility(8);
                View view4 = audienceActivity.ivPlayBtn;
                if (view4 != null) {
                    view4.setVisibility(0);
                }
                BDCloudVideoView bDCloudVideoView2 = bdCloudVideoView;
                if (bDCloudVideoView2 != null) {
                    bDCloudVideoView2.start();
                }
                AudienceLandscapeView audienceLandscapeView2 = audienceActivity.landscapeView;
                if (audienceLandscapeView2 != null) {
                    audienceLandscapeView2.setPlayBtn(true);
                }
            } else {
                audienceActivity.pbReplay();
            }
        }
    }

    public static /* synthetic */ void lambda$playBackPause$64(AudienceActivity audienceActivity, View view) {
        try {
            if (audienceActivity.isNeedPay) {
                return;
            }
            audienceActivity.rlPlayBtn.setVisibility(8);
            if (bdCloudVideoView != null && !audienceActivity.isNeedPay) {
                bdCloudVideoView.start();
            }
            audienceActivity.landscapeView.setPlayBtn(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void verifyPassWord(int i, View view, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, FrameLayout frameLayout, View view2) {
        int i2;
        AudienceActivity audienceActivity = this;
        try {
            WiseEditText wiseEditText = (WiseEditText) view.findViewById(2131297310);
            WiseEditText wiseEditText2 = (WiseEditText) view.findViewById(2131297311);
            WiseEditText wiseEditText3 = (WiseEditText) view.findViewById(2131297312);
            WiseEditText wiseEditText4 = (WiseEditText) view.findViewById(2131297313);
            TextView textView = (TextView) view.findViewById(2131299031);
            TextView textView2 = (TextView) view.findViewById(2131299059);
            TextView textView3 = (TextView) view.findViewById(2131298905);
            TextView textView4 = (TextView) view.findViewById(2131299035);
            final RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(2131298345);
            RelativeLayout relativeLayout2 = (RelativeLayout) view.findViewById(2131298386);
            LinearLayout linearLayout = (LinearLayout) view.findViewById(2131297791);
            final ImageView imageView = (ImageView) view.findViewById(2131298209);
            relativeLayout.setVisibility(0);
            wiseEditText.requestFocus();
            ((ImageView) view.findViewById(2131296427)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$rIOlS1AbouTWFgK14yfPV9bXaNs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    AudienceActivity.this.activityContext.finish();
                }
            });
            ((ImageView) view.findViewById(2131297269)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$VjbdxIkuBr-CfCviw2eA4JWCnLs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view3) {
                    AudienceActivity.lambda$verifyPassWord$67(imageView, relativeLayout, view3);
                }
            });
            String passwordCheckNum = anchorInfoBean.getPasswordCheckNum();
            try {
                if (TextUtils.isEmpty(passwordCheckNum)) {
                    passwordCheckNum = "0";
                }
                i2 = Integer.parseInt(passwordCheckNum);
            } catch (NumberFormatException unused) {
                i2 = 0;
            }
            if (i2 == 0) {
                relativeLayout.setVisibility(8);
                relativeLayout2.setVisibility(0);
                linearLayout.setVisibility(8);
                textView.setVisibility(0);
                textView4.setText("当前直播间三次输入密码均有误，今天不可重试");
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$AT3iv7hdko9FYRzLod8CKurGM3E
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view3) {
                        AudienceActivity.this.activityContext.finish();
                    }
                });
            }
            WiseEditText[] wiseEditTextArr = {wiseEditText, wiseEditText2, wiseEditText3, wiseEditText4};
            StringBuilder sb = new StringBuilder();
            int i3 = 0;
            while (i3 < wiseEditTextArr.length) {
                final WiseEditText[] wiseEditTextArr2 = wiseEditTextArr;
                final int i4 = i3;
                LinearLayout linearLayout2 = linearLayout;
                RelativeLayout relativeLayout3 = relativeLayout2;
                RelativeLayout relativeLayout4 = relativeLayout;
                TextView textView5 = textView4;
                TextView textView6 = textView;
                try {
                    wiseEditTextArr[i3].addTextChangedListener(new C827212(wiseEditTextArr, i3, sb, i, relativeLayout, frameLayout, view2, anchorInfoBean, relativeLayout2, linearLayout2, textView, textView5, textView2, wiseEditText, wiseEditText2, wiseEditText3, wiseEditText4, textView3));
                } catch (Exception e) {
                    e = e;
                }
                try {
                    wiseEditTextArr2[i4].setSoftKeyListener(new View.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$enDJa99sUD55BNPELezcg7Cy4kg
                        @Override // android.view.View.OnKeyListener
                        public final boolean onKey(View view3, int i5, KeyEvent keyEvent) {
                            return AudienceActivity.lambda$verifyPassWord$69(AudienceActivity.this, wiseEditTextArr2, i4, view3, i5, keyEvent);
                        }
                    });
                    i3 = i4 + 1;
                    audienceActivity = this;
                    wiseEditTextArr = wiseEditTextArr2;
                    linearLayout = linearLayout2;
                    relativeLayout2 = relativeLayout3;
                    relativeLayout = relativeLayout4;
                    textView4 = textView5;
                    textView = textView6;
                } catch (Exception e2) {
                    e = e2;
                    MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                    return;
                }
            }
        } catch (Exception e3) {
            e = e3;
        }
    }

    public static /* synthetic */ void lambda$verifyPassWord$67(final ImageView imageView, final RelativeLayout relativeLayout, View view) {
        imageView.setVisibility(0);
        relativeLayout.setVisibility(8);
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$8jEa1PtixExehtyd60GECPkr8AA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudienceActivity.lambda$verifyPassWord$66(relativeLayout, imageView, view2);
            }
        });
    }

    public static /* synthetic */ void lambda$verifyPassWord$66(RelativeLayout relativeLayout, ImageView imageView, View view) {
        relativeLayout.setVisibility(0);
        imageView.setVisibility(8);
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$12 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C827212 implements TextWatcher {
        private int mEnd;
        private int mStart;
        private CharSequence temp;
        final /* synthetic */ ZhuboDataEntity.AnchorInfoBean val$anchorInfoBean;
        final /* synthetic */ FrameLayout val$inputParent;
        final /* synthetic */ WiseEditText val$inputPw1;
        final /* synthetic */ WiseEditText val$inputPw2;
        final /* synthetic */ WiseEditText val$inputPw3;
        final /* synthetic */ WiseEditText val$inputPw4;
        final /* synthetic */ int val$j;
        final /* synthetic */ LinearLayout val$llTwo;
        final /* synthetic */ WiseEditText[] val$mArray;
        final /* synthetic */ int val$position;
        final /* synthetic */ RelativeLayout val$rlTip;
        final /* synthetic */ RelativeLayout val$rlinputPw;
        final /* synthetic */ StringBuilder val$stringBuilder;
        final /* synthetic */ TextView val$tvOk;
        final /* synthetic */ TextView val$tvOpportunity;
        final /* synthetic */ TextView val$tvReset;
        final /* synthetic */ TextView val$tvcancle;
        final /* synthetic */ View val$view1;

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C827212(WiseEditText[] wiseEditTextArr, int i, StringBuilder sb, int i2, RelativeLayout relativeLayout, FrameLayout frameLayout, View view, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, RelativeLayout relativeLayout2, LinearLayout linearLayout, TextView textView, TextView textView2, TextView textView3, WiseEditText wiseEditText, WiseEditText wiseEditText2, WiseEditText wiseEditText3, WiseEditText wiseEditText4, TextView textView4) {
            AudienceActivity.this = r3;
            this.val$mArray = wiseEditTextArr;
            this.val$j = i;
            this.val$stringBuilder = sb;
            this.val$position = i2;
            this.val$rlinputPw = relativeLayout;
            this.val$inputParent = frameLayout;
            this.val$view1 = view;
            this.val$anchorInfoBean = anchorInfoBean;
            this.val$rlTip = relativeLayout2;
            this.val$llTwo = linearLayout;
            this.val$tvOk = textView;
            this.val$tvOpportunity = textView2;
            this.val$tvReset = textView3;
            this.val$inputPw1 = wiseEditText;
            this.val$inputPw2 = wiseEditText2;
            this.val$inputPw3 = wiseEditText3;
            this.val$inputPw4 = wiseEditText4;
            this.val$tvcancle = textView4;
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            this.temp = charSequence;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            int i;
            C827212 c827212;
            try {
                this.mStart = this.val$mArray[this.val$j].getSelectionStart();
                this.mEnd = this.val$mArray[this.val$j].getSelectionEnd();
                this.val$stringBuilder.append(editable.toString());
                try {
                    if (this.temp.length() != 1 || (this.val$j != 0 && this.val$j != 1 && this.val$j != 2 && this.val$j != 3)) {
                        i = 1;
                        c827212 = this;
                    } else if (this.val$j == 3) {
                        ObservableSubscribeProxy<Map<String, String>> checkPassword = AudienceActivity.this.managerAudienceLoadData.checkPassword(((ListDataEntity) AudienceActivity.this.list.get(this.val$position)).getJobNumber(), this.val$stringBuilder.toString());
                        final RelativeLayout relativeLayout = this.val$rlinputPw;
                        final FrameLayout frameLayout = this.val$inputParent;
                        final int i2 = this.val$position;
                        final View view = this.val$view1;
                        final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = this.val$anchorInfoBean;
                        final RelativeLayout relativeLayout2 = this.val$rlTip;
                        final LinearLayout linearLayout = this.val$llTwo;
                        final TextView textView = this.val$tvOk;
                        final TextView textView2 = this.val$tvOpportunity;
                        final StringBuilder sb = this.val$stringBuilder;
                        final TextView textView3 = this.val$tvReset;
                        final WiseEditText wiseEditText = this.val$inputPw1;
                        final WiseEditText wiseEditText2 = this.val$inputPw2;
                        final WiseEditText wiseEditText3 = this.val$inputPw3;
                        final WiseEditText wiseEditText4 = this.val$inputPw4;
                        final TextView textView4 = this.val$tvcancle;
                        try {
                            Consumer<? super Map<String, String>> consumer = new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$12$1UC55joLAsAX7tGVQfiC5YP8tx4
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    AudienceActivity.C827212.lambda$afterTextChanged$0(AudienceActivity.C827212.this, relativeLayout, frameLayout, i2, view, anchorInfoBean, relativeLayout2, linearLayout, textView, textView2, sb, textView3, wiseEditText, wiseEditText2, wiseEditText3, wiseEditText4, textView4, (Map) obj);
                                }
                            };
                            c827212 = this;
                            checkPassword.subscribe(consumer, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$12$1QwIeDMhuESMA4dINX69YwQ1HM0
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    AudienceActivity.C827212 c8272122 = AudienceActivity.C827212.this;
                                    MsLogUtil.m7979d("AudienceActivity", ((Throwable) obj).getMessage());
                                }
                            });
                            i = 1;
                        } catch (Exception e) {
                            e = e;
                            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                            return;
                        }
                    } else {
                        c827212 = this;
                        String obj = c827212.val$mArray[c827212.val$j].getText().toString();
                        String trim = Pattern.compile("[^a-zA-Z0-9]").matcher(obj).replaceAll("").trim();
                        if (!obj.equals(trim)) {
                            c827212.val$mArray[c827212.val$j].setText(trim);
                            c827212.val$mArray[c827212.val$j].requestFocus();
                            i = 1;
                        } else {
                            i = 1;
                            c827212.val$mArray[c827212.val$j + 1].setFocusable(true);
                            c827212.val$mArray[c827212.val$j + 1].setFocusableInTouchMode(true);
                            c827212.val$mArray[c827212.val$j + 1].requestFocus();
                        }
                    }
                    if (c827212.temp.length() > i) {
                        editable.delete(c827212.mStart - i, c827212.mEnd);
                        int i3 = c827212.mStart;
                        c827212.val$mArray[c827212.val$j].setText(editable);
                        c827212.val$mArray[c827212.val$j].setSelection(i3);
                    }
                } catch (Exception e2) {
                    e = e2;
                }
            } catch (Exception e3) {
                e = e3;
            }
        }

        public static /* synthetic */ void lambda$afterTextChanged$0(C827212 c827212, final RelativeLayout relativeLayout, FrameLayout frameLayout, int i, View view, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final RelativeLayout relativeLayout2, LinearLayout linearLayout, TextView textView, TextView textView2, StringBuilder sb, TextView textView3, final WiseEditText wiseEditText, final WiseEditText wiseEditText2, final WiseEditText wiseEditText3, final WiseEditText wiseEditText4, TextView textView4, Map map) throws Exception {
            String str = (String) map.get("statusCode");
            String str2 = (String) map.get("data");
            relativeLayout.setVisibility(8);
            if ("0000".equals(str)) {
                frameLayout.setVisibility(8);
                AudienceActivity.this.v2Video.setVisibility(0);
                if (AudienceActivity.this.wrapper != null) {
                    AudienceActivity.this.wrapper.removeAllConsumers().addConsumer(AudienceActivity.this.consumer);
                }
                AudienceActivity audienceActivity = AudienceActivity.this;
                audienceActivity.getSharpnessInfo(((ListDataEntity) audienceActivity.list.get(i)).getJobNumber(), view, ((ListDataEntity) AudienceActivity.this.list.get(i)).getCoverImg(), i);
                AudienceActivity.this.managerSocket.setRoomId(anchorInfoBean.getLiveRoom());
                AudienceActivity.this.managerSocket.evnetJoinRoom();
            } else if ("0001".equals(str)) {
                relativeLayout.setVisibility(8);
                relativeLayout2.setVisibility(0);
                linearLayout.setVisibility(8);
                textView.setVisibility(0);
                textView2.setText("当前直播间三次输入密码均有误，今天不可重试");
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.12.1
                    {
                        C827212.this = c827212;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NBSActionInstrumentation.onClickEventEnter(view2, this);
                        Tracker.onClick(view2);
                        AudienceActivity.this.activityContext.finish();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else if ("4004".equals(str)) {
                relativeLayout2.setVisibility(0);
                sb.setLength(0);
                int parseInt = Integer.parseInt(str2);
                if (parseInt >= 1) {
                    linearLayout.setVisibility(0);
                    textView.setVisibility(8);
                    linearLayout.setVisibility(0);
                    textView2.setText("当前直播间每天仅限三次输入机会，您还有" + parseInt + "次机会");
                    textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.12.2
                        {
                            C827212.this = c827212;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            NBSActionInstrumentation.onClickEventEnter(view2, this);
                            Tracker.onClick(view2);
                            relativeLayout2.setVisibility(8);
                            relativeLayout.setVisibility(0);
                            wiseEditText.getText().clear();
                            wiseEditText2.getText().clear();
                            wiseEditText3.getText().clear();
                            wiseEditText4.getText().clear();
                            wiseEditText.requestFocus();
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    textView4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.12.3
                        {
                            C827212.this = c827212;
                        }

                        @Override // android.view.View.OnClickListener
                        public void onClick(View view2) {
                            NBSActionInstrumentation.onClickEventEnter(view2, this);
                            Tracker.onClick(view2);
                            AudienceActivity.this.activityContext.finish();
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    return;
                }
                linearLayout.setVisibility(8);
                textView.setVisibility(0);
                textView2.setText("当前直播间三次输入密码均有误，今天不可重试");
                textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.12.4
                    {
                        C827212.this = c827212;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view2) {
                        NBSActionInstrumentation.onClickEventEnter(view2, this);
                        Tracker.onClick(view2);
                        AudienceActivity.this.activityContext.finish();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            }
        }
    }

    public static /* synthetic */ boolean lambda$verifyPassWord$69(AudienceActivity audienceActivity, WiseEditText[] wiseEditTextArr, int i, View view, int i2, KeyEvent keyEvent) {
        if (i2 == 67) {
            try {
                if (keyEvent.getAction() != 0 || wiseEditTextArr[i].getText().length() > 0 || i <= 0) {
                    return false;
                }
                wiseEditTextArr[i].clearFocus();
                int i3 = i - 1;
                wiseEditTextArr[i3].setEnabled(true);
                wiseEditTextArr[i3].requestFocus();
                return false;
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                return false;
            }
        }
        return false;
    }

    private void getInteractionAnchorsInfo(String str) {
        this.managerAudienceLoadData.getInteractionAnchorsInfo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$kfznQ1IuOC-IdPENw3F5JKe1ja8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getInteractionAnchorsInfo$72(AudienceActivity.this, (LianMZBEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$5xwJz0nsHnwej4NvxCDmWPSWpbU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                AudienceActivity.this.messageView.setInterAnchorInfoDiaplay(null, null, null);
            }
        });
    }

    public static /* synthetic */ void lambda$getInteractionAnchorsInfo$72(AudienceActivity audienceActivity, LianMZBEntity lianMZBEntity) throws Exception {
        if (lianMZBEntity != null && lianMZBEntity.getData() != null) {
            audienceActivity.messageView.setInterAnchorInfoDiaplay(lianMZBEntity.getData(), audienceActivity.managerAudienceLoadData, new AudienceMessageView.CallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$2PE98tGkx_1NbgDyZ_DFWEt87Is
                @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.CallBack
                public final void callBack(String str) {
                    AudienceActivity.lambda$getInteractionAnchorsInfo$71(AudienceActivity.this, str);
                }
            });
        } else {
            audienceActivity.messageView.setInterAnchorInfoDiaplay(null, null, null);
        }
    }

    public static /* synthetic */ void lambda$getInteractionAnchorsInfo$71(AudienceActivity audienceActivity, final String str) {
        if (audienceActivity.isClickedLianMai) {
            return;
        }
        audienceActivity.runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$mfae5yaJD2aF-dYhRkgI7-_7_Ko
            @Override // java.lang.Runnable
            public final void run() {
                AudienceActivity.lambda$getInteractionAnchorsInfo$70(AudienceActivity.this, str);
            }
        });
    }

    public static /* synthetic */ void lambda$getInteractionAnchorsInfo$70(AudienceActivity audienceActivity, String str) {
        audienceActivity.pageNum = "0";
        audienceActivity.fromType = SINGLE;
        audienceActivity.singleUserId = str;
        audienceActivity.loadData(str);
    }

    public void leaveRoom(int i) {
        try {
            this.managerSocket.eventLeaveRoom();
            this.messageView.clearMessage();
            if (this.messageView != null) {
                this.messageView.setVideoProgressGone();
            }
            if (this.landscapeView != null) {
                this.landscapeView.setVideoProgressGone();
            }
            this.currentLogo = null;
            AudienceGuanzhuDialog.setLiveRoom(this.list.get(i).getJobNumber());
            if (this.helpingDialog != null) {
                this.helpingDialog.dismiss();
                this.helpingDialog = null;
            }
            if (this.liveUrlList != null) {
                this.liveUrlList.clear();
            }
            this.currentLiveUrl = null;
            this.messageView.setInterAnchorInfoDiaplay(null, null, null);
            clearPayInfo();
            this.liveRoomUIInfo = null;
            setLiveMore();
            releaseVideo();
            showLiveCover();
            this.smallVideoView = null;
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void setLogo(String str) {
        try {
            this.currentLogo = str;
            this.messageView.setLogoVisable(str);
            if (this.toggleBack.getVisibility() == 0) {
                GlideApp.with((FragmentActivity) this.activityContext).load(str).into(this.ivLogo);
                this.ivLogo.setVisibility(0);
            } else {
                this.ivLogo.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void setPlayerUrl(SharpnessEntity.LiveUrlBean liveUrlBean, View view) {
        try {
            UIUtils.logD("lln", "setPlayerUrl");
            addVideoView(view);
            this.isPlaying = false;
            this.streamStartTime = System.currentTimeMillis();
            playUrl(liveUrlBean);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void playUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        try {
            this.isClickedLianMai = false;
            this.currentLiveUrl = liveUrlBean;
            bdCloudVideoView.stopPlayback();
            bdCloudVideoView.reSetRender();
            bdCloudVideoView.setVideoPath(getSwitchUrl(liveUrlBean));
            bdCloudVideoView.setTag(getSwitchUrl(liveUrlBean));
            String currentPlayingUrl = bdCloudVideoView.getCurrentPlayingUrl();
            if (!TextUtils.isEmpty(currentPlayingUrl) && currentPlayingUrl.contains("m3u8")) {
                UIUtils.logD("m3u8", "包含连接");
                bdCloudVideoView.setVolume(1.0f, 1.0f);
            }
            if (!this.isNeedPay) {
                bdCloudVideoView.start();
            }
            App.getSharePreferenceUtil().putString("gzdqingxidu", liveUrlBean.getSharpnessLevel());
            this.messageView.setSwitchTextView(liveUrlBean);
            this.landscapeView.setSwitchTextView(liveUrlBean);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void filerList(int i) {
        try {
            this.isXiabo = false;
            Iterator<ListDataEntity> it = this.list.iterator();
            while (it.hasNext()) {
                ListDataEntity next = it.next();
                if ("N".equals(next.getLiving()) && "1".equals(next.getDataType())) {
                    it.remove();
                }
            }
            this.mAdapter.notifyItemRemoved(i);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setWebSocketData(AudienceLiveInfoEvent audienceLiveInfoEvent) {
        try {
            final LiveMsg liveMsg = (LiveMsg) audienceLiveInfoEvent.getData();
            if ("myJoin".equals(liveMsg.getType())) {
                this.audienceUser.setMgr(liveMsg.isMgr());
                liveMsg.setType("join");
                liveMsg.setLevel(this.audienceUser.getLevel());
                liveMsg.setNick(this.audienceUser.getNick());
                if (this.alivcInputTextDialog != null) {
                    this.alivcInputTextDialog.setCanAtUser(liveMsg.isMgr());
                }
            }
            if (!"chat".equals(liveMsg.getType()) || TextUtils.isEmpty(liveMsg.getRid()) || liveMsg.getRid().equals(this.list.get(this.mCurrentItem).getJobNumber())) {
                if ("push".equals(liveMsg.getType()) && "6".equals(liveMsg.getmType()) && !TextUtils.isEmpty(this.audienceUser.getNick()) && this.audienceUser.getNick().equals(liveMsg.getNick())) {
                    return;
                }
                if ("finish".equals(liveMsg.getType())) {
                    xiaBoStatus(false);
                } else if ("login".equals(liveMsg.getType())) {
                    this.audienceUser.setLogin(true);
                    this.audienceUser.setNick(liveMsg.getNick());
                    this.audienceUser.setLevel(liveMsg.getLevel());
                } else if ("push".equals(liveMsg.getType()) && "5".equals(liveMsg.getmType())) {
                    this.managerAudienceLoadData.loadShopData(this.list.get(this.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$-E2TOk0xZCgjueNURcxv6lwy-DI
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$setWebSocketData$74(AudienceActivity.this, (ShopEntity) obj);
                        }
                    });
                    this.managerAudienceLoadData.loadShopList(this.list.get(this.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$i4gfnTiZyZ1yFKQiCyHP0cFKvNg
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$setWebSocketData$75(AudienceActivity.this, (List) obj);
                        }
                    });
                } else if ("push".equals(liveMsg.getType()) && "8".equals(liveMsg.getmType())) {
                    this.managerAudienceLoadData.loadZhuboData(this.list.get(this.mCurrentItem).getJobNumber(), "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$hkgPXyNO0IFw3y0L-QQVelGRTVw
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$setWebSocketData$76(AudienceActivity.this, (ZhuboDataEntity) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                    this.managerAudienceLoadData.loadShopData(this.list.get(this.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$AGjVgfXeUuyYW1azJKGr1jFDa-k
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$setWebSocketData$77(AudienceActivity.this, (ShopEntity) obj);
                        }
                    });
                } else if ("push".equals(liveMsg.getType()) && "7".equals(liveMsg.getmType())) {
                    this.managerAudienceLoadData.loadZhuuangXiuData(this.list.get(this.mCurrentItem).getJobNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$SJEtjuPERJ_4Ox3-rFLkAYdCoJM
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$setWebSocketData$78(AudienceActivity.this, (ZhuangXiuEntity) obj);
                        }
                    });
                } else if ("push".equals(liveMsg.getType()) && "20".equals(liveMsg.getmType())) {
                    final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = this.zhuboDataEntity.getAnchorInfoBean();
                    this.managerAudienceLoadData.queryActivityTime(anchorInfoBean.getUserId(), anchorInfoBean.getUserName()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$Z2428__9RAVWGEjugTPZkRis6b8
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.this.messageView.setHuodongView(r1.getUserId(), anchorInfoBean.getUserName(), (ActivityTimeEntity) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                } else if ("push".equals(liveMsg.getType()) && "602".equals(liveMsg.getmType())) {
                    if (liveMsg.getId().equals(userId)) {
                        this.helpingDialog = new HelpingHandDialog(this.activityContext, liveMsg.getLevel(), liveMsg.getGiftNum(), liveMsg.getGiftCode(), new HelpingHandDialog.OnHelpingDialogListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.13
                            {
                                AudienceActivity.this = this;
                            }

                            @Override // com.sinovatech.unicom.separatemodule.audience.view.HelpingHandDialog.OnHelpingDialogListener
                            public void callBack() {
                                AudienceActivity.IntentGo(AudienceActivity.this.activityContext, liveMsg.getNick());
                            }
                        });
                        this.helpingDialog.show();
                    }
                } else if ("push".equals(liveMsg.getType()) && "14".equals(liveMsg.getmType())) {
                    if (this.zhuboDataEntity.getAnchorInfoBean().getUserId().equals(liveMsg.getRid())) {
                        getInteractionAnchorsInfo(liveMsg.getRid());
                    }
                } else if ("push".equals(liveMsg.getType()) && "16".equals(liveMsg.getmType())) {
                    this.messageView.setInterAnchorInfoDiaplay(null, null, null);
                    if (this.zhuboDataEntity.getAnchorInfoBean().getUserId().equals(liveMsg.getRid())) {
                        UIUtils.toast("对方已离开连麦");
                        this.messageView.gonePkView();
                    }
                } else if ("push".equals(liveMsg.getType()) && "700".equals(liveMsg.getmType())) {
                    this.messageView.setScore(liveMsg.getPkScoreFirst(), liveMsg.getPkScoreSecond());
                } else if ("push".equals(liveMsg.getType()) && "701".equals(liveMsg.getmType())) {
                    this.messageView.showWiner(liveMsg.getPkWinId(), liveMsg.getPkScoreFirst(), liveMsg.getPkScoreSecond());
                } else if ("push".equals(liveMsg.getType()) && "710".equals(liveMsg.getmType()) && liveMsg.getNick().equals(this.audienceUser.getNick())) {
                    UIUtils.logD("test", "屏蔽掉了socket发来的本人求讲解");
                } else {
                    this.messageView.updateMessageAdapter(liveMsg);
                    this.landscapeView.updateMessageAdapter(liveMsg);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setWebSocketData$74(AudienceActivity audienceActivity, ShopEntity shopEntity) throws Exception {
        audienceActivity.messageView.setGoods(shopEntity);
        audienceActivity.landscapeView.setGoods(shopEntity);
    }

    public static /* synthetic */ void lambda$setWebSocketData$75(AudienceActivity audienceActivity, List list) throws Exception {
        audienceActivity.messageView.setGoodsList(list);
        audienceActivity.landscapeView.setGoodsList(list);
    }

    public static /* synthetic */ void lambda$setWebSocketData$76(AudienceActivity audienceActivity, ZhuboDataEntity zhuboDataEntity) throws Exception {
        audienceActivity.messageView.updatePush(zhuboDataEntity);
        audienceActivity.landscapeView.updatePush(zhuboDataEntity);
    }

    public static /* synthetic */ void lambda$setWebSocketData$77(AudienceActivity audienceActivity, ShopEntity shopEntity) throws Exception {
        audienceActivity.messageView.setGoods(shopEntity);
        audienceActivity.landscapeView.setGoods(shopEntity);
    }

    public static /* synthetic */ void lambda$setWebSocketData$78(AudienceActivity audienceActivity, ZhuangXiuEntity zhuangXiuEntity) throws Exception {
        audienceActivity.messageView.setMultiView(audienceActivity.isMulti);
        audienceActivity.messageView.setRenovation(zhuangXiuEntity);
    }

    private boolean isLandscape() {
        return getScreenOrientation(this.activityContext) == 0;
    }

    public static void IntentGo(AppCompatActivity appCompatActivity, String str, String str2, String str3, int i) {
        try {
            if (appCompatActivity instanceof AudienceActivity) {
                AudienceActivity audienceActivity = (AudienceActivity) appCompatActivity;
                if (audienceActivity.isLandscape()) {
                    audienceActivity.toggleFullScreen(appCompatActivity);
                }
            }
            AudienceGuanzhuDialog.cancelGuanzhusub();
            if (!str.startsWith(URLSet.getLanjieZhiboUrl()) && !str.contains("zhibo/index.html#/huifangplayer?")) {
                if (!TextUtils.isEmpty(str2)) {
                    new ManagerAudienceLoadData(appCompatActivity).goodsLog(userId, str3, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$vgnPmua8gCO3TDCEOfJjqKAZPoQ
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$IntentGo$80((Boolean) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                }
                Intent intent = new Intent(appCompatActivity, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(str);
                if ((appCompatActivity instanceof AudienceActivity) && ((AudienceActivity) appCompatActivity).currentLiveUrl != null) {
                    webParamsEntity.setType("audience");
                }
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                appCompatActivity.startActivityForResult(intent, i);
                return;
            }
            IntentManager.handleLocal(appCompatActivity, "", str);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static void IntentGo(AppCompatActivity appCompatActivity, String str) {
        IntentGo(appCompatActivity, str, "", "", 4000);
    }

    public void IntentGoWithOutVideo(String str) {
        try {
            AudienceGuanzhuDialog.cancelGuanzhusub();
            Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(str);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            this.activityContext.startActivityForResult(intent, 4000);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void isFullView(TextView textView) {
        try {
            if ((this.activityContext.getResources().getConfiguration().screenLayout & 15) != 3 || Build.VERSION.SDK_INT < 24 || this.activityContext.isInMultiWindowMode()) {
                return;
            }
            textView.setVisibility(8);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void toggleFullScreen4SmallVideo(Context context) {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297726);
            TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
            ImageView imageView = (ImageView) findViewByPosition.findViewById(2131296393);
            ImageView imageView2 = (ImageView) findViewByPosition.findViewById(2131296394);
            LinearLayout linearLayout2 = (LinearLayout) findViewByPosition.findViewById(2131297733);
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296988);
            FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131296396);
            bdCloudVideoView.setVideoScalingMode(1);
            final View findViewById = findViewByPosition.findViewById(2131298334);
            if (findViewById != null) {
                findViewById.setVisibility(8);
            }
            if (getScreenOrientation(context) == 0) {
                if (this.wrapper != null) {
                    this.wrapper.removeAllConsumers().addConsumer(this.consumer);
                }
                this.smartSwipeRefresh.getSwipeConsumer().unlockAllDirections();
                setRequestedOrientation(1);
                this.isHorizontal = false;
                resetLiveCover();
                frameLayout.removeAllViews();
                linearLayout.setVisibility(0);
                textView.setVisibility(0);
                isFullView(textView);
                imageView.setVisibility(8);
                imageView2.setVisibility(0);
                linearLayout2.setVisibility(8);
                this.mLayoutManager.setScrollEnabled(true);
                findViewByPosition.findViewById(2131297750).setVisibility(0);
                if (this.smartSwipeRefresh != null) {
                    this.smartSwipeRefresh.startLoadMore();
                }
                View findViewById2 = findViewByPosition.findViewById(2131297383);
                if (findViewById2 != null) {
                    findViewById2.setVisibility(0);
                }
                if (!this.smallVideoView.getPlayStatus()) {
                    this.smallVideoView.setPlayStatus(false, false);
                }
            } else {
                frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131296395);
                setRequestedOrientation(0);
                this.mLayoutManager.setScrollEnabled(false);
                if (this.smartSwipeRefresh != null) {
                    this.smartSwipeRefresh.disableLoadMore();
                }
                linearLayout.setVisibility(8);
                textView.setVisibility(8);
                imageView.setVisibility(8);
                imageView2.setVisibility(8);
                linearLayout2.setVisibility(0);
                boolean z = App.getSharePreferenceUtil().getBoolean("bright_volume_show");
                findViewById.setVisibility(z ? 8 : 0);
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$ryI5zFzkjoZXM8NJZhhC7mjaFo8
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        findViewById.setVisibility(8);
                    }
                });
                if (z) {
                    App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
                }
                findViewByPosition.findViewById(2131297750).setVisibility(8);
                MyGestureView myGestureView = new MyGestureView(this.activityContext);
                myGestureView.setActivity(this.activityContext);
                myGestureView.setListener(new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.14
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                    public void doubleTapCallback() {
                    }

                    {
                        AudienceActivity.this = this;
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                    public void onClick(long j) {
                        try {
                            if (AudienceActivity.this.smallVideoView.getPlayStatus()) {
                                AudienceActivity.this.smallVideoView.setPlayStatus(false, true);
                                AudienceActivity.bdCloudVideoView.pause();
                            } else {
                                AudienceActivity.this.smallVideoView.setPlayStatus(true, true);
                                if (!AudienceActivity.this.isNeedPay) {
                                    AudienceActivity.bdCloudVideoView.start();
                                }
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                        }
                    }
                });
                if (!this.smallVideoView.getPlayStatus()) {
                    this.smallVideoView.setPlayStatus(false, true);
                }
                ViewGroup viewGroup = (ViewGroup) myGestureView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                frameLayout.addView(myGestureView, new FrameLayout.LayoutParams(-1, -1));
                View findViewById3 = findViewByPosition.findViewById(2131297383);
                if (findViewById3 != null) {
                    findViewById3.setVisibility(8);
                }
            }
            ViewGroup viewGroup2 = (ViewGroup) bdCloudVideoView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            frameLayout2.addView(bdCloudVideoView);
            if (bdCloudVideoView.getVisibility() != 0) {
                bdCloudVideoView.setVisibility(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"SourceLockedOrientationActivity"})
    public void toggleFullScreen(Context context) {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
            ImageView imageView = (ImageView) findViewByPosition.findViewById(2131296397);
            final FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131296994);
            AudienceGoodsListDialogNew.getInstance().releaseDialog();
            AudienceGoodsListDialogNew.getInstance().releasePw();
            if (getScreenOrientation(context) == 0) {
                if (this.wrapper != null) {
                    this.wrapper.removeAllConsumers().addConsumer(this.consumer);
                }
                this.smartSwipeRefresh.getSwipeConsumer().unlockAllDirections();
                setRequestedOrientation(1);
                this.isHorizontal = false;
                resetLiveCover();
                this.messageView.setVisibility(0);
                this.toggleBack.setVisibility(8);
                this.mLayoutManager.setScrollEnabled(true);
                frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
                bdCloudVideoView.setVideoScalingMode(1);
                imageView.setVisibility(8);
                AudienceGuanzhuDialog.isLandscape = false;
                ShareManager.isLandscape = false;
                this.v2Video.setVisibility(0);
                if (this.isMulti) {
                    UIUtils.logD("切换屏幕", "设置为竖屏，处理多视角");
                    View findViewById = findViewByPosition.findViewById(2131297749);
                    findViewByPosition.findViewById(2131297748).setVisibility(8);
                    findViewByPosition.findViewById(2131297444).setVisibility(0);
                    findViewByPosition.findViewById(2131299019).setVisibility(findViewById.getVisibility() == 8 ? 0 : 8);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.gravity = 48;
                    FrameLayout frameLayout3 = (FrameLayout) findViewByPosition.findViewById(2131296997);
                    frameLayout3.removeAllViews();
                    ViewGroup viewGroup = (ViewGroup) findViewById.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeAllViews();
                    }
                    frameLayout3.addView(findViewById, layoutParams);
                    if (this.angleAdapter != null) {
                        this.angleAdapter.notifyDataSetChanged();
                    }
                }
                if (this.llPayInfo != null) {
                    UIUtils.logD("切换屏幕", "设置为竖屏，处理付费提示");
                    ViewGroup viewGroup2 = (ViewGroup) this.llPayInfo.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeView(this.llPayInfo);
                    }
                    this.vTop1.setVisibility(0);
                    this.vTop2.setVisibility(0);
                    FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) this.llPayInfo.getLayoutParams();
                    layoutParams2.gravity = 1;
                    this.payView.addView(this.llPayInfo, layoutParams2);
                    this.tvBtnPay4PB.setVisibility(0);
                }
                this.messageView.updateProgress();
                if (frameLayout2 != null) {
                    frameLayout2.removeAllViews();
                }
            } else {
                if (frameLayout2 != null) {
                    frameLayout2.removeAllViews();
                }
                if (this.wrapper != null) {
                    this.wrapper.removeAllConsumers();
                }
                this.smartSwipeRefresh.getSwipeConsumer().lockAllDirections();
                this.mLayoutManager.setScrollEnabled(false);
                int i = getResources().getDisplayMetrics().widthPixels;
                setRequestedOrientation(0);
                this.isHorizontal = true;
                setLiveCoverLandscape();
                this.messageView.setVisibility(8);
                this.toggleBack.setVisibility(8);
                this.v2Video.setVisibility(8);
                stopAnime();
                bdCloudVideoView.setVideoScalingMode(1);
                imageView.setVisibility(8);
                if (this.liveRoomUIInfo != null) {
                    this.landscapeView.hideChatRoomAndGiftByNet(this.liveRoomUIInfo);
                }
                this.landscapeView.initData(this.zhuboDataEntity, this.audienceUser, this.managerAudienceLoadData, !"Wifi".equals(DeviceHelper.getNETType(this.activityContext)), new C827915(findViewByPosition));
                this.landscapeView.setClickListener(this.clickVideo);
                ViewGroup viewGroup3 = (ViewGroup) this.landscapeView.getParent();
                if (viewGroup3 != null) {
                    viewGroup3.removeAllViews();
                }
                frameLayout2.addView(this.landscapeView);
                AudienceGuanzhuDialog.isLandscape = true;
                ShareManager.isLandscape = true;
                if (this.isMulti) {
                    UIUtils.logD("切换屏幕", "设置为横屏，处理多视角");
                    this.landscapeView.setMulti();
                    View findViewById2 = findViewByPosition.findViewById(2131297749);
                    findViewByPosition.findViewById(2131297748).setVisibility(0);
                    findViewByPosition.findViewById(2131297444).setVisibility(8);
                    findViewByPosition.findViewById(2131299019).setVisibility(8);
                    FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) findViewById2.getLayoutParams();
                    layoutParams3.width = (i * 16) / 9;
                    layoutParams3.gravity = 81;
                    ViewGroup viewGroup4 = (ViewGroup) findViewById2.getParent();
                    if (viewGroup4 != null) {
                        viewGroup4.removeAllViews();
                    }
                    frameLayout2.addView(findViewById2, layoutParams3);
                    if (this.angleAdapter != null) {
                        this.angleAdapter.notifyDataSetChanged();
                    }
                    UIUtils.logD("切换屏幕", "设置为横屏，处理多视角,处理结束");
                }
                if (this.llPayInfo != null) {
                    UIUtils.logD("切换屏幕", "设置为竖屏，处理付费提示");
                    ViewGroup viewGroup5 = (ViewGroup) this.llPayInfo.getParent();
                    if (viewGroup5 != null) {
                        viewGroup5.removeView(this.llPayInfo);
                    }
                    this.vTop1.setVisibility(8);
                    this.vTop2.setVisibility(8);
                    this.tvBtnPay4PB.setVisibility(8);
                    FrameLayout.LayoutParams layoutParams4 = (FrameLayout.LayoutParams) this.llPayInfo.getLayoutParams();
                    layoutParams4.gravity = 1;
                    frameLayout2.addView(this.llPayInfo, layoutParams4);
                }
                if (!App.getSharePreferenceUtil().getBoolean(AudienceLandscapeView.VOICE_LIGHT_CONTROL_COVER)) {
                    final View inflate = LayoutInflater.from(this.activityContext).inflate(2131493312, (ViewGroup) null);
                    inflate.findViewById(2131298334).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$AgMKoiXyhL44QCHiJRUOUC-s918
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            frameLayout2.removeView(inflate);
                        }
                    });
                    ViewParent parent = inflate.getParent();
                    if (parent != null) {
                        ((ViewGroup) parent).removeAllViews();
                    }
                    frameLayout2.addView(inflate);
                    App.getSharePreferenceUtil().putBoolean(AudienceLandscapeView.VOICE_LIGHT_CONTROL_COVER, true);
                }
            }
            if (!TextUtils.isEmpty(this.currentLogo)) {
                setLogo(this.currentLogo);
            }
            UIUtils.logD("切换屏幕", bdCloudVideoView.getVideoHeight() + "----w:" + bdCloudVideoView.getVideoWidth());
            ViewGroup viewGroup6 = (ViewGroup) bdCloudVideoView.getParent();
            if (viewGroup6 != null) {
                viewGroup6.removeAllViews();
            }
            if (this.isMulti) {
                if (getMultiSelectedCount() == 1) {
                    frameLayout.addView(bdCloudVideoView);
                    return;
                }
                UIUtils.logD("切换屏幕", "多视角播放加入布局，切换横竖屏");
                showMorePlayer(findViewByPosition, true);
                return;
            }
            frameLayout.addView(bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$15 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C827915 implements AudienceLandscapeView.LandscapeCallBack {
        final /* synthetic */ View val$view;

        C827915(View view) {
            AudienceActivity.this = r1;
            this.val$view = view;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public boolean isNeedPay() {
            return AudienceActivity.this.isNeedPay();
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void close() {
            AudienceActivity audienceActivity = AudienceActivity.this;
            audienceActivity.toggleFullScreen(audienceActivity.activityContext);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void showKeyboard() {
            try {
                AudienceActivity.this.alivcInputTextDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$15$YjMRuoXdkClPJZCbHXlqbu5fwco
                    @Override // com.sinovatech.unicom.separatemodule.audience.util.InputDialog.OnTextSendListener
                    public final void onTextSend(String str) {
                        AudienceActivity.C827915.lambda$showKeyboard$0(AudienceActivity.C827915.this, str);
                    }
                });
                AudienceActivity.this.alivcInputTextDialog.show();
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        public static /* synthetic */ void lambda$showKeyboard$0(C827915 c827915, String str) {
            if (AudienceActivity.this.audienceUser.isLogin()) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setMsg(str.replace("|*|", ""));
                if (str.contains("@") && str.contains("|*|")) {
                    liveMsg.setType("atto");
                    liveMsg.setAtto("all");
                } else {
                    liveMsg.setType("chat");
                }
                liveMsg.setLevel(AudienceActivity.this.audienceUser.getLevel());
                liveMsg.setNick(AudienceActivity.this.audienceUser.getNick());
                liveMsg.setMgr(AudienceActivity.this.audienceUser.isMgr());
                AudienceActivity.this.messageView.updateMessageAdapter(liveMsg);
                AudienceActivity.this.landscapeView.updateMessageAdapter(liveMsg);
                if (!str.contains("@") || !str.contains("|*|")) {
                    AudienceActivity.this.managerSocket.eventFayan(str);
                } else {
                    AudienceActivity.this.managerSocket.eventAtUser(liveMsg.getAtto(), liveMsg.getMsg());
                }
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
            try {
                AudienceActivity.bdCloudVideoView.stopPlayback();
                AudienceActivity.this.playUrl(liveUrlBean);
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void guanzhuStatus() {
            AudienceActivity.this.managerAudienceLoadData.isGuanzhu(AudienceActivity.userId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$15$xEEu30UHdvDZYwVJSt_U71fsSnk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.C827915.lambda$guanzhuStatus$1(AudienceActivity.C827915.this, (Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }

        public static /* synthetic */ void lambda$guanzhuStatus$1(C827915 c827915, Boolean bool) throws Exception {
            AudienceActivity.this.messageView.setGuanzhuView(bool.booleanValue(), AudienceActivity.this.zhuboDataEntity);
            AudienceActivity.this.landscapeView.setGuanzuGone(bool.booleanValue());
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void showMulti() {
            try {
                AudienceActivity.this.showAngle(this.val$view.findViewById(2131297749), true);
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }
    }

    private int getMultiSelectedCount() {
        int i;
        Exception e;
        try {
            if (this.angleAdapter != null) {
                i = 0;
                for (int i2 = 0; i2 < this.angleAdapter.getData().size(); i2++) {
                    try {
                        if (this.angleAdapter.getItem(i2).isSelection()) {
                            i++;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                        return i;
                    }
                }
                return i;
            }
            return 0;
        } catch (Exception e3) {
            i = 0;
            e = e3;
        }
    }

    public int getScreenOrientation(Context context) {
        Activity activity = (Activity) context;
        int rotation = activity.getWindowManager().getDefaultDisplay().getRotation();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        if (((rotation != 0 && rotation != 2) || i2 <= i) && ((rotation != 1 && rotation != 3) || i <= i2)) {
            switch (rotation) {
                case 0:
                default:
                    return 0;
                case 1:
                    return 1;
                case 2:
                    return 8;
                case 3:
                    return 9;
            }
        }
        switch (rotation) {
            case 0:
                return 1;
            case 1:
                return 0;
            case 2:
                return 9;
            case 3:
                return 8;
            default:
                return 1;
        }
    }

    private void addVideoView(View view) {
        UIUtils.logD("bdCloudVideoView-add", bdCloudVideoView.getVideoHeight() + "--0--w:" + bdCloudVideoView.getVideoWidth());
        FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296395);
        List<ListDataEntity> list = this.list;
        if (list != null) {
            int size = list.size();
            int i = this.mCurrentItem;
            if (size > i) {
                ListDataEntity listDataEntity = this.list.get(i);
                View findViewById = view.findViewById(2131296435);
                if (listDataEntity != null && "N".equals(listDataEntity.getLiving())) {
                    listDataEntity.setLiving("Y");
                }
                if (findViewById != null && findViewById.getVisibility() == 0) {
                    findViewById.setVisibility(8);
                }
            }
        }
        try {
            TextView textView = (TextView) view.findViewById(2131298899);
            if (textView != null) {
                textView.setVisibility(8);
                if (bdCloudVideoView.getVideoHeight() < bdCloudVideoView.getVideoWidth() && getScreenOrientation(this.activityContext) == 1) {
                    textView.setVisibility(0);
                    isFullView(textView);
                    this.smallVideoView.setCanShowFull(textView.getVisibility() == 0);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (bdCloudVideoView.getVideoHeight() < bdCloudVideoView.getVideoWidth()) {
                if (getScreenOrientation(this.activityContext) != 0) {
                    bdCloudVideoView.setVideoScalingMode(1);
                    frameLayout = (FrameLayout) view.findViewById(2131296396);
                    if (this.messageView != null) {
                        this.messageView.showToggleView(true, null);
                    }
                } else {
                    bdCloudVideoView.setVideoScalingMode(1);
                }
            } else {
                bdCloudVideoView.setVideoScalingMode(2);
                if (this.messageView != null) {
                    this.messageView.showToggleView(false, null);
                }
            }
            ViewGroup viewGroup = (ViewGroup) bdCloudVideoView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            frameLayout.addView(bdCloudVideoView);
            if (bdCloudVideoView.getVisibility() != 0) {
                bdCloudVideoView.setVisibility(0);
            }
        } catch (Exception e2) {
            MsLogUtil.m7979d("AudienceActivity", e2.getMessage());
        }
    }

    private void startAnime() {
        try {
            this.upTipImage.setVisibility(0);
            this.animation2 = AnimationUtils.loadAnimation(this.activityContext, 2130772011);
            this.upTipImage.startAnimation(this.animation2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopAnime() {
        try {
            if (this.animation2 != null) {
                this.animation2.cancel();
            }
            this.upTipImage.clearAnimation();
            this.upTipImage.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseVideo() {
        try {
            bdCloudVideoView.stopPlayback();
            bdCloudVideoView.release();
            bdCloudVideoView.setTag("");
            AudienceGoodsListDialogNew.getInstance().releaseDialog();
            ViewParent parent = bdCloudVideoView.getParent();
            if (parent instanceof FrameLayout) {
                ((FrameLayout) parent).removeView(bdCloudVideoView);
            }
            releaseAngleMorePlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void xiaBoStatus(boolean z) {
        try {
            MsLogUtil.m7979d("AudienceActivity", "xiaBoStatus.living=" + z);
            this.list.get(this.mCurrentItem).setLiving("N");
            this.isXiabo = true;
            releaseVideo();
            this.mAdapter.notifyItemChanged(this.mCurrentItem);
            MsLogUtil.m7979d("AudienceActivity", "准备进入自动切换mCurrentItem=" + this.mCurrentItem);
            autoNext(z);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void autoNext(boolean z) {
        try {
            UIUtils.toastCenter(z ? "您当前无权限访问，为您推荐其他直播" : "当前直播间已下播，为您推荐其他直播");
            int i = this.mCurrentItem + 1;
            if (this.autoNextLive == null || this.mAdapter.getItemCount() <= i) {
                return;
            }
            this.autoNextLive.removeCallbacksAndMessages(null);
            MsLogUtil.m7979d("AudienceActivity", "跳转至=" + i);
            this.autoNextLive.sendEmptyMessageDelayed(i, 800L);
        } catch (Exception unused) {
            MsLogUtil.m7979d("AudienceActivity", "自动切换直播间失败");
        }
    }

    public void releaseSocket() {
        this.managerSocket.onSocketStop();
    }

    private String getSwitchUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        if (liveUrlBean == null) {
            return "";
        }
        return !"Wifi".equals(DeviceHelper.getNETType(this.activityContext)) ? liveUrlBean.getLiveFreePullUrlByFlv() : liveUrlBean.getLivePullUrlByFlv();
    }

    private void upKadunLog(LogFlag logFlag) {
        switch (logFlag) {
            case STARTERROR:
                ZhuboDataEntity zhuboDataEntity = this.zhuboDataEntity;
                if (zhuboDataEntity != null) {
                    this.managerAudienceLoadData.getKaduanLog("33", zhuboDataEntity.getAnchorInfoBean().getUserId(), "1", getSwitchUrl(this.currentLiveUrl), this.isMulti ? "2" : "");
                    return;
                }
                return;
            case PLAYINGBUFFER:
                ZhuboDataEntity zhuboDataEntity2 = this.zhuboDataEntity;
                if (zhuboDataEntity2 != null) {
                    this.managerAudienceLoadData.getKaduanLog("33", zhuboDataEntity2.getAnchorInfoBean().getUserId(), "2", getSwitchUrl(this.currentLiveUrl), this.isMulti ? "2" : "");
                    return;
                }
                return;
            case STREAMERROR:
                ZhuboDataEntity zhuboDataEntity3 = this.zhuboDataEntity;
                if (zhuboDataEntity3 != null) {
                    this.managerAudienceLoadData.getKaduanLog("33", zhuboDataEntity3.getAnchorInfoBean().getUserId(), "3", getSwitchUrl(this.currentLiveUrl), this.isMulti ? "2" : "");
                    return;
                }
                return;
            case TIME:
                ZhuboDataEntity zhuboDataEntity4 = this.zhuboDataEntity;
                if (zhuboDataEntity4 != null) {
                    ManagerAudienceLoadData managerAudienceLoadData = this.managerAudienceLoadData;
                    String userId2 = zhuboDataEntity4.getAnchorInfoBean().getUserId();
                    managerAudienceLoadData.getKaduanLog("34", userId2, this.bufferTime + "", getSwitchUrl(this.currentLiveUrl), this.isMulti ? "2" : "");
                    return;
                }
                return;
            case HOMEINFO:
                ZhuboDataEntity zhuboDataEntity5 = this.zhuboDataEntity;
                if (zhuboDataEntity5 != null) {
                    StatisticsUploadUtils.uploadRealTime2(this.activityContext, "szhibo0001", "直播LIVE", "03", zhuboDataEntity5.getAnchorInfoBean().getUserId(), this.zhuboDataEntity.getAnchorInfoBean().getUserName(), getSwitchUrl(this.currentLiveUrl), this.liveChannel, "");
                    return;
                }
                return;
            default:
                return;
        }
    }

    private void getUseFulChat(String str) {
        this.managerAudienceLoadData.getUsefulChatList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$9tygNNOuzz8RHsYaLTEXPSgAFc4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getUseFulChat$83(AudienceActivity.this, (UsefulChatEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getUseFulChat$83(AudienceActivity audienceActivity, UsefulChatEntity usefulChatEntity) throws Exception {
        if (usefulChatEntity == null || usefulChatEntity.getData() == null) {
            return;
        }
        audienceActivity.alivcInputTextDialog.setList(usefulChatEntity.getData());
    }

    public void setAtUser(String str, String str2) {
        if (this.audienceUser.isMgr()) {
            this.alivcInputTextDialog.setAtUser(str, str2);
            this.alivcInputTextDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$1wyVa7ZuXyZyrhTnQVSxRZzA3Bg
                @Override // com.sinovatech.unicom.separatemodule.audience.util.InputDialog.OnTextSendListener
                public final void onTextSend(String str3) {
                    AudienceActivity.lambda$setAtUser$84(AudienceActivity.this, str3);
                }
            });
            this.alivcInputTextDialog.show();
        }
    }

    public static /* synthetic */ void lambda$setAtUser$84(AudienceActivity audienceActivity, String str) {
        try {
            if (audienceActivity.audienceUser.isLogin()) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setMsg(str.replace("|*|", ""));
                if (str.contains("@") && str.contains("|*|")) {
                    liveMsg.setType("atto");
                    liveMsg.setAtto("all");
                } else {
                    liveMsg.setType("chat");
                }
                liveMsg.setLevel(audienceActivity.audienceUser.getLevel());
                liveMsg.setNick(audienceActivity.audienceUser.getNick());
                liveMsg.setMgr(audienceActivity.audienceUser.isMgr());
                audienceActivity.messageView.updateMessageAdapter(liveMsg);
                if (str.contains("@") && str.contains("|*|")) {
                    audienceActivity.managerSocket.eventAtUser(liveMsg.getAtto(), liveMsg.getMsg());
                } else {
                    audienceActivity.managerSocket.eventFayan(str);
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void recevierNet(NetEvent netEvent) {
        try {
            if (this.messageView != null) {
                this.messageView.checkWifi();
            }
            playUrl(this.currentLiveUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getLiveList() {
        Gson gson = new Gson();
        List<ListDataEntity> list = this.list;
        return !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list);
    }

    public void returnIndex() {
        Intent intent = new Intent();
        intent.putExtra("indexForLive", this.fromType.equals(f18464JS) ? this.mItemFormMain : this.mCurrentItem);
        if ("fromMain".equals(this.fromType)) {
            intent.putExtra("liveList", getLiveList());
        }
        if (!TextUtils.isEmpty(this.playBackPageNum)) {
            intent.putExtra(MultiViewLiveFragment.PAGE_NUM_PLAY_BACK, this.playBackPageNum);
        }
        setResult(2001, intent);
    }

    public void clickGuanzhu() {
        this.landscapeView.setGuanzuGone(true);
    }

    private boolean isCurrentRoom(String str) {
        String jobNumber = this.list.get(this.mCurrentItem).getJobNumber();
        UIUtils.logD("fufeizhibo", "roomId=" + str + "|currentRoomId=" + jobNumber);
        return str.equals(jobNumber);
    }

    public void getSharpnessInfo(final String str, final View view, final String str2, int i) {
        ListDataEntity listDataEntity = this.list.get(i);
        if (listDataEntity != null && listDataEntity.getDataType() != null && "3".equals(listDataEntity.getDataType())) {
            getPlayBackVideoInfo(i);
        } else {
            this.managerAudienceLoadData.getSharpnessInfo(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$9fnCOl0nZpdhSqazyjcexbEEXS4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.this.handleSharpnessInfo((SharpnessEntity) obj, str, view, str2);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$J2lS-euOjJx1XH5SQCiOKkM6zqo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MsLogUtil.m7980d(((Throwable) obj).getMessage());
                }
            });
        }
    }

    public void handleSharpnessInfo(SharpnessEntity sharpnessEntity, String str, View view, String str2) {
        try {
            if ("0000".equals(sharpnessEntity.getStatusCode()) && isCurrentRoom(str)) {
                clearPayInfo();
                if (!TextUtils.isEmpty(sharpnessEntity.getPromptText())) {
                    new ToastDialogManager(this).show(sharpnessEntity.getPromptText());
                }
                this.payInfo4PB = sharpnessEntity.getPbPayInfo();
                if (this.payInfo4PB == null) {
                    handlePayInfo(sharpnessEntity, str);
                } else {
                    handlePBPayInfo(this.payInfo4PB);
                }
                if (TextUtils.isEmpty(sharpnessEntity.getLogo())) {
                    this.ivLogo.setVisibility(8);
                } else {
                    setLogo(sharpnessEntity.getLogo());
                }
                this.isMulti = "Y".equals(sharpnessEntity.getLiveViewAngleMore());
                if (this.messageView != null) {
                    this.messageView.setMultiView(this.isMulti);
                }
                if (this.isMulti) {
                    UIUtils.logD("多视角测试", "进入多视角");
                    sharpnessEntity.getLiveViewAngleMoreList().get(0).setSelection(true);
                    sharpnessEntity.getLiveViewAngleMoreList().get(0).setCover(str2);
                    setAngleMore(sharpnessEntity.getLiveViewAngleMoreList());
                    this.selectedPlayer.clear();
                    this.selectedPlayer.add(this.players.get(0));
                    if (UIUtils.isFoldScreen(this.activityContext) && getScreenOrientation(this.activityContext) == 0) {
                        toggleFullScreen(this.activityContext);
                    }
                    if (this.isMulti && !UIUtils.isFoldScreen(this.activityContext) && !App.getSharePreferenceUtil().getBoolean("mulitview_details_masking_flag")) {
                        View inflate = LayoutInflater.from(this.activityContext).inflate(2131493051, (ViewGroup) null);
                        View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
                        if (findViewByPosition != null) {
                            final FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296996);
                            frameLayout.setVisibility(0);
                            if (((ViewGroup) frameLayout.getParent()) != null) {
                                frameLayout.removeAllViews();
                            }
                            frameLayout.addView(inflate);
                            ((RelativeLayout) inflate.findViewById(2131297747)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$NTIgSk3R5eqIvGowq1Pg6Dr77fw
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    frameLayout.setVisibility(8);
                                }
                            });
                            App.getSharePreferenceUtil().putBoolean("mulitview_details_masking_flag", true);
                        }
                    }
                }
                this.liveUrlList = sharpnessEntity.getData();
                setPlayDate(view);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void handlePayInfo(SharpnessEntity sharpnessEntity, final String str) {
        try {
            if ("Y".equals(sharpnessEntity.getPaidLive()) && !"Y".equals(sharpnessEntity.getPayingUser())) {
                this.buyUrl = sharpnessEntity.getPaidLinks();
                this.tips = sharpnessEntity.getPaidAd();
                this.isPB = false;
                initPayInfo();
                this.llPayInfo.setVisibility(8);
                this.llPayTipe.setVisibility(8);
                this.ivPlayIcon.setVisibility(8);
                this.llTrySeeDialog.setVisibility(8);
                this.tvBtnPay4PB.setVisibility(8);
                showPayViewTop();
                try {
                    this.timePayInfo = Integer.valueOf(sharpnessEntity.getFreeTime()).intValue();
                } catch (Exception unused) {
                }
                if (this.timePayInfo < 10) {
                    showPayInfo();
                } else {
                    this.llPayInfo.setVisibility(0);
                    this.llPayTipe.setVisibility(0);
                    portrait4PB();
                    TextView textView = this.tvFreeTimeTips;
                    textView.setText("可试看" + this.timePayInfo + "秒，订购权益包后可观看全部内容");
                    this.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$KlPANFTAL2G4L3Nmmnbup2JEn4o
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$handlePayInfo$88(AudienceActivity.this, str, (Long) obj);
                        }
                    });
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$handlePayInfo$88(AudienceActivity audienceActivity, String str, Long l) throws Exception {
        if (audienceActivity.isRunning) {
            audienceActivity.timePayInfo--;
            if (audienceActivity.timePayInfo % 10 == 0) {
                audienceActivity.consumeFreeTime(str);
            }
            if (audienceActivity.timePayInfo == 0) {
                audienceActivity.showPayInfo();
                return;
            }
            audienceActivity.tvFreeTimeTips.setText("可试看" + audienceActivity.timePayInfo + "秒，订购权益包后可观看全部内容");
            return;
        }
        UIUtils.logD("AudienceActivity", "暂停倒计时" + audienceActivity.timePayInfo);
    }

    public void setPlayDate(View view) {
        if (view == null) {
            try {
                view = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.messageView.setLiveUrlList(this.liveUrlList);
        this.landscapeView.setLiveUrlList(this.liveUrlList);
        if (this.liveUrlList == null || this.liveUrlList.size() <= 0) {
            return;
        }
        if (this.liveUrlList.size() > 1) {
            setPlayerUrl(this.liveUrlList.get(1), view);
        } else {
            setPlayerUrl(this.liveUrlList.get(0), view);
        }
    }

    private void consumeFreeTime(String str) {
        this.managerAudienceLoadData.consumeFreeTime(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$oneLTbxT0SJR4R-LbTZ7DLWEewo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("", ((String) obj) + "===========");
            }
        });
    }

    private void clearPayInfo() {
        try {
            this.isNeedPay = false;
            this.isPB = false;
            this.payInfo4PB = null;
            if (this.payInfoCountDown != null) {
                this.payInfoCountDown.dispose();
                this.payInfoCountDown = null;
            }
            this.timePayInfo = 0;
            if (this.llPayInfo != null) {
                this.llPayInfo.setVisibility(8);
                this.tvBtnPay4PB.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public boolean isNeedPay() {
        if (this.isNeedPay) {
            if (this.isPB) {
                showPBPayInfo();
            } else {
                showPayInfo();
            }
        }
        return this.isNeedPay;
    }

    private void showPayInfo() {
        try {
            clearPayInfo();
            this.isNeedPay = true;
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(8);
            this.llTrySeeDialog.setVisibility(0);
            bdCloudVideoView.stopPlayback();
            if (!this.isMulti || this.players == null) {
                return;
            }
            for (BDCloudVideoView bDCloudVideoView : this.players) {
                if (bDCloudVideoView != null) {
                    bDCloudVideoView.stopPlayback();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void dismissPayInfo() {
        try {
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(8);
            this.llTrySeeDialog.setVisibility(8);
            this.ivPlayIcon.setVisibility(0);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void showPayViewTop() {
        try {
            int i = 0;
            boolean z = getScreenOrientation(this.activityContext) != 0;
            UIUtils.logD("AudienceActivity", "处理付费弹窗(屏幕切换时）");
            this.vTop1.setVisibility(z ? 0 : 8);
            View view = this.vTop2;
            if (!z) {
                i = 8;
            }
            view.setVisibility(i);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void initPayInfo() {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            this.llPayInfo = (LinearLayout) findViewByPosition.findViewById(2131297753);
            this.payView = (FrameLayout) findViewByPosition.findViewById(2131296998);
            this.vTop1 = findViewByPosition.findViewById(2131299530);
            this.vTop2 = findViewByPosition.findViewById(2131299531);
            this.tvFreeTimeTips = (TextView) findViewByPosition.findViewById(2131298945);
            this.ivPlayIcon = (ImageView) findViewByPosition.findViewById(2131297450);
            this.llTrySeeDialog = (LinearLayout) findViewByPosition.findViewById(2131297789);
            this.tvPayTips = (TextView) findViewByPosition.findViewById(2131299043);
            this.tvPayTips.setText(this.tips);
            this.tvNoOrder = (TextView) findViewByPosition.findViewById(2131299028);
            this.tvOrder = (TextView) findViewByPosition.findViewById(2131299036);
            this.ivPlayIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$Rx1h7JP--kz9CVvYuS4arRVpyXM
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.lambda$initPayInfo$90(AudienceActivity.this, view);
                }
            });
            this.tvNoOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$PAkGAcc2MfEfTbQZXFLS17HCXuo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.this.dismissPayInfo();
                }
            });
            this.tvOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$hXqj2YpFZnfXSbjeZiKMSlq6ncE
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.lambda$initPayInfo$92(AudienceActivity.this, view);
                }
            });
            this.llPayTipe = (LinearLayout) findViewByPosition.findViewById(2131297754);
            this.tvBtnPay4PB = (TextView) findViewByPosition.findViewById(2131299041);
            this.llPayTipe.setVisibility(8);
            this.tvBtnPay4PB.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$txNEfxGOlmTi6wEJOo1OsVwpzCs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.lambda$initPayInfo$93(AudienceActivity.this, view);
                }
            });
            this.llPayInfo.setVisibility(8);
            this.llPayTipe.setVisibility(8);
            this.ivPlayIcon.setVisibility(8);
            this.llTrySeeDialog.setVisibility(8);
            this.tvBtnPay4PB.setVisibility(8);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initPayInfo$90(AudienceActivity audienceActivity, View view) {
        if (!audienceActivity.isPB) {
            audienceActivity.showPayInfo();
        } else {
            audienceActivity.pbReplay();
        }
    }

    public static /* synthetic */ void lambda$initPayInfo$92(AudienceActivity audienceActivity, View view) {
        Intent intent = new Intent(audienceActivity.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(audienceActivity.buyUrl);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        audienceActivity.startActivityForResult(intent, 4111);
    }

    public static /* synthetic */ void lambda$initPayInfo$93(AudienceActivity audienceActivity, View view) {
        if (audienceActivity.llTrySeeDialog.getVisibility() == 8) {
            Intent intent = new Intent(audienceActivity.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(audienceActivity.buyUrl);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            audienceActivity.startActivityForResult(intent, 4111);
        }
    }

    private void handlePBPayInfo(final PlayBackPayInfoEntity playBackPayInfoEntity) {
        try {
            if (!TextUtils.isEmpty(playBackPayInfoEntity.getPromptText())) {
                UIUtils.toastCenter(playBackPayInfoEntity.getPromptText());
            }
            if ("Y".equals(playBackPayInfoEntity.getPaidLiveBackPlay()) && !"Y".equals(playBackPayInfoEntity.getUserIsPaid())) {
                this.buyUrl = playBackPayInfoEntity.getPayUrl();
                this.tips = playBackPayInfoEntity.getPaidAd();
                this.isPB = true;
                initPayInfo();
                showPayViewTop();
                if ("Y".equals(playBackPayInfoEntity.getTryLook())) {
                    this.isNeedPay = false;
                    TextView textView = this.tvFreeTimeTips;
                    textView.setText("可试看" + playBackPayInfoEntity.getFreeTime() + "秒，订购权益包后可观看全部内容");
                    this.tvPayTips.setText(this.tips);
                    this.llPayInfo.setVisibility(0);
                    this.llPayTipe.setVisibility(0);
                    portrait4PB();
                    this.payInfoCountDown = Observable.interval(0L, 500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$cY3lGRjSsNTaJm7Vt2G66KHb4_0
                        @Override // io.reactivex.functions.Function
                        public final Object apply(Object obj) {
                            return AudienceActivity.lambda$handlePBPayInfo$94(PlayBackPayInfoEntity.this, (Long) obj);
                        }
                    }).observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$EmikfsRqgStzvSrUZoqs2frSszs
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$handlePBPayInfo$95(AudienceActivity.this, (Boolean) obj);
                        }
                    });
                    return;
                }
                this.tvFreeTimeTips.setText("订购权益包后可观看全部内容");
                showPBPayInfo();
                return;
            }
            this.isNeedPay = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ Boolean lambda$handlePBPayInfo$94(PlayBackPayInfoEntity playBackPayInfoEntity, Long l) throws Exception {
        BDCloudVideoView bDCloudVideoView = bdCloudVideoView;
        boolean z = false;
        if (bDCloudVideoView != null && bDCloudVideoView.getCurrentPosition() >= playBackPayInfoEntity.getFreeTime4MillisSeconds()) {
            z = true;
        }
        return Boolean.valueOf(z);
    }

    public static /* synthetic */ void lambda$handlePBPayInfo$95(AudienceActivity audienceActivity, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            audienceActivity.payInfoCountDown.dispose();
            audienceActivity.showPBPayInfo();
        } else if (audienceActivity.isNeedPay) {
            audienceActivity.isNeedPay = false;
        }
    }

    private void portrait4PB() {
        try {
            this.tvBtnPay4PB.setVisibility(getScreenOrientation(this.activityContext) == 0 ? 8 : 0);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void doNotTry() {
        try {
            if (bdCloudVideoView != null) {
                if (bdCloudVideoView.isPlaying() || !this.isNeedPay) {
                    showPBPayInfo();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void seekToPlay(int i) {
        try {
            MsLogUtil.m7979d("AudienceActivity", "seekToPlay=" + i);
            if (this.payInfo4PB != null) {
                handlePBPayInfo(this.payInfo4PB);
                if (i < this.payInfo4PB.getFreeTime4MillisSeconds() && !this.isNeedPay) {
                    bdCloudVideoView.start();
                    if (this.landscapeView != null) {
                        this.landscapeView.setPlayBtn(true);
                        return;
                    }
                    return;
                } else if (this.isNeedPay) {
                    bdCloudVideoView.pause();
                    if (this.messageView != null) {
                        this.messageView.setPlayBtn(false);
                    }
                    if (this.landscapeView != null) {
                        this.landscapeView.setPlayBtn(false);
                        return;
                    }
                    return;
                } else {
                    return;
                }
            }
            bdCloudVideoView.start();
            if (this.messageView != null) {
                this.messageView.setPlayBtn(true);
            }
            if (this.landscapeView != null) {
                this.landscapeView.setPlayBtn(true);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void pbReplay() {
        try {
            if (bdCloudVideoView != null) {
                bdCloudVideoView.seekTo(0);
                seekToPlay(0);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void showPBPayInfo() {
        try {
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(0);
            portrait4PB();
            this.llTrySeeDialog.setVisibility(0);
            this.isNeedPay = true;
            bdCloudVideoView.pause();
            if (this.messageView != null) {
                this.messageView.setPlayBtn(false);
            }
            if (this.landscapeView != null) {
                this.landscapeView.setPlayBtn(false);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dissmissMoreLive() {
        try {
            if (this.consumer != null) {
                this.consumer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initMoreLive() {
        try {
            LinearLayout linearLayout = (LinearLayout) this.activityContext.getLayoutInflater().inflate(2131493000, (ViewGroup) null);
            linearLayout.findViewById(2131297745).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$WOprds0y1vsiYgm8DOZt0DJW8Do
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UIUtils.logD("xcy", "拦截点击透传");
                }
            });
            final String str = URLSet.getLiveSearch() + "&source_page=2";
            TextView textView = (TextView) linearLayout.findViewById(2131299072);
            if (!TextUtils.isEmpty(str) && OptionValveUtil.INSTENCE.isShowSearchBtn()) {
                textView.setVisibility(0);
                PvCurrencyLogUtils.pvLogLive("", 3, "", "搜索", "更多直播列表", "", "搜索");
            } else {
                textView.setVisibility(8);
            }
            textView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$mD7ou75xCJU-mr3-XEQlEsPjVr8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.lambda$initMoreLive$97(AudienceActivity.this, str, view);
                }
            });
            this.tabLive = (TextView) linearLayout.findViewById(2131299096);
            this.tabAdvance = (TextView) linearLayout.findViewById(2131299094);
            this.tabPlayback = (TextView) linearLayout.findViewById(2131299097);
            this.tabFengguang = (TextView) linearLayout.findViewById(2131299095);
            this.tabLive.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$IH8yTttftlnOiZtah7hxxOMarxs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.this.setLiveMore();
                }
            });
            this.tabAdvance.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$wAlSEOo0g2H2vt04z75vVTMHHMw
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.this.setYuGao();
                }
            });
            this.tabPlayback.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$0JGEkNABqPayxZLER_LZ2WKOxAk
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.this.setHuiFang();
                }
            });
            this.tabFengguang.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$30C03TlA_z5KWdtEnH_lC9OlECA
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceActivity.this.setFengGuang();
                }
            });
            this.vpBanner = (BannerViewPager) linearLayout.findViewById(2131299542);
            getBannerList();
            this.rlvContent = (RecyclerView) linearLayout.findViewById(2131298400);
            this.rlvContent.setLayoutManager(new GridLayoutManager(this.activityContext, 2));
            getLivingList("6", this.fengGuangPN);
            getYuGaoList(this.yugaoPN);
            linearLayout.setLayoutParams(new ViewGroup.LayoutParams(SmartSwipe.dp2px(330, this), -1));
            this.sideslipLayout = linearLayout;
            this.consumer = new DrawerConsumer();
            this.f27860group = new SwipeConsumerExclusiveGroup();
            this.f27860group.setLockOther(true);
            this.f27860group.add(this.consumer);
            addSideslip(this.mRecyclerView);
            this.messageView.setConsumer(this.consumer);
            this.liveAdapter = new BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder>(2131493204) { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.18
                {
                    AudienceActivity.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem) {
                    baseViewHolder.setText(2131298939, FormatUtils.getShowString(liveInfoItem.getVideoNum())).setGone(2131298939, true);
                    baseViewHolder.setText(2131298863, liveInfoItem.getUserNickName());
                    baseViewHolder.setGone(2131298990, false);
                    baseViewHolder.setGone(2131299153, false);
                    baseViewHolder.setGone(2131298939, true);
                    baseViewHolder.setGone(2131297402, true);
                    baseViewHolder.setText(2131298979, liveInfoItem.getVideoTitle());
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getUserHeadImg()).placeholder(2131232746).error(2131232746).into((CircularImage) baseViewHolder.getView(2131297635));
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getVideoBgImg()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231335).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)))).placeholder(2131232747).error(2131232747).into((ImageView) baseViewHolder.getView(2131297366));
                    ((TextView) baseViewHolder.getView(2131299120)).setText("Y".equals(liveInfoItem.getMoreViewAngle()) ? "多视角" : "直播中");
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load((Integer) 2131232744).into((ImageView) baseViewHolder.getView(2131297516));
                    baseViewHolder.getView(2131297792).setBackground(AudienceActivity.this.activityContext.getDrawable(2131231805));
                    baseViewHolder.addOnClickListener(2131299153);
                    baseViewHolder.addOnClickListener(2131297635);
                    baseViewHolder.addOnClickListener(2131298308);
                }
            };
            this.yugaoAdapter = new BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder>(2131493204) { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.19
                {
                    AudienceActivity.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem) {
                    baseViewHolder.setText(2131298863, liveInfoItem.getUserNickName());
                    baseViewHolder.setText(2131298979, liveInfoItem.getVideoTitle());
                    baseViewHolder.setGone(2131298990, true);
                    baseViewHolder.setGone(2131299153, true);
                    baseViewHolder.setGone(2131298939, false);
                    baseViewHolder.setGone(2131297402, false);
                    if ("0".equals(liveInfoItem.getVideoCloseState())) {
                        baseViewHolder.setText(2131299153, "预约");
                        baseViewHolder.setBackgroundRes(2131299153, 2131230969);
                    } else if ("1".equals(liveInfoItem.getVideoCloseState())) {
                        baseViewHolder.setText(2131299153, "已预约");
                        baseViewHolder.setBackgroundRes(2131299153, 2131230968);
                    } else if ("3".equals(liveInfoItem.getVideoCloseState())) {
                        baseViewHolder.setText(2131299153, "预约");
                        baseViewHolder.setBackgroundRes(2131299153, 2131230969);
                    }
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getUserHeadImg()).placeholder(2131232746).error(2131232746).into((CircularImage) baseViewHolder.getView(2131297635));
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getVideoBgImg()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231335).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)))).placeholder(2131232747).error(2131232747).into((ImageView) baseViewHolder.getView(2131297366));
                    baseViewHolder.setGone(2131298939, false);
                    baseViewHolder.setText(2131298990, LivePvInfoEntity.switchdate(liveInfoItem.getVideoTime()) + " 开播");
                    ((TextView) baseViewHolder.getView(2131299120)).setText("直播预告");
                    baseViewHolder.setImageResource(2131297516, 2131232539);
                    baseViewHolder.getView(2131297792).setBackground(AudienceActivity.this.activityContext.getDrawable(2131232540));
                    baseViewHolder.addOnClickListener(2131297635);
                    baseViewHolder.addOnClickListener(2131298308);
                    baseViewHolder.addOnClickListener(2131299153);
                }
            };
            this.huifangAdapter = new BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder>(2131493204) { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.20
                {
                    AudienceActivity.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem) {
                    baseViewHolder.setText(2131298863, liveInfoItem.getUserNickName());
                    baseViewHolder.setText(2131298979, liveInfoItem.getVideoTitle());
                    baseViewHolder.setGone(2131298990, false);
                    baseViewHolder.setGone(2131299153, false);
                    baseViewHolder.setGone(2131298939, true);
                    baseViewHolder.setGone(2131297402, true);
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getUserHeadImg()).placeholder(2131232746).error(2131232746).into((CircularImage) baseViewHolder.getView(2131297635));
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getVideoBgImg()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231335).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)))).placeholder(2131232747).error(2131232747).into((ImageView) baseViewHolder.getView(2131297366));
                    baseViewHolder.setGone(2131298939, true);
                    baseViewHolder.setText(2131298939, FormatUtils.getShowString(liveInfoItem.getVideoNum()));
                    ((TextView) baseViewHolder.getView(2131299120)).setText("Y".equals(liveInfoItem.getMoreViewAngle()) ? "多视角回放" : "精彩回放");
                    baseViewHolder.setImageResource(2131297516, 2131232742);
                    baseViewHolder.getView(2131297792).setBackground(AudienceActivity.this.activityContext.getDrawable(2131232538));
                    baseViewHolder.addOnClickListener(2131299153);
                    baseViewHolder.addOnClickListener(2131297635);
                    baseViewHolder.addOnClickListener(2131298308);
                }
            };
            this.xuannifengguangAdapter = new BaseQuickAdapter<LiveOrFengGuangInfoEntity.LiveInfoItem, BaseViewHolder>(2131493204) { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.21
                {
                    AudienceActivity.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem) {
                    baseViewHolder.setText(2131298939, FormatUtils.getShowString(liveInfoItem.getVideoNum())).setGone(2131298939, true);
                    baseViewHolder.setText(2131298863, liveInfoItem.getUserNickName());
                    baseViewHolder.setText(2131298979, liveInfoItem.getVideoTitle());
                    baseViewHolder.setGone(2131298990, false);
                    baseViewHolder.setGone(2131299153, false);
                    baseViewHolder.setGone(2131298939, true);
                    baseViewHolder.setGone(2131297402, true);
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getUserHeadImg()).placeholder(2131232746).error(2131232746).into((CircularImage) baseViewHolder.getView(2131297635));
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load(liveInfoItem.getVideoBgImg()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231335).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)))).placeholder(2131232747).error(2131232747).into((ImageView) baseViewHolder.getView(2131297366));
                    ((TextView) baseViewHolder.getView(2131299120)).setText("直播中");
                    Glide.with((FragmentActivity) AudienceActivity.this.activityContext).load((Integer) 2131232744).into((ImageView) baseViewHolder.getView(2131297516));
                    baseViewHolder.getView(2131297792).setBackground(AudienceActivity.this.activityContext.getDrawable(2131231805));
                    baseViewHolder.addOnClickListener(2131299153);
                    baseViewHolder.addOnClickListener(2131297635);
                    baseViewHolder.addOnClickListener(2131298308);
                }
            };
            this.liveAdapter.setLoadMoreView(new LiveSimpleLoadMoreView());
            this.yugaoAdapter.setLoadMoreView(new LiveSimpleLoadMoreView());
            this.huifangAdapter.setLoadMoreView(new LiveSimpleLoadMoreView());
            this.xuannifengguangAdapter.setLoadMoreView(new LiveSimpleLoadMoreView());
            this.yugaoAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$1mZnJS2fPNsc3sU-eo6_DS3nXZw
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    AudienceActivity.lambda$initMoreLive$103(AudienceActivity.this, baseQuickAdapter, view, i);
                }
            });
            this.liveAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$2lGh9BvfS5hG_Kv-D49xcXcO-I4
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    AudienceActivity.lambda$initMoreLive$104(AudienceActivity.this, baseQuickAdapter, view, i);
                }
            });
            this.huifangAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$H6fALS2sYbGDlGC8_ZbOW63gA0w
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    AudienceActivity.lambda$initMoreLive$105(AudienceActivity.this, baseQuickAdapter, view, i);
                }
            });
            this.xuannifengguangAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$rwsB0C1Y0rEWFBz2UM04aTx0nS8
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    AudienceActivity.lambda$initMoreLive$106(AudienceActivity.this, baseQuickAdapter, view, i);
                }
            });
            this.liveAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$4qeCH6_KASO2iVKDo4MTXNEMQjg
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
                public final void onLoadMoreRequested() {
                    AudienceActivity.lambda$initMoreLive$107(AudienceActivity.this);
                }
            }, this.rlvContent);
            this.yugaoAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$B-quAohVRdsoMyjjE2I7Es8uLn8
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
                public final void onLoadMoreRequested() {
                    AudienceActivity.lambda$initMoreLive$108(AudienceActivity.this);
                }
            }, this.rlvContent);
            this.huifangAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$pKgRqLGCUB17cAD4sQAtY1r0J2o
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
                public final void onLoadMoreRequested() {
                    AudienceActivity.lambda$initMoreLive$109(AudienceActivity.this);
                }
            }, this.rlvContent);
            this.xuannifengguangAdapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$bAlTzfTSa5JHM_Ehr4tC2PzhIEU
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
                public final void onLoadMoreRequested() {
                    AudienceActivity.lambda$initMoreLive$110(AudienceActivity.this);
                }
            }, this.rlvContent);
            View inflate = LayoutInflater.from(this.activityContext).inflate(2131493319, (ViewGroup) null);
            Glide.with((FragmentActivity) this.activityContext).load((Integer) 2131231637).into((ImageView) inflate.findViewById(2131297398));
            ((TextView) inflate.findViewById(2131298932)).setText("暂无主播进行直播，可查看预告列表");
            TextView textView2 = (TextView) inflate.findViewById(2131299072);
            textView2.setVisibility(0);
            textView2.setText("去查看");
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.22
                {
                    AudienceActivity.this = this;
                }

                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    AudienceActivity.this.setYuGao();
                    PvCurrencyLogUtils.pvLogLive("", 2, "", "搜索", "更多直播列表页", "", "搜索");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.liveAdapter.setEmptyView(inflate);
            View inflate2 = LayoutInflater.from(this.activityContext).inflate(2131493319, (ViewGroup) null);
            Glide.with((FragmentActivity) this.activityContext).load((Integer) 2131231636).into((ImageView) inflate2.findViewById(2131297398));
            ((TextView) inflate2.findViewById(2131298932)).setText("主播暂无回放视频");
            this.huifangAdapter.setEmptyView(inflate2);
            View inflate3 = LayoutInflater.from(this.activityContext).inflate(2131493319, (ViewGroup) null);
            Glide.with((FragmentActivity) this.activityContext).load((Integer) 2131231638).into((ImageView) inflate3.findViewById(2131297398));
            ((TextView) inflate3.findViewById(2131298932)).setText("暂无预告，快去观看其他视频吧");
            this.yugaoAdapter.setEmptyView(inflate3);
            View inflate4 = LayoutInflater.from(this.activityContext).inflate(2131493319, (ViewGroup) null);
            Glide.with((FragmentActivity) this.activityContext).load((Integer) 2131231635).into((ImageView) inflate4.findViewById(2131297398));
            ((TextView) inflate4.findViewById(2131298932)).setText("暂无内容，快去观看其他视频吧");
            this.xuannifengguangAdapter.setEmptyView(inflate4);
            qiehuanInit();
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$97(AudienceActivity audienceActivity, String str, View view) {
        audienceActivity.IntentGoWithOutVideo(str);
        PvCurrencyLogUtils.pvLogLive("", 2, "", "搜索", "更多直播列表", "", "搜索");
    }

    public static /* synthetic */ void lambda$initMoreLive$103(AudienceActivity audienceActivity, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        try {
            int id = view.getId();
            if (id == 2131297635) {
                String userLinkUrl = audienceActivity.yugaoAdapter.getData().get(i).getUserLinkUrl();
                if (TextUtils.isEmpty(userLinkUrl)) {
                    return;
                }
                IntentGo(audienceActivity.activityContext, audienceActivity.addChannel(userLinkUrl, "直播"));
            } else if (id == 2131299153) {
                if (audienceActivity.yugaoAdapter.getData().size() > 0 && !"1".equals(audienceActivity.yugaoAdapter.getData().get(i).getVideoCloseState())) {
                    audienceActivity.managerAudienceLoadData.reserveLivePv("1", audienceActivity.yugaoAdapter.getData().get(i).getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$pYp6Bj8t17Uvrp59XzZnJG9Kgbk
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceActivity.lambda$initMoreLive$102(AudienceActivity.this, i, (LivePvInfoEntity) obj);
                        }
                    });
                }
                PvCurrencyLogUtils.pvLogLive("", 2, "", "预约键位", "更多直播列表页", "", "直播预告");
            } else {
                audienceActivity.IntentGoWithOutVideo(URLSet.getFuYiPingYuGaoUrl(audienceActivity.yugaoAdapter.getData().get(i).getUserId()));
                PvCurrencyLogUtils.pvLogLive(audienceActivity.yugaoAdapter.getData().get(i).getVideoLinkUrl(), 2, audienceActivity.yugaoAdapter.getData().get(i).getUserNickName(), "预告卡片", "更多直播列表页", audienceActivity.yugaoAdapter.getData().get(i).getVideoTitle(), "直播预告");
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$102(AudienceActivity audienceActivity, int i, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            UIUtils.showDialog(audienceActivity.activityContext, new LivePvResultDialog(audienceActivity.activityContext, audienceActivity.yugaoAdapter.getData().get(i).getVideoTime().replace(" 开播", "")));
            audienceActivity.yugaoAdapter.getData().get(i).setVideoCloseState("1");
            audienceActivity.yugaoAdapter.notifyDataSetChanged();
            return;
        }
        UIUtils.showCenterOnlyTextToast(audienceActivity.activityContext, "出了一点儿小问题，请稍后再试", 1000);
    }

    public static /* synthetic */ void lambda$initMoreLive$104(AudienceActivity audienceActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            if (view.getId() == 2131297635) {
                String userLinkUrl = audienceActivity.liveAdapter.getData().get(i).getUserLinkUrl();
                if (!TextUtils.isEmpty(userLinkUrl)) {
                    IntentGo(audienceActivity.activityContext, audienceActivity.addChannel(userLinkUrl, "直播"));
                }
                PvCurrencyLogUtils.pvLogLive(audienceActivity.liveAdapter.getData().get(i).getVideoLinkUrl(), 2, audienceActivity.liveAdapter.getData().get(i).getUserNickName(), "头像", "更多直播列表页", audienceActivity.liveAdapter.getData().get(i).getVideoTitle(), "精品直播");
                return;
            }
            audienceActivity.gotoLiveInfo(audienceActivity.liveAdapter.getData(), i);
            audienceActivity.consumer.close();
            String videoLinkUrl = audienceActivity.liveAdapter.getData().get(i).getVideoLinkUrl();
            String userNickName = audienceActivity.liveAdapter.getData().get(i).getUserNickName();
            PvCurrencyLogUtils.pvLogLive(videoLinkUrl, 2, userNickName, "直播卡片(" + audienceActivity.liveAdapter.getData().get(i).getVideoTitle() + "-" + audienceActivity.liveAdapter.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", audienceActivity.liveAdapter.getData().get(i).getVideoTitle(), "精品直播");
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$105(AudienceActivity audienceActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            if (view.getId() == 2131297635) {
                String userLinkUrl = audienceActivity.huifangAdapter.getData().get(i).getUserLinkUrl();
                if (!TextUtils.isEmpty(userLinkUrl)) {
                    IntentGo(audienceActivity.activityContext, audienceActivity.addChannel(userLinkUrl, "直播回放"));
                }
                PvCurrencyLogUtils.pvLogLive(audienceActivity.huifangAdapter.getData().get(i).getVideoLinkUrl(), 2, audienceActivity.huifangAdapter.getData().get(i).getUserNickName(), "头像", "更多直播列表页", audienceActivity.huifangAdapter.getData().get(i).getVideoTitle(), "直播回放");
                return;
            }
            IntentManager.handleLocal(audienceActivity.activityContext, "直播回放", URLSet.getFuYiPingHuiFangUrl(audienceActivity.huifangAdapter.getData().get(i).getUserId(), audienceActivity.huifangAdapter.getData().get(i).getVideoId()), null);
            String videoLinkUrl = audienceActivity.huifangAdapter.getData().get(i).getVideoLinkUrl();
            String userNickName = audienceActivity.huifangAdapter.getData().get(i).getUserNickName();
            PvCurrencyLogUtils.pvLogLive(videoLinkUrl, 2, userNickName, "直播回放卡片(" + audienceActivity.huifangAdapter.getData().get(i).getVideoTitle() + "-" + audienceActivity.huifangAdapter.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", audienceActivity.huifangAdapter.getData().get(i).getVideoTitle(), "直播回放");
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$106(AudienceActivity audienceActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            if (view.getId() == 2131297635) {
                String userLinkUrl = audienceActivity.xuannifengguangAdapter.getData().get(i).getUserLinkUrl();
                if (!TextUtils.isEmpty(userLinkUrl)) {
                    IntentGo(audienceActivity.activityContext, audienceActivity.addChannel(userLinkUrl, "直播回放"));
                }
                PvCurrencyLogUtils.pvLogLive(audienceActivity.xuannifengguangAdapter.getData().get(i).getVideoLinkUrl(), 2, audienceActivity.xuannifengguangAdapter.getData().get(i).getUserNickName(), "头像", "更多直播列表页", audienceActivity.xuannifengguangAdapter.getData().get(i).getVideoTitle(), "慢直播");
                return;
            }
            audienceActivity.gotoLiveInfo(audienceActivity.xuannifengguangAdapter.getData(), i);
            audienceActivity.consumer.close();
            String videoLinkUrl = audienceActivity.xuannifengguangAdapter.getData().get(i).getVideoLinkUrl();
            String userNickName = audienceActivity.xuannifengguangAdapter.getData().get(i).getUserNickName();
            PvCurrencyLogUtils.pvLogLive(videoLinkUrl, 2, userNickName, "直播卡片(" + audienceActivity.xuannifengguangAdapter.getData().get(i).getVideoTitle() + "-" + audienceActivity.xuannifengguangAdapter.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", audienceActivity.xuannifengguangAdapter.getData().get(i).getVideoTitle(), "慢直播");
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$107(AudienceActivity audienceActivity) {
        if ("-1".equals(audienceActivity.livingPN)) {
            audienceActivity.liveAdapter.loadMoreEnd();
        } else {
            audienceActivity.getLivingList("CHOICE", audienceActivity.livingPN);
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$108(AudienceActivity audienceActivity) {
        if ("-1".equals(audienceActivity.yugaoPN)) {
            audienceActivity.yugaoAdapter.loadMoreEnd();
        } else {
            audienceActivity.getYuGaoList(audienceActivity.yugaoPN);
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$109(AudienceActivity audienceActivity) {
        if ("-1".equals(audienceActivity.oldListPage)) {
            audienceActivity.huifangAdapter.loadMoreEnd();
        } else {
            audienceActivity.getHuiFangList(audienceActivity.oldListPage);
        }
    }

    public static /* synthetic */ void lambda$initMoreLive$110(AudienceActivity audienceActivity) {
        if ("-1".equals(audienceActivity.fengGuangPN)) {
            audienceActivity.xuannifengguangAdapter.loadMoreEnd();
        } else {
            audienceActivity.getLivingList("6", audienceActivity.fengGuangPN);
        }
    }

    private void addSideslip(View view) {
        if (view != null) {
            try {
                this.wrapper = SmartSwipe.wrap(view);
                ((DrawerConsumer) this.wrapper.addConsumer(this.consumer)).setRightDrawerView(this.sideslipLayout).setScrimColor(2130706432).setEdgeSize(UIUtils.dip2px(50.0f)).addListener(new SimpleSwipeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.23
                    {
                        AudienceActivity.this = this;
                    }

                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeStart(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        UIUtils.logD("xcyOK", "onSwipeStart");
                        if (AudienceActivity.this.messageView != null) {
                            AudienceActivity.this.messageView.setBubbleVisibility(false);
                        }
                    }

                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeOpened(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        UIUtils.logD("xcyOK", "onSwipeOpened");
                        AudienceActivity.this.v2Video.setVisibility(8);
                        if (AudienceActivity.this.mLayoutManager != null) {
                            AudienceActivity.this.mLayoutManager.setScrollEnabled(false);
                        }
                    }

                    @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                    public void onSwipeClosed(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i) {
                        UIUtils.logD("xcyOK", "onSwipeClosed");
                        AudienceActivity.this.v2Video.setVisibility(0);
                        if (AudienceActivity.this.mLayoutManager != null) {
                            AudienceActivity.this.mLayoutManager.setScrollEnabled(true);
                        }
                        if (AudienceActivity.this.messageView != null) {
                            AudienceActivity.this.messageView.setBubbleVisibility(true);
                        }
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void gotoLiveInfo(List<LiveOrFengGuangInfoEntity.LiveInfoItem> list, int i) {
        try {
            if (list.size() > 0) {
                Intent intent = new Intent(this.activityContext, AudienceActivity.class);
                intent.putExtra("from", f18464JS);
                StringBuffer stringBuffer = new StringBuffer();
                for (LiveOrFengGuangInfoEntity.LiveInfoItem liveInfoItem : list) {
                    stringBuffer.append(liveInfoItem.getUserId());
                    stringBuffer.append(",");
                }
                stringBuffer.deleteCharAt(stringBuffer.length() - 1);
                String str = System.currentTimeMillis() + "json";
                App.getSharePreferenceUtil().putString(JSON_LIST_STR_FILE, str, stringBuffer.toString());
                intent.putExtra("listStr", str);
                intent.putExtra("index", i + "");
                startActivity(intent);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void qiehuanInit() {
        final ArrayList arrayList = new ArrayList();
        final ArrayList arrayList2 = new ArrayList();
        final ArrayList arrayList3 = new ArrayList();
        final ArrayList arrayList4 = new ArrayList();
        this.managerAudienceLoadData.getLivingList("CHOICE", "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$L9p5iLQwRPmjk8HPRplOFAbq4fs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$qiehuanInit$114(AudienceActivity.this, arrayList, arrayList2, arrayList3, arrayList4, (LiveOrFengGuangInfoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$_SwIq9q98W7VfMHX4GMhhUni0oY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                AudienceActivity.this.setLiveMore();
            }
        });
    }

    public static /* synthetic */ void lambda$qiehuanInit$114(AudienceActivity audienceActivity, List list, final List list2, final List list3, final List list4, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && liveOrFengGuangInfoEntity.getData() != null && liveOrFengGuangInfoEntity.getData().size() > 0) {
            list.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (list.size() > 0) {
            audienceActivity.setLiveMore();
        } else {
            audienceActivity.managerAudienceLoadData.getLivingList("6", "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$PuULJDs-7QuBKtrKaC4cvT_Xhi4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$qiehuanInit$113(AudienceActivity.this, list2, list3, list4, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$qiehuanInit$113(AudienceActivity audienceActivity, List list, final List list2, final List list3, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && liveOrFengGuangInfoEntity.getData() != null && liveOrFengGuangInfoEntity.getData().size() > 0) {
            list.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (list.size() > 0) {
            audienceActivity.setFengGuang();
        } else {
            audienceActivity.managerAudienceLoadData.getYuGaoList("0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$JGaCnPsikt4u7K7KUT8OhsBe97o
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$qiehuanInit$112(AudienceActivity.this, list2, list3, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$qiehuanInit$112(AudienceActivity audienceActivity, List list, final List list2, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && liveOrFengGuangInfoEntity.getData() != null && liveOrFengGuangInfoEntity.getData().size() > 0) {
            list.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (list.size() > 0) {
            audienceActivity.setYuGao();
        } else {
            audienceActivity.managerAudienceLoadData.getAllLiveList("ALLPLAYBACK", "1").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$5oJ19yOAFfRoLMnhRu8Jhqv7LKM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$qiehuanInit$111(AudienceActivity.this, list2, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        }
    }

    public static /* synthetic */ void lambda$qiehuanInit$111(AudienceActivity audienceActivity, List list, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode()) && (liveOrFengGuangInfoEntity.getData() != null || liveOrFengGuangInfoEntity.getData().size() > 0)) {
            list.addAll(liveOrFengGuangInfoEntity.getData());
        }
        if (list.size() > 0) {
            audienceActivity.setHuiFang();
        } else {
            audienceActivity.setLiveMore();
        }
    }

    private void getLivingList(final String str, final String str2) {
        try {
            if (this.f18465pd == null) {
                this.f18465pd = new CustomePorgressDialog(this.activityContext);
            }
            this.f18465pd.setMessage("加载中");
            this.f18465pd.show();
            this.managerAudienceLoadData.getLivingList(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$0YfsU5ufAahvdLxmCQhybM0zEJs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$getLivingList$116(AudienceActivity.this, str, str2, (LiveOrFengGuangInfoEntity) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getLivingList$116(AudienceActivity audienceActivity, String str, String str2, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            int i = 0;
            if ("6".equals(str)) {
                audienceActivity.fengGuangPN = liveOrFengGuangInfoEntity.getNextPageNum();
                if ("1".equals(str2)) {
                    audienceActivity.xuannifengguangAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
                    while (i < liveOrFengGuangInfoEntity.getData().size()) {
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "直播卡片(" + liveOrFengGuangInfoEntity.getData().get(i).getVideoTitle() + "-" + liveOrFengGuangInfoEntity.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", "", "慢直播");
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "头像", "更多直播列表页", "", "4");
                        i++;
                    }
                } else {
                    audienceActivity.xuannifengguangAdapter.addData(liveOrFengGuangInfoEntity.getData());
                    while (i < liveOrFengGuangInfoEntity.getData().size()) {
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "直播卡片(" + liveOrFengGuangInfoEntity.getData().get(i).getVideoTitle() + "-" + liveOrFengGuangInfoEntity.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", "", "慢直播");
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "头像", "更多直播列表页", "", "慢直播");
                        i++;
                    }
                }
                if ("-1".equals(audienceActivity.fengGuangPN)) {
                    audienceActivity.xuannifengguangAdapter.loadMoreEnd();
                } else {
                    audienceActivity.xuannifengguangAdapter.loadMoreComplete();
                }
                audienceActivity.rlvContent.setAdapter(audienceActivity.xuannifengguangAdapter);
                audienceActivity.xuannifengguangAdapter.notifyDataSetChanged();
            } else {
                audienceActivity.livingPN = liveOrFengGuangInfoEntity.getNextPageNum();
                if ("1".equals(str2)) {
                    audienceActivity.liveAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
                    while (i < liveOrFengGuangInfoEntity.getData().size()) {
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "直播卡片(" + liveOrFengGuangInfoEntity.getData().get(i).getVideoTitle() + "-" + liveOrFengGuangInfoEntity.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", "", "精品直播");
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "头像", "更多直播列表页", "", "精品直播");
                        i++;
                    }
                } else {
                    audienceActivity.liveAdapter.addData(liveOrFengGuangInfoEntity.getData());
                    while (i < liveOrFengGuangInfoEntity.getData().size()) {
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "直播卡片(" + liveOrFengGuangInfoEntity.getData().get(i).getVideoTitle() + "-" + liveOrFengGuangInfoEntity.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", "", "精品直播");
                        PvCurrencyLogUtils.pvLogLive("", 3, "", "头像", "更多直播列表页", "", "精品直播");
                        i++;
                    }
                }
                if ("-1".equals(audienceActivity.livingPN)) {
                    audienceActivity.liveAdapter.loadMoreEnd();
                } else {
                    audienceActivity.liveAdapter.loadMoreComplete();
                }
                audienceActivity.rlvContent.setAdapter(audienceActivity.liveAdapter);
                audienceActivity.liveAdapter.notifyDataSetChanged();
            }
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog == null || !customePorgressDialog.isShowing()) {
            return;
        }
        audienceActivity.f18465pd.dismiss();
    }

    private void getYuGaoList(final String str) {
        if (this.f18465pd == null) {
            this.f18465pd = new CustomePorgressDialog(this.activityContext);
        }
        this.f18465pd.setMessage("加载中");
        this.f18465pd.show();
        this.managerAudienceLoadData.getYuGaoList(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$N2hFiUJKTTc27GeIMOj8CDJQkMA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getYuGaoList$117(AudienceActivity.this, str, (LiveOrFengGuangInfoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getYuGaoList$117(AudienceActivity audienceActivity, String str, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            audienceActivity.yugaoPN = liveOrFengGuangInfoEntity.getNextPageNum();
            int i = 0;
            if ("1".equals(str)) {
                audienceActivity.yugaoAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
                while (i < liveOrFengGuangInfoEntity.getData().size()) {
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "预告卡片", "更多直播列表页", "", "直播预告");
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "预约键位", "更多直播列表页", "", "直播预告");
                    i++;
                }
            } else {
                audienceActivity.yugaoAdapter.addData(liveOrFengGuangInfoEntity.getData());
                while (i < liveOrFengGuangInfoEntity.getData().size()) {
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "预告卡片", "更多直播列表页", "", "直播预告");
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "预约键位", "更多直播列表页", "", "直播预告");
                    i++;
                }
            }
            if ("-1".equals(audienceActivity.fengGuangPN)) {
                audienceActivity.yugaoAdapter.loadMoreEnd();
            } else {
                audienceActivity.yugaoAdapter.loadMoreComplete();
            }
            audienceActivity.rlvContent.setAdapter(audienceActivity.yugaoAdapter);
            audienceActivity.yugaoAdapter.notifyDataSetChanged();
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog == null || !customePorgressDialog.isShowing()) {
            return;
        }
        audienceActivity.f18465pd.dismiss();
    }

    private void getHuiFangList(final String str) {
        if (this.f18465pd == null) {
            this.f18465pd = new CustomePorgressDialog(this.activityContext);
        }
        this.f18465pd.setMessage("加载中");
        this.f18465pd.show();
        this.managerAudienceLoadData.getAllLiveList("ALLPLAYBACK", str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$Vlukg0pfZM6xuz_ewacWhscNn6s
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getHuiFangList$118(AudienceActivity.this, str, (LiveOrFengGuangInfoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getHuiFangList$118(AudienceActivity audienceActivity, String str, LiveOrFengGuangInfoEntity liveOrFengGuangInfoEntity) throws Exception {
        if ("0000".equals(liveOrFengGuangInfoEntity.getStatusCode())) {
            audienceActivity.oldListPage = liveOrFengGuangInfoEntity.getNextPageNum();
            int i = 0;
            if ("1".equals(str)) {
                audienceActivity.huifangAdapter.setNewData(liveOrFengGuangInfoEntity.getData());
                while (i < liveOrFengGuangInfoEntity.getData().size()) {
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "直播回放卡片(" + liveOrFengGuangInfoEntity.getData().get(i).getVideoTitle() + "-" + liveOrFengGuangInfoEntity.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", "", "直播回放");
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "头像", "更多直播列表页", "", "直播回放");
                    i++;
                }
            } else {
                audienceActivity.huifangAdapter.addData(liveOrFengGuangInfoEntity.getData());
                while (i < liveOrFengGuangInfoEntity.getData().size()) {
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "直播回放卡片(" + liveOrFengGuangInfoEntity.getData().get(i).getVideoTitle() + "-" + liveOrFengGuangInfoEntity.getData().get(i).getVideoLinkUrl() + ")", "更多直播列表页", "", "直播回放");
                    PvCurrencyLogUtils.pvLogLive("", 3, "", "头像", "更多直播列表页", "", "直播回放");
                    i++;
                }
            }
            if ("-1".equals(audienceActivity.oldListPage)) {
                audienceActivity.huifangAdapter.loadMoreEnd();
            } else {
                audienceActivity.huifangAdapter.loadMoreComplete();
            }
        }
        CustomePorgressDialog customePorgressDialog = audienceActivity.f18465pd;
        if (customePorgressDialog == null || !customePorgressDialog.isShowing()) {
            return;
        }
        audienceActivity.f18465pd.dismiss();
    }

    private void getBannerList() {
        this.managerAudienceLoadData.getBannerList().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$oT2PF2p_pmmqkDXeCGdkWwMmVzM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$getBannerList$121(AudienceActivity.this, (LiveBanner) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$07-uiwIgchkGWJxtw5g624BhNUk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                AudienceActivity.this.vpBanner.setVisibility(8);
            }
        });
    }

    public static /* synthetic */ void lambda$getBannerList$121(AudienceActivity audienceActivity, final LiveBanner liveBanner) throws Exception {
        if ("0000".equals(liveBanner.getStatusCode())) {
            audienceActivity.vpBanner.setHolderCreator(new HolderCreator() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$DJdXoqHRFFO-ADeKX7FtLIhThHs
                @Override // com.zhpan.bannerview.holder.HolderCreator
                public final ViewHolder createViewHolder() {
                    return AudienceActivity.lambda$getBannerList$119();
                }
            }).setIndicatorWidth(BannerUtils.dp2px(6.0f), BannerUtils.dp2px(6.0f)).setIndicatorHeight(BannerUtils.dp2px(6.0f)).setIndicatorGap(BannerUtils.dp2px(5.0f)).setIndicatorColor(Color.parseColor("#80FFFFFF"), Color.parseColor("#FFFFFF")).setInterval(3000).setPageStyle(0).setOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.24
                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                }

                {
                    AudienceActivity.this = audienceActivity;
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                    NBSActionInstrumentation.onPageSelectedEnter(i, this);
                    PvCurrencyLogUtils.pvLogLive("", 3, "", liveBanner.getData().get(i).getTitle() + "-" + liveBanner.getData().get(i).getLinkUrl(), "更多直播列表页", "", "banner");
                    NBSActionInstrumentation.onPageSelectedExit();
                }
            }).setOnPageClickListener(new BannerViewPager.OnPageClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$6oks5GP5A-LuG1VroVmoNCK-zSQ
                @Override // com.zhpan.bannerview.BannerViewPager.OnPageClickListener
                public final void onPageClick(int i) {
                    AudienceActivity.lambda$getBannerList$120(AudienceActivity.this, liveBanner, i);
                }
            }).create(liveBanner.getData());
            return;
        }
        audienceActivity.vpBanner.setVisibility(8);
    }

    public static /* synthetic */ ViewHolder lambda$getBannerList$119() {
        return new BannerViewHolder();
    }

    public static /* synthetic */ void lambda$getBannerList$120(AudienceActivity audienceActivity, LiveBanner liveBanner, int i) {
        IntentGo(audienceActivity.activityContext, liveBanner.getData().get(i).getLinkUrl());
        PvCurrencyLogUtils.pvLogLive("", 2, "", liveBanner.getData().get(i).getTitle() + "-" + liveBanner.getData().get(i).getLinkUrl(), "更多直播列表页", "", "banner");
        audienceActivity.consumer.close();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class BannerViewHolder implements ViewHolder<LiveBannerEntity> {
        private ImageView bannerView;

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public View createView(ViewGroup viewGroup, Context context, int i) {
            View inflate = LayoutInflater.from(context).inflate(2131492999, viewGroup, false);
            this.bannerView = (ImageView) inflate.findViewById(2131296501);
            return inflate;
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public void onBind(Context context, LiveBannerEntity liveBannerEntity, int i, int i2) {
            try {
                Glide.with(context).load(liveBannerEntity.getImgSrc()).apply((BaseRequestOptions<?>) new RequestOptions().placeholder(2131232745).error(2131232745).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)))).into(this.bannerView);
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }
    }

    public void setLiveMore() {
        try {
            if (this.tabLive != null) {
                this.tabLive.setTextColor(-1);
                this.tabLive.setTextSize(1, 17.0f);
                this.tabLive.setTypeface(Typeface.defaultFromStyle(1));
            }
            if (this.tabAdvance != null) {
                this.tabAdvance.setTextColor(-3355444);
                this.tabAdvance.setTextSize(1, 15.0f);
                this.tabAdvance.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabPlayback != null) {
                this.tabPlayback.setTextColor(-3355444);
                this.tabPlayback.setTextSize(1, 15.0f);
                this.tabPlayback.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabFengguang != null) {
                this.tabFengguang.setTextColor(-3355444);
                this.tabFengguang.setTextSize(1, 15.0f);
                this.tabFengguang.setTypeface(Typeface.defaultFromStyle(0));
                getLivingList("CHOICE", this.livingPN);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void setFengGuang() {
        try {
            if (this.tabFengguang != null) {
                this.tabFengguang.setTextColor(-1);
                this.tabFengguang.setTextSize(1, 17.0f);
                this.tabFengguang.setTypeface(Typeface.defaultFromStyle(1));
            }
            if (this.tabPlayback != null) {
                this.tabPlayback.setTextColor(-3355444);
                this.tabPlayback.setTextSize(1, 15.0f);
                this.tabPlayback.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabLive != null) {
                this.tabLive.setTextColor(-3355444);
                this.tabLive.setTextSize(1, 15.0f);
                this.tabLive.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabAdvance != null) {
                this.tabAdvance.setTextColor(-3355444);
                this.tabAdvance.setTextSize(1, 15.0f);
                this.tabAdvance.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.rlvContent != null) {
                this.rlvContent.setAdapter(this.xuannifengguangAdapter);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void setYuGao() {
        try {
            if (this.tabAdvance != null) {
                this.tabAdvance.setTextColor(-1);
                this.tabAdvance.setTextSize(1, 17.0f);
                this.tabAdvance.setTypeface(Typeface.defaultFromStyle(1));
            }
            if (this.tabLive != null) {
                this.tabLive.setTextColor(-3355444);
                this.tabLive.setTextSize(1, 15.0f);
                this.tabLive.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabPlayback != null) {
                this.tabPlayback.setTextColor(-3355444);
                this.tabPlayback.setTextSize(1, 15.0f);
                this.tabPlayback.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabFengguang != null) {
                this.tabFengguang.setTextColor(-3355444);
                this.tabFengguang.setTextSize(1, 15.0f);
                this.tabFengguang.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.rlvContent != null) {
                this.rlvContent.setAdapter(this.yugaoAdapter);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void setHuiFang() {
        try {
            if (this.tabPlayback != null) {
                this.tabPlayback.setTextColor(-1);
                this.tabPlayback.setTextSize(1, 17.0f);
                this.tabPlayback.setTypeface(Typeface.defaultFromStyle(1));
            }
            if (this.tabLive != null) {
                this.tabLive.setTextColor(-3355444);
                this.tabLive.setTextSize(1, 15.0f);
                this.tabLive.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabAdvance != null) {
                this.tabAdvance.setTextColor(-3355444);
                this.tabAdvance.setTextSize(1, 15.0f);
                this.tabAdvance.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.tabFengguang != null) {
                this.tabFengguang.setTextColor(-3355444);
                this.tabFengguang.setTextSize(1, 15.0f);
                this.tabFengguang.setTypeface(Typeface.defaultFromStyle(0));
            }
            if (this.rlvContent != null) {
                this.rlvContent.setAdapter(this.huifangAdapter);
                this.oldListPage = "1";
                getHuiFangList(this.oldListPage);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void initAngleMorePlayer(List<AngleMoreEntity> list) {
        try {
            if (this.players == null) {
                this.players = new ArrayList();
            }
            if (this.players.size() < list.size()) {
                int size = list.size() - this.players.size();
                for (int i = 0; i < size; i++) {
                    final BDCloudVideoView bDCloudVideoView = (BDCloudVideoView) LayoutInflater.from(this.activityContext).inflate(2131492974, (ViewGroup) null);
                    bDCloudVideoView.setMaxProbeTime(200);
                    bDCloudVideoView.setBufferTimeInMs(200);
                    bDCloudVideoView.setLooping(true);
                    bDCloudVideoView.toggleFrameChasing(false);
                    bDCloudVideoView.setVideoScalingMode(1);
                    bDCloudVideoView.setVolume(0.0f, 0.0f);
                    bDCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$ygOGGLdcq0Z6_tn9kbsmRwv_Hbw
                        @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
                        public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                            AudienceActivity.lambda$initAngleMorePlayer$123(AudienceActivity.this, bDCloudVideoView, playerState);
                        }
                    });
                    this.players.add(bDCloudVideoView);
                }
            }
            for (int i2 = 0; i2 < list.size(); i2++) {
                AngleMoreEntity angleMoreEntity = list.get(i2);
                if (angleMoreEntity != null && angleMoreEntity.getList() != null && angleMoreEntity.getList().size() > 0) {
                    SharpnessEntity.LiveUrlBean liveUrlBean = angleMoreEntity.getList().get(angleMoreEntity.getList().size() > 1 ? 1 : 0);
                    BDCloudVideoView bDCloudVideoView2 = this.players.get(i2);
                    bDCloudVideoView2.setPlayList(angleMoreEntity.getList());
                    bDCloudVideoView2.setImgUrl(angleMoreEntity.getCover());
                    bDCloudVideoView2.stopPlayback();
                    bDCloudVideoView2.reSetRender();
                    bDCloudVideoView2.setVideoPath(getSwitchUrl(liveUrlBean));
                    bDCloudVideoView2.setTag(getSwitchUrl(liveUrlBean));
                    bDCloudVideoView2.start();
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initAngleMorePlayer$123(AudienceActivity audienceActivity, BDCloudVideoView bDCloudVideoView, BDCloudVideoView.PlayerState playerState) {
        try {
            UIUtils.logD("多视角播放器", "nowState=" + playerState);
            if (playerState == BDCloudVideoView.PlayerState.STATE_PLAYING) {
                if (audienceActivity.isNeedPay) {
                    bDCloudVideoView.stopPlayback();
                    bDCloudVideoView.setBackgroundColor(0);
                    bDCloudVideoView.setVisibility(8);
                } else {
                    UIUtils.logD("多视角播放器", "播放:" + bDCloudVideoView.getCurrentPlayingUrl());
                    bDCloudVideoView.setBackgroundColor(-16777216);
                    bDCloudVideoView.setVisibility(0);
                }
            } else if (playerState == BDCloudVideoView.PlayerState.STATE_IDLE) {
                bDCloudVideoView.setBackgroundColor(0);
                bDCloudVideoView.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void recoverMainPlayer(View view) {
        try {
            showLiveCover();
            FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296396);
            FrameLayout frameLayout2 = (FrameLayout) view.findViewById(2131296395);
            for (AngleMoreEntity angleMoreEntity : this.angleAdapter.getData()) {
                if (angleMoreEntity.isSelection()) {
                    bdCloudVideoView.setVolume(1.0f, 1.0f);
                    this.liveUrlList = angleMoreEntity.getList();
                    frameLayout.setVisibility(0);
                    frameLayout2.removeAllViews();
                    GlideApp.with((FragmentActivity) this).load(angleMoreEntity.getCover()).into((ImageView) view.findViewById(2131296393));
                    setPlayDate(view);
                    return;
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void releaseAngleMorePlayers() {
        ViewGroup viewGroup;
        try {
            if (this.players != null) {
                UIUtils.logD("xcy", "回收多视角播放器");
                for (int i = 0; i < this.players.size(); i++) {
                    BDCloudVideoView bDCloudVideoView = this.players.get(i);
                    bDCloudVideoView.stopPlayback();
                    bDCloudVideoView.release();
                    ViewGroup viewGroup2 = (ViewGroup) bDCloudVideoView.getParent();
                    if (viewGroup2 != null) {
                        viewGroup2.removeAllViews();
                    }
                }
                this.selectedPlayer.clear();
                this.players.clear();
                if (this.multiRoot != null && (viewGroup = (ViewGroup) this.multiRoot.getParent()) != null) {
                    viewGroup.removeView(this.multiRoot);
                }
                if (this.llMultiView != null) {
                    this.llMultiView.setVisibility(8);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @SuppressLint({"CheckResult"})
    private void setAngleMore(List<AngleMoreEntity> list) {
        try {
            UIUtils.logD("AudienceActivity", "setAngleMore");
            initAngleMorePlayer(list);
            final View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            this.llMultiView = findViewByPosition.findViewById(2131297749);
            RecyclerView recyclerView = (RecyclerView) findViewByPosition.findViewById(2131298406);
            final TextView textView = (TextView) findViewByPosition.findViewById(2131299019);
            this.llMultiView.setVisibility(0);
            textView.setVisibility(8);
            RxView.clicks((ImageView) findViewByPosition.findViewById(2131297444)).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$SRMawSQkwbAEYE7QyAz2H0DhFEo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    r0.dismissAngle(AudienceActivity.this.llMultiView, textView);
                }
            });
            RxView.clicks((LinearLayout) findViewByPosition.findViewById(2131297748)).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$jMLHcHp04-NG5x1htvUYD2_DldM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    r0.dismissAngle(AudienceActivity.this.llMultiView, null);
                }
            });
            RxView.clicks(textView).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$4HZmVbP9EkiVh06R2kbkuIIpXyg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceActivity.lambda$setAngleMore$126(AudienceActivity.this, textView, obj);
                }
            });
            this.multiLayoutManager = new LinearLayoutManager(this.activityContext, 0, false) { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.25
                {
                    AudienceActivity.this = this;
                }

                @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
                public boolean canScrollHorizontally() {
                    return !AudienceActivity.this.isNeedPay;
                }
            };
            recyclerView.setLayoutManager(this.multiLayoutManager);
            BaseQuickAdapter<AngleMoreEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<AngleMoreEntity, BaseViewHolder>(2131493203) { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.26
                {
                    AudienceActivity.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, AngleMoreEntity angleMoreEntity) {
                    int indexOf = AudienceActivity.this.angleAdapter.getData().indexOf(angleMoreEntity);
                    FrameLayout frameLayout = (FrameLayout) baseViewHolder.getView(2131296999);
                    GlideApp.with((FragmentActivity) AudienceActivity.this.activityContext).load(angleMoreEntity.getCover()).into((ImageView) baseViewHolder.getView(2131297366));
                    baseViewHolder.setText(2131299108, angleMoreEntity.getName()).setGone(2131299496, angleMoreEntity.isSelection());
                    if (angleMoreEntity.isSelection()) {
                        frameLayout.setVisibility(8);
                        frameLayout.removeAllViews();
                    } else {
                        frameLayout.setVisibility(angleMoreEntity.isVis() ? 0 : 8);
                        AudienceActivity.this.playerIntoList(indexOf, frameLayout);
                    }
                    baseViewHolder.addOnClickListener(2131298410);
                }
            };
            this.angleAdapter = baseQuickAdapter;
            recyclerView.setAdapter(baseQuickAdapter);
            recyclerView.addOnScrollListener(new C829227());
            this.angleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$DpM56lxgEJ22-HMi-E1E6_HtSAc
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                    AudienceActivity.lambda$setAngleMore$128(AudienceActivity.this, findViewByPosition, baseQuickAdapter2, view, i);
                }
            });
            this.angleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$YkVidjK9VWqDscNxuNOtySECnxE
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                    AudienceActivity.lambda$setAngleMore$130(AudienceActivity.this, findViewByPosition, baseQuickAdapter2, view, i);
                }
            });
            this.angleAdapter.setNewData(list);
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setAngleMore$126(AudienceActivity audienceActivity, TextView textView, Object obj) throws Exception {
        audienceActivity.showAngle(audienceActivity.llMultiView, false);
        textView.setVisibility(8);
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceActivity$27 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C829227 extends RecyclerView.OnScrollListener {
        C829227() {
            AudienceActivity.this = r1;
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            UIUtils.logD("多视角列表", "newState=" + i);
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            try {
                int findFirstCompletelyVisibleItemPosition = AudienceActivity.this.multiLayoutManager.findFirstCompletelyVisibleItemPosition();
                int findLastCompletelyVisibleItemPosition = AudienceActivity.this.multiLayoutManager.findLastCompletelyVisibleItemPosition();
                int i3 = 0;
                while (i3 < AudienceActivity.this.angleAdapter.getData().size()) {
                    ((AngleMoreEntity) AudienceActivity.this.angleAdapter.getData().get(i3)).setVis(i3 >= findFirstCompletelyVisibleItemPosition && i3 <= findLastCompletelyVisibleItemPosition);
                    i3++;
                }
                recyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$27$J3_MmCBw1QVUj62wrcXletkaK1s
                    @Override // java.lang.Runnable
                    public final void run() {
                        AudienceActivity.C829227.lambda$onScrolled$0(AudienceActivity.C829227.this);
                    }
                });
            } catch (Exception e) {
                MsLogUtil.m7979d("AudienceActivity", e.getMessage());
            }
        }

        public static /* synthetic */ void lambda$onScrolled$0(C829227 c829227) {
            UIUtils.logD("多视角列表", "多视角列表post=");
            AudienceActivity.this.angleAdapter.notifyDataSetChanged();
        }
    }

    public static /* synthetic */ void lambda$setAngleMore$128(AudienceActivity audienceActivity, final View view, BaseQuickAdapter baseQuickAdapter, View view2, final int i) {
        if (audienceActivity.isNeedPay) {
            return;
        }
        audienceActivity.resetPlayerVoice();
        Disposable disposable = audienceActivity.changeAngleView;
        if (disposable != null) {
            disposable.dispose();
            audienceActivity.changeAngleView = null;
        }
        audienceActivity.changeAngleView = Observable.timer(600L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$RtTbOQi94rbDz1afXeXfjFFZkIg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$setAngleMore$127(AudienceActivity.this, i, view, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setAngleMore$127(AudienceActivity audienceActivity, int i, View view, Long l) throws Exception {
        try {
            UIUtils.logD("多视角列表", "点击多视角列表执行操作");
            AngleMoreEntity angleMoreEntity = audienceActivity.angleAdapter.getData().get(i);
            int i2 = 0;
            for (int i3 = 0; i3 < audienceActivity.angleAdapter.getData().size(); i3++) {
                if (audienceActivity.angleAdapter.getData().get(i3).isSelection()) {
                    i2++;
                }
            }
            boolean z = true;
            if (i2 == 1) {
                int i4 = 0;
                while (true) {
                    if (i4 >= audienceActivity.angleAdapter.getData().size()) {
                        i4 = 0;
                        break;
                    } else if (!audienceActivity.angleAdapter.getData().get(i4).isSelection()) {
                        i4++;
                    } else if (i4 != i) {
                        audienceActivity.angleAdapter.getItem(i4).setSelection(false);
                        audienceActivity.angleAdapter.getItem(i).setSelection(true);
                    }
                }
                if (i4 != i) {
                    audienceActivity.angleAdapter.notifyItemChanged(i4);
                    audienceActivity.angleAdapter.notifyItemChanged(i);
                    bdCloudVideoView.setVolume(1.0f, 1.0f);
                    audienceActivity.liveUrlList = audienceActivity.angleAdapter.getData().get(i).getList();
                    ((FrameLayout) view.findViewById(2131296396)).setVisibility(0);
                    ((FrameLayout) view.findViewById(2131296395)).removeAllViews();
                    audienceActivity.setPlayDate(view);
                    audienceActivity.selectedPlayer.clear();
                    audienceActivity.selectedPlayer.add(audienceActivity.players.get(i));
                    MsLogUtil.m7979d("多视角列表点击", "players.size=" + audienceActivity.players.size());
                    GlideApp.with((FragmentActivity) audienceActivity).load(angleMoreEntity.getCover()).into((ImageView) view.findViewById(2131296393));
                } else {
                    UIUtils.logD("多视角列表点击", "已选中不进行切换");
                }
            } else {
                if (angleMoreEntity.isSelection()) {
                    z = false;
                }
                angleMoreEntity.setSelection(z);
                audienceActivity.angleAdapter.notifyItemChanged(i);
                if (angleMoreEntity.isSelection()) {
                    audienceActivity.selectedPlayer.add(audienceActivity.players.get(i));
                } else {
                    audienceActivity.selectedPlayer.remove(audienceActivity.players.get(i));
                }
                audienceActivity.showMorePlayer(view, false);
            }
            if (audienceActivity.changeAngleView != null) {
                audienceActivity.changeAngleView.dispose();
                audienceActivity.changeAngleView = null;
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setAngleMore$130(AudienceActivity audienceActivity, final View view, BaseQuickAdapter baseQuickAdapter, View view2, final int i) {
        if (audienceActivity.isNeedPay) {
            return;
        }
        Disposable disposable = audienceActivity.addView2List;
        if (disposable != null) {
            disposable.dispose();
            audienceActivity.addView2List = null;
        }
        audienceActivity.addView2List = Observable.timer(600L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$bscFHaM0UQnOv22-E84fnGcWGMI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$setAngleMore$129(AudienceActivity.this, i, view, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setAngleMore$129(AudienceActivity audienceActivity, int i, View view, Long l) throws Exception {
        try {
            AngleMoreEntity angleMoreEntity = audienceActivity.angleAdapter.getData().get(i);
            int i2 = 0;
            for (int i3 = 0; i3 < audienceActivity.angleAdapter.getData().size(); i3++) {
                if (audienceActivity.angleAdapter.getData().get(i3).isSelection()) {
                    i2++;
                }
            }
            boolean z = true;
            if (i2 == 1 && angleMoreEntity.isSelection()) {
                UIUtils.logD("多视角列表", "目前只选中了一个视角，无法取消");
            } else {
                if (angleMoreEntity.isSelection()) {
                    z = false;
                }
                angleMoreEntity.setSelection(z);
                audienceActivity.angleAdapter.notifyItemChanged(i);
                if (angleMoreEntity.isSelection()) {
                    audienceActivity.selectedPlayer.add(audienceActivity.players.get(i));
                } else {
                    audienceActivity.selectedPlayer.remove(audienceActivity.players.get(i));
                }
                audienceActivity.showMorePlayer(view, false);
            }
            if (audienceActivity.addView2List != null) {
                audienceActivity.addView2List.dispose();
                audienceActivity.addView2List = null;
            }
        } catch (Exception e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    private void resetPlayerVoice() {
        try {
            if (this.players != null) {
                for (BDCloudVideoView bDCloudVideoView : this.players) {
                    bDCloudVideoView.setVolume(0.0f, 0.0f);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showMorePlayer(View view, boolean z) {
        try {
            bdCloudVideoView.setVolume(0.0f, 0.0f);
            resetPlayerVoice();
            if (view == null) {
                view = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            }
            ((FrameLayout) view.findViewById(2131296396)).removeAllViews();
            FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296395);
            frameLayout.removeAllViews();
            if (z) {
                if (getScreenOrientation(this.activityContext) == 0) {
                    this.multiRoot = LayoutInflater.from(this.activityContext).inflate(2131493230, (ViewGroup) null);
                } else {
                    this.multiRoot = LayoutInflater.from(this.activityContext).inflate(2131493231, (ViewGroup) null);
                }
            } else if (getScreenOrientation(this.activityContext) == 0) {
                this.multiRoot = LayoutInflater.from(this.activityContext).inflate(2131493231, (ViewGroup) null);
            } else {
                this.multiRoot = LayoutInflater.from(this.activityContext).inflate(2131493230, (ViewGroup) null);
            }
            FrameLayout frameLayout2 = (FrameLayout) this.multiRoot.findViewById(2131297000);
            FrameLayout frameLayout3 = (FrameLayout) this.multiRoot.findViewById(2131297001);
            FrameLayout frameLayout4 = (FrameLayout) this.multiRoot.findViewById(2131297002);
            FrameLayout frameLayout5 = (FrameLayout) this.multiRoot.findViewById(2131297003);
            FrameLayout frameLayout6 = (FrameLayout) this.multiRoot.findViewById(2131297004);
            FrameLayout frameLayout7 = (FrameLayout) this.multiRoot.findViewById(2131297005);
            View findViewById = this.multiRoot.findViewById(2131299492);
            View findViewById2 = this.multiRoot.findViewById(2131297758);
            View findViewById3 = this.multiRoot.findViewById(2131299491);
            int i = 8;
            frameLayout2.setVisibility(8);
            frameLayout3.setVisibility(8);
            frameLayout4.setVisibility(8);
            frameLayout5.setVisibility(8);
            frameLayout6.setVisibility(8);
            frameLayout7.setVisibility(8);
            findViewById.setVisibility(8);
            findViewById2.setVisibility(8);
            if (findViewById3 != null) {
                if (!UIUtils.isFoldScreen(this.activityContext)) {
                    i = 0;
                }
                findViewById3.setVisibility(i);
            }
            int multiSelectedCount = getMultiSelectedCount();
            ArrayList arrayList = new ArrayList();
            switch (multiSelectedCount) {
                case 2:
                    arrayList.add(frameLayout2);
                    arrayList.add(frameLayout3);
                    break;
                case 3:
                    findViewById.setVisibility(0);
                    findViewById2.setVisibility(0);
                    arrayList.add(frameLayout6);
                    arrayList.add(frameLayout7);
                    arrayList.add(frameLayout4);
                    break;
                case 4:
                    findViewById2.setVisibility(0);
                    arrayList.add(frameLayout6);
                    arrayList.add(frameLayout7);
                    arrayList.add(frameLayout4);
                    arrayList.add(frameLayout5);
                    break;
                default:
                    recoverMainPlayer(view);
                    return;
            }
            for (int i2 = 0; i2 < this.selectedPlayer.size(); i2++) {
                BDCloudVideoView bDCloudVideoView = this.selectedPlayer.get(i2);
                if (i2 < arrayList.size()) {
                    FrameLayout frameLayout8 = (FrameLayout) arrayList.get(i2);
                    frameLayout8.removeAllViews();
                    ViewGroup viewGroup = (ViewGroup) bDCloudVideoView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeAllViews();
                    }
                    frameLayout8.setVisibility(0);
                    ImageView imageView = new ImageView(this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                    GlideApp.with((FragmentActivity) this).load(bDCloudVideoView.getImgUrl()).into(imageView);
                    frameLayout8.addView(imageView, layoutParams);
                    frameLayout8.addView(bDCloudVideoView);
                }
                if (i2 == 0) {
                    bDCloudVideoView.setVolume(1.0f, 1.0f);
                }
            }
            ViewGroup viewGroup2 = (ViewGroup) this.multiRoot.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            hideLiveCover();
            frameLayout.addView(this.multiRoot);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showAngle(final View view, boolean z) {
        try {
            view.setVisibility(0);
            view.clearAnimation();
            Animation loadAnimation = AnimationUtils.loadAnimation(this.activityContext, z ? 2130772010 : 2130772100);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.28
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                {
                    AudienceActivity.this = this;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                    view.setVisibility(0);
                }
            });
            view.startAnimation(loadAnimation);
        } catch (Exception e) {
            UIUtils.logD("AudienceActivity", e.getMessage());
        }
    }

    public void dismissAngle(final View view, final View view2) {
        try {
            view.clearAnimation();
            Animation loadAnimation = AnimationUtils.loadAnimation(this.activityContext, view2 == null ? 2130771990 : 2130772101);
            loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceActivity.29
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                {
                    AudienceActivity.this = this;
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    try {
                        view.setVisibility(8);
                        if (view2 != null) {
                            view2.setVisibility(0);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7979d("AudienceActivity", e.getMessage());
                    }
                }
            });
            view.startAnimation(loadAnimation);
        } catch (Resources.NotFoundException e) {
            MsLogUtil.m7979d("AudienceActivity", e.getMessage());
        }
    }

    public void playerIntoList(int i, FrameLayout frameLayout) {
        if (frameLayout != null) {
            try {
                frameLayout.removeAllViews();
                BDCloudVideoView player = getPlayer(i);
                if (player != null) {
                    ViewGroup viewGroup = (ViewGroup) player.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeAllViews();
                    }
                    frameLayout.addView(player);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private BDCloudVideoView getPlayer(int i) {
        List<BDCloudVideoView> list = this.players;
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    private void getShareCode() {
        this.managerAudienceLoadData.getShareCode().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$2crrkkmw2t_7LNKWY9Wffy9Do0A
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.this.shareCode = (String) ((BaseVideoEntity) obj).getData();
            }
        });
    }

    private void hideChatRoomAndGiftByNet(final String str) {
        this.managerAudienceLoadData.getLiveRoomUIHide(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$_X3sfPjCUaPL13GsEUVcyr6anK4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$hideChatRoomAndGiftByNet$132(AudienceActivity.this, str, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceActivity$L5cZpecah6QplINTd-p7vfzCLho
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceActivity.lambda$hideChatRoomAndGiftByNet$133(AudienceActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$hideChatRoomAndGiftByNet$132(AudienceActivity audienceActivity, String str, BaseVideoEntity baseVideoEntity) throws Exception {
        if (baseVideoEntity != null && "0000".equals(baseVideoEntity.getStatusCode()) && baseVideoEntity.getData() != null) {
            audienceActivity.liveRoomUIInfo = (LiveRoomUiHideEntity) baseVideoEntity.getData();
            audienceActivity.liveRoomUIInfo.setRoomId(str);
            if (audienceActivity.messageView != null && baseVideoEntity.getData() != null) {
                audienceActivity.messageView.hideChatRoomAndGiftByNet((LiveRoomUiHideEntity) baseVideoEntity.getData());
            }
            if (audienceActivity.landscapeView == null || baseVideoEntity.getData() == null) {
                return;
            }
            audienceActivity.landscapeView.hideChatRoomAndGiftByNet((LiveRoomUiHideEntity) baseVideoEntity.getData());
            return;
        }
        audienceActivity.liveRoomUIInfo = null;
    }

    public static /* synthetic */ void lambda$hideChatRoomAndGiftByNet$133(AudienceActivity audienceActivity, Throwable th) throws Exception {
        UIUtils.logD("liveEx", "查询是否隐藏聊天室及礼物UI是否显示接口出错。");
        audienceActivity.liveRoomUIInfo = null;
    }
}
