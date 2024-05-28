package com.alipay.security.mobile.module.p110a.p111a;

import com.alipay.security.mobile.module.p110a.C2081a;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.a.a.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2083b {
    /* renamed from: a */
    public static String m20565a(String str) {
        try {
            if (C2081a.m20577a(str)) {
                return null;
            }
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-1");
            messageDigest.update(str.getBytes("UTF-8"));
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(String.format("%02x", Byte.valueOf(digest[i])));
            }
            return sb.toString();
        } catch (Exception unused) {
            return null;
        }
    }
}
