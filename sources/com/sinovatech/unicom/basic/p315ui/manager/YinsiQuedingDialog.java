package com.sinovatech.unicom.basic.p315ui.manager;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.ConfigManager;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sinovatech.unicom.basic.ui.manager.YinsiQuedingDialog */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class YinsiQuedingDialog {
    public static void show(final Activity activity, final ConfigManager configManager) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
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
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            ((TextView) inflate.findViewById(2131296788)).setText("");
            TextView textView = (TextView) inflate.findViewById(2131296785);
            textView.setGravity(17);
            textView.setTextColor(Color.parseColor("#666666"));
            textView.setText("您需要同意相关政策方可使用本软件");
            textView.setTextSize(1, 13.0f);
            Button button = (Button) inflate.findViewById(2131296787);
            button.setText("再次查看");
            button.setTextColor(activity.getResources().getColor(2131099937));
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.YinsiQuedingDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    new YisiDialogManager(activity).showYinSi(configManager);
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            button2.setVisibility(0);
            button2.setText("不同意并退出");
            button2.setTextColor(activity.getResources().getColor(2131099831));
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.ui.manager.YinsiQuedingDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    activity.finish();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.ui.manager.YinsiQuedingDialog.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    return i4 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
