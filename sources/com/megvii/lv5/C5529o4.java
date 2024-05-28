package com.megvii.lv5;

import java.io.Serializable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.o4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5529o4 implements InterfaceC5466h4, Serializable, Cloneable {

    /* renamed from: a */
    public final String f13113a;

    /* renamed from: b */
    public final String f13114b;

    public C5529o4(String str, String str2) {
        this.f13113a = (String) C5527o2.m13246a(str, "Name");
        this.f13114b = str2;
    }

    @Override // com.megvii.lv5.InterfaceC5466h4
    /* renamed from: b */
    public String mo13216b() {
        return this.f13114b;
    }

    @Override // com.megvii.lv5.InterfaceC5466h4
    /* renamed from: c */
    public String mo13215c() {
        return this.f13113a;
    }

    public Object clone() {
        return super.clone();
    }

    public String toString() {
        C5558s4 c5558s4;
        C5527o2.m13246a(this, "Header");
        if (this instanceof InterfaceC5455g4) {
            c5558s4 = ((InterfaceC5455g4) this).m13501a();
        } else {
            c5558s4 = new C5558s4(64);
            String mo13215c = mo13215c();
            String mo13216b = mo13216b();
            int length = mo13215c.length() + 2;
            if (mo13216b != null) {
                length += mo13216b.length();
            }
            c5558s4.m13167a(length);
            c5558s4.m13166a(mo13215c);
            c5558s4.m13166a(": ");
            if (mo13216b != null) {
                c5558s4.m13166a(mo13216b);
            }
        }
        return c5558s4.toString();
    }
}
