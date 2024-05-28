package com.gmrz.android.uaf.asm.service;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IUafAsmService extends IInterface {
    String sendRequest(String str) throws RemoteException;

    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IUafAsmService {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, "com.gmrz.android.uaf.asm.service.IUafAsmService");
        }

        public static IUafAsmService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface("com.gmrz.android.uaf.asm.service.IUafAsmService");
            if (queryLocalInterface != null && (queryLocalInterface instanceof IUafAsmService)) {
                return (IUafAsmService) queryLocalInterface;
            }
            return new C4392a(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString("com.gmrz.android.uaf.asm.service.IUafAsmService");
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface("com.gmrz.android.uaf.asm.service.IUafAsmService");
            String sendRequest = sendRequest(parcel.readString());
            parcel2.writeNoException();
            parcel2.writeString(sendRequest);
            return true;
        }

        /* renamed from: com.gmrz.android.uaf.asm.service.IUafAsmService$Stub$a */
        /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
        static class C4392a implements IUafAsmService {

            /* renamed from: a */
            private final IBinder f10186a;

            C4392a(IBinder iBinder) {
                this.f10186a = iBinder;
            }

            @Override // android.os.IInterface
            public final IBinder asBinder() {
                return this.f10186a;
            }

            @Override // com.gmrz.android.uaf.asm.service.IUafAsmService
            public final String sendRequest(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken("com.gmrz.android.uaf.asm.service.IUafAsmService");
                    obtain.writeString(str);
                    this.f10186a.transact(1, obtain, obtain2, 0);
                    obtain2.readException();
                    String readString = obtain2.readString();
                    obtain2.recycle();
                    obtain.recycle();
                    return readString;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }
    }
}
