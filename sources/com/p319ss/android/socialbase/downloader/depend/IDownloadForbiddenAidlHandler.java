package com.p319ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlCallback;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadForbiddenAidlHandler extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadForbiddenAidlHandler {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler
        public boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException {
            return false;
        }
    }

    boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadForbiddenAidlHandler {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler";
        static final int TRANSACTION_onForbidden = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadForbiddenAidlHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadForbiddenAidlHandler)) {
                return (IDownloadForbiddenAidlHandler) queryLocalInterface;
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
            boolean onForbidden = onForbidden(IDownloadForbiddenAidlCallback.Stub.asInterface(parcel.readStrongBinder()));
            parcel2.writeNoException();
            parcel2.writeInt(onForbidden ? 1 : 0);
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadForbiddenAidlHandler {
            public static IDownloadForbiddenAidlHandler sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadForbiddenAidlHandler
            public boolean onForbidden(IDownloadForbiddenAidlCallback iDownloadForbiddenAidlCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iDownloadForbiddenAidlCallback != null ? iDownloadForbiddenAidlCallback.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onForbidden(iDownloadForbiddenAidlCallback);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadForbiddenAidlHandler iDownloadForbiddenAidlHandler) {
            if (Proxy.sDefaultImpl != null || iDownloadForbiddenAidlHandler == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadForbiddenAidlHandler;
            return true;
        }

        public static IDownloadForbiddenAidlHandler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
