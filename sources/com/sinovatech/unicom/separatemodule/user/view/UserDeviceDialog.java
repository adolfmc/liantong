package com.sinovatech.unicom.separatemodule.user.view;

import android.app.Activity;
import android.app.Dialog;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.alibaba.android.arouter.utils.TextUtils;
import com.bytedance.applog.tracker.Tracker;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.basic.p315ui.view.date_time_picker.DateTimePicker;
import com.sinovatech.unicom.common.DateUtils;
import com.sinovatech.unicom.separatemodule.collect.CollectUtils;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import com.sinovatech.unicom.separatemodule.user.manager.DialogCallBack;
import com.sinovatech.unicom.separatemodule.user.manager.ManagerUserDevice;
import java.text.SimpleDateFormat;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class UserDeviceDialog {
    public static long time = ManagerUserDevice.getInstance().getDeviceTime();
    public static boolean isRefresh = false;

    public static void showDatePickerDialog(Activity activity, String str, final DialogCallBack dialogCallBack) {
        try {
            isRefresh = false;
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
            View inflate = activity.getLayoutInflater().inflate(2131493480, (ViewGroup) null);
            dialog.setContentView(inflate);
            TextView textView = (TextView) inflate.findViewById(2131298517);
            final DateTimePicker dateTimePicker = (DateTimePicker) inflate.findViewById(2131299853);
            dateTimePicker.setDefaultMillisecond(System.currentTimeMillis());
            dateTimePicker.setMaxMillisecond(System.currentTimeMillis());
            dateTimePicker.setMinMillisecond(DateUtils.getYearTimeStamp(2000));
            final CollectUtils collectUtils = new CollectUtils();
            dateTimePicker.setOnDateTimeChangedListener(new Function1<Long, Unit>() { // from class: com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog.1
                @Override // kotlin.jvm.functions.Function1
                public Unit invoke(Long l) {
                    MsLogUtil.m7979d("时间戳", "==" + l + "==" + CollectUtils.this.formatTime(l.longValue(), "yyyy-MM-dd"));
                    if (dateTimePicker.getVisibility() == 0) {
                        UserDeviceDialog.time = l.longValue();
                        UserDeviceDialog.isRefresh = true;
                        return null;
                    }
                    return null;
                }
            });
            inflate.findViewById(2131298202).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                        DialogCallBack dialogCallBack2 = dialogCallBack;
                        if (dialogCallBack2 != null) {
                            dialogCallBack2.dismiss(false);
                        }
                        ManagerUserDevice.getInstance().simpleClickLog("5170106", "", "取消编辑时间", "取消编辑时间");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            inflate.findViewById(2131298712).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    ManagerUserDevice.getInstance().setDeviceTime(UserDeviceDialog.time);
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                        DialogCallBack dialogCallBack2 = dialogCallBack;
                        if (dialogCallBack2 != null) {
                            dialogCallBack2.dismiss(UserDeviceDialog.isRefresh);
                        }
                        ManagerUserDevice.getInstance().simpleClickLog("5170105", "", "变更保存时间", "变更保存时间");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            inflate.findViewById(2131298512).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog.4
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    if (DateTimePicker.this.getVisibility() == 0) {
                        DateTimePicker.this.setVisibility(8);
                    } else {
                        DateTimePicker.this.setVisibility(0);
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            long deviceTime = ManagerUserDevice.getInstance().getDeviceTime();
            if (deviceTime == 0) {
                if (TextUtils.isEmpty(str)) {
                    textView.setText(collectUtils.formatTime(System.currentTimeMillis(), "yyyy-MM-dd"));
                } else {
                    try {
                        textView.setText(new SimpleDateFormat("yyyy-MM-dd").format(new SimpleDateFormat("yyyyMMdd").parse(str)));
                    } catch (Exception e) {
                        e.printStackTrace();
                        textView.setText(collectUtils.formatTime(System.currentTimeMillis(), "yyyy-MM-dd"));
                    }
                }
            } else {
                textView.setText(collectUtils.formatTime(deviceTime, "yyyy-MM-dd"));
            }
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    public static void showDialog(final Activity activity, String str, String str2, String str3, String str4, String str5, final String str6, final DialogCallBack dialogCallBack) {
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
            View inflate = activity.getLayoutInflater().inflate(2131493481, (ViewGroup) null);
            dialog.setContentView(inflate);
            Button button = (Button) inflate.findViewById(2131298120);
            Button button2 = (Button) inflate.findViewById(2131299854);
            TextView textView = (TextView) inflate.findViewById(2131296708);
            if (!TextUtils.isEmpty(str4)) {
                button.setText(str4);
            }
            if (!TextUtils.isEmpty(str5)) {
                button2.setText(str5);
            }
            if (!TextUtils.isEmpty(str2)) {
                textView.setText(str2);
            }
            button.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                        UserDeviceDialog.showDatePickerDialog(activity, str6, dialogCallBack);
                        DialogCallBack dialogCallBack2 = dialogCallBack;
                        if (dialogCallBack2 != null) {
                            dialogCallBack2.dismiss(false);
                        }
                        ManagerUserDevice.getInstance().simpleClickLog("5170102", "", "编辑时间弹窗否", "编辑时间弹窗否");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            button2.setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.user.view.UserDeviceDialog.6
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    Dialog dialog2 = dialog;
                    if (dialog2 != null) {
                        dialog2.dismiss();
                        DialogCallBack dialogCallBack2 = dialogCallBack;
                        if (dialogCallBack2 != null) {
                            dialogCallBack2.dismiss(false);
                        }
                        ManagerUserDevice.getInstance().simpleClickLog("5170103", "", "编辑时间弹窗是", "编辑时间弹窗是");
                    }
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
