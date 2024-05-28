package p472q0;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import p474r0.InterfaceC13717c;

/* renamed from: q0.o */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13693o implements InterfaceC13717c<View> {

    /* renamed from: a */
    public final int f27559a;

    /* renamed from: b */
    public final InterfaceC13717c<?> f27560b;

    public C13693o(int i, InterfaceC13717c<?> interfaceC13717c) {
        this.f27559a = i;
        this.f27560b = interfaceC13717c;
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: a */
    public final int mo124a() {
        InterfaceC13717c<?> interfaceC13717c = this.f27560b;
        if (interfaceC13717c == null) {
            return 0;
        }
        return interfaceC13717c.mo124a();
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: a */
    public final View mo123a(Context context) {
        return LayoutInflater.from(context).inflate(this.f27559a, (ViewGroup) null);
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: b */
    public final int mo122b() {
        InterfaceC13717c<?> interfaceC13717c = this.f27560b;
        if (interfaceC13717c == null) {
            return 17;
        }
        return interfaceC13717c.mo122b();
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: c */
    public final int mo121c() {
        InterfaceC13717c<?> interfaceC13717c = this.f27560b;
        if (interfaceC13717c == null) {
            return 0;
        }
        return interfaceC13717c.mo121c();
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: d */
    public final float mo120d() {
        InterfaceC13717c<?> interfaceC13717c = this.f27560b;
        if (interfaceC13717c == null) {
            return 0.0f;
        }
        return interfaceC13717c.mo120d();
    }

    @Override // p474r0.InterfaceC13717c
    /* renamed from: e */
    public final float mo119e() {
        InterfaceC13717c<?> interfaceC13717c = this.f27560b;
        if (interfaceC13717c == null) {
            return 0.0f;
        }
        return interfaceC13717c.mo119e();
    }
}
