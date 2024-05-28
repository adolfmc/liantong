package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.support.p086v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.home.view.ServiceView;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserServiceOrderEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserServiceOrder;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserServiceOrderVh extends RVItemViewHolder {
    private final String TAG;
    private AppCompatActivity activityContext;
    private final LinearLayout bottom_layout;
    private ImageView content_iv;
    private ImageView fourIv;
    private TextView fourJiao;
    private FrameLayout fourLayout;
    private TextView fourTv;
    private String message;
    private String myOrderTip;
    private ImageView oneIv;
    private TextView oneJiao;
    private FrameLayout oneLayout;
    private TextView oneTv;
    private UserServiceOrderEntity.Data orderEntity0;
    private UserServiceOrderEntity.Data orderEntity1;
    private UserServiceOrderEntity.Data orderEntity2;
    private UserServiceOrderEntity.Data orderEntity3;
    private ImageView threeIv;
    private TextView threeJiao;
    private FrameLayout threeLayout;
    private TextView threeTv;
    private final TextView title_tv;
    private ImageView twoIv;
    private TextView twoJiao;
    private FrameLayout twoLayout;
    private TextView twoTv;

    public UserServiceOrderVh(Activity activity, View view) {
        super(activity, view);
        this.TAG = "我的订单与服务记录";
        this.activityContext = (AppCompatActivity) activity;
        this.bottom_layout = (LinearLayout) view.findViewById(2131296529);
        this.title_tv = (TextView) view.findViewById(2131298801);
        this.content_iv = (ImageView) view.findViewById(2131296705);
        this.oneTv = (TextView) view.findViewById(2131298175);
        this.oneIv = (ImageView) view.findViewById(2131298174);
        this.oneJiao = (TextView) view.findViewById(2131298172);
        this.oneLayout = (FrameLayout) view.findViewById(2131298173);
        this.twoTv = (TextView) view.findViewById(2131299162);
        this.twoIv = (ImageView) view.findViewById(2131299161);
        this.twoJiao = (TextView) view.findViewById(2131299159);
        this.twoLayout = (FrameLayout) view.findViewById(2131299160);
        this.threeTv = (TextView) view.findViewById(2131298778);
        this.threeIv = (ImageView) view.findViewById(2131298777);
        this.threeJiao = (TextView) view.findViewById(2131298775);
        this.threeLayout = (FrameLayout) view.findViewById(2131298776);
        this.fourTv = (TextView) view.findViewById(2131297031);
        this.fourIv = (ImageView) view.findViewById(2131297030);
        this.fourJiao = (TextView) view.findViewById(2131297028);
        this.fourLayout = (FrameLayout) view.findViewById(2131297029);
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            if (rVItemEntity.refresh) {
                rVItemEntity.refresh = false;
                initView();
                initData();
                ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.USERSERVICEORDER, this.itemView);
            }
        }
    }

    private void initView() {
        this.oneLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserServiceOrderVh$Q-WEtQdwXBybc-wrk6lrJu9tZIs
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserServiceOrderVh.this.intentUrl(0);
            }
        });
        this.twoLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserServiceOrderVh$IrA3r81wPty_hJ1zxfmcRZGO1aA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserServiceOrderVh.this.intentUrl(1);
            }
        });
        this.threeLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserServiceOrderVh$hOVd-tH3b4ueEb3SxygdaIbpZDM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserServiceOrderVh.this.intentUrl(2);
            }
        });
        this.fourLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserServiceOrderVh$V-Bnztuky9cl82fL_UUvXTo3cyA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserServiceOrderVh.this.intentUrl(3);
            }
        });
        this.content_iv.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserServiceOrderVh.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                try {
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (!TextUtils.isEmpty(UserServiceOrderVh.this.myOrderTip)) {
                    CustomDialogManager.showUserOrderDialog(UserServiceOrderVh.this.activityContext, UserServiceOrderVh.this.message, UserServiceOrderVh.this.myOrderTip, new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserServiceOrderVh.1.1
                        @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
                        public void onClickOk() {
                        }
                    });
                    UserServiceOrderVh.this.clickCollect(4, "我的订单与服务记录-提醒", "");
                    NBSActionInstrumentation.onClickEventExit();
                    return;
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        addServiceView();
    }

    private void addServiceView() {
        try {
            if (App.hasLogined() && !UserManager.getInstance().isYiwang()) {
                ServiceView serviceView = new ServiceView(this.activityContext, ServiceView.USER);
                serviceView.getData(this.activityContext);
                if (this.bottom_layout.getChildCount() > 0) {
                    this.bottom_layout.removeAllViews();
                }
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(-1, -1);
                layoutParams.setMargins(UIUtils.dip2px(8.0f), 0, UIUtils.dip2px(10.0f), 0);
                this.bottom_layout.addView(serviceView, layoutParams);
                this.bottom_layout.setVisibility(0);
                return;
            }
            this.bottom_layout.setVisibility(8);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7980d("服务提醒组件插入异常");
        }
    }

    private void initData() {
        dataBind(ManagerUserServiceOrder.getInstance().getDefaultEntity());
        dataBind(ManagerUserServiceOrder.getInstance().cacheData());
        ManagerUserServiceOrder.getInstance().getUserServiceOrder(this.activityContext).subscribe(new Consumer<UserServiceOrderEntity>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserServiceOrderVh.2
            @Override // io.reactivex.functions.Consumer
            public void accept(UserServiceOrderEntity userServiceOrderEntity) throws Exception {
                UserServiceOrderVh.this.dataBind(userServiceOrderEntity);
            }
        }, new Consumer() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserServiceOrderVh$nzWwgQ_vNJK103LMXcm7u5A6eiQ
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UserServiceOrderVh userServiceOrderVh = UserServiceOrderVh.this;
                MsLogUtil.m7979d("我的订单与服务记录", ((Throwable) obj).getMessage());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void dataBind(UserServiceOrderEntity userServiceOrderEntity) {
        if (userServiceOrderEntity == null) {
            return;
        }
        String myOrderSwitch = userServiceOrderEntity.getMyOrderSwitch();
        if (!TextUtils.isEmpty(myOrderSwitch) && "0".equals(myOrderSwitch)) {
            setVisibility(false);
            return;
        }
        setVisibility(true);
        this.message = userServiceOrderEntity.getMessage();
        if (!TextUtils.isEmpty(this.message)) {
            this.title_tv.setText(this.message);
        }
        this.myOrderTip = userServiceOrderEntity.getMyOrderTip();
        if (!TextUtils.isEmpty(this.myOrderTip)) {
            this.content_iv.setVisibility(0);
        } else {
            this.content_iv.setVisibility(8);
        }
        List<UserServiceOrderEntity.Data> dataList = userServiceOrderEntity.getDataList();
        if (dataList == null || dataList.size() < 4) {
            return;
        }
        addLightData(dataList);
        this.orderEntity0 = dataList.get(0);
        this.oneTv.setText(this.orderEntity0.getStatus());
        GlideApp.with(App.getInstance()).load(this.orderEntity0.getIcon()).placeholder(2131231795).into(this.oneIv);
        copeCorner(this.orderEntity0.getNum(), this.oneJiao);
        this.orderEntity1 = dataList.get(1);
        this.twoTv.setText(this.orderEntity1.getStatus());
        GlideApp.with(App.getInstance()).load(this.orderEntity1.getIcon()).placeholder(2131231795).into(this.twoIv);
        copeCorner(this.orderEntity1.getNum(), this.twoJiao);
        this.orderEntity2 = dataList.get(2);
        this.threeTv.setText(this.orderEntity2.getStatus());
        GlideApp.with(App.getInstance()).load(this.orderEntity2.getIcon()).placeholder(2131231795).into(this.threeIv);
        copeCorner(this.orderEntity2.getNum(), this.threeJiao);
        this.orderEntity3 = dataList.get(3);
        this.fourTv.setText(this.orderEntity3.getStatus());
        GlideApp.with(App.getInstance()).load(this.orderEntity3.getIcon()).placeholder(2131231795).into(this.fourIv);
        copeCorner(this.orderEntity3.getNum(), this.fourJiao);
    }

    private void copeCorner(String str, TextView textView) {
        if (TextUtils.isEmpty(str) || "0".equals(str) || !App.hasLogined()) {
            textView.setVisibility(8);
            return;
        }
        textView.setVisibility(0);
        if (str.length() >= 3) {
            str = "99+";
        }
        if (str.length() <= 2) {
            textView.setText(str);
            textView.setBackground(this.activityContext.getResources().getDrawable(2131232164));
            return;
        }
        textView.setText(str);
        textView.setBackground(this.activityContext.getResources().getDrawable(2131232165));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void intentUrl(int i) {
        String str = "";
        String str2 = "";
        try {
            switch (i) {
                case 0:
                    str = this.orderEntity0.getUrl();
                    str2 = this.orderEntity0.getStatus();
                    break;
                case 1:
                    str = this.orderEntity1.getUrl();
                    str2 = this.orderEntity1.getStatus();
                    break;
                case 2:
                    str = this.orderEntity2.getUrl();
                    str2 = this.orderEntity2.getStatus();
                    break;
                case 3:
                    str = this.orderEntity3.getUrl();
                    str2 = this.orderEntity3.getStatus();
                    break;
            }
            if (TextUtils.isEmpty(str)) {
                return;
            }
            IntentManager.generateIntentAndGo(this.activityContext, str, "", false, "");
            clickCollect(i, str2, str);
        } catch (Exception e) {
            e.printStackTrace();
            MsLogUtil.m7979d("我的订单与服务记录", e.getMessage());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clickCollect(int i, String str, String str2) {
        StringBuilder sb = new StringBuilder();
        sb.append("512010");
        int i2 = i + 1;
        sb.append(i2);
        String sb2 = sb.toString();
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setPageName("我的").setPbName(str).setCodeId(sb2).setStoreyCode(sb2.substring(0, 3)).setTargetUrl(str2).build());
        PvCurrencyLogUtils.sendServicePvLog(sb2, "我的服务和订单" + i2, "My page", str, str2, "2", "1", "");
    }

    private void addLightData(List<UserServiceOrderEntity.Data> list) {
        try {
            ArrayList arrayList = new ArrayList();
            int i = 0;
            while (i < list.size()) {
                StringBuilder sb = new StringBuilder();
                sb.append("512010");
                int i2 = i + 1;
                sb.append(i2);
                arrayList.add(new UserLightEntity(sb.toString(), list.get(i).getStatus()));
                i = i2;
            }
            ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.USERSERVICEORDER, arrayList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
