package com.sinovatech.unicom.separatemodule.playdetails.xiangqing;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.LinearLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.audience.view.easydanmaku.danmaku.Danmaku;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.playdetails.user.VideoUserActivity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.adapter.VideoDetailsAdapter;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.DianZanEntity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.GuanZhuEntity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsData;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity;
import com.sinovatech.unicom.separatemodule.playdetails.xiangqing.utils.NumUtils;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import com.sinovatech.unicom.separatemodule.videocenter.VideoCenterActivity;
import com.sinovatech.unicom.separatemodule.videocenter.entity.VideoUserEntity;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment;
import com.sinovatech.unicom.separatemodule.videocenter.utils.HiBoardLog;
import com.sinovatech.unicom.separatemodule.videocenter.utils.LiuZPTLog;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoDetailsFragment extends BaseVideoFragment implements View.OnClickListener {
    private AppCompatActivity activityContext;
    private String category;
    private List<VideoDetailsData.DataDTO> data;
    private VideoDetailsEntity.DataDTO dataDTO;
    private String digg_count;
    private String digg_status;
    private boolean follow;
    private String fromGid;
    private String group_id_str;
    private Gson gson;
    private HiBoardLog hiBoardLog;
    private LinearLayoutManager linearLayoutManager;
    private VideoDetailsEntity.DataDTO list;
    private String logJson;
    private LiuZPTLog logUpdater;
    private ImageView mIvFollow;
    private CircularImage mIvImage;
    private ImageView mIvShare;
    private ImageView mIvZan;
    private LinearLayout mLlGuanzhu;
    private RecyclerView mRvVideoDetails;
    private TextView mTvBofang;
    private TextView mTvFollow;
    private TextView mTvFs;
    private TextView mTvName;
    private TextView mTvShichang;
    private TextView mTvTitle;
    private TextView mTvZanNum;
    private LinearLayout mVideoDetailsll;
    private Map<String, String> map;
    private String title;
    private String user_id;
    private VideoDetailsEntity.DataDTO.UserInfoDTO user_info;
    private VideoDetailsAdapter videoDetailsAdapter;
    private String videoUrl;
    private String video_duration;
    private String video_watch_count;
    private FrameLayout xinxiliu_ad_fr;

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    public int setContentView() {
        return 2131493130;
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    @SuppressLint({"SetTextI18n"})
    public void initView() {
        this.activityContext = (AppCompatActivity) getActivity();
        this.hiBoardLog = new HiBoardLog(this.activityContext);
        this.mVideoDetailsll = (LinearLayout) findViewById(2131299508);
        this.mIvImage = (CircularImage) findViewById(2131297401);
        this.mIvImage.setOnClickListener(this);
        this.mTvName = (TextView) findViewById(2131299021);
        this.mTvFs = (TextView) findViewById(2131298946);
        this.mLlGuanzhu = (LinearLayout) findViewById(2131297721);
        this.mIvFollow = (ImageView) findViewById(2131297386);
        this.mTvFollow = (TextView) findViewById(2131298944);
        this.mLlGuanzhu.setOnClickListener(this);
        this.mTvTitle = (TextView) findViewById(2131299108);
        this.mTvBofang = (TextView) findViewById(2131298886);
        this.mTvShichang = (TextView) findViewById(2131299074);
        this.mIvZan = (ImageView) findViewById(2131297528);
        this.mIvZan.setOnClickListener(this);
        this.mTvZanNum = (TextView) findViewById(2131299157);
        this.mIvShare = (ImageView) findViewById(2131299512);
        this.mIvShare.setOnClickListener(this);
        this.xinxiliu_ad_fr = (FrameLayout) findViewById(2131299850);
        this.mRvVideoDetails = (RecyclerView) findViewById(2131298428);
        this.linearLayoutManager = new LinearLayoutManager(getActivity());
        this.linearLayoutManager.setOrientation(1);
        this.mRvVideoDetails.setLayoutManager(this.linearLayoutManager);
        this.data = new ArrayList();
        this.videoDetailsAdapter = new VideoDetailsAdapter(getActivity(), this.data);
        this.mRvVideoDetails.setAdapter(this.videoDetailsAdapter);
        this.videoDetailsAdapter.setmOnItemClickListener(new VideoDetailsAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.1
            @Override // com.sinovatech.unicom.separatemodule.playdetails.xiangqing.adapter.VideoDetailsAdapter.OnItemClickListener
            public void onItemClick(int i) {
                try {
                    Intent intent = new Intent(VideoDetailsFragment.this.getActivity(), VideoDetailsActivity.class);
                    intent.putExtra("group_id_str", ((VideoDetailsData.DataDTO) VideoDetailsFragment.this.data.get(i)).getGroup_id_str());
                    intent.putExtra("fromGid", VideoDetailsFragment.this.group_id_str);
                    intent.putExtra("category", VideoDetailsFragment.this.category);
                    intent.putExtra("imageUrl", ((VideoDetailsData.DataDTO) VideoDetailsFragment.this.data.get(i)).getMiddle_image().get(0).getUrl());
                    VideoDetailsFragment.this.startActivity(intent);
                    VideoDetailsFragment.this.getActivity().finish();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        this.logUpdater = new LiuZPTLog((VideoDetailsActivity) getActivity());
        this.mRvVideoDetails.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.2
            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int i) {
            }

            @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull @NotNull RecyclerView recyclerView, int i, int i2) {
                VideoDetailsFragment.this.uploadData(VideoDetailsFragment.this.linearLayoutManager.findLastVisibleItemPosition());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void uploadData(int i) {
        try {
            if (i >= this.data.size()) {
                i = this.data.size() - 1;
            }
            if (i < 0) {
                i = 0;
            }
            for (int i2 = 0; i2 <= i; i2++) {
                if (this.data.size() > 0) {
                    VideoDetailsData.DataDTO dataDTO = this.data.get(i2);
                    if (!dataDTO.isUpload()) {
                        this.hiBoardLog.hiBoardLogShow(dataDTO.getGroup_id_str(), this.fromGid, "related");
                        dataDTO.setUpload(true);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.videocenter.fragment.BaseVideoFragment
    public void lazyLoad() {
        if (TextUtils.isEmpty(this.user_id)) {
            return;
        }
        UIUtils.logD("VideoDetailsFragment", "lazyLoad");
        isFollowing(this.user_id);
    }

    private void getZan(final String str) {
        try {
            String dianZan = URLSet.getDianZan(str, this.group_id_str);
            HashMap hashMap = new HashMap();
            hashMap.put("version", this.activityContext.getString(2131886969));
            App.getAsyncHttpClient().rxPost(dianZan, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.3
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    Gson gson = new Gson();
                    DianZanEntity dianZanEntity = (DianZanEntity) (!(gson instanceof Gson) ? gson.fromJson(str2, (Class<Object>) DianZanEntity.class) : NBSGsonInstrumentation.fromJson(gson, str2, (Class<Object>) DianZanEntity.class));
                    if (dianZanEntity != null && dianZanEntity.getStatusCode().equals("0000")) {
                        String digg_count = dianZanEntity.getData().getDigg_count();
                        VideoDetailsFragment.this.mTvZanNum.setText(NumUtils.getDianZanNum(digg_count));
                        VideoCenterActivity.dingCountMap.put(VideoDetailsFragment.this.group_id_str, digg_count);
                        Map<String, String> map = VideoCenterActivity.dingCountMap;
                        String str3 = VideoDetailsFragment.this.group_id_str + "ding";
                        if (!str.equals("digg")) {
                            digg_count = "";
                        }
                        map.put(str3, digg_count);
                    }
                    UIUtils.logD("视频详情点赞", "onNext: " + str2);
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.logD("视频详情点赞", "onError: " + th.getMessage());
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void getTuiJianList() {
        String videoTuiJianList = URLSet.getVideoTuiJianList("5", this.group_id_str);
        UIUtils.logD("视频详情Fragment", "推荐列表url: " + videoTuiJianList);
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        try {
            App.getAsyncHttpClient().rxPost(videoTuiJianList, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.4
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str) {
                    UIUtils.logD("视频详情fragment", "onNext: " + str);
                    Gson gson = new Gson();
                    VideoDetailsData videoDetailsData = (VideoDetailsData) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) VideoDetailsData.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) VideoDetailsData.class));
                    if (videoDetailsData.getStatusCode().equals("0000")) {
                        VideoDetailsFragment.this.data.addAll(videoDetailsData.getData());
                        VideoDetailsFragment.this.videoDetailsAdapter.setData(VideoDetailsFragment.this.data);
                        VideoDetailsFragment.this.loadXinXiLiuAd();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    th.printStackTrace();
                    String message = th.getMessage();
                    UIUtils.logD("视频详情fragment", "onError: " + message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"NonConstantResourceId"})
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        try {
            int id = view.getId();
            if (id == 2131297401) {
                Intent intent = new Intent(getActivity(), VideoUserActivity.class);
                intent.putExtra("user_id", this.user_id);
                intent.putExtra("from", "videoDetail");
                startActivity(intent);
                if (this.map != null) {
                    String str = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                    this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "进入个人中心", this.map.get("storey"), this.map.get("content_id"), str, str, this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                }
            } else if (id != 2131297528) {
                if (id != 2131297721) {
                    if (id == 2131299512) {
                        doCapture(this.list);
                    }
                } else if (this.follow) {
                    offGuanzhu(this.user_info.getUser_id());
                    this.mLlGuanzhu.setBackgroundResource(2131232573);
                    this.mIvFollow.setVisibility(0);
                    this.mTvFollow.setText("关注");
                    this.mTvFollow.setTextColor(Color.parseColor(Danmaku.COLOR_WHITE));
                    this.follow = false;
                    if (this.map != null) {
                        String str2 = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                        this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击取消关注", this.map.get("storey"), this.map.get("content_id"), str2, str2, this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                    }
                } else {
                    onGuanzhu(this.user_info.getUser_id());
                    this.mLlGuanzhu.setBackgroundResource(2131232574);
                    this.mTvFollow.setText("已关注");
                    this.mTvFollow.setTextColor(Color.parseColor("#ff666666"));
                    this.mIvFollow.setVisibility(8);
                    this.follow = true;
                    if (this.map != null) {
                        String str3 = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                        this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "点击关注接口", this.map.get("storey"), this.map.get("content_id"), str3, str3, this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                    }
                }
            } else if (this.digg_status.equals("true")) {
                getZan("undigg");
                this.mIvZan.setImageResource(2131232599);
                this.digg_status = "false";
                if (this.map != null) {
                    String str4 = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                    this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "取消点赞", this.map.get("storey"), this.map.get("content_id"), str4, str4, this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                }
            } else {
                getZan("digg");
                this.mIvZan.setImageResource(2131231696);
                this.digg_status = "true";
                if (this.map != null) {
                    String str5 = VideoDetailsActivity.bdVideo.getCurrentPosition() + "";
                    this.logUpdater.setLogByCommon(this.videoUrl, "", "视频详情", "2", "点击按钮", "19", "视频详情", "点赞", this.map.get("storey"), this.map.get("content_id"), str5, str5, this.video_duration, this.map.get("tab_id"), this.map.get("tab_name"), "");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Unreachable block: B:8:0x008e
        	at jadx.core.dex.visitors.blocks.BlockProcessor.checkForUnreachableBlocks(BlockProcessor.java:81)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:47)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    private void doCapture(com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity.DataDTO r9) {
        /*
            r8 = this;
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r9.getTitle()
            java.lang.StringBuilder r2 = new java.lang.StringBuilder
            r2.<init>()
            java.lang.String r3 = "来自中国联通APP-"
            r2.append(r3)
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$UserInfoDTO r3 = r9.getUser_info()
            java.lang.String r3 = r3.getName()
            r2.append(r3)
            java.lang.String r2 = r2.toString()
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$VideoDTO r3 = r9.getVideo()
            java.util.List r3 = r3.getVideo_list()
            r4 = 0
            java.lang.Object r3 = r3.get(r4)
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$VideoDTO$VideoListDTO r3 = (com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity.DataDTO.VideoDTO.VideoListDTO) r3
            java.lang.String r3 = r3.getMain_url()
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO$VideoDTO r4 = r9.getVideo()
            java.lang.String r4 = r4.getPoster_url()
            java.lang.String r5 = "shortmessage,wechat,wechatmoments,qq,qzone,sinaweibo"
            java.lang.String r6 = "shareType"
            java.lang.String r7 = "url"
            r0.put(r6, r7)     // Catch: java.lang.Exception -> L8c
            java.lang.String r6 = "shareTitle"
            r0.put(r6, r1)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "shareContent"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "shareURL"
            r0.put(r1, r3)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "shareIconURL"
            r0.put(r1, r4)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramShare"
            java.lang.String r2 = "1"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramType"
            java.lang.String r2 = "0"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramUserName"
            java.lang.String r2 = "gh_2bab3e2deed1"
            r0.put(r1, r2)     // Catch: java.lang.Exception -> L8c
            java.lang.String r1 = "miniProgramPath"
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Exception -> L8c
            r2.<init>()     // Catch: java.lang.Exception -> L8c
            java.lang.String r3 = "pages/videoshare/videoshare?groupId= "
            r2.append(r3)     // Catch: java.lang.Exception -> L8c
            long r3 = r9.getGroup_id()     // Catch: java.lang.Exception -> L8c
            r2.append(r3)     // Catch: java.lang.Exception -> L8c
            java.lang.String r9 = r2.toString()     // Catch: java.lang.Exception -> L8c
            r0.put(r1, r9)     // Catch: java.lang.Exception -> L8c
            goto L93
        L8c:
            r9 = move-exception
            goto L90
        L8e:
            r9 = move-exception
            r5 = 0
        L90:
            r9.printStackTrace()
        L93:
            android.support.v4.app.FragmentActivity r9 = r8.getActivity()
            boolean r1 = r0 instanceof org.json.JSONObject
            if (r1 != 0) goto La0
            java.lang.String r0 = r0.toString()
            goto La6
        La0:
            org.json.JSONObject r0 = (org.json.JSONObject) r0
            java.lang.String r0 = com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation.toString(r0)
        La6:
            com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment$5 r1 = new com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment$5
            r1.<init>()
            com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShowShareDialog(r9, r5, r0, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.doCapture(com.sinovatech.unicom.separatemodule.playdetails.xiangqing.entity.VideoDetailsEntity$DataDTO):void");
    }

    private void onGuanzhu(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        String GuanzhuON = URLSet.GuanzhuON(str);
        UIUtils.logD("视频详情fragment关注", "onGuanzhu: " + GuanzhuON);
        try {
            App.getAsyncHttpClient().rxPost(GuanzhuON, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.6
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    Gson gson = new Gson();
                    GuanZhuEntity guanZhuEntity = (GuanZhuEntity) (!(gson instanceof Gson) ? gson.fromJson(str2, (Class<Object>) GuanZhuEntity.class) : NBSGsonInstrumentation.fromJson(gson, str2, (Class<Object>) GuanZhuEntity.class));
                    if (guanZhuEntity.getStatusCode().equals("0000")) {
                        String description = guanZhuEntity.getData().getDescription();
                        UIUtils.logD("视频详情关注成功", "onNext: " + description);
                        UIUtils.toast(guanZhuEntity.getData().getDescription());
                        GuanZhuEntity.DataDTO.UserDTO user = guanZhuEntity.getData().getUser();
                        VideoUserEntity videoUserEntity = new VideoUserEntity();
                        videoUserEntity.setSchema(user.getHome_page());
                        videoUserEntity.setUserId(user.getUser_id() + "");
                        videoUserEntity.setAvatarUrl(user.getAvatar_url());
                        videoUserEntity.setName(user.getName());
                        VideoCenterActivity.attentionUser(videoUserEntity, true);
                        return;
                    }
                    UIUtils.toast(guanZhuEntity.getData().getDescription());
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.toast("数据访问错误");
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void offGuanzhu(final String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        String GuanzhuOFF = URLSet.GuanzhuOFF(str);
        UIUtils.logD("视频详情fragment取消关注", "offGuanzhu: " + GuanzhuOFF);
        try {
            App.getAsyncHttpClient().rxPost(GuanzhuOFF, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.7
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    UIUtils.logD("视频详情取消关注", "onNext: " + str2);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        String string = jSONObject.getString("message");
                        if (jSONObject.getString("statusCode").equals("0000")) {
                            UIUtils.toast("取消关注");
                            VideoUserEntity videoUserEntity = new VideoUserEntity();
                            videoUserEntity.setUserId(str);
                            VideoCenterActivity.attentionUser(videoUserEntity, false);
                        } else {
                            UIUtils.toast(string);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.toast(th.getMessage());
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void isFollowing(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        try {
            App.getAsyncHttpClient().rxPost(URLSet.isFollowing(str), hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.8
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    UIUtils.logD("zs作者主页是否关注" + str2);
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        if (TextUtils.equals(jSONObject.getString("statusCode"), "0000")) {
                            VideoDetailsFragment.this.isFollows(jSONObject.optJSONObject("data").optString("following"));
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    UIUtils.toast(th.getMessage());
                    th.printStackTrace();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressLint({"SetTextI18n"})
    public void setData(VideoDetailsEntity.DataDTO dataDTO, String str, String str2, String str3, String str4) {
        if (dataDTO == null) {
            return;
        }
        try {
            this.list = dataDTO;
            this.group_id_str = dataDTO.getGroup_id_str();
            this.category = str3;
            this.fromGid = str4;
            UIUtils.logD("视频详情fragment", "setData: " + this.group_id_str);
            this.user_info = dataDTO.getUser_info();
            this.user_id = this.user_info.getUser_id();
            this.title = dataDTO.getTitle();
            this.video_watch_count = dataDTO.getWatch_count();
            this.video_duration = dataDTO.getDuration();
            this.digg_count = dataDTO.getDigg_count();
            this.digg_status = dataDTO.getDigg_status();
            this.follow = this.user_info.isFollow();
            this.videoUrl = str;
            this.logJson = str2;
            getTuiJianList();
            isFollowing(this.user_id);
            Glide.with((FragmentActivity) this.activityContext).load(this.user_info.getAvatar_url()).into(this.mIvImage);
            this.mTvName.setText(this.user_info.getName());
            TextView textView = this.mTvFs;
            textView.setText(NumUtils.getFsNum(this.user_info.getFollower_count()) + "粉丝");
            this.mTvTitle.setText(this.title);
            TextView textView2 = this.mTvBofang;
            textView2.setText(NumUtils.getBoFangNum(this.video_watch_count) + "次播放量");
            TextView textView3 = this.mTvShichang;
            textView3.setText(NumUtils.stringForTime(Integer.parseInt(this.video_duration) * 1000) + "时长");
            this.mTvZanNum.setText(NumUtils.getDianZanNum(this.digg_count));
            if (TextUtils.equals(this.digg_status, "true")) {
                this.mIvZan.setImageResource(2131231696);
            } else {
                this.mIvZan.setImageResource(2131232599);
            }
            this.gson = new Gson();
            Gson gson = this.gson;
            String str5 = this.logJson;
            Type type = new TypeToken<Map<String, String>>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.9
            }.getType();
            this.map = (Map) (!(gson instanceof Gson) ? gson.fromJson(str5, type) : NBSGsonInstrumentation.fromJson(gson, str5, type));
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mVideoDetailsll.setVisibility(0);
    }

    public void isFollows(String str) {
        try {
            if (TextUtils.equals(str, "true")) {
                this.mLlGuanzhu.setBackgroundResource(2131232574);
                this.mTvFollow.setText("已关注");
                this.mTvFollow.setTextColor(Color.parseColor("#ff666666"));
                this.mIvFollow.setVisibility(8);
            } else {
                this.mLlGuanzhu.setBackgroundResource(2131232349);
                this.mIvFollow.setVisibility(0);
                this.mTvFollow.setText("关注");
                this.mTvFollow.setTextColor(Color.parseColor(Danmaku.COLOR_WHITE));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadXinXiLiuAd() {
        MsLogUtil.m7979d("更多详情信息流广告", "开始加载");
        try {
            if (OptionValveUtil.INSTENCE.isShowHuoShanDetailsAdv()) {
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("947100126");
                adConfigEntity.setBannerWidth(341);
                AdFactory.getAd(this.activityContext, adConfigEntity).loadInteraction(new IAdInterface.IInteractionCallBack() { // from class: com.sinovatech.unicom.separatemodule.playdetails.xiangqing.VideoDetailsFragment.10
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onResult(int i, View view) {
                        if (view != null) {
                            VideoDetailsFragment.this.xinxiliu_ad_fr.setVisibility(0);
                            if (VideoDetailsFragment.this.xinxiliu_ad_fr.getChildCount() > 0) {
                                VideoDetailsFragment.this.xinxiliu_ad_fr.removeAllViews();
                            }
                            VideoDetailsFragment.this.xinxiliu_ad_fr.addView(view);
                        }
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IInteractionCallBack
                    public void onClose() {
                        VideoDetailsFragment.this.xinxiliu_ad_fr.setVisibility(8);
                    }
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("更多详情信息流广告", "异常" + e.getMessage());
        }
    }
}
