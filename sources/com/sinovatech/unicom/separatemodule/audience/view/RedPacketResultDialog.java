package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RedPacketResultDialog extends Dialog {
    private Button btnConfirm;
    private String img;
    private String info;
    private ImageView ivClose;
    private ImageView ivJpImg;
    private String name;
    private TextView tvInfo;
    private TextView tvName;

    public RedPacketResultDialog(@NonNull Context context) {
        super(context, 2131951850);
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493103);
        setCanceledOnTouchOutside(false);
        initView();
        refreshView();
        initEvent();
    }

    private void initView() {
        this.ivClose = (ImageView) findViewById(2131297379);
        this.ivJpImg = (ImageView) findViewById(2131297414);
        this.tvName = (TextView) findViewById(2131298996);
        this.tvInfo = (TextView) findViewById(2131298997);
        this.btnConfirm = (Button) findViewById(2131296548);
    }

    private void initEvent() {
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.RedPacketResultDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (RedPacketResultDialog.this.isShowing()) {
                    RedPacketResultDialog.this.dismiss();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.btnConfirm.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.RedPacketResultDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (RedPacketResultDialog.this.isShowing()) {
                    RedPacketResultDialog.this.dismiss();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
    }

    @Override // android.app.Dialog
    public void show() {
        super.show();
        refreshView();
    }

    public String getName() {
        return this.name;
    }

    public RedPacketResultDialog setName(String str) {
        this.name = str;
        return this;
    }

    public String getInfo() {
        return this.info;
    }

    public RedPacketResultDialog setInfo(String str) {
        this.info = str;
        return this;
    }

    public String getImg() {
        return this.img;
    }

    public RedPacketResultDialog setImg(String str) {
        this.img = str;
        return this;
    }

    public void refreshView() {
        String str;
        if (!TextUtils.isEmpty(this.name)) {
            TextView textView = this.tvName;
            if (this.name.contains("获得")) {
                str = this.name;
            } else {
                str = "获得" + this.name;
            }
            textView.setText(str);
        }
        if (!TextUtils.isEmpty(this.info)) {
            this.tvInfo.setText(this.info);
        }
        if (TextUtils.isEmpty(this.img)) {
            return;
        }
        GlideApp.with(getContext()).load(this.img).into(this.ivJpImg);
    }
}
