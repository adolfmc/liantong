package com.p319ss.android.socialbase.appdownloader.p340u;

import android.text.TextUtils;
import android.util.Base64;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.u.b */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10150b {
    /* renamed from: mb */
    public static String m6594mb(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        byte[] decode = Base64.decode(DownloadUtils.hexToString(str), 0);
        int length = str2.length();
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (byte b : decode) {
            if (i >= length) {
                i %= length;
            }
            sb.append((char) (b ^ str2.charAt(i)));
            i++;
        }
        return sb.toString();
    }

    /* renamed from: mb */
    public static String m6595mb(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        return new String(Base64.decode(DownloadUtils.hexToString(str), 0));
    }
}
