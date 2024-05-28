package com.p319ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadDiskSpaceAidlHandler extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadDiskSpaceAidlHandler {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler
        public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceAidlCallback iDownloadDiskSpaceAidlCallback) throws RemoteException {
            return false;
        }
    }

    boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceAidlCallback iDownloadDiskSpaceAidlCallback) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadDiskSpaceAidlHandler {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler";
        static final int TRANSACTION_cleanUpDisk = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadDiskSpaceAidlHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadDiskSpaceAidlHandler)) {
                return (IDownloadDiskSpaceAidlHandler) queryLocalInterface;
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
            boolean cleanUpDisk = cleanUpDisk(parcel.readLong(), parcel.readLong(), IDownloadDiskSpaceAidlCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(cleanUpDisk ? 1 : 0);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadDiskSpaceAidlHandler {
            public static IDownloadDiskSpaceAidlHandler sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadDiskSpaceAidlHandler
            public boolean cleanUpDisk(long j, long j2, IDownloadDiskSpaceAidlCallback iDownloadDiskSpaceAidlCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeLong(j);
                    obtain.writeLong(j2);
                    obtain.writeStrongBinder(iDownloadDiskSpaceAidlCallback != null ? iDownloadDiskSpaceAidlCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cleanUpDisk(j, j2, iDownloadDiskSpaceAidlCallback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadDiskSpaceAidlHandler iDownloadDiskSpaceAidlHandler) {
            if (Proxy.sDefaultImpl != null || iDownloadDiskSpaceAidlHandler == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadDiskSpaceAidlHandler;
            return true;
        }

        public static IDownloadDiskSpaceAidlHandler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
