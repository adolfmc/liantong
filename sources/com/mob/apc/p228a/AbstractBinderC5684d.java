package com.mob.apc.p228a;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* renamed from: com.mob.apc.a.d */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractBinderC5684d extends Binder implements IInterface {
    /* renamed from: a */
    public abstract C5686e mo12840a(C5686e c5686e) throws RemoteException;

    @Override // android.os.IInterface
    public IBinder asBinder() {
        return this;
    }

    public AbstractBinderC5684d() {
        attachInterface(this, "com.mob.apc.impl.IAidlInterface");
    }

    /* renamed from: a */
    public static AbstractBinderC5684d m12841a(IBinder iBinder) {
        if (iBinder == null) {
            return null;
        }
        IInterface queryLocalInterface = iBinder.queryLocalInterface("com.mob.apc.impl.IAidlInterface");
        if (queryLocalInterface != null && (queryLocalInterface instanceof AbstractBinderC5684d)) {
            return (AbstractBinderC5684d) queryLocalInterface;
        }
        return new BinderC5685a(iBinder);
    }

    @Override // android.os.Binder
    public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
        if (i != 1) {
            if (i == 1598968902) {
                parcel2.writeString("com.mob.apc.impl.IAidlInterface");
                return true;
            }
            return super.onTransact(i, parcel, parcel2, i2);
        }
        parcel.enforceInterface("com.mob.apc.impl.IAidlInterface");
        C5686e mo12840a = mo12840a(C5686e.m12839a(parcel));
        parcel2.writeNoException();
        if (mo12840a != null) {
            mo12840a.m12838a(parcel2, 1);
        } else {
            parcel2.writeInt(0);
        }
        return true;
    }

    /* renamed from: com.mob.apc.a.d$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    static class BinderC5685a extends AbstractBinderC5684d {

        /* renamed from: a */
        private IBinder f14026a;

        @Override // android.os.Binder, android.os.IBinder
        public String getInterfaceDescriptor() {
            return "com.mob.apc.impl.IAidlInterface";
        }

        BinderC5685a(IBinder iBinder) {
            this.f14026a = iBinder;
        }

        @Override // com.mob.apc.p228a.AbstractBinderC5684d, android.os.IInterface
        public IBinder asBinder() {
            return this.f14026a;
        }

        @Override // com.mob.apc.p228a.AbstractBinderC5684d
        /* renamed from: a */
        public C5686e mo12840a(C5686e c5686e) throws RemoteException {
            Parcel obtain = Parcel.obtain();
            Parcel obtain2 = Parcel.obtain();
            try {
                obtain.writeInterfaceToken("com.mob.apc.impl.IAidlInterface");
                if (c5686e != null) {
                    c5686e.m12838a(obtain, 0);
                } else {
                    obtain.writeInt(0);
                }
                this.f14026a.transact(1, obtain, obtain2, 0);
                obtain2.readException();
                return C5686e.m12839a(obtain2);
            } finally {
                obtain2.recycle();
                obtain.recycle();
            }
        }
    }
}
