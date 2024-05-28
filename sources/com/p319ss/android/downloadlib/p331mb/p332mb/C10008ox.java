package com.p319ss.android.downloadlib.p331mb.p332mb;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.addownload.C9940x;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.mb.mb.ox */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C10008ox {
    /* renamed from: mb */
    public static String m7188mb(Context context) {
        try {
            return m7187mb(C10006b.m7192mb(m7189mb(), "MD5"));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: mb */
    public static String m7189mb() {
        return m7186ox(C9940x.getContext());
    }

    /* renamed from: ox */
    public static String m7186ox(Context context) {
        String str;
        try {
            str = Settings.Secure.getString(context.getContentResolver(), "android_id");
        } catch (Exception e) {
            e.printStackTrace();
            str = null;
        }
        return TextUtils.isEmpty(str) ? "normal" : str;
    }

    /* renamed from: mb */
    public static String m7187mb(byte[] bArr) {
        return C10007mb.m7190mb(bArr);
    }
}
