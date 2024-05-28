package com.sinovatech.unicom.separatemodule.videocenter;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.p083v4.app.FragmentActivity;
import android.support.p086v7.widget.GridLayoutManager;
import android.support.p086v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupWindow;
import com.bumptech.glide.load.MultiTransformation;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.fort.andjni.JniLib;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mob.tools.utils.ResHelper;
import com.networkbench.agent.impl.background.NBSApplicationStateMonitor;
import com.networkbench.agent.impl.instrumentation.NBSAppInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSTraceUnit;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.WebFragment;
import com.sinovatech.unicom.basic.p315ui.share.ShareManager;
import com.sinovatech.unicom.basic.p315ui.share.WebMenuManager;
import com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.common.DeviceHelper;
import com.sinovatech.unicom.common.RxLifecycleUtils;
import com.sinovatech.unicom.common.SystemServiceUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.BaseActivity;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.audience.AudienceMainActivity;
import com.sinovatech.unicom.separatemodule.audience.ManagerAudienceLoadData;
import com.sinovatech.unicom.separatemodule.audience.entity.BaseVideoEntity;
import com.sinovatech.unicom.separatemodule.audience.entity.VideoEntity;
import com.sinovatech.unicom.separatemodule.audience.function.RecommendListFunction;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter;
import com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseViewHolder;
import com.sinovatech.unicom.separatemodule.audience.smallvideo.SmallVideoActivity;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.videocenter.fragment.VideoRingFragment;
import com.uber.autodispose.ObservableSubscribeProxy;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import org.apache.commons.lang3.RandomStringUtils;
import org.jetbrains.annotations.NotNull;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class VideoTodayActivity extends BaseActivity {
    public NBSTraceUnit _nbs_trace;
    private VideoTodayActivity activityContext;
    private BaseQuickAdapter<VideoEntity, BaseViewHolder> adapter;
    private GridLayoutManager mLayoutManager;
    private ManagerAudienceLoadData managerAudienceLoadData;
    private ImageView moreImage;
    private PopupWindow popupWindow;
    private RecyclerView rlvContent;
    private int pageNum = 1;
    private Map<String, String> videoIds = new HashMap();

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$setLog$7(String str) throws Exception {
    }

    @Override // com.sinovatech.unicom.p318ui.BaseActivity, android.support.p086v7.app.AppCompatActivity, android.support.p083v4.app.FragmentActivity, android.support.p083v4.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        JniLib.m15918cV(this, bundle, 119);
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

    /* renamed from: com.sinovatech.unicom.separatemodule.videocenter.VideoTodayActivity$1 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C96061 extends BaseQuickAdapter<VideoEntity, BaseViewHolder> {
        C96061(int i) {
            super(i);
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.sinovatech.unicom.separatemodule.audience.quickrecycler.BaseQuickAdapter
        public void convert(BaseViewHolder baseViewHolder, VideoEntity videoEntity) {
            baseViewHolder.setText(2131299060, videoEntity.getName()).setText(2131299130, videoEntity.getNickname()).setText(2131299157, VideoTodayActivity.this.getShowString(videoEntity.getLikeNum())).setImageResource(2131297528, "0".equals(videoEntity.getLikeFlag()) ? 2131231613 : 2131231615);
            GlideApp.with((FragmentActivity) VideoTodayActivity.this.activityContext).load(videoEntity.getPicpath()).apply((BaseRequestOptions<?>) new RequestOptions().error(2131231128).transform(new MultiTransformation(new CenterCrop(), new RoundedCornersTransformation(UIUtils.dip2px(5.0f), 0, RoundedCornersTransformation.CornerType.ALL)))).into((ImageView) baseViewHolder.getView(2131297474));
            GlideApp.with((FragmentActivity) VideoTodayActivity.this.activityContext).load(videoEntity.getHeadimg()).error(2131231245).placeholder(2131231245).into((CircularImage) baseViewHolder.getView(2131297517));
        }
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.videocenter.VideoTodayActivity$2 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class C96072 extends RecyclerView.OnScrollListener {
        C96072() {
        }

        @Override // android.support.p086v7.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(@NonNull @NotNull RecyclerView recyclerView, int i) {
            if (i != 0) {
                return;
            }
            VideoTodayActivity.this.setLog("滑动", "21", "今日推荐列表纵向滑动", "", "", "", "1", "3", "今日推荐", "今日推荐", "", "", "", "", "", "");
        }
    }

    public static /* synthetic */ void lambda$onCreate$0(VideoTodayActivity videoTodayActivity, BaseQuickAdapter baseQuickAdapter, View view, int i) {
        String json = VideoRingFragment.toJson(videoTodayActivity.adapter.getData());
        Intent intent = new Intent(videoTodayActivity.activityContext, SmallVideoActivity.class);
        intent.putExtra(AudienceMainActivity.RING_FLAG, "4593");
        intent.putExtra(AudienceMainActivity.RING_TAB, "今日推荐");
        intent.putExtra("type", AudienceMainActivity.RING_FLAG);
        App.getSharePreferenceUtil().putString(SmallVideoActivity.SP_KEY, json);
        intent.putExtra("videoList", SmallVideoActivity.SP_KEY);
        intent.putExtra("videoIndex", i);
        intent.putExtra("videoPageNum", videoTodayActivity.pageNum);
        intent.putExtra("videoChannel", "今日推荐");
        videoTodayActivity.startActivityForResult(intent, 2333);
        videoTodayActivity.setLog("点击坑位", "26", "", "", "", "", "1", "2", "今日推荐", "今日推荐", "", videoTodayActivity.adapter.getItem(i).getId(), "", "", "", videoTodayActivity.adapter.getItem(i).getTjpara());
    }

    private void exposureEvent() {
        int findLastCompletelyVisibleItemPosition = this.mLayoutManager.findLastCompletelyVisibleItemPosition();
        for (int findFirstCompletelyVisibleItemPosition = this.mLayoutManager.findFirstCompletelyVisibleItemPosition(); findFirstCompletelyVisibleItemPosition < findLastCompletelyVisibleItemPosition + 1; findFirstCompletelyVisibleItemPosition++) {
            String id = this.adapter.getItem(findFirstCompletelyVisibleItemPosition).getId();
            if (!this.videoIds.containsKey(id)) {
                this.videoIds.put(id, "曝光");
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getShowString(String str) {
        BigDecimal bigDecimal = new BigDecimal(str);
        BigDecimal bigDecimal2 = new BigDecimal(10000);
        if (bigDecimal.compareTo(bigDecimal2) > -1) {
            String format = new DecimalFormat("#.00").format(bigDecimal.divide(bigDecimal2));
            return format + "w";
        }
        return str;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void getRecommend(final int i) {
        ((ObservableSubscribeProxy) App.getAsyncHttpClient(30, 10, 10).rxGet(URLSet.getBoardInfo(i + "", "4593"), null).subscribeOn(Schedulers.m1934io()).observeOn(Schedulers.computation()).map(new RecommendListFunction()).observeOn(AndroidSchedulers.mainThread()).m1940as(RxLifecycleUtils.bindLifecycle(this.activityContext))).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoTodayActivity$c6icH7r-UjfLcwvEQIox6faKkFs
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                VideoTodayActivity.lambda$getRecommend$5(VideoTodayActivity.this, i, (BaseVideoEntity) obj);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoTodayActivity$Wg48Cm_l2R1RBArIznfXxG2rjI4
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                ((Throwable) obj).printStackTrace();
            }
        });
    }

    public static /* synthetic */ void lambda$getRecommend$5(VideoTodayActivity videoTodayActivity, int i, BaseVideoEntity baseVideoEntity) throws Exception {
        if ("0000".equals(baseVideoEntity.getStatusCode())) {
            if (i == 1) {
                videoTodayActivity.adapter.setNewData((List) baseVideoEntity.getData());
            } else {
                videoTodayActivity.adapter.addData((Collection) baseVideoEntity.getData());
            }
            try {
                videoTodayActivity.pageNum = Integer.valueOf(baseVideoEntity.getNextPageNum()).intValue();
            } catch (NumberFormatException unused) {
                videoTodayActivity.pageNum = -1;
            }
            if (videoTodayActivity.pageNum > 0) {
                videoTodayActivity.adapter.loadMoreComplete();
            } else {
                videoTodayActivity.adapter.loadMoreEnd();
            }
        }
    }

    @Override // android.support.p083v4.app.FragmentActivity, android.app.Activity
    public void onActivityResult(int i, int i2, @Nullable @org.jetbrains.annotations.Nullable Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (intent.hasExtra(AudienceMainActivity.RING_LIST)) {
            Gson gson = new Gson();
            String stringExtra = intent.getStringExtra(AudienceMainActivity.RING_LIST);
            Type type = new TypeToken<List<VideoEntity>>() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoTodayActivity.3
            }.getType();
            Object fromJson = !(gson instanceof Gson) ? gson.fromJson(stringExtra, type) : NBSGsonInstrumentation.fromJson(gson, stringExtra, type);
            int intExtra = intent.getIntExtra(AudienceMainActivity.RING_INDEX, 0);
            this.adapter.setNewData((List) fromJson);
            this.rlvContent.smoothScrollToPosition(intExtra);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showMore() {
        try {
            List<WebMenuManager.WebMenuEntity> parseWebMenuJsonData = WebMenuManager.parseWebMenuJsonData("", "视频彩铃");
            WebMoreFunctionView webMoreFunctionView = new WebMoreFunctionView(this.activityContext, "", new WebMoreFunctionView.Listener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoTodayActivity.4
                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onShareCancle(String str) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onShareComplete(String str) {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void refresh() {
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onClick() {
                    VideoTodayActivity.this.popupWindow.dismiss();
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onBackHome() {
                    VideoTodayActivity.this.goMain();
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void tiaozhuan(String str) {
                    Intent intent = new Intent(VideoTodayActivity.this.activityContext, WebDetailActivity.class);
                    WebParamsEntity webParamsEntity = new WebParamsEntity();
                    webParamsEntity.setUrl(str);
                    webParamsEntity.setNeedTitle(true);
                    webParamsEntity.setRequestType("get");
                    intent.putExtra(WebFragment.webParams, webParamsEntity);
                    VideoTodayActivity.this.startActivity(intent);
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onShareError(String str, String str2) {
                    if (!TextUtils.isEmpty(str2)) {
                        int stringRes = ResHelper.getStringRes(VideoTodayActivity.this.activityContext, str2);
                        if (stringRes > 0) {
                            UIUtils.toastLong(VideoTodayActivity.this.activityContext.getResources().getString(stringRes));
                            return;
                        } else {
                            UIUtils.toastLong(str2);
                            return;
                        }
                    }
                    UIUtils.toastLong("分享失败");
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void onMenu() {
                    try {
                        if (VideoTodayActivity.this.popupWindow == null || !VideoTodayActivity.this.popupWindow.isShowing()) {
                            return;
                        }
                        VideoTodayActivity.this.popupWindow.dismiss();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

                @Override // com.sinovatech.unicom.basic.p315ui.share.WebMoreFunctionView.Listener
                public void doCaptureWebview(String str, String str2, String str3, String str4, String str5, String str6, String str7, WebMoreFunctionView.CaptureCallback captureCallback) {
                    if (str.equals("longScreenshot")) {
                        VideoTodayActivity.this.doCapture();
                    }
                }
            });
            this.popupWindow = new PopupWindow(this.activityContext);
            this.popupWindow.setAnimationStyle(2131952229);
            this.popupWindow.setFocusable(true);
            this.popupWindow.setOutsideTouchable(true);
            this.popupWindow.setBackgroundDrawable(new ColorDrawable(0));
            this.popupWindow.setWidth(UIUtils.dip2px(this.activityContext, 160.0f));
            if (parseWebMenuJsonData.size() > 2) {
                this.popupWindow.setHeight(((UIUtils.dip2px(this.activityContext, 50.0f) + 1) * parseWebMenuJsonData.size()) + UIUtils.dip2px(this.activityContext, 10.0f));
            } else {
                this.popupWindow.setHeight((UIUtils.dip2px(this.activityContext, 50.0f) + 1) * parseWebMenuJsonData.size());
            }
            this.popupWindow.setContentView(webMoreFunctionView.getContentView(parseWebMenuJsonData));
            this.popupWindow.showAsDropDown(this.moreImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void doCapture() {
        ShareManager.ShowShareDialog(this.activityContext, WebMenuManager.getAllShareString(), "{\n    \"shareContent\":\"手机新用户专享福利，话费+流量双重红包!\",\n    \"shareIconURL\":\"https://m1.img.10010.com/resources/7188192A31B5AE06E41B64DA6D65A9B0/20210325/png/20210325101313.png\",\n    \"provider\":\"10010\",\n    \"shareTitle\":\"视频彩铃\",\n    \"shareType\":\"localimg\",\n    \"shareURL\":\"https://m.client.10010.com/mobileService/openPlatform/openPlatLine.htm?to_url=https://m.client.10010.com/newuser_century/static/textdl/userLogin\"\n}", new ShareManager.ShareListener() { // from class: com.sinovatech.unicom.separatemodule.videocenter.VideoTodayActivity.5
            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onComplete(String str) {
                UIUtils.toast("成功");
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onCancel(String str) {
                UIUtils.toast("取消");
            }

            @Override // com.sinovatech.unicom.basic.p315ui.share.ShareManager.ShareListener
            public void onError(String str, String str2) {
                UIUtils.toast("失败");
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void goMain() {
        IntentManager.intentFilter(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setLog(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
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
            hashMap.put("channel_id", "3000011091");
            hashMap.put("content_position", "1");
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
            hashMap.put("tab_id", "spcl");
            hashMap.put("tab_name", "视频彩铃");
            hashMap.put("operateDesc", "");
            hashMap.put("tjpara", "");
            if (this.managerAudienceLoadData == null) {
                this.managerAudienceLoadData = new ManagerAudienceLoadData(this.activityContext);
            }
            this.managerAudienceLoadData.setLogPoit(hashMap).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.videocenter.-$$Lambda$VideoTodayActivity$TbOq_mPexaw1XcxTU_QIJ8svaMo
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    VideoTodayActivity.lambda$setLog$7((String) obj);
                }
            });
        } catch (Resources.NotFoundException e) {
            e.printStackTrace();
        }
    }
}
