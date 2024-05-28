package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.support.p086v7.widget.CardView;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.activity.LoginBindActivity;
import com.sinovatech.unicom.basic.server.IntentManager;
import com.sinovatech.unicom.p318ui.App;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CopyDialogManager {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CopyDialogListener {
        void onCancel();
    }

    public static void show(final Activity activity, String str, final String str2, String str3, String str4, final CopyDialogListener copyDialogListener) {
        final Dialog dialog = new Dialog(activity, 2131951850);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int i = displayMetrics.widthPixels;
        int i2 = displayMetrics.heightPixels;
        WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
        int i3 = (int) (i * 0.68d);
        attributes.width = i3;
        double d = i2;
        attributes.height = (int) (0.46d * d);
        Window window = dialog.getWindow();
        window.setAttributes(attributes);
        window.setDimAmount(0.6f);
        window.addFlags(2);
        View inflate = activity.getLayoutInflater().inflate(2131493219, (ViewGroup) null);
        CardView cardView = (CardView) inflate.findViewById(2131296714);
        ViewGroup.LayoutParams layoutParams = cardView.getLayoutParams();
        layoutParams.height = (int) (0.44d * d);
        cardView.setLayoutParams(layoutParams);
        ImageView imageView = (ImageView) inflate.findViewById(2131296717);
        ViewGroup.LayoutParams layoutParams2 = imageView.getLayoutParams();
        layoutParams2.height = (int) (0.2d * d);
        imageView.setLayoutParams(layoutParams2);
        ((TextView) inflate.findViewById(2131296716)).setText(str);
        Glide.with(activity).load(str3).into(imageView);
        ((ImageView) inflate.findViewById(2131296715)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CopyDialogManager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                dialog.dismiss();
                copyDialogListener.onCancel();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        ((Button) inflate.findViewById(2131296718)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CopyDialogManager.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                NBSActionInstrumentation.onClickEventEnter(view, this);
                Tracker.onClick(view);
                if (!App.hasLogined()) {
                    Intent intent = new Intent(activity, LoginBindActivity.class);
                    intent.putExtra("directAccess", true);
                    intent.putExtra("url", str2);
                    intent.putExtra("title", "");
                    intent.putExtra("backMode", "1");
                    intent.putExtra("requestType", "get");
                    activity.startActivity(intent);
                } else {
                    IntentManager.gotoWebViewActivity(activity, str2, "");
                }
                dialog.dismiss();
                NBSActionInstrumentation.onClickEventExit();
            }
        });
        dialog.setContentView(inflate);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();
        dialog.getWindow().setLayout(i3, (int) (d * 0.58d));
    }
}
