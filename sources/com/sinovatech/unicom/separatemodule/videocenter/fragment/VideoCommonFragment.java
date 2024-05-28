package com.sinovatech.unicom.separatemodule.videocenter.fragment;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.billy.android.swipe.SmartSwipeRefresh;
import com.billy.android.swipe.refresh.ClassicHeader;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.nineoldandroids.animation.ObjectAnimator;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.basic.view.CustomePorgressDialog;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SharePreferenceUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.RecommendEntity;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.audience.view.VideoCommonMoreDialog;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.jubao.VideoDetailsJBActivity;
import com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.video.utils.ToastUtil;
import com.sinovatech.unicom.separatemodule.videocenter.OnPausePlayListener;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.sinovatech.unicom.separatemodule.videocenter.entity.AdViewBean;
import com.sinovatech.unicom.separatemodule.videocenter.entity.DislikeParam;
import com.sinovatech.unicom.separatemodule.videocenter.entity.NewsInfoEntity;
import com.sinovatech.unicom.separatemodule.videocenter.entity.NewsInfoShare;
import com.sinovatech.unicom.separatemodule.videocenter.entity.ShowAdViewBean;
import com.sinovatech.unicom.separatemodule.videocenter.entity.VideoUserEntity;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
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
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoCommonFragment extends BaseVideoFragment {
    public static String AD_SHOW_TIME = "ad_show_time";
    public static String IS_SHOW_ADVIEW = "is_show_adview";
    public static String LIST_DATA_CACHE = "list_data_cache";
    public static final String TAG = "VideoCommonFragment";
    private String VIDEO_USER_FILE;
    private VideoCenterActivity activityContext;
    private ArrayList<AdViewBean> adViewBeanArrayList;
    private BaseQuickAdapter<NewsInfoEntity, BaseViewHolder> adapter;
    private BaseQuickAdapter<VideoUserEntity, BaseViewHolder> attentionAdapter;
    private ArrayList<String> bannerImgList;
    private ArrayList<String> bannerUrlList;

    /* renamed from: cf */
    private int f18632cf;
    private String channelId;

    /* renamed from: cl */
    private int f18633cl;
    private View errorView;
    private String groupId;
    private boolean hasShowBannerAD;
    private View header;
    private ImageView home_xuanfu_image;
    private boolean isAddBanner;
    private boolean isBackByAllFull;
    private boolean isShowFlotWindow;
    private ImageView ivArrow;
    private ImageView ivDataImg;
    private View ivLoading;
    private LinearLayoutManager layoutManager;

    /* renamed from: lf */
    private int f18634lf;
    private Disposable loader;
    private View loadingBg;
    private View loadingView;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private MyImageLoader myImageLoader;
    private String newsId;
    private ObjectAnimator objectAnimator;
    private String pageName;

    /* renamed from: pd */
    private CustomePorgressDialog f18635pd;
    private int playIndex;
    private View playStatus;
    private RecyclerView rlvVideo;
    private ArrayList<ShowAdViewBean> showPositionBeanList;
    private SmartSwipeRefresh smartSwipeRefresh;
    private long startTime;
    private Disposable timer;
    private TextView tvDataText;
    private int currentPosition = -1;
    private int clickedPosition = -1;
    private Gson gson = new Gson();
    private int[] positionArr = {4, 8, 14, 22, 32};
    private int nowAdViewShowSize = 0;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$finalExecution$2(String str) throws Exception {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$share$11(String str) throws Exception {
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected int setContentView() {
        return 2131493309;
    }

    public String getPageName() {
        return this.pageName;
    }

    public void setPageName(String str) {
        this.pageName = str;
    }

    public void setCode(String str) {
        try {
            this.groupId = str;
            this.channelId = str;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCode() {
        return this.channelId;
    }

    private void loadBanner() {
        try {
            this.bannerImgList = new ArrayList<>();
            this.bannerUrlList = new ArrayList<>();
            HashMap hashMap = new HashMap();
            hashMap.put("version", getString(2131886969));
            App.getAsyncHttpClient().post(URLSet.getBannerViewData(), hashMap, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.1
                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onSuccess(int i, String str) {
                    JSONArray optJSONArray;
                    super.onSuccess(i, str);
                    try {
                        if (TextUtils.isEmpty(str)) {
                            return;
                        }
                        JSONObject jSONObject = new JSONObject(str);
                        if (!jSONObject.optString("statusCode").equals("0000") || (optJSONArray = jSONObject.optJSONArray("data")) == null || optJSONArray.length() <= 0) {
                            return;
                        }
                        VideoCommonFragment.this.setBannerData(optJSONArray);
                        VideoCommonFragment.this.addBanner();
                        VideoCommonFragment.this.isAddBanner = true;
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.loopj.android.http.AsyncHttpResponseHandler
                public void onFailure(Throwable th, String str) {
                    super.onFailure(th, str);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBannerData(JSONArray jSONArray) {
        for (int i = 0; i < jSONArray.length(); i++) {
            JSONObject optJSONObject = jSONArray.optJSONObject(i);
            String optString = optJSONObject.optString("activityJumpUrl");
            String optString2 = optJSONObject.optString("activityImg");
            this.bannerUrlList.add(optString);
            this.bannerImgList.add(optString2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addBanner() {
        BaseQuickAdapter<NewsInfoEntity, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null || baseQuickAdapter.getData().size() <= 3 || this.bannerImgList.size() <= 0) {
            return;
        }
        this.adapter.getData().get(1).setBannerImgList(this.bannerImgList);
        this.adapter.getData().get(1).setBannerUrlList(this.bannerUrlList);
        this.adapter.notifyItemChanged(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadDrawAdView() {
        if (OptionValveUtil.INSTENCE.isShowTuiJianWeiShiPinAdv()) {
            this.adViewBeanArrayList = new ArrayList<>();
            MsLogUtil.m7979d("ADLog", "开始加载广告");
            for (final int i = 0; i < this.nowAdViewShowSize; i++) {
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setBannerWidth(360);
                adConfigEntity.setCodeId("947100069");
                AdFactory.getAd(this.activityContext, adConfigEntity).loadInteraction(new IAdInterface.IInteractionCallBack() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.2
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onResult(int i2, View view) {
                        if (view != null) {
                            AdViewBean adViewBean = new AdViewBean();
                            adViewBean.setPosition(VideoCommonFragment.this.positionArr[i]);
                            adViewBean.setView(view);
                            VideoCommonFragment.this.adViewBeanArrayList.add(adViewBean);
                        }
                        MsLogUtil.m7979d("ADLog", "返回码：" + i2 + "已加载广告:" + VideoCommonFragment.this.adViewBeanArrayList.size() + "条");
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onClose() {
                        MsLogUtil.m7979d("ADLog", "onClose");
                    }
                });
            }
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected void lazyLoad() {
        try {
            if (this.loader != null) {
                this.loader.dispose();
                this.loader = null;
            }
            this.loader = Observable.timer(200L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$8x-kODs02_Y6goS4ma0aoCW3d6g
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$lazyLoad$0(VideoCommonFragment.this, (Long) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$lazyLoad$0(VideoCommonFragment videoCommonFragment, Long l) throws Exception {
        videoCommonFragment.getData();
        Disposable disposable = videoCommonFragment.loader;
        if (disposable != null) {
            disposable.dispose();
            videoCommonFragment.loader = null;
        }
    }

    private void getData() {
        try {
            registerPlayAndPauseListener();
            if (this.isBackByAllFull) {
                UIUtils.logD("xcyLoadData", "由全屏返回：" + this.groupId + "|" + this.activityContext.getCurrentShowCode());
                this.isBackByAllFull = false;
            } else if (this.activityContext.getCurrentShowCode().equals(this.channelId)) {
                UIUtils.logD("xcyChannel", "相同：" + this.channelId);
                refreshList();
                finalExecution();
            } else {
                setCode(this.activityContext.getCurrentTab());
                UIUtils.logD("xcyChannel", "不同：当前=" + this.channelId + "|选中=" + this.activityContext.getCurrentShowCode());
                if (isUpdateDing()) {
                    updateUserFollow();
                    if (this.activityContext.getCurrentShowCode().replace("拦截", "").equals(this.channelId)) {
                        UIUtils.logD("xcyChannel", "播放频道：" + this.channelId);
                        this.timer = Observable.timer(1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$d76kZtSCYaYKtkTRmfJXiyKFk9Q
                            @Override // io.reactivex.functions.Consumer
                            public final void accept(Object obj) {
                                VideoCommonFragment.lambda$getData$1(VideoCommonFragment.this, (Long) obj);
                            }
                        });
                    }
                } else if (this.activityContext.isTabsRefresh) {
                    UIUtils.logD("xcyChannel", "不同：tab刷新了");
                    if (isAttentionChannel()) {
                        List<VideoUserEntity> list = VideoCenterActivity.attentionUsers;
                        if (list != null && list.size() != 0) {
                            if (this.header == null) {
                                initAttentionView();
                            } else {
                                refreshAttentionList();
                            }
                            getContentData(this.groupId, false, null);
                            this.currentPosition = -1;
                        }
                        this.adapter.removeAllHeaderView();
                        this.attentionAdapter = null;
                        getContentData(this.groupId, false, null);
                        this.currentPosition = -1;
                    } else {
                        UIUtils.logD("xcyC", "不同：" + this.channelId + "|非关注");
                        List<NewsInfoEntity> loadDataCache = loadDataCache();
                        if (loadDataCache != null && loadDataCache.size() != 0) {
                            setData(loadDataCache);
                        }
                        getContentData(this.groupId, false, null);
                    }
                    this.activityContext.isTabsRefresh = false;
                } else {
                    getContentData(this.activityContext.getCurrentTab(), false, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$getData$1(VideoCommonFragment videoCommonFragment, Long l) throws Exception {
        if ("Wifi".equals(DeviceHelper.getNETType(videoCommonFragment.activityContext))) {
            videoCommonFragment.playVideo(videoCommonFragment.f18634lf, false);
        }
        Disposable disposable = videoCommonFragment.timer;
        if (disposable != null) {
            disposable.dispose();
            videoCommonFragment.timer = null;
        }
    }

    private void finalExecution() throws Exception {
        try {
            UIUtils.logD("xcyChannel", "刷新or加载缓存逻辑处理结束，埋点；当前频道：" + this.groupId);
            this.activityContext.interceptorRequest(this.channelId);
            this.startTime = System.currentTimeMillis();
            this.newsId = "";
            if (this.adapter != null && this.adapter.getData().size() > 0) {
                NewsInfoEntity newsInfoEntity = this.adapter.getData().get(0);
                if (newsInfoEntity != null) {
                    VideoCenterActivity.liuZPTLog.setLogByCommon("", "", "负一屏首页", "2", "浏览页面", "1", newsInfoEntity.getTitle(), "浏览页面", this.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), "0", "0", newsInfoEntity.getVideo_duration(), this.activityContext.getCurrentTab(), this.activityContext.getCurrentTabName(), "");
                } else {
                    VideoCenterActivity.liuZPTLog.setLogByCommon("", "", "负一屏首页", "2", "浏览页面", "1", this.activityContext.getCurrentTabName(), "浏览页面", this.activityContext.getCurrentTabName(), "", "0", "0", "", this.activityContext.getCurrentTab(), this.activityContext.getCurrentTabName(), "");
                }
            } else {
                VideoCenterActivity.liuZPTLog.setLogByCommon("", "", "负一屏首页", "2", "浏览页面", "1", this.activityContext.getCurrentTabName(), "浏览页面", this.activityContext.getCurrentTabName(), "", "0", "0", "", this.activityContext.getCurrentTab(), this.activityContext.getCurrentTabName(), "");
            }
            this.managerAudienceLoadData.hiBoardLog("25", this.channelId).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$Jur8v_iL2E3-6q-SZ_oS_4PGrwc
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$finalExecution$2((String) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            VideoCenterActivity.liuZPTLog.setLogByCommon("", "", "负一屏首页", "2", "浏览页面", "1", this.activityContext.getCurrentTabName(), "浏览页面", this.activityContext.getCurrentTabName(), "", "0", "0", "", this.activityContext.getCurrentTab(), this.activityContext.getCurrentTabName(), "");
        }
    }

    private void registerPlayAndPauseListener() throws Exception {
        this.activityContext.setOnPausePlayListener(new OnPausePlayListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.3
            @Override // com.sinovatech.unicom.separatemodule.videocenter.OnPausePlayListener
            public void onPauseClicked() {
                try {
                    Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
                    String str = tag == null ? "" : (String) tag;
                    String str2 = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
                    int i = VideoCommonFragment.this.currentPosition > -1 ? VideoCommonFragment.this.currentPosition : 0;
                    if (VideoCommonFragment.this.adapter == null || VideoCommonFragment.this.adapter.getData().size() <= 0) {
                        return;
                    }
                    NewsInfoEntity newsInfoEntity = (NewsInfoEntity) VideoCommonFragment.this.adapter.getData().get(i);
                    VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击暂停", VideoCommonFragment.this.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), VideoCommonFragment.this.activityContext.getCurrentTab(), VideoCommonFragment.this.activityContext.getCurrentTabName(), "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.videocenter.OnPausePlayListener
            public void onPlayClicked() {
                try {
                    Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
                    String str = tag == null ? "" : (String) tag;
                    int i = VideoCommonFragment.this.currentPosition > -1 ? VideoCommonFragment.this.currentPosition : 0;
                    if (VideoCommonFragment.this.adapter == null || VideoCommonFragment.this.adapter.getData().size() <= 0) {
                        return;
                    }
                    NewsInfoEntity newsInfoEntity = (NewsInfoEntity) VideoCommonFragment.this.adapter.getData().get(i);
                    VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击播放", VideoCommonFragment.this.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), "0", "0", newsInfoEntity.getVideo_duration(), VideoCommonFragment.this.activityContext.getCurrentTab(), VideoCommonFragment.this.activityContext.getCurrentTabName(), "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.videocenter.OnPausePlayListener
            public void onFullScreenClicked() {
                try {
                    Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
                    String str = tag == null ? "" : (String) tag;
                    String str2 = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
                    int i = VideoCommonFragment.this.currentPosition > -1 ? VideoCommonFragment.this.currentPosition : 0;
                    if (VideoCommonFragment.this.adapter == null || VideoCommonFragment.this.adapter.getData().size() <= 0) {
                        return;
                    }
                    NewsInfoEntity newsInfoEntity = (NewsInfoEntity) VideoCommonFragment.this.adapter.getData().get(i);
                    VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击全屏", VideoCommonFragment.this.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), VideoCommonFragment.this.activityContext.getCurrentTab(), VideoCommonFragment.this.activityContext.getCurrentTabName(), "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override // com.sinovatech.unicom.separatemodule.videocenter.OnPausePlayListener
            public void onAllTabsClicked() {
                try {
                    Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
                    String str = tag == null ? "" : (String) tag;
                    String str2 = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
                    int i = VideoCommonFragment.this.currentPosition > -1 ? VideoCommonFragment.this.currentPosition : 0;
                    if (VideoCommonFragment.this.adapter == null || VideoCommonFragment.this.adapter.getData().size() <= 0) {
                        return;
                    }
                    NewsInfoEntity newsInfoEntity = (NewsInfoEntity) VideoCommonFragment.this.adapter.getData().get(i);
                    VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击全部频道", VideoCommonFragment.this.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), VideoCommonFragment.this.activityContext.getCurrentTab(), VideoCommonFragment.this.activityContext.getCurrentTabName(), "");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void refreshList() throws Exception {
        if (isAttentionChannel()) {
            List<VideoUserEntity> list = VideoCenterActivity.attentionUsers;
            if (list == null || list.size() == 0) {
                this.adapter.removeAllHeaderView();
                this.attentionAdapter = null;
            } else if (this.header == null) {
                initAttentionView();
            } else {
                refreshAttentionList();
            }
            getContentData(this.groupId, false, null);
            this.currentPosition = -1;
            return;
        }
        UIUtils.logD("xcyC", "相同：" + this.channelId + "|非关注");
        BaseQuickAdapter<NewsInfoEntity, BaseViewHolder> baseQuickAdapter = this.adapter;
        if (baseQuickAdapter == null || baseQuickAdapter.getData().size() == 0) {
            List<NewsInfoEntity> loadDataCache = loadDataCache();
            if (loadDataCache == null || loadDataCache.size() == 0) {
                getContentData(this.groupId, false, null);
                return;
            } else {
                setData(loadDataCache);
                return;
            }
        }
        rePlay();
    }

    private void rePlay() {
        UIUtils.logD("xcyChannel", "播放频道：" + this.channelId);
        this.timer = Observable.timer(1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$YcB1OH6HBuganZvkLch4PguNiaI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCommonFragment.lambda$rePlay$3(VideoCommonFragment.this, (Long) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$rePlay$3(VideoCommonFragment videoCommonFragment, Long l) throws Exception {
        if ("Wifi".equals(DeviceHelper.getNETType(videoCommonFragment.activityContext))) {
            videoCommonFragment.playVideo(videoCommonFragment.f18634lf, false);
        }
        Disposable disposable = videoCommonFragment.timer;
        if (disposable != null) {
            disposable.dispose();
            videoCommonFragment.timer = null;
        }
    }

    private boolean isUpdateDing() {
        return this.clickedPosition > -1;
    }

    private void setData(List<NewsInfoEntity> list) {
        try {
            this.adapter.setNewData(list);
            updateUserFollow();
            this.activityContext.setTempGone();
            if (this.activityContext.getCurrentShowCode().replace("拦截", "").equals(this.channelId)) {
                UIUtils.logD("xcyLoadData", "播放频道" + this.channelId);
                this.timer = Observable.timer(1000L, TimeUnit.MILLISECONDS).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$ux3DwQ_DVZvTc1M6w4dOg5YsCUg
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        VideoCommonFragment.lambda$setData$4(VideoCommonFragment.this, (Long) obj);
                    }
                });
            }
            saveDataCache(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$setData$4(VideoCommonFragment videoCommonFragment, Long l) throws Exception {
        if ("Wifi".equals(DeviceHelper.getNETType(videoCommonFragment.activityContext))) {
            videoCommonFragment.playVideo(videoCommonFragment.adapter.getData().size() > 2 ? 1 : 0, false);
        }
        Disposable disposable = videoCommonFragment.timer;
        if (disposable != null) {
            disposable.dispose();
            videoCommonFragment.timer = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadData(int i) {
        try {
            if (i >= this.adapter.getData().size()) {
                i = this.adapter.getData().size() - 1;
            }
            if (i < 0) {
                i = 0;
            }
            for (int i2 = 0; i2 <= i; i2++) {
                if (this.adapter.getData().size() > 0) {
                    NewsInfoEntity newsInfoEntity = this.adapter.getData().get(i2);
                    if (!newsInfoEntity.isUpload()) {
                        VideoCenterActivity.hiBoardLog.hiBoardLogShow(newsInfoEntity.getGroup_id_str(), "", this.channelId);
                        newsInfoEntity.setUpload(true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateUserFollow() {
        int i;
        try {
            UIUtils.logD("xcyLoadData", "updateUserFollow========0");
            for (NewsInfoEntity newsInfoEntity : this.adapter.getData()) {
                newsInfoEntity.setFollow(this.activityContext.isAttention(newsInfoEntity.getUser_id()));
            }
            Iterator<NewsInfoEntity> it = this.adapter.getData().iterator();
            while (true) {
                i = 0;
                if (!it.hasNext()) {
                    break;
                }
                NewsInfoEntity next = it.next();
                if (VideoCenterActivity.dingCountMap.containsKey(next.getGroup_id_str())) {
                    next.setDigg_count(VideoCenterActivity.dingCountMap.get(next.getGroup_id_str()));
                    Map<String, String> map = VideoCenterActivity.dingCountMap;
                    StringBuilder sb = new StringBuilder();
                    sb.append(next.getGroup_id_str());
                    sb.append("ding");
                    next.setDing(TextUtils.isEmpty(map.get(sb.toString())) ? false : true);
                    VideoCenterActivity.dingCountMap.remove(next.getGroup_id_str());
                    VideoCenterActivity.dingCountMap.remove(next.getGroup_id_str() + "ding");
                }
            }
            if (this.clickedPosition > -1) {
                UIUtils.logD("xcy", "updateUserFollow========1");
                View findViewByPosition = this.layoutManager.findViewByPosition(this.clickedPosition + this.adapter.getHeaderLayoutCount());
                if (findViewByPosition != null) {
                    TextView textView = (TextView) findViewByPosition.findViewById(2131298896);
                    TextView textView2 = (TextView) findViewByPosition.findViewById(2131298900);
                    if (textView != null && textView2 != null) {
                        boolean isFollow = this.adapter.getData().get(this.clickedPosition).isFollow();
                        textView.setVisibility(isFollow ? 8 : 0);
                        if (!isFollow) {
                            i = 8;
                        }
                        textView2.setVisibility(i);
                    } else {
                        UIUtils.logD("xcy", "attentionView == null");
                    }
                    NewsInfoEntity newsInfoEntity2 = this.adapter.getData().get(this.clickedPosition);
                    TextView textView3 = (TextView) findViewByPosition.findViewById(2131298991);
                    ImageView imageView = (ImageView) findViewByPosition.findViewById(2131297410);
                    if (textView3 != null) {
                        textView3.setText(NumUtils.getDianZanNum(newsInfoEntity2.getDigg_count()));
                    }
                    if (imageView != null) {
                        imageView.setImageResource(newsInfoEntity2.isDing() ? 2131231696 : 2131231701);
                    }
                } else {
                    UIUtils.logD("xcy", "view == null");
                }
                this.clickedPosition = -1;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean isAttentionChannel() {
        return "guanzhu".equals(this.channelId);
    }

    private boolean checkDay() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String string = App.getSharePreferenceUtil().getString(AD_SHOW_TIME);
        App.getSharePreferenceUtil().putString(AD_SHOW_TIME, simpleDateFormat.format(new Date()));
        return (TextUtils.isEmpty(string) || string.equals(simpleDateFormat.format(new Date()))) ? false : true;
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    protected void initView() {
        try {
            this.hasShowBannerAD = false;
            this.activityContext = (VideoCenterActivity) getActivity();
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
            this.VIDEO_USER_FILE = "video_center_info_" + UserManager.getInstance().getCurrentPhoneNumber() + getResources().getString(2131886249);
            this.rlvVideo = (RecyclerView) findViewById(2131298414);
            this.layoutManager = new LinearLayoutManager(getActivity());
            this.rlvVideo.setLayoutManager(this.layoutManager);
            this.showPositionBeanList = new ArrayList<>();
            if (this.channelId == null) {
                this.channelId = "tuijian";
            }
            if (this.groupId == null) {
                this.groupId = this.channelId;
            }
            if (this.channelId.equals("tuijian")) {
                this.myImageLoader = new MyImageLoader();
                initNowShowAdSize();
                loadDrawAdView();
                setFloatWindow(0);
            }
            RecyclerView recyclerView = this.rlvVideo;
            BaseQuickAdapter<NewsInfoEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<NewsInfoEntity, BaseViewHolder>(2131493247) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.4
                /* JADX INFO: Access modifiers changed from: protected */
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                public void convert(BaseViewHolder baseViewHolder, final NewsInfoEntity newsInfoEntity) {
                    RelativeLayout relativeLayout = (RelativeLayout) baseViewHolder.getView(2131298307);
                    Banner banner = (Banner) baseViewHolder.getView(2131296501);
                    LinearLayout linearLayout = (LinearLayout) baseViewHolder.getView(2131297689);
                    relativeLayout.setVisibility(8);
                    linearLayout.setVisibility(8);
                    try {
                        if (newsInfoEntity.getBannerImgList() != null && newsInfoEntity.getBannerImgList().size() > 0) {
                            VideoCommonFragment.this.hasShowBannerAD = true;
                            linearLayout.setVisibility(0);
                            banner.setImages(newsInfoEntity.getBannerImgList()).setImageLoader(new ImageLoader() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.4.2
                                @Override // com.youth.banner.loader.ImageLoaderInterface
                                public void displayImage(Context context, Object obj, ImageView imageView) {
                                    GlideApp.with(App.getInstance()).load(obj).listener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.4.2.1
                                        @Override // com.bumptech.glide.request.RequestListener
                                        public boolean onResourceReady(Drawable drawable, Object obj2, Target<Drawable> target, DataSource dataSource, boolean z) {
                                            return false;
                                        }

                                        @Override // com.bumptech.glide.request.RequestListener
                                        public boolean onLoadFailed(@Nullable GlideException glideException, Object obj2, Target<Drawable> target, boolean z) {
                                            UIUtils.toast("banner加载错误：" + glideException.getMessage());
                                            return false;
                                        }
                                    }).into(imageView);
                                }
                            }).setDelayTime(5000).setImageLoader(VideoCommonFragment.this.myImageLoader).setOnBannerListener(new OnBannerListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.4.1
                                @Override // com.youth.banner.listener.OnBannerListener
                                public void OnBannerClick(int i) {
                                    IntentManager.generateIntentAndGo(VideoCommonFragment.this.activityContext, newsInfoEntity.getBannerUrlList().get(i));
                                }
                            }).setIndicatorGravity(800).start();
                        }
                        if (newsInfoEntity.getView() > -1 && VideoCommonFragment.this.adViewBeanArrayList != null && VideoCommonFragment.this.adViewBeanArrayList.size() > 0 && newsInfoEntity.getView() < VideoCommonFragment.this.adViewBeanArrayList.size()) {
                            AdViewBean adViewBean = (AdViewBean) VideoCommonFragment.this.adViewBeanArrayList.get(newsInfoEntity.getView());
                            if (adViewBean != null) {
                                relativeLayout.setVisibility(0);
                                relativeLayout.removeAllViews();
                                ViewGroup viewGroup = (ViewGroup) adViewBean.getView().getParent();
                                if (viewGroup != null) {
                                    viewGroup.removeView(adViewBean.getView());
                                }
                                relativeLayout.addView(adViewBean.getView());
                            } else {
                                relativeLayout.setVisibility(8);
                            }
                        } else {
                            relativeLayout.setVisibility(8);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    GlideApp.with((FragmentActivity) VideoCommonFragment.this.activityContext).load(newsInfoEntity.getAvatar_url()).placeholder(2131231245).error(2131231245).into((CircularImage) baseViewHolder.getView(2131297517));
                    baseViewHolder.setText(2131298976, newsInfoEntity.getName()).setText(2131298979, newsInfoEntity.getTitle()).setText(2131298975, NumUtils.getDianZanNum(newsInfoEntity.getComment_count())).setText(2131298991, NumUtils.getDianZanNum(newsInfoEntity.getDigg_count())).setImageResource(2131297410, newsInfoEntity.isDing() ? 2131231696 : 2131231701);
                    baseViewHolder.setGone(2131298896, !newsInfoEntity.isFollow()).setGone(2131298900, newsInfoEntity.isFollow());
                    GlideApp.with((FragmentActivity) VideoCommonFragment.this.activityContext).load(newsInfoEntity.getCoverImageUrl()).placeholder(2131231109).error(2131231109).into((ImageView) baseViewHolder.getView(2131297519));
                    StringBuilder sb = new StringBuilder();
                    sb.append(NumUtils.getBoFangNum(TextUtils.isEmpty(newsInfoEntity.getVideo_watch_count()) ? "0" : newsInfoEntity.getVideo_watch_count()));
                    sb.append("次播放");
                    baseViewHolder.setText(2131299054, sb.toString());
                    baseViewHolder.setText(2131299053, NumUtils.stringForTime(Integer.parseInt(TextUtils.isEmpty(newsInfoEntity.getVideo_duration()) ? "0" : newsInfoEntity.getVideo_duration()) * 1000));
                    FrameLayout frameLayout = (FrameLayout) baseViewHolder.getView(2131297011);
                    if (frameLayout != null) {
                        baseViewHolder.setGone(2131297762, frameLayout.getChildCount() == 0);
                    } else {
                        baseViewHolder.setGone(2131297762, true);
                    }
                    baseViewHolder.addOnClickListener(2131298896);
                    baseViewHolder.addOnClickListener(2131298900);
                    baseViewHolder.addOnClickListener(2131297358);
                    baseViewHolder.addOnClickListener(2131298976);
                    baseViewHolder.addOnClickListener(2131297517);
                    baseViewHolder.addOnClickListener(2131297731);
                    baseViewHolder.addOnClickListener(2131297519);
                    baseViewHolder.addOnClickListener(2131297454);
                    baseViewHolder.addOnClickListener(2131297730);
                }
            };
            this.adapter = baseQuickAdapter;
            recyclerView.setAdapter(baseQuickAdapter);
            this.adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$8LcFWWRjyuYrEBxspX4Feu9r19s
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                    VideoCommonFragment.lambda$initView$5(VideoCommonFragment.this, baseQuickAdapter2, view, i);
                }
            });
            this.adapter.setOnItemChildClickListener(new BaseQuickAdapter.OnItemChildClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$GurgHmZzMkk9xwDtaDunL_4uTcE
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemChildClickListener
                public final void onItemChildClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                    VideoCommonFragment.lambda$initView$6(VideoCommonFragment.this, baseQuickAdapter2, view, i);
                }
            });
            this.smartSwipeRefresh = SmartSwipeRefresh.behindMode(this.rlvVideo, false, false).setDataLoader(new SmartSwipeRefresh.SmartSwipeRefreshDataLoader() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.5
                @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
                public void onRefresh(SmartSwipeRefresh smartSwipeRefresh) {
                    if (TextUtils.isEmpty(VideoCommonFragment.this.groupId)) {
                        VideoCommonFragment videoCommonFragment = VideoCommonFragment.this;
                        videoCommonFragment.setCode(videoCommonFragment.activityContext.getCurrentTab());
                        if (TextUtils.isEmpty(VideoCommonFragment.this.groupId)) {
                            VideoCommonFragment.this.setCode("tuijian");
                        }
                    }
                    VideoCommonFragment.this.activityContext.play(null);
                    if (VideoCommonFragment.this.isAttentionChannel()) {
                        VideoCommonFragment.this.activityContext.getAttentionList();
                    }
                    VideoCommonFragment videoCommonFragment2 = VideoCommonFragment.this;
                    videoCommonFragment2.getContentData(videoCommonFragment2.groupId, false, VideoCommonFragment.this.smartSwipeRefresh);
                    if (VideoCommonFragment.this.channelId.equals("tuijian")) {
                        String string = App.getSharePreferenceUtil().getString(VideoCommonFragment.IS_SHOW_ADVIEW);
                        if (TextUtils.isEmpty(string)) {
                            string = "0";
                        }
                        VideoCommonFragment.this.nowAdViewShowSize = Integer.parseInt(string);
                        VideoCommonFragment.this.loadDrawAdView();
                        VideoCommonFragment.this.isAddBanner = false;
                    }
                }

                @Override // com.billy.android.swipe.SmartSwipeRefresh.SmartSwipeRefreshDataLoader
                public void onLoadMore(SmartSwipeRefresh smartSwipeRefresh) {
                    UIUtils.logD("xcyTest", "下拉刷新");
                }
            }).disableLoadMore().setHeader(new ClassicHeader(this.activityContext) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.6
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
            this.adapter.setOnLoadMoreListener(new BaseQuickAdapter.RequestLoadMoreListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$uJiG7ExugxCkhvFjeM669zF7FO0
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.RequestLoadMoreListener
                public final void onLoadMoreRequested() {
                    VideoCommonFragment.lambda$initView$7(VideoCommonFragment.this);
                }
            }, this.rlvVideo);
            this.rlvVideo.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.7
                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView2, int i) {
                    switch (i) {
                        case 0:
                            if (VideoCommonFragment.this.currentPosition < VideoCommonFragment.this.f18632cf || VideoCommonFragment.this.currentPosition > VideoCommonFragment.this.f18633cl) {
                                VideoCommonFragment.this.activityContext.play(null);
                                VideoCommonFragment.this.dismissPlayStatus();
                            }
                            if ("Wifi".equals(DeviceHelper.getNETType(VideoCommonFragment.this.activityContext))) {
                                VideoCommonFragment videoCommonFragment = VideoCommonFragment.this;
                                videoCommonFragment.playVideo(videoCommonFragment.f18634lf, false);
                            }
                            if (VideoCommonFragment.this.channelId.equals("tuijian")) {
                                VideoCommonFragment.this.setFloatWindow(0);
                                return;
                            }
                            return;
                        case 1:
                            if (VideoCommonFragment.this.channelId.equals("tuijian")) {
                                VideoCommonFragment.this.setFloatWindow(1);
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }

                @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
                public void onScrolled(@NonNull @NotNull RecyclerView recyclerView2, int i, int i2) {
                    VideoCommonFragment videoCommonFragment = VideoCommonFragment.this;
                    videoCommonFragment.f18632cf = videoCommonFragment.layoutManager.findFirstVisibleItemPosition();
                    VideoCommonFragment.this.layoutManager.findFirstCompletelyVisibleItemPosition();
                    VideoCommonFragment videoCommonFragment2 = VideoCommonFragment.this;
                    videoCommonFragment2.f18633cl = videoCommonFragment2.layoutManager.findLastVisibleItemPosition();
                    VideoCommonFragment videoCommonFragment3 = VideoCommonFragment.this;
                    videoCommonFragment3.f18634lf = videoCommonFragment3.layoutManager.findLastCompletelyVisibleItemPosition();
                    VideoCommonFragment videoCommonFragment4 = VideoCommonFragment.this;
                    videoCommonFragment4.uploadData(videoCommonFragment4.f18633cl);
                    if (VideoCommonFragment.this.channelId.equals("tuijian")) {
                        VideoCommonFragment.this.checkShowAdViewSize();
                        VideoCommonFragment.this.addAdView();
                    }
                }
            });
            initEmptyView();
            this.f18635pd = new CustomePorgressDialog(this.activityContext);
            this.f18635pd.setMessage("网络请求中");
            this.f18635pd.setCanceledOnTouchOutside(true);
            this.f18635pd.setCancelable(true);
            this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initView$5(VideoCommonFragment videoCommonFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        videoCommonFragment.clickedPosition = i;
        NewsInfoEntity newsInfoEntity = videoCommonFragment.adapter.getData().get(i);
        String str = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
        Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
        String json = VideoCenterActivity.liuZPTLog.getJson(tag == null ? "" : (String) tag, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击视频详情", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str, str, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
        videoCommonFragment.activityContext.play(null);
        VideoDetailsActivity.startVideoDetail(videoCommonFragment.activityContext, videoCommonFragment.adapter.getData().get(i).getGroup_id_str(), videoCommonFragment.groupId, videoCommonFragment.adapter.getData().get(i).isFollow(), false, json, newsInfoEntity.getCoverImageUrl());
    }

    public static /* synthetic */ void lambda$initView$6(VideoCommonFragment videoCommonFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        videoCommonFragment.clickedPosition = i;
        NewsInfoEntity newsInfoEntity = videoCommonFragment.adapter.getData().get(i);
        Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
        String str = tag == null ? "" : (String) tag;
        String str2 = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
        switch (view.getId()) {
            case 2131297358:
                videoCommonFragment.showMore(newsInfoEntity);
                VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击更多", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
                return;
            case 2131297454:
            case 2131297519:
                videoCommonFragment.playVideo(i, true);
                return;
            case 2131297517:
            case 2131298976:
                Intent intent = new Intent(videoCommonFragment.getActivity(), VideoUserActivity.class);
                intent.putExtra("user_id", newsInfoEntity.getUser_id());
                videoCommonFragment.startActivity(intent);
                VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "进入个人中心", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
                return;
            case 2131297730:
                String json = VideoCenterActivity.liuZPTLog.getJson(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击评论", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
                videoCommonFragment.activityContext.play(null);
                VideoDetailsActivity.startVideoDetail(videoCommonFragment.activityContext, newsInfoEntity.getGroup_id_str(), videoCommonFragment.groupId, newsInfoEntity.isFollow(), true, json, newsInfoEntity.getCoverImageUrl());
                return;
            case 2131297731:
                videoCommonFragment.share(newsInfoEntity.getTitle(), newsInfoEntity.getName(), newsInfoEntity.getShare_url(), newsInfoEntity.getCoverImageUrl(), newsInfoEntity.getGroup_id_str());
                VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "分享", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
                return;
            case 2131298896:
                videoCommonFragment.attentionUser(newsInfoEntity, i);
                VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击关注", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
                return;
            case 2131298900:
                videoCommonFragment.unAttentionUser(newsInfoEntity, i);
                VideoCenterActivity.liuZPTLog.setLogByCommon(str, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击取消关注", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str2, str2, newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
                return;
            default:
                return;
        }
    }

    public static /* synthetic */ void lambda$initView$7(VideoCommonFragment videoCommonFragment) {
        UIUtils.logD("xcyTest", "加载更多");
        if (TextUtils.isEmpty(videoCommonFragment.groupId)) {
            videoCommonFragment.setCode(videoCommonFragment.activityContext.getCurrentTab());
            if (TextUtils.isEmpty(videoCommonFragment.groupId)) {
                videoCommonFragment.setCode("tuijian");
            }
        }
        videoCommonFragment.getContentData(videoCommonFragment.groupId, true, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setFloatWindow(final int i) {
        this.home_xuanfu_image = (ImageView) findViewById(2131297214);
        final LinearLayout linearLayout = (LinearLayout) findViewById(2131297713);
        final ImageView imageView = (ImageView) findViewById(2131297213);
        this.managerAudienceLoadData.getFloatWindow().subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$hnxklG_mpC9RGZIKssRplGTkCiU
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCommonFragment.lambda$setFloatWindow$8(VideoCommonFragment.this, linearLayout, imageView, i, (BaseVideoEntity) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$setFloatWindow$8(VideoCommonFragment videoCommonFragment, final LinearLayout linearLayout, ImageView imageView, int i, final BaseVideoEntity baseVideoEntity) throws Exception {
        if (!baseVideoEntity.getStatusCode().equals("0000") || baseVideoEntity.getData() == null) {
            return;
        }
        if (!videoCommonFragment.isShowFlotWindow) {
            linearLayout.setVisibility(0);
        }
        GlideApp.with(videoCommonFragment.getContext()).load(((RecommendEntity) baseVideoEntity.getData()).getActivityImg()).into(videoCommonFragment.home_xuanfu_image);
        videoCommonFragment.home_xuanfu_image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                IntentManager.generateIntentAndGo(VideoCommonFragment.this.activityContext, ((RecommendEntity) baseVideoEntity.getData()).getActivityJumpUrl());
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                VideoCommonFragment.this.isShowFlotWindow = true;
                linearLayout.setVisibility(8);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        if (i == 1) {
            videoCommonFragment.objectAnimator = ObjectAnimator.ofFloat(videoCommonFragment.home_xuanfu_image, "translationX", 0.0f, 100.0f);
            videoCommonFragment.home_xuanfu_image.setAlpha(100);
            imageView.setVisibility(8);
            videoCommonFragment.objectAnimator.start();
        } else if (i == 0) {
            videoCommonFragment.home_xuanfu_image.setAlpha(255);
            imageView.setVisibility(0);
            videoCommonFragment.objectAnimator = ObjectAnimator.ofFloat(videoCommonFragment.home_xuanfu_image, "translationX", 100.0f, 0.0f);
            videoCommonFragment.objectAnimator.start();
        }
    }

    private void initNowShowAdSize() {
        if (checkDay()) {
            for (int i = 0; i < this.positionArr.length; i++) {
                ShowAdViewBean showAdViewBean = new ShowAdViewBean();
                showAdViewBean.setShow(false);
                showAdViewBean.setPosition(this.positionArr[i]);
                this.showPositionBeanList.add(showAdViewBean);
            }
            this.nowAdViewShowSize = this.showPositionBeanList.size();
            return;
        }
        String string = App.getSharePreferenceUtil().getString(IS_SHOW_ADVIEW);
        if (TextUtils.isEmpty(string)) {
            string = "5";
        }
        this.nowAdViewShowSize = Integer.parseInt(string);
        for (int i2 = 0; i2 < this.nowAdViewShowSize; i2++) {
            ShowAdViewBean showAdViewBean2 = new ShowAdViewBean();
            showAdViewBean2.setShow(false);
            showAdViewBean2.setPosition(this.positionArr[i2]);
            this.showPositionBeanList.add(showAdViewBean2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void addAdView() {
        try {
            if (this.adViewBeanArrayList != null) {
                for (int i = 0; i < this.adViewBeanArrayList.size(); i++) {
                    List<NewsInfoEntity> data = this.adapter.getData();
                    int position = this.adViewBeanArrayList.get(i).getPosition();
                    if (this.hasShowBannerAD && position > 0) {
                        position--;
                    }
                    if (data.size() > position && !data.get(position).isAddView()) {
                        data.get(position).setAddView(true);
                        data.get(position).setView(i);
                        this.adapter.notifyItemChanged(position);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void checkShowAdViewSize() {
        for (int i = 0; i < this.nowAdViewShowSize; i++) {
            try {
                if (this.f18633cl == this.positionArr[i]) {
                    for (int i2 = 0; i2 < this.showPositionBeanList.size(); i2++) {
                        if (this.showPositionBeanList.get(i2).getPosition() == this.f18633cl) {
                            this.showPositionBeanList.get(i2).setShow(true);
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        int i3 = 0;
        for (int i4 = 0; i4 < this.nowAdViewShowSize; i4++) {
            if (this.showPositionBeanList.get(i4).isShow()) {
                i3++;
                App.getSharePreferenceUtil().putString(IS_SHOW_ADVIEW, (this.nowAdViewShowSize - i3) + "");
            }
        }
    }

    private void initAttentionView() {
        try {
            if (isAttentionChannel()) {
                this.header = LayoutInflater.from(this.activityContext).inflate(2131493305, (ViewGroup) null);
                this.ivArrow = (ImageView) this.header.findViewById(2131297343);
                this.ivArrow.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$SyaZ0GG63k35x6Ii3mtfFetCBp4
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        VideoCommonFragment.lambda$initAttentionView$9(VideoCommonFragment.this, view);
                    }
                });
                RecyclerView recyclerView = (RecyclerView) this.header.findViewById(2131298398);
                recyclerView.setLayoutManager(new GridLayoutManager(this.activityContext, 5));
                BaseQuickAdapter<VideoUserEntity, BaseViewHolder> baseQuickAdapter = new BaseQuickAdapter<VideoUserEntity, BaseViewHolder>(2131493245) { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.10
                    /* JADX INFO: Access modifiers changed from: protected */
                    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
                    public void convert(BaseViewHolder baseViewHolder, VideoUserEntity videoUserEntity) {
                        GlideApp.with((FragmentActivity) VideoCommonFragment.this.activityContext).load(videoUserEntity.getAvatarUrl()).placeholder(2131231245).error(2131231245).into((CircularImage) baseViewHolder.getView(2131297517));
                        baseViewHolder.setText(2131299129, videoUserEntity.getName());
                    }
                };
                this.attentionAdapter = baseQuickAdapter;
                recyclerView.setAdapter(baseQuickAdapter);
                this.attentionAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$UB1GwaVsW_NV4MTtqxlsjJa90vM
                    @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
                    public final void onItemClick(BaseQuickAdapter baseQuickAdapter2, View view, int i) {
                        VideoCommonFragment.lambda$initAttentionView$10(VideoCommonFragment.this, baseQuickAdapter2, view, i);
                    }
                });
                refreshAttentionList();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initAttentionView$9(VideoCommonFragment videoCommonFragment, View view) {
        UIUtils.logD("xcy", "点击事件");
        if (videoCommonFragment.ivArrow.getTag() == null) {
            videoCommonFragment.ivArrow.setImageResource(2131231670);
            videoCommonFragment.ivArrow.setTag("展开");
            videoCommonFragment.attentionAdapter.setNewData(VideoCenterActivity.attentionUsers);
            return;
        }
        videoCommonFragment.ivArrow.setImageResource(2131231669);
        videoCommonFragment.ivArrow.setTag(null);
        videoCommonFragment.attentionAdapter.setNewData(VideoCenterActivity.attentionUsers.subList(0, 5));
    }

    public static /* synthetic */ void lambda$initAttentionView$10(VideoCommonFragment videoCommonFragment, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        try {
            Intent intent = new Intent(videoCommonFragment.getActivity(), VideoUserActivity.class);
            intent.putExtra("user_id", videoCommonFragment.attentionAdapter.getData().get(i).getUserId());
            videoCommonFragment.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void doRefresh() {
        SmartSwipeRefresh smartSwipeRefresh = this.smartSwipeRefresh;
        if (smartSwipeRefresh != null) {
            smartSwipeRefresh.getHeader().onReset();
            this.smartSwipeRefresh.startRefresh();
            return;
        }
        refresh();
    }

    private void refresh() {
        this.rlvVideo.scrollToPosition(0);
        if (TextUtils.isEmpty(this.groupId)) {
            this.groupId = "tuijian";
        }
        this.activityContext.play(null);
        if (isAttentionChannel()) {
            this.activityContext.getAttentionList();
        }
        getContentData(this.groupId, false, this.smartSwipeRefresh);
        if (this.channelId.equals("tuijian")) {
            String string = App.getSharePreferenceUtil().getString(IS_SHOW_ADVIEW);
            if (TextUtils.isEmpty(string)) {
                string = "0";
            }
            this.nowAdViewShowSize = Integer.parseInt(string);
            loadDrawAdView();
            this.isAddBanner = false;
        }
    }

    private void showMore(final NewsInfoEntity newsInfoEntity) {
        try {
            VideoCommonMoreDialog.show(this.activityContext, newsInfoEntity, new VideoCommonMoreDialog.CallBack() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.11
                @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoCommonMoreDialog.CallBack
                public void clicked1(Dialog dialog, NewsInfoEntity newsInfoEntity2) {
                    VideoCommonFragment.this.dislike(newsInfoEntity2);
                    dialog.dismiss();
                    Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
                    String str = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
                    VideoCenterActivity.liuZPTLog.setLogByCommon(tag == null ? "" : (String) tag, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity2.getTitle(), "点击不感兴趣", VideoCommonFragment.this.activityContext.getCurrentTabName(), newsInfoEntity2.getVideo_id(), str, str, newsInfoEntity2.getVideo_duration(), VideoCommonFragment.this.activityContext.getCurrentTab(), VideoCommonFragment.this.activityContext.getCurrentTabName(), "");
                }

                @Override // com.sinovatech.unicom.separatemodule.audience.view.VideoCommonMoreDialog.CallBack
                public void clicked2(Dialog dialog, NewsInfoEntity newsInfoEntity2) {
                    dialog.dismiss();
                    Object tag = VideoCenterActivity.bdCloudVideoView.getTag();
                    String str = VideoCenterActivity.bdCloudVideoView.getCurrentPosition() + "";
                    VideoDetailsJBActivity.startVideoDeailsJB(VideoCommonFragment.this.activityContext, newsInfoEntity.getGroup_id_str(), VideoCenterActivity.liuZPTLog.getJson(tag == null ? "" : (String) tag, "", "负一屏首页", "2", "点击按钮", "19", newsInfoEntity.getTitle(), "点击举报", VideoCommonFragment.this.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), str, str, newsInfoEntity.getVideo_duration(), VideoCommonFragment.this.activityContext.getCurrentTab(), VideoCommonFragment.this.activityContext.getCurrentTabName(), ""));
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void share(String str, String str2, String str3, String str4, String str5) {
        try {
            JSONObject jSONObject = new JSONObject();
            String str6 = "来自中国联通APP-" + str2;
            try {
                jSONObject.put("shareType", "url");
                jSONObject.put("shareTitle", str);
                jSONObject.put("shareContent", str6);
                jSONObject.put("shareURL", str3);
                jSONObject.put("shareIconURL", str4);
                jSONObject.put("miniProgramShare", "1");
                jSONObject.put("miniProgramType", "0");
                jSONObject.put("miniProgramUserName", "gh_2bab3e2deed1");
                jSONObject.put("miniProgramPath", "pages/videoshare/videoshare?groupId= " + str5);
            } catch (Exception e) {
                e.printStackTrace();
            }
            ShareManager.ShowShareDialog(this.activityContext, "shortmessage,wechat,wechatmoments,qq,qzone,sinaweibo", !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject), new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.12
                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onComplete(String str7) {
                    UIUtils.toast("成功");
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onCancel(String str7) {
                    UIUtils.toast("取消");
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                public void onError(String str7, String str8) {
                    UIUtils.toast("失败");
                }
            });
            this.managerAudienceLoadData.hiBoardLog("23", str5).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$SN-9iUe_iRK2AM9hnTmB863PmJA
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$share$11((String) obj);
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void initEmptyView() {
        try {
            View inflate = LayoutInflater.from(this.activityContext).inflate(2131493250, (ViewGroup) null);
            this.loadingView = inflate.findViewById(2131298335);
            this.loadingBg = inflate.findViewById(2131299475);
            this.ivLoading = inflate.findViewById(2131297370);
            this.errorView = inflate.findViewById(2131297706);
            this.ivDataImg = (ImageView) inflate.findViewById(2131297369);
            this.tvDataText = (TextView) inflate.findViewById(2131298924);
            this.errorView.setVisibility(8);
            this.loadingView.setVisibility(0);
            startAnim(this.ivLoading);
            this.adapter.setEmptyView(inflate);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void startAnim(View view) {
        try {
            view.setAnimation(AnimationUtils.loadAnimation(this.activityContext, 2130772001));
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getContentData(final String str, final boolean z, final SmartSwipeRefresh smartSwipeRefresh) {
        try {
            String contentDate = URLSet.getContentDate(str, UIUtils.getAppVersionName(this.activityContext));
            HashMap hashMap = new HashMap();
            hashMap.put("version", this.activityContext.getString(2131886969));
            if (!z) {
                hashMap.put("pageTag", "1");
            }
            UIUtils.logD("xcyTest", "tabsUrl-->" + contentDate);
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxGet(contentDate, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$CtA8ITHgiku4W71qHlOk8nwwNQo
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return VideoCommonFragment.lambda$getContentData$12(str, (String) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$xg_PPhKimIRwdrnIHB-cYj7MZ9M
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$getContentData$13(VideoCommonFragment.this, z, smartSwipeRefresh, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$A0uqr2zf2Pi7Kswu4R6ezNfgUJo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$getContentData$14(VideoCommonFragment.this, smartSwipeRefresh, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$getContentData$12(String str, String str2) throws Exception {
        UIUtils.logD("xcyTest", str + "推荐列表-->" + str2);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str2);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        baseVideoEntity.setCount(jSONObject.optString("nextPageNum"));
        String optString = jSONObject.optString("data");
        if (optString.startsWith("[")) {
            ArrayList arrayList = new ArrayList();
            JSONArray jSONArray = new JSONArray(optString);
            for (int i = 0; i < jSONArray.length(); i++) {
                JSONObject optJSONObject = jSONArray.optJSONObject(i);
                NewsInfoEntity newsInfoEntity = new NewsInfoEntity();
                newsInfoEntity.setGroup_id(optJSONObject.optLong("group_id"));
                newsInfoEntity.setGroup_id_str(optJSONObject.optString("group_id_str"));
                newsInfoEntity.setItem_id(optJSONObject.optString("item_id"));
                newsInfoEntity.setVideo_id(optJSONObject.optString("video_id"));
                newsInfoEntity.setTitle(optJSONObject.optString("title"));
                newsInfoEntity.setVideo_watch_count(optJSONObject.optString("video_watch_count"));
                newsInfoEntity.setVideo_duration(optJSONObject.optString("video_duration"));
                newsInfoEntity.setShare_url(optJSONObject.optString("share_url"));
                newsInfoEntity.setDigg_count(optJSONObject.optString("digg_count"));
                newsInfoEntity.setComment_count(optJSONObject.optString("comment_count"));
                newsInfoEntity.setStick(optJSONObject.optBoolean("is_stick"));
                JSONObject optJSONObject2 = optJSONObject.optJSONObject("user_info");
                if (optJSONObject2 != null) {
                    newsInfoEntity.setAvatar_url(optJSONObject2.optString("avatar_url"));
                    newsInfoEntity.setName(optJSONObject2.optString("name"));
                    newsInfoEntity.setFollow(optJSONObject2.optBoolean("follow"));
                    newsInfoEntity.setHome_page(optJSONObject2.optString("home_page"));
                    newsInfoEntity.setUser_id(optJSONObject2.optString("user_id"));
                }
                JSONArray optJSONArray = optJSONObject.optJSONArray("cover_image_list");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    newsInfoEntity.setCoverImageUrl(optJSONArray.optJSONObject(0).optString("url"));
                }
                arrayList.add(newsInfoEntity);
            }
            baseVideoEntity.setData(arrayList);
        }
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$getContentData$13(VideoCommonFragment videoCommonFragment, boolean z, SmartSwipeRefresh smartSwipeRefresh, BaseVideoEntity baseVideoEntity) throws Exception {
        CustomePorgressDialog customePorgressDialog = videoCommonFragment.f18635pd;
        if (customePorgressDialog != null && customePorgressDialog.isShowing()) {
            videoCommonFragment.f18635pd.dismiss();
        }
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            for (NewsInfoEntity newsInfoEntity : (List) baseVideoEntity.getData()) {
                newsInfoEntity.setFollow(videoCommonFragment.activityContext.isAttention(newsInfoEntity.getUser_id()));
            }
            if (z) {
                videoCommonFragment.adapter.addData((Collection) baseVideoEntity.getData());
            } else {
                videoCommonFragment.setData((List) baseVideoEntity.getData());
                if (smartSwipeRefresh != null) {
                    smartSwipeRefresh.finished(true);
                    videoCommonFragment.uploadData(videoCommonFragment.f18633cl);
                }
            }
            if (!videoCommonFragment.isAddBanner && videoCommonFragment.channelId.equals("tuijian")) {
                videoCommonFragment.loadBanner();
            }
            if ("1".equals(baseVideoEntity.getCount())) {
                videoCommonFragment.adapter.loadMoreComplete();
            } else {
                videoCommonFragment.adapter.loadMoreEnd();
            }
            videoCommonFragment.saveDataCache(true);
        } else {
            videoCommonFragment.adapter.loadMoreFail();
            ToastUtil.showToast(baseVideoEntity.getMessage());
        }
        videoCommonFragment.errorView.setVisibility(0);
        videoCommonFragment.ivDataImg.setImageResource(2131231689);
        videoCommonFragment.tvDataText.setText(videoCommonFragment.isAttentionChannel() ? "暂无关注\n快去关注你喜欢的作者吧～" : "很抱歉，\n没有数据可以显示。");
        videoCommonFragment.loadingView.setVisibility(8);
        videoCommonFragment.activityContext.play(null);
    }

    public static /* synthetic */ void lambda$getContentData$14(VideoCommonFragment videoCommonFragment, SmartSwipeRefresh smartSwipeRefresh, Throwable th) throws Exception {
        if (smartSwipeRefresh != null) {
            smartSwipeRefresh.finished(true);
        }
        UIUtils.logD(th.getMessage());
        videoCommonFragment.adapter.loadMoreFail();
        ToastUtil.showToast(th.getMessage());
        videoCommonFragment.errorView.setVisibility(0);
        videoCommonFragment.ivDataImg.setImageResource(2131231682);
        videoCommonFragment.tvDataText.setText("页面加载异常，请稍后再试");
        videoCommonFragment.loadingView.setVisibility(8);
        if (videoCommonFragment.f18635pd.isShowing()) {
            videoCommonFragment.f18635pd.dismiss();
        }
    }

    private void attentionUser(final NewsInfoEntity newsInfoEntity, int i) {
        try {
            View findViewByPosition = this.layoutManager.findViewByPosition(i + this.adapter.getHeaderLayoutCount());
            final TextView textView = (TextView) findViewByPosition.findViewById(2131298896);
            final TextView textView2 = (TextView) findViewByPosition.findViewById(2131298900);
            this.f18635pd.show();
            String attentionUser = URLSet.attentionUser(newsInfoEntity.getUser_id());
            UIUtils.logD("xcy", "关注-->" + attentionUser);
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(attentionUser, "").subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$akNvuu88JgabZ7awm3jediCLjzk
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return VideoCommonFragment.lambda$attentionUser$15(VideoCommonFragment.this, (String) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$-bOpx_eLPaqfp4oWgPSF_yN4Wu8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$attentionUser$16(VideoCommonFragment.this, textView, textView2, newsInfoEntity, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$NVr8GyfaC_KKBeq_t_GAl-MMPMw
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$attentionUser$17(VideoCommonFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ BaseVideoEntity lambda$attentionUser$15(VideoCommonFragment videoCommonFragment, String str) throws Exception {
        UIUtils.logD("xcy", videoCommonFragment.groupId + "关注-->" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$attentionUser$16(VideoCommonFragment videoCommonFragment, TextView textView, TextView textView2, NewsInfoEntity newsInfoEntity, BaseVideoEntity baseVideoEntity) throws Exception {
        videoCommonFragment.f18635pd.dismiss();
        ToastUtil.showToast("0000".equals(baseVideoEntity.getStatusCode()) ? "关注成功" : "关注失败");
        textView.setVisibility(8);
        textView2.setVisibility(0);
        newsInfoEntity.setFollow(true);
        VideoUserEntity videoUserEntity = new VideoUserEntity();
        videoUserEntity.setSchema(newsInfoEntity.getHome_page());
        videoUserEntity.setUserId(newsInfoEntity.getUser_id());
        videoUserEntity.setAvatarUrl(newsInfoEntity.getAvatar_url());
        videoUserEntity.setName(newsInfoEntity.getName());
        VideoCenterActivity.attentionUser(videoUserEntity, true);
        if (videoCommonFragment.isAttentionChannel() && videoCommonFragment.header != null) {
            videoCommonFragment.refreshAttentionList();
        } else {
            videoCommonFragment.initAttentionView();
        }
        if (videoCommonFragment.isAttentionChannel()) {
            videoCommonFragment.f18635pd.show();
            videoCommonFragment.getContentData(videoCommonFragment.groupId, false, null);
        }
    }

    public static /* synthetic */ void lambda$attentionUser$17(VideoCommonFragment videoCommonFragment, Throwable th) throws Exception {
        UIUtils.logD(th.getMessage());
        ToastUtil.showToast(th.getMessage());
        videoCommonFragment.f18635pd.dismiss();
    }

    private void unAttentionUser(final NewsInfoEntity newsInfoEntity, int i) {
        try {
            View findViewByPosition = this.layoutManager.findViewByPosition(i + this.adapter.getHeaderLayoutCount());
            final TextView textView = (TextView) findViewByPosition.findViewById(2131298896);
            final TextView textView2 = (TextView) findViewByPosition.findViewById(2131298900);
            this.f18635pd.show();
            String unFollow = URLSet.unFollow(newsInfoEntity.getUser_id());
            UIUtils.logD("xcy", "取消关注-->" + unFollow);
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(unFollow, "").subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$yfpoNQtmnwX-871MOQDeHq5lLvY
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return VideoCommonFragment.lambda$unAttentionUser$18(VideoCommonFragment.this, (String) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$UBBxT-mq8ibUtq1cOWphLrdz95U
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$unAttentionUser$19(VideoCommonFragment.this, textView, textView2, newsInfoEntity, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$vUq6yzuOXanFnb6afZa-2U4ShqU
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$unAttentionUser$20(VideoCommonFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ BaseVideoEntity lambda$unAttentionUser$18(VideoCommonFragment videoCommonFragment, String str) throws Exception {
        UIUtils.logD("xcy", videoCommonFragment.groupId + "取消关注-->" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$unAttentionUser$19(VideoCommonFragment videoCommonFragment, TextView textView, TextView textView2, NewsInfoEntity newsInfoEntity, BaseVideoEntity baseVideoEntity) throws Exception {
        videoCommonFragment.f18635pd.dismiss();
        ToastUtil.showToast("0000".equals(baseVideoEntity.getStatusCode()) ? "取消关注成功" : "取消关注失败");
        textView.setVisibility(0);
        textView2.setVisibility(8);
        newsInfoEntity.setFollow(false);
        VideoUserEntity videoUserEntity = new VideoUserEntity();
        videoUserEntity.setSchema(newsInfoEntity.getHome_page());
        videoUserEntity.setUserId(newsInfoEntity.getUser_id());
        videoUserEntity.setAvatarUrl(newsInfoEntity.getAvatar_url());
        videoUserEntity.setName(newsInfoEntity.getName());
        VideoCenterActivity.attentionUser(videoUserEntity, false);
        if (videoCommonFragment.isAttentionChannel() && videoCommonFragment.header != null) {
            videoCommonFragment.refreshAttentionList();
        } else {
            videoCommonFragment.initAttentionView();
        }
        if (videoCommonFragment.isAttentionChannel()) {
            videoCommonFragment.f18635pd.show();
            videoCommonFragment.activityContext.play(null);
            videoCommonFragment.dismissPlayStatus();
            videoCommonFragment.getContentData(videoCommonFragment.groupId, false, null);
        }
    }

    public static /* synthetic */ void lambda$unAttentionUser$20(VideoCommonFragment videoCommonFragment, Throwable th) throws Exception {
        UIUtils.logD(th.getMessage());
        ToastUtil.showToast(th.getMessage());
        videoCommonFragment.f18635pd.dismiss();
    }

    private void refreshAttentionList() {
        try {
            if (VideoCenterActivity.attentionUsers.size() == 0) {
                this.adapter.removeAllHeaderView();
                return;
            }
            if (this.adapter.getHeaderLayoutCount() == 0) {
                this.adapter.setHeaderView(this.header);
            }
            boolean z = VideoCenterActivity.attentionUsers != null && VideoCenterActivity.attentionUsers.size() > 5;
            this.ivArrow.setVisibility(z ? 0 : 8);
            ArrayList arrayList = new ArrayList();
            if (z) {
                arrayList.addAll(VideoCenterActivity.attentionUsers.subList(0, 5));
            } else {
                arrayList.addAll(VideoCenterActivity.attentionUsers);
            }
            this.attentionAdapter.setNewData(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dislike(final NewsInfoEntity newsInfoEntity) {
        try {
            this.f18635pd.show();
            DislikeParam dislikeParam = new DislikeParam();
            dislikeParam.setId(newsInfoEntity.getGroup_id_str());
            dislikeParam.setCategory(this.groupId);
            dislikeParam.setTimestamp(System.currentTimeMillis() / 1000);
            Gson gson = new Gson();
            String json = !(gson instanceof Gson) ? gson.toJson(dislikeParam) : NBSGsonInstrumentation.toJson(gson, dislikeParam);
            String dislike = URLSet.dislike();
            UIUtils.logD("xcy", "tabsUrl-->" + dislike);
            ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxPost(dislike, json).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$7BRB-_QZ4BTbrzmhleX0MOmNh8c
                @Override // io.reactivex.functions.Function
                public final Object apply(Object obj) {
                    return VideoCommonFragment.lambda$dislike$21(VideoCommonFragment.this, (String) obj);
                }
            }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$TamixisTq1l86CB-q6UAAFiq6DY
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$dislike$22(VideoCommonFragment.this, newsInfoEntity, (BaseVideoEntity) obj);
                }
            }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$8JCHsW_nltA-17adGByc4Ccu-Ok
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoCommonFragment.lambda$dislike$23(VideoCommonFragment.this, (Throwable) obj);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ BaseVideoEntity lambda$dislike$21(VideoCommonFragment videoCommonFragment, String str) throws Exception {
        UIUtils.logD("xcy", videoCommonFragment.groupId + "不感兴趣-->" + str);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$dislike$22(VideoCommonFragment videoCommonFragment, NewsInfoEntity newsInfoEntity, BaseVideoEntity baseVideoEntity) throws Exception {
        videoCommonFragment.f18635pd.dismiss();
        int indexOf = videoCommonFragment.adapter.getData().indexOf(newsInfoEntity) + videoCommonFragment.adapter.getHeaderLayoutCount();
        View findViewByPosition = videoCommonFragment.layoutManager.findViewByPosition(indexOf);
        int childCount = findViewByPosition != null ? ((FrameLayout) findViewByPosition.findViewById(2131297011)).getChildCount() : 0;
        videoCommonFragment.adapter.getData().remove(newsInfoEntity);
        videoCommonFragment.adapter.notifyItemRemoved(indexOf);
        if (childCount > 0) {
            videoCommonFragment.activityContext.play(null);
            videoCommonFragment.dismissPlayStatus();
            videoCommonFragment.playVideo(videoCommonFragment.f18634lf, false);
        }
    }

    public static /* synthetic */ void lambda$dislike$23(VideoCommonFragment videoCommonFragment, Throwable th) throws Exception {
        UIUtils.logD(th.getMessage());
        ToastUtil.showToast(th.getMessage());
        videoCommonFragment.f18635pd.dismiss();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void playVideo(int i, final boolean z) {
        int i2;
        if (i == -1) {
            try {
                i = this.layoutManager.findViewByPosition(this.f18632cf) == null ? this.f18633cl : this.f18632cf;
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        if (this.adapter.getData().size() == 0) {
            this.activityContext.play(null);
            return;
        }
        if (i >= this.adapter.getData().size()) {
            i = this.adapter.getData().size() - 1;
        }
        if (this.adapter.getHeaderLayoutCount() > 0) {
            i2 = z ? i : Math.max(i - this.adapter.getHeaderLayoutCount(), 0);
            i += z ? this.adapter.getHeaderLayoutCount() : 0;
            if (i >= this.adapter.getData().size()) {
                i = this.adapter.getData().size() - 1;
            }
        } else {
            i2 = i;
        }
        final NewsInfoEntity newsInfoEntity = this.adapter.getData().get(i2);
        final String group_id_str = newsInfoEntity.getGroup_id_str();
        if (i == this.currentPosition && VideoCenterActivity.bdCloudVideoView.isPlaying()) {
            UIUtils.logD("xcy", i + "当前正在播放" + group_id_str);
            return;
        }
        if (this.activityContext.isPlaying) {
            this.activityContext.play(null);
            dismissPlayStatus();
        }
        this.currentPosition = i;
        String videoPlayList = URLSet.getVideoPlayList(group_id_str);
        UIUtils.logD("xcy", "getVideoUrl-->" + videoPlayList);
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(10, 15, 10).rxGet(videoPlayList, null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new Function() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$QCPmG1WgTSD6Pemk7AEutahT6Nc
            @Override // io.reactivex.functions.Function
            public final Object apply(Object obj) {
                return VideoCommonFragment.lambda$playVideo$24(group_id_str, (String) obj);
            }
        }).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$14LRe_4WzY-OsQNHYdqK_0IymIk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoCommonFragment.lambda$playVideo$25(VideoCommonFragment.this, z, group_id_str, newsInfoEntity, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.-$$Lambda$VideoCommonFragment$TA9mLFHhd03Zg4xV4BvzJ7wKoLs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("xcyTest", ((Throwable) obj).getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ BaseVideoEntity lambda$playVideo$24(String str, String str2) throws Exception {
        JSONArray optJSONArray;
        UIUtils.logD("xcy", str + "视频详情-->" + str2);
        BaseVideoEntity baseVideoEntity = new BaseVideoEntity();
        JSONObject jSONObject = new JSONObject(str2);
        baseVideoEntity.setMessage(jSONObject.optString("message"));
        baseVideoEntity.setStatusCode(jSONObject.optString("statusCode"));
        JSONObject optJSONObject = jSONObject.optJSONObject("data");
        if (optJSONObject != null && (optJSONArray = optJSONObject.optJSONObject("video").optJSONArray("video_list")) != null && optJSONArray.length() > 0) {
            baseVideoEntity.setData(optJSONArray.getJSONObject(0).optString("main_url"));
        }
        return baseVideoEntity;
    }

    public static /* synthetic */ void lambda$playVideo$25(VideoCommonFragment videoCommonFragment, boolean z, String str, NewsInfoEntity newsInfoEntity, BaseVideoEntity baseVideoEntity) throws Exception {
        if (!z && !str.equals(videoCommonFragment.activityContext.stopItemId)) {
            VideoCenterActivity.hiBoardLog.hiBoardLogAutoPlay(str, "", videoCommonFragment.channelId, "list");
        }
        if (z) {
            videoCommonFragment.activityContext.hiBoardLogPlay(str);
        }
        VideoCenterActivity.liuZPTLog.setLogByCommon((String) baseVideoEntity.getData(), VideoCenterActivity.bdCloudVideoView.getTag() != null ? (String) VideoCenterActivity.bdCloudVideoView.getTag() : "", "负一屏首页", z ? "2" : "3", z ? "点击按钮" : "滑动", z ? "19" : "21", newsInfoEntity.getTitle(), z ? "点击播放" : "自动播放", videoCommonFragment.activityContext.getCurrentTabName(), newsInfoEntity.getVideo_id(), "0", "0", newsInfoEntity.getVideo_duration(), videoCommonFragment.activityContext.getCurrentTab(), videoCommonFragment.activityContext.getCurrentTabName(), "");
        videoCommonFragment.setPlayView((String) baseVideoEntity.getData(), videoCommonFragment.currentPosition, str, z);
    }

    public void showPlayStatus() {
        View view = this.playStatus;
        if (view != null) {
            view.setVisibility(8);
        }
    }

    public void dismissPlayStatus() {
        View view = this.playStatus;
        if (view != null) {
            view.setVisibility(0);
        }
    }

    private void setPlayView(String str, int i, String str2, boolean z) {
        try {
            View videoPlayer = this.activityContext.getVideoPlayer();
            ViewGroup viewGroup = (ViewGroup) videoPlayer.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            this.playIndex = i;
            ((FrameLayout) this.adapter.getViewByPosition(i, 2131297011)).addView(videoPlayer);
            this.playStatus = this.adapter.getViewByPosition(i, 2131297762);
            showPlayStatus();
            NewsInfoShare newsInfoShare = new NewsInfoShare();
            newsInfoShare.setName(this.adapter.getData().get(i).getName());
            newsInfoShare.setTitle(this.adapter.getData().get(i).getTitle());
            newsInfoShare.setCoverImageUrl(this.adapter.getData().get(i).getCoverImageUrl());
            newsInfoShare.setShare_url(this.adapter.getData().get(i).getShare_url());
            newsInfoShare.setGroup_id(this.adapter.getData().get(i).getGroup_id());
            VideoCenterActivity videoCenterActivity = this.activityContext;
            Gson gson = this.gson;
            videoCenterActivity.play(str, str2, z, !(gson instanceof Gson) ? gson.toJson(newsInfoShare) : NBSGsonInstrumentation.toJson(gson, newsInfoShare));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void back2List(View view) {
        try {
            UIUtils.logD("xcy", "back2list");
            FrameLayout frameLayout = (FrameLayout) this.layoutManager.findViewByPosition(this.playIndex).findViewById(2131297011);
            ViewGroup viewGroup = (ViewGroup) view.getParent();
            if (viewGroup != null) {
                viewGroup.removeAllViews();
            }
            frameLayout.addView(view);
            this.isBackByAllFull = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveDataCache(boolean z) {
        try {
            if (this.adapter != null) {
                Gson gson = this.gson;
                List<NewsInfoEntity> data = this.adapter.getData();
                String json = !(gson instanceof Gson) ? gson.toJson(data) : NBSGsonInstrumentation.toJson(gson, data);
                SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
                String str = this.VIDEO_USER_FILE;
                sharePreferenceUtil.putString(str, LIST_DATA_CACHE + this.channelId, json);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<NewsInfoEntity> loadDataCache() {
        try {
            SharePreferenceUtil sharePreferenceUtil = App.getSharePreferenceUtil();
            String str = this.VIDEO_USER_FILE;
            String string = sharePreferenceUtil.getString(str, LIST_DATA_CACHE + this.channelId);
            if (TextUtils.isEmpty(string)) {
                return null;
            }
            Gson gson = this.gson;
            Type type = new TypeToken<List<NewsInfoEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoCommonFragment.13
            }.getType();
            return (List) (!(gson instanceof Gson) ? gson.fromJson(string, type) : NBSGsonInstrumentation.fromJson(gson, string, type));
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment, com.sinovatech.unicom.basic.p315ui.fragment.BaseFragment, android.support.p083v4.app.Fragment
    public void onPause() {
        super.onPause();
        saveDataCache(false);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class MyImageLoader extends ImageLoader {
        private MyImageLoader() {
        }

        @Override // com.youth.banner.loader.ImageLoaderInterface
        public void displayImage(Context context, Object obj, ImageView imageView) {
            GlideApp.with(context).load(obj).into(imageView);
        }
    }
}
