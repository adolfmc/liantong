package com.bytedance.applog;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3645n3;

/* renamed from: com.bytedance.applog.a3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC3543a3<SERVICE> implements InterfaceC3645n3 {

    /* renamed from: a */
    public final String f8363a;

    /* renamed from: b */
    public AbstractC3749z2<Boolean> f8364b = new C3544a();

    /* renamed from: com.bytedance.applog.a3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3544a extends AbstractC3749z2<Boolean> {
        public C3544a() {
        }

        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public Boolean mo16989a(Object[] objArr) {
            return Boolean.valueOf(C3710v0.m17102a((Context) objArr[0], AbstractC3543a3.this.f8363a));
        }
    }

    public AbstractC3543a3(String str) {
        this.f8363a = str;
    }

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        String str = (String) new C3713v3(context, mo16999c(context), mo17000a()).m17071a();
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        InterfaceC3645n3.C3646a c3646a = new InterfaceC3645n3.C3646a();
        c3646a.f8617a = str;
        return c3646a;
    }

    /* renamed from: a */
    public abstract C3713v3.InterfaceC3715b<SERVICE, String> mo17000a();

    @Override // com.bytedance.applog.InterfaceC3645n3
    /* renamed from: b */
    public boolean mo17056b(Context context) {
        if (context == null) {
            return false;
        }
        return this.f8364b.m16988b(context).booleanValue();
    }

    /* renamed from: c */
    public abstract Intent mo16999c(Context context);
}
