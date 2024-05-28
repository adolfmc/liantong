package com.p319ss.android.socialbase.downloader.p342db;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.p342db.ISqlCacheLoadCompleteCallbackAidl;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.db.ISqlDownloadCacheAidl */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ISqlDownloadCacheAidl extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.db.ISqlDownloadCacheAidl$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements ISqlDownloadCacheAidl {
        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskCancel(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskCompleted(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskConnected(int i, long j, String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskError(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskIntercept(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskPause(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskPrepare(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskProgress(int i, long j) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo OnDownloadTaskRetry(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void addDownloadChunk(DownloadChunk downloadChunk) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void addSubDownloadChunk(DownloadChunk downloadChunk) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public boolean cacheExist(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void clearData() throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public boolean ensureDownloadCacheSyncSuccess() throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public List<DownloadInfo> getAllDownloadInfo() throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public List<DownloadChunk> getDownloadChunk(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo getDownloadInfo(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void init() throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public boolean isDownloadCacheSyncSuccess() throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo onDownloadTaskStart(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void removeAllDownloadChunk(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public boolean removeDownloadInfo(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public boolean removeDownloadTaskData(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void setInitCallback(ISqlCacheLoadCompleteCallbackAidl iSqlCacheLoadCompleteCallbackAidl) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void syncDownloadInfo(DownloadInfo downloadInfo) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public DownloadInfo updateChunkCount(int i, int i2) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void updateDownloadChunk(int i, int i2, long j) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public boolean updateDownloadInfo(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
        public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException {
        }
    }

    DownloadInfo OnDownloadTaskCancel(int i, long j) throws RemoteException;

    DownloadInfo OnDownloadTaskCompleted(int i, long j) throws RemoteException;

    DownloadInfo OnDownloadTaskConnected(int i, long j, String str, String str2) throws RemoteException;

    DownloadInfo OnDownloadTaskError(int i, long j) throws RemoteException;

    DownloadInfo OnDownloadTaskIntercept(int i) throws RemoteException;

    DownloadInfo OnDownloadTaskPause(int i, long j) throws RemoteException;

    DownloadInfo OnDownloadTaskPrepare(int i) throws RemoteException;

    DownloadInfo OnDownloadTaskProgress(int i, long j) throws RemoteException;

    DownloadInfo OnDownloadTaskRetry(int i) throws RemoteException;

    void addDownloadChunk(DownloadChunk downloadChunk) throws RemoteException;

    void addSubDownloadChunk(DownloadChunk downloadChunk) throws RemoteException;

    boolean cacheExist(int i) throws RemoteException;

    void clearData() throws RemoteException;

    boolean ensureDownloadCacheSyncSuccess() throws RemoteException;

    List<DownloadInfo> getAllDownloadInfo() throws RemoteException;

    List<DownloadChunk> getDownloadChunk(int i) throws RemoteException;

    DownloadInfo getDownloadInfo(int i) throws RemoteException;

    List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException;

    List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException;

    List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException;

    List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException;

    void init() throws RemoteException;

    boolean isDownloadCacheSyncSuccess() throws RemoteException;

    DownloadInfo onDownloadTaskStart(int i) throws RemoteException;

    void removeAllDownloadChunk(int i) throws RemoteException;

    boolean removeDownloadInfo(int i) throws RemoteException;

    boolean removeDownloadTaskData(int i) throws RemoteException;

    void setInitCallback(ISqlCacheLoadCompleteCallbackAidl iSqlCacheLoadCompleteCallbackAidl) throws RemoteException;

    void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException;

    void syncDownloadInfo(DownloadInfo downloadInfo) throws RemoteException;

    void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException;

    DownloadInfo updateChunkCount(int i, int i2) throws RemoteException;

    void updateDownloadChunk(int i, int i2, long j) throws RemoteException;

    boolean updateDownloadInfo(DownloadInfo downloadInfo) throws RemoteException;

    void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException;

    void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException;

    /* renamed from: com.ss.android.socialbase.downloader.db.ISqlDownloadCacheAidl$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements ISqlDownloadCacheAidl {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.db.ISqlDownloadCacheAidl";
        static final int TRANSACTION_OnDownloadTaskCancel = 28;
        static final int TRANSACTION_OnDownloadTaskCompleted = 26;
        static final int TRANSACTION_OnDownloadTaskConnected = 22;
        static final int TRANSACTION_OnDownloadTaskError = 24;
        static final int TRANSACTION_OnDownloadTaskIntercept = 30;
        static final int TRANSACTION_OnDownloadTaskPause = 27;
        static final int TRANSACTION_OnDownloadTaskPrepare = 29;
        static final int TRANSACTION_OnDownloadTaskProgress = 23;
        static final int TRANSACTION_OnDownloadTaskRetry = 25;
        static final int TRANSACTION_addDownloadChunk = 11;
        static final int TRANSACTION_addSubDownloadChunk = 12;
        static final int TRANSACTION_cacheExist = 2;
        static final int TRANSACTION_clearData = 20;
        static final int TRANSACTION_ensureDownloadCacheSyncSuccess = 32;
        static final int TRANSACTION_getAllDownloadInfo = 8;
        static final int TRANSACTION_getDownloadChunk = 9;
        static final int TRANSACTION_getDownloadInfo = 3;
        static final int TRANSACTION_getDownloadInfoList = 4;
        static final int TRANSACTION_getFailedDownloadInfosWithMimeType = 5;
        static final int TRANSACTION_getSuccessedDownloadInfosWithMimeType = 6;
        static final int TRANSACTION_getUnCompletedDownloadInfosWithMimeType = 7;
        static final int TRANSACTION_init = 1;
        static final int TRANSACTION_isDownloadCacheSyncSuccess = 31;
        static final int TRANSACTION_onDownloadTaskStart = 21;
        static final int TRANSACTION_removeAllDownloadChunk = 10;
        static final int TRANSACTION_removeDownloadInfo = 18;
        static final int TRANSACTION_removeDownloadTaskData = 19;
        static final int TRANSACTION_setInitCallback = 36;
        static final int TRANSACTION_syncDownloadChunks = 34;
        static final int TRANSACTION_syncDownloadInfo = 33;
        static final int TRANSACTION_syncDownloadInfoFromOtherCache = 35;
        static final int TRANSACTION_updateChunkCount = 16;
        static final int TRANSACTION_updateDownloadChunk = 13;
        static final int TRANSACTION_updateDownloadInfo = 17;
        static final int TRANSACTION_updateSubDownloadChunk = 14;
        static final int TRANSACTION_updateSubDownloadChunkIndex = 15;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static ISqlDownloadCacheAidl asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof ISqlDownloadCacheAidl)) {
                return (ISqlDownloadCacheAidl) queryLocalInterface;
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
                    init();
                    parcel2.writeNoException();
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean cacheExist = cacheExist(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(cacheExist ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo downloadInfo = getDownloadInfo(parcel.readInt());
                    parcel2.writeNoException();
                    if (downloadInfo != null) {
                        parcel2.writeInt(1);
                        downloadInfo.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadInfo> downloadInfoList = getDownloadInfoList(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(downloadInfoList);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadInfo> failedDownloadInfosWithMimeType = getFailedDownloadInfosWithMimeType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(failedDownloadInfosWithMimeType);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadInfo> successedDownloadInfosWithMimeType = getSuccessedDownloadInfosWithMimeType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(successedDownloadInfosWithMimeType);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadInfo> unCompletedDownloadInfosWithMimeType = getUnCompletedDownloadInfosWithMimeType(parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(unCompletedDownloadInfosWithMimeType);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadInfo> allDownloadInfo = getAllDownloadInfo();
                    parcel2.writeNoException();
                    parcel2.writeTypedList(allDownloadInfo);
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    List<DownloadChunk> downloadChunk = getDownloadChunk(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeTypedList(downloadChunk);
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeAllDownloadChunk(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    addDownloadChunk(parcel.readInt() != 0 ? DownloadChunk.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    addSubDownloadChunk(parcel.readInt() != 0 ? DownloadChunk.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateDownloadChunk(parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateSubDownloadChunk(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateSubDownloadChunkIndex(parcel.readInt(), parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo updateChunkCount = updateChunkCount(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    if (updateChunkCount != null) {
                        parcel2.writeInt(1);
                        updateChunkCount.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean updateDownloadInfo = updateDownloadInfo(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    parcel2.writeInt(updateDownloadInfo ? 1 : 0);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean removeDownloadInfo = removeDownloadInfo(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(removeDownloadInfo ? 1 : 0);
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean removeDownloadTaskData = removeDownloadTaskData(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(removeDownloadTaskData ? 1 : 0);
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearData();
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo onDownloadTaskStart = onDownloadTaskStart(parcel.readInt());
                    parcel2.writeNoException();
                    if (onDownloadTaskStart != null) {
                        parcel2.writeInt(1);
                        onDownloadTaskStart.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskConnected = OnDownloadTaskConnected(parcel.readInt(), parcel.readLong(), parcel.readString(), parcel.readString());
                    parcel2.writeNoException();
                    if (OnDownloadTaskConnected != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskConnected.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskProgress = OnDownloadTaskProgress(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    if (OnDownloadTaskProgress != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskProgress.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskError = OnDownloadTaskError(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    if (OnDownloadTaskError != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskError.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskRetry = OnDownloadTaskRetry(parcel.readInt());
                    parcel2.writeNoException();
                    if (OnDownloadTaskRetry != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskRetry.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskCompleted = OnDownloadTaskCompleted(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    if (OnDownloadTaskCompleted != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskCompleted.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskPause = OnDownloadTaskPause(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    if (OnDownloadTaskPause != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskPause.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskCancel = OnDownloadTaskCancel(parcel.readInt(), parcel.readLong());
                    parcel2.writeNoException();
                    if (OnDownloadTaskCancel != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskCancel.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskPrepare = OnDownloadTaskPrepare(parcel.readInt());
                    parcel2.writeNoException();
                    if (OnDownloadTaskPrepare != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskPrepare.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    DownloadInfo OnDownloadTaskIntercept = OnDownloadTaskIntercept(parcel.readInt());
                    parcel2.writeNoException();
                    if (OnDownloadTaskIntercept != null) {
                        parcel2.writeInt(1);
                        OnDownloadTaskIntercept.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean isDownloadCacheSyncSuccess = isDownloadCacheSyncSuccess();
                    parcel2.writeNoException();
                    parcel2.writeInt(isDownloadCacheSyncSuccess ? 1 : 0);
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean ensureDownloadCacheSyncSuccess = ensureDownloadCacheSyncSuccess();
                    parcel2.writeNoException();
                    parcel2.writeInt(ensureDownloadCacheSyncSuccess ? 1 : 0);
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncDownloadInfo(parcel.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncDownloadChunks(parcel.readInt(), parcel.createTypedArrayList(DownloadChunk.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncDownloadInfoFromOtherCache(parcel.readInt(), parcel.createTypedArrayList(DownloadChunk.CREATOR));
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInitCallback(ISqlCacheLoadCompleteCallbackAidl.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.db.ISqlDownloadCacheAidl$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements ISqlDownloadCacheAidl {
            public static ISqlDownloadCacheAidl sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void init() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().init();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public boolean cacheExist(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().cacheExist(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo getDownloadInfo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadInfo(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadInfoList(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFailedDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSuccessedDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnCompletedDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public List<DownloadInfo> getAllDownloadInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllDownloadInfo();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public List<DownloadChunk> getDownloadChunk(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadChunk(i);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadChunk.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void removeAllDownloadChunk(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAllDownloadChunk(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void addDownloadChunk(DownloadChunk downloadChunk) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadChunk != null) {
                        obtain.writeInt(1);
                        downloadChunk.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDownloadChunk(downloadChunk);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void addSubDownloadChunk(DownloadChunk downloadChunk) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (downloadChunk != null) {
                        obtain.writeInt(1);
                        downloadChunk.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addSubDownloadChunk(downloadChunk);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void updateDownloadChunk(int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateDownloadChunk(i, i2, j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateSubDownloadChunk(i, i2, i3, j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateSubDownloadChunkIndex(i, i2, i3, i4);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo updateChunkCount(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateChunkCount(i, i2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public boolean updateDownloadInfo(DownloadInfo downloadInfo) throws RemoteException {
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
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateDownloadInfo(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public boolean removeDownloadInfo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDownloadInfo(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public boolean removeDownloadTaskData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDownloadTaskData(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void clearData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearData();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo onDownloadTaskStart(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().onDownloadTaskStart(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskConnected(int i, long j, String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskConnected(i, j, str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskProgress(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskProgress(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskError(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskError(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskRetry(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskRetry(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskCompleted(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskCompleted(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskPause(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskPause(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskCancel(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskCancel(i, j);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskPrepare(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(29, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskPrepare(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public DownloadInfo OnDownloadTaskIntercept(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(30, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().OnDownloadTaskIntercept(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public boolean isDownloadCacheSyncSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDownloadCacheSyncSuccess();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public boolean ensureDownloadCacheSyncSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().ensureDownloadCacheSyncSuccess();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void syncDownloadInfo(DownloadInfo downloadInfo) throws RemoteException {
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
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncDownloadInfo(downloadInfo);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncDownloadChunks(i, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncDownloadInfoFromOtherCache(i, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
            public void setInitCallback(ISqlCacheLoadCompleteCallbackAidl iSqlCacheLoadCompleteCallbackAidl) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(iSqlCacheLoadCompleteCallbackAidl != null ? iSqlCacheLoadCompleteCallbackAidl.asBinder() : null);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInitCallback(iSqlCacheLoadCompleteCallbackAidl);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(ISqlDownloadCacheAidl iSqlDownloadCacheAidl) {
            if (Proxy.sDefaultImpl != null || iSqlDownloadCacheAidl == null) {
                return false;
            }
            Proxy.sDefaultImpl = iSqlDownloadCacheAidl;
            return true;
        }

        public static ISqlDownloadCacheAidl getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
