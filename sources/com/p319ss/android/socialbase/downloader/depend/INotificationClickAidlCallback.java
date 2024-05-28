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
/* renamed from: com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface INotificationClickAidlCallback extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements INotificationClickAidlCallback {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
        public boolean onClickWhenInstalled(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
        public boolean onClickWhenSuccess(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
        public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }
    }

    boolean onClickWhenInstalled(DownloadInfo downloadInfo) throws RemoteException;

    boolean onClickWhenSuccess(DownloadInfo downloadInfo) throws RemoteException;

    boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements INotificationClickAidlCallback {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback";
        static final int TRANSACTION_onClickWhenInstalled = 3;
        static final int TRANSACTION_onClickWhenSuccess = 2;
        static final int TRANSACTION_onClickWhenUnSuccess = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static INotificationClickAidlCallback asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof INotificationClickAidlCallback)) {
                return (INotificationClickAidlCallback) queryLocalInterface;
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
                    boolean onClickWhenUnSuccess = onClickWhenUnSuccess(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(onClickWhenUnSuccess ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean onClickWhenSuccess = onClickWhenSuccess(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(onClickWhenSuccess ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean onClickWhenInstalled = onClickWhenInstalled(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(onClickWhenInstalled ? 1 : 0);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.INotificationClickAidlCallback$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements INotificationClickAidlCallback {
            public static INotificationClickAidlCallback sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
            public boolean onClickWhenUnSuccess(DownloadInfo downloadInfo) throws RemoteException {
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
                        return Stub.getDefaultImpl().onClickWhenUnSuccess(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
            public boolean onClickWhenSuccess(DownloadInfo downloadInfo) throws RemoteException {
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
                        return Stub.getDefaultImpl().onClickWhenSuccess(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback
            public boolean onClickWhenInstalled(DownloadInfo downloadInfo) throws RemoteException {
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
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onClickWhenInstalled(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(INotificationClickAidlCallback iNotificationClickAidlCallback) {
            if (Proxy.sDefaultImpl != null || iNotificationClickAidlCallback == null) {
                return false;
            }
            Proxy.sDefaultImpl = iNotificationClickAidlCallback;
            return true;
        }

        public static INotificationClickAidlCallback getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
