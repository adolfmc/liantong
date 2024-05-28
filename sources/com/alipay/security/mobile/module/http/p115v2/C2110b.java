package com.alipay.security.mobile.module.http.p115v2;

import android.content.Context;
import com.alipay.security.mobile.module.http.C2104d;
import com.alipay.security.mobile.module.http.InterfaceC2100a;
import com.alipay.security.mobile.module.http.model.C2106b;
import com.alipay.security.mobile.module.http.model.C2107c;
import com.alipay.security.mobile.module.http.model.C2108d;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.alipay.security.mobile.module.http.v2.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2110b implements InterfaceC2109a {

    /* renamed from: a */
    private static InterfaceC2109a f4040a;

    /* renamed from: b */
    private static InterfaceC2100a f4041b;

    /* renamed from: a */
    public static InterfaceC2109a m20462a(Context context, String str) {
        if (context == null) {
            return null;
        }
        if (f4040a == null) {
            f4041b = C2104d.m20469a(context, str);
            f4040a = new C2110b();
        }
        return f4040a;
    }

    @Override // com.alipay.security.mobile.module.http.p115v2.InterfaceC2109a
    /* renamed from: a */
    public C2107c mo20461a(C2108d c2108d) {
        return C2106b.m20466a(f4041b.mo20473a(C2106b.m20467a(c2108d)));
    }

    @Override // com.alipay.security.mobile.module.http.p115v2.InterfaceC2109a
    /* renamed from: a */
    public boolean mo20460a(String str) {
        return f4041b.mo20471a(str);
    }
}
