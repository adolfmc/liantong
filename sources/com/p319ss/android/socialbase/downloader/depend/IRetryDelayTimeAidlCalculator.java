package com.p319ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IRetryDelayTimeAidlCalculator extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IRetryDelayTimeAidlCalculator {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator
        public long calculateRetryDelayTime(int i, int i2) throws RemoteException {
            return 0L;
        }
    }

    long calculateRetryDelayTime(int i, int i2) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IRetryDelayTimeAidlCalculator {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator";
        static final int TRANSACTION_calculateRetryDelayTime = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IRetryDelayTimeAidlCalculator asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IRetryDelayTimeAidlCalculator)) {
                return (IRetryDelayTimeAidlCalculator) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i != 1) {
                if (i == 1598968902) {
                    parcel2.writeString(DESCRIPTOR);
                    return true;
                }
                return super.onTransact(i, parcel, parcel2, i2);
            }
            parcel.enforceInterface(DESCRIPTOR);
            long calculateRetryDelayTime = calculateRetryDelayTime(parcel.readInt(), parcel.readInt());
            parcel2.writeNoException();
            parcel2.writeLong(calculateRetryDelayTime);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IRetryDelayTimeAidlCalculator {
            public static IRetryDelayTimeAidlCalculator sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.IRetryDelayTimeAidlCalculator
            public long calculateRetryDelayTime(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().calculateRetryDelayTime(i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IRetryDelayTimeAidlCalculator iRetryDelayTimeAidlCalculator) {
            if (Proxy.sDefaultImpl != null || iRetryDelayTimeAidlCalculator == null) {
                return false;
            }
            Proxy.sDefaultImpl = iRetryDelayTimeAidlCalculator;
            return true;
        }

        public static IRetryDelayTimeAidlCalculator getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
