package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e;

import java.util.ArrayList;
import java.util.Collection;

/* renamed from: a.a.a.a.a.e.a.e.c */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class C0671c implements InterfaceC0676h {

    /* renamed from: a */
    public Collection f2033a;

    public C0671c(Collection collection) {
        this.f2033a = new ArrayList(collection);
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p049e.InterfaceC0676h
    /* renamed from: a */
    public Collection mo22451a(InterfaceC0675g interfaceC0675g) {
        if (interfaceC0675g == null) {
            return new ArrayList(this.f2033a);
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : this.f2033a) {
            if (interfaceC0675g.m22452a(obj)) {
                arrayList.add(obj);
            }
        }
        return arrayList;
    }
}
