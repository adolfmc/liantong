package com.sinovatech.unicom.separatemodule.playdetails.channel;

import android.content.Intent;
import android.os.Bundle;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.support.p086v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.fort.andjni.JniLib;
import com.google.gson.Gson;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.separatemodule.advtise.AdFactory;
import com.sinovatech.unicom.separatemodule.advtise.bean.AdConfigEntity;
import com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.TouTiaoTwoAdapter;
import com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.TouTiaoTwoMyAdapter;
import com.sinovatech.unicom.separatemodule.playdetails.channel.entity.ChannelEntity;
import com.sinovatech.unicom.separatemodule.videocenter.OptionValveUtil;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.jetbrains.annotations.NotNull;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ChannelActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private AppCompatActivity activityContext;
    private FrameLayout adv_group_fl;
    private FrameLayout adv_layout;
    private List<ChannelEntity.DataDTO> data;
    private int frozenIndex;
    private Gson gson;
    boolean isEdit = false;
    private ImageView mChannelIvBack;
    private List<ChannelEntity.DataDTO> mDataOne;
    private List<ChannelEntity.DataDTO> mDataTwo;
    private TextView mMyChannelSubTitle;
    private TouTiaoTwoAdapter mTouTiaoTwoAdapter;
    private TouTiaoTwoMyAdapter mTouTiaoTwoMyAdapter;
    private TextView mTvEdit;
    private RecyclerView rvtoutioaoneadd;
    private RecyclerView rvtoutioaonemy;

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 102);
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

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onResume() {
        NBSAppInstrumentation.activityResumeBeginIns(getClass().getName());
        super.onResume();
        NBSAppInstrumentation.activityResumeEndIns();
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

    private void loadBannerAd() {
        UIUtils.logD("更多频道banner广告", "开始加载banner广告");
        try {
            if (OptionValveUtil.INSTENCE.isShowChannelBannerAdv()) {
                AdConfigEntity adConfigEntity = new AdConfigEntity();
                adConfigEntity.setAdType("PANGLE");
                adConfigEntity.setCodeId("947099957");
                adConfigEntity.setBannerWidth(334);
                adConfigEntity.setBannerHeight(97);
                adConfigEntity.setScale(0.93d);
                AdFactory.getAd(this.activityContext, adConfigEntity).loadBanner(new IAdInterface.IBannerAdCallBack() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.ChannelActivity.1
                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
                    public void onResult(int i, View view) {
                        if (view != null) {
                            UIUtils.logD("更多频道banner广告", "banner广告加载成功");
                            if (ChannelActivity.this.adv_layout.getChildCount() > 0) {
                                ChannelActivity.this.adv_layout.removeAllViews();
                            }
                            ChannelActivity.this.adv_group_fl.setVisibility(0);
                            ChannelActivity.this.adv_layout.addView(view);
                        }
                    }

                    @Override // com.sinovatech.unicom.separatemodule.advtise.service.IAdInterface.IBannerAdCallBack
                    public void onClose() {
                        ChannelActivity.this.adv_group_fl.setVisibility(4);
                    }
                });
            }
        } catch (Exception unused) {
            UIUtils.logD("更多频道banner广告", "banner广告有异常");
        }
    }

    private void initData() {
        HashMap hashMap = new HashMap();
        hashMap.put("version", this.activityContext.getString(2131886969));
        String channel = URLSet.getChannel();
        UIUtils.logD("频道管理activity", "initData: " + channel);
        try {
            App.getAsyncHttpClient().rxPost(channel, hashMap).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.ChannelActivity.2
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str) {
                    UIUtils.logD("频道管理", "onNext: " + str);
                    Gson gson = new Gson();
                    ChannelEntity channelEntity = (ChannelEntity) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) ChannelEntity.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) ChannelEntity.class));
                    if (channelEntity.getStatusCode().equals("0000")) {
                        ChannelActivity.this.data = channelEntity.getData();
                        for (ChannelEntity.DataDTO dataDTO : ChannelActivity.this.data) {
                            if (dataDTO.getWhetherShow().equals("Y")) {
                                ChannelActivity.this.mDataOne.add(dataDTO);
                                ChannelActivity.this.mTouTiaoTwoMyAdapter.notifyDataSetChanged();
                                UIUtils.logD("频道管理yes个数", "onNext: " + ChannelActivity.this.mDataOne.size());
                            } else if (dataDTO.getWhetherShow().equals("N")) {
                                ChannelActivity.this.mDataTwo.add(dataDTO);
                                UIUtils.logD("频道管理no个数", "onNext: " + ChannelActivity.this.mDataTwo.size());
                                ChannelActivity.this.mTouTiaoTwoAdapter.notifyDataSetChanged();
                            }
                        }
                    }
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    th.printStackTrace();
                    UIUtils.logD("频道管理", "onError: " + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initView() {
        this.rvtoutioaoneadd = (RecyclerView) findViewById(2131298426);
        this.mChannelIvBack = (ImageView) findViewById(2131296607);
        this.rvtoutioaonemy = (RecyclerView) findViewById(2131298427);
        this.mTvEdit = (TextView) findViewById(2131299116);
        this.mMyChannelSubTitle = (TextView) findViewById(2131299117);
        this.adv_layout = (FrameLayout) findViewById(2131296327);
        this.adv_group_fl = (FrameLayout) findViewById(2131296328);
        try {
            this.mTvEdit.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.-$$Lambda$ChannelActivity$NUMDBxXYL79JyNr33Xnn9XUd2H4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ChannelActivity.lambda$initView$0(ChannelActivity.this, view);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.mChannelIvBack.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.-$$Lambda$ChannelActivity$FcCjiLwc160ysJO2fP7mTdduto4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ChannelActivity.lambda$initView$1(ChannelActivity.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initView$0(ChannelActivity channelActivity, View view) {
        if (channelActivity.isEdit) {
            channelActivity.mTvEdit.setText("编辑");
            channelActivity.mMyChannelSubTitle.setText("点击进入频道");
            channelActivity.mTouTiaoTwoMyAdapter.showDeleteIcon(false);
        } else {
            channelActivity.mTvEdit.setText("完成");
            channelActivity.mMyChannelSubTitle.setText("拖拽可以排序");
            channelActivity.mTouTiaoTwoMyAdapter.showDeleteIcon(true);
        }
        channelActivity.isEdit = !channelActivity.isEdit;
    }

    public static /* synthetic */ void lambda$initView$1(ChannelActivity channelActivity, View view) {
        try {
            ArrayList arrayList = new ArrayList();
            for (ChannelEntity.DataDTO dataDTO : channelActivity.mDataOne) {
                if (!TextUtils.isEmpty(dataDTO.getSubCode())) {
                    arrayList.add(dataDTO);
                }
            }
            for (ChannelEntity.DataDTO dataDTO2 : channelActivity.mDataTwo) {
                arrayList.add(dataDTO2);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("navStr", arrayList);
            Gson gson = new Gson();
            channelActivity.saveChannel(!(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onBackPressed() {
        try {
            ArrayList arrayList = new ArrayList();
            for (ChannelEntity.DataDTO dataDTO : this.mDataOne) {
                if (!TextUtils.isEmpty(dataDTO.getSubCode())) {
                    arrayList.add(dataDTO);
                }
            }
            for (ChannelEntity.DataDTO dataDTO2 : this.mDataTwo) {
                arrayList.add(dataDTO2);
            }
            HashMap hashMap = new HashMap();
            hashMap.put("navStr", arrayList);
            Gson gson = this.gson;
            saveChannel(!(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void saveChannel(String str) {
        try {
            App.getAsyncHttpClient().rxPost(URLSet.SaveChannel(), str).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.ChannelActivity.3
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        jSONObject.getString("message");
                        TextUtils.equals("0000", jSONObject.getString("statusCode"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    UIUtils.logD("频道管理保存", "onNext: " + str2);
                    Intent intent = new Intent();
                    Gson gson = ChannelActivity.this.gson;
                    List list = ChannelActivity.this.mDataOne;
                    intent.putExtra("channelList", !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list));
                    ChannelActivity.this.setResult(2112, intent);
                    ChannelActivity.this.finish();
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    Intent intent = new Intent();
                    Gson gson = ChannelActivity.this.gson;
                    List list = ChannelActivity.this.mDataOne;
                    intent.putExtra("channelList", !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list));
                    ChannelActivity.this.setResult(2112, intent);
                    ChannelActivity.this.finish();
                    UIUtils.logD("频道管理保存", "onError: " + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void saveChannel(String str, final Intent intent) {
        try {
            App.getAsyncHttpClient().rxPost(URLSet.SaveChannel(), str).subscribeOn(Schedulers.m1934io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Observer<String>() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.ChannelActivity.4
                @Override // io.reactivex.Observer
                public void onComplete() {
                }

                @Override // io.reactivex.Observer
                public void onSubscribe(@NotNull Disposable disposable) {
                }

                @Override // io.reactivex.Observer
                public void onNext(@NotNull String str2) {
                    try {
                        JSONObject jSONObject = new JSONObject(str2);
                        jSONObject.getString("message");
                        TextUtils.equals("0000", jSONObject.getString("statusCode"));
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    UIUtils.logD("频道管理保存", "onNext: " + str2);
                    Intent intent2 = intent;
                    Gson gson = ChannelActivity.this.gson;
                    List list = ChannelActivity.this.mDataOne;
                    intent2.putExtra("channelList", !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list));
                    ChannelActivity.this.setResult(2112, intent);
                    ChannelActivity.this.finish();
                }

                @Override // io.reactivex.Observer
                public void onError(@NotNull Throwable th) {
                    Intent intent2 = intent;
                    Gson gson = ChannelActivity.this.gson;
                    List list = ChannelActivity.this.mDataOne;
                    intent2.putExtra("channelList", !(gson instanceof Gson) ? gson.toJson(list) : NBSGsonInstrumentation.toJson(gson, list));
                    ChannelActivity.this.setResult(2112, intent);
                    ChannelActivity.this.finish();
                    UIUtils.logD("频道管理保存", "onError: " + th.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initTwoList() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        this.mDataTwo = new ArrayList();
        this.rvtoutioaoneadd.setLayoutManager(gridLayoutManager);
        this.mTouTiaoTwoAdapter = new TouTiaoTwoAdapter(2131493242, this.mDataTwo);
        this.rvtoutioaoneadd.setAdapter(this.mTouTiaoTwoAdapter);
        try {
            this.mTouTiaoTwoAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.-$$Lambda$ChannelActivity$NQnCSBBiooorGZ87OPgh_UCvR0I
                @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter.OnItemClickListener
                public final void onItemClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                    ChannelActivity.lambda$initTwoList$2(ChannelActivity.this, baseQuickAdapter, view, i);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static /* synthetic */ void lambda$initTwoList$2(ChannelActivity channelActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        if (i <= -1 || i >= channelActivity.mDataTwo.size()) {
            return;
        }
        ChannelEntity.DataDTO remove = channelActivity.mDataTwo.remove(i);
        channelActivity.mTouTiaoTwoAdapter.notifyItemRemoved(i);
        remove.setWhetherShow("Y");
        channelActivity.mDataOne.add(remove);
        channelActivity.mTouTiaoTwoMyAdapter.notifyItemInserted(channelActivity.mDataOne.size() - 1);
    }

    private void initOneList() {
        try {
            GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
            this.mDataOne = new ArrayList();
            if (OptionValveUtil.INSTENCE.isShowAttentionTab()) {
                ChannelEntity.DataDTO dataDTO = new ChannelEntity.DataDTO();
                dataDTO.setPageName("关注");
                dataDTO.setUnMove(true);
                this.mDataOne.add(dataDTO);
            }
            if (OptionValveUtil.INSTENCE.isShowRecommendTab()) {
                ChannelEntity.DataDTO dataDTO2 = new ChannelEntity.DataDTO();
                dataDTO2.setPageName("热门");
                dataDTO2.setUnMove(true);
                this.mDataOne.add(dataDTO2);
            }
            if (OptionValveUtil.INSTENCE.isShowRingtoneTab()) {
                ChannelEntity.DataDTO dataDTO3 = new ChannelEntity.DataDTO();
                dataDTO3.setPageName("视频彩铃");
                dataDTO3.setUnMove(true);
                this.mDataOne.add(dataDTO3);
            }
            if (OptionValveUtil.INSTENCE.isShowZhiBoTab()) {
                ChannelEntity.DataDTO dataDTO4 = new ChannelEntity.DataDTO();
                dataDTO4.setPageName("直播");
                dataDTO4.setUnMove(true);
                this.mDataOne.add(dataDTO4);
            }
            this.frozenIndex = this.mDataOne.size() - 1;
            this.rvtoutioaonemy.setLayoutManager(gridLayoutManager);
            this.mTouTiaoTwoMyAdapter = new TouTiaoTwoMyAdapter(this, this.mDataOne);
            this.mTouTiaoTwoMyAdapter.setmAdapterCallBack(new TouTiaoTwoMyAdapter.AdapterCallBack() { // from class: com.sinovatech.unicom.separatemodule.playdetails.channel.ChannelActivity.5
                @Override // com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.TouTiaoTwoMyAdapter.AdapterCallBack
                public void onItemClickListener(TouTiaoTwoMyAdapter.ViewHolder viewHolder, int i) {
                    if (i <= -1 || i >= ChannelActivity.this.mDataOne.size()) {
                        return;
                    }
                    if (ChannelActivity.this.isEdit) {
                        if (i > ChannelActivity.this.frozenIndex) {
                            ChannelEntity.DataDTO dataDTO5 = (ChannelEntity.DataDTO) ChannelActivity.this.mDataOne.remove(i);
                            dataDTO5.setWhetherShow("N");
                            ChannelActivity.this.mDataTwo.add(0, dataDTO5);
                            ChannelActivity.this.mTouTiaoTwoAdapter.notifyItemInserted(0);
                            ChannelActivity.this.mTouTiaoTwoMyAdapter.notifyItemRemoved(i);
                            return;
                        }
                        return;
                    }
                    Intent intent = new Intent();
                    intent.putExtra("channelName", ((ChannelEntity.DataDTO) ChannelActivity.this.mDataOne.get(i)).getPageName());
                    ArrayList arrayList = new ArrayList();
                    for (ChannelEntity.DataDTO dataDTO6 : ChannelActivity.this.mDataOne) {
                        if (!TextUtils.isEmpty(dataDTO6.getSubCode())) {
                            arrayList.add(dataDTO6);
                        }
                    }
                    arrayList.addAll(ChannelActivity.this.mDataTwo);
                    HashMap hashMap = new HashMap();
                    hashMap.put("navStr", arrayList);
                    Gson gson = new Gson();
                    ChannelActivity.this.saveChannel(!(gson instanceof Gson) ? gson.toJson(hashMap) : NBSGsonInstrumentation.toJson(gson, hashMap), intent);
                }

                @Override // com.sinovatech.unicom.separatemodule.playdetails.channel.adapter.TouTiaoTwoMyAdapter.AdapterCallBack
                public boolean onItemLongClickListener(TouTiaoTwoMyAdapter.ViewHolder viewHolder, int i) {
                    if (!ChannelActivity.this.isEdit && i > ChannelActivity.this.frozenIndex) {
                        ChannelActivity channelActivity = ChannelActivity.this;
                        channelActivity.isEdit = true;
                        channelActivity.mTvEdit.setText("完成");
                        ChannelActivity.this.mMyChannelSubTitle.setText("拖拽可以排序");
                        ChannelActivity.this.mTouTiaoTwoMyAdapter.showDeleteIcon(true);
                        return true;
                    }
                    return false;
                }
            });
            new ItemTouchHelper(new MyItemTouchHandler(this.mTouTiaoTwoMyAdapter, this.frozenIndex + 1)).attachToRecyclerView(this.rvtoutioaonemy);
            this.rvtoutioaonemy.setAdapter(this.mTouTiaoTwoMyAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
