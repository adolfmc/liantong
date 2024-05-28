package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.p086v7.app.AppCompatActivity;
import android.support.p086v7.widget.CardView;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.eventbus.QiandaoEvent2;
import com.sinovatech.unicom.basic.eventbus.YouxiangEvent;
import com.sinovatech.unicom.basic.p314po.MyUnicomEntity;
import com.sinovatech.unicom.basic.p314po.WebParamsEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.fragment.HomeWebFragment;
import com.sinovatech.unicom.basic.p315ui.home.view.MarqueeTextView;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerHomeLiBaoTiXing;
import com.sinovatech.unicom.basic.p315ui.manager.ManagerMail;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.basic.view.CircularImage;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.PreferenceConstUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.common.utils.ZhengzeUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.Log.StatisticsUploadUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.miniprogram.cumphome.MainTabCumpLauncher;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.storage.JSStorageBox;
import com.sinovatech.unicom.separatemodule.notice.PushMsgDao;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity;
import com.sinovatech.unicom.separatemodule.user.entity.UserQuanyiEntity;
import com.sinovatech.unicom.separatemodule.user.eventbus.UserHeaderEvent;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUser;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import com.sinovatech.unicom.separatemodule.user.view.MarqueeHorizontalTextView;
import com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

/*  JADX ERROR: NullPointerException in pass: ClassModifier
    java.lang.NullPointerException
    	at jadx.core.utils.BlockUtils.collectAllInsns(BlockUtils.java:987)
    	at jadx.core.dex.visitors.ClassModifier.removeBridgeMethod(ClassModifier.java:230)
    	at jadx.core.dex.visitors.ClassModifier.removeSyntheticMethods(ClassModifier.java:152)
    	at java.util.ArrayList.forEach(ArrayList.java:1259)
    	at jadx.core.dex.visitors.ClassModifier.visit(ClassModifier.java:62)
    */
