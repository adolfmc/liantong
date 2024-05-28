package com.bytedance.applog;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import com.bytedance.applog.C3713v3;
import com.bytedance.applog.InterfaceC3750z3;

/* renamed from: com.bytedance.applog.y2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3738y2 extends AbstractC3543a3<InterfaceC3750z3> {

    /* renamed from: com.bytedance.applog.y2$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C3739a implements C3713v3.InterfaceC3715b<InterfaceC3750z3, String> {
        public C3739a(C3738y2 c3738y2) {
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public InterfaceC3750z3 mo16998a(IBinder iBinder) {
            return InterfaceC3750z3.AbstractBinderC3751a.m16987a(iBinder);
        }

        @Override // com.bytedance.applog.C3713v3.InterfaceC3715b
        /* renamed from: a */
        public String mo16997a(InterfaceC3750z3 interfaceC3750z3) {
            InterfaceC3750z3 interfaceC3750z32 = interfaceC3750z3;
            if (interfaceC3750z32 == null) {
                return null;
            }
            InterfaceC3750z3.AbstractBinderC3751a.C3752a c3752a = (InterfaceC3750z3.AbstractBinderC3751a.C3752a) interfaceC3750z32;
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.asus.msa.SupplementaryDID.IDidAidlInterface");
                c3752a.f9000a.transact(3, obtain, obtain2, 0);
                obtain2.readException();
                return obtain2.readString();
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }

    public C3738y2() {
        super("com.asus.msa.SupplementaryDID");
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: a */
    public C3713v3.InterfaceC3715b<InterfaceC3750z3, String> mo17000a() {
        return new C3739a(this);
    }

    @Override // com.bytedance.applog.AbstractC3543a3
    /* renamed from: c */
    public Intent mo16999c(Context context) {
        Intent intent = new Intent();
        intent.setAction("com.asus.msa.action.ACCESS_DID");
        intent.setComponent(new ComponentName("com.asus.msa.SupplementaryDID", "com.asus.msa.SupplementaryDID.SupplementaryDIDService"));
        return intent;
    }
}
