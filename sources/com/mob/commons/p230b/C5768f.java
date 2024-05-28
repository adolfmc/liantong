package com.mob.commons.p230b;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import com.mob.commons.C5868q;
import java.util.ArrayList;

/* renamed from: com.mob.commons.b.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5768f extends AbstractC5764e {

    /* renamed from: c */
    private C5770a f14235c;

    /* renamed from: d */
    private C5770a f14236d;

    /* renamed from: e */
    private BroadcastReceiver f14237e;

    public C5768f(Context context) {
        super(context);
        this.f14235c = new C5770a(C5868q.m12203b("0047dc[c<chcb"));
        this.f14236d = new C5770a(C5868q.m12203b("009EegcfBii$dcciGhe7cb"));
    }

    @Override // com.mob.commons.p230b.AbstractC5764e
    /* renamed from: d */
    public synchronized String mo12514d() {
        if (this.f14224a == null) {
            return null;
        }
        return m12517a(this.f14224a.getApplicationContext(), this.f14235c, false);
    }

    /* JADX WARN: Code restructure failed: missing block: B:38:0x0097, code lost:
        if (r1 != null) goto L44;
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x0099, code lost:
        r1.close();
     */
    /* JADX WARN: Code restructure failed: missing block: B:46:0x00a9, code lost:
        if (r1 == null) goto L43;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x00ac, code lost:
        return null;
     */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private java.lang.String m12517a(android.content.Context r10, com.mob.commons.p230b.C5768f.C5770a r11, boolean r12) {
        /*
            r9 = this;
            r0 = 0
            if (r11 != 0) goto L4
            return r0
        L4:
            if (r12 != 0) goto L11
            boolean r1 = r11.m12508b()
            if (r1 == 0) goto L11
            java.lang.String r10 = com.mob.commons.p230b.C5768f.C5770a.m12510a(r11)
            return r10
        L11:
            java.lang.String r1 = "036bLdcYdhedhjkkb1dcceecce;e-chfbcfecdeFf(cjceZeKecdcTied=chcbegcbck5k"
            java.lang.String r1 = com.mob.commons.C5868q.m12203b(r1)
            android.net.Uri r3 = android.net.Uri.parse(r1)
            android.content.ContentResolver r2 = r10.getContentResolver()     // Catch: java.lang.Throwable -> La0
            r4 = 0
            r5 = 0
            r10 = 1
            java.lang.String[] r6 = new java.lang.String[r10]     // Catch: java.lang.Throwable -> La0
            java.lang.String r1 = com.mob.commons.p230b.C5768f.C5770a.m12507b(r11)     // Catch: java.lang.Throwable -> La0
            r8 = 0
            r6[r8] = r1     // Catch: java.lang.Throwable -> La0
            r7 = 0
            android.database.Cursor r1 = r2.query(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> La0
            if (r1 == 0) goto L87
            r1.moveToFirst()     // Catch: java.lang.Throwable -> L85
            java.lang.String r2 = "005GccRcf9cf,e"
            java.lang.String r2 = com.mob.commons.C5868q.m12203b(r2)     // Catch: java.lang.Throwable -> L85
            int r2 = r1.getColumnIndex(r2)     // Catch: java.lang.Throwable -> L85
            if (r2 < 0) goto L49
            java.lang.String r2 = r1.getString(r2)     // Catch: java.lang.Throwable -> L85
            r11.m12509a(r2)     // Catch: java.lang.Throwable -> L85
            goto L4a
        L49:
            r2 = r0
        L4a:
            if (r12 != 0) goto L7f
            java.lang.String r12 = "007eXdb^i?chciWe3cb"
            java.lang.String r12 = com.mob.commons.C5868q.m12203b(r12)     // Catch: java.lang.Throwable -> L85
            int r12 = r1.getColumnIndex(r12)     // Catch: java.lang.Throwable -> L85
            if (r12 < 0) goto L5f
            long r3 = r1.getLong(r12)     // Catch: java.lang.Throwable -> L85
            r11.m12511a(r3)     // Catch: java.lang.Throwable -> L85
        L5f:
            java.lang.String r11 = "004bWdccbBe"
            java.lang.String r11 = com.mob.commons.C5868q.m12203b(r11)     // Catch: java.lang.Throwable -> L85
            int r11 = r1.getColumnIndex(r11)     // Catch: java.lang.Throwable -> L85
            if (r11 < 0) goto L7f
            int r11 = r1.getInt(r11)     // Catch: java.lang.Throwable -> L85
            r12 = 1000(0x3e8, float:1.401E-42)
            if (r11 == r12) goto L7f
            r9.m12513f()     // Catch: java.lang.Throwable -> L85
            boolean r11 = r9.m12515a(r8)     // Catch: java.lang.Throwable -> L85
            if (r11 != 0) goto L7f
            r9.m12515a(r10)     // Catch: java.lang.Throwable -> L85
        L7f:
            if (r1 == 0) goto L84
            r1.close()     // Catch: java.lang.Throwable -> L84
        L84:
            return r2
        L85:
            r10 = move-exception
            goto La2
        L87:
            if (r12 == 0) goto L8e
            java.lang.String r12 = "1"
            r11.m12509a(r12)     // Catch: java.lang.Throwable -> L85
        L8e:
            boolean r11 = r9.m12515a(r8)     // Catch: java.lang.Throwable -> L85
            if (r11 == 0) goto L97
            r9.m12515a(r10)     // Catch: java.lang.Throwable -> L85
        L97:
            if (r1 == 0) goto Lac
        L99:
            r1.close()     // Catch: java.lang.Throwable -> Lac
            goto Lac
        L9d:
            r10 = move-exception
            r1 = r0
            goto Lae
        La0:
            r10 = move-exception
            r1 = r0
        La2:
            com.mob.tools.log.NLog r11 = com.mob.tools.MobLog.getInstance()     // Catch: java.lang.Throwable -> Lad
            r11.m11341d(r10)     // Catch: java.lang.Throwable -> Lad
            if (r1 == 0) goto Lac
            goto L99
        Lac:
            return r0
        Lad:
            r10 = move-exception
        Lae:
            if (r1 == 0) goto Lb3
            r1.close()     // Catch: java.lang.Throwable -> Lb3
        Lb3:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.mob.commons.p230b.C5768f.m12517a(android.content.Context, com.mob.commons.b.f$a, boolean):java.lang.String");
    }

    /* renamed from: f */
    private void m12513f() {
        try {
            if (this.f14237e == null) {
                IntentFilter intentFilter = new IntentFilter();
                intentFilter.addAction(C5868q.m12203b("044b+dcceecceQe=chfbcfecde%f%cjceFeKecdcFiedHchcbecdkfidjdhfkdgcgfkfjffdgcgdhejcgfieidkdghcff"));
                this.f14237e = new BroadcastReceiver() { // from class: com.mob.commons.b.f.1
                    @Override // android.content.BroadcastReceiver
                    public void onReceive(Context context, Intent intent) {
                        String stringExtra;
                        ArrayList<String> stringArrayListExtra;
                        if (context == null || intent == null) {
                            return;
                        }
                        try {
                            boolean z = false;
                            if (intent.getIntExtra(C5868q.m12203b("016Udc)iedFdhcbdgdc9hLchdecjekKfcIdd"), 0) == 2 && (stringArrayListExtra = intent.getStringArrayListExtra(C5868q.m12203b("017Mdc$iedNdhcbfjOcb^ckIc2ddKeKebchegTh"))) != null) {
                                z = stringArrayListExtra.contains(context.getPackageName());
                            }
                            if (z && (stringExtra = intent.getStringExtra(C5868q.m12203b("0100dc?ied;dhcbdjcj.ie"))) != null && stringExtra.equals(C5868q.m12203b("004<dc[c<chcb"))) {
                                C5768f.this.f14235c.m12511a(0L);
                            }
                        } catch (Throwable unused) {
                        }
                    }
                };
                if (Build.VERSION.SDK_INT < 33) {
                    this.f14224a.registerReceiver(this.f14237e, intentFilter, C5868q.m12203b("048bBdcceecce8e[chfbcfecdeSfDcjce*e6ecdc>iedPchcbecGie%cicechegegchdc-dVecfkfjffdgcgdhejcgfieidkdghcff"), null);
                } else {
                    this.f14224a.registerReceiver(this.f14237e, intentFilter, C5868q.m12203b("048b+dcceecce?e[chfbcfecde-f9cjce!e_ecdc'ied%chcbecOieQcicechegegchdcYdXecfkfjffdgcgdhejcgfieidkdghcff"), null, 4);
                }
            }
        } catch (Throwable unused) {
        }
    }

    /* renamed from: a */
    private boolean m12515a(boolean z) {
        C5770a c5770a;
        if (!z && (c5770a = this.f14236d) != null && c5770a.m12512a() != null) {
            return this.f14236d.m12512a().equals("0");
        }
        String m12517a = m12517a(this.f14224a, this.f14236d, true);
        return m12517a != null && "0".equals(m12517a);
    }

    /* renamed from: com.mob.commons.b.f$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C5770a {

        /* renamed from: a */
        private String f14239a;

        /* renamed from: b */
        private long f14240b;

        /* renamed from: c */
        private String f14241c;

        public C5770a(String str) {
            this.f14239a = str;
        }

        /* renamed from: a */
        public void m12511a(long j) {
            this.f14240b = j;
        }

        /* renamed from: a */
        public String m12512a() {
            return this.f14241c;
        }

        /* renamed from: a */
        public void m12509a(String str) {
            this.f14241c = str;
        }

        /* renamed from: b */
        public boolean m12508b() {
            return this.f14240b > System.currentTimeMillis();
        }
    }
}
