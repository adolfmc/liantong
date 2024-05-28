package com.mob.tools.utils;

import android.content.Context;
import android.text.TextUtils;
import com.mob.commons.C5869r;
import com.mob.tools.MobLog;
import com.mob.tools.p237a.C6029b;
import java.io.File;

/* renamed from: com.mob.tools.utils.b */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6200b {

    /* renamed from: a */
    private final Context f15299a;

    public C6200b(Context context) {
        this.f15299a = context;
    }

    /* renamed from: a */
    public String m11095a() {
        StringBuilder sb = new StringBuilder("");
        try {
            if (m11092d()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            if (m11091e()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            if (m11093c()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            if (m11094b()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
            if (m11090f()) {
                sb.append("1");
            } else {
                sb.append("0");
            }
        } catch (Throwable unused) {
        }
        return sb.toString();
    }

    /* renamed from: b */
    private boolean m11094b() {
        try {
            Object invokeStaticMethodNoThrow = ReflectHelper.invokeStaticMethodNoThrow(ReflectHelper.importClassNoThrow(C5869r.m12200a("027de5dcdjeddidcfdedfhfdejdkfh%ifFdfgkdjedQjf9dj3i=di8f-fh"), null), C5869r.m12200a("003TeeWfi"), "", "ro.build.tags");
            String valueOf = invokeStaticMethodNoThrow != null ? String.valueOf(invokeStaticMethodNoThrow) : null;
            if (!(valueOf != null && valueOf.contains(C5869r.m12200a("009if8fh_iZhkdlDf2dkfh")))) {
                if (!m11089g()) {
                    return false;
                }
            }
            return true;
        } catch (Throwable unused) {
            return false;
        }
    }

    /* renamed from: c */
    private boolean m11093c() {
        return "0".equals(C6029b.m11780a(this.f15299a).m11777a(C5869r.m12200a("020]djedfdfeeded9i=fdef.gd fhPhEfdSg-ed2c'dl-f,dc")));
    }

    /* renamed from: d */
    private boolean m11092d() {
        String m11777a = C6029b.m11780a(this.f15299a).m11777a(C5869r.m12200a("025Gdjedfdfeeded7i0fdddVf6djdiefdiLfAdcfeeded>iWfh2idif"));
        if (m11777a != null) {
            return TextUtils.equals(m11777a.toLowerCase(), "orange") || TextUtils.equals(m11777a.toLowerCase(), "red");
        }
        return false;
    }

    /* renamed from: e */
    private boolean m11091e() {
        String m11777a = C6029b.m11780a(this.f15299a).m11777a(C5869r.m12200a("027Idjedfdfeeded(i+fdddfedf)fid0fddc9f)dddiPcf@dhfh$idif"));
        return m11777a != null && TextUtils.equals(C5869r.m12200a("008@dgQegWedScRdlAfUdc"), m11777a.toLowerCase());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: f */
    private boolean m11090f() {
        /*
            r10 = this;
            int r0 = android.os.Process.myPid()
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            r2 = 1
            r3 = 2
            r4 = 0
            r5 = 0
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
            r6.<init>()     // Catch: java.lang.Throwable -> L87
            r6.append(r0)     // Catch: java.lang.Throwable -> L87
            java.lang.String r0 = "007l<dfeddg:eiQfh"
            java.lang.String r0 = com.mob.commons.C5869r.m12200a(r0)     // Catch: java.lang.Throwable -> L87
            r6.append(r0)     // Catch: java.lang.Throwable -> L87
            java.lang.String r0 = r6.toString()     // Catch: java.lang.Throwable -> L87
            java.lang.StringBuilder r6 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L87
            r6.<init>()     // Catch: java.lang.Throwable -> L87
            java.lang.String r7 = "010cdi,if?lj+djedKcl"
            java.lang.String r7 = com.mob.commons.C5869r.m12200a(r7)     // Catch: java.lang.Throwable -> L87
            r6.append(r7)     // Catch: java.lang.Throwable -> L87
            r6.append(r0)     // Catch: java.lang.Throwable -> L87
            java.lang.String r0 = r6.toString()     // Catch: java.lang.Throwable -> L87
            java.lang.Object r0 = com.mob.commons.C5873u.m12173c(r0)     // Catch: java.lang.Throwable -> L87
            java.lang.String r6 = "014BeeQfi7ei?ejMdg.i<ejQiSdj=fd[df"
            java.lang.String r6 = com.mob.commons.C5869r.m12200a(r6)     // Catch: java.lang.Throwable -> L7f
            java.lang.Object[] r7 = new java.lang.Object[r4]     // Catch: java.lang.Throwable -> L7f
            java.lang.Object r6 = com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r0, r6, r5, r7)     // Catch: java.lang.Throwable -> L7f
            java.io.InputStream r6 = (java.io.InputStream) r6     // Catch: java.lang.Throwable -> L7f
            if (r6 == 0) goto L6f
            java.io.BufferedReader r7 = new java.io.BufferedReader     // Catch: java.lang.Throwable -> L6c
            java.io.InputStreamReader r8 = new java.io.InputStreamReader     // Catch: java.lang.Throwable -> L6c
            java.lang.String r9 = "utf-8"
            r8.<init>(r6, r9)     // Catch: java.lang.Throwable -> L6c
            r7.<init>(r8)     // Catch: java.lang.Throwable -> L6c
        L58:
            java.lang.String r8 = r7.readLine()     // Catch: java.lang.Throwable -> L67
            if (r8 == 0) goto L70
            r1.append(r8)     // Catch: java.lang.Throwable -> L67
            java.lang.String r8 = "\n"
            r1.append(r8)     // Catch: java.lang.Throwable -> L67
            goto L58
        L67:
            r8 = move-exception
            goto L8b
        L69:
            r1 = move-exception
            r7 = r5
            goto Lb8
        L6c:
            r8 = move-exception
            r7 = r5
            goto L8b
        L6f:
            r7 = r5
        L70:
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r4] = r7
            r3[r2] = r6
            com.mob.commons.C5873u.m12179a(r3)
            if (r0 == 0) goto La8
            goto L9d
        L7c:
            r1 = move-exception
            r6 = r5
            goto L85
        L7f:
            r8 = move-exception
            r6 = r5
            goto L8a
        L82:
            r1 = move-exception
            r0 = r5
            r6 = r0
        L85:
            r7 = r6
            goto Lb8
        L87:
            r8 = move-exception
            r0 = r5
            r6 = r0
        L8a:
            r7 = r6
        L8b:
            com.mob.tools.log.NLog r9 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> Lb7
            r9.m11341d(r8)     // Catch: java.lang.Throwable -> Lb7
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r4] = r7
            r3[r2] = r6
            com.mob.commons.C5873u.m12179a(r3)
            if (r0 == 0) goto La8
        L9d:
            java.lang.String r2 = "007<dc(fSfhZi5djeddk"
            java.lang.String r2 = com.mob.commons.C5869r.m12200a(r2)
            java.lang.Object[] r3 = new java.lang.Object[r4]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r0, r2, r5, r3)
        La8:
            java.lang.String r0 = r1.toString()
            java.lang.String r1 = "006.df7d;eedifhdl"
            java.lang.String r1 = com.mob.commons.C5869r.m12200a(r1)
            boolean r0 = r0.contains(r1)
            return r0
        Lb7:
            r1 = move-exception
        Lb8:
            java.io.Closeable[] r3 = new java.io.Closeable[r3]
            r3[r4] = r7
            r3[r2] = r6
            com.mob.commons.C5873u.m12179a(r3)
            if (r0 == 0) goto Lce
            java.lang.String r2 = "007<dc(fSfhZi5djeddk"
            java.lang.String r2 = com.mob.commons.C5869r.m12200a(r2)
            java.lang.Object[] r3 = new java.lang.Object[r4]
            com.mob.tools.utils.ReflectHelper.invokeInstanceMethodNoThrow(r0, r2, r5, r3)
        Lce:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.tools.utils.C6200b.m11090f():boolean");
    }

    /* renamed from: g */
    private boolean m11089g() {
        try {
        } catch (Throwable th) {
            MobLog.getInstance().m11325w(th);
        }
        if (new File(C5869r.m12200a("025l5fhdkfh%if%dfIldjjl*ejdg0jfIdjdgfhVf6djfdXdj9dl")).exists()) {
            return true;
        }
        String[] strArr = {C5869r.m12200a("012l<dc]didlg]edGcdgl"), C5869r.m12200a("016lTdcCdidlg@ed-cdgl]fedi4el"), C5869r.m12200a("017lUdc=didlgJedAcdglBecfedi3el"), C5869r.m12200a("006lKfhfediPel"), C5869r.m12200a("008l2fhdg+l*fedi[el"), C5869r.m12200a("012lFfhdkfhJifCdfZl-fediZel"), C5869r.m12200a("017lTfhdkfh0ifFdfPl2fediIelZfdEf(ec>il"), C5869r.m12200a("021lWfhdkfh%if.df;l)fediLelDefKdUdiQg(fhXdYefJfl"), C5869r.m12200a("016lLfhdkfhCifLdf(l3fhdcTlUecfediBel"), C5869r.m12200a("025lTfhdkfh?if2df(l<dgfhdjYlEff4f2hkJeffCdchkdjeded0il"), C5869r.m12200a("013l-fhdkfhQifDdfJlDecfediWel"), C5869r.m12200a("013lQfhdkfhKif dfIlUfhfediXel"), C5869r.m12200a("012l'dd.fe%dceddjJlJfedi6el"), C5869r.m12200a("006lcdchf"), C5869r.m12200a("005l-dcPdid"), C5869r.m12200a("004lFdc6fSdd")};
        for (String str : strArr) {
            if (new File(str, C5869r.m12200a("002Qfhdg")).exists()) {
                return true;
            }
        }
        for (String str2 : strArr) {
            if (new File(str2, C5869r.m12200a("007!fedgfhdkfeedec")).exists()) {
                return true;
            }
        }
        for (String str3 : strArr) {
            if (new File(str3, C5869r.m12200a("006^df,dYeedifhdl")).exists()) {
                return true;
            }
        }
        return false;
    }
}
