package com.gmrz.fido.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.gmrz.appsdk.recorder.api.Record;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@NBSInstrumented
/* renamed from: com.gmrz.fido.offline.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RecordUtil {

    /* renamed from: a */
    private SQLiteDatabase f10362a;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: RecordUtil.java */
    /* renamed from: com.gmrz.fido.offline.f$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    static class C4437b {

        /* renamed from: a */
        private static final RecordUtil f10363a = new RecordUtil();
    }

    /* renamed from: a */
    public static RecordUtil m15733a(Context context) {
        C4437b.f10363a.f10362a = new ExcDBHelper(context).getWritableDatabase();
        return C4437b.f10363a;
    }

    /* renamed from: b */
    public List<List<Record>> m15730b() {
        SQLiteDatabase sQLiteDatabase = this.f10362a;
        if (sQLiteDatabase != null) {
            String[] strArr = {"SN"};
            Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("sdkrecord", strArr, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "sdkrecord", strArr, null, null, null, null, null);
            HashSet hashSet = new HashSet();
            while (query.moveToNext()) {
                hashSet.add(query.getString(query.getColumnIndex("SN")));
            }
            query.close();
            if (hashSet.size() == 0) {
                return null;
            }
            ArrayList arrayList = new ArrayList();
            Iterator it = hashSet.iterator();
            while (it.hasNext()) {
                ArrayList arrayList2 = new ArrayList();
                SQLiteDatabase sQLiteDatabase2 = this.f10362a;
                String[] strArr2 = {(String) it.next()};
                Cursor query2 = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query("sdkrecord", null, "SN=?", strArr2, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase2, "sdkrecord", null, "SN=?", strArr2, null, null, null);
                while (query2.moveToNext()) {
                    Record record = new Record();
                    record.f10345a = query2.getLong(query2.getColumnIndex("TIMESTAMP"));
                    record.f10346b = Record.OPERATION.valueOf(query2.getString(query2.getColumnIndex("OPERATION")));
                    record.f10348d = query2.getString(query2.getColumnIndex("DESCRIPTION"));
                    record.f10349e = query2.getString(query2.getColumnIndex("MESSAGE"));
                    String string = query2.getString(query2.getColumnIndex("EXC_TYPE"));
                    if (!TextUtils.isEmpty(string)) {
                        record.f10347c = Record.ExcType.valueOf(string);
                    }
                    arrayList2.add(record);
                }
                query2.close();
                arrayList.add(arrayList2);
            }
            return arrayList;
        }
        throw new IllegalArgumentException("database instance is null");
    }

    /* renamed from: c */
    public List<Record> m15729c() {
        SQLiteDatabase sQLiteDatabase = this.f10362a;
        if (sQLiteDatabase != null) {
            Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery("select SN from sdkrecord order by ID desc limit 0,1;", null) : NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, "select SN from sdkrecord order by ID desc limit 0,1;", null);
            if (!rawQuery.moveToFirst()) {
                rawQuery.close();
                return null;
            }
            rawQuery.moveToFirst();
            String string = rawQuery.getString(rawQuery.getColumnIndex("SN"));
            if (!rawQuery.isClosed()) {
                rawQuery.close();
            }
            SQLiteDatabase sQLiteDatabase2 = this.f10362a;
            String[] strArr = {string};
            Cursor query = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query("sdkrecord", null, "SN=?", strArr, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase2, "sdkrecord", null, "SN=?", strArr, null, null, null);
            ArrayList arrayList = new ArrayList();
            while (query.moveToNext()) {
                Record record = new Record();
                record.f10345a = query.getLong(query.getColumnIndex("TIMESTAMP"));
                record.f10346b = Record.OPERATION.valueOf(query.getString(query.getColumnIndex("OPERATION")));
                record.f10348d = query.getString(query.getColumnIndex("DESCRIPTION"));
                record.f10349e = query.getString(query.getColumnIndex("MESSAGE"));
                String string2 = query.getString(query.getColumnIndex("EXC_TYPE"));
                if (!TextUtils.isEmpty(string2)) {
                    record.f10347c = Record.ExcType.valueOf(string2);
                }
                arrayList.add(record);
            }
            query.close();
            return arrayList;
        }
        throw new IllegalArgumentException("database instance is null");
    }

    private RecordUtil() {
    }

    /* renamed from: a */
    public void m15731a(Record record) {
        if (this.f10362a == null) {
            throw new IllegalArgumentException("database instance is null");
        }
        if (record != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("TIMESTAMP", Long.valueOf(System.currentTimeMillis()));
            contentValues.put("OPERATION", record.f10346b.toString());
            contentValues.put("DESCRIPTION", record.f10348d);
            contentValues.put("SN", record.f10350f);
            Record.ExcType excType = record.f10347c;
            if (excType != null) {
                contentValues.put("EXC_TYPE", excType.toString());
            }
            String str = record.f10349e;
            if (str != null) {
                contentValues.put("MESSAGE", str);
            }
            SQLiteDatabase sQLiteDatabase = this.f10362a;
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.insert(sQLiteDatabase, "sdkrecord", null, contentValues);
            } else {
                sQLiteDatabase.insert("sdkrecord", null, contentValues);
            }
            m15732a(this.f10362a, "sdkrecord", "SN", 200);
            return;
        }
        throw new IllegalArgumentException("can not save empty record data");
    }

    /* renamed from: a */
    public void m15734a() {
        SQLiteDatabase sQLiteDatabase = this.f10362a;
        if (sQLiteDatabase != null) {
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "delete from sdkrecord");
                return;
            } else {
                sQLiteDatabase.execSQL("delete from sdkrecord");
                return;
            }
        }
        throw new IllegalArgumentException("database instance is null");
    }

    /* renamed from: a */
    private void m15732a(SQLiteDatabase sQLiteDatabase, String str, String str2, int i) {
        String str3 = "select count(*) from " + str;
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        Cursor rawQuery = !z ? sQLiteDatabase.rawQuery(str3, null) : NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, str3, null);
        long j = rawQuery.moveToFirst() ? rawQuery.getLong(0) : 0L;
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        if (j >= i) {
            String str4 = "select " + str2 + " from " + str + " order by ID desc limit 0,1;";
            Cursor rawQuery2 = !z ? sQLiteDatabase.rawQuery(str4, null) : NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, str4, null);
            rawQuery2.moveToFirst();
            rawQuery2.getString(rawQuery2.getColumnIndex(str2));
            if (!rawQuery2.isClosed()) {
                rawQuery2.close();
            }
            m15734a();
        }
    }
}
