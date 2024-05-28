package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.bumptech.glide.request.RequestOptions;
import com.bytedance.applog.tracker.Tracker;
import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.networkbench.agent.impl.instrumentation.NBSActionInstrumentation;
import com.sinovatech.unicom.p318ui.GlideApp;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.entity.FileModel;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FileUtils {
    public static boolean isAgree(FileModel fileModel, String str) {
        int isType;
        if (fileModel == null || TextUtils.isEmpty(fileModel.getFileName()) || (isType = isType(fileModel)) == -1) {
            return false;
        }
        return isFuHe(str, isType);
    }

    public static int isType(FileModel fileModel) {
        if (fileModel != null) {
            String fileName = fileModel.getFileName();
            if (TextUtils.isEmpty(fileName)) {
                return -1;
            }
            if (fileName.endsWith(".jpg") || fileName.endsWith(".png")) {
                return 1;
            }
            if (fileName.endsWith(".gif")) {
                return 2;
            }
            if (fileName.endsWith(".mp4")) {
                return 3;
            }
            if (fileName.endsWith(".doc")) {
                return 4;
            }
            if (fileName.endsWith(".txt")) {
                return 5;
            }
            if (fileName.endsWith(".pdf")) {
                return 6;
            }
            return (fileName.endsWith(".xls") || fileName.endsWith(".xlsx")) ? 7 : -1;
        }
        return -1;
    }

    public static boolean isFuHe(String str, int i) {
        char c;
        int hashCode = str.hashCode();
        if (hashCode == 96673) {
            if (str.equals("all")) {
                c = 0;
            }
            c = 65535;
        } else if (hashCode == 3143036) {
            if (str.equals("file")) {
                c = 3;
            }
            c = 65535;
        } else if (hashCode != 100313435) {
            if (hashCode == 112202875 && str.equals("video")) {
                c = 2;
            }
            c = 65535;
        } else {
            if (str.equals("image")) {
                c = 1;
            }
            c = 65535;
        }
        switch (c) {
            case 0:
                return true;
            case 1:
                if (i == 1 || i == 2) {
                    return true;
                }
            case 2:
                if (i == 3) {
                    return true;
                }
                break;
            case 3:
                if (i == 4 || i == 5 || i == 6 || i == 7) {
                    return true;
                }
                break;
        }
        return false;
    }

    public static RequestOptions gildeOptions() {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.fitCenter().override(200, 200);
        return requestOptions;
    }

    public static String ShowLongFileSzie(Long l) {
        if (l.longValue() >= 1048576) {
            return geShi(l.longValue() / 1048576.0d) + " MB";
        } else if (l.longValue() >= 1024) {
            return geShi(l.longValue() / 1024.0d) + " KB";
        } else if (l.longValue() < 1024) {
            return geShi(l.longValue()) + " B";
        } else {
            return "0.0 KB";
        }
    }

    public static String formatTime(long j) {
        SimpleDateFormat simpleDateFormat;
        Date date = new Date(j);
        if (j / 1000 <= 3600) {
            simpleDateFormat = new SimpleDateFormat("mm:ss");
        } else {
            simpleDateFormat = new SimpleDateFormat(JtDateUtil.dateFormatHMS);
        }
        return simpleDateFormat.format(date);
    }

    public static void clearMemoryCache(Context context) {
        System.gc();
        GlideApp.get(context).clearMemory();
    }

    public static String geShi(double d) {
        return String.format("%.1f", Double.valueOf(d));
    }

    public static Dialog showDialog(Activity activity) {
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
            ((Button) inflate.findViewById(2131296787)).setOnClickListener(new View.OnClickListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    NBSActionInstrumentation.onClickEventEnter(view, this);
                    Tracker.onClick(view);
                    dialog.cancel();
                    NBSActionInstrumentation.onClickEventExit();
                }
            });
            Button button = (Button) inflate.findViewById(2131296781);
            dialog.setContentView(inflate);
            dialog.setCancelable(false);
            dialog.setCanceledOnTouchOutside(false);
            dialog.setOnShowListener(new DialogInterface.OnShowListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils.2
                @Override // android.content.DialogInterface.OnShowListener
                public void onShow(DialogInterface dialogInterface) {
                }
            });
            dialog.setOnCancelListener(new DialogInterface.OnCancelListener() { // from class: com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.selectwechatfile.utils.FileUtils.3
                @Override // android.content.DialogInterface.OnCancelListener
                public void onCancel(DialogInterface dialogInterface) {
                }
            });
            dialog.show();
            dialog.getWindow().setLayout((int) (d * 0.8d), -2);
            dialog.hide();
            return dialog;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
