package com.networkbench.nbslens.nbsnativecrashlib;

import android.text.TextUtils;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.nbslens.nbsnativecrashlib.l */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6798l {
    private C6798l() {
    }

    /* renamed from: a */
    public static boolean m8411a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || str3 == null) {
            return false;
        }
        C6781e m8462a = C6781e.m8462a();
        return m8462a.m8456a(str, "\n\n" + str2 + ":\n" + str3 + "\n\n");
    }

    /* renamed from: a */
    public static boolean m8413a(File file) {
        return file.getName().endsWith(".java.nbscrash");
    }

    /* renamed from: b */
    public static boolean m8408b(File file) {
        return file.getName().endsWith(".native.nbscrash");
    }

    /* renamed from: c */
    public static boolean m8405c(File file) {
        return file.getName().endsWith(".anr.nbscrash");
    }

    /* renamed from: a */
    public static File[] m8414a() {
        return m8410a(new String[]{".java.nbscrash"});
    }

    /* renamed from: b */
    public static File[] m8409b() {
        return m8410a(new String[]{".native.nbscrash"});
    }

    /* renamed from: c */
    public static File[] m8406c() {
        return m8410a(new String[]{".anr.nbscrash"});
    }

    /* renamed from: d */
    public static File[] m8404d() {
        return m8410a(new String[]{".java.nbscrash", ".native.nbscrash", ".anr.nbscrash"});
    }

    /* renamed from: d */
    public static boolean m8403d(File file) {
        return C6781e.m8462a().m8460a(file);
    }

    /* renamed from: a */
    public static boolean m8412a(String str) {
        return C6781e.m8462a().m8460a(new File(str));
    }

    /* renamed from: e */
    public static boolean m8402e() {
        return m8407b(new String[]{".java.nbscrash"});
    }

    /* renamed from: f */
    public static boolean m8401f() {
        return m8407b(new String[]{".native.nbscrash"});
    }

    /* renamed from: g */
    public static boolean m8400g() {
        return m8407b(new String[]{".anr.nbscrash"});
    }

    /* renamed from: h */
    public static boolean m8399h() {
        return m8407b(new String[]{".java.nbscrash", ".native.nbscrash", ".anr.nbscrash"});
    }

    /* renamed from: a */
    private static File[] m8410a(final String[] strArr) {
        String m8520c = NBSNativeCrash.m8520c();
        if (m8520c == null) {
            return new File[0];
        }
        File file = new File(m8520c);
        if (!file.exists() || !file.isDirectory()) {
            return new File[0];
        }
        File[] listFiles = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.l.1
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                if (str.startsWith("tombstone_")) {
                    for (String str2 : strArr) {
                        if (str.endsWith(str2)) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
        });
        if (listFiles == null) {
            return new File[0];
        }
        Arrays.sort(listFiles, new Comparator<File>() { // from class: com.networkbench.nbslens.nbsnativecrashlib.l.2
            @Override // java.util.Comparator
            /* renamed from: a */
            public int compare(File file2, File file3) {
                return file2.getName().compareTo(file3.getName());
            }
        });
        return listFiles;
    }

    /* renamed from: b */
    private static boolean m8407b(final String[] strArr) {
        File[] listFiles;
        String m8520c = NBSNativeCrash.m8520c();
        if (m8520c == null) {
            return false;
        }
        File file = new File(m8520c);
        if (file.exists() && file.isDirectory() && (listFiles = file.listFiles(new FilenameFilter() { // from class: com.networkbench.nbslens.nbsnativecrashlib.l.3
            @Override // java.io.FilenameFilter
            public boolean accept(File file2, String str) {
                if (str.startsWith("tombstone_")) {
                    for (String str2 : strArr) {
                        if (str.endsWith(str2)) {
                            return true;
                        }
                    }
                    return false;
                }
                return false;
            }
        })) != null) {
            boolean z = true;
            for (File file2 : listFiles) {
                if (!C6781e.m8462a().m8460a(file2)) {
                    z = false;
                }
            }
            return z;
        }
        return false;
    }
}
