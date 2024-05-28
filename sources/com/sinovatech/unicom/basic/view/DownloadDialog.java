package com.sinovatech.unicom.basic.view;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.widget.TextView;
import com.sinovatech.unicom.common.UIUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class DownloadDialog extends Dialog {
    private TextView messageText;
    private UnicomProgressBar progressBar;

    public DownloadDialog(Context context) {
        super(context, 2131951850);
        setContentView(2131493482);
        setCancelable(false);
        setCanceledOnTouchOutside(false);
        this.progressBar = (UnicomProgressBar) findViewById(2131298235);
        this.progressBar.setMax(100);
        this.progressBar.setCricleColor(Color.parseColor("#E5E5E5"));
        this.progressBar.setCricleProgressColor(Color.parseColor("#2CCDA7"));
        this.progressBar.setTextColor(-16777216);
        this.progressBar.setTextSize(UIUtils.dip2px(14.0f));
        this.progressBar.setRoundWidth(UIUtils.dip2px(70.0f));
        this.progressBar.setRoundWidth(UIUtils.dip2px(3.0f));
        this.messageText = (TextView) findViewById(2131296872);
    }

    public void setMessage(CharSequence charSequence) {
        this.messageText.setText(charSequence);
    }

    public void setProgress(int i) {
        this.progressBar.setProgress(i);
    }
}
