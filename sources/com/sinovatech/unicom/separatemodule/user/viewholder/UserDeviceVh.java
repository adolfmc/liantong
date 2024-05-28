package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.CardView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.login.dongtaimiyao.SystemTimeUtil;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserDeviceEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.manager.DialogCallBack;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserDevice;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import com.sinovatech.unicom.separatemodule.user.manager.UserDeviceRequestManager;
import com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserDeviceVh extends RVItemViewHolder {
    private final String TAG;
    private AppCompatActivity activity;
    private String bannerUrls;
    private ImageView default_iv;
    private ImageView device_good_iv1;
    private ImageView device_good_iv2;
    private LinearLayout device_good_lable1;
    private LinearLayout device_good_lable2;
    private TextView device_good_lable_tv1;
    private TextView device_good_lable_tv2;
    private CardView device_image_view;
    private ImageView device_iv;
    private CardView device_main_view;
    private TextView device_name_tv1;
    private TextView device_name_tv2;
    private String feedBackContent;
    private TextView good_black_tv1;
    private TextView good_black_tv2;
    private TextView good_red_tv1;
    private TextView good_red_tv2;
    private RelativeLayout good_rl1;
    private RelativeLayout good_rl2;
    private ImageView iv_refresh;
    private UserDeviceEntity.Data.LeftData leftData;
    private UserDeviceRequestManager manager;
    private String moreJumpUrl;
    private String moreTitle;
    private LinearLayout more_ll;
    private TextView more_tv;
    private UserDeviceEntity.Data.RightData rightData1;
    private UserDeviceEntity.Data.RightData rightData2;
    long tempTime;
    private FrameLayout user_device_fl;
    private TextView user_device_lable_tv;
    private TextView user_device_right_top_tv1;
    private TextView user_device_right_top_tv2;
    private RelativeLayout user_device_rl;
    private TextView user_device_time_tv;
    private TextView user_device_title_tv;
    private ImageView wenhao_iv;

    public UserDeviceVh(Activity activity, View view) {
        super(activity, view);
        this.TAG = "UserDeviceVh";
        this.moreJumpUrl = "";
        this.feedBackContent = "";
        this.tempTime = 0L;
        this.activity = (AppCompatActivity) activity;
        this.manager = new UserDeviceRequestManager(this.activity);
        initView(view);
        setVisibility(false);
    }

    private void initView(View view) {
        try {
            this.user_device_rl = (RelativeLayout) view.findViewById(2131299361);
            this.user_device_fl = (FrameLayout) view.findViewById(2131299357);
            this.device_image_view = (CardView) view.findViewById(2131296857);
            this.wenhao_iv = (ImageView) view.findViewById(2131299607);
            this.more_ll = (LinearLayout) view.findViewById(2131298084);
            this.device_good_lable1 = (LinearLayout) view.findViewById(2131296853);
            this.device_good_lable2 = (LinearLayout) view.findViewById(2131296854);
            this.user_device_title_tv = (TextView) view.findViewById(2131299363);
            this.user_device_lable_tv = (TextView) view.findViewById(2131299358);
            this.user_device_time_tv = (TextView) view.findViewById(2131299362);
            this.more_tv = (TextView) view.findViewById(2131298086);
            this.device_iv = (ImageView) view.findViewById(2131296858);
            this.user_device_right_top_tv1 = (TextView) view.findViewById(2131299359);
            this.user_device_right_top_tv2 = (TextView) view.findViewById(2131299360);
            this.device_good_lable_tv1 = (TextView) view.findViewById(2131296855);
            this.device_good_lable_tv2 = (TextView) view.findViewById(2131296856);
            this.device_good_iv1 = (ImageView) view.findViewById(2131296851);
            this.device_good_iv2 = (ImageView) view.findViewById(2131296852);
            this.device_name_tv1 = (TextView) view.findViewById(2131296860);
            this.device_name_tv2 = (TextView) view.findViewById(2131296861);
            this.good_black_tv1 = (TextView) view.findViewById(2131297082);
            this.good_black_tv2 = (TextView) view.findViewById(2131297083);
            this.good_red_tv1 = (TextView) view.findViewById(2131297084);
            this.good_red_tv2 = (TextView) view.findViewById(2131297085);
            this.iv_refresh = (ImageView) view.findViewById(2131297468);
            this.device_main_view = (CardView) view.findViewById(2131296859);
            this.default_iv = (ImageView) view.findViewById(2131296820);
            this.good_rl1 = (RelativeLayout) view.findViewById(2131297086);
            this.good_rl2 = (RelativeLayout) view.findViewById(2131297087);
            this.good_rl1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    try {
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    if (UserDeviceVh.this.rightData1 != null && !TextUtils.isEmpty(UserDeviceVh.this.rightData1.getTarget_url())) {
                        IntentManager.generateIntentAndGo(UserDeviceVh.this.activityContext, UserDeviceVh.this.rightData1.getTarget_url(), "", false, "get");
                        ManagerUserDevice.getInstance().complexClickLog("5170201", UserDeviceVh.this.rightData1.getTarget_url(), "我的设备商品1", UserDeviceVh.this.rightData1.getGoodType() + "," + UserDeviceVh.this.rightData1.getBlackViceTitle() + "," + UserDeviceVh.this.rightData1.getRedViceTitle() + UserDeviceVh.this.rightData1.getGoodLabel() + "," + UserDeviceVh.this.rightData1.getLabel(), UserDeviceVh.this.rightData1.getGoodTypeCode(), UserDeviceVh.this.rightData1.getGoodsId(), UserDeviceVh.this.rightData1.getActType(), UserDeviceVh.this.rightData1.getActId(), UserDeviceVh.this.rightData1.getTarget_url(), "", UserDeviceVh.this.rightData1.getFloorId(), UserDeviceVh.this.rightData1.getMaintaining_information(), UserDeviceVh.this.rightData1.getMaintenance_position_code(), UserDeviceVh.this.rightData1.getFloorDataType(), UserDeviceVh.this.rightData1.getFloorMaterialId(), UserDeviceVh.this.rightData1.getFloorTemplateType(), UserDeviceVh.this.rightData1.getFloorTraceId(), UserDeviceVh.this.rightData1.getFloorBatchId(), UserDeviceVh.this.rightData1.getFloorAlgorithmType(), UserDeviceVh.this.rightData1.getGoodTypeCode());
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.good_rl2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    if (UserDeviceVh.this.rightData2 != null && !TextUtils.isEmpty(UserDeviceVh.this.rightData2.getTarget_url())) {
                        IntentManager.generateIntentAndGo(UserDeviceVh.this.activityContext, UserDeviceVh.this.rightData2.getTarget_url(), "", false, "get");
                        ManagerUserDevice.getInstance().complexClickLog("5170202", UserDeviceVh.this.rightData2.getTarget_url(), "我的设备商品1", UserDeviceVh.this.rightData2.getGoodType() + "," + UserDeviceVh.this.rightData2.getBlackViceTitle() + "," + UserDeviceVh.this.rightData2.getRedViceTitle() + UserDeviceVh.this.rightData2.getGoodLabel() + "," + UserDeviceVh.this.rightData2.getLabel(), UserDeviceVh.this.rightData2.getGoodTypeCode(), UserDeviceVh.this.rightData2.getGoodsId(), UserDeviceVh.this.rightData2.getActType(), UserDeviceVh.this.rightData2.getActId(), UserDeviceVh.this.rightData2.getTarget_url(), "", UserDeviceVh.this.rightData2.getFloorId(), UserDeviceVh.this.rightData2.getMaintaining_information(), UserDeviceVh.this.rightData2.getMaintenance_position_code(), UserDeviceVh.this.rightData2.getFloorDataType(), UserDeviceVh.this.rightData2.getFloorMaterialId(), UserDeviceVh.this.rightData2.getFloorTemplateType(), UserDeviceVh.this.rightData2.getFloorTraceId(), UserDeviceVh.this.rightData2.getFloorBatchId(), UserDeviceVh.this.rightData2.getFloorAlgorithmType(), UserDeviceVh.this.rightData2.getGoodTypeCode());
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.default_iv.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    if (!TextUtils.isEmpty(UserDeviceVh.this.bannerUrls)) {
                        IntentManager.generateIntentAndGo(UserDeviceVh.this.activityContext, UserDeviceVh.this.bannerUrls, "", false, "get");
                        ManagerUserDevice.getInstance().simpleClickLog("5170401", UserDeviceVh.this.bannerUrls, "我的设备banner", "");
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.iv_refresh.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    UserDeviceVh.this.loadData();
                    ManagerUserDevice.getInstance().simpleClickLog("5170302", "", "我的设备刷新", "我的设备刷新");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.more_ll.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    IntentManager.generateIntentAndGo(UserDeviceVh.this.activityContext, UserDeviceVh.this.moreJumpUrl, "", false, "get");
                    ManagerUserDevice.getInstance().simpleClickLog("5170301", UserDeviceVh.this.moreJumpUrl, "我的设备更多", "我的设备更多");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.wenhao_iv.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view2) {
                    NBSActionInstrumentation.onClickEventEnter(view2, this);
                    Tracker.onClick(view2);
                    UserDeviceDialog.showDialog(UserDeviceVh.this.activityContext, "", UserDeviceVh.this.feedBackContent, "", "", "", UserDeviceVh.this.leftData.getUseDeviceTimeStr(), new DialogCallBack() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.6.1
                        @Override // com.sinovatech.unicom.separatemodule.user.manager.DialogCallBack
                        public void onShow() {
                        }

                        @Override // com.sinovatech.unicom.separatemodule.user.manager.DialogCallBack
                        public void dismiss(boolean z) {
                            if (z) {
                                ManagerUserDevice managerUserDevice = ManagerUserDevice.getInstance();
                                managerUserDevice.simpleClickLog("5170104", ManagerUserDevice.getInstance().getDeviceTime() + "", "选择编辑时间", "选择编辑时间");
                                UserDeviceVh.this.loadData();
                            }
                        }
                    });
                    ManagerUserDevice.getInstance().complexClickLog("5170101", "", "我的设备编辑时间", "我的设备编辑时间", "", UserDeviceVh.this.leftData.getGoodsId(), UserDeviceVh.this.leftData.getActType(), UserDeviceVh.this.leftData.getActId(), UserDeviceVh.this.leftData.getTarget_url(), "", UserDeviceVh.this.leftData.getFloorId(), UserDeviceVh.this.leftData.getMaintaining_information(), UserDeviceVh.this.leftData.getMaintenance_position_code(), UserDeviceVh.this.leftData.getFloorDataType(), UserDeviceVh.this.leftData.getFloorMaterialId(), UserDeviceVh.this.leftData.getFloorTemplateType(), UserDeviceVh.this.leftData.getFloorTraceId(), UserDeviceVh.this.leftData.getFloorBatchId(), UserDeviceVh.this.leftData.getFloorAlgorithmType(), "");
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void showDefaultData(UserDeviceEntity.Data data) {
        try {
            setVisibility(true);
            if (TextUtils.equals(data.getShowType(), "1")) {
                GlideApp.with(this.activityContext).load(data.getDefaultData().getBannerIcon()).into(this.default_iv);
                this.bannerUrls = data.getDefaultData().getBannerUrls();
                if (!TextUtils.isEmpty(data.getDefaultData().getBannerIcon()) && !TextUtils.isEmpty(this.bannerUrls)) {
                    this.device_main_view.setVisibility(8);
                    this.device_image_view.setVisibility(0);
                    return;
                }
                setVisibility(false);
                return;
            }
            this.device_main_view.setVisibility(0);
            this.device_image_view.setVisibility(8);
        } catch (Exception unused) {
            setVisibility(false);
        }
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            if (rVItemEntity.refresh) {
                rVItemEntity.refresh = false;
                if (!App.hasLogined() || LoginManager.isKuandaiOrGuhua()) {
                    setVisibility(false);
                    return;
                }
                ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.USERDEVICE, this.itemView);
                loadData();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadData() {
        try {
            if (this.manager == null) {
                this.manager = new UserDeviceRequestManager(this.activity);
            }
            this.manager.getUserDevice("1").subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserDeviceVh$NcGizmOz9dvYMBsmHfLlEdkn7O8
                @Override // io.reactivex.functions.Consumer
                public final void accept(Object obj) {
                    UserDeviceVh.lambda$loadData$0(UserDeviceVh.this, (UserDeviceEntity) obj);
                }
            }, new Consumer<Throwable>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.7
                @Override // io.reactivex.functions.Consumer
                public void accept(Throwable th) throws Exception {
                    th.printStackTrace();
                    UserDeviceVh.this.setVisibility(false);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            setVisibility(false);
        }
    }

    public static /* synthetic */ void lambda$loadData$0(UserDeviceVh userDeviceVh, UserDeviceEntity userDeviceEntity) throws Exception {
        if (userDeviceEntity != null) {
            UserDeviceEntity.Data data = userDeviceEntity.getData();
            userDeviceVh.feedBackContent = data.getFeedBackContent();
            userDeviceVh.moreJumpUrl = data.getLeftData().getMoreTitleUrls();
            userDeviceVh.moreTitle = userDeviceEntity.getData().getLeftData().getMoreTitle();
            userDeviceVh.refreshView(data);
        }
    }

    private void refreshView(UserDeviceEntity.Data data) {
        if (data == null) {
            setVisibility(false);
            return;
        }
        setVisibility(true);
        try {
            this.leftData = data.getLeftData();
            if (this.leftData == null) {
                showDefaultData(data);
                return;
            }
            refreshTime(this.leftData);
            this.user_device_title_tv.setText(TextUtils.isEmpty(this.leftData.getTitle()) ? "我的设备" : this.leftData.getTitle());
            this.more_tv.setText(TextUtils.isEmpty(this.leftData.getMoreTitle()) ? "更多" : this.leftData.getMoreTitle());
            GlideApp.with(this.activityContext).load(this.leftData.getDevcideIcon()).into(this.device_iv);
            List<UserDeviceEntity.Data.RightData> rightData = data.getRightData();
            if (rightData != null && rightData.size() >= 2) {
                this.rightData1 = rightData.get(0);
                if (TextUtils.isEmpty(this.rightData1.getGoodLabel())) {
                    this.user_device_right_top_tv1.setVisibility(8);
                } else {
                    this.user_device_right_top_tv1.setVisibility(0);
                    this.user_device_right_top_tv1.setText(this.rightData1.getGoodLabel());
                }
                if (TextUtils.isEmpty(this.rightData1.getLabel())) {
                    this.device_good_lable1.setVisibility(8);
                } else {
                    this.device_good_lable1.setVisibility(0);
                }
                this.device_good_lable_tv1.setText(this.rightData1.getLabel());
                GlideApp.with(this.activityContext).load(this.rightData1.getGoodIcon()).addListener(new RequestListener<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserDeviceVh.8
                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onLoadFailed(@Nullable GlideException glideException, Object obj, Target<Drawable> target, boolean z) {
                        MsLogUtil.m7979d("glid加载", glideException.getMessage());
                        return false;
                    }

                    @Override // com.bumptech.glide.request.RequestListener
                    public boolean onResourceReady(Drawable drawable, Object obj, Target<Drawable> target, DataSource dataSource, boolean z) {
                        MsLogUtil.m7979d("glid加载", "成功");
                        return false;
                    }
                }).into(this.device_good_iv1);
                this.device_name_tv1.setText(this.rightData1.getGoodType());
                this.good_black_tv1.setText(this.rightData1.getBlackViceTitle());
                this.good_red_tv1.setText(this.rightData1.getRedViceTitle());
                this.rightData2 = rightData.get(1);
                if (TextUtils.isEmpty(this.rightData2.getGoodLabel())) {
                    this.user_device_right_top_tv2.setVisibility(8);
                } else {
                    this.user_device_right_top_tv2.setVisibility(0);
                    this.user_device_right_top_tv2.setText(this.rightData2.getGoodLabel());
                }
                if (TextUtils.isEmpty(this.rightData2.getLabel())) {
                    this.device_good_lable2.setVisibility(8);
                } else {
                    this.device_good_lable2.setVisibility(0);
                }
                this.device_good_lable_tv2.setText(this.rightData2.getLabel());
                GlideApp.with(this.activityContext).load(this.rightData2.getGoodIcon()).into(this.device_good_iv2);
                this.device_name_tv2.setText(this.rightData2.getGoodType());
                this.good_black_tv2.setText(this.rightData2.getBlackViceTitle());
                this.good_red_tv2.setText(this.rightData2.getRedViceTitle());
                addCollectData(data);
                return;
            }
            showDefaultData(data);
        } catch (Exception e) {
            e.printStackTrace();
            setVisibility(false);
        }
    }

    private void addCollectData(UserDeviceEntity.Data data) {
        try {
            ArrayList arrayList = new ArrayList();
            if (data == null) {
                return;
            }
            if ("1".equals(data.getShowType())) {
                arrayList.add(new UserLightEntity("5170401", "我的设备banner"));
            } else {
                List<UserDeviceEntity.Data.RightData> rightData = data.getRightData();
                UserDeviceEntity.Data.RightData rightData2 = rightData.get(0);
                UserDeviceEntity.Data.RightData rightData3 = rightData.get(1);
                arrayList.add(new UserLightEntity("5170201", rightData2.getGoodType(), rightData2.getActType() + "", rightData2.getActId() + "", rightData2.getGoodsId(), "", rightData2.getTarget_url(), "", "", rightData2.getFloorId(), rightData2.getMaintaining_information(), rightData2.getMaintenance_position_code(), rightData2.getFloorDataType(), rightData2.getFloorMaterialId(), rightData2.getFloorTemplateType(), rightData2.getFloorTraceId(), rightData2.getFloorBatchId(), rightData2.getFloorAlgorithmType(), rightData2.getGoodType()));
                arrayList.add(new UserLightEntity("5170202", rightData3.getGoodType(), rightData3.getActType(), rightData3.getActId(), rightData3.getGoodsId(), "", rightData3.getTarget_url(), "", "", rightData3.getFloorId(), rightData3.getMaintaining_information(), rightData3.getMaintenance_position_code(), rightData3.getFloorDataType(), rightData3.getFloorMaterialId(), rightData3.getFloorTemplateType(), rightData3.getFloorTraceId(), rightData3.getFloorBatchId(), rightData3.getFloorAlgorithmType(), rightData2.getGoodType()));
                arrayList.add(new UserLightEntity("5170101", "我的设备编辑时间"));
                arrayList.add(new UserLightEntity("5170301", "我的设备更多"));
            }
            ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.USERDEVICE, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void refreshTime(UserDeviceEntity.Data.LeftData leftData) {
        String useDeviceStr;
        try {
            this.user_device_lable_tv.setText(TextUtils.isEmpty(leftData.getLabelStr()) ? "当前" : leftData.getLabelStr());
            long deviceTime = ManagerUserDevice.getInstance().getDeviceTime();
            if (deviceTime != 0) {
                useDeviceStr = "已使用" + TimeUnit.MILLISECONDS.toDays(Math.abs(SystemTimeUtil.currentTimeMillis() - deviceTime)) + "天";
                this.user_device_lable_tv.setText("已手动修改");
            } else {
                useDeviceStr = (leftData == null || TextUtils.isEmpty(leftData.getUseDeviceStr())) ? "手动添加时间" : leftData.getUseDeviceStr();
            }
            this.user_device_time_tv.setText(useDeviceStr);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
