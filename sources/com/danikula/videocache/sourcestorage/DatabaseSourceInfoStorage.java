package com.danikula.videocache.sourcestorage;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.danikula.videocache.Preconditions;
import com.danikula.videocache.SourceInfo;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

@NBSInstrumented
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class DatabaseSourceInfoStorage extends SQLiteOpenHelper implements SourceInfoStorage {
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_MIME = "mime";
    private static final String COLUMN_URL = "url";
    private static final String CREATE_SQL = "CREATE TABLE SourceInfo (_id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,url TEXT NOT NULL,mime TEXT,length INTEGER);";
    private static final String TABLE = "SourceInfo";
    private static final String COLUMN_LENGTH = "length";
    private static final String[] ALL_COLUMNS = {"_id", "url", COLUMN_LENGTH, "mime"};

    /* JADX INFO: Access modifiers changed from: package-private */
    public DatabaseSourceInfoStorage(Context context) {
        super(context, "AndroidVideoCache.db", (SQLiteDatabase.CursorFactory) null, 1);
        Preconditions.checkNotNull(context);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        Preconditions.checkNotNull(sQLiteDatabase);
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, CREATE_SQL);
        } else {
            sQLiteDatabase.execSQL(CREATE_SQL);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        throw new IllegalStateException("Should not be called. There is no any migration");
    }

    @Override // com.danikula.videocache.sourcestorage.SourceInfoStorage
    public SourceInfo get(String str) {
        Preconditions.checkNotNull(str);
        Cursor cursor = null;
        r0 = null;
        SourceInfo convert = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            String[] strArr = ALL_COLUMNS;
            String[] strArr2 = {str};
            Cursor query = !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.query(TABLE, strArr, "url=?", strArr2, null, null, null) : NBSSQLiteInstrumentation.query(readableDatabase, TABLE, strArr, "url=?", strArr2, null, null, null);
            if (query != null) {
                try {
                    if (query.moveToFirst()) {
                        convert = convert(query);
                    }
                } catch (Throwable th) {
                    cursor = query;
                    th = th;
                    if (cursor != null) {
                        cursor.close();
                    }
                    throw th;
                }
            }
            if (query != null) {
                query.close();
            }
            return convert;
        } catch (Throwable th2) {
            th = th2;
        }
    }

    @Override // com.danikula.videocache.sourcestorage.SourceInfoStorage
    public void put(String str, SourceInfo sourceInfo) {
        Preconditions.checkAllNotNull(str, sourceInfo);
        boolean z = get(str) != null;
        ContentValues convert = convert(sourceInfo);
        if (z) {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            String[] strArr = {str};
            if (writableDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.update(writableDatabase, TABLE, convert, "url=?", strArr);
                return;
            } else {
                writableDatabase.update(TABLE, convert, "url=?", strArr);
                return;
            }
        }
        SQLiteDatabase writableDatabase2 = getWritableDatabase();
        if (writableDatabase2 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.insert(writableDatabase2, TABLE, null, convert);
        } else {
            writableDatabase2.insert(TABLE, null, convert);
        }
    }

    @Override // com.danikula.videocache.sourcestorage.SourceInfoStorage
    public void release() {
        close();
    }

    private SourceInfo convert(Cursor cursor) {
        return new SourceInfo(cursor.getString(cursor.getColumnIndexOrThrow("url")), cursor.getLong(cursor.getColumnIndexOrThrow(COLUMN_LENGTH)), cursor.getString(cursor.getColumnIndexOrThrow("mime")));
    }

    private ContentValues convert(SourceInfo sourceInfo) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("url", sourceInfo.url);
        contentValues.put(COLUMN_LENGTH, Long.valueOf(sourceInfo.length));
        contentValues.put("mime", sourceInfo.mime);
        return contentValues;
    }
}
