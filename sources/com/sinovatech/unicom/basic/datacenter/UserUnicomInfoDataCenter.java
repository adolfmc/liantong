package com.sinovatech.unicom.basic.datacenter;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.sinovatech.unicom.basic.p314po.RightMenuEntity;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DBOpenHelper;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class UserUnicomInfoDataCenter {
    public static String TYPE_BAIDUMAP_CITY = "TYPE_BAIDUMAP_CITY";
    public static String TYPE_NOTICCLASS = "notic_class";
    public static String TYPE_RIGHTMENU = "TYPE_RIGHTMENU";
    public static String TYPE_VAIDUMAPCOLLECT = "type_baidumaip";
    public static String TYPE_WATCH = "TYPE_WATCH";
    public static String TYPE_YINSIXIEYI = "TYPE_YINSIXIEYI";
    private Context context;
    private DBOpenHelper dbOpenHelper;

    public UserUnicomInfoDataCenter(Context context) {
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
        DatabaseManager.initializeInstance(this.dbOpenHelper);
    }

    public void updataUserUnicomInfoDataCenter(String str, String str2, String str3) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {str, str3};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_UserUnicomInfoDataCenter_info where usermobile=? and type=? ", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_UserUnicomInfoDataCenter_info where usermobile=? and type=? ", strArr);
                    }
                    Object[] objArr = {str, str2, str3};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_UserUnicomInfoDataCenter_info(usermobile,jsoncontent,type)  values(?,?,?)", objArr);
                    } else {
                        openDatabase.execSQL("insert into unicommobile_UserUnicomInfoDataCenter_info(usermobile,jsoncontent,type)  values(?,?,?)", objArr);
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

    /* JADX WARN: Removed duplicated region for block: B:50:0x007e A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.String getUserUnicomInfoData(java.lang.String r8, java.lang.String r9) {
        /*
            r7 = this;
            com.sinovatech.unicom.basic.datacenter.DatabaseManager r0 = com.sinovatech.unicom.basic.datacenter.DatabaseManager.getInstance()
            android.database.sqlite.SQLiteDatabase r1 = r0.openDatabase()
            r2 = 0
            java.lang.String r3 = "select jsoncontent from unicommobile_UserUnicomInfoDataCenter_info where usermobile=? and type=?"
            r4 = 2
            java.lang.String[] r4 = new java.lang.String[r4]     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            r5 = 0
            r4[r5] = r8     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            r5 = 1
            r4[r5] = r9     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            boolean r5 = r1 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            if (r5 != 0) goto L1d
            android.database.Cursor r1 = r1.rawQuery(r3, r4)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            goto L23
        L1d:
            android.database.sqlite.SQLiteDatabase r1 = (android.database.sqlite.SQLiteDatabase) r1     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
            android.database.Cursor r1 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r1, r3, r4)     // Catch: java.lang.Throwable -> L4f java.lang.Exception -> L51
        L23:
            boolean r3 = r1.moveToNext()     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            if (r3 == 0) goto L33
            java.lang.String r3 = "jsoncontent"
            int r3 = r1.getColumnIndex(r3)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
            java.lang.String r2 = r1.getString(r3)     // Catch: java.lang.Throwable -> L47 java.lang.Exception -> L4a
        L33:
            if (r1 == 0) goto L3e
            boolean r3 = r1.isClosed()     // Catch: java.lang.Exception -> L42
            if (r3 != 0) goto L3e
            r1.close()     // Catch: java.lang.Exception -> L42
        L3e:
            r0.closeDatabase()     // Catch: java.lang.Exception -> L42
            goto L64
        L42:
            r0 = move-exception
            r0.printStackTrace()
            goto L64
        L47:
            r8 = move-exception
            r2 = r1
            goto L7c
        L4a:
            r3 = move-exception
            r6 = r3
            r3 = r1
            r1 = r6
            goto L53
        L4f:
            r8 = move-exception
            goto L7c
        L51:
            r1 = move-exception
            r3 = r2
        L53:
            r1.printStackTrace()     // Catch: java.lang.Throwable -> L7a
            if (r3 == 0) goto L61
            boolean r1 = r3.isClosed()     // Catch: java.lang.Exception -> L42
            if (r1 != 0) goto L61
            r3.close()     // Catch: java.lang.Exception -> L42
        L61:
            r0.closeDatabase()     // Catch: java.lang.Exception -> L42
        L64:
            java.lang.String r0 = "0"
            boolean r8 = r0.equals(r8)
            if (r8 != 0) goto L79
            boolean r8 = android.text.TextUtils.isEmpty(r2)
            if (r8 == 0) goto L79
            java.lang.String r8 = "0"
            java.lang.String r8 = r7.getUserUnicomInfoData(r8, r9)
            return r8
        L79:
            return r2
        L7a:
            r8 = move-exception
            r2 = r3
        L7c:
            if (r2 == 0) goto L87
            boolean r9 = r2.isClosed()     // Catch: java.lang.Exception -> L8b
            if (r9 != 0) goto L87
            r2.close()     // Catch: java.lang.Exception -> L8b
        L87:
            r0.closeDatabase()     // Catch: java.lang.Exception -> L8b
            goto L8f
        L8b:
            r9 = move-exception
            r9.printStackTrace()
        L8f:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.basic.datacenter.UserUnicomInfoDataCenter.getUserUnicomInfoData(java.lang.String, java.lang.String):java.lang.String");
    }

    public List<RightMenuEntity> getPrivateList(String str) {
        ArrayList arrayList = new ArrayList();
        try {
            JSONArray optJSONArray = new JSONObject(str).optJSONArray("data");
            for (int i = 0; i < optJSONArray.length(); i++) {
                JSONObject optJSONObject = optJSONArray.optJSONObject(i);
                if (optJSONObject != null) {
                    RightMenuEntity rightMenuEntity = new RightMenuEntity();
                    rightMenuEntity.setDesc_info(optJSONObject.optString("desc_info"));
                    rightMenuEntity.setIcon_url(optJSONObject.optString("icon_url"));
                    rightMenuEntity.setCid(optJSONObject.optString("id"));
                    rightMenuEntity.setInterfaceUrl(optJSONObject.optString("interfaceUrl"));
                    rightMenuEntity.setIsVideo(optJSONObject.optString("isVideo"));
                    rightMenuEntity.setTypeCode(optJSONObject.optString("typeCode"));
                    rightMenuEntity.setUrl(optJSONObject.optString("url"));
                    rightMenuEntity.setTitle(optJSONObject.optString("title"));
                    rightMenuEntity.setUnchecked_url(optJSONObject.optString("unchecked_url"));
                    rightMenuEntity.setMobile(UserManager.getInstance().getCurrentPhoneNumber());
                    rightMenuEntity.setClassifyCode(optJSONObject.optString("classifyCode"));
                    arrayList.add(rightMenuEntity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arrayList;
    }
}
