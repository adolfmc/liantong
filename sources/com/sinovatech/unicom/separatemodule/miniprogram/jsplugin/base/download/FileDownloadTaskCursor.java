package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download;

import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download.FileDownloadTask_;
import io.objectbox.BoxStore;
import io.objectbox.Cursor;
import io.objectbox.Transaction;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class FileDownloadTaskCursor extends Cursor<FileDownloadTask> {
    private static final FileDownloadTask_.FileDownloadTaskIdGetter ID_GETTER = FileDownloadTask_.__ID_GETTER;
    private static final int __ID_url = FileDownloadTask_.url.f24389id;
    private static final int __ID_tempFilePath = FileDownloadTask_.tempFilePath.f24389id;
    private static final int __ID_filePath = FileDownloadTask_.filePath.f24389id;
    private static final int __ID_totalLength = FileDownloadTask_.totalLength.f24389id;
    private static final int __ID_totalOffset = FileDownloadTask_.totalOffset.f24389id;
    private static final int __ID_speed = FileDownloadTask_.speed.f24389id;
    private static final int __ID_taskStatus = FileDownloadTask_.taskStatus.f24389id;
    private static final int __ID_errorMsg = FileDownloadTask_.errorMsg.f24389id;
    private static final int __ID_fileName = FileDownloadTask_.fileName.f24389id;
    private static final int __ID_fileContentType = FileDownloadTask_.fileContentType.f24389id;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class Factory implements CursorFactory<FileDownloadTask> {
        @Override // io.objectbox.internal.CursorFactory
        public Cursor<FileDownloadTask> createCursor(Transaction transaction, long j, BoxStore boxStore) {
            return new FileDownloadTaskCursor(transaction, j, boxStore);
        }
    }

    public FileDownloadTaskCursor(Transaction transaction, long j, BoxStore boxStore) {
        super(transaction, j, FileDownloadTask_.__INSTANCE, boxStore);
    }

    @Override // io.objectbox.Cursor
    public final long getId(FileDownloadTask fileDownloadTask) {
        return ID_GETTER.getId(fileDownloadTask);
    }

    @Override // io.objectbox.Cursor
    public final long put(FileDownloadTask fileDownloadTask) {
        String url = fileDownloadTask.getUrl();
        int i = url != null ? __ID_url : 0;
        String tempFilePath = fileDownloadTask.getTempFilePath();
        int i2 = tempFilePath != null ? __ID_tempFilePath : 0;
        String filePath = fileDownloadTask.getFilePath();
        int i3 = filePath != null ? __ID_filePath : 0;
        String speed = fileDownloadTask.getSpeed();
        collect400000(this.cursor, 0L, 1, i, url, i2, tempFilePath, i3, filePath, speed != null ? __ID_speed : 0, speed);
        String taskStatus = fileDownloadTask.getTaskStatus();
        int i4 = taskStatus != null ? __ID_taskStatus : 0;
        String errorMsg = fileDownloadTask.getErrorMsg();
        int i5 = errorMsg != null ? __ID_errorMsg : 0;
        String fileName = fileDownloadTask.getFileName();
        int i6 = fileName != null ? __ID_fileName : 0;
        String fileContentType = fileDownloadTask.getFileContentType();
        collect400000(this.cursor, 0L, 0, i4, taskStatus, i5, errorMsg, i6, fileName, fileContentType != null ? __ID_fileContentType : 0, fileContentType);
        long collect004000 = collect004000(this.cursor, fileDownloadTask.getId(), 2, __ID_totalLength, fileDownloadTask.getTotalLength(), __ID_totalOffset, fileDownloadTask.getTotalOffset(), 0, 0L, 0, 0L);
        fileDownloadTask.setId(collect004000);
        return collect004000;
    }
}
