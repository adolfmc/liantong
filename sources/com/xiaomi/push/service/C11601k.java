package com.xiaomi.push.service;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.push.C11184bb;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.k */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11601k {

    /* renamed from: a */
    private static final Map<String, Long> f23685a = new HashMap();

    /* renamed from: a */
    public static boolean m2522a(byte[] bArr, String str) {
        boolean z = false;
        if (bArr != null && bArr.length > 0 && !TextUtils.isEmpty(str)) {
            String m4751a = C11184bb.m4751a(bArr);
            if (!TextUtils.isEmpty(m4751a)) {
                synchronized (f23685a) {
                    if (f23685a.get(m4751a + str) != null) {
                        z = true;
                    } else {
                        f23685a.put(m4751a + str, Long.valueOf(SystemClock.elapsedRealtime()));
                    }
                    m2523a();
                }
            }
        }
        return z;
    }

    /* renamed from: a */
    private static void m2523a() {
        long elapsedRealtime = SystemClock.elapsedRealtime();
        ArrayList<String> arrayList = new ArrayList(f23685a.size());
        for (Map.Entry<String, Long> entry : f23685a.entrySet()) {
            if (elapsedRealtime - entry.getValue().longValue() > 60000) {
                arrayList.add(entry.getKey());
            }
        }
        for (String str : arrayList) {
            f23685a.remove(str);
        }
    }
}
