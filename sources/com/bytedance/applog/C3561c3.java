package com.bytedance.applog;

import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3548b;
import com.bytedance.applog.InterfaceC3645n3;

/* renamed from: com.bytedance.applog.c3 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3561c3 extends AbstractC3543a3<InterfaceC3548b> {

    /* renamed from: com.bytedance.applog.c3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3562a implements C3713v3.InterfaceC3715b<InterfaceC3548b, String> {
        public C3562a(C3561c3 c3561c3) {
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public InterfaceC3548b mo16998a(IBinder iBinder) {
            return InterfaceC3548b.AbstractBinderC3549a.m17333a(iBinder);
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public String mo16997a(InterfaceC3548b interfaceC3548b) {
            InterfaceC3548b interfaceC3548b2 = interfaceC3548b;
            if (interfaceC3548b2 == null) {
                return null;
            }
            InterfaceC3548b.AbstractBinderC3549a.C3550a c3550a = (InterfaceC3548b.AbstractBinderC3549a.C3550a) interfaceC3548b2;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.bun.lib.MsaIdInterface");
                c3550a.f8367a.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public C3561c3() {
        super("com.mdid.msa");
    }

    @Override // com.bytedance.applog.AbstractC3543a3, com.bytedance.applog.InterfaceC3645n3
    /* renamed from: a */
    public InterfaceC3645n3.C3646a mo17057a(Context context) {
        String packageName = context.getPackageName();
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaKlService");
        intent.setAction("com.bun.msa.action.start.service");
        intent.putExtra("com.bun.msa.param.pkgname", packageName);
        try {
            intent.putExtra("com.bun.msa.param.runinset", true);
            context.startService(intent);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return super.mo17057a(context);
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: a */
    public C3713v3.InterfaceC3715b<InterfaceC3548b, String> mo17000a() {
        return new C3562a(this);
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: c */
    public Intent mo16999c(Context context) {
        Intent intent = new Intent();
        intent.setClassName("com.mdid.msa", "com.mdid.msa.service.MsaIdService");
        intent.setAction("com.bun.msa.action.bindto.service");
        intent.putExtra("com.bun.msa.param.pkgname", context.getPackageName());
        return intent;
    }
}
