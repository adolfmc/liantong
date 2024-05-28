package com.p319ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadAidlDepend extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadAidlDepend {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlDepend
        public void monitorLogSend(DownloadInfo downloadInfo, BaseException baseException, int i) throws RemoteException {
        }
    }

    void monitorLogSend(DownloadInfo downloadInfo, BaseException baseException, int i) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadAidlDepend {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend";
        static final int TRANSACTION_monitorLogSend = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadAidlDepend asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadAidlDepend)) {
                return (IDownloadAidlDepend) queryLocalInterface;
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
            monitorLogSend(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null, parcel.readInt() != 0 ? BaseException.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
            parcel2.writeNoException();
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlDepend$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadAidlDepend {
            public static IDownloadAidlDepend sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlDepend
            public void monitorLogSend(DownloadInfo downloadInfo, BaseException baseException, int i) throws RemoteException {
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
                    if (baseException != null) {
                        obtain.writeInt(1);
                        baseException.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().monitorLogSend(downloadInfo, baseException, i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadAidlDepend iDownloadAidlDepend) {
            if (Proxy.sDefaultImpl != null || iDownloadAidlDepend == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadAidlDepend;
            return true;
        }

        public static IDownloadAidlDepend getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
