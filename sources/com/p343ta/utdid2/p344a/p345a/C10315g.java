package com.p343ta.utdid2.p344a.p345a;

import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ta.utdid2.a.a.g */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C10315g {

    /* renamed from: a */
    private static final Pattern f19732a = Pattern.compile("([\t\r\n])+");

    /* renamed from: a */
    public static boolean m6435a(String str) {
        return str == null || str.length() <= 0;
    }

    /* renamed from: a */
    public static int m6436a(String str) {
        if (str.length() > 0) {
            int i = 0;
            for (char c : str.toCharArray()) {
                i = (i * 31) + c;
            }
            return i;
        }
        return 0;
    }
}
