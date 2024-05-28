package com.p319ss.android.downloadlib.p331mb.p333ox;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.mb.ox.hj */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface InterfaceC10012hj extends IInterface {
    /* renamed from: mb */
    void mo7174mb(C10018ox c10018ox) throws RemoteException;

    /* renamed from: com.ss.android.downloadlib.mb.ox.hj$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractBinderC10013mb extends Binder implements InterfaceC10012hj {

        /* renamed from: mb */
        private static String f19295mb = "";

        /* renamed from: mb */
        public static InterfaceC10012hj m7176mb(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f19295mb);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC10012hj)) {
                return new C10014mb(iBinder);
            }
            return (InterfaceC10012hj) queryLocalInterface;
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1) {
                parcel.enforceInterface(f19295mb);
                mo7174mb(parcel.readInt() != 0 ? C10018ox.CREATOR.createFromParcel(parcel) : null);
                parcel2.writeNoException();
                return true;
            } else if (i != 1598968902) {
                return super.onTransact(i, parcel, parcel2, i2);
            } else {
                parcel2.writeString(f19295mb);
                return true;
            }
        }

        /* renamed from: com.ss.android.downloadlib.mb.ox.hj$mb$mb */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        static class C10014mb implements InterfaceC10012hj {

            /* renamed from: mb */
            private IBinder f19296mb;

            C10014mb(IBinder iBinder) {
                if (TextUtils.isEmpty(AbstractBinderC10013mb.f19295mb)) {
                    JSONObject m7364lz = C9940x.m7364lz();
                    String unused = AbstractBinderC10013mb.f19295mb = C10150b.m6594mb(m7364lz.optString("t"), m7364lz.optString("s"));
                }
                this.f19296mb = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f19296mb;
            }

            @Override // com.p319ss.android.downloadlib.p331mb.p333ox.InterfaceC10012hj
            /* renamed from: mb */
            public void mo7174mb(C10018ox c10018ox) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC10013mb.f19295mb);
                    if (c10018ox != null) {
                        obtain.writeInt(1);
                        c10018ox.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    this.f19296mb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
