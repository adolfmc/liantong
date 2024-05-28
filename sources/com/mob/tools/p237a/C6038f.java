package com.mob.tools.p237a;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.ResolveInfo;
import android.location.Location;
import com.mob.commons.p231cc.C5791a;
import com.mob.tools.MobLog;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/* renamed from: com.mob.tools.a.f */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C6038f implements InterfaceC6028a {

    /* renamed from: a */
    private HashMap<String, Object> f14870a;

    public C6038f(HashMap<String, Object> hashMap) {
        this.f14870a = hashMap;
    }

    /* renamed from: a */
    private Object m11648a(String str, Object... objArr) {
        LinkedList<Object> m12457a;
        try {
            if (this.f14870a == null || !this.f14870a.containsKey(str) || (m12457a = C5791a.m12457a(this.f14870a.get(str), objArr)) == null || m12457a.isEmpty()) {
                return null;
            }
            return m12457a.get(0);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x007f, code lost:
        if (r3 != java.lang.Boolean.TYPE) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x0088, code lost:
        if (r3 != java.lang.Integer.TYPE) goto L48;
     */
    /* JADX WARN: Code restructure failed: missing block: B:44:0x0092, code lost:
        if (r3 != java.lang.Byte.TYPE) goto L52;
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x009b, code lost:
        if (r3 != java.lang.Character.TYPE) goto L56;
     */
    /* JADX WARN: Code restructure failed: missing block: B:50:0x00a4, code lost:
        if (r3 != java.lang.Short.TYPE) goto L60;
     */
    /* JADX WARN: Code restructure failed: missing block: B:53:0x00ad, code lost:
        if (r3 != java.lang.Long.TYPE) goto L64;
     */
    /* JADX WARN: Code restructure failed: missing block: B:56:0x00b8, code lost:
        if (r3 != java.lang.Float.TYPE) goto L68;
     */
    /* JADX WARN: Code restructure failed: missing block: B:59:0x00c2, code lost:
        if (r3 != java.lang.Double.TYPE) goto L72;
     */
    /* JADX WARN: Code restructure failed: missing block: B:61:0x00ca, code lost:
        return (T) java.lang.Double.valueOf(0.0d);
     */
    /* JADX WARN: Code restructure failed: missing block: B:66:?, code lost:
        return (T) false;
     */
    /* JADX WARN: Code restructure failed: missing block: B:67:?, code lost:
        return (T) (-1);
     */
    /* JADX WARN: Code restructure failed: missing block: B:68:?, code lost:
        return (T) (byte) 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:?, code lost:
        return (T) 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:70:?, code lost:
        return (T) 0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:?, code lost:
        return (T) 0L;
     */
    /* JADX WARN: Code restructure failed: missing block: B:72:?, code lost:
        return (T) java.lang.Float.valueOf(0.0f);
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:?, code lost:
        return r0;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private <T> T m11649a(java.lang.Class<T> r3, java.lang.Object r4) {
        /*
            r2 = this;
            r0 = 0
            if (r3 == 0) goto L7a
            if (r4 == 0) goto L7a
            java.lang.Class<java.lang.Void> r1 = java.lang.Void.class
            if (r3 != r1) goto Lb
            goto L7a
        Lb:
            java.lang.Class r1 = java.lang.Boolean.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L18
            java.lang.Class<java.lang.Boolean> r1 = java.lang.Boolean.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L18:
            java.lang.Class r1 = java.lang.Integer.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L24
            java.lang.Class<java.lang.Integer> r1 = java.lang.Integer.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L24:
            java.lang.Class r1 = java.lang.Byte.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L30
            java.lang.Class<java.lang.Byte> r1 = java.lang.Byte.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L30:
            java.lang.Class r1 = java.lang.Character.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L3c
            java.lang.Class<java.lang.Character> r1 = java.lang.Character.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L3c:
            java.lang.Class r1 = java.lang.Short.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L48
            java.lang.Class<java.lang.Short> r1 = java.lang.Short.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L48:
            java.lang.Class r1 = java.lang.Long.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L54
            java.lang.Class<java.lang.Long> r1 = java.lang.Long.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L54:
            java.lang.Class r1 = java.lang.Float.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L60
            java.lang.Class<java.lang.Float> r1 = java.lang.Float.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L60:
            java.lang.Class r1 = java.lang.Double.TYPE     // Catch: java.lang.Throwable -> L72
            if (r3 != r1) goto L6c
            java.lang.Class<java.lang.Double> r1 = java.lang.Double.class
            java.lang.Object r4 = r1.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L6c:
            java.lang.Object r4 = r3.cast(r4)     // Catch: java.lang.Throwable -> L72
            r0 = r4
            goto L7a
        L72:
            r4 = move-exception
            com.mob.tools.log.NLog r1 = com.mob.tools.MobLog.getInstance()
            r1.m11341d(r4)
        L7a:
            if (r0 != 0) goto Lca
            java.lang.Class r4 = java.lang.Boolean.TYPE
            r1 = 0
            if (r3 != r4) goto L86
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r1)
            goto Lca
        L86:
            java.lang.Class r4 = java.lang.Integer.TYPE
            if (r3 != r4) goto L90
            r3 = -1
            java.lang.Integer r0 = java.lang.Integer.valueOf(r3)
            goto Lca
        L90:
            java.lang.Class r4 = java.lang.Byte.TYPE
            if (r3 != r4) goto L99
            java.lang.Byte r0 = java.lang.Byte.valueOf(r1)
            goto Lca
        L99:
            java.lang.Class r4 = java.lang.Character.TYPE
            if (r3 != r4) goto La2
            java.lang.Character r0 = java.lang.Character.valueOf(r1)
            goto Lca
        La2:
            java.lang.Class r4 = java.lang.Short.TYPE
            if (r3 != r4) goto Lab
            java.lang.Short r0 = java.lang.Short.valueOf(r1)
            goto Lca
        Lab:
            java.lang.Class r4 = java.lang.Long.TYPE
            if (r3 != r4) goto Lb6
            r3 = 0
            java.lang.Long r0 = java.lang.Long.valueOf(r3)
            goto Lca
        Lb6:
            java.lang.Class r4 = java.lang.Float.TYPE
            if (r3 != r4) goto Lc0
            r3 = 0
            java.lang.Float r0 = java.lang.Float.valueOf(r3)
            goto Lca
        Lc0:
            java.lang.Class r4 = java.lang.Double.TYPE
            if (r3 != r4) goto Lca
            r3 = 0
            java.lang.Double r0 = java.lang.Double.valueOf(r3)
        Lca:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.p237a.C6038f.m11649a(java.lang.Class, java.lang.Object):java.lang.Object");
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public boolean mo11590a() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("cird", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public boolean mo11547b() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("cx", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: c */
    public boolean mo11538c() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ckpd", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: d */
    public boolean mo11534d() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("degb", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: e */
    public boolean mo11531e() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("vnmt", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: f */
    public boolean mo11528f() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ckua", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: g */
    public boolean mo11525g() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("dvenbl", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: h */
    public boolean mo11522h() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ubenbl", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: i */
    public boolean mo11520i() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("iwpxy", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: j */
    public String mo11519j() {
        return (String) m11649a(String.class, m11648a("gavti", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public String mo11575a(boolean z) {
        return (String) m11649a(String.class, m11648a("gsimtfce", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public String mo11540b(boolean z) {
        return (String) m11649a(String.class, m11648a("gbsifce", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: c */
    public String mo11535c(boolean z) {
        return (String) m11649a(String.class, m11648a("gcriefce", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: d */
    public String mo11532d(boolean z) {
        return (String) m11649a(String.class, m11648a("gcrnmfce", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: k */
    public String mo11518k() {
        return (String) m11649a(String.class, m11648a("gmivsn", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: l */
    public String mo11517l() {
        return (String) m11649a(String.class, m11648a("bgmdl", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: m */
    public String mo11516m() {
        return (String) m11649a(String.class, m11648a("gmnft", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: n */
    public String mo11515n() {
        return (String) m11649a(String.class, m11648a("gbrd", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: o */
    public String mo11514o() {
        return (String) m11649a(String.class, m11648a("gdvtp", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: p */
    public Object mo11513p() {
        return m11649a(Object.class, m11648a("gtecloc", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: q */
    public ArrayList<HashMap<String, Object>> mo11512q() {
        return (ArrayList) m11649a(ArrayList.class, m11648a("gnbclin", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: r */
    public HashMap<String, Object> mo11511r() {
        return mo11529e(false);
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: e */
    public HashMap<String, Object> mo11529e(boolean z) {
        return (HashMap) m11649a(HashMap.class, m11648a("wmcwifce", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: s */
    public int mo11510s() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("govsit", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: t */
    public String mo11509t() {
        return (String) m11649a(String.class, m11648a("govsnm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: u */
    public String mo11508u() {
        return (String) m11649a(String.class, m11648a("golgu", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: v */
    public String mo11507v() {
        return (String) m11649a(String.class, m11648a("gocnty", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: w */
    public HashMap<String, Object> mo11506w() {
        return (HashMap) m11649a(HashMap.class, m11648a("gcuin", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: x */
    public ArrayList<ArrayList<String>> mo11505x() {
        return (ArrayList) m11649a(ArrayList.class, m11648a("gtydvin", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: y */
    public String mo11504y() {
        return (String) m11649a(String.class, m11648a("gqmkn", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: z */
    public HashMap<String, HashMap<String, Long>> mo11503z() {
        return (HashMap) m11649a(HashMap.class, m11648a("gszin", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: A */
    public HashMap<String, Long> mo11616A() {
        return (HashMap) m11649a(HashMap.class, m11648a("gmrin", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: B */
    public String mo11615B() {
        return (String) m11649a(String.class, m11648a("galgu", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: C */
    public String mo11614C() {
        return (String) m11649a(String.class, m11648a("gscsz", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: f */
    public String mo11526f(boolean z) {
        return (String) m11649a(String.class, m11648a("gneypfce", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: D */
    public String mo11613D() {
        return (String) m11649a(String.class, m11648a("gneypnw", new Object[0]));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: E */
    public String mo11612E() {
        return (String) m11649a(String.class, m11648a("gnktpfs", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: F */
    public String mo11611F() {
        return (String) m11649a(String.class, m11648a("gdtlnktpfs", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: G */
    public boolean mo11610G() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("cknavbl", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: H */
    public int mo11609H() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("gdntp", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: I */
    public String mo11608I() {
        return (String) m11649a(String.class, m11648a("gtmne", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: J */
    public String mo11607J() {
        return (String) m11649a(String.class, m11648a("gflv", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: K */
    public String mo11606K() {
        return (String) m11649a(String.class, m11648a("gbsbd", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: L */
    public String mo11605L() {
        return (String) m11649a(String.class, m11648a("gbfspy", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: M */
    public String mo11604M() {
        return (String) m11649a(String.class, m11648a("gbplfo", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: N */
    public String mo11603N() {
        return (String) m11649a(String.class, m11648a("giads", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public ArrayList<HashMap<String, String>> mo11571a(boolean z, boolean z2) {
        return (ArrayList) m11649a(ArrayList.class, m11648a("giafce", Boolean.valueOf(z), Boolean.valueOf(z2)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: O */
    public ArrayList<HashMap<String, String>> mo11602O() {
        return (ArrayList) m11649a(ArrayList.class, m11648a("gal", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: P */
    public ArrayList<HashMap<String, String>> mo11601P() {
        return (ArrayList) m11649a(ArrayList.class, m11648a("gsl", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public Location mo11589a(int i, int i2, boolean z) {
        return (Location) m11649a(Location.class, m11648a("glctn", Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public String mo11582a(String str) {
        return (String) m11649a(String.class, m11648a("gstmpts", str));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: Q */
    public String mo11600Q() {
        return (String) m11649a(String.class, m11648a("gdvk", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: g */
    public String mo11523g(boolean z) {
        return (String) m11649a(String.class, m11648a("gdvkfc", Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public boolean mo11543b(String str) {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ipgist", str))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: R */
    public String mo11599R() {
        return (String) m11649a(String.class, m11648a("gscpt", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: S */
    public String mo11598S() {
        return (String) m11649a(String.class, m11648a("gsnmd", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: c */
    public String mo11537c(String str) {
        return (String) m11649a(String.class, m11648a("gsnmdfp", str));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: T */
    public String mo11597T() {
        return (String) m11649a(String.class, m11648a("gpgnm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: U */
    public String mo11596U() {
        return (String) m11649a(String.class, m11648a("gpnmmt", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: d */
    public String mo11533d(String str) {
        return (String) m11649a(String.class, m11648a("gpnmfp", str));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: V */
    public int mo11595V() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("gpvsnm", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: W */
    public String mo11594W() {
        return (String) m11649a(String.class, m11648a("gpvsme", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: X */
    public boolean mo11593X() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("cinmnps", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: Y */
    public String mo11592Y() {
        return (String) m11649a(String.class, m11648a("gcrtpcnm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: Z */
    public boolean mo11591Z() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ciafgd", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: e */
    public boolean mo11530e(String str) {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ckpmsi", str))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aa */
    public Context mo11570aa() {
        return (Context) m11649a(Context.class, m11648a("gaplcn", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public List<ResolveInfo> mo11588a(Intent intent, int i) {
        return (List) m11649a(List.class, m11648a("qritsvc", intent, Integer.valueOf(i)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public ResolveInfo mo11545b(Intent intent, int i) {
        return (ResolveInfo) m11649a(ResolveInfo.class, m11648a("rsaciy", intent, Integer.valueOf(i)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public PackageInfo mo11574a(boolean z, int i, String str, int i2) {
        return (PackageInfo) m11649a(PackageInfo.class, m11648a("gpgiffist", Boolean.valueOf(z), Integer.valueOf(i), str, Integer.valueOf(i2)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ab */
    public String mo11569ab() {
        return (String) m11649a(String.class, m11648a("gdvda", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ac */
    public String mo11568ac() {
        return (String) m11649a(String.class, m11648a("gdvdtnas", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ad */
    public long mo11567ad() {
        return ((Long) m11649a(Long.TYPE, m11648a("galtut", (Object[]) null))).longValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ae */
    public String mo11566ae() {
        return (String) m11649a(String.class, m11648a("gdvme", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: af */
    public String mo11565af() {
        return (String) m11649a(String.class, m11648a("gcrup", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ag */
    public String mo11564ag() {
        return (String) m11649a(String.class, m11648a("gcifm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ah */
    public String mo11563ah() {
        return (String) m11649a(String.class, m11648a("godm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ai */
    public String mo11562ai() {
        return (String) m11649a(String.class, m11648a("godhm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aj */
    public HashMap<String, Object> mo11561aj() {
        return (HashMap) m11649a(HashMap.class, m11648a("galdm", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ak */
    public ApplicationInfo mo11560ak() {
        return (ApplicationInfo) m11649a(ApplicationInfo.class, m11648a("gtaif", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: al */
    public ArrayList<HashMap<String, Object>> mo11559al() {
        return (ArrayList) m11649a(ArrayList.class, m11648a("gtaifok", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public ApplicationInfo mo11581a(String str, int i) {
        return (ApplicationInfo) m11649a(ApplicationInfo.class, m11648a("gtaifprm", str, Integer.valueOf(i)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: a */
    public ApplicationInfo mo11573a(boolean z, String str, int i) {
        return (ApplicationInfo) m11649a(ApplicationInfo.class, m11648a("gtaifprmfce", Boolean.valueOf(z), str, Integer.valueOf(i)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: am */
    public long mo11558am() {
        return ((Long) m11649a(Long.TYPE, m11648a("gtbdt", (Object[]) null))).longValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: an */
    public double mo11557an() {
        return ((Double) m11649a(Double.TYPE, m11648a("gtscnin", (Object[]) null))).doubleValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ao */
    public int mo11556ao() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("gtscnppi", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ap */
    public boolean mo11555ap() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ishmos", (Object[]) null))).booleanValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aq */
    public String mo11554aq() {
        return (String) m11649a(String.class, m11648a("gthmosv", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: ar */
    public String mo11553ar() {
        return (String) m11649a(String.class, m11648a("gthmosdtlv", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: as */
    public int mo11552as() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("gthmpmst", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: at */
    public int mo11551at() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("gthmepmst", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: au */
    public String mo11550au() {
        return (String) m11649a(String.class, m11648a("gtinnerlangmt", (Object[]) null));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: av */
    public int mo11549av() {
        return ((Integer) m11649a(Integer.TYPE, m11648a("gtgramgendt", (Object[]) null))).intValue();
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public Object mo11546b(int i, int i2, boolean z) {
        return m11649a(Object.class, m11648a("gtelcme", Integer.valueOf(i), Integer.valueOf(i2), Boolean.valueOf(z)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: b */
    public Object mo11539b(boolean z, int i, String str, int i2) {
        return m11649a(Object.class, m11648a("gpgiffist", Boolean.valueOf(z), Integer.valueOf(i), str, Integer.valueOf(i2)));
    }

    @Override // com.mob.tools.p237a.InterfaceC6028a
    /* renamed from: aw */
    public boolean mo11548aw() {
        return ((Boolean) m11649a(Boolean.TYPE, m11648a("ctedebbing", (Object[]) null))).booleanValue();
    }
}
