package p001a.p058b.p062b.p063a.p064a.p066b;

import p001a.p002a.p003a.p004a.outline;

/* renamed from: a.b.b.a.a.b.a */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0720a {

    /* renamed from: a */
    public String f2187a;

    /* renamed from: b */
    public int f2188b;

    public C0720a(String str) {
        this.f2187a = "";
        this.f2188b = 0;
        this.f2187a = str;
        this.f2188b = 0;
    }

    public String toString() {
        StringBuilder m24437a = outline.m24437a("BlockData{block='");
        m24437a.append(this.f2187a);
        m24437a.append('\'');
        m24437a.append(", retryCount=");
        m24437a.append(this.f2188b);
        m24437a.append('}');
        return m24437a.toString();
    }
}
