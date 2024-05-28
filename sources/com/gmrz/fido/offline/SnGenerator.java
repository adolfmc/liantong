package com.gmrz.fido.offline;

import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.fido.offline.g */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SnGenerator {

    /* renamed from: a */
    public static String f10364a;

    /* renamed from: a */
    private static String m15727a() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    /* renamed from: b */
    public static String m15726b() {
        String str = f10364a;
        if (str != null) {
            return str;
        }
        String m15727a = m15727a();
        f10364a = m15727a;
        return m15727a;
    }
}
