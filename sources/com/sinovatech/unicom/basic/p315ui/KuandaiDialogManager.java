package com.sinovatech.unicom.basic.p315ui;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;

/* renamed from: com.sinovatech.unicom.basic.ui.KuandaiDialogManager */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class KuandaiDialogManager {
    private Activity context;
    private EditText editText;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.sinovatech.unicom.basic.ui.KuandaiDialogManager$KuandaiDialogListener */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface KuandaiDialogListener {
        void onBackKeyDown();

        void onCancel();

        void onClickCancel();

        void onClickOk();

        void onShow();
    }

    public KuandaiDialogManager(Activity activity) {
        this.context = activity;
    }

    public void show(String str, Bitmap bitmap, final KuandaiDialogListener kuandaiDialogListener) {
        try {
            final Dialog dialog = new Dialog(this.context, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            this.context.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i2 = displayMetrics.widthPixels;
            int i3 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i2;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            View inflate = this.context.getLayoutInflater().inflate(2131493267, (ViewGroup) null);
            this.editText = (EditText) inflate.findViewById(2131297564);
            this.editText.setHint(str);
            ((ImageView) inflate.findViewById(2131297547)).setImageBitmap(bitmap);
            ((Button) inflate.findViewById(2131297563)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.KuandaiDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    kuandaiDialogListener.onClickOk();
                    dialog.dismiss();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            ((Button) inflate.findViewById(2131297562)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.KuandaiDialogManager.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.dismiss();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.ui.KuandaiDialogManager.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    if (i4 == 4 && keyEvent.getRepeatCount() == 0) {
                        dialogInterface.cancel();
                        return true;
                    }
                    return false;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String getCode() {
        EditText editText = this.editText;
        return editText != null ? editText.getText().toString() : "";
    }
}
