package p393h;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.unicom.pay.qpay.open.bean.WPQPayUserInfoBean;

@NBSInstrumented
/* renamed from: h.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C12014f extends SQLiteOpenHelper {
    public C12014f(Context context) {
        super(context, "QPay.db", (SQLiteDatabase.CursorFactory) null, 4);
    }

    /* renamed from: a */
    public final WPQPayUserInfoBean m1981a(String str) {
        SQLiteDatabase readableDatabase = getReadableDatabase();
        String[] strArr = {str};
        Cursor rawQuery = !(readableDatabase instanceof SQLiteDatabase) ? readableDatabase.rawQuery("SELECT * FROM QPayUserInfoBean WHERE userNo=?", strArr) : NBSSQLiteInstrumentation.rawQuery(readableDatabase, "SELECT * FROM QPayUserInfoBean WHERE userNo=?", strArr);
        if (!rawQuery.moveToFirst()) {
            rawQuery.close();
            return null;
        }
        WPQPayUserInfoBean wPQPayUserInfoBean = new WPQPayUserInfoBean();
        int columnIndex = rawQuery.getColumnIndex(WPQPayUserInfoBean.QPAY_COLUMN_ID);
        wPQPayUserInfoBean.setUserNo(columnIndex == -1 ? "" : rawQuery.getString(columnIndex));
        int columnIndex2 = rawQuery.getColumnIndex(WPQPayUserInfoBean.QPAY_COLUMN_KEY);
        wPQPayUserInfoBean.setPayToken(columnIndex2 == -1 ? "" : rawQuery.getString(columnIndex2));
        int columnIndex3 = rawQuery.getColumnIndex(WPQPayUserInfoBean.QPAY_COLUMN_CURRENT_KEY);
        wPQPayUserInfoBean.setCurrentFido(columnIndex3 == -1 ? "" : rawQuery.getString(columnIndex3));
        int columnIndex4 = rawQuery.getColumnIndex(WPQPayUserInfoBean.QPAY_COLUMN_SUPPORT_DOUBLE_KEY);
        wPQPayUserInfoBean.setIsSupportTwo(columnIndex4 == -1 ? "" : rawQuery.getString(columnIndex4));
        rawQuery.close();
        return wPQPayUserInfoBean;
    }

    /* renamed from: a */
    public final void m1982a(WPQPayUserInfoBean wPQPayUserInfoBean) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_ID, wPQPayUserInfoBean.getUserNo());
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_KEY, wPQPayUserInfoBean.getPayToken());
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_CURRENT_KEY, wPQPayUserInfoBean.getCurrentFido());
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_SUPPORT_DOUBLE_KEY, wPQPayUserInfoBean.getIsSupportTwo());
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.insert(writableDatabase, WPQPayUserInfoBean.QPAY_TABLE_NAME, null, contentValues);
        } else {
            writableDatabase.insert(WPQPayUserInfoBean.QPAY_TABLE_NAME, null, contentValues);
        }
    }

    /* renamed from: b */
    public final void m1980b(WPQPayUserInfoBean wPQPayUserInfoBean) {
        SQLiteDatabase writableDatabase = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_ID, wPQPayUserInfoBean.getUserNo());
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_KEY, wPQPayUserInfoBean.getPayToken());
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_CURRENT_KEY, wPQPayUserInfoBean.getCurrentFido());
        contentValues.put(WPQPayUserInfoBean.QPAY_COLUMN_SUPPORT_DOUBLE_KEY, wPQPayUserInfoBean.getIsSupportTwo());
        String[] strArr = {wPQPayUserInfoBean.getUserNo()};
        if (writableDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.update(writableDatabase, WPQPayUserInfoBean.QPAY_TABLE_NAME, contentValues, "userNo = ? ", strArr);
        } else {
            writableDatabase.update(WPQPayUserInfoBean.QPAY_TABLE_NAME, contentValues, "userNo = ? ", strArr);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, WPQPayUserInfoBean.CREATE_TABLE);
        } else {
            sQLiteDatabase.execSQL(WPQPayUserInfoBean.CREATE_TABLE);
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS QPayUserInfoBean");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS QPayUserInfoBean");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, WPQPayUserInfoBean.CREATE_TABLE);
        } else {
            sQLiteDatabase.execSQL(WPQPayUserInfoBean.CREATE_TABLE);
        }
    }
}