/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserAHeaderVH extends RVItemViewHolder {
    private String appId;
    private ImageView bottomCardButton;
    private LinearLayout bottomCardLayout;
    private ImageView bottomCardLevelImageView;
    private MarqueeHorizontalTextView bottomCardTitle1;
    private TextView bottomCardTitle2;
    private TextView bottomCardTitle3;
    private FrameLayout h5Layout;
    private ManagerHomeLiBaoTiXing homeLiBaoTiXing;
    private LinearLayout loginLayout;
    private ManagerMail managerMail;
    private ManagerUser managerUser;
    private ImageView message1Image;
    private LinearLayout mobileLayout;
    private TextView mobileText;
    private TextView mobileType;
    private CardView newCardView;
    private CircularImage photoImage;
    private ImageView plusImage;
    private PushMsgDao pushMsgDao;
    private ImageView qiandao1Image;
    String remark3;
    private final View root_layout;
    private ImageView setting1Image;
    private TextView taocan2Button;
    private TextView taocan2LeftJiaobiaoText;
    private TextView taocan2MainTitleText;
    private MarqueeTextView taocan2RigthJiaobiaoText;
    private TextView taocan2ViceTitleText;
    private RelativeLayout taocanLayout;
    private CardView taocanLayout3;
    private ImageView taocanLayout3Image;
    private LinearLayout taocanPaddingLayout;
    private TextView taocanTextView1;
    private TextView taocanTextView2;
    private ImageView taocandscImageView_hy_3;
    private ImageView taocandscImageView_yp_3;
    private LinearLayout taocandscLayout_fk;
    private LinearLayout taocandscLayout_hy;
    private LinearLayout taocandscLayout_kd;
    private LinearLayout taocandscLayout_yp;
    private TextView taocandscTextView_fk_1;
    private TextView taocandscTextView_fk_2;
    private ImageView taocandscTextView_fk_3;
    private TextView taocandscTextView_hy_1;
    private TextView taocandscTextView_hy_2;
    private TextView taocandscTextView_kd_1;
    private TextView taocandscTextView_kd_2;
    private ImageView taocandscTextView_kd_3;
    private TextView taocandscTextView_yp_1;
    private TextView taocandscTextView_yp_2;
    private RelativeLayout taocanlayout2;
    private LinearLayout unLoginLayout;
    private TextView unLoginTextView;
    private UserNewTopentity userNewTopentity;
    private HomeWebFragment webFragment;
    private ImageView wenhaoImnage;
    private ImageView xingjiImage;
    private LinearLayout yuanshengLayout;

    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IXiaoheitiaoCallBack {
        void onFail();

        void onFinish();
    }

    /*  JADX ERROR: Failed to decode insn: 0x0414: UNKNOWN(0x4273), method: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.lambda$loadTaocan$10(com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH, com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0414: UNKNOWN(0x4273)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0422: UNKNOWN(0x427A), method: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.lambda$loadTaocan$10(com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH, com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity):void
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0422: UNKNOWN(0x427A)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    public static /* synthetic */ void lambda$loadTaocan$10(com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH r11, com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity r12) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 1201
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.lambda$loadTaocan$10(com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH, com.sinovatech.unicom.separatemodule.user.entity.UserNewTopentity):void");
    }

    public static /* synthetic */ void lambda$resetUnloginUI$12(View view) {
    }

    public static /* synthetic */ void lambda$resetUnloginUI$13(View view) {
    }

    public static /* synthetic */ void lambda$resetUnloginUI$14(View view) {
    }

    public static /* synthetic */ void lambda$resetUnloginUI$15(View view) {
    }

    public UserAHeaderVH(final Activity activity, View view) {
        super(activity, view);
        this.remark3 = "";
        this.root_layout = view.findViewById(2131298416);
        this.unLoginLayout = (LinearLayout) view.findViewById(2131299453);
        this.unLoginTextView = (TextView) view.findViewById(2131299452);
        this.loginLayout = (LinearLayout) view.findViewById(2131299408);
        this.photoImage = (CircularImage) view.findViewById(2131299413);
        this.mobileLayout = (LinearLayout) view.findViewById(2131299411);
        this.mobileText = (TextView) view.findViewById(2131299410);
        this.message1Image = (ImageView) view.findViewById(2131299409);
        this.setting1Image = (ImageView) view.findViewById(2131299416);
        this.qiandao1Image = (ImageView) view.findViewById(2131299415);
        this.xingjiImage = (ImageView) view.findViewById(2131299417);
        this.plusImage = (ImageView) this.itemView.findViewById(2131299414);
        AppCompatActivity appCompatActivity = (AppCompatActivity) activity;
        this.managerUser = new ManagerUser(appCompatActivity);
        this.homeLiBaoTiXing = new ManagerHomeLiBaoTiXing(appCompatActivity);
        this.pushMsgDao = new PushMsgDao(activity);
        this.setting1Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$63YgoUD95ukRc3sJUOGT6FPAnMo
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserAHeaderVH.lambda$new$0(UserAHeaderVH.this, activity, view2);
            }
        });
        this.message1Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$f0QeghcmwGlbov3xCFA4dbi2j40
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserAHeaderVH.lambda$new$1(UserAHeaderVH.this, activity, view2);
            }
        });
        this.qiandao1Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.1
            {
                UserAHeaderVH.this = this;
            }

            @Override // android.view.View.OnClickListener
            public void onClick(View view2) {
                NBSActionInstrumentation.onClickEventEnter(view2, this);
                Tracker.onClick(view2);
                if (!App.hasLogined()) {
                    Activity activity2 = activity;
                    activity2.startActivity(new Intent(activity2, LoginBindActivity.class));
                    UserAHeaderVH.this.logDJ01("5010105", "登录", "", "签到", "我的页面签到");
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.mobileLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$1QmsbnLW4SinrNO5WC4igI8X8yQ
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserAHeaderVH.lambda$new$2(UserAHeaderVH.this, activity, view2);
            }
        });
        this.managerMail = new ManagerMail(appCompatActivity, new ManagerMail.RefreshComplete() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$3umOtCn8rYOegRE-aMfjdurdys0
            @Override // com.sinovatech.unicom.basic.p315ui.manager.ManagerMail.RefreshComplete
            public final void complete() {
                UserAHeaderVH.lambda$new$3(activity);
            }
        });
        this.mobileType = (TextView) view.findViewById(2131299412);
        this.newCardView = (CardView) view.findViewById(2131299287);
        this.taocanLayout = (RelativeLayout) view.findViewById(2131299435);
        this.taocanTextView1 = (TextView) view.findViewById(2131299434);
        this.taocanTextView2 = (TextView) view.findViewById(2131299436);
        this.taocandscTextView_yp_1 = (TextView) view.findViewById(2131299341);
        this.taocandscTextView_yp_2 = (TextView) view.findViewById(2131299342);
        this.taocandscImageView_yp_3 = (ImageView) view.findViewById(2131299349);
        this.taocandscTextView_hy_1 = (TextView) view.findViewById(2131299343);
        this.taocandscTextView_hy_2 = (TextView) view.findViewById(2131299344);
        this.taocandscImageView_hy_3 = (ImageView) view.findViewById(2131299350);
        this.taocandscTextView_fk_1 = (TextView) view.findViewById(2131299345);
        this.taocandscTextView_fk_2 = (TextView) view.findViewById(2131299346);
        this.taocandscTextView_fk_3 = (ImageView) view.findViewById(2131299351);
        this.taocandscTextView_kd_1 = (TextView) view.findViewById(2131299347);
        this.taocandscTextView_kd_2 = (TextView) view.findViewById(2131299348);
        this.taocandscTextView_kd_3 = (ImageView) view.findViewById(2131299352);
        this.taocandscLayout_yp = (LinearLayout) view.findViewById(2131299353);
        this.taocandscLayout_hy = (LinearLayout) view.findViewById(2131299354);
        this.taocandscLayout_fk = (LinearLayout) view.findViewById(2131299355);
        this.taocandscLayout_kd = (LinearLayout) view.findViewById(2131299356);
        this.bottomCardLayout = (LinearLayout) view.findViewById(2131299426);
        this.bottomCardLevelImageView = (ImageView) view.findViewById(2131299425);
        this.bottomCardTitle1 = (MarqueeHorizontalTextView) view.findViewById(2131299431);
        this.bottomCardTitle2 = (TextView) view.findViewById(2131299432);
        this.bottomCardTitle3 = (TextView) view.findViewById(2131299433);
        this.bottomCardButton = (ImageView) view.findViewById(2131299424);
        this.taocanPaddingLayout = (LinearLayout) view.findViewById(2131299430);
        this.taocanlayout2 = (RelativeLayout) view.findViewById(2131299427);
        this.taocan2LeftJiaobiaoText = (TextView) view.findViewById(2131299437);
        this.taocan2MainTitleText = (TextView) view.findViewById(2131299438);
        this.taocan2ViceTitleText = (TextView) view.findViewById(2131299439);
        this.taocan2Button = (TextView) view.findViewById(2131299440);
        this.taocan2RigthJiaobiaoText = (MarqueeTextView) view.findViewById(2131299441);
        this.taocanLayout3 = (CardView) view.findViewById(2131299428);
        this.taocanLayout3Image = (ImageView) view.findViewById(2131299429);
        this.wenhaoImnage = (ImageView) view.findViewById(2131299455);
        this.yuanshengLayout = (LinearLayout) view.findViewById(2131299288);
        this.h5Layout = (FrameLayout) view.findViewById(2131299376);
        this.newCardView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$uF8GzHIhHIS4ZSmiKUnUHnJSHaE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                UserAHeaderVH.lambda$new$4(activity, view2);
            }
        });
    }

    public static /* synthetic */ void lambda$new$0(UserAHeaderVH userAHeaderVH, Activity activity, View view) {
        IntentManager.handleLocal(activity, "", "LOCAL_SETTING");
        userAHeaderVH.logDJ01("5010106", "", "", "设置", "我的页面设置");
    }

    public static /* synthetic */ void lambda$new$1(UserAHeaderVH userAHeaderVH, Activity activity, View view) {
        IntentManager.handleLocal(activity, "", "LOCAL_MESSAGE");
        userAHeaderVH.logDJ01("5010107", "LOCAL_MESSAGE", userAHeaderVH.remark3, "消息中心", "我的页面消息");
    }

    public static /* synthetic */ void lambda$new$2(UserAHeaderVH userAHeaderVH, Activity activity, View view) {
        if (UserManager.getInstance().getLoginType().equals("05")) {
            userAHeaderVH.managerMail.showDialog();
            return;
        }
        IntentManager.handleLocal(activity, "", "LOCAL_LOGIN_BIND");
        userAHeaderVH.logDJ01("5010102", "", userAHeaderVH.remark3, "登录", "我的页面用户名称");
    }

    public static /* synthetic */ void lambda$new$3(Activity activity) {
        StatisticsUploadUtils.upload(activity, "51", "我的-头部", "按钮", "0", "邮箱", "");
        EventBusUtils.post(new YouxiangEvent(EventBusUtils.EVENT_USER_YOUXIANG));
    }

    public static /* synthetic */ void lambda$new$4(Activity activity, View view) {
        if (App.hasLogined()) {
            return;
        }
        activity.startActivity(new Intent(activity, LoginBindActivity.class));
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        boolean z;
        JSONObject optJSONObject;
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            if (rVItemEntity.isGengxinXiaoheitiao) {
                setH5LayoutHeight(rVItemEntity.xiaoheiTiaoHight);
                rVItemEntity.isGengxinXiaoheitiao = false;
                String string = App.getSharePreferenceUtil().getString("WoDeXiaoHeiTiaoCumpAppIdConfig");
                if (!TextUtils.isEmpty(string)) {
                    try {
                        String str = JSStorageBox.get(string + "_", "myPackageInfo" + UserManager.getInstance().getCurrentPhoneNumber());
                        if (!TextUtils.isEmpty(str) && (optJSONObject = new JSONObject(str).optJSONObject("value")) != null) {
                            String optString = optJSONObject.optString("headTitle");
                            if ("null".equals(optString)) {
                                optString = "";
                            }
                            if (!TextUtils.isEmpty(optString)) {
                                this.mobileType.setVisibility(0);
                                this.mobileType.setText(optString);
                            } else {
                                this.mobileType.setVisibility(8);
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            if (rVItemEntity.refresh) {
                rVItemEntity.refresh = false;
                EventBusUtils.post(new QiandaoEvent2(EventBusUtils.EVENT_QIANDAOED));
                loadNickName(true);
                loadPlusVip(true);
                loadNickName(false);
                loadPlusVip(false);
                String string2 = App.getSharePreferenceUtil().getString("xiaoheitiao_xiaochngxu_url");
                boolean z2 = App.getSharePreferenceUtil().getBoolean("xiaoheitiao_xiaochngxu");
                boolean z3 = !TextUtils.isEmpty(string2) && string2.contains("edop_unicom");
                try {
                    String queryParameter = Uri.parse(string2.contains("&path=") ? string2.split("&path=")[0] : string2).getQueryParameter("appid");
                    App.getSharePreferenceUtil().putString("WoDeXiaoHeiTiaoCumpAppIdConfig", queryParameter);
                    z = new MainTabCumpLauncher(this.activityContext, "").getPreparedCumpEntity(queryParameter) != null;
                } catch (Exception e2) {
                    e2.printStackTrace();
                    z = false;
                }
                logXiaochengxu("小程序开关", "xiaochengxuSwitch:" + z2 + "     isActiveUrl:" + z3 + "     isPrepared:" + z);
                if (z2 && z3 && z) {
                    this.yuanshengLayout.setVisibility(8);
                    this.h5Layout.setVisibility(0);
                    this.mobileType.setVisibility(8);
                    int i = App.getSharePreferenceUtil().getInt(PreferenceConstUtils.getXiaoheitiaoHeightKey(), 0);
                    if (i == 0) {
                        i = App.hasLogined() ? 154 : 82;
                    }
                    setH5LayoutHeight(i);
                    HomeWebFragment homeWebFragment = this.webFragment;
                    if (homeWebFragment == null) {
                        WebParamsEntity webParamsEntity = new WebParamsEntity();
                        webParamsEntity.setUrl(string2);
                        webParamsEntity.setMiniProgramOpenUrlFlag(true);
                        webParamsEntity.setMiniProgramUrl(string2);
                        this.webFragment = HomeWebFragment.newIntence(webParamsEntity, 99, false);
                        ((AppCompatActivity) this.activityContext).getSupportFragmentManager().beginTransaction().replace(2131299376, this.webFragment).commitAllowingStateLoss();
                        this.webFragment.setiXiaoheitiaoCallBack(new IXiaoheitiaoCallBack() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.2
                            @Override // com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.IXiaoheitiaoCallBack
                            public void onFinish() {
                            }

                            {
                                UserAHeaderVH.this = this;
                            }

                            @Override // com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.IXiaoheitiaoCallBack
                            public void onFail() {
                                UserAHeaderVH.this.logXiaochengxu("加载小程序失败", "");
                                UserAHeaderVH.this.yuanshengLayout.setVisibility(0);
                                UserAHeaderVH.this.h5Layout.setVisibility(8);
                                UserAHeaderVH.this.loadTaocan(true);
                                UserAHeaderVH.this.loadTaocan(false);
                                UserAHeaderVH.this.baoguang(true);
                            }
                        });
                        logXiaochengxu("首次加载小程序", "");
                    } else {
                        homeWebFragment.reloadWeb();
                        logXiaochengxu("重新加载小程序", "");
                    }
                    baoguang(false);
                } else {
                    this.yuanshengLayout.setVisibility(0);
                    this.h5Layout.setVisibility(8);
                    loadTaocan(true);
                    loadTaocan(false);
                    baoguang(true);
                }
                ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.HEADER, this.root_layout);
            }
        }
    }

    public void baoguang(boolean z) {
        if (this.userNewTopentity != null) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new UserLightEntity("5010101", "我的页面头像"));
            arrayList.add(new UserLightEntity("5010102", "我的页面用户名称"));
            arrayList.add(new UserLightEntity("5010105", "我的页面签到"));
            arrayList.add(new UserLightEntity("5010106", "我的页面设置"));
            arrayList.add(new UserLightEntity("5010103", "我的页面星级"));
            arrayList.add(new UserLightEntity("5010108", "我的页面Plus会员"));
            if (z) {
                arrayList.add(new UserLightEntity("5160101", "我的页面卡片-套餐"));
                arrayList.add(new UserLightEntity("5160201", "我的页面卡片-副卡"));
                arrayList.add(new UserLightEntity("5160202", "我的页面卡片-宽带"));
                arrayList.add(new UserLightEntity("5160203", "我的页面卡片-合约"));
                arrayList.add(new UserLightEntity("5160204", "我的页面卡片-云盘"));
                if (TextUtils.equals(this.userNewTopentity.getCurrentPhoneNumber(), UserManager.getInstance().getCurrentPhoneNumber())) {
                    String cardFloorTitle = this.userNewTopentity.getCardFloorTitle();
                    arrayList = arrayList;
                    arrayList.add(new UserLightEntity("5160105", TextUtils.isEmpty(cardFloorTitle) ? "灰卡片" : cardFloorTitle, notNull(this.userNewTopentity.getFloorActType()), notNull(this.userNewTopentity.getFloorActId()), notNull(this.userNewTopentity.getFloorGoodsId()), notNull(this.userNewTopentity.getFloorBusinessType()), notNull(this.userNewTopentity.getFloorGoodsUrl()), notNull(this.userNewTopentity.getFloorManrongActivity()), notNull(this.userNewTopentity.getFloorManrongType()), notNull(this.userNewTopentity.getFloorId()), notNull(this.userNewTopentity.getFloorBuriedPointData()), notNull(this.userNewTopentity.getFloorPosition()), notNull(this.userNewTopentity.getFloorDataType()), notNull(this.userNewTopentity.getFloorMaterialId()), notNull(this.userNewTopentity.getFloorTemplateType()), notNull(this.userNewTopentity.getFloorTraceId()), notNull(this.userNewTopentity.getFloorBatchId()), notNull(this.userNewTopentity.getFloorAlgorithmType()), ""));
                }
            }
            ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.HEADER, arrayList);
        }
    }

    private void loadNickName(boolean z) {
        if (App.hasLogined()) {
            this.loginLayout.setVisibility(0);
            this.unLoginLayout.setVisibility(8);
            this.managerUser.getNickName(z).subscribe(new C95643());
            return;
        }
        this.loginLayout.setVisibility(8);
        this.unLoginLayout.setVisibility(0);
        this.photoImage.setImageResource(2131232482);
        this.xingjiImage.setVisibility(4);
        this.mobileText.setText("--");
        this.photoImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$EaKJTKezB3aL03YTQqJBXPlu1y0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$loadNickName$5(UserAHeaderVH.this, view);
            }
        });
        this.unLoginTextView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$8hOxOtm_HVtFMqat0Itulvq-EME
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                IntentManager.handleLocal(UserAHeaderVH.this.activityContext, "", "LOCAL-LOGIN_BIND");
            }
        });
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH$3 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C95643 implements Observer<MyUnicomEntity> {
        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(@NonNull Throwable th) {
        }

        C95643() {
            UserAHeaderVH.this = r1;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(@NonNull Disposable disposable) {
            if (TextUtils.isEmpty(CacheDataCenter.getInstance().getUserInfoNew(UserManager.getInstance().getCurrentPhoneNumber()))) {
                UserAHeaderVH.this.xingjiImage.setVisibility(4);
                UserAHeaderVH.this.photoImage.setImageResource(2131232482);
                UserAHeaderVH.this.mobileText.setText("--");
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(@NonNull final MyUnicomEntity myUnicomEntity) {
            if (UserManager.getInstance().getCurrentPhoneNumber().equals(myUnicomEntity.getPhone())) {
                GlideApp.with(UserAHeaderVH.this.activityContext).load(myUnicomEntity.getHeadImage()).placeholder(2131232482).into(UserAHeaderVH.this.photoImage);
                UserManager.getInstance().saveUserPhotoURL(myUnicomEntity.getPhone(), myUnicomEntity.getHeadImage());
                UserAHeaderVH.this.photoImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$3$3a19yipnVCMSkF3TCuFbosi1mzA
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UserAHeaderVH.C95643.lambda$onNext$0(UserAHeaderVH.C95643.this, myUnicomEntity, view);
                    }
                });
                if ("00000000000".equals(myUnicomEntity.getPhone())) {
                    myUnicomEntity.setPhone(UserManager.getInstance().getUserAccountName());
                }
                UserAHeaderVH.this.mobileText.setText(TextUtils.isEmpty(myUnicomEntity.getNickName()) ? myUnicomEntity.getPhone() : myUnicomEntity.getNickName());
                UserAHeaderVH.this.mobileLayout.setContentDescription(TextUtils.isEmpty(myUnicomEntity.getNickName()) ? myUnicomEntity.getPhone() : myUnicomEntity.getNickName());
                String levelPic = myUnicomEntity.getLevelPic();
                if (!TextUtils.isEmpty(levelPic)) {
                    UserAHeaderVH.this.xingjiImage.setVisibility(0);
                    GlideApp.with(UserAHeaderVH.this.activityContext).load(levelPic).into((RequestBuilder<Drawable>) new SimpleTarget<Drawable>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.3.1
                        {
                            C95643.this = this;
                        }

                        @Override // com.bumptech.glide.request.target.Target
                        public /* bridge */ /* synthetic */ void onResourceReady(@android.support.annotation.NonNull Object obj, @Nullable Transition transition) {
                            onResourceReady((Drawable) obj, (Transition<? super Drawable>) transition);
                        }

                        public void onResourceReady(@android.support.annotation.NonNull Drawable drawable, @Nullable Transition<? super Drawable> transition) {
                            UserAHeaderVH.this.xingjiImage.setImageDrawable(drawable);
                        }
                    });
                    UserAHeaderVH.this.xingjiImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$3$CgHad6dLFISwklgpFXHmld-WZuU
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserAHeaderVH.C95643.lambda$onNext$1(UserAHeaderVH.C95643.this, myUnicomEntity, view);
                        }
                    });
                } else {
                    UserAHeaderVH.this.xingjiImage.setVisibility(8);
                }
            }
            if (!TextUtils.isEmpty(myUnicomEntity.getThirdPictureLinkUrl())) {
                UserAHeaderVH.this.qiandao1Image.setVisibility(0);
            } else {
                UserAHeaderVH.this.qiandao1Image.setVisibility(8);
            }
            UserAHeaderVH.this.qiandao1Image.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$3$tGi8ZEOIu0iX2y81HRdhWzTugwU
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    UserAHeaderVH.C95643.lambda$onNext$2(UserAHeaderVH.C95643.this, myUnicomEntity, view);
                }
            });
            UserHeaderEvent userHeaderEvent = new UserHeaderEvent(EventBusUtils.EVENT_USER_HEADER);
            userHeaderEvent.setNickName(TextUtils.isEmpty(myUnicomEntity.getNickName()) ? myUnicomEntity.getPhone() : myUnicomEntity.getNickName());
            userHeaderEvent.setQiandaoUrl(myUnicomEntity.getThirdPictureLinkUrl());
            EventBusUtils.post(userHeaderEvent);
        }

        public static /* synthetic */ void lambda$onNext$0(C95643 c95643, MyUnicomEntity myUnicomEntity, View view) {
            String jpUrl = myUnicomEntity.getJpUrl();
            if (UserManager.getInstance().isYiwang()) {
                String phone = TextUtils.isEmpty(myUnicomEntity.getNickName()) ? myUnicomEntity.getPhone() : myUnicomEntity.getNickName();
                if (TextUtils.isEmpty(jpUrl)) {
                    IntentManager.goYwUserInfoActivity(UserAHeaderVH.this.activityContext, phone, myUnicomEntity.getInfoDetail());
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("infoUrl", myUnicomEntity.getInfoDetail());
                bundle.putString("nickName", phone);
                IntentManager.gotoWebViewActivityBundle(UserAHeaderVH.this.activityContext, jpUrl, "", bundle);
            } else {
                if (TextUtils.isEmpty(jpUrl)) {
                    jpUrl = URLSet.userDetails();
                }
                IntentManager.gotoWebViewActivity(UserAHeaderVH.this.activityContext, jpUrl, "");
            }
            UserAHeaderVH.this.logDJ01("5010101", myUnicomEntity.getInfoDetail(), UserAHeaderVH.this.remark3, "个人中心", "我的页面头像");
        }

        public static /* synthetic */ void lambda$onNext$1(C95643 c95643, MyUnicomEntity myUnicomEntity, View view) {
            IntentManager.generateIntentAndGo(UserAHeaderVH.this.activityContext, myUnicomEntity.getLevelLinkedAddress(), "");
            UserAHeaderVH.this.logDJ01("5010103", myUnicomEntity.getLevelLinkedAddress(), UserAHeaderVH.this.remark3, "星级", "我的页面星级");
        }

        public static /* synthetic */ void lambda$onNext$2(C95643 c95643, MyUnicomEntity myUnicomEntity, View view) {
            if (TextUtils.isEmpty(myUnicomEntity.getThirdPictureLinkUrl())) {
                return;
            }
            IntentManager.generateIntentAndGo(UserAHeaderVH.this.activityContext, myUnicomEntity.getThirdPictureLinkUrl(), "签到", true, "get");
            UserAHeaderVH.this.logDJ01("5010105", myUnicomEntity.getThirdPictureLinkUrl(), UserAHeaderVH.this.remark3, "签到", "我的页面签到");
        }
    }

    public static /* synthetic */ void lambda$loadNickName$5(UserAHeaderVH userAHeaderVH, View view) {
        IntentManager.handleLocal(userAHeaderVH.activityContext, "", "LOCAL-LOGIN_BIND");
        userAHeaderVH.logDJ01("5010101", "登录", userAHeaderVH.remark3, "个人中心", "我的页面头像");
    }

    private void loadPlusVip(boolean z) {
        if (App.hasLogined()) {
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            this.managerUser.getUserHeaderCard(z, currentPhoneNumber).subscribe(new C95664(currentPhoneNumber));
            return;
        }
        this.plusImage.setVisibility(8);
    }

    /* renamed from: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH$4 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class C95664 implements Observer<UserQuanyiEntity> {
        final /* synthetic */ String val$mobile;

        @Override // io.reactivex.Observer
        public void onComplete() {
        }

        @Override // io.reactivex.Observer
        public void onError(@NonNull Throwable th) {
        }

        C95664(String str) {
            UserAHeaderVH.this = r1;
            this.val$mobile = str;
        }

        @Override // io.reactivex.Observer
        public void onSubscribe(@NonNull Disposable disposable) {
            if (TextUtils.isEmpty(CacheDataCenter.getInstance().getQuanyiNew(this.val$mobile))) {
                UserAHeaderVH.this.plusImage.setVisibility(8);
            }
        }

        @Override // io.reactivex.Observer
        public void onNext(@NonNull UserQuanyiEntity userQuanyiEntity) {
            final UserQuanyiEntity.IdentifyArrayBean identifyArrayBean;
            if ("0000".equals(userQuanyiEntity.getCode())) {
                if ("6".equals(userQuanyiEntity.getMember().getPlusflag())) {
                    UserAHeaderVH.this.plusImage.setVisibility(0);
                    List<UserQuanyiEntity.IdentifyArrayBean> identifyArray = userQuanyiEntity.getIdentifyArray();
                    if (identifyArray == null || identifyArray.size() <= 0 || (identifyArrayBean = identifyArray.get(0)) == null || TextUtils.isEmpty(identifyArrayBean.getIcon())) {
                        return;
                    }
                    GlideApp.with(UserAHeaderVH.this.activityContext).load(identifyArrayBean.getIcon()).into(UserAHeaderVH.this.plusImage);
                    UserAHeaderVH.this.plusImage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$4$4CpmPGuTZIneOoRoIfJRrLQp4Dg
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserAHeaderVH.C95664.lambda$onNext$0(UserAHeaderVH.C95664.this, identifyArrayBean, view);
                        }
                    });
                    return;
                }
                UserAHeaderVH.this.plusImage.setVisibility(8);
            }
        }

        public static /* synthetic */ void lambda$onNext$0(C95664 c95664, UserQuanyiEntity.IdentifyArrayBean identifyArrayBean, View view) {
            IntentManager.generateIntentAndGo(UserAHeaderVH.this.activityContext, identifyArrayBean.getUrl());
            UserAHeaderVH.this.logDJ01("5010108", identifyArrayBean.getUrl(), UserAHeaderVH.this.remark3, "plus", "我的页面Plus会员");
        }
    }

    public void loadTaocan(boolean z) {
        if (!App.hasLogined()) {
            resetUnloginUI();
        }
        if (TextUtils.isEmpty(CacheDataCenter.getInstance().getNewTopHeader(UserManager.getInstance().getCurrentPhoneNumber()))) {
            resetUnloginUI();
        }
        this.managerUser.getNewUserTop(z, UserManager.getInstance().getCurrentPhoneNumber()).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$aO5nYKYmXsqTNDNt2iJDa_B13yI
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UserAHeaderVH.lambda$loadTaocan$10(UserAHeaderVH.this, (UserNewTopentity) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    @NBSInstrumented
    /* renamed from: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH$12 */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    class View$OnClickListenerC956112 implements View.OnClickListener {
        final /* synthetic */ UserNewTopentity val$entity;

        View$OnClickListenerC956112(UserNewTopentity userNewTopentity) {
            UserAHeaderVH.this = r1;
            this.val$entity = userNewTopentity;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            Tracker.onClick(view);
            UserAHeaderVH.this.xiaoheitiaoJump(this.val$entity);
            NBSActionInstrumentation.onClickEventExit();
        }
    }

    public void xiaoheitiaoJump(UserNewTopentity userNewTopentity) {
        gotoWebView(userNewTopentity.getCardFloorButtonUrl());
        logDJ07("5160105", userNewTopentity.getCardFloorButtonUrl(), "我的页面卡片-灰色卡片", userNewTopentity.getCardFloorToucheTemplate(), userNewTopentity.getCardFloorToucheID(), userNewTopentity.getCardFloorTitle(), userNewTopentity.getFloorActType(), userNewTopentity.getFloorActId(), userNewTopentity.getFloorGoodsId(), userNewTopentity.getFloorBusinessType(), userNewTopentity.getFloorGoodsUrl(), userNewTopentity.getFloorManrongActivity(), userNewTopentity.getFloorManrongType(), userNewTopentity.getFloorId(), userNewTopentity.getFloorBuriedPointData(), userNewTopentity.getFloorPosition(), userNewTopentity.getFloorDataType(), userNewTopentity.getFloorMaterialId(), userNewTopentity.getFloorTemplateType(), userNewTopentity.getFloorTraceId(), userNewTopentity.getFloorBatchId(), userNewTopentity.getFloorAlgorithmType());
    }

    public void gotoWebView(String str) {
        IntentManager.gotoWebViewActivity(this.activityContext, str, "");
    }

    public void gotoLogin() {
        this.activityContext.startActivity(new Intent(this.activityContext, LoginBindActivity.class));
    }

    private void resetUnloginUI() {
        this.mobileType.setVisibility(8);
        this.taocanTextView1.setText("我的套餐");
        this.taocanTextView2.setText("-");
        this.taocanLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$oV2uSr-v_hIhGKaUBLX32Kny-yU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$resetUnloginUI$11(UserAHeaderVH.this, view);
            }
        });
        this.taocandscTextView_kd_1.setText("宽带");
        this.taocandscTextView_kd_2.setText("--");
        this.taocandscTextView_kd_3.setVisibility(8);
        this.taocandscLayout_kd.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$TF9TyEdl-f6Y8EUmr73slw679C0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$resetUnloginUI$12(view);
            }
        });
        this.taocandscTextView_fk_1.setText("副卡");
        this.taocandscTextView_fk_2.setText("--");
        this.taocandscTextView_fk_3.setVisibility(8);
        this.taocandscLayout_fk.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$E7NuiQIye2JVCniZ-4Wuxaule8M
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$resetUnloginUI$13(view);
            }
        });
        this.taocandscTextView_hy_1.setText("合约");
        this.taocandscTextView_hy_2.setText("--");
        this.taocandscImageView_hy_3.setVisibility(8);
        this.taocandscLayout_hy.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$6AQ1k21nZbNJRTDZFInZOOVjudA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$resetUnloginUI$14(view);
            }
        });
        this.taocandscTextView_yp_1.setText("云盘");
        this.taocandscTextView_yp_2.setText("--");
        this.taocandscImageView_yp_3.setVisibility(8);
        this.taocandscLayout_yp.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$3iwm5-IL33OGlw3X9Cee0oPDyX4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$resetUnloginUI$15(view);
            }
        });
        this.bottomCardLayout.setVisibility(8);
        this.taocanlayout2.setVisibility(8);
        this.taocanLayout3.setVisibility(8);
    }

    public static /* synthetic */ void lambda$resetUnloginUI$11(UserAHeaderVH userAHeaderVH, View view) {
        if (App.hasLogined()) {
            return;
        }
        userAHeaderVH.gotoLogin();
    }

    private void setCardTextColor(TextView textView, String str) {
        if (ZhengzeUtils.isHasNum(str)) {
            textView.setTextColor(-13421773);
        } else {
            textView.setTextColor(-6710887);
        }
    }

    private SpannableStringBuilder getNewCardText(String str, String str2, String str3) {
        SpannableString spannableString;
        SpannableString spannableString2;
        SpannableString spannableString3;
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        try {
            spannableString = new SpannableString(str);
            spannableString.setSpan(new ForegroundColorSpan(-13421773), 0, str.length(), 33);
            spannableString2 = new SpannableString(str2);
            spannableString2.setSpan(new ForegroundColorSpan(-6710887), 0, str2.length(), 33);
            spannableString3 = new SpannableString(str3);
            spannableString3.setSpan(new ForegroundColorSpan(-13421773), 0, str3.length(), 33);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!"/0".equals(str2) && !"/".equals(str2)) {
            spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString2).append((CharSequence) spannableString3);
            return spannableStringBuilder;
        }
        spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString3);
        return spannableStringBuilder;
    }

    private SpannableStringBuilder getCloudText(String str, String str2, String str3) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        try {
            if (!TextUtils.isEmpty(str2)) {
                if (!TextUtils.isEmpty(str)) {
                    str2 = "/" + str2;
                    SpannableString spannableString = new SpannableString(str);
                    spannableString.setSpan(new ForegroundColorSpan(-13421773), 0, str.length(), 33);
                    spannableStringBuilder.append((CharSequence) spannableString);
                } else {
                    SpannableString spannableString2 = new SpannableString("剩余空间");
                    spannableString2.setSpan(new ForegroundColorSpan(-6710887), 0, 4, 33);
                    spannableStringBuilder.append((CharSequence) spannableString2);
                }
                SpannableString spannableString3 = new SpannableString(str2);
                spannableString3.setSpan(new ForegroundColorSpan(-6710887), 0, str2.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString3);
            } else {
                SpannableString spannableString4 = new SpannableString(str3);
                spannableString4.setSpan(new ForegroundColorSpan(-6710887), 0, str3.length(), 33);
                spannableStringBuilder.append((CharSequence) spannableString4);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return spannableStringBuilder;
    }

    private void initTipsDialog(String str, final String str2, final String str3) {
        this.wenhaoImnage.setVisibility(8);
        try {
            if (Integer.parseInt(str) >= 2) {
                return;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(str3)) {
            return;
        }
        String charSequence = this.taocandscTextView_kd_2.getText().toString();
        if (TextUtils.isEmpty(charSequence) || !charSequence.contains("/")) {
            return;
        }
        this.wenhaoImnage.setVisibility(0);
        this.wenhaoImnage.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserAHeaderVH$UN7RbO8-JoyZHXSO2x-Zbz0VeXE
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                UserAHeaderVH.lambda$initTipsDialog$16(UserAHeaderVH.this, str2, str3, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initTipsDialog$16(UserAHeaderVH userAHeaderVH, String str, String str2, View view) {
        userAHeaderVH.logDJ01("5160102", "", "", "我的套餐说明提示", "");
        CustomDialogManager.showUserDialog(userAHeaderVH.activityContext, str, str2, "", new CustomDialogManager.SimpleCustomeDialogListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserAHeaderVH.13
            @Override // com.sinovatech.unicom.basic.view.CustomDialogManager.SimpleCustomeDialogListener
            public void onClickOk() {
            }

            {
                UserAHeaderVH.this = userAHeaderVH;
            }
        });
    }

    public void setH5LayoutHeight(int i) {
        if (i != 0) {
            this.h5Layout.setVisibility(0);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) this.h5Layout.getLayoutParams();
            layoutParams.height = UIUtils.dip2px2(this.activityContext, i);
            this.h5Layout.setLayoutParams(layoutParams);
        } else {
            this.h5Layout.setVisibility(8);
        }
        logXiaochengxu("设置高度", String.valueOf(i));
    }

    public void logXiaochengxu(String str, String str2) {
        PvCurrencyLogUtils.pvLogUserDJ("S2ndpage1249", str, "", str2, "", "");
    }

    public void logDJ01(String str, String str2, String str3, String str4, String str5) {
        PvCurrencyLogUtils.pvLogUserDJ(str, str5, str4, str2, "", "");
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setCodeId(str).setPageName("我的").setPbName(str4).setStoreyCode(str.substring(0, 3)).build());
    }

    public void logDJ04(String str, String str2, String str3, String str4, String str5, String str6) {
        PvCurrencyLogUtils.pvLogUserDJ(str, str3, str6, str2, str4, "", str5, "");
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setCodeId(str).setPageName("我的").setPbName(str6).setStoreyCode(str.substring(0, 3)).build());
    }

    public void logDJ06(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        PvCurrencyLogUtils.pvLogUserDJ(str, str3, str6, str2, str4, "", str5, "");
        CollectDataEntity.Builder storeyCode = CollectDataEntity.newBuilder().setCodeId(str).setPageName("我的").setPbName(str6).setStoreyCode(str.substring(0, 3));
        storeyCode.setActType(notNull(str7)).setActId(notNull(str8)).setCommodityId(notNull(str9)).setBusinessType(notNull(str10)).setTargetUrl(notNull(str11)).setManrongActivity(notNull(str12)).setManrongType(notNull(str13)).setGoodSid(notNull(str9)).setGoods_onlyid(notNull(str14)).setMaintaining_information(notNull(str15)).setMaintenance_position_code(notNull(str16));
        UnicomCollectManager.getInstance().clickCollect(storeyCode.build());
    }

    private void logDJ07(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16, String str17, String str18, String str19, String str20, String str21, String str22) {
        String str23 = TextUtils.isEmpty(str6) ? "灰卡片" : str6;
        PvCurrencyLogUtils.pvLogUserDJ(str, str3, str23, str2, str4, "", str5, "");
        CollectDataEntity.Builder storeyCode = CollectDataEntity.newBuilder().setCodeId(str).setPageName("我的").setPbName(notNull(str23)).setStoreyCode(notNull(str.substring(0, 3)));
        storeyCode.setActType(notNull(str7)).setActId(notNull(str8)).setCommodityId(notNull(str9)).setBusinessType(notNull(str10)).setTargetUrl(notNull(str11)).setManrongActivity(notNull(str12)).setManrongType(notNull(str13)).setGoodSid(notNull(str9)).setGoods_onlyid(notNull(str14)).setMaintaining_information(notNull(str15)).setMaintenance_position_code(notNull(str16)).setBiz_Type(notNull(str17)).setMaterial_id(notNull(str18)).setTemplate_id(notNull(str19)).setTrace_id(notNull(str20)).setBatch_id(notNull(str21)).setAlgorithm_type(notNull(str22));
        UnicomCollectManager.getInstance().clickCollect(storeyCode.build());
    }

    private String notNull(String str) {
        return "null".equals(str) ? "" : str;
    }
}
