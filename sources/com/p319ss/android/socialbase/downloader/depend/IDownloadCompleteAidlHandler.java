package com.p319ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadCompleteAidlHandler extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadCompleteAidlHandler {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler
        public void handle(DownloadInfo downloadInfo) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler
        public boolean needHandle(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }
    }

    void handle(DownloadInfo downloadInfo) throws RemoteException;

    boolean needHandle(DownloadInfo downloadInfo) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadCompleteAidlHandler {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler";
        static final int TRANSACTION_handle = 1;
        static final int TRANSACTION_needHandle = 2;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadCompleteAidlHandler asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadCompleteAidlHandler)) {
                return (IDownloadCompleteAidlHandler) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    handle(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean needHandle = needHandle(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(needHandle ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadCompleteAidlHandler {
            public static IDownloadCompleteAidlHandler sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler
            public void handle(DownloadInfo downloadInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadInfo != null) {
                        obtain.writeInt(1);
                        downloadInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().handle(downloadInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadCompleteAidlHandler
            public boolean needHandle(DownloadInfo downloadInfo) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadInfo != null) {
                        obtain.writeInt(1);
                        downloadInfo.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().needHandle(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadCompleteAidlHandler iDownloadCompleteAidlHandler) {
            if (Proxy.sDefaultImpl != null || iDownloadCompleteAidlHandler == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadCompleteAidlHandler;
            return true;
        }

        public static IDownloadCompleteAidlHandler getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
