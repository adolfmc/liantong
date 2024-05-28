package com.sinovatech.unicom.separatemodule.gamecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.content.ContextCompat;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.ScaleAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.p315ui.share.WebMenuManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.HotGameAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.function.GameCenterSignInFunction;
import com.sinovatech.unicom.separatemodule.gamecenter.view.SignInResultDialog;
import com.tencent.qqmini.util.MiniGameUtils;
import com.uber.autodispose.ObservableSubscribeProxy;
import com.zhpan.bannerview.BannerViewPager;
import com.zhpan.bannerview.holder.HolderCreator;
import com.zhpan.bannerview.holder.ViewHolder;
import com.zhpan.bannerview.utils.BannerUtils;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCenterActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private GameCenterActivity activityContext;
    private ClosePosterRunnable closePosterRunnable;
    private SignInHistoryEntity current;
    private SignInResultDialog dialog;
    private Handler handler;
    private HotGameAdapter hotAdapter;
    private ImageView ivHotIcon;
    private ImageView ivHotRightBtn;
    private ImageView ivHotTips;
    private ImageView ivMoreWelfare;
    private ImageView ivSignInAnim;
    private ImageView ivSignInStar;
    private ImageView ivSignInTips;
    private ImageView ivTitleClose;
    private ImageView ivTitleShare;
    private View llGoneBtn;
    private LinearLayout llHotGameArea;
    private LinearLayout llHotMore;
    private LinearLayout llRecentlyList;
    private LinearLayout llRecentlyMore;
    private LinearLayout llRecommendMore;
    private LinearLayout llSignInLine;
    private ImageView mImgGamePosterColse;
    private ImageView mImgPoster;
    private LinearLayout mLinVpBgView;
    private RelativeLayout mRlGamePosterView;
    private GameCenterDataManager manager;
    private List<ImageView> popupList;
    private GameAdapter recentlyAdapter;
    private GameAdapter recommendAdapter;
    private List<RelativeLayout> rlBgLineList;
    private RecyclerView rlvHotList;
    private RecyclerView rlvRecentlyList;
    private RecyclerView rlvRecommendList;
    private String taskCenterUrl;
    private List<TextView> tvBgList;
    private TextView tvCoin;
    private TextView tvSearch;
    private TextView tvSignInBtn;
    private TextView tvTitle;
    private List<View> vBgLineList;
    private List<View> vLineList;
    private BannerViewPager vpBanner;
    private Map<String, String> urlMap = new HashMap();
    private Map<String, String> shareMap = new HashMap();
    private String moreHotUrl = "";
    private String is_action = "";
    private long time = 0;

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
    public void onStart() {
        NBSApplicationStateMonitor.getInstance().activityStarted(getClass().getName());
        super.onStart();
        NBSAppInstrumentation.activityStartEndIns();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onStop() {
        NBSApplicationStateMonitor.getInstance().activityStopped(getClass().getName());
        super.onStop();
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        NBSTraceEngine.startTracing(getClass().getName());
        super.onCreate(bundle);
        this.activityContext = this;
        setContentView(2131492913);
        UIUtils.setStatusBarMode(this, true, true);
        initUI();
        this.manager = GameCenterDataManager.getInstance();
        loadFloorInfoCache();
        loadRecommendCache();
        loadRecentlyCache();
        loadHotCache();
        initSignInUI();
        loadSignInHistory();
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "41");
        hashMap.put("p25", "2");
        hashMap.put("p32", "Android");
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$C3-gXIABoQobGhitMsQbN3pin-k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("游戏日志结果-->", (String) obj);
            }
        });
        getDialogData();
        NBSAppInstrumentation.activityCreateEndIns();
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        initData();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void initUI() {
        this.ivTitleClose = (ImageView) findViewById(2131297512);
        this.ivTitleShare = (ImageView) findViewById(2131297513);
        this.tvSearch = (TextView) findViewById(2131299072);
        this.llRecommendMore = (LinearLayout) findViewById(2131297767);
        this.tvTitle = (TextView) findViewById(2131299108);
        this.rlvRecommendList = (RecyclerView) findViewById(2131298409);
        this.llRecentlyList = (LinearLayout) findViewById(2131297765);
        this.llRecentlyMore = (LinearLayout) findViewById(2131297766);
        this.rlvRecentlyList = (RecyclerView) findViewById(2131298408);
        this.vpBanner = (BannerViewPager) findViewById(2131299542);
        this.ivMoreWelfare = (ImageView) findViewById(2131297443);
        this.llHotMore = (LinearLayout) findViewById(2131297725);
        this.ivHotIcon = (ImageView) findViewById(2131297394);
        this.mImgPoster = (ImageView) findViewById(2131297070);
        this.mRlGamePosterView = (RelativeLayout) findViewById(2131297071);
        this.mRlGamePosterView.setVisibility(8);
        this.mLinVpBgView = (LinearLayout) findViewById(2131297072);
        this.mImgGamePosterColse = (ImageView) findViewById(2131297069);
        this.mImgGamePosterColse.setOnClickListener(this);
        GlideApp.with((FragmentActivity) this.activityContext).load((Integer) 2131231341).into(this.ivHotIcon);
        this.rlvHotList = (RecyclerView) findViewById(2131298403);
        this.llGoneBtn = findViewById(2131297695);
        this.ivHotTips = (ImageView) findViewById(2131297396);
        this.ivHotRightBtn = (ImageView) findViewById(2131297395);
        this.ivSignInTips = (ImageView) findViewById(2131297498);
        this.llHotGameArea = (LinearLayout) findViewById(2131297723);
        this.ivTitleClose.setOnClickListener(this);
        this.ivTitleShare.setOnClickListener(this);
        this.tvSearch.setOnClickListener(this);
        this.llRecommendMore.setOnClickListener(this);
        this.llRecentlyMore.setOnClickListener(this);
        this.ivMoreWelfare.setOnClickListener(this);
        this.llHotMore.setOnClickListener(this);
        this.llGoneBtn.setOnClickListener(this);
        this.ivSignInTips.setOnClickListener(this);
        this.ivHotTips.setOnClickListener(this);
        this.llRecentlyList.setVisibility(8);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.activityContext);
        linearLayoutManager.setOrientation(0);
        this.rlvRecommendList.setLayoutManager(linearLayoutManager);
        this.recommendAdapter = new GameAdapter(this.activityContext, null);
        this.rlvRecommendList.setAdapter(this.recommendAdapter);
        this.recommendAdapter.setListener(new GameAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$K3juQ37W7wzSnhhNvt9nZdxK5is
            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameAdapter.GameClickedListener
            public final void cliced(GamesEntity.GamesDataEntity gamesDataEntity) {
                GameCenterActivity.lambda$initUI$1(GameCenterActivity.this, gamesDataEntity);
            }
        });
        LinearLayoutManager linearLayoutManager2 = new LinearLayoutManager(this.activityContext);
        linearLayoutManager2.setOrientation(0);
        this.rlvRecentlyList.setLayoutManager(linearLayoutManager2);
        this.recentlyAdapter = new GameAdapter(this.activityContext, null);
        this.rlvRecentlyList.setAdapter(this.recentlyAdapter);
        this.recentlyAdapter.setListener(new GameAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$vyvVlQxCqaMnMTSaj84DJnljlKQ
            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameAdapter.GameClickedListener
            public final void cliced(GamesEntity.GamesDataEntity gamesDataEntity) {
                GameCenterActivity.this.startGame(gamesDataEntity, "0");
            }
        });
        this.closePosterRunnable = new ClosePosterRunnable();
    }

    public static /* synthetic */ void lambda$initUI$1(GameCenterActivity gameCenterActivity, GamesEntity.GamesDataEntity gamesDataEntity) {
        if (!TextUtils.isEmpty(gamesDataEntity.getId())) {
            gameCenterActivity.startGame(gamesDataEntity, "18");
        } else {
            IntentManager.generateIntentAndGo(gameCenterActivity.activityContext, gamesDataEntity.getUrl(), gamesDataEntity.getName(), true, "get");
        }
    }

    private void initData() {
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.1
            @Override // java.lang.Runnable
            public void run() {
                GameCenterActivity.this.initFloorInfo();
                GameCenterActivity.this.initRecommendList();
                GameCenterActivity.this.initRecentlyList();
                GameCenterActivity.this.initSignInInfo();
                GameCenterActivity.this.initHotList();
            }
        }, 200L);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        long currentTimeMillis = System.currentTimeMillis();
        if (currentTimeMillis - this.time > 1500) {
            this.time = currentTimeMillis;
            switch (view.getId()) {
                case 2131297069:
                    Handler handler = this.handler;
                    if (handler != null) {
                        handler.removeCallbacks(this.closePosterRunnable);
                    }
                    posterLog("", "4");
                    closePoster();
                    break;
                case 2131297396:
                    Map<String, String> map = this.urlMap;
                    if (map != null && map.containsKey("flow_rule_url")) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.urlMap.get("flow_rule_url"), "", true, "get");
                        break;
                    }
                    break;
                case 2131297443:
                    if (!TextUtils.isEmpty(this.taskCenterUrl)) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.taskCenterUrl, "", true, "get");
                        break;
                    }
                    break;
                case 2131297498:
                    Map<String, String> map2 = this.urlMap;
                    if (map2 != null && map2.containsKey("sign_rule_url")) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.urlMap.get("sign_rule_url"), "", true, "0", "get");
                        break;
                    }
                    break;
                case 2131297512:
                case 2131297695:
                    finish();
                    break;
                case 2131297513:
                    String newDefaultJson = WebMenuManager.getNewDefaultJson();
                    Map<String, String> map3 = this.shareMap;
                    if (map3 != null && !map3.isEmpty()) {
                        newDefaultJson = "{\n    \"shareContent\":\"手机新用户专享福利，话费+流量双重红包!\",\n    \"shareIconURL\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325101313.png\",\n    \"provider\":\"10010\",\n    \"shareTitle\":\"新人百元礼包\",\n    \"shareType\":\"url\",\n    \"headUrl\":\"https://wap.10010.com/mobileService/clickCountLogRecord/pageClickCount.htm?flag=new\",\n    \"shareURL\":\"" + this.shareMap.get("shareUrl") + "\",\n    \"huabaoIconUrl\":\"" + this.shareMap.get("shareImgUrl") + "\",\n    \"businessCode\":\"111111\",\n    \"tip_title\":\"推荐好友办理业务赚手厅话费券\",\n    \"tip_content\":\"1.点击微信分享好友或微信群。\\n2.3个月内好友在手厅办理的业务，你都将获得相应的话费券\\n3.话费券明细在手厅一我的-推荐有礼’处查看。\"\n}";
                    }
                    ShareManager.ShowShareDialog(this.activityContext, "qq,qzone,wechat,wechatmoments", newDefaultJson, new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.2
                        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                        public void onComplete(String str) {
                            UIUtils.toastCenter("成功");
                        }

                        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                        public void onCancel(String str) {
                            UIUtils.toastCenter("取消");
                        }

                        @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
                        public void onError(String str, String str2) {
                            UIUtils.toastCenter("失败");
                        }
                    });
                    HashMap hashMap = new HashMap();
                    hashMap.put("p2", "40526");
                    hashMap.put("p3", "游戏专区App客户端");
                    hashMap.put("p5", "41");
                    hashMap.put("p25", "1");
                    hashMap.put("p32", "Android");
                    hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
                    this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$qAJFHPGncIjW1B28ux1WQ2um2m8
                        @Override // io.reactivex.functions.Consumer
                        public final void accept(Object obj) {
                            UIUtils.logD("游戏日志结果-->", (String) obj);
                        }
                    });
                    break;
                case 2131297725:
                    if (!TextUtils.isEmpty(this.moreHotUrl)) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.moreHotUrl, "", true, "get");
                        break;
                    }
                    break;
                case 2131297766:
                    Map<String, String> map4 = this.urlMap;
                    if (map4 != null && map4.containsKey("recently_url")) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.urlMap.get("recently_url"), "", true, "get");
                        break;
                    }
                    break;
                case 2131297767:
                    Map<String, String> map5 = this.urlMap;
                    if (map5 != null && map5.containsKey("boutique_url")) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.urlMap.get("boutique_url"), "", true, "get");
                        break;
                    }
                    break;
                case 2131299072:
                    Map<String, String> map6 = this.urlMap;
                    if (map6 != null && map6.containsKey("search_url")) {
                        IntentManager.generateIntentAndGo(this.activityContext, this.urlMap.get("search_url"), "", true, "get");
                        break;
                    }
                    break;
            }
            NBSActionInstrumentation.onClickEventExit();
            return;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C87213 implements Observer<GamesEntity> {
        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(Throwable th) {
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(Disposable disposable) {
        }

        C87213() {
        }

        @Override // io.reactivex.Observer
        public void onNext(final GamesEntity gamesEntity) {
            if (gamesEntity.getBanner() == null || gamesEntity.getBanner().size() <= 0) {
                GameCenterActivity.this.vpBanner.setVisibility(8);
            } else {
                GameCenterActivity.this.vpBanner.setVisibility(0);
                GameCenterActivity.this.vpBanner.setHolderCreator(new HolderCreator() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$3$kcDY_g8Z5HLe3klQ6Q0KSpn4EhI
                    @Override // com.zhpan.bannerview.holder.HolderCreator
                    public final ViewHolder createViewHolder() {
                        return GameCenterActivity.C87213.lambda$onNext$0(GameCenterActivity.C87213.this);
                    }
                }).setIndicatorWidth(BannerUtils.dp2px(6.0f), BannerUtils.dp2px(6.0f)).setIndicatorHeight(BannerUtils.dp2px(6.0f)).setIndicatorGap(BannerUtils.dp2px(5.0f)).setIndicatorColor(Color.parseColor("#80FFFFFF"), Color.parseColor("#FFFFFF")).setInterval(2000).setPageStyle(0).setOnPageClickListener(new BannerViewPager.OnPageClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$3$cOFT9-5M1s0Q6ba7dT7xiXbkezc
                    @Override // com.zhpan.bannerview.BannerViewPager.OnPageClickListener
                    public final void onPageClick(int i) {
                        GameCenterActivity.C87213.lambda$onNext$1(GameCenterActivity.C87213.this, gamesEntity, i);
                    }
                }).create(gamesEntity.getBanner());
            }
            if (!TextUtils.isEmpty(gamesEntity.getSignInImg())) {
                GlideApp.with((FragmentActivity) GameCenterActivity.this.activityContext).load(gamesEntity.getSignInImg()).error(2131231347).into(GameCenterActivity.this.ivMoreWelfare);
            }
            GameCenterActivity.this.taskCenterUrl = gamesEntity.getTitle();
            GlideApp.with((FragmentActivity) GameCenterActivity.this.activityContext).load(gamesEntity.getHotUrl()).placeholder(2131231341).error(2131231341).into(GameCenterActivity.this.ivHotIcon);
            GlideApp.with((FragmentActivity) GameCenterActivity.this.activityContext).load(gamesEntity.getRightImg()).error(2131231345).into(GameCenterActivity.this.ivHotRightBtn);
            if (TextUtils.isEmpty(GameCenterActivity.this.moreHotUrl)) {
                GameCenterActivity.this.moreHotUrl = gamesEntity.getRightUrl();
            }
        }

        public static /* synthetic */ ViewHolder lambda$onNext$0(C87213 c87213) {
            return new BannerViewHolder();
        }

        public static /* synthetic */ void lambda$onNext$1(C87213 c87213, GamesEntity gamesEntity, int i) {
            gamesEntity.getBanner().get(i);
            GameCenterActivity.this.startGame(gamesEntity.getBanner().get(i), "19");
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFloorInfo() {
        this.manager.getGameCenterFloor(this).subscribe(new C87213());
        this.manager.getFloorUrl(this).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$ghSBRZd1UMe7E2VPAthidGN2B-Q
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCenterActivity.lambda$initFloorInfo$4(GameCenterActivity.this, (Map) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$SbwgiT4kVLfkhwMcbVe37_L7WGM
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCenterActivity.lambda$initFloorInfo$5(GameCenterActivity.this, (Throwable) obj);
            }
        });
        this.manager.getHuabaoUrl(this).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$f6WqERPimp1xcPLSTdZUhscHqhE
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCenterActivity.lambda$initFloorInfo$6(GameCenterActivity.this, (Map) obj);
            }
        });
        this.manager.getGameCenterFloorIcon(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.4
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(GamesEntity gamesEntity) {
                if (gamesEntity.getRecommendList() != null) {
                    GameCenterActivity.this.recommendAdapter.setFristItem(gamesEntity.getRecommendList());
                }
            }
        });
    }

    public static /* synthetic */ void lambda$initFloorInfo$4(GameCenterActivity gameCenterActivity, Map map) throws Exception {
        if (gameCenterActivity.urlMap.isEmpty()) {
            gameCenterActivity.urlMap = map;
        }
    }

    public static /* synthetic */ void lambda$initFloorInfo$5(GameCenterActivity gameCenterActivity, Throwable th) throws Exception {
        if (gameCenterActivity.urlMap == null) {
            gameCenterActivity.urlMap = new HashMap();
        }
        JSONObject jSONObject = new JSONObject(CacheDataCenter.getInstance().getMapUrl());
        if ("0000".equals(jSONObject.optString("code"))) {
            JSONObject optJSONObject = jSONObject.optJSONObject("data");
            String optString = optJSONObject.optString("boutique_url");
            if (!TextUtils.isEmpty(optString)) {
                gameCenterActivity.urlMap.put("boutique_url", optString);
            }
            String optString2 = optJSONObject.optString("recreational_url");
            if (!TextUtils.isEmpty(optString2)) {
                gameCenterActivity.urlMap.put("recreational_url", optString2);
            }
            String optString3 = optJSONObject.optString("recently_url");
            if (!TextUtils.isEmpty(optString3)) {
                gameCenterActivity.urlMap.put("recently_url", optString3);
            }
            String optString4 = optJSONObject.optString("search_url");
            if (!TextUtils.isEmpty(optString4)) {
                gameCenterActivity.urlMap.put("search_url", optString4);
            }
            String optString5 = optJSONObject.optString("sign_rule_url");
            if (!TextUtils.isEmpty(optString5)) {
                gameCenterActivity.urlMap.put("sign_rule_url", optString5);
            }
            String optString6 = optJSONObject.optString("flow_rule_url");
            if (TextUtils.isEmpty(optString6)) {
                return;
            }
            gameCenterActivity.urlMap.put("flow_rule_url", optString6);
        }
    }

    public static /* synthetic */ void lambda$initFloorInfo$6(GameCenterActivity gameCenterActivity, Map map) throws Exception {
        if (gameCenterActivity.shareMap.isEmpty()) {
            gameCenterActivity.shareMap = map;
        }
    }

    private void loadFloorInfoCache() {
        String gameCenterFloor = CacheDataCenter.getInstance().getGameCenterFloor();
        if (TextUtils.isEmpty(gameCenterFloor)) {
            return;
        }
        final GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterFloor);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                String optString2 = optJSONObject.optString("carousel");
                if (optString2.startsWith("[{")) {
                    JSONArray jSONArray = new JSONArray(optString2);
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject2 = jSONArray.optJSONObject(i);
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        gamesDataEntity.setHotImgUrl(optJSONObject2.optString("deploy_game"));
                        gamesDataEntity.setUrl(optJSONObject2.optString("gameUrl"));
                        gamesDataEntity.setQqMark(optJSONObject2.optString("qq_mark"));
                        gamesDataEntity.setName(optJSONObject2.optString("game_name"));
                        gamesDataEntity.setTwoGameType("");
                        gamesDataEntity.setCompany(optJSONObject2.optString("company"));
                        gamesDataEntity.setId(optJSONObject2.optString("game_id"));
                        gamesDataEntity.setGameType("");
                        arrayList.add(gamesDataEntity);
                    }
                    gamesEntity.setBanner(arrayList);
                }
                JSONArray optJSONArray = optJSONObject.optJSONArray("poster");
                if (optJSONArray != null && optJSONArray.length() > 0) {
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < optJSONArray.length(); i2++) {
                        JSONObject optJSONObject3 = optJSONArray.optJSONObject(i2);
                        if (optJSONObject3 != null) {
                            GamesEntity.PosterData posterData = new GamesEntity.PosterData();
                            String optString3 = optJSONObject3.optString("deploy_game");
                            String optString4 = optJSONObject3.optString("qq_mark");
                            String optString5 = optJSONObject3.optString("gameUrl");
                            String optString6 = optJSONObject3.optString("game_name");
                            String optString7 = optJSONObject3.optString("company");
                            String optString8 = optJSONObject3.optString("game_id");
                            String optString9 = optJSONObject3.optString("is_action");
                            if (TextUtils.equals("null", optString3)) {
                                optString3 = "";
                            }
                            posterData.setDeploy_game(optString3);
                            if (TextUtils.equals("null", optString4)) {
                                optString4 = "";
                            }
                            posterData.setQq_mark(optString4);
                            if (TextUtils.equals("null", optString7)) {
                                optString7 = "";
                            }
                            posterData.setCompany(optString7);
                            if (TextUtils.equals("null", optString8)) {
                                optString8 = "";
                            }
                            posterData.setGame_id(optString8);
                            if (TextUtils.equals("null", optString6)) {
                                optString6 = "";
                            }
                            posterData.setGame_name(optString6);
                            if (TextUtils.equals("null", optString5)) {
                                optString5 = "";
                            }
                            posterData.setGameUrl(optString5);
                            if (TextUtils.equals("null", optString9)) {
                                optString9 = "";
                            }
                            posterData.setIs_action(optString9);
                            arrayList2.add(posterData);
                        }
                    }
                    gamesEntity.setPoster(arrayList2);
                    showPoster(gamesEntity, !(optJSONArray instanceof JSONArray) ? optJSONArray.toString() : NBSJSONArrayInstrumentation.toString(optJSONArray));
                }
                String optString10 = optJSONObject.optString("icon");
                if (optString10.startsWith("[{")) {
                    JSONArray jSONArray2 = new JSONArray(optString10);
                    if (jSONArray2.length() > 0) {
                        for (int i3 = 0; i3 < jSONArray2.length(); i3++) {
                            JSONObject optJSONObject4 = jSONArray2.optJSONObject(i3);
                            GamesEntity.GamesDataEntity gamesDataEntity2 = new GamesEntity.GamesDataEntity();
                            gamesDataEntity2.setSmallImage(optJSONObject4.optString("bubble"));
                            gamesDataEntity2.setUrl(optJSONObject4.optString("gameUrl"));
                            gamesDataEntity2.setName(optJSONObject4.optString("main_title"));
                            gamesDataEntity2.setGameType("center");
                            if (i3 == 0) {
                                gamesEntity.setFristItem(gamesDataEntity2);
                            }
                        }
                        if (jSONArray2.length() > 1) {
                            JSONObject optJSONObject5 = jSONArray2.optJSONObject(1);
                            gamesEntity.setRightImg(optJSONObject5.optString("bubble"));
                            gamesEntity.setRightUrl(optJSONObject5.optString("gameUrl"));
                        }
                    }
                }
                String optString11 = optJSONObject.optString("advertisement");
                if (optString11.startsWith("[{")) {
                    JSONArray jSONArray3 = new JSONArray(optString11);
                    if (jSONArray3.length() > 0) {
                        gamesEntity.setHotUrl(jSONArray3.optJSONObject(0).optString("deploy_game"));
                        if (jSONArray3.length() > 1) {
                            gamesEntity.setSignInImg(jSONArray3.optJSONObject(1).optString("deploy_game"));
                            gamesEntity.setTitle(jSONArray3.optJSONObject(1).optString("gameUrl"));
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gamesEntity.getBanner() != null && gamesEntity.getBanner().size() > 0) {
            this.vpBanner.setVisibility(0);
            this.vpBanner.setHolderCreator(new HolderCreator() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$P-J4bgNKE91wdyIobQMZsYEPITg
                @Override // com.zhpan.bannerview.holder.HolderCreator
                public final ViewHolder createViewHolder() {
                    return GameCenterActivity.lambda$loadFloorInfoCache$7(GameCenterActivity.this);
                }
            }).setIndicatorWidth(BannerUtils.dp2px(6.0f), BannerUtils.dp2px(6.0f)).setIndicatorHeight(BannerUtils.dp2px(6.0f)).setIndicatorGap(BannerUtils.dp2px(5.0f)).setIndicatorColor(Color.parseColor("#80FFFFFF"), Color.parseColor("#FFFFFF")).setInterval(2000).setPageStyle(0).setOnPageClickListener(new BannerViewPager.OnPageClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$-0vem0DLL-gcz1dJASEfA9SlGgg
                @Override // com.zhpan.bannerview.BannerViewPager.OnPageClickListener
                public final void onPageClick(int i4) {
                    GameCenterActivity.lambda$loadFloorInfoCache$8(GameCenterActivity.this, gamesEntity, i4);
                }
            }).create(gamesEntity.getBanner());
        } else {
            this.vpBanner.setVisibility(8);
        }
        if (!TextUtils.isEmpty(gamesEntity.getSignInImg())) {
            GlideApp.with((FragmentActivity) this.activityContext).load(gamesEntity.getSignInImg()).error(2131231347).into(this.ivMoreWelfare);
        }
        this.taskCenterUrl = gamesEntity.getTitle();
        GlideApp.with((FragmentActivity) this.activityContext).load(gamesEntity.getHotUrl()).placeholder(2131231341).error(2131231341).into(this.ivHotIcon);
        GlideApp.with((FragmentActivity) this.activityContext).load(gamesEntity.getRightImg()).error(2131231345).into(this.ivHotRightBtn);
        if (TextUtils.isEmpty(this.moreHotUrl)) {
            this.moreHotUrl = gamesEntity.getRightUrl();
        }
        try {
            String optString12 = new JSONObject(CacheDataCenter.getInstance().getGameCenterFloor2()).optJSONObject("data").optString("icon");
            if (optString12.startsWith("[{")) {
                JSONArray jSONArray4 = new JSONArray(optString12);
                ArrayList arrayList3 = new ArrayList();
                for (int i4 = 0; i4 < jSONArray4.length(); i4++) {
                    JSONObject optJSONObject6 = jSONArray4.optJSONObject(i4);
                    GamesEntity.GamesDataEntity gamesDataEntity3 = new GamesEntity.GamesDataEntity();
                    gamesDataEntity3.setSmallImage(optJSONObject6.optString("bubble"));
                    gamesDataEntity3.setUrl(optJSONObject6.optString("gameUrl"));
                    gamesDataEntity3.setName(optJSONObject6.optString("main_title"));
                    gamesDataEntity3.setGameType("center");
                    arrayList3.add(gamesDataEntity3);
                    if (i4 == 0) {
                        gamesEntity.setFristItem(gamesDataEntity3);
                    }
                }
                if (gamesEntity.getRecommendList() != null) {
                    this.recommendAdapter.setFristItem(arrayList3);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static /* synthetic */ ViewHolder lambda$loadFloorInfoCache$7(GameCenterActivity gameCenterActivity) {
        return new BannerViewHolder();
    }

    public static /* synthetic */ void lambda$loadFloorInfoCache$8(GameCenterActivity gameCenterActivity, GamesEntity gamesEntity, int i) {
        gamesEntity.getBanner().get(i);
        gameCenterActivity.startGame(gamesEntity.getBanner().get(i), "19");
    }

    private void showPoster(GamesEntity gamesEntity, String str) {
        int i;
        final GamesEntity.PosterData posterData;
        if (gamesEntity != null) {
            this.is_action = "";
            List<GamesEntity.PosterData> poster = gamesEntity.getPoster();
            if (poster == null || poster.size() <= 0 || (i = App.getSharePreferenceUtil().getInt("game_poster_postion")) < 0 || i >= poster.size() || (posterData = poster.get(i)) == null) {
                return;
            }
            this.is_action = posterData.getIs_action();
            GlideApp.with((FragmentActivity) this.activityContext).load(posterData.getDeploy_game()).placeholder(2131231244).error(2131231244).into(this.mImgPoster);
            this.mRlGamePosterView.setVisibility(0);
            this.mRlGamePosterView.setClickable(true);
            this.mRlGamePosterView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (!TextUtils.isEmpty(posterData.getGameUrl())) {
                        if (GameCenterActivity.this.handler != null) {
                            GameCenterActivity.this.handler.removeCallbacks(GameCenterActivity.this.closePosterRunnable);
                        }
                        GameCenterActivity.this.posterLog(posterData.getDeploy_game(), "3");
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        String str2 = TextUtils.isEmpty(posterData.getQq_mark()) ? "N" : "Y";
                        gamesDataEntity.setHotImgUrl(posterData.getDeploy_game());
                        gamesDataEntity.setUrl(posterData.getGameUrl());
                        gamesDataEntity.setQqMark(str2);
                        gamesDataEntity.setName(posterData.getGame_name());
                        gamesDataEntity.setTwoGameType("");
                        gamesDataEntity.setCompany(posterData.getCompany());
                        gamesDataEntity.setId(posterData.getGame_id());
                        gamesDataEntity.setGameType("");
                        GameCenterActivity.this.startGame(gamesDataEntity, "");
                        GameCenterActivity.this.mRlGamePosterView.setVisibility(8);
                        GameCenterActivity.this.mRlGamePosterView.setClickable(false);
                        GameCenterActivity.this.mRlGamePosterView.clearAnimation();
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.handler = new Handler();
            this.handler.postDelayed(this.closePosterRunnable, 3000L);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class ClosePosterRunnable implements Runnable {
        private ClosePosterRunnable() {
        }

        @Override // java.lang.Runnable
        public void run() {
            GameCenterActivity.this.closePoster();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closePoster() {
        if (TextUtils.equals("1", this.is_action)) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(500L);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.6
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    GameCenterActivity.this.mRlGamePosterView.setVisibility(8);
                    GameCenterActivity.this.mRlGamePosterView.setClickable(false);
                }
            });
            RelativeLayout relativeLayout = this.mRlGamePosterView;
            if (relativeLayout != null) {
                relativeLayout.startAnimation(scaleAnimation);
                return;
            }
            return;
        }
        this.mRlGamePosterView.setVisibility(8);
        this.mRlGamePosterView.setClickable(false);
    }

    public void startAlphaAnimation() {
        this.mLinVpBgView.setVisibility(0);
        AlphaAnimation alphaAnimation = new AlphaAnimation(1.0f, 0.2f);
        alphaAnimation.setDuration(1500L);
        alphaAnimation.setRepeatMode(1);
        alphaAnimation.setRepeatCount(2);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.7
            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationRepeat(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationStart(Animation animation) {
            }

            @Override // android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(Animation animation) {
                GameCenterActivity.this.mLinVpBgView.setVisibility(8);
                GameCenterActivity.this.mLinVpBgView.clearAnimation();
            }
        });
        this.mLinVpBgView.startAnimation(alphaAnimation);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRecommendList() {
        this.manager.postRecommendList(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.8
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(GamesEntity gamesEntity) {
                if ("0000".equals(gamesEntity.getCode())) {
                    GameCenterActivity.this.recommendAdapter.update(gamesEntity.getData());
                    if (TextUtils.isEmpty(gamesEntity.getTitle())) {
                        return;
                    }
                    GameCenterActivity.this.tvTitle.setText(gamesEntity.getTitle());
                    return;
                }
                UIUtils.toastCenter(gamesEntity.getMsg());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                GameCenterActivity.this.loadRecommendCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRecommendCache() {
        String gameCenterRecommend = CacheDataCenter.getInstance().getGameCenterRecommend();
        if (TextUtils.isEmpty(gameCenterRecommend)) {
            return;
        }
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterRecommend);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setName(optJSONObject.optString("name"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setResourceId(optJSONObject.optString("resourceId"));
                    gamesDataEntity.setResource_id(optJSONObject.optString("resource_id"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    gamesDataEntity.setCompany(optJSONObject.optString("company"));
                    gamesDataEntity.setSmallImage(optJSONObject.optString("smallImage"));
                    gamesDataEntity.setFreeFlow(optJSONObject.optString("freeFlow"));
                    gamesDataEntity.setGameIap(optJSONObject.optString("gameIap"));
                    gamesDataEntity.setQqMark(optJSONObject.optString("qqMark"));
                    gamesDataEntity.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    gamesDataEntity.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    gamesDataEntity.setGameLabel(optJSONObject.optString("gameLabel"));
                    gamesDataEntity.setSlogan(optJSONObject.optString("slogan"));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
                JSONObject optJSONObject2 = jSONObject.optJSONObject("config");
                if (optJSONObject2 != null) {
                    gamesEntity.setTitle(optJSONObject2.optString("floor_name"));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if ("0000".equals(gamesEntity.getCode())) {
            this.recommendAdapter.update(gamesEntity.getData());
            if (TextUtils.isEmpty(gamesEntity.getTitle())) {
                return;
            }
            this.tvTitle.setText(gamesEntity.getTitle());
            return;
        }
        UIUtils.toastCenter(gamesEntity.getMsg());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRecentlyList() {
        this.manager.postRecentlyList(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.9
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(GamesEntity gamesEntity) {
                if (gamesEntity.getData() == null || gamesEntity.getData().size() <= 0) {
                    GameCenterActivity.this.llRecentlyList.setVisibility(8);
                    return;
                }
                GameCenterActivity.this.llRecentlyList.setVisibility(0);
                GameCenterActivity.this.recentlyAdapter.update(gamesEntity.getData());
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                GameCenterActivity.this.loadRecentlyCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRecentlyCache() {
        String gameCenterPlay = CacheDataCenter.getInstance().getGameCenterPlay();
        if (TextUtils.isEmpty(gameCenterPlay)) {
            return;
        }
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterPlay);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            String optString2 = jSONObject.optString("recentlyList");
            if ("0000".equals(optString) && optString2.startsWith("[{")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("recentlyList");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setName(optJSONObject.optString("name"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setResourceId(optJSONObject.optString("resourceId"));
                    gamesDataEntity.setResource_id(optJSONObject.optString("resource_id"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    gamesDataEntity.setGame_id(optJSONObject.optString("game_id"));
                    gamesDataEntity.setCompany(optJSONObject.optString("company"));
                    gamesDataEntity.setSmallImage(optJSONObject.optString("smallImage"));
                    gamesDataEntity.setFreeFlow(optJSONObject.optString("freeFlow"));
                    gamesDataEntity.setGameIap(optJSONObject.optString("gameIap"));
                    gamesDataEntity.setQqMark(optJSONObject.optString("qqMark"));
                    gamesDataEntity.setQq_mark(optJSONObject.optString("qq_mark"));
                    gamesDataEntity.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    gamesDataEntity.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    gamesDataEntity.setGameLabel(optJSONObject.optString("gameLabel"));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (gamesEntity.getData() != null && gamesEntity.getData().size() > 0) {
            this.llRecentlyList.setVisibility(0);
            this.recentlyAdapter.update(gamesEntity.getData());
            return;
        }
        this.llRecentlyList.setVisibility(8);
    }

    private void initSignInUI() {
        this.popupList = new ArrayList();
        this.popupList.add((ImageView) findViewById(2131297456));
        this.popupList.add((ImageView) findViewById(2131297457));
        this.popupList.add((ImageView) findViewById(2131297458));
        this.popupList.add((ImageView) findViewById(2131297459));
        this.popupList.add((ImageView) findViewById(2131297460));
        this.popupList.add((ImageView) findViewById(2131297461));
        this.popupList.add((ImageView) findViewById(2131297462));
        this.llSignInLine = (LinearLayout) findViewById(2131297780);
        this.vLineList = new ArrayList();
        this.vLineList.add(findViewById(2131299481));
        this.vLineList.add(findViewById(2131299482));
        this.vLineList.add(findViewById(2131299483));
        this.vLineList.add(findViewById(2131299484));
        this.vLineList.add(findViewById(2131299485));
        this.vLineList.add(findViewById(2131299486));
        this.tvSignInBtn = (TextView) findViewById(2131299079);
        this.tvCoin = (TextView) findViewById(2131298913);
        this.ivSignInAnim = (ImageView) findViewById(2131297496);
        this.ivSignInStar = (ImageView) findViewById(2131297497);
        this.tvBgList = new ArrayList();
        this.tvBgList.add((TextView) findViewById(2131298879));
        this.tvBgList.add((TextView) findViewById(2131298880));
        this.tvBgList.add((TextView) findViewById(2131298881));
        this.tvBgList.add((TextView) findViewById(2131298882));
        this.tvBgList.add((TextView) findViewById(2131298883));
        this.tvBgList.add((TextView) findViewById(2131298884));
        this.tvBgList.add((TextView) findViewById(2131298885));
        this.rlBgLineList = new ArrayList();
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298316));
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298317));
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298318));
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298319));
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298320));
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298321));
        this.rlBgLineList.add((RelativeLayout) findViewById(2131298322));
        this.vBgLineList = new ArrayList();
        this.vBgLineList.add(findViewById(2131299467));
        this.vBgLineList.add(findViewById(2131299468));
        this.vBgLineList.add(findViewById(2131299469));
        this.vBgLineList.add(findViewById(2131299470));
        this.vBgLineList.add(findViewById(2131299471));
        this.vBgLineList.add(findViewById(2131299472));
        GlideApp.with((FragmentActivity) this.activityContext).asGif().load((Integer) 2131231356).into(this.ivSignInStar);
        RotateAnimation rotateAnimation = new RotateAnimation(0.0f, 360.0f, 1, 0.5f, 1, 0.5f);
        rotateAnimation.setInterpolator(new LinearInterpolator());
        rotateAnimation.setDuration(5000L);
        rotateAnimation.setRepeatCount(-1);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setStartOffset(0L);
        this.ivSignInAnim.startAnimation(rotateAnimation);
        this.tvSignInBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$8M7rnk2fWUmiNA86sjNNhKEmGmU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameCenterActivity.this.signIn();
            }
        });
        this.tvCoin.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$IqSGa9e49TqBQNF8m0UumtihLOE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                GameCenterActivity.this.signIn();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initSignInInfo() {
        this.manager.postGameSignInHistory(this.activityContext).subscribe(new Observer<SignInHistoryEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.10
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(SignInHistoryEntity signInHistoryEntity) {
                GameCenterActivity.this.current = signInHistoryEntity;
                boolean z = false;
                for (int i = 0; i < signInHistoryEntity.getSignInHistoryList().size(); i++) {
                    SignInHistoryEntity.HistoryEntity historyEntity = signInHistoryEntity.getSignInHistoryList().get(i);
                    if (TextUtils.isEmpty(historyEntity.getIgmUrl())) {
                        ((ImageView) GameCenterActivity.this.popupList.get(i)).setVisibility(4);
                    } else {
                        ((ImageView) GameCenterActivity.this.popupList.get(i)).setVisibility(0);
                        GlideApp.with((FragmentActivity) GameCenterActivity.this.activityContext).asGif().load(historyEntity.getIgmUrl()).into((ImageView) GameCenterActivity.this.popupList.get(i));
                    }
                    if (TextUtils.isEmpty(historyEntity.getRewardVal())) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("第");
                        int i2 = i + 1;
                        sb.append(i2);
                        sb.append("天");
                        ((TextView) GameCenterActivity.this.tvBgList.get(i)).setText(sb.toString());
                        ((RelativeLayout) GameCenterActivity.this.rlBgLineList.get(i)).setVisibility(8);
                        if (i < GameCenterActivity.this.vBgLineList.size()) {
                            ((View) GameCenterActivity.this.vBgLineList.get(i)).setBackground(ContextCompat.getDrawable(GameCenterActivity.this.activityContext, 2131231355));
                        }
                        if ("Y".equals(historyEntity.getSignState())) {
                            GameCenterActivity.this.tvCoin.setText("+" + i2);
                            for (int i3 = 0; i3 < i; i3++) {
                                ((View) GameCenterActivity.this.vLineList.get(i3)).setVisibility(0);
                            }
                        }
                    } else {
                        ((TextView) GameCenterActivity.this.tvBgList.get(i)).setText("已打卡");
                        ((RelativeLayout) GameCenterActivity.this.rlBgLineList.get(i)).setVisibility(0);
                        if (i < GameCenterActivity.this.vBgLineList.size()) {
                            ((View) GameCenterActivity.this.vBgLineList.get(i)).setBackground(ContextCompat.getDrawable(GameCenterActivity.this.activityContext, 2131231354));
                        }
                        if ("Y".equals(historyEntity.getSignState())) {
                            z = true;
                        }
                    }
                }
                GameCenterActivity.this.llSignInLine.setVisibility(z ? 4 : 0);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void signIn() {
        SignInHistoryEntity signInHistoryEntity = this.current;
        if (signInHistoryEntity != null) {
            if (!"0000".equals(signInHistoryEntity.getRespCode())) {
                if (App.hasLogined()) {
                    return;
                }
                new AvoidOnResult(this).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$8m0ujPig2l7R357Ff9Z8bAfhajU
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public final void onActivityResult(int i, Intent intent) {
                        GameCenterActivity.lambda$signIn$11(GameCenterActivity.this, i, intent);
                    }
                });
                return;
            }
            this.manager.postGameSignIn(this.activityContext).subscribe(new Observer<SignInResultEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.11
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onError(Throwable th) {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(SignInResultEntity signInResultEntity) {
                    if (signInResultEntity.getCode().equals("0000")) {
                        GameCenterActivity.this.getExtraAward(signInResultEntity);
                        GameCenterActivity.this.initSignInInfo();
                    } else if (signInResultEntity.getCode().equals("0002") || signInResultEntity.getCode().equals("0010")) {
                        UIUtils.toastCenter("您的操作过于频繁，监测到号码异常，暂无法领取奖励");
                        GameCenterActivity.this.llSignInLine.setVisibility(4);
                        for (int i = 0; i < GameCenterActivity.this.current.getSignInHistoryList().size(); i++) {
                            if ("Y".equals(GameCenterActivity.this.current.getSignInHistoryList().get(i).getSignState())) {
                                ((TextView) GameCenterActivity.this.tvBgList.get(i)).setText("已打卡");
                                ((RelativeLayout) GameCenterActivity.this.rlBgLineList.get(i)).setVisibility(0);
                                if (i < GameCenterActivity.this.vBgLineList.size()) {
                                    ((View) GameCenterActivity.this.vBgLineList.get(i)).setBackground(ContextCompat.getDrawable(GameCenterActivity.this.activityContext, 2131231354));
                                    return;
                                }
                                return;
                            }
                        }
                    } else {
                        UIUtils.toastCenter(signInResultEntity.getRespDesc());
                        GameCenterActivity.this.initSignInInfo();
                    }
                }
            });
        }
    }

    public static /* synthetic */ void lambda$signIn$11(GameCenterActivity gameCenterActivity, int i, Intent intent) {
        if (App.hasLogined()) {
            UIUtils.logD("登录成功，重新加载数据");
            gameCenterActivity.initData();
            return;
        }
        UIUtils.logD("登录失败");
    }

    private void getDialogData() {
        this.manager.postNewGames(this.activityContext).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.12
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(GamesEntity gamesEntity) {
                if (GameCenterActivity.this.dialog == null && "0000".equals(gamesEntity.getCode())) {
                    GameCenterActivity gameCenterActivity = GameCenterActivity.this;
                    gameCenterActivity.dialog = new SignInResultDialog(gameCenterActivity.activityContext);
                    GameCenterActivity.this.dialog.setList(gamesEntity.getData());
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHotList() {
        this.manager.getHotList(this.activityContext).subscribe(new Observer<HotGamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.13
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(HotGamesEntity hotGamesEntity) {
                UIUtils.logD("test", "begin show0 ");
                if (hotGamesEntity.getPopularList() == null || hotGamesEntity.getPopularList().size() <= 0) {
                    if (GameCenterActivity.this.hotAdapter == null) {
                        GameCenterActivity.this.llHotGameArea.setVisibility(8);
                        return;
                    }
                    return;
                }
                UIUtils.logD("test", "begin show " + hotGamesEntity.getPopularList().size());
                GameCenterActivity.this.llHotGameArea.setVisibility(0);
                GameCenterActivity.this.rlvHotList.setLayoutManager(new LinearLayoutManager(GameCenterActivity.this.activityContext, 0, false));
                GameCenterActivity gameCenterActivity = GameCenterActivity.this;
                gameCenterActivity.hotAdapter = new HotGameAdapter(gameCenterActivity.activityContext, GameCenterActivity.createList(hotGamesEntity.getPopularList()));
                GameCenterActivity.this.rlvHotList.setAdapter(GameCenterActivity.this.hotAdapter);
                GameCenterActivity.this.hotAdapter.setListener(new HotGameAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.13.1
                    @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.HotGameAdapter.GameClickedListener
                    public void toGameInfo(HotGamesEntity.HotGame hotGame) {
                        GameCenterActivity.this.startGame(hotGame, "3");
                    }

                    @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.HotGameAdapter.GameClickedListener
                    public void getCoin(HotGamesEntity.HotGame hotGame) {
                        GameCenterActivity.this.flowGet(hotGame);
                    }
                });
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                GameCenterActivity.this.loadHotCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadHotCache() {
        String gameCenterHot = CacheDataCenter.getInstance().getGameCenterHot();
        if (TextUtils.isEmpty(gameCenterHot)) {
            return;
        }
        HotGamesEntity hotGamesEntity = new HotGamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterHot);
            String optString = jSONObject.optString("code");
            hotGamesEntity.setCode(optString);
            hotGamesEntity.setReceiveFlag(jSONObject.optString("receiveFlag"));
            String optString2 = jSONObject.optString("popularList");
            if ("0000".equals(optString) && optString2.startsWith("[")) {
                JSONArray optJSONArray = jSONObject.optJSONArray("popularList");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    HotGamesEntity.HotGame hotGame = new HotGamesEntity.HotGame();
                    hotGame.setName(optJSONObject.optString("name"));
                    hotGame.setUrl(optJSONObject.optString("url"));
                    hotGame.setResourceId(optJSONObject.optString("resourceId"));
                    hotGame.setResource_id(optJSONObject.optString("resource_id"));
                    hotGame.setId(optJSONObject.optString("id"));
                    hotGame.setCompany(optJSONObject.optString("company"));
                    hotGame.setSmallImage(optJSONObject.optString("smallImage"));
                    hotGame.setFreeFlow(optJSONObject.optString("freeFlow"));
                    hotGame.setGameIap(optJSONObject.optString("gameIap"));
                    hotGame.setQqMark(optJSONObject.optString("qqMark"));
                    hotGame.setCurrentMinute(optJSONObject.optInt("currentMinute"));
                    hotGame.setGameType(optJSONObject.optString("gameType"));
                    hotGame.setMinute(optJSONObject.optString("minute"));
                    hotGame.setState(optJSONObject.optString("state"));
                    hotGame.setTwoGameType(optJSONObject.optString("twoGameType"));
                    hotGame.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    hotGame.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    hotGame.setGameLabel(optJSONObject.optString("gameLabel"));
                    arrayList.add(hotGame);
                }
                hotGamesEntity.setPopularList(arrayList);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (hotGamesEntity.getPopularList() == null || hotGamesEntity.getPopularList().size() <= 0) {
            return;
        }
        this.llHotGameArea.setVisibility(0);
        this.rlvHotList.setLayoutManager(new LinearLayoutManager(this.activityContext, 0, false));
        this.hotAdapter = new HotGameAdapter(this.activityContext, createList(hotGamesEntity.getPopularList()));
        this.rlvHotList.setAdapter(this.hotAdapter);
        this.hotAdapter.setListener(new HotGameAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.14
            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.HotGameAdapter.GameClickedListener
            public void toGameInfo(HotGamesEntity.HotGame hotGame2) {
                GameCenterActivity.this.startGame(hotGame2, "3");
            }

            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.HotGameAdapter.GameClickedListener
            public void getCoin(HotGamesEntity.HotGame hotGame2) {
                GameCenterActivity.this.flowGet(hotGame2);
            }
        });
    }

    private void loadSignInHistory() {
        String gameCenterSingInHistory = CacheDataCenter.getInstance().getGameCenterSingInHistory();
        SignInHistoryEntity signInHistoryEntity = new SignInHistoryEntity();
        ArrayList arrayList = new ArrayList();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterSingInHistory);
            String optString = jSONObject.optString("respCode");
            signInHistoryEntity.setRespCode(optString);
            if ("0000".equals(optString)) {
                JSONObject jSONObject2 = new JSONObject(jSONObject.optString("signin_history"));
                Iterator<String> keys = jSONObject2.keys();
                while (keys.hasNext()) {
                    JSONObject optJSONObject = jSONObject2.optJSONObject(keys.next());
                    SignInHistoryEntity.HistoryEntity historyEntity = new SignInHistoryEntity.HistoryEntity();
                    historyEntity.setDayIndex(optJSONObject.optInt("dayIndex"));
                    historyEntity.setIgmUrl(optJSONObject.optString("img_url"));
                    historyEntity.setRewardVal(optJSONObject.optString("reward_val"));
                    historyEntity.setSignState(optJSONObject.optString("sign_state"));
                    historyEntity.setRewardType(optJSONObject.optString("reward_type"));
                    historyEntity.setRewardInfo(optJSONObject.optString("reward_info"));
                    historyEntity.setUserId(optJSONObject.optString("user_id"));
                    arrayList.add(historyEntity);
                }
                Collections.sort(arrayList, new Comparator<SignInHistoryEntity.HistoryEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.15
                    @Override // java.util.Comparator
                    public int compare(SignInHistoryEntity.HistoryEntity historyEntity2, SignInHistoryEntity.HistoryEntity historyEntity3) {
                        return historyEntity2.getDayIndex() - historyEntity3.getDayIndex();
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (arrayList.isEmpty()) {
            int i = 0;
            while (i < 7) {
                SignInHistoryEntity.HistoryEntity historyEntity2 = new SignInHistoryEntity.HistoryEntity();
                int i2 = i + 1;
                historyEntity2.setDayIndex(i2);
                historyEntity2.setSignState(i == 0 ? "Y" : "N");
                arrayList.add(historyEntity2);
                i = i2;
            }
        }
        signInHistoryEntity.setSignInHistoryList(arrayList);
        this.current = signInHistoryEntity;
        boolean z = false;
        for (int i3 = 0; i3 < signInHistoryEntity.getSignInHistoryList().size(); i3++) {
            SignInHistoryEntity.HistoryEntity historyEntity3 = signInHistoryEntity.getSignInHistoryList().get(i3);
            if (TextUtils.isEmpty(historyEntity3.getIgmUrl())) {
                this.popupList.get(i3).setVisibility(4);
            } else {
                this.popupList.get(i3).setVisibility(0);
                GlideApp.with((FragmentActivity) this.activityContext).asGif().load(historyEntity3.getIgmUrl()).into(this.popupList.get(i3));
            }
            if (TextUtils.isEmpty(historyEntity3.getRewardVal())) {
                StringBuilder sb = new StringBuilder();
                sb.append("第");
                int i4 = i3 + 1;
                sb.append(i4);
                sb.append("天");
                this.tvBgList.get(i3).setText(sb.toString());
                this.rlBgLineList.get(i3).setVisibility(8);
                if (i3 < this.vBgLineList.size()) {
                    this.vBgLineList.get(i3).setBackground(ContextCompat.getDrawable(this.activityContext, 2131231355));
                }
                if ("Y".equals(historyEntity3.getSignState())) {
                    this.tvCoin.setText("+" + i4);
                    for (int i5 = 0; i5 < i3; i5++) {
                        this.vLineList.get(i5).setVisibility(0);
                    }
                }
            } else {
                this.tvBgList.get(i3).setText("已打卡");
                this.rlBgLineList.get(i3).setVisibility(0);
                if (i3 < this.vBgLineList.size()) {
                    this.vBgLineList.get(i3).setBackground(ContextCompat.getDrawable(this.activityContext, 2131231354));
                }
                if ("Y".equals(historyEntity3.getSignState())) {
                    z = true;
                }
            }
        }
        this.llSignInLine.setVisibility(z ? 4 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void flowGet(HotGamesEntity.HotGame hotGame) {
        this.manager.flowGet(this.activityContext, hotGame.getId()).subscribe(new Observer<FlowGetResultEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.16
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(FlowGetResultEntity flowGetResultEntity) {
                UIUtils.toastCenter(flowGetResultEntity.getMsg());
                if (flowGetResultEntity.getCode().equals("0000") && flowGetResultEntity.getData() != null) {
                    GameCenterActivity.this.hotAdapter.changeGameStatus(flowGetResultEntity.getData().getId());
                }
                GameCenterActivity.this.initHotList();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGame(GamesEntity.GamesDataEntity gamesDataEntity, String str) {
        if (TextUtils.isEmpty(gamesDataEntity.getQqMark())) {
            return;
        }
        if ("Y".equals(gamesDataEntity.getQqMark())) {
            MiniGameUtils.showToast();
        } else {
            IntentManager.generateIntentAndGo(this.activityContext, gamesDataEntity.getUrl(), gamesDataEntity.getName(), true, "get");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p25", gamesDataEntity.getGameType());
        hashMap.put("P26", gamesDataEntity.getId());
        hashMap.put("p27", gamesDataEntity.getName());
        hashMap.put("p28", gamesDataEntity.getCompany());
        hashMap.put("p31", str);
        hashMap.put("p32", "Android");
        hashMap.put("p33", gamesDataEntity.getTwoGameType());
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        hashMap.put("p35", "Y".equals(gamesDataEntity.getQqMark()) ? "1" : "2");
        hashMap.put("p23", TextUtils.isEmpty(gamesDataEntity.getBoutiqueFlag()) ? "" : gamesDataEntity.getBoutiqueFlag());
        hashMap.put("p16", TextUtils.isEmpty(gamesDataEntity.getResourceId()) ? TextUtils.isEmpty(gamesDataEntity.getResource_id()) ? "" : gamesDataEntity.getResource_id() : gamesDataEntity.getBoutiqueFlag());
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$jxuupLmGiclrlMVnRYvS9rwTN4c
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("游戏日志结果-->", (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGame(HotGamesEntity.HotGame hotGame, String str) {
        if (TextUtils.isEmpty(hotGame.getQqMark())) {
            return;
        }
        if ("Y".equals(hotGame.getQqMark())) {
            MiniGameUtils.showToast();
        } else {
            IntentManager.generateIntentAndGo(this.activityContext, hotGame.getUrl(), hotGame.getName(), true, "get");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p25", hotGame.getGameType());
        hashMap.put("P26", hotGame.getId());
        hashMap.put("p27", hotGame.getName());
        hashMap.put("p28", hotGame.getCompany());
        hashMap.put("p31", str);
        hashMap.put("p32", "Android");
        hashMap.put("p33", hotGame.getTwoGameType());
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        hashMap.put("p35", "Y".equals(hotGame.getQqMark()) ? "1" : "2");
        hashMap.put("p23", TextUtils.isEmpty(hotGame.getBoutiqueFlag()) ? "" : hotGame.getBoutiqueFlag());
        hashMap.put("p16", TextUtils.isEmpty(hotGame.getResourceId()) ? TextUtils.isEmpty(hotGame.getResource_id()) ? "" : hotGame.getResource_id() : hotGame.getBoutiqueFlag());
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$YxNaWNLqTB9D-dK1wOX7xCe7Y2M
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("游戏日志结果-->", (String) obj);
            }
        });
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class BannerViewHolder implements ViewHolder<GamesEntity.GamesDataEntity> {
        private ImageView bannerView;

        public BannerViewHolder() {
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public View createView(ViewGroup viewGroup, Context context, int i) {
            View inflate = LayoutInflater.from(context).inflate(2131493142, viewGroup, false);
            this.bannerView = (ImageView) inflate.findViewById(2131296501);
            return inflate;
        }

        @Override // com.zhpan.bannerview.holder.ViewHolder
        public void onBind(Context context, GamesEntity.GamesDataEntity gamesDataEntity, int i, int i2) {
            Glide.with(context).load(gamesDataEntity.getHotImgUrl()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231335).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(16, 0, RoundedCornersTransformation.CornerType.ALL)))).into(this.bannerView);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static List<Map<String, HotGamesEntity.HotGame>> createList(List<HotGamesEntity.HotGame> list) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (i % 2 == 0) {
                HashMap hashMap = new HashMap();
                hashMap.put("up", list.get(i));
                arrayList.add(hashMap);
            } else {
                ((Map) arrayList.get(arrayList.size() - 1)).put("down", list.get(i));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getExtraAward(final SignInResultEntity signInResultEntity) {
        HashMap hashMap = new HashMap();
        hashMap.put("methodType", "signin_history");
        ((ObservableSubscribeProxy) App.getAsyncHttpClient().rxPost(URLSet.gameSignInHistory(), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new GameCenterSignInFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Observer<SignInHistoryEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.17
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            /* JADX WARN: Removed duplicated region for block: B:34:0x00b1 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:35:0x00e1 A[SYNTHETIC] */
            /* JADX WARN: Removed duplicated region for block: B:36:0x0094 A[SYNTHETIC] */
            @Override // io.reactivex.Observer
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onNext(com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity r7) {
                /*
                    Method dump skipped, instructions count: 310
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.gamecenter.GameCenterActivity.C871917.onNext(com.sinovatech.unicom.separatemodule.gamecenter.entity.SignInHistoryEntity):void");
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                IntentManager.generateIntentAndGo(GameCenterActivity.this.activityContext, URLSet.getTaskCenterUrl(signInResultEntity.getCurrentIntegral(), "-1", "-1"), "", true, "get");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void posterLog(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p25", str2);
        hashMap.put("P26", str);
        hashMap.put("p32", "Android");
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCenterActivity$Xd1yi4IyQqHuc8GdGDhbS7XT3H8
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("游戏日志结果-->", (String) obj);
            }
        });
    }
}
