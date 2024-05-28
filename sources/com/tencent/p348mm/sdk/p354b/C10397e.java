package com.tencent.p348mm.sdk.p354b;

import com.tencent.p348mm.p349a.C10368a;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.b.e */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class C10397e {

    /* renamed from: r */
    private final C10368a f20011r;

    /* renamed from: s */
    private C10396d<String, String> f20012s;

    /* renamed from: b */
    public final String m6185b(String str) {
        String str2;
        try {
            if (str.startsWith("!")) {
                if (this.f20012s.m6186a(str)) {
                    return this.f20012s.get(str);
                }
                str2 = str.substring(1);
                try {
                    String[] split = str2.split("@");
                    if (split.length > 1) {
                        String str3 = split[0];
                        int intValue = Integer.valueOf(split[0]).intValue();
                        String substring = str2.substring(str3.length() + 1, str3.length() + 1 + intValue);
                        String str4 = this.f20011r.m6230a(substring) + str2.substring(str3.length() + 1 + intValue);
                        this.f20012s.put(str, str4);
                        return str4;
                    }
                    return str2;
                } catch (Exception e) {
                    e = e;
                    e.printStackTrace();
                    return "[td]" + str2;
                }
            }
            return str;
        } catch (Exception e2) {
            e = e2;
            str2 = str;
        }
    }
}
