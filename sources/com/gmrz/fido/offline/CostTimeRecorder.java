package com.gmrz.fido.offline;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.gmrz.appsdk.debug.api.Auto;
import com.gmrz.appsdk.debug.api.Manual;
import com.gmrz.appsdk.debug.api.Type;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;

@NBSInstrumented
/* renamed from: com.gmrz.fido.offline.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CostTimeRecorder {

    /* renamed from: c */
    private static CostTimeRecorder f10359c;

    /* renamed from: a */
    private final SQLiteDatabase f10360a;

    /* renamed from: b */
    private Type f10361b;

    private CostTimeRecorder(Context context) {
        this.f10360a = new CostTimeDBHelper(context).getWritableDatabase();
    }

    /* renamed from: a */
    public static CostTimeRecorder m15745a(Context context) {
        if (f10359c == null) {
            f10359c = new CostTimeRecorder(context);
        }
        return f10359c;
    }

    /* renamed from: b */
    public void m15740b(long j, String str) {
        Type type = this.f10361b;
        if (type != null) {
            String str2 = "";
            if (type.equals(Type.AUTO)) {
                str2 = "auto";
            } else if (this.f10361b.equals(Type.MANUAL)) {
                str2 = "manual";
            }
            m15741a(str2, j, str);
            return;
        }
        throw new IllegalArgumentException("cost time recorder need set work mode, current work mode is null");
    }

    /* renamed from: a */
    public void m15746a(long j, String str) {
        Type type = this.f10361b;
        if (type != null) {
            if (type.equals(Type.AUTO)) {
                Auto auto = new Auto();
                auto.m15800a(str);
                auto.m15797b(Type.DISCOVERY.toString());
                auto.m15793d(j);
                m15743a(auto, -1);
                return;
            }
            return;
        }
        throw new IllegalArgumentException("cost time recorder need set work mode, current work mode is null");
    }

    /* renamed from: a */
    public void m15744a(Type type) {
        this.f10361b = type;
    }

    /* renamed from: a */
    private Object[] m15742a(String str) {
        Object[] objArr = new Object[2];
        String str2 = "select * from " + str + " order by ID desc limit 0,1;";
        SQLiteDatabase sQLiteDatabase = this.f10360a;
        Cursor rawQuery = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.rawQuery(str2, null) : NBSSQLiteInstrumentation.rawQuery(sQLiteDatabase, str2, null);
        if (!rawQuery.moveToFirst()) {
            rawQuery.close();
            return null;
        }
        rawQuery.moveToFirst();
        if (str.equals("auto")) {
            Auto auto = new Auto();
            auto.m15797b(rawQuery.getString(rawQuery.getColumnIndex("OP_TYPE")));
            auto.m15800a(rawQuery.getString(rawQuery.getColumnIndex("CLIENT_TYPE")));
            auto.m15801a(rawQuery.getLong(rawQuery.getColumnIndex("NET_FIRST")));
            auto.m15793d(rawQuery.getLong(rawQuery.getColumnIndex("SDK_DISCOVERY")));
            auto.m15791e(rawQuery.getLong(rawQuery.getColumnIndex("SDK_OPERATION")));
            auto.m15795c(rawQuery.getLong(rawQuery.getColumnIndex("PROCESS_IN_SDK")));
            auto.m15798b(rawQuery.getLong(rawQuery.getColumnIndex("NET_SECOND")));
            auto.m15789f(rawQuery.getLong(rawQuery.getColumnIndex("TOTAL")));
            objArr[0] = Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("ID")));
            objArr[1] = auto;
        } else if (str.equals("manual")) {
            Manual manual = new Manual();
            manual.m15781b(rawQuery.getString(rawQuery.getColumnIndex("OP_TYPE")));
            manual.m15784a(rawQuery.getString(rawQuery.getColumnIndex("CLIENT_TYPE")));
            manual.m15785a(rawQuery.getLong(rawQuery.getColumnIndex("NET_FIRST")));
            manual.m15777d(rawQuery.getLong(rawQuery.getColumnIndex("SDK_DISCOVERY")));
            manual.m15775e(rawQuery.getLong(rawQuery.getColumnIndex("SDK_OPERATION")));
            manual.m15779c(rawQuery.getLong(rawQuery.getColumnIndex("PROCESS_IN_SDK")));
            manual.m15782b(rawQuery.getLong(rawQuery.getColumnIndex("NET_SECOND")));
            manual.m15773f(rawQuery.getLong(rawQuery.getColumnIndex("TOTAL")));
            objArr[0] = Integer.valueOf(rawQuery.getInt(rawQuery.getColumnIndex("ID")));
            objArr[1] = manual;
        }
        if (!rawQuery.isClosed()) {
            rawQuery.close();
        }
        return objArr;
    }

    /* renamed from: a */
    private void m15743a(Object obj, int i) {
        String str = "";
        ContentValues contentValues = new ContentValues();
        if (obj instanceof Auto) {
            str = "auto";
            Auto auto = (Auto) obj;
            contentValues.put("OP_TYPE", auto.m15794d());
            contentValues.put("CLIENT_TYPE", auto.m15802a());
            contentValues.put("NET_FIRST", Long.valueOf(auto.m15799b()));
            contentValues.put("SDK_DISCOVERY", Long.valueOf(auto.m15790f()));
            contentValues.put("SDK_OPERATION", Long.valueOf(auto.m15788g()));
            contentValues.put("PROCESS_IN_SDK", Long.valueOf(auto.m15792e()));
            contentValues.put("NET_SECOND", Long.valueOf(auto.m15796c()));
            contentValues.put("TOTAL", Long.valueOf(auto.m15787h()));
        } else if (obj instanceof Manual) {
            str = "manual";
            Manual manual = (Manual) obj;
            contentValues.put("OP_TYPE", manual.m15778d());
            contentValues.put("CLIENT_TYPE", manual.m15786a());
            contentValues.put("NET_FIRST", Long.valueOf(manual.m15783b()));
            contentValues.put("SDK_DISCOVERY", Long.valueOf(manual.m15774f()));
            contentValues.put("SDK_OPERATION", Long.valueOf(manual.m15772g()));
            contentValues.put("PROCESS_IN_SDK", Long.valueOf(manual.m15776e()));
            contentValues.put("NET_SECOND", Long.valueOf(manual.m15780c()));
            contentValues.put("TOTAL", Long.valueOf(manual.m15771h()));
        }
        if (i >= 0) {
            SQLiteDatabase sQLiteDatabase = this.f10360a;
            String[] strArr = {String.valueOf(i)};
            if (sQLiteDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.update(sQLiteDatabase, str, contentValues, "ID=?", strArr);
                return;
            } else {
                sQLiteDatabase.update(str, contentValues, "ID=?", strArr);
                return;
            }
        }
        SQLiteDatabase sQLiteDatabase2 = this.f10360a;
        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.insert(sQLiteDatabase2, str, null, contentValues);
        } else {
            sQLiteDatabase2.insert(str, null, contentValues);
        }
    }

    /* renamed from: a */
    private void m15741a(String str, long j, String str2) {
        if (str.equals("auto")) {
            Object[] m15742a = m15742a("auto");
            if (m15742a == null) {
                return;
            }
            Auto auto = (Auto) m15742a[1];
            auto.m15800a(str2);
            auto.m15791e(j);
            m15743a(auto, Integer.valueOf(m15742a[0].toString()).intValue());
        } else if (str.equals("manual")) {
            Manual manual = new Manual();
            manual.m15784a(str2);
            manual.m15775e(j);
            m15743a(manual, -1);
        }
    }
}
