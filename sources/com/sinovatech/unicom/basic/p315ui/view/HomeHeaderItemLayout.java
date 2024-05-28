package com.sinovatech.unicom.basic.p315ui.view;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.text.style.ReplacementSpan;
import android.text.style.StyleSpan;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.URLUtil;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p314po.HeaderChildEntity;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.p315ui.activity.MainActivity;
import com.sinovatech.unicom.basic.p315ui.home.manager.UnicomHomeLogUtils;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.LanguageUtil;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.p318ui.App;
import com.sinovatech.unicom.p318ui.C9718R;
import com.sinovatech.unicom.separatemodule.Log.PvCurrencyLogUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

/* renamed from: com.sinovatech.unicom.basic.ui.view.HomeHeaderItemLayout */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HomeHeaderItemLayout extends RelativeLayout {
    private MainActivity activityContext;
    TextView contentTv;
    SimpleDateFormat formatter;
    LinearLayout layout;
    TextView remianTv;
    TextView unitTv;

    public HomeHeaderItemLayout(Context context) {
        super(context);
        this.formatter = new SimpleDateFormat(JtDateUtil.dateFormatYM);
        this.activityContext = (MainActivity) context;
        init(context);
    }

    public HomeHeaderItemLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.formatter = new SimpleDateFormat(JtDateUtil.dateFormatYM);
        this.activityContext = (MainActivity) context;
        context.obtainStyledAttributes(attributeSet, C9718R.styleable.HomeHeaderItemLayout);
        init(context);
    }

    private void init(Context context) {
        this.layout = (LinearLayout) LayoutInflater.from(context).inflate(2131493170, (ViewGroup) this, false);
        this.layout.setLayoutParams(this.layout.getLayoutParams());
        this.remianTv = (TextView) this.layout.findViewById(2131297146);
        this.contentTv = (TextView) this.layout.findViewById(2131297147);
        this.unitTv = (TextView) this.layout.findViewById(2131297148);
        addView(this.layout);
    }

    public void setData(final HeaderChildEntity headerChildEntity, final int i, Typeface typeface) {
        boolean z;
        try {
            boolean z2 = false;
            if (App.hasLogined()) {
                this.contentTv.setText(headerChildEntity.getNumber());
                this.unitTv.setText(TextUtils.isEmpty(headerChildEntity.getUnit()) ? "" : headerChildEntity.getUnit());
                try {
                    z = headerChildEntity.getNumber().matches("^[\\u4e00-\\u9fa5]+$");
                } catch (Exception e) {
                    MsLogUtil.m7978e(e.getMessage());
                    z = false;
                }
                if (z) {
                    this.contentTv.setTextSize(1, 19.0f);
                    this.contentTv.setTypeface(Typeface.defaultFromStyle(1));
                } else {
                    this.contentTv.setTypeface(typeface);
                    this.contentTv.setTextSize(1, 21.0f);
                }
            } else {
                this.contentTv.setTypeface(typeface);
                this.contentTv.setTextSize(1, 21.0f);
                this.contentTv.setText(headerChildEntity.getNumber());
                this.unitTv.setText("");
            }
            this.layout.setVisibility(0);
            this.remianTv.setText(LanguageUtil.getInstance().getText(headerChildEntity.getRemianTitle(), this.remianTv, true));
            this.remianTv.setGravity(49);
            if ("1".equals(headerChildEntity.getIsWarn())) {
                this.contentTv.setTextColor(Color.parseColor("#E60327"));
                this.unitTv.setTextColor(Color.parseColor("#E60327"));
                this.remianTv.setTextColor(Color.parseColor("#E60327"));
            } else {
                this.contentTv.setTextColor(Color.parseColor("#333333"));
                this.unitTv.setTextColor(Color.parseColor("#333333"));
                this.remianTv.setTextColor(Color.parseColor("#666666"));
            }
            if ("2".equals(headerChildEntity.getIsWarn())) {
                if (!TextUtils.isEmpty(headerChildEntity.getPointUpdateTimeStamp())) {
                    String string = App.getSharePreferenceUtil().getString("PointUpdateTimeStamp");
                    if (!TextUtils.isEmpty(string) && i == 4) {
                        App.getSharePreferenceUtil().putString("PointUpdateTimeStamp" + i, string);
                    }
                    if (!headerChildEntity.getPointUpdateTimeStamp().equals(App.getSharePreferenceUtil().getString("PointUpdateTimeStamp" + i))) {
                        z2 = true;
                    }
                }
                if (!z2) {
                    this.contentTv.setTextColor(-1703896);
                } else {
                    this.contentTv.setTextColor(-13355980);
                }
            }
            this.layout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.view.HomeHeaderItemLayout.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (!App.hasLogined()) {
                        String unloginJumpUrl = HomeHeaderItemLayout.this.getUnloginJumpUrl(headerChildEntity.getRemianTitle());
                        if (TextUtils.isEmpty(unloginJumpUrl)) {
                            HomeHeaderItemLayout.this.activityContext.startActivity(new Intent(HomeHeaderItemLayout.this.activityContext, LoginBindActivity.class));
                            PvCurrencyLogUtils.pvLogMainDJ("1020100", "", "", "", "首页-背景-账号信息");
                        } else {
                            IntentManager.generateIntentAndGo(HomeHeaderItemLayout.this.activityContext, unloginJumpUrl, headerChildEntity.getRemianTitle(), false, "get");
                            PvCurrencyLogUtils.pvLogMainDJ("1020100", unloginJumpUrl, "", "", "首页-背景-账号信息");
                        }
                        UnicomHomeLogUtils.getInstance().clickLog("1020100", "首页-背景-账号信息");
                        NBSActionInstrumentation.onClickEventExit();
                        return;
                    }
                    if (!TextUtils.isEmpty(headerChildEntity.getUrl())) {
                        IntentManager.generateIntentAndGo(HomeHeaderItemLayout.this.activityContext, headerChildEntity.getUrl(), headerChildEntity.getRemianTitle(), true, "get");
                        try {
                            PvCurrencyLogUtils.pvLogMainDJ(PvCurrencyLogUtils.getPostion("10201", i), headerChildEntity.getUrl() + "", HomeHeaderItemLayout.this.contentTv.getText().toString() + "", headerChildEntity.getRemianTitle() + "", "余量展示位置");
                            UnicomHomeLogUtils.getInstance().clickLog(PvCurrencyLogUtils.getPostion("10201", i), headerChildEntity.getRemianTitle());
                        } catch (Exception e2) {
                            MsLogUtil.m7978e(e2.getMessage());
                        }
                        if ("2".equals(headerChildEntity.getIsWarn())) {
                            App.getSharePreferenceUtil().putString("HomecreditTime", HomeHeaderItemLayout.this.formatter.format(new Date()));
                            if (!TextUtils.isEmpty(headerChildEntity.getPointUpdateTimeStamp())) {
                                App.getSharePreferenceUtil().putString("PointUpdateTimeStamp" + i, headerChildEntity.getPointUpdateTimeStamp());
                            } else {
                                App.getSharePreferenceUtil().putString("PointUpdateTimeStamp" + i, "");
                            }
                        }
                        if ("可用积分".equals(headerChildEntity.getRemianTitle())) {
                            App.getSharePreferenceUtil().putString("PointUsefulRedpoint", HomeHeaderItemLayout.this.activityContext.getResources().getString(2131886969));
                        }
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private SpannableStringBuilder getBigSmallStr(String str, String str2, float f) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(0), 0, str.length(), 33);
        if (TextUtils.isEmpty(str2)) {
            return spannableStringBuilder.append((CharSequence) spannableString);
        }
        SpannableString spannableString2 = new SpannableString(str2);
        spannableString2.setSpan(new RelativeSizeSpan(f), 0, str2.length(), 34);
        spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString2);
        return spannableStringBuilder;
    }

    private SpannableStringBuilder getBigSmallStr2(String str, String str2) {
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        SpannableString spannableString = new SpannableString(str);
        spannableString.setSpan(new StyleSpan(0), 0, str.length(), 33);
        if (TextUtils.isEmpty(str2)) {
            return spannableStringBuilder.append((CharSequence) spannableString);
        }
        SpannableString spannableString2 = new SpannableString(str2);
        spannableString2.setSpan(new CustomVerticalCenterSpan(13), 0, str2.length(), 34);
        spannableStringBuilder.append((CharSequence) spannableString).append((CharSequence) spannableString2);
        return spannableStringBuilder;
    }

    private int getColorAlpha(int i) {
        int pow = (int) Math.pow(16.0d, 6.0d);
        return (i % pow) + (pow * 200);
    }

    /* renamed from: com.sinovatech.unicom.basic.ui.view.HomeHeaderItemLayout$CustomVerticalCenterSpan */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public class CustomVerticalCenterSpan extends ReplacementSpan {
        private int fontSizeSp;

        public CustomVerticalCenterSpan(int i) {
            this.fontSizeSp = i;
        }

        @Override // android.text.style.ReplacementSpan
        public int getSize(Paint paint, CharSequence charSequence, int i, int i2, Paint.FontMetricsInt fontMetricsInt) {
            return (int) getCustomTextPaint(paint).measureText(charSequence.subSequence(i, i2).toString());
        }

        @Override // android.text.style.ReplacementSpan
        public void draw(Canvas canvas, CharSequence charSequence, int i, int i2, float f, int i3, int i4, int i5, Paint paint) {
            CharSequence subSequence = charSequence.subSequence(i, i2);
            TextPaint customTextPaint = getCustomTextPaint(paint);
            Paint.FontMetricsInt fontMetricsInt = customTextPaint.getFontMetricsInt();
            canvas.drawText(subSequence.toString(), f, i4 - (((((fontMetricsInt.descent + i4) + i4) + fontMetricsInt.ascent) / 2) - ((i5 + i3) / 2)), customTextPaint);
        }

        private TextPaint getCustomTextPaint(Paint paint) {
            TextPaint textPaint = new TextPaint(paint);
            textPaint.setTextSize(UIUtils.sp2px(this.fontSizeSp));
            return textPaint;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getUnloginJumpUrl(String str) {
        char c;
        String string;
        int hashCode = str.hashCode();
        if (hashCode == 648925886) {
            if (str.equals("剩余流量")) {
                c = 1;
            }
            c = 65535;
        } else if (hashCode == 649167628) {
            if (str.equals("剩余话费")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode != 649170870) {
            if (hashCode == 669930192 && str.equals("可用积分")) {
                c = 3;
            }
            c = 65535;
        } else {
            if (str.equals("剩余语音")) {
                c = 2;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                string = App.getSharePreferenceUtil().getString("unicom_unlogin_freeurl");
                break;
            case 1:
                string = App.getSharePreferenceUtil().getString("unicom_unlogin_flowurl");
                break;
            case 2:
                string = App.getSharePreferenceUtil().getString("unicom_unlogin_voiceurl");
                break;
            case 3:
                string = App.getSharePreferenceUtil().getString("unicom_unlogin_integral");
                break;
            default:
                string = "";
                break;
        }
        return (TextUtils.isEmpty(string) || URLUtil.isNetworkUrl(string)) ? string : "";
    }
}
