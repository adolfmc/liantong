package com.tydic.softphone.utils;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.widget.Toast;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class ToastUtils {
    public static final int Toast_Position_center = 1;
    public static final int Toast_Position_default = 0;
    public static final int Toast_Position_top = 2;
    private static Toast mToast;

    public static Toast showToast(Context context, String str, int i) {
        if (mToast == null) {
            mToast = Toast.makeText(context, str, 0);
        }
        setToastPos((Activity) context, i);
        mToast.show();
        return mToast;
    }

    private static Toast setToastPos(Activity activity, int i) {
        switch (i) {
            case 0:
                mToast.setGravity(80, 0, activity.getWindowManager().getDefaultDisplay().getHeight() / 9);
                break;
            case 1:
                mToast.setGravity(17, 0, 0);
                break;
            case 2:
                setTopToast(activity, activity.getCurrentFocus());
                break;
        }
        return mToast;
    }

    private static void setTopToast(Activity activity, View view) {
        mToast.setGravity(48, 0, activity.getWindowManager().getDefaultDisplay().getHeight() / 4);
    }

    public static void destoryToast() {
        mToast = null;
    }
}
