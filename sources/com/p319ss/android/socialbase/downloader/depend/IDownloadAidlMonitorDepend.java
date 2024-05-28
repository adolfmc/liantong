package com.p319ss.android.socialbase.downloader.depend;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadAidlMonitorDepend extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadAidlMonitorDepend {
        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
        public int[] getAdditionalMonitorStatus() throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
        public String getEventPage() throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
        public void monitorLogSend(String str) throws RemoteException {
        }
    }

    int[] getAdditionalMonitorStatus() throws RemoteException;

    String getEventPage() throws RemoteException;

    void monitorLogSend(String str) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadAidlMonitorDepend {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend";
        static final int TRANSACTION_getAdditionalMonitorStatus = 3;
        static final int TRANSACTION_getEventPage = 2;
        static final int TRANSACTION_monitorLogSend = 1;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadAidlMonitorDepend asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadAidlMonitorDepend)) {
                return (IDownloadAidlMonitorDepend) queryLocalInterface;
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
                    monitorLogSend(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    String eventPage = getEventPage();
                    parcel2.writeNoException();
                    parcel2.writeString(eventPage);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    int[] additionalMonitorStatus = getAdditionalMonitorStatus();
                    parcel2.writeNoException();
                    parcel2.writeIntArray(additionalMonitorStatus);
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadAidlMonitorDepend {
            public static IDownloadAidlMonitorDepend sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
            public void monitorLogSend(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().monitorLogSend(str);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
            public String getEventPage() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getEventPage();
                    }
                    obtain2.readException();
                    return obtain2.readString();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.depend.IDownloadAidlMonitorDepend
            public int[] getAdditionalMonitorStatus() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAdditionalMonitorStatus();
                    }
                    obtain2.readException();
                    return obtain2.createIntArray();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadAidlMonitorDepend iDownloadAidlMonitorDepend) {
            if (Proxy.sDefaultImpl != null || iDownloadAidlMonitorDepend == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadAidlMonitorDepend;
            return true;
        }

        public static IDownloadAidlMonitorDepend getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
