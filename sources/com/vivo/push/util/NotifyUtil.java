package com.vivo.push.util;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class NotifyUtil {
    private static BaseNotifyDataAdapter sNotifyData = null;
    private static String sNotifyDataAdapter = "com.vivo.push.util.NotifyDataAdapter";
    private static BaseNotifyLayoutAdapter sNotifyLayout = null;
    private static String sNotifyLayoutAdapter = "com.vivo.push.util.NotifyLayoutAdapter";

    /* JADX WARN: Removed duplicated region for block: B:11:0x0011  */
    /* JADX WARN: Removed duplicated region for block: B:18:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.Object getObjectByReflect(java.lang.String r1, java.lang.Object r2) {
        /*
            r0 = 0
            java.lang.Class r1 = java.lang.Class.forName(r1)     // Catch: java.lang.Exception -> L6
            goto L7
        L6:
            r1 = r0
        L7:
            if (r1 == 0) goto Le
            java.lang.Object r1 = r1.newInstance()     // Catch: java.lang.Exception -> Le
            goto Lf
        Le:
            r1 = r0
        Lf:
            if (r1 != 0) goto L12
            r1 = r2
        L12:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.vivo.push.util.NotifyUtil.getObjectByReflect(java.lang.String, java.lang.Object):java.lang.Object");
    }

    private static synchronized void initAdapter(Context context) {
        synchronized (NotifyUtil.class) {
            if (sNotifyData == null) {
                BaseNotifyDataAdapter baseNotifyDataAdapter = (BaseNotifyDataAdapter) getObjectByReflect(sNotifyDataAdapter, new DefaultNotifyDataAdapter());
                sNotifyData = baseNotifyDataAdapter;
                baseNotifyDataAdapter.init(context);
            }
            if (sNotifyLayout == null) {
                BaseNotifyLayoutAdapter baseNotifyLayoutAdapter = (BaseNotifyLayoutAdapter) getObjectByReflect(sNotifyLayoutAdapter, new DefaultNotifyLayoutAdapter());
                sNotifyLayout = baseNotifyLayoutAdapter;
                baseNotifyLayoutAdapter.init(context);
            }
        }
    }

    public static BaseNotifyDataAdapter getNotifyDataAdapter(Context context) {
        initAdapter(context);
        return sNotifyData;
    }

    public static BaseNotifyLayoutAdapter getNotifyLayoutAdapter(Context context) {
        initAdapter(context);
        return sNotifyLayout;
    }
}
