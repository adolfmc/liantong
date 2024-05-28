package com.sinovatech.unicom.basic.view;

import android.app.Activity;
import android.app.Dialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.text.TextUtils;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.UIUtils;
import com.sinovatech.unicom.separatemodule.audience.util.ADCallBack;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class CustomDialogManager {

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CustomeDialogCheckBoxListener {
        void onCancle();

        void onCheck(boolean z);

        void onClickOk();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface CustomeDialogListener {
        void onBackKeyDown();

        void onCancel();

        void onClickCancel();

        void onClickOk();

        void onShow();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SetCallingDialogListener {
        void close();

        void onClick(boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SimpleCustomeDialogListener {
        void onClickOk();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface SimpleCustomeDialogListener2 {
        void onCancel();

        void onClickOk();
    }

    public static void show(Activity activity, String str, String str2, boolean z, String str3, String str4, boolean z2, CustomeDialogListener customeDialogListener) {
        show(activity, false, str, str2, z, str3, str4, z2, customeDialogListener);
    }

    public static void show(Activity activity, boolean z, String str, String str2, boolean z2, String str3, String str4, final boolean z3, final CustomeDialogListener customeDialogListener) {
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
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            if (z) {
                UIUtils.setGJR(inflate);
            }
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z2) {
                button2.setVisibility(0);
                button.setBackgroundResource(2131231200);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.2
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        customeDialogListener.onClickCancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.3
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onShow();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.4
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onCancel();
                }
            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.5
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    if (i4 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z3) {
                            dialogInterface.cancel();
                        }
                        customeDialogListener.onBackKeyDown();
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

    public static void show(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, final boolean z2, final CustomeDialogListener customeDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i2 = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i3;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            UIUtils.setGJR(inflate);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(i);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            if (z) {
                button2.setVisibility(0);
                button.setBackgroundResource(2131231200);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.7
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        customeDialogListener.onClickCancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.8
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onShow();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.9
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onCancel();
                }
            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.10
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i5, KeyEvent keyEvent) {
                    if (i5 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                        }
                        customeDialogListener.onBackKeyDown();
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

    public static void show(final Activity activity, String str, String str2, boolean z, String str3, String str4, final boolean z2, boolean z3, final CustomeDialogListener customeDialogListener) {
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
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.11
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    UIUtils.dismissDialog(activity, dialog);
                    CustomeDialogListener customeDialogListener2 = customeDialogListener;
                    if (customeDialogListener2 != null) {
                        customeDialogListener2.onClickOk();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                button.setBackgroundResource(2131231200);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.12
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        UIUtils.dismissDialog(activity, dialog);
                        CustomeDialogListener customeDialogListener2 = customeDialogListener;
                        if (customeDialogListener2 != null) {
                            customeDialogListener2.onClickCancel();
                        }
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(z3);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.13
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    CustomeDialogListener customeDialogListener2 = CustomeDialogListener.this;
                    if (customeDialogListener2 != null) {
                        customeDialogListener2.onShow();
                    }
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.14
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    CustomeDialogListener customeDialogListener2 = CustomeDialogListener.this;
                    if (customeDialogListener2 != null) {
                        customeDialogListener2.onCancel();
                    }
                }
            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.15
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    if (i4 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2 && !activity.isFinishing() && !activity.isDestroyed() && dialogInterface != null) {
                            dialogInterface.cancel();
                        }
                        CustomeDialogListener customeDialogListener2 = customeDialogListener;
                        if (customeDialogListener2 != null) {
                            customeDialogListener2.onBackKeyDown();
                            return true;
                        }
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

    public static void show(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, final boolean z2, boolean z3, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
        View inflate;
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i2 = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i3;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            if (z3) {
                inflate = activity.getLayoutInflater().inflate(2131493443, (ViewGroup) null);
            } else {
                inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            }
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(i);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.16
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.17
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.18
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i5, KeyEvent keyEvent) {
                    if (i5 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                            return true;
                        }
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

    public static void show2(Activity activity, String str, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493443, (ViewGroup) null);
            ((TextView) inflate.findViewById(2131296788)).setVisibility(8);
            TextView textView = (TextView) inflate.findViewById(2131296785);
            textView.setGravity(17);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            ((Button) inflate.findViewById(2131296787)).setVisibility(8);
            Button button = (Button) inflate.findViewById(2131296781);
            button.setBackgroundResource(2131231203);
            button.setText("我知道了");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.19
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    SimpleCustomeDialogListener simpleCustomeDialogListener2 = SimpleCustomeDialogListener.this;
                    if (simpleCustomeDialogListener2 != null) {
                        simpleCustomeDialogListener2.onClickOk();
                    }
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.20
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

    public static void show3(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, final boolean z2, boolean z3, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
        View inflate;
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i2 = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i3;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            if (z3) {
                inflate = activity.getLayoutInflater().inflate(2131493443, (ViewGroup) null);
            } else {
                inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            }
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(i);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.21
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.22
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.23
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i5, KeyEvent keyEvent) {
                    if (i5 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                            return true;
                        }
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

    public static void show5(Activity activity, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493078, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296785);
            textView.setGravity(17);
            if ("02".equals(UserManager.getInstance().getCurrentPhoneType())) {
                textView.setText("暂不支持固话账号使用云盘功能，\n请切换手机号码访问");
            }
            if ("03".equals(UserManager.getInstance().getCurrentPhoneType())) {
                textView.setText("暂不支持宽带账号使用云盘功能，\n请切换手机号码访问");
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setText("取消");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.24
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setVisibility(0);
            button2.setText("切换帐号");
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.25
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.26
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

    public static void show4(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, final boolean z2, boolean z3, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
        View inflate;
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i2 = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i3;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            if (z3) {
                inflate = activity.getLayoutInflater().inflate(2131493443, (ViewGroup) null);
            } else {
                inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            }
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
                textView.setVisibility(0);
            } else {
                textView.setVisibility(8);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(i);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.27
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.28
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        SimpleCustomeDialogListener.this.onClickOk();
                        dialog.cancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.29
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i5, KeyEvent keyEvent) {
                    if (i5 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                            return true;
                        }
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

    public static void show(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, boolean z2, SimpleCustomeDialogListener simpleCustomeDialogListener) {
        show(activity, str, str2, 17, z, str3, str4, z2, false, simpleCustomeDialogListener);
    }

    public static void show(Activity activity, String str, String str2, boolean z, String str3, String str4, boolean z2, SimpleCustomeDialogListener simpleCustomeDialogListener) {
        show(activity, str, str2, 17, z, str3, str4, z2, simpleCustomeDialogListener);
    }

    public static void show(final Activity activity, ADCallBack aDCallBack, final SetCallingDialogListener setCallingDialogListener) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493540, (ViewGroup) null);
            inflate.findViewById(2131297374).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.-$$Lambda$CustomDialogManager$Desh0nrAUgyzTif8-dsN-4BjPUI
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomDialogManager.lambda$show$0(dialog, setCallingDialogListener, view);
                }
            });
            final ImageView imageView = (ImageView) inflate.findViewById(2131297331);
            final TextView textView = (TextView) inflate.findViewById(2131299075);
            final ImageView imageView2 = (ImageView) inflate.findViewById(2131297467);
            imageView.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.-$$Lambda$CustomDialogManager$fXqGSz6XFfEehWIR1VW5jXV1f8Y
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    ImageView imageView3 = imageView;
                    imageView3.setSelected(!imageView3.isSelected());
                }
            });
            imageView2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.-$$Lambda$CustomDialogManager$6TnrEFf5fcKOeMUHHDf6H1GeCh4
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomDialogManager.lambda$show$2(imageView2, activity, textView, view);
                }
            });
            ((TextView) inflate.findViewById(2131298890)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.-$$Lambda$CustomDialogManager$rcFzfqUT7pT2BzdJ2IvswxmaF5U
                @Override // android.view.View.OnClickListener
                public final void onClick(View view) {
                    CustomDialogManager.lambda$show$3(dialog, setCallingDialogListener, imageView, view);
                }
            });
            if (aDCallBack != null) {
                aDCallBack.addAdView(inflate);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.-$$Lambda$CustomDialogManager$GXWtHDEKtU7qyaBkWftMn57fQDE
                @Override // android.content.DialogInterface.OnKeyListener
                public final boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    return CustomDialogManager.lambda$show$4(dialogInterface, i4, keyEvent);
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$0(Dialog dialog, SetCallingDialogListener setCallingDialogListener, View view) {
        dialog.cancel();
        if (setCallingDialogListener != null) {
            setCallingDialogListener.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$2(ImageView imageView, Activity activity, TextView textView, View view) {
        int right = imageView.getRight();
        int left = imageView.getLeft();
        PopupWindow popupWindow = new PopupWindow(LayoutInflater.from(activity).inflate(2131493395, (ViewGroup) null), -2, -2, true);
        popupWindow.getContentView().measure(0, 0);
        int measuredWidth = right - popupWindow.getContentView().getMeasuredWidth();
        popupWindow.setTouchable(true);
        popupWindow.showAsDropDown(textView, measuredWidth - ((right - left) / 2), 10);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ void lambda$show$3(Dialog dialog, SetCallingDialogListener setCallingDialogListener, ImageView imageView, View view) {
        dialog.cancel();
        setCallingDialogListener.onClick(imageView.isSelected());
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static /* synthetic */ boolean lambda$show$4(DialogInterface dialogInterface, int i, KeyEvent keyEvent) {
        if (i == 4 && keyEvent.getRepeatCount() == 0) {
            dialogInterface.cancel();
            return true;
        }
        return false;
    }

    public static void show(Activity activity, String str, String str2) {
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
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.30
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            ((Button) inflate.findViewById(2131296781)).setVisibility(8);
            button.setBackgroundResource(2131231203);
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.31
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

    public static void show(Activity activity, String str, String str2, String str3, String str4, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(17);
            textView2.setTextColor(Color.parseColor("#666666"));
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setTypeface(null, 1);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.32
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            button2.setVisibility(0);
            if (!TextUtils.isEmpty(str3)) {
                button2.setText(str3);
            }
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.33
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.34
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

    public static void show(Activity activity, String str, String str2, String str3, String str4, boolean z, String str5, final CustomeDialogCheckBoxListener customeDialogCheckBoxListener) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493441, (ViewGroup) null);
            final CheckBox checkBox = (CheckBox) inflate.findViewById(2131298584);
            ((TextView) inflate.findViewById(2131296614)).setText(str5);
            LinearLayout linearLayout = (LinearLayout) inflate.findViewById(2131296609);
            if (z) {
                linearLayout.setVisibility(0);
            } else {
                linearLayout.setVisibility(8);
            }
            linearLayout.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.35
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    try {
                        if (checkBox != null) {
                            checkBox.setChecked(!checkBox.isChecked());
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.36
                @Override // android.widget.CompoundButton.OnCheckedChangeListener
                public void onCheckedChanged(CompoundButton compoundButton, boolean z2) {
                    Tracker.onCheckedChanged(compoundButton, z2);
                    CustomeDialogCheckBoxListener customeDialogCheckBoxListener2 = CustomeDialogCheckBoxListener.this;
                    if (customeDialogCheckBoxListener2 != null) {
                        customeDialogCheckBoxListener2.onCheck(z2);
                    }
                }
            });
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(17);
            textView2.setTextColor(Color.parseColor("#666666"));
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setTypeface(null, 1);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.37
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogCheckBoxListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            button2.setVisibility(0);
            if (!TextUtils.isEmpty(str3)) {
                button2.setText(str3);
            }
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.38
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogCheckBoxListener.onCancle();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.39
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

    public static void show(Activity activity, String str, String str2, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            TextView textView = (TextView) inflate.findViewById(2131296785);
            textView.setGravity(17);
            textView.setTextColor(Color.parseColor("#333333"));
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str2)) {
                button.setText(str2);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.40
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            button2.setVisibility(0);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.41
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.42
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

    public static void show(Activity activity, String str, String str2, ADCallBack aDCallBack, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            TextView textView = (TextView) inflate.findViewById(2131296785);
            textView.setGravity(17);
            textView.setTextColor(Color.parseColor("#333333"));
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str2)) {
                button.setText(str2);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.43
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            button2.setVisibility(0);
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.44
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            if (aDCallBack != null) {
                aDCallBack.addAdView(inflate);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.45
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

    public static void show(Activity activity, String str, String str2, int i, boolean z, String str3, String str4, final boolean z2, boolean z3, boolean z4, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
        View inflate;
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            float f = displayMetrics.density;
            int i2 = displayMetrics.densityDpi;
            float f2 = displayMetrics.xdpi;
            float f3 = displayMetrics.ydpi;
            int i3 = displayMetrics.widthPixels;
            int i4 = displayMetrics.heightPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i3;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            if (z3) {
                inflate = activity.getLayoutInflater().inflate(2131493443, (ViewGroup) null);
            } else {
                inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            }
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(i);
            if (z4) {
                textView2.setMaxHeight(UIUtils.dip2px(activity, 280.0f));
                textView2.setVerticalScrollBarEnabled(true);
                textView2.setMovementMethod(ScrollingMovementMethod.getInstance());
            }
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.46
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.47
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.48
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i5, KeyEvent keyEvent) {
                    if (i5 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                            return true;
                        }
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

    public static void showSkinDialog(Activity activity, String str, boolean z, String str2, String str3, final boolean z2, final SimpleCustomeDialogListener2 simpleCustomeDialogListener2) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            ((TextView) inflate.findViewById(2131296788)).setVisibility(8);
            TextView textView = (TextView) inflate.findViewById(2131296785);
            int dip2px = UIUtils.dip2px(25.0f);
            textView.setPadding(dip2px, dip2px, dip2px, dip2px);
            textView.setGravity(17);
            textView.setTextColor(Color.parseColor("#333333"));
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            button.setTextColor(Color.parseColor("#E60027"));
            if (!TextUtils.isEmpty(str3)) {
                button.setText(str3);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.49
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener2.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setTextColor(Color.parseColor("#666666"));
            if (z) {
                button2.setVisibility(0);
                if (!TextUtils.isEmpty(str2)) {
                    button2.setText(str2);
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.50
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.51
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    if (i2 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                            return true;
                        }
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

    public static void show(Activity activity, final CustomeDialogListener customeDialogListener) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            textView.setText("提示");
            textView.setVisibility(0);
            ((TextView) inflate.findViewById(2131296785)).setText("检测到您更换了手机，需要您重新打开联通APP");
            Button button = (Button) inflate.findViewById(2131296787);
            button.setText("确定");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.52
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            ((Button) inflate.findViewById(2131296781)).setVisibility(8);
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.53
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onShow();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.54
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onCancel();
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void showZhuXiao(Activity activity, String str, String str2, boolean z, String str3, String str4, final boolean z2, boolean z3, final CustomeDialogListener customeDialogListener) {
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
            Window window = dialog.getWindow();
            window.setAttributes(attributes);
            window.setDimAmount(0.6f);
            window.addFlags(2);
            View inflate = activity.getLayoutInflater().inflate(2131493442, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            if (!TextUtils.isEmpty(str)) {
                textView.setText(str);
            }
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            if (!TextUtils.isEmpty(str2)) {
                textView2.setText(str2);
            }
            Button button = (Button) inflate.findViewById(2131296787);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.55
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    customeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button2 = (Button) inflate.findViewById(2131296781);
            if (z) {
                button2.setVisibility(0);
                button.setBackgroundResource(2131231200);
                if (!TextUtils.isEmpty(str3)) {
                    button2.setText(str3);
                    button2.setTextColor(Color.parseColor("#333333"));
                }
                button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.56
                    @Override // android.view.View.OnClickListener
                    public void onClick(View view) {
                        NBSActionInstrumentation.onClickEventEnter(view, this);
                        Tracker.onClick(view);
                        dialog.cancel();
                        customeDialogListener.onClickCancel();
                        NBSActionInstrumentation.onClickEventExit();
                    }
                });
            } else {
                button2.setVisibility(8);
                button.setBackgroundResource(2131231203);
            }
            dialog.setContentView(inflate);
            dialog.setCancelable(true);
            dialog.setCanceledOnTouchOutside(z3);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.57
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onShow();
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.58
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                    CustomeDialogListener.this.onCancel();
                }
            });
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.59
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i4, KeyEvent keyEvent) {
                    if (i4 == 4 && keyEvent.getRepeatCount() == 0) {
                        if (z2) {
                            dialogInterface.cancel();
                        }
                        customeDialogListener.onBackKeyDown();
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

    public static void showUserDialog(Activity activity, String str, String str2, String str3, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            TextView textView = (TextView) inflate.findViewById(2131296788);
            textView.setText(str);
            textView.setTextColor(-13421773);
            textView.setTextSize(1, 16.0f);
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(17);
            textView2.setText(str2);
            textView2.setTextColor(-10066330);
            textView2.setTextSize(1, 13.0f);
            Button button = (Button) inflate.findViewById(2131296787);
            if (TextUtils.isEmpty(str3)) {
                str3 = "知道了";
            }
            button.setText(str3);
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.60
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            ((Button) inflate.findViewById(2131296781)).setVisibility(8);
            button.setBackgroundResource(2131231203);
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.61
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

    public static void showUserOrderDialog(Activity activity, String str, String str2, final SimpleCustomeDialogListener simpleCustomeDialogListener) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493444, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            textView.setText(str);
            textView.setTextColor(-13421773);
            textView.setTextSize(1, 16.0f);
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            textView2.setGravity(3);
            textView2.setText(str2);
            textView2.setTextColor(-10066330);
            textView2.setTextSize(1, 13.0f);
            Button button = (Button) inflate.findViewById(2131296787);
            button.setText("知道了");
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.62
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    simpleCustomeDialogListener.onClickOk();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            ((Button) inflate.findViewById(2131296781)).setVisibility(8);
            button.setBackgroundResource(2131231203);
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.63
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

    public static void showFuWu(Activity activity, final SimpleCustomeDialogListener2 simpleCustomeDialogListener2) {
        try {
            final Dialog dialog = new Dialog(activity, 2131951850);
            DisplayMetrics displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int i = displayMetrics.widthPixels;
            WindowManager.LayoutParams attributes = dialog.getWindow().getAttributes();
            double d = i;
            attributes.width = (int) (0.6d * d);
            attributes.height = -2;
            dialog.getWindow().setAttributes(attributes);
            View inflate = activity.getLayoutInflater().inflate(2131493138, (ViewGroup) null);
            TextView textView = (TextView) inflate.findViewById(2131296788);
            TextView textView2 = (TextView) inflate.findViewById(2131296785);
            Button button = (Button) inflate.findViewById(2131296787);
            button.setText("不保存");
            Button button2 = (Button) inflate.findViewById(2131296781);
            button2.setText("保存");
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.64
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    SimpleCustomeDialogListener2 simpleCustomeDialogListener22 = SimpleCustomeDialogListener2.this;
                    if (simpleCustomeDialogListener22 != null) {
                        simpleCustomeDialogListener22.onClickOk();
                    }
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.65
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    SimpleCustomeDialogListener2 simpleCustomeDialogListener22 = SimpleCustomeDialogListener2.this;
                    if (simpleCustomeDialogListener22 != null) {
                        simpleCustomeDialogListener22.onCancel();
                    }
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnKeyListener(new DialogInterface.OnKeyListener() { // from class: com.sinovatech.unicom.basic.view.CustomDialogManager.66
                @Override // android.content.DialogInterface.OnKeyListener
                public boolean onKey(DialogInterface dialogInterface, int i2, KeyEvent keyEvent) {
                    return i2 == 4 && keyEvent.getRepeatCount() == 0;
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
