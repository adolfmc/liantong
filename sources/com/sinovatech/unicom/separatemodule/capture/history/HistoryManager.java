package com.sinovatech.unicom.separatemodule.capture.history;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.Result;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class HistoryManager {
    private static final int MAX_ITEMS = 2000;
    private static final String TAG = "HistoryManager";
    private final Activity activity;
    private static final String[] COLUMNS = {"text", "display", "format", "timestamp", "details"};
    private static final String[] COUNT_COLUMN = {"COUNT(1)"};
    private static final String[] ID_COL_PROJECTION = {"id"};
    private static final String[] ID_DETAIL_COL_PROJECTION = {"id", "details"};
    private static final DateFormat EXPORT_DATE_TIME_FORMAT = DateFormat.getDateTimeInstance(2, 2);

    public HistoryManager(Activity activity) {
        this.activity = activity;
    }

    public boolean hasHistoryItems() {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getReadableDatabase();
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            String[] strArr = COUNT_COLUMN;
            cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("history", strArr, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "history", strArr, null, null, null, null, null);
            cursor.moveToFirst();
            boolean z = cursor.getInt(0) > 0;
            close(cursor, sQLiteDatabase);
            return z;
        } catch (Throwable th2) {
            th = th2;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    public List<HistoryItem> buildHistoryItems() {
        SQLiteDatabase sQLiteDatabase;
        DBHelper dBHelper = new DBHelper(this.activity);
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            sQLiteDatabase = dBHelper.getReadableDatabase();
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            String[] strArr = COLUMNS;
            cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("history", strArr, null, null, null, null, "timestamp DESC") : NBSSQLiteInstrumentation.query(sQLiteDatabase, "history", strArr, null, null, null, null, "timestamp DESC");
            while (cursor.moveToNext()) {
                String string = cursor.getString(0);
                String string2 = cursor.getString(1);
                cursor.getString(2);
                arrayList.add(new HistoryItem(new Result(string, null, null, null, cursor.getLong(3)), string2, cursor.getString(4)));
            }
            close(cursor, sQLiteDatabase);
            return arrayList;
        } catch (Throwable th2) {
            th = th2;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    public HistoryItem buildHistoryItem(int i) {
        SQLiteDatabase sQLiteDatabase;
        Cursor cursor = null;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getReadableDatabase();
            try {
                String[] strArr = COLUMNS;
                cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("history", strArr, null, null, null, null, "timestamp DESC") : NBSSQLiteInstrumentation.query(sQLiteDatabase, "history", strArr, null, null, null, null, "timestamp DESC");
                cursor.move(i + 1);
                String string = cursor.getString(0);
                HistoryItem historyItem = new HistoryItem(new Result(string, null, null, BarcodeFormat.valueOf(cursor.getString(2)), cursor.getLong(3)), cursor.getString(1), cursor.getString(4));
                close(cursor, sQLiteDatabase);
                return historyItem;
            } catch (Throwable th) {
                th = th;
                close(cursor, sQLiteDatabase);
                throw th;
            }
        } catch (Throwable th2) {
            th = th2;
            sQLiteDatabase = null;
        }
    }

    public void deleteAllHistoryItems() {
        SQLiteDatabase sQLiteDatabase;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.delete(sQLiteDatabase, "history", null, null);
            } else {
                sQLiteDatabase.delete("history", null, null);
            }
            close(null, sQLiteDatabase);
        } catch (Throwable th2) {
            th = th2;
            close(null, sQLiteDatabase);
            throw th;
        }
    }

    public void deleteHistoryItem(String str) {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                String[] strArr = ID_COL_PROJECTION;
                String[] strArr2 = {str};
                cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("history", strArr, "text=?", strArr2, null, null, "timestamp DESC") : NBSSQLiteInstrumentation.query(sQLiteDatabase, "history", strArr, "text=?", strArr2, null, null, "timestamp DESC");
            } catch (Throwable th2) {
                th = th2;
                cursor = null;
            }
        } catch (Throwable th3) {
            sQLiteDatabase = null;
            th = th3;
            cursor = null;
        }
        try {
            if (cursor.moveToNext()) {
                String str2 = "id=" + cursor.getString(0);
                if (sQLiteDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.delete(sQLiteDatabase, "history", str2, null);
                } else {
                    sQLiteDatabase.delete("history", str2, null);
                }
            }
            close(cursor, sQLiteDatabase);
        } catch (Throwable th4) {
            th = th4;
            close(cursor, sQLiteDatabase);
            throw th;
        }
    }

    public void addHistoryItem(String str) {
        SQLiteDatabase sQLiteDatabase;
        ContentValues contentValues = new ContentValues();
        contentValues.put("text", str);
        contentValues.put("format", "");
        contentValues.put("display", str);
        contentValues.put("timestamp", Long.valueOf(System.currentTimeMillis()));
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
        } catch (Throwable th) {
            th = th;
            sQLiteDatabase = null;
        }
        try {
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.insert(sQLiteDatabase, "history", "timestamp", contentValues);
            } else {
                sQLiteDatabase.insert("history", "timestamp", contentValues);
            }
            close(null, sQLiteDatabase);
        } catch (Throwable th2) {
            th = th2;
            close(null, sQLiteDatabase);
            throw th;
        }
    }

    public void trimHistory() {
        SQLiteDatabase sQLiteDatabase;
        Throwable th;
        Cursor cursor;
        try {
            sQLiteDatabase = new DBHelper(this.activity).getWritableDatabase();
            try {
                String[] strArr = ID_COL_PROJECTION;
                cursor = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("history", strArr, null, null, null, null, "timestamp DESC") : NBSSQLiteInstrumentation.query(sQLiteDatabase, "history", strArr, null, null, null, null, "timestamp DESC");
                try {
                    cursor.move(2000);
                    while (cursor.moveToNext()) {
                        String str = "id=" + cursor.getString(0);
                        if (sQLiteDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.delete(sQLiteDatabase, "history", str, null);
                        } else {
                            sQLiteDatabase.delete("history", str, null);
                        }
                    }
                } catch (SQLiteException unused) {
                } catch (Throwable th2) {
                    th = th2;
                    close(cursor, sQLiteDatabase);
                    throw th;
                }
            } catch (SQLiteException unused2) {
                cursor = null;
            } catch (Throwable th3) {
                cursor = null;
                th = th3;
            }
        } catch (SQLiteException unused3) {
            sQLiteDatabase = null;
            cursor = null;
        } catch (Throwable th4) {
            sQLiteDatabase = null;
            th = th4;
            cursor = null;
        }
        close(cursor, sQLiteDatabase);
    }

    private static String massageHistoryField(String str) {
        return str == null ? "" : str.replace("\"", "\"\"");
    }

    private static void close(Cursor cursor, SQLiteDatabase sQLiteDatabase) {
        if (cursor != null) {
            cursor.close();
        }
        if (sQLiteDatabase != null) {
            sQLiteDatabase.close();
        }
    }
}
