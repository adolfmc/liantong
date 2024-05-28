package com.sinovatech.unicom.separatemodule.miniprogram.utils;

import android.content.Context;
import android.widget.Toast;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class FastToast {
    private static Toast mToast;

    private FastToast() {
    }

    public static void showText(Context context, String str) {
        try {
            if (mToast != null) {
                mToast.cancel();
            }
            mToast = Toast.makeText(context, str, 0);
            mToast.setGravity(17, 0, 0);
            mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
