package com.p319ss.android.socialbase.downloader.depend;

import android.net.Uri;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadAidlFileProvider extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadAidlFileProvider {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider
        public Uri getUriForFile(String str, String str2) throws RemoteException {
            return null;
        }
    }

    Uri getUriForFile(String str, String str2) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadAidlFileProvider {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider";
        static final int TRANSACTION_getUriForFile = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadAidlFileProvider asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadAidlFileProvider)) {
                return (IDownloadAidlFileProvider) queryLocalInterface;
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
            Uri uriForFile = getUriForFile(parcel.readString(), parcel.readString());
            parcel2.writeNoException();
            if (uriForFile != null) {
                parcel2.writeInt(1);
                uriForFile.writeToParcel(parcel2, 1);
            } else {
                parcel2.writeInt(0);
            }
            return true;
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadAidlFileProvider {
            public static IDownloadAidlFileProvider sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider
            public Uri getUriForFile(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUriForFile(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? (Uri) Uri.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadAidlFileProvider iDownloadAidlFileProvider) {
            if (Proxy.sDefaultImpl != null || iDownloadAidlFileProvider == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadAidlFileProvider;
            return true;
        }

        public static IDownloadAidlFileProvider getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
