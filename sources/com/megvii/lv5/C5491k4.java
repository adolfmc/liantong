package com.megvii.lv5;

import java.io.Serializable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.k4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5491k4 implements Serializable, Cloneable {

    /* renamed from: a */
    public final String f12843a;

    /* renamed from: b */
    public final int f12844b;

    /* renamed from: c */
    public final int f12845c;

    public C5491k4(String str, int i, int i2) {
        this.f12843a = (String) C5527o2.m13246a(str, "Protocol name");
        this.f12844b = C5527o2.m13257a(i, "Protocol minor version");
        this.f12845c = C5527o2.m13257a(i2, "Protocol minor version");
    }

    public Object clone() {
        return super.clone();
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof C5491k4) {
            C5491k4 c5491k4 = (C5491k4) obj;
            return this.f12843a.equals(c5491k4.f12843a) && this.f12844b == c5491k4.f12844b && this.f12845c == c5491k4.f12845c;
        }
        return false;
    }

    public final int hashCode() {
        return (this.f12843a.hashCode() ^ (this.f12844b * 100000)) ^ this.f12845c;
    }

    public String toString() {
        return this.f12843a + '/' + Integer.toString(this.f12844b) + '.' + Integer.toString(this.f12845c);
    }
}
