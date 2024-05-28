package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Activity;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.baidu.cloud.cloudplayer.videoview.BDCloudVideoView;
import com.bytedance.applog.tracker.Tracker;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.AudienceActivity;
import com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter;
import com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.view.LikeView;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SmallVideoView {
    private AudienceActivity activityContext;
    LinearLayout adRootView;
    ImageView anchorImg;
    ImageView anchorImg2;
    TextView anchorName;
    TextView anchorName2;
    private View barRoot;
    private BDCloudVideoView bdCloudVideoView;
    TextView commentNum;
    FrameLayout contairlayout;
    private LikeView.TouchCallBack doubleClickListener;
    private SmallVideoEntity.Data entity;
    TextView fullScreen;
    CardView goodsCard;
    TextView goodsDesc;
    ImageView goodsImg;
    TextView goodsOldPrice;
    TextView goodsPrice;
    TextView goodsTitle;
    ImageView guanzhuBtn;

    /* renamed from: i */
    private int f18490i;
    private boolean isPlaying = true;
    private boolean isStartTrackingTouch;
    private final View itemView;
    ImageView ivBack;
    private ImageView ivBarLandscapeBtn;
    ImageView ivBigV;
    ImageView ivBtnPlay;
    ImageView ivBtnPlay2;
    private View ivFinish;
    ImageView ivLandscapeBack;
    ImageView ivPreviewBack;
    ImageView ivPreviewShare;
    ImageView ivVideoRing;
    ImageView ivVideoRing2;
    LikeView likeView;
    private SmallVideoAdapter.ItemClickedListener listener;
    ImageView liveRoomCover;
    ImageView liveRoomCover2;
    LinearLayout llBtnComment;
    LinearLayout llBtnPreview;
    LinearLayout llBtnPreview2;
    LinearLayout llBtnSetRing;
    LinearLayout llBtnSetRing2;
    LinearLayout llBtnShare;
    LinearLayout llComment;
    LinearLayout llContentLayout;
    LinearLayout llFreeTips;
    LinearLayout llLandscapeInfo;
    LinearLayout llMore;
    LinearLayout llMore2;
    LinearLayout llPreviewRoot;
    private LinearLayout llProgressBar;
    private LinearLayout llProgressTextLine;
    LinearLayout llShu;
    LinearLayout llVideoHelp;
    LinearLayout llVideoHelp2;
    private Disposable playProgressUpdater;
    private final FrameLayout progressLine;
    RelativeLayout rlAnchorIcon;
    private RelativeLayout rlInfoArea;
    LinearLayout rl_comfort;
    LinearLayout root;
    private SeekBar sbProgress;
    private final int screenH;
    private final int screenW;
    TextView shareNum;
    private TextView tvEndTime;
    TextView tvLandscapeTitle;
    TextView tvPreviewSetRing;
    private TextView tvStartTime;
    TextView tvTitleLabel;
    private View vBarLandscapeBg;
    private View vBarLandscapeEnd;
    RelativeLayout videoRootView;
    TextView videoTag;
    TextView videoTag2;
    TextView videoTitle;
    TextView videoTitle2;
    TextView watchNum;
    ImageView zanIcon;
    ImageView zanIcon2;
    TextView zanNum;
    TextView zanNum2;

    /*  JADX ERROR: JadxRuntimeException in pass: CheckCode
        jadx.core.utils.exceptions.JadxRuntimeException: Incorrect register number in instruction: 0x03fc: NOT  (r2 I:long) = (r15 I:long), expected to be less than 9
        	at jadx.core.dex.visitors.CheckCode.checkInstructions(CheckCode.java:75)
        	at jadx.core.dex.visitors.CheckCode.visit(CheckCode.java:33)
        */
    public android.view.View initData(com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity.Data r6, int r7, com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter.ItemClickedListener r8) {
        /*
            Method dump skipped, instructions count: 1077
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.audience.view.SmallVideoView.initData(com.sinovatech.unicom.separatemodule.audience.entity.SmallVideoEntity$Data, int, com.sinovatech.unicom.separatemodule.audience.adpter.SmallVideoAdapter$ItemClickedListener):android.view.View");
    }

    public SmallVideoView(AppCompatActivity appCompatActivity, BDCloudVideoView bDCloudVideoView) {
        this.bdCloudVideoView = bDCloudVideoView;
        this.activityContext = (AudienceActivity) appCompatActivity;
        this.itemView = LayoutInflater.from(appCompatActivity).inflate(2131493237, (ViewGroup) null);
        this.ivFinish = this.itemView.findViewById(2131297383);
        this.adRootView = (LinearLayout) this.itemView.findViewById(2131296985);
        this.videoRootView = (RelativeLayout) this.itemView.findViewById(2131298392);
        this.root = (LinearLayout) this.itemView.findViewById(2131297726);
        this.contairlayout = (FrameLayout) this.itemView.findViewById(2131296395);
        this.liveRoomCover = (ImageView) this.itemView.findViewById(2131296393);
        this.liveRoomCover2 = (ImageView) this.itemView.findViewById(2131296394);
        this.anchorImg = (ImageView) this.itemView.findViewById(2131297332);
        this.anchorImg2 = (ImageView) this.itemView.findViewById(2131297333);
        this.rlAnchorIcon = (RelativeLayout) this.itemView.findViewById(2131298309);
        this.ivBigV = (ImageView) this.itemView.findViewById(2131297518);
        this.anchorName = (TextView) this.itemView.findViewById(2131298863);
        this.anchorName2 = (TextView) this.itemView.findViewById(2131298864);
        this.tvTitleLabel = (TextView) this.itemView.findViewById(2131299112);
        this.guanzhuBtn = (ImageView) this.itemView.findViewById(2131297390);
        this.watchNum = (TextView) this.itemView.findViewById(2131299147);
        this.videoTitle = (TextView) this.itemView.findViewById(2131299144);
        this.videoTitle2 = (TextView) this.itemView.findViewById(2131299145);
        this.videoTag = (TextView) this.itemView.findViewById(2131299142);
        this.videoTag2 = (TextView) this.itemView.findViewById(2131299143);
        this.zanNum = (TextView) this.itemView.findViewById(2131299157);
        this.zanNum2 = (TextView) this.itemView.findViewById(2131299158);
        this.zanIcon = (ImageView) this.itemView.findViewById(2131297529);
        this.zanIcon2 = (ImageView) this.itemView.findViewById(2131297530);
        this.llBtnComment = (LinearLayout) this.itemView.findViewById(2131297704);
        this.commentNum = (TextView) this.itemView.findViewById(2131298917);
        this.llBtnShare = (LinearLayout) this.itemView.findViewById(2131297779);
        this.shareNum = (TextView) this.itemView.findViewById(2131299073);
        this.goodsCard = (CardView) this.itemView.findViewById(2131296461);
        this.goodsImg = (ImageView) this.itemView.findViewById(2131296407);
        this.goodsTitle = (TextView) this.itemView.findViewById(2131296419);
        this.goodsDesc = (TextView) this.itemView.findViewById(2131296406);
        this.goodsPrice = (TextView) this.itemView.findViewById(2131296418);
        this.goodsOldPrice = (TextView) this.itemView.findViewById(2131296417);
        this.ivBtnPlay = (ImageView) this.itemView.findViewById(2131297359);
        this.ivBtnPlay2 = (ImageView) this.itemView.findViewById(2131297360);
        this.fullScreen = (TextView) this.itemView.findViewById(2131298899);
        this.llLandscapeInfo = (LinearLayout) this.itemView.findViewById(2131297733);
        this.ivLandscapeBack = (ImageView) this.itemView.findViewById(2131297418);
        this.tvLandscapeTitle = (TextView) this.itemView.findViewById(2131299000);
        this.llFreeTips = (LinearLayout) this.itemView.findViewById(2131297716);
        this.llComment = (LinearLayout) this.itemView.findViewById(2131297795);
        this.llBtnPreview = (LinearLayout) this.itemView.findViewById(2131297798);
        this.llBtnPreview2 = (LinearLayout) this.itemView.findViewById(2131297799);
        this.llBtnSetRing = (LinearLayout) this.itemView.findViewById(2131297777);
        this.llBtnSetRing2 = (LinearLayout) this.itemView.findViewById(2131297778);
        this.ivVideoRing = (ImageView) this.itemView.findViewById(2131297520);
        this.ivVideoRing2 = (ImageView) this.itemView.findViewById(2131297521);
        this.llPreviewRoot = (LinearLayout) this.itemView.findViewById(2131297764);
        this.ivPreviewBack = (ImageView) this.itemView.findViewById(2131297464);
        this.ivPreviewShare = (ImageView) this.itemView.findViewById(2131297465);
        this.tvPreviewSetRing = (TextView) this.itemView.findViewById(2131299055);
        this.likeView = (LikeView) this.itemView.findViewById(2131297988);
        this.ivBack = (ImageView) this.itemView.findViewById(2131297344);
        this.llMore = (LinearLayout) this.itemView.findViewById(2131297743);
        this.llMore2 = (LinearLayout) this.itemView.findViewById(2131297744);
        this.llVideoHelp = (LinearLayout) this.itemView.findViewById(2131297796);
        this.llVideoHelp2 = (LinearLayout) this.itemView.findViewById(2131297797);
        this.rl_comfort = (LinearLayout) this.itemView.findViewById(2131298331);
        this.llShu = (LinearLayout) this.itemView.findViewById(2131297750);
        this.llContentLayout = (LinearLayout) this.itemView.findViewById(2131296706);
        this.screenW = UIUtils.getScreenWidth((Activity) appCompatActivity);
        this.screenH = UIUtils.getScreenHeight(appCompatActivity, UIUtils.ScreenHeightMode.FullScreen);
        this.rlInfoArea = (RelativeLayout) this.itemView.findViewById(2131298344);
        this.progressLine = (FrameLayout) this.itemView.findViewById(2131297007);
        initProgressBar();
    }

    public static /* synthetic */ void lambda$initData$0(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$1(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$2(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$3(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.zhuboxiangqing(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$4(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.shagnpinInfo(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$5(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.zhuanfa(smallVideoView.f18490i, data, false);
        }
    }

    public static /* synthetic */ void lambda$initData$6(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.pinglun(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$7(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.pinglun(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$8(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$9(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$10(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$11(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.dianzan(smallVideoView.f18490i, data);
        }
    }

    public static /* synthetic */ void lambda$initData$12(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.guanzhu(smallVideoView.f18490i, data);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$13(SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.backPortraitScreen();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$14(SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.backPortraitScreen();
        }
    }

    public static /* synthetic */ void lambda$initData$15(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        smallVideoView.root.setVisibility(8);
        smallVideoView.llPreviewRoot.setVisibility(0);
        smallVideoView.fullScreen.setVisibility(8);
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(true, data);
        }
    }

    public static /* synthetic */ void lambda$initData$16(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        smallVideoView.root.setVisibility(8);
        smallVideoView.llPreviewRoot.setVisibility(0);
        smallVideoView.fullScreen.setVisibility(8);
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(true, data);
        }
    }

    public static /* synthetic */ void lambda$initData$17(SmallVideoView smallVideoView, SmallVideoEntity.Data data, SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        smallVideoView.root.setVisibility(0);
        smallVideoView.llPreviewRoot.setVisibility(8);
        smallVideoView.fullScreen.setVisibility(data.isShowFull() ? 0 : 8);
        if (itemClickedListener != null) {
            itemClickedListener.onPreview(false, data);
        }
    }

    public static /* synthetic */ void lambda$initData$18(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.zhuanfa(smallVideoView.f18490i, data, true);
        }
    }

    public static /* synthetic */ void lambda$initData$19(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.setRing(smallVideoView.f18490i, data, "我知道了");
        }
    }

    public static /* synthetic */ void lambda$initData$20(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.setRing(smallVideoView.f18490i, data, "预览");
        }
    }

    public static /* synthetic */ void lambda$initData$21(SmallVideoView smallVideoView, SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.setRing(smallVideoView.f18490i, data, "预览");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$22(SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.back();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$23(SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.back();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$24(SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.showMore(data);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$25(SmallVideoAdapter.ItemClickedListener itemClickedListener, SmallVideoEntity.Data data, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.showMore(data);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$26(SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.showHelp();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$initData$27(SmallVideoAdapter.ItemClickedListener itemClickedListener, View view) {
        if (itemClickedListener != null) {
            itemClickedListener.showHelp();
        }
    }

    public void setCanShowFull(boolean z) {
        SmallVideoEntity.Data data = this.entity;
        if (data != null) {
            data.setShowFull(z);
        }
    }

    public SmallVideoEntity.Data getData() {
        return this.entity;
    }

    public void setPlayStatus(boolean z, boolean z2) {
        this.isPlaying = z;
        this.ivBtnPlay.setVisibility(8);
        this.ivBtnPlay2.setVisibility(8);
        if (z2) {
            this.ivBtnPlay2.setVisibility(z ? 8 : 0);
        } else {
            this.ivBtnPlay.setVisibility(z ? 8 : 0);
        }
    }

    public boolean getPlayStatus() {
        return this.isPlaying;
    }

    public void setDoubleClickListener(LikeView.TouchCallBack touchCallBack) {
        this.doubleClickListener = touchCallBack;
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
        this.ivBarLandscapeBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$SmallVideoView$48Yv4DRoi39ZiVEus0ciYskWO7M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                SmallVideoView.this.clickPlayOrPause();
            }
        });
        setProgressBarLandscape(false);
        clearProgress();
        this.sbProgress.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.SmallVideoView.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
                if (SmallVideoView.this.tvStartTime != null) {
                    SmallVideoView.this.tvStartTime.setText(NumUtils.stringForTime(i));
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                SmallVideoView.this.isStartTrackingTouch = true;
                SmallVideoView.this.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(SmallVideoView.this.activityContext, 2131232586));
                SmallVideoView.this.llProgressTextLine.setVisibility(0);
                if (SmallVideoView.this.rlInfoArea != null) {
                    SmallVideoView.this.rlInfoArea.setVisibility(8);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Tracker.onStopTrackingTouch(seekBar);
                SmallVideoView.this.isStartTrackingTouch = false;
                SmallVideoView.this.sbProgress.setProgressDrawableTiled(ContextCompat.getDrawable(SmallVideoView.this.activityContext, 2131232576));
                SmallVideoView.this.llProgressTextLine.setVisibility(8);
                if (SmallVideoView.this.rlInfoArea != null) {
                    SmallVideoView.this.rlInfoArea.setVisibility(0);
                }
                if (SmallVideoView.this.bdCloudVideoView != null) {
                    SmallVideoView.this.bdCloudVideoView.seekTo(SmallVideoView.this.sbProgress.getProgress());
                }
            }
        });
        this.playProgressUpdater = Observable.interval(200L, TimeUnit.MILLISECONDS).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$SmallVideoView$xYUi5pNehdT5Dl4TnMNFIyw3Gmg
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                SmallVideoView.lambda$initProgressBar$29(SmallVideoView.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$initProgressBar$29(SmallVideoView smallVideoView, Long l) throws Exception {
        BDCloudVideoView bDCloudVideoView;
        SeekBar seekBar = smallVideoView.sbProgress;
        if (seekBar != null && seekBar.getMax() > 0 && (bDCloudVideoView = smallVideoView.bdCloudVideoView) != null) {
            if (smallVideoView.isStartTrackingTouch) {
                return;
            }
            smallVideoView.sbProgress.setProgress(bDCloudVideoView.getCurrentPosition());
            return;
        }
        smallVideoView.setProgressBarMax();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickPlayOrPause() {
        BDCloudVideoView bDCloudVideoView = this.bdCloudVideoView;
        if (bDCloudVideoView != null) {
            if (bDCloudVideoView.isPlaying()) {
                this.bdCloudVideoView.pause();
                AudienceActivity audienceActivity = this.activityContext;
                if (audienceActivity.getScreenOrientation(audienceActivity) == 0) {
                    this.ivBtnPlay2.setVisibility(0);
                    this.ivBtnPlay.setVisibility(8);
                } else if (this.bdCloudVideoView.getVideoWidth() > this.bdCloudVideoView.getVideoHeight()) {
                    this.ivBtnPlay2.setVisibility(8);
                    this.ivBtnPlay.setVisibility(0);
                } else {
                    this.ivBtnPlay2.setVisibility(0);
                    this.ivBtnPlay.setVisibility(8);
                }
                this.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(this.activityContext, 2131232592));
                return;
            }
            this.bdCloudVideoView.start();
            this.ivBtnPlay.setVisibility(8);
            this.ivBtnPlay2.setVisibility(8);
            this.ivBarLandscapeBtn.setImageDrawable(ContextCompat.getDrawable(this.activityContext, 2131232593));
        }
    }

    private void addProgressTo(FrameLayout frameLayout) {
        View view;
        MsLogUtil.m7979d("SmallVideoView", "进度条加入布局");
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
