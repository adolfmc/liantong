package com.taisys.oti;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.p083v4.app.ActivityCompat;
import android.util.Log;
import com.taisys.oti.C10333a;
import com.taisys.oti.C10338b;
import com.taisys.oti.C10344c;

/* renamed from: com.taisys.oti.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C10351f {

    /* renamed from: a */
    public static final int f19921a = 0;

    /* renamed from: b */
    public static final int f19922b = 1;

    /* renamed from: c */
    public static final int f19923c = 2;

    /* renamed from: d */
    public static final int f19924d = 3;

    /* renamed from: e */
    public static final int f19925e = 4;

    /* renamed from: f */
    public static final int f19926f = -1;

    /* renamed from: g */
    public static boolean f19927g = true;

    /* renamed from: h */
    public static boolean f19928h = true;

    /* renamed from: i */
    public static String f19929i;

    /* renamed from: j */
    public static String f19930j;

    /* renamed from: k */
    public static int f19931k;

    /* renamed from: l */
    private Context f19932l;

    /* renamed from: m */
    private InterfaceC10356a f19933m;

    /* renamed from: p */
    private C10344c f19936p;

    /* renamed from: q */
    private C10333a f19937q;

    /* renamed from: r */
    private C10338b f19938r;

    /* renamed from: n */
    private int f19934n = -1;

    /* renamed from: o */
    private boolean f19935o = false;

    /* renamed from: s */
    private int[] f19939s = {2, 3, 4};

    /* renamed from: u */
    private Handler f19941u = new Handler() { // from class: com.taisys.oti.f.1
        @Override // android.os.Handler
        public void handleMessage(Message message) {
            C10350e.m6257d("Message What=" + message.what);
            switch (message.what) {
                case 0:
                    if (C10351f.this.f19933m != null) {
                        C10351f.this.f19933m.mo6231a(true);
                        return;
                    }
                    return;
                case 1:
                    if (C10351f.this.f19933m != null) {
                        C10351f.this.f19933m.mo6231a(false);
                        return;
                    }
                    return;
                default:
                    return;
            }
        }
    };

    /* renamed from: t */
    private int f19940t = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.taisys.oti.f$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface InterfaceC10356a {
        /* renamed from: a */
        void mo6231a(boolean z);
    }

    public C10351f(Context context, InterfaceC10356a interfaceC10356a) {
        this.f19932l = context;
        this.f19933m = interfaceC10356a;
        m6235j();
        m6243d();
    }

    /* renamed from: a */
    public int m6255a() {
        return this.f19934n;
    }

    /* renamed from: a */
    public void m6248a(boolean z) {
        this.f19935o = z;
    }

    /* renamed from: d */
    private void m6243d() {
        int i = this.f19940t;
        int[] iArr = this.f19939s;
        if (i < iArr.length) {
            switch (iArr[i]) {
                case 2:
                    m6238g();
                    return;
                case 3:
                    m6237h();
                    return;
                case 4:
                    m6236i();
                    return;
                default:
                    return;
            }
        }
        this.f19940t = 0;
        Message.obtain(this.f19941u, 1).sendToTarget();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: e */
    public void m6241e() {
        this.f19940t++;
        m6243d();
    }

    /* renamed from: f */
    private boolean m6239f() {
        try {
            Class.forName("android.support.v4.app.ActivityCompat");
            return true;
        } catch (ClassNotFoundException unused) {
            return false;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: g */
    private void m6238g() {
        if (f19927g) {
            boolean z = true;
            if (Build.VERSION.SDK_INT >= 23 && m6239f()) {
                z = (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.READ_SMS") == 0) & true & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.WRITE_SMS") == 0) & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.SEND_SMS") == 0) & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.RECEIVE_SMS") == 0);
            }
            if (z) {
                this.f19936p = new C10344c(this.f19932l, new C10344c.InterfaceC10347c() { // from class: com.taisys.oti.f.2
                    @Override // com.taisys.oti.C10344c.InterfaceC10347c
                    /* renamed from: a */
                    public void mo6234a(boolean z2) {
                        if (z2) {
                            C10351f.this.f19934n = 2;
                            Message.obtain(C10351f.this.f19941u, 0).sendToTarget();
                            return;
                        }
                        C10351f.this.f19936p = null;
                        C10351f.this.m6241e();
                    }
                });
                return;
            }
            C10350e.m6257d("NO SMS Permission");
            m6241e();
            return;
        }
        m6241e();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: h */
    private void m6237h() {
        if (f19928h) {
            boolean z = true;
            if (Build.VERSION.SDK_INT >= 23 && m6239f()) {
                z = (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.READ_SMS") == 0) & true & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.READ_CONTACTS") == 0) & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.WRITE_CONTACTS") == 0);
            }
            if (z) {
                this.f19937q = new C10333a(this.f19932l, new C10333a.InterfaceC10336b() { // from class: com.taisys.oti.f.3
                    @Override // com.taisys.oti.C10333a.InterfaceC10336b
                    /* renamed from: a */
                    public void mo6233a(boolean z2) {
                        Message.obtain(C10351f.this.f19941u, 1);
                        if (z2) {
                            C10351f.this.f19934n = 3;
                            Message.obtain(C10351f.this.f19941u, 0).sendToTarget();
                            return;
                        }
                        C10351f.this.f19937q.m6340b();
                        C10351f.this.f19937q = null;
                        C10351f.this.m6241e();
                    }
                });
                return;
            }
            C10350e.m6257d("NO ADN Permission");
            m6241e();
            return;
        }
        m6241e();
    }

    @SuppressLint({"NewApi"})
    /* renamed from: i */
    private void m6236i() {
        if (f19928h) {
            boolean z = true;
            if (Build.VERSION.SDK_INT >= 23 && m6239f()) {
                z = (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.READ_SMS") == 0) & true & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.READ_CONTACTS") == 0) & (ActivityCompat.checkSelfPermission(this.f19932l, "android.permission.WRITE_CONTACTS") == 0);
            }
            if (z) {
                this.f19938r = new C10338b(this.f19932l, new C10338b.InterfaceC10341b() { // from class: com.taisys.oti.f.4
                    @Override // com.taisys.oti.C10338b.InterfaceC10341b
                    /* renamed from: a */
                    public void mo6232a(boolean z2) {
                        Message.obtain(C10351f.this.f19941u, 1);
                        if (z2) {
                            C10351f.this.f19934n = 4;
                            Message.obtain(C10351f.this.f19941u, 0).sendToTarget();
                            return;
                        }
                        C10351f.this.f19938r.m6313b();
                        C10351f.this.f19938r = null;
                        C10351f.this.m6241e();
                    }
                });
                return;
            } else {
                m6241e();
                return;
            }
        }
        m6241e();
    }

    /* renamed from: b */
    public void m6247b() {
        C10333a c10333a = this.f19937q;
        if (c10333a != null) {
            c10333a.m6340b();
        }
        C10338b c10338b = this.f19938r;
        if (c10338b != null) {
            c10338b.m6313b();
        }
    }

    /* renamed from: a */
    public byte[] m6254a(int i, byte[] bArr) {
        if (this.f19935o) {
            byte[] bArr2 = null;
            switch (this.f19934n) {
                case 2:
                    if (this.f19936p != null) {
                        if (i == 1) {
                            bArr2 = this.f19936p.m6289a(C10350e.f19910c, bArr);
                            break;
                        } else {
                            return null;
                        }
                    }
                    break;
                case 3:
                    if (this.f19937q != null) {
                        if (i == 1) {
                            bArr2 = this.f19937q.m6341a(C10350e.f19912e, bArr);
                            break;
                        } else {
                            return null;
                        }
                    }
                    break;
                case 4:
                    if (this.f19938r != null) {
                        if (i == 1) {
                            bArr2 = this.f19938r.m6315a(C10350e.f19912e, bArr);
                            break;
                        } else {
                            return null;
                        }
                    }
                    break;
            }
            if (bArr2 == null || !C10350e.m6267a(bArr2)) {
                return null;
            }
            return bArr2;
        }
        return null;
    }

    /* renamed from: c */
    public byte[] m6245c() {
        switch (this.f19934n) {
            case 2:
                C10344c c10344c = this.f19936p;
                if (c10344c != null) {
                    return c10344c.m6284d();
                }
                return null;
            case 3:
                C10333a c10333a = this.f19937q;
                if (c10333a != null) {
                    return c10333a.m6337c();
                }
                return null;
            case 4:
                C10338b c10338b = this.f19938r;
                if (c10338b != null) {
                    return c10338b.m6308c();
                }
                return null;
            default:
                return null;
        }
    }

    @SuppressLint({"NewApi"})
    /* renamed from: j */
    private void m6235j() {
        f19929i = Build.MANUFACTURER;
        f19930j = Build.MODEL;
        f19931k = Build.VERSION.SDK_INT;
        Log.i("OTI", "Manufacturer=" + f19929i);
        Log.i("OTI", "Modal=" + f19930j);
        Log.i("OTI", "SDK=" + f19931k);
    }
}
