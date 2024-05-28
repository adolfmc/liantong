package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.gl */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum EnumC11410gl {
    MISC_CONFIG(1),
    PLUGIN_CONFIG(2);
    

    /* renamed from: a */
    private final int f22660a;

    EnumC11410gl(int i) {
        this.f22660a = i;
    }

    /* renamed from: a */
    public int m3636a() {
        return this.f22660a;
    }

    /* renamed from: a */
    public static EnumC11410gl m3635a(int i) {
        switch (i) {
            case 1:
                return MISC_CONFIG;
            case 2:
                return PLUGIN_CONFIG;
            default:
                return null;
        }
    }
}
