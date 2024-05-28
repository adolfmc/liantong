package com.sinovatech.unicom.separatemodule.notice;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.sinovatech.unicom.basic.datacenter.DatabaseManager;
import com.sinovatech.unicom.basic.server.UserManager;
import com.sinovatech.unicom.common.DBOpenHelper;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class PushMsgDao {
    private DBOpenHelper dbOpenHelper;

    public PushMsgDao(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context);
        DatabaseManager.initializeInstance(this.dbOpenHelper);
    }

    public void clearPushMessage() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_push_messagerecord");
                    } else {
                        openDatabase.execSQL("delete from unicommobile_push_messagerecord");
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

    public void insertPushMessageRecord(PushMessageEntity pushMessageEntity) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    Object[] objArr = {pushMessageEntity.getId(), pushMessageEntity.getTitle(), pushMessageEntity.getContent(), pushMessageEntity.getUrl(), pushMessageEntity.getMsgType(), UserManager.getInstance().getCurrentPhoneNumber(), pushMessageEntity.getDate(), "0", pushMessageEntity.getSecondLevel(), pushMessageEntity.getEndTime(), pushMessageEntity.getNewMsgType(), pushMessageEntity.getServiceId()};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_push_messagerecord(message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,secondType,message_endtime,message_msgtype,message_serviceId)values(?,?,?,?,?,?,?,?,?,?,?,?)", objArr);
                    } else {
                        openDatabase.execSQL("insert into unicommobile_push_messagerecord(message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,secondType,message_endtime,message_msgtype,message_serviceId)values(?,?,?,?,?,?,?,?,?,?,?,?)", objArr);
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
                MsLogUtil.m7978e(e2.getMessage());
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void deletePushMessageRecord(PushMessageEntity pushMessageEntity) {
        deletePushMessageRecord(pushMessageEntity.getId());
    }

    public void deletePushMessageRecord(String str) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = {str};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_push_messagerecord where message_id=?", objArr);
                } else {
                    openDatabase.execSQL("delete from unicommobile_push_messagerecord where message_id=?", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    public void deletePushMessageRecordFirstLevel(String str) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = {str};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_push_messagerecord where message_type=?", objArr);
                } else {
                    openDatabase.execSQL("delete from unicommobile_push_messagerecord where message_type=?", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    public void deletePushMessageRecordAll() {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = new Object[0];
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_push_messagerecord", objArr);
                } else {
                    openDatabase.execSQL("delete from unicommobile_push_messagerecord", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    public void updatePushMessageRecordStatus(PushMessageEntity pushMessageEntity, String str) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = {str, pushMessageEntity.getId()};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "update unicommobile_push_messagerecord set message_status=? where message_id=?", objArr);
                } else {
                    openDatabase.execSQL("update unicommobile_push_messagerecord set message_status=? where message_id=?", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    public void updatePushMessageRecordStatus(String str, String str2) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = {str2, str};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "update unicommobile_push_messagerecord set message_status=? where message_id=?", objArr);
                } else {
                    openDatabase.execSQL("update unicommobile_push_messagerecord set message_status=? where message_id=?", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    public void updateAllPushMessageRecordStatus(String str) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = {str};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "update unicommobile_push_messagerecord set message_status=?", objArr);
                } else {
                    openDatabase.execSQL("update unicommobile_push_messagerecord set message_status=?", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    public void updateAllPushMessageRecordStatus(String str, String str2) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                openDatabase.beginTransaction();
                Object[] objArr = {str, str2};
                if (openDatabase instanceof SQLiteDatabase) {
                    NBSSQLiteInstrumentation.execSQL(openDatabase, "update unicommobile_push_messagerecord set message_status=? where message_type=?", objArr);
                } else {
                    openDatabase.execSQL("update unicommobile_push_messagerecord set message_status=? where message_type=?", objArr);
                }
                openDatabase.setTransactionSuccessful();
                if (openDatabase != null && openDatabase.inTransaction()) {
                    openDatabase.endTransaction();
                }
                databaseManager.closeDatabase();
            } catch (Exception unused) {
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
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:77:0x018a A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.sinovatech.unicom.separatemodule.notice.PushMessageEntity> getPushMessageRecord(java.lang.String r17, java.lang.String r18, int r19) {
        /*
            Method dump skipped, instructions count: 412
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.separatemodule.notice.PushMsgDao.getPushMessageRecord(java.lang.String, java.lang.String, int):java.util.List");
    }

    public List<PushMessageEntity> getPushMessageRecord(String str, String str2) {
        String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            try {
                try {
                    if ("0".equals(str2)) {
                        String[] strArr = {currentPhoneNumber};
                        cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype,message_serviceId  from unicommobile_push_messagerecord where message_usermobile=? order by id desc", strArr) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype,message_serviceId  from unicommobile_push_messagerecord where message_usermobile=? order by id desc", strArr);
                    } else {
                        String[] strArr2 = {currentPhoneNumber, str2};
                        cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype,message_serviceId  from unicommobile_push_messagerecord where message_usermobile=? and message_type=? order by id desc", strArr2) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype,message_serviceId  from unicommobile_push_messagerecord where message_usermobile=? and message_type=? order by id desc", strArr2);
                    }
                    while (cursor.moveToNext()) {
                        PushMessageEntity pushMessageEntity = new PushMessageEntity();
                        String string = cursor.getString(cursor.getColumnIndex("message_id"));
                        String string2 = cursor.getString(cursor.getColumnIndex("message_title"));
                        String string3 = cursor.getString(cursor.getColumnIndex("message_content"));
                        String string4 = cursor.getString(cursor.getColumnIndex("message_url"));
                        String string5 = cursor.getString(cursor.getColumnIndex("message_type"));
                        String string6 = cursor.getString(cursor.getColumnIndex("message_usermobile"));
                        String string7 = cursor.getString(cursor.getColumnIndex("message_date"));
                        String string8 = cursor.getString(cursor.getColumnIndex("message_status"));
                        String string9 = cursor.getString(cursor.getColumnIndex("message_endtime"));
                        String string10 = cursor.getString(cursor.getColumnIndex("message_msgtype"));
                        String string11 = cursor.getString(cursor.getColumnIndex("message_serviceId"));
                        pushMessageEntity.setId(string);
                        pushMessageEntity.setTitle(string2);
                        pushMessageEntity.setContent(string3);
                        pushMessageEntity.setUrl(string4);
                        pushMessageEntity.setMsgType(string5);
                        pushMessageEntity.setUserMobile(string6);
                        pushMessageEntity.setDate(string7);
                        pushMessageEntity.setStatus(string8);
                        pushMessageEntity.setEndTime(string9);
                        pushMessageEntity.setNewMsgType(string10);
                        pushMessageEntity.setServiceId(string11);
                        try {
                            if (System.currentTimeMillis() - Long.parseLong(string7) > 31536000000L) {
                                deletePushMessageRecord(pushMessageEntity);
                            } else {
                                arrayList.add(pushMessageEntity);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th) {
                    if (cursor != null) {
                        try {
                            if (!cursor.isClosed()) {
                                cursor.close();
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
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e4) {
            e4.printStackTrace();
        }
        return arrayList;
    }

    public int getPushMessageRecordSize(String str, String str2) {
        ArrayList arrayList = new ArrayList();
        try {
            String currentPhoneNumber = UserManager.getInstance().getCurrentPhoneNumber();
            DatabaseManager databaseManager = DatabaseManager.getInstance();
            SQLiteDatabase openDatabase = databaseManager.openDatabase();
            Cursor cursor = null;
            try {
                if ("0".equals(str2)) {
                    String[] strArr = {currentPhoneNumber, "1"};
                    cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype from unicommobile_push_messagerecord where message_usermobile=? and message_status !=? order by id desc", strArr) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype from unicommobile_push_messagerecord where message_usermobile=? and message_status !=? order by id desc", strArr);
                } else {
                    String[] strArr2 = {currentPhoneNumber, str2, "1"};
                    cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype from unicommobile_push_messagerecord where message_usermobile=? and message_type=? and message_status !=? order by id desc", strArr2) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select message_id,message_title,message_content,message_url,message_type,message_usermobile,message_date,message_status,message_endtime,message_msgtype from unicommobile_push_messagerecord where message_usermobile=? and message_type=? and message_status !=? order by id desc", strArr2);
                }
                while (cursor.moveToNext()) {
                    PushMessageEntity pushMessageEntity = new PushMessageEntity();
                    String string = cursor.getString(cursor.getColumnIndex("message_id"));
                    String string2 = cursor.getString(cursor.getColumnIndex("message_title"));
                    String string3 = cursor.getString(cursor.getColumnIndex("message_content"));
                    String string4 = cursor.getString(cursor.getColumnIndex("message_url"));
                    String string5 = cursor.getString(cursor.getColumnIndex("message_type"));
                    String string6 = cursor.getString(cursor.getColumnIndex("message_usermobile"));
                    String string7 = cursor.getString(cursor.getColumnIndex("message_date"));
                    String string8 = cursor.getString(cursor.getColumnIndex("message_status"));
                    String string9 = cursor.getString(cursor.getColumnIndex("message_endtime"));
                    String string10 = cursor.getString(cursor.getColumnIndex("message_msgtype"));
                    pushMessageEntity.setId(string);
                    pushMessageEntity.setTitle(string2);
                    pushMessageEntity.setContent(string3);
                    pushMessageEntity.setUrl(string4);
                    pushMessageEntity.setMsgType(string5);
                    pushMessageEntity.setUserMobile(string6);
                    pushMessageEntity.setDate(string7);
                    pushMessageEntity.setStatus(string8);
                    pushMessageEntity.setEndTime(string9);
                    pushMessageEntity.setNewMsgType(string10);
                    try {
                        if (System.currentTimeMillis() - Long.parseLong(string7) > 1471228928) {
                            deletePushMessageRecord(pushMessageEntity);
                        } else {
                            arrayList.add(pushMessageEntity);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                if (cursor != null) {
                    try {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    } catch (Exception e2) {
                        e = e2;
                        e.printStackTrace();
                        return arrayList.size();
                    }
                }
                databaseManager.closeDatabase();
            } catch (Exception e3) {
                e3.printStackTrace();
                if (cursor != null) {
                    try {
                        if (!cursor.isClosed()) {
                            cursor.close();
                        }
                    } catch (Exception e4) {
                        e = e4;
                        e.printStackTrace();
                        return arrayList.size();
                    }
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e5) {
            MsLogUtil.m7978e(e5.getMessage());
        }
        return arrayList.size();
    }

    public boolean getPushMessageRecordExist(PushMessageEntity pushMessageEntity) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        boolean z = false;
        Cursor cursor = null;
        try {
            try {
                try {
                    String[] strArr = {pushMessageEntity.getId()};
                    cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select id from unicommobile_push_messagerecord where message_id=?", strArr) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select id from unicommobile_push_messagerecord where message_id=?", strArr);
                    z = cursor.moveToNext();
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                    databaseManager.closeDatabase();
                } catch (Throwable th) {
                    if (cursor != null) {
                        try {
                            if (!cursor.isClosed()) {
                                cursor.close();
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
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
                databaseManager.closeDatabase();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return z;
    }
}
