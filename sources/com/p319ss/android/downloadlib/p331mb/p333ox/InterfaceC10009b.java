package com.p319ss.android.downloadlib.p331mb.p333ox;

import android.annotation.SuppressLint;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.p331mb.p333ox.InterfaceC10012hj;
import com.p319ss.android.socialbase.appdownloader.p340u.C10150b;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.mb.ox.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface InterfaceC10009b extends IInterface {
    /* renamed from: mb */
    void mo7178mb(C10018ox c10018ox, InterfaceC10012hj interfaceC10012hj) throws RemoteException;

    /* renamed from: com.ss.android.downloadlib.mb.ox.b$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static abstract class AbstractBinderC10010mb extends Binder implements InterfaceC10009b {

        /* renamed from: mb */
        private static String f19293mb = "";

        /* renamed from: mb */
        public static InterfaceC10009b m7180mb(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(f19293mb);
            if (queryLocalInterface == null || !(queryLocalInterface instanceof InterfaceC10009b)) {
                return new C10011mb(iBinder);
            }
            return (InterfaceC10009b) queryLocalInterface;
        }

        @Override // android.os.Binder
        @SuppressLint({"WrongConstant"})
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(f19293mb);
                return true;
            }
            if (i == 1) {
                parcel.enforceInterface(f19293mb);
                mo7178mb(parcel.readInt() != 0 ? C10018ox.CREATOR.createFromParcel(parcel) : null, InterfaceC10012hj.AbstractBinderC10013mb.m7176mb(parcel.readStrongBinder()));
                parcel2.writeNoException();
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }

        /* renamed from: com.ss.android.downloadlib.mb.ox.b$mb$mb */
        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        static class C10011mb implements InterfaceC10009b {

            /* renamed from: mb */
            private IBinder f19294mb;

            C10011mb(IBinder iBinder) {
                if (TextUtils.isEmpty(AbstractBinderC10010mb.f19293mb)) {
                    JSONObject m7364lz = C9940x.m7364lz();
                    String unused = AbstractBinderC10010mb.f19293mb = C10150b.m6594mb(m7364lz.optString("r"), m7364lz.optString("s"));
                }
                this.f19294mb = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.f19294mb;
            }

            @Override // com.p319ss.android.downloadlib.p331mb.p333ox.InterfaceC10009b
            /* renamed from: mb */
            public void mo7178mb(C10018ox c10018ox, InterfaceC10012hj interfaceC10012hj) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(AbstractBinderC10010mb.f19293mb);
                    if (c10018ox != null) {
                        obtain.writeInt(1);
                        c10018ox.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeStrongBinder(interfaceC10012hj != null ? interfaceC10012hj.asBinder() : null);
                    this.f19294mb.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
