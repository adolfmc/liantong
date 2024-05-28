package com.sinovatech.unicom.separatemodule.audience.playback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
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
import com.fort.andjni.JniLib;
import com.jakewharton.rxbinding2.view.RxView;
import com.megvii.livenesslib.util.ConUtil;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.AudienceMessageView;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.AngleMoreEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceUser;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveMsg;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveRoomUiHideEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.PlayBackPayInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.audience.util.InputDialog;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGuanzhuDialog;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView;
import com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView;
import com.sinovatech.unicom.separatemodule.audience.view.ToastDialogManager;
import com.sinovatech.unicom.separatemodule.audience.view.WiseEditText;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.tongyicaiji.TYCJBoxManager;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudiencePlayBackActivity extends BaseActivity {
    private static final String TAG = "AudiencePlayBackActivity";
    public NBSTraceUnit _nbs_trace;
    private AudiencePlayBackActivity activityContext;
    private Disposable addView2List;
    private InputDialog alivcInputTextDialog;
    private BaseQuickAdapter<AngleMoreEntity, BaseViewHolder> angleAdapter;
    private AudienceUser audienceUser;
    public BDCloudVideoView bdCloudVideoView;
    public String buyUrl;
    private Disposable changeAngleView;
    private View.OnClickListener clickVideo;
    private String coverImg;
    private SharpnessEntity.LiveUrlBean currentLiveUrl;
    private LiveGiftView dianZanAnim;
    private FrameLayout flInputPassword;
    private FrameLayout flMessageArea;
    private WiseEditText inputPw1;
    private WiseEditText inputPw2;
    private WiseEditText inputPw3;
    private WiseEditText inputPw4;
    private boolean isClickPause;
    private boolean isHorizontal;
    private boolean isMulti;
    public boolean isNeedPay;
    public boolean isPB;
    private boolean isRunning;
    private ImageView ivCover;
    private View ivPlayBtn;
    private ImageView ivPlayIcon;
    private String jobNumber;
    private AudienceLandscapeView landscapeView;
    private LiveRoomUiHideEntity liveRoomUIInfo;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private LinearLayout llPayInfo;
    private LinearLayout llPayTipe;
    private LinearLayout llTrySeeDialog;
    private LinearLayout llTwo;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private AudienceMessageView messageView;
    private LinearLayoutManager multiLayoutManager;
    public PlayBackPayInfoEntity payInfo4PB;
    private Disposable payInfoCountDown;
    private FrameLayout payView;
    private RelativeLayout rlInputPw;
    private RelativeLayout rlPlayBtn;
    private RelativeLayout rlTip;
    private View root;
    private String shareCode;
    private SharpnessEntity sharpness;
    private int timePayInfo;
    private String tips;
    private View toggleBack;
    private TextView tvBtnPay4PB;
    private TextView tvCancel;
    private TextView tvFreeTimeTips;
    private TextView tvNoOrder;
    private TextView tvOk;
    private TextView tvOpportunity;
    private TextView tvOrder;
    private TextView tvPayTips;
    private TextView tvReset;
    private View vTop1;
    private View vTop2;
    private String videoId;
    private ZhuboDataEntity zhuboDataEntity;
    private List<BDCloudVideoView> players = new ArrayList();
    private List<BDCloudVideoView> selectedPlayer = new ArrayList();
    private Handler playVideoHandler = new Handler(Looper.myLooper(), new Handler.Callback() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity.2
        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            AudiencePlayBackActivity audiencePlayBackActivity = AudiencePlayBackActivity.this;
            audiencePlayBackActivity.handleSharpnessInfo(audiencePlayBackActivity.sharpness, AudiencePlayBackActivity.this.jobNumber, AudiencePlayBackActivity.this.activityContext.root, AudiencePlayBackActivity.this.coverImg);
            return false;
        }
    });
    private Handler verifyPwdHandler = new Handler(Looper.myLooper(), new C83844());

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 69);
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

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    public static /* synthetic */ void lambda$onCreate$0(AudiencePlayBackActivity audiencePlayBackActivity, int i, Intent intent) {
        if (App.hasLogined()) {
            audiencePlayBackActivity.entrance(audiencePlayBackActivity.getIntent());
        } else {
            audiencePlayBackActivity.activityContext.finish();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onNewIntent(Intent intent) {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.stopPlayback();
            ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
        }
        entrance(intent);
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        if (getScreenOrientation(this) == 0) {
            toggleFullScreen(this);
        }
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        this.isRunning = true;
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null && !bDCloudVideoView.isPlaying() && !this.isNeedPay && !this.isClickPause) {
            this.bdCloudVideoView.start();
        }
        NBSAppInstrumentation.activityResumeEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        UIUtils.logD(TAG, "onPause");
        if (isFinishing()) {
            UIUtils.logD(TAG, "isFinishing");
            try {
                if (this.messageView != null) {
                    this.messageView.clearMessage();
                    this.messageView.releaseTimer();
                    this.messageView.releasePkTimer();
                }
                releaseVideo();
                clearPayInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.isRunning = false;
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.pause();
        }
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        UIUtils.logD(TAG, "onDestroy");
        try {
            if (this.verifyPwdHandler != null) {
                this.verifyPwdHandler.removeCallbacksAndMessages(null);
            }
            if (this.playVideoHandler != null) {
                this.playVideoHandler.removeCallbacksAndMessages(null);
            }
            if (this.messageView != null) {
                this.messageView.clearMessage();
                this.messageView.releaseTimer();
                this.messageView.releasePkTimer();
            }
            releaseVideo();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void releaseVideo() {
        try {
            this.bdCloudVideoView.stopPlayback();
            this.bdCloudVideoView.release();
            this.bdCloudVideoView.setTag("");
            AudienceGoodsListDialogNew.getInstance().releaseDialog();
            ViewParent parent = this.bdCloudVideoView.getParent();
            if (parent instanceof FrameLayout) {
                ((FrameLayout) parent).removeView(this.bdCloudVideoView);
            }
            releaseAngleMorePlayers();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void releaseAngleMorePlayers() {
        if (this.players != null) {
            UIUtils.logD("xcy", "回收多视角播放器");
            for (int i = 0; i < this.players.size(); i++) {
                BDCloudVideoView bDCloudVideoView = this.players.get(i);
                bDCloudVideoView.stopPlayback();
                bDCloudVideoView.release();
                ViewGroup viewGroup = (ViewGroup) bDCloudVideoView.getParent();
                if (viewGroup != null) {
                    viewGroup.removeAllViews();
                }
            }
        }
    }

    private void entrance(Intent intent) {
        this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        initView();
        initData(intent);
    }

    private void initView() {
        this.root = findViewById(2131296441);
        this.flInputPassword = (FrameLayout) findViewById(2131296992);
        this.ivCover = (ImageView) findViewById(2131296393);
        this.flMessageArea = (FrameLayout) findViewById(2131296434);
        this.messageView = (AudienceMessageView) LayoutInflater.from(this.activityContext).inflate(2131492998, (ViewGroup) null);
        this.landscapeView = (AudienceLandscapeView) LayoutInflater.from(this.activityContext).inflate(2131492990, (ViewGroup) null);
        this.dianZanAnim = (LiveGiftView) findViewById(2131296884);
        this.dianZanAnim.setVisibility(0);
        this.alivcInputTextDialog = new InputDialog(this.activityContext, "1234567");
        this.toggleBack = findViewById(2131296464);
        BDCloudVideoView.setAK("3927c43912004909bf897578e93bc5f9");
        this.bdCloudVideoView = (BDCloudVideoView) LayoutInflater.from(this).inflate(2131492974, (ViewGroup) null);
        this.bdCloudVideoView.setMaxProbeTime(200);
        this.bdCloudVideoView.setCachingHintViewVisibility(false);
        this.bdCloudVideoView.setLooping(true);
        this.bdCloudVideoView.setBufferTimeInMs(200);
        this.bdCloudVideoView.setMaxCacheSizeInBytes(1048576);
        this.bdCloudVideoView.toggleFrameChasing(false);
        this.bdCloudVideoView.setVideoScalingMode(1);
        this.bdCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$zMb4_G0a_WGSMnJ1IRpQO3_Q218
            @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
            public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                AudiencePlayBackActivity.lambda$initView$1(AudiencePlayBackActivity.this, playerState);
            }
        });
        TYCJBoxManager.getInstance().collectClickSdk(this.activityContext, "S2ndpage1214", "直播回放", "百度播放器", "", "com.baidu.cloud.cloudplayer", "0");
        this.clickVideo = new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$dNeFpa2O6ge_pRmMtFfjRFQHNpA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudiencePlayBackActivity.lambda$initView$2(AudiencePlayBackActivity.this, view);
            }
        };
        this.rlPlayBtn = (RelativeLayout) findViewById(2131298368);
        this.rlPlayBtn.setVisibility(8);
        this.ivPlayBtn = findViewById(2131297449);
        this.ivPlayBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$l1R3OG6s_CcWLpTlJgdYgSk0DhU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudiencePlayBackActivity.lambda$initView$3(AudiencePlayBackActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initView$1(AudiencePlayBackActivity audiencePlayBackActivity, BDCloudVideoView.PlayerState playerState) {
        try {
            UIUtils.logD(TAG, "333333333----" + playerState);
            switch (playerState) {
                case STATE_ERROR:
                case STATE_PREPARED:
                case STATE_PLAYBACK_COMPLETED:
                    return;
                case STATE_IDLE:
                    audiencePlayBackActivity.showLiveCover();
                    return;
                case STATE_PREPARING:
                    audiencePlayBackActivity.bdCloudVideoView.setCachingHintViewVisibility(false);
                    return;
                case STATE_PLAYING:
                    audiencePlayBackActivity.isClickPause = false;
                    audiencePlayBackActivity.bdCloudVideoView.setCachingHintViewVisibility(false);
                    if (audiencePlayBackActivity.isNeedPay) {
                        if (audiencePlayBackActivity.bdCloudVideoView != null) {
                            audiencePlayBackActivity.bdCloudVideoView.pause();
                            return;
                        }
                        return;
                    }
                    audiencePlayBackActivity.rlPlayBtn.setVisibility(8);
                    audiencePlayBackActivity.hideLiveCover();
                    if (audiencePlayBackActivity.messageView != null) {
                        audiencePlayBackActivity.messageView.setPlayBtn(true);
                    }
                    if (audiencePlayBackActivity.landscapeView != null) {
                        audiencePlayBackActivity.landscapeView.setPlayBtn(true);
                        return;
                    }
                    return;
                case STATE_PAUSED:
                    if (audiencePlayBackActivity.isNeedPay) {
                        return;
                    }
                    audiencePlayBackActivity.rlPlayBtn.setVisibility(0);
                    return;
                case STATE_VIDEOSIZE_CHANGED:
                    UIUtils.logD(TAG, audiencePlayBackActivity.bdCloudVideoView.getVideoHeight() + "----" + playerState + "-----" + audiencePlayBackActivity.bdCloudVideoView.getVideoWidth());
                    try {
                        if (audiencePlayBackActivity.root == null || audiencePlayBackActivity.isNeedPay) {
                            return;
                        }
                        audiencePlayBackActivity.addVideoView(audiencePlayBackActivity.root);
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
                default:
                    return;
            }
        } catch (Exception e2) {
            MsLogUtil.m7979d(TAG, e2.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initView$2(AudiencePlayBackActivity audiencePlayBackActivity, View view) {
        if (audiencePlayBackActivity.bdCloudVideoView.getDuration() > 0) {
            if (audiencePlayBackActivity.rlPlayBtn.getVisibility() == 8) {
                audiencePlayBackActivity.rlPlayBtn.setVisibility(0);
                BDCloudVideoView bDCloudVideoView = audiencePlayBackActivity.bdCloudVideoView;
                if (bDCloudVideoView != null) {
                    audiencePlayBackActivity.isClickPause = true;
                    bDCloudVideoView.pause();
                    PvCurrencyLogUtils.pvLogLive("", 2, "", "暂停", "直播回放页", "", "直播回放页");
                }
                AudienceMessageView audienceMessageView = audiencePlayBackActivity.messageView;
                if (audienceMessageView != null) {
                    audienceMessageView.setPlayBtn(false);
                }
                AudienceLandscapeView audienceLandscapeView = audiencePlayBackActivity.landscapeView;
                if (audienceLandscapeView != null) {
                    audienceLandscapeView.setPlayBtn(false);
                }
                if (audiencePlayBackActivity.isNeedPay) {
                    View view2 = audiencePlayBackActivity.ivPlayBtn;
                    if (view2 != null) {
                        view2.setVisibility(8);
                        return;
                    }
                    return;
                }
                View view3 = audiencePlayBackActivity.ivPlayBtn;
                if (view3 != null) {
                    view3.setVisibility(0);
                }
            } else if (!audiencePlayBackActivity.isNeedPay) {
                audiencePlayBackActivity.rlPlayBtn.setVisibility(8);
                View view4 = audiencePlayBackActivity.ivPlayBtn;
                if (view4 != null) {
                    view4.setVisibility(0);
                }
                BDCloudVideoView bDCloudVideoView2 = audiencePlayBackActivity.bdCloudVideoView;
                if (bDCloudVideoView2 != null) {
                    bDCloudVideoView2.start();
                }
                AudienceMessageView audienceMessageView2 = audiencePlayBackActivity.messageView;
                if (audienceMessageView2 != null) {
                    audienceMessageView2.setPlayBtn(true);
                }
                AudienceLandscapeView audienceLandscapeView2 = audiencePlayBackActivity.landscapeView;
                if (audienceLandscapeView2 != null) {
                    audienceLandscapeView2.setPlayBtn(true);
                }
            } else {
                audiencePlayBackActivity.pbReplay();
            }
        }
    }

    public static /* synthetic */ void lambda$initView$3(AudiencePlayBackActivity audiencePlayBackActivity, View view) {
        if (audiencePlayBackActivity.isNeedPay) {
            return;
        }
        audiencePlayBackActivity.rlPlayBtn.setVisibility(8);
        BDCloudVideoView bDCloudVideoView = audiencePlayBackActivity.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.start();
            PvCurrencyLogUtils.pvLogLive("", 2, "", "播放", "直播回放页", "", "直播回放页");
        }
        AudienceMessageView audienceMessageView = audiencePlayBackActivity.messageView;
        if (audienceMessageView != null) {
            audienceMessageView.setPlayBtn(true);
        }
        AudienceLandscapeView audienceLandscapeView = audiencePlayBackActivity.landscapeView;
        if (audienceLandscapeView != null) {
            audienceLandscapeView.setPlayBtn(true);
        }
    }

    private void showLiveCover() {
        ImageView imageView;
        View view = this.root;
        if (view == null || (imageView = (ImageView) view.findViewById(2131296393)) == null) {
            return;
        }
        UIUtils.logD(TAG, "显示直播间背景图");
        if (getMultiSelectedCount() < 2) {
            imageView.setVisibility(0);
        }
    }

    private void hideLiveCover() {
        ImageView imageView;
        View view = this.root;
        if (view == null || (imageView = (ImageView) view.findViewById(2131296393)) == null) {
            return;
        }
        UIUtils.logD(TAG, "隐藏直播间背景图");
        imageView.setVisibility(8);
    }

    private void initData(Intent intent) {
        this.jobNumber = intent.getStringExtra("userId");
        this.videoId = intent.getStringExtra("videoId");
        this.audienceUser = new AudienceUser();
        this.audienceUser.setLogin(true);
        this.audienceUser.setNick(UserManager.getInstance().getUserAccountName());
        getRoomInfo();
    }

    private void getRoomInfo() {
        this.managerAudienceLoadData.getAngleMoreVideoInfo(this.videoId, "videoBack").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$1jkXI0EZeQJKpAAp3XfAzKr6gBA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$getRoomInfo$6(AudiencePlayBackActivity.this, (SharpnessEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$hw7jL5Le5HuNjMkbKaAgPriNvKE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$getRoomInfo$7(AudiencePlayBackActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getRoomInfo$6(final AudiencePlayBackActivity audiencePlayBackActivity, SharpnessEntity sharpnessEntity) throws Exception {
        UIUtils.logD(TAG, "多视角回放请求成功");
        if ("0000".equals(sharpnessEntity.getStatusCode())) {
            final ZhuboDataEntity userInfo = sharpnessEntity.getUserInfo();
            audiencePlayBackActivity.zhuboDataEntity = userInfo;
            GlideApp.with((FragmentActivity) audiencePlayBackActivity.activityContext).load(userInfo.getAnchorInfoBean().getLiveCoverImg()).into(audiencePlayBackActivity.ivCover);
            ZhuboDataEntity.AnchorInfoBean anchorInfoBean = userInfo.getAnchorInfoBean();
            audiencePlayBackActivity.isMulti = "Y".equals(sharpnessEntity.getLiveViewAngleMore());
            audiencePlayBackActivity.messageView.initData(userInfo, audiencePlayBackActivity.managerAudienceLoadData, audiencePlayBackActivity.audienceUser, new C83791(userInfo));
            audiencePlayBackActivity.messageView.setClickListener(audiencePlayBackActivity.clickVideo);
            ViewParent parent = audiencePlayBackActivity.messageView.getParent();
            if (parent != null) {
                ((ViewGroup) parent).removeAllViews();
            }
            audiencePlayBackActivity.flMessageArea.removeAllViews();
            audiencePlayBackActivity.flMessageArea.addView(audiencePlayBackActivity.messageView);
            audiencePlayBackActivity.messageView.setLiveType(anchorInfoBean.getDataType());
            audiencePlayBackActivity.landscapeView.setLiveType(anchorInfoBean.getDataType());
            audiencePlayBackActivity.messageView.hideGiftUI();
            audiencePlayBackActivity.landscapeView.hideGiftUI();
            audiencePlayBackActivity.getShareCode(anchorInfoBean);
            audiencePlayBackActivity.managerAudienceLoadData.loadZhuboData(audiencePlayBackActivity.jobNumber, "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$NiV7cApIVfa7q05zrlw8FxxPZWg
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudiencePlayBackActivity.lambda$getRoomInfo$4(AudiencePlayBackActivity.this, (ZhuboDataEntity) obj);
                }
            });
            ShopEntity shopEntity = sharpnessEntity.getShopEntity();
            audiencePlayBackActivity.messageView.setGoods(shopEntity);
            audiencePlayBackActivity.landscapeView.setGoods(shopEntity);
            audiencePlayBackActivity.messageView.setGoodsList(sharpnessEntity.getGoodlist());
            audiencePlayBackActivity.landscapeView.setGoodsList(sharpnessEntity.getGoodlist());
            audiencePlayBackActivity.messageView.setZanText(sharpnessEntity.getVideoPraiseNum(), audiencePlayBackActivity.videoId);
            audiencePlayBackActivity.landscapeView.setZanText(sharpnessEntity.getVideoPraiseNum(), audiencePlayBackActivity.videoId);
            audiencePlayBackActivity.managerAudienceLoadData.isGuanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$2gfLcSW7aZQJ0WPm5a_WsNa-KE0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudiencePlayBackActivity.lambda$getRoomInfo$5(AudiencePlayBackActivity.this, userInfo, (Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            String isNeedCheck = sharpnessEntity.getIsNeedCheck();
            audiencePlayBackActivity.sharpness = sharpnessEntity;
            audiencePlayBackActivity.coverImg = anchorInfoBean.getLiveCoverImg();
            MsLogUtil.m7979d(TAG, "密码状态：" + isNeedCheck);
            int i = 0;
            if ("Y".equals(isNeedCheck)) {
                View inflate = LayoutInflater.from(audiencePlayBackActivity.activityContext).inflate(2131493094, (ViewGroup) null);
                audiencePlayBackActivity.flInputPassword.removeAllViews();
                audiencePlayBackActivity.flInputPassword.addView(inflate);
                audiencePlayBackActivity.flInputPassword.setVisibility(0);
                try {
                    i = Integer.parseInt(sharpnessEntity.getPasswordCheckErrorNum());
                } catch (Exception unused) {
                    MsLogUtil.m7979d(TAG, "数字转化异常");
                }
                audiencePlayBackActivity.verifyPassWord(audiencePlayBackActivity.videoId, i);
                return;
            }
            audiencePlayBackActivity.playVideoHandler.sendEmptyMessage(0);
            return;
        }
        MsLogUtil.m7979d(TAG, "获取回放失败");
        audiencePlayBackActivity.jump2Live();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83791 implements AudienceMessageView.IPage1Click {
        final /* synthetic */ ZhuboDataEntity val$entity;

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void clickShoppingCart() {
        }

        C83791(ZhuboDataEntity zhuboDataEntity) {
            this.val$entity = zhuboDataEntity;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public boolean isNeedPay() {
            return AudiencePlayBackActivity.this.activityContext.isNeedPay();
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void showZanAnim() {
            if (AudiencePlayBackActivity.this.dianZanAnim != null) {
                AudiencePlayBackActivity.this.dianZanAnim.startLocalZanSvgaAnim();
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void clickGoods(ShopEntity.DataBean.GoodsBean goodsBean) {
            String livePullUrl = this.val$entity.getAnchorInfoBean().getLivePullUrl();
            String userName = this.val$entity.getAnchorInfoBean().getUserName();
            PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, "商品卡片" + goodsBean.getName() + goodsBean.getGoodsUrl(), "直播回放页", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播回放页");
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void userInfo() {
            try {
                String userIndexUrl = this.val$entity.getAnchorInfoBean().getUserIndexUrl();
                if (!TextUtils.isEmpty(userIndexUrl)) {
                    AudiencePlayBackActivity.this.IntentGoWithOutVideo(AudiencePlayBackActivity.this.addChannel(userIndexUrl, "直播回放"));
                }
                PvCurrencyLogUtils.pvLogLive(this.val$entity.getAnchorInfoBean().getLivePullUrl(), 2, this.val$entity.getAnchorInfoBean().getUserName(), "头像", "直播回放", this.val$entity.getAnchorInfoBean().getLiveTitle(), "直播回放页");
            } catch (Exception e) {
                MsLogUtil.m7979d(AudiencePlayBackActivity.TAG, e.getMessage());
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void toggle() {
            AudiencePlayBackActivity audiencePlayBackActivity = AudiencePlayBackActivity.this;
            audiencePlayBackActivity.toggleFullScreen(audiencePlayBackActivity.activityContext);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void zanNum(String str) {
            if (AudiencePlayBackActivity.this.landscapeView != null) {
                AudiencePlayBackActivity.this.landscapeView.setZanNum(str);
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
            AudiencePlayBackActivity.this.bdCloudVideoView.stopPlayback();
            AudiencePlayBackActivity.this.playUrl(liveUrlBean);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.IPage1Click
        public void showKeyboard() {
            AudiencePlayBackActivity.this.alivcInputTextDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$1$t6G9pdyhMetj7SPGu8tfdZh_Row
                @Override // com.sinovatech.unicom.separatemodule.audience.util.InputDialog.OnTextSendListener
                public final void onTextSend(String str) {
                    AudiencePlayBackActivity.C83791.lambda$showKeyboard$0(AudiencePlayBackActivity.C83791.this, str);
                }
            });
            AudiencePlayBackActivity.this.alivcInputTextDialog.show();
            PvCurrencyLogUtils.pvLogLive("", 2, "", "聊天框", "直播回放页", "", "直播回放页");
        }

        public static /* synthetic */ void lambda$showKeyboard$0(C83791 c83791, String str) {
            if (AudiencePlayBackActivity.this.audienceUser.isLogin()) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setMsg(str.replace("|*|", ""));
                if (str.contains("@") && str.contains("|*|")) {
                    liveMsg.setType("atto");
                    liveMsg.setAtto("all");
                } else {
                    liveMsg.setType("chat");
                }
                liveMsg.setLevel(AudiencePlayBackActivity.this.audienceUser.getLevel());
                liveMsg.setNick(AudiencePlayBackActivity.this.audienceUser.getNick());
                liveMsg.setMgr(AudiencePlayBackActivity.this.audienceUser.isMgr());
                AudiencePlayBackActivity.this.messageView.updateMessageAdapter(liveMsg);
                PvCurrencyLogUtils.pvLogLive("", 2, "", "聊天框-发送(" + liveMsg.getMsg() + ")", "直播回放页", "", "直播回放页");
            }
        }
    }

    public static /* synthetic */ void lambda$getRoomInfo$4(AudiencePlayBackActivity audiencePlayBackActivity, ZhuboDataEntity zhuboDataEntity) throws Exception {
        audiencePlayBackActivity.messageView.updatePush(zhuboDataEntity);
        audiencePlayBackActivity.landscapeView.updatePush(zhuboDataEntity);
    }

    public static /* synthetic */ void lambda$getRoomInfo$5(AudiencePlayBackActivity audiencePlayBackActivity, ZhuboDataEntity zhuboDataEntity, Boolean bool) throws Exception {
        if (audiencePlayBackActivity.isMulti) {
            audiencePlayBackActivity.messageView.setGuanzhuView(true, zhuboDataEntity);
            audiencePlayBackActivity.landscapeView.setGuanzuGone(true);
            return;
        }
        audiencePlayBackActivity.messageView.setGuanzhuView(bool.booleanValue(), zhuboDataEntity);
        audiencePlayBackActivity.landscapeView.setGuanzuGone(bool.booleanValue());
    }

    public static /* synthetic */ void lambda$getRoomInfo$7(AudiencePlayBackActivity audiencePlayBackActivity, Throwable th) throws Exception {
        th.printStackTrace();
        UIUtils.logD(TAG, "多视角回放请求错误：" + th.getMessage());
        UIUtils.toastCenter("加载失败，请稍后再试");
        audiencePlayBackActivity.startActivity(new Intent(audiencePlayBackActivity.activityContext, AudienceActivity.class));
        audiencePlayBackActivity.finish();
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

    private void jump2Live() {
        UIUtils.toastCenter("您当前无权限访问，为您推荐其他直播");
        startActivity(new Intent(this.activityContext, AudienceActivity.class));
        finish();
    }

    private void verifyPassWord(String str, int i) {
        try {
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
                this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$7Hs4LSOp7Q3HuQldxXkRB6h_-uo
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudiencePlayBackActivity.this.activityContext.finish();
                    }
                });
            }
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$HOVuWzG6xYdx-Opu7b7Xzs8J9g0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudiencePlayBackActivity.this.activityContext.finish();
                }
            });
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$zQQ5T5AfWiU3ou0ktVlvq2KCl_A
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudiencePlayBackActivity.lambda$verifyPassWord$10(AudiencePlayBackActivity.this, imageView3, view);
                }
            });
            imageView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$cGp8AmNv34xvx4w6xbBLJdBPq-4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudiencePlayBackActivity.lambda$verifyPassWord$11(AudiencePlayBackActivity.this, imageView3, view);
                }
            });
            final WiseEditText[] wiseEditTextArr = {this.inputPw1, this.inputPw2, this.inputPw3, this.inputPw4};
            for (final int i2 = 0; i2 < wiseEditTextArr.length; i2++) {
                wiseEditTextArr[i2].addTextChangedListener(new TextWatcher() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity.3
                    private int mEnd;
                    private int mStart;
                    private CharSequence temp;

                    @Override // android.text.TextWatcher
                    public void onTextChanged(CharSequence charSequence, int i3, int i4, int i5) {
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
                            if (AudiencePlayBackActivity.this.verifyPwdHandler != null) {
                                AudiencePlayBackActivity.this.verifyPwdHandler.sendEmptyMessage(0);
                            }
                        } catch (Exception e) {
                            MsLogUtil.m7979d(AudiencePlayBackActivity.TAG, "密码校验发生异常:" + e.getMessage());
                        }
                    }
                });
                wiseEditTextArr[i2].setSoftKeyListener(new View.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$QXuSiZDJLrhxME2YeughTbOa_tU
                    @Override // android.view.View.OnKeyListener
                    public final boolean onKey(View view, int i3, KeyEvent keyEvent) {
                        return AudiencePlayBackActivity.lambda$verifyPassWord$12(wiseEditTextArr, i2, view, i3, keyEvent);
                    }
                });
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, "密码校验初始化失败:" + e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$verifyPassWord$10(AudiencePlayBackActivity audiencePlayBackActivity, ImageView imageView, View view) {
        imageView.setVisibility(0);
        audiencePlayBackActivity.rlInputPw.setVisibility(8);
        audiencePlayBackActivity.rlTip.setVisibility(8);
    }

    public static /* synthetic */ void lambda$verifyPassWord$11(AudiencePlayBackActivity audiencePlayBackActivity, ImageView imageView, View view) {
        audiencePlayBackActivity.rlInputPw.setVisibility(0);
        imageView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$verifyPassWord$12(WiseEditText[] wiseEditTextArr, int i, View view, int i2, KeyEvent keyEvent) {
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

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83844 implements Handler.Callback {
        C83844() {
        }

        @Override // android.os.Handler.Callback
        public boolean handleMessage(@NonNull Message message) {
            String obj = AudiencePlayBackActivity.this.inputPw1.getText().toString();
            String obj2 = AudiencePlayBackActivity.this.inputPw2.getText().toString();
            String obj3 = AudiencePlayBackActivity.this.inputPw3.getText().toString();
            String obj4 = AudiencePlayBackActivity.this.inputPw4.getText().toString();
            final StringBuilder sb = new StringBuilder();
            if (TextUtils.isEmpty(obj.trim()) || TextUtils.isEmpty(obj2.trim()) || TextUtils.isEmpty(obj3.trim()) || TextUtils.isEmpty(obj4.trim())) {
                return false;
            }
            sb.append(obj);
            sb.append(obj2);
            sb.append(obj3);
            sb.append(obj4);
            ConUtil.isGoneKeyBoard(AudiencePlayBackActivity.this.activityContext);
            AudiencePlayBackActivity.this.managerAudienceLoadData.verifyPwd(AudiencePlayBackActivity.this.videoId, sb.toString()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$4$mE1PJM-upGD8_uAQqDbkmOZQogw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj5) {
                    AudiencePlayBackActivity.C83844.lambda$handleMessage$4(AudiencePlayBackActivity.C83844.this, sb, (Map) obj5);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$4$hmNAEUz1hUFIryifiq1HreN-dq0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj5) {
                    MsLogUtil.m7979d(AudiencePlayBackActivity.TAG, ((Throwable) obj5).getMessage());
                }
            });
            return false;
        }

        public static /* synthetic */ void lambda$handleMessage$4(final C83844 c83844, StringBuilder sb, Map map) throws Exception {
            int i;
            String str = (String) map.get("statusCode");
            AudiencePlayBackActivity.this.rlInputPw.setVisibility(8);
            if ("0000".equals(str)) {
                AudiencePlayBackActivity.this.flInputPassword.removeAllViews();
                AudiencePlayBackActivity.this.flInputPassword.setVisibility(8);
                if (AudiencePlayBackActivity.this.playVideoHandler != null) {
                    AudiencePlayBackActivity.this.playVideoHandler.sendEmptyMessage(0);
                }
            } else if ("0004".equals(str)) {
                AudiencePlayBackActivity.this.rlInputPw.setVisibility(8);
                AudiencePlayBackActivity.this.rlTip.setVisibility(0);
                AudiencePlayBackActivity.this.llTwo.setVisibility(8);
                AudiencePlayBackActivity.this.tvOk.setVisibility(0);
                AudiencePlayBackActivity.this.tvOpportunity.setText("当前直播回放三次输入密码均有误，今天不可重试。");
                AudiencePlayBackActivity.this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$4$o6WcwFjB957yw27G-Grruj3WDHg
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudiencePlayBackActivity.this.activityContext.finish();
                    }
                });
            } else {
                AudiencePlayBackActivity.this.rlTip.setVisibility(0);
                sb.setLength(0);
                try {
                    i = Integer.parseInt((String) map.get("data"));
                } catch (NumberFormatException unused) {
                    MsLogUtil.m7979d(AudiencePlayBackActivity.TAG, "已校验失败次数转化失败");
                    i = 0;
                }
                int i2 = 3 - i;
                if (i2 >= 1) {
                    AudiencePlayBackActivity.this.llTwo.setVisibility(0);
                    AudiencePlayBackActivity.this.tvOk.setVisibility(8);
                    AudiencePlayBackActivity.this.llTwo.setVisibility(0);
                    AudiencePlayBackActivity.this.tvOpportunity.setText("当前直播间每天仅限三次输入机会，您还有" + i2 + "次机会");
                    AudiencePlayBackActivity.this.tvReset.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$4$_TR65lUrL57_Ky4cHS0ogZJn00U
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AudiencePlayBackActivity.C83844.lambda$handleMessage$1(AudiencePlayBackActivity.C83844.this, view);
                        }
                    });
                    AudiencePlayBackActivity.this.tvCancel.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$4$F6heESck9Ab4EH8q26mcxXaE6GE
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AudiencePlayBackActivity.this.activityContext.finish();
                        }
                    });
                    return;
                }
                AudiencePlayBackActivity.this.rlInputPw.setVisibility(8);
                AudiencePlayBackActivity.this.rlTip.setVisibility(0);
                AudiencePlayBackActivity.this.llTwo.setVisibility(8);
                AudiencePlayBackActivity.this.tvOk.setVisibility(0);
                AudiencePlayBackActivity.this.tvOpportunity.setText("当前直播回放三次输入密码均有误，今天不可重试");
                AudiencePlayBackActivity.this.tvOk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$4$ZWCbg6z61BffrKjFi_J3o3Zq5Qk
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudiencePlayBackActivity.this.activityContext.finish();
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$handleMessage$1(C83844 c83844, View view) {
            AudiencePlayBackActivity.this.rlTip.setVisibility(8);
            AudiencePlayBackActivity.this.rlInputPw.setVisibility(0);
            AudiencePlayBackActivity.this.inputPw1.getText().clear();
            AudiencePlayBackActivity.this.inputPw2.getText().clear();
            AudiencePlayBackActivity.this.inputPw3.getText().clear();
            AudiencePlayBackActivity.this.inputPw4.getText().clear();
            AudiencePlayBackActivity.this.inputPw1.requestFocus();
        }
    }

    public void clickGuanzhu() {
        this.landscapeView.setGuanzuGone(true);
    }

    private void showPayViewTop() {
        boolean z = getScreenOrientation(this.activityContext) != 0;
        UIUtils.logD("AudienceActivity", "处理付费弹窗(屏幕切换时）");
        this.vTop1.setVisibility(z ? 0 : 8);
        this.vTop2.setVisibility(z ? 0 : 8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissPayInfo() {
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
        this.ivPlayIcon.setVisibility(0);
    }

    private void initPayInfo() {
        View view = this.root;
        this.llPayInfo = (LinearLayout) view.findViewById(2131297753);
        this.payView = (FrameLayout) view.findViewById(2131296998);
        this.vTop1 = view.findViewById(2131299530);
        this.vTop2 = view.findViewById(2131299531);
        this.tvFreeTimeTips = (TextView) view.findViewById(2131298945);
        this.ivPlayIcon = (ImageView) view.findViewById(2131297450);
        this.llTrySeeDialog = (LinearLayout) view.findViewById(2131297789);
        this.tvPayTips = (TextView) view.findViewById(2131299043);
        this.tvPayTips.setText(this.tips);
        this.tvNoOrder = (TextView) view.findViewById(2131299028);
        this.tvOrder = (TextView) view.findViewById(2131299036);
        this.ivPlayIcon.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$x7j1GWjXoNjoz1QY5U_84FxtTZY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudiencePlayBackActivity.lambda$initPayInfo$13(AudiencePlayBackActivity.this, view2);
            }
        });
        this.tvNoOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$DPyIEhBJ1rKoo2sAN4v6fOvKfc0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudiencePlayBackActivity.this.dismissPayInfo();
            }
        });
        this.tvOrder.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$886EVG7ECJ_Mlmhrh4Eoqp7RqKk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudiencePlayBackActivity.lambda$initPayInfo$15(AudiencePlayBackActivity.this, view2);
            }
        });
        this.llPayTipe = (LinearLayout) view.findViewById(2131297754);
        this.tvBtnPay4PB = (TextView) view.findViewById(2131299041);
        this.llPayTipe.setVisibility(8);
        this.tvBtnPay4PB.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$mrbl9n3pjLpJiXY4mBBWG4CAZOE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                AudiencePlayBackActivity.lambda$initPayInfo$16(AudiencePlayBackActivity.this, view2);
            }
        });
        this.llPayInfo.setVisibility(8);
        this.llPayTipe.setVisibility(8);
        this.ivPlayIcon.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
    }

    public static /* synthetic */ void lambda$initPayInfo$13(AudiencePlayBackActivity audiencePlayBackActivity, View view) {
        if (!audiencePlayBackActivity.isPB) {
            audiencePlayBackActivity.showPayInfo();
        } else {
            audiencePlayBackActivity.pbReplay();
        }
    }

    public static /* synthetic */ void lambda$initPayInfo$15(AudiencePlayBackActivity audiencePlayBackActivity, View view) {
        Intent intent = new Intent(audiencePlayBackActivity.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(audiencePlayBackActivity.buyUrl);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        audiencePlayBackActivity.startActivityForResult(intent, 4111);
    }

    public static /* synthetic */ void lambda$initPayInfo$16(AudiencePlayBackActivity audiencePlayBackActivity, View view) {
        if (audiencePlayBackActivity.llTrySeeDialog.getVisibility() == 8) {
            Intent intent = new Intent(audiencePlayBackActivity.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(audiencePlayBackActivity.buyUrl);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            audiencePlayBackActivity.startActivityForResult(intent, 4111);
        }
    }

    private void consumeFreeTime(String str) {
        this.managerAudienceLoadData.consumeFreeTime(str).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$6uOBTPELBPLmb_Gc-Rkg2B0gOyU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("", ((String) obj) + "===========");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleSharpnessInfo(SharpnessEntity sharpnessEntity, String str, View view, String str2) {
        if ("0000".equals(sharpnessEntity.getStatusCode())) {
            clearPayInfo();
            if (!TextUtils.isEmpty(sharpnessEntity.getPromptText())) {
                UIUtils.toastCenter(sharpnessEntity.getPromptText());
            }
            this.payInfo4PB = sharpnessEntity.getPbPayInfo();
            PlayBackPayInfoEntity playBackPayInfoEntity = this.payInfo4PB;
            if (playBackPayInfoEntity != null) {
                handlePBPayInfo(playBackPayInfoEntity);
            } else {
                handlePayInfo(sharpnessEntity);
            }
            this.isMulti = "Y".equals(sharpnessEntity.getLiveViewAngleMore());
            AudienceMessageView audienceMessageView = this.messageView;
            if (audienceMessageView != null) {
                audienceMessageView.setMultiView(this.isMulti);
            }
            if (this.isMulti) {
                UIUtils.logD("多视角测试", "进入多视角");
                List<SharpnessEntity.LiveUrlBean> list = this.liveUrlList;
                if (list == null) {
                    this.liveUrlList = sharpnessEntity.getData();
                } else {
                    list.clear();
                    this.liveUrlList.addAll(sharpnessEntity.getLiveViewAngleMoreList().get(0).getList());
                }
                sharpnessEntity.getLiveViewAngleMoreList().get(0).setSelection(true);
                sharpnessEntity.getLiveViewAngleMoreList().get(0).setCover(str2);
                setAngleMore(sharpnessEntity.getLiveViewAngleMoreList());
                this.selectedPlayer.clear();
                this.selectedPlayer.add(this.players.get(0));
                if (UIUtils.isFoldScreen(this.activityContext) && getScreenOrientation(this.activityContext) == 0) {
                    toggleFullScreen(this.activityContext);
                }
            } else {
                List<SharpnessEntity.LiveUrlBean> list2 = this.liveUrlList;
                if (list2 == null) {
                    this.liveUrlList = sharpnessEntity.getData();
                } else {
                    list2.clear();
                    this.liveUrlList.addAll(sharpnessEntity.getData());
                }
            }
            setPlayDate(view);
        }
    }

    private void handlePayInfo(SharpnessEntity sharpnessEntity) {
        this.buyUrl = "";
        if (!"Y".equals(sharpnessEntity.getPaidLive()) || "Y".equals(sharpnessEntity.getPayingUser())) {
            return;
        }
        this.buyUrl = sharpnessEntity.getPaidLinks();
        this.tips = sharpnessEntity.getPaidAd();
        this.isPB = false;
        initPayInfo();
        this.llPayInfo.setVisibility(8);
        this.llPayTipe.setVisibility(8);
        this.ivPlayIcon.setVisibility(8);
        this.llTrySeeDialog.setVisibility(8);
        showPayViewTop();
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
        this.payInfoCountDown = Observable.interval(0L, 1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$a7iuVMaDKE36ec6JAUvoylY0OAQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$handlePayInfo$18(AudiencePlayBackActivity.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$handlePayInfo$18(AudiencePlayBackActivity audiencePlayBackActivity, Long l) throws Exception {
        if (audiencePlayBackActivity.isRunning) {
            audiencePlayBackActivity.timePayInfo--;
            if (audiencePlayBackActivity.timePayInfo % 10 == 0) {
                audiencePlayBackActivity.consumeFreeTime(audiencePlayBackActivity.jobNumber);
            }
            if (audiencePlayBackActivity.timePayInfo == 0) {
                audiencePlayBackActivity.showPayInfo();
                return;
            }
            audiencePlayBackActivity.tvFreeTimeTips.setText("可试看" + audiencePlayBackActivity.timePayInfo + "秒，订购权益包后可观看全部内容");
            return;
        }
        UIUtils.logD(TAG, "暂停倒计时" + audiencePlayBackActivity.timePayInfo);
    }

    private void handlePBPayInfo(final PlayBackPayInfoEntity playBackPayInfoEntity) {
        try {
            MsLogUtil.m7979d(TAG, "处理直播回放付费流程");
            if (!TextUtils.isEmpty(playBackPayInfoEntity.getPromptText())) {
                new ToastDialogManager(this).show(playBackPayInfoEntity.getPromptText());
            }
            this.buyUrl = "";
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
                    this.payInfoCountDown = Observable.interval(0L, 500L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.m1934io()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$_Z_dKencD4pnPzj-42nt7ZWw84c
                        @Override // io.reactivex.functions.Function
                        public final Object apply(Object obj) {
                            return AudiencePlayBackActivity.lambda$handlePBPayInfo$19(AudiencePlayBackActivity.this, playBackPayInfoEntity, (Long) obj);
                        }
                    }).observeOn(AndroidSchedulers.mainThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$1uJ0VcgjoLAAoXL-GoRBR_HNEyo
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudiencePlayBackActivity.lambda$handlePBPayInfo$20(AudiencePlayBackActivity.this, (Boolean) obj);
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

    public static /* synthetic */ Boolean lambda$handlePBPayInfo$19(AudiencePlayBackActivity audiencePlayBackActivity, PlayBackPayInfoEntity playBackPayInfoEntity, Long l) throws Exception {
        int currentPosition = audiencePlayBackActivity.bdCloudVideoView.getCurrentPosition();
        int freeTime4MillisSeconds = playBackPayInfoEntity.getFreeTime4MillisSeconds();
        boolean z = false;
        if (audiencePlayBackActivity.bdCloudVideoView != null && currentPosition >= freeTime4MillisSeconds) {
            z = true;
        }
        MsLogUtil.m7979d(TAG, "当前进度=" + currentPosition + ">=" + freeTime4MillisSeconds + "=" + z);
        return Boolean.valueOf(z);
    }

    public static /* synthetic */ void lambda$handlePBPayInfo$20(AudiencePlayBackActivity audiencePlayBackActivity, Boolean bool) throws Exception {
        if (bool.booleanValue()) {
            audiencePlayBackActivity.payInfoCountDown.dispose();
            audiencePlayBackActivity.showPBPayInfo();
        } else if (audiencePlayBackActivity.isNeedPay) {
            audiencePlayBackActivity.isNeedPay = false;
        }
    }

    private void portrait4PB() {
        this.tvBtnPay4PB.setVisibility(getScreenOrientation(this.activityContext) == 0 ? 8 : 0);
    }

    public void seekToPlay(int i) {
        MsLogUtil.m7979d(TAG, "seekToPlay=" + i);
        PlayBackPayInfoEntity playBackPayInfoEntity = this.payInfo4PB;
        if (playBackPayInfoEntity != null) {
            handlePBPayInfo(playBackPayInfoEntity);
            if (i < this.payInfo4PB.getFreeTime4MillisSeconds() && !this.isNeedPay) {
                this.bdCloudVideoView.start();
                AudienceMessageView audienceMessageView = this.messageView;
                if (audienceMessageView != null) {
                    audienceMessageView.setPlayBtn(true);
                }
                AudienceLandscapeView audienceLandscapeView = this.landscapeView;
                if (audienceLandscapeView != null) {
                    audienceLandscapeView.setPlayBtn(true);
                    return;
                }
                return;
            } else if (this.isNeedPay) {
                this.bdCloudVideoView.pause();
                AudienceMessageView audienceMessageView2 = this.messageView;
                if (audienceMessageView2 != null) {
                    audienceMessageView2.setPlayBtn(false);
                }
                AudienceLandscapeView audienceLandscapeView2 = this.landscapeView;
                if (audienceLandscapeView2 != null) {
                    audienceLandscapeView2.setPlayBtn(false);
                    return;
                }
                return;
            } else {
                return;
            }
        }
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.start();
        }
        AudienceMessageView audienceMessageView3 = this.messageView;
        if (audienceMessageView3 != null) {
            audienceMessageView3.setPlayBtn(true);
        }
        AudienceLandscapeView audienceLandscapeView3 = this.landscapeView;
        if (audienceLandscapeView3 != null) {
            audienceLandscapeView3.setPlayBtn(true);
        }
    }

    private void pbReplay() {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            bDCloudVideoView.seekTo(0);
            seekToPlay(0);
        }
    }

    private void initAngleMorePlayer(List<AngleMoreEntity> list) {
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
                bDCloudVideoView.setOnPlayerStateListener(new BDCloudVideoView.OnPlayerStateListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$UeEjyz09tJXLLODuRES-YSHLwtM
                    @Override // com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView.OnPlayerStateListener
                    public final void onPlayerStateChanged(BDCloudVideoView.PlayerState playerState) {
                        AudiencePlayBackActivity.lambda$initAngleMorePlayer$21(AudiencePlayBackActivity.this, bDCloudVideoView, playerState);
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
                bDCloudVideoView2.setVideoPath(liveUrlBean.getLiveFreePullUrl());
                bDCloudVideoView2.setTag(liveUrlBean.getLiveFreePullUrl());
                bDCloudVideoView2.start();
            }
        }
    }

    public static /* synthetic */ void lambda$initAngleMorePlayer$21(AudiencePlayBackActivity audiencePlayBackActivity, BDCloudVideoView bDCloudVideoView, BDCloudVideoView.PlayerState playerState) {
        UIUtils.logD("testbj", "nowState=" + playerState);
        if (playerState == BDCloudVideoView.PlayerState.STATE_PLAYING) {
            if (audiencePlayBackActivity.isNeedPay) {
                bDCloudVideoView.stopPlayback();
                bDCloudVideoView.setBackgroundColor(0);
                bDCloudVideoView.setVisibility(8);
                return;
            }
            UIUtils.logD("testbj", "播放:" + audiencePlayBackActivity.isNeedPay);
            bDCloudVideoView.setBackgroundColor(-16777216);
            bDCloudVideoView.setVisibility(0);
        } else if (playerState == BDCloudVideoView.PlayerState.STATE_IDLE) {
            bDCloudVideoView.setBackgroundColor(0);
            bDCloudVideoView.setVisibility(8);
        }
    }

    private BDCloudVideoView getPlayer(int i) {
        List<BDCloudVideoView> list = this.players;
        if (list != null) {
            return list.get(i);
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playerIntoList(int i, FrameLayout frameLayout) {
        try {
            frameLayout.removeAllViews();
            BDCloudVideoView player = getPlayer(i);
            ViewGroup viewGroup = (ViewGroup) player.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            frameLayout.addView(player);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    private void setAngleMore(List<AngleMoreEntity> list) {
        UIUtils.logD("AudienceActivity", "setAngleMore");
        initAngleMorePlayer(list);
        final LinearLayout linearLayout = (LinearLayout) this.root.findViewById(2131297749);
        RecyclerView recyclerView = (RecyclerView) this.root.findViewById(2131298406);
        final TextView textView = (TextView) this.root.findViewById(2131299019);
        linearLayout.setVisibility(0);
        textView.setVisibility(8);
        RxView.clicks((ImageView) this.root.findViewById(2131297444)).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$-cb26jeIsfMBicS8MvRrGujLMKQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.this.dismissAngle(linearLayout, textView);
            }
        });
        RxView.clicks((LinearLayout) this.root.findViewById(2131297748)).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$51e5J3rfHuwvezC69iOom6HOBno
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.this.dismissAngle(linearLayout, null);
            }
        });
        RxView.clicks(textView).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$q8DD4WjEK7E-qd40dHXV597rxNI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$setAngleMore$24(AudiencePlayBackActivity.this, linearLayout, textView, obj);
            }
        });
        this.multiLayoutManager = new LinearLayoutManager(this.activityContext, 0, false) { // from class: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity.5
            @Override // android.support.p086v7.widget.LinearLayoutManager, android.support.p086v7.widget.RecyclerView.LayoutManager
            public boolean canScrollHorizontally() {
                return !AudiencePlayBackActivity.this.isNeedPay;
            }
        };
        recyclerView.setLayoutManager(this.multiLayoutManager);
        BaseQuickAdapter<AngleMoreEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<AngleMoreEntity, BaseViewHolder>(2131493203) { // from class: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity.6
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
            public void convert(BaseViewHolder baseViewHolder, AngleMoreEntity angleMoreEntity) {
                int indexOf = AudiencePlayBackActivity.this.angleAdapter.getData().indexOf(angleMoreEntity);
                FrameLayout frameLayout = (FrameLayout) baseViewHolder.getView(2131296999);
                GlideApp.with((FragmentActivity) AudiencePlayBackActivity.this.activityContext).load(angleMoreEntity.getCover()).into((ImageView) baseViewHolder.getView(2131297366));
                baseViewHolder.setText(2131299108, angleMoreEntity.getName()).setGone(2131299496, angleMoreEntity.isSelection());
                if (angleMoreEntity.isSelection()) {
                    frameLayout.setVisibility(8);
                    frameLayout.removeAllViews();
                } else {
                    frameLayout.setVisibility(angleMoreEntity.isVis() ? 0 : 8);
                    AudiencePlayBackActivity.this.playerIntoList(indexOf, frameLayout);
                }
                baseViewHolder.addOnClickListener(2131298410);
            }
        };
        this.angleAdapter = baseQuickAdapter;
        recyclerView.setAdapter(baseQuickAdapter);
        recyclerView.addOnScrollListener(new C83877());
        this.angleAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$nH_VRtG2BWt65Lf4FPIaf6Qe9k0
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
            public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                AudiencePlayBackActivity.lambda$setAngleMore$26(AudiencePlayBackActivity.this, baseQuickAdapter2, view, i);
            }
        });
        this.angleAdapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$otaIiUr0TPe8LHIZmlvV7ySBATY
            @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
            public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                AudiencePlayBackActivity.lambda$setAngleMore$28(AudiencePlayBackActivity.this, baseQuickAdapter2, view, i);
            }
        });
        this.angleAdapter.setNewData(list);
    }

    public static /* synthetic */ void lambda$setAngleMore$24(AudiencePlayBackActivity audiencePlayBackActivity, LinearLayout linearLayout, TextView textView, Object obj) throws Exception {
        audiencePlayBackActivity.showAngle(linearLayout, false);
        textView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity$7 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83877 extends RecyclerView.OnScrollListener {
        C83877() {
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int i) {
            UIUtils.logD("多视角列表", "newState=" + i);
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrolled(@NonNull RecyclerView recyclerView, int i, int i2) {
            int findFirstCompletelyVisibleItemPosition = AudiencePlayBackActivity.this.multiLayoutManager.findFirstCompletelyVisibleItemPosition();
            int findLastCompletelyVisibleItemPosition = AudiencePlayBackActivity.this.multiLayoutManager.findLastCompletelyVisibleItemPosition();
            int i3 = 0;
            while (i3 < AudiencePlayBackActivity.this.angleAdapter.getData().size()) {
                ((AngleMoreEntity) AudiencePlayBackActivity.this.angleAdapter.getData().get(i3)).setVis(i3 >= findFirstCompletelyVisibleItemPosition && i3 <= findLastCompletelyVisibleItemPosition);
                i3++;
            }
            recyclerView.post(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$7$RvadtKTGIBBY1Z8X07jL7lNs2eY
                @Override // java.lang.Runnable
                public final void run() {
                    AudiencePlayBackActivity.C83877.lambda$onScrolled$0(AudiencePlayBackActivity.C83877.this);
                }
            });
        }

        public static /* synthetic */ void lambda$onScrolled$0(C83877 c83877) {
            UIUtils.logD("多视角列表", "多视角列表post=");
            AudiencePlayBackActivity.this.angleAdapter.notifyDataSetChanged();
        }
    }

    public static /* synthetic */ void lambda$setAngleMore$26(final AudiencePlayBackActivity audiencePlayBackActivity, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        if (audiencePlayBackActivity.isNeedPay) {
            return;
        }
        audiencePlayBackActivity.resetPlayerVoice();
        Disposable disposable = audiencePlayBackActivity.changeAngleView;
        if (disposable != null) {
            disposable.dispose();
            audiencePlayBackActivity.changeAngleView = null;
        }
        audiencePlayBackActivity.changeAngleView = Observable.timer(600L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$QHktiMldqW3mnmi6Tn5w8tV3NGQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$setAngleMore$25(AudiencePlayBackActivity.this, i, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setAngleMore$25(AudiencePlayBackActivity audiencePlayBackActivity, int i, Long l) throws Exception {
        UIUtils.logD("多视角列表", "点击多视角列表执行操作");
        AngleMoreEntity angleMoreEntity = audiencePlayBackActivity.angleAdapter.getData().get(i);
        int i2 = 0;
        for (int i3 = 0; i3 < audiencePlayBackActivity.angleAdapter.getData().size(); i3++) {
            if (audiencePlayBackActivity.angleAdapter.getData().get(i3).isSelection()) {
                i2++;
            }
        }
        if (i2 == 1) {
            int i4 = 0;
            while (true) {
                if (i4 >= audiencePlayBackActivity.angleAdapter.getData().size()) {
                    i4 = 0;
                    break;
                } else if (!audiencePlayBackActivity.angleAdapter.getData().get(i4).isSelection()) {
                    i4++;
                } else if (i4 != i) {
                    audiencePlayBackActivity.angleAdapter.getItem(i4).setSelection(false);
                    audiencePlayBackActivity.angleAdapter.getItem(i).setSelection(true);
                }
            }
            if (i4 != i) {
                audiencePlayBackActivity.angleAdapter.notifyItemChanged(i4);
                audiencePlayBackActivity.angleAdapter.notifyItemChanged(i);
                audiencePlayBackActivity.bdCloudVideoView.setVolume(1.0f, 1.0f);
                audiencePlayBackActivity.liveUrlList.clear();
                audiencePlayBackActivity.liveUrlList.addAll(audiencePlayBackActivity.angleAdapter.getData().get(i).getList());
                ((FrameLayout) audiencePlayBackActivity.root.findViewById(2131296396)).setVisibility(0);
                ((FrameLayout) audiencePlayBackActivity.root.findViewById(2131296395)).removeAllViews();
                audiencePlayBackActivity.setPlayDate(audiencePlayBackActivity.root);
                audiencePlayBackActivity.selectedPlayer.clear();
                audiencePlayBackActivity.selectedPlayer.add(audiencePlayBackActivity.players.get(i));
                GlideApp.with((FragmentActivity) audiencePlayBackActivity).load(angleMoreEntity.getCover()).into((ImageView) audiencePlayBackActivity.root.findViewById(2131296393));
            } else {
                UIUtils.logD("多视角列表点击", "已选中不进行切换");
            }
        } else {
            angleMoreEntity.setSelection(true ^ angleMoreEntity.isSelection());
            audiencePlayBackActivity.angleAdapter.notifyItemChanged(i);
            if (angleMoreEntity.isSelection()) {
                audiencePlayBackActivity.selectedPlayer.add(audiencePlayBackActivity.players.get(i));
            } else {
                audiencePlayBackActivity.selectedPlayer.remove(audiencePlayBackActivity.players.get(i));
            }
            audiencePlayBackActivity.showMorePlayer(audiencePlayBackActivity.root, false);
        }
        Disposable disposable = audiencePlayBackActivity.changeAngleView;
        if (disposable != null) {
            disposable.dispose();
            audiencePlayBackActivity.changeAngleView = null;
        }
    }

    public static /* synthetic */ void lambda$setAngleMore$28(final AudiencePlayBackActivity audiencePlayBackActivity, BaseQuickAdapter baseQuickAdapter, View view, final int i) {
        if (audiencePlayBackActivity.isNeedPay) {
            return;
        }
        Disposable disposable = audiencePlayBackActivity.addView2List;
        if (disposable != null) {
            disposable.dispose();
            audiencePlayBackActivity.addView2List = null;
        }
        audiencePlayBackActivity.addView2List = Observable.timer(600L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$hu3y47ACBzM5_LbisQbEEa-mrJk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$setAngleMore$27(AudiencePlayBackActivity.this, i, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setAngleMore$27(AudiencePlayBackActivity audiencePlayBackActivity, int i, Long l) throws Exception {
        AngleMoreEntity angleMoreEntity = audiencePlayBackActivity.angleAdapter.getData().get(i);
        int i2 = 0;
        for (int i3 = 0; i3 < audiencePlayBackActivity.angleAdapter.getData().size(); i3++) {
            if (audiencePlayBackActivity.angleAdapter.getData().get(i3).isSelection()) {
                i2++;
            }
        }
        if (i2 == 1 && angleMoreEntity.isSelection()) {
            UIUtils.logD("多视角列表", "目前只选中了一个视角，无法取消");
        } else {
            angleMoreEntity.setSelection(true ^ angleMoreEntity.isSelection());
            audiencePlayBackActivity.angleAdapter.notifyItemChanged(i);
            if (angleMoreEntity.isSelection()) {
                audiencePlayBackActivity.selectedPlayer.add(audiencePlayBackActivity.players.get(i));
            } else {
                audiencePlayBackActivity.selectedPlayer.remove(audiencePlayBackActivity.players.get(i));
            }
            audiencePlayBackActivity.showMorePlayer(audiencePlayBackActivity.root, false);
        }
        Disposable disposable = audiencePlayBackActivity.addView2List;
        if (disposable != null) {
            disposable.dispose();
            audiencePlayBackActivity.addView2List = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playUrl(SharpnessEntity.LiveUrlBean liveUrlBean) {
        this.currentLiveUrl = liveUrlBean;
        this.bdCloudVideoView.stopPlayback();
        this.bdCloudVideoView.reSetRender();
        this.bdCloudVideoView.setVideoPath(liveUrlBean.getLiveFreePullUrl());
        this.bdCloudVideoView.setTag(liveUrlBean.getLiveFreePullUrl());
        if (this.bdCloudVideoView.getCurrentPlayingUrl().contains("m3u8")) {
            UIUtils.logD(TAG, "m3u8包含连接");
            this.bdCloudVideoView.setVolume(1.0f, 1.0f);
        }
        if (this.isNeedPay) {
            return;
        }
        this.bdCloudVideoView.start();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void IntentGoWithOutVideo(String str) {
        AudienceGuanzhuDialog.cancelGuanzhusub();
        Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
        WebParamsEntity webParamsEntity = new WebParamsEntity();
        webParamsEntity.setUrl(str);
        webParamsEntity.setNeedTitle(true);
        webParamsEntity.setRequestType("get");
        intent.putExtra(WebFragment.webParams, webParamsEntity);
        this.activityContext.startActivityForResult(intent, 4000);
    }

    private void getShareCode(final ZhuboDataEntity.AnchorInfoBean anchorInfoBean) {
        this.managerAudienceLoadData.getShareCode().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$-euPylwy3YjRLwYAvsMH5e36FWM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$getShareCode$29(AudiencePlayBackActivity.this, anchorInfoBean, (BaseVideoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$getShareCode$29(AudiencePlayBackActivity audiencePlayBackActivity, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, BaseVideoEntity baseVideoEntity) throws Exception {
        String str;
        audiencePlayBackActivity.shareCode = (String) baseVideoEntity.getData();
        if ("3".equals(anchorInfoBean.getDataType())) {
            str = "#/huifangplayer?videoId=" + audiencePlayBackActivity.videoId + "&";
        } else {
            str = "#/liveplayer?";
        }
        audiencePlayBackActivity.messageView.setShareData(anchorInfoBean, audiencePlayBackActivity.managerAudienceLoadData, audiencePlayBackActivity.shareCode, str);
        audiencePlayBackActivity.landscapeView.setShareData(anchorInfoBean, audiencePlayBackActivity.managerAudienceLoadData, audiencePlayBackActivity.shareCode, str);
    }

    private void clearPayInfo() {
        this.isNeedPay = false;
        MsLogUtil.m7979d(TAG, "clearPayInfo==>" + this.isNeedPay);
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
        List<BDCloudVideoView> list;
        clearPayInfo();
        this.isNeedPay = true;
        this.llPayInfo.setVisibility(0);
        this.llPayTipe.setVisibility(8);
        this.llTrySeeDialog.setVisibility(0);
        this.bdCloudVideoView.stopPlayback();
        if (!this.isMulti || (list = this.players) == null) {
            return;
        }
        for (BDCloudVideoView bDCloudVideoView : list) {
            if (bDCloudVideoView != null) {
                bDCloudVideoView.stopPlayback();
            }
        }
    }

    private void showPBPayInfo() {
        try {
            clearPayInfo();
            this.llPayInfo.setVisibility(0);
            this.llPayTipe.setVisibility(0);
            portrait4PB();
            this.llTrySeeDialog.setVisibility(0);
            this.isNeedPay = true;
            if (this.bdCloudVideoView != null) {
                this.bdCloudVideoView.pause();
            }
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        this.managerAudienceLoadData.isGuanzhu(this.jobNumber).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$H7IjeACAHxtrD-54vZFb6cpqazo
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudiencePlayBackActivity.lambda$onActivityResult$30(AudiencePlayBackActivity.this, (Boolean) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        if (i == 4111) {
            getRoomInfo();
        }
    }

    public static /* synthetic */ void lambda$onActivityResult$30(AudiencePlayBackActivity audiencePlayBackActivity, Boolean bool) throws Exception {
        if (audiencePlayBackActivity.isMulti) {
            audiencePlayBackActivity.messageView.setGuanzhuView(true, null);
            audiencePlayBackActivity.landscapeView.setGuanzuGone(true);
            return;
        }
        audiencePlayBackActivity.messageView.setGuanzhuView(bool.booleanValue(), audiencePlayBackActivity.zhuboDataEntity);
        audiencePlayBackActivity.landscapeView.setGuanzuGone(bool.booleanValue());
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

    @SuppressLint({"SourceLockedOrientationActivity"})
    public void toggleFullScreen(Context context) {
        try {
            View view = this.root;
            FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296395);
            FrameLayout frameLayout2 = (FrameLayout) view.findViewById(2131296994);
            if (getScreenOrientation(context) == 0) {
                setRequestedOrientation(1);
                this.isHorizontal = false;
                resetLiveCover();
                this.messageView.setVisibility(0);
                this.toggleBack.setVisibility(8);
                frameLayout = (FrameLayout) view.findViewById(2131296396);
                this.bdCloudVideoView.setVideoScalingMode(1);
                AudienceGuanzhuDialog.isLandscape = false;
                ShareManager.isLandscape = false;
                if (this.isMulti) {
                    UIUtils.logD("切换屏幕", "设置为竖屏，处理多视角");
                    View findViewById = view.findViewById(2131297749);
                    view.findViewById(2131297748).setVisibility(8);
                    view.findViewById(2131297444).setVisibility(0);
                    view.findViewById(2131299019).setVisibility(findViewById.getVisibility() == 8 ? 0 : 8);
                    FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) findViewById.getLayoutParams();
                    layoutParams.width = -1;
                    layoutParams.gravity = 48;
                    FrameLayout frameLayout3 = (FrameLayout) view.findViewById(2131296997);
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
                frameLayout2.removeAllViews();
                this.messageView.updateProgress();
            } else {
                frameLayout2.removeAllViews();
                int i = getResources().getDisplayMetrics().widthPixels;
                setRequestedOrientation(0);
                this.isHorizontal = true;
                setLiveCoverLandscape();
                this.messageView.setVisibility(8);
                this.toggleBack.setVisibility(8);
                this.bdCloudVideoView.setVideoScalingMode(1);
                if (this.liveRoomUIInfo != null) {
                    this.landscapeView.hideChatRoomAndGiftByNet(this.liveRoomUIInfo);
                }
                this.landscapeView.initData(this.zhuboDataEntity, this.audienceUser, this.managerAudienceLoadData, !"Wifi".equals(DeviceHelper.getNETType(this.activityContext)), new C83888(view));
                ViewGroup viewGroup3 = (ViewGroup) this.landscapeView.getParent();
                if (viewGroup3 != null) {
                    viewGroup3.removeAllViews();
                }
                frameLayout2.removeAllViews();
                frameLayout2.addView(this.landscapeView);
                this.landscapeView.setClickListener(this.clickVideo);
                AudienceGuanzhuDialog.isLandscape = true;
                ShareManager.isLandscape = true;
                if (this.isMulti) {
                    UIUtils.logD("切换屏幕", "设置为横屏，处理多视角");
                    this.landscapeView.setMulti();
                    View findViewById2 = view.findViewById(2131297749);
                    view.findViewById(2131297748).setVisibility(0);
                    view.findViewById(2131297444).setVisibility(8);
                    view.findViewById(2131299019).setVisibility(8);
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
            }
            UIUtils.logD("切换屏幕", this.bdCloudVideoView.getVideoHeight() + "----w:" + this.bdCloudVideoView.getVideoWidth());
            ViewGroup viewGroup6 = (ViewGroup) this.bdCloudVideoView.getParent();
            if (viewGroup6 != null) {
                viewGroup6.removeAllViews();
            }
            if (this.isMulti) {
                if (getMultiSelectedCount() == 1) {
                    frameLayout.addView(this.bdCloudVideoView);
                    return;
                }
                UIUtils.logD("切换屏幕", "多视角播放加入布局，切换横竖屏");
                showMorePlayer(view, true);
                return;
            }
            frameLayout.addView(this.bdCloudVideoView);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity$8 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C83888 implements AudienceLandscapeView.LandscapeCallBack {
        final /* synthetic */ View val$view;

        C83888(View view) {
            this.val$view = view;
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public boolean isNeedPay() {
            return AudiencePlayBackActivity.this.activityContext.isNeedPay();
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void close() {
            AudiencePlayBackActivity audiencePlayBackActivity = AudiencePlayBackActivity.this;
            audiencePlayBackActivity.toggleFullScreen(audiencePlayBackActivity.activityContext);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void showKeyboard() {
            AudiencePlayBackActivity.this.alivcInputTextDialog.setmOnTextSendListener(new InputDialog.OnTextSendListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$8$CWjZFRBHHik6_08bSLEc9vDirEw
                @Override // com.sinovatech.unicom.separatemodule.audience.util.InputDialog.OnTextSendListener
                public final void onTextSend(String str) {
                    AudiencePlayBackActivity.C83888.lambda$showKeyboard$0(AudiencePlayBackActivity.C83888.this, str);
                }
            });
            AudiencePlayBackActivity.this.alivcInputTextDialog.show();
        }

        public static /* synthetic */ void lambda$showKeyboard$0(C83888 c83888, String str) {
            if (AudiencePlayBackActivity.this.audienceUser.isLogin()) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setMsg(str.replace("|*|", ""));
                if (str.contains("@") && str.contains("|*|")) {
                    liveMsg.setType("atto");
                    liveMsg.setAtto("all");
                } else {
                    liveMsg.setType("chat");
                }
                liveMsg.setLevel(AudiencePlayBackActivity.this.audienceUser.getLevel());
                liveMsg.setNick(AudiencePlayBackActivity.this.audienceUser.getNick());
                liveMsg.setMgr(AudiencePlayBackActivity.this.audienceUser.isMgr());
                AudiencePlayBackActivity.this.messageView.updateMessageAdapter(liveMsg);
                AudiencePlayBackActivity.this.landscapeView.updateMessageAdapter(liveMsg);
            }
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
            AudiencePlayBackActivity.this.bdCloudVideoView.stopPlayback();
            AudiencePlayBackActivity.this.playUrl(liveUrlBean);
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void guanzhuStatus() {
            AudiencePlayBackActivity.this.managerAudienceLoadData.isGuanzhu(AudiencePlayBackActivity.this.jobNumber).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.-$$Lambda$AudiencePlayBackActivity$8$If6x_-j4hG0ZdY40WCq8uUQtIVU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudiencePlayBackActivity.C83888.lambda$guanzhuStatus$1(AudiencePlayBackActivity.C83888.this, (Boolean) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        }

        public static /* synthetic */ void lambda$guanzhuStatus$1(C83888 c83888, Boolean bool) throws Exception {
            if (AudiencePlayBackActivity.this.isMulti) {
                AudiencePlayBackActivity.this.messageView.setGuanzhuView(true, null);
                AudiencePlayBackActivity.this.landscapeView.setGuanzuGone(true);
                return;
            }
            AudiencePlayBackActivity.this.messageView.setGuanzhuView(bool.booleanValue(), AudiencePlayBackActivity.this.zhuboDataEntity);
            AudiencePlayBackActivity.this.landscapeView.setGuanzuGone(bool.booleanValue());
        }

        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.LandscapeCallBack
        public void showMulti() {
            AudiencePlayBackActivity.this.showAngle(this.val$view.findViewById(2131297749), true);
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
        View inflate;
        try {
            this.bdCloudVideoView.setVolume(0.0f, 0.0f);
            resetPlayerVoice();
            View view2 = view == null ? this.root : view;
            FrameLayout frameLayout = (FrameLayout) view2.findViewById(2131296396);
            frameLayout.removeAllViews();
            FrameLayout frameLayout2 = (FrameLayout) view2.findViewById(2131296395);
            frameLayout2.removeAllViews();
            if (z) {
                if (getScreenOrientation(this.activityContext) == 0) {
                    inflate = LayoutInflater.from(this.activityContext).inflate(2131493230, (ViewGroup) null);
                } else {
                    inflate = LayoutInflater.from(this.activityContext).inflate(2131493231, (ViewGroup) null);
                }
            } else if (getScreenOrientation(this.activityContext) == 0) {
                inflate = LayoutInflater.from(this.activityContext).inflate(2131493231, (ViewGroup) null);
            } else {
                inflate = LayoutInflater.from(this.activityContext).inflate(2131493230, (ViewGroup) null);
            }
            FrameLayout frameLayout3 = (FrameLayout) inflate.findViewById(2131297000);
            FrameLayout frameLayout4 = (FrameLayout) inflate.findViewById(2131297001);
            FrameLayout frameLayout5 = (FrameLayout) inflate.findViewById(2131297002);
            FrameLayout frameLayout6 = (FrameLayout) inflate.findViewById(2131297003);
            FrameLayout frameLayout7 = (FrameLayout) inflate.findViewById(2131297004);
            FrameLayout frameLayout8 = (FrameLayout) inflate.findViewById(2131297005);
            View findViewById = inflate.findViewById(2131299492);
            View findViewById2 = inflate.findViewById(2131297758);
            View findViewById3 = inflate.findViewById(2131299491);
            frameLayout3.setVisibility(8);
            frameLayout4.setVisibility(8);
            frameLayout5.setVisibility(8);
            frameLayout6.setVisibility(8);
            frameLayout7.setVisibility(8);
            frameLayout8.setVisibility(8);
            findViewById.setVisibility(8);
            findViewById2.setVisibility(8);
            if (findViewById3 != null) {
                findViewById3.setVisibility(UIUtils.isFoldScreen(this.activityContext) ? 8 : 0);
            }
            int multiSelectedCount = getMultiSelectedCount();
            ArrayList arrayList = new ArrayList();
            View view3 = view2;
            switch (multiSelectedCount) {
                case 2:
                    arrayList.add(frameLayout3);
                    arrayList.add(frameLayout4);
                    break;
                case 3:
                    findViewById.setVisibility(0);
                    findViewById2.setVisibility(0);
                    arrayList.add(frameLayout7);
                    arrayList.add(frameLayout8);
                    arrayList.add(frameLayout5);
                    break;
                case 4:
                    findViewById2.setVisibility(0);
                    arrayList.add(frameLayout7);
                    arrayList.add(frameLayout8);
                    arrayList.add(frameLayout5);
                    arrayList.add(frameLayout6);
                    break;
                default:
                    showLiveCover();
                    for (AngleMoreEntity angleMoreEntity : this.angleAdapter.getData()) {
                        if (angleMoreEntity.isSelection()) {
                            this.bdCloudVideoView.setVolume(1.0f, 1.0f);
                            frameLayout.setVisibility(0);
                            frameLayout2.removeAllViews();
                            GlideApp.with((FragmentActivity) this).load(angleMoreEntity.getCover()).into((ImageView) view3.findViewById(2131296393));
                            setPlayDate(view3);
                            return;
                        }
                    }
                    return;
            }
            for (int i = 0; i < this.selectedPlayer.size(); i++) {
                BDCloudVideoView bDCloudVideoView = this.selectedPlayer.get(i);
                if (i < arrayList.size()) {
                    FrameLayout frameLayout9 = (FrameLayout) arrayList.get(i);
                    frameLayout9.removeAllViews();
                    ViewGroup viewGroup = (ViewGroup) bDCloudVideoView.getParent();
                    if (viewGroup != null) {
                        viewGroup.removeAllViews();
                    }
                    frameLayout9.setVisibility(0);
                    ImageView imageView = new ImageView(this);
                    imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                    FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -1);
                    GlideApp.with((FragmentActivity) this).load(bDCloudVideoView.getImgUrl()).into(imageView);
                    frameLayout9.addView(imageView, layoutParams);
                    frameLayout9.addView(bDCloudVideoView);
                }
                if (i == 0) {
                    bDCloudVideoView.setVolume(1.0f, 1.0f);
                }
            }
            ViewGroup viewGroup2 = (ViewGroup) inflate.getParent();
            if (viewGroup2 != null) {
                viewGroup2.removeAllViews();
            }
            hideLiveCover();
            frameLayout2.addView(inflate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setPlayDate(View view) {
        if (view == null) {
            try {
                view = this.root;
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

    private void setPlayerUrl(SharpnessEntity.LiveUrlBean liveUrlBean, View view) {
        UIUtils.logD(TAG, "setPlayerUrl");
        addVideoView(view);
        playUrl(liveUrlBean);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showAngle(final View view, boolean z) {
        view.setVisibility(0);
        view.clearAnimation();
        Animation loadAnimation = AnimationUtils.loadAnimation(this.activityContext, z ? 2130772010 : 2130772100);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity.9
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
                view.setVisibility(0);
            }
        });
        view.startAnimation(loadAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismissAngle(final View view, final View view2) {
        view.clearAnimation();
        Animation loadAnimation = AnimationUtils.loadAnimation(this.activityContext, view2 == null ? 2130771990 : 2130772101);
        loadAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity.10
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                view.setVisibility(8);
                View view3 = view2;
                if (view3 != null) {
                    view3.setVisibility(0);
                }
            }
        });
        view.startAnimation(loadAnimation);
    }

    private void setLiveCoverLandscape() {
        LinearLayout linearLayout;
        ImageView imageView;
        View view = this.root;
        if (view == null || (linearLayout = (LinearLayout) view.findViewById(2131297734)) == null || (imageView = (ImageView) view.findViewById(2131296393)) == null) {
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
    }

    private int getMultiSelectedCount() {
        if (this.angleAdapter != null) {
            int i = 0;
            for (int i2 = 0; i2 < this.angleAdapter.getData().size(); i2++) {
                if (this.angleAdapter.getItem(i2).isSelection()) {
                    i++;
                }
            }
            return i;
        }
        return 0;
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
        UIUtils.logD(TAG, "addVideoView:" + this.bdCloudVideoView.getVideoHeight() + "--0--w:" + this.bdCloudVideoView.getVideoWidth());
        FrameLayout frameLayout = (FrameLayout) view.findViewById(2131296395);
        if (this.bdCloudVideoView.getVideoHeight() < this.bdCloudVideoView.getVideoWidth()) {
            if (getScreenOrientation(this.activityContext) != 0) {
                this.bdCloudVideoView.setVideoScalingMode(1);
                frameLayout = (FrameLayout) view.findViewById(2131296396);
                AudienceMessageView audienceMessageView = this.messageView;
                if (audienceMessageView != null) {
                    audienceMessageView.showToggleView(true, null);
                }
            } else {
                this.bdCloudVideoView.setVideoScalingMode(1);
            }
        } else {
            this.bdCloudVideoView.setVideoScalingMode(2);
            AudienceMessageView audienceMessageView2 = this.messageView;
            if (audienceMessageView2 != null) {
                audienceMessageView2.showToggleView(false, null);
            }
        }
        ViewGroup viewGroup = (ViewGroup) this.bdCloudVideoView.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        frameLayout.addView(this.bdCloudVideoView);
        if (this.bdCloudVideoView.getVisibility() != 0) {
            this.bdCloudVideoView.setVisibility(0);
        }
    }

    private void resetLiveCover() {
        FrameLayout frameLayout;
        ImageView imageView;
        View view = this.root;
        if (view == null || (frameLayout = (FrameLayout) view.findViewById(2131296995)) == null || (imageView = (ImageView) view.findViewById(2131296393)) == null) {
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
    }
}
