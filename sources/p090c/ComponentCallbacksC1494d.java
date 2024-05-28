package p090c;

import android.app.Application;
import android.content.ComponentCallbacks;

/* renamed from: c.d */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public final class ComponentCallbacksC1494d implements ComponentCallbacks {

    /* renamed from: a */
    public final /* synthetic */ Application f2507a;

    /* renamed from: b */
    public final /* synthetic */ C1497g f2508b;

    public ComponentCallbacksC1494d(C1497g c1497g, Application application) {
        this.f2508b = c1497g;
        this.f2507a = application;
    }

    /*  JADX ERROR: JadxRuntimeException in pass: InlineMethods
        jadx.core.utils.exceptions.JadxRuntimeException: Failed to process method for inline: c.g.a(c.g, boolean):boolean
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:76)
        	at jadx.core.dex.visitors.InlineMethods.visit(InlineMethods.java:51)
        Caused by: java.lang.NullPointerException
        	at jadx.core.dex.instructions.args.RegisterArg.sameRegAndSVar(RegisterArg.java:173)
        	at jadx.core.dex.instructions.args.InsnArg.isSameVar(InsnArg.java:269)
        	at jadx.core.dex.visitors.MarkMethodsForInline.isSyntheticAccessPattern(MarkMethodsForInline.java:118)
        	at jadx.core.dex.visitors.MarkMethodsForInline.inlineMth(MarkMethodsForInline.java:86)
        	at jadx.core.dex.visitors.MarkMethodsForInline.process(MarkMethodsForInline.java:53)
        	at jadx.core.dex.visitors.InlineMethods.processInvokeInsn(InlineMethods.java:65)
        	... 1 more
        */
    @Override // android.content.ComponentCallbacks
    public final void onConfigurationChanged(android.content.res.Configuration r6) {
        /*
            r5 = this;
            if (r6 == 0) goto L6d
            float r0 = r6.fontScale
            r1 = 0
            int r0 = (r0 > r1 ? 1 : (r0 == r1 ? 0 : -1))
            if (r0 <= 0) goto L30
            c.g r0 = r5.f2508b
            android.content.res.Resources r1 = android.content.res.Resources.getSystem()
            android.util.DisplayMetrics r1 = r1.getDisplayMetrics()
            float r1 = r1.scaledDensity
            r0.f2517f = r1
            java.lang.String r0 = "initScaledDensity = "
            java.lang.StringBuilder r0 = cn.ltzf.passguard.C1730a.m22016a(r0)
            c.g r1 = r5.f2508b
            float r1 = r1.f2517f
            r0.append(r1)
            java.lang.String r1 = " on ConfigurationChanged"
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            p390g.C11944a.m2026a(r0)
        L30:
            c.g r0 = r5.f2508b
            int r6 = r6.orientation
            r1 = 0
            r2 = 1
            if (r6 != r2) goto L3a
            r6 = r2
            goto L3b
        L3a:
            r6 = r1
        L3b:
            p090c.C1497g.m22189a(r0, r6)
            android.app.Application r6 = r5.f2507a
            int[] r6 = p390g.C11946c.m2023a(r6)
            c.g r0 = r5.f2508b
            r1 = r6[r1]
            r0.f2520i = r1
            r6 = r6[r2]
            r0.f2521j = r6
            float r1 = (float) r1
            float r6 = (float) r6
            float r6 = r6 / r1
            double r1 = (double) r6
            r3 = 4609434218613702656(0x3ff8000000000000, double:1.5)
            int r6 = (r1 > r3 ? 1 : (r1 == r3 ? 0 : -1))
            if (r6 < 0) goto L5b
            r6 = 360(0x168, float:5.04E-43)
            goto L5d
        L5b:
            int r6 = r0.f2516e
        L5d:
            r0.f2518g = r6
            c.g r6 = p090c.C1497g.m22187c()
            c.f r0 = new c.f
            r0.<init>()
            r6.getClass()
            r6.f2529r = r0
        L6d:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: p090c.ComponentCallbacksC1494d.onConfigurationChanged(android.content.res.Configuration):void");
    }

    @Override // android.content.ComponentCallbacks
    public final void onLowMemory() {
    }
}
