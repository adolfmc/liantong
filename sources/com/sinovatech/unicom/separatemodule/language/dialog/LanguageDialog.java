package com.sinovatech.unicom.separatemodule.language.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.view.CustomDialogManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.language.entity.LanguageEntity;
import com.sinovatech.unicom.separatemodule.language.mongolian.view.MongolianTextView;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class LanguageDialog {
    public static void show(Activity activity, LanguageEntity languageEntity, CustomDialogManager.SimpleCustomeDialogListener simpleCustomeDialogListener) {
        if (TextUtils.equals("1", languageEntity.getShowDirectionFlag())) {
            showPortrait(activity, languageEntity, simpleCustomeDialogListener);
        } else {
            showLandscape(activity, languageEntity, simpleCustomeDialogListener);
        }
    }

    public static void showPortrait(Activity activity, LanguageEntity languageEntity, final CustomDialogManager.SimpleCustomeDialogListener simpleCustomeDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            int i2 = (int) (i * 0.7d);
            attributes.width = i2;
            attributes.height = UIUtils.dip2px(activity, 316.0f);
            dialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493098, (ViewGroup) null);
            MongolianTextView mongolianTextView = (MongolianTextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(languageEntity.getReminderPop())) {
                mongolianTextView.setText(languageEntity.getReminderPop());
            }
            ((TextView) inflate.findViewById(2131296789)).setText("温馨提示");
            TextView textView = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(languageEntity.getSwitchLanguagePop())) {
                textView.setText(languageEntity.getSwitchLanguagePop());
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296786);
            if (!TextUtils.isEmpty(languageEntity.getLanguageName())) {
                textView2.setText("切换到" + languageEntity.getLanguageName() + "版本吗?");
            }
            ((LinearLayout) inflate.findViewById(2131296874)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            MongolianTextView mongolianTextView2 = (MongolianTextView) inflate.findViewById(2131296875);
            if (!TextUtils.isEmpty(languageEntity.getConfirmBtnPop())) {
                mongolianTextView2.setText(languageEntity.getConfirmBtnPop());
            }
            ((TextView) inflate.findViewById(2131296876)).setText("确定");
            ((LinearLayout) inflate.findViewById(2131296877)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            MongolianTextView mongolianTextView3 = (MongolianTextView) inflate.findViewById(2131296878);
            if (!TextUtils.isEmpty(languageEntity.getCancelBtnPop())) {
                mongolianTextView3.setText(languageEntity.getCancelBtnPop());
            }
            ((TextView) inflate.findViewById(2131296879)).setText("取消");
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog.3
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i3, KeyEvent keyEvent) {
                    return i3 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout(i2, UIUtils.dip2px(activity, 316.0f));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showLandscape(Activity activity, LanguageEntity languageEntity, final CustomDialogManager.SimpleCustomeDialogListener simpleCustomeDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i;
            attributes.width = (int) (0.8d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493097, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(languageEntity.getReminderPop())) {
                textView.setText(languageEntity.getReminderPop());
            }
            ((TextView) inflate.findViewById(2131296789)).setText("温馨提示");
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(languageEntity.getSwitchLanguagePop())) {
                textView2.setText(languageEntity.getSwitchLanguagePop());
            }
            TextView textView3 = (TextView) inflate.findViewById(2131296786);
            if (!TextUtils.isEmpty(languageEntity.getLanguageName())) {
                textView3.setText("切换至" + languageEntity.getLanguageName() + "版吗?");
            }
            ((LinearLayout) inflate.findViewById(2131296874)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            TextView textView4 = (TextView) inflate.findViewById(2131296875);
            if (!TextUtils.isEmpty(languageEntity.getConfirmBtnPop())) {
                textView4.setText(languageEntity.getConfirmBtnPop());
            }
            ((TextView) inflate.findViewById(2131296876)).setText("确定");
            ((LinearLayout) inflate.findViewById(2131296877)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            TextView textView5 = (TextView) inflate.findViewById(2131296878);
            if (!TextUtils.isEmpty(languageEntity.getCancelBtnPop())) {
                textView5.setText(languageEntity.getCancelBtnPop());
            }
            ((TextView) inflate.findViewById(2131296879)).setText("取消");
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.separatemodule.language.dialog.LanguageDialog.6
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    return i2 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.9d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
