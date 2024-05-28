package com.alipay.sdk.util;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.sdk.util.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum EnumC2041d {
    WIFI(0, "WIFI"),
    NETWORK_TYPE_1(1, "unicom2G"),
    NETWORK_TYPE_2(2, "mobile2G"),
    NETWORK_TYPE_4(4, "telecom2G"),
    NETWORK_TYPE_5(5, "telecom3G"),
    NETWORK_TYPE_6(6, "telecom3G"),
    NETWORK_TYPE_12(12, "telecom3G"),
    NETWORK_TYPE_8(8, "unicom3G"),
    NETWORK_TYPE_3(3, "unicom3G"),
    NETWORK_TYPE_13(13, "LTE"),
    NETWORK_TYPE_11(11, "IDEN"),
    NETWORK_TYPE_9(9, "HSUPA"),
    NETWORK_TYPE_10(10, "HSPA"),
    NETWORK_TYPE_15(15, "HSPAP"),
    NONE(-1, "none");
    

    /* renamed from: p */
    private int f3871p;

    /* renamed from: q */
    private String f3872q;

    EnumC2041d(int i, String str) {
        this.f3871p = i;
        this.f3872q = str;
    }

    /* renamed from: a */
    public final int m20708a() {
        return this.f3871p;
    }

    /* renamed from: b */
    public final String m20706b() {
        return this.f3872q;
    }

    /* renamed from: a */
    public static EnumC2041d m20707a(int i) {
        EnumC2041d[] values;
        for (EnumC2041d enumC2041d : values()) {
            if (enumC2041d.m20708a() == i) {
                return enumC2041d;
            }
        }
        return NONE;
    }
}
