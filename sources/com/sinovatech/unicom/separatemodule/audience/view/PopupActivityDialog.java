package com.sinovatech.unicom.separatemodule.audience.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PopupActivityDialog extends Dialog {
    private View clickView;
    private String img;
    private ImageView ivClose;
    private ImageView ivJpImg;
    private IPopupListener listener;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface IPopupListener {
        void callback();
    }

    public PopupActivityDialog(@NonNull Context context, String str, IPopupListener iPopupListener) {
        super(context, 2131951850);
        this.img = str;
        this.listener = iPopupListener;
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(2131493100);
        setCanceledOnTouchOutside(true);
        initView();
        refreshView();
        initEvent();
    }

    private void initView() {
        this.ivClose = (ImageView) findViewById(2131297376);
        this.ivJpImg = (ImageView) findViewById(2131297455);
        this.clickView = findViewById(2131299473);
    }

    private void initEvent() {
        this.ivClose.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.PopupActivityDialog.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (PopupActivityDialog.this.isShowing()) {
                    PopupActivityDialog.this.dismiss();
                }
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        this.clickView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.audience.view.PopupActivityDialog.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (PopupActivityDialog.this.isShowing()) {
                    PopupActivityDialog.this.dismiss();
                    if (PopupActivityDialog.this.listener != null) {
                        PopupActivityDialog.this.listener.callback();
                    }
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

    public void refreshView() {
        if (TextUtils.isEmpty(this.img)) {
            return;
        }
        GlideApp.with(getContext()).load(this.img).into(this.ivJpImg);
    }

    public void setImg(String str) {
        this.img = str;
    }
}
