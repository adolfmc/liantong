package com.sinovatech.unicom.separatemodule.audience.fragment;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.LinearSmoothScroller;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
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
import android.widget.ViewFlipper;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.billy.android.swipe.SmartSwipeWrapper;
import com.billy.android.swipe.SwipeConsumer;
import com.billy.android.swipe.consumer.SlidingConsumer;
import com.billy.android.swipe.listener.SimpleSwipeListener;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSFragmentSession;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.eventbus.NetEvent;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.p315ui.view.HomeMengcengView;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircleProgressAlpha;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ISmallVideo;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.VideoDataType;
import com.sinovatech.unicom.separatemodule.audience.adpter.ComFortAdapter;
import com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.HelpBtnInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.TaskScoreInfo;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoMoreConfigEntity;
import com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment;
import com.sinovatech.unicom.separatemodule.audience.function.RecommendListFunction;
import com.sinovatech.unicom.separatemodule.audience.function.SmallVideoItemFunction;
import com.sinovatech.unicom.separatemodule.audience.util.ADCallBack;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.util.SpringScaleInterpolator;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsDialog;
import com.sinovatech.unicom.separatemodule.audience.view.ComFortView;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import com.sinovatech.unicom.separatemodule.audience.view.LiveNoMoreDataFooter;
import com.sinovatech.unicom.separatemodule.audience.view.MyGestureView;
import com.sinovatech.unicom.separatemodule.audience.view.OrderVideoCallingResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog;
import com.sinovatech.unicom.separatemodule.livepinglun.LiveCommentActivity;
import com.sinovatech.unicom.separatemodule.livepinglun.entity.LiveCommentEntity;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.notice.NoticDateUtil;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener;
import com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.RandomStringUtils;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmallVideoFragment extends BaseFragment {
    private static final int COUNT_DOWN_TOTAL = 30000;
    private static final String DATA_NO_MORE = "-1";
    private static final String DEFAULT_CODE_0 = "0";
    private static final String NET_DATA_STATUS_CODE = "0000";
    private static final String STATUS_Y = "Y";
    private static final String TAG = "SmallVideoFragment";
    public NBSTraceUnit _nbs_trace;
    private String accessName;
    private BaseActivity activityContext;
    private int adFypMax;
    private SmallVideoAdapter.ItemClickedListener adapterListener;
    private View barRoot;
    private FrameLayout baseLayout;
    private BDCloudVideoView bdCloudVideoView;
    private boolean countDownReady;
    private View countDownView;
    private CircleProgressAlpha cpSchedule;
    private Map<String, String> customParams;
    private LikeView.TouchCallBack doubleClickListener;
    private FrameLayout flAdPauseView;
    private FrameLayout flAdPauseViewLandscape;
    private FrameLayout flProgressBarAreaLandscape;
    private View fragmentCacheView;
    private boolean isFromPersonalCenterBack;
    private boolean isLoadMore;
    private boolean isLoadingAd;
    private boolean isNeedCode;
    private boolean isSetting;
    private boolean isStartTrackingTouch;
    private ImageView ivBack;
    private ImageView ivBarLandscapeBtn;
    private View ivBtnPlay;
    private View ivBtnPlay2;
    private String keyWords;
    private LikeView likeView;
    private LinearLayout llEmpty;
    private View llLandscapeTitle;
    private LinearLayout llProgressBar;
    private LinearLayout llProgressTextLine;
    private Disposable logTimer;
    private SmallVideoAdapter mAdapter;
    private int mCurrentItem;
    private ISmallVideo mISmallVideoImpl;
    private ViewPagerLayoutManager mLayoutManager;
    private List<VideoMoreConfigEntity> mMoreConfigs;
    private RecyclerView mRecyclerView;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private int oldSlotCount;
    private int pauseCount;

    /* renamed from: pd */
    private CustomePorgressDialog f18480pd;
    private Disposable playProgressUpdater;
    private String playedTime;
    private SmallVideoEntity.Data previousItem;
    private Disposable reSetVideo;
    private int realPosition;
    private ComFortView reclComfort;
    private int ringIndex;
    private List<VideoEntity> ringList;
    private RelativeLayout rlAdPauseView;
    private RelativeLayout rlAdPauseViewLandscape;
    private LinearLayout rlComfort;
    private RelativeLayout rlInfoArea;
    private SeekBar sbProgress;
    private String singleVideo;
    private int slotCount;
    private String slotTime;
    private SmartSwipeRefresh smartSwipeRefresh;
    private LinearSmoothScroller smoothScroller;
    private String startTime;
    private String steamVideoId;
    private CountDownTimer taskScoreTimer;
    private boolean toasted;
    private TextView tvCountNotice;
    private View tvDetailNotice;
    private TextView tvEndTime;
    private TextView tvStartTime;
    private String typeId;
    private String userId;
    private View vBarLandscapeBg;
    private View vBarLandscapeEnd;
    private ViewFlipper vfNotice;
    private VideoDataType videoDataType;
    private String videoId;
    private Map<String, String> videoIdsWatched;
    private String videoTime;
    private String welfareUrl;
    private String pageNum = "1";
    private int countDown = 30000;
    private String tabTitle = "";
    private String rawData = "";
    private String fromType = "audienceMain";
    private Handler playHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$fINvjxazUGUMXNvJht10-J6PCpM
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return SmallVideoFragment.lambda$new$3(SmallVideoFragment.this, message);
        }
    });
    private Handler closer = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.8
        {
            SmallVideoFragment.this = this;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            try {
                if (SmallVideoFragment.this.smartSwipeRefresh == null || SmallVideoFragment.this.smartSwipeRefresh.getSwipeConsumer() == null) {
                    return false;
                }
                SmallVideoFragment.this.smartSwipeRefresh.getSwipeConsumer().smoothClose();
                return false;
            } catch (Exception e) {
                MsLogUtil.m7979d(SmallVideoFragment.TAG, e.getMessage());
                return false;
            }
        }
    });
    private int countViewStatus = 8;
    private final List<View> adList = new ArrayList();
    private final ConcurrentHashMap<Integer, SmallVideoEntity.Data> drawAdFypMap = new ConcurrentHashMap<>();
    private final List<View> pauseAdList = new ArrayList();
    private final List<View> pauseAdListF = new ArrayList();
    private Handler addPlayer2UIHandler = new Handler(new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$Z4rIZ7ojBGtvLzgS5oaj9Su4ir8
        @Override // android.os.Handler.Callback
        public final boolean handleMessage(Message message) {
            return SmallVideoFragment.lambda$new$24(SmallVideoFragment.this, message);
        }
    });
    private long playBeginTime = 0;
    private int realPlayed = 0;
    private Handler playTime = new Handler(new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.12
        {
            SmallVideoFragment.this = this;
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i != 3241) {
                if (i != 5000 || SmallVideoFragment.this.llLandscapeTitle == null || SmallVideoFragment.this.flProgressBarAreaLandscape == null) {
                    return false;
                }
                SmallVideoFragment.this.llLandscapeTitle.setVisibility(8);
                SmallVideoFragment.this.flProgressBarAreaLandscape.setVisibility(8);
                return false;
            } else if (SmallVideoFragment.this.bdCloudVideoView != null) {
                if (SmallVideoFragment.this.bdCloudVideoView.isPlaying()) {
                    SmallVideoFragment.access$5208(SmallVideoFragment.this);
                }
                SmallVideoFragment.this.startTiming();
                return false;
            } else {
                return false;
            }
        }
    });
    public String helpUrl = "https://m.10155.com/h5/mactivity/woapphall.html#/instructions";
    public boolean isShowHelpBtn = true;

    private String getBaseJsonStr() {
        return "{\"data\":xxxxxx,\"message\":\"成功\",\"nextPageNum\":\"1\",\"statusCode\":\"0000\"}";
    }

    public static /* synthetic */ void lambda$hiBoardLog$50(String str) throws Exception {
    }

    public static /* synthetic */ void lambda$upLoadVideoErrorLog$31(String str) throws Exception {
    }

    public static /* synthetic */ void lambda$zanVideo$33(String str) throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        NBSFragmentSession.getInstance().fragmentSessionPause(getClass().getName(), this);
        super.onPause();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onResume() {
        NBSFragmentSession.fragmentSessionResumeBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment");
        super.onResume();
        NBSFragmentSession.fragmentSessionResumeEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment");
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void setUserVisibleHint(boolean z) {
        NBSFragmentSession.setUserVisibleHint(z, getClass().getName());
        super.setUserVisibleHint(z);
    }

    public void showLoading() {
    }

    static /* synthetic */ int access$5208(SmallVideoFragment smallVideoFragment) {
        int i = smallVideoFragment.realPosition;
        smallVideoFragment.realPosition = i + 1;
        return i;
    }

    public void setFrom(String str) {
        this.fromType = str;
    }

    @Override // android.support.p083v4.app.Fragment
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override // android.support.p083v4.app.Fragment
    public void onCreate(@Nullable Bundle bundle) {
        NBSTraceEngine.startTracingInFragment(getClass().getName());
        super.onCreate(bundle);
        UIUtils.logD(TAG, "onCreate");
        NBSFragmentSession.fragmentOnCreateEnd(getClass().getName());
    }

    private void initCircleProgress(View view) {
        if (this.videoDataType == VideoDataType.SmallVideo4Up || this.videoDataType == VideoDataType.SearchVideo) {
            return;
        }
        try {
            this.countDownView = view.findViewById(2131296588);
            this.countDownView.setVisibility(0);
            this.countViewStatus = 0;
            this.countDownView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$Bo4AzPO4Yy0SmUC5DYbRR8Uyir8
                @Override // android.view.View.OnClickListener
                public final void onClick(View view2) {
                    SmallVideoFragment.this.gotoWelFareCenter();
                }
            });
            this.cpSchedule = (CircleProgressAlpha) view.findViewById(2131296723);
            this.cpSchedule.setPaddingscale(0.95f);
            this.cpSchedule.setProdressWidth(this.cpSchedule.dp2px(2));
            this.cpSchedule.setCircleBackgroud(Color.parseColor("#000000"));
            this.cpSchedule.setCircleAlpha(76);
            this.cpSchedule.setPreProgress(Color.parseColor("#FFFFFF"));
            this.cpSchedule.setPreAlpha(76);
            this.cpSchedule.setProgress(Color.parseColor("#FFFFFF"));
            this.cpSchedule.setMaxValue(30000);
            this.tvDetailNotice = view.findViewById(2131298859);
            this.tvCountNotice = (TextView) view.findViewById(2131298947);
            this.vfNotice = (ViewFlipper) view.findViewById(2131296719);
            this.vfNotice.setVisibility(8);
            this.tvDetailNotice.setVisibility(0);
            getScoreInfo(true);
            readJson();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void getScoreInfo(final boolean z) {
        if (this.videoDataType == VideoDataType.SmallVideo4Up || this.videoDataType == VideoDataType.SearchVideo) {
            return;
        }
        try {
            this.countDownReady = true;
            this.managerAudienceLoadData.getTaskScoreInfo(getVideoFromType()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$CmVMktyTrolbN5SPLbfwaLG-8Eg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getScoreInfo$1(SmallVideoFragment.this, z, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$qIbROxrLi4HdHSuEEvqCjW-r83Y
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getScoreInfo$2(SmallVideoFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getScoreInfo$1(SmallVideoFragment smallVideoFragment, boolean z, BaseVideoEntity baseVideoEntity) throws Exception {
        int i;
        int i2;
        int i3;
        int i4;
        if (!"0000".equals(baseVideoEntity.getStatusCode()) || baseVideoEntity.getData() == null) {
            return;
        }
        UIUtils.logD(TAG, "获取今日获取积分：" + ((TaskScoreInfo) baseVideoEntity.getData()).getScoreTotalDay());
        try {
            i = Integer.parseInt(((TaskScoreInfo) baseVideoEntity.getData()).getScoreWatchDay());
        } catch (NumberFormatException e) {
            e.printStackTrace();
            i = 0;
        }
        try {
            i2 = Integer.parseInt(((TaskScoreInfo) baseVideoEntity.getData()).getScoreWatchMonth());
        } catch (NumberFormatException e2) {
            e2.printStackTrace();
            i2 = 0;
        }
        try {
            i3 = Integer.parseInt(((TaskScoreInfo) baseVideoEntity.getData()).getScoreMaxWatchDay());
        } catch (NumberFormatException e3) {
            e3.printStackTrace();
            i3 = 0;
        }
        try {
            i4 = Integer.parseInt(((TaskScoreInfo) baseVideoEntity.getData()).getScoreMaxWatchMonth());
        } catch (NumberFormatException e4) {
            e4.printStackTrace();
            i4 = 0;
        }
        if ("0".equals(((TaskScoreInfo) baseVideoEntity.getData()).getScoreTotalDay())) {
            smallVideoFragment.vfNotice.setVisibility(8);
            smallVideoFragment.tvDetailNotice.setVisibility(0);
        } else {
            smallVideoFragment.tvCountNotice.setText("今日获得\n" + ((TaskScoreInfo) baseVideoEntity.getData()).getScoreTotalDay() + "积分");
            smallVideoFragment.vfNotice.setVisibility(0);
            smallVideoFragment.tvDetailNotice.setVisibility(8);
            if (!z && i == i3) {
                UIUtils.toast("今日观看满5分钟，奖励10积分。");
            }
        }
        smallVideoFragment.countDownReady = i < i3 && i2 < i4;
        if (smallVideoFragment.countDownReady) {
            return;
        }
        smallVideoFragment.timerStop();
        smallVideoFragment.cpSchedule.setCurrentValue(0);
    }

    public static /* synthetic */ void lambda$getScoreInfo$2(SmallVideoFragment smallVideoFragment, Throwable th) throws Exception {
        UIUtils.logD(TAG, "获取当前任务积分失败。无法显示福利中心入口。");
        ViewFlipper viewFlipper = smallVideoFragment.vfNotice;
        if (viewFlipper != null && smallVideoFragment.tvDetailNotice != null) {
            viewFlipper.setVisibility(8);
            smallVideoFragment.tvDetailNotice.setVisibility(0);
        }
        smallVideoFragment.countDownReady = false;
        smallVideoFragment.timerStop();
        smallVideoFragment.cpSchedule.setCurrentValue(0);
    }

    public void timerStart() {
        setPlayerBtnStatus(false);
        if (this.videoDataType == VideoDataType.SmallVideo4Up || this.videoDataType == VideoDataType.SearchVideo) {
            return;
        }
        try {
            if (!this.countDownReady || videoIdIsExists(this.videoId)) {
                return;
            }
            if (this.taskScoreTimer != null) {
                this.taskScoreTimer.cancel();
                this.taskScoreTimer = null;
            }
            this.taskScoreTimer = new CountDownTimerC83521(this.countDown, 10);
            this.taskScoreTimer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class CountDownTimerC83521 extends CountDownTimer {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        CountDownTimerC83521(long j, long j2) {
            super(j, j2);
            SmallVideoFragment.this = r1;
        }

        @Override // android.os.CountDownTimer
        public void onTick(long j) {
            try {
                if (SmallVideoFragment.this.cpSchedule == null || SmallVideoFragment.this.taskScoreTimer == null || SmallVideoFragment.this.bdCloudVideoView == null) {
                    return;
                }
                if (SmallVideoFragment.this.bdCloudVideoView.getCurrentPosition() - SmallVideoFragment.this.bdCloudVideoView.getDuration() > -1000) {
                    UIUtils.logD(SmallVideoFragment.TAG, "播放进度到达视频长度，停止计时");
                    SmallVideoFragment.this.timerStop();
                }
                SmallVideoFragment.this.cpSchedule.setCurrentValue(Math.max(30000 - ((int) j), SmallVideoFragment.this.cpSchedule.getCurrentValue()));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // android.os.CountDownTimer
        public void onFinish() {
            try {
                UIUtils.logD(SmallVideoFragment.TAG, "onFinish()");
                SmallVideoFragment.this.managerAudienceLoadData.taskWatch30s(SmallVideoFragment.this.getVideoFromType(), SmallVideoFragment.this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$1$NPdYXrhBKlHHkvoGVWHHr-6GxfY
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.CountDownTimerC83521.lambda$onFinish$0(SmallVideoFragment.CountDownTimerC83521.this, (BaseVideoEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$1$psJXgEbMIZRuUO5e70GCFhpfHrQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        Throwable th = (Throwable) obj;
                        UIUtils.logD(SmallVideoFragment.TAG, "观看30s视频增加积分错误：" + th.getMessage());
                    }
                });
                SmallVideoFragment.this.countDown = 30000;
                SmallVideoFragment.this.cpSchedule.setCurrentValue(30000);
                SmallVideoFragment.this.taskScoreTimer.cancel();
                SmallVideoFragment.this.taskScoreTimer = null;
                SmallVideoFragment.this.timerStart();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$onFinish$0(CountDownTimerC83521 countDownTimerC83521, BaseVideoEntity baseVideoEntity) throws Exception {
            UIUtils.logD(SmallVideoFragment.TAG, "观看30s视频增加积分成功。调用查询积分接口.info=" + baseVideoEntity.getStatusCode() + "|message=" + baseVideoEntity.getMessage());
            SmallVideoFragment.this.getScoreInfo(false);
        }
    }

    public void timerStop() {
        try {
            if (this.taskScoreTimer != null) {
                UIUtils.logD(TAG, "timerStop");
                this.taskScoreTimer.cancel();
                if (this.cpSchedule != null) {
                    this.countDown = 30000 - this.cpSchedule.getCurrentValue();
                }
                this.taskScoreTimer = null;
                saveJson();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void timerPause() {
        try {
            setPlayerBtnStatus(true);
            UIUtils.logD(TAG, "timerPause");
            if (this.taskScoreTimer != null) {
                this.taskScoreTimer.cancel();
                if (this.cpSchedule != null) {
                    this.countDown = 30000 - this.cpSchedule.getCurrentValue();
                }
                this.taskScoreTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void clearOldVideoId() {
        try {
            if (this.videoIdsWatched != null) {
                Iterator<Map.Entry<String, String>> it = this.videoIdsWatched.entrySet().iterator();
                while (it.hasNext()) {
                    if (!NoticDateUtil.IsToday(it.next().getValue())) {
                        it.remove();
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void readJson() {
        try {
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            String string = sharePreferenceUtil.getString("SMALL_VIDEO_FULI_VIDEO_IDS" + UserManager.getInstance().getCurrentPhoneNumber());
            if (!TextUtils.isEmpty(string)) {
                UIUtils.logD(TAG, "看过的视频id==" + string);
                Gson gson = new Gson();
                Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.2
                    {
                        SmallVideoFragment.this = this;
                    }
                }.getType();
                this.videoIdsWatched = (Map) (!(gson instanceof Gson) ? gson.fromJson(string, type) : NBSGsonInstrumentation.fromJson(gson, string, type));
                clearOldVideoId();
                return;
            }
            this.videoIdsWatched = new HashMap(16);
        } catch (JsonSyntaxException e) {
            this.videoIdsWatched = new HashMap(16);
            e.printStackTrace();
        }
    }

    private void saveJson() {
        try {
            if (this.videoIdsWatched != null) {
                UIUtils.logD(TAG, "存储看过的视频id=" + this.videoId);
                Map<String, String> map = this.videoIdsWatched;
                String str = this.videoId;
                map.put(str, System.currentTimeMillis() + "");
                Gson gson = new Gson();
                Map<String, String> map2 = this.videoIdsWatched;
                String json = !(gson instanceof Gson) ? gson.toJson(map2) : NBSGsonInstrumentation.toJson(gson, map2);
                SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                sharePreferenceUtil.putString("SMALL_VIDEO_FULI_VIDEO_IDS" + UserManager.getInstance().getCurrentPhoneNumber(), json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean videoIdIsExists(String str) {
        try {
            boolean containsKey = this.videoIdsWatched.containsKey(str);
            if (containsKey) {
                try {
                    if (!NoticDateUtil.IsToday(this.videoIdsWatched.get(str))) {
                        return false;
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }
            return containsKey;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    @Override // android.support.p083v4.app.Fragment
    @Nullable
    public View onCreateView(@NonNull LayoutInflater layoutInflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        NBSFragmentSession.fragmentOnCreateViewBegin(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment", viewGroup);
        UIUtils.logD(TAG, "onCreateView");
        this.activityContext = (BaseActivity) getActivity();
        BaseActivity baseActivity = this.activityContext;
        this.mISmallVideoImpl = (ISmallVideo) baseActivity;
        this.managerAudienceLoadData = new ManagerAudienceLoadData(baseActivity);
        if (this.videoDataType == null) {
            UIUtils.logD(TAG, "onCreate设置为" + VideoDataType.SmallVideo);
            Log.d("lln", "看看哪个快onCreateView==" + this.videoDataType);
            this.videoDataType = VideoDataType.SmallVideo;
        }
        View view = this.fragmentCacheView;
        if (view != null) {
            ViewGroup viewGroup2 = (ViewGroup) view.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeView(this.fragmentCacheView);
            }
            View view2 = this.fragmentCacheView;
            NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment");
            return view2;
        }
        EventBusUtils.register(this);
        View inflate = layoutInflater.inflate(2131492973, viewGroup, false);
        this.baseLayout = (FrameLayout) inflate.findViewById(2131297006);
        GlideApp.with(this).load((Integer) 2131232734).into((ImageView) inflate.findViewById(2131297427));
        getWelfareUrl(inflate);
        getMoreConfigInfo();
        this.llEmpty = (LinearLayout) inflate.findViewById(2131297711);
        this.llEmpty.setVisibility(8);
        ((TextView) inflate.findViewById(2131298933)).setText("服务器开小差，没有请求到数据。");
        getHelpBtnInfo();
        this.smoothScroller = new LinearSmoothScroller(getActivity()) { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.3
            {
                SmallVideoFragment.this = this;
            }

            @Override // android.support.p086v7.widget.LinearSmoothScroller
            public float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return 80.0f / displayMetrics.densityDpi;
            }
        };
        initListener();
        initRecyclerView(inflate);
        initProgressBar();
        try {
            this.fragmentCacheView = inflate;
            this.f18480pd = new CustomePorgressDialog(this.activityContext);
            this.f18480pd.setMessage("加载中");
            this.f18480pd.setCanceledOnTouchOutside(true);
            this.f18480pd.setCancelable(true);
            if (!TextUtils.isEmpty(this.typeId) && this.mAdapter != null && this.mAdapter.getData().size() > 0) {
                UIUtils.logD(TAG, "onCreated");
                this.mCurrentItem = this.ringIndex;
                this.mAdapter.setFuYiPing(true);
                this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                startPlay(this.ringIndex);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"audienceMain".equals(this.fromType)) {
            lazyLoad();
        }
        View view3 = this.fragmentCacheView;
        NBSFragmentSession.fragmentOnCreateViewEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment");
        return view3;
    }

    public static /* synthetic */ boolean lambda$new$3(SmallVideoFragment smallVideoFragment, Message message) {
        smallVideoFragment.clearProgress();
        smallVideoFragment.startPlay(smallVideoFragment.mCurrentItem);
        return false;
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83664 implements SmallVideoAdapter.ItemClickedListener {
        public static /* synthetic */ void lambda$shagnpinInfo$1(Boolean bool) throws Exception {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void clicked(int i, SmallVideoEntity.Data data) {
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void toWelfareCenter() {
        }

        C83664() {
            SmallVideoFragment.this = r1;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void playVideo(int i) {
            MsLogUtil.m7979d(SmallVideoFragment.TAG, "playVideo=" + i);
            if (i != SmallVideoFragment.this.mCurrentItem || SmallVideoFragment.this.playHandler == null) {
                return;
            }
            MsLogUtil.m7979d(SmallVideoFragment.TAG, "playVideo=" + i + "|mCurrentItem=" + SmallVideoFragment.this.mCurrentItem);
            SmallVideoFragment.this.playHandler.removeMessages(0);
            SmallVideoFragment.this.playHandler.sendEmptyMessageDelayed(0, 300L);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void back() {
            SmallVideoFragment.this.mISmallVideoImpl.returnRingList();
            SmallVideoFragment.this.setLog("点击按钮", "19", "返回", "2");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void guanzhu(final int i, SmallVideoEntity.Data data) {
            try {
                SmallVideoFragment.this.managerAudienceLoadData.focusOnAnchor(data.getUserId(), data.getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$4$ze3-Fs7knkFKSEMy5PMGdoJtevg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.C83664.lambda$guanzhu$0(SmallVideoFragment.C83664.this, i, (String) obj);
                    }
                });
                SmallVideoFragment.this.setLog("点击按钮", "19", "关注", "2");
                PvCurrencyLogUtils.pvLogLive("", 2, "", "关注", "视频", "", "视频");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$guanzhu$0(C83664 c83664, int i, String str) throws Exception {
            try {
                String optString = new JSONObject(str).optString("statusCode");
                if ("0000".equals(optString)) {
                    UIUtils.toast("已成功关注主播");
                } else {
                    UIUtils.toast("关注主播失败");
                }
                View findViewByPosition = SmallVideoFragment.this.mLayoutManager.findViewByPosition(i);
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
                View findViewByPosition = SmallVideoFragment.this.mLayoutManager.findViewByPosition(i);
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
                    SmallVideoFragment.this.zanVideo(data.getVideoId(), "N", data.getContentType());
                    if (imageView2 != null) {
                        imageView2.setImageResource(2131231683);
                    }
                    if (imageView != null) {
                        imageView.setImageResource(2131231612);
                    }
                    int i2 = 0;
                    data.setHasZan(false);
                    try {
                        i2 = Integer.parseInt(data.getVideoPraiseNum()) - 1;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                    }
                    data.setVideoPraiseNum(i2 + "");
                    if (textView2 != null) {
                        textView2.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                    }
                    if (textView != null) {
                        textView.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
                    }
                    SmallVideoFragment.this.setLog("点击按钮", "19", "取消点赞", "2");
                    PvCurrencyLogUtils.pvLogLive("", 2, "", "取消点赞", "视频", "", "视频");
                    return;
                }
                SmallVideoFragment.this.handleZanAction(i, data);
            } catch (NumberFormatException e2) {
                e2.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void pinglun(int i, SmallVideoEntity.Data data) {
            SmallVideoFragment.this.pingLun(data);
            SmallVideoFragment.this.setLog("点击按钮", "19", "评论", "2");
            PvCurrencyLogUtils.pvLogLive("", 2, "", "评论", "视频", "", "视频");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void zhuanfa(int i, SmallVideoEntity.Data data, boolean z) {
            SmallVideoFragment.this.share(data);
            SmallVideoFragment.this.setLog("点击按钮", "19", z ? "预览页分享" : "分享", "2");
        }

        /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment$4$1 */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        class C83671 implements OnDialogBtnClickListener {
            final /* synthetic */ SmallVideoEntity.Data val$entity;

            public static /* synthetic */ void lambda$onClickGoodsLook$0(Boolean bool) throws Exception {
            }

            C83671(SmallVideoEntity.Data data) {
                C83664.this = r1;
                this.val$entity = data;
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickGoodsLook(GoodListEntity goodListEntity) {
                SmallVideoFragment.this.managerAudienceLoadData.goodsLog(this.val$entity.getUserId(), goodListEntity.getId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$4$1$x4i5jPpWNYK0gfRGLldxcQLd2es
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.C83664.C83671.lambda$onClickGoodsLook$0((Boolean) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                Intent intent = new Intent(SmallVideoFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(goodListEntity.getGoodsUrl());
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                SmallVideoFragment.this.startActivity(intent);
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickOrder() {
                IntentManager.gotoWebViewActivity(SmallVideoFragment.this.activityContext, URLSet.liveOrderMenu(), "我的订单");
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void shangpin(int i, SmallVideoEntity.Data data) {
            if (data.getGoodsData() == null || data.getGoodsData().size() <= 0) {
                return;
            }
            AudienceGoodsDialog.show(SmallVideoFragment.this.activityContext, data.getGoodsData(), new C83671(data));
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void shagnpinInfo(int i, SmallVideoEntity.Data data) {
            try {
                SmallVideoFragment.this.managerAudienceLoadData.goodsLog(data.getUserId(), data.getGoodsId(), "0").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$4$sWyFGmqGtILXbx_seoaXSxsbwBI
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.C83664.lambda$shagnpinInfo$1((Boolean) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                Intent intent = new Intent(SmallVideoFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(data.getGoodsLink());
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                SmallVideoFragment.this.startActivity(intent);
                SmallVideoFragment.this.setLog("点击按钮", "19", "商品卡片", "2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void zhuboxiangqing(int i, SmallVideoEntity.Data data) {
            try {
                String userIndexUrl = data.getUserIndexUrl();
                int videoType = SmallVideoFragment.this.mAdapter.getItem(i).getVideoType();
                if (TextUtils.isEmpty(userIndexUrl)) {
                    return;
                }
                Intent intent = new Intent(SmallVideoFragment.this.activityContext, WebDetailActivity.class);
                WebParamsEntity webParamsEntity = new WebParamsEntity();
                webParamsEntity.setUrl(SmallVideoFragment.this.addChannel(userIndexUrl, videoType == 1 ? "视频彩铃" : "小视频"));
                webParamsEntity.setType(SmallVideoFragment.this.fromType);
                webParamsEntity.setNeedTitle(true);
                webParamsEntity.setRequestType("get");
                intent.putExtra(WebFragment.webParams, webParamsEntity);
                SmallVideoFragment.this.startActivityForResult(intent, 1221);
                SmallVideoFragment.this.setLog("点击按钮", "19", "头像", "2");
                PvCurrencyLogUtils.pvLogLive("", 2, "", "头像", "视频", "", "视频");
            } catch (Exception e) {
                MsLogUtil.m7979d(SmallVideoFragment.TAG, e.getMessage());
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void backPortraitScreen() {
            SmallVideoFragment smallVideoFragment = SmallVideoFragment.this;
            smallVideoFragment.toggleFullScreen(smallVideoFragment.activityContext);
            PvCurrencyLogUtils.pvLogLive("", 2, "", "全屏观看", "视频", "", "视频");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void setRing(int i, SmallVideoEntity.Data data, String str) {
            try {
                SmallVideoFragment.this.setVideoRingtone(data.getVideoId(), str, data.getViewTitle());
                SmallVideoFragment.this.setLog("点击按钮", "19", str.equals("预览") ? "设置彩铃" : "预览页设置彩铃", "2");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void onPreview(boolean z, SmallVideoEntity.Data data) {
            try {
                int i = 8;
                if (!SmallVideoFragment.this.bdCloudVideoView.isPlaying()) {
                    SmallVideoFragment.this.bdCloudVideoView.start();
                    SmallVideoFragment.this.ivBtnPlay.setVisibility(8);
                    SmallVideoFragment.this.ivBtnPlay2.setVisibility(8);
                    SmallVideoFragment.this.rlAdPauseView.setVisibility(8);
                    SmallVideoFragment.this.rlAdPauseViewLandscape.setVisibility(8);
                }
                if (TextUtils.isEmpty(SmallVideoFragment.this.typeId)) {
                    SmallVideoFragment.this.mISmallVideoImpl.setTabVisibility(z ? 8 : 0);
                } else if (SmallVideoFragment.this.ivBack != null) {
                    SmallVideoFragment.this.ivBack.setVisibility(z ? 8 : 0);
                }
                SmallVideoFragment.this.mLayoutManager.setScrollEnabled(!z);
                if (SmallVideoFragment.this.ivBack != null) {
                    LikeView likeView = SmallVideoFragment.this.likeView;
                    if (!z) {
                        i = 0;
                    }
                    likeView.setVisibility(i);
                }
                if (z) {
                    SmallVideoFragment.this.hiBoardLog("19");
                    SmallVideoFragment.this.setLog("点击按钮", "19", "预览", "2");
                } else {
                    SmallVideoFragment.this.setLog("点击按钮", "19", "预览页返回", "2");
                }
                SmallVideoFragment.this.setPreview(z);
            } catch (Exception e) {
                e.printStackTrace();
            }
            PvCurrencyLogUtils.pvLogLive("", 2, "", "预览", "视频", "", "视频");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void showMore(final SmallVideoEntity.Data data) {
            if (SmallVideoFragment.this.mMoreConfigs != null) {
                VideoRingMoreDialog.show(SmallVideoFragment.this.activityContext, SmallVideoFragment.this.mMoreConfigs, SmallVideoFragment.this.fromType, new VideoRingMoreDialog.CallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.4.2
                    {
                        C83664.this = this;
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void pingLun() {
                        SmallVideoFragment.this.pingLun(data);
                        SmallVideoFragment.this.setLog("点击按钮", "19", "评论", "2");
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void share() {
                        SmallVideoFragment.this.share(data);
                        SmallVideoFragment.this.setLog("点击按钮", "19", "分享", "2");
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoRingMoreDialog.CallBack
                    public void click(String str) {
                        SmallVideoFragment.this.setLog("点击按钮", "19", str, "2");
                    }
                });
                SmallVideoFragment.this.setLog("点击按钮", "19", "更多", "2");
                PvCurrencyLogUtils.pvLogLive("", 2, "", "更多", "视频", "", "视频");
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener
        public void showHelp() {
            IntentManager.gotoWebViewActivity(SmallVideoFragment.this.activityContext, SmallVideoFragment.this.helpUrl, "");
            SmallVideoFragment.this.setLog("点击按钮", "19", "帮助", "2");
            PvCurrencyLogUtils.pvLogLive("", 2, "", "帮助", "视频", "", "视频");
        }
    }

    private void initListener() {
        this.adapterListener = new C83664();
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

    public void handleZanAction(int i, SmallVideoEntity.Data data) {
        TextView textView;
        ImageView imageView;
        ImageView imageView2;
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
            TextView textView2 = null;
            if (findViewByPosition != null) {
                ImageView imageView3 = (ImageView) findViewByPosition.findViewById(2131297530);
                textView = (TextView) findViewByPosition.findViewById(2131299158);
                imageView = (ImageView) findViewByPosition.findViewById(2131297529);
                textView2 = (TextView) findViewByPosition.findViewById(2131299157);
                imageView2 = imageView3;
            } else {
                textView = null;
                imageView = null;
                imageView2 = null;
            }
            zanVideo(data.getVideoId(), STATUS_Y, data.getContentType());
            data.setHasZan(true);
            String videoPraiseNum = data.getVideoPraiseNum();
            int i2 = 0;
            try {
                if (TextUtils.isEmpty(videoPraiseNum)) {
                    videoPraiseNum = "0";
                }
                i2 = Integer.parseInt(videoPraiseNum) + 1;
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
            data.setVideoPraiseNum(i2 + "");
            if (textView2 != null) {
                textView2.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
            }
            if (textView != null) {
                textView.setText(FormatUtils.getShowString(data.getVideoPraiseNum()));
            }
            if (imageView != null) {
                imageView.setImageResource(2131231684);
            }
            if (imageView2 != null) {
                imageView2.setImageResource(2131231615);
            }
            showZanAnimation(imageView);
            showZanAnimation(imageView2);
            setLog("点击按钮", "19", "点赞", "2");
            PvCurrencyLogUtils.pvLogLive("", 2, "", "点赞", "视频", "", "视频");
            if (this.videoDataType == VideoDataType.SmallVideo4Up || this.videoDataType == VideoDataType.SearchVideo) {
                return;
            }
            this.managerAudienceLoadData.taskVideoThumbsUp(getVideoFromType(), this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$XZfmBM7GBw2YD2gBrtkF6FNhWAw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$handleZanAction$4(SmallVideoFragment.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$wJmfoGD0SMv8lQ58DRSix9CdX_A
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    UIUtils.logD(SmallVideoFragment.TAG, "点赞送积分接口发生错误。");
                }
            });
        } catch (Exception e2) {
            MsLogUtil.m7980d(e2.getMessage());
        }
    }

    public static /* synthetic */ void lambda$handleZanAction$4(SmallVideoFragment smallVideoFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode()) && STATUS_Y.equals((String) baseVideoEntity.getData())) {
            UIUtils.toast("累计点赞3个视频，奖励1积分。");
            smallVideoFragment.getScoreInfo(true);
        }
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:47:0x00fe -> B:57:0x0101). Please submit an issue!!! */
    public void share(SmallVideoEntity.Data data) {
        JSONObject jSONObject = new JSONObject();
        String viewTitle = data.getViewTitle();
        String str = "来自中国联通APP-" + data.getUserName() + "主播";
        String str2 = data.getVideoUrl() + "&business_module=" + (data.getVideoType() + 1);
        String videoImg = data.getVideoImg();
        if (TextUtils.isEmpty(videoImg)) {
            videoImg = data.getTranscodeImg();
        }
        String videoTag = TextUtils.isEmpty(data.getVideoTag()) ? "创新·与智慧同行" : data.getVideoTag();
        String str3 = null;
        try {
            if (TextUtils.isEmpty(this.typeId)) {
                str3 = "wechat,wechatmoments,qq,qzone";
                jSONObject.put("shareType", "url");
                jSONObject.put("shareTitle", viewTitle);
                jSONObject.put("shareContent", videoTag);
                jSONObject.put("shareURL", URLSet.shareVideo(data.getVideoId()) + "&business_module=" + (data.getVideoType() + 1));
                jSONObject.put("shareIconURL", videoImg);
            } else {
                str3 = "wechat,wechatmoments,qq,qzone";
                jSONObject.put("shareType", "url");
                jSONObject.put("shareTitle", viewTitle);
                jSONObject.put("shareContent", str);
                jSONObject.put("shareURL", str2);
                jSONObject.put("shareIconURL", videoImg);
                jSONObject.put("miniProgramShare", "1");
                jSONObject.put("miniProgramType", "0");
                jSONObject.put("miniProgramUserName", "gh_2bab3e2deed1");
                jSONObject.put("miniProgramPath", "pages/stact/stvideo/video?videoId=" + data.getVideoId());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            ShareManager.ShowShareDialog(this.activityContext, str3, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.5
                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onCancel(String str4) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onComplete(String str4) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onError(String str4, String str5) {
                }

                {
                    SmallVideoFragment.this = this;
                }
            });
            hiBoardLog("5");
        } catch (Exception e2) {
            e2.printStackTrace();
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

    private void initLoadMore() {
        try {
            this.smartSwipeRefresh = SmartSwipeRefresh.translateMode(this.mRecyclerView, false, false).setDataLoader(new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.6
                @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
                public void onRefresh(SmartSwipeRefresh smartSwipeRefresh) {
                }

                {
                    SmallVideoFragment.this = this;
                }

                @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
                public void onLoadMore(SmartSwipeRefresh smartSwipeRefresh) {
                    try {
                        MsLogUtil.m7980d("加载下一页数据");
                        if (smartSwipeRefresh != null) {
                            smartSwipeRefresh.finished(true);
                        }
                    } catch (Exception e) {
                        MsLogUtil.m7979d(SmallVideoFragment.TAG, e.getMessage());
                    }
                }
            }).disableRefresh().setFooter(new LiveNoMoreDataFooter(this.activityContext));
            ((SlidingConsumer) this.smartSwipeRefresh.getSwipeConsumer().m17389as(SlidingConsumer.class)).addListener(new SimpleSwipeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.7
                {
                    SmallVideoFragment.this = this;
                }

                @Override // com.billy.android.swipe.listener.SimpleSwipeListener, com.billy.android.swipe.listener.SwipeListener
                public void onSwipeProcess(SmartSwipeWrapper smartSwipeWrapper, SwipeConsumer swipeConsumer, int i, boolean z, float f) {
                    if (f == 1.5d) {
                        try {
                            if (SmallVideoFragment.this.closer != null) {
                                SmallVideoFragment.this.closer.sendEmptyMessageDelayed(0, 1200L);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7979d(SmallVideoFragment.TAG, e.getMessage());
                        }
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    private void initRecyclerView(View view) {
        try {
            this.mRecyclerView = (RecyclerView) view.findViewById(2131296463);
            this.mAdapter = new SmallVideoAdapter(new ArrayList(), this.activityContext, this.adapterListener);
            this.mAdapter.setShowMoreBtn(this.mMoreConfigs != null);
            initLayoutManager();
            this.mRecyclerView.setLayoutManager(this.mLayoutManager);
            this.mRecyclerView.setAdapter(this.mAdapter);
            initLoadMore();
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void setPreview(boolean z) {
        View findViewByPosition;
        try {
            int i = 8;
            if (this.countDownView != null) {
                this.countDownView.setVisibility(z ? 8 : this.countViewStatus);
            }
            if (this.mLayoutManager == null || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null) {
                return;
            }
            View findViewById = findViewByPosition.findViewById(2131297796);
            if (!z) {
                i = this.mAdapter.getShowHelp();
            }
            findViewById.setVisibility(i);
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    private void getMoreConfigInfo() {
        String str;
        try {
            if (C836420.f18481xc39b7d0a[this.videoDataType.ordinal()] != 1) {
                str = "smallVideo";
            } else {
                str = this.fromType.equals(this.accessName) ? "videoCRBT" : "negativeOneScreen";
            }
            this.managerAudienceLoadData.getVideoRingRule(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$T2ClpHalFUUpfFRxjZfbJdGSeLk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getMoreConfigInfo$6(SmallVideoFragment.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$m8py-nxEFZH-kpvJsjnCc059_pg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getMoreConfigInfo$7(SmallVideoFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getMoreConfigInfo$6(SmallVideoFragment smallVideoFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode()) && baseVideoEntity.getData() != null && ((List) baseVideoEntity.getData()).size() > 0) {
            smallVideoFragment.mMoreConfigs = (List) baseVideoEntity.getData();
            if (smallVideoFragment.videoDataType == VideoDataType.Ringtone) {
                VideoMoreConfigEntity videoMoreConfigEntity = new VideoMoreConfigEntity();
                videoMoreConfigEntity.setConfName("分享");
                videoMoreConfigEntity.setConfIcon("share");
                smallVideoFragment.mMoreConfigs.add(0, videoMoreConfigEntity);
                VideoMoreConfigEntity videoMoreConfigEntity2 = new VideoMoreConfigEntity();
                videoMoreConfigEntity2.setConfName("评论");
                videoMoreConfigEntity2.setConfIcon("comment");
                smallVideoFragment.mMoreConfigs.add(0, videoMoreConfigEntity2);
            }
            SmallVideoAdapter smallVideoAdapter = smallVideoFragment.mAdapter;
            if (smallVideoAdapter != null) {
                smallVideoAdapter.setShowMoreBtn(true);
                smallVideoFragment.mAdapter.notifyItemChanged(smallVideoFragment.mCurrentItem);
                return;
            }
            return;
        }
        SmallVideoAdapter smallVideoAdapter2 = smallVideoFragment.mAdapter;
        if (smallVideoAdapter2 != null) {
            smallVideoAdapter2.setShowMoreBtn(false);
        }
    }

    public static /* synthetic */ void lambda$getMoreConfigInfo$7(SmallVideoFragment smallVideoFragment, Throwable th) throws Exception {
        SmallVideoAdapter smallVideoAdapter = smallVideoFragment.mAdapter;
        if (smallVideoAdapter != null) {
            smallVideoAdapter.setShowMoreBtn(false);
        }
    }

    private void initLayoutManager() {
        try {
            this.mLayoutManager = new ViewPagerLayoutManager(this.activityContext, 1);
            this.mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.9
                @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
                public void onLayoutComplete() {
                }

                @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
                public void onPageRelease(boolean z, int i) {
                }

                {
                    SmallVideoFragment.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.video.viewpager.OnViewPagerListener
                public void onPageSelected(int i, boolean z) {
                    UIUtils.logD("xcyTest", "position=" + i + "||mCurrentItem = " + SmallVideoFragment.this.mCurrentItem);
                    try {
                        if (SmallVideoFragment.this.likeView != null) {
                            SmallVideoFragment.this.likeView.removeAllViews();
                        }
                        int i2 = 0;
                        if (SmallVideoFragment.this.mCurrentItem != i) {
                            try {
                                SmallVideoFragment.this.setLog("滑动", "21", i > SmallVideoFragment.this.mCurrentItem ? "下一个视频切换" : "上一个视频切换", "3");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            SmallVideoFragment.this.isSetting = false;
                            SmallVideoFragment.this.playEndSetLog();
                            SmallVideoFragment.this.hiBoardLog("4");
                            SmallVideoFragment.this.leaveRoom();
                            SmallVideoFragment.this.mCurrentItem = i;
                            Log.d("lln", "会走这里播放吗");
                            SmallVideoFragment.this.startPlay(SmallVideoFragment.this.mCurrentItem);
                        }
                        if (!z || SmallVideoFragment.DATA_NO_MORE.equals(SmallVideoFragment.this.pageNum) || SmallVideoFragment.this.isLoadMore) {
                            return;
                        }
                        SmallVideoFragment.this.isLoadMore = true;
                        if (TextUtils.isEmpty(SmallVideoFragment.this.typeId)) {
                            switch (SmallVideoFragment.this.videoDataType) {
                                case SmallVideo:
                                    SmallVideoFragment.this.loadData();
                                    return;
                                case SmallVideo4Up:
                                    SmallVideoFragment.this.getPersonalCenterVideoList(SmallVideoFragment.this.userId, SmallVideoFragment.this.pageNum);
                                    return;
                                case SearchVideo:
                                    SmallVideoFragment.this.fromSearch();
                                    return;
                                default:
                                    return;
                            }
                        } else if ("4593".equals(SmallVideoFragment.this.typeId)) {
                        } else {
                            try {
                                i2 = Integer.parseInt(SmallVideoFragment.this.pageNum);
                            } catch (NumberFormatException e2) {
                                e2.printStackTrace();
                            }
                            SmallVideoFragment.this.getRecommend(i2);
                        }
                    } catch (NumberFormatException e3) {
                        e3.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void loadData() {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        }
        this.managerAudienceLoadData.getVideos(this.pageNum, this.singleVideo).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$wwq8Kq5MHpfxMlUs_AAJQ_7Hrgk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$loadData$8(SmallVideoFragment.this, (SmallVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$TOeFmcFvSqdI_YiMcbCyu8Dr7j4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$loadData$9((Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$loadData$8(SmallVideoFragment smallVideoFragment, SmallVideoEntity smallVideoEntity) throws Exception {
        try {
            if ("0000".equals(smallVideoEntity.getStatusCode())) {
                smallVideoFragment.pageNum = smallVideoEntity.getNextPageNum();
                if (smallVideoFragment.smartSwipeRefresh != null) {
                    smallVideoFragment.smartSwipeRefresh.finished(true);
                    smallVideoFragment.smartSwipeRefresh.setNoMoreData(DATA_NO_MORE.equals(smallVideoFragment.pageNum));
                }
                UIUtils.logD(TAG, "小视频加载列表数据完毕");
                ArrayList arrayList = new ArrayList();
                for (SmallVideoEntity.Data data : smallVideoEntity.getData()) {
                    arrayList.add(data.getVideoUrl());
                }
                smallVideoFragment.mISmallVideoImpl.preload(arrayList);
                if (smallVideoFragment.adList.size() < 3) {
                    UIUtils.logD(TAG, "小视频加载广告数据");
                    smallVideoFragment.loadAdView();
                }
                if (smallVideoFragment.mAdapter != null) {
                    UIUtils.logD(TAG, "小视频列表adapter初始化成功");
                    if (smallVideoFragment.isLoadMore) {
                        UIUtils.logD(TAG, "小视频加载更多");
                        smallVideoFragment.mAdapter.addData(smallVideoEntity.getData());
                        smallVideoFragment.isLoadMore = false;
                        return;
                    }
                    UIUtils.logD(TAG, "小视频列表更新数据完成");
                    if (smallVideoFragment.videoDataType == VideoDataType.CustomVideo) {
                        smallVideoFragment.getSteamVideoData(smallVideoEntity.getData(), smallVideoFragment.mCurrentItem);
                        return;
                    }
                    smallVideoFragment.mAdapter.setNewData(smallVideoEntity.getData());
                    smallVideoFragment.mRecyclerView.scrollToPosition(smallVideoFragment.mCurrentItem);
                    smallVideoFragment.startPlay(smallVideoFragment.mCurrentItem);
                    return;
                }
                smallVideoFragment.mAdapter = new SmallVideoAdapter(smallVideoEntity.getData(), smallVideoFragment.activityContext, smallVideoFragment.adapterListener);
                smallVideoFragment.mRecyclerView.setAdapter(smallVideoFragment.mAdapter);
                smallVideoFragment.llEmpty.setVisibility(smallVideoEntity.getData().size() == 0 ? 0 : 8);
                return;
            }
            smallVideoFragment.llEmpty.setVisibility(smallVideoFragment.mAdapter.getItemCount() == 0 ? 0 : 8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$loadData$9(Throwable th) throws Exception {
        UIUtils.toast("加载失败，请稍后再试");
        th.printStackTrace();
    }

    private void showAdView(View view, final int i) {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition == null) {
                this.reSetVideo = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$CkKpBgKpW4ATHj5nOjGRgUnTrXg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.lambda$showAdView$10(SmallVideoFragment.this, i, (Long) obj);
                    }
                });
                return;
            }
            LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131296985);
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            linearLayout.addView(view);
            if ("Wifi".equals(DeviceHelper.getNETType(this.activityContext))) {
                return;
            }
            ToastUtil.showToast("此内容不免流");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showAdView$10(SmallVideoFragment smallVideoFragment, int i, Long l) throws Exception {
        smallVideoFragment.setVideoSizeView(i);
        Disposable disposable = smallVideoFragment.reSetVideo;
        if (disposable != null) {
            disposable.dispose();
            smallVideoFragment.reSetVideo = null;
        }
    }

    private void loadAdView() {
        try {
            if (OptionValveUtil.INSTENCE.isShowDrawAD()) {
                UIUtils.logD("xcy", "开始加载draw广告" + this.adList.size() + "");
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("946713139");
                AdFactory.getAd(this.activityContext, adConfigEntity).loadDraw(new IAdInterface.IDrawCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$HwcCl88grmaam51VAcMdUuizmSs
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IDrawCallBack
                    public final void onCallBack(View view) {
                        SmallVideoFragment.lambda$loadAdView$11(SmallVideoFragment.this, view);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$loadAdView$11(SmallVideoFragment smallVideoFragment, View view) {
        try {
            if (view != null) {
                smallVideoFragment.adList.add(view);
                if (smallVideoFragment.adList.size() == 1) {
                    SmallVideoEntity.Data data = new SmallVideoEntity.Data();
                    data.setVideoType(2);
                    data.setAdView(smallVideoFragment.adList.get(0));
                    smallVideoFragment.mAdapter.getData().add(data);
                    smallVideoFragment.mAdapter.notifyItemInserted(smallVideoFragment.mAdapter.getData().size() - 1);
                    UIUtils.logD("xcy", "成功加载：" + smallVideoFragment.adList.size() + "");
                } else if (smallVideoFragment.adList.size() == 2) {
                    SmallVideoEntity.Data data2 = new SmallVideoEntity.Data();
                    data2.setVideoType(2);
                    data2.setAdView(smallVideoFragment.adList.get(1));
                    if (smallVideoFragment.mAdapter.getData().size() > 10) {
                        smallVideoFragment.mAdapter.getData().add(data2);
                        smallVideoFragment.mAdapter.notifyItemInserted(smallVideoFragment.mAdapter.getData().size() - 1);
                        UIUtils.logD("xcy", "成功加载：" + smallVideoFragment.adList.size() + "");
                    }
                } else if (smallVideoFragment.adList.size() == 3) {
                    SmallVideoEntity.Data data3 = new SmallVideoEntity.Data();
                    data3.setVideoType(2);
                    data3.setAdView(smallVideoFragment.adList.get(2));
                    if (smallVideoFragment.mAdapter.getData().size() > 15) {
                        smallVideoFragment.mAdapter.getData().add(data3);
                        smallVideoFragment.mAdapter.notifyItemInserted(smallVideoFragment.mAdapter.getData().size() - 1);
                        UIUtils.logD("xcy", "成功加载：" + smallVideoFragment.adList.size() + "");
                    }
                }
            } else {
                UIUtils.logD("xcy", "ad view == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAdFypMap(int i) {
        try {
            SmallVideoEntity.Data data = new SmallVideoEntity.Data();
            data.setVideoType(2);
            int i2 = i + 5;
            this.drawAdFypMap.put(Integer.valueOf(i2), data);
            SmallVideoEntity.Data data2 = new SmallVideoEntity.Data();
            data2.setVideoType(2);
            this.drawAdFypMap.put(Integer.valueOf(i + 11), data2);
            SmallVideoEntity.Data data3 = new SmallVideoEntity.Data();
            data3.setVideoType(2);
            int i3 = i + 17;
            this.drawAdFypMap.put(Integer.valueOf(i3), data3);
            this.adFypMax = i3;
            loadAdFypView(i2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadAdFypView(final int i) {
        this.isLoadingAd = true;
        UIUtils.logD("xcyTestAD", "开始加载负一屏draw广告:" + i);
        try {
            if (OptionValveUtil.INSTENCE.isShowDrawAD()) {
                if (i > this.adFypMax) {
                    UIUtils.logD("xcyTestAD", "已加载完全部广告");
                    return;
                }
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("946713185");
                AdFactory.getAd(this.activityContext, adConfigEntity).loadDraw(new IAdInterface.IDrawCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$sY21UpsCZQiVUL_gNJ5bH94Soj8
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IDrawCallBack
                    public final void onCallBack(View view) {
                        SmallVideoFragment.lambda$loadAdFypView$12(SmallVideoFragment.this, i, view);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.isLoadingAd = false;
        }
    }

    public static /* synthetic */ void lambda$loadAdFypView$12(SmallVideoFragment smallVideoFragment, int i, View view) {
        try {
            if (view != null) {
                UIUtils.logD("xcyTestAD", "加载负一屏广告成功,位置=" + i);
                SmallVideoEntity.Data data = smallVideoFragment.drawAdFypMap.get(Integer.valueOf(i));
                if (data != null && data.getAdView() == null) {
                    data.setAdView(view);
                    if (smallVideoFragment.mAdapter.getData().size() > i && smallVideoFragment.mCurrentItem < i) {
                        UIUtils.logD("xcyTestAD", "插入位置：" + i);
                        smallVideoFragment.mAdapter.getData().add(i, smallVideoFragment.drawAdFypMap.remove(Integer.valueOf(i)));
                        smallVideoFragment.mAdapter.notifyItemInserted(i);
                        UIUtils.logD("xcyTestAD", "size=" + smallVideoFragment.mAdapter.getData().size());
                    }
                    smallVideoFragment.loadAdFypView(i + 5);
                }
            } else {
                UIUtils.logD("xcyTestAD", "ad view == null");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        smallVideoFragment.isLoadingAd = false;
    }

    private void insertAdFypView() {
        try {
            for (Map.Entry<Integer, SmallVideoEntity.Data> entry : this.drawAdFypMap.entrySet()) {
                int intValue = entry.getKey().intValue();
                if (entry.getValue().getAdView() == null) {
                    if (!this.isLoadingAd) {
                        loadAdFypView(intValue);
                    }
                } else if (this.mAdapter.getData().size() > intValue && this.mCurrentItem < intValue) {
                    UIUtils.logD("xcyTestAD", "延后插入：" + intValue);
                    this.mAdapter.getData().add(intValue, this.drawAdFypMap.remove(Integer.valueOf(intValue)));
                    this.mAdapter.notifyItemInserted(intValue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void loadPauseAdView() {
        try {
            if (OptionValveUtil.INSTENCE.isShowPauseAD()) {
                if (TextUtils.isEmpty(this.typeId)) {
                    if (this.pauseAdList.size() > 0) {
                        displayPauseAd(getAdView());
                    }
                } else if (this.pauseAdListF.size() > 0) {
                    displayPauseAd(getAdViewF());
                }
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId(TextUtils.isEmpty(this.typeId) ? "946588762" : "946588780");
                adConfigEntity.setBannerWidth(265);
                adConfigEntity.setBannerHeight(94);
                AdFactory.getAd(this.activityContext, adConfigEntity).loadInteraction(new IAdInterface.IInteractionCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.10
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onClose() {
                    }

                    {
                        SmallVideoFragment.this = this;
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onResult(int i, View view) {
                        UIUtils.logD("xcy", "code=" + i);
                        try {
                            if (SmallVideoFragment.this.bdCloudVideoView != null && !SmallVideoFragment.this.bdCloudVideoView.isPlaying()) {
                                if (TextUtils.isEmpty(SmallVideoFragment.this.typeId)) {
                                    if (view != null) {
                                        if (SmallVideoFragment.this.pauseAdList.size() == 0) {
                                            UIUtils.logD("xcy", "加载网络随机广告");
                                            SmallVideoFragment.this.displayPauseAd(view);
                                        } else {
                                            UIUtils.logD("xcy", "已显示随机广告");
                                        }
                                    } else {
                                        UIUtils.logD("xcy", "ad view == null");
                                        if (SmallVideoFragment.this.pauseAdList.size() == 0) {
                                            SmallVideoFragment.this.rlAdPauseView.setVisibility(8);
                                            SmallVideoFragment.this.rlAdPauseViewLandscape.setVisibility(8);
                                        }
                                    }
                                } else if (view != null) {
                                    if (SmallVideoFragment.this.pauseAdListF.size() == 0) {
                                        SmallVideoFragment.this.displayPauseAd(view);
                                    }
                                } else if (SmallVideoFragment.this.pauseAdListF.size() == 0) {
                                    SmallVideoFragment.this.rlAdPauseView.setVisibility(8);
                                    SmallVideoFragment.this.rlAdPauseViewLandscape.setVisibility(8);
                                }
                            } else {
                                UIUtils.logD("xcy", "视频正在播放");
                            }
                            if (view != null) {
                                if (TextUtils.isEmpty(SmallVideoFragment.this.typeId)) {
                                    SmallVideoFragment.this.pauseAdList.add(view);
                                } else {
                                    SmallVideoFragment.this.pauseAdListF.add(view);
                                }
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

    public void displayPauseAd(View view) {
        try {
            if (this.mISmallVideoImpl.getScreenOrientation(this.activityContext) == 0) {
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
            MsLogUtil.m7979d(TAG, e.getMessage());
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

    private View getAdViewF() {
        try {
            return this.pauseAdListF.get(new Random().nextInt(this.pauseAdListF.size()));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
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
        UIUtils.logD(TAG, "小视频lazyLoad");
        playLive();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.fragment.BaseFragment
    public void clearData() {
        SmallVideoAdapter smallVideoAdapter = this.mAdapter;
        if (smallVideoAdapter != null) {
            smallVideoAdapter.setNewData(new ArrayList());
        }
    }

    public void playLive() {
        try {
            getSDKAccessCode();
            UIUtils.logD(TAG, "playLive，videoDataType= " + this.videoDataType);
            uploadLog();
            int i = 0;
            if (TextUtils.isEmpty(this.typeId)) {
                if (this.videoDataType == VideoDataType.SmallVideo) {
                    this.pageNum = "1";
                    this.mCurrentItem = 0;
                    loadData();
                    return;
                } else if (this.videoDataType == VideoDataType.CustomVideo) {
                    this.pageNum = "1";
                    this.mCurrentItem = 0;
                    loadData();
                    return;
                } else {
                    loadVideosData();
                    return;
                }
            }
            List<SmallVideoEntity.Data> rings2SmallVideo = rings2SmallVideo(this.ringList);
            if (rings2SmallVideo.size() == 0) {
                getRecommend(1);
                return;
            }
            if (TextUtils.isEmpty(this.accessName)) {
                initAdFypMap(this.ringIndex);
            }
            if (this.mAdapter != null) {
                this.mAdapter.setFuYiPing(true);
                this.mAdapter.setNewData(rings2SmallVideo);
                this.mCurrentItem = this.ringIndex;
                this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                return;
            }
            this.mAdapter = new SmallVideoAdapter(rings2SmallVideo, this.activityContext, this.adapterListener);
            this.mRecyclerView.setAdapter(this.mAdapter);
            LinearLayout linearLayout = this.llEmpty;
            if (rings2SmallVideo.size() != 0) {
                i = 8;
            }
            linearLayout.setVisibility(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playVideo() {
        try {
            playLive();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    private void loadVideosData() {
        UIUtils.logD(TAG, "加载个人中心传入的小视频播放列表或者搜索进入");
        Observable.create(new ObservableOnSubscribe() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$v9w1oxjLjCqrOcdWUvQnpVtUv3c
            @Override // io.reactivex.ObservableOnSubscribe
            public final void subscribe(ObservableEmitter observableEmitter) {
                observableEmitter.onNext(SmallVideoFragment.this.rawData);
            }
        }).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new SmallVideoItemFunction()).observeOn(AndroidSchedulers.mainThread()).subscribe(new $$Lambda$SmallVideoFragment$JIOUNabwCjYIQKQZVus7aHOLoFg(this), new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$9vnrEGeG6FF4a-tCtZMLgDzsH80
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                UIUtils.logD(SmallVideoFragment.TAG, "个人中心小视频列表解析失败：" + th.getMessage());
            }
        });
    }

    @Override // android.support.p083v4.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        try {
            UIUtils.logD(TAG, "onActivityResult ");
            if (i == 1221) {
                UIUtils.logD(TAG, "个人中心返回 ");
                this.isFromPersonalCenterBack = true;
                getGuanZhuStatus(this.mCurrentItem);
            } else {
                UIUtils.logD("test", "获取评论数量");
                this.managerAudienceLoadData.getCommentList(this.mAdapter.getItem(this.mCurrentItem).getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$viNeWoPVr5MY0Q9Rju-kFrU6J6o
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.lambda$onActivityResult$15(SmallVideoFragment.this, (LiveCommentEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$VYQS8AjCABQvv_fRNmzdKQXqbVI
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.lambda$onActivityResult$16((Throwable) obj);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$onActivityResult$15(SmallVideoFragment smallVideoFragment, LiveCommentEntity liveCommentEntity) throws Exception {
        if ("0000".equals(liveCommentEntity.getCode())) {
            SmallVideoEntity.Data item = smallVideoFragment.mAdapter.getItem(smallVideoFragment.mCurrentItem);
            item.setCommentNum(liveCommentEntity.getCommentList().size() + "");
            View findViewByPosition = smallVideoFragment.mLayoutManager.findViewByPosition(smallVideoFragment.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            ((TextView) findViewByPosition.findViewById(2131298917)).setText(liveCommentEntity.getCommentList().size() + "");
        }
    }

    public static /* synthetic */ void lambda$onActivityResult$16(Throwable th) throws Exception {
        UIUtils.logD("test", "获取评论数量错误");
        th.printStackTrace();
    }

    public void startPlay(int i) {
        try {
            this.playBeginTime = System.currentTimeMillis();
            startTiming();
            MsLogUtil.m7979d(TAG, "播放视频：startPlay（" + i + ")");
            if (!DeviceHelper.isNetworkAvailable(getActivity()) && !this.toasted) {
                ToastUtil.showToast("网络已断开");
                this.toasted = true;
            }
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition != null) {
                this.rlComfort = (LinearLayout) findViewByPosition.findViewById(2131298331);
            }
            if (this.rlComfort != null) {
                this.rlComfort.setVisibility(8);
            }
            SmallVideoEntity.Data item = this.mAdapter.getItem(i);
            insertAdFypView();
            if (item.getVideoType() == 2) {
                UIUtils.logD("xcyTestAD", "======进入=====");
                if (item.getAdView() != null) {
                    showAdView(item.getAdView(), i);
                    return;
                }
                return;
            }
            this.videoId = item.getVideoId();
            int i2 = C836420.f18481xc39b7d0a[this.videoDataType.ordinal()];
            if (i2 == 1) {
                setVideoNum(this.videoId);
                if (this.mAdapter.getItem(this.mCurrentItem).getVideoType() == 1 && !this.mAdapter.getItem(this.mCurrentItem).isHasAddCount()) {
                    getVideoNum();
                }
                getVideoInfo(i, this.videoId, item.getContentType());
            } else {
                switch (i2) {
                    case 3:
                    case 4:
                        if (!TextUtils.isEmpty(item.getVideoUrl()) && !TextUtils.isEmpty(item.getUserName())) {
                            playVideo(i);
                            break;
                        } else {
                            getVideoData(i);
                            break;
                        }
                        break;
                    default:
                        playVideo(i);
                        break;
                }
            }
            if ("314d970131b64ea0bc4ea98fa07b247b".equals(this.videoId)) {
                getComFort();
            } else if (this.rlComfort != null) {
                this.rlComfort.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void playVideo(final int i) {
        try {
            MsLogUtil.m7979d(TAG, "playVideo(" + i + ")");
            setVideoNum(this.videoId);
            if (this.mAdapter.getItem(this.mCurrentItem).getVideoType() == 1 && !this.mAdapter.getItem(this.mCurrentItem).isHasAddCount()) {
                getVideoNum();
            }
            this.bdCloudVideoView = this.mISmallVideoImpl.playVideo(this.mAdapter.getItem(i).getVideoUrl());
            setLogPlaying();
            getAnchorInfo(i);
            setZanStatus(this.mAdapter.getData().get(i).getVideoId());
            getGuanZhuStatus(i);
            this.managerAudienceLoadData.getCommentList(this.mAdapter.getItem(i).getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$K4PW4heBhAxAjEL8Iq9mrBpfCbE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$playVideo$17(SmallVideoFragment.this, i, (LiveCommentEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$IpJYooYv0iowkDUZaAKIlVYu66g
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$playVideo$18((Throwable) obj);
                }
            });
            setVideoSizeView(i);
            if (STATUS_Y.equals(this.mAdapter.getItem(i).getIsShow())) {
                showMasking();
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$playVideo$17(SmallVideoFragment smallVideoFragment, int i, LiveCommentEntity liveCommentEntity) throws Exception {
        if ("0000".equals(liveCommentEntity.getCode())) {
            smallVideoFragment.mAdapter.getData().get(i).setCommentNum(liveCommentEntity.getCommentList().size() + "");
            View findViewByPosition = smallVideoFragment.mLayoutManager.findViewByPosition(i);
            if (findViewByPosition != null) {
                ((TextView) findViewByPosition.findViewById(2131298917)).setText(liveCommentEntity.getCommentList().size() + "");
            }
        }
    }

    public static /* synthetic */ void lambda$playVideo$18(Throwable th) throws Exception {
        UIUtils.logD("test", "获取评论数量错误");
        th.printStackTrace();
    }

    private void getComFort() {
        try {
            this.managerAudienceLoadData.getComFort().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$UZewKnqh89fj9kYPmHdN62gs0dY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getComFort$19(SmallVideoFragment.this, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$iFxh3SzFyCWXuP7FzI8hqlJe64Y
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getComFort$20(SmallVideoFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getComFort$19(SmallVideoFragment smallVideoFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("200".equals(baseVideoEntity.getStatusCode())) {
            smallVideoFragment.rlComfort.setVisibility(0);
            View findViewByPosition = smallVideoFragment.mLayoutManager.findViewByPosition(smallVideoFragment.mCurrentItem);
            if (findViewByPosition != null) {
                smallVideoFragment.reclComfort = (ComFortView) findViewByPosition.findViewById(2131298262);
                smallVideoFragment.reclComfort.setLayoutManager(new LinearLayoutManager(smallVideoFragment.getContext(), 1, false));
                smallVideoFragment.reclComfort.setAdapter(new ComFortAdapter(smallVideoFragment.getContext(), baseVideoEntity));
                smallVideoFragment.reclComfort.start();
            }
        }
    }

    public static /* synthetic */ void lambda$getComFort$20(SmallVideoFragment smallVideoFragment, Throwable th) throws Exception {
        th.printStackTrace();
        smallVideoFragment.rlComfort.setVisibility(8);
    }

    private void getGuanZhuStatus(final int i) {
        try {
            this.managerAudienceLoadData.isGuanzhu(this.mAdapter.getItem(i).getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$cLlOT5r-HVUbU3mtIdpWrkGB3GE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getGuanZhuStatus$21(SmallVideoFragment.this, i, (Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getGuanZhuStatus$21(SmallVideoFragment smallVideoFragment, int i, Boolean bool) throws Exception {
        smallVideoFragment.mAdapter.getItem(i).setFocusOn(bool.booleanValue());
        View findViewByPosition = smallVideoFragment.mLayoutManager.findViewByPosition(i);
        if (findViewByPosition == null) {
            return;
        }
        findViewByPosition.findViewById(2131297390).setVisibility(bool.booleanValue() ? 8 : 0);
    }

    private void isFullView(TextView textView) {
        try {
            if ((this.activityContext.getResources().getConfiguration().screenLayout & 15) != 3 || Build.VERSION.SDK_INT < 24 || this.activityContext.isInMultiWindowMode()) {
                return;
            }
            textView.setVisibility(8);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void getAnchorInfo(final int i) {
        try {
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
            }
            this.managerAudienceLoadData.getVideoInfo(this.mAdapter.getItem(i).getVideoId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$-C-gkkuDo7X4mLk5knBgvCZYMME
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.this.mAdapter.getItem(i).setUserIndexUrl(((SmallVideoInfoEntity) obj).getData().getUserIndexUrl());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public void setVideoSizeView() {
        try {
            if (this.addPlayer2UIHandler != null) {
                this.addPlayer2UIHandler.sendEmptyMessageDelayed(200, 500L);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    private void setVideoSizeView(final int i) {
        MsLogUtil.m7979d(TAG, "setVideoSizeView");
        try {
            if (this.bdCloudVideoView == null) {
                MsLogUtil.m7979d(TAG, "setVideoSizeView，播放器为空");
            } else if (this.bdCloudVideoView.isInWebView) {
                MsLogUtil.m7979d("小窗测试", "开启小窗后，不抢位置");
            } else {
                ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
                View findViewByPosition = this.mLayoutManager.findViewByPosition(i);
                if (findViewByPosition == null) {
                    this.reSetVideo = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$Or-m7fBDkpjmSxjN8AMwr8Gp820
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            SmallVideoFragment.lambda$setVideoSizeView$23(SmallVideoFragment.this, i, (Long) obj);
                        }
                    });
                    return;
                }
                int videoWidth = this.bdCloudVideoView.getVideoWidth();
                int videoHeight = this.bdCloudVideoView.getVideoHeight();
                SmallVideoEntity.Data item = this.mAdapter.getItem(i);
                if (item != null && item.getImgWidth() > 0 && !this.bdCloudVideoView.isPlaying()) {
                    this.addPlayer2UIHandler.sendEmptyMessage(33);
                }
                MsLogUtil.m7979d(TAG, "视频宽：" + videoWidth + "|视频高：" + videoHeight);
                if (videoWidth < 1) {
                    ViewGroup viewGroup2 = (ViewGroup) this.bdCloudVideoView.getParent();
                    if (viewGroup2 != null && viewGroup2 != this.baseLayout) {
                        viewGroup2.removeView(this.bdCloudVideoView);
                    }
                    MsLogUtil.m7979d("小窗测试", "小视频将播放器放到下层");
                    this.baseLayout.addView(this.bdCloudVideoView);
                    return;
                }
                this.bdCloudVideoView.setVideoScalingMode(2);
                FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
                this.ivBtnPlay = findViewByPosition.findViewById(2131297359);
                int i2 = 8;
                this.ivBtnPlay.setVisibility(8);
                this.ivBtnPlay2 = findViewByPosition.findViewById(2131297360);
                this.ivBtnPlay2.setVisibility(8);
                this.flAdPauseView = (FrameLayout) findViewByPosition.findViewById(2131296983);
                this.rlAdPauseView = (RelativeLayout) findViewByPosition.findViewById(2131298305);
                this.flAdPauseViewLandscape = (FrameLayout) findViewByPosition.findViewById(2131296984);
                this.rlAdPauseViewLandscape = (RelativeLayout) findViewByPosition.findViewById(2131298306);
                this.rlInfoArea = (RelativeLayout) findViewByPosition.findViewById(2131298344);
                this.rlAdPauseView.setVisibility(8);
                this.rlAdPauseViewLandscape.setVisibility(8);
                TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
                textView.setVisibility(8);
                LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297764);
                if (videoWidth > videoHeight && this.mISmallVideoImpl.getScreenOrientation(this.activityContext) == 1) {
                    frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296396);
                    if (this.bdCloudVideoView.isPlaying() && linearLayout.getVisibility() == 8) {
                        textView.setVisibility(0);
                        isFullView(textView);
                        this.mAdapter.getData().get(i).setShowFull(textView.getVisibility() == 0);
                    }
                    this.bdCloudVideoView.setVideoScalingMode(1);
                }
                if (frameLayout != null) {
                    MsLogUtil.m7979d("小窗测试", "将小视频加入正常播放位置");
                    frameLayout.addView(this.bdCloudVideoView);
                }
                initLikeView(findViewByPosition);
                View findViewById = findViewByPosition.findViewById(isWoMusic() ? 2131297717 : 2131297716);
                if (!"Wifi".equals(DeviceHelper.getNETType(this.activityContext)) && !UserManager.getInstance().isYiwang()) {
                    i2 = 0;
                }
                findViewById.setVisibility(i2);
                this.ivBack = (ImageView) findViewByPosition.findViewById(2131297344);
                setProgressBarLandscape(false);
                addProgressTo((FrameLayout) findViewByPosition.findViewById(2131297007));
                this.addPlayer2UIHandler.sendEmptyMessage(32);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setVideoSizeView$23(SmallVideoFragment smallVideoFragment, int i, Long l) throws Exception {
        smallVideoFragment.setVideoSizeView(i);
        Disposable disposable = smallVideoFragment.reSetVideo;
        if (disposable != null) {
            disposable.dispose();
            smallVideoFragment.reSetVideo = null;
        }
    }

    public static /* synthetic */ boolean lambda$new$24(SmallVideoFragment smallVideoFragment, Message message) {
        try {
            switch (message.what) {
                case 32:
                    View findViewByPosition = smallVideoFragment.mLayoutManager.findViewByPosition(smallVideoFragment.mCurrentItem);
                    if (findViewByPosition != null && smallVideoFragment.bdCloudVideoView.isPlaying()) {
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
                    View findViewByPosition2 = smallVideoFragment.mLayoutManager.findViewByPosition(smallVideoFragment.mCurrentItem);
                    if (findViewByPosition2 != null) {
                        SmallVideoEntity.Data item = smallVideoFragment.mAdapter.getItem(smallVideoFragment.mCurrentItem);
                        View findViewById4 = findViewByPosition2.findViewById(2131296393);
                        if (findViewById4 != null) {
                            findViewById4.setVisibility(item.getImgWidth() >= item.getImgHeight() ? 8 : 0);
                        }
                        View findViewById5 = findViewByPosition2.findViewById(2131296394);
                        if (findViewById5 != null) {
                            findViewById5.setVisibility(item.getImgWidth() > item.getImgHeight() ? 0 : 8);
                        }
                        View findViewById6 = findViewByPosition2.findViewById(2131299478);
                        if (findViewById6 != null) {
                            findViewById6.setVisibility(item.getImgWidth() > item.getImgHeight() ? 0 : 8);
                            break;
                        }
                    }
                    break;
                default:
                    try {
                        if (smallVideoFragment.mISmallVideoImpl != null) {
                            if (smallVideoFragment.mISmallVideoImpl.getScreenOrientation(smallVideoFragment.activityContext) == 0) {
                                smallVideoFragment.setLandscapePlayer();
                            } else {
                                smallVideoFragment.setVideoSizeView(smallVideoFragment.mCurrentItem);
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
            LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297726);
            TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
            LinearLayout linearLayout2 = (LinearLayout) findViewByPosition.findViewById(2131297733);
            FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131296988);
            this.flProgressBarAreaLandscape = (FrameLayout) findViewByPosition.findViewById(2131297008);
            this.llLandscapeTitle = findViewByPosition.findViewById(2131297735);
            this.ivBtnPlay2.setVisibility(8);
            this.ivBtnPlay.setVisibility(8);
            this.mLayoutManager.setScrollEnabled(false);
            if (this.smartSwipeRefresh != null) {
                this.smartSwipeRefresh.disableLoadMore();
            }
            linearLayout.setVisibility(8);
            textView.setVisibility(8);
            linearLayout2.setVisibility(0);
            final boolean z = App.getSharePreferenceUtil().getBoolean("bright_volume_show");
            final View findViewById = findViewByPosition.findViewById(2131298334);
            findViewById.setVisibility(z ? 8 : 0);
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$s0Tpye6KvigfFqu6-8cgSuMOG0E
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SmallVideoFragment.lambda$setLandscapePlayer$25(findViewById, z, view);
                }
            });
            if (!z) {
                App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
            }
            this.bdCloudVideoView.setVideoScalingMode(1);
            if (this.countDownView != null) {
                this.countDownView.setVisibility(8);
            }
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
            setLog("点击按钮", "19", "点击全屏", "2");
            ViewGroup viewGroup2 = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            MsLogUtil.m7979d("小窗测试", "播放器加入横屏布局");
            frameLayout.addView(this.bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setLandscapePlayer$25(View view, boolean z, View view2) {
        view.setVisibility(8);
        if (z) {
            return;
        }
        App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
    }

    private boolean isWoMusic() {
        return this.videoDataType == VideoDataType.Ringtone;
    }

    public void autoHideLandscape() {
        if (this.playTime != null) {
            MsLogUtil.m7979d(TAG, "自动隐藏");
            this.playTime.removeMessages(5000);
            this.playTime.sendEmptyMessageDelayed(5000, 5000L);
        }
    }

    private void initLikeView(View view) {
        try {
            this.likeView = (LikeView) view.findViewById(2131297988);
            this.doubleClickListener = new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.11
                {
                    SmallVideoFragment.this = this;
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void onClick(long j) {
                    try {
                        MsLogUtil.m7979d(SmallVideoFragment.TAG, "单击");
                        if (SmallVideoFragment.this.mISmallVideoImpl.getScreenOrientation(SmallVideoFragment.this.activityContext) == 0) {
                            if (SmallVideoFragment.this.llLandscapeTitle == null || SmallVideoFragment.this.flProgressBarAreaLandscape == null) {
                                return;
                            }
                            int visibility = SmallVideoFragment.this.llLandscapeTitle.getVisibility();
                            int i = 8;
                            if (visibility == 8) {
                                SmallVideoFragment.this.autoHideLandscape();
                            }
                            SmallVideoFragment.this.llLandscapeTitle.setVisibility(visibility == 0 ? 8 : 0);
                            FrameLayout frameLayout = SmallVideoFragment.this.flProgressBarAreaLandscape;
                            if (visibility != 0) {
                                i = 0;
                            }
                            frameLayout.setVisibility(i);
                            return;
                        }
                        SmallVideoFragment.this.clickPlayOrPause();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void doubleTapCallback() {
                    try {
                        MsLogUtil.m7979d(SmallVideoFragment.TAG, "双击");
                        SmallVideoEntity.Data data = SmallVideoFragment.this.mAdapter.getData().get(SmallVideoFragment.this.mCurrentItem);
                        if (data == null || data.isHasZan()) {
                            return;
                        }
                        SmallVideoFragment.this.handleZanAction(SmallVideoFragment.this.mCurrentItem, data);
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
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

    public void dismissLoading() {
        View view = this.ivBtnPlay;
        if (view != null) {
            view.setVisibility(8);
        }
        View view2 = this.ivBtnPlay2;
        if (view2 != null) {
            view2.setVisibility(8);
        }
    }

    public void dismissImg() {
        startPlaySetLog();
        Handler handler = this.addPlayer2UIHandler;
        if (handler != null) {
            handler.removeMessages(32);
            this.addPlayer2UIHandler.sendEmptyMessageDelayed(32, 500L);
        }
    }

    public void leaveRoom() {
        SmallVideoFragment smallVideoFragment;
        try {
            String videoId = this.mAdapter.getData().get(this.mCurrentItem).getVideoId();
            String videoUrl = this.mAdapter.getData().get(this.mCurrentItem).getVideoUrl();
            String videoUrl2 = this.previousItem == null ? "" : this.previousItem.getVideoUrl();
            String viewTitle = this.previousItem == null ? "" : this.previousItem.getViewTitle();
            String viewTitle2 = this.mAdapter.getData().get(this.mCurrentItem).getViewTitle();
            String tjpara = this.mAdapter.getItem(this.mCurrentItem).getTjpara();
            String activityType = getActivityType();
            String str = ((System.currentTimeMillis() - this.playBeginTime) / 1000) + "";
            StringBuilder sb = new StringBuilder();
            sb.append(this.bdCloudVideoView.getCurrentPosition() / 1000);
            sb.append("");
            String sb2 = sb.toString();
            setLog("结束播放", "36", "", videoUrl, videoUrl2, viewTitle, activityType, "1", viewTitle2, TextUtils.isEmpty(this.tabTitle) ? "" : this.tabTitle, str, videoId, sb2, this.realPlayed + "", (this.bdCloudVideoView.getDuration() / 1000) + "", tjpara);
            smallVideoFragment = this;
        } catch (Exception e) {
            e.printStackTrace();
            smallVideoFragment = this;
        }
        try {
            smallVideoFragment.previousItem = smallVideoFragment.mAdapter.getData().get(smallVideoFragment.mCurrentItem);
            if (smallVideoFragment.bdCloudVideoView != null) {
                smallVideoFragment.statisticalTime(smallVideoFragment.bdCloudVideoView.getCurrentPosition() / 1000);
                smallVideoFragment.bdCloudVideoView.stopPlayback();
                smallVideoFragment.bdCloudVideoView.reSetRender();
                ViewGroup viewGroup = (ViewGroup) smallVideoFragment.bdCloudVideoView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeView(smallVideoFragment.bdCloudVideoView);
                }
            }
            smallVideoFragment.realPlayed = 0;
        } catch (Exception e2) {
            MsLogUtil.m7980d(e2.getMessage());
        }
    }

    public void startTiming() {
        if (this.playTime != null) {
            int i = this.realPosition;
            if (i == 0) {
                this.realPosition = i + 1;
            }
            this.playTime.sendEmptyMessageDelayed(3241, 1000L);
        }
    }

    private void statisticalTime(int i) {
        try {
            SmallVideoEntity.Data item = this.mAdapter.getItem(this.mCurrentItem);
            if (TextUtils.isEmpty(item.getVideoId())) {
                return;
            }
            ManagerAudienceLoadData managerAudienceLoadData = this.managerAudienceLoadData;
            String videoId = item.getVideoId();
            managerAudienceLoadData.statisticalTime(videoId, i + "");
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void uploadLog() {
        try {
            if (TextUtils.isEmpty(this.mISmallVideoImpl.getLiveChannel())) {
                return;
            }
            StatisticsUploadUtils.uploadSmallVideoPVLog(this.activityContext, "S2ndpage1104", "duannei", "进入小视频播放页", "小视频", this.mISmallVideoImpl.getLiveChannel());
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void toggleFullScreen(Context context) {
        try {
            if (this.mISmallVideoImpl.getScreenOrientation(context) == 0) {
                addPortraitUI();
            } else {
                addLandscapeUI();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addPortraitUI() {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            findViewByPosition.findViewById(2131298334).setVisibility(8);
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
            TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
            FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131297007);
            this.flProgressBarAreaLandscape = (FrameLayout) findViewByPosition.findViewById(2131297008);
            this.llLandscapeTitle = findViewByPosition.findViewById(2131297735);
            this.mISmallVideoImpl.fullScreen();
            ((FrameLayout) findViewByPosition.findViewById(2131296988)).removeAllViews();
            ((LinearLayout) findViewByPosition.findViewById(2131297726)).setVisibility(0);
            textView.setVisibility(0);
            isFullView(textView);
            ((LinearLayout) findViewByPosition.findViewById(2131297733)).setVisibility(8);
            this.mLayoutManager.setScrollEnabled(true);
            FrameLayout frameLayout3 = (FrameLayout) findViewByPosition.findViewById(2131296396);
            this.bdCloudVideoView.setVideoScalingMode(1);
            if (this.countDownView != null && this.countDownView.getVisibility() == 8) {
                this.countDownView.setVisibility(0);
            }
            findViewByPosition.findViewById(2131297750).setVisibility(0);
            if (this.smartSwipeRefresh != null) {
                this.smartSwipeRefresh.startLoadMore();
            }
            setProgressBarLandscape(false);
            addProgressTo(frameLayout2);
            setLog("点击按钮", "19", "全屏返回", "2");
            if (this.ivBarLandscapeBtn != null && this.ivBarLandscapeBtn.getTag() != null) {
                if (this.bdCloudVideoView.getVideoWidth() > this.bdCloudVideoView.getVideoHeight()) {
                    this.ivBtnPlay2.setVisibility(8);
                    this.ivBtnPlay.setVisibility(0);
                } else {
                    this.ivBtnPlay2.setVisibility(0);
                    this.ivBtnPlay.setVisibility(8);
                }
            }
            ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            MsLogUtil.m7979d("小窗测试", "横屏转竖屏，播放器加入竖屏布局");
            frameLayout3.addView(this.bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addLandscapeUI() {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            FrameLayout frameLayout = (FrameLayout) findViewByPosition.findViewById(2131296395);
            LinearLayout linearLayout = (LinearLayout) findViewByPosition.findViewById(2131297726);
            TextView textView = (TextView) findViewByPosition.findViewById(2131298899);
            LinearLayout linearLayout2 = (LinearLayout) findViewByPosition.findViewById(2131297733);
            FrameLayout frameLayout2 = (FrameLayout) findViewByPosition.findViewById(2131296988);
            this.flProgressBarAreaLandscape = (FrameLayout) findViewByPosition.findViewById(2131297008);
            this.llLandscapeTitle = findViewByPosition.findViewById(2131297735);
            this.mISmallVideoImpl.fullScreen();
            this.ivBtnPlay2.setVisibility(8);
            this.ivBtnPlay.setVisibility(8);
            this.mLayoutManager.setScrollEnabled(false);
            if (this.smartSwipeRefresh != null) {
                this.smartSwipeRefresh.disableLoadMore();
            }
            linearLayout.setVisibility(8);
            textView.setVisibility(8);
            linearLayout2.setVisibility(0);
            boolean z = App.getSharePreferenceUtil().getBoolean("bright_volume_show");
            final View findViewById = findViewByPosition.findViewById(2131298334);
            findViewById.setVisibility(z ? 8 : 0);
            findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$_gqk9t6-Y4tYDb4u9fDICdaOv8A
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SmallVideoFragment.lambda$addLandscapeUI$26(findViewById, view);
                }
            });
            if (!z) {
                App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
            }
            this.bdCloudVideoView.setVideoScalingMode(1);
            if (this.countDownView != null) {
                this.countDownView.setVisibility(8);
            }
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
            setLog("点击按钮", "19", "点击全屏", "2");
            ViewGroup viewGroup2 = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            MsLogUtil.m7979d("小窗测试", "播放器加入横屏布局2");
            frameLayout.addView(this.bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$addLandscapeUI$26(View view, View view2) {
        view.setVisibility(8);
        App.getSharePreferenceUtil().putBoolean("bright_volume_show", true);
    }

    private void setVideoLog(final String str) {
        try {
            String tjpara = this.mAdapter.getItem(this.mCurrentItem).getTjpara();
            UIUtils.logD("playLog", "\nvideoId=" + this.videoId + "\nstartTime=" + this.startTime + "\nvideoTime=" + this.videoTime + "\nplayedTime=" + this.slotTime + "\nslotTime=" + this.playedTime + "\nplayType=" + str + "\ntjpara=" + tjpara);
            final String str2 = this.slotTime;
            this.managerAudienceLoadData.videoTimeLog(this.videoId, this.startTime, this.videoTime, this.playedTime, this.slotTime, str, tjpara).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$z_HY2Wgu9yAFOS8TnUG37_xhsw0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$setVideoLog$27(SmallVideoFragment.this, str, str2, (Map) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$qpYpJJbpnHFBT-9Qg9m4IpFQ67Q
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    Throwable th = (Throwable) obj;
                    UIUtils.logD("playLog", "报错：" + th.getMessage());
                }
            });
            if (this.mAdapter.getItem(this.mCurrentItem).getVideoType() == 1) {
                this.managerAudienceLoadData.videoRingTimeLog(this.videoId, this.startTime, this.videoTime, this.playedTime, this.slotTime, str, tjpara).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$o7C4lyEtgKNICSdx6uj8bnaMXQg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        Map map = (Map) obj;
                        UIUtils.logD(SmallVideoFragment.TAG, "负一屏视频彩铃用户观看时长接口返回：statusCode=" + ((String) map.get("statusCode")) + "|message=" + ((String) map.get("message")));
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setVideoLog$27(SmallVideoFragment smallVideoFragment, String str, String str2, Map map) throws Exception {
        UIUtils.logD("playLog", ((String) map.get("statusCode")) + ":" + ((String) map.get("message")));
        if ("1".equals(str)) {
            String str3 = (String) map.get("statusCode");
            char c = 65535;
            switch (str3.hashCode()) {
                case 1477632:
                    if (str3.equals("0000")) {
                        c = 0;
                        break;
                    }
                    break;
                case 1477633:
                    if (str3.equals("0001")) {
                        c = 1;
                        break;
                    }
                    break;
            }
            switch (c) {
                case 0:
                    smallVideoFragment.oldSlotCount = 0;
                    try {
                        smallVideoFragment.oldSlotCount = Integer.parseInt(str2);
                        return;
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        return;
                    }
                case 1:
                    if ("实际播放时长错误".equals(map.get("message"))) {
                        smallVideoFragment.slotCount = smallVideoFragment.oldSlotCount + 5;
                        smallVideoFragment.slotTime = smallVideoFragment.slotCount + "";
                        smallVideoFragment.setVideoLog(str);
                        return;
                    }
                    return;
                default:
                    UIUtils.logD(TAG, "接口错误");
                    return;
            }
        }
    }

    public void startPlaySetLog() {
        try {
            UIUtils.logD(TAG, "开始播放时 初始化数据-->startPlaySetLog()");
            if (this.mAdapter != null && this.mAdapter.getData().size() > 0) {
                this.videoId = this.mAdapter.getItem(this.mCurrentItem).getVideoId();
            }
            this.startTime = System.currentTimeMillis() + "";
            if (this.bdCloudVideoView != null) {
                this.videoTime = (this.bdCloudVideoView.getDuration() / 1000) + "";
            }
            this.slotTime = "0";
            this.playedTime = "0";
            this.slotCount = 0;
            setVideoLog("0");
            videoLogTimer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void playEndSetLog() {
        try {
            if (TextUtils.isEmpty(this.startTime)) {
                return;
            }
            UIUtils.logD("playLog", "结束播放时-->playEndSetLog()");
            this.slotTime = this.slotCount + "";
            this.playedTime = "" + (this.bdCloudVideoView.getCurrentPosition() / 1000);
            setVideoLog("2");
            if (this.logTimer != null) {
                this.logTimer.dispose();
                this.logTimer = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void videoLogTimer() {
        try {
            if (this.logTimer != null) {
                this.logTimer.dispose();
                this.logTimer = null;
            }
            this.logTimer = Observable.interval(1000L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$XlCc2Bm56D239IE1D86X6ufTHMs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$videoLogTimer$30(SmallVideoFragment.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$videoLogTimer$30(SmallVideoFragment smallVideoFragment, Long l) throws Exception {
        smallVideoFragment.realPosition = Math.max(smallVideoFragment.bdCloudVideoView.getCurrentPosition(), smallVideoFragment.realPosition);
        smallVideoFragment.slotCount++;
        if (smallVideoFragment.bdCloudVideoView.isPlaying()) {
            if (smallVideoFragment.pauseCount >= 5) {
                smallVideoFragment.slotTime = smallVideoFragment.slotCount + "";
                smallVideoFragment.playedTime = "" + (smallVideoFragment.bdCloudVideoView.getCurrentPosition() / 1000);
                smallVideoFragment.setVideoLog("1");
            }
            smallVideoFragment.pauseCount = 0;
            if (smallVideoFragment.slotCount % 5 == 0) {
                smallVideoFragment.slotTime = smallVideoFragment.slotCount + "";
                smallVideoFragment.playedTime = "" + (smallVideoFragment.bdCloudVideoView.getCurrentPosition() / 1000);
                smallVideoFragment.setVideoLog("1");
                return;
            }
            return;
        }
        smallVideoFragment.pauseCount++;
        if (smallVideoFragment.pauseCount % 1800 == 0) {
            smallVideoFragment.slotTime = smallVideoFragment.slotCount + "";
            smallVideoFragment.playedTime = "" + (smallVideoFragment.bdCloudVideoView.getCurrentPosition() / 1000);
            smallVideoFragment.setVideoLog("1");
        }
    }

    @Override // android.support.p083v4.app.Fragment
    public void onStart() {
        NBSFragmentSession.getInstance().fragmentSessionStarted(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment", this);
        if (this.mISmallVideoImpl.getScreenOrientation(this.activityContext) == 0) {
            toggleFullScreen(getActivity());
        }
        super.onStart();
        NBSFragmentSession.fragmentStartEnd(getClass().getName(), "com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment");
    }

    @Override // android.support.p083v4.app.Fragment
    public void onDestroy() {
        RecyclerView recyclerView = this.mRecyclerView;
        if (recyclerView != null) {
            recyclerView.stopScroll();
        }
        try {
            if (this.playHandler != null) {
                this.playHandler.removeCallbacksAndMessages(null);
            }
            if (this.logTimer != null) {
                this.logTimer.dispose();
                this.logTimer = null;
            }
            if (this.reSetVideo != null) {
                this.reSetVideo.dispose();
                this.reSetVideo = null;
            }
            EventBusUtils.unregister(this);
            playEndSetLog();
            if (this.closer != null) {
                this.closer.removeCallbacksAndMessages(null);
                this.closer = null;
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
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }

    public void upLoadVideoErrorLog(String str, String str2) {
        try {
            String str3 = "";
            if (this.mCurrentItem < this.mAdapter.getItemCount() && this.mCurrentItem >= 0) {
                str3 = this.mAdapter.getItem(this.mCurrentItem).getVideoUrl();
            }
            this.managerAudienceLoadData.uploadVideoErrorLog(this.videoId, str3, str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$4l2xFH9aKZQ5EhWE3xnCYaQfflw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$upLoadVideoErrorLog$31((String) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void recevierNet(NetEvent netEvent) {
        try {
            View findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem);
            if (findViewByPosition == null) {
                return;
            }
            int i = 2131297717;
            if (!"Wifi".equals(DeviceHelper.getNETType(this.activityContext))) {
                if (this.mAdapter.getData().get(this.mCurrentItem).getAdView() == null && !UserManager.getInstance().isYiwang()) {
                    if (!isWoMusic()) {
                        i = 2131297716;
                    }
                    findViewByPosition.findViewById(i).setVisibility(0);
                    this.mISmallVideoImpl.showFreeTips(1500L);
                } else {
                    ToastUtil.showToast("此内容不免流");
                }
            } else {
                if (!isWoMusic()) {
                    i = 2131297716;
                }
                findViewByPosition.findViewById(i).setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setZanStatus(String str) {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        }
        this.managerAudienceLoadData.getVideoZan(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$_gzkCJh4E-4mCtjddHIjasN57WM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$setZanStatus$32(SmallVideoFragment.this, (Map) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setZanStatus$32(SmallVideoFragment smallVideoFragment, Map map) throws Exception {
        String str = (String) map.get("thumbsUp");
        String str2 = (String) map.get("thumbsNum");
        smallVideoFragment.mAdapter.getData().get(smallVideoFragment.mCurrentItem).setHasZan(STATUS_Y.equals(str));
        smallVideoFragment.mAdapter.getData().get(smallVideoFragment.mCurrentItem).setVideoPraiseNum(str2);
        View findViewByPosition = smallVideoFragment.mLayoutManager.findViewByPosition(smallVideoFragment.mCurrentItem);
        if (findViewByPosition != null) {
            ImageView imageView = (ImageView) findViewByPosition.findViewById(2131297529);
            ((TextView) findViewByPosition.findViewById(2131299157)).setText(FormatUtils.getShowString(str2));
            imageView.setImageResource(STATUS_Y.equals(str) ? 2131231684 : 2131231683);
        }
    }

    public void zanVideo(String str, String str2, String str3) {
        try {
            if (TextUtils.isEmpty(this.typeId)) {
                this.managerAudienceLoadData.zanVideo(str, str2).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$04QQ353BNOffjD_BC9M6BfC99pQ
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        SmallVideoFragment.lambda$zanVideo$33((String) obj);
                    }
                });
            } else {
                zanFyp("N".equals(str2) ? "2" : "1", str, str3);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void showZanAnimation(View view) {
        ObjectAnimator ofFloat = ObjectAnimator.ofFloat(view, "scaleX", 1.0f, 1.8f, 1.0f);
        ObjectAnimator ofFloat2 = ObjectAnimator.ofFloat(view, "scaleY", 1.0f, 1.8f, 1.0f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1200L);
        animatorSet.setInterpolator(new SpringScaleInterpolator(0.4f));
        animatorSet.playTogether(ofFloat, ofFloat2);
        animatorSet.start();
    }

    public void setVideoRingtone(final String str, String str2, final String str3) {
        try {
            if (this.isSetting) {
                return;
            }
            this.isSetting = true;
            this.f18480pd.show();
            this.managerAudienceLoadData.setVideoRing(str, getVideoFromType()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$MVk3w4Sygz6W4JQl6hAqCwmRMso
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$setVideoRingtone$34(SmallVideoFragment.this, str, str3, (Map) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$duIauqk2hAOOpImiemXGF1SmVJY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$setVideoRingtone$35(SmallVideoFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static /* synthetic */ void lambda$setVideoRingtone$34(SmallVideoFragment smallVideoFragment, String str, String str2, Map map) throws Exception {
        char c;
        smallVideoFragment.isSetting = false;
        smallVideoFragment.f18480pd.dismiss();
        String str3 = (String) map.get("statusCode");
        String str4 = (String) map.get("message");
        int hashCode = str3.hashCode();
        if (hashCode != 1627548) {
            switch (hashCode) {
                case 1477632:
                    if (str3.equals("0000")) {
                        c = 0;
                        break;
                    }
                    c = 65535;
                    break;
                case 1477633:
                    if (str3.equals("0001")) {
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
            if (str3.equals("5100")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                smallVideoFragment.showSetRingResult(str, str2);
                return;
            case 1:
                smallVideoFragment.showOrderRingDialog();
                return;
            case 2:
                Toast toast = new Toast(smallVideoFragment.activityContext);
                View inflate = LayoutInflater.from(smallVideoFragment.activityContext).inflate(2131493392, (ViewGroup) null);
                ((TextView) inflate.findViewById(2131296893)).setText("请求频繁，请稍后再试");
                toast.setView(inflate);
                toast.setGravity(17, 0, 0);
                toast.show();
                break;
        }
        ToastUtil.showToast(str4);
    }

    public static /* synthetic */ void lambda$setVideoRingtone$35(SmallVideoFragment smallVideoFragment, Throwable th) throws Exception {
        smallVideoFragment.isSetting = false;
        ToastUtil.showToast(th.getMessage());
        smallVideoFragment.f18480pd.dismiss();
    }

    public void showRingtoneLibraryFull() {
        try {
            CustomDialogManager.show(this.activityContext, "您设置的铃音库已满,建议您进行清理", "去清理", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$XtOHH9MpYF4kj2IIOumf1WUnCUs
                @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                public final void onClickOk() {
                    SmallVideoFragment.lambda$showRingtoneLibraryFull$36(SmallVideoFragment.this);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$showRingtoneLibraryFull$36(SmallVideoFragment smallVideoFragment) {
        Intent intent = new Intent(smallVideoFragment.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl("https://m.10155.com/h5/mactivity/woapphall.html#/my/ring?isIdle=1");
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        smallVideoFragment.startActivity(intent);
    }

    public void getSmsCode(EditText editText) {
        ObservableSubscribeProxy<String> videoRingCode = this.managerAudienceLoadData.getVideoRingCode();
        Objects.requireNonNull(editText);
        videoRingCode.subscribe(new $$Lambda$A5_pOEfwd7wMXr3XVpBvI8lmIak(editText));
    }

    private void showSetRingResult(String str, String str2) {
        try {
            CustomDialogManager.show(this.activityContext, new ADCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$yyV8taO9Yrj2HgkEog4yZ3p6Hy4
                @Override // com.sinovatech.unicom.separatemodule.audience.util.ADCallBack
                public final void addAdView(View view) {
                    SmallVideoFragment.lambda$showSetRingResult$37(SmallVideoFragment.this, view);
                }
            }, new C835613(str, str2));
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$showSetRingResult$37(SmallVideoFragment smallVideoFragment, final View view) {
        if (view == null || !OptionValveUtil.INSTENCE.isShowOrderAD()) {
            return;
        }
        smallVideoFragment.dismissPauseAd();
        UIUtils.logD("xcyBanner", "开始加载banner广告");
        AdConfigEntity adConfigEntity = new AdConfigEntity();
        adConfigEntity.setAdType("PANGLE");
        adConfigEntity.setCodeId(TextUtils.isEmpty(smallVideoFragment.typeId) ? "946709155" : "946909427");
        adConfigEntity.setBannerWidth(302);
        adConfigEntity.setScale(0.85d);
        AdFactory.getAd(smallVideoFragment.activityContext, adConfigEntity).loadBanner(new IAdInterface.IBannerAdCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.14
            {
                SmallVideoFragment.this = smallVideoFragment;
            }

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
                        layoutParams.topMargin = UIUtils.dip2px(SmallVideoFragment.this.activityContext, 5.0f);
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

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment$13 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C835613 implements CustomDialogManager.SetCallingDialogListener {
        final /* synthetic */ String val$ringId;
        final /* synthetic */ String val$viewTitle;

        C835613(String str, String str2) {
            SmallVideoFragment.this = r1;
            this.val$ringId = str;
            this.val$viewTitle = str2;
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SetCallingDialogListener
        public void onClick(boolean z) {
            final String[] strArr = {" . ", " . . ", " . . ."};
            final Toast toast = new Toast(SmallVideoFragment.this.activityContext);
            View inflate = LayoutInflater.from(SmallVideoFragment.this.activityContext).inflate(2131493391, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(2131296893);
            ValueAnimator ofInt = ValueAnimator.ofInt(0, 3);
            ofInt.setRepeatCount(-1);
            ofInt.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$13$Jzoi49Sr-637JkHqzsay5EcjwcA
                @Override // android.animation.ValueAnimator.AnimatorUpdateListener
                public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                    textView.setText(r1[((Integer) valueAnimator.getAnimatedValue()).intValue() % strArr.length]);
                }
            });
            ofInt.start();
            toast.setView(inflate);
            toast.setGravity(17, 0, 0);
            toast.show();
            ObservableSubscribeProxy<Map<String, String>> ringSetting = SmallVideoFragment.this.managerAudienceLoadData.getRingSetting(SmallVideoFragment.this.getVideoFromType(), this.val$ringId, z ? "1" : "0");
            final String str = this.val$viewTitle;
            ringSetting.subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$13$Gdv02cuwsOcaY-MkQlPYV-wyrFc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.C835613.lambda$onClick$1(SmallVideoFragment.C835613.this, toast, str, (Map) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$13$RR20EEmH24Q6bvPqRJx5VXq_UPo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.C835613.lambda$onClick$2(toast, (Throwable) obj);
                }
            });
        }

        /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
        public static /* synthetic */ void lambda$onClick$1(C835613 c835613, Toast toast, String str, Map map) throws Exception {
            char c;
            toast.cancel();
            Toast toast2 = new Toast(SmallVideoFragment.this.activityContext);
            View inflate = LayoutInflater.from(SmallVideoFragment.this.activityContext).inflate(2131493392, (ViewGroup) null);
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
                    OrderVideoCallingResultDialog.show(SmallVideoFragment.this.activityContext, "");
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
                        OrderVideoCallingResultDialog.show(SmallVideoFragment.this.activityContext, str);
                    }
                    if (str3.equals("设置成功,奖励2积分")) {
                        ToastUtil.showToast(str3);
                        return;
                    }
                    return;
                case 4:
                    SmallVideoFragment.this.showRingtoneLibraryFull();
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

        public static /* synthetic */ void lambda$onClick$2(Toast toast, Throwable th) throws Exception {
            toast.cancel();
            ToastUtil.showToast("设置失败");
        }

        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SetCallingDialogListener
        public void close() {
            SmallVideoFragment.this.setLog("点击按钮", "19", "取消设置彩铃", "2");
        }
    }

    private void showOrderRingDialog() {
        getOrderRingInstruction();
    }

    private void getOrderRingInstruction() {
        this.managerAudienceLoadData.getOderRingExplain(getVideoFromType(), this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$jYkLZZYE3c7a2gB6vsWm9thBK9Y
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$getOrderRingInstruction$38(SmallVideoFragment.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$A6UvLM8vnELA9XLDTTG29e3Z0Fs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("lln", ((Throwable) obj).getMessage());
            }
        });
    }

    /* JADX WARN: Removed duplicated region for block: B:52:0x0095  */
    /* JADX WARN: Removed duplicated region for block: B:57:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void lambda$getOrderRingInstruction$38(com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r11, final com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity r12) throws java.lang.Exception {
        /*
            java.lang.Object r0 = r12.getData()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto L88
            java.lang.Object r0 = r12.getData()
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            if (r0 <= 0) goto L88
            r0 = r1
            r3 = r0
            r4 = r3
        L17:
            java.lang.Object r5 = r12.getData()
            java.util.List r5 = (java.util.List) r5
            int r5 = r5.size()
            if (r0 >= r5) goto L62
            java.lang.Object r5 = r12.getData()
            java.util.List r5 = (java.util.List) r5
            java.lang.Object r5 = r5.get(r0)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r5 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r5
            java.lang.String r5 = r5.getRecommendedStatus()
            java.lang.String r6 = "1"
            boolean r5 = r5.equals(r6)
            if (r5 == 0) goto L4b
            java.lang.Object r4 = r12.getData()
            java.util.List r4 = (java.util.List) r4
            java.lang.Object r4 = r4.get(r0)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r4 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r4
            r4.setSelected(r2)
            r4 = r0
        L4b:
            java.lang.Object r5 = r12.getData()
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
            java.lang.Object r0 = r12.getData()
            java.util.List r0 = (java.util.List) r0
            int r0 = r0.size()
            if (r3 != r0) goto L7f
            java.lang.Object r0 = r12.getData()
            java.util.List r0 = (java.util.List) r0
            java.lang.Object r0 = r0.get(r1)
            com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity$DataEntity r0 = (com.sinovatech.unicom.separatemodule.audience.entity.VideoRingExplainEntity.DataEntity) r0
            r0.setSelected(r2)
            r9 = r2
            goto L89
        L7f:
            java.lang.Object r0 = r12.getData()
            java.util.List r0 = (java.util.List) r0
            java.util.Collections.swap(r0, r4, r2)
        L88:
            r9 = r1
        L89:
            java.lang.String r0 = "0000"
            java.lang.String r1 = r12.getStatusCode()
            boolean r0 = r0.equals(r1)
            if (r0 == 0) goto Lac
            com.sinovatech.unicom.ui.BaseActivity r5 = r11.activityContext
            java.lang.String r6 = r11.getVideoFromType()
            java.lang.Object r0 = r12.getData()
            r7 = r0
            java.util.List r7 = (java.util.List) r7
            boolean r8 = r11.isNeedCode
            com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment$15 r10 = new com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment$15
            r10.<init>()
            com.sinovatech.unicom.separatemodule.audience.view.OrderVideoRingDialog.show(r5, r6, r7, r8, r9, r10)
        Lac:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.lambda$getOrderRingInstruction$38(com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment, com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity):void");
    }

    public void OrderVideoTingNoneCode(String str, String str2, String str3, String str4, final String str5, final String str6) {
        this.managerAudienceLoadData.getVideoRing(str, str2, str3, str4).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$xjG-DUoZD6XM6DJSyyFyQgrCc-M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$OrderVideoTingNoneCode$40(SmallVideoFragment.this, str5, str6, (BaseVideoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$OrderVideoTingNoneCode$40(SmallVideoFragment smallVideoFragment, String str, String str2, BaseVideoEntity baseVideoEntity) throws Exception {
        if (!"0000".equals(baseVideoEntity.getStatusCode())) {
            str = baseVideoEntity.getMessage();
        }
        OrderVideoRingResultDialog.show(smallVideoFragment.activityContext, "0000".equals(baseVideoEntity.getStatusCode()), str, str2, smallVideoFragment.typeId);
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            String message = baseVideoEntity.getMessage();
            if (TextUtils.isEmpty(message)) {
                return;
            }
            ToastUtil.showToast(message);
        }
    }

    private void showMasking() {
        View findViewByPosition;
        try {
            if (((this.activityContext instanceof AudienceMainActivity) && "慢直播".equals(((AudienceMainActivity) this.activityContext).getCurrentTabName())) || (findViewByPosition = this.mLayoutManager.findViewByPosition(this.mCurrentItem)) == null) {
                return;
            }
            final View findViewById = findViewByPosition.findViewById(this.mAdapter.isFuYiPing() ? 2131297778 : 2131297777);
            if (findViewById != null) {
                findViewById.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$ClnQvtOVR4dALLI3bsjSJpPrsgg
                    @Override // java.lang.Runnable
                    public final void run() {
                        HomeMengcengView.showVideoRingMengceng(findViewById, SmallVideoFragment.this.activityContext);
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void setVideoNum(String str) {
        this.managerAudienceLoadData.setVideoNum(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$44Mqff8d0m8tTwI4C5WV_PW5C7k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$setVideoNum$42(SmallVideoFragment.this, (String) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    public static /* synthetic */ void lambda$setVideoNum$42(SmallVideoFragment smallVideoFragment, String str) throws Exception {
        UIUtils.logD("lln", str);
        String viewNum = smallVideoFragment.mAdapter.getItem(smallVideoFragment.mCurrentItem).getViewNum();
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
        SmallVideoEntity.Data item = smallVideoFragment.mAdapter.getItem(smallVideoFragment.mCurrentItem);
        item.setViewNum(i2 + "");
        ((TextView) ((View) Objects.requireNonNull(smallVideoFragment.mLayoutManager.findViewByPosition(smallVideoFragment.mCurrentItem))).findViewById(2131299147)).setText(FormatUtils.getShowString(i2) + "人看过");
    }

    private void getVideoNum() {
        this.managerAudienceLoadData.getVideoNum(this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$6hOLikBq36kvzIIA8UASiniYS-Y
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$getVideoNum$43(SmallVideoFragment.this, (Map) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    /* JADX WARN: Can't wrap try/catch for region: R(11:1|2|3|(7:8|9|(1:11)|12|13|14|15)|21|9|(0)|12|13|14|15) */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x004c, code lost:
        r1 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x004d, code lost:
        r1.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static /* synthetic */ void lambda$getVideoNum$43(com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment r3, java.util.Map r4) throws java.lang.Exception {
        /*
            r0 = 0
            java.lang.String r1 = "0000"
            java.lang.String r2 = "statusCode"
            java.lang.Object r2 = r4.get(r2)     // Catch: java.lang.NumberFormatException -> L2e
            boolean r1 = r1.equals(r2)     // Catch: java.lang.NumberFormatException -> L2e
            if (r1 == 0) goto L2c
            java.lang.String r1 = "data"
            java.lang.Object r1 = r4.get(r1)     // Catch: java.lang.NumberFormatException -> L2e
            java.lang.CharSequence r1 = (java.lang.CharSequence) r1     // Catch: java.lang.NumberFormatException -> L2e
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch: java.lang.NumberFormatException -> L2e
            if (r1 == 0) goto L1f
            goto L2c
        L1f:
            java.lang.String r1 = "data"
            java.lang.Object r4 = r4.get(r1)     // Catch: java.lang.NumberFormatException -> L2e
            java.lang.String r4 = (java.lang.String) r4     // Catch: java.lang.NumberFormatException -> L2e
            int r4 = java.lang.Integer.parseInt(r4)     // Catch: java.lang.NumberFormatException -> L2e
            goto L33
        L2c:
            r4 = r0
            goto L33
        L2e:
            r4 = move-exception
            r4.printStackTrace()
            r4 = r0
        L33:
            com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter r1 = r3.mAdapter
            int r2 = r3.mCurrentItem
            com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity$Data r1 = r1.getItem(r2)
            java.lang.String r1 = r1.getViewNum()
            boolean r2 = android.text.TextUtils.isEmpty(r1)
            if (r2 == 0) goto L47
            java.lang.String r1 = "0"
        L47:
            int r0 = java.lang.Integer.parseInt(r1)     // Catch: java.lang.Exception -> L4c
            goto L50
        L4c:
            r1 = move-exception
            r1.printStackTrace()
        L50:
            int r0 = r0 + r4
            com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter r4 = r3.mAdapter
            int r1 = r3.mCurrentItem
            com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity$Data r4 = r4.getItem(r1)
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r1.append(r0)
            java.lang.String r2 = ""
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            r4.setViewNum(r1)
            com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter r4 = r3.mAdapter
            int r1 = r3.mCurrentItem
            com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity$Data r4 = r4.getItem(r1)
            r1 = 1
            r4.setHasAddCount(r1)
            com.sinovatech.unicom.separatemodule.video.viewpager.ViewPagerLayoutManager r4 = r3.mLayoutManager
            int r1 = r3.mCurrentItem
            android.view.View r4 = r4.findViewByPosition(r1)
            java.lang.Object r4 = java.util.Objects.requireNonNull(r4)
            android.view.View r4 = (android.view.View) r4
            r1 = 2131299147(0x7f090b4b, float:1.8216287E38)
            android.view.View r4 = r4.findViewById(r1)
            android.widget.TextView r4 = (android.widget.TextView) r4
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r0 = com.sinovatech.unicom.separatemodule.audience.util.FormatUtils.getShowString(r0)
            r1.append(r0)
            java.lang.String r0 = "人看过"
            r1.append(r0)
            java.lang.String r0 = r1.toString()
            r4.setText(r0)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.lambda$getVideoNum$43(com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment, java.util.Map):void");
    }

    public void setInfo(String str, String str2, int i, int i2, String str3) {
        try {
            this.typeId = str;
            if (TextUtils.isEmpty(str)) {
                if (this.videoDataType != VideoDataType.SmallVideo4Up && this.videoDataType != VideoDataType.SearchVideo && this.videoDataType != VideoDataType.CustomVideo) {
                    UIUtils.logD(TAG, "setInfo设置为" + VideoDataType.SmallVideo);
                    this.videoDataType = VideoDataType.SmallVideo;
                }
            } else {
                this.videoDataType = VideoDataType.Ringtone;
            }
            this.tabTitle = str3;
            if (!TextUtils.isEmpty(str2)) {
                this.ringList = getRingList(str2);
            }
            this.ringIndex = i;
            this.pageNum = i2 + "";
            if (this.smartSwipeRefresh != null) {
                this.smartSwipeRefresh.finished(true);
                this.smartSwipeRefresh.setNoMoreData(DATA_NO_MORE.equals(Integer.valueOf(i2)));
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
    }

    private List<VideoEntity> getRingList(String str) {
        ArrayList arrayList = new ArrayList();
        if (str != null) {
            try {
                if (str.startsWith("[{")) {
                    JSONArray jSONArray = new JSONArray(str);
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        VideoEntity videoEntity = new VideoEntity();
                        String optString = optJSONObject.optString("contentId");
                        String optString2 = optJSONObject.optString("contentid");
                        if (TextUtils.isEmpty(optString)) {
                            optString = optString2;
                        }
                        videoEntity.setId(optString);
                        if (!TextUtils.isEmpty(videoEntity.getId())) {
                            videoEntity.setName(optJSONObject.optString("name"));
                            videoEntity.setPicpath(optJSONObject.optString("picpath"));
                            videoEntity.setPrice(optJSONObject.optString("price"));
                            videoEntity.setLikeNum(optJSONObject.optString("likeNum"));
                            videoEntity.setLikeFlag(optJSONObject.optString("likeFlag"));
                            videoEntity.setViewNum(optJSONObject.optString("viewNum"));
                            videoEntity.setFavNum(optJSONObject.optString("favNum"));
                            videoEntity.setFavFlag(optJSONObject.optString("favFlag"));
                            JSONObject optJSONObject2 = optJSONObject.optJSONObject("userinfo");
                            JSONObject optJSONObject3 = optJSONObject.optJSONObject("userbaseinfo");
                            if (optJSONObject2 != null) {
                                videoEntity.setHeadimg(optJSONObject2.optString("headimg"));
                                videoEntity.setNickname(optJSONObject2.optString("nickname"));
                                videoEntity.setIdentification(optJSONObject2.optString("identification"));
                            } else if (optJSONObject3 != null) {
                                videoEntity.setHeadimg(optJSONObject3.optString("headimg"));
                                videoEntity.setNickname(optJSONObject3.optString("nickname"));
                                videoEntity.setIdentification(optJSONObject3.optString("identification"));
                            } else {
                                videoEntity.setHeadimg(optJSONObject.optString("headimg"));
                                videoEntity.setNickname(optJSONObject.optString("nickname"));
                                videoEntity.setIdentification(optJSONObject.optString("identification"));
                            }
                            String optString3 = optJSONObject.optString("singleTag");
                            if (TextUtils.isEmpty(optString3)) {
                                JSONArray optJSONArray = optJSONObject.optJSONArray("tags");
                                if (optJSONArray != null && optJSONArray.length() > 0) {
                                    JSONObject optJSONObject4 = optJSONArray.optJSONObject(0);
                                    if (optJSONObject4 != null) {
                                        videoEntity.setSingleTag(optJSONObject4.optString("tagName"));
                                    }
                                } else {
                                    videoEntity.setSingleTag("1".equals(optJSONObject.optString("isOriginal")) ? "原创" : "");
                                }
                            } else {
                                videoEntity.setSingleTag(optString3);
                            }
                            JSONArray optJSONArray2 = optJSONObject.optJSONArray("label");
                            if (optJSONArray2 != null) {
                                StringBuffer stringBuffer = new StringBuffer();
                                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                                    stringBuffer.append("#");
                                    stringBuffer.append(optJSONArray2.optString(i2));
                                }
                                videoEntity.setLabel(stringBuffer.toString());
                            }
                            videoEntity.setTjpara(optJSONObject.optString("tjpara"));
                            videoEntity.setContentType(optJSONObject.optString("contentType"));
                            arrayList.add(videoEntity);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return arrayList;
    }

    public void setCustomParams(String str, Map<String, String> map) {
        this.accessName = str;
        this.customParams = map;
    }

    public void getRecommend(final int i) {
        String boardInfo;
        Map<String, String> hashMap;
        try {
            if (this.fromType.equals(this.accessName)) {
                boardInfo = URLSet.getBoardInfoNew();
                hashMap = this.customParams;
                hashMap.put("version", getResources().getString(2131886969));
                hashMap.put("nextPageNum", i + "");
                hashMap.put("accessName", this.accessName);
            } else {
                boardInfo = URLSet.getBoardInfo(i + "", this.typeId);
                hashMap = new HashMap<>(8);
                hashMap.put("version", getResources().getString(2131886969));
                if ("4235".equals(this.typeId)) {
                    boardInfo = boardInfo.replace("getBoardInfo", "getInfoOfBothVideo");
                    hashMap.put("provinceCode", UserManager.getInstance().getLocateProvinceCode());
                    hashMap.put("cityCode", UserManager.getInstance().getLocateCityCode());
                    hashMap.put("page", i == 1 ? "1" : "2");
                }
            }
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(30, 10, 10).rxGet(boardInfo, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecommendListFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$yUERn2Qkei1xePXk1fcIY-HD1D0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getRecommend$44(SmallVideoFragment.this, i, (BaseVideoEntity) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Resources.NotFoundException e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getRecommend$44(SmallVideoFragment smallVideoFragment, int i, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            List<SmallVideoEntity.Data> rings2SmallVideo = smallVideoFragment.rings2SmallVideo((List) baseVideoEntity.getData());
            smallVideoFragment.pageNum = baseVideoEntity.getNextPageNum();
            SmartSwipeRefresh smartSwipeRefresh = smallVideoFragment.smartSwipeRefresh;
            if (smartSwipeRefresh != null) {
                smartSwipeRefresh.finished(true);
                smallVideoFragment.smartSwipeRefresh.setNoMoreData(DATA_NO_MORE.equals(smallVideoFragment.pageNum));
            }
            if (i == 1) {
                if (TextUtils.isEmpty(smallVideoFragment.accessName)) {
                    smallVideoFragment.initAdFypMap(smallVideoFragment.ringIndex);
                }
                if (smallVideoFragment.mAdapter != null) {
                    UIUtils.logD("xcyTest", "playLive");
                    smallVideoFragment.mAdapter.setFuYiPing(true);
                    smallVideoFragment.mAdapter.setNewData(rings2SmallVideo);
                    smallVideoFragment.mCurrentItem = smallVideoFragment.ringIndex;
                    smallVideoFragment.mRecyclerView.scrollToPosition(smallVideoFragment.mCurrentItem);
                    smallVideoFragment.startPlay(smallVideoFragment.mCurrentItem);
                    return;
                }
                return;
            }
            smallVideoFragment.mAdapter.addData(rings2SmallVideo);
            smallVideoFragment.mAdapter.setFuYiPing(true);
            smallVideoFragment.isLoadMore = false;
            smallVideoFragment.insertAdFypView();
        }
    }

    private List<SmallVideoEntity.Data> rings2SmallVideo(List<VideoEntity> list) {
        ArrayList arrayList = new ArrayList();
        try {
            for (VideoEntity videoEntity : list) {
                SmallVideoEntity.Data data = new SmallVideoEntity.Data();
                data.setVideoId(videoEntity.getId());
                data.setTranscodeImg(videoEntity.getPicpath());
                data.setVideoImg(videoEntity.getPicpath());
                data.setUserImg(videoEntity.getHeadimg());
                data.setUserName(videoEntity.getNickname());
                data.setViewNum(videoEntity.getViewNum());
                data.setViewTitle(videoEntity.getName());
                data.setVideoTag(videoEntity.getLabel());
                data.setVideoPraiseNum(videoEntity.getLikeNum());
                data.setShareNum("");
                data.setFocusOn(true);
                data.setHasZan("1".equals(videoEntity.getLikeFlag()));
                data.setIsShow(UserManager.getInstance().isYiwang() ? "N" : STATUS_Y);
                data.setVideoType(1);
                data.setTjpara(videoEntity.getTjpara());
                data.setContentType(videoEntity.getContentType());
                data.setSingleTag(videoEntity.getSingleTag());
                data.setIdentification(videoEntity.getIdentification());
                arrayList.add(data);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    private List<VideoEntity> smallVideo2Rings(List<SmallVideoEntity.Data> list) {
        ArrayList arrayList;
        try {
            arrayList = new ArrayList();
        } catch (Exception e) {
            e = e;
            arrayList = null;
        }
        try {
            for (SmallVideoEntity.Data data : list) {
                if (data.getVideoType() == 1) {
                    VideoEntity videoEntity = new VideoEntity();
                    videoEntity.setId(data.getVideoId());
                    videoEntity.setPicpath(data.getTranscodeImg());
                    videoEntity.setPicpath(data.getVideoImg());
                    videoEntity.setHeadimg(data.getUserImg());
                    videoEntity.setNickname(data.getUserName());
                    videoEntity.setViewNum(data.getViewNum());
                    videoEntity.setName(data.getViewTitle());
                    videoEntity.setLabel(data.getVideoTag());
                    videoEntity.setLikeNum(data.getVideoPraiseNum());
                    videoEntity.setLikeFlag(data.isHasZan() ? "1" : "0");
                    videoEntity.setTjpara(data.getTjpara());
                    videoEntity.setContentType(data.getContentType());
                    videoEntity.setSingleTag(data.getSingleTag());
                    videoEntity.setIdentification(data.getIdentification());
                    arrayList.add(videoEntity);
                }
            }
        } catch (Exception e2) {
            e = e2;
            e.printStackTrace();
            return arrayList;
        }
        return arrayList;
    }

    private void getVideoInfo(final int i, String str, String str2) {
        try {
            String videoInfo = TextUtils.isEmpty(this.accessName) ? URLSet.getVideoInfo() : URLSet.getContentNew();
            HashMap hashMap = new HashMap(8);
            hashMap.put("songId", "");
            hashMap.put("contentId", str);
            hashMap.put("resourceType", "10");
            hashMap.put("quaType", "1");
            hashMap.put("segment", "0");
            hashMap.put("contentType", str2);
            if (!TextUtils.isEmpty(this.accessName)) {
                hashMap.put("accessName", this.accessName);
            }
            Gson gson = new Gson();
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(30, 10, 10).rxPost(videoInfo, !(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap)).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$TXgJsNbf89AuSSnRANFig000_P4
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return SmallVideoFragment.lambda$getVideoInfo$45((String) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$p_soAMc1hLfQ3koA8O2xl187S5k
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getVideoInfo$46(SmallVideoFragment.this, i, (BaseVideoEntity) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ BaseVideoEntity lambda$getVideoInfo$45(String str) throws Exception {
        UIUtils.logD("xcyTest", "推荐===》" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null) {
            VideoEntity videoEntity = new VideoEntity();
            videoEntity.setPicpath(optJSONObject.optString("screenshot"));
            videoEntity.setSingername(optJSONObject.optString("fileurl"));
            baseVideoEntity.setData(videoEntity);
        }
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$getVideoInfo$46(SmallVideoFragment smallVideoFragment, int i, BaseVideoEntity baseVideoEntity) throws Exception {
        if (!"0000".equals(baseVideoEntity.getStatusCode()) || baseVideoEntity.getData() == null) {
            return;
        }
        smallVideoFragment.mAdapter.getItem(i).setVideoUrl(((VideoEntity) baseVideoEntity.getData()).getSingername());
        smallVideoFragment.mAdapter.getItem(i).setTranscodeImg(((VideoEntity) baseVideoEntity.getData()).getPicpath());
        if (smallVideoFragment.bdCloudVideoView == null) {
            smallVideoFragment.bdCloudVideoView = smallVideoFragment.mISmallVideoImpl.playVideo(smallVideoFragment.mAdapter.getItem(i).getVideoUrl(), smallVideoFragment.mAdapter.getItem(i).getVideoId());
        } else {
            smallVideoFragment.mISmallVideoImpl.playVideo(smallVideoFragment.mAdapter.getItem(i).getVideoUrl(), smallVideoFragment.mAdapter.getItem(i).getVideoId());
        }
        MsLogUtil.m7979d(TAG, "getVideoInfo===>");
        smallVideoFragment.setLogPlaying();
        smallVideoFragment.setVideoSizeView(i);
        if (STATUS_Y.equals(smallVideoFragment.mAdapter.getItem(i).getIsShow())) {
            smallVideoFragment.showMasking();
        }
    }

    private void setLogPlaying() {
        try {
            String videoId = this.mAdapter.getData().get(this.mCurrentItem).getVideoId();
            String videoUrl = this.mAdapter.getData().get(this.mCurrentItem).getVideoUrl();
            String videoUrl2 = this.previousItem == null ? "" : this.previousItem.getVideoUrl();
            String viewTitle = this.previousItem == null ? "" : this.previousItem.getViewTitle();
            String viewTitle2 = this.mAdapter.getData().get(this.mCurrentItem).getViewTitle();
            String str = TextUtils.isEmpty(this.tabTitle) ? "" : this.tabTitle;
            int i = 0;
            int duration = this.bdCloudVideoView == null ? 0 : this.bdCloudVideoView.getDuration();
            String str2 = (duration / 1000) + "";
            long currentTimeMillis = (System.currentTimeMillis() - this.playBeginTime) / 1000;
            if (this.bdCloudVideoView != null) {
                i = this.bdCloudVideoView.getCurrentPosition();
            }
            String tjpara = this.mAdapter.getItem(this.mCurrentItem).getTjpara();
            String activityType = getActivityType();
            String str3 = currentTimeMillis + "";
            StringBuilder sb = new StringBuilder();
            sb.append(i / 1000);
            sb.append("");
            setLog("开始播放", "35", "", videoUrl, videoUrl2, viewTitle, activityType, "1", viewTitle2, str, str3, videoId, sb.toString(), this.realPlayed + "", str2, tjpara);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void zanFyp(String str, String str2, String str3) {
        try {
            String likeOperation = TextUtils.isEmpty(this.accessName) ? URLSet.likeOperation(str, str2) : URLSet.likeOperationNew();
            HashMap hashMap = null;
            if (!TextUtils.isEmpty(this.accessName)) {
                hashMap = new HashMap();
                hashMap.put("operType", str);
                hashMap.put("objId", str2);
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                hashMap.put("contentType", str3);
                hashMap.put("accessName", this.accessName);
            }
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(30, 10, 10).rxGet(likeOperation, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$zFCd-K0CBzZLCN53SkqtpddwpVM
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return SmallVideoFragment.lambda$zanFyp$47((String) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$RtQxWePPLEJKhjYsMe_SpUkArhw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UIUtils.logD(SmallVideoFragment.TAG, (String) ((BaseVideoEntity) obj).getData());
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ BaseVideoEntity lambda$zanFyp$47(String str) throws Exception {
        UIUtils.logD("lln", "点赞===》" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setData(jSONObject.optString("data"));
        return baseVideoEntity;
    }

    public String getVideoListJson() {
        try {
            List<VideoEntity> smallVideo2Rings = smallVideo2Rings(this.mAdapter.getData());
            Gson gson = new Gson();
            return !(gson instanceof Gson) ? gson.toJson(smallVideo2Rings) : NBSGsonInstrumentation.toJson(gson, smallVideo2Rings);
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public int getCurrentIndex() {
        return this.mCurrentItem;
    }

    private String getPreviousItemInfo(int i) {
        return this.previousItem != null ? (this.videoDataType != VideoDataType.Ringtone || this.fromType.equals(this.accessName)) ? i == 0 ? this.previousItem.getVideoUrl() : this.previousItem.getViewTitle() : "" : "";
    }

    public String getVideoFromType() {
        return C836420.f18481xc39b7d0a[this.videoDataType.ordinal()] != 1 ? "1" : this.fromType.equals(this.accessName) ? "3" : "2";
    }

    public String getActivityType() {
        return this.videoDataType == VideoDataType.Ringtone ? this.fromType.equals(this.accessName) ? "3" : "1" : "2";
    }

    private String getTabName() {
        return C836420.f18481xc39b7d0a[this.videoDataType.ordinal()] != 1 ? "小视频" : "视频彩铃";
    }

    private String getTabID() {
        return C836420.f18481xc39b7d0a[this.videoDataType.ordinal()] != 1 ? "xiaoshipin" : "spcl";
    }

    public void setLog(String str, String str2, String str3, String str4) {
        try {
            String videoId = this.mAdapter.getData().get(this.mCurrentItem).getVideoId();
            String videoUrl = this.mAdapter.getData().get(this.mCurrentItem).getVideoUrl();
            String videoUrl2 = this.previousItem == null ? "" : this.previousItem.getVideoUrl();
            String viewTitle = this.previousItem == null ? "" : this.previousItem.getViewTitle();
            String viewTitle2 = this.mAdapter.getData().get(this.mCurrentItem).getViewTitle();
            String tjpara = this.mAdapter.getItem(this.mCurrentItem).getTjpara();
            String activityType = getActivityType();
            String str5 = ((System.currentTimeMillis() - this.playBeginTime) / 1000) + "";
            StringBuilder sb = new StringBuilder();
            sb.append(this.bdCloudVideoView.getCurrentPosition() / 1000);
            sb.append("");
            String sb2 = sb.toString();
            setLog(str, str2, str3, videoUrl, videoUrl2, viewTitle, activityType, str4, viewTitle2, TextUtils.isEmpty(this.tabTitle) ? "" : this.tabTitle, str5, videoId, sb2, this.realPlayed + "", (this.bdCloudVideoView.getDuration() / 1000) + "", tjpara);
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, str + "|" + str3 + "|" + e.getMessage());
        }
    }

    private String getChannelId() {
        return this.videoDataType == VideoDataType.Ringtone ? "3000011091" : "3000011092";
    }

    public void setLog(final String str, String str2, final String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            HashMap hashMap = new HashMap();
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
            hashMap.put("channel_id", getChannelId());
            hashMap.put("content_position", this.videoDataType == VideoDataType.Ringtone ? "1" : "");
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
            hashMap.put("tab_id", getTabID());
            hashMap.put("tab_name", getTabName());
            hashMap.put("operateDesc", "");
            hashMap.put("tjpara", str16 == null ? "" : str16);
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
            }
            this.managerAudienceLoadData.setLogPoit(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$OxxiM04Pghqvsu-HwuObAzYASgw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    MsLogUtil.m7979d(SmallVideoFragment.TAG, "埋点：" + str + "|" + str3 + "===>" + ((String) obj));
                }
            });
        } catch (Resources.NotFoundException e) {
            MsLogUtil.m7979d(TAG, "埋点：" + str + "|" + str3 + ",发生错误：" + e.getMessage());
        }
    }

    public void hiBoardLog(String str) {
        if (this.mAdapter.getItem(this.mCurrentItem).getVideoType() == 1) {
            this.managerAudienceLoadData.hiBoardLog(str, this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$AUH2CyIZPD0EP0ZsHBWCv5HSnZs
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$hiBoardLog$50((String) obj);
                }
            });
        }
    }

    @SuppressLint({"NotifyDataSetChanged"})
    private void getHelpBtnInfo() {
        if (this.managerAudienceLoadData == null) {
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        }
        this.managerAudienceLoadData.getHelpBtnInfo().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$5RtO5xOQHnBUH9HoOk5J1zem6V8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$getHelpBtnInfo$51(SmallVideoFragment.this, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$_HhjwZ1G8-K1jWLZp8jJFMl9V74
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("xcyTest", ((Throwable) obj).getMessage());
            }
        });
    }

    public static /* synthetic */ void lambda$getHelpBtnInfo$51(SmallVideoFragment smallVideoFragment, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            smallVideoFragment.helpUrl = ((HelpBtnInfoEntity) baseVideoEntity.getData()).getHelpUrl();
            smallVideoFragment.isShowHelpBtn = ((HelpBtnInfoEntity) baseVideoEntity.getData()).isShowHelpBtn();
            SmallVideoAdapter smallVideoAdapter = smallVideoFragment.mAdapter;
            if (smallVideoAdapter != null) {
                smallVideoAdapter.setShowHelpBtn(smallVideoFragment.isShowHelpBtn);
                smallVideoFragment.mAdapter.notifyDataSetChanged();
            }
        }
    }

    private void getWelfareUrl(final View view) {
        try {
            if (this.videoDataType == VideoDataType.SmallVideo4Up || this.videoDataType == VideoDataType.SearchVideo || UserManager.getInstance().isYiwang()) {
                return;
            }
            this.managerAudienceLoadData.getWelfareUrl(TextUtils.isEmpty(this.typeId) ? "02" : !this.fromType.equals(this.accessName) ? "01" : "03").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$8rzAjdaaxVTq6AVyQMj9gLnS0UM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$getWelfareUrl$53(SmallVideoFragment.this, view, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$oE9RnjNju9QQ5iGuFqCYoTE1xEU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UIUtils.logD("xcyTest", ((Throwable) obj).getMessage());
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$getWelfareUrl$53(SmallVideoFragment smallVideoFragment, View view, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            smallVideoFragment.welfareUrl = (String) baseVideoEntity.getData();
            if (TextUtils.isEmpty(smallVideoFragment.welfareUrl)) {
                return;
            }
            smallVideoFragment.initCircleProgress(view);
        }
    }

    public void gotoWelFareCenter() {
        try {
            if (TextUtils.isEmpty(this.welfareUrl)) {
                this.welfareUrl = "";
            }
            Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(this.welfareUrl);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setType("smallVideo");
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            startActivity(intent);
            setLog("点击按钮", "19", "做任务得积分", "2");
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void getPersonalCenterVideoList(String str, String str2) {
        this.managerAudienceLoadData.getVideoList(str, str2).subscribe(new $$Lambda$SmallVideoFragment$JIOUNabwCjYIQKQZVus7aHOLoFg(this), new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$FFh819Lo6fUsZeRf4S2hd3B9Qs4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                UIUtils.logD(SmallVideoFragment.TAG, "获取个人中心小视频列表失败：" + th.getMessage());
            }
        });
    }

    public void setSingleVideo(String str) {
        this.videoDataType = VideoDataType.SmallVideo;
        this.singleVideo = str;
    }

    public void setSteamVideo(String str) {
        this.videoDataType = VideoDataType.CustomVideo;
        try {
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                try {
                    this.mCurrentItem = Integer.parseInt(jSONObject.optString("index"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    this.mCurrentItem = 0;
                }
                this.pageNum = jSONObject.optString("nextPageNum");
                String optString = jSONObject.optString("videos");
                this.steamVideoId = jSONObject.optJSONArray("videos").optJSONObject(0).optString("videoId");
                this.rawData = getBaseJsonStr().replace("1", this.pageNum).replace("xxxxxx", optString);
                return;
            }
            this.pageNum = "1";
            UIUtils.logD(TAG, "json为空时设置为" + VideoDataType.SmallVideo);
            this.videoDataType = VideoDataType.SmallVideo;
            loadData();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    public void setVideoInfo(String str) {
        UIUtils.logD(TAG, "setVideoInfo");
        try {
            this.videoDataType = VideoDataType.SmallVideo4Up;
            if (!TextUtils.isEmpty(str)) {
                JSONObject jSONObject = new JSONObject(str);
                this.videoDataType = "search_video".equals(jSONObject.optString("type")) ? VideoDataType.SearchVideo : VideoDataType.SmallVideo4Up;
                this.userId = jSONObject.optString("userId");
                this.keyWords = jSONObject.optString("searchWorld");
                try {
                    this.mCurrentItem = Integer.parseInt(jSONObject.optString("index"));
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                    this.mCurrentItem = 0;
                }
                this.pageNum = jSONObject.optString("nextPageNum");
                this.rawData = getBaseJsonStr().replace("1", this.pageNum).replace("xxxxxx", jSONObject.optString("videos"));
                if (this.isFromPersonalCenterBack) {
                    loadVideosData();
                    this.isFromPersonalCenterBack = false;
                }
                if (this.countDownView != null) {
                    this.countDownView.setVisibility(8);
                    this.countDownReady = false;
                    if (this.taskScoreTimer != null) {
                        this.taskScoreTimer.cancel();
                        this.taskScoreTimer = null;
                        return;
                    }
                    return;
                }
                return;
            }
            this.pageNum = "1";
            UIUtils.logD(TAG, "json为空时设置为" + VideoDataType.SmallVideo);
            this.videoDataType = VideoDataType.SmallVideo;
            loadData();
        } catch (Exception e2) {
            e2.printStackTrace();
            this.pageNum = "1";
            UIUtils.logD(TAG, "发生异常时设置为" + VideoDataType.SmallVideo);
            this.videoDataType = VideoDataType.SmallVideo;
            loadData();
        }
    }

    public void handleVideoData(SmallVideoEntity smallVideoEntity) {
        try {
            if ("0000".equals(smallVideoEntity.getStatusCode())) {
                this.pageNum = smallVideoEntity.getNextPageNum();
                if (this.smartSwipeRefresh != null) {
                    this.smartSwipeRefresh.finished(true);
                    this.smartSwipeRefresh.setNoMoreData(DATA_NO_MORE.equals(this.pageNum));
                }
                if (this.mAdapter != null) {
                    UIUtils.logD(TAG, "小视频列表adapter初始化成功");
                    if (this.isLoadMore) {
                        UIUtils.logD(TAG, "小视频加载更多");
                        this.mAdapter.addData(smallVideoEntity.getData());
                        this.isLoadMore = false;
                        return;
                    }
                    UIUtils.logD(TAG, "小视频列表更新数据完成");
                    this.mAdapter.setNewData(smallVideoEntity.getData());
                    this.mRecyclerView.scrollToPosition(this.mCurrentItem);
                    startPlay(this.mCurrentItem);
                    return;
                }
                return;
            }
            this.pageNum = "1";
            this.videoDataType = VideoDataType.SmallVideo;
            loadData();
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void fromSearch() {
        this.managerAudienceLoadData.searchVideos(this.keyWords, this.pageNum).subscribe(new $$Lambda$SmallVideoFragment$JIOUNabwCjYIQKQZVus7aHOLoFg(this), new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$_mAG9BCapKX_-msyek_8TTQYoJk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Throwable th = (Throwable) obj;
                UIUtils.logD(SmallVideoFragment.TAG, "搜索列表错误:" + th.getMessage());
            }
        });
    }

    private void getVideoData(final int i) {
        this.managerAudienceLoadData.getVideoInfo(this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$lclSjSj1aQJJssIW9lsCYcSi2EQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$getVideoData$57(SmallVideoFragment.this, i, (SmallVideoInfoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getVideoData$57(SmallVideoFragment smallVideoFragment, int i, SmallVideoInfoEntity smallVideoInfoEntity) throws Exception {
        if ("0000".equals(smallVideoInfoEntity.getStatusCode())) {
            smallVideoFragment.mAdapter.getData().set(i, smallVideoInfoEntity.getData());
            smallVideoFragment.mAdapter.notifyItemChanged(i);
            smallVideoFragment.startPlay(i);
        }
    }

    private void getSteamVideoData(final List<SmallVideoEntity.Data> list, final int i) {
        this.managerAudienceLoadData.getVideoInfo(this.steamVideoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$6b56T3lqDc9G6dWzEclO4rfU0lk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$getSteamVideoData$58(SmallVideoFragment.this, i, list, (SmallVideoInfoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$3d0ZXDtOXHncTqugRfJJh7YZf2w
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoFragment.lambda$getSteamVideoData$59(SmallVideoFragment.this, list, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getSteamVideoData$58(SmallVideoFragment smallVideoFragment, final int i, List list, final SmallVideoInfoEntity smallVideoInfoEntity) throws Exception {
        if ("0000".equals(smallVideoInfoEntity.getStatusCode())) {
            smallVideoFragment.getActivity().runOnUiThread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.16
                {
                    SmallVideoFragment.this = smallVideoFragment;
                }

                @Override // java.lang.Runnable
                public void run() {
                    if (SmallVideoFragment.this.rawData.contains("webCustomGoodsInfo")) {
                        try {
                            JSONObject optJSONObject = new JSONObject(SmallVideoFragment.this.rawData).optJSONArray("data").optJSONObject(i).optJSONObject("webCustomGoodsInfo");
                            if (optJSONObject != null) {
                                smallVideoInfoEntity.getData().setIsShowGoods(SmallVideoFragment.STATUS_Y);
                                smallVideoInfoEntity.getData().setViewTitle(optJSONObject.optString("goodsName"));
                                smallVideoInfoEntity.getData().setGoodsLink(optJSONObject.optString("goodsLink"));
                                smallVideoInfoEntity.getData().setGoodsImg(optJSONObject.optString("goodsImg"));
                                smallVideoInfoEntity.getData().setGoodsPrice(optJSONObject.optString("goodsPrice"));
                                smallVideoInfoEntity.getData().setGoodsName(optJSONObject.optString("goodsName"));
                                smallVideoInfoEntity.getData().setGoodsDesc(optJSONObject.optString("goodsDesc"));
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
            smallVideoFragment.videoDataType = VideoDataType.SmallVideo;
            list.add(0, smallVideoInfoEntity.getData());
            smallVideoFragment.mAdapter.setNewData(list);
            smallVideoFragment.startPlay(0);
        } else if ("4001".equals(smallVideoInfoEntity.getStatusCode())) {
            Log.d("lln", "4001视频已下架==");
            smallVideoFragment.mAdapter.getData().add(new SmallVideoEntity.Data());
            ToastUtil.showToast("当前无此视频数据，自动为您推荐其他视频");
            smallVideoFragment.mCurrentItem = 1;
            smallVideoFragment.videoDataType = VideoDataType.SmallVideo;
            smallVideoFragment.mAdapter.addData(list);
            Log.d("lln", "自动播放下一条");
            smallVideoFragment.mRecyclerView.getLayoutManager().smoothScrollToPosition(smallVideoFragment.mRecyclerView, null, 1);
            new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.17
                {
                    SmallVideoFragment.this = smallVideoFragment;
                }

                @Override // java.lang.Runnable
                public void run() {
                    SmallVideoFragment.this.mCurrentItem = 0;
                    SmallVideoFragment.this.mAdapter.getData().remove(0);
                    SmallVideoFragment.this.mAdapter.notifyItemRemoved(0);
                }
            }, 1000L);
        }
    }

    public static /* synthetic */ void lambda$getSteamVideoData$59(SmallVideoFragment smallVideoFragment, List list, Throwable th) throws Exception {
        Log.d("lln", "error==" + th.getMessage());
        smallVideoFragment.mAdapter.getData().add(new SmallVideoEntity.Data());
        ToastUtil.showToast("当前无此视频数据，自动为您推荐其他视频");
        smallVideoFragment.mCurrentItem = 1;
        smallVideoFragment.videoDataType = VideoDataType.SmallVideo;
        smallVideoFragment.mAdapter.addData(list);
        Log.d("lln", "自动播放下一条");
        smallVideoFragment.mRecyclerView.getLayoutManager().smoothScrollToPosition(smallVideoFragment.mRecyclerView, null, 1);
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.18
            {
                SmallVideoFragment.this = smallVideoFragment;
            }

            @Override // java.lang.Runnable
            public void run() {
                SmallVideoFragment.this.mCurrentItem = 0;
                SmallVideoFragment.this.mAdapter.getData().remove(0);
                SmallVideoFragment.this.mAdapter.notifyItemRemoved(0);
            }
        }, 1000L);
    }

    private String channelCodo() {
        return TextUtils.isEmpty(this.typeId) ? "xiaoshipin" : this.fromType.equals(this.accessName) ? "shipincailing" : "fuyiping";
    }

    public void getSDKAccessCode() {
        new Thread(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$DMslUibKkrI_XhJr7AsfQMPQC0A
            @Override // java.lang.Runnable
            public final void run() {
                SmallVideoFragment.lambda$getSDKAccessCode$61(SmallVideoFragment.this);
            }
        }).start();
    }

    public static /* synthetic */ void lambda$getSDKAccessCode$61(SmallVideoFragment smallVideoFragment) {
        try {
            if (smallVideoFragment.activityContext != null) {
                GetNetAccessCode.getInstance().getSDKAccessCode(smallVideoFragment.activityContext, smallVideoFragment.channelCodo(), new GetNetAccessCode.GetNetAccessCodeClick() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$C2nSfyJpQL42At1C_ub7lTEPRo4
                    @Override // com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.login.utils.GetNetAccessCode.GetNetAccessCodeClick
                    public final void onGetData(String str) {
                        SmallVideoFragment.lambda$getSDKAccessCode$60(SmallVideoFragment.this, str);
                    }
                });
            }
        } catch (Exception unused) {
            MsLogUtil.m7979d(TAG, "net取号初始化问题。");
            smallVideoFragment.isNeedCode = false;
        }
    }

    public static /* synthetic */ void lambda$getSDKAccessCode$60(SmallVideoFragment smallVideoFragment, String str) {
        if (!TextUtils.isEmpty(str)) {
            smallVideoFragment.isNeedCode = "1".equals(str);
        } else {
            smallVideoFragment.isNeedCode = false;
        }
    }

    private void initProgressBar() {
        try {
            this.barRoot = LayoutInflater.from(this.activityContext).inflate(2131493436, (ViewGroup) null);
            this.llProgressBar = (LinearLayout) this.barRoot.findViewById(2131297760);
            this.llProgressTextLine = (LinearLayout) this.barRoot.findViewById(2131297761);
            this.tvStartTime = (TextView) this.barRoot.findViewById(2131299050);
            this.tvEndTime = (TextView) this.barRoot.findViewById(2131299052);
            this.sbProgress = (SeekBar) this.barRoot.findViewById(2131298453);
            this.vBarLandscapeBg = this.barRoot.findViewById(2131299464);
            this.vBarLandscapeEnd = this.barRoot.findViewById(2131299465);
            this.ivBarLandscapeBtn = (ImageView) this.barRoot.findViewById(2131297348);
            this.ivBarLandscapeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$lpLhYgFU9-F1530HoaVru7-vYf4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    SmallVideoFragment.this.clickPlayOrPause();
                }
            });
            setProgressBarLandscape(false);
            clearProgress();
            this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.SmallVideoFragment.19
                {
                    SmallVideoFragment.this = this;
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    if (SmallVideoFragment.this.tvStartTime != null) {
                        SmallVideoFragment.this.tvStartTime.setText(NumUtils.stringForTime(i));
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    SmallVideoFragment.this.isStartTrackingTouch = true;
                    SmallVideoFragment.this.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(SmallVideoFragment.this.activityContext, 2131232586));
                    SmallVideoFragment.this.sbProgress.setThumb(ContextCompat.getDrawable(SmallVideoFragment.this.activityContext, 2131232074));
                    SmallVideoFragment.this.sbProgress.setTag("pause");
                    SmallVideoFragment.this.llProgressTextLine.setVisibility(0);
                    if (SmallVideoFragment.this.rlInfoArea != null) {
                        SmallVideoFragment.this.rlInfoArea.setVisibility(8);
                    }
                    if (SmallVideoFragment.this.playTime != null) {
                        SmallVideoFragment.this.playTime.removeMessages(5000);
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Tracker.onStopTrackingTouch(seekBar);
                    SmallVideoFragment.this.isStartTrackingTouch = false;
                    SmallVideoFragment.this.llProgressTextLine.setVisibility(8);
                    if (SmallVideoFragment.this.rlInfoArea != null) {
                        SmallVideoFragment.this.rlInfoArea.setVisibility(0);
                    }
                    if (SmallVideoFragment.this.bdCloudVideoView != null) {
                        SmallVideoFragment.this.bdCloudVideoView.seekTo(SmallVideoFragment.this.sbProgress.getProgress());
                        if (!SmallVideoFragment.this.bdCloudVideoView.isPlaying()) {
                            SmallVideoFragment.this.bdCloudVideoView.start();
                            SmallVideoFragment.this.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(SmallVideoFragment.this.activityContext, 2131232584));
                            SmallVideoFragment.this.sbProgress.setThumb(ContextCompat.getDrawable(SmallVideoFragment.this.activityContext, 2131232582));
                            SmallVideoFragment.this.sbProgress.setTag(null);
                            SmallVideoFragment.this.ivBarLandscapeBtn.setTag(null);
                        }
                    }
                    try {
                        String videoId = SmallVideoFragment.this.mAdapter.getData().get(SmallVideoFragment.this.mCurrentItem).getVideoId();
                        String videoUrl = SmallVideoFragment.this.mAdapter.getData().get(SmallVideoFragment.this.mCurrentItem).getVideoUrl();
                        String videoUrl2 = SmallVideoFragment.this.previousItem == null ? "" : SmallVideoFragment.this.previousItem.getVideoUrl();
                        String viewTitle = SmallVideoFragment.this.previousItem == null ? "" : SmallVideoFragment.this.previousItem.getViewTitle();
                        String viewTitle2 = SmallVideoFragment.this.mAdapter.getData().get(SmallVideoFragment.this.mCurrentItem).getViewTitle();
                        long progress = SmallVideoFragment.this.sbProgress.getProgress() / 1000;
                        String tjpara = SmallVideoFragment.this.mAdapter.getItem(SmallVideoFragment.this.mCurrentItem).getTjpara();
                        SmallVideoFragment smallVideoFragment = SmallVideoFragment.this;
                        String activityType = SmallVideoFragment.this.getActivityType();
                        String str = ((System.currentTimeMillis() - SmallVideoFragment.this.playBeginTime) / 1000) + "";
                        String str2 = progress + "";
                        smallVideoFragment.setLog("进度条拖动", "23", "进度条拖动", videoUrl, videoUrl2, viewTitle, activityType, "1", viewTitle2, TextUtils.isEmpty(SmallVideoFragment.this.tabTitle) ? "" : SmallVideoFragment.this.tabTitle, str, videoId, str2, SmallVideoFragment.this.realPlayed + "", (SmallVideoFragment.this.bdCloudVideoView.getDuration() / 1000) + "", tjpara);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    SmallVideoFragment.this.autoHideLandscape();
                }
            });
            this.playProgressUpdater = Observable.interval(200L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.fragment.-$$Lambda$SmallVideoFragment$_Se8EBwKechA5wNXAu5Q8Dgg3Y4
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    SmallVideoFragment.lambda$initProgressBar$63(SmallVideoFragment.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initProgressBar$63(SmallVideoFragment smallVideoFragment, Long l) throws Exception {
        BDCloudVideoView bDCloudVideoView;
        SeekBar seekBar = smallVideoFragment.sbProgress;
        if (seekBar != null && seekBar.getMax() > 0 && (bDCloudVideoView = smallVideoFragment.bdCloudVideoView) != null) {
            if (!smallVideoFragment.isStartTrackingTouch) {
                smallVideoFragment.sbProgress.setProgress(bDCloudVideoView.getCurrentPosition());
            }
        } else {
            smallVideoFragment.setProgressBarMax();
        }
        if (smallVideoFragment.isStartTrackingTouch) {
            return;
        }
        ImageView imageView = smallVideoFragment.ivBarLandscapeBtn;
        if (imageView != null && imageView.getTag() == null) {
            Object tag = smallVideoFragment.sbProgress.getTag();
            if ("playing".equals(tag == null ? "" : (String) tag)) {
                return;
            }
            smallVideoFragment.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(smallVideoFragment.activityContext, 2131232584));
            smallVideoFragment.sbProgress.setThumb(ContextCompat.getDrawable(smallVideoFragment.activityContext, 2131232582));
            smallVideoFragment.sbProgress.setTag("playing");
            smallVideoFragment.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(smallVideoFragment.activityContext, 2131232593));
            smallVideoFragment.ivBarLandscapeBtn.setTag(null);
            return;
        }
        Object tag2 = smallVideoFragment.sbProgress.getTag();
        if ("pause".equals(tag2 == null ? "" : (String) tag2)) {
            return;
        }
        smallVideoFragment.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(smallVideoFragment.activityContext, 2131232576));
        smallVideoFragment.sbProgress.setThumb(ContextCompat.getDrawable(smallVideoFragment.activityContext, 2131232575));
        smallVideoFragment.sbProgress.setTag("pause");
        smallVideoFragment.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(smallVideoFragment.activityContext, 2131232592));
        smallVideoFragment.ivBarLandscapeBtn.setTag("pause");
    }

    public void clickPlayOrPause() {
        try {
            if (this.bdCloudVideoView == null || this.bdCloudVideoView.getCurrentPosition() <= 900) {
                return;
            }
            if (this.bdCloudVideoView.isPlaying()) {
                this.bdCloudVideoView.pause();
                if (this.mISmallVideoImpl.getScreenOrientation(this.activityContext) == 0) {
                    this.ivBtnPlay2.setVisibility(8);
                    this.ivBtnPlay.setVisibility(8);
                } else if (this.bdCloudVideoView.getVideoWidth() > this.bdCloudVideoView.getVideoHeight()) {
                    this.ivBtnPlay2.setVisibility(8);
                    this.ivBtnPlay.setVisibility(0);
                } else {
                    this.ivBtnPlay2.setVisibility(0);
                    this.ivBtnPlay.setVisibility(8);
                }
                loadPauseAdView();
                setLog("点击按钮", "19", "点击暂停", "2");
                PvCurrencyLogUtils.pvLogLive("", 2, "", "暂停", "视频", "", "视频");
            } else {
                this.bdCloudVideoView.start();
                this.ivBtnPlay.setVisibility(8);
                this.ivBtnPlay2.setVisibility(8);
                this.rlAdPauseView.setVisibility(8);
                this.rlAdPauseViewLandscape.setVisibility(8);
                setLog("点击按钮", "19", "点击播放", "2");
                PvCurrencyLogUtils.pvLogLive("", 2, "", "播放", "视频", "", "视频");
            }
            this.playTime.removeMessages(5000);
            this.playTime.sendEmptyMessageDelayed(5000, 5000L);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void setPlayerBtnStatus(boolean z) {
        this.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(this.activityContext, z ? 2131232592 : 2131232593));
        this.ivBarLandscapeBtn.setTag(z ? "pause" : null);
    }

    private void addProgressTo(FrameLayout frameLayout) {
        try {
            MsLogUtil.m7979d(TAG, "进度条加入布局");
            if (frameLayout == null || this.barRoot == null) {
                return;
            }
            ViewGroup viewGroup = (ViewGroup) this.barRoot.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            this.sbProgress.setMax(0);
            frameLayout.addView(this.barRoot);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void setProgressBarLandscape(boolean z) {
        try {
            if (this.vBarLandscapeBg != null) {
                int i = 0;
                this.vBarLandscapeBg.setVisibility(z ? 0 : 8);
                this.vBarLandscapeEnd.setVisibility(z ? 0 : 8);
                ImageView imageView = this.ivBarLandscapeBtn;
                if (!z) {
                    i = 8;
                }
                imageView.setVisibility(i);
                RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.llProgressBar.getLayoutParams();
                if (z) {
                    layoutParams.height = UIUtils.dip2px(70.0f);
                } else {
                    layoutParams.height = -2;
                }
                this.llProgressBar.setLayoutParams(layoutParams);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void setProgressBarMax() {
        try {
            if (this.bdCloudVideoView == null || this.sbProgress == null || this.tvEndTime == null || this.bdCloudVideoView.getDuration() <= 0) {
                return;
            }
            int duration = this.bdCloudVideoView.getDuration();
            this.sbProgress.setMax(duration);
            this.tvEndTime.setText(NumUtils.stringForTime(duration));
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    private void clearProgress() {
        try {
            if (this.sbProgress == null || this.tvStartTime == null || this.tvEndTime == null) {
                return;
            }
            this.sbProgress.setProgress(0);
            this.sbProgress.setMax(0);
            this.tvEndTime.setText("00:00");
            this.tvStartTime.setText("00:00");
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }
}
