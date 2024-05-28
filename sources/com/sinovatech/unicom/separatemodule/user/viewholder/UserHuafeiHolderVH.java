package com.sinovatech.unicom.separatemodule.user.viewholder;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.p086v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.sinovatech.unicom.basic.boxcenter.CacheDataCenter;
import com.sinovatech.unicom.basic.eventbus.QiandaoEvent2;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.basic.server.LoginManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.EventBusUtils;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemEntity;
import com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder;
import com.sinovatech.unicom.separatemodule.user.UserDataSourceManager;
import com.sinovatech.unicom.separatemodule.user.entity.HuaFeiEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserLightEntity;
import com.sinovatech.unicom.separatemodule.user.entity.UserQuanyiEntity;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUser;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserLightCollect;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.HashMap;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserHuafeiHolderVH extends RVItemViewHolder {
    private LinearLayout huafeiLayout;
    private ImageView imageView;
    private ImageView ivMark1;
    private ImageView ivMark2;
    private ImageView ivMark3;
    private ImageView ivMark4;
    private LinearLayout layout1;
    private LinearLayout layout2;
    private LinearLayout layout3;
    private LinearLayout layout4;
    private ManagerUser managerUser;
    private String remark3;
    private TextView titleText1_1;
    private TextView titleText1_2;
    private TextView titleText1_3;
    private TextView titleText2_1;
    private TextView titleText2_2;
    private TextView titleText2_3;
    private TextView titleText3_1;
    private TextView titleText3_2;
    private TextView titleText3_3;
    private TextView titleText4_1;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$0(View view) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$1(View view) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$2(View view) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$4(View view) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$5(View view) {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$bindData$6(View view) {
    }

    public UserHuafeiHolderVH(Activity activity, View view) {
        super(activity, view);
        this.remark3 = "";
        this.managerUser = new ManagerUser((AppCompatActivity) activity);
        this.titleText1_1 = (TextView) view.findViewById(2131299384);
        this.titleText1_2 = (TextView) view.findViewById(2131299385);
        this.titleText1_3 = (TextView) view.findViewById(2131299386);
        this.ivMark1 = (ImageView) view.findViewById(2131297431);
        this.layout1 = (LinearLayout) view.findViewById(2131299380);
        this.titleText2_1 = (TextView) view.findViewById(2131299387);
        this.titleText2_2 = (TextView) view.findViewById(2131299388);
        this.titleText2_3 = (TextView) view.findViewById(2131299389);
        this.ivMark2 = (ImageView) view.findViewById(2131297432);
        this.layout2 = (LinearLayout) view.findViewById(2131299381);
        this.titleText3_1 = (TextView) view.findViewById(2131299390);
        this.titleText3_2 = (TextView) view.findViewById(2131299391);
        this.titleText3_3 = (TextView) view.findViewById(2131299392);
        this.ivMark3 = (ImageView) view.findViewById(2131297433);
        this.layout3 = (LinearLayout) view.findViewById(2131299382);
        this.imageView = (ImageView) view.findViewById(2131299378);
        this.titleText4_1 = (TextView) view.findViewById(2131299393);
        this.ivMark4 = (ImageView) view.findViewById(2131297434);
        this.layout4 = (LinearLayout) view.findViewById(2131299383);
        this.huafeiLayout = (LinearLayout) view.findViewById(2131297619);
        Typeface createFromAsset = Typeface.createFromAsset(activity.getAssets(), "bebas_regular.otf");
        this.titleText1_1.setTypeface(createFromAsset);
        this.titleText2_1.setTypeface(createFromAsset);
        this.titleText3_1.setTypeface(createFromAsset);
    }

    @Override // com.sinovatech.unicom.separatemodule.templateholder.RVItemViewHolder
    public void bindData(Object obj) {
        if (obj instanceof RVItemEntity) {
            RVItemEntity rVItemEntity = (RVItemEntity) obj;
            if (!rVItemEntity.refresh) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(new UserLightEntity("5030101", "话费优惠"));
                arrayList.add(new UserLightEntity("5030102", "备用金"));
                arrayList.add(new UserLightEntity("5030103", "借钱"));
                arrayList.add(new UserLightEntity("5030104", "进入钱包"));
                ManagerUserLightCollect.getInstance().addCollectData(UserDataSourceManager.HUAFEI, arrayList);
                return;
            }
            rVItemEntity.refresh = false;
            UIUtils.logD("bindData", "header");
            EventBusUtils.post(new QiandaoEvent2(EventBusUtils.EVENT_QIANDAOED));
            String accountType = LoginManager.getAccountType();
            if (!App.hasLogined() || "1".equals(accountType) || "4".equals(accountType)) {
                setVisibility(true);
                this.huafeiLayout.setVisibility(0);
                String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
                if (!TextUtils.isEmpty(CacheDataCenter.getInstance().getQuanyiNew(currentPhoneNumber))) {
                    loadHeaderCard(true, currentPhoneNumber);
                }
                loadHeaderCard(false, currentPhoneNumber);
                if (TextUtils.isEmpty(CacheDataCenter.getInstance().getUserQianBaoData())) {
                    this.titleText1_1.setText("--");
                    this.titleText1_2.setText("剩余话费");
                    this.titleText1_3.setText("自动充");
                    this.titleText2_1.setText("--");
                    this.titleText2_2.setText("存钱");
                    this.titleText2_3.setText("灵活存取");
                    this.titleText3_1.setText(getBigSmallStr2("20万"));
                    this.titleText3_2.setText("借钱");
                    this.titleText3_3.setText("沃分--");
                    this.titleText4_1.setText("进入钱包");
                    this.layout1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$ySyg-bu1vgHqcMuCJm_4mr7KADs
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$bindData$0(view);
                        }
                    });
                    this.layout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$kKuGpaL00fByspmSfdmnrcf8bwM
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$bindData$1(view);
                        }
                    });
                    this.layout3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$wb63hMBoOE6xiUwOFPbb9E2x-Ms
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$bindData$2(view);
                        }
                    });
                    this.layout4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$rcBxWDFE9ZsovthniMnR66CLw70
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            IntentManager.generateIntentAndGo(UserHuafeiHolderVH.this.activityContext, "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/ci-mps-st-web/");
                        }
                    });
                } else {
                    loadWodeqianbao(true);
                }
                if (LoginManager.isKuandaiOrGuhua()) {
                    this.huafeiLayout.setVisibility(8);
                    setVisibility(false);
                } else {
                    this.huafeiLayout.setVisibility(0);
                    setVisibility(true);
                    loadWodeqianbao(false);
                }
            } else {
                setVisibility(false);
                this.huafeiLayout.setVisibility(8);
                this.titleText1_1.setText("--");
                this.titleText1_2.setText("剩余话费");
                this.titleText1_3.setText("自动充");
                this.titleText2_1.setText("--");
                this.titleText2_2.setText("存钱");
                this.titleText2_3.setText("灵活存取");
                this.titleText3_1.setText(getBigSmallStr2("20万"));
                this.titleText3_2.setText("借钱");
                this.titleText3_3.setText("沃分--");
                this.titleText4_1.setText("进入钱包");
                this.layout1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$wN6QkVxNx_NjQBtW3vfNxXwBCZU
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UserHuafeiHolderVH.lambda$bindData$4(view);
                    }
                });
                this.layout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$Mtqs4gTluMuwThfqpYc9JSSczLk
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UserHuafeiHolderVH.lambda$bindData$5(view);
                    }
                });
                this.layout3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$nCJjP_ILW5-5rSAwxJBBEASa4mM
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UserHuafeiHolderVH.lambda$bindData$6(view);
                    }
                });
                this.layout4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$isYbw3ToxbSmTRDno4VTnm35MSo
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        IntentManager.generateIntentAndGo(UserHuafeiHolderVH.this.activityContext, "https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/ci-mps-st-web/");
                    }
                });
            }
            ManagerUserLightCollect.getInstance().addView(UserDataSourceManager.HUAFEI, this.itemView);
        }
    }

    private void loadWodeqianbao(boolean z) {
        this.managerUser.getWoDeQianbao(z).subscribe(new Consumer() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$4XNFxd-HLxxTA-vUri0J6Qj1NQA
            @Override // io.reactivex.functions.Consumer
            public final void accept(Object obj) {
                UserHuafeiHolderVH.this.initCard((HuaFeiEntity) obj);
            }
        }, $$Lambda$Jxp4LOjD5wh7hYvpBAWXzgH0LNY.INSTANCE);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initCard(HuaFeiEntity huaFeiEntity) {
        if (huaFeiEntity != null) {
            HuaFeiEntity.DataEntity data = huaFeiEntity.getData();
            if ("0000".equals(huaFeiEntity.getCode()) && "0".equals(huaFeiEntity.getMyQueryMoneySwitch())) {
                setVisibility(false);
            } else if (data != null) {
                final HuaFeiEntity.DataEntity.RemainPhoneCostEntity remainPhoneCost = data.getRemainPhoneCost();
                final HuaFeiEntity.DataEntity.PhoneCostPayEntity phoneCostPay = data.getPhoneCostPay();
                final HuaFeiEntity.DataEntity.BorrowMoneyEntity borrowMoney = data.getBorrowMoney();
                final HuaFeiEntity.DataEntity.CreditPointEntity creditPoint = data.getCreditPoint();
                final HuaFeiEntity.DataEntity.MyMoneyBagEntity myMoneyBag = data.getMyMoneyBag();
                if (remainPhoneCost != null && "0000".equals(remainPhoneCost.getDatacode())) {
                    this.titleText1_1.setText(remainPhoneCost.getMoney());
                    this.titleText1_2.setText(remainPhoneCost.getTitle());
                    this.titleText1_3.setText(remainPhoneCost.getDesc());
                    if (remainPhoneCost.getBubblePicUrl() != null && !remainPhoneCost.getBubblePicUrl().isEmpty() && remainPhoneCost.getBubbleTag() != null && !remainPhoneCost.getBubbleTag().isEmpty()) {
                        this.ivMark1.setVisibility(0);
                        GlideApp.with(this.activityContext).load(remainPhoneCost.getBubblePicUrl()).into(this.ivMark1);
                        this.ivMark1.setVisibility(0);
                    } else {
                        this.ivMark1.setVisibility(4);
                    }
                    this.layout1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$lW2qQtRI5bOwYDPT9sD9whQzoVQ
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$initCard$8(UserHuafeiHolderVH.this, remainPhoneCost, view);
                        }
                    });
                }
                if (phoneCostPay != null && "0000".equals(phoneCostPay.getDatacode())) {
                    this.titleText2_1.setText(phoneCostPay.getMoney());
                    this.titleText2_2.setText(phoneCostPay.getTitle());
                    this.titleText2_3.setText(phoneCostPay.getDesc());
                    if (phoneCostPay.getBubblePicUrl() != null && !phoneCostPay.getBubblePicUrl().isEmpty() && phoneCostPay.getBubbleTag() != null && !phoneCostPay.getBubbleTag().isEmpty()) {
                        GlideApp.with(this.activityContext).load(phoneCostPay.getBubblePicUrl()).into(this.ivMark2);
                        this.ivMark2.setVisibility(0);
                    } else {
                        this.ivMark2.setVisibility(4);
                    }
                    this.layout2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$rvjG3UWmbJ4Tg7o0LDG2pAAqZ0g
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$initCard$9(UserHuafeiHolderVH.this, phoneCostPay, view);
                        }
                    });
                }
                if (borrowMoney != null && "0000".equals(borrowMoney.getDatacode())) {
                    this.titleText3_1.setText(getBigSmallStr2(borrowMoney.getMoney()));
                    this.titleText3_2.setText(borrowMoney.getTitle());
                    if (borrowMoney.getBubblePicUrl() != null && borrowMoney.getBubbleTag() != null && !borrowMoney.getBubblePicUrl().isEmpty() && !borrowMoney.getBubbleTag().isEmpty()) {
                        this.ivMark3.setVisibility(0);
                        GlideApp.with(this.activityContext).load(borrowMoney.getBubblePicUrl()).into(this.ivMark3);
                    } else {
                        this.ivMark3.setVisibility(4);
                    }
                    this.titleText3_1.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$BWPLLcBXoTjqdnuT6L9mqoAN_Cc
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$initCard$10(UserHuafeiHolderVH.this, borrowMoney, view);
                        }
                    });
                    this.titleText3_2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$gIzXhunzG9wyUJbssNgpKnBy6b4
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.this.titleText3_1.performClick();
                        }
                    });
                    this.layout3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$FwtND80lpuyjbDmIJ2D_Eeyb83g
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.this.titleText3_1.performClick();
                        }
                    });
                }
                if (creditPoint != null && "0000".equals(creditPoint.getDatacode())) {
                    if (TextUtils.isEmpty(creditPoint.getPoint()) || "null".equals(creditPoint.getPoint())) {
                        this.titleText3_3.setText(creditPoint.getTitle());
                    } else {
                        TextView textView = this.titleText3_3;
                        textView.setText(creditPoint.getTitle() + " " + creditPoint.getPoint());
                    }
                    this.titleText3_3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$UdWWv80C5XVHxtP-V_DA7v0NUXU
                        @Override // android.view.View.OnClickListener
                        public final void onClick(View view) {
                            UserHuafeiHolderVH.lambda$initCard$13(UserHuafeiHolderVH.this, creditPoint, view);
                        }
                    });
                }
                if (myMoneyBag == null || !"0000".equals(myMoneyBag.getDatacode())) {
                    return;
                }
                GlideApp.with(this.activityContext).load(myMoneyBag.getPicUrl()).placeholder(2131232547).error(2131232547).into(this.imageView);
                this.titleText4_1.setText(myMoneyBag.getTitle());
                if (myMoneyBag.getBubblePicUrl() != null && myMoneyBag.getBubbleTag() != null && !myMoneyBag.getBubblePicUrl().isEmpty() && !myMoneyBag.getBubbleTag().isEmpty()) {
                    this.ivMark4.setVisibility(0);
                    GlideApp.with(this.activityContext).load(myMoneyBag.getBubblePicUrl()).into(this.ivMark4);
                } else {
                    this.ivMark4.setVisibility(4);
                }
                this.layout4.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.-$$Lambda$UserHuafeiHolderVH$ioo0FO8e-V5Y6x8IzVvqIv31o8E
                    @Override // android.view.View.OnClickListener
                    public final void onClick(View view) {
                        UserHuafeiHolderVH.lambda$initCard$14(UserHuafeiHolderVH.this, myMoneyBag, view);
                    }
                });
            }
        }
    }

    public static /* synthetic */ void lambda$initCard$8(UserHuafeiHolderVH userHuafeiHolderVH, HuaFeiEntity.DataEntity.RemainPhoneCostEntity remainPhoneCostEntity, View view) {
        if (!App.hasLogined()) {
            remainPhoneCostEntity.setLink("");
        }
        if (remainPhoneCostEntity.getBubbleTag() != null && !remainPhoneCostEntity.getBubbleTag().isEmpty()) {
            userHuafeiHolderVH.uploadClickEvent("syhf", remainPhoneCostEntity.getBubbleTag());
            userHuafeiHolderVH.ivMark1.setVisibility(4);
        }
        IntentManager.generateIntentAndGo(userHuafeiHolderVH.activityContext, remainPhoneCostEntity.getLink());
        userHuafeiHolderVH.logDJ03("5030101", remainPhoneCostEntity.getLink(), userHuafeiHolderVH.remark3, remainPhoneCostEntity.getTitle(), "我的页面财富楼层1");
    }

    public static /* synthetic */ void lambda$initCard$9(UserHuafeiHolderVH userHuafeiHolderVH, HuaFeiEntity.DataEntity.PhoneCostPayEntity phoneCostPayEntity, View view) {
        if (!App.hasLogined()) {
            phoneCostPayEntity.setLink("");
        }
        if (App.hasLogined() && TextUtils.isEmpty(phoneCostPayEntity.getLink())) {
            phoneCostPayEntity.setLink("https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/itf/fixcs/hfb/home?source=app_sjyyt&c=SWDZCQ01");
        }
        if (phoneCostPayEntity.getBubbleTag() != null && !phoneCostPayEntity.getBubbleTag().isEmpty()) {
            userHuafeiHolderVH.ivMark2.setVisibility(4);
            userHuafeiHolderVH.uploadClickEvent("hfb", phoneCostPayEntity.getBubbleTag());
        }
        IntentManager.generateIntentAndGo(userHuafeiHolderVH.activityContext, phoneCostPayEntity.getLink());
        userHuafeiHolderVH.logDJ03("5030102", phoneCostPayEntity.getLink(), userHuafeiHolderVH.remark3, phoneCostPayEntity.getTitle(), "我的页面财富楼层2");
    }

    public static /* synthetic */ void lambda$initCard$10(UserHuafeiHolderVH userHuafeiHolderVH, HuaFeiEntity.DataEntity.BorrowMoneyEntity borrowMoneyEntity, View view) {
        if (!App.hasLogined()) {
            borrowMoneyEntity.setLink("");
        }
        if (borrowMoneyEntity.getBubbleTag() != null && !borrowMoneyEntity.getBubbleTag().isEmpty()) {
            userHuafeiHolderVH.ivMark3.setVisibility(4);
            userHuafeiHolderVH.uploadClickEvent("jq", borrowMoneyEntity.getBubbleTag());
        }
        IntentManager.generateIntentAndGo(userHuafeiHolderVH.activityContext, borrowMoneyEntity.getLink());
        userHuafeiHolderVH.logDJ03("5030103", borrowMoneyEntity.getLink(), userHuafeiHolderVH.remark3, borrowMoneyEntity.getTitle(), "我的页面财富楼层3");
    }

    public static /* synthetic */ void lambda$initCard$13(UserHuafeiHolderVH userHuafeiHolderVH, HuaFeiEntity.DataEntity.CreditPointEntity creditPointEntity, View view) {
        if (!App.hasLogined()) {
            creditPointEntity.setLink("");
        }
        if (creditPointEntity.getBubbleTag() != null && !creditPointEntity.getBubbleTag().isEmpty()) {
            userHuafeiHolderVH.ivMark3.setVisibility(4);
            userHuafeiHolderVH.uploadClickEvent("wxyf", creditPointEntity.getBubbleTag());
        }
        IntentManager.generateIntentAndGo(userHuafeiHolderVH.activityContext, creditPointEntity.getLink());
        userHuafeiHolderVH.logDJ03("5030103", creditPointEntity.getLink(), userHuafeiHolderVH.remark3, creditPointEntity.getTitle(), "我的页面财富楼层3");
    }

    public static /* synthetic */ void lambda$initCard$14(UserHuafeiHolderVH userHuafeiHolderVH, HuaFeiEntity.DataEntity.MyMoneyBagEntity myMoneyBagEntity, View view) {
        if (!App.hasLogined()) {
            myMoneyBagEntity.setLink("");
        }
        if (App.hasLogined() && TextUtils.isEmpty(myMoneyBagEntity.getLink())) {
            myMoneyBagEntity.setLink("https://m.client.10010.com/mobileService/openPlatform/openPlatLineNew.htm?to_url=https://epay.10010.com/ci-mps-st-web/");
        }
        if (myMoneyBagEntity.getBubbleTag() != null && !myMoneyBagEntity.getBubbleTag().isEmpty()) {
            userHuafeiHolderVH.ivMark4.setVisibility(4);
            userHuafeiHolderVH.uploadClickEvent("qb", myMoneyBagEntity.getBubbleTag());
        }
        IntentManager.generateIntentAndGo(userHuafeiHolderVH.activityContext, myMoneyBagEntity.getLink());
        userHuafeiHolderVH.logDJ03("5030104", myMoneyBagEntity.getLink(), userHuafeiHolderVH.remark3, myMoneyBagEntity.getTitle(), "我的页面财富楼层4");
    }

    private SpannableStringBuilder getBigSmallStr2(String str) {
        try {
            if (!str.contains("万")) {
                return new SpannableStringBuilder(str);
            }
            String substring = str.substring(0, str.length() - 1);
            String substring2 = str.substring(str.length() - 1);
            Integer.parseInt(substring);
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            if (TextUtils.isEmpty(substring)) {
                return null;
            }
            SpannableString spannableString = new SpannableString(substring);
            spannableString.setSpan(new StyleSpan(0), 0, substring.length(), 33);
            if (TextUtils.isEmpty(substring2)) {
                return spannableStringBuilder.append((CharSequence) spannableString);
            }
            SpannableString spannableString2 = new SpannableString(substring2);
            spannableString2.setSpan(new RelativeSizeSpan(0.6f), 0, substring2.length(), 34);
            spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString2);
            return spannableStringBuilder;
        } catch (Exception e) {
            e.printStackTrace();
            return new SpannableStringBuilder(str);
        }
    }

    private void logDJ03(String str, String str2, String str3, String str4, String str5) {
        PvCurrencyLogUtils.pvLogUserDJ(str, str5, str4, str2, str3, "");
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setCodeId(str).setPageName("我的").setPbName(str4).setStoreyCode(str.substring(0, 3)).build());
    }

    private void uploadClickEvent(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("position", str);
        hashMap.put("bubbleTag", str2);
        hashMap.put("version", this.activityContext.getResources().getString(2131886969));
        App.getAsyncHttpClient().post(URLSet.qianBaoUpLoadEvent(), hashMap, new AsyncHttpResponseHandler() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserHuafeiHolderVH.1
            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onSuccess(int i, String str3) {
                super.onSuccess(i, str3);
                UIUtils.logD("unicom", "财富楼层点击上报成功");
            }

            @Override // com.loopj.android.http.AsyncHttpResponseHandler
            public void onFailure(Throwable th, String str3) {
                super.onFailure(th, str3);
                UIUtils.logD("unicom", "财富楼层点击上报失败");
            }
        });
    }

    private void loadHeaderCard(boolean z, String str) {
        this.managerUser.getUserHeaderCard(z, str).subscribe(new Observer<UserQuanyiEntity>() { // from class: com.sinovatech.unicom.separatemodule.user.viewholder.UserHuafeiHolderVH.2
            @Override // io.reactivex.Observer
            public void onComplete() {
            }

            @Override // io.reactivex.Observer
            public void onError(@NonNull Throwable th) {
            }

            @Override // io.reactivex.Observer
            public void onSubscribe(@NonNull Disposable disposable) {
            }

            @Override // io.reactivex.Observer
            public void onNext(@NonNull UserQuanyiEntity userQuanyiEntity) {
                UserQuanyiEntity.MemberBean member;
                if (!"0000".equals(userQuanyiEntity.getCode()) || (member = userQuanyiEntity.getMember()) == null) {
                    return;
                }
                String vipLevel = member.getVipLevel();
                if ("1".equals(vipLevel)) {
                    UserHuafeiHolderVH.this.remark3 = "青铜会员";
                }
                if ("2".equals(vipLevel)) {
                    UserHuafeiHolderVH.this.remark3 = "白银会员";
                }
                if ("3".equals(vipLevel)) {
                    UserHuafeiHolderVH.this.remark3 = "黄金会员";
                }
                if ("4".equals(vipLevel)) {
                    UserHuafeiHolderVH.this.remark3 = "钻石会员";
                }
                if ("5".equals(vipLevel)) {
                    UserHuafeiHolderVH.this.remark3 = "至尊会员";
                }
                if ("6".equals(member.getPlusflag())) {
                    UserHuafeiHolderVH.this.remark3 = "plus会员";
                }
            }
        });
    }
}
