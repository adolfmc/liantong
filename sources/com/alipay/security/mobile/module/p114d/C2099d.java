package com.alipay.security.mobile.module.p114d;

import com.alipay.security.mobile.module.p110a.C2081a;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.d.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2099d {

    /* renamed from: a */
    private static String f4002a = "";

    /* renamed from: b */
    private static String f4003b = "";

    /* renamed from: c */
    private static String f4004c = "";

    /* renamed from: a */
    public static synchronized void m20480a(String str) {
        synchronized (C2099d.class) {
            ArrayList arrayList = new ArrayList();
            arrayList.add(str);
            m20477a(arrayList);
        }
    }

    /* renamed from: a */
    public static synchronized void m20479a(String str, String str2, String str3) {
        synchronized (C2099d.class) {
            f4002a = str;
            f4003b = str2;
            f4004c = str3;
        }
    }

    /* renamed from: a */
    public static synchronized void m20478a(Throwable th) {
        String str;
        synchronized (C2099d.class) {
            ArrayList arrayList = new ArrayList();
            if (th != null) {
                StringWriter stringWriter = new StringWriter();
                th.printStackTrace(new PrintWriter(stringWriter));
                str = stringWriter.toString();
            } else {
                str = "";
            }
            arrayList.add(str);
            m20477a(arrayList);
        }
    }

    /* renamed from: a */
    private static synchronized void m20477a(List<String> list) {
        synchronized (C2099d.class) {
            if (!C2081a.m20577a(f4003b) && !C2081a.m20577a(f4004c)) {
                StringBuffer stringBuffer = new StringBuffer();
                stringBuffer.append(f4004c);
                Iterator<String> it = list.iterator();
                while (it.hasNext()) {
                    stringBuffer.append(", " + it.next());
                }
                stringBuffer.append("\n");
                try {
                    File file = new File(f4002a);
                    if (!file.exists()) {
                        file.mkdirs();
                    }
                    File file2 = new File(f4002a, f4003b);
                    if (!file2.exists()) {
                        file2.createNewFile();
                    }
                    FileWriter fileWriter = ((long) stringBuffer.length()) + file2.length() <= 51200 ? new FileWriter(file2, true) : new FileWriter(file2);
                    fileWriter.write(stringBuffer.toString());
                    fileWriter.flush();
                    fileWriter.close();
                } catch (Exception unused) {
                }
            }
        }
    }
}
