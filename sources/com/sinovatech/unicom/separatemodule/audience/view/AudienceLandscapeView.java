package com.sinovatech.unicom.separatemodule.audience.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.PagerAdapter;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.CardView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.bytedance.applog.tracker.Tracker;
import com.jakewharton.rxbinding2.view.RxView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.hub.utils.MsLogUtil;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceUser;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveMsg;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveRoomUiHideEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.interfaceImpl.VideoProgressVisible;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import com.sinovatech.unicom.separatemodule.audience.util.ChatTextUtil;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.util.RoomDescBgSpan;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceSwitchDialog;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.LandscapeGiftMsgAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.MsgAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.TopMsgAnimView;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Danmaku;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.DanmakuManager;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import group.pals.android.lib.p392ui.lockpattern.util.ShakeUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceLandscapeView extends FrameLayout implements View.OnClickListener, View.OnKeyListener, VideoProgressVisible {
    public static final String VOICE_LIGHT_CONTROL_COVER = "voice_light_control_cover";
    private AppCompatActivity activityContext;
    private AudienceUser audienceUser;
    private Disposable autoGoneLock;
    private Disposable autoGoneView;
    private ImageView bottomImage1;
    private ImageView bottomImage2;
    private FrameLayout bubbleLayout;
    private AlivcLikeView bubbleView;
    private CircularImage civAnchorImg;
    private View.OnClickListener clickListener;
    private int count;
    private ZhuboDataEntity.AnchorInfoBean currentAnchor;
    private int danmuType;
    private LiveGiftView dianZanAnim;
    private Animation dianzanAnimation;
    private int dianzanCount;
    private DanmakuManager dmManager;
    private LikeView.TouchCallBack doubleClickListener;
    private Disposable ds1;
    private Disposable explainTimer;
    private TextView explanNotice;
    private FrameLayout flComment;
    private CardView goodsCardView;
    private TextView goodsContent;
    private ImageView goodsImage;
    private TextView goodsOriPrice;
    private TextView goodsPrice;
    private TextView goodsSize;
    private TextView goodsTilte;
    private MyGestureView gvControl;
    private boolean hasUpdateFans;
    private boolean isHaveGood;
    private boolean isMultiView;
    private boolean isRecycle;
    private boolean isShowView;
    private boolean isStartTrackingTouch;
    private ImageView ivBarrageBtn;
    private ImageView ivGiftBtn;
    private ImageView ivLock;
    private ImageView ivMulti;
    private ImageView ivPlayBtn;
    private ImageView ivShareBtn;
    private ImageView ivZan;
    private MsgAnimView joinAnimView;
    private LandscapeGiftMsgAnimView lGiftMsgAnimView;
    private LandscapeCallBack landscapeCallBack;
    private View layouGift;
    private View layoutMain;
    private LikeView likeView;
    private List<GiftEntity> list;
    private String liveTitle;
    private String liveType;
    private LiveRoomUiHideEntity liveUI;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private LinearLayout llAnimArea;
    private LinearLayout llCanGoneArea;
    private View llFreeTips;
    private LinearLayout llGiftDialog;
    private View llProgressBar;
    private LinearLayout ll_hidebottom;
    private ManagerAudienceLoadData managerLoad;
    private int myJifenNum;
    private PopupWindow popupWindow;
    private RelativeLayout rlHide;
    private View rlMain;
    private RelativeLayout rlShoppingBag;
    private RelativeLayout rlShow;
    private View rlZan;
    private SeekBar seekBar;
    private long showTime;
    private Disposable subscribe;
    private Disposable subscribeGift;
    private TextView textView1;
    private TextView textView2;
    private TextView textView3;
    private ImageView tips;
    private TopMsgAnimView topMsgAnimView;
    private TextView tvAchorCity;
    private TextView tvAnchorName;
    private TextView tvAttentionAnchorBtn;
    private TextView tvBegin;
    private TextView tvCommentBtn;
    private TextView tvDefinitionBtn;
    private TextView tvEnd;
    private TextView tvFansNum;
    private TextView tvGetGiftNum;
    private TextView tvGoodsNum;
    private ImageView tvHide;
    private TextView tvLiveTitle;
    private TextView tvPayInfoBtn4PB;
    private TextView tvPopularity;
    private Disposable updateSeek;
    private String videoId;
    private View view;
    private ViewPager viewPager;
    private AdapterViewpager vpAdapter;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface LandscapeCallBack {
        void close();

        void guanzhuStatus();

        boolean isNeedPay();

        void showKeyboard();

        void showMulti();

        void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean);
    }

    private void initFitment() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clickZan$11(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clickZan$12(Boolean bool) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setShareData$20(String str) throws Exception {
    }

    static /* synthetic */ int access$1308(AudienceLandscapeView audienceLandscapeView) {
        int i = audienceLandscapeView.count;
        audienceLandscapeView.count = i + 1;
        return i;
    }

    public void setLandscapeCallBack(LandscapeCallBack landscapeCallBack) {
        this.landscapeCallBack = landscapeCallBack;
    }

    public void setLiveTitle(String str) {
        try {
            this.liveTitle = str;
            if (this.tvLiveTitle == null || TextUtils.isEmpty(str)) {
                return;
            }
            SpannableString spannableString = new SpannableString("主题" + str);
            spannableString.setSpan(new RoomDescBgSpan(Color.parseColor("#4DFFFFFF"), Color.parseColor("#FFFFFFFF")), 0, 2, 33);
            this.tvLiveTitle.setText(spannableString);
            this.tvLiveTitle.setVisibility(this.ll_hidebottom.getVisibility());
        } catch (Exception e) {
            MsLogUtil.m7999d("AudienceLandscapeView", e.getMessage());
        }
    }

    @Override // android.view.View.OnKeyListener
    public boolean onKey(View view, int i, KeyEvent keyEvent) {
        try {
            if (keyEvent.getAction() == 0 && i == 4) {
                this.view.setFocusableInTouchMode(false);
                this.view.clearFocus();
                if (this.landscapeCallBack != null) {
                    this.landscapeCallBack.close();
                    cleanAnimation();
                }
                showChatRoomAndGiftUI();
                return true;
            }
        } catch (Exception e) {
            MsLogUtil.m7999d("AudienceLandscapeView", e.getMessage());
        }
        return false;
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.interfaceImpl.VideoProgressVisible
    public void setVideoProgressGone() {
        View view = this.llProgressBar;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public AudienceLandscapeView(Context context) {
        super(context);
        this.liveType = "1";
        this.activityContext = (AppCompatActivity) context;
        initView();
    }

    public AudienceLandscapeView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.liveType = "1";
        this.activityContext = (AppCompatActivity) context;
        initView();
    }

    private void landscape4PB() {
        try {
            if (this.activityContext instanceof AudiencePlayBackActivity) {
                final AudiencePlayBackActivity audiencePlayBackActivity = (AudiencePlayBackActivity) this.activityContext;
                TextView textView = this.tvPayInfoBtn4PB;
                if (TextUtils.isEmpty(audiencePlayBackActivity.buyUrl)) {
                    r1 = 8;
                }
                textView.setVisibility(r1);
                this.tvPayInfoBtn4PB.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$b_1n1MBugI5tHLsoMFLg5S41jPk
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceLandscapeView.lambda$landscape4PB$0(AudienceLandscapeView.this, audiencePlayBackActivity, view);
                    }
                });
            } else if (this.activityContext instanceof AudienceActivity) {
                final AudienceActivity audienceActivity = (AudienceActivity) this.activityContext;
                this.tvPayInfoBtn4PB.setVisibility(TextUtils.isEmpty(audienceActivity.buyUrl) ? 8 : 0);
                this.tvPayInfoBtn4PB.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$MsImbdegW6ztt8OrpDkzu2udZpU
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceLandscapeView.lambda$landscape4PB$1(AudienceLandscapeView.this, audienceActivity, view);
                    }
                });
            } else {
                this.tvPayInfoBtn4PB.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$landscape4PB$0(AudienceLandscapeView audienceLandscapeView, AudiencePlayBackActivity audiencePlayBackActivity, View view) {
        try {
            Intent intent = new Intent(audienceLandscapeView.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(audiencePlayBackActivity.buyUrl);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            audiencePlayBackActivity.startActivityForResult(intent, 4001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$landscape4PB$1(AudienceLandscapeView audienceLandscapeView, AudienceActivity audienceActivity, View view) {
        try {
            Intent intent = new Intent(audienceLandscapeView.activityContext, WebDetailActivity.class);
            WebParamsEntity webParamsEntity = new WebParamsEntity();
            webParamsEntity.setUrl(audienceActivity.buyUrl);
            webParamsEntity.setNeedTitle(true);
            webParamsEntity.setRequestType("get");
            intent.putExtra(WebFragment.webParams, webParamsEntity);
            audienceActivity.startActivityForResult(intent, 4001);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isNotPlayBack() {
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (this.activityContext instanceof AudiencePlayBackActivity) {
            return true ^ ((AudiencePlayBackActivity) this.activityContext).isPB;
        }
        if (this.activityContext instanceof AudienceActivity) {
            return true ^ ((AudienceActivity) this.activityContext).isPB;
        }
        return true;
    }

    public void initData(ZhuboDataEntity zhuboDataEntity, AudienceUser audienceUser, ManagerAudienceLoadData managerAudienceLoadData, boolean z, LandscapeCallBack landscapeCallBack) {
        try {
            this.clickListener = null;
            this.audienceUser = audienceUser;
            this.managerLoad = managerAudienceLoadData;
            this.currentAnchor = zhuboDataEntity.getAnchorInfoBean();
            setAnchorInfo(zhuboDataEntity.getAnchorInfoBean());
            this.llFreeTips.setVisibility(z ? 0 : 8);
            if (landscapeCallBack != null) {
                setLandscapeCallBack(landscapeCallBack);
            }
            this.lGiftMsgAnimView.stopAnimHdGift();
            showDianzanAnime();
            this.isMultiView = false;
            if (this.dmManager == null && !this.isRecycle) {
                this.ivBarrageBtn.setImageResource(2131230918);
                startDanmu();
            } else {
                switch (this.danmuType) {
                    case 1:
                        this.ivBarrageBtn.setImageResource(2131230919);
                        break;
                    case 2:
                        this.ivBarrageBtn.setImageResource(2131230917);
                        break;
                    default:
                        this.ivBarrageBtn.setImageResource(2131230918);
                        break;
                }
            }
            if (this.liveUI != null && zhuboDataEntity.getAnchorInfoBean().getUserId().equals(this.liveUI.getRoomId())) {
                hideChatRoomAndGiftByNet(this.liveUI);
            } else {
                this.liveUI = null;
            }
            this.view.setFocusableInTouchMode(true);
            this.view.requestFocus();
            this.view.setOnKeyListener(this);
            autoGoneView();
            landscape4PB();
            if (this.seekBar == null || this.seekBar.getMax() <= 0 || this.llProgressBar.getVisibility() != 0 || this.isStartTrackingTouch) {
                return;
            }
            this.seekBar.setProgress(getPlayer().getCurrentPosition());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BDCloudVideoView getPlayer() {
        try {
            if (this.activityContext instanceof AudiencePlayBackActivity) {
                return ((AudiencePlayBackActivity) this.activityContext).bdCloudVideoView;
            }
            AudienceActivity audienceActivity = (AudienceActivity) this.activityContext;
            return AudienceActivity.bdCloudVideoView;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initProgressBar() {
        try {
            this.llProgressBar = this.view.findViewById(2131297759);
            this.llProgressBar.setVisibility(this.isMultiView ? 8 : 0);
            this.ivPlayBtn = (ImageView) this.view.findViewById(2131297348);
            this.ivPlayBtn.setVisibility(0);
            this.tvBegin = (TextView) this.view.findViewById(2131299049);
            this.tvEnd = (TextView) this.view.findViewById(2131299051);
            this.seekBar = (SeekBar) this.view.findViewById(2131298452);
            this.seekBar.setMax(0);
            this.tvEnd.setText("00:00");
            this.tvBegin.setText("00:00");
            startPlayerProgressUpdate();
            this.ivPlayBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$RsKcSThNEw5_QBZPnpJnLH9hYY0
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceLandscapeView.lambda$initProgressBar$2(AudienceLandscapeView.this, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initProgressBar$2(AudienceLandscapeView audienceLandscapeView, View view) {
        View.OnClickListener onClickListener = audienceLandscapeView.clickListener;
        if (onClickListener != null) {
            onClickListener.onClick(null);
        }
    }

    private void setProgressBarMax() {
        try {
            int duration = getPlayer().getDuration();
            this.seekBar.setMax(duration);
            this.tvEnd.setText(NumUtils.stringForTime(duration));
            this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.1
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                    if (AudienceLandscapeView.this.tvBegin != null) {
                        AudienceLandscapeView.this.tvBegin.setText(NumUtils.stringForTime(i));
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    try {
                        AudienceLandscapeView.this.isStartTrackingTouch = true;
                        AudienceLandscapeView.this.seekBar.setProgressDrawableTiled(AudienceLandscapeView.this.getResources().getDrawable(2131232586));
                    } catch (Resources.NotFoundException e) {
                        e.printStackTrace();
                    }
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    Tracker.onStopTrackingTouch(seekBar);
                    try {
                        AudienceLandscapeView.this.isStartTrackingTouch = false;
                        AudienceLandscapeView.this.seekBar.setProgressDrawableTiled(AudienceLandscapeView.this.getResources().getDrawable(2131232576));
                        try {
                            if (AudienceLandscapeView.this.getPlayer() != null && AudienceLandscapeView.this.getPlayer().getDuration() > 0) {
                                AudienceLandscapeView.this.getPlayer().seekTo(AudienceLandscapeView.this.seekBar.getProgress());
                                AudienceLandscapeView.this.seekToPlay(AudienceLandscapeView.this.seekBar.getProgress());
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } catch (Resources.NotFoundException e2) {
                        e2.printStackTrace();
                    }
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToPlay(int i) {
        try {
            if (this.activityContext instanceof AudiencePlayBackActivity) {
                ((AudiencePlayBackActivity) this.activityContext).seekToPlay(i);
            } else if (this.activityContext instanceof AudienceActivity) {
                ((AudienceActivity) this.activityContext).seekToPlay(i);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setPlayBtn(boolean z) {
        try {
            if (z) {
                if (this.ivPlayBtn != null) {
                    this.ivPlayBtn.setImageResource(2131232593);
                }
            } else if (this.ivPlayBtn != null) {
                this.ivPlayBtn.setImageResource(2131232592);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startPlayerProgressUpdate() {
        try {
            stopUpdateProgress();
            this.updateSeek = Observable.interval(800L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$O8uVBCwqcw6AhbUM9ZMv-H18xU8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$startPlayerProgressUpdate$3(AudienceLandscapeView.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$startPlayerProgressUpdate$3(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        try {
            if (audienceLandscapeView.isMultiView) {
                audienceLandscapeView.updateSeek.dispose();
                audienceLandscapeView.updateSeek = null;
            }
            if (!audienceLandscapeView.getPlayer().isPlaying() || audienceLandscapeView.isMultiView) {
                return;
            }
            if (audienceLandscapeView.seekBar != null && audienceLandscapeView.seekBar.getMax() > 0 && audienceLandscapeView.llProgressBar.getVisibility() == 0) {
                if (audienceLandscapeView.isStartTrackingTouch) {
                    return;
                }
                audienceLandscapeView.seekBar.setProgress(audienceLandscapeView.getPlayer().getCurrentPosition());
                return;
            }
            audienceLandscapeView.setProgressBarMax();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void stopUpdateProgress() {
        Disposable disposable = this.updateSeek;
        if (disposable != null) {
            disposable.dispose();
            this.updateSeek = null;
        }
    }

    public void setClickListener(View.OnClickListener onClickListener) {
        this.clickListener = onClickListener;
    }

    private void initLikeView() {
        try {
            this.likeView = (LikeView) this.view.findViewById(2131297988);
            this.doubleClickListener = new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.2
                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void onClick(long j) {
                    try {
                        AudienceLandscapeView.this.rlMain.performClick();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                public void doubleTapCallback() {
                    try {
                        if (AudienceLandscapeView.this.ivLock.isSelected()) {
                            return;
                        }
                        AudienceLandscapeView.this.clickZan();
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
            e.printStackTrace();
        }
    }

    private void goneView() {
        try {
            this.isShowView = true;
            this.layoutMain.setVisibility(8);
            this.ll_hidebottom.setVisibility(8);
            this.ivLock.setVisibility(8);
            this.bubbleLayout.setVisibility(8);
            if (this.popupWindow != null) {
                this.popupWindow.dismiss();
            }
            if (this.tvLiveTitle != null) {
                this.tvLiveTitle.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showView() {
        try {
            this.isShowView = false;
            this.layoutMain.setVisibility(0);
            this.ll_hidebottom.setVisibility(0);
            if (this.tvLiveTitle != null && !TextUtils.isEmpty(this.liveTitle)) {
                this.tvLiveTitle.setVisibility(0);
            }
            this.ivLock.setVisibility(0);
            this.bubbleLayout.setVisibility(0);
            autoGoneView();
            if (System.currentTimeMillis() - this.showTime >= 5000 || this.popupWindow == null || this.danmuType != 1) {
                return;
            }
            this.popupWindow.showAsDropDown(this.ivBarrageBtn, -40, -180, 17);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        try {
            this.view = LayoutInflater.from(this.activityContext).inflate(2131492997, (ViewGroup) null);
            this.tvLiveTitle = (TextView) this.view.findViewById(2131299061);
            this.layoutMain = this.view.findViewById(2131297579);
            this.view.findViewById(2131298384).setOnClickListener(this);
            this.llAnimArea = (LinearLayout) this.view.findViewById(2131297686);
            initLandscapeMainView();
            addView(this.view);
            this.ivMulti = (ImageView) this.view.findViewById(2131297354);
            this.ivMulti.setOnClickListener(this);
            this.ivMulti.setVisibility(8);
            this.isMultiView = false;
            UIUtils.logD("xcy", "多视角横屏初始化完成");
            this.ivGiftBtn.setVisibility(0);
            this.gvControl = (MyGestureView) this.view.findViewById(2131297098);
            this.gvControl.setActivity(this.activityContext);
            this.gvControl.setVisibility(0);
            this.gvControl.setListener(this.doubleClickListener);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMulti() {
        try {
            this.isMultiView = true;
            UIUtils.logD("xcy", "设置多视角");
            this.ivMulti.setVisibility(0);
            this.ivGiftBtn.setVisibility(!"1".equals(this.liveType) ? 8 : 0);
            this.tvDefinitionBtn.setVisibility("1".equals(this.liveType) ? 0 : 8);
            if (this.liveUI != null) {
                hideChatRoomAndGiftByNet(this.liveUI);
            }
            if (this.llProgressBar != null) {
                this.llProgressBar.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLiveType(String str) {
        try {
            this.liveType = TextUtils.isEmpty(str) ? "1" : str;
            this.ivGiftBtn.setVisibility(!"1".equals(str) ? 8 : 0);
            if (this.liveUI != null) {
                hideChatRoomAndGiftByNet(this.liveUI);
            }
            if ("3".equals(str)) {
                initProgressBar();
            } else if (this.llProgressBar != null) {
                this.llProgressBar.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initLandscapeMainView() {
        initAnchorInfo();
        initFitment();
        initTipsAnimation();
        initCommentDarrage();
        initGiftAnimationView();
        initAttentionDialog();
        initBottomBtns();
        initLikeView();
    }

    private void initBottomBtns() {
        try {
            this.tvGoodsNum = (TextView) this.view.findViewById(2131298965);
            this.llCanGoneArea = (LinearLayout) this.view.findViewById(2131297694);
            this.ll_hidebottom = (LinearLayout) this.view.findViewById(2131297693);
            this.tvCommentBtn = (TextView) this.view.findViewById(2131298887);
            this.tvCommentBtn.setVisibility(0);
            this.tvCommentBtn.setOnClickListener(this);
            this.ivBarrageBtn = (ImageView) this.view.findViewById(2131297352);
            this.ivBarrageBtn.setOnClickListener(this);
            this.ivBarrageBtn.setVisibility(0);
            this.tvDefinitionBtn = (TextView) this.view.findViewById(2131298888);
            this.tvDefinitionBtn.setOnClickListener(this);
            this.ivShareBtn = (ImageView) this.view.findViewById(2131297355);
            this.ivGiftBtn = (ImageView) this.view.findViewById(2131297353);
            this.ivGiftBtn.setVisibility(0);
            this.ivGiftBtn.setOnClickListener(this);
            this.rlZan = this.view.findViewById(2131298326);
            this.ivZan = (ImageView) this.view.findViewById(2131297356);
            this.rlZan.setOnClickListener(this);
            this.bubbleLayout = (FrameLayout) this.view.findViewById(2131296446);
            this.goodsCardView = (CardView) this.view.findViewById(2131297088);
            this.goodsImage = (ImageView) this.view.findViewById(2131297090);
            this.goodsTilte = (TextView) this.view.findViewById(2131297093);
            this.goodsContent = (TextView) this.view.findViewById(2131297089);
            this.goodsPrice = (TextView) this.view.findViewById(2131297092);
            this.goodsOriPrice = (TextView) this.view.findViewById(2131297091);
            this.rlHide = (RelativeLayout) this.view.findViewById(2131298339);
            this.rlShow = (RelativeLayout) this.view.findViewById(2131298383);
            this.rlHide.setOnClickListener(this);
            this.rlShow.setOnClickListener(this);
            this.rlShoppingBag = (RelativeLayout) this.view.findViewById(2131298325);
            this.goodsSize = (TextView) this.view.findViewById(2131296384);
            this.rlShoppingBag.setOnClickListener(this);
            this.ivLock = (ImageView) this.view.findViewById(2131297425);
            this.ivLock.setOnClickListener(this);
            this.explanNotice = (TextView) this.view.findViewById(2131298937);
            this.rlMain = this.view.findViewById(2131298384);
            this.tvPayInfoBtn4PB = (TextView) this.view.findViewById(2131299042);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initAnchorInfo() {
        try {
            this.view.findViewById(2131297685).setOnClickListener(this);
            this.view.findViewById(2131297346).setOnClickListener(this);
            this.tvFansNum = (TextView) this.view.findViewById(2131298939);
            this.tvGetGiftNum = (TextView) this.view.findViewById(2131298958);
            this.llFreeTips = this.view.findViewById(2131297716);
            this.tvAttentionAnchorBtn = (TextView) this.view.findViewById(2131298873);
            this.tvAttentionAnchorBtn.setOnClickListener(this);
            this.tvAchorCity = (TextView) this.view.findViewById(2131298861);
            this.tvPopularity = (TextView) this.view.findViewById(2131298866);
            this.tvAnchorName = (TextView) this.view.findViewById(2131298863);
            this.civAnchorImg = (CircularImage) this.view.findViewById(2131296666);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTipsAnimation() {
        try {
            this.joinAnimView = (MsgAnimView) this.view.findViewById(2131297544);
            this.topMsgAnimView = (TopMsgAnimView) this.view.findViewById(2131298814);
            this.lGiftMsgAnimView = (LandscapeGiftMsgAnimView) this.view.findViewById(2131297077);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initCommentDarrage() {
        try {
            this.flComment = (FrameLayout) this.view.findViewById(2131296989);
            this.flComment.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGiftAnimationView() {
        try {
            this.dianZanAnim = (LiveGiftView) this.view.findViewById(2131296884);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initGiftDialog() {
        try {
            this.llGiftDialog = (LinearLayout) this.view.findViewById(2131297719);
            this.llGiftDialog.setOnClickListener(this);
            this.view.findViewById(2131299477).setOnClickListener(this);
            this.bottomImage1 = (ImageView) this.view.findViewById(2131296399);
            this.textView1 = (TextView) this.view.findViewById(2131296401);
            this.bottomImage2 = (ImageView) this.view.findViewById(2131296400);
            this.textView2 = (TextView) this.view.findViewById(2131296402);
            this.textView3 = (TextView) this.view.findViewById(2131296403);
            this.tips = (ImageView) this.view.findViewById(2131297389);
            C84623 c84623 = new C84623();
            this.viewPager = (ViewPager) this.view.findViewById(2131299541);
            initViewpager(this.viewPager, this.list, this.activityContext, c84623);
            this.managerLoad.loadGiftNum().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$7N7v2Oicd7vI-iqoNU-HApcfPzY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$initGiftDialog$4(AudienceLandscapeView.this, (String) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            this.managerLoad.loadScore().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$fFcpDgI_gOmyi_Q12X7buVC7GXo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$initGiftDialog$5(AudienceLandscapeView.this, (String) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$CP8fDj2zy7b85jsWzB4NW7Nfg-w
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$initGiftDialog$6(AudienceLandscapeView.this, (Throwable) obj);
                }
            });
            ((ImageView) this.view.findViewById(2131296404)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$p17ig8yOrbKsS-ciaKyiizn4rJs
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceLandscapeView.this.dismiss();
                }
            });
            showGiftDialog();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C84623 implements AdapterView.OnItemClickListener {
        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$0(String str) throws Exception {
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        public static /* synthetic */ void lambda$onItemClick$2(String str) throws Exception {
        }

        C84623() {
        }

        @Override // android.widget.AdapterView.OnItemClickListener
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long j) {
            NBSActionInstrumentation.onItemClickEnter(view, i, this);
            Tracker.onItemClick(adapterView, view, i, j);
            try {
                List<GiftEntity> list = AudienceLandscapeView.this.vpAdapter.getItemAdapter(AudienceLandscapeView.this.viewPager.getCurrentItem()).list;
                if (AudienceLandscapeView.this.vpAdapter.getCount() > 1) {
                    for (int i2 = 0; i2 < AudienceLandscapeView.this.vpAdapter.getItemAdapterCount(); i2++) {
                        if (i2 != AudienceLandscapeView.this.viewPager.getCurrentItem()) {
                            for (GiftEntity giftEntity : AudienceLandscapeView.this.vpAdapter.getItemAdapter(i2).list) {
                                giftEntity.setSelected(false);
                            }
                            AudienceLandscapeView.this.vpAdapter.getItemAdapter(i2).notifyDataSetChanged();
                        }
                    }
                }
                for (GiftEntity giftEntity2 : list) {
                    giftEntity2.setSelected(false);
                }
                final GiftEntity giftEntity3 = list.get(i);
                if (!giftEntity3.isXianhua()) {
                    giftEntity3.setSelected(true);
                }
                AudienceLandscapeView.this.vpAdapter.getItemAdapter(AudienceLandscapeView.this.viewPager.getCurrentItem()).update(list);
                GlideApp.with((FragmentActivity) AudienceLandscapeView.this.activityContext).load(giftEntity3.getImgFileNum()).into(AudienceLandscapeView.this.bottomImage2);
                AudienceLandscapeView.this.textView2.setText(giftEntity3.getGiftNum() + "");
                if ("10001".equals(giftEntity3.getGiftCode())) {
                    if (giftEntity3.getGiftNum() <= 0) {
                        NBSActionInstrumentation.onItemClickExit();
                        return;
                    }
                    if (giftEntity3.getGiftNum() > 0) {
                        AudienceLandscapeView.access$1308(AudienceLandscapeView.this);
                    }
                    if (AudienceLandscapeView.this.subscribeGift != null) {
                        AudienceLandscapeView.this.subscribeGift.dispose();
                    }
                    AudienceLandscapeView.this.subscribeGift = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$3$cE8f9W565j1TMZMH7oDXB9sype4
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceLandscapeView.C84623.lambda$onItemClick$1(AudienceLandscapeView.C84623.this, giftEntity3, (Long) obj);
                        }
                    });
                    if (giftEntity3.getGiftNum() >= 1) {
                        giftEntity3.setGiftNum(giftEntity3.getGiftNum() - 1);
                    }
                } else if (giftEntity3.getGiftNum() > 0) {
                    AudienceLandscapeView.this.textView3.setVisibility(0);
                    AudienceLandscapeView.this.textView3.setText("赠送");
                    AudienceLandscapeView.this.textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$3$4z-wda-uSHKaVRVAyPPeLRTgTeg
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view2) {
                            AudienceLandscapeView.C84623.lambda$onItemClick$3(AudienceLandscapeView.C84623.this, giftEntity3, view2);
                        }
                    });
                } else {
                    try {
                        int parseInt = Integer.parseInt(giftEntity3.getGiftPrice());
                        AudienceLandscapeView.this.textView3.setVisibility(0);
                        if (AudienceLandscapeView.this.myJifenNum > parseInt) {
                            AudienceLandscapeView.this.textView3.setText("去兑换");
                            AudienceLandscapeView.this.textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$3$DYRupYt3m4Efd5cbBPvLAC6ODnw
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    AudienceLandscapeView.C84623.lambda$onItemClick$4(AudienceLandscapeView.C84623.this, giftEntity3, view2);
                                }
                            });
                        } else {
                            AudienceLandscapeView.this.textView3.setText("赚积分");
                            AudienceLandscapeView.this.textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$3$t-Awd3i-g63cTjDxDamnYgmIr4U
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view2) {
                                    AudienceLandscapeView.C84623.lambda$onItemClick$5(AudienceLandscapeView.C84623.this, giftEntity3, view2);
                                }
                            });
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
            NBSActionInstrumentation.onItemClickExit();
        }

        public static /* synthetic */ void lambda$onItemClick$1(C84623 c84623, GiftEntity giftEntity, Long l) throws Exception {
            try {
                if (AudienceLandscapeView.this.count <= 0) {
                    return;
                }
                ManagerAudienceLoadData managerAudienceLoadData = AudienceLandscapeView.this.managerLoad;
                ZhuboDataEntity.AnchorInfoBean anchorInfoBean = AudienceLandscapeView.this.currentAnchor;
                String giftCode = giftEntity.getGiftCode();
                managerAudienceLoadData.sendLiwu(anchorInfoBean, giftCode, AudienceLandscapeView.this.count + "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$3$sHZwAbCKm_J20yAGTcjboOT8d4c
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceLandscapeView.C84623.lambda$onItemClick$0((String) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                AudienceLandscapeView.this.dismiss();
                AudienceLandscapeView audienceLandscapeView = AudienceLandscapeView.this;
                String giftCode2 = giftEntity.getGiftCode();
                audienceLandscapeView.giftClick(giftCode2, AudienceLandscapeView.this.count + "");
                if (AudienceLandscapeView.this.subscribeGift != null) {
                    AudienceLandscapeView.this.subscribeGift.dispose();
                    AudienceLandscapeView.this.subscribeGift = null;
                    AudienceLandscapeView.this.count = 0;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$onItemClick$3(C84623 c84623, GiftEntity giftEntity, View view) {
            try {
                AudienceLandscapeView.this.managerLoad.sendLiwu(AudienceLandscapeView.this.currentAnchor, giftEntity.getGiftCode(), "1").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$3$vxmcVPcfhVomT2OMugjKoixhozE
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceLandscapeView.C84623.lambda$onItemClick$2((String) obj);
                    }
                }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                AudienceLandscapeView.this.dismiss();
                AudienceLandscapeView.this.giftClick(giftEntity.getGiftCode(), "1");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$onItemClick$4(C84623 c84623, GiftEntity giftEntity, View view) {
            try {
                AudienceActivity.IntentGo(AudienceLandscapeView.this.activityContext, giftEntity.getPayUrl());
                AudienceLandscapeView.this.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public static /* synthetic */ void lambda$onItemClick$5(C84623 c84623, GiftEntity giftEntity, View view) {
            try {
                if (!TextUtils.isEmpty(giftEntity.getObtainUrl())) {
                    AudienceActivity.IntentGo(AudienceLandscapeView.this.activityContext, giftEntity.getObtainUrl());
                } else {
                    AudienceActivity.IntentGo(AudienceLandscapeView.this.activityContext, URLSet.getAudienceQiandaoUrl());
                }
                AudienceLandscapeView.this.dismiss();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static /* synthetic */ void lambda$initGiftDialog$4(AudienceLandscapeView audienceLandscapeView, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        if ("0000".equals(jSONObject.optString("statusCode"))) {
            JSONObject optJSONObject = jSONObject.optJSONObject("gift");
            for (List<GiftEntity> list : audienceLandscapeView.vpAdapter.getPagerList()) {
                for (GiftEntity giftEntity : list) {
                    JSONObject optJSONObject2 = optJSONObject.optJSONObject(giftEntity.getGiftCode());
                    if (optJSONObject2 != null) {
                        giftEntity.setGiftNum(optJSONObject2.optInt("giftNum"));
                    }
                    giftEntity.setSelected(false);
                }
            }
            for (GiftAdapter giftAdapter : audienceLandscapeView.vpAdapter.adapterList) {
                giftAdapter.notifyDataSetChanged();
            }
        }
    }

    public static /* synthetic */ void lambda$initGiftDialog$5(AudienceLandscapeView audienceLandscapeView, String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("score");
        if ("0000".equals(jSONObject.optString("statusCode"))) {
            TextView textView = audienceLandscapeView.textView1;
            textView.setText("可兑换积分:" + optString);
            try {
                audienceLandscapeView.myJifenNum = Integer.parseInt(optString);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            audienceLandscapeView.textView1.setText("重新刷新");
        }
        audienceLandscapeView.bottomImage1.setImageResource(2131230915);
    }

    public static /* synthetic */ void lambda$initGiftDialog$6(AudienceLandscapeView audienceLandscapeView, Throwable th) throws Exception {
        audienceLandscapeView.textView1.setText("重新刷新");
        audienceLandscapeView.bottomImage1.setImageResource(2131230915);
    }

    private void initAttentionDialog() {
        try {
            this.dianzanAnimation = AnimationUtils.loadAnimation(this.activityContext, 2130772094);
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
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

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        try {
            boolean z = true;
            switch (view.getId()) {
                case 2131297346:
                    view.getRootView().setFocusableInTouchMode(false);
                    view.getRootView().clearFocus();
                    if (this.landscapeCallBack != null) {
                        this.landscapeCallBack.close();
                        cleanAnimation();
                    }
                    showChatRoomAndGiftUI();
                    break;
                case 2131297352:
                    this.isRecycle = false;
                    if (this.landscapeCallBack != null && this.landscapeCallBack.isNeedPay()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else if (this.danmuType == 2) {
                        this.danmuType = 0;
                        startDanmu();
                        break;
                    } else if (this.danmuType == 1) {
                        recycleDanmu();
                        break;
                    } else if (this.danmuType == 0) {
                        startStreamLineDanmu();
                        break;
                    }
                    break;
                case 2131297353:
                    if (this.landscapeCallBack != null && this.landscapeCallBack.isNeedPay() && isNotPlayBack()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    List<GiftEntity> giftList = CacheDataCenter.getInstance().getGiftList();
                    if (giftList.size() == 0) {
                        this.managerLoad.getGiftListNew().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$Q-y6tFnIXJT3ddT3YO0SBNEmflY
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                AudienceLandscapeView.lambda$onClick$10(AudienceLandscapeView.this, (List) obj);
                            }
                        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                        break;
                    } else {
                        UIUtils.logD("lln", "使用缓存数据--获取礼物列表");
                        this.list = giftList;
                        initGiftDialog();
                        break;
                    }
                case 2131297354:
                    if (this.landscapeCallBack != null && this.landscapeCallBack.isNeedPay() && isNotPlayBack()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    if (this.landscapeCallBack != null) {
                        this.landscapeCallBack.showMulti();
                    }
                    AudienceGoodsListDialogNew.getInstance().releasePw();
                    break;
                case 2131297425:
                    ImageView imageView = this.ivLock;
                    if (this.ivLock.isSelected()) {
                        z = false;
                    }
                    imageView.setSelected(z);
                    if (this.ivLock.isSelected()) {
                        this.layoutMain.setVisibility(8);
                        this.ll_hidebottom.setVisibility(8);
                        if (this.tvLiveTitle != null) {
                            this.tvLiveTitle.setVisibility(8);
                        }
                        this.goodsCardView.setVisibility(8);
                        this.flComment.setVisibility(4);
                        this.llAnimArea.setVisibility(8);
                        this.bubbleLayout.setVisibility(8);
                        this.rlShow.setVisibility(8);
                        if (this.popupWindow != null) {
                            this.popupWindow.dismiss();
                        }
                        if (this.gvControl != null) {
                            this.gvControl.setVisibility(8);
                        }
                        autoGoneLock();
                        break;
                    } else {
                        showLockView();
                        break;
                    }
                case 2131297685:
                    String userIndexUrl = this.currentAnchor.getUserIndexUrl();
                    if (!TextUtils.isEmpty(userIndexUrl)) {
                        Intent intent = new Intent(this.activityContext, WebDetailActivity.class);
                        WebParamsEntity webParamsEntity = new WebParamsEntity();
                        webParamsEntity.setUrl(addChannel(userIndexUrl, "直播".equals(this.liveType) ? this.liveType : "直播回放"));
                        webParamsEntity.setNeedTitle(true);
                        webParamsEntity.setRequestType("get");
                        intent.putExtra(WebFragment.webParams, webParamsEntity);
                        this.activityContext.startActivityForResult(intent, 4000);
                        break;
                    }
                    break;
                case 2131297719:
                    UIUtils.logD("landscape", "点击了礼物窗体");
                    break;
                case 2131298326:
                    if (this.landscapeCallBack != null && this.landscapeCallBack.isNeedPay()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else {
                        clickZan();
                        break;
                    }
                    break;
                case 2131298339:
                    this.goodsCardView.setVisibility(8);
                    this.rlShow.setVisibility(0);
                    break;
                case 2131298383:
                    this.goodsCardView.setVisibility(0);
                    this.rlShow.setVisibility(8);
                    break;
                case 2131298384:
                    if (!this.isShowView) {
                        goneView();
                        break;
                    } else if (this.ivLock.isSelected()) {
                        this.ivLock.setVisibility(0);
                        break;
                    } else {
                        showView();
                        break;
                    }
                case 2131298873:
                    guanzhu(this.currentAnchor.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$VsGSVw5ug7m2f3zpRPGIQd8-yLQ
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceLandscapeView.lambda$onClick$8(AudienceLandscapeView.this, (String) obj);
                        }
                    }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
                    break;
                case 2131298887:
                    if (this.landscapeCallBack != null && this.landscapeCallBack.isNeedPay()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else if (this.landscapeCallBack != null) {
                        this.landscapeCallBack.showKeyboard();
                        break;
                    }
                    break;
                case 2131298888:
                    if (this.landscapeCallBack != null && this.landscapeCallBack.isNeedPay() && isNotPlayBack()) {
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    } else if (this.liveUrlList != null && this.liveUrlList.size() > 0) {
                        AudienceSwitchDialog.show(this.activityContext, this.liveUrlList, true, new AudienceSwitchDialog.SwitchDialogInterface() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$ZKDDDTNzHHTcbCaZNEt3aAMYiyE
                            @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceSwitchDialog.SwitchDialogInterface
                            public final void onClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
                                AudienceLandscapeView.lambda$onClick$9(AudienceLandscapeView.this, liveUrlBean);
                            }
                        });
                        break;
                    }
                    break;
                case 2131299477:
                    dismiss();
                    break;
            }
        } catch (Exception e) {
            MsLogUtil.m7999d("AudienceLandscapeView", e.getMessage());
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public static /* synthetic */ void lambda$onClick$8(AudienceLandscapeView audienceLandscapeView, String str) throws Exception {
        if ("0000".equals(str)) {
            UIUtils.toastCenter("关注成功");
            audienceLandscapeView.tvAttentionAnchorBtn.setVisibility(8);
            try {
                String charSequence = audienceLandscapeView.tvFansNum.getText().toString();
                if (!TextUtils.isEmpty(charSequence)) {
                    int parseInt = Integer.parseInt(charSequence);
                    TextView textView = audienceLandscapeView.tvFansNum;
                    textView.setText((parseInt + 1) + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            LandscapeCallBack landscapeCallBack = audienceLandscapeView.landscapeCallBack;
            if (landscapeCallBack != null) {
                landscapeCallBack.guanzhuStatus();
                return;
            }
            return;
        }
        UIUtils.toastCenter(str);
    }

    public static /* synthetic */ void lambda$onClick$9(AudienceLandscapeView audienceLandscapeView, SharpnessEntity.LiveUrlBean liveUrlBean) {
        try {
            App.getSharePreferenceUtil().putString("gzdqingxidu", liveUrlBean.getSharpnessLevel());
            audienceLandscapeView.landscapeCallBack.switchClick(liveUrlBean);
            audienceLandscapeView.setSwitchTextView(liveUrlBean);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$onClick$10(AudienceLandscapeView audienceLandscapeView, List list) throws Exception {
        UIUtils.logD("lln", "请求网络数据--获取礼物列表");
        audienceLandscapeView.list = list;
        audienceLandscapeView.initGiftDialog();
    }

    private void showLockView() {
        try {
            this.flComment.setVisibility(0);
            this.llAnimArea.setVisibility(0);
            this.bubbleLayout.setVisibility(0);
            this.layoutMain.setVisibility(0);
            this.ll_hidebottom.setVisibility(0);
            if (this.tvLiveTitle != null && !TextUtils.isEmpty(this.liveTitle)) {
                this.tvLiveTitle.setVisibility(0);
            }
            this.ivLock.setVisibility(0);
            this.bubbleLayout.setVisibility(0);
            if (this.popupWindow != null) {
                this.popupWindow.showAsDropDown(this.ivBarrageBtn, -40, -180, 17);
            }
            if (this.isHaveGood) {
                this.goodsCardView.setVisibility(0);
            }
            if (this.gvControl != null) {
                this.gvControl.setVisibility(0);
            }
            autoGoneView();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickZan() {
        try {
            this.ivZan.startAnimation(this.dianzanAnimation);
            int parseInt = Integer.parseInt(this.tvPopularity.getTag().toString()) + 1;
            this.tvPopularity.setText(FormatUtils.getShowString(parseInt));
            this.tvPopularity.setTag(parseInt + "");
            this.dianZanAnim.startLocalZanSvgaAnim();
            ShakeUtils.vibrate(this.activityContext, 100L);
        } catch (Exception e) {
            e.printStackTrace();
            this.tvPopularity.setText("1");
            this.tvPopularity.setTag("1");
        }
        try {
            if (!TextUtils.isEmpty(this.videoId)) {
                this.managerLoad.videoPraise(this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$M-U7fhno7G6zKlkbqKpvTydY2lg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceLandscapeView.lambda$clickZan$11((String) obj);
                    }
                });
                return;
            }
            this.dianzanCount++;
            if (this.subscribe != null) {
                this.subscribe.dispose();
            }
            this.subscribe = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$k-r0Gvr6RYS8qGf70jAodB11qRU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$clickZan$13(AudienceLandscapeView.this, (Long) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$clickZan$13(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        ManagerAudienceLoadData managerAudienceLoadData = audienceLandscapeView.managerLoad;
        String userId = audienceLandscapeView.currentAnchor.getUserId();
        managerAudienceLoadData.addPriser(userId, audienceLandscapeView.dianzanCount + "", audienceLandscapeView.isMultiView ? "2" : "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$5_66pF4m8deh0oEjNFw4SXjf1Hs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceLandscapeView.lambda$clickZan$12((Boolean) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        audienceLandscapeView.dianzanCount = 0;
        Disposable disposable = audienceLandscapeView.subscribe;
        if (disposable != null) {
            disposable.dispose();
            audienceLandscapeView.subscribe = null;
        }
    }

    private void autoGoneView() {
        try {
            if (this.autoGoneView != null) {
                this.autoGoneView.dispose();
            }
            this.autoGoneView = Observable.timer(5000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$9jV3rNy12Nl0yAxPYYzPg7GoCzw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$autoGoneView$14(AudienceLandscapeView.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$autoGoneView$14(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        try {
            audienceLandscapeView.goneView();
            if (audienceLandscapeView.autoGoneView != null) {
                audienceLandscapeView.autoGoneView.dispose();
                audienceLandscapeView.autoGoneView = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void autoGoneLock() {
        Disposable disposable = this.autoGoneLock;
        if (disposable != null) {
            disposable.dispose();
        }
        this.autoGoneLock = Observable.timer(3000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$0PgP_Ejl0Dz8sQ3KhRWRboXUpL4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceLandscapeView.lambda$autoGoneLock$15(AudienceLandscapeView.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$autoGoneLock$15(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        try {
            audienceLandscapeView.ivLock.setVisibility(8);
            if (audienceLandscapeView.autoGoneLock != null) {
                audienceLandscapeView.autoGoneLock.dispose();
                audienceLandscapeView.autoGoneLock = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setAnchorInfo(ZhuboDataEntity.AnchorInfoBean anchorInfoBean) {
        try {
            GlideApp.with((FragmentActivity) this.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into(this.civAnchorImg);
            this.tvAnchorName.setText(anchorInfoBean.getUserName());
            this.tvAchorCity.setText(anchorInfoBean.getUserCityName());
            if (this.hasUpdateFans) {
                return;
            }
            this.tvGetGiftNum.setText(anchorInfoBean.getReceiveGiftNum());
            this.tvFansNum.setText(anchorInfoBean.getFansNum());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setZanText(String str, String str2) {
        this.videoId = str2;
        try {
            setZanNum(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGoods(ShopEntity shopEntity) {
        if (shopEntity != null) {
            try {
                if ("0000".equals(shopEntity.getStatusCode())) {
                    ShopEntity.DataBean data = shopEntity.getData();
                    if (data != null) {
                        setZanText(data.getRoundPraise(), "");
                        if ("Y".equalsIgnoreCase(data.getHaveGoods())) {
                            this.isHaveGood = true;
                            if (this.rlShow.getVisibility() == 8) {
                                this.goodsCardView.setVisibility(0);
                            }
                            final ShopEntity.DataBean.GoodsBean goods = data.getGoods();
                            GlideApp.with((FragmentActivity) this.activityContext).load(goods.getCoverImgUrl()).into(this.goodsImage);
                            this.goodsTilte.setText(goods.getName());
                            this.goodsContent.setText(goods.getDesc());
                            this.goodsPrice.setText(TextUtils.isEmpty(goods.getPrice()) ? goods.getOriginalPrice() : goods.getPrice());
                            if (TextUtils.isEmpty(goods.getOriginalPrice())) {
                                this.goodsOriPrice.setVisibility(8);
                            } else {
                                this.goodsOriPrice.setVisibility(0);
                                this.goodsOriPrice.getPaint().setFlags(16);
                            }
                            this.goodsCardView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$cY27hn5KblPxfffSF0wNM4RD1SY
                                @Override // android.view.View.OnClickListener
                                public final void onClick(View view) {
                                    AudienceActivity.IntentGo(AudienceLandscapeView.this.activityContext, r1.getGoodsUrl(), "0", goods.getId(), 4000);
                                }
                            });
                            return;
                        }
                        this.isHaveGood = false;
                        this.goodsCardView.setVisibility(8);
                        this.isHaveGood = false;
                        return;
                    }
                    this.isHaveGood = false;
                    this.goodsCardView.setVisibility(8);
                    this.isHaveGood = false;
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.isHaveGood = false;
        this.goodsCardView.setVisibility(8);
        this.isHaveGood = false;
    }

    public void setGoodsList(final List<GoodListEntity> list) {
        if (list != null) {
            try {
                if (list.size() > 0) {
                    this.rlShoppingBag.setVisibility(0);
                    TextView textView = this.goodsSize;
                    textView.setText(list.size() + "");
                    RxView.clicks(this.rlShoppingBag).throttleFirst(2L, TimeUnit.SECONDS).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$TbxWv-lPkf-mtoBQvv120shDUyU
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            AudienceLandscapeView.lambda$setGoodsList$17(AudienceLandscapeView.this, list, obj);
                        }
                    });
                    AudienceGoodsListDialogNew.getInstance().update(list).setOnClick(new AudienceGoodsListDialogNew.onLandscapeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$qTCFagh7xjG1eOWTaR3JAtEksSg
                        @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew.onLandscapeListener
                        public final void sendMsg() {
                            AudienceLandscapeView.lambda$setGoodsList$18(AudienceLandscapeView.this);
                        }
                    });
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        this.isHaveGood = false;
        this.rlShoppingBag.setVisibility(8);
        this.goodsCardView.setVisibility(8);
    }

    public static /* synthetic */ void lambda$setGoodsList$17(AudienceLandscapeView audienceLandscapeView, List list, Object obj) throws Exception {
        try {
            AudienceGoodsListDialogNew.getInstance().initLandscape(audienceLandscapeView.activityContext, list, audienceLandscapeView.currentAnchor.getUserId(), audienceLandscapeView.rlMain);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setGoodsList$18(AudienceLandscapeView audienceLandscapeView) {
        try {
            audienceLandscapeView.showExplainNotice();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showExplainNotice() {
        try {
            this.explanNotice.setVisibility(0);
            this.explainTimer = Observable.interval(3L, 1L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$XfwAay6yQuR5bKR3tlHiTgpSPvM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$showExplainNotice$19(AudienceLandscapeView.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showExplainNotice$19(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        try {
            audienceLandscapeView.explanNotice.setVisibility(8);
            audienceLandscapeView.explainTimer.dispose();
            audienceLandscapeView.explainTimer = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setShareData(final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final ManagerAudienceLoadData managerAudienceLoadData, final String str, final String str2) {
        try {
            this.ivShareBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$5sui3ZgSo7lQsHY_AZfTnKXPt7g
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceLandscapeView.lambda$setShareData$21(AudienceLandscapeView.this, str, anchorInfoBean, str2, managerAudienceLoadData, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setShareData$21(AudienceLandscapeView audienceLandscapeView, String str, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, String str2, ManagerAudienceLoadData managerAudienceLoadData, View view) {
        String shareContentDesc;
        try {
            JSONObject jSONObject = new JSONObject();
            String str3 = null;
            String str4 = "&liveShareInfo=" + str + "&liveShareProvince=" + UserManager.getInstance().getCurrentProvinceCode() + "&liveShareCity=" + UserManager.getInstance().getCurrentCityCode() + "&liveShareChannel=";
            String str5 = audienceLandscapeView.isMultiView ? "&isToMultiLive=Y" : "";
            String str6 = anchorInfoBean.getUserName() + "正在直播";
            if (TextUtils.isEmpty(anchorInfoBean.getShareContentDesc())) {
                shareContentDesc = "来自中国联通APP-" + anchorInfoBean.getUserName() + "主播";
            } else {
                shareContentDesc = anchorInfoBean.getShareContentDesc();
            }
            String replace = (URLSet.getZhiboShareUrl(anchorInfoBean.getUserId()) + str5 + str4).replace("#/liveplayer?", str2);
            String userImg = anchorInfoBean.getUserImg();
            try {
                str3 = "wechat,wechatmoments,qq,qzone";
                jSONObject.put("shareType", "url");
                jSONObject.put("shareTitle", str6);
                jSONObject.put("shareContent", shareContentDesc);
                jSONObject.put("shareURL", replace);
                jSONObject.put("shareIconURL", userImg);
            } catch (Exception e) {
                e.printStackTrace();
            }
            managerAudienceLoadData.addShare(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$MinXlt-xIOHuT8vxrdumzXj5EKE
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$setShareData$20((String) obj);
                }
            });
            ShareManager.ShowShareDialog(audienceLandscapeView.activityContext, str3, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.4
                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onCancel(String str7) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onComplete(String str7) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onError(String str7, String str8) {
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public void updateMessageAdapter(LiveMsg liveMsg) {
        try {
            UIUtils.logD("updateMessageAdapter", liveMsg.getType() + "----" + liveMsg.getmType());
            AlivcLiveMessageInfo alivcLiveMessageInfo = new AlivcLiveMessageInfo();
            alivcLiveMessageInfo.setDataContent(liveMsg.getMsg());
            alivcLiveMessageInfo.setAvatar(liveMsg.getAvatar());
            alivcLiveMessageInfo.setSendName(liveMsg.getNick());
            alivcLiveMessageInfo.setUserId(liveMsg.getId());
            alivcLiveMessageInfo.setLevel(liveMsg.getLevel());
            alivcLiveMessageInfo.setGiftNum(liveMsg.getGiftNum());
            alivcLiveMessageInfo.setGiftCode(liveMsg.getGiftCode());
            alivcLiveMessageInfo.setAcType(liveMsg.getAcType());
            alivcLiveMessageInfo.setMgr(liveMsg.isMgr());
            if ("join".equals(liveMsg.getType()) && !TextUtils.isEmpty(liveMsg.getNick())) {
                startJoinOrFocusAnim(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.isMgr(), true);
            }
            if ("chat".equals(liveMsg.getType())) {
                sendDanmu(ChatTextUtil.getChat(this.activityContext, alivcLiveMessageInfo));
            }
            "atto".equals(liveMsg.getType());
            if ("push".equals(liveMsg.getType())) {
                if ("2".equals(liveMsg.getmType())) {
                    startJoinOrFocusAnim(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.isMgr(), false);
                }
                if ("3".equals(liveMsg.getmType())) {
                    startBrowseGoodAnim(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.isMgr(), "");
                }
                if ("4".equals(liveMsg.getmType())) {
                    startOrderAnim(liveMsg.getNick(), liveMsg.getLevel(), "");
                }
                if ("6".equals(liveMsg.getmType())) {
                    startMsgGiftAnim(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.getGiftCode(), liveMsg.getGiftNum());
                }
                "201".equals(liveMsg.getmType());
                if ("710".equals(liveMsg.getmType()) && this.currentAnchor.getUserId().equals(liveMsg.getRid())) {
                    alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_EXPLAIN_GOODS.getMsgType());
                    String goodsInfoById = AudienceGoodsListDialogNew.getInstance().getGoodsInfoById(liveMsg.getGoodsId());
                    if (TextUtils.isEmpty(goodsInfoById)) {
                        return;
                    }
                    alivcLiveMessageInfo.setDataContent("求讲解 " + goodsInfoById + "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendDanmu(String str) {
        try {
            if (this.dmManager != null) {
                Danmaku danmaku = new Danmaku();
                danmaku.text = str;
                danmaku.size = UIUtils.dip2px(this.activityContext, 14.0f);
                this.dmManager.send(danmaku);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sendDanmu(SpannableStringBuilder spannableStringBuilder) {
        try {
            if (this.dmManager != null) {
                Danmaku danmaku = new Danmaku();
                danmaku.spanText = spannableStringBuilder;
                danmaku.size = UIUtils.dip2px(this.activityContext, 14.0f);
                this.dmManager.send(danmaku);
            } else {
                switch (this.danmuType) {
                    case 1:
                        startStreamLineDanmu();
                        break;
                    case 2:
                        break;
                    default:
                        startDanmu();
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startDanmu() {
        try {
            this.ivBarrageBtn.setImageResource(2131230918);
            this.dmManager = DanmakuManager.getInstance();
            this.dmManager.init(this.activityContext, this.flComment);
            this.dmManager.setMaxDanmakuSize(20);
            this.dmManager.getConfig().setMaxScrollLine(6);
            this.dmManager.getConfig().setLineHeight(UIUtils.dip2px(this.activityContext, 20.0f));
            this.dmManager.resetPositionCalculator();
            if (this.popupWindow != null) {
                this.popupWindow.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void recycleDanmu() {
        try {
            this.danmuType = 2;
            this.isRecycle = true;
            this.ivBarrageBtn.setImageResource(2131230917);
            this.dmManager = null;
            this.flComment.removeAllViews();
            if (this.popupWindow != null) {
                this.popupWindow.dismiss();
                this.popupWindow = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"CheckResult"})
    private void startStreamLineDanmu() {
        try {
            this.danmuType = 1;
            this.ivBarrageBtn.setImageResource(2131230919);
            this.dmManager.setMaxDanmakuSize(8);
            this.dmManager.getConfig().setMaxScrollLine(2);
            this.dmManager.getConfig().setLineHeight(UIUtils.dip2px(this.activityContext, 20.0f));
            this.dmManager.resetPositionCalculator();
            View inflate = LayoutInflater.from(this.activityContext).inflate(2131493005, (ViewGroup) null);
            if (this.popupWindow == null) {
                this.popupWindow = new PopupWindow(inflate, -2, -2, false);
            }
            this.popupWindow.showAsDropDown(this.ivBarrageBtn, -40, -180, 17);
            this.showTime = System.currentTimeMillis();
            Observable.timer(5000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$2_3veSBqfHk2R6LJU9Ts0jjLWl0
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$startStreamLineDanmu$22(AudienceLandscapeView.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$startStreamLineDanmu$22(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        PopupWindow popupWindow = audienceLandscapeView.popupWindow;
        if (popupWindow != null) {
            popupWindow.dismiss();
        }
    }

    public ObservableSubscribeProxy<String> guanzhu(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.addGaunzhu(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$5vdrHpa4tzQQMel_D19lZK2Y1QI
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AudienceLandscapeView.lambda$guanzhu$23((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ String lambda$guanzhu$23(String str) throws Exception {
        JSONObject jSONObject = new JSONObject(str);
        String optString = jSONObject.optString("statusCode");
        return "0000".equals(optString) ? optString : jSONObject.optString("message");
    }

    public void startJoinOrFocusAnim(String str, String str2, boolean z, boolean z2) {
        MsgAnimView msgAnimView = this.joinAnimView;
        if (msgAnimView != null) {
            msgAnimView.startAnimJoinOrFocus(str, str2, z, z2);
        }
    }

    public void stopJoinOrFocusAnim() {
        MsgAnimView msgAnimView = this.joinAnimView;
        if (msgAnimView != null) {
            msgAnimView.stopAnimJoinOrFocus();
        }
    }

    public void startBrowseGoodAnim(String str, String str2, boolean z, String str3) {
        MsgAnimView msgAnimView = this.joinAnimView;
        if (msgAnimView != null) {
            msgAnimView.startAnimBrowseGoods(str, str2, z, str3);
        }
    }

    public void stopBrowseGoodAnim() {
        MsgAnimView msgAnimView = this.joinAnimView;
        if (msgAnimView != null) {
            msgAnimView.stopAnimBrowseGoods();
        }
    }

    public void startOrderAnim(String str, String str2, String str3) {
        TopMsgAnimView topMsgAnimView = this.topMsgAnimView;
        if (topMsgAnimView != null) {
            topMsgAnimView.startAnimOrder(str, str2, str3);
        }
    }

    public void stopOrderAnim() {
        TopMsgAnimView topMsgAnimView = this.topMsgAnimView;
        if (topMsgAnimView != null) {
            topMsgAnimView.stopAnimMsgGift();
        }
    }

    public void startMsgGiftAnim(String str, String str2, String str3, String str4) {
        GiftEntity giftinfo;
        if (this.topMsgAnimView == null || (giftinfo = getGiftinfo(str3)) == null) {
            return;
        }
        if (!TextUtils.isEmpty(giftinfo.getImgFileFloat())) {
            this.topMsgAnimView.startAnimMsgGift(str, str2, giftinfo.getImgFileFloat());
        }
        LandscapeGiftMsgAnimView landscapeGiftMsgAnimView = this.lGiftMsgAnimView;
        if (landscapeGiftMsgAnimView != null) {
            landscapeGiftMsgAnimView.startAnimHdGift(str, str2, giftinfo.getImgFileFloat(), str4, str3);
        }
    }

    private GiftEntity getGiftinfo(String str) {
        try {
            for (GiftEntity giftEntity : CacheDataCenter.getInstance().getGiftList()) {
                if (giftEntity.getGiftCode().equals(str)) {
                    return giftEntity;
                }
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void stopMsgGiftAnim() {
        TopMsgAnimView topMsgAnimView = this.topMsgAnimView;
        if (topMsgAnimView != null) {
            topMsgAnimView.stopAnimMsgGift();
            LandscapeGiftMsgAnimView landscapeGiftMsgAnimView = this.lGiftMsgAnimView;
            if (landscapeGiftMsgAnimView != null) {
                landscapeGiftMsgAnimView.stopAnimHdGift();
            }
        }
    }

    private void cleanAnimation() {
        try {
            stopBrowseGoodAnim();
            stopJoinOrFocusAnim();
            stopMsgGiftAnim();
            stopOrderAnim();
            this.bubbleLayout.removeAllViews();
            if (this.ds1 != null) {
                this.ds1.dispose();
            }
            this.bubbleView = null;
            if (this.rlShow != null) {
                this.rlShow.setVisibility(8);
            }
            if (this.popupWindow != null) {
                this.popupWindow.dismiss();
            }
            AudienceGoodsListDialogNew.getInstance().releasePw();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGuanzuGone(boolean z) {
        try {
            this.tvAttentionAnchorBtn.setVisibility(z ? 8 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ObservableSubscribeProxy<Boolean> addPriser(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        return (ObservableSubscribeProxy) App.getAsyncHttpClient().rxGet(URLSet.addpraiseUser(str, str2), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$L60xPsL-TrOPg77ZeoC3f0hc5Tw
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return AudienceLandscapeView.lambda$addPriser$24((String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ Boolean lambda$addPriser$24(String str) throws Exception {
        return true;
    }

    public void setSwitchTextView(SharpnessEntity.LiveUrlBean liveUrlBean) {
        try {
            this.tvDefinitionBtn.setText(liveUrlBean.getSharpnessName());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setLiveUrlList(List<SharpnessEntity.LiveUrlBean> list) {
        try {
            this.liveUrlList = list;
            this.tvDefinitionBtn.setVisibility((list == null || list.size() <= 1) ? 8 : 0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initViewpager(ViewPager viewPager, List<GiftEntity> list, AppCompatActivity appCompatActivity, AdapterView.OnItemClickListener onItemClickListener) {
        try {
            new ArrayList();
            this.vpAdapter = new AdapterViewpager(createList(list, 8), appCompatActivity, onItemClickListener);
            viewPager.setAdapter(this.vpAdapter);
            if (list.size() > 8) {
                this.tips.setVisibility(0);
            } else {
                this.tips.setVisibility(4);
            }
            viewPager.setCurrentItem(0);
            viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.AudienceLandscapeView.5
                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrollStateChanged(int i) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageScrolled(int i, float f, int i2) {
                }

                @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
                public void onPageSelected(int i) {
                    NBSActionInstrumentation.onPageSelectedEnter(i, this);
                    AudienceLandscapeView.this.tips.setImageResource(i == 0 ? 2131230903 : 2131230904);
                    NBSActionInstrumentation.onPageSelectedExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static List<List<GiftEntity>> createList(List<GiftEntity> list, int i) {
        int i2;
        ArrayList arrayList = new ArrayList();
        try {
            int size = list.size() % i == 0 ? list.size() / i : (list.size() / i) + 1;
            int i3 = 0;
            while (i3 < size) {
                ArrayList arrayList2 = new ArrayList();
                int i4 = i3 * i;
                while (true) {
                    i2 = i3 + 1;
                    if (i4 > (i * i2) - 1) {
                        break;
                    }
                    if (i4 <= list.size() - 1) {
                        arrayList2.add(list.get(i4));
                    }
                    i4++;
                }
                arrayList.add(arrayList2);
                i3 = i2;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class GiftAdapter extends BaseAdapter {
        private AppCompatActivity activityContext;
        private LayoutInflater inflater;
        public List<GiftEntity> list;

        @Override // android.widget.Adapter
        public Object getItem(int i) {
            return null;
        }

        @Override // android.widget.Adapter
        public long getItemId(int i) {
            return 0L;
        }

        public GiftAdapter(List<GiftEntity> list, AppCompatActivity appCompatActivity) {
            this.list = list;
            this.inflater = LayoutInflater.from(appCompatActivity);
            this.activityContext = appCompatActivity;
        }

        public void update(List<GiftEntity> list) {
            this.list = list;
            notifyDataSetChanged();
        }

        @Override // android.widget.Adapter
        public int getCount() {
            return this.list.size();
        }

        @Override // android.widget.Adapter
        public View getView(int i, View view, ViewGroup viewGroup) {
            GiftEntity giftEntity = this.list.get(i);
            View inflate = this.inflater.inflate(2131492989, viewGroup, false);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131296429);
            TextView textView = (TextView) inflate.findViewById(2131296430);
            GlideApp.with((FragmentActivity) this.activityContext).load(giftEntity.getGiftImgSrc()).into((ImageView) inflate.findViewById(2131296428));
            ((TextView) inflate.findViewById(2131296431)).setText(giftEntity.getGiftName());
            if (giftEntity.isXianhua()) {
                textView.setText(giftEntity.getGiftNum() + "");
                textView.setBackgroundResource(2131230905);
                textView.setTextColor(-1);
            } else {
                textView.setText(giftEntity.getGiftPrice() + "积分");
                textView.setBackgroundResource(0);
                textView.setTextColor(-6710887);
            }
            if (giftEntity.isSelected()) {
                linearLayout.setBackgroundResource(2131230902);
            } else {
                linearLayout.setBackgroundResource(0);
            }
            return inflate;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AdapterViewpager extends PagerAdapter {
        private AppCompatActivity activityContext;
        private List<GiftAdapter> adapterList = new ArrayList();
        private AdapterView.OnItemClickListener listener;
        private List<List<GiftEntity>> pagerList;

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
            return view == obj;
        }

        public AdapterViewpager(List<List<GiftEntity>> list, AppCompatActivity appCompatActivity, AdapterView.OnItemClickListener onItemClickListener) {
            this.pagerList = list;
            this.activityContext = appCompatActivity;
            this.listener = onItemClickListener;
        }

        public List<List<GiftEntity>> getPagerList() {
            return this.pagerList;
        }

        public int getItemAdapterCount() {
            return this.adapterList.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return this.pagerList.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public Object instantiateItem(@NonNull ViewGroup viewGroup, int i) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(2131492976, (ViewGroup) null);
            GridView gridView = (GridView) inflate.findViewById(2131296405);
            GiftAdapter giftAdapter = new GiftAdapter(this.pagerList.get(i), this.activityContext);
            this.adapterList.add(giftAdapter);
            gridView.setAdapter((ListAdapter) giftAdapter);
            gridView.setOnItemClickListener(this.listener);
            viewGroup.addView(inflate);
            return inflate;
        }

        public GiftAdapter getItemAdapter(int i) {
            return this.adapterList.get(i);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(@NonNull ViewGroup viewGroup, int i, @NonNull Object obj) {
            viewGroup.removeView((View) obj);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void giftClick(String str, String str2) {
        LiveMsg liveMsg = new LiveMsg();
        liveMsg.setType("push");
        liveMsg.setmType("6");
        liveMsg.setNick(this.audienceUser.getNick());
        liveMsg.setLevel(this.audienceUser.getLevel());
        liveMsg.setGiftNum(str2);
        liveMsg.setGiftCode(str);
        updateMessageAdapter(liveMsg);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dismiss() {
        try {
            this.llGiftDialog.setVisibility(8);
            this.bottomImage2.setImageResource(0);
            this.textView2.setText("");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showGiftDialog() {
        try {
            this.llGiftDialog.setVisibility(0);
            this.textView3.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDianzanAnime() {
        try {
            if (this.bubbleView == null) {
                this.bubbleView = new AlivcLikeView(this.activityContext);
            }
            if (((ViewGroup) this.bubbleView.getParent()) == null) {
                this.bubbleLayout.addView(this.bubbleView);
            }
            this.bubbleView.onStart();
            if (this.ds1 != null) {
                this.ds1.dispose();
            }
            this.ds1 = Observable.interval(0L, 800L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$AudienceLandscapeView$CvEaaoZrdjqo_I6AEzhzzodI4Ho
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceLandscapeView.lambda$showDianzanAnime$25(AudienceLandscapeView.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showDianzanAnime$25(AudienceLandscapeView audienceLandscapeView, Long l) throws Exception {
        try {
            audienceLandscapeView.bubbleView.addPraise(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setZanNum(String str) {
        try {
            this.tvPopularity.setText(FormatUtils.getShowString(str));
            this.tvPopularity.setTag(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updatePush(ZhuboDataEntity zhuboDataEntity) {
        try {
            if ("0000".equals(zhuboDataEntity.getStatusCode())) {
                this.hasUpdateFans = true;
                ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
                this.tvGetGiftNum.setText(FormatUtils.getShowString(anchorInfoBean.getReceiveGiftNum()));
                this.tvFansNum.setText(FormatUtils.getShowString(anchorInfoBean.getFansNum()));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public View getRoomInfoView() {
        return this.view;
    }

    public void hideGiftUI() {
        try {
            this.ivGiftBtn.setVisibility(8);
            this.llAnimArea.setVisibility(8);
            this.tvCommentBtn.setVisibility(0);
            this.flComment.setVisibility(0);
            this.ivBarrageBtn.setVisibility(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void hideChatRoomAndGiftByNet(LiveRoomUiHideEntity liveRoomUiHideEntity) {
        if (liveRoomUiHideEntity != null) {
            try {
                this.liveUI = liveRoomUiHideEntity;
                if (this.ivGiftBtn != null && "0".equals(liveRoomUiHideEntity.getIsShowGift())) {
                    this.ivGiftBtn.setVisibility(8);
                }
                if (this.tvCommentBtn != null && "0".equals(liveRoomUiHideEntity.getIsShowChat())) {
                    this.tvCommentBtn.setVisibility(8);
                }
                if (this.ivBarrageBtn != null && "0".equals(liveRoomUiHideEntity.getIsShowChat())) {
                    this.ivBarrageBtn.setVisibility(8);
                }
                if (this.flComment != null && "0".equals(liveRoomUiHideEntity.getIsShowChat())) {
                    this.flComment.setVisibility(4);
                }
                if (this.llAnimArea == null || !"0".equals(liveRoomUiHideEntity.getIsShowGift())) {
                    return;
                }
                this.llAnimArea.setVisibility(8);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void showChatRoomAndGiftUI() {
        try {
            this.liveUI = null;
            if (this.ivGiftBtn != null) {
                this.ivGiftBtn.setVisibility(0);
            }
            if (this.tvCommentBtn != null) {
                this.tvCommentBtn.setVisibility(0);
            }
            if (this.ivBarrageBtn != null) {
                this.ivBarrageBtn.setVisibility(0);
            }
            if (this.flComment != null) {
                this.flComment.setVisibility(0);
            }
            if (this.llAnimArea != null) {
                this.llAnimArea.setVisibility(0);
            }
            if (this.ll_hidebottom != null) {
                this.ll_hidebottom.setVisibility(0);
            }
            if (this.layoutMain != null) {
                this.layoutMain.setVisibility(0);
            }
            if (this.isHaveGood && this.goodsCardView != null) {
                this.goodsCardView.setVisibility(0);
            }
            if (this.tvLiveTitle != null) {
                this.tvLiveTitle.setVisibility(TextUtils.isEmpty(this.liveTitle) ? 8 : 0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
