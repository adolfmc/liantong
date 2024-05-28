package com.sinovatech.unicom.separatemodule.audience;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.PagerAdapter;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.CardView;
import android.text.Editable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Chronometer;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.billy.android.swipe.consumer.DrawerConsumer;
import com.bumptech.glide.load.Transformation;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceMessageView;
import com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener;
import com.sinovatech.unicom.separatemodule.audience.entity.ActivityTimeEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.AlivcLiveMessageInfo;
import com.sinovatech.unicom.separatemodule.audience.entity.AudienceUser;
import com.sinovatech.unicom.separatemodule.audience.entity.GiftEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.GoodListEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LianMZBEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveMsg;
import com.sinovatech.unicom.separatemodule.audience.entity.LivePvInfoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.LiveRoomUiHideEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.SharpnessEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ShopEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuangXiuEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity;
import com.sinovatech.unicom.separatemodule.audience.interfaceImpl.VideoProgressVisible;
import com.sinovatech.unicom.separatemodule.audience.playback.AudiencePlayBackActivity;
import com.sinovatech.unicom.separatemodule.audience.util.FormatUtils;
import com.sinovatech.unicom.separatemodule.audience.util.RoomDescBgSpan;
import com.sinovatech.unicom.separatemodule.audience.view.AlivcChatListView;
import com.sinovatech.unicom.separatemodule.audience.view.AlivcLikeView;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGiftDialog;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsDialog;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceGuanzhuDialog;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceInnerRecylerView;
import com.sinovatech.unicom.separatemodule.audience.view.AudienceSwitchDialog;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import com.sinovatech.unicom.separatemodule.audience.view.LiveGiftView;
import com.sinovatech.unicom.separatemodule.audience.view.LivePvResultDialog;
import com.sinovatech.unicom.separatemodule.audience.view.animview.CountDownView;
import com.sinovatech.unicom.separatemodule.audience.view.animview.WelcomFloatAnimView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import group.pals.android.lib.p392ui.lockpattern.util.ShakeUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import java.util.concurrent.TimeUnit;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.json.JSONException;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class AudienceMessageView extends FrameLayout implements VideoProgressVisible {
    private static final String TAG = "AudienceMessageView";
    private AppCompatActivity activityContext;
    private Disposable adAnimationTimer;
    private ImageView adCloseImage1;
    private ImageView adCloseImage2;
    private ImageView adCloseImage3;
    private ImageView adImage1;
    private ImageView adImage2;
    private ImageView adImage3;
    private RelativeLayout adLayout1;
    private RelativeLayout adLayout2;
    private RelativeLayout adLayout3;
    private ImageView adverseResult;
    AlivcLikeView alivcLikeView;
    private AudienceUser audienceUser;
    private View bottomInputLayout;
    private LinearLayout bottomLayout;
    FrameLayout bubbleLayout;
    AlivcLikeView bubbleView;
    TextView cityView;
    private View.OnClickListener clickListener;
    private View.OnClickListener clickScreenListener;
    ImageView closeImage;
    private Chronometer cmLivePk;
    AlivcChatListView commentListView;
    private DrawerConsumer consumer;
    CountDownView countDownView;
    private ZhuboDataEntity.AnchorInfoBean currentAnchor;
    private Animation dianzanAnimation;
    int dianzanCount;
    private ImageView dianzanImageView;
    private Disposable ds1;
    private Disposable explainTimer;
    TextView fansView;
    TextView gaunzhuView;
    LiveGiftView giftAnimeView;
    TextView giftNumView;
    private ImageView giftTextView;
    private RelativeLayout goodListLayout;
    private TextView goodListText;
    CardView goodsCardView;
    TextView goodsContent;
    ImageView goodsImage;
    private OnDialogBtnClickListener goodsItemClickListener;
    LinearLayout goodsLayout;
    private ImageView goodsListBtn;
    TextView goodsOriPrice;
    TextView goodsPrice;
    TextView goodsTilte;
    View goodsViewDown;
    LinearLayout headerLayout;
    FrameLayout huodongFrameLayout;
    private View interAnchorBtn;
    private CircularImage interAnchorIcon;
    private LinearLayout interAnchorInfo;
    private TextView interAnchorName;
    private boolean isHideScreen;
    private boolean isMultiView;
    private boolean isStartTrackingTouch;
    private ImageView ivLogo;
    private ImageView ivPlayBtn;
    private LikeView likeView;
    private LikeView likeView2;
    private LiveRoomUiHideEntity liveRoomUIInfo;
    private String liveTitle;
    private String liveType;
    private List<SharpnessEntity.LiveUrlBean> liveUrlList;
    private View llPkResult;
    private View llProgressBar;
    private ManagerAudienceLoadData managerLoadData;
    RelativeLayout messageRootlayout;
    private View messageView1;
    private View messageView2;
    private LinearLayout mianliuLayout;
    TextView morezhiboView;
    private View multiToggle;
    private ImageView myResult;
    TextView nameView;
    private IPage1Click page1Click;
    ImageView photoView;
    private Disposable pkViewGoneTimer;
    TextView renqiView;
    private RelativeLayout rlPkProgress;
    private LinearLayout rlaudiencemorezhibo;
    private SeekBar seekBar;
    private ImageView shareTextView;
    private ImageView showIcon;
    private TextView showTextView;
    private TextView subsWatchTime;
    Disposable subscribe;
    private View subscribeWatchLayout;
    private TextView switchTextView;
    private ImageView tiezhi1;
    private ImageView tiezhi2;
    private ImageView tiezhi3;
    private ImageView toggle;
    private TextView tvBegin;
    private TextView tvEnd;
    private TextView tvExplainNotice;
    private TextView tvLiveTitle;
    private TextView tvMe;
    private View tvSubWatchBtn;
    private TextView tvSubWatchStatus;
    private TextView tvYou;
    private Disposable updateSeek;
    View vTop20;
    private String videoId;
    private View view;
    private AdapterViewpager viewpagerAdapter;
    private ImageView zhibogif;

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CallBack {
        void callBack(String str);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IPage1Click {
        void clickGoods(ShopEntity.DataBean.GoodsBean goodsBean);

        void clickShoppingCart();

        boolean isNeedPay();

        void showKeyboard();

        void showZanAnim();

        void switchClick(SharpnessEntity.LiveUrlBean liveUrlBean);

        void toggle();

        void userInfo();

        void zanNum(String str);
    }

    private void initGiftView() {
    }

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: CAST (r12 I:int) = (int) (r8 I:float), expected to be less than 6
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    private void initView() {
        /*
            Method dump skipped, instructions count: 1089
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.initView():void");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clickZan$2(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$clickZan$3(Boolean bool) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setShareData$34(String str) throws Exception {
    }

    public void setConsumer(DrawerConsumer drawerConsumer) {
        this.consumer = drawerConsumer;
        LinearLayout linearLayout = this.rlaudiencemorezhibo;
        if (linearLayout != null) {
            linearLayout.setVisibility(0);
        }
    }

    public AudienceMessageView(@NonNull Context context) {
        super(context);
        this.dianzanCount = 0;
        this.liveType = "1";
        this.activityContext = (AppCompatActivity) context;
        initView();
    }

    public AudienceMessageView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.dianzanCount = 0;
        this.liveType = "1";
        this.activityContext = (AppCompatActivity) context;
        initView();
        this.goodsItemClickListener = new OnDialogBtnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.1
            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickGoodsLook(GoodListEntity goodListEntity) {
                if (AudienceMessageView.this.activityContext instanceof AudienceActivity) {
                    AudienceActivity.IntentGo(AudienceMessageView.this.activityContext, goodListEntity.getGoodsUrl(), "0", goodListEntity.getId(), 4000);
                } else {
                    IntentManager.gotoWebViewActivity(AudienceMessageView.this.activityContext, goodListEntity.getGoodsUrl(), goodListEntity.getName());
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.audience.adpter.OnDialogBtnClickListener
            public void onClickOrder() {
                IntentManager.gotoWebViewActivity(AudienceMessageView.this.activityContext, URLSet.liveOrderMenu(), "我的订单");
            }
        };
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C83103 implements TextWatcher {
        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
        }

        C83103() {
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable editable) {
            if (AudienceMessageView.this.page1Click == null || AudienceMessageView.this.renqiView == null || AudienceMessageView.this.renqiView.getTag() == null) {
                return;
            }
            AudienceMessageView.this.page1Click.zanNum(AudienceMessageView.this.renqiView.getTag().toString());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BDCloudVideoView getPlayer() {
        AppCompatActivity appCompatActivity = this.activityContext;
        if (appCompatActivity instanceof AudiencePlayBackActivity) {
            return ((AudiencePlayBackActivity) appCompatActivity).bdCloudVideoView;
        }
        AudienceActivity audienceActivity = (AudienceActivity) appCompatActivity;
        return AudienceActivity.bdCloudVideoView;
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
            this.ivPlayBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$1PXAHX524HnFVCa0LLevYDZE6vo
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceMessageView.lambda$initProgressBar$0(AudienceMessageView.this, view);
                }
            });
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$initProgressBar$0(AudienceMessageView audienceMessageView, View view) {
        View.OnClickListener onClickListener = audienceMessageView.clickListener;
        if (onClickListener != null) {
            onClickListener.onClick(null);
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

    private void setProgressBarMax() {
        int duration = getPlayer().getDuration();
        this.seekBar.setMax(duration);
        this.tvEnd.setText(NumUtils.stringForTime(duration));
        this.seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.4
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (AudienceMessageView.this.tvBegin != null) {
                    AudienceMessageView.this.tvBegin.setText(NumUtils.stringForTime(i));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                AudienceMessageView.this.isStartTrackingTouch = true;
                AudienceMessageView.this.seekBar.setProgressDrawableTiled(AudienceMessageView.this.getResources().getDrawable(2131232586));
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Tracker.onStopTrackingTouch(seekBar);
                AudienceMessageView.this.isStartTrackingTouch = false;
                AudienceMessageView.this.seekBar.setProgressDrawableTiled(AudienceMessageView.this.getResources().getDrawable(2131232576));
                try {
                    if (AudienceMessageView.this.getPlayer() == null || AudienceMessageView.this.getPlayer().getDuration() <= 0) {
                        return;
                    }
                    AudienceMessageView.this.getPlayer().seekTo(AudienceMessageView.this.seekBar.getProgress());
                    AudienceMessageView.this.seekToPlay(AudienceMessageView.this.seekBar.getProgress());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void seekToPlay(int i) {
        AppCompatActivity appCompatActivity = this.activityContext;
        if (appCompatActivity instanceof AudiencePlayBackActivity) {
            ((AudiencePlayBackActivity) appCompatActivity).seekToPlay(i);
        } else if (appCompatActivity instanceof AudienceActivity) {
            ((AudienceActivity) appCompatActivity).seekToPlay(i);
        }
    }

    private void startPlayerProgressUpdate() {
        stopUpdateProgress();
        this.updateSeek = Observable.interval(800L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$U8r3QUkHN5N3j1FruTlCfmyl37o
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMessageView.lambda$startPlayerProgressUpdate$1(AudienceMessageView.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$startPlayerProgressUpdate$1(AudienceMessageView audienceMessageView, Long l) throws Exception {
        if (audienceMessageView.isMultiView) {
            audienceMessageView.updateSeek.dispose();
            audienceMessageView.updateSeek = null;
        }
        if (!audienceMessageView.getPlayer().isPlaying() || audienceMessageView.isMultiView) {
            return;
        }
        SeekBar seekBar = audienceMessageView.seekBar;
        if (seekBar != null && seekBar.getMax() > 0 && audienceMessageView.llProgressBar.getVisibility() == 0) {
            if (audienceMessageView.isStartTrackingTouch) {
                return;
            }
            audienceMessageView.seekBar.setProgress(audienceMessageView.getPlayer().getCurrentPosition());
            return;
        }
        audienceMessageView.setProgressBarMax();
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
            this.likeView = (LikeView) this.messageView1.findViewById(2131297988);
            if (this.likeView != null) {
                this.likeView.removeAllViews();
                this.likeView.setListener(new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.5
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                    public void onClick(long j) {
                        if (!"3".equals(AudienceMessageView.this.liveType) || AudienceMessageView.this.clickScreenListener == null) {
                            return;
                        }
                        AudienceMessageView.this.clickScreenListener.onClick(null);
                    }

                    @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                    public void doubleTapCallback() {
                        try {
                            AudienceMessageView.this.clickZan(AudienceMessageView.this.currentAnchor.getUserId());
                        } catch (NumberFormatException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
            if (this.messageView2 != null) {
                this.likeView = (LikeView) this.messageView2.findViewById(2131297988);
                if (this.likeView != null) {
                    this.likeView.removeAllViews();
                    this.likeView.setListener(new LikeView.TouchCallBack() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.6
                        @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                        public void onClick(long j) {
                        }

                        @Override // com.sinovatech.unicom.separatemodule.audience.view.LikeView.TouchCallBack
                        public void doubleTapCallback() {
                            try {
                                AudienceMessageView.this.clickZan(AudienceMessageView.this.currentAnchor.getUserId());
                            } catch (NumberFormatException e) {
                                e.printStackTrace();
                            }
                        }
                    });
                }
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickZan(final String str) {
        try {
            this.dianzanImageView.startAnimation(this.dianzanAnimation);
            this.bubbleView.addPraise(1);
            int parseInt = Integer.parseInt(this.renqiView.getTag().toString()) + 1;
            this.renqiView.setText(FormatUtils.getShowString(parseInt));
            this.renqiView.setTag(parseInt + "");
            ShakeUtils.vibrate(this.activityContext, 100L);
            if (this.page1Click != null) {
                this.page1Click.showZanAnim();
            }
        } catch (Exception e) {
            e.printStackTrace();
            this.renqiView.setText("1");
            this.renqiView.setTag("1");
        }
        if (!TextUtils.isEmpty(this.videoId)) {
            this.managerLoadData.videoPraise(this.videoId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$HMlb0J_pXm90NI09P3EnbEzrCqc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceMessageView.lambda$clickZan$2((String) obj);
                }
            });
            return;
        }
        this.dianzanCount++;
        Disposable disposable = this.subscribe;
        if (disposable != null) {
            disposable.dispose();
        }
        this.subscribe = Observable.timer(500L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$s2RrKSD9v9s8nXr5p0tUQYu-sYk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMessageView.lambda$clickZan$4(AudienceMessageView.this, str, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$clickZan$4(AudienceMessageView audienceMessageView, String str, Long l) throws Exception {
        ManagerAudienceLoadData managerAudienceLoadData = audienceMessageView.managerLoadData;
        managerAudienceLoadData.addPriser(str, audienceMessageView.dianzanCount + "", audienceMessageView.isMultiView ? "2" : "").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$bcXu_mrsfLwq6MSlx1sAc9zO238
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMessageView.lambda$clickZan$3((Boolean) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        audienceMessageView.dianzanCount = 0;
    }

    public void setBubbleVisibility(boolean z) {
        this.bubbleLayout.setVisibility(z ? 0 : 8);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    private void adaptiveHW(String str) {
        char c;
        switch (str.hashCode()) {
            case -1643327438:
                if (str.equals("WLZ-AL10")) {
                    c = 5;
                    break;
                }
                c = 65535;
                break;
            case -1643325547:
                if (str.equals("WLZ-AN00")) {
                    c = 6;
                    break;
                }
                c = 65535;
                break;
            case -1213379779:
                if (str.equals("OXF-AN00")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case -1213379748:
                if (str.equals("OXF-AN10")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case -927088269:
                if (str.equals("OXP-AN00")) {
                    c = 4;
                    break;
                }
                c = 65535;
                break;
            case -676647774:
                if (str.equals("JER-AN10")) {
                    c = 7;
                    break;
                }
                c = 65535;
                break;
            case -271168666:
                if (str.equals("ANA-AN00")) {
                    c = '\n';
                    break;
                }
                c = 65535;
                break;
            case 516770704:
                if (str.equals("EBG-AN00")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 516770735:
                if (str.equals("EBG-AN10")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            case 1145422734:
                if (str.equals("ELS-AN00")) {
                    c = 11;
                    break;
                }
                c = 65535;
                break;
            case 1145422765:
                if (str.equals("ELS-AN10")) {
                    c = '\t';
                    break;
                }
                c = 65535;
                break;
            case 1998436947:
                if (str.equals("NOH-AN00")) {
                    c = '\b';
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
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
            case 7:
            case '\b':
            case '\t':
            case '\n':
            case 11:
                this.vTop20.setVisibility(0);
                return;
            default:
                this.vTop20.setVisibility(8);
                return;
        }
    }

    private String getTuoMinNumber(String str) {
        if (str == null || str.trim().length() != 11) {
            return "";
        }
        return str.substring(0, 3) + "****" + str.substring(7, 11);
    }

    private boolean isNotPlayBack() {
        AppCompatActivity appCompatActivity = this.activityContext;
        if (appCompatActivity instanceof AudiencePlayBackActivity) {
            return !((AudiencePlayBackActivity) appCompatActivity).isPB;
        }
        if (appCompatActivity instanceof AudienceActivity) {
            return !((AudienceActivity) appCompatActivity).isPB;
        }
        return true;
    }

    public void initData(final ZhuboDataEntity zhuboDataEntity, final ManagerAudienceLoadData managerAudienceLoadData, final AudienceUser audienceUser, final IPage1Click iPage1Click) {
        this.clickListener = null;
        this.page1Click = iPage1Click;
        this.audienceUser = audienceUser;
        AudienceUser audienceUser2 = this.audienceUser;
        if (audienceUser2 != null && TextUtils.isEmpty(audienceUser2.getNick())) {
            this.audienceUser.setNick(getTuoMinNumber(UserManager.getInstance().getCurrentPhoneNumber()));
        }
        final ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
        this.managerLoadData = managerAudienceLoadData;
        this.currentAnchor = zhuboDataEntity.getAnchorInfoBean();
        this.tvLiveTitle.setVisibility(8);
        GlideApp.with((FragmentActivity) this.activityContext).load(anchorInfoBean.getUserImg()).placeholder(2131231806).error(2131231806).into(this.photoView);
        this.nameView.setText(anchorInfoBean.getUserName());
        this.cityView.setText(anchorInfoBean.getUserCityName());
        this.giftNumView.setText(anchorInfoBean.getReceiveGiftNum());
        this.fansView.setText(anchorInfoBean.getFansNum());
        this.rlaudiencemorezhibo.setVisibility(zhuboDataEntity.getAnchorInfoBean().getDataType().equals("3") ? 8 : 0);
        if (this.consumer == null) {
            this.rlaudiencemorezhibo.setVisibility(8);
        }
        showDianzanAnime();
        stopUpdateProgress();
        this.headerLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$17Vs6lExU1jpNr8JLXuh5OgFQ-0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.IPage1Click.this.userInfo();
            }
        });
        this.switchTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$yRO0MTmd1RtGFqj1jJ1JZnYAgYg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$7(AudienceMessageView.this, iPage1Click, view);
            }
        });
        this.dianzanImageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$PLEL8s3zzmRNhK385IVJlu4JDD4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$8(AudienceMessageView.this, iPage1Click, anchorInfoBean, view);
            }
        });
        this.closeImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Fnf8b9osqSzd0qewyikj-5pyKys
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$9(AudienceMessageView.this, view);
            }
        });
        this.giftTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qw0Wgg3pbdNbEACI8P0tPHLptpE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$11(AudienceMessageView.this, iPage1Click, managerAudienceLoadData, anchorInfoBean, audienceUser, zhuboDataEntity, view);
            }
        });
        this.showTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$3SHdnJ09bmotJt4cN1UIq1FBa84
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$12(AudienceMessageView.IPage1Click.this, view);
            }
        });
        this.gaunzhuView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$55MEB1te82YCByr3TRm-QFjSwr4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$14(AudienceMessageView.this, managerAudienceLoadData, anchorInfoBean, view);
            }
        });
        this.toggle.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$zI-nZ8SDi4Si04MERJegof3pz7A
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.IPage1Click.this.toggle();
            }
        });
        this.multiToggle.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$U3QCAt6ediv-R0dd_pjCtSMzeV8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.IPage1Click.this.toggle();
            }
        });
        this.rlaudiencemorezhibo.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$NYk-BMRdho6N0FFSu3q1fdt4dZo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$initData$17(AudienceMessageView.this, anchorInfoBean, view);
            }
        });
        this.mianliuLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$lXKhNUcbZbxqjydoqn5N3YjW2DQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UIUtils.toastCenter("联通号码免流播放");
            }
        });
        checkWifi();
        this.tvSubWatchBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$CD02k7e7j8VG9FSSnYvb3uAsM44
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                managerAudienceLoadData.reserveLivePv("1", zhuboDataEntity.getAnchorInfoBean().getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$f0JmvJxdh1l1jAN4VDVYAr3nduo
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        AudienceMessageView.lambda$initData$19(AudienceMessageView.this, (LivePvInfoEntity) obj);
                    }
                });
            }
        });
        if (this.liveRoomUIInfo != null && zhuboDataEntity.getAnchorInfoBean().getUserId().equals(this.liveRoomUIInfo.getRoomId())) {
            hideChatRoomAndGiftByNet(this.liveRoomUIInfo);
        } else {
            this.liveRoomUIInfo = null;
        }
        MsLogUtil.m7979d(TAG, "initData执行完毕");
    }

    public static /* synthetic */ void lambda$initData$7(final AudienceMessageView audienceMessageView, final IPage1Click iPage1Click, View view) {
        List<SharpnessEntity.LiveUrlBean> list;
        if ((iPage1Click.isNeedPay() && audienceMessageView.isNotPlayBack()) || (list = audienceMessageView.liveUrlList) == null || list.size() <= 1) {
            return;
        }
        AudienceSwitchDialog.show(audienceMessageView.activityContext, audienceMessageView.liveUrlList, false, new AudienceSwitchDialog.SwitchDialogInterface() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$i0dWvyJAn7zgvGuHnnOkKNDhnW4
            @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceSwitchDialog.SwitchDialogInterface
            public final void onClick(SharpnessEntity.LiveUrlBean liveUrlBean) {
                AudienceMessageView.lambda$initData$6(AudienceMessageView.this, iPage1Click, liveUrlBean);
            }
        });
    }

    public static /* synthetic */ void lambda$initData$6(AudienceMessageView audienceMessageView, IPage1Click iPage1Click, SharpnessEntity.LiveUrlBean liveUrlBean) {
        App.getSharePreferenceUtil().putString("gzdqingxidu", liveUrlBean.getSharpnessLevel());
        iPage1Click.switchClick(liveUrlBean);
        audienceMessageView.setSwitchTextView(liveUrlBean);
    }

    public static /* synthetic */ void lambda$initData$8(AudienceMessageView audienceMessageView, IPage1Click iPage1Click, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, View view) {
        if (iPage1Click.isNeedPay()) {
            return;
        }
        audienceMessageView.clickZan(anchorInfoBean.getUserId());
    }

    public static /* synthetic */ void lambda$initData$9(AudienceMessageView audienceMessageView, View view) {
        if (AudienceInnerRecylerView.isInnerRecylerView) {
            audienceMessageView.releaseHuodongView();
            AudienceInnerRecylerView.isInnerRecylerView = false;
            return;
        }
        audienceMessageView.releaseHuodongView();
        audienceMessageView.activityContext.onBackPressed();
    }

    public static /* synthetic */ void lambda$initData$11(final AudienceMessageView audienceMessageView, IPage1Click iPage1Click, final ManagerAudienceLoadData managerAudienceLoadData, final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final AudienceUser audienceUser, final ZhuboDataEntity zhuboDataEntity, View view) {
        if (iPage1Click.isNeedPay() && audienceMessageView.isNotPlayBack()) {
            return;
        }
        List<GiftEntity> giftList = CacheDataCenter.getInstance().getGiftList();
        if (giftList.size() == 0) {
            managerAudienceLoadData.getGiftListNew().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$57YwAlJYvyuYLAGbP6WDbj8tBDM
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceMessageView.lambda$initData$10(AudienceMessageView.this, anchorInfoBean, managerAudienceLoadData, audienceUser, zhuboDataEntity, (List) obj);
                }
            }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
            return;
        }
        UIUtils.logD("lln", "使用缓存数据--获取礼物列表");
        AudienceGiftDialog.show(audienceMessageView.activityContext, anchorInfoBean, giftList, managerAudienceLoadData, new AudienceGiftDialog.GiftClick() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.8
            @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceGiftDialog.GiftClick
            public void onClick(String str, String str2) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setType("push");
                liveMsg.setmType("6");
                liveMsg.setNick(audienceUser.getNick());
                liveMsg.setLevel(audienceUser.getLevel());
                liveMsg.setGiftNum(str2);
                liveMsg.setGiftCode(str);
                AudienceMessageView.this.updateMessageAdapter(liveMsg);
                String livePullUrl = zhuboDataEntity.getAnchorInfoBean().getLivePullUrl();
                String userName = zhuboDataEntity.getAnchorInfoBean().getUserName();
                PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, "礼物-" + str, "直播详情页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
            }
        });
    }

    public static /* synthetic */ void lambda$initData$10(AudienceMessageView audienceMessageView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, ManagerAudienceLoadData managerAudienceLoadData, final AudienceUser audienceUser, final ZhuboDataEntity zhuboDataEntity, List list) throws Exception {
        UIUtils.logD("lln", "请求网络数据--获取礼物列表");
        AudienceGiftDialog.show(audienceMessageView.activityContext, anchorInfoBean, list, managerAudienceLoadData, new AudienceGiftDialog.GiftClick() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.7
            @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceGiftDialog.GiftClick
            public void onClick(String str, String str2) {
                LiveMsg liveMsg = new LiveMsg();
                liveMsg.setType("push");
                liveMsg.setmType("6");
                liveMsg.setNick(audienceUser.getNick());
                liveMsg.setLevel(audienceUser.getLevel());
                liveMsg.setGiftNum(str2);
                liveMsg.setGiftCode(str);
                AudienceMessageView.this.updateMessageAdapter(liveMsg);
                String livePullUrl = zhuboDataEntity.getAnchorInfoBean().getLivePullUrl();
                String userName = zhuboDataEntity.getAnchorInfoBean().getUserName();
                PvCurrencyLogUtils.pvLogLive(livePullUrl, 2, userName, "礼物-" + str, "直播详情页", zhuboDataEntity.getAnchorInfoBean().getLiveTitle(), "直播详情页");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$12(IPage1Click iPage1Click, View view) {
        if (iPage1Click.isNeedPay()) {
            return;
        }
        iPage1Click.showKeyboard();
        AudienceGuanzhuDialog.cancelGuanzhusub();
    }

    public static /* synthetic */ void lambda$initData$14(final AudienceMessageView audienceMessageView, ManagerAudienceLoadData managerAudienceLoadData, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, View view) {
        managerAudienceLoadData.guanzhu(anchorInfoBean.getUserId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$4LQKg-hUC3NiZYXzNjRtvTMZd_8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMessageView.lambda$initData$13(AudienceMessageView.this, (String) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
        PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "关注", "直播详情页", anchorInfoBean.getLiveTitle(), "直播详情页");
    }

    public static /* synthetic */ void lambda$initData$13(AudienceMessageView audienceMessageView, String str) throws Exception {
        if ("0000".equals(str)) {
            UIUtils.toastCenter("关注成功");
            audienceMessageView.gaunzhuView.setVisibility(8);
            audienceMessageView.countDownView.setAttention(true);
            try {
                String charSequence = audienceMessageView.fansView.getText().toString();
                if (!TextUtils.isEmpty(charSequence)) {
                    int parseInt = Integer.parseInt(charSequence);
                    TextView textView = audienceMessageView.fansView;
                    textView.setText((parseInt + 1) + "");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            CountDownView countDownView = audienceMessageView.countDownView;
            if (countDownView != null && countDownView.isDialogHD()) {
                audienceMessageView.countDownView.goGZchoujiang();
            }
            AudienceGuanzhuDialog.cancelGuanzhusub();
            return;
        }
        UIUtils.toastCenter(str);
    }

    public static /* synthetic */ void lambda$initData$17(AudienceMessageView audienceMessageView, ZhuboDataEntity.AnchorInfoBean anchorInfoBean, View view) {
        DrawerConsumer drawerConsumer = audienceMessageView.consumer;
        if (drawerConsumer != null && drawerConsumer.isClosed()) {
            audienceMessageView.consumer.smoothRightOpen();
        }
        PvCurrencyLogUtils.pvLogLive(anchorInfoBean.getLivePullUrl(), 2, anchorInfoBean.getUserName(), "更多直播", "直播详情页", anchorInfoBean.getLiveTitle(), "直播详情页");
    }

    public static /* synthetic */ void lambda$initData$19(AudienceMessageView audienceMessageView, LivePvInfoEntity livePvInfoEntity) throws Exception {
        if ("0000".equals(livePvInfoEntity.getStatusCode())) {
            LivePvResultDialog livePvResultDialog = new LivePvResultDialog(audienceMessageView.activityContext, audienceMessageView.subsWatchTime.getText().toString().replace(" 开播", ""));
            audienceMessageView.tvSubWatchBtn.setVisibility(8);
            audienceMessageView.tvSubWatchStatus.setVisibility(0);
            livePvResultDialog.show();
            return;
        }
        audienceMessageView.tvSubWatchBtn.setVisibility(0);
        audienceMessageView.tvSubWatchStatus.setVisibility(8);
        ToastUtil.showToast("出了一点儿小问题，请稍后再试");
    }

    public void updateProgress() {
        SeekBar seekBar = this.seekBar;
        if (seekBar == null || seekBar.getMax() <= 0 || this.llProgressBar.getVisibility() != 0 || this.isStartTrackingTouch) {
            return;
        }
        this.seekBar.setProgress(getPlayer().getCurrentPosition());
    }

    public void checkWifi() {
        if ("Wifi".equals(DeviceHelper.getNETType(this.activityContext))) {
            this.mianliuLayout.setVisibility(8);
        } else {
            this.mianliuLayout.setVisibility(0);
        }
    }

    public void setLiveUrlList(List<SharpnessEntity.LiveUrlBean> list) {
        this.liveUrlList = list;
    }

    public void setSwitchTextView(SharpnessEntity.LiveUrlBean liveUrlBean) {
        this.switchTextView.setText(liveUrlBean.getSharpnessName());
    }

    public void showToggleView(boolean z, String str) {
        if (z) {
            this.toggle.setVisibility(this.isMultiView ? 8 : 0);
            this.multiToggle.setVisibility(!this.isMultiView ? 8 : 0);
            if (UIUtils.isFoldScreen(this.activityContext)) {
                this.multiToggle.setVisibility(8);
            }
            this.ivLogo.setVisibility(TextUtils.isEmpty(str) ? 8 : 0);
            if (this.interAnchorInfo.getVisibility() == 0) {
                this.toggle.setVisibility(8);
                this.multiToggle.setVisibility(8);
                return;
            }
            return;
        }
        this.toggle.setVisibility(8);
        this.multiToggle.setVisibility(this.isMultiView ? 0 : 8);
        this.ivLogo.setVisibility(8);
    }

    public void setGuanzhuView(boolean z, ZhuboDataEntity zhuboDataEntity) {
        if (!z) {
            this.gaunzhuView.setVisibility(this.isMultiView ? 8 : 0);
            CountDownView countDownView = this.countDownView;
            if (countDownView != null) {
                countDownView.setAttention(false);
            }
            if (zhuboDataEntity == null || this.isMultiView) {
                return;
            }
            AudienceGuanzhuDialog.show(this.activityContext, zhuboDataEntity, new AudienceGuanzhuDialog.GuanzhuClick() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$EAL2zSYPU6nKhTi9XLYzC1tHCtM
                @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceGuanzhuDialog.GuanzhuClick
                public final void onClick() {
                    AudienceMessageView.lambda$setGuanzhuView$21(AudienceMessageView.this);
                }
            });
            return;
        }
        this.gaunzhuView.setVisibility(8);
        this.countDownView.setAttention(true);
    }

    public static /* synthetic */ void lambda$setGuanzhuView$21(AudienceMessageView audienceMessageView) {
        audienceMessageView.gaunzhuView.performClick();
        AppCompatActivity appCompatActivity = audienceMessageView.activityContext;
        if (appCompatActivity instanceof AudienceActivity) {
            ((AudienceActivity) appCompatActivity).clickGuanzhu();
        }
        AppCompatActivity appCompatActivity2 = audienceMessageView.activityContext;
        if (appCompatActivity2 instanceof AudiencePlayBackActivity) {
            ((AudiencePlayBackActivity) appCompatActivity2).clickGuanzhu();
        }
    }

    public void setRenovation(final ZhuangXiuEntity zhuangXiuEntity) {
        setRenovationImage(this.adImage1, zhuangXiuEntity.getAdRenovation01());
        setRenovationImage(this.adImage2, zhuangXiuEntity.getAdRenovation02());
        if (!TextUtils.isEmpty(zhuangXiuEntity.getAdRenovation01().getImgSrc())) {
            this.adLayout1.setVisibility(0);
            PvCurrencyLogUtils.pvLogLive("", 3, "", "广告1", "直播详情页", "", "直播详情页");
        } else {
            this.adLayout1.setVisibility(4);
        }
        this.adLayout1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$cxnf3Ad4tniUsYAkHMAfCUv9SBA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$setRenovation$22(AudienceMessageView.this, zhuangXiuEntity, view);
            }
        });
        if (!TextUtils.isEmpty(zhuangXiuEntity.getAdRenovation02().getImgSrc())) {
            this.adLayout2.setVisibility(0);
            PvCurrencyLogUtils.pvLogLive("", 3, "", "广告2", "直播详情页", "", "直播详情页");
        } else {
            this.adLayout2.setVisibility(4);
        }
        this.adLayout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$zNKK0Q0dVCnZPkODm997vDGDoNc
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$setRenovation$23(AudienceMessageView.this, zhuangXiuEntity, view);
            }
        });
        if (!TextUtils.isEmpty(zhuangXiuEntity.getAdRenovation03().getImgSrc())) {
            this.adLayout3.setVisibility(0);
            PvCurrencyLogUtils.pvLogLive("", 3, "", "广告3", "直播详情页", "", "直播详情页");
        } else {
            this.adLayout3.setVisibility(4);
        }
        this.adLayout3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$AmGLozAURK2r-TCC9dzKgrT4sD8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$setRenovation$24(AudienceMessageView.this, zhuangXiuEntity, view);
            }
        });
        setRenovationImage(this.adImage3, zhuangXiuEntity.getAdRenovation03());
        setRenovationImage(this.tiezhi1, zhuangXiuEntity.getPasterRenovation01());
        setRenovationImage(this.tiezhi2, zhuangXiuEntity.getPasterRenovation02());
        setRenovationImage(this.tiezhi3, zhuangXiuEntity.getPasterRenovation03());
        this.adCloseImage1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$2WtxnrrtP2Tz3QXlGga0WwWAuIY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.this.adLayout1.setVisibility(4);
            }
        });
        this.adCloseImage2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$onDFCLkEEZVYlX8xWE22F60IQ0U
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.this.adLayout2.setVisibility(4);
            }
        });
        this.adCloseImage3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$2uqHqDxE9DE8a5e8Z6yvnNjlRgA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.this.adLayout3.setVisibility(4);
            }
        });
        if (this.adLayout1.getVisibility() == 0 || this.adLayout2.getVisibility() == 0 || this.adLayout3.getVisibility() == 0) {
            this.adAnimationTimer = Observable.interval(2L, 1L, TimeUnit.SECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<Long>() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.9
                @Override // io.reactivex.functions.Consumer
                public void accept(Long l) throws Exception {
                    if (l.longValue() % 60 == 0) {
                        if (AudienceMessageView.this.adLayout1.getVisibility() == 0) {
                            AudienceMessageView.this.adLayout1.startAnimation(AnimationUtils.loadAnimation(AudienceMessageView.this.activityContext, 2130771986));
                        }
                        if (AudienceMessageView.this.adLayout2.getVisibility() == 0) {
                            AudienceMessageView.this.adLayout2.startAnimation(AnimationUtils.loadAnimation(AudienceMessageView.this.activityContext, 2130771986));
                        }
                        if (AudienceMessageView.this.adLayout3.getVisibility() == 0) {
                            AudienceMessageView.this.adLayout3.startAnimation(AnimationUtils.loadAnimation(AudienceMessageView.this.activityContext, 2130771986));
                            return;
                        }
                        return;
                    }
                    AudienceMessageView.this.adLayout1.clearAnimation();
                    AudienceMessageView.this.adLayout2.clearAnimation();
                    AudienceMessageView.this.adLayout3.clearAnimation();
                }
            });
        } else {
            releaseTimer();
        }
        if (zhuangXiuEntity.getFloatImg01() != null && this.giftAnimeView != null) {
            final ZhuangXiuEntity.Renovation floatImg01 = zhuangXiuEntity.getFloatImg01();
            if (!TextUtils.isEmpty(floatImg01.getImgSrc())) {
                this.giftAnimeView.startFloatAnim(floatImg01.getTitle(), floatImg01.getImgSrc(), new WelcomFloatAnimView.FloatLinkListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$w2g32DQVanpm8EPktWrv2BRSxZw
                    @Override // com.sinovatech.unicom.separatemodule.audience.view.animview.WelcomFloatAnimView.FloatLinkListener
                    public final void clicked() {
                        AudienceMessageView.lambda$setRenovation$28(AudienceMessageView.this, floatImg01);
                    }
                });
            }
        }
        hideAdvertisement();
    }

    public static /* synthetic */ void lambda$setRenovation$22(AudienceMessageView audienceMessageView, ZhuangXiuEntity zhuangXiuEntity, View view) {
        if (TextUtils.isEmpty(zhuangXiuEntity.getAdRenovation01().getLinkUrl())) {
            return;
        }
        AppCompatActivity appCompatActivity = audienceMessageView.activityContext;
        if (appCompatActivity instanceof AudienceActivity) {
            AudienceActivity.IntentGo(appCompatActivity, zhuangXiuEntity.getAdRenovation01().getLinkUrl(), "1", zhuangXiuEntity.getAdRenovation01().getId(), 4000);
        } else {
            IntentManager.gotoWebViewActivity(appCompatActivity, zhuangXiuEntity.getAdRenovation01().getLinkUrl(), zhuangXiuEntity.getAdRenovation01().getTitle());
        }
        PvCurrencyLogUtils.pvLogLive("", 2, "", "广告1", "直播详情页", "", "直播详情页");
    }

    public static /* synthetic */ void lambda$setRenovation$23(AudienceMessageView audienceMessageView, ZhuangXiuEntity zhuangXiuEntity, View view) {
        if (TextUtils.isEmpty(zhuangXiuEntity.getAdRenovation02().getLinkUrl())) {
            return;
        }
        AppCompatActivity appCompatActivity = audienceMessageView.activityContext;
        if (appCompatActivity instanceof AudienceActivity) {
            AudienceActivity.IntentGo(appCompatActivity, zhuangXiuEntity.getAdRenovation02().getLinkUrl(), "1", zhuangXiuEntity.getAdRenovation02().getId(), 4000);
        } else {
            IntentManager.gotoWebViewActivity(appCompatActivity, zhuangXiuEntity.getAdRenovation02().getLinkUrl(), zhuangXiuEntity.getAdRenovation02().getTitle());
        }
        PvCurrencyLogUtils.pvLogLive("", 2, "", "广告2", "直播详情页", "", "直播详情页");
    }

    public static /* synthetic */ void lambda$setRenovation$24(AudienceMessageView audienceMessageView, ZhuangXiuEntity zhuangXiuEntity, View view) {
        if (TextUtils.isEmpty(zhuangXiuEntity.getAdRenovation03().getLinkUrl())) {
            return;
        }
        AppCompatActivity appCompatActivity = audienceMessageView.activityContext;
        if (appCompatActivity instanceof AudienceActivity) {
            AudienceActivity.IntentGo(appCompatActivity, zhuangXiuEntity.getAdRenovation03().getLinkUrl(), "1", zhuangXiuEntity.getAdRenovation03().getId(), 4000);
        } else {
            IntentManager.gotoWebViewActivity(appCompatActivity, zhuangXiuEntity.getAdRenovation03().getLinkUrl(), zhuangXiuEntity.getAdRenovation03().getTitle());
        }
        PvCurrencyLogUtils.pvLogLive("", 2, "", "广告3", "直播详情页", "", "直播详情页");
    }

    public static /* synthetic */ void lambda$setRenovation$28(AudienceMessageView audienceMessageView, ZhuangXiuEntity.Renovation renovation) {
        if (TextUtils.isEmpty(renovation.getLinkUrl())) {
            return;
        }
        AppCompatActivity appCompatActivity = audienceMessageView.activityContext;
        if (appCompatActivity instanceof AudienceActivity) {
            AudienceActivity.IntentGo(appCompatActivity, renovation.getLinkUrl());
        }
    }

    public void releaseTimer() {
        Disposable disposable = this.adAnimationTimer;
        if (disposable == null || disposable.isDisposed()) {
            return;
        }
        this.adLayout1.clearAnimation();
        this.adAnimationTimer.dispose();
        this.adAnimationTimer = null;
    }

    public void setZanText(String str, String str2) {
        this.videoId = str2;
        try {
            this.renqiView.setText(FormatUtils.getShowString(str));
            this.renqiView.setTag(str);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setGoods(ShopEntity shopEntity) {
        if (shopEntity != null && "0000".equals(shopEntity.getStatusCode())) {
            ShopEntity.DataBean data = shopEntity.getData();
            if (data != null) {
                setZanText(data.getRoundPraise(), "");
                if ("Y".equalsIgnoreCase(data.getHaveGoods())) {
                    this.goodsCardView.setVisibility(0);
                    this.goodsViewDown.setVisibility(0);
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
                    this.goodsLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$sFst59ZmlHDP56hc8NQ7Xmc_psY
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            AudienceMessageView.lambda$setGoods$29(AudienceMessageView.this, goods, view);
                        }
                    });
                    return;
                }
                this.goodsCardView.setVisibility(8);
                this.goodsViewDown.setVisibility(8);
                return;
            }
            this.goodsCardView.setVisibility(8);
            this.goodsViewDown.setVisibility(8);
            return;
        }
        this.goodsCardView.setVisibility(8);
        this.goodsViewDown.setVisibility(8);
    }

    public static /* synthetic */ void lambda$setGoods$29(AudienceMessageView audienceMessageView, ShopEntity.DataBean.GoodsBean goodsBean, View view) {
        AppCompatActivity appCompatActivity = audienceMessageView.activityContext;
        if (appCompatActivity instanceof AudienceActivity) {
            AudienceActivity.IntentGo(appCompatActivity, goodsBean.getGoodsUrl(), "0", goodsBean.getId(), 4000);
            IPage1Click iPage1Click = audienceMessageView.page1Click;
            if (iPage1Click != null) {
                iPage1Click.clickGoods(goodsBean);
                return;
            }
            return;
        }
        IntentManager.gotoWebViewActivity(appCompatActivity, goodsBean.getGoodsUrl(), goodsBean.getName());
    }

    public void setLiveType(String str) {
        View findViewById;
        try {
            this.liveType = str;
            if ("3".equals(str)) {
                initProgressBar();
                this.clickScreenListener = new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$KeWof6Xehj0WXxVJ5X34OjQsA9s
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceMessageView.lambda$setLiveType$30(AudienceMessageView.this, view);
                    }
                };
                if (this.messageView1 == null || (findViewById = this.messageView1.findViewById(2131299474)) == null) {
                    return;
                }
                findViewById.setVisibility(0);
                findViewById.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$uIInSt21qKi-Cl0enUmIev5kqTY
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        AudienceMessageView.lambda$setLiveType$31(AudienceMessageView.this, view);
                    }
                });
                return;
            }
            if (this.llProgressBar != null) {
                this.llProgressBar.setVisibility(8);
            }
            View findViewById2 = this.messageView1.findViewById(2131299474);
            if (findViewById2 != null) {
                findViewById2.setVisibility(8);
            }
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setLiveType$30(AudienceMessageView audienceMessageView, View view) {
        View findViewById;
        try {
            audienceMessageView.isHideScreen = !audienceMessageView.isHideScreen;
            if (audienceMessageView.messageView1 != null && (findViewById = audienceMessageView.messageView1.findViewById(2131297746)) != null) {
                findViewById.setVisibility(audienceMessageView.isHideScreen ? 8 : 0);
            }
            if (audienceMessageView.bottomLayout != null) {
                audienceMessageView.bottomLayout.setVisibility(audienceMessageView.isHideScreen ? 8 : 0);
            }
            if (audienceMessageView.isMultiView) {
                if (audienceMessageView.llProgressBar != null) {
                    audienceMessageView.llProgressBar.setVisibility(8);
                }
            } else if (audienceMessageView.llProgressBar != null) {
                audienceMessageView.llProgressBar.setVisibility(audienceMessageView.isHideScreen ? 8 : 0);
            }
            if (audienceMessageView.bubbleLayout != null) {
                audienceMessageView.bubbleLayout.setVisibility(audienceMessageView.isHideScreen ? 8 : 0);
            }
        } catch (Exception e) {
            MsLogUtil.m7979d(TAG, e.getMessage());
        }
    }

    public static /* synthetic */ void lambda$setLiveType$31(AudienceMessageView audienceMessageView, View view) {
        View.OnClickListener onClickListener = audienceMessageView.clickScreenListener;
        if (onClickListener != null) {
            onClickListener.onClick(null);
        }
    }

    public void setGoodsList(final List<GoodListEntity> list) {
        if (list == null) {
            try {
                if (this.goodListLayout == null) {
                    return;
                }
                this.goodListLayout.setVisibility(8);
            } catch (Exception e) {
                MsLogUtil.m7980d(e.getMessage());
                return;
            }
        }
        if (list.size() == 0) {
            this.goodListLayout.setVisibility(8);
        } else {
            this.goodListLayout.setVisibility(0);
        }
        if (this.goodListText != null) {
            TextView textView = this.goodListText;
            textView.setText(list.size() + "");
        }
        if (this.goodsListBtn != null) {
            this.goodsListBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$25GGuZVXozs12t5P_DaQ513yAAc
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    AudienceMessageView.lambda$setGoodsList$32(AudienceMessageView.this, list, view);
                }
            });
        }
        AudienceGoodsListDialogNew.getInstance().update(list).setListener(new AudienceGoodsListDialogNew.OnExplainListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$pwb6Il0cOPlulHGgD9vOFravLMY
            @Override // com.sinovatech.unicom.separatemodule.audience.view.AudienceGoodsListDialogNew.OnExplainListener
            public final void sendMsg(String str) {
                AudienceMessageView.lambda$setGoodsList$33(AudienceMessageView.this, str);
            }
        });
    }

    public static /* synthetic */ void lambda$setGoodsList$32(AudienceMessageView audienceMessageView, List list, View view) {
        if ("2".equals(audienceMessageView.currentAnchor.getDataType()) || "3".equals(audienceMessageView.liveType)) {
            AudienceGoodsDialog.show(audienceMessageView.activityContext, list, audienceMessageView.goodsItemClickListener);
        } else {
            AudienceGoodsListDialogNew.getInstance().init(audienceMessageView.activityContext, list, audienceMessageView.currentAnchor.getUserId());
        }
        if (audienceMessageView.activityContext instanceof AudiencePlayBackActivity) {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "商品列表", "直播回放页", "", "直播回放页");
        } else {
            PvCurrencyLogUtils.pvLogLive("", 3, "", "商品列表", "直播详情页", "", "直播详情页");
        }
        IPage1Click iPage1Click = audienceMessageView.page1Click;
        if (iPage1Click != null) {
            iPage1Click.clickShoppingCart();
        }
    }

    public static /* synthetic */ void lambda$setGoodsList$33(AudienceMessageView audienceMessageView, String str) {
        try {
            AlivcLiveMessageInfo alivcLiveMessageInfo = new AlivcLiveMessageInfo();
            alivcLiveMessageInfo.setSendName(audienceMessageView.audienceUser.getNick());
            alivcLiveMessageInfo.setUserId("求讲解");
            alivcLiveMessageInfo.setLevel(audienceMessageView.audienceUser.getLevel());
            alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_EXPLAIN_GOODS.getMsgType());
            alivcLiveMessageInfo.setDataContent("求讲解 " + str + "");
            audienceMessageView.commentListView.addMessage(alivcLiveMessageInfo);
            audienceMessageView.showExplainNotice();
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void delChat(int i) {
        try {
            int chatCount = this.commentListView.getChatCount();
            if (i < 0 || i >= chatCount) {
                return;
            }
            this.commentListView.removeMessage(i);
        } catch (Exception e) {
            MsLogUtil.m7980d(e.getMessage());
        }
    }

    public void setShareData(final ZhuboDataEntity.AnchorInfoBean anchorInfoBean, final ManagerAudienceLoadData managerAudienceLoadData, final String str, final String str2) {
        this.shareTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Lz8LfItloGzQUR0fE4K1hnWpJAo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$setShareData$35(AudienceMessageView.this, str, anchorInfoBean, str2, managerAudienceLoadData, view);
            }
        });
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:16:0x00cb
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    public static /* synthetic */ void lambda$setShareData$35(com.sinovatech.unicom.separatemodule.audience.AudienceMessageView r8, java.lang.String r9, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity.AnchorInfoBean r10, java.lang.String r11, com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData r12, android.view.View r13) {
        /*
            org.json.JSONObject r13 = new org.json.JSONObject
            r13.<init>()
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "&liveShareInfo="
            r0.append(r1)
            r0.append(r9)
            java.lang.String r9 = "&liveShareProvince="
            r0.append(r9)
            com.sinovatech.unicom.basic.server.UserManager r9 = com.sinovatech.unicom.basic.server.UserManager.getInstance()
            java.lang.String r9 = r9.getCurrentProvinceCode()
            r0.append(r9)
            java.lang.String r9 = "&liveShareCity="
            r0.append(r9)
            com.sinovatech.unicom.basic.server.UserManager r9 = com.sinovatech.unicom.basic.server.UserManager.getInstance()
            java.lang.String r9 = r9.getCurrentCityCode()
            r0.append(r9)
            java.lang.String r9 = "&liveShareChannel="
            r0.append(r9)
            java.lang.String r9 = r0.toString()
            boolean r0 = r8.isMultiView
            if (r0 == 0) goto L42
            java.lang.String r0 = "&isToMultiLive=Y"
            goto L44
        L42:
            java.lang.String r0 = ""
        L44:
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = r10.getUserName()
            r1.append(r2)
            java.lang.String r2 = "正在直播"
            r1.append(r2)
            java.lang.String r1 = r1.toString()
            java.lang.String r2 = r10.getShareContentDesc()
            boolean r2 = android.text.TextUtils.isEmpty(r2)
            if (r2 == 0) goto L81
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "来自中国联通APP-"
            r2.append(r3)
            java.lang.String r3 = r10.getUserName()
            r2.append(r3)
            java.lang.String r3 = "主播"
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            goto L85
        L81:
            java.lang.String r2 = r10.getShareContentDesc()
        L85:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder
            r3.<init>()
            java.lang.String r4 = r10.getUserId()
            java.lang.String r4 = com.sinovatech.unicom.common.URLSet.getZhiboShareUrl(r4)
            r3.append(r4)
            r3.append(r0)
            r3.append(r9)
            java.lang.String r9 = r3.toString()
            java.lang.String r0 = "#/liveplayer?"
            java.lang.String r9 = r9.replace(r0, r11)
            java.lang.String r11 = r10.getUserImg()
            java.lang.String r0 = "wechat,wechatmoments,qq,qzone"
            java.lang.String r3 = "shareType"
            java.lang.String r4 = "url"
            r13.put(r3, r4)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r3 = "shareTitle"
            r13.put(r3, r1)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r1 = "shareContent"
            r13.put(r1, r2)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r1 = "shareURL"
            r13.put(r1, r9)     // Catch: java.lang.Exception -> Lc9
            java.lang.String r9 = "shareIconURL"
            r13.put(r9, r11)     // Catch: java.lang.Exception -> Lc9
            goto Ld0
        Lc9:
            r9 = move-exception
            goto Lcd
        Lcb:
            r9 = move-exception
            r0 = 0
        Lcd:
            r9.printStackTrace()
        Ld0:
            java.lang.String r9 = r10.getUserId()
            com.uber.autodispose.ObservableSubscribeProxy r9 = r12.addShare(r9)
            com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0 r11 = new io.reactivex.functions.Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0
                static {
                    /*
                        com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0 r0 = new com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0
                        r0.<init>()
                        
                        // error: 0x0005: SPUT  (r0 I:com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0) com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0.INSTANCE com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0.<clinit>():void");
                }

                {
                    /*
                        r0 = this;
                        r0.<init>()
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0.<init>():void");
                }

                @Override // io.reactivex.functions.Consumer
                public final void accept(java.lang.Object r1) {
                    /*
                        r0 = this;
                        java.lang.String r1 = (java.lang.String) r1
                        com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.lambda$setShareData$34(r1)
                        return
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.$$Lambda$AudienceMessageView$Qq1OuA0x5MM5ftbkrJsTkD1BFG0.accept(java.lang.Object):void");
                }
            }
            r9.subscribe(r11)
            android.support.v7.app.AppCompatActivity r9 = r8.activityContext
            boolean r11 = r13 instanceof org.json.JSONObject
            if (r11 != 0) goto Le8
            java.lang.String r11 = r13.toString()
            goto Lee
        Le8:
            org.json.JSONObject r13 = (org.json.JSONObject) r13
            java.lang.String r11 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r13)
        Lee:
            com.sinovatech.unicom.separatemodule.audience.AudienceMessageView$10 r12 = new com.sinovatech.unicom.separatemodule.audience.AudienceMessageView$10
            r12.<init>()
            com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShowShareDialog(r9, r0, r11, r12)
            java.lang.String r1 = r10.getLivePullUrl()
            r2 = 2
            java.lang.String r3 = r10.getUserName()
            java.lang.String r4 = "分享"
            java.lang.String r5 = "直播详情页"
            java.lang.String r6 = r10.getLiveTitle()
            java.lang.String r7 = "直播详情页"
            com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils.pvLogLive(r1, r2, r3, r4, r5, r6, r7)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.lambda$setShareData$35(com.sinovatech.unicom.separatemodule.audience.AudienceMessageView, java.lang.String, com.sinovatech.unicom.separatemodule.audience.entity.ZhuboDataEntity$AnchorInfoBean, java.lang.String, com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData, android.view.View):void");
    }

    public void setLiveTitle(String str) {
        this.liveTitle = str;
        if (this.tvLiveTitle == null || TextUtils.isEmpty(str)) {
            return;
        }
        SpannableString spannableString = new SpannableString("主题" + str);
        spannableString.setSpan(new RoomDescBgSpan(Color.parseColor("#4DFFFFFF"), Color.parseColor("#FFFFFFFF")), 0, 2, 33);
        this.tvLiveTitle.setText(spannableString);
    }

    public void updateMessageAdapter(LiveMsg liveMsg) {
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
        if ("desc".equals(liveMsg.getType()) && this.commentListView.getAdapter().getListLength() == 0) {
            alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_ROOM_DESC.getMsgType());
            this.commentListView.addMessage(alivcLiveMessageInfo);
        }
        if ("notic".equals(liveMsg.getType())) {
            alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_UP_MIC.getMsgType());
            this.commentListView.addMessage(alivcLiveMessageInfo);
        }
        if ("join".equals(liveMsg.getType()) && !TextUtils.isEmpty(liveMsg.getNick())) {
            showJoinAnimeorFouces(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.isMgr(), true);
        }
        if ("chat".equals(liveMsg.getType())) {
            alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_TYPE_CHAT.getMsgType());
            this.commentListView.addMessage(alivcLiveMessageInfo);
        }
        if ("atto".equals(liveMsg.getType()) && !TextUtils.isEmpty(liveMsg.getMsg())) {
            showAttoInfoAnim(liveMsg.getMsg());
        }
        if ("push".equals(liveMsg.getType())) {
            if ("2".equals(liveMsg.getmType())) {
                showJoinAnimeorFouces(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.isMgr(), false);
            }
            if ("3".equals(liveMsg.getmType())) {
                showtBrowseGoodAnim(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.isMgr(), "");
            }
            if ("4".equals(liveMsg.getmType())) {
                showOrderAnim(liveMsg.getNick(), liveMsg.getLevel(), "");
            }
            if ("6".equals(liveMsg.getmType())) {
                alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_TYPE_SENDGIFT.getMsgType());
                this.commentListView.addMessage(alivcLiveMessageInfo);
                showGiftAnime(liveMsg.getGiftCode());
                showMsgGiftAnim(liveMsg.getNick(), liveMsg.getLevel(), liveMsg.getGiftCode());
            }
            if ("201".equals(liveMsg.getmType())) {
                alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_TYPE_LIKE.getMsgType());
                this.commentListView.addMessage(alivcLiveMessageInfo);
            }
            if ("710".equals(liveMsg.getmType()) && this.currentAnchor.getUserId().equals(liveMsg.getRid())) {
                alivcLiveMessageInfo.setType(AlivcLiveMessageInfo.AlivcMsgType.ALIVC_MESSAGE_EXPLAIN_GOODS.getMsgType());
                String goodsInfoById = AudienceGoodsListDialogNew.getInstance().getGoodsInfoById(liveMsg.getGoodsId());
                if (TextUtils.isEmpty(goodsInfoById)) {
                    return;
                }
                alivcLiveMessageInfo.setDataContent("求讲解 " + goodsInfoById + "");
                this.commentListView.addMessage(alivcLiveMessageInfo);
            }
        }
    }

    public void updatePush(ZhuboDataEntity zhuboDataEntity) {
        if ("0000".equals(zhuboDataEntity.getStatusCode())) {
            ZhuboDataEntity.AnchorInfoBean anchorInfoBean = zhuboDataEntity.getAnchorInfoBean();
            this.giftNumView.setText(FormatUtils.getShowString(anchorInfoBean.getReceiveGiftNum()));
            this.fansView.setText(FormatUtils.getShowString(anchorInfoBean.getFansNum()));
        }
    }

    public void setLivePvMessage(LivePvInfoEntity.InfoEntity infoEntity, String str) {
        this.messageRootlayout.setVisibility(4);
        this.giftAnimeView.setVisibility(8);
        this.bottomInputLayout.setVisibility(8);
        this.giftTextView.setVisibility(8);
        this.dianzanImageView.setVisibility(0);
        this.subscribeWatchLayout.setVisibility(0);
        this.subsWatchTime.setText(infoEntity != null ? infoEntity.getPrevueTime() : "该主播未发布预告");
        char c = 65535;
        switch (str.hashCode()) {
            case 1567967:
                if (str.equals("3101")) {
                    c = 0;
                    break;
                }
                break;
            case 1567968:
                if (str.equals("3102")) {
                    c = 1;
                    break;
                }
                break;
            case 1567969:
                if (str.equals("3103")) {
                    c = 2;
                    break;
                }
                break;
            case 1567970:
                if (str.equals("3104")) {
                    c = 3;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                this.tvSubWatchBtn.setVisibility(8);
                this.tvSubWatchStatus.setVisibility(0);
                return;
            case 1:
                this.tvSubWatchBtn.setVisibility(0);
                this.tvSubWatchStatus.setVisibility(8);
                return;
            case 2:
            case 3:
                this.tvSubWatchBtn.setVisibility(8);
                this.tvSubWatchStatus.setVisibility(8);
                return;
            default:
                return;
        }
    }

    private void setLivePvGone() {
        this.subscribeWatchLayout.setVisibility(8);
        this.dianzanImageView.setVisibility(0);
        this.bottomInputLayout.setVisibility(0);
        this.giftAnimeView.setVisibility(0);
    }

    public void clearMessage() {
        AudienceGuanzhuDialog.cancelGuanzhusub();
        this.messageRootlayout.setVisibility(4);
        this.tvLiveTitle.setVisibility(8);
        this.photoView.setImageResource(0);
        this.nameView.setText("");
        this.renqiView.setText("");
        this.renqiView.setTag("");
        this.cityView.setText("");
        this.giftNumView.setText("");
        this.fansView.setText("");
        this.gaunzhuView.setVisibility(8);
        this.tiezhi1.setVisibility(4);
        this.tiezhi2.setVisibility(4);
        this.tiezhi3.setVisibility(4);
        this.adImage3.setVisibility(4);
        this.adLayout1.setVisibility(4);
        this.adLayout2.setVisibility(4);
        this.goodsCardView.setVisibility(8);
        this.goodsViewDown.setVisibility(8);
        this.goodListLayout.setVisibility(4);
        this.commentListView.clearAll();
        setLivePvGone();
        this.ivLogo.setVisibility(8);
        showToggleView(false, null);
        releaseHuodongView();
        this.giftAnimeView.stopAllGiftAnim();
        this.bubbleLayout.removeAllViews();
        Disposable disposable = this.ds1;
        if (disposable != null) {
            disposable.dispose();
        }
        this.bubbleView = null;
        clearPkView();
        showChatRoomAndGiftUI();
        setVideoProgressGone();
    }

    @Override // com.sinovatech.unicom.separatemodule.audience.interfaceImpl.VideoProgressVisible
    public void setVideoProgressGone() {
        View view = this.llProgressBar;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class AdapterViewpager extends PagerAdapter {
        private List<View> mViewList;

        @Override // android.support.p083v4.view.PagerAdapter
        public boolean isViewFromObject(View view, Object obj) {
            return view == obj;
        }

        public AdapterViewpager(List<View> list) {
            this.mViewList = list;
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public int getCount() {
            return this.mViewList.size();
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public Object instantiateItem(ViewGroup viewGroup, int i) {
            viewGroup.addView(this.mViewList.get(i));
            return this.mViewList.get(i);
        }

        @Override // android.support.p083v4.view.PagerAdapter
        public void destroyItem(ViewGroup viewGroup, int i, Object obj) {
            viewGroup.removeView(this.mViewList.get(i));
        }
    }

    private void setRenovationImage(ImageView imageView, ZhuangXiuEntity.Renovation renovation) {
        if (!TextUtils.isEmpty(renovation.getImgSrc())) {
            imageView.setVisibility(0);
            GlideApp.with((FragmentActivity) this.activityContext).load(renovation.getImgSrc()).transform((Transformation<Bitmap>) new RoundedCornersTransformation(UIUtils.dip2px(8.0f), 0)).into(imageView);
            return;
        }
        imageView.setVisibility(4);
    }

    private void showGiftAnime(String str) {
        UIUtils.logD("showGiftAnime", "--->" + str);
        initGiftView();
        this.giftAnimeView.startGiftAnime(str);
    }

    private void showJoinAnimeorFouces(String str, String str2, boolean z, boolean z2) {
        initGiftView();
        this.giftAnimeView.setJoinBottomMargin(UIUtils.dip2px(this.activityContext, 270.0f));
        this.giftAnimeView.startJoinOrFocusAnim(str, str2, z, z2);
    }

    private void showtBrowseGoodAnim(String str, String str2, boolean z, String str3) {
        initGiftView();
        this.giftAnimeView.setJoinBottomMargin(UIUtils.dip2px(this.activityContext, 270.0f));
        this.giftAnimeView.startBrowseGoodAnim(str, str2, z, str3);
    }

    private void showAttoInfoAnim(String str) {
        this.giftAnimeView.setJoinBottomMargin(UIUtils.dip2px(this.activityContext, 240.0f));
        this.giftAnimeView.startAttoInfoAnim(str);
    }

    private void showMsgGiftAnim(String str, String str2, String str3) {
        initGiftView();
        this.giftAnimeView.setMsgTopMargin(UIUtils.dip2px(100.0f));
        this.giftAnimeView.startMsgGiftAnim(str, str2, str3);
    }

    private void showOrderAnim(String str, String str2, String str3) {
        initGiftView();
        this.giftAnimeView.setMsgTopMargin(UIUtils.dip2px(100.0f));
        try {
            this.giftAnimeView.startOrderAnim(str, str2, str3);
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
            this.ds1 = Observable.interval(0L, 800L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$VU4q6cW_x9YeCIhRbcWvrquO8Sk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    AudienceMessageView.lambda$showDianzanAnime$36(AudienceMessageView.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$showDianzanAnime$36(AudienceMessageView audienceMessageView, Long l) throws Exception {
        try {
            audienceMessageView.bubbleView.addPraise(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setHuodongView(String str, String str2, ActivityTimeEntity activityTimeEntity) {
        if (this.isMultiView) {
            UIUtils.logD("多视角测试", "屏蔽活动");
            return;
        }
        if (this.countDownView == null) {
            this.countDownView = new CountDownView(this.activityContext);
        }
        ViewGroup viewGroup = (ViewGroup) this.countDownView.getParent();
        if (viewGroup != null) {
            viewGroup.removeAllViews();
        }
        TextView textView = this.gaunzhuView;
        if (textView != null) {
            this.countDownView.setTvGuanzhu(textView);
        }
        this.countDownView.setJsonData(str, str2, activityTimeEntity);
        this.huodongFrameLayout.addView(this.countDownView);
    }

    public void releaseHuodongView() {
        CountDownView countDownView = this.countDownView;
        if (countDownView != null) {
            countDownView.releaseHandler();
            ViewGroup viewGroup = (ViewGroup) this.countDownView.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
        }
        this.huodongFrameLayout.removeAllViews();
    }

    public void redPacketTest() {
        this.photoView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$2sbk5pDp4da0F53ZrTnCIPCbk2I
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$redPacketTest$37(AudienceMessageView.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$redPacketTest$37(AudienceMessageView audienceMessageView, View view) {
        ActivityTimeEntity activityTimeEntity = new ActivityTimeEntity();
        UIUtils.logD("ActivityTimeFunction", "活动消息---->{\"acId\":\"AC20200604112010\",\"durationTime\":\"1\",\"preheatTime\":1599036683000,\"beginTime\":1599036923000,\"endTime\":1606873942000,\"acName\":\"直播天天抽奖\",\"ifPartakeActivity\":\"NO\",\"acType\":\"0\",\"respCode\":\"0000\",\"preheatImg\":\"http://ecstest2018.10010.com/marketplatNew/20200604/98_9df32935bc7548d39394d9102d781d40.png\"}");
        if (!TextUtils.isEmpty("{\"acId\":\"AC20200604112010\",\"durationTime\":\"1\",\"preheatTime\":1599036683000,\"beginTime\":1599036923000,\"endTime\":1606873942000,\"acName\":\"直播天天抽奖\",\"ifPartakeActivity\":\"NO\",\"acType\":\"0\",\"respCode\":\"0000\",\"preheatImg\":\"http://ecstest2018.10010.com/marketplatNew/20200604/98_9df32935bc7548d39394d9102d781d40.png\"}")) {
            JSONObject jSONObject = null;
            try {
                jSONObject = new JSONObject("{\"acId\":\"AC20200604112010\",\"durationTime\":\"1\",\"preheatTime\":1599036683000,\"beginTime\":1599036923000,\"endTime\":1606873942000,\"acName\":\"直播天天抽奖\",\"ifPartakeActivity\":\"NO\",\"acType\":\"0\",\"respCode\":\"0000\",\"preheatImg\":\"http://ecstest2018.10010.com/marketplatNew/20200604/98_9df32935bc7548d39394d9102d781d40.png\"}");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            if (jSONObject != null) {
                String optString = jSONObject.optString("respCode");
                String optString2 = jSONObject.optString("acId");
                String optString3 = jSONObject.optString("acName");
                String optString4 = jSONObject.optString("acType");
                Long valueOf = Long.valueOf(jSONObject.optLong("preheatTime"));
                String optString5 = jSONObject.optString("preheatImg");
                String optString6 = jSONObject.optString("durationTime");
                Long valueOf2 = Long.valueOf(jSONObject.optLong("beginTime"));
                Long valueOf3 = Long.valueOf(jSONObject.optLong("endTime"));
                String optString7 = jSONObject.optString("ifPartakeActivity");
                activityTimeEntity.setRespCode(optString);
                activityTimeEntity.setAcId(optString2);
                activityTimeEntity.setAcName(optString3);
                activityTimeEntity.setAcType(optString4);
                activityTimeEntity.setPreheatTime(valueOf);
                activityTimeEntity.setPreheatImg(optString5);
                activityTimeEntity.setDurationTime(optString6);
                activityTimeEntity.setBeginTime(valueOf2);
                activityTimeEntity.setEndTime(valueOf3);
                activityTimeEntity.setIfPartakeActivity(optString7);
            }
        }
        audienceMessageView.setHuodongView("userId", "userName", activityTimeEntity);
    }

    public void setLogoVisable(String str) {
        GlideApp.with((FragmentActivity) this.activityContext).load(str).into(this.ivLogo);
    }

    public void setInterAnchorInfoDiaplay(final LianMZBEntity.AnchorsInfoEntity anchorsInfoEntity, ManagerAudienceLoadData managerAudienceLoadData, final CallBack callBack) {
        if (anchorsInfoEntity == null) {
            this.interAnchorInfo.setVisibility(8);
            return;
        }
        this.interAnchorInfo.setVisibility(0);
        this.toggle.setVisibility(8);
        this.multiToggle.setVisibility(8);
        this.interAnchorBtn.setVisibility(8);
        GlideApp.with((FragmentActivity) this.activityContext).load(anchorsInfoEntity.getHeadImg()).into(this.interAnchorIcon);
        this.interAnchorName.setText(anchorsInfoEntity.getNickName());
        if ("Y".equals(anchorsInfoEntity.getPkStatus())) {
            setCountDown(Long.valueOf(anchorsInfoEntity.getPkStartTime()).longValue());
            setScore(anchorsInfoEntity.getSourcePkScore(), anchorsInfoEntity.getTargetPkScore());
        } else {
            gonePkView();
        }
        this.interAnchorInfo.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$M_slVoi-ZVBlFqmVoe4nvC5I8Sg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AudienceMessageView.lambda$setInterAnchorInfoDiaplay$38(AudienceMessageView.CallBack.this, anchorsInfoEntity, view);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setInterAnchorInfoDiaplay$38(CallBack callBack, LianMZBEntity.AnchorsInfoEntity anchorsInfoEntity, View view) {
        if (callBack != null) {
            callBack.callBack(anchorsInfoEntity.getJobNumber());
        }
    }

    private void initPkView() {
        this.rlPkProgress = (RelativeLayout) this.view.findViewById(2131298354);
        this.cmLivePk = (Chronometer) this.view.findViewById(2131296616);
        this.tvMe = (TextView) this.view.findViewById(2131299008);
        this.tvYou = (TextView) this.view.findViewById(2131299152);
        this.llPkResult = this.view.findViewById(2131297755);
        this.showIcon = (ImageView) this.view.findViewById(2131297494);
        this.myResult = (ImageView) this.view.findViewById(2131297435);
        this.adverseResult = (ImageView) this.view.findViewById(2131297330);
    }

    private void setCountDown(long j) {
        this.rlPkProgress.setVisibility(0);
        setScore("0", "0");
        long j2 = j + 300000;
        if (j2 - System.currentTimeMillis() > 0) {
            this.cmLivePk.setBase(j2);
            this.cmLivePk.setFormat("%s");
            this.cmLivePk.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$QtUlVVV-ZFA15qBAxgqZMLXWqT8
                @Override // android.widget.Chronometer.OnChronometerTickListener
                public final void onChronometerTick(Chronometer chronometer) {
                    AudienceMessageView.lambda$setCountDown$39(AudienceMessageView.this, chronometer);
                }
            });
            this.cmLivePk.start();
        } else {
            this.cmLivePk.setText("00:00");
            this.cmLivePk.stop();
        }
        this.llPkResult.setVisibility(8);
        this.showIcon.setVisibility(8);
        this.myResult.setVisibility(8);
        this.adverseResult.setVisibility(8);
    }

    public static /* synthetic */ void lambda$setCountDown$39(AudienceMessageView audienceMessageView, Chronometer chronometer) {
        long base = audienceMessageView.cmLivePk.getBase() - System.currentTimeMillis();
        audienceMessageView.cmLivePk.setText(audienceMessageView.secondsToStr(base));
        if (base <= 0) {
            audienceMessageView.cmLivePk.stop();
        }
    }

    public String secondsToStr(long j) {
        int i;
        int intValue = new Double(j / 1000).intValue();
        if (intValue <= 60) {
            i = 0;
        } else if (intValue > 3600) {
            int i2 = intValue % 3600;
            i = i2 / 60;
            intValue = (i2 % 60) % 60;
        } else {
            i = intValue / 60;
            intValue %= 60;
        }
        return parseStr(i) + ":" + parseStr(intValue);
    }

    private static String parseStr(int i) {
        if (i >= 10) {
            return i + "";
        }
        return "0" + i;
    }

    public void setScore(String str, String str2) {
        if (!TextUtils.isEmpty(str)) {
            TextView textView = this.tvMe;
            textView.setText("我方 " + str);
        }
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        TextView textView2 = this.tvYou;
        textView2.setText(str2 + " 对方");
    }

    public void showWiner(final String str, String str2, String str3) {
        setScore(str2, str3);
        this.llPkResult.setVisibility(0);
        this.showIcon.setVisibility(0);
        if (!TextUtils.isEmpty(str)) {
            if ("N".equals(str)) {
                GlideApp.with(this).load((Integer) 2131232069).into(this.showIcon);
            } else if (str.equals(this.currentAnchor.getUserId())) {
                GlideApp.with(this).load((Integer) 2131232070).into(this.showIcon);
                GlideApp.with(this).load((Integer) 2131232070).into(this.myResult);
                GlideApp.with(this).load((Integer) 2131232068).into(this.adverseResult);
            } else {
                GlideApp.with(this).load((Integer) 2131232068).into(this.showIcon);
                GlideApp.with(this).load((Integer) 2131232068).into(this.myResult);
                GlideApp.with(this).load((Integer) 2131232070).into(this.adverseResult);
            }
        }
        AnimationSet animationSet = new AnimationSet(true);
        float screenWidth = ((UIUtils.getScreenWidth((Activity) this.activityContext) * 140) / 360.0f) / 2.0f;
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, screenWidth, screenWidth);
        scaleAnimation.setDuration(1500L);
        scaleAnimation.setRepeatCount(0);
        animationSet.addAnimation(scaleAnimation);
        ScaleAnimation scaleAnimation2 = new ScaleAnimation(1.0f, 0.0f, 1.0f, 0.0f, screenWidth, screenWidth);
        scaleAnimation2.setStartOffset(3000L);
        scaleAnimation2.setDuration(500L);
        scaleAnimation2.setRepeatCount(0);
        scaleAnimation2.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.audience.AudienceMessageView.11
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                AudienceMessageView.this.showIcon.setVisibility(8);
                if ("N".equals(str)) {
                    AudienceMessageView.this.myResult.setVisibility(8);
                    AudienceMessageView.this.adverseResult.setVisibility(8);
                } else {
                    AudienceMessageView.this.myResult.setVisibility(0);
                    AudienceMessageView.this.adverseResult.setVisibility(0);
                }
                AudienceMessageView.this.gonePkView();
            }
        });
        animationSet.addAnimation(scaleAnimation2);
        animationSet.setFillAfter(true);
        this.showIcon.startAnimation(animationSet);
    }

    public void gonePkView() {
        this.pkViewGoneTimer = Observable.interval(5L, 1L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$BJUGo_NHCf8r-p3eUqOxpfm7tPU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                Long l = (Long) obj;
                AudienceMessageView.this.clearPkView();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearPkView() {
        this.rlPkProgress.setVisibility(8);
        this.llPkResult.setVisibility(8);
        this.showIcon.setVisibility(8);
        this.myResult.setVisibility(8);
        this.adverseResult.setVisibility(8);
        this.cmLivePk.start();
    }

    public void releasePkTimer() {
        Disposable disposable = this.pkViewGoneTimer;
        if (disposable == null || !disposable.isDisposed()) {
            return;
        }
        this.pkViewGoneTimer.dispose();
        this.pkViewGoneTimer = null;
    }

    private void showExplainNotice() {
        this.tvExplainNotice.setVisibility(0);
        this.explainTimer = Observable.interval(5L, 1L, TimeUnit.SECONDS).take(1L).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.-$$Lambda$AudienceMessageView$_C7NkPGphLE8FJFQ1szWtVroe0Q
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                AudienceMessageView.lambda$showExplainNotice$41(AudienceMessageView.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$showExplainNotice$41(AudienceMessageView audienceMessageView, Long l) throws Exception {
        audienceMessageView.tvExplainNotice.setVisibility(8);
        audienceMessageView.explainTimer.dispose();
        audienceMessageView.explainTimer = null;
    }

    public View getRoomInfoView() {
        return this.view;
    }

    private void showInputLine() {
        this.showTextView.setVisibility(OptionValveUtil.INSTENCE.isShowChatRoomTab() ? 0 : 4);
        this.messageRootlayout.setVisibility(OptionValveUtil.INSTENCE.isShowChatRoomTab() ? 0 : 4);
        this.tvLiveTitle.setVisibility(OptionValveUtil.INSTENCE.isShowChatRoomTab() ? 8 : 0);
    }

    public void setMultiView(boolean z) {
        this.isMultiView = z;
        this.multiToggle.setVisibility(this.isMultiView ? 0 : 8);
        if (UIUtils.isFoldScreen(this.activityContext)) {
            this.multiToggle.setVisibility(8);
        }
        hideAdvertisement();
        if (this.llProgressBar == null || !"3".equals(this.liveType)) {
            return;
        }
        this.llProgressBar.setVisibility(this.isMultiView ? 8 : 0);
    }

    private void hideAdvertisement() {
        ImageView imageView = this.tiezhi2;
        if (imageView != null && this.isMultiView) {
            imageView.setVisibility(8);
        }
        ImageView imageView2 = this.tiezhi3;
        if (imageView2 != null && this.isMultiView) {
            imageView2.setVisibility(8);
        }
        RelativeLayout relativeLayout = this.adLayout2;
        if (relativeLayout != null && this.isMultiView) {
            relativeLayout.setVisibility(8);
        }
        RelativeLayout relativeLayout2 = this.adLayout3;
        if (relativeLayout2 != null && this.isMultiView) {
            relativeLayout2.setVisibility(8);
        }
        if (this.switchTextView != null && !"1".equals(this.liveType)) {
            this.switchTextView.setVisibility(8);
        }
        LiveRoomUiHideEntity liveRoomUiHideEntity = this.liveRoomUIInfo;
        if (liveRoomUiHideEntity != null) {
            hideChatRoomAndGiftByNet(liveRoomUiHideEntity);
        }
    }

    public void hideGiftUI() {
        this.giftTextView.setVisibility(8);
        this.showTextView.setVisibility(0);
        this.messageRootlayout.setVisibility(0);
        this.tvLiveTitle.setVisibility(8);
    }

    public void hideChatRoomAndGiftByNet(LiveRoomUiHideEntity liveRoomUiHideEntity) {
        if (liveRoomUiHideEntity != null) {
            this.liveRoomUIInfo = liveRoomUiHideEntity;
            if (this.giftTextView != null && "0".equals(liveRoomUiHideEntity.getIsShowGift())) {
                this.giftTextView.setVisibility(8);
            }
            if (this.showTextView != null && "0".equals(liveRoomUiHideEntity.getIsShowChat())) {
                this.showTextView.setVisibility(4);
            } else {
                showInputLine();
            }
            if (this.messageRootlayout != null && "0".equals(liveRoomUiHideEntity.getIsShowChat())) {
                this.messageRootlayout.setVisibility(4);
                this.tvLiveTitle.setVisibility(0);
            } else {
                showInputLine();
            }
            if (this.giftAnimeView == null || !"0".equals(liveRoomUiHideEntity.getIsShowGift())) {
                return;
            }
            this.giftAnimeView.setVisibility(8);
        }
    }

    private void showChatRoomAndGiftUI() {
        ImageView imageView = this.giftTextView;
        if (imageView != null) {
            imageView.setVisibility(0);
        }
        TextView textView = this.showTextView;
        if (textView != null) {
            textView.setVisibility(0);
        }
        RelativeLayout relativeLayout = this.messageRootlayout;
        if (relativeLayout != null) {
            relativeLayout.setVisibility(0);
            this.tvLiveTitle.setVisibility(8);
        }
        LiveGiftView liveGiftView = this.giftAnimeView;
        if (liveGiftView != null) {
            liveGiftView.setVisibility(0);
        }
    }
}
