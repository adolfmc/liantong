package com.sinovatech.unicom.separatemodule.dianzishenfenzheng;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.sinovatech.unicom.basic.datacenter.DatabaseManager;
import com.sinovatech.unicom.common.DBOpenHelper;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class ElectronicIdCardDao {
    private DBOpenHelper dbOpenHelper;

    public ElectronicIdCardDao(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context);
        DatabaseManager.initializeInstance(this.dbOpenHelper);
    }

    public boolean updateData(String str, String str2) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                String[] strArr = {str};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicom_electronic_id_card where usermobile=? ", strArr);
                } else {
                    openDatabase.execSQL("delete from unicom_electronic_id_card where usermobile=? ", strArr);
                }
                String[] strArr2 = {str, str2};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicom_electronic_id_card(usermobile,jsoncontent) values(?,?)", strArr2);
                } else {
                    openDatabase.execSQL("insert into unicom_electronic_id_card(usermobile,jsoncontent) values(?,?)", strArr2);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null) {
                    try {
                        if (openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                databaseManager.closeDatabase();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (openDatabase != null) {
                    try {
                        if (openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return false;
                    }
                }
                databaseManager.closeDatabase();
                return false;
            }
        } catch (Throwable th) {
            if (openDatabase != null) {
                try {
                    if (openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                    throw th;
                }
            }
            databaseManager.closeDatabase();
            throw th;
        }
    }

    public boolean deleteData(String str) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                String[] strArr = {str};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicom_electronic_id_card where usermobile=? ", strArr);
                } else {
                    openDatabase.execSQL("delete from unicom_electronic_id_card where usermobile=? ", strArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null) {
                    try {
                        if (openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                        return true;
                    }
                }
                databaseManager.closeDatabase();
                return true;
            } catch (Exception e2) {
                e2.printStackTrace();
                if (openDatabase != null) {
                    try {
                        if (openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Exception e3) {
                        e3.printStackTrace();
                        return false;
                    }
                }
                databaseManager.closeDatabase();
                return false;
            }
        } catch (Throwable th) {
            if (openDatabase != null) {
                try {
                    if (openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                } catch (Exception e4) {
                    e4.printStackTrace();
                    throw th;
                }
            }
            databaseManager.closeDatabase();
            throw th;
        }
    }

    public String getJsonData(String str) {
        String str2;
        str2 = "";
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        Cursor cursor = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            try {
                String[] strArr = {str};
                cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select jsoncontent from unicom_electronic_id_card where usermobile=?", strArr) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select jsoncontent from unicom_electronic_id_card where usermobile=?", strArr);
                str2 = cursor.moveToNext() ? cursor.getString(cursor.getColumnIndex("jsoncontent")) : "";
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                databaseManager.closeDatabase();
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                databaseManager.closeDatabase();
            }
            return str2;
        } catch (Throwable th) {
            if (cursor != null) {
                try {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Exception e3) {
                    e3.printStackTrace();
                    throw th;
                }
            }
            databaseManager.closeDatabase();
            throw th;
        }
    }
}
