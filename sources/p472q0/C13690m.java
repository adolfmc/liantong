package p472q0;

import android.app.Application;
import android.os.Handler;
import android.os.Looper;
import java.lang.ref.WeakReference;
import p474r0.InterfaceC13715a;
import p474r0.InterfaceC13716b;
import p474r0.InterfaceC13717c;

/* renamed from: q0.m */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class C13690m implements InterfaceC13716b {

    /* renamed from: g */
    public static final Handler f27546g = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    public Application f27547a;

    /* renamed from: b */
    public C13676a f27548b;

    /* renamed from: c */
    public WeakReference<InterfaceC13715a> f27549c;

    /* renamed from: d */
    public InterfaceC13717c<?> f27550d;

    /* renamed from: e */
    public volatile CharSequence f27551e;

    /* renamed from: f */
    public final RunnableC13691a f27552f = new RunnableC13691a();

    /* renamed from: q0.m$a */
    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class RunnableC13691a implements Runnable {
        public RunnableC13691a() {
        }

        @Override // java.lang.Runnable
        public final void run() {
            WeakReference<InterfaceC13715a> weakReference = C13690m.this.f27549c;
            InterfaceC13715a interfaceC13715a = weakReference != null ? weakReference.get() : null;
            if (interfaceC13715a != null) {
                interfaceC13715a.cancel();
            }
            C13690m c13690m = C13690m.this;
            InterfaceC13715a m137a = c13690m.m137a(c13690m.f27547a);
            C13690m.this.f27549c = new WeakReference<>(m137a);
            C13690m c13690m2 = C13690m.this;
            CharSequence charSequence = c13690m2.f27551e;
            c13690m2.getClass();
            m137a.setDuration(charSequence.length() > 20 ? 1 : 0);
            m137a.setText(C13690m.this.f27551e);
            m137a.show();
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:22:0x00a0, code lost:
        if (((java.lang.Integer) r1.getMethod("checkOpNoThrow", r7, r7, java.lang.String.class).invoke(r0, java.lang.Integer.valueOf(((java.lang.Integer) r0.getClass().getDeclaredField("OP_POST_NOTIFICATION").get(java.lang.Integer.class)).intValue()), java.lang.Integer.valueOf(r10.getApplicationInfo().uid), r10.getPackageName())).intValue() == 0) goto L37;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final p474r0.InterfaceC13715a m137a(android.app.Application r10) {
        /*
            Method dump skipped, instructions count: 253
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: p472q0.C13690m.m137a(android.app.Application):r0.a");
    }
}
