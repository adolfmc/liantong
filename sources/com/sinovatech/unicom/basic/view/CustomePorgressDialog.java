package com.sinovatech.unicom.basic.view;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomePorgressDialog extends ProgressDialog {
    String message;
    private TextView messageTextView;

    public CustomePorgressDialog(Context context) {
        super(context, 2131951850);
        this.message = "";
    }

    public CustomePorgressDialog(Context context, int i) {
        super(context, i);
        this.message = "";
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog, android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493446);
        this.messageTextView = (TextView) findViewById(2131296797);
        if (!TextUtils.isEmpty(this.message)) {
            if (!"null".equals(this.message)) {
                this.messageTextView.setText(this.message);
            }
        } else {
            this.messageTextView.setText("努力加载中...");
        }
        getWindow().setDimAmount(0.0f);
    }

    @Override // android.app.ProgressDialog, android.app.AlertDialog
    public void setMessage(CharSequence charSequence) {
        super.setMessage(charSequence);
        this.message = (String) charSequence;
        TextView textView = this.messageTextView;
        if (textView != null) {
            textView.setText(charSequence);
        }
    }
}
