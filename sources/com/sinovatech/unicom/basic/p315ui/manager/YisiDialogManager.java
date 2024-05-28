package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.common.URLSet;

/* renamed from: com.sinovatech.unicom.basic.ui.manager.YisiDialogManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class YisiDialogManager {
    private Activity activity;

    public YisiDialogManager(Activity activity) {
        this.activity = activity;
    }

    public void showYinSi(final ConfigManager configManager) {
        try {
            final Dialog dialog = new Dialog(SoulPermission.getInstance().getTopActivity(), 2131952273);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.getWindow().setGravity(17);
            View inflate = this.activity.getLayoutInflater().inflate(2131493030, (ViewGroup) null);
            final TextView textView = (TextView) inflate.findViewById(2131296869);
            TextView textView2 = (TextView) inflate.findViewById(2131296873);
            TextView textView3 = (TextView) inflate.findViewById(2131296864);
            textView.setText(Html.fromHtml("我们非常重视隐私和个人信息保护,请您先认真阅读《<font color='#e60028'><a href='yinsi'>中国联通APP用户隐私政策</a></font>》、《<font color='#e60028'><a href='xieyi'>中国联通APP用户服务协议</a></font>》以及《<font color='#e60028'><a href='liantongyinsi'>中国联通用户隐私政策</a></font>》的全部条款,接受全部条款后再开始使用我们的服务。"));
            textView.setHighlightColor(Color.parseColor("#00000000"));
            textView.setMovementMethod(LinkMovementMethod.getInstance());
            CharSequence text = textView.getText();
            if (text instanceof Spannable) {
                int length = text.length();
                Spannable spannable = (Spannable) textView.getText();
                URLSpan[] uRLSpanArr = (URLSpan[]) spannable.getSpans(0, length, URLSpan.class);
                SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder(text);
                spannableStringBuilder.clearSpans();
                for (final URLSpan uRLSpan : uRLSpanArr) {
                    spannableStringBuilder.setSpan(new ClickableSpan() { // from class: com.sinovatech.unicom.basic.ui.manager.YisiDialogManager.1
                        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
                        public void updateDrawState(TextPaint textPaint) {
                            super.updateDrawState(textPaint);
                            textPaint.setColor(Color.parseColor("#ffe60028"));
                            textPaint.setUnderlineText(false);
                        }

                        @Override // android.text.style.ClickableSpan
                        public void onClick(View view) {
                            NBSActionInstrumentation.onClickEventEnter(view, this);
                            if ("yinsi".equals(uRLSpan.getURL())) {
                                YisiDialogManager.this.toXieyi(URLSet.getUserPrivacy(), "隐私政策");
                            } else if ("xieyi".equals(uRLSpan.getURL())) {
                                YisiDialogManager.this.toXieyi(URLSet.getUserserver(), "用户服务协议");
                            } else if ("liantongyinsi".equals(uRLSpan.getURL())) {
                                YisiDialogManager.this.toXieyi(URLSet.getunicom_yinsizhengce(), "中国联通用户隐私政策》");
                            }
                            NBSActionInstrumentation.onClickEventExit();
                        }
                    }, spannable.getSpanStart(uRLSpan), spannable.getSpanEnd(uRLSpan), 33);
                }
                textView.setText(spannableStringBuilder);
                textView.setFocusable(false);
                textView.setFocusableInTouchMode(false);
                textView.setOnLongClickListener(new View.OnLongClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.YisiDialogManager.2
                    @Override // android.view.View.OnLongClickListener
                    public boolean onLongClick(View view) {
                        NBSActionInstrumentation.onLongClickEventEnter(view, this);
                        textView.setVisibility(0);
                        NBSActionInstrumentation.onLongClickEventExit();
                        return true;
                    }
                });
            }
            textView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.YisiDialogManager.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    configManager.setXieyiClickTime(configManager.getXieyiTimeTemp());
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            textView3.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.YisiDialogManager.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    YinsiQuedingDialog.show(YisiDialogManager.this.activity, configManager);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.show();
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            attributes.width = (int) (UIUtils.getScreenWidth(this.activity) * 0.8d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void toXieyi(String str, String str2) {
        IntentManager.gotoWebViewActivity(this.activity, str, str2);
    }
}
