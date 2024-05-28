package com.sinovatech.unicom.separatemodule.dialog;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.separatemodule.dialog.BaseDialog;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class UserYunpanDialog {

    @NBSInstrumented
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Builder extends BaseDialog.Builder<Builder> {
        private boolean mAutoDismiss;
        private final TextView mCancelView;
        private final TextView mConfirmView;
        private final ViewGroup mContainerLayout;
        private final TextView mContentView;

        public Builder(Context context) {
            super(context);
            this.mAutoDismiss = true;
            setContentView(2131493534);
            setAnimStyle(2131951865);
            setGravity(17);
            this.mContainerLayout = (ViewGroup) findViewById(2131297793);
            this.mContentView = (TextView) findViewById(2131299015);
            this.mCancelView = (TextView) findViewById(2131299121);
            this.mConfirmView = (TextView) findViewById(2131299122);
            setOnClickListener(this.mCancelView, this.mConfirmView);
            this.mContentView.setText("暂不支持固话、宽带号码访问。\n请使用手机号码访问");
        }

        public Builder setMsgText(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mContentView.setText(str);
                this.mContentView.setPadding(15, 0, 15, 0);
            }
            return this;
        }

        public Builder setCancelText(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mCancelView.setText(str);
            }
            return this;
        }

        public Builder setOkText(String str) {
            if (!TextUtils.isEmpty(str)) {
                this.mConfirmView.setText(str);
            }
            return this;
        }

        @Override // com.sinovatech.unicom.separatemodule.dialog.BaseDialog.Builder, com.sinovatech.unicom.separatemodule.dialog.action.ClickAction, android.view.View.OnClickListener
        public void onClick(View view) {
            NBSActionInstrumentation.onClickEventEnter(view, this);
            if (view == this.mConfirmView) {
                try {
                    getActivity().startActivity(new Intent(getActivity(), LoginBindActivity.class));
                } catch (Exception e) {
                    e.printStackTrace();
                }
                dismiss();
            }
            if (view == this.mCancelView) {
                dismiss();
            }
            NBSActionInstrumentation.onClickEventExit();
        }
    }
}
