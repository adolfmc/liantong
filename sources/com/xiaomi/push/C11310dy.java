package com.xiaomi.push;

import android.util.Pair;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.dy */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11310dy {

    /* renamed from: a */
    private static Vector<Pair<String, Long>> f22035a = new Vector<>();

    /* renamed from: a */
    private static ConcurrentHashMap<String, Long> f22036a = new ConcurrentHashMap<>();

    /* renamed from: a */
    public static String m4069a() {
        StringBuilder sb = new StringBuilder();
        synchronized (f22035a) {
            for (int i = 0; i < f22035a.size(); i++) {
                Pair<String, Long> elementAt = f22035a.elementAt(i);
                sb.append((String) elementAt.first);
                sb.append(":");
                sb.append(elementAt.second);
                if (i < f22035a.size() - 1) {
                    sb.append(";");
                }
            }
            f22035a.clear();
        }
        return sb.toString();
    }
}
