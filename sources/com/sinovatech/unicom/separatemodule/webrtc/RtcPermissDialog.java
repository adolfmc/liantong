package com.sinovatech.unicom.separatemodule.webrtc;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class RtcPermissDialog extends Dialog {
    private static final String TAG = "RtcPermissDialog";
    private Context context;

    private RtcPermissDialog(Context context, int i) {
        super(context, i);
        this.context = context;
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class Builder {
        private View.OnClickListener mButtonCancelClickListener;
        private View.OnClickListener mButtonConfirmClickListener;
        private RtcPermissDialog mDialog;
        private View mLayout;
        TextView tv_Content;
        TextView tv_cancel;
        TextView tv_ok;
        TextView tv_title;

        public Builder(Context context) {
            init(context);
        }

        private void init(Context context) {
            this.mDialog = new RtcPermissDialog(context, 2131951850);
            this.mLayout = ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(2131493104, (ViewGroup) null, false);
            this.mDialog.addContentView(this.mLayout, new ViewGroup.LayoutParams(-1, -2));
            this.tv_ok = (TextView) this.mLayout.findViewById(2131299065);
            this.tv_cancel = (TextView) this.mLayout.findViewById(2131299063);
            this.tv_title = (TextView) this.mLayout.findViewById(2131299066);
            this.tv_Content = (TextView) this.mLayout.findViewById(2131299064);
        }

        public Builder setTitle(String str) {
            TextView textView = this.tv_title;
            if (textView == null) {
                Log.d(RtcPermissDialog.TAG, "title is null");
            } else {
                textView.setText(str);
            }
            return this;
        }

        public Builder setContent(String str) {
            TextView textView = this.tv_Content;
            if (textView == null) {
                Log.d(RtcPermissDialog.TAG, "content is null");
            } else {
                textView.setText(str);
            }
            return this;
        }

        public Builder setButtonCancel(String str, View.OnClickListener onClickListener) {
            this.tv_cancel.setText(str);
            this.mButtonCancelClickListener = onClickListener;
            return this;
        }

        public Builder setButtonConfirm(String str, View.OnClickListener onClickListener) {
            this.tv_ok.setText(str);
            this.mButtonConfirmClickListener = onClickListener;
            return this;
        }

        public RtcPermissDialog create() {
            this.tv_cancel.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcPermissDialog.Builder.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Builder.this.mDialog.dismiss();
                    Builder.this.mButtonCancelClickListener.onClick(view);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.tv_ok.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.webrtc.RtcPermissDialog.Builder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Builder.this.mDialog.dismiss();
                    Builder.this.mButtonConfirmClickListener.onClick(view);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            this.mDialog.setContentView(this.mLayout);
            this.mDialog.setCancelable(true);
            this.mDialog.setCanceledOnTouchOutside(false);
            return this.mDialog;
        }
    }

    @Override // android.app.Dialog
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        Window window = getWindow();
        WindowManager.LayoutParams attributes = window.getAttributes();
        attributes.type = 2038;
        attributes.width = (int) (this.context.getResources().getDisplayMetrics().widthPixels * 0.8d);
        window.setAttributes(attributes);
    }
}
