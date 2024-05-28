package com.sinovatech.unicom.basic.p315ui;

import android.app.Activity;
import android.app.Dialog;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.CycleInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* renamed from: com.sinovatech.unicom.basic.ui.FingerDialogManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class FingerDialogManager {
    private Activity context;
    private Dialog dialog;
    private TranslateAnimation mAnimation = new TranslateAnimation(0.0f, 5.0f, 0.0f, 0.0f);
    private TextView messageView;
    private ImageView shakeImage;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.FingerDialogManager$FingerDialogListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface FingerDialogListener {
        void onCancel();

        void onReset();
    }

    public FingerDialogManager(Activity activity) {
        this.context = activity;
        this.mAnimation.setDuration(800L);
        this.mAnimation.setInterpolator(new CycleInterpolator(8.0f));
    }

    public void show(String str, String str2, boolean z, final FingerDialogListener fingerDialogListener) {
        try {
            if (this.context == null || this.context.isDestroyed() || this.context.isFinishing()) {
                return;
            }
            this.dialog = new Dialog(this.context, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i2 = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = this.dialog.getWindow().getAttributes();
            double d = i2;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = this.dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = this.context.getLayoutInflater().inflate(2131493119, (ViewGroup) null);
            this.shakeImage = (ImageView) inflate.findViewById(2131296969);
            View findViewById = inflate.findViewById(2131296968);
            this.messageView = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str2)) {
                this.messageView.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.FingerDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    FingerDialogManager.this.dialog.cancel();
                    fingerDialogListener.onCancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                button2.setBackgroundResource(2131231198);
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.FingerDialogManager.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        FingerDialogManager.this.dialog.cancel();
                        fingerDialogListener.onReset();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
                findViewById.setVisibility(0);
            } else {
                button2.setVisibility(8);
                findViewById.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            this.dialog.setContentView(inflate);
            this.dialog.setCancelable(false);
            this.dialog.setCanceledOnTouchOutside(false);
            this.dialog.show();
            this.dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void cancel() {
        try {
            if (this.context == null || this.context.isFinishing() || this.context.isDestroyed() || this.dialog == null || !this.dialog.isShowing()) {
                return;
            }
            this.dialog.cancel();
        } catch (Exception e) {
            MsLogUtil.m7978e(e.getMessage());
        }
    }

    public void startShake() {
        ImageView imageView = this.shakeImage;
        if (imageView != null) {
            imageView.startAnimation(this.mAnimation);
        }
    }

    public void setTip(String str) {
        TextView textView = this.messageView;
        if (textView != null) {
            textView.setText(str);
        }
    }
}
