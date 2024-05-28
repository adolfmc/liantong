package com.vivo.push.util;

import android.content.Context;

/* renamed from: com.vivo.push.util.ad */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class SharePreferenceManager extends BaseSharePreference {

    /* renamed from: b */
    private static SharePreferenceManager f21181b;

    /* renamed from: b */
    public static synchronized SharePreferenceManager m5455b() {
        SharePreferenceManager sharePreferenceManager;
        synchronized (SharePreferenceManager.class) {
            if (f21181b == null) {
                f21181b = new SharePreferenceManager();
            }
            sharePreferenceManager = f21181b;
        }
        return sharePreferenceManager;
    }

    /* renamed from: a */
    public final synchronized void m5456a(Context context) {
        if (this.f21202a == null) {
            this.f21202a = context;
            m5422a(context, "com.vivo.push_preferences");
        }
    }

    /* renamed from: c */
    public final byte[] m5453c() {
        byte[] m5454b = m5454b(m5411b("com.vivo.push.secure_cache_iv", ""));
        return (m5454b == null || m5454b.length <= 0) ? new byte[]{34, 32, 33, 37, 33, 34, 32, 33, 33, 33, 34, 41, 35, 32, 32, 32} : m5454b;
    }

    /* renamed from: d */
    public final byte[] m5452d() {
        byte[] m5454b = m5454b(m5411b("com.vivo.push.secure_cache_key", ""));
        return (m5454b == null || m5454b.length <= 0) ? new byte[]{33, 34, 35, 36, 37, 38, 39, 40, 41, 32, 38, 37, 36, 35, 34, 33} : m5454b;
    }

    /* renamed from: b */
    private static byte[] m5454b(String str) {
        int i;
        byte[] bArr = null;
        try {
            String[] split = str.split(",");
            if (split.length > 0) {
                bArr = new byte[split.length];
                i = split.length;
            } else {
                i = 0;
            }
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = Byte.parseByte(split[i2].trim());
            }
        } catch (Exception e) {
            LogUtil.m5354a("SharePreferenceManager", "getCodeBytes error:" + e.getMessage());
        }
        return bArr;
    }
}
