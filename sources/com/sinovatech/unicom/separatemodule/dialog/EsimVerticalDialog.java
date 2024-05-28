package com.sinovatech.unicom.separatemodule.dialog;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.separatemodule.dialog.BaseDialog;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class EsimVerticalDialog {

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Builder<B extends Builder<?>> extends BaseDialog.Builder<B> {
        private boolean mAutoDismiss;
        private final TextView mCancelView;
        private final TextView mConfirmView;
        private final ViewGroup mContainerLayout;
        private final View mLineView;
        private final TextView mTitleView;
        private final TextView messageView;

        public Builder(Context context) {
            super(context);
            this.mAutoDismiss = true;
            setContentView(2131493112);
            setAnimStyle(2131951865);
            setGravity(17);
            this.mContainerLayout = (ViewGroup) findViewById(2131297793);
            this.mTitleView = (TextView) findViewById(2131299123);
            this.mCancelView = (TextView) findViewById(2131299121);
            this.mLineView = findViewById(2131299499);
            this.mConfirmView = (TextView) findViewById(2131299122);
            this.messageView = (TextView) findViewById(2131296942);
            setOnClickListener(this.mCancelView, this.mConfirmView);
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.Builder, com.sinovatech.unicom.separatemodule.dialog.action.ClickAction, android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            super.onClick(view);
            if (view == this.mConfirmView) {
                dismiss();
            }
            if (view == this.mCancelView) {
                dismiss();
            }
            NBSActionInstrumentation.onClickEventExit();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setMessageView(String str) {
            this.messageView.setText(str);
            return this;
        }

        public B setCustomView(@LayoutRes int i) {
            return setCustomView(LayoutInflater.from(getContext()).inflate(i, this.mContainerLayout, false));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setCustomView(View view) {
            this.mContainerLayout.addView(view, 1);
            return this;
        }

        public B setTitle(@StringRes int i) {
            return setTitle(getString(i));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setTitle(CharSequence charSequence) {
            this.mTitleView.setText(charSequence);
            return this;
        }

        public B setCancel(@StringRes int i) {
            return setCancel(getString(i));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setCancel(CharSequence charSequence) {
            int i = 8;
            this.mCancelView.setVisibility(8);
            this.mCancelView.setText(charSequence);
            View view = this.mLineView;
            if (charSequence != null && !"".equals(charSequence.toString())) {
                i = 0;
            }
            view.setVisibility(i);
            return this;
        }

        public B setConfirm(@StringRes int i) {
            return setConfirm(getString(i));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setConfirm(CharSequence charSequence) {
            this.mConfirmView.setText(charSequence);
            return this;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public B setAutoDismiss(boolean z) {
            this.mAutoDismiss = z;
            return this;
        }

        public void autoDismiss() {
            if (this.mAutoDismiss) {
                dismiss();
            }
        }
    }
}
