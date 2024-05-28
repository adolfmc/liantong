package p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b;

import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.e.a.b.l */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractC0170l extends AbstractC0258r {
    /* renamed from: a */
    public static AbstractC0170l m24108a(Object obj) {
        if (obj instanceof AbstractC0170l) {
            return (AbstractC0170l) obj;
        }
        if (obj != null) {
            try {
                return m24108a((Object) AbstractC0258r.m23755a((byte[]) obj));
            } catch (IOException e) {
                throw new IllegalArgumentException("failed to construct NULL from byte[]: " + e.getMessage());
            } catch (ClassCastException unused) {
                throw new IllegalArgumentException("unknown object in getInstance(): " + obj.getClass().getName());
            }
        }
        return null;
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public abstract void mo22982a(C0252q c0252q);

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r, p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0174m
    public int hashCode() {
        return -1;
    }

    public String toString() {
        return "NULL";
    }

    @Override // p001a.p002a.p003a.p004a.p005a.p014e.p015a.p016b.AbstractC0258r
    /* renamed from: a */
    public boolean mo22981a(AbstractC0258r abstractC0258r) {
        return abstractC0258r instanceof AbstractC0170l;
    }
}
