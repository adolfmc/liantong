package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.p052d;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.e.l.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0694b implements InterfaceC0695c {

    /* renamed from: d */
    public static final List f2056d = Collections.unmodifiableList(new ArrayList());

    /* renamed from: a */
    public String f2057a;

    /* renamed from: b */
    public List f2058b;

    /* renamed from: c */
    public byte[] f2059c;

    public C0694b(String str, byte[] bArr) {
        this(str, f2056d, bArr);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.p051l.p052d.InterfaceC0695c
    /* renamed from: a */
    public C0694b mo22380a() {
        return this;
    }

    /* renamed from: b */
    public byte[] m22383b() {
        return this.f2059c;
    }

    /* renamed from: c */
    public List m22382c() {
        return this.f2058b;
    }

    /* renamed from: d */
    public String m22381d() {
        return this.f2057a;
    }

    public C0694b(String str, List list, byte[] bArr) {
        this.f2057a = str;
        this.f2058b = Collections.unmodifiableList(list);
        this.f2059c = bArr;
    }
}
