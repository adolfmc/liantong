package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class LivePvResultDialog extends Dialog {
    private ImageView ivIcon;
    private String time;
    private TextView tvNotice;
    private TextView tvSure;
    private TextView tvTitle;

    public LivePvResultDialog(@NonNull Context context, String str) {
        super(context, 2131951850);
        this.time = str;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493099);
        setCanceledOnTouchOutside(false);
        initView();
        refreshView();
        initEvent();
    }

    private void initView() {
        this.ivIcon = (ImageView) findViewById(2131297375);
        this.tvTitle = (TextView) findViewById(2131298929);
        this.tvNotice = (TextView) findViewById(2131298927);
        this.tvSure = (TextView) findViewById(2131298928);
    }

    public void refreshView() {
        this.ivIcon.setImageResource(2131231491);
        this.tvTitle.setText("预约成功");
        TextView textView = this.tvNotice;
        textView.setText("您已成功预约 " + this.time + "直播，开播前将以短信通知您，注意查收哦~");
    }

    private void initEvent() {
        this.tvSure.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.-$$Lambda$LivePvResultDialog$NGFUnq98YXKfhgp1FkAUtZHkQ-w
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                LivePvResultDialog.this.dismiss();
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        refreshView();
    }
}
