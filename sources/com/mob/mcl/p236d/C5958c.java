package com.mob.mcl.p236d;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.lang.reflect.Method;

@NBSInstrumented
/* renamed from: com.mob.mcl.d.c */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5958c {

    /* renamed from: a */
    private C5956a f14671a;

    public C5958c(Context context) {
        this.f14671a = new C5956a(context.getApplicationContext());
    }

    /* renamed from: a */
    public void m11951a(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = this.f14671a.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("workId", str);
            contentValues.put("expireTime", Long.valueOf(j));
            if (writableDatabase instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.replace(writableDatabase, "msg", null, contentValues);
            } else {
                writableDatabase.replace("msg", null, contentValues);
            }
            writableDatabase.close();
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
    }

    /* renamed from: a */
    public long m11952a(String str) {
        try {
            SQLiteDatabase readableDatabase = this.f14671a.getReadableDatabase();
            Cursor cursor = (Cursor) SQLiteDatabase.class.getMethod("rawQuery", String.class, String[].class).invoke(readableDatabase, "select expireTime from msg where workId = ?", new String[]{str});
            if (cursor.moveToFirst()) {
                return cursor.getLong(cursor.getColumnIndex("expireTime"));
            }
            if (cursor != null) {
                cursor.close();
            }
            readableDatabase.close();
            return 0L;
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
            return 0L;
        }
    }

    /* renamed from: a */
    public void m11953a() {
        try {
            SQLiteDatabase writableDatabase = this.f14671a.getWritableDatabase();
            Method declaredMethod = SQLiteDatabase.class.getDeclaredMethod("execSQL", String.class, Object[].class);
            declaredMethod.invoke(writableDatabase, "delete from msg where expireTime < ?", new String[]{System.currentTimeMillis() + ""});
            writableDatabase.close();
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
    }

    /* renamed from: b */
    public void m11950b(String str) {
        try {
            SQLiteDatabase writableDatabase = this.f14671a.getWritableDatabase();
            SQLiteDatabase.class.getDeclaredMethod("execSQL", String.class, Object[].class).invoke(writableDatabase, "delete from msg where workId = ?", new String[]{str});
            writableDatabase.close();
        } catch (Throwable th) {
            C5957b.m11958a().m11955a(th);
        }
    }
}
