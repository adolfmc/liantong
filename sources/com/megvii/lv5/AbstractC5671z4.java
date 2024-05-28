package com.megvii.lv5;

import android.util.Log;
import com.megvii.lv5.C5668z3;
import java.io.UnsupportedEncodingException;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.z4 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC5671z4<T> extends AbstractC5652x3<T> {

    /* renamed from: n */
    public static final String f13974n = String.format("application/json; charset=%s", "utf-8");

    /* renamed from: l */
    public final C5668z3.InterfaceC5670b<T> f13975l;

    /* renamed from: m */
    public final String f13976m;

    public AbstractC5671z4(int i, String str, String str2, C5668z3.InterfaceC5670b<T> interfaceC5670b, C5668z3.InterfaceC5669a interfaceC5669a) {
        super(i, str, interfaceC5669a);
        this.f13975l = interfaceC5670b;
        this.f13976m = str2;
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: a */
    public void mo12872a(T t) {
        C5668z3.InterfaceC5670b<T> interfaceC5670b = this.f13975l;
        if (interfaceC5670b != null) {
            interfaceC5670b.mo12873a(t);
        }
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: b */
    public byte[] mo12871b() {
        try {
            String str = this.f13976m;
            if (str == null) {
                return null;
            }
            return str.getBytes("utf-8");
        } catch (UnsupportedEncodingException unused) {
            Log.wtf("Volley", C5440f4.m13536a("Unsupported Encoding while trying to get the bytes of %s using %s", this.f13976m, "utf-8"));
            return null;
        }
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    /* renamed from: c */
    public String mo12870c() {
        return f13974n;
    }

    @Override // com.megvii.lv5.AbstractC5652x3
    @Deprecated
    /* renamed from: e */
    public byte[] mo12869e() {
        return mo12871b();
    }
}
