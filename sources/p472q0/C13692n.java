package p472q0;

import android.app.Application;
import p474r0.InterfaceC13716b;
import p474r0.InterfaceC13717c;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: q0.n */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C13692n {

    /* renamed from: a */
    public static Application f27554a;

    /* renamed from: b */
    public static InterfaceC13716b f27555b;

    /* renamed from: c */
    public static InterfaceC13717c<?> f27556c;

    /* renamed from: d */
    public static C13689l f27557d;

    /* renamed from: e */
    public static Boolean f27558e;

    /* JADX WARN: Removed duplicated region for block: B:32:0x0069 A[Catch: ClassNotFoundException -> 0x0098, TRY_LEAVE, TryCatch #0 {ClassNotFoundException -> 0x0098, blocks: (B:18:0x003d, B:32:0x0069, B:21:0x004a, B:24:0x0053, B:27:0x005a), top: B:39:0x003d }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x009c A[SYNTHETIC] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m135a(java.lang.CharSequence r8) {
        /*
            if (r8 == 0) goto Lb3
            int r0 = r8.length()
            if (r0 != 0) goto La
            goto Lb3
        La:
            q0.l r0 = p472q0.C13692n.f27557d
            if (r0 != 0) goto L15
            q0.l r0 = new q0.l
            r0.<init>()
            p472q0.C13692n.f27557d = r0
        L15:
            q0.l r0 = p472q0.C13692n.f27557d
            r0.getClass()
            boolean r0 = m136a()
            if (r0 != 0) goto L22
            goto L9f
        L22:
            java.lang.Throwable r0 = new java.lang.Throwable
            r0.<init>()
            java.lang.StackTraceElement[] r0 = r0.getStackTrace()
            int r1 = r0.length
            r2 = 0
            r3 = r2
        L2e:
            if (r3 >= r1) goto L9f
            r4 = r0[r3]
            int r5 = r4.getLineNumber()
            if (r5 > 0) goto L39
            goto L9c
        L39:
            java.lang.String r6 = r4.getClassName()
            java.lang.Class r6 = java.lang.Class.forName(r6)     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.Class<q0.l> r7 = p472q0.C13689l.class
            boolean r7 = r7.equals(r6)     // Catch: java.lang.ClassNotFoundException -> L98
            if (r7 == 0) goto L4a
            goto L64
        L4a:
            java.lang.Class<q0.n> r7 = p472q0.C13692n.class
            boolean r7 = r7.equals(r6)     // Catch: java.lang.ClassNotFoundException -> L98
            if (r7 == 0) goto L53
            goto L64
        L53:
            boolean r7 = r6.isInterface()     // Catch: java.lang.ClassNotFoundException -> L98
            if (r7 == 0) goto L5a
            goto L64
        L5a:
            int r6 = r6.getModifiers()     // Catch: java.lang.ClassNotFoundException -> L98
            boolean r6 = java.lang.reflect.Modifier.isAbstract(r6)     // Catch: java.lang.ClassNotFoundException -> L98
            if (r6 == 0) goto L66
        L64:
            r6 = 1
            goto L67
        L66:
            r6 = r2
        L67:
            if (r6 != 0) goto L9c
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.ClassNotFoundException -> L98
            r6.<init>()     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r7 = "("
            r6.append(r7)     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r4 = r4.getFileName()     // Catch: java.lang.ClassNotFoundException -> L98
            r6.append(r4)     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r4 = ":"
            r6.append(r4)     // Catch: java.lang.ClassNotFoundException -> L98
            r6.append(r5)     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r4 = ") "
            r6.append(r4)     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r4 = r8.toString()     // Catch: java.lang.ClassNotFoundException -> L98
            r6.append(r4)     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r4 = r6.toString()     // Catch: java.lang.ClassNotFoundException -> L98
            java.lang.String r5 = "ToastUtils"
            android.util.Log.i(r5, r4)     // Catch: java.lang.ClassNotFoundException -> L98
            goto L9f
        L98:
            r4 = move-exception
            r4.printStackTrace()
        L9c:
            int r3 = r3 + 1
            goto L2e
        L9f:
            r0.b r0 = p472q0.C13692n.f27555b
            q0.m r0 = (p472q0.C13690m) r0
            r0.f27551e = r8
            android.os.Handler r8 = p472q0.C13690m.f27546g
            q0.m$a r1 = r0.f27552f
            r8.removeCallbacks(r1)
            q0.m$a r0 = r0.f27552f
            r1 = 200(0xc8, double:9.9E-322)
            r8.postDelayed(r0, r1)
        Lb3:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p472q0.C13692n.m135a(java.lang.CharSequence):void");
    }

    /* renamed from: a */
    public static boolean m136a() {
        if (f27558e == null) {
            f27558e = Boolean.valueOf((f27554a.getApplicationInfo().flags & 2) != 0);
        }
        return f27558e.booleanValue();
    }
}
