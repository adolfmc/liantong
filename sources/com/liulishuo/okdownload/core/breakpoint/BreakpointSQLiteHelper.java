package com.liulishuo.okdownload.core.breakpoint;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.support.annotation.NonNull;
import android.util.SparseArray;
import com.liulishuo.okdownload.core.exception.SQLiteException;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class BreakpointSQLiteHelper extends SQLiteOpenHelper {
    private static final String BLOCK_TABLE_NAME = "block";
    private static final String BREAKPOINT_TABLE_NAME = "breakpoint";
    private static final String NAME = "okdownload-breakpoint.db";
    private static final String RESPONSE_FILENAME_TABLE_NAME = "okdownloadResponseFilename";
    static final String TASK_FILE_DIRTY_TABLE_NAME = "taskFileDirty";
    private static final int VERSION = 3;

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public BreakpointSQLiteHelper(Context context) {
        super(context, NAME, (SQLiteDatabase.CursorFactory) null, 3);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onOpen(SQLiteDatabase sQLiteDatabase) {
        super.onOpen(sQLiteDatabase);
        if (Build.VERSION.SDK_INT >= 16) {
            setWriteAheadLoggingEnabled(true);
        } else {
            sQLiteDatabase.enableWriteAheadLogging();
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS breakpoint( id INTEGER PRIMARY KEY, url VARCHAR NOT NULL, etag VARCHAR, parent_path VARCHAR NOT NULL, filename VARCHAR, task_only_parent_path TINYINT(1) DEFAULT 0, chunked TINYINT(1) DEFAULT 0)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS breakpoint( id INTEGER PRIMARY KEY, url VARCHAR NOT NULL, etag VARCHAR, parent_path VARCHAR NOT NULL, filename VARCHAR, task_only_parent_path TINYINT(1) DEFAULT 0, chunked TINYINT(1) DEFAULT 0)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS block( id INTEGER PRIMARY KEY AUTOINCREMENT, breakpoint_id INTEGER, block_index INTEGER, start_offset INTEGER, content_length INTEGER, current_offset INTEGER)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS block( id INTEGER PRIMARY KEY AUTOINCREMENT, breakpoint_id INTEGER, block_index INTEGER, start_offset INTEGER, content_length INTEGER, current_offset INTEGER)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS okdownloadResponseFilename( url VARCHAR NOT NULL PRIMARY KEY, filename VARCHAR NOT NULL)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS okdownloadResponseFilename( url VARCHAR NOT NULL PRIMARY KEY, filename VARCHAR NOT NULL)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS taskFileDirty( id INTEGER PRIMARY KEY)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS taskFileDirty( id INTEGER PRIMARY KEY)");
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        if (i == 1 && i2 == 2) {
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS okdownloadResponseFilename( url VARCHAR NOT NULL PRIMARY KEY, filename VARCHAR NOT NULL)");
            } else {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS okdownloadResponseFilename( url VARCHAR NOT NULL PRIMARY KEY, filename VARCHAR NOT NULL)");
            }
        }
        if (i <= 2) {
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS taskFileDirty( id INTEGER PRIMARY KEY)");
            } else {
                sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS taskFileDirty( id INTEGER PRIMARY KEY)");
            }
        }
    }

    public void markFileDirty(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues(1);
        contentValues.put("id", Integer.valueOf(i));
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.insert(writableDatabase, TASK_FILE_DIRTY_TABLE_NAME, null, contentValues);
        } else {
            writableDatabase.insert(TASK_FILE_DIRTY_TABLE_NAME, null, contentValues);
        }
    }

    public void markFileClear(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = {String.valueOf(i)};
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.delete(writableDatabase, TASK_FILE_DIRTY_TABLE_NAME, "id = ?", strArr);
        } else {
            writableDatabase.delete(TASK_FILE_DIRTY_TABLE_NAME, "id = ?", strArr);
        }
    }

    public List<Integer> loadDirtyFileList() {
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("SELECT * FROM taskFileDirty", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "SELECT * FROM taskFileDirty", null);
            while (cursor.moveToNext()) {
                arrayList.add(Integer.valueOf(cursor.getInt(cursor.getColumnIndex("id"))));
            }
            return arrayList;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public SparseArray<BreakpointInfo> loadToCache() {
        Cursor cursor;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ArrayList<BreakpointInfoRow> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        Cursor cursor2 = null;
        try {
            cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("SELECT * FROM breakpoint", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "SELECT * FROM breakpoint", null);
            while (cursor.moveToNext()) {
                try {
                    arrayList.add(new BreakpointInfoRow(cursor));
                } catch (Throwable th) {
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    if (cursor2 != null) {
                        cursor2.close();
                    }
                    throw th;
                }
            }
            cursor2 = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("SELECT * FROM block", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "SELECT * FROM block", null);
            while (cursor2.moveToNext()) {
                arrayList2.add(new BlockInfoRow(cursor2));
            }
            if (cursor != null) {
                cursor.close();
            }
            if (cursor2 != null) {
                cursor2.close();
            }
            SparseArray<BreakpointInfo> sparseArray = new SparseArray<>();
            for (BreakpointInfoRow breakpointInfoRow : arrayList) {
                BreakpointInfo info = breakpointInfoRow.toInfo();
                Iterator it = arrayList2.iterator();
                while (it.hasNext()) {
                    BlockInfoRow blockInfoRow = (BlockInfoRow) it.next();
                    if (blockInfoRow.getBreakpointId() == info.f12214id) {
                        info.addBlock(blockInfoRow.toInfo());
                        it.remove();
                    }
                }
                sparseArray.put(info.f12214id, info);
            }
            return sparseArray;
        } catch (Throwable th2) {
            th = th2;
            cursor = null;
        }
    }

    public HashMap<String, String> loadResponseFilenameToMap() {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        HashMap<String, String> hashMap = new HashMap<>();
        Cursor cursor = null;
        try {
            cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("SELECT * FROM okdownloadResponseFilename", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "SELECT * FROM okdownloadResponseFilename", null);
            while (cursor.moveToNext()) {
                hashMap.put(cursor.getString(cursor.getColumnIndex("url")), cursor.getString(cursor.getColumnIndex("filename")));
            }
            return hashMap;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    public void updateFilename(@NonNull String str, @NonNull String str2) {
        Throwable th;
        Cursor cursor;
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues(2);
        contentValues.put("url", str);
        contentValues.put("filename", str2);
        synchronized (str.intern()) {
            try {
                String[] strArr = {str};
                cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("SELECT filename FROM okdownloadResponseFilename WHERE url = ?", strArr) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "SELECT filename FROM okdownloadResponseFilename WHERE url = ?", strArr);
                try {
                    if (cursor.moveToFirst()) {
                        if (!str2.equals(cursor.getString(cursor.getColumnIndex("filename")))) {
                            if (writableDatabase instanceof SQLiteDatabase) {
                                NBSSQLiteInstrumentation.replace(writableDatabase, RESPONSE_FILENAME_TABLE_NAME, null, contentValues);
                            } else {
                                writableDatabase.replace(RESPONSE_FILENAME_TABLE_NAME, null, contentValues);
                            }
                        }
                    } else if (writableDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.insert(writableDatabase, RESPONSE_FILENAME_TABLE_NAME, null, contentValues);
                    } else {
                        writableDatabase.insert(RESPONSE_FILENAME_TABLE_NAME, null, contentValues);
                    }
                    if (cursor != null) {
                        cursor.close();
                    }
                } catch (Throwable th2) {
                    th = th2;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            } catch (Throwable th3) {
                th = th3;
                cursor = null;
            }
        }
    }

    public void insert(@NonNull BreakpointInfo breakpointInfo) throws IOException {
        int blockCount = breakpointInfo.getBlockCount();
        SQLiteDatabase writableDatabase = getWritableDatabase();
        for (int i = 0; i < blockCount; i++) {
            BlockInfo block = breakpointInfo.getBlock(i);
            ContentValues values = toValues(breakpointInfo.f12214id, i, block);
            if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insert(BLOCK_TABLE_NAME, null, values) : NBSSQLiteInstrumentation.insert(writableDatabase, BLOCK_TABLE_NAME, null, values)) == -1) {
                throw new SQLiteException("insert block " + block + " failed!");
            }
        }
        ContentValues values2 = toValues(breakpointInfo);
        if ((!(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.insert(BREAKPOINT_TABLE_NAME, null, values2) : NBSSQLiteInstrumentation.insert(writableDatabase, BREAKPOINT_TABLE_NAME, null, values2)) != -1) {
            return;
        }
        throw new SQLiteException("insert info " + breakpointInfo + " failed!");
    }

    public void updateBlockIncrease(@NonNull BreakpointInfo breakpointInfo, int i, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("current_offset", Long.valueOf(j));
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = {Integer.toString(breakpointInfo.f12214id), Integer.toString(i)};
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.update(writableDatabase, BLOCK_TABLE_NAME, contentValues, "breakpoint_id = ? AND block_index = ?", strArr);
        } else {
            writableDatabase.update(BLOCK_TABLE_NAME, contentValues, "breakpoint_id = ? AND block_index = ?", strArr);
        }
    }

    public void updateInfo(@NonNull BreakpointInfo breakpointInfo) throws IOException {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        writableDatabase.beginTransaction();
        Cursor cursor = null;
        try {
            SQLiteDatabase writableDatabase2 = getWritableDatabase();
            String str = "SELECT id FROM breakpoint WHERE id =" + breakpointInfo.f12214id + " LIMIT 1";
            cursor = !(writableDatabase2 instanceof SQLiteDatabase) ? writableDatabase2.rawQuery(str, null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase2, str, null);
            if (cursor.moveToNext()) {
                removeInfo(breakpointInfo.f12214id);
                insert(breakpointInfo);
                writableDatabase.setTransactionSuccessful();
                if (cursor != null) {
                    cursor.close();
                }
                writableDatabase.endTransaction();
            }
        } finally {
            if (cursor != null) {
                cursor.close();
            }
            writableDatabase.endTransaction();
        }
    }

    public void removeInfo(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = {String.valueOf(i)};
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.delete(writableDatabase, BREAKPOINT_TABLE_NAME, "id = ?", strArr);
        } else {
            writableDatabase.delete(BREAKPOINT_TABLE_NAME, "id = ?", strArr);
        }
        removeBlock(i);
    }

    public void removeBlock(int i) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        String[] strArr = {String.valueOf(i)};
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.delete(writableDatabase, BLOCK_TABLE_NAME, "breakpoint_id = ?", strArr);
        } else {
            writableDatabase.delete(BLOCK_TABLE_NAME, "breakpoint_id = ?", strArr);
        }
    }

    private static ContentValues toValues(@NonNull BreakpointInfo breakpointInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", Integer.valueOf(breakpointInfo.f12214id));
        contentValues.put("url", breakpointInfo.getUrl());
        contentValues.put("etag", breakpointInfo.getEtag());
        contentValues.put("parent_path", breakpointInfo.parentFile.getAbsolutePath());
        contentValues.put("filename", breakpointInfo.getFilename());
        contentValues.put("task_only_parent_path", Integer.valueOf(breakpointInfo.isTaskOnlyProvidedParentPath() ? 1 : 0));
        contentValues.put("chunked", Integer.valueOf(breakpointInfo.isChunked() ? 1 : 0));
        return contentValues;
    }

    private static ContentValues toValues(int i, int i2, @NonNull BlockInfo blockInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("breakpoint_id", Integer.valueOf(i));
        contentValues.put("block_index", Integer.valueOf(i2));
        contentValues.put("start_offset", Long.valueOf(blockInfo.getStartOffset()));
        contentValues.put("content_length", Long.valueOf(blockInfo.getContentLength()));
        contentValues.put("current_offset", Long.valueOf(blockInfo.getCurrentOffset()));
        return contentValues;
    }
}
