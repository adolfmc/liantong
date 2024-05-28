package com.xiaomi.push.providers;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;

@NBSInstrumented
/* renamed from: com.xiaomi.push.providers.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11477a extends SQLiteOpenHelper {

    /* renamed from: a */
    private static int f23368a = 1;

    /* renamed from: a */
    public static final Object f23369a = new Object();

    /* renamed from: a */
    private static final String[] f23370a = {"package_name", "TEXT", "message_ts", " LONG DEFAULT 0 ", "bytes", " LONG DEFAULT 0 ", "network_type", " INT DEFAULT -1 ", "rcv", " INT DEFAULT -1 ", "imsi", "TEXT"};

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    public C11477a(Context context) {
        super(context, "traffic.db", (SQLiteDatabase.CursorFactory) null, f23368a);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        synchronized (f23369a) {
            try {
                m2937a(sQLiteDatabase);
            } catch (SQLException e) {
                AbstractC11049b.m5276a(e);
            }
        }
    }

    /* renamed from: a */
    private void m2937a(SQLiteDatabase sQLiteDatabase) {
        StringBuilder sb = new StringBuilder("CREATE TABLE traffic(_id INTEGER  PRIMARY KEY ,");
        for (int i = 0; i < f23370a.length - 1; i += 2) {
            if (i != 0) {
                sb.append(",");
            }
            sb.append(f23370a[i]);
            sb.append(" ");
            sb.append(f23370a[i + 1]);
        }
        sb.append(");");
        String sb2 = sb.toString();
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, sb2);
        } else {
            sQLiteDatabase.execSQL(sb2);
        }
    }
}
