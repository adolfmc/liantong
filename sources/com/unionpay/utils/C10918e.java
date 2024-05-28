package com.unionpay.utils;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.unionpay.utils.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10918e {
    /* renamed from: a */
    public static String m5840a(Context context) {
        String str = "";
        try {
            if (context instanceof Activity) {
                str = ((Activity) context).getPackageName();
            }
            return str == null ? "" : str;
        } catch (Exception unused) {
            return str;
        }
    }

    /* renamed from: b */
    public static String m5839b(Context context) {
        if (context != null) {
            try {
                String m5870a = UPUtils.m5870a(context, "merchant_id");
                if (TextUtils.isEmpty(m5870a)) {
                    m5870a = UUID.randomUUID().toString();
                    if (!TextUtils.isEmpty(m5870a)) {
                        m5870a = m5870a.replaceAll("-", "");
                        UPUtils.m5869a(context, m5870a, "merchant_id");
                    }
                }
                return m5870a;
            } catch (Exception unused) {
                return "";
            }
        }
        return "";
    }
}
