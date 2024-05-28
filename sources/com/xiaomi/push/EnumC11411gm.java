package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.gm */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum EnumC11411gm {
    INT(1),
    LONG(2),
    STRING(3),
    BOOLEAN(4);
    

    /* renamed from: a */
    private final int f22666a;

    EnumC11411gm(int i) {
        this.f22666a = i;
    }

    /* renamed from: a */
    public static EnumC11411gm m3634a(int i) {
        switch (i) {
            case 1:
                return INT;
            case 2:
                return LONG;
            case 3:
                return STRING;
            case 4:
                return BOOLEAN;
            default:
                return null;
        }
    }
}
