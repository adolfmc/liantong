package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HelpingHandDialog extends Dialog {
    private String btnText;
    private TextView ivBtn;
    private ImageView ivClose;
    private OnHelpingDialogListener listener;
    private String notice;
    private String tips;
    private TextView tvNotice;
    private TextView tvTips;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnHelpingDialogListener {
        void callBack();
    }

    public HelpingHandDialog(@NonNull Context context, String str, String str2, String str3, OnHelpingDialogListener onHelpingDialogListener) {
        super(context, 2131951850);
        this.tips = str;
        this.notice = str3;
        this.btnText = str2;
        this.listener = onHelpingDialogListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493096);
        setCanceledOnTouchOutside(false);
        initView();
        refreshView();
        initEvent();
    }

    private void initView() {
        this.tvTips = (TextView) findViewById(2131298969);
        this.ivBtn = (TextView) findViewById(2131298967);
        this.tvNotice = (TextView) findViewById(2131298968);
        this.ivClose = (ImageView) findViewById(2131297393);
    }

    public void refreshView() {
        this.tvTips.setText(this.tips);
        this.ivBtn.setText(this.btnText);
        this.tvNotice.setText(this.notice);
    }

    private void initEvent() {
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$HelpingHandDialog$DvE9LFLx_PVsrrpbLj7wYI1I87g
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpingHandDialog.this.dismiss();
            }
        });
        this.ivBtn.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$HelpingHandDialog$h2VlFoYkXX6OFmRQSErTr5KUpJg
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                HelpingHandDialog.lambda$initEvent$1(HelpingHandDialog.this, view);
            }
        });
    }

    public static /* synthetic */ void lambda$initEvent$1(HelpingHandDialog helpingHandDialog, View view) {
        OnHelpingDialogListener onHelpingDialogListener = helpingHandDialog.listener;
        if (onHelpingDialogListener != null) {
            onHelpingDialogListener.callBack();
            helpingHandDialog.dismiss();
        }
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        refreshView();
    }
}
