package com.mob.commons.p230b;

import android.content.Context;
import android.database.ContentObserver;
import android.net.Uri;
import android.text.TextUtils;
import com.mob.commons.C5868q;
import com.mob.commons.p230b.AbstractC5764e;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.C6152DH;

/* renamed from: com.mob.commons.b.k */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5777k extends AbstractC5764e {

    /* renamed from: c */
    private C5778a f14248c;

    /* renamed from: d */
    private String f14249d;

    public C5777k(Context context) {
        super(context);
        this.f14248c = null;
        this.f14249d = "100215079";
        if (!TextUtils.isEmpty(C5868q.f14477j)) {
            this.f14249d = C5868q.f14477j;
        }
        NLog mobLog = MobLog.getInstance();
        mobLog.m11342d("oamt vivo appid: " + this.f14249d, new Object[0]);
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: e */
    public synchronized boolean mo12495e() {
        return "1".equals(C6152DH.SyncMtd.getSystemProperties(C5868q.m12203b("034ie1ciegcheg1hTecegcjegecchcbWedhKchdechHe?cichcbecegcf_iiIdcci<heKcb")));
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: b */
    protected AbstractC5764e.C5767b mo12493b() {
        AbstractC5764e.C5767b c5767b = new AbstractC5764e.C5767b();
        c5767b.f14234b = m12500a(0);
        return c5767b;
    }

    /* JADX WARN: Code restructure failed: missing block: B:16:0x003b, code lost:
        if (r0 != null) goto L9;
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x003d, code lost:
        r0.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:25:0x0052, code lost:
        if (r0 == null) goto L13;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String m12500a(int r10) {
        /*
            r9 = this;
            java.lang.String r0 = r9.m12497b(r10)
            r1 = 0
            if (r0 != 0) goto L8
            return r1
        L8:
            android.net.Uri r3 = android.net.Uri.parse(r0)     // Catch: java.lang.Throwable -> L49
            android.content.Context r0 = r9.f14224a     // Catch: java.lang.Throwable -> L49
            android.content.ContentResolver r2 = r0.getContentResolver()     // Catch: java.lang.Throwable -> L49
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r0 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L49
            if (r0 == 0) goto L3b
            boolean r2 = r0.moveToNext()     // Catch: java.lang.Throwable -> L39
            if (r2 == 0) goto L3b
            java.lang.String r2 = "005)cc@cf'cf*e"
            java.lang.String r2 = com.mob.commons.C5868q.m12203b(r2)     // Catch: java.lang.Throwable -> L39
            int r2 = r0.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L39
            java.lang.String r1 = r0.getString(r2)     // Catch: java.lang.Throwable -> L39
            if (r0 == 0) goto L35
            r0.close()     // Catch: java.lang.Throwable -> L35
        L35:
            r9.m12496c(r10)     // Catch: java.lang.Throwable -> L38
        L38:
            return r1
        L39:
            r2 = move-exception
            goto L4b
        L3b:
            if (r0 == 0) goto L40
        L3d:
            r0.close()     // Catch: java.lang.Throwable -> L40
        L40:
            r9.m12496c(r10)     // Catch: java.lang.Throwable -> L55
            goto L55
        L44:
            r0 = move-exception
            r8 = r1
            r1 = r0
            r0 = r8
            goto L57
        L49:
            r2 = move-exception
            r0 = r1
        L4b:
            com.mob.tools.log.NLog r3 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> L56
            r3.m11341d(r2)     // Catch: java.lang.Throwable -> L56
            if (r0 == 0) goto L40
            goto L3d
        L55:
            return r1
        L56:
            r1 = move-exception
        L57:
            if (r0 == 0) goto L5c
            r0.close()     // Catch: java.lang.Throwable -> L5c
        L5c:
            r9.m12496c(r10)     // Catch: java.lang.Throwable -> L5f
        L5f:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p230b.C5777k.m12500a(int):java.lang.String");
    }

    /* renamed from: b */
    private String m12497b(int i) {
        if (i == 0) {
            return C5868q.m12203b("051b4dcUdhedhjkkbMdcceecccchccdcecccceegecdhcbfjcidcccchcb9eSci_kDdhcb2edhZchdech)eJcidhcb)kPfkdkdhej");
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m12498a(boolean z, int i) {
        try {
            String m12500a = m12500a(i);
            if (i == 0) {
                m12522a(m12500a);
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: c */
    private void m12496c(int i) {
        if (i == 0 && this.f14248c == null) {
            this.f14248c = new C5778a(this, 0);
            this.f14224a.getContentResolver().registerContentObserver(Uri.parse(m12497b(0)), true, this.f14248c);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.mob.commons.b.k$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5778a extends ContentObserver {

        /* renamed from: a */
        private int f14250a;

        /* renamed from: b */
        private C5777k f14251b;

        public C5778a(C5777k c5777k, int i) {
            super(null);
            this.f14250a = i;
            this.f14251b = c5777k;
        }

        @Override // android.database.ContentObserver
        public void onChange(boolean z) {
            C5777k c5777k = this.f14251b;
            if (c5777k != null) {
                c5777k.m12498a(z, this.f14250a);
            }
        }
    }
}
