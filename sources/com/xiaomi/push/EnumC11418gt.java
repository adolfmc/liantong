package com.xiaomi.push;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.gt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum EnumC11418gt {
    RegIdExpired(0),
    PackageUnregistered(1),
    Init(2);
    

    /* renamed from: a */
    private final int f22832a;

    EnumC11418gt(int i) {
        this.f22832a = i;
    }

    /* renamed from: a */
    public int m3519a() {
        return this.f22832a;
    }

    /* renamed from: a */
    public static EnumC11418gt m3518a(int i) {
        switch (i) {
            case 0:
                return RegIdExpired;
            case 1:
                return PackageUnregistered;
            case 2:
                return Init;
            default:
                return null;
        }
    }
}
