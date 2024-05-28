package com.mob.commons;

import android.os.Message;
import android.text.TextUtils;
import com.mob.MobSDK;
import com.mob.commons.p229a.C5731l;
import com.mob.tools.MobLog;
import com.mob.tools.network.NetCommunicator;
import com.mob.tools.utils.AbstractRunnableC6217h;
import com.mob.tools.utils.C6152DH;
import com.mob.tools.utils.ResHelper;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

/* renamed from: com.mob.commons.h */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5843h {

    /* renamed from: a */
    private static final String f14377a = C5869r.m12200a("004Nfddcdk$g");

    /* renamed from: b */
    private static C5843h f14378b;

    /* renamed from: c */
    private NetCommunicator f14379c;

    /* renamed from: f */
    private String f14382f;

    /* renamed from: d */
    private SimpleDateFormat f14380d = new SimpleDateFormat(C5869r.m12200a("025Ndkdkdkdkhkhchchkdcdciffjfj>kIdfdf*kGfhfhfdejejejifgc"));

    /* renamed from: e */
    private HashMap<String, Object> f14381e = new HashMap<>();

    /* renamed from: g */
    private int f14383g = -1;

    /* renamed from: h */
    private Runnable f14384h = new AbstractRunnableC6217h() { // from class: com.mob.commons.h.1
        @Override // com.mob.tools.utils.AbstractRunnableC6217h
        /* renamed from: a */
        public void mo10991a() {
            if (C5747b.m12562c()) {
                C5843h.this.m12279c();
            }
        }
    };

    /* renamed from: a */
    public static synchronized C5843h m12291a() {
        C5843h c5843h;
        synchronized (C5843h.class) {
            if (f14378b == null) {
                f14378b = new C5843h();
            }
            c5843h = f14378b;
        }
        return c5843h;
    }

    private C5843h() {
        this.f14382f = null;
        this.f14382f = UUID.randomUUID().toString();
    }

    /* renamed from: b */
    private synchronized int m12282b() {
        return this.f14383g;
    }

    /* renamed from: a */
    public synchronized void m12290a(int i) {
        this.f14383g = i;
        if (i != 1 && i != 4 && i != 17 && i != 18 && i != 19 && i != 20 && i != 10) {
        }
    }

    /* renamed from: a */
    public synchronized void m12288a(int i, Throwable th) {
        m12289a(i, m12282b(), th, null, "-99");
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: a */
    private synchronized void m12289a(int i, int i2, Throwable th, String str, String str2) {
        if (th == null) {
            MobLog.getInstance().m11342d(str, new Object[0]);
        } else {
            MobLog.getInstance().m11341d(th);
        }
        if (C5831e.m12321a()) {
            return;
        }
        final Message message = new Message();
        message.what = 1;
        Object[] objArr = new Object[5];
        objArr[0] = Long.valueOf(System.currentTimeMillis());
        if (th == null) {
            th = str;
        }
        objArr[1] = th;
        objArr[2] = Integer.valueOf(i);
        objArr[3] = Integer.valueOf(i2);
        objArr[4] = str2;
        message.obj = objArr;
        C5892y.f14526d.execute(new AbstractRunnableC6217h() { // from class: com.mob.commons.h.2
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                C5843h.this.m12287a(message);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12287a(Message message) {
        String valueOf;
        if (this.f14381e.size() > 10) {
            m12278c(this.f14381e);
            this.f14381e.clear();
        }
        Object[] objArr = (Object[]) message.obj;
        this.f14381e.put(C5869r.m12200a("002-fhdc"), this.f14382f);
        ArrayList arrayList = (ArrayList) this.f14381e.get(C5869r.m12200a("004gCdifhAi"));
        if (arrayList == null) {
            arrayList = new ArrayList();
        }
        HashMap hashMap = new HashMap();
        hashMap.put(C5869r.m12200a("002ci"), objArr[0]);
        if (objArr[1] instanceof Throwable) {
            valueOf = m12284a((Throwable) objArr[1]);
        } else {
            valueOf = String.valueOf(objArr[1]);
        }
        if (!TextUtils.isEmpty(valueOf)) {
            valueOf = valueOf.replaceAll("\r\n\t", " ").replaceAll("\n\t", " ").replaceAll("\n", " ");
        }
        String m12200a = C5869r.m12200a("002Jdfee");
        hashMap.put(m12200a, "[" + this.f14380d.format(objArr[0]) + "][" + objArr[2] + "][" + objArr[3] + "][" + objArr[4] + "] " + valueOf);
        hashMap.put(C5869r.m12200a("002fi"), objArr[2]);
        hashMap.put(C5869r.m12200a("002jXed"), objArr[3]);
        arrayList.add(hashMap);
        this.f14381e.put(C5869r.m12200a("004gDdifh$i"), arrayList);
        if (C5831e.m12321a()) {
            return;
        }
        C6152DH.requester(MobSDK.getContext()).request(new C6152DH.DHResponder() { // from class: com.mob.commons.h.3
            @Override // com.mob.tools.utils.C6152DH.DHResponder
            public void onResponse(C6152DH.DHResponse dHResponse) {
                C5731l.m12681a().m12671b(10L, C5843h.this.f14384h);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m12279c() {
        boolean z;
        File[] listFiles;
        if (this.f14381e.size() > 0) {
            z = m12283a(this.f14381e);
            if (!z) {
                m12278c(this.f14381e);
            }
            this.f14381e.clear();
        } else {
            z = true;
        }
        if (z) {
            File m12276e = m12276e();
            if (!m12276e.exists() || !m12276e.isDirectory() || (listFiles = m12276e.listFiles()) == null || listFiles.length <= 0) {
                return;
            }
            for (File file : listFiles) {
                if (m12283a((HashMap) ResHelper.readObjectFromFile(file.getAbsolutePath())) && !file.delete()) {
                    file.delete();
                }
            }
        }
    }

    /* renamed from: a */
    private boolean m12283a(HashMap<String, Object> hashMap) {
        try {
            return m12280b(hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            try {
                return m12280b(hashMap);
            } catch (Throwable th2) {
                MobLog.getInstance().m11341d(th2);
                return false;
            }
        }
    }

    /* renamed from: b */
    private boolean m12280b(HashMap<String, Object> hashMap) throws Throwable {
        if (hashMap == null || hashMap.isEmpty()) {
            return true;
        }
        HashMap<String, Object> m12189e = C5871t.m12189e();
        m12189e.put(C5869r.m12200a("006f?djdjeddjfh"), hashMap);
        m12277d();
        HashMap hashMap2 = (HashMap) this.f14379c.requestSynchronized(m12189e, C5847i.m12275a().m12272a("dtc") + C5869r.m12200a("004lBdcdjFg"), false);
        return hashMap2 == null || hashMap2.isEmpty();
    }

    /* renamed from: d */
    private void m12277d() {
        if (this.f14379c == null) {
            this.f14379c = new NetCommunicator(1024, "ab0a0a6473d1891d388773574764b239d4ad80cb2fd3a83d81d03901c1548c13fee7c9692c326e6682b239d4c5d0021d1b607642c47ec29f10b0602908c3e6c9", "23c3c8cb41c47dd288cc7f4c218fbc7c839a34e0a0d1b2130e87b7914936b120a2d6570ee7ac66282328d50f2acfd82f2259957c89baea32547758db05de9cd7c6822304c8e45742f24bbbe41c1e12f09e18c6fab4d078065f2e5aaed94c900c66e8bbf8a120eefa7bd1fb52114d529250084f5f6f369ed4ce9645978dd30c51");
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:27:0x0047 A[Catch: all -> 0x0040, TRY_LEAVE, TryCatch #0 {all -> 0x0040, blocks: (B:25:0x0043, B:27:0x0047, B:30:0x0055), top: B:35:0x0043 }] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0055 A[Catch: all -> 0x0040, TRY_ENTER, TRY_LEAVE, TryCatch #0 {all -> 0x0040, blocks: (B:25:0x0043, B:27:0x0047, B:30:0x0055), top: B:35:0x0043 }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m12284a(java.lang.Throwable r6) {
        /*
            r5 = this;
            if (r6 != 0) goto L5
            java.lang.String r6 = ""
            return r6
        L5:
            r0 = 0
            r1 = r6
        L7:
            r2 = 0
            r3 = 1
            if (r1 == 0) goto L1e
            boolean r4 = r1 instanceof java.net.UnknownHostException     // Catch: java.lang.Throwable -> L42
            if (r4 == 0) goto L19
            java.lang.String r6 = ""
            java.io.Closeable[] r1 = new java.io.Closeable[r3]
            r1[r2] = r0
            com.mob.commons.C5873u.m12179a(r1)
            return r6
        L19:
            java.lang.Throwable r1 = r1.getCause()     // Catch: java.lang.Throwable -> L42
            goto L7
        L1e:
            java.io.StringWriter r1 = new java.io.StringWriter     // Catch: java.lang.Throwable -> L42
            r1.<init>()     // Catch: java.lang.Throwable -> L42
            java.io.PrintWriter r0 = new java.io.PrintWriter     // Catch: java.lang.Throwable -> L3d
            r0.<init>(r1)     // Catch: java.lang.Throwable -> L3d
            r6.printStackTrace(r0)     // Catch: java.lang.Throwable -> L3d
            r0.flush()     // Catch: java.lang.Throwable -> L3d
            java.lang.String r6 = r1.toString()     // Catch: java.lang.Throwable -> L3d
            java.io.Closeable[] r0 = new java.io.Closeable[r3]
            r0[r2] = r1
            com.mob.commons.C5873u.m12179a(r0)
            return r6
        L3a:
            r6 = move-exception
            r0 = r1
            goto L61
        L3d:
            r6 = move-exception
            r0 = r1
            goto L43
        L40:
            r6 = move-exception
            goto L61
        L42:
            r6 = move-exception
        L43:
            boolean r1 = r6 instanceof java.lang.OutOfMemoryError     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L55
            java.lang.String r6 = "023Ree3fi9ej[idcRdlekdjVdcf)ejOi.djdi6ePeeifededdf"
            java.lang.String r6 = com.mob.commons.C5869r.m12200a(r6)     // Catch: java.lang.Throwable -> L40
            java.io.Closeable[] r1 = new java.io.Closeable[r3]
            r1[r2] = r0
            com.mob.commons.C5873u.m12179a(r1)
            return r6
        L55:
            java.lang.String r6 = r6.getMessage()     // Catch: java.lang.Throwable -> L40
            java.io.Closeable[] r1 = new java.io.Closeable[r3]
            r1[r2] = r0
            com.mob.commons.C5873u.m12179a(r1)
            return r6
        L61:
            java.io.Closeable[] r1 = new java.io.Closeable[r3]
            r1[r2] = r0
            com.mob.commons.C5873u.m12179a(r1)
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.C5843h.m12284a(java.lang.Throwable):java.lang.String");
    }

    /* renamed from: e */
    private File m12276e() {
        return new File(ResHelper.getDataCache(MobSDK.getContext()), f14377a);
    }

    /* renamed from: c */
    private void m12278c(HashMap<String, Object> hashMap) {
        File[] listFiles;
        try {
            File m12276e = m12276e();
            if (!m12276e.exists() || !m12276e.isDirectory()) {
                m12276e.delete();
                m12276e.mkdirs();
            }
            StringBuilder sb = new StringBuilder();
            sb.append(f14377a);
            sb.append("_");
            int i = 0;
            sb.append(0);
            File file = new File(m12276e, sb.toString());
            if (file.exists() && (listFiles = m12276e.listFiles()) != null && listFiles.length > 0) {
                file = new File(m12276e, f14377a + "_0");
                while (file.exists()) {
                    i++;
                    file = new File(m12276e, f14377a + "_" + i);
                }
            }
            ResHelper.saveObjectToFile(file.getPath(), hashMap);
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
        }
    }
}
