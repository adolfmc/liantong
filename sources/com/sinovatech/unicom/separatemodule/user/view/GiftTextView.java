package com.sinovatech.unicom.separatemodule.user.view;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.avoidResult.AvoidOnResult;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectDataEntity;
import com.sinovatech.unicom.separatemodule.collect.UnicomCollectManager;
import com.sinovatech.unicom.separatemodule.user.entity.UserLibaoEntity;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class GiftTextView extends FrameLayout {
    private ImageView iconImage;
    private TextView numText;
    private ImageView redPointImage;
    private TextView titleText;

    public GiftTextView(@NonNull Context context) {
        super(context);
        initView(context);
    }

    public GiftTextView(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        initView(context);
    }

    private void initView(Context context) {
        LayoutInflater.from(context).inflate(2131493315, this);
        this.numText = (TextView) findViewById(2131299405);
        this.titleText = (TextView) findViewById(2131299406);
        this.redPointImage = (ImageView) findViewById(2131297431);
        this.iconImage = (ImageView) findViewById(2131299456);
    }

    public void setData(int i, UserLibaoEntity.ResultDTO resultDTO, String str) {
        String telVoucherData;
        String telVoucherNewly;
        String telVoucherUrl;
        String str2;
        String str3;
        final String str4;
        String str5;
        String str6 = "";
        switch (i) {
            case 1:
                String telVoucherTitle = resultDTO.getTelVoucherTitle();
                telVoucherData = resultDTO.getTelVoucherData();
                String telVoucherUnit = resultDTO.getTelVoucherUnit();
                telVoucherNewly = resultDTO.getTelVoucherNewly();
                telVoucherUrl = resultDTO.getTelVoucherUrl();
                str2 = "5020101";
                str3 = "我的页面礼包-坑位1";
                str4 = telVoucherTitle;
                str5 = telVoucherUnit;
                break;
            case 2:
                String trafficPacketTitle = resultDTO.getTrafficPacketTitle();
                telVoucherData = resultDTO.getTrafficPacketData();
                String trafficPacketUnit = resultDTO.getTrafficPacketUnit();
                telVoucherNewly = resultDTO.getTrafficPacketNewly();
                telVoucherUrl = resultDTO.getTrafficPacketUrl();
                str2 = "5020102";
                str3 = "我的页面礼包-坑位2";
                str4 = trafficPacketTitle;
                str5 = trafficPacketUnit;
                break;
            case 3:
                String voicePackageTitle = resultDTO.getVoicePackageTitle();
                telVoucherData = resultDTO.getVoicePackageData();
                String voicePackageUnit = resultDTO.getVoicePackageUnit();
                telVoucherNewly = resultDTO.getVoicePackageNewly();
                telVoucherUrl = resultDTO.getVoicePackageUrl();
                str2 = "5020103";
                str3 = "我的页面礼包-坑位3";
                str4 = voicePackageTitle;
                str5 = voicePackageUnit;
                break;
            default:
                String allGiftTitle = resultDTO.getAllGiftTitle();
                telVoucherNewly = resultDTO.getAllGiftNewly();
                String allGiftUrl = resultDTO.getAllGiftUrl();
                str2 = "5020104";
                str3 = "我的页面礼包-全部礼包";
                telVoucherData = "";
                str5 = "";
                str4 = allGiftTitle;
                str6 = resultDTO.getAllGiftIconUrl();
                telVoucherUrl = allGiftUrl;
                break;
        }
        this.titleText.setText(str4);
        if (i == 4) {
            this.numText.setVisibility(4);
            this.iconImage.setVisibility(0);
            GlideApp.with(getContext()).load(str6).into(this.iconImage);
        } else {
            this.numText.setVisibility(0);
            this.iconImage.setVisibility(8);
            this.numText.setText(getBigSmallStr2(telVoucherData, str5));
        }
        if ("1".equals(telVoucherNewly) && App.hasLogined() && "1".equals(str)) {
            this.redPointImage.setVisibility(0);
        } else {
            this.redPointImage.setVisibility(4);
        }
        final String str7 = str2;
        final String str8 = telVoucherUrl;
        final String str9 = str3;
        setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.GiftTextView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                GiftTextView.this.logDJ(str7, str8, str4, str9);
                GiftTextView.this.gotoWebView(str8);
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public void setTypeface(Typeface typeface) {
        this.numText.setTypeface(typeface);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void gotoWebView(String str) {
        try {
            if (!App.hasLogined()) {
                new AvoidOnResult((Activity) getContext()).startForResult(LoginBindActivity.class, new AvoidOnResult.Callback() { // from class: com.sinovatech.unicom.separatemodule.user.view.GiftTextView.2
                    @Override // com.sinovatech.unicom.common.avoidResult.AvoidOnResult.Callback
                    public void onActivityResult(int i, Intent intent) {
                        if (App.hasLogined()) {
                            GiftTextView.this.performClick();
                        }
                    }
                });
            } else {
                IntentManager.gotoWebViewActivity((Activity) getContext(), str, "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private SpannableStringBuilder getBigSmallStr2(String str, String str2) {
        try {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            SpannableString spannableString = new SpannableString(str);
            spannableString.setSpan(new StyleSpan(0), 0, str.length(), 33);
            if (!TextUtils.isEmpty(str2) && !"-".equals(str2)) {
                SpannableString spannableString2 = new SpannableString(str2);
                spannableString2.setSpan(new RelativeSizeSpan(0.6f), 0, str2.length(), 34);
                spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString2);
                return spannableStringBuilder;
            }
            return spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) str2);
        } catch (Exception e) {
            e.printStackTrace();
            return new SpannableStringBuilder(str + str2);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logDJ(String str, String str2, String str3, String str4) {
        PvCurrencyLogUtils.pvLogUserDJ(str, str4, str3, str2, "", "");
        UnicomCollectManager.getInstance().clickCollect(CollectDataEntity.newBuilder().setCodeId(str).setPageName("我的").setPbName(str3).setStoreyCode(str.substring(0, 3)).build());
    }
}
