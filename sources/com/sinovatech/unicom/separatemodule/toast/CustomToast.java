package com.sinovatech.unicom.separatemodule.toast;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;
import java.lang.reflect.Field;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class CustomToast {
    private static Field sField_TN;
    private static Field sField_TN_Handler;
    private Toast mToast;

    static {
        try {
            sField_TN = Toast.class.getDeclaredField("mTN");
            sField_TN.setAccessible(true);
            sField_TN_Handler = sField_TN.getType().getDeclaredField("mHandler");
            sField_TN_Handler.setAccessible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void hook(Toast toast) {
        try {
            Object obj = sField_TN.get(toast);
            sField_TN_Handler.set(obj, new SafelyHandlerWarpper((Handler) sField_TN_Handler.get(obj)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static CustomToast make() {
        return new CustomToast();
    }

    public void showToast(Context context, CharSequence charSequence, int i) {
        showToast(context, charSequence, i, false);
    }

    public void showToastCenter(Context context, CharSequence charSequence, int i) {
        showToast(context, charSequence, i, true);
    }

    public void showToast(Context context, CharSequence charSequence, int i, boolean z) {
        if (context != null) {
            try {
                if (TextUtils.isEmpty(charSequence)) {
                    return;
                }
                this.mToast = Toast.makeText(context, charSequence, i);
                if (z) {
                    this.mToast.setGravity(17, 0, 0);
                }
                hook(this.mToast);
                this.mToast.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void showToastView(Context context, View view, int i) {
        if (context == null || view == null) {
            return;
        }
        try {
            this.mToast = new Toast(context);
            this.mToast.setGravity(17, 0, 0);
            this.mToast.setDuration(i);
            this.mToast.setView(view);
            hook(this.mToast);
            this.mToast.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static class SafelyHandlerWarpper extends Handler {
        private Handler impl;

        public SafelyHandlerWarpper(Handler handler) {
            this.impl = handler;
        }

        @Override // android.os.Handler
        public void dispatchMessage(Message message) {
            try {
                super.dispatchMessage(message);
            } catch (Exception unused) {
            }
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            try {
                this.impl.handleMessage(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
