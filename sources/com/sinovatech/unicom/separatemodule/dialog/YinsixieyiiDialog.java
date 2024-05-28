package com.sinovatech.unicom.separatemodule.dialog;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.common.URLSet;
import com.sinovatech.unicom.separatemodule.dialog.BaseDialog;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class YinsixieyiiDialog {

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Builder extends BaseDialog.Builder<Builder> {
        private boolean mAutoDismiss;
        private final TextView mCancelView;
        private final TextView mConfirmView;
        private final ViewGroup mContainerLayout;
        private final TextView mContentView;
        private final TextView mTitleView;

        public Builder(Context context) {
            super(context);
            this.mAutoDismiss = true;
            setContentView(2131493601);
            setAnimStyle(2131951865);
            setGravity(17);
            this.mContainerLayout = (ViewGroup) findViewById(2131297793);
            this.mContentView = (TextView) findViewById(2131299015);
            this.mTitleView = (TextView) findViewById(2131299123);
            this.mCancelView = (TextView) findViewById(2131299121);
            this.mConfirmView = (TextView) findViewById(2131299122);
            setOnClickListener(this.mCancelView, this.mConfirmView);
            this.mContentView.setMovementMethod(LinkMovementMethod.getInstance());
            this.mContentView.setText(getXieyiSpannableString());
        }

        private SpannableStringBuilder getXieyiSpannableString() {
            SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder();
            spannableStringBuilder.append((CharSequence) "为了更好地保障您的隐私权益，请您阅读并同意");
            SpannableString spannableString = new SpannableString("《中国联通APP用户隐私政策》");
            spannableString.setSpan(new XieyiClickSpan(URLSet.getUserPrivacy()), 0, spannableString.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString);
            spannableStringBuilder.append((CharSequence) "、");
            SpannableString spannableString2 = new SpannableString("《中国联通APP用户服务协议》");
            spannableString2.setSpan(new XieyiClickSpan(URLSet.getUserserver()), 0, spannableString2.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString2);
            spannableStringBuilder.append((CharSequence) "以及");
            SpannableString spannableString3 = new SpannableString("《中国联通用户隐私政策》");
            spannableString3.setSpan(new XieyiClickSpan(URLSet.getunicom_yinsizhengce()), 0, spannableString3.length(), 33);
            spannableStringBuilder.append((CharSequence) spannableString3);
            return spannableStringBuilder;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        @NBSInstrumented
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public class XieyiClickSpan extends ClickableSpan {
            private String url;

            public XieyiClickSpan(String str) {
                this.url = str;
            }

            @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
            public void updateDrawState(TextPaint textPaint) {
                super.updateDrawState(textPaint);
                textPaint.setColor(-10786159);
                textPaint.setUnderlineText(false);
            }

            @Override // android.text.style.ClickableSpan
            public void onClick(@NonNull View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Builder.this.toXieyi(this.url);
                NBSActionInstrumentation.onClickEventExit();
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void toXieyi(String str) {
            try {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse(str)));
            } catch (Exception e) {
                MsLogUtil.m7978e(e.getMessage());
            }
        }
    }
}
