package com.alipay.sdk.widget;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.text.TextUtils;
import com.alipay.sdk.util.C2040c;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.widget.e */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2063e {

    /* renamed from: a */
    private static boolean f3939a;

    static {
        f3939a = Build.VERSION.SDK_INT >= 11;
    }

    /* renamed from: a */
    public static Dialog m20608a(Context context, String str, String str2, String str3, DialogInterface.OnClickListener onClickListener, String str4, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder m20609a = m20609a(context, str, str3, onClickListener, str4, onClickListener2);
        m20609a.setTitle(str);
        m20609a.setMessage(str2);
        AlertDialog create = m20609a.create();
        create.setCanceledOnTouchOutside(false);
        create.setOnKeyListener(new DialogInterface$OnKeyListenerC2064f());
        try {
            create.show();
        } catch (Throwable th) {
            C2040c.m20716a("msp", th);
        }
        return create;
    }

    /* renamed from: a */
    private static AlertDialog.Builder m20609a(Context context, String str, String str2, DialogInterface.OnClickListener onClickListener, String str3, DialogInterface.OnClickListener onClickListener2) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        if (f3939a) {
            if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
                builder.setPositiveButton(str3, onClickListener2);
            }
            if (!TextUtils.isEmpty(str2) && onClickListener != null) {
                builder.setNegativeButton(str2, onClickListener);
            }
        } else {
            if (!TextUtils.isEmpty(str2) && onClickListener != null) {
                builder.setPositiveButton(str2, onClickListener);
            }
            if (!TextUtils.isEmpty(str3) && onClickListener2 != null) {
                builder.setNegativeButton(str3, onClickListener2);
            }
        }
        return builder;
    }
}
