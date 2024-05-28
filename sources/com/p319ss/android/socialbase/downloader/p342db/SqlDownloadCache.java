package com.p319ss.android.socialbase.downloader.p342db;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.text.TextUtils;
import android.util.Log;
import android.util.SparseArray;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONArrayInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.p319ss.android.socialbase.downloader.constants.DBDefinition;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.downloader.ISqlDownloadCache;
import com.p319ss.android.socialbase.downloader.logger.Logger;
import com.p319ss.android.socialbase.downloader.model.DownloadChunk;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl;
import com.p319ss.android.socialbase.downloader.segment.Segment;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import org.json.JSONArray;

@NBSInstrumented
/* renamed from: com.ss.android.socialbase.downloader.db.SqlDownloadCache */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SqlDownloadCache extends ISqlDownloadCacheAidl.Stub implements ISqlDownloadCache {
    private static volatile SQLiteDatabase database;
    private volatile boolean cacheSynced;
    ISqlCacheLoadCompleteCallbackAidl callback;
    private TableStatements chunkTableStatements;
    private TableStatements downloadTableStatements;
    private TableStatements segmentTableStatements;

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public boolean ensureDownloadCacheSyncSuccess() {
        return false;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public List<DownloadInfo> getAllDownloadInfo() {
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public List<DownloadInfo> getFailedDownloadInfosWithMimeType(String str) {
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public List<DownloadInfo> getSuccessedDownloadInfosWithMimeType(String str) {
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public List<DownloadInfo> getUnCompletedDownloadInfosWithMimeType(String str) {
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo onDownloadTaskStart(int i) {
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void syncDownloadChunks(int i, List<DownloadChunk> list) {
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void syncDownloadInfo(DownloadInfo downloadInfo) {
    }

    public SqlDownloadCache() {
        this(false);
    }

    public SqlDownloadCache(boolean z) {
        this.callback = null;
        if (z) {
            this.cacheSynced = false;
            init();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ensureDataBaseInit() {
        if (database == null) {
            synchronized (SqlDownloadCache.class) {
                if (database == null) {
                    database = DownloadDBHelper.getInstance().getWritableDatabase();
                    this.downloadTableStatements = new TableStatements(database, "downloader", DBDefinition.DOWNLOAD_ALL_COLUMNS, DBDefinition.DOWNLOAD_PK_COLUMNS);
                    this.chunkTableStatements = new TableStatements(database, "downloadChunk", DBDefinition.CHUNK_ALL_COLUMNS, DBDefinition.CHUNK_PK_COLUMNS);
                    this.segmentTableStatements = new TableStatements(database, "segments", DBDefinition.SEGMENT_ALL_COLUMNS, DBDefinition.SEGMENT_PK_COLUMNS);
                }
            }
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void init() {
        init(new SparseArray<>(), new SparseArray<>(), null);
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.ISqlDownloadCache
    public void init(final SparseArray<DownloadInfo> sparseArray, final SparseArray<List<DownloadChunk>> sparseArray2, final SqlCacheLoadCompleteCallback sqlCacheLoadCompleteCallback) {
        try {
            Runnable runnable = new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.1
                /* JADX WARN: Code restructure failed: missing block: B:126:0x0280, code lost:
                    if (r0 == null) goto L91;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:133:0x028c, code lost:
                    if (r0 == null) goto L91;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:135:0x0290, code lost:
                    return;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:167:0x0341, code lost:
                    if (r0 == null) goto L186;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:174:0x034c, code lost:
                    if (r0 == null) goto L186;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:175:0x034e, code lost:
                    r0.callback();
                    r18.this$0.cacheSynced = true;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:176:0x0356, code lost:
                    r18.this$0.onInitFinish(r2, r3);
                 */
                /* JADX WARN: Code restructure failed: missing block: B:177:0x035f, code lost:
                    throw r0;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:81:0x01a8, code lost:
                    if (r0 != null) goto L93;
                 */
                /* JADX WARN: Removed duplicated region for block: B:200:0x00e7 A[SYNTHETIC] */
                /* JADX WARN: Removed duplicated region for block: B:201:0x00d7 A[SYNTHETIC] */
                @Override // java.lang.Runnable
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                    To view partially-correct code enable 'Show inconsistent code' option in preferences
                */
                public void run() {
                    /*
                        Method dump skipped, instructions count: 886
                        To view this dump change 'Code comments level' option to 'DEBUG'
                    */
                    throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.RunnableC101811.run():void");
                }
            };
            ExecutorService dBThreadExecutorService = DownloadComponentManager.getDBThreadExecutorService();
            if (dBThreadExecutorService != null) {
                dBThreadExecutorService.execute(runnable);
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearAntiHijackDirIfNeeded(List<DownloadInfo> list) {
        if (list == null) {
            return;
        }
        try {
            for (DownloadInfo downloadInfo : list) {
                if (downloadInfo != null && downloadInfo.isSavePathRedirected()) {
                    DownloadUtils.clearAntiHijackDir(downloadInfo);
                }
            }
        } catch (Throwable unused) {
        }
    }

    private void safeBeginTransaction() {
        database.beginTransaction();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void loadCacheFromDB(List<DownloadInfo> list, List<Integer> list2, SparseArray<DownloadInfo> sparseArray, SparseArray<DownloadInfo> sparseArray2, SparseArray<List<DownloadChunk>> sparseArray3) {
        int size = sparseArray.size();
        if (size < 0 || database == null) {
            return;
        }
        synchronized (database) {
            safeBeginTransaction();
            if (!list.isEmpty()) {
                if (DownloadSetting.obtainGlobal().optBugFix("clear_invalid_task_error")) {
                    String[] strArr = new String[list.size()];
                    for (int i = 0; i < list.size(); i++) {
                        strArr[i] = String.valueOf(list.get(i));
                    }
                    String str = "CAST(_id AS TEXT) IN (" + new String(new char[list.size() - 1]).replace("\u0000", "?,") + "?)";
                    SQLiteDatabase sQLiteDatabase = database;
                    if (sQLiteDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.delete(sQLiteDatabase, "downloader", str, strArr);
                    } else {
                        sQLiteDatabase.delete("downloader", str, strArr);
                    }
                    SQLiteDatabase sQLiteDatabase2 = database;
                    if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.delete(sQLiteDatabase2, "downloadChunk", str, strArr);
                    } else {
                        sQLiteDatabase2.delete("downloadChunk", str, strArr);
                    }
                } else {
                    String join = TextUtils.join(", ", list2);
                    SQLiteDatabase sQLiteDatabase3 = database;
                    String[] strArr2 = {join};
                    if (sQLiteDatabase3 instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.delete(sQLiteDatabase3, "downloader", "_id IN (?)", strArr2);
                    } else {
                        sQLiteDatabase3.delete("downloader", "_id IN (?)", strArr2);
                    }
                    SQLiteDatabase sQLiteDatabase4 = database;
                    String[] strArr3 = {join};
                    if (sQLiteDatabase4 instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.delete(sQLiteDatabase4, "downloadChunk", "_id IN (?)", strArr3);
                    } else {
                        sQLiteDatabase4.delete("downloadChunk", "_id IN (?)", strArr3);
                    }
                }
            }
            for (int i2 = 0; i2 < size; i2++) {
                int keyAt = sparseArray.keyAt(i2);
                DownloadInfo downloadInfo = sparseArray.get(keyAt);
                SQLiteDatabase sQLiteDatabase5 = database;
                String[] strArr4 = {String.valueOf(keyAt)};
                if (sQLiteDatabase5 instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.delete(sQLiteDatabase5, "downloader", "_id = ?", strArr4);
                } else {
                    sQLiteDatabase5.delete("downloader", "_id = ?", strArr4);
                }
                SQLiteDatabase sQLiteDatabase6 = database;
                ContentValues contentValues = downloadInfo.toContentValues();
                if (sQLiteDatabase6 instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.insert(sQLiteDatabase6, "downloader", null, contentValues);
                } else {
                    sQLiteDatabase6.insert("downloader", null, contentValues);
                }
                if (downloadInfo.getChunkCount() > 1) {
                    List<DownloadChunk> downloadChunk = getDownloadChunk(keyAt);
                    if (downloadChunk.size() > 0) {
                        SQLiteDatabase sQLiteDatabase7 = database;
                        String[] strArr5 = {String.valueOf(keyAt)};
                        if (sQLiteDatabase7 instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.delete(sQLiteDatabase7, "downloadChunk", "_id = ?", strArr5);
                        } else {
                            sQLiteDatabase7.delete("downloadChunk", "_id = ?", strArr5);
                        }
                        for (DownloadChunk downloadChunk2 : downloadChunk) {
                            downloadChunk2.setId(downloadInfo.getId());
                            SQLiteDatabase sQLiteDatabase8 = database;
                            ContentValues contentValues2 = downloadChunk2.toContentValues();
                            if (sQLiteDatabase8 instanceof SQLiteDatabase) {
                                NBSSQLiteInstrumentation.insert(sQLiteDatabase8, "downloadChunk", null, contentValues2);
                            } else {
                                sQLiteDatabase8.insert("downloadChunk", null, contentValues2);
                            }
                        }
                    }
                }
            }
            if (sparseArray2 != null && sparseArray3 != null) {
                int size2 = sparseArray2.size();
                for (int i3 = 0; i3 < size2; i3++) {
                    int id = sparseArray2.valueAt(i3).getId();
                    List<DownloadChunk> parseHostChunkList = DownloadUtils.parseHostChunkList(getDownloadChunk(id));
                    if (parseHostChunkList != null && parseHostChunkList.size() > 0) {
                        sparseArray3.put(id, parseHostChunkList);
                    }
                }
            }
            database.setTransactionSuccessful();
            safeEndTransaction();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public boolean cacheExist(int i) {
        try {
            return getDownloadInfo(i) != null;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public com.p319ss.android.socialbase.downloader.model.DownloadInfo getDownloadInfo(int r9) {
        /*
            r8 = this;
            r8.ensureDataBaseInit()
            android.database.sqlite.SQLiteDatabase r0 = com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.database
            r1 = 0
            if (r0 == 0) goto L72
            r0 = 0
            r2 = 1
            android.database.sqlite.SQLiteDatabase r3 = com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.database     // Catch: java.lang.Throwable -> L5b
            java.lang.String r4 = "SELECT * FROM %s WHERE %s = ?"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L5b
            java.lang.String r6 = "downloader"
            r5[r0] = r6     // Catch: java.lang.Throwable -> L5b
            java.lang.String r6 = "_id"
            r5[r2] = r6     // Catch: java.lang.Throwable -> L5b
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L5b
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L5b
            java.lang.String r9 = java.lang.Integer.toString(r9)     // Catch: java.lang.Throwable -> L5b
            r5[r0] = r9     // Catch: java.lang.Throwable -> L5b
            boolean r9 = r3 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L5b
            if (r9 != 0) goto L2e
            android.database.Cursor r9 = r3.rawQuery(r4, r5)     // Catch: java.lang.Throwable -> L5b
            goto L34
        L2e:
            android.database.sqlite.SQLiteDatabase r3 = (android.database.sqlite.SQLiteDatabase) r3     // Catch: java.lang.Throwable -> L5b
            android.database.Cursor r9 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r3, r4, r5)     // Catch: java.lang.Throwable -> L5b
        L34:
            boolean r3 = r9.moveToNext()     // Catch: java.lang.Throwable -> L54
            if (r3 == 0) goto L47
            com.ss.android.socialbase.downloader.model.DownloadInfo r3 = new com.ss.android.socialbase.downloader.model.DownloadInfo     // Catch: java.lang.Throwable -> L54
            r3.<init>(r9)     // Catch: java.lang.Throwable -> L54
            android.database.Cursor[] r1 = new android.database.Cursor[r2]
            r1[r0] = r9
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r1)
            return r3
        L47:
            android.database.Cursor[] r2 = new android.database.Cursor[r2]
            r2[r0] = r9
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r2)
            goto L72
        L4f:
            r1 = move-exception
            r7 = r1
            r1 = r9
            r9 = r7
            goto L6a
        L54:
            r3 = move-exception
            r7 = r3
            r3 = r9
            r9 = r7
            goto L5d
        L59:
            r9 = move-exception
            goto L6a
        L5b:
            r9 = move-exception
            r3 = r1
        L5d:
            r9.printStackTrace()     // Catch: java.lang.Throwable -> L68
            android.database.Cursor[] r9 = new android.database.Cursor[r2]
            r9[r0] = r3
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r9)
            goto L72
        L68:
            r9 = move-exception
            r1 = r3
        L6a:
            android.database.Cursor[] r2 = new android.database.Cursor[r2]
            r2[r0] = r1
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r2)
            throw r9
        L72:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.getDownloadInfo(int):com.ss.android.socialbase.downloader.model.DownloadInfo");
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public List<DownloadInfo> getDownloadInfoList(String str) {
        Cursor[] cursorArr;
        ensureDataBaseInit();
        ArrayList arrayList = new ArrayList();
        if (database != null) {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase = database;
                String format = String.format("SELECT * FROM %s WHERE %s = ?", "downloader", "url");
                String[] strArr = {str};
                cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(format, strArr) : NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, format, strArr);
                if (cursor.moveToNext()) {
                    arrayList.add(new DownloadInfo(cursor));
                }
                cursorArr = new Cursor[]{cursor};
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    cursorArr = new Cursor[]{cursor};
                } catch (Throwable th2) {
                    DownloadUtils.safeClose(cursor);
                    throw th2;
                }
            }
            DownloadUtils.safeClose(cursorArr);
        }
        return arrayList;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public List<DownloadChunk> getDownloadChunk(int i) {
        Cursor[] cursorArr;
        ArrayList arrayList = new ArrayList();
        ensureDataBaseInit();
        if (database != null) {
            Cursor cursor = null;
            try {
                SQLiteDatabase sQLiteDatabase = database;
                String format = String.format("SELECT * FROM %s WHERE %s = ?", "downloadChunk", "_id");
                String[] strArr = {Integer.toString(i)};
                cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(format, strArr) : NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, format, strArr);
                while (cursor.moveToNext()) {
                    arrayList.add(new DownloadChunk(cursor));
                }
                cursorArr = new Cursor[]{cursor};
            } catch (Throwable th) {
                try {
                    th.printStackTrace();
                    cursorArr = new Cursor[]{cursor};
                } catch (Throwable th2) {
                    DownloadUtils.safeClose(cursor);
                    throw th2;
                }
            }
            DownloadUtils.safeClose(cursorArr);
        }
        return arrayList;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void removeAllDownloadChunk(final int i) {
        ensureDataBaseInit();
        if (database == null || this.chunkTableStatements == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SqlDownloadCache.this.deleteInner(i, SqlDownloadCache.this.chunkTableStatements.getDeleteStatement());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void deleteInner(int i, SQLiteStatement sQLiteStatement) {
        if (sQLiteStatement == null) {
            return;
        }
        try {
            synchronized (sQLiteStatement) {
                sQLiteStatement.bindLong(1, i);
                sQLiteStatement.execute();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void addDownloadChunk(final DownloadChunk downloadChunk) {
        ensureDataBaseInit();
        if (database == null || this.chunkTableStatements == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SqlDownloadCache.this.insertDownloadChunkInner(downloadChunk, SqlDownloadCache.this.chunkTableStatements.getInsertStatement());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void addSubDownloadChunk(DownloadChunk downloadChunk) {
        addDownloadChunk(downloadChunk);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insertDownloadInfoInner(DownloadInfo downloadInfo, SQLiteStatement sQLiteStatement) {
        if (downloadInfo == null || sQLiteStatement == null) {
            return;
        }
        try {
            synchronized (sQLiteStatement) {
                downloadInfo.bindValue(sQLiteStatement);
                sQLiteStatement.executeInsert();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void insertDownloadChunkInner(DownloadChunk downloadChunk, SQLiteStatement sQLiteStatement) {
        if (downloadChunk == null || sQLiteStatement == null) {
            return;
        }
        try {
            synchronized (sQLiteStatement) {
                downloadChunk.bindValue(sQLiteStatement);
                sQLiteStatement.executeInsert();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void updateDownloadChunk(final int i, final int i2, final long j) {
        ensureDataBaseInit();
        if (i == 0 || i2 < 0 || j < 0 || database == null || this.chunkTableStatements == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.4
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SqlDownloadCache.this.updateDownloadChunkInner(i, i2, j, SqlDownloadCache.this.chunkTableStatements.getUpdateStatement());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void updateSubDownloadChunk(final int i, final int i2, final int i3, final long j) {
        ensureDataBaseInit();
        if (i == 0 || i2 < 0 || i3 < 0 || j < 0 || database == null || this.chunkTableStatements == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.5
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SqlDownloadCache.this.updateSubDownloadChunkInner(i, i2, i3, j, SqlDownloadCache.this.chunkTableStatements.getUpdateStatement());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void updateSubDownloadChunkIndex(final int i, final int i2, final int i3, final int i4) {
        ensureDataBaseInit();
        if (i == 0 || i3 < 0 || i4 == i2 || i4 < 0 || database == null || this.chunkTableStatements == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.6
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SqlDownloadCache.this.updateSubDownloadChunkIndexInner(i, i2, i3, i4, SqlDownloadCache.this.chunkTableStatements.getUpdateStatement());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubDownloadChunkIndexInner(int i, int i2, int i3, int i4, SQLiteStatement sQLiteStatement) {
        try {
            synchronized (sQLiteStatement) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("chunkIndex", Integer.valueOf(i4));
                SQLiteDatabase sQLiteDatabase = database;
                String[] strArr = {Integer.toString(i), Integer.toString(i2), Integer.toString(i3)};
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.update(sQLiteDatabase, "downloadChunk", contentValues, "_id = ? AND chunkIndex = ? AND hostChunkIndex = ?", strArr);
                } else {
                    sQLiteDatabase.update("downloadChunk", contentValues, "_id = ? AND chunkIndex = ? AND hostChunkIndex = ?", strArr);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateDownloadChunkInner(int i, int i2, long j, SQLiteStatement sQLiteStatement) {
        try {
            synchronized (sQLiteStatement) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("curOffset", Long.valueOf(j));
                SQLiteDatabase sQLiteDatabase = database;
                String[] strArr = {Integer.toString(i), Integer.toString(i2)};
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.update(sQLiteDatabase, "downloadChunk", contentValues, "_id = ? AND chunkIndex = ?", strArr);
                } else {
                    sQLiteDatabase.update("downloadChunk", contentValues, "_id = ? AND chunkIndex = ?", strArr);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSubDownloadChunkInner(int i, int i2, int i3, long j, SQLiteStatement sQLiteStatement) {
        try {
            synchronized (sQLiteStatement) {
                ContentValues contentValues = new ContentValues();
                contentValues.put("curOffset", Long.valueOf(j));
                SQLiteDatabase sQLiteDatabase = database;
                String[] strArr = {Integer.toString(i), Integer.toString(i2), Integer.toString(i3)};
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.update(sQLiteDatabase, "downloadChunk", contentValues, "_id = ? AND chunkIndex = ? AND hostChunkIndex = ?", strArr);
                } else {
                    sQLiteDatabase.update("downloadChunk", contentValues, "_id = ? AND chunkIndex = ? AND hostChunkIndex = ?", strArr);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void updateDownloadInfoInner(DownloadInfo downloadInfo, SQLiteStatement sQLiteStatement) {
        if (downloadInfo == null || sQLiteStatement == null) {
            return;
        }
        try {
            synchronized (sQLiteStatement) {
                downloadInfo.bindValue(sQLiteStatement);
                sQLiteStatement.bindLong(downloadInfo.getBindValueCount() + 1, downloadInfo.getId());
                sQLiteStatement.execute();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo updateChunkCount(int i, int i2) {
        ensureDataBaseInit();
        if (database == null) {
            return null;
        }
        int i3 = 10;
        while (database.isDbLockedByCurrentThread() && i3 - 1 >= 0) {
            try {
                Thread.sleep(5L);
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("chunkCount", Integer.valueOf(i2));
        SQLiteDatabase sQLiteDatabase = database;
        String[] strArr = {Integer.toString(i)};
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.update(sQLiteDatabase, "downloader", contentValues, "_id = ? ", strArr);
        } else {
            sQLiteDatabase.update("downloader", contentValues, "_id = ? ", strArr);
        }
        return null;
    }

    private void addDownloadInfo(final DownloadInfo downloadInfo) {
        ensureDataBaseInit();
        if (database == null || this.downloadTableStatements == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.7
            @Override // java.lang.Runnable
            public void run() {
                try {
                    SqlDownloadCache.this.insertDownloadInfoInner(downloadInfo, SqlDownloadCache.this.downloadTableStatements.getInsertStatement());
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        });
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public boolean updateDownloadInfo(final DownloadInfo downloadInfo) {
        ensureDataBaseInit();
        if (downloadInfo == null || database == null) {
            return false;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.8
            @Override // java.lang.Runnable
            public void run() {
                SqlDownloadCache.this.updateDownloadInfoForCurrentThread(downloadInfo);
            }
        });
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void updateDownloadInfoForCurrentThread(DownloadInfo downloadInfo) {
        if (downloadInfo == null) {
            return;
        }
        try {
            if (!cacheExist(downloadInfo.getId())) {
                addDownloadInfo(downloadInfo);
            } else if (this.downloadTableStatements == null) {
            } else {
                updateDownloadInfoInner(downloadInfo, this.downloadTableStatements.getUpdateStatement());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void safeEndTransaction() {
        try {
            if (database == null || !database.inTransaction()) {
                return;
            }
            database.endTransaction();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public boolean removeDownloadInfo(int i) {
        TableStatements tableStatements;
        ensureDataBaseInit();
        if (database == null || (tableStatements = this.downloadTableStatements) == null) {
            return false;
        }
        try {
            deleteInner(i, tableStatements.getDeleteStatement());
            return true;
        } catch (Throwable th) {
            th.printStackTrace();
            return false;
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public boolean removeDownloadTaskData(final int i) {
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.9
            @Override // java.lang.Runnable
            public void run() {
                SqlDownloadCache.this.removeDownloadInfo(i);
                SqlDownloadCache.this.removeAllDownloadChunk(i);
                SqlDownloadCache.this.removeSegments(i);
            }
        });
        return true;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void clearData() {
        ensureDataBaseInit();
        if (database == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.10
            @Override // java.lang.Runnable
            public void run() {
                SqlDownloadCache.this.clearDataInSubThread();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void clearDataInSubThread() {
        safeBeginTransaction();
        SQLiteDatabase sQLiteDatabase = database;
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.delete(sQLiteDatabase, "downloader", null, null);
        } else {
            sQLiteDatabase.delete("downloader", null, null);
        }
        SQLiteDatabase sQLiteDatabase2 = database;
        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.delete(sQLiteDatabase2, "downloadChunk", null, null);
        } else {
            sQLiteDatabase2.delete("downloadChunk", null, null);
        }
        database.setTransactionSuccessful();
        safeEndTransaction();
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskConnected(int i, long j, String str, String str2) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 3);
        contentValues.put("totalBytes", Long.valueOf(j));
        contentValues.put("eTag", str);
        if (!TextUtils.isEmpty(str2)) {
            contentValues.put("name", str2);
        }
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskProgress(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 4);
        contentValues.put("curBytes", Long.valueOf(j));
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskError(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) (-1));
        contentValues.put("curBytes", Long.valueOf(j));
        if (j > 0) {
            contentValues.put("isFirstDownload", (Integer) 0);
        }
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskRetry(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 5);
        contentValues.put("isFirstDownload", (Integer) 0);
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskCompleted(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) (-3));
        contentValues.put("curBytes", Long.valueOf(j));
        contentValues.put("isFirstDownload", (Integer) 0);
        contentValues.put("isFirstSuccess", (Integer) 0);
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskPause(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) (-2));
        contentValues.put("curBytes", Long.valueOf(j));
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskCancel(int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) (-4));
        contentValues.put("curBytes", Long.valueOf(j));
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskPrepare(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 1);
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public DownloadInfo OnDownloadTaskIntercept(int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) (-7));
        update(i, contentValues);
        return null;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public boolean isDownloadCacheSyncSuccess() {
        return this.cacheSynced;
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void syncDownloadInfoFromOtherCache(int i, List<DownloadChunk> list) {
        try {
            removeAllDownloadChunk(i);
            if (list != null) {
                for (DownloadChunk downloadChunk : list) {
                    if (downloadChunk != null) {
                        addDownloadChunk(downloadChunk);
                        if (downloadChunk.hasChunkDivided()) {
                            for (DownloadChunk downloadChunk2 : downloadChunk.getSubChunkList()) {
                                addDownloadChunk(downloadChunk2);
                            }
                        }
                    }
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public ArrayList<Segment> getSegments(int i) {
        Map<Long, Segment> segmentMap = getSegmentMap(i);
        if (segmentMap == null || segmentMap.isEmpty()) {
            return null;
        }
        return new ArrayList<>(segmentMap.values());
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public java.util.Map<java.lang.Long, com.p319ss.android.socialbase.downloader.segment.Segment> getSegmentMap(int r12) {
        /*
            r11 = this;
            r11.ensureDataBaseInit()
            android.database.sqlite.SQLiteDatabase r0 = com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.database
            r1 = 0
            if (r0 == 0) goto La3
            r0 = 0
            r2 = 1
            android.database.sqlite.SQLiteDatabase r3 = com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.database     // Catch: java.lang.Throwable -> L8c
            java.lang.String r4 = "SELECT * FROM %s WHERE %s = ?"
            r5 = 2
            java.lang.Object[] r5 = new java.lang.Object[r5]     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = "segments"
            r5[r0] = r6     // Catch: java.lang.Throwable -> L8c
            java.lang.String r6 = "_id"
            r5[r2] = r6     // Catch: java.lang.Throwable -> L8c
            java.lang.String r4 = java.lang.String.format(r4, r5)     // Catch: java.lang.Throwable -> L8c
            java.lang.String[] r5 = new java.lang.String[r2]     // Catch: java.lang.Throwable -> L8c
            java.lang.String r12 = java.lang.Integer.toString(r12)     // Catch: java.lang.Throwable -> L8c
            r5[r0] = r12     // Catch: java.lang.Throwable -> L8c
            boolean r12 = r3 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L8c
            if (r12 != 0) goto L2e
            android.database.Cursor r12 = r3.rawQuery(r4, r5)     // Catch: java.lang.Throwable -> L8c
            goto L34
        L2e:
            android.database.sqlite.SQLiteDatabase r3 = (android.database.sqlite.SQLiteDatabase) r3     // Catch: java.lang.Throwable -> L8c
            android.database.Cursor r12 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r3, r4, r5)     // Catch: java.lang.Throwable -> L8c
        L34:
            boolean r3 = r12.moveToNext()     // Catch: java.lang.Throwable -> L85
            if (r3 == 0) goto L78
            java.lang.String r3 = "info"
            int r3 = r12.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L85
            if (r3 < 0) goto L47
            java.lang.String r3 = r12.getString(r3)     // Catch: java.lang.Throwable -> L85
            goto L48
        L47:
            r3 = r1
        L48:
            java.util.HashMap r4 = new java.util.HashMap     // Catch: java.lang.Throwable -> L85
            r4.<init>()     // Catch: java.lang.Throwable -> L85
            org.json.JSONArray r5 = new org.json.JSONArray     // Catch: java.lang.Throwable -> L85
            r5.<init>(r3)     // Catch: java.lang.Throwable -> L85
            r3 = r0
        L53:
            int r6 = r5.length()     // Catch: java.lang.Throwable -> L85
            if (r3 >= r6) goto L70
            org.json.JSONObject r6 = r5.getJSONObject(r3)     // Catch: java.lang.Throwable -> L85
            com.ss.android.socialbase.downloader.segment.Segment r7 = new com.ss.android.socialbase.downloader.segment.Segment     // Catch: java.lang.Throwable -> L85
            r7.<init>(r6)     // Catch: java.lang.Throwable -> L85
            long r8 = r7.getStartOffset()     // Catch: java.lang.Throwable -> L85
            java.lang.Long r6 = java.lang.Long.valueOf(r8)     // Catch: java.lang.Throwable -> L85
            r4.put(r6, r7)     // Catch: java.lang.Throwable -> L85
            int r3 = r3 + 1
            goto L53
        L70:
            android.database.Cursor[] r1 = new android.database.Cursor[r2]
            r1[r0] = r12
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r1)
            return r4
        L78:
            android.database.Cursor[] r2 = new android.database.Cursor[r2]
            r2[r0] = r12
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r2)
            goto La3
        L80:
            r1 = move-exception
            r10 = r1
            r1 = r12
            r12 = r10
            goto L9b
        L85:
            r3 = move-exception
            r10 = r3
            r3 = r12
            r12 = r10
            goto L8e
        L8a:
            r12 = move-exception
            goto L9b
        L8c:
            r12 = move-exception
            r3 = r1
        L8e:
            r12.printStackTrace()     // Catch: java.lang.Throwable -> L99
            android.database.Cursor[] r12 = new android.database.Cursor[r2]
            r12[r0] = r3
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r12)
            goto La3
        L99:
            r12 = move-exception
            r1 = r3
        L9b:
            android.database.Cursor[] r2 = new android.database.Cursor[r2]
            r2[r0] = r1
            com.p319ss.android.socialbase.downloader.utils.DownloadUtils.safeClose(r2)
            throw r12
        La3:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.p342db.SqlDownloadCache.getSegmentMap(int):java.util.Map");
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public boolean updateSegments(int i, Map<Long, Segment> map) {
        long currentTimeMillis = System.currentTimeMillis();
        ensureDataBaseInit();
        if (database == null) {
            return false;
        }
        JSONArray jSONArray = new JSONArray();
        try {
            for (Long l : map.keySet()) {
                jSONArray.put(map.get(Long.valueOf(l.longValue())).toJson());
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
        Log.d("SqlDownloadCache", "json=" + jSONArray);
        SQLiteStatement insertOrReplaceStatement = this.segmentTableStatements.getInsertOrReplaceStatement();
        synchronized (insertOrReplaceStatement) {
            insertOrReplaceStatement.clearBindings();
            insertOrReplaceStatement.bindLong(1, i);
            insertOrReplaceStatement.bindString(2, !(jSONArray instanceof JSONArray) ? jSONArray.toString() : NBSJSONArrayInstrumentation.toString(jSONArray));
            insertOrReplaceStatement.execute();
        }
        Logger.m6475d("SqlDownloadCache", "updateSegments cost=" + DownloadUtils.cost(currentTimeMillis));
        return false;
    }

    @Override // com.p319ss.android.socialbase.downloader.downloader.IDownloadCache
    public void removeSegments(int i) {
        ensureDataBaseInit();
        if (database == null) {
            return;
        }
        try {
            deleteInner(i, this.segmentTableStatements.getDeleteStatement());
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void update(final int i, final ContentValues contentValues) {
        ensureDataBaseInit();
        if (database == null) {
            return;
        }
        DownloadComponentManager.submitDBTask(new Runnable() { // from class: com.ss.android.socialbase.downloader.db.SqlDownloadCache.11
            @Override // java.lang.Runnable
            public void run() {
                SqlDownloadCache.this.updateInner(i, contentValues);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateInner(int i, ContentValues contentValues) {
        int i2 = 10;
        while (database.isDbLockedByCurrentThread() && i2 - 1 >= 0) {
            try {
                Thread.sleep(5L);
            } catch (Throwable th) {
                th.printStackTrace();
                return;
            }
        }
        SQLiteDatabase sQLiteDatabase = database;
        String[] strArr = {String.valueOf(i)};
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.update(sQLiteDatabase, "downloader", contentValues, "_id = ? ", strArr);
        } else {
            sQLiteDatabase.update("downloader", contentValues, "_id = ? ", strArr);
        }
    }

    @Override // com.p319ss.android.socialbase.downloader.p342db.ISqlDownloadCacheAidl
    public void setInitCallback(ISqlCacheLoadCompleteCallbackAidl iSqlCacheLoadCompleteCallbackAidl) {
        this.callback = iSqlCacheLoadCompleteCallbackAidl;
    }

    public void onInitFinish(SparseArray<DownloadInfo> sparseArray, SparseArray<List<DownloadChunk>> sparseArray2) {
        try {
            HashMap sparseArrayToHashMap = DownloadUtils.sparseArrayToHashMap(sparseArray);
            HashMap sparseArrayToHashMap2 = DownloadUtils.sparseArrayToHashMap(sparseArray2);
            if (this.callback != null) {
                this.callback.callback(sparseArrayToHashMap, sparseArrayToHashMap2);
            }
        } catch (Throwable unused) {
        }
    }
}
