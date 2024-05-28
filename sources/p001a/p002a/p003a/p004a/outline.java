package p001a.p002a.p003a.p004a;

import android.util.Log;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class outline {
    /* renamed from: a */
    public static StringBuilder m24437a(String str) {
        StringBuilder sb = new StringBuilder();
        sb.append(str);
        return sb;
    }

    /* renamed from: a */
    public static void m24438a(Exception exc, StringBuilder sb, String str) {
        sb.append(exc.getMessage());
        Log.d(str, sb.toString());
    }
}
