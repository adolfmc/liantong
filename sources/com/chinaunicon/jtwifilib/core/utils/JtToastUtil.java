package com.chinaunicon.jtwifilib.core.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class JtToastUtil {
    public static final int SHOW_TOAST = 0;
    private static Handler baseHandler = new Handler() { // from class: com.chinaunicon.jtwifilib.core.utils.JtToastUtil.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (message.what != 0) {
                return;
            }
            JtToastUtil.showShort(JtToastUtil.mContext, message.getData().getString("TEXT"));
        }
    };
    private static Context mContext;
    private static Toast toast;

    public static void showToastInThread(Context context, int i) {
        mContext = context;
        Message obtainMessage = baseHandler.obtainMessage(0);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", context.getResources().getString(i));
        obtainMessage.setData(bundle);
        baseHandler.sendMessage(obtainMessage);
    }

    public static void showToastInThread(Context context, String str) {
        mContext = context;
        Message obtainMessage = baseHandler.obtainMessage(0);
        Bundle bundle = new Bundle();
        bundle.putString("TEXT", str);
        obtainMessage.setData(bundle);
        baseHandler.sendMessage(obtainMessage);
    }

    public static void showShort(Context context, CharSequence charSequence) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(context, charSequence, 0);
        } else {
            toast2.setText(charSequence);
        }
        toast.show();
    }

    public static void showShort(Context context, int i) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(context, i, 0);
        } else {
            toast2.setText(i);
        }
        toast.show();
    }

    public static void showLong(Context context, CharSequence charSequence) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(context, charSequence, 1);
        } else {
            toast2.setText(charSequence);
        }
        toast.show();
    }

    public static void showLong(Context context, int i) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(context, i, 1);
        } else {
            toast2.setText(i);
        }
        toast.show();
    }

    public static void show(Context context, CharSequence charSequence, int i) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(context, charSequence, i);
        } else {
            toast2.setText(charSequence);
        }
        toast.show();
    }

    public static void show(Context context, int i, int i2) {
        Toast toast2 = toast;
        if (toast2 == null) {
            toast = Toast.makeText(context, i, i2);
        } else {
            toast2.setText(i);
        }
        toast.show();
    }

    public static void hideToast() {
        Toast toast2 = toast;
        if (toast2 != null) {
            toast2.cancel();
        }
    }
}
