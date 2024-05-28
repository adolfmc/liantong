package com.sinovatech.unicom.separatemodule.baidumap;

import android.app.Activity;
import android.app.Dialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.sinovatech.unicom.basic.datacenter.UserUnicomInfoDataCenter;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduCollectPopWindow implements View.OnClickListener {
    private static UserUnicomInfoDataCenter unicomInfoDataCenter;
    private Activity activityContext;
    private Button baidu;
    private Button gaode;
    private Button mCancel;
    private OnClickListener mOnClickListener;
    private Dialog navigationDialog;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface OnClickListener {
        void onClick(String str);
    }

    public BaiduCollectPopWindow(Activity activity) {
        this.activityContext = activity;
    }

    public void collectPopWindow() {
        unicomInfoDataCenter = new UserUnicomInfoDataCenter(this.activityContext);
        this.navigationDialog = new Dialog(this.activityContext, 2131952236);
        this.navigationDialog.setCancelable(true);
        this.navigationDialog.setCanceledOnTouchOutside(true);
        this.navigationDialog.getWindow().setGravity(80);
        this.navigationDialog.getWindow().setWindowAnimations(2131952235);
        LinearLayout linearLayout = (LinearLayout) LayoutInflater.from(this.activityContext).inflate(2131493014, (ViewGroup) null);
        this.mCancel = (Button) linearLayout.findViewById(2131296489);
        this.mCancel.setOnClickListener(this);
        this.baidu = (Button) linearLayout.findViewById(2131296488);
        this.baidu.setOnClickListener(this);
        this.gaode = (Button) linearLayout.findViewById(2131296490);
        this.gaode.setOnClickListener(this);
        this.navigationDialog.setContentView(linearLayout);
        this.navigationDialog.show();
        WindowManager.LayoutParams attributes = this.navigationDialog.getWindow().getAttributes();
        attributes.width = -1;
        attributes.height = -2;
        this.navigationDialog.getWindow().setAttributes(attributes);
        this.navigationDialog.setCanceledOnTouchOutside(false);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        NBSActionInstrumentation.onClickEventEnter(view, this);
        Tracker.onClick(view);
        switch (view.getId()) {
            case 2131296488:
                this.navigationDialog.dismiss();
                OnClickListener onClickListener = this.mOnClickListener;
                if (onClickListener != null) {
                    onClickListener.onClick(this.baidu.getText().toString());
                    break;
                }
                break;
            case 2131296489:
                this.navigationDialog.dismiss();
                this.mOnClickListener.onClick("取消");
                break;
            case 2131296490:
                this.navigationDialog.dismiss();
                OnClickListener onClickListener2 = this.mOnClickListener;
                if (onClickListener2 != null) {
                    onClickListener2.onClick(this.gaode.getText().toString());
                    break;
                }
                break;
        }
        NBSActionInstrumentation.onClickEventExit();
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.mOnClickListener = onClickListener;
    }
}
