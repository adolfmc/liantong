package com.megvii.lv5;

import android.content.Context;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.x2 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5651x2 {

    /* renamed from: a */
    public static Context f13896a;

    /* renamed from: b */
    public static C5651x2 f13897b;

    /* renamed from: c */
    public static File f13898c;

    /* renamed from: e */
    public static String f13900e;

    /* renamed from: d */
    public static SimpleDateFormat f13899d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    /* renamed from: f */
    public static boolean f13901f = true;

    /* renamed from: a */
    public static void m12902a(Object obj) {
        File file;
        String str;
        if (!f13901f || f13896a == null || f13897b == null || (file = f13898c) == null || !file.exists()) {
            return;
        }
        StringBuilder sb = new StringBuilder();
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        if (stackTrace != null) {
            for (StackTraceElement stackTraceElement : stackTrace) {
                if (!stackTraceElement.isNativeMethod() && !stackTraceElement.getClassName().equals(Thread.class.getName()) && !stackTraceElement.getClassName().equals(f13897b.getClass().getName())) {
                    f13900e = stackTraceElement.getFileName();
                    str = "[" + f13899d.format(new Date()) + " ThreadId:" + Thread.currentThread().getId() + " " + stackTraceElement.getClassName() + " " + stackTraceElement.getMethodName() + " Line:" + stackTraceElement.getLineNumber() + "]";
                    break;
                }
            }
        }
        str = null;
        sb.append(str);
        sb.append(" - ");
        sb.append(obj.toString());
        String sb2 = sb.toString();
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(f13898c, true));
            bufferedWriter.write(sb2);
            bufferedWriter.write("\r\n");
            bufferedWriter.flush();
        } catch (Exception e) {
            String str2 = "Write failure !!! " + e.toString();
        }
    }
}
