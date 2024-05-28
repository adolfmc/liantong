package com.p319ss.android.socialbase.downloader.downloader;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.p319ss.android.socialbase.downloader.depend.IDownloadAidlFileProvider;
import com.p319ss.android.socialbase.downloader.depend.IDownloadAidlListener;
import com.p319ss.android.socialbase.downloader.depend.IDownloadNotificationEventAidlListener;
import com.p319ss.android.socialbase.downloader.depend.INotificationClickAidlCallback;
import com.p319ss.android.socialbase.downloader.depend.ProcessAidlCallback;
import com.p319ss.android.socialbase.downloader.model.DownloadAidlTask;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IDownloadAidlService extends IInterface {

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService$Default */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Default implements IDownloadAidlService {
        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void addDownloadChunk(DownloadChunk downloadChunk) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void addDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void addDownloadListener1(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z, boolean z2) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void addProcessCallback(ProcessAidlCallback processAidlCallback) throws RemoteException {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean canResume(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void cancel(int i, boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void clearData() throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void clearDownloadData(int i, boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void dispatchProcessCallback(int i, int i2) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void forceDownloadIngoreRecommendSize(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadInfo> getAllDownloadInfo() throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public long getCurBytes(int i) throws RemoteException {
            return 0L;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadChunk> getDownloadChunk(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public IDownloadAidlFileProvider getDownloadFileUriProvider(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public int getDownloadId(String str, String str2) throws RemoteException {
            return 0;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public DownloadInfo getDownloadInfo(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public DownloadInfo getDownloadInfoByUrlAndPath(String str, String str2) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public IDownloadNotificationEventAidlListener getDownloadNotificationEventListener(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public int getDownloadWithIndependentProcessStatus(int i) throws RemoteException {
            return 0;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public INotificationClickAidlCallback getNotificationClickCallback(int i) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public int getStatus(int i) throws RemoteException {
            return 0;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException {
            return null;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean isDownloadCacheSyncSuccess() throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean isDownloading(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean isHttpServiceInit() throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean isServiceForeground() throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void pause(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void pauseAll() throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void removeAllDownloadChunk(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean removeDownloadInfo(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void removeDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean removeDownloadTaskData(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void resetDownloadData(int i, boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void restart(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void restartAllFailedDownloadTasks(List<String> list) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void resume(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean retryDelayStart(int i) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void setDownloadNotificationEventListener(int i, IDownloadNotificationEventAidlListener iDownloadNotificationEventAidlListener) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void setDownloadWithIndependentProcessStatus(int i, boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void setLogLevel(int i) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void setThrottleNetSpeed(int i, long j) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void startForeground(int i, Notification notification) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void stopForeground(boolean z) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void tryDownload(DownloadAidlTask downloadAidlTask) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void updateDownloadChunk(int i, int i2, long j) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public boolean updateDownloadInfo(DownloadInfo downloadInfo) throws RemoteException {
            return false;
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException {
        }

        @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
        public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException {
        }
    }

    void addDownloadChunk(DownloadChunk downloadChunk) throws RemoteException;

    void addDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException;

    void addDownloadListener1(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z, boolean z2) throws RemoteException;

    void addProcessCallback(ProcessAidlCallback processAidlCallback) throws RemoteException;

    boolean canResume(int i) throws RemoteException;

    void cancel(int i, boolean z) throws RemoteException;

    void clearData() throws RemoteException;

    void clearDownloadData(int i, boolean z) throws RemoteException;

    void dispatchProcessCallback(int i, int i2) throws RemoteException;

    void forceDownloadIngoreRecommendSize(int i) throws RemoteException;

    List<DownloadInfo> getAllDownloadInfo() throws RemoteException;

    long getCurBytes(int i) throws RemoteException;

    List<DownloadChunk> getDownloadChunk(int i) throws RemoteException;

    IDownloadAidlFileProvider getDownloadFileUriProvider(int i) throws RemoteException;

    int getDownloadId(String str, String str2) throws RemoteException;

    DownloadInfo getDownloadInfo(int i) throws RemoteException;

    DownloadInfo getDownloadInfoByUrlAndPath(String str, String str2) throws RemoteException;

    List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException;

    IDownloadNotificationEventAidlListener getDownloadNotificationEventListener(int i) throws RemoteException;

    int getDownloadWithIndependentProcessStatus(int i) throws RemoteException;

    List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) throws RemoteException;

    List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException;

    INotificationClickAidlCallback getNotificationClickCallback(int i) throws RemoteException;

    int getStatus(int i) throws RemoteException;

    List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException;

    List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException;

    boolean isDownloadCacheSyncSuccess() throws RemoteException;

    boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) throws RemoteException;

    boolean isDownloading(int i) throws RemoteException;

    boolean isHttpServiceInit() throws RemoteException;

    boolean isServiceForeground() throws RemoteException;

    void pause(int i) throws RemoteException;

    void pauseAll() throws RemoteException;

    void removeAllDownloadChunk(int i) throws RemoteException;

    boolean removeDownloadInfo(int i) throws RemoteException;

    void removeDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException;

    boolean removeDownloadTaskData(int i) throws RemoteException;

    void resetDownloadData(int i, boolean z) throws RemoteException;

    void restart(int i) throws RemoteException;

    void restartAllFailedDownloadTasks(List<String> list) throws RemoteException;

    void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) throws RemoteException;

    void resume(int i) throws RemoteException;

    boolean retryDelayStart(int i) throws RemoteException;

    void setDownloadNotificationEventListener(int i, IDownloadNotificationEventAidlListener iDownloadNotificationEventAidlListener) throws RemoteException;

    void setDownloadWithIndependentProcessStatus(int i, boolean z) throws RemoteException;

    void setLogLevel(int i) throws RemoteException;

    void setThrottleNetSpeed(int i, long j) throws RemoteException;

    void startForeground(int i, Notification notification) throws RemoteException;

    void stopForeground(boolean z) throws RemoteException;

    void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException;

    void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException;

    void tryDownload(DownloadAidlTask downloadAidlTask) throws RemoteException;

    void updateDownloadChunk(int i, int i2, long j) throws RemoteException;

    boolean updateDownloadInfo(DownloadInfo downloadInfo) throws RemoteException;

    void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException;

    void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException;

    /*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
        java.lang.NullPointerException
        */
    /* renamed from: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService$Stub */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static abstract class Stub extends Binder implements IDownloadAidlService {
        private static final String DESCRIPTOR = "com.ss.android.socialbase.downloader.downloader.IDownloadAidlService";
        static final int TRANSACTION_addDownloadChunk = 38;
        static final int TRANSACTION_addDownloadListener = 26;
        static final int TRANSACTION_addDownloadListener1 = 27;
        static final int TRANSACTION_addProcessCallback = 49;
        static final int TRANSACTION_canResume = 4;
        static final int TRANSACTION_cancel = 3;
        static final int TRANSACTION_clearData = 46;
        static final int TRANSACTION_clearDownloadData = 22;
        static final int TRANSACTION_dispatchProcessCallback = 50;
        static final int TRANSACTION_forceDownloadIngoreRecommendSize = 24;
        static final int TRANSACTION_getAllDownloadInfo = 19;
        static final int TRANSACTION_getCurBytes = 8;
        static final int TRANSACTION_getDownloadChunk = 13;
        static final int TRANSACTION_getDownloadFileUriProvider = 54;
        static final int TRANSACTION_getDownloadId = 14;
        static final int TRANSACTION_getDownloadInfo = 11;
        static final int TRANSACTION_getDownloadInfoByUrlAndPath = 15;
        static final int TRANSACTION_getDownloadInfoList = 12;
        static final int TRANSACTION_getDownloadNotificationEventListener = 51;
        static final int TRANSACTION_getDownloadWithIndependentProcessStatus = 37;
        static final int TRANSACTION_getDownloadingDownloadInfosWithMimeType = 18;
        static final int TRANSACTION_getFailedDownloadInfosWithMimeType = 16;
        static final int TRANSACTION_getNotificationClickCallback = 52;
        static final int TRANSACTION_getStatus = 9;
        static final int TRANSACTION_getSuccessedDownloadInfosWithMimeType = 17;
        static final int TRANSACTION_getUnCompletedDownloadInfosWithMimeType = 32;
        static final int TRANSACTION_isDownloadCacheSyncSuccess = 35;
        static final int TRANSACTION_isDownloadSuccessAndFileNotExist = 28;
        static final int TRANSACTION_isDownloading = 10;
        static final int TRANSACTION_isHttpServiceInit = 31;
        static final int TRANSACTION_isServiceForeground = 55;
        static final int TRANSACTION_pause = 2;
        static final int TRANSACTION_pauseAll = 7;
        static final int TRANSACTION_removeAllDownloadChunk = 41;
        static final int TRANSACTION_removeDownloadInfo = 40;
        static final int TRANSACTION_removeDownloadListener = 25;
        static final int TRANSACTION_removeDownloadTaskData = 45;
        static final int TRANSACTION_resetDownloadData = 23;
        static final int TRANSACTION_restart = 6;
        static final int TRANSACTION_restartAllFailedDownloadTasks = 20;
        static final int TRANSACTION_restartAllPauseReserveOnWifiDownloadTasks = 21;
        static final int TRANSACTION_resume = 5;
        static final int TRANSACTION_retryDelayStart = 33;
        static final int TRANSACTION_setDownloadNotificationEventListener = 53;
        static final int TRANSACTION_setDownloadWithIndependentProcessStatus = 36;
        static final int TRANSACTION_setLogLevel = 34;
        static final int TRANSACTION_setThrottleNetSpeed = 56;
        static final int TRANSACTION_startForeground = 29;
        static final int TRANSACTION_stopForeground = 30;
        static final int TRANSACTION_syncDownloadChunks = 48;
        static final int TRANSACTION_syncDownloadInfoFromOtherCache = 47;
        static final int TRANSACTION_tryDownload = 1;
        static final int TRANSACTION_updateDownloadChunk = 42;
        static final int TRANSACTION_updateDownloadInfo = 39;
        static final int TRANSACTION_updateSubDownloadChunk = 43;
        static final int TRANSACTION_updateSubDownloadChunkIndex = 44;

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        /*  JADX ERROR: Dependency scan failed at insn: 0x03FC: INVOKE_CUSTOM_RANGE r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143
            jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x12
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInsn(UsageInfoVisitor.java:128)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.lambda$processInstructions$0(UsageInfoVisitor.java:84)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processInstructions(UsageInfoVisitor.java:82)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processMethod(UsageInfoVisitor.java:67)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.processClass(UsageInfoVisitor.java:56)
            	at jadx.core.dex.visitors.usage.UsageInfoVisitor.init(UsageInfoVisitor.java:41)
            	at jadx.core.dex.nodes.RootNode.runPreDecompileStage(RootNode.java:282)
            */
        /*  JADX ERROR: Failed to decode insn: 0x03FC: INVOKE_CUSTOM_RANGE r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18, r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30, r31, r32, r33, r34, r35, r36, r37, r38, r39, r40, r41, r42, r43, r44, r45, r46, r47, r48, r49, r50, r51, r52, r53, r54, r55, r56, r57, r58, r59, r60, r61, r62, r63, r64, r65, r66, r67, r68, r69, r70, r71, r72, r73, r74, r75, r76, r77, r78, r79, r80, r81, r82, r83, r84, r85, r86, r87, r88, r89, r90, r91, r92, r93, r94, r95, r96, r97, r98, r99, r100, r101, r102, r103, r104, r105, r106, r107, r108, r109, r110, r111, r112, r113, r114, r115, r116, r117, r118, r119, r120, r121, r122, r123, r124, r125, r126, r127, r128, r129, r130, r131, r132, r133, r134, r135, r136, r137, r138, r139, r140, r141, r142, r143, method: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean
            jadx.core.utils.exceptions.JadxRuntimeException: 'invoke-custom' instruction processing error: Unknown encoded value type: 0x12
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:47)
            	at jadx.core.dex.instructions.InsnDecoder.invokeCustom(InsnDecoder.java:588)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:458)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            Caused by: jadx.plugins.input.dex.DexException: Unknown encoded value type: 0x12
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseValue(EncodedValueParser.java:87)
            	at jadx.plugins.input.dex.sections.annotations.EncodedValueParser.parseEncodedArray(EncodedValueParser.java:95)
            	at jadx.plugins.input.dex.sections.SectionReader.getCallSite(SectionReader.java:209)
            	at jadx.plugins.input.dex.insns.DexInsnData.getIndexAsCallSite(DexInsnData.java:141)
            	at jadx.core.utils.input.InsnDataUtils.getCallSite(InsnDataUtils.java:27)
            	at jadx.core.dex.instructions.InvokeCustomBuilder.build(InvokeCustomBuilder.java:24)
            	... 12 more
            */
        /*  JADX ERROR: Failed to decode insn: 0x0448: FILLED_NEW_ARRAY_RANGE , method: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean
            java.lang.NullPointerException
            	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x0451: UNKNOWN(0x0D73), method: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0451: UNKNOWN(0x0D73)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        /*  JADX ERROR: Failed to decode insn: 0x050C: UNKNOWN(0x8D40), method: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean
            jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x050C: UNKNOWN(0x8D40)'
            	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
            	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
            	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
            	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
            	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
            	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:415)
            	at jadx.core.ProcessClass.process(ProcessClass.java:67)
            	at jadx.core.ProcessClass.generateCode(ProcessClass.java:115)
            	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
            	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
            */
        @Override // android.os.Binder
        public boolean onTransact(int r13, android.os.Parcel r14, android.os.Parcel r15, int r16) throws android.os.RemoteException {
            /*
                Method dump skipped, instructions count: 1300
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService.Stub.onTransact(int, android.os.Parcel, android.os.Parcel, int):boolean");
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IDownloadAidlService asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface queryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (queryLocalInterface != null && (queryLocalInterface instanceof IDownloadAidlService)) {
                return (IDownloadAidlService) queryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        /* JADX INFO: Access modifiers changed from: package-private */
        /* renamed from: com.ss.android.socialbase.downloader.downloader.IDownloadAidlService$Stub$Proxy */
        /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
        public static class Proxy implements IDownloadAidlService {
            public static IDownloadAidlService sDefaultImpl;
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

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void tryDownload(DownloadAidlTask downloadAidlTask) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(downloadAidlTask != null ? downloadAidlTask.asBinder() : null);
                    if (!this.mRemote.transact(1, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().tryDownload(downloadAidlTask);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void pause(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(2, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pause(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void cancel(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(3, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().cancel(i, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean canResume(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(4, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().canResume(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void resume(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(5, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resume(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void restart(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(6, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restart(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void pauseAll() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(7, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().pauseAll();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public long getCurBytes(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(8, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurBytes(i);
                    }
                    obtain2.readException();
                    return obtain2.readLong();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public int getStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(9, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getStatus(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean isDownloading(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(10, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDownloading(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public DownloadInfo getDownloadInfo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(11, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadInfo(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadInfo> getDownloadInfoList(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(12, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadInfoList(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadChunk> getDownloadChunk(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(13, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadChunk(i);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadChunk.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public int getDownloadId(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(14, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadId(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public DownloadInfo getDownloadInfoByUrlAndPath(String str, String str2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    obtain.writeString(str2);
                    if (!this.mRemote.transact(15, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadInfoByUrlAndPath(str, str2);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0 ? DownloadInfo.CREATOR.createFromParcel(obtain2) : null;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(16, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getFailedDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(17, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getSuccessedDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadInfo> getDownloadingDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(18, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadingDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadInfo> getAllDownloadInfo() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(19, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAllDownloadInfo();
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void restartAllFailedDownloadTasks(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    if (!this.mRemote.transact(20, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restartAllFailedDownloadTasks(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void restartAllPauseReserveOnWifiDownloadTasks(List<String> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStringList(list);
                    if (!this.mRemote.transact(21, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().restartAllPauseReserveOnWifiDownloadTasks(list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void clearDownloadData(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(22, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearDownloadData(i, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void resetDownloadData(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(23, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().resetDownloadData(i, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void forceDownloadIngoreRecommendSize(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(24, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().forceDownloadIngoreRecommendSize(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void removeDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iDownloadAidlListener != null ? iDownloadAidlListener.asBinder() : null);
                    obtain.writeInt(i3);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(25, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeDownloadListener(i, i2, iDownloadAidlListener, i3, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void addDownloadListener(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iDownloadAidlListener != null ? iDownloadAidlListener.asBinder() : null);
                    obtain.writeInt(i3);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(26, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDownloadListener(i, i2, iDownloadAidlListener, i3, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void addDownloadListener1(int i, int i2, IDownloadAidlListener iDownloadAidlListener, int i3, boolean z, boolean z2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeStrongBinder(iDownloadAidlListener != null ? iDownloadAidlListener.asBinder() : null);
                    obtain.writeInt(i3);
                    int i4 = 1;
                    obtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i4 = 0;
                    }
                    obtain.writeInt(i4);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(27, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDownloadListener1(i, i2, iDownloadAidlListener, i3, z, z2);
                        obtain2.recycle();
                        obtain.recycle();
                        return;
                    }
                    obtain2.readException();
                    obtain2.recycle();
                    obtain.recycle();
                } catch (Throwable th2) {
                    th = th2;
                    obtain2.recycle();
                    obtain.recycle();
                    throw th;
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean isDownloadSuccessAndFileNotExist(DownloadInfo downloadInfo) throws RemoteException {
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
                    if (!this.mRemote.transact(28, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDownloadSuccessAndFileNotExist(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void startForeground(int i, Notification notification) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (notification != null) {
                        obtain.writeInt(1);
                        notification.writeToParcel(obtain, 0);
                    } else {
                        obtain.writeInt(0);
                    }
                    if (this.mRemote.transact(29, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().startForeground(i, notification);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void stopForeground(boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(30, obtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().stopForeground(z);
                } finally {
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean isHttpServiceInit() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(31, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isHttpServiceInit();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeString(str);
                    if (!this.mRemote.transact(32, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getUnCompletedDownloadInfosWithMimeType(str);
                    }
                    obtain2.readException();
                    return obtain2.createTypedArrayList(DownloadInfo.CREATOR);
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean retryDelayStart(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(33, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().retryDelayStart(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void setLogLevel(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(34, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLogLevel(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean isDownloadCacheSyncSuccess() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(35, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDownloadCacheSyncSuccess();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void setDownloadWithIndependentProcessStatus(int i, boolean z) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(36, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDownloadWithIndependentProcessStatus(i, z);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public int getDownloadWithIndependentProcessStatus(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(37, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadWithIndependentProcessStatus(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt();
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
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
                    if (!this.mRemote.transact(38, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addDownloadChunk(downloadChunk);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
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
                    if (!this.mRemote.transact(39, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().updateDownloadInfo(downloadInfo);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean removeDownloadInfo(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(40, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDownloadInfo(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void removeAllDownloadChunk(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(41, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeAllDownloadChunk(i);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void updateDownloadChunk(int i, int i2, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(42, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateDownloadChunk(i, i2, j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void updateSubDownloadChunk(int i, int i2, int i3, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(43, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateSubDownloadChunk(i, i2, i3, j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void updateSubDownloadChunkIndex(int i, int i2, int i3, int i4) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    obtain.writeInt(i3);
                    obtain.writeInt(i4);
                    if (!this.mRemote.transact(44, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateSubDownloadChunkIndex(i, i2, i3, i4);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean removeDownloadTaskData(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(45, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().removeDownloadTaskData(i);
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void clearData() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(46, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearData();
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(47, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncDownloadInfoFromOtherCache(i, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void syncDownloadChunks(int i, List<DownloadChunk> list) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeTypedList(list);
                    if (!this.mRemote.transact(48, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncDownloadChunks(i, list);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void addProcessCallback(ProcessAidlCallback processAidlCallback) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeStrongBinder(processAidlCallback != null ? processAidlCallback.asBinder() : null);
                    if (!this.mRemote.transact(49, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addProcessCallback(processAidlCallback);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void dispatchProcessCallback(int i, int i2) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeInt(i2);
                    if (!this.mRemote.transact(50, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dispatchProcessCallback(i, i2);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public IDownloadNotificationEventAidlListener getDownloadNotificationEventListener(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(51, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadNotificationEventListener(i);
                    }
                    obtain2.readException();
                    return IDownloadNotificationEventAidlListener.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public INotificationClickAidlCallback getNotificationClickCallback(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(52, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNotificationClickCallback(i);
                    }
                    obtain2.readException();
                    return INotificationClickAidlCallback.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void setDownloadNotificationEventListener(int i, IDownloadNotificationEventAidlListener iDownloadNotificationEventAidlListener) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeStrongBinder(iDownloadNotificationEventAidlListener != null ? iDownloadNotificationEventAidlListener.asBinder() : null);
                    if (!this.mRemote.transact(53, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDownloadNotificationEventListener(i, iDownloadNotificationEventAidlListener);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public IDownloadAidlFileProvider getDownloadFileUriProvider(int i) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    if (!this.mRemote.transact(54, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDownloadFileUriProvider(i);
                    }
                    obtain2.readException();
                    return IDownloadAidlFileProvider.Stub.asInterface(obtain2.readStrongBinder());
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public boolean isServiceForeground() throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(55, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isServiceForeground();
                    }
                    obtain2.readException();
                    return obtain2.readInt() != 0;
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }

            @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadAidlService
            public void setThrottleNetSpeed(int i, long j) throws RemoteException {
                Parcel obtain = Parcel.obtain();
                Parcel obtain2 = Parcel.obtain();
                try {
                    obtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    obtain.writeInt(i);
                    obtain.writeLong(j);
                    if (!this.mRemote.transact(56, obtain, obtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setThrottleNetSpeed(i, j);
                    } else {
                        obtain2.readException();
                    }
                } finally {
                    obtain2.recycle();
                    obtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IDownloadAidlService iDownloadAidlService) {
            if (Proxy.sDefaultImpl != null || iDownloadAidlService == null) {
                return false;
            }
            Proxy.sDefaultImpl = iDownloadAidlService;
            return true;
        }

        public static IDownloadAidlService getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
