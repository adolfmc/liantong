package com.sinovatech.unicom.separatemodule.gamecenter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p083v4.view.ViewPager;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.Transformation;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceEngine;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.FlowGetAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameCenterAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.GridViewAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.adapter.MyViewPagerAdapter;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.FlowGetResultEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.GamesEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.HotGetEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.entity.NickNameEntity;
import com.sinovatech.unicom.separatemodule.gamecenter.view.RoundImageView;
import com.tencent.qqmini.util.MiniGameUtils;
import com.youth.banner.Banner;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GameCentersActivity extends BaseActivity implements View.OnClickListener {
    public NBSTraceUnit _nbs_trace;
    private GameCentersActivity activityContext;
    private Banner ban_top;
    private LinearLayout banner_li;
    private ClosePosterRunnable closePosterRunnable;
    @NotNull
    private HotGetEntity entity;
    private ImageView firstOne;
    private int flag;
    private LoadFloeGetRunnable floeGetRunnable;
    private FlowGetAdapter flowGetAdapter;
    private ImageView gamePosterClose;
    private ImageView gamePosterImg;
    private RelativeLayout gamePosterView;
    private Handler handler;
    private ArrayList<HotGetEntity.DataBean.IconBean> hotlist;
    private ImageView imageBubbleLeft;
    private ImageView imageBubbleMiddle;
    private ImageView imageBubbleRight;
    private ImageView imageDeployGame;
    private ImageView imageDeployGameLeft;
    private ImageView imageDeployGameMiddle;
    private ImageView imageDeployGameRight;
    private ImageView ivHotIcon;
    private ImageView ivHotTips;
    private ImageView ivToright;
    private LinearLayout jinGangQuLi;
    private ArrayList<GamesEntity.GamesDataEntity> jingGangQuList;
    private GameCenterAdapter likeAdapter;
    private ArrayList<GamesEntity.GamesDataEntity> likeListBean;
    private ArrayList<FlowGetEntity.PopularListBean> listBean;
    private View llBtnGone;
    private LinearLayout llFlowGet;
    private RelativeLayout llHotGameArea;
    private LinearLayout llHotList;
    private LinearLayout llHotListTitle;
    private LinearLayout llRecentlyList;
    private LinearLayout llSearch;
    private LinearLayout llUpdate;
    private LinearLayout llYourLike;
    private LoadFloeGetRunnable loadFloeGetRunnable;
    private LinearLayout mMain_linear;
    private GameCentersDataManager manager;
    private GameCenterDataManager managerAgo;
    private LinearLayout moreHotList;
    private MyImageLoader myImageLoader;
    private GameCenterAdapter recentlyAdapter;
    private ArrayList<GamesEntity.GamesDataEntity> recentlyList;
    private RelativeLayout rlChange;
    private FrameLayout rlHotOne;
    private FrameLayout rlHotThree;
    private FrameLayout rlHotTwo;
    private RecyclerView rlYourLike;
    private RecyclerView rlvFlowgetList;
    private RecyclerView rlvRecentlyList;
    private TextView tvGameName;
    private TextView tvGameNameOne;
    private TextView tvGameNameThree;
    private TextView tvId;
    private TextView tvLeftText;
    private TextView tvOpenGame;
    private TextView tvOpenGameOne;
    private TextView tvOpenGameThree;
    private TextView tvSearch;
    private TextView tvViceTitle;
    private TextView tvViceTitleOne;
    private TextView tvViceTitleThree;
    private Map<String, String> shareMap = new HashMap();
    private Map<String, String> urlMap = new HashMap();
    private int mNum = 0;
    private String is_action = "";
    private boolean isShowPoster = false;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$startGame$11(Throwable th) throws Exception {
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
        setContentView(2131492917);
        UIUtils.setStatusBarMode(this, true, true);
        this.manager = GameCentersDataManager.getInstance();
        this.managerAgo = GameCenterDataManager.getInstance();
        this.myImageLoader = new MyImageLoader();
        try {
            initUI();
            intLoadBannerInfoCache();
            initKingKongCanche();
            loadRecentlyCache();
            loadHotListCache();
            loadFlowGetCache();
            loadYourlikeCanche();
            loadNickNameCache();
            HashMap hashMap = new HashMap();
            hashMap.put("p2", "40526");
            hashMap.put("p3", "游戏专区App客户端");
            hashMap.put("p5", "41");
            hashMap.put("p25", "2");
            hashMap.put("p32", "Android");
            hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
            this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$5UMZzPNu4ti-IS38nOidclG7Nxk
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UIUtils.logD("游戏日志结果-->", (String) obj);
                }
            });
            new Timer().schedule(new TimerTask() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.1
                @Override // java.util.TimerTask, java.lang.Runnable
                public void run() {
                }
            }, 30000L);
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSAppInstrumentation.activityCreateEndIns();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initKingKongCanche() {
        String str;
        String gameKingKongDistrict = CacheDataCenter.getInstance().getGameKingKongDistrict();
        if (TextUtils.isEmpty(gameKingKongDistrict)) {
            return;
        }
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameKingKongDistrict);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("data");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                    gamesDataEntity.setTitle(optJSONObject.optString("title"));
                    gamesDataEntity.setAndroid_version(optJSONObject.optString("android_version"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setIcon(optJSONObject.optString("icon"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    if (optJSONObject.optString("group_code") != null && !optJSONObject.optString("group_code").isEmpty()) {
                        str = "group_code";
                        gamesDataEntity.setGroupCode(optJSONObject.optString(str));
                        arrayList.add(gamesDataEntity);
                    }
                    str = "groupCode";
                    gamesDataEntity.setGroupCode(optJSONObject.optString(str));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
                if (gamesEntity.getData() != null) {
                    this.jinGangQuLi.setVisibility(0);
                    this.jingGangQuList.clear();
                    this.jingGangQuList.addAll(gamesEntity.getData());
                    setJinGangQuUI();
                    return;
                }
                this.jinGangQuLi.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void intLoadBannerInfoCache() {
        String gameBanner = CacheDataCenter.getInstance().getGameBanner();
        if (TextUtils.isEmpty(gameBanner)) {
            return;
        }
        final GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameBanner);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                String optString2 = jSONObject.optJSONObject("data").optString("carousel");
                if (optString2.startsWith("[{")) {
                    JSONArray jSONArray = new JSONArray(optString2);
                    ArrayList arrayList = new ArrayList();
                    for (int i = 0; i < jSONArray.length(); i++) {
                        JSONObject optJSONObject = jSONArray.optJSONObject(i);
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        gamesDataEntity.setHotImgUrl(optJSONObject.optString("deploy_game"));
                        gamesDataEntity.setUrl(optJSONObject.optString("gameUrl"));
                        gamesDataEntity.setQqMark(optJSONObject.optString("qq_mark"));
                        gamesDataEntity.setName(optJSONObject.optString("game_name"));
                        gamesDataEntity.setTwoGameType("");
                        gamesDataEntity.setCompany(optJSONObject.optString("company"));
                        gamesDataEntity.setId(optJSONObject.optString("game_id"));
                        gamesDataEntity.setGameType("");
                        arrayList.add(gamesDataEntity);
                    }
                    gamesEntity.setBanner(arrayList);
                    ArrayList arrayList2 = new ArrayList();
                    for (int i2 = 0; i2 < gamesEntity.getBanner().size(); i2++) {
                        arrayList2.add(gamesEntity.getBanner().get(i2).getHotImgUrl());
                    }
                    if (gamesEntity.getBanner() != null && gamesEntity.getBanner().size() > 0) {
                        this.banner_li.setVisibility(0);
                        this.ban_top.setImages(arrayList2).setImageLoader(new ImageLoader() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.2
                            @Override // com.youth.banner.loader.ImageLoaderInterface
                            public void displayImage(Context context, Object obj, ImageView imageView) {
                                GlideApp.with(App.getInstance()).load(obj).listener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.2.1
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
                        }).setDelayTime(3000).setImageLoader(this.myImageLoader).setIndicatorGravity(800).start();
                        this.ban_top.setOnBannerListener(new OnBannerListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.3
                            @Override // com.youth.banner.listener.OnBannerListener
                            public void OnBannerClick(int i3) {
                                gamesEntity.getBanner().get(i3);
                                GameCentersActivity.this.startGame(gamesEntity.getBanner().get(i3), "19");
                            }
                        });
                        return;
                    }
                    this.banner_li.setVisibility(8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void intLoadFloorInfoCache() {
        JSONArray optJSONArray;
        String gameCenterFloor = CacheDataCenter.getInstance().getGameCenterFloor();
        if (TextUtils.isEmpty(gameCenterFloor)) {
            return;
        }
        GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterFloor);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if (!"0000".equals(optString) || (optJSONArray = jSONObject.optJSONObject("data").optJSONArray("poster")) == null || optJSONArray.length() <= 0) {
                return;
            }
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    GamesEntity.PosterData posterData = new GamesEntity.PosterData();
                    String optString2 = optJSONObject.optString("deploy_game");
                    String optString3 = optJSONObject.optString("qq_mark");
                    String optString4 = optJSONObject.optString("gameUrl");
                    String optString5 = optJSONObject.optString("game_name");
                    String optString6 = optJSONObject.optString("company");
                    String optString7 = optJSONObject.optString("game_id");
                    String optString8 = optJSONObject.optString("is_action");
                    if (TextUtils.equals("null", optString2)) {
                        optString2 = "";
                    }
                    posterData.setDeploy_game(optString2);
                    if (TextUtils.equals("null", optString3)) {
                        optString3 = "";
                    }
                    posterData.setQq_mark(optString3);
                    if (TextUtils.equals("null", optString6)) {
                        optString6 = "";
                    }
                    posterData.setCompany(optString6);
                    if (TextUtils.equals("null", optString7)) {
                        optString7 = "";
                    }
                    posterData.setGame_id(optString7);
                    if (TextUtils.equals("null", optString5)) {
                        optString5 = "";
                    }
                    posterData.setGame_name(optString5);
                    if (TextUtils.equals("null", optString4)) {
                        optString4 = "";
                    }
                    posterData.setGameUrl(optString4);
                    if (TextUtils.equals("null", optString8)) {
                        optString8 = "";
                    }
                    posterData.setIs_action(optString8);
                    arrayList.add(posterData);
                }
            }
            gamesEntity.setPoster(arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadRecentlyCache() {
        String str;
        if (UserManager.getInstance().getCurrentPhoneNumber().equals("0")) {
            this.llRecentlyList.setVisibility(8);
        }
        this.recentlyList.clear();
        String gameCenterPlay = CacheDataCenter.getInstance().getGameCenterPlay();
        if (TextUtils.isEmpty(gameCenterPlay)) {
            return;
        }
        UIUtils.logD("最近在玩数据", gameCenterPlay);
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
                    gamesDataEntity.setGame_name(optJSONObject.optString("game_name"));
                    gamesDataEntity.setUrl(optJSONObject.optString("url"));
                    gamesDataEntity.setResourceId(optJSONObject.optString("resourceId"));
                    gamesDataEntity.setResource_id(optJSONObject.optString("resource_id"));
                    gamesDataEntity.setId(optJSONObject.optString("id"));
                    gamesDataEntity.setCompany(optJSONObject.optString("company"));
                    gamesDataEntity.setSmallImage(optJSONObject.optString("smallImage"));
                    gamesDataEntity.setFreeFlow(optJSONObject.optString("freeFlow"));
                    gamesDataEntity.setGameIap(optJSONObject.optString("gameIap"));
                    gamesDataEntity.setQqMark(optJSONObject.optString("qqMark"));
                    gamesDataEntity.setQq_mark(optJSONObject.optString("qq_mark"));
                    gamesDataEntity.setBoutiqueFlag(optJSONObject.optString("boutique_flag"));
                    gamesDataEntity.setLaceImgUrl(optJSONObject.optString("laceImgUrl"));
                    gamesDataEntity.setGameLabel(optJSONObject.optString("gameLabel"));
                    gamesDataEntity.setGamePersonsSum(optJSONObject.optString("personNum"));
                    gamesDataEntity.setTagName(optJSONObject.optString("tagName"));
                    if (optJSONObject.optString("group_code") != null && !optJSONObject.optString("group_code").isEmpty()) {
                        str = "group_code";
                        gamesDataEntity.setGroupCode(optJSONObject.optString(str));
                        arrayList.add(gamesDataEntity);
                    }
                    str = "groupCode";
                    gamesDataEntity.setGroupCode(optJSONObject.optString(str));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
                if (gamesEntity.getData().size() <= 30 && gamesEntity.getData().size() <= 30) {
                    if (gamesEntity.getData() != null) {
                        this.llRecentlyList.setVisibility(0);
                        for (int i2 = 0; i2 < gamesEntity.getData().size(); i2++) {
                            if (!this.recentlyList.contains(gamesEntity.getData().get(i2))) {
                                this.recentlyList.add(gamesEntity.getData().get(i2));
                            }
                        }
                        this.recentlyAdapter.notifyDataSetChanged();
                        return;
                    }
                    this.llRecentlyList.setVisibility(8);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadFlowGetCache() {
        String str;
        String flowGet = CacheDataCenter.getInstance().getFlowGet();
        if (TextUtils.isEmpty(flowGet)) {
            return;
        }
        final FlowGetEntity flowGetEntity = new FlowGetEntity();
        FlowGetEntity.ConfigBean configBean = new FlowGetEntity.ConfigBean();
        try {
            JSONObject jSONObject = new JSONObject(flowGet);
            String optString = jSONObject.optString("code");
            flowGetEntity.setCode(jSONObject.optString("code"));
            flowGetEntity.setMsg(jSONObject.optString("msg"));
            JSONObject jSONObject2 = jSONObject.getJSONObject("config");
            flowGetEntity.setCode(optString);
            if ("0000".equals(optString)) {
                JSONArray optJSONArray = jSONObject.optJSONArray("popularList");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                    optJSONObject.optString("name");
                    FlowGetEntity.PopularListBean popularListBean = new FlowGetEntity.PopularListBean();
                    popularListBean.setName(optJSONObject.optString("name"));
                    popularListBean.setMinute(optJSONObject.optString("minute"));
                    popularListBean.setPersonNum(Double.valueOf(optJSONObject.optDouble("personNum")));
                    popularListBean.setBackGroundImg(optJSONObject.optString("backGroundImg"));
                    popularListBean.setUrl(optJSONObject.optString("url"));
                    popularListBean.setSmallImage(optJSONObject.optString("smallImage"));
                    popularListBean.setCurrentMinute(optJSONObject.optInt("currentMinute"));
                    popularListBean.setState(optJSONObject.optString("state"));
                    popularListBean.setCopywriting(optJSONObject.optString("copywriting"));
                    popularListBean.setTagName(optJSONObject.optString("tagName"));
                    popularListBean.setFreeFlow(optJSONObject.optString("freeFlow"));
                    popularListBean.setQqMark(optJSONObject.optString("qqMark"));
                    popularListBean.setId(optJSONObject.optString("id"));
                    popularListBean.setCompany(optJSONObject.optString("company"));
                    if (optJSONObject.optString("group_code") != null && !optJSONObject.optString("group_code").isEmpty()) {
                        str = "group_code";
                        popularListBean.setGroupCode(optJSONObject.optString(str));
                        arrayList.add(popularListBean);
                    }
                    str = "groupCode";
                    popularListBean.setGroupCode(optJSONObject.optString(str));
                    arrayList.add(popularListBean);
                }
                flowGetEntity.setPopularList(arrayList);
                configBean.setImg_url(jSONObject2.getString("img_url"));
                configBean.setRight_text(jSONObject2.getString("right_text"));
                configBean.setLeft_text(jSONObject2.getString("left_text"));
                flowGetEntity.setConfig(configBean);
                if (flowGetEntity.getPopularList() != null) {
                    this.llHotGameArea.setVisibility(0);
                    for (int i2 = 0; i2 < flowGetEntity.getPopularList().size(); i2++) {
                        if (flowGetEntity.getPopularList().get(i2) != null && this.listBean.size() < 6) {
                            this.listBean.add(flowGetEntity.getPopularList().get(i2));
                        }
                    }
                    this.flowGetAdapter.notifyDataSetChanged();
                    GlideApp.with((FragmentActivity) this).load(flowGetEntity.getConfig().getImg_url()).into(this.ivHotIcon);
                    this.tvLeftText.setText(flowGetEntity.getConfig().getLeft_text());
                    this.ivHotTips.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.4
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            IntentManager.generateIntentAndGo(GameCentersActivity.this.activityContext, r2, "", true, "get");
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    this.llFlowGet.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.5
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            IntentManager.generateIntentAndGo(GameCentersActivity.this.activityContext, flowGetEntity.getConfig().getRight_text(), "", true, "get");
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    return;
                }
                this.llHotGameArea.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadHotListCache() {
        String str;
        String hotGet = CacheDataCenter.getInstance().getHotGet();
        if (TextUtils.isEmpty(hotGet)) {
            return;
        }
        final HotGetEntity hotGetEntity = new HotGetEntity();
        try {
            JSONObject jSONObject = new JSONObject(hotGet);
            UIUtils.logD("人气排行数据", hotGet);
            String optString = jSONObject.optString("code");
            hotGetEntity.setDesc(jSONObject.optString("desc"));
            hotGetEntity.setCode(optString);
            if (optString.equals("0000")) {
                CacheDataCenter.getInstance().setHotGet(hotGet);
            } else {
                String hotGet2 = CacheDataCenter.getInstance().getHotGet();
                if (!TextUtils.isEmpty(hotGet2)) {
                    jSONObject = new JSONObject(hotGet2);
                    hotGetEntity.setCode(jSONObject.optString("code"));
                    hotGetEntity.setDesc(jSONObject.optString("desc"));
                }
            }
            if (optString.equals("0000")) {
                HotGetEntity.DataBean dataBean = new HotGetEntity.DataBean();
                HotGetEntity.DataBean.CarouselBean carouselBean = new HotGetEntity.DataBean.CarouselBean();
                JSONObject optJSONObject = jSONObject.optJSONObject("data");
                dataBean.setMore_url(optJSONObject.optString("more_url"));
                JSONArray optJSONArray = optJSONObject.optJSONArray("carousel");
                ArrayList arrayList = new ArrayList();
                for (int i = 0; i < optJSONArray.length(); i++) {
                    carouselBean.setDeploy_game(optJSONArray.optJSONObject(i).optString("deploy_game"));
                    arrayList.add(carouselBean);
                }
                JSONArray optJSONArray2 = optJSONObject.optJSONArray("icon");
                ArrayList arrayList2 = new ArrayList();
                for (int i2 = 0; i2 < optJSONArray2.length(); i2++) {
                    JSONObject optJSONObject2 = optJSONArray2.optJSONObject(i2);
                    HotGetEntity.DataBean.IconBean iconBean = new HotGetEntity.DataBean.IconBean();
                    iconBean.setGame_name(optJSONObject2.optString("game_name"));
                    iconBean.setName(optJSONObject2.optString("name"));
                    iconBean.setGameUrl(optJSONObject2.optString("gameUrl"));
                    iconBean.setDeploy_game(optJSONObject2.optString("deploy_game"));
                    iconBean.setVice_title(optJSONObject2.optString("vice_title"));
                    iconBean.setBubble(optJSONObject2.optString("bubble"));
                    iconBean.setGame_persons_base(optJSONObject2.optString("game_persons_base"));
                    iconBean.setLocation(optJSONObject2.optInt("location"));
                    iconBean.setQq_mark(optJSONObject2.optString("qq_mark"));
                    iconBean.setQqMark(optJSONObject2.optString("qqMark"));
                    iconBean.setCompany(optJSONObject2.optString("company"));
                    iconBean.setResource_id(optJSONObject2.optString("resource_id"));
                    iconBean.setResourceId(optJSONObject2.optString("resourceId"));
                    iconBean.setTwo_game_type(optJSONObject2.optString("two_game_type"));
                    iconBean.setFree_flow(optJSONObject2.optString("free_flow"));
                    if (optJSONObject2.optString("group_code") != null && !optJSONObject2.optString("group_code").isEmpty()) {
                        str = "group_code";
                        iconBean.setGroupCode(optJSONObject2.optString(str));
                        arrayList2.add(iconBean);
                    }
                    str = "groupCode";
                    iconBean.setGroupCode(optJSONObject2.optString(str));
                    arrayList2.add(iconBean);
                }
                dataBean.setIcon(arrayList2);
                dataBean.setCarousel(arrayList);
                hotGetEntity.setData(dataBean);
                if (hotGetEntity.getData() != null) {
                    this.llHotList.setVisibility(0);
                    this.llUpdate.setVisibility(0);
                    List<HotGetEntity.DataBean.IconBean> icon = hotGetEntity.getData().getIcon();
                    this.hotlist.clear();
                    this.hotlist.addAll(icon);
                    initHotUi();
                    GlideApp.with((FragmentActivity) this).load(hotGetEntity.getData().getCarousel().get(0).getDeploy_game()).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageDeployGame);
                    this.imageDeployGame.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$Mubz9L2qPwwj-IpzqOPCawq1L7s
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            GameCentersActivity.lambda$loadHotListCache$1(GameCentersActivity.this, hotGetEntity, view);
                        }
                    });
                    final String more_url = hotGetEntity.getData().getMore_url();
                    this.moreHotList.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.7
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            IntentManager.generateIntentAndGo(GameCentersActivity.this.activityContext, more_url, "", true, "get");
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    return;
                }
                this.llHotList.setVisibility(8);
                this.llUpdate.setVisibility(8);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$loadHotListCache$1(GameCentersActivity gameCentersActivity, HotGetEntity hotGetEntity, View view) {
        final String gameUrl = hotGetEntity.getData().getCarousel().get(0).getGameUrl();
        if (App.hasLogined()) {
            if (TextUtils.isEmpty(gameUrl)) {
                return;
            }
            IntentManager.gotoWebViewActivity(gameCentersActivity.activityContext, gameUrl, "");
            return;
        }
        new AvoidOnResult(gameCentersActivity.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.6
            @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
            public void onActivityResult(int i, Intent intent) {
                if (!App.hasLogined() || TextUtils.isEmpty(gameUrl)) {
                    return;
                }
                IntentManager.gotoWebViewActivity(GameCentersActivity.this.activityContext, gameUrl, "");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadYourlikeCanche() {
        String str;
        if (UserManager.getInstance().getCurrentPhoneNumber().equals("0")) {
            this.llYourLike.setVisibility(8);
            return;
        }
        String gameCenterRecommend = CacheDataCenter.getInstance().getGameCenterRecommend();
        if (TextUtils.isEmpty(gameCenterRecommend)) {
            return;
        }
        final GamesEntity gamesEntity = new GamesEntity();
        try {
            JSONObject jSONObject = new JSONObject(gameCenterRecommend);
            String optString = jSONObject.optString("code");
            gamesEntity.setCode(optString);
            gamesEntity.setMsg(jSONObject.optString("msg"));
            if ("0000".equals(optString)) {
                CacheDataCenter.getInstance().setGameCenterRecommend(gameCenterRecommend);
            } else {
                String gameCenterRecommend2 = CacheDataCenter.getInstance().getGameCenterRecommend();
                if (!TextUtils.isEmpty(gameCenterRecommend2)) {
                    jSONObject = new JSONObject(gameCenterRecommend2);
                    optString = jSONObject.optString("code");
                    gamesEntity.setCode(optString);
                    gamesEntity.setMsg(jSONObject.optString("msg"));
                }
            }
            boolean equals = "0000".equals(optString);
            if (equals) {
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
                    gamesDataEntity.setGamePersonsSum(optJSONObject.optString("personNum"));
                    gamesDataEntity.setTagName(optJSONObject.optString("tagName"));
                    if (optJSONObject.optString("group_code") != null && !optJSONObject.optString("group_code").isEmpty()) {
                        str = "group_code";
                        gamesDataEntity.setGroupCode(optJSONObject.optString(str));
                        arrayList.add(gamesDataEntity);
                    }
                    str = "groupCode";
                    gamesDataEntity.setGroupCode(optJSONObject.optString(str));
                    arrayList.add(gamesDataEntity);
                }
                gamesEntity.setData(arrayList);
                JSONObject optJSONObject2 = jSONObject.optJSONObject("config");
                if (optJSONObject2 != null) {
                    gamesEntity.setTitle(optJSONObject2.optString("floor_name"));
                }
            }
            if (gamesEntity.getData().size() <= 3) {
                this.rlChange.setVisibility(4);
            }
            if (gamesEntity.getData() != null) {
                this.llYourLike.setVisibility(0);
                for (int i2 = 0; i2 < gamesEntity.getData().size(); i2++) {
                    if (gamesEntity.getData().get(i2) != null && this.likeListBean.size() < 3) {
                        this.likeListBean.add(gamesEntity.getData().get(i2));
                        if (this.likeListBean.size() == 3) {
                            this.flag = i2 + 1;
                        }
                    }
                }
                this.likeAdapter.notifyDataSetChanged();
            } else {
                this.llYourLike.setVisibility(8);
            }
            this.rlChange.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.8
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (GameCentersActivity.this.flag == 0) {
                        for (int i3 = 0; i3 < gamesEntity.getData().size(); i3++) {
                            if (gamesEntity.getData().get(i3) != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                GameCentersActivity.this.likeListBean.add(gamesEntity.getData().get(i3));
                                if (GameCentersActivity.this.likeListBean.size() == 3) {
                                    GameCentersActivity.this.flag = i3 + 1;
                                }
                            }
                        }
                        GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                    }
                    GameCentersActivity.this.likeListBean.clear();
                    if (gamesEntity.getData().size() % 3 == 0) {
                        for (int i4 = GameCentersActivity.this.flag; i4 < gamesEntity.getData().size(); i4++) {
                            if (gamesEntity.getData() != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                GameCentersActivity.this.likeListBean.add(gamesEntity.getData().get(i4));
                                if (GameCentersActivity.this.likeListBean.size() == 3) {
                                    GameCentersActivity.this.flag = i4 + 1;
                                }
                            }
                        }
                        GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                        if (GameCentersActivity.this.flag == gamesEntity.getData().size()) {
                            GameCentersActivity.this.flag = 0;
                        }
                    } else {
                        int i5 = GameCentersActivity.this.flag;
                        while (true) {
                            i5++;
                            if (i5 >= gamesEntity.getData().size()) {
                                break;
                            } else if (gamesEntity.getData() != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                GameCentersActivity.this.likeListBean.add(gamesEntity.getData().get(i5));
                                if (GameCentersActivity.this.likeListBean.size() == 3) {
                                    GameCentersActivity.this.flag = i5;
                                }
                            }
                        }
                        if (GameCentersActivity.this.likeListBean.size() < 3) {
                            GameCentersActivity.this.likeListBean.clear();
                            int size = gamesEntity.getData().size();
                            while (true) {
                                size--;
                                if (size < gamesEntity.getData().size() - 3) {
                                    break;
                                } else if (gamesEntity.getData().get(size) != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                    GameCentersActivity.this.likeListBean.add(gamesEntity.getData().get(size));
                                }
                            }
                            GameCentersActivity.this.flag = 0;
                        }
                        GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadNickNameCache() {
        String nickName = CacheDataCenter.getInstance().getNickName();
        NickNameEntity nickNameEntity = new NickNameEntity();
        if (TextUtils.isEmpty(nickName)) {
            return;
        }
        try {
            JSONObject jSONObject = new JSONObject(nickName);
            String optString = jSONObject.optString("respcode");
            jSONObject.optString("msg");
            if (optString.equals("0000")) {
                CacheDataCenter.getInstance().setNickName(nickName);
            } else if (!TextUtils.isEmpty(CacheDataCenter.getInstance().getNickName())) {
                nickNameEntity.setMsg(jSONObject.optString("msg"));
                nickNameEntity.setRespcode(jSONObject.optString("respcode"));
            }
            if (optString.equals("0000")) {
                NickNameEntity.RespDataBean respDataBean = new NickNameEntity.RespDataBean();
                respDataBean.setNickName(jSONObject.optJSONObject("respData").optString("nickName"));
                nickNameEntity.setRespData(respDataBean);
                if (nickNameEntity.getRespData().getNickName() != null) {
                    TextView textView = this.tvId;
                    textView.setText(nickNameEntity.getRespData().getNickName() + "的专属定制");
                    return;
                }
                this.tvId.setText("您的专属定制");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        initData();
        NBSAppInstrumentation.activityResumeEndIns();
    }

    private void initUI() {
        this.ban_top = (Banner) findViewById(2131296497);
        this.banner_li = (LinearLayout) findViewById(2131296503);
        this.tvSearch = (TextView) findViewById(2131299072);
        this.rlvRecentlyList = (RecyclerView) findViewById(2131298408);
        this.llRecentlyList = (LinearLayout) findViewById(2131297765);
        this.llYourLike = (LinearLayout) findViewById(2131297801);
        this.rlYourLike = (RecyclerView) findViewById(2131298396);
        this.tvId = (TextView) findViewById(2131298971);
        this.mMain_linear = (LinearLayout) findViewById(2131297998);
        this.llSearch = (LinearLayout) findViewById(2131297774);
        this.rlvFlowgetList = (RecyclerView) findViewById(2131298401);
        this.jinGangQuLi = (LinearLayout) findViewById(2131297535);
        this.ivHotIcon = (ImageView) findViewById(2131297394);
        this.ivHotTips = (ImageView) findViewById(2131297396);
        this.imageDeployGame = (ImageView) findViewById(2131297270);
        this.tvLeftText = (TextView) findViewById(2131299001);
        this.ivToright = (ImageView) findViewById(2131297514);
        this.moreHotList = (LinearLayout) findViewById(2131298083);
        this.imageBubbleLeft = (ImageView) findViewById(2131297266);
        this.imageDeployGameLeft = (ImageView) findViewById(2131297273);
        this.tvGameName = (TextView) findViewById(2131298955);
        this.tvViceTitle = (TextView) findViewById(2131299137);
        this.tvOpenGame = (TextView) findViewById(2131299032);
        this.imageBubbleMiddle = (ImageView) findViewById(2131297267);
        this.firstOne = (ImageView) findViewById(2131296970);
        this.imageDeployGameMiddle = (ImageView) findViewById(2131297271);
        this.tvGameNameOne = (TextView) findViewById(2131298956);
        this.tvViceTitleOne = (TextView) findViewById(2131299138);
        this.tvOpenGameOne = (TextView) findViewById(2131299033);
        this.imageBubbleRight = (ImageView) findViewById(2131297268);
        this.imageDeployGameRight = (ImageView) findViewById(2131297272);
        this.tvGameNameThree = (TextView) findViewById(2131298957);
        this.tvViceTitleThree = (TextView) findViewById(2131299139);
        this.tvOpenGameThree = (TextView) findViewById(2131299034);
        this.rlChange = (RelativeLayout) findViewById(2131298327);
        this.gamePosterImg = (ImageView) findViewById(2131297070);
        this.gamePosterClose = (ImageView) findViewById(2131297069);
        this.gamePosterView = (RelativeLayout) findViewById(2131297071);
        this.llBtnGone = findViewById(2131297695);
        this.llHotListTitle = (LinearLayout) findViewById(2131297725);
        this.rlHotOne = (FrameLayout) findViewById(2131298340);
        this.rlHotTwo = (FrameLayout) findViewById(2131298342);
        this.rlHotThree = (FrameLayout) findViewById(2131298341);
        this.llUpdate = (LinearLayout) findViewById(2131297794);
        this.llHotList = (LinearLayout) findViewById(2131297724);
        this.llHotGameArea = (RelativeLayout) findViewById(2131297723);
        this.llFlowGet = (LinearLayout) findViewById(2131297714);
        this.imageDeployGame.setAdjustViewBounds(true);
        this.tvSearch.setOnClickListener(this);
        this.gamePosterClose.setOnClickListener(this);
        this.llBtnGone.setOnClickListener(this);
        this.listBean = new ArrayList<>();
        this.rlvFlowgetList.setLayoutManager(new GridLayoutManager(this, 3));
        this.flowGetAdapter = new FlowGetAdapter(this.listBean, this);
        this.rlvFlowgetList.setAdapter(this.flowGetAdapter);
        this.flowGetAdapter.onItemClistener(new C87619());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(0);
        this.rlvRecentlyList.setLayoutManager(linearLayoutManager);
        this.recentlyList = new ArrayList<>();
        this.recentlyAdapter = new GameCenterAdapter(this, this.recentlyList, 2131493215);
        this.rlvRecentlyList.setAdapter(this.recentlyAdapter);
        this.recentlyAdapter.setListener(new GameCenterAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$jx2dqftGg9qVCpPKK06oEVdbOvY
            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameCenterAdapter.GameClickedListener
            public final void cliced(GamesEntity.GamesDataEntity gamesDataEntity) {
                GameCentersActivity.this.startGame(gamesDataEntity, "0");
            }
        });
        this.hotlist = new ArrayList<>();
        this.jingGangQuList = new ArrayList<>();
        this.likeListBean = new ArrayList<>();
        this.rlYourLike.setLayoutManager(new LinearLayoutManager(this));
        this.likeAdapter = new GameCenterAdapter(this, this.likeListBean, 2131493216);
        this.rlYourLike.setAdapter(this.likeAdapter);
        this.likeAdapter.setListener(new GameCenterAdapter.GameClickedListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.10
            @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.GameCenterAdapter.GameClickedListener
            public void cliced(GamesEntity.GamesDataEntity gamesDataEntity) {
                GameCentersActivity.this.startGame(gamesDataEntity, "30");
            }
        });
        this.closePosterRunnable = new ClosePosterRunnable();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity$9 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C87619 implements FlowGetAdapter.onItemClistener {
        C87619() {
        }

        @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.FlowGetAdapter.onItemClistener
        public void OnItem(FlowGetEntity.PopularListBean popularListBean) {
            IntentManager.generateIntentAndGo(GameCentersActivity.this, popularListBean.getUrl(), "", true, "get");
            GameCentersLogManager.log(GameCentersActivity.this.activityContext, popularListBean.getGameType(), popularListBean.getId() == null ? popularListBean.getGame_id() : popularListBean.getId(), popularListBean.getName().isEmpty() ? popularListBean.getGame_name() : popularListBean.getName(), popularListBean.getCompany(), "3", popularListBean.getTwoGameType(), ("Y".equals(popularListBean.getQqMark()) || "Y".equals(popularListBean.getQq_mark())) ? "1" : "2", popularListBean.getResourceId().isEmpty() ? popularListBean.getResource_id() : popularListBean.getResourceId(), popularListBean.getGroupCode());
        }

        @Override // com.sinovatech.unicom.separatemodule.gamecenter.adapter.FlowGetAdapter.onItemClistener
        public void onFlowGet(final FlowGetEntity.PopularListBean popularListBean) {
            if (UserManager.getInstance().isYiwang()) {
                UIUtils.showCenterOnlyTextToast(GameCentersActivity.this.activityContext, "仅限联通手机号码领取", 1);
            } else {
                GameCentersActivity.this.managerAgo.flowGet(GameCentersActivity.this.activityContext, popularListBean.getId()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$9$mU_n-SarEGZbznq8rPDl5SY9-Vw
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        GameCentersActivity.C87619.lambda$onFlowGet$0(GameCentersActivity.C87619.this, popularListBean, (FlowGetResultEntity) obj);
                    }
                }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$9$mJ_vCTurYOqtNTAxtGj6nWz2bj4
                    @Override // io.reactivex.functions.Consumer
                    public final void accept(Object obj) {
                        Throwable th = (Throwable) obj;
                        UIUtils.showCenterOnlyTextToast(GameCentersActivity.this.activityContext, "领取失败请稍后再试", 1);
                    }
                });
            }
        }

        public static /* synthetic */ void lambda$onFlowGet$0(C87619 c87619, FlowGetEntity.PopularListBean popularListBean, FlowGetResultEntity flowGetResultEntity) throws Exception {
            UIUtils.showCenterOnlyTextToast(GameCentersActivity.this.activityContext, flowGetResultEntity.getMsg(), 1);
            if ("0000".equals(flowGetResultEntity.getCode())) {
                popularListBean.setState("2");
                GameCentersActivity.this.flowGetAdapter.notifyDataSetChanged();
                GameCentersActivity.this.initFlowGet();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setJinGangQuUI() {
        this.mNum = 0;
        LayoutInflater from = LayoutInflater.from(this);
        int ceil = (int) Math.ceil((this.jingGangQuList.size() * 1.0d) / 5);
        ViewPager viewPager = new ViewPager(this);
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < ceil; i++) {
            GridView gridView = (GridView) from.inflate(2131493255, (ViewGroup) viewPager, false);
            gridView.setAdapter((ListAdapter) new GridViewAdapter(this, this.jingGangQuList, i));
            arrayList.add(gridView);
        }
        viewPager.setAdapter(new MyViewPagerAdapter(arrayList));
        viewPager.setLayoutParams(new LinearLayout.LayoutParams(-1, -1));
        this.mMain_linear.removeAllViews();
        for (int i2 = 0; i2 < ceil; i2++) {
            View view = new View(this);
            view.setBackgroundResource(2131232155);
            view.setEnabled(false);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(10, 10);
            layoutParams.leftMargin = 10;
            this.mMain_linear.addView(view, layoutParams);
            UIUtils.logD("子view", this.mMain_linear.getChildCount() + "");
        }
        UIUtils.logD("页面数量", ceil + "");
        this.mMain_linear.getChildAt(this.mNum).setEnabled(true);
        this.jinGangQuLi.removeAllViews();
        this.jinGangQuLi.addView(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.11
            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrollStateChanged(int i3) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageScrolled(int i3, float f, int i4) {
            }

            @Override // android.support.p083v4.view.ViewPager.OnPageChangeListener
            public void onPageSelected(int i3) {
                NBSActionInstrumentation.onPageSelectedEnter(i3, this);
                GameCentersActivity.this.mMain_linear.getChildAt(GameCentersActivity.this.mNum).setEnabled(false);
                GameCentersActivity.this.mMain_linear.getChildAt(i3).setEnabled(true);
                GameCentersActivity.this.mNum = i3;
                NBSActionInstrumentation.onPageSelectedExit();
            }
        });
    }

    private void initData() {
        new Handler().postDelayed(new Runnable() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.12
            @Override // java.lang.Runnable
            public void run() {
                GameCentersActivity.this.initFloorInfo();
                GameCentersActivity.this.initBannerInfo();
                GameCentersActivity.this.initJinGangQu();
                GameCentersActivity.this.initRecentlyList();
                GameCentersActivity.this.initFlowGet();
                GameCentersActivity.this.initHotList();
                GameCentersActivity.this.initYourLikeList();
                GameCentersActivity.this.initNickName();
            }
        }, 200L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initJinGangQu() {
        this.manager.postKingKongDistrict(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.13
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NotNull GamesEntity gamesEntity) {
                if (gamesEntity.getData() != null) {
                    GameCentersActivity.this.jingGangQuList.clear();
                    GameCentersActivity.this.jingGangQuList.addAll(gamesEntity.getData());
                    GameCentersActivity.this.setJinGangQuUI();
                    GameCentersActivity.this.jinGangQuLi.setVisibility(0);
                    return;
                }
                GameCentersActivity.this.jinGangQuLi.setVisibility(8);
            }

            @Override // io.reactivex.Observer
            public void onError(@NotNull Throwable th) {
                GameCentersActivity.this.initKingKongCanche();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initBannerInfo() {
        this.managerAgo.getGameBannerData(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.14
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NotNull final GamesEntity gamesEntity) {
                ArrayList arrayList = new ArrayList();
                UIUtils.logD("bannerdata", "banner长度" + gamesEntity.getBanner().size());
                for (int i = 0; i < gamesEntity.getBanner().size(); i++) {
                    arrayList.add(gamesEntity.getBanner().get(i).getHotImgUrl());
                }
                if (gamesEntity.getBanner() == null || gamesEntity.getBanner().size() <= 0) {
                    GameCentersActivity.this.banner_li.setVisibility(8);
                    return;
                }
                GameCentersActivity.this.banner_li.setVisibility(0);
                GameCentersActivity.this.ban_top.setImages(arrayList).setImageLoader(new ImageLoader() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.14.1
                    @Override // com.youth.banner.loader.ImageLoaderInterface
                    public void displayImage(Context context, Object obj, ImageView imageView) {
                        GlideApp.with(context).load(obj).into(imageView);
                    }
                }).setDelayTime(3000).setImageLoader(GameCentersActivity.this.myImageLoader).setIndicatorGravity(800).start();
                GameCentersActivity.this.ban_top.setOnBannerListener(new OnBannerListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.14.2
                    @Override // com.youth.banner.listener.OnBannerListener
                    public void OnBannerClick(int i2) {
                        gamesEntity.getBanner().get(i2);
                        GameCentersActivity.this.startGame(gamesEntity.getBanner().get(i2), "19");
                    }
                });
            }

            @Override // io.reactivex.Observer
            public void onError(@NotNull Throwable th) {
                GameCentersActivity.this.intLoadBannerInfoCache();
            }
        });
        this.manager.getHuabaoUrl(this).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$kd6ee_pYudw0gEcTt0cdl-ZCUdc
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$initBannerInfo$3(GameCentersActivity.this, (Map) obj);
            }
        });
        this.manager.getFloorUrl(this).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$NONiBm5ZhN9sQe-SQz0wDembc9k
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$initBannerInfo$4(GameCentersActivity.this, (Map) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$VK3LHZCl_mOb_DncJueVZFwL2qw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$initBannerInfo$5(GameCentersActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$initBannerInfo$3(GameCentersActivity gameCentersActivity, Map map) throws Exception {
        if (gameCentersActivity.shareMap.isEmpty()) {
            gameCentersActivity.shareMap = map;
        }
    }

    public static /* synthetic */ void lambda$initBannerInfo$4(GameCentersActivity gameCentersActivity, Map map) throws Exception {
        if (gameCentersActivity.urlMap.isEmpty()) {
            gameCentersActivity.urlMap = map;
        }
    }

    public static /* synthetic */ void lambda$initBannerInfo$5(GameCentersActivity gameCentersActivity, Throwable th) throws Exception {
        if (gameCentersActivity.urlMap == null) {
            gameCentersActivity.urlMap = new HashMap();
        }
        JSONObject jSONObject = new JSONObject(CacheDataCenter.getInstance().getMapUrl());
        if ("0000".equals(jSONObject.optString("code"))) {
            String optString = jSONObject.optJSONObject("data").optString("search_url");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            gameCentersActivity.urlMap.put("search_url", optString);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity$15 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C873715 implements Observer<HotGetEntity> {
        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(@NotNull Disposable disposable) {
        }

        C873715() {
        }

        @Override // io.reactivex.Observer
        public void onNext(@NotNull final HotGetEntity hotGetEntity) {
            if (hotGetEntity.getData() != null) {
                GameCentersActivity.this.llHotList.setVisibility(0);
                GameCentersActivity.this.llUpdate.setVisibility(0);
                GameCentersActivity.this.entity = hotGetEntity;
                List<HotGetEntity.DataBean.IconBean> icon = hotGetEntity.getData().getIcon();
                GameCentersActivity.this.hotlist.clear();
                GameCentersActivity.this.hotlist.addAll(icon);
                GameCentersActivity.this.initHotUi();
                GlideApp.with((FragmentActivity) GameCentersActivity.this).load(hotGetEntity.getData().getCarousel().get(0).getDeploy_game()).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(GameCentersActivity.this.imageDeployGame);
                GameCentersActivity.this.imageDeployGame.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$15$esxxNOumxD_sq_I72hnGm3zE0GE
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        GameCentersActivity.C873715.lambda$onNext$0(GameCentersActivity.C873715.this, hotGetEntity, view);
                    }
                });
                final String more_url = hotGetEntity.getData().getMore_url();
                GameCentersActivity.this.moreHotList.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.15.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        IntentManager.generateIntentAndGo(GameCentersActivity.this.activityContext, more_url, "", true, "get");
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                return;
            }
            GameCentersActivity.this.llHotList.setVisibility(8);
            GameCentersActivity.this.llUpdate.setVisibility(8);
        }

        public static /* synthetic */ void lambda$onNext$0(C873715 c873715, final HotGetEntity hotGetEntity, View view) {
            final String gameUrl = hotGetEntity.getData().getCarousel().get(0).getGameUrl();
            if (App.hasLogined()) {
                if (TextUtils.isEmpty(gameUrl)) {
                    return;
                }
                IntentManager.gotoWebViewActivity(GameCentersActivity.this.activityContext, gameUrl, "");
                GameCentersLogManager.log(GameCentersActivity.this.activityContext, "", gameUrl, "", "", "31", "", "", "", "");
                return;
            }
            new AvoidOnResult(GameCentersActivity.this.activityContext).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.15.1
                @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                public void onActivityResult(int i, Intent intent) {
                    if (!App.hasLogined() || TextUtils.isEmpty(gameUrl)) {
                        return;
                    }
                    IntentManager.gotoWebViewActivity(GameCentersActivity.this.activityContext, gameUrl, "");
                    GameCentersLogManager.log(GameCentersActivity.this.activityContext, "", "", hotGetEntity.getData().getTitle(), "", "31", "", "", "", "");
                }
            });
        }

        @Override // io.reactivex.Observer
        public void onError(@NotNull Throwable th) {
            GameCentersActivity.this.loadHotListCache();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHotList() {
        this.manager.getHotList(this).subscribe(new C873715());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initHotUi() {
        int size = this.hotlist.size();
        if (size > 0) {
            final HotGetEntity.DataBean.IconBean iconBean = this.hotlist.get(0);
            GlideApp.with((FragmentActivity) this).load(iconBean.getBubble()).transform((Transformation<Bitmap>) new RoundedCornersTransformation(15, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageBubbleMiddle);
            GlideApp.with((FragmentActivity) this).load(iconBean.getDeploy_game()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageDeployGameMiddle);
            this.tvGameNameOne.setText(iconBean.getGame_name());
            TextView textView = this.tvViceTitleOne;
            textView.setText(getFomatNum(iconBean.getGame_persons_base()) + "人在玩");
            this.rlHotTwo.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.16
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    GameCentersActivity.this.startGameHotList(iconBean, "26");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
        if (size > 1) {
            final HotGetEntity.DataBean.IconBean iconBean2 = this.hotlist.get(1);
            GlideApp.with((FragmentActivity) this).load(iconBean2.getBubble()).transform((Transformation<Bitmap>) new RoundedCornersTransformation(15, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageBubbleLeft);
            GlideApp.with((FragmentActivity) this).load(iconBean2.getDeploy_game()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageDeployGameLeft);
            this.tvGameName.setText(iconBean2.getGame_name());
            TextView textView2 = this.tvViceTitle;
            textView2.setText(getFomatNum(iconBean2.getGame_persons_base()) + "人在玩");
            this.rlHotOne.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.17
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    GameCentersActivity.this.startGameHotList(iconBean2, "26");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
        if (size > 2) {
            final HotGetEntity.DataBean.IconBean iconBean3 = this.hotlist.get(2);
            GlideApp.with((FragmentActivity) this).load(iconBean3.getBubble()).transform((Transformation<Bitmap>) new RoundedCornersTransformation(15, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageBubbleRight);
            GlideApp.with((FragmentActivity) this).load(iconBean3.getDeploy_game()).placeholder(2131231337).error(2131231337).transform((Transformation<Bitmap>) new RoundedCornersTransformation(27, 0, RoundedCornersTransformation.CornerType.ALL)).into(this.imageDeployGameRight);
            this.tvGameNameThree.setText(iconBean3.getGame_name());
            TextView textView3 = this.tvViceTitleThree;
            textView3.setText(getFomatNum(iconBean3.getGame_persons_base()) + "人在玩");
            this.rlHotThree.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.18
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    GameCentersActivity.this.startGameHotList(iconBean3, "26");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        }
    }

    private String getFomatNum(String str) {
        try {
            if (Float.parseFloat(str) > 10000.0f) {
                BigDecimal divide = new BigDecimal(str).divide(new BigDecimal("10000"));
                DecimalFormat decimalFormat = new DecimalFormat("###.#");
                decimalFormat.setRoundingMode(RoundingMode.UP);
                return decimalFormat.format(divide) + "万";
            }
            return str;
        } catch (Exception e) {
            e.printStackTrace();
            return str;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFlowGet() {
        this.manager.postFlowGet(this).subscribe(new Observer<FlowGetEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.19
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NotNull final FlowGetEntity flowGetEntity) {
                if (flowGetEntity.getPopularList() != null) {
                    GameCentersActivity.this.llHotGameArea.setVisibility(0);
                    List<FlowGetEntity.PopularListBean> popularList = flowGetEntity.getPopularList();
                    if (popularList != null && popularList.size() > 6) {
                        popularList = popularList.subList(0, 6);
                    }
                    GameCentersActivity.this.listBean.clear();
                    GameCentersActivity.this.listBean.addAll(popularList);
                    GameCentersActivity.this.flowGetAdapter.notifyDataSetChanged();
                    GlideApp.with((FragmentActivity) GameCentersActivity.this).load(flowGetEntity.getConfig().getImg_url()).into(GameCentersActivity.this.ivHotIcon);
                    GameCentersActivity.this.tvLeftText.setText(flowGetEntity.getConfig().getLeft_text());
                    GameCentersActivity.this.ivHotTips.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.19.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            IntentManager.generateIntentAndGo(GameCentersActivity.this.activityContext, r2, "", true, "get");
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    GameCentersActivity.this.llFlowGet.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.19.2
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            Tracker.onClick(view);
                            IntentManager.generateIntentAndGo(GameCentersActivity.this.activityContext, flowGetEntity.getConfig().getRight_text(), "", true, "get");
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    });
                    return;
                }
                GameCentersActivity.this.llHotGameArea.setVisibility(8);
            }

            @Override // io.reactivex.Observer
            public void onError(@NotNull Throwable th) {
                GameCentersActivity.this.loadFlowGetCache();
            }
        });
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        Map<String, String> map;
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        int id = view.getId();
        if (id == 2131297069) {
            Handler handler = this.handler;
            if (handler != null) {
                handler.removeCallbacks(this.closePosterRunnable);
            }
            posterLog("", "4", "34");
            closePoster();
        } else if (id == 2131297695) {
            finish();
        } else if (id == 2131299072 && (map = this.urlMap) != null && map.containsKey("search_url")) {
            IntentManager.generateIntentAndGo(this.activityContext, this.urlMap.get("search_url"), "", true, "get");
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initFloorInfo() {
        this.managerAgo.getGameCenterFloor(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.20
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(@NotNull Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NotNull GamesEntity gamesEntity) {
                if (GameCentersActivity.this.isShowPoster) {
                    return;
                }
                GameCentersActivity.this.showPoster(gamesEntity);
            }
        });
        this.manager.getHuabaoUrl(this).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$gMrtiKucHtesR5OuDlD-miFStQ4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$initFloorInfo$6(GameCentersActivity.this, (Map) obj);
            }
        });
        this.manager.getFloorUrl(this).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$OmyNXPXAPmBtdEGzP22fG_QHjWw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$initFloorInfo$7(GameCentersActivity.this, (Map) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$vMN4eiBl9dwCc8_LpXBr2SSAmyw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$initFloorInfo$8(GameCentersActivity.this, (Throwable) obj);
            }
        });
    }

    public static /* synthetic */ void lambda$initFloorInfo$6(GameCentersActivity gameCentersActivity, Map map) throws Exception {
        if (gameCentersActivity.shareMap.isEmpty()) {
            gameCentersActivity.shareMap = map;
        }
    }

    public static /* synthetic */ void lambda$initFloorInfo$7(GameCentersActivity gameCentersActivity, Map map) throws Exception {
        if (gameCentersActivity.urlMap.isEmpty()) {
            gameCentersActivity.urlMap = map;
        }
    }

    public static /* synthetic */ void lambda$initFloorInfo$8(GameCentersActivity gameCentersActivity, Throwable th) throws Exception {
        if (gameCentersActivity.urlMap == null) {
            gameCentersActivity.urlMap = new HashMap();
        }
        JSONObject jSONObject = new JSONObject(CacheDataCenter.getInstance().getMapUrl());
        if ("0000".equals(jSONObject.optString("code"))) {
            String optString = jSONObject.optJSONObject("data").optString("search_url");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            gameCentersActivity.urlMap.put("search_url", optString);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initRecentlyList() {
        if (!App.hasLogined()) {
            UIUtils.logD("登录状态", UserManager.getInstance().getCurrentPhoneNumber());
            this.llRecentlyList.setVisibility(8);
        } else {
            this.llRecentlyList.setVisibility(0);
        }
        this.manager.postRecentlyList(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.21
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(GamesEntity gamesEntity) {
                if (gamesEntity.getData() == null || !gamesEntity.getCode().equals("0000")) {
                    GameCentersActivity.this.llRecentlyList.setVisibility(8);
                    return;
                }
                GameCentersActivity.this.recentlyList.clear();
                for (int i = 0; i < gamesEntity.getData().size(); i++) {
                    if (!GameCentersActivity.this.recentlyList.contains(gamesEntity.getData().get(i)) && GameCentersActivity.this.recentlyList.size() < 30) {
                        GameCentersActivity.this.recentlyList.add(gamesEntity.getData().get(i));
                    }
                }
                GameCentersActivity.this.recentlyAdapter.notifyDataSetChanged();
            }

            @Override // io.reactivex.Observer
            public void onError(Throwable th) {
                GameCentersActivity.this.loadRecentlyCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initYourLikeList() {
        if (!App.hasLogined()) {
            this.llYourLike.setVisibility(8);
        } else {
            this.manager.postRecommendList(this).subscribe(new Observer<GamesEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.22
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull GamesEntity gamesEntity) {
                    if (gamesEntity == null) {
                        GameCentersActivity.this.llYourLike.setVisibility(8);
                        return;
                    }
                    if (gamesEntity.getData() != null && gamesEntity.getData().size() <= 3) {
                        GameCentersActivity.this.rlChange.setVisibility(4);
                    }
                    if (gamesEntity.getData() == null || gamesEntity.getData().size() <= 0) {
                        GameCentersActivity.this.llYourLike.setVisibility(8);
                    } else {
                        GameCentersActivity.this.llYourLike.setVisibility(0);
                        for (int i = 0; i < gamesEntity.getData().size(); i++) {
                            if (gamesEntity.getData().get(i) != null && GameCentersActivity.this.likeListBean.size() < 3 && !"新人礼包".equals(gamesEntity.getData().get(i).getTitle())) {
                                GameCentersActivity.this.likeListBean.add(gamesEntity.getData().get(i));
                                if (GameCentersActivity.this.likeListBean.size() == 3) {
                                    GameCentersActivity.this.flag = i + 1;
                                }
                            }
                        }
                        GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                    }
                    GameCentersActivity.this.rlChange.setOnClickListener(new View$OnClickListenerC87511(gamesEntity));
                }

                /* JADX INFO: Access modifiers changed from: package-private */
                @NBSInstrumented
                /* renamed from: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity$22$1 */
                /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
                public class View$OnClickListenerC87511 implements View.OnClickListener {
                    final /* synthetic */ GamesEntity val$gamesEntity;

                    View$OnClickListenerC87511(GamesEntity gamesEntity) {
                        this.val$gamesEntity = gamesEntity;
                    }

                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        try {
                            HashMap hashMap = new HashMap();
                            hashMap.put("p2", "40526");
                            hashMap.put("p3", "游戏专区App客户端");
                            hashMap.put("p5", "55");
                            hashMap.put("p25", "1");
                            hashMap.put("p32", "Android");
                            hashMap.put("p34", GameCentersActivity.this.getString(2131886969).split("@")[1]);
                            GameCentersDataManager.getInstance().commonLog(GameCentersActivity.this, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$22$1$ilbqyJZDqLxjqkrx0E_uZZyspXI
                                @Override // io.reactivex.functions.Consumer
                                public final void accept(Object obj) {
                                    UIUtils.logD("游戏日志结果-->", (String) obj);
                                }
                            });
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        if (GameCentersActivity.this.flag == 0) {
                            for (int i = 0; i < this.val$gamesEntity.getData().size(); i++) {
                                if (this.val$gamesEntity.getData().get(i) != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                    GameCentersActivity.this.likeListBean.add(this.val$gamesEntity.getData().get(i));
                                    if (GameCentersActivity.this.likeListBean.size() == 3) {
                                        GameCentersActivity.this.flag = i + 1;
                                    }
                                }
                            }
                            GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                        }
                        GameCentersActivity.this.likeListBean.clear();
                        if (this.val$gamesEntity.getData().size() % 3 == 0) {
                            for (int i2 = GameCentersActivity.this.flag; i2 < this.val$gamesEntity.getData().size(); i2++) {
                                if (this.val$gamesEntity.getData() != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                    GameCentersActivity.this.likeListBean.add(this.val$gamesEntity.getData().get(i2));
                                    if (GameCentersActivity.this.likeListBean.size() == 3) {
                                        GameCentersActivity.this.flag = i2 + 1;
                                    }
                                }
                            }
                            GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                            if (GameCentersActivity.this.flag == this.val$gamesEntity.getData().size()) {
                                GameCentersActivity.this.flag = 0;
                            }
                        } else {
                            for (int i3 = GameCentersActivity.this.flag + 1; i3 < this.val$gamesEntity.getData().size(); i3++) {
                                if (this.val$gamesEntity.getData() != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                    GameCentersActivity.this.likeListBean.add(this.val$gamesEntity.getData().get(i3));
                                    if (GameCentersActivity.this.likeListBean.size() == 3) {
                                        GameCentersActivity.this.flag = i3;
                                    }
                                }
                            }
                            if (GameCentersActivity.this.likeListBean.size() < 3) {
                                GameCentersActivity.this.likeListBean.clear();
                                for (int size = this.val$gamesEntity.getData().size() - 1; size >= this.val$gamesEntity.getData().size() - 3; size--) {
                                    if (this.val$gamesEntity.getData().get(size) != null && GameCentersActivity.this.likeListBean.size() < 3) {
                                        GameCentersActivity.this.likeListBean.add(this.val$gamesEntity.getData().get(size));
                                    }
                                }
                                GameCentersActivity.this.flag = 0;
                            }
                            GameCentersActivity.this.likeAdapter.notifyDataSetChanged();
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    GameCentersActivity.this.loadYourlikeCanche();
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initNickName() {
        this.manager.postNickName(this).subscribe(new Observer<NickNameEntity>() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.23
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NotNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NotNull NickNameEntity nickNameEntity) {
                if (nickNameEntity.getRespData().getNickName() != null) {
                    TextView textView = GameCentersActivity.this.tvId;
                    textView.setText(nickNameEntity.getRespData().getNickName() + "的专属定制");
                } else if (UserManager.getInstance().getCurrentPhoneNumber().equals("0")) {
                } else {
                    String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
                    TextView textView2 = GameCentersActivity.this.tvId;
                    textView2.setText(currentPhoneNumber + "的专属定制");
                }
            }

            @Override // io.reactivex.Observer
            public void onError(@NotNull Throwable th) {
                GameCentersActivity.this.loadNickNameCache();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showPoster(GamesEntity gamesEntity) {
        int i;
        final GamesEntity.PosterData posterData;
        this.isShowPoster = true;
        UIUtils.logD("游戏海报", gamesEntity.getPoster().get(0).getDeploy_game());
        if (gamesEntity != null) {
            this.is_action = "";
            List<GamesEntity.PosterData> poster = gamesEntity.getPoster();
            if (poster == null || poster.size() <= 0 || (i = App.getSharePreferenceUtil().getInt("game_poster_postion")) < 0 || i >= poster.size() || (posterData = poster.get(i)) == null) {
                return;
            }
            this.is_action = posterData.getIs_action();
            GlideApp.with((FragmentActivity) this.activityContext).load(posterData.getDeploy_game()).placeholder(2131231244).error(2131231244).into(this.gamePosterImg);
            this.gamePosterView.setVisibility(0);
            this.gamePosterView.setClickable(true);
            this.gamePosterView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.24
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!TextUtils.isEmpty(posterData.getGameUrl())) {
                        if (GameCentersActivity.this.handler != null) {
                            GameCentersActivity.this.handler.removeCallbacks(GameCentersActivity.this.closePosterRunnable);
                        }
                        GameCentersActivity.this.posterLog("", "4", "33");
                        GamesEntity.GamesDataEntity gamesDataEntity = new GamesEntity.GamesDataEntity();
                        String str = TextUtils.isEmpty(posterData.getQq_mark()) ? "N" : "Y";
                        gamesDataEntity.setHotImgUrl(posterData.getDeploy_game());
                        gamesDataEntity.setUrl(posterData.getGameUrl());
                        gamesDataEntity.setQqMark(str);
                        gamesDataEntity.setName(posterData.getGame_name());
                        gamesDataEntity.setTwoGameType("");
                        gamesDataEntity.setCompany(posterData.getCompany());
                        gamesDataEntity.setId(posterData.getGame_id());
                        gamesDataEntity.setGameType("");
                        GameCentersActivity.this.startGame(gamesDataEntity, "");
                        GameCentersActivity.this.gamePosterView.setVisibility(8);
                        GameCentersActivity.this.gamePosterView.setClickable(false);
                        GameCentersActivity.this.gamePosterView.clearAnimation();
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
            GameCentersActivity.this.closePoster();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    class LoadFloeGetRunnable implements Runnable {
        @Override // java.lang.Runnable
        public void run() {
        }

        private LoadFloeGetRunnable() {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void closePoster() {
        if (TextUtils.equals("1", this.is_action)) {
            ScaleAnimation scaleAnimation = new ScaleAnimation(1.0f, 1.0f, 1.0f, 0.0f, 1, 0.5f, 1, 0.5f);
            scaleAnimation.setDuration(500L);
            scaleAnimation.setFillAfter(true);
            scaleAnimation.setRepeatCount(0);
            scaleAnimation.setAnimationListener(new Animation.AnimationListener() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.GameCentersActivity.25
                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationRepeat(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationStart(Animation animation) {
                }

                @Override // android.view.animation.Animation.AnimationListener
                public void onAnimationEnd(Animation animation) {
                    GameCentersActivity.this.gamePosterView.setVisibility(8);
                    GameCentersActivity.this.gamePosterView.setClickable(false);
                }
            });
            RelativeLayout relativeLayout = this.gamePosterView;
            if (relativeLayout != null) {
                relativeLayout.startAnimation(scaleAnimation);
                return;
            }
            return;
        }
        this.gamePosterView.setVisibility(8);
        this.gamePosterView.setClickable(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGameHotList(final HotGetEntity.DataBean.IconBean iconBean, String str) {
        if ("Y".equals(iconBean.getQq_mark()) || "Y".equals(iconBean.getQqMark())) {
            MiniGameUtils.showToast();
        } else {
            IntentManager.generateIntentAndGo(this.activityContext, iconBean.getGameUrl(), iconBean.getName(), true, "get");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p25", iconBean.getGameType());
        hashMap.put("p26", iconBean.getGame_id() == null ? iconBean.getId() : iconBean.getGame_id());
        hashMap.put("p27", iconBean.getName() == null ? iconBean.getGame_name() : iconBean.getName());
        hashMap.put("p28", iconBean.getCompany());
        hashMap.put("p31", str);
        hashMap.put("p32", "Android");
        hashMap.put("p33", iconBean.getGameType());
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        hashMap.put("p35", ("Y".equals(iconBean.getQq_mark()) || "Y".equals(iconBean.getQqMark())) ? "1" : "2");
        hashMap.put("p23", "");
        hashMap.put("p16", (iconBean.getResource_id() == null || iconBean.getResource_id().isEmpty()) ? iconBean.getResourceId() : iconBean.getResource_id());
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$eveuoT817LnpsMQ8CYsZbjuNLJ4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$startGameHotList$9(HotGetEntity.DataBean.IconBean.this, (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$startGameHotList$9(HotGetEntity.DataBean.IconBean iconBean, String str) throws Exception {
        if (iconBean.getResource_id() == null || iconBean.getResource_id().isEmpty()) {
            iconBean.getResourceId();
        } else {
            iconBean.getResource_id();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startGame(GamesEntity.GamesDataEntity gamesDataEntity, String str) {
        if ("Y".equals(gamesDataEntity.getQqMark()) || "Y".equals(gamesDataEntity.getQq_mark())) {
            MiniGameUtils.showToast();
        } else {
            IntentManager.generateIntentAndGo(this.activityContext, gamesDataEntity.getUrl(), gamesDataEntity.getTitle(), true, "get");
        }
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p24", gamesDataEntity.getGroupCode());
        hashMap.put("p25", gamesDataEntity.getGameType());
        hashMap.put("p26", gamesDataEntity.getId() == null ? gamesDataEntity.getGame_id() : gamesDataEntity.getId());
        hashMap.put("p27", !gamesDataEntity.getName().isEmpty() ? gamesDataEntity.getName() : gamesDataEntity.getGame_name());
        hashMap.put("p28", gamesDataEntity.getCompany());
        hashMap.put("p31", str);
        hashMap.put("p32", "Android");
        hashMap.put("p33", gamesDataEntity.getTwoGameType());
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        hashMap.put("p35", ("Y".equals(gamesDataEntity.getQqMark()) || "Y".equals(gamesDataEntity.getQq_mark())) ? "1" : "2");
        hashMap.put("p23", TextUtils.isEmpty(gamesDataEntity.getBoutiqueFlag()) ? "" : gamesDataEntity.getBoutiqueFlag());
        hashMap.put("p16", (gamesDataEntity.getResourceId() == null || gamesDataEntity.getResourceId().isEmpty()) ? gamesDataEntity.getResource_id() : gamesDataEntity.getResourceId());
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$aiaxyXm9UTEBnGJRKgprxdz-mrY
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("游戏日志结果-->", (String) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$1W2wzUPJwa8rHe5dYhRT9KXNhDk
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                GameCentersActivity.lambda$startGame$11((Throwable) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void posterLog(String str, String str2, String str3) {
        HashMap hashMap = new HashMap();
        hashMap.put("p2", "40526");
        hashMap.put("p3", "游戏专区App客户端");
        hashMap.put("p5", "2");
        hashMap.put("p25", str2);
        hashMap.put("p31", str3);
        hashMap.put("P26", str);
        hashMap.put("p32", "Android");
        hashMap.put("p34", this.activityContext.getString(2131886969).split("@")[1]);
        this.manager.commonLog(this.activityContext, hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.gamecenter.-$$Lambda$GameCentersActivity$C8UcH169HTZTBrBH3VczEIX6PCw
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UIUtils.logD("游戏日志结果-->", (String) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public class MyImageLoader extends ImageLoader {
        private MyImageLoader() {
        }

        @Override // com.youth.banner.loader.ImageLoaderInterface
        public void displayImage(Context context, Object obj, ImageView imageView) {
            GlideApp.with(context).load(obj).into(imageView);
        }

        @Override // com.youth.banner.loader.ImageLoader, com.youth.banner.loader.ImageLoaderInterface
        public ImageView createImageView(Context context) {
            return new RoundImageView(context);
        }
    }
}
