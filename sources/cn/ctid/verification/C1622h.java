package cn.ctid.verification;

import android.app.Activity;
import com.tfd.sdk.LF8bOvWP4;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.ctid.verification.h */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1622h {

    /* renamed from: a */
    public final byte[] f2683a;

    /* renamed from: b */
    public final byte[] f2684b;

    /* renamed from: c */
    public final byte[] f2685c;

    /* renamed from: d */
    private int f2686d = -1;

    /* renamed from: e */
    private int f2687e = -1;

    static {
        System.loadLibrary("jade2_LF8bOvWP4");
        LF8bOvWP4.interfaceV(127);
    }

    public C1622h(String str) {
        byte[] m22077a = C1614K.m22077a(str);
        this.f2684b = m22077a;
        this.f2683a = new byte[18];
        byte[] bArr = this.f2683a;
        System.arraycopy(m22077a, 0, bArr, 0, bArr.length);
        int length = m22077a.length;
        byte[] bArr2 = this.f2683a;
        this.f2685c = new byte[length - bArr2.length];
        int length2 = bArr2.length;
        byte[] bArr3 = this.f2685c;
        System.arraycopy(m22077a, length2, bArr3, 0, bArr3.length);
    }

    /* renamed from: a */
    public native boolean m22065a(Activity activity, InterfaceC1625k interfaceC1625k);
}
