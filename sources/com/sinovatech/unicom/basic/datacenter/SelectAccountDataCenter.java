package com.sinovatech.unicom.basic.datacenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.sinovatech.unicom.basic.p314po.LoginAccountEntity;
import com.sinovatech.unicom.basic.server.ConfigManager;
import com.sinovatech.unicom.common.DBOpenHelper;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class SelectAccountDataCenter {
    private String LOG = "SelectAccountDataCenter";
    private ConfigManager configManager;
    private Context context;
    private DBOpenHelper dbOpenHelper;
    private String limitNumer;

    public SelectAccountDataCenter(Context context) {
        this.limitNumer = "8";
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
        this.configManager = new ConfigManager(context);
        DatabaseManager.initializeInstance(this.dbOpenHelper);
        String limitNumer = this.configManager.getLimitNumer();
        if (TextUtils.isEmpty(limitNumer)) {
            return;
        }
        try {
            Integer.parseInt(limitNumer);
            this.limitNumer = limitNumer;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void insertSelectAccountData(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_selectaccount where accountname=?", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_selectaccount where accountname=?", strArr);
                    }
                    Object[] objArr = {str, str2, str3, str4, str5, str6, str7};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_basic_selectaccount(accountname, areaid, accounttype, password, keyversion, isbind,iconurl)  values(?,?,?,?,?,?,?)", objArr);
                    } else {
                        openDatabase.execSQL("insert into unicommobile_basic_selectaccount(accountname, areaid, accounttype, password, keyversion, isbind,iconurl)  values(?,?,?,?,?,?,?)", objArr);
                    }
                    openDatabase.setTransactionSuccessful();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th) {
                    if (openDatabase != null) {
                        try {
                            if (openDatabase.inTransaction()) {
                                openDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw th;
                        }
                    }
                    databaseManager.closeDatabase();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void insertSelectAccountData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_selectaccount where accountname=?", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_selectaccount where accountname=?", strArr);
                    }
                    Object[] objArr = {str, str2, str3, str4, str5, str6, str7, str8};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_basic_selectaccount(accountname, areaid, accounttype, password, keyversion, isbind,iconurl,yw_code)  values(?,?,?,?,?,?,?,?)", objArr);
                    } else {
                        openDatabase.execSQL("insert into unicommobile_basic_selectaccount(accountname, areaid, accounttype, password, keyversion, isbind,iconurl,yw_code)  values(?,?,?,?,?,?,?,?)", objArr);
                    }
                    openDatabase.setTransactionSuccessful();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th) {
                    if (openDatabase != null) {
                        try {
                            if (openDatabase.inTransaction()) {
                                openDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw th;
                        }
                    }
                    databaseManager.closeDatabase();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void insertSelectAccountData(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_selectaccount where accountname=?", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_selectaccount where accountname=?", strArr);
                    }
                    Object[] objArr = {str, str2, str3, str4, str5, str6, str7, str8, str9};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_basic_selectaccount(accountname, areaid, accounttype, password, keyversion, isbind,iconurl,cid,intact)  values(?,?,?,?,?,?,?,?,?)", objArr);
                    } else {
                        openDatabase.execSQL("insert into unicommobile_basic_selectaccount(accountname, areaid, accounttype, password, keyversion, isbind,iconurl,cid,intact)  values(?,?,?,?,?,?,?,?,?)", objArr);
                    }
                    openDatabase.setTransactionSuccessful();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                }
            } catch (Throwable th) {
                if (openDatabase != null) {
                    try {
                        if (openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        throw th;
                    }
                }
                databaseManager.closeDatabase();
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void deleteSelectAccountData(String str) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_selectaccount where accountname=? ", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_selectaccount where accountname=? ", strArr);
                    }
                    openDatabase.setTransactionSuccessful();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                } catch (Exception e) {
                    e.printStackTrace();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                }
            } catch (Throwable th) {
                if (openDatabase != null) {
                    try {
                        if (openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Exception e2) {
                        e2.printStackTrace();
                        throw th;
                    }
                }
                databaseManager.closeDatabase();
                throw th;
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void deleteAllAccountData() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_selectaccount");
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_selectaccount");
                    }
                    openDatabase.setTransactionSuccessful();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th) {
                    if (openDatabase != null) {
                        try {
                            if (openDatabase.inTransaction()) {
                                openDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw th;
                        }
                    }
                    databaseManager.closeDatabase();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public LoginAccountEntity getSelectAccountData(String str, String str2) {
        LoginAccountEntity loginAccountEntity;
        Cursor query;
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        Cursor cursor = null;
        LoginAccountEntity loginAccountEntity2 = null;
        cursor = null;
        try {
            try {
                String[] strArr = {"accountname", "areaid", "accounttype", "password", "keyversion", "iconurl", "cid", "intact"};
                String[] strArr2 = {str, str2};
                query = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.query("unicommobile_basic_selectaccount", strArr, "accountname=? and isbind=?", strArr2, null, null, "id desc", null) : NBSSQLiteInstrumentation.query(openDatabase, "unicommobile_basic_selectaccount", strArr, "accountname=? and isbind=?", strArr2, null, null, "id desc", null);
            } catch (Throwable th) {
                th = th;
            }
            try {
                try {
                    if (query.moveToNext()) {
                        String string = query.getString(query.getColumnIndex("accountname"));
                        String string2 = query.getString(query.getColumnIndex("areaid"));
                        String string3 = query.getString(query.getColumnIndex("accounttype"));
                        String string4 = query.getString(query.getColumnIndex("password"));
                        String string5 = query.getString(query.getColumnIndex("keyversion"));
                        String string6 = query.getString(query.getColumnIndex("iconurl"));
                        String string7 = query.getString(query.getColumnIndex("cid"));
                        String string8 = query.getString(query.getColumnIndex("intact"));
                        loginAccountEntity = new LoginAccountEntity(string, string2, string3, string4, string5, string6, string7);
                        try {
                            loginAccountEntity.setIntact(string8);
                            loginAccountEntity2 = loginAccountEntity;
                        } catch (Exception e) {
                            e = e;
                            cursor = query;
                            e.printStackTrace();
                            if (cursor != null) {
                                try {
                                    if (!cursor.isClosed()) {
                                        cursor.close();
                                    }
                                } catch (Exception e2) {
                                    e2.printStackTrace();
                                    return loginAccountEntity;
                                }
                            }
                            databaseManager.closeDatabase();
                            return loginAccountEntity;
                        }
                    }
                    if (query != null) {
                        try {
                            if (!query.isClosed()) {
                                query.close();
                            }
                        } catch (Exception e3) {
                            e3.printStackTrace();
                            return loginAccountEntity2;
                        }
                    }
                    databaseManager.closeDatabase();
                    return loginAccountEntity2;
                } catch (Exception e4) {
                    e = e4;
                    loginAccountEntity = null;
                }
            } catch (Throwable th2) {
                th = th2;
                cursor = query;
                Throwable th3 = th;
                if (cursor != null) {
                    try {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    } catch (Exception e5) {
                        e5.printStackTrace();
                        throw th3;
                    }
                }
                databaseManager.closeDatabase();
                throw th3;
            }
        } catch (Exception e6) {
            e = e6;
            loginAccountEntity = null;
        }
    }

    public void updateSelectAccountIcon(String str, String str2) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {str2, str};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "update unicommobile_basic_selectaccount set iconurl=? where accountname=? ", strArr);
                    } else {
                        openDatabase.execSQL("update unicommobile_basic_selectaccount set iconurl=? where accountname=? ", strArr);
                    }
                    openDatabase.setTransactionSuccessful();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th) {
                    if (openDatabase != null) {
                        try {
                            if (openDatabase.inTransaction()) {
                                openDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw th;
                        }
                    }
                    databaseManager.closeDatabase();
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public List<LoginAccountEntity> getSelectAccountDataList(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            if ("1".equals(str2)) {
                try {
                    try {
                        openDatabase.beginTransaction();
                        String str3 = "delete from unicommobile_basic_selectaccount where (select count(accountname) from unicommobile_basic_selectaccount where isbind=1)>" + this.limitNumer + " and accountname in (select accountname from unicommobile_basic_selectaccount where isbind=1 order by id desc limit (select count(accountname) from unicommobile_basic_selectaccount) offset " + this.limitNumer + " )";
                        if (openDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.execSQL(openDatabase, str3);
                        } else {
                            openDatabase.execSQL(str3);
                        }
                        openDatabase.setTransactionSuccessful();
                        if (openDatabase != null && openDatabase.inTransaction()) {
                            openDatabase.endTransaction();
                        }
                    } catch (Throwable th) {
                        if (openDatabase != null) {
                            try {
                                if (openDatabase.inTransaction()) {
                                    openDatabase.endTransaction();
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                        throw th;
                    }
                } catch (Exception e2) {
                    e2.printStackTrace();
                    if (openDatabase != null && openDatabase.inTransaction()) {
                        openDatabase.endTransaction();
                    }
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        StringBuilder sb = new StringBuilder();
        ArrayList arrayList2 = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                try {
                    if (!TextUtils.isEmpty(str) && !TextUtils.isEmpty(str2)) {
                        sb.append("accounttype=?");
                        arrayList2.add(str);
                        sb.append(" and isbind=?");
                        arrayList2.add(str2);
                    } else if (!TextUtils.isEmpty(str)) {
                        sb.append("accounttype=?");
                        arrayList2.add(str);
                    } else if (!TextUtils.isEmpty(str2)) {
                        sb.append("isbind=?");
                        arrayList2.add(str2);
                    }
                    String[] strArr = (String[]) arrayList2.toArray(new String[arrayList2.size()]);
                    String[] strArr2 = {"accountname", "areaid", "accounttype", "password", "keyversion", "iconurl", "cid", "intact"};
                    String sb2 = sb.toString();
                    String str4 = "0," + this.limitNumer + "";
                    cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.query("unicommobile_basic_selectaccount", strArr2, sb2, strArr, null, null, "id desc", str4) : NBSSQLiteInstrumentation.query(openDatabase, "unicommobile_basic_selectaccount", strArr2, sb2, strArr, null, null, "id desc", str4);
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndex("accountname"));
                        String string2 = cursor.getString(cursor.getColumnIndex("areaid"));
                        String string3 = cursor.getString(cursor.getColumnIndex("accounttype"));
                        String string4 = cursor.getString(cursor.getColumnIndex("password"));
                        String string5 = cursor.getString(cursor.getColumnIndex("keyversion"));
                        String string6 = cursor.getString(cursor.getColumnIndex("iconurl"));
                        String string7 = cursor.getString(cursor.getColumnIndex("cid"));
                        String string8 = cursor.getString(cursor.getColumnIndex("intact"));
                        LoginAccountEntity loginAccountEntity = new LoginAccountEntity(string, string2, string3, string4, string5, string6, string7);
                        loginAccountEntity.setIntact(string8);
                        if ("idcard".equals(str)) {
                            try {
                                loginAccountEntity.setAccountnameJiami(string.substring(0, 6) + "**********" + string.substring(16, 18));
                            } catch (Exception e4) {
                                e4.printStackTrace();
                            }
                        }
                        arrayList.add(loginAccountEntity);
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th2) {
                    if (cursor != null) {
                        try {
                            if (!cursor.isClosed()) {
                                cursor.close();
                            }
                        } catch (Exception e5) {
                            e5.printStackTrace();
                            throw th2;
                        }
                    }
                    databaseManager.closeDatabase();
                    throw th2;
                }
            } catch (Exception e6) {
                e6.printStackTrace();
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e7) {
            e7.printStackTrace();
        }
        return arrayList;
    }
}
