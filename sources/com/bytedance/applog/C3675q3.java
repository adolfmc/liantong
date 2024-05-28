package com.bytedance.applog;

import android.content.Context;
import android.os.SystemProperties;
import android.util.Base64;
import android.util.Log;
import com.bun.miitmdid.core.JLibrary;
import com.bytedance.applog.InterfaceC3645n3;
import com.bytedance.p170dr.VivoIdentifier;

/* renamed from: com.bytedance.applog.q3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3675q3 implements InterfaceC3645n3 {

    /* renamed from: b */
    public static final String f8781b;

    /* renamed from: c */
    public static final AbstractC3749z2<Boolean> f8782c;

    /* renamed from: a */
    public VivoIdentifier f8783a;

    /* renamed from: com.bytedance.applog.q3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3676a extends AbstractC3749z2<Boolean> {
        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public Boolean mo16989a(Object[] objArr) {
            String str = "0";
            try {
                str = SystemProperties.get(C3675q3.f8781b, "0");
            } catch (Throwable unused) {
            }
            return Boolean.valueOf("1".equals(str));
        }
    }

    static {
        String str;
        try {
            str = new String(Base64.decode("cGVyc2lzdC5zeXMuaWRlbnRpZmllcmlkLnN1cHBvcnRlZA==".getBytes("UTF-8"), 2));
        } catch (Exception unused) {
            str = "";
        }
        f8781b = str;
        f8782c = new C3676a();
    }

    public C3675q3(Context context) {
        try {
            if (JLibrary.context == null) {
                JLibrary.InitEntry(context);
            }
        } catch (Throwable unused) {
        }
        try {
            this.f8783a = new VivoIdentifier();
            this.f8783a.preloadOaid(context);
        } catch (Throwable th) {
            C3578e3.m17305a("OaidVivo", Log.getStackTraceString(th), null);
        }
    }

    /* renamed from: a */
    public static boolean m17136a() {
        return f8782c.m16988b(new Object[0]).booleanValue();
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        VivoIdentifier vivoIdentifier = this.f8783a;
        if (vivoIdentifier == null) {
            return null;
        }
        return vivoIdentifier.getOaid(context);
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        return f8782c.m16988b(new Object[0]).booleanValue();
    }
}
