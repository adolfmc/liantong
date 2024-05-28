package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RedPacketNoneDialog extends Dialog {
    private ImageView ivNone;
    private String msg;
    private TextView tvMsg;

    public RedPacketNoneDialog(@NonNull Context context) {
        super(context, 2131951850);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493102);
        setCanceledOnTouchOutside(false);
        initView();
        initEvent();
    }

    private void initView() {
        this.ivNone = (ImageView) findViewById(2131297378);
        this.tvMsg = (TextView) findViewById(2131299058);
    }

    private void initEvent() {
        this.ivNone.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.RedPacketNoneDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (RedPacketNoneDialog.this.isShowing()) {
                    RedPacketNoneDialog.this.dismiss();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        refresh();
    }

    public String getMsg() {
        return this.msg;
    }

    public RedPacketNoneDialog setMsg(String str) {
        this.msg = str;
        return this;
    }

    private void refresh() {
        if (TextUtils.isEmpty(this.msg)) {
            return;
        }
        this.tvMsg.setText(this.msg);
    }
}
