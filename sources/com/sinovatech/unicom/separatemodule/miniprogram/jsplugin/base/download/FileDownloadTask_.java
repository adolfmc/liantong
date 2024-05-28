package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download;

import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.download.FileDownloadTaskCursor;
import io.objectbox.EntityInfo;
import io.objectbox.Property;
import io.objectbox.annotation.apihint.Internal;
import io.objectbox.internal.CursorFactory;
import io.objectbox.internal.IdGetter;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class FileDownloadTask_ implements EntityInfo<FileDownloadTask> {
    public static final Property<FileDownloadTask>[] __ALL_PROPERTIES;
    public static final String __DB_NAME = "FileDownloadTask";
    public static final int __ENTITY_ID = 23;
    public static final String __ENTITY_NAME = "FileDownloadTask";
    public static final Property<FileDownloadTask> __ID_PROPERTY;
    public static final Class<FileDownloadTask> __ENTITY_CLASS = FileDownloadTask.class;
    public static final CursorFactory<FileDownloadTask> __CURSOR_FACTORY = new FileDownloadTaskCursor.Factory();
    @Internal
    static final FileDownloadTaskIdGetter __ID_GETTER = new FileDownloadTaskIdGetter();
    public static final FileDownloadTask_ __INSTANCE = new FileDownloadTask_();

    /* renamed from: id */
    public static final Property<FileDownloadTask> f18571id = new Property<>(__INSTANCE, 0, 1, Long.TYPE, "id", true, "id");
    public static final Property<FileDownloadTask> url = new Property<>(__INSTANCE, 1, 2, String.class, "url");
    public static final Property<FileDownloadTask> tempFilePath = new Property<>(__INSTANCE, 2, 11, String.class, "tempFilePath");
    public static final Property<FileDownloadTask> filePath = new Property<>(__INSTANCE, 3, 3, String.class, "filePath");
    public static final Property<FileDownloadTask> totalLength = new Property<>(__INSTANCE, 4, 4, Long.TYPE, "totalLength");
    public static final Property<FileDownloadTask> totalOffset = new Property<>(__INSTANCE, 5, 5, Long.TYPE, "totalOffset");
    public static final Property<FileDownloadTask> speed = new Property<>(__INSTANCE, 6, 6, String.class, "speed");
    public static final Property<FileDownloadTask> taskStatus = new Property<>(__INSTANCE, 7, 7, String.class, "taskStatus");
    public static final Property<FileDownloadTask> errorMsg = new Property<>(__INSTANCE, 8, 8, String.class, "errorMsg");
    public static final Property<FileDownloadTask> fileName = new Property<>(__INSTANCE, 9, 9, String.class, "fileName");
    public static final Property<FileDownloadTask> fileContentType = new Property<>(__INSTANCE, 10, 10, String.class, "fileContentType");

    @Override // io.objectbox.EntityInfo
    public String getDbName() {
        return "FileDownloadTask";
    }

    @Override // io.objectbox.EntityInfo
    public int getEntityId() {
        return 23;
    }

    @Override // io.objectbox.EntityInfo
    public String getEntityName() {
        return "FileDownloadTask";
    }

    static {
        Property<FileDownloadTask> property = f18571id;
        __ALL_PROPERTIES = new Property[]{property, url, tempFilePath, filePath, totalLength, totalOffset, speed, taskStatus, errorMsg, fileName, fileContentType};
        __ID_PROPERTY = property;
    }

    @Override // io.objectbox.EntityInfo
    public Class<FileDownloadTask> getEntityClass() {
        return __ENTITY_CLASS;
    }

    @Override // io.objectbox.EntityInfo
    public Property<FileDownloadTask>[] getAllProperties() {
        return __ALL_PROPERTIES;
    }

    @Override // io.objectbox.EntityInfo
    public Property<FileDownloadTask> getIdProperty() {
        return __ID_PROPERTY;
    }

    @Override // io.objectbox.EntityInfo
    public IdGetter<FileDownloadTask> getIdGetter() {
        return __ID_GETTER;
    }

    @Override // io.objectbox.EntityInfo
    public CursorFactory<FileDownloadTask> getCursorFactory() {
        return __CURSOR_FACTORY;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Internal
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static final class FileDownloadTaskIdGetter implements IdGetter<FileDownloadTask> {
        FileDownloadTaskIdGetter() {
        }

        @Override // io.objectbox.internal.IdGetter
        public long getId(FileDownloadTask fileDownloadTask) {
            return fileDownloadTask.getId();
        }
    }
}
