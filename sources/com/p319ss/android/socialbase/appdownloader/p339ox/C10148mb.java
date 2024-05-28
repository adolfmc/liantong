package com.p319ss.android.socialbase.appdownloader.p339ox;

import android.os.Build;
import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.appdownloader.ox.mb */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C10148mb {

    /* renamed from: mb */
    public static final String f19563mb;

    static {
        StringBuilder sb = new StringBuilder();
        boolean z = true;
        boolean z2 = !TextUtils.isEmpty(Build.VERSION.RELEASE);
        boolean z3 = !TextUtils.isEmpty(Build.ID);
        z = (!"REL".equals(Build.VERSION.CODENAME) || TextUtils.isEmpty(Build.MODEL)) ? false : false;
        sb.append("AppDownloader");
        if (z2) {
            sb.append("/");
            sb.append(Build.VERSION.RELEASE);
        }
        sb.append(" (Linux; U; Android");
        if (z2) {
            sb.append(" ");
            sb.append(Build.VERSION.RELEASE);
        }
        if (z || z3) {
            sb.append(";");
            if (z) {
                sb.append(" ");
                sb.append(Build.MODEL);
            }
            if (z3) {
                sb.append(" Build/");
                sb.append(Build.ID);
            }
        }
        sb.append(")");
        f19563mb = sb.toString();
    }
}
