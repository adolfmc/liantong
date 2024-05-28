package com.sinovatech.unicom.separatemodule.audience.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RedPacketDialog extends Dialog {
    private Button btnTime;
    private Context context;
    private int countdown;
    @SuppressLint({"HandlerLeak"})
    Handler handler;
    private boolean isClosed;
    private ImageView ivClose;
    private String time;

    static /* synthetic */ int access$010(RedPacketDialog redPacketDialog) {
        int i = redPacketDialog.countdown;
        redPacketDialog.countdown = i - 1;
        return i;
    }

    public RedPacketDialog(@NonNull Context context) {
        super(context, 2131951850);
        this.countdown = 60;
        this.handler = new Handler() { // from class: com.sinovatech.unicom.separatemodule.audience.view.RedPacketDialog.1
            @Override // android.os.Handler
            public void handleMessage(Message message) {
                super.handleMessage(message);
                if (message.what == 10002) {
                    if (RedPacketDialog.this.countdown != 0) {
                        Button button = RedPacketDialog.this.btnTime;
                        button.setText("倒计时" + RedPacketDialog.this.countdown + "S");
                        RedPacketDialog.access$010(RedPacketDialog.this);
                        Message message2 = new Message();
                        message2.what = 10002;
                        message2.obj = Integer.valueOf(RedPacketDialog.this.countdown);
                        RedPacketDialog.this.handler.sendMessageDelayed(message2, 1000L);
                        return;
                    }
                    RedPacketDialog.this.dismiss();
                }
            }
        };
        this.context = context;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493101);
        setCanceledOnTouchOutside(false);
        getWindow().setBackgroundDrawable(new ColorDrawable(0));
        getWindow().setLayout(-1, -1);
        initView();
        refreshView();
        initEvent();
    }

    private void initView() {
        this.btnTime = (Button) findViewById(2131296546);
        this.ivClose = (ImageView) findViewById(2131297377);
        this.ivClose.setVisibility(8);
    }

    public void setCountdown(int i) {
        this.countdown = i;
    }

    @Override // android.app.Dialog
    public void show() {
        try {
            if (this.context instanceof Activity) {
                if (((Activity) this.context).isFinishing()) {
                    this.handler.removeMessages(10002);
                } else {
                    super.show();
                    this.handler.sendEmptyMessage(10002);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initEvent() {
        this.btnTime.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.RedPacketDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (RedPacketDialog.this.isShowing()) {
                    RedPacketDialog.this.dismiss();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    public String getTime() {
        return this.time;
    }

    public RedPacketDialog setTime(String str) {
        this.time = str;
        return this;
    }

    public void refreshView() {
        if (TextUtils.isEmpty(this.time)) {
            return;
        }
        Button button = this.btnTime;
        button.setText(this.time + "S");
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        super.dismiss();
        this.isClosed = true;
        this.handler.removeMessages(10002);
    }

    public boolean isClosed() {
        return this.isClosed;
    }

    public void setClosed(boolean z) {
        this.isClosed = z;
    }
}
