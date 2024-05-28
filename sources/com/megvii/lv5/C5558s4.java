package com.megvii.lv5;

import java.io.Serializable;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.s4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C5558s4 implements Serializable {

    /* renamed from: a */
    public char[] f13278a;

    /* renamed from: b */
    public int f13279b;

    public C5558s4(int i) {
        C5527o2.m13257a(i, "Buffer capacity");
        this.f13278a = new char[i];
    }

    /* renamed from: a */
    public void m13168a(char c) {
        int i = this.f13279b + 1;
        if (i > this.f13278a.length) {
            m13165b(i);
        }
        this.f13278a[this.f13279b] = c;
        this.f13279b = i;
    }

    /* renamed from: a */
    public void m13167a(int i) {
        if (i <= 0) {
            return;
        }
        int length = this.f13278a.length;
        int i2 = this.f13279b;
        if (i > length - i2) {
            m13165b(i2 + i);
        }
    }

    /* renamed from: a */
    public void m13166a(String str) {
        if (str == null) {
            str = "null";
        }
        int length = str.length();
        int i = this.f13279b + length;
        if (i > this.f13278a.length) {
            m13165b(i);
        }
        str.getChars(0, length, this.f13278a, this.f13279b);
        this.f13279b = i;
    }

    /* renamed from: b */
    public final void m13165b(int i) {
        char[] cArr = new char[Math.max(this.f13278a.length << 1, i)];
        System.arraycopy(this.f13278a, 0, cArr, 0, this.f13279b);
        this.f13278a = cArr;
    }

    public String toString() {
        return new String(this.f13278a, 0, this.f13279b);
    }
}
