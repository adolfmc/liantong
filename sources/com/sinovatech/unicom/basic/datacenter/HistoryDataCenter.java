package com.sinovatech.unicom.basic.datacenter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.p284qw.soul.permission.SoulPermission;
import com.sinovatech.unicom.basic.p315ui.activity.LookHistoryActivity;
import com.sinovatech.unicom.basic.p315ui.activity.WebDetailActivity;
import com.sinovatech.unicom.common.DBOpenHelper;
import com.sinovatech.unicom.separatemodule.Log.StatisticsEntity;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class HistoryDataCenter {
    private String LOG = "HistoryDataCenter";
    private Context context;
    private DBOpenHelper dbOpenHelper;

    public HistoryDataCenter(Context context) {
        this.context = context;
        this.dbOpenHelper = new DBOpenHelper(context);
        DatabaseManager.initializeInstance(this.dbOpenHelper);
    }

    private String serializeUser(StatisticsEntity statisticsEntity) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(statisticsEntity);
        String encode = URLEncoder.encode(byteArrayOutputStream.toString("ISO-8859-1"), "UTF-8");
        objectOutputStream.close();
        byteArrayOutputStream.close();
        return encode;
    }

    private StatisticsEntity deSerialization(String str) throws IOException, ClassNotFoundException {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(URLDecoder.decode(str, "UTF-8").getBytes("ISO-8859-1"));
        ObjectInputStream objectInputStream = new ObjectInputStream(byteArrayInputStream);
        StatisticsEntity statisticsEntity = (StatisticsEntity) objectInputStream.readObject();
        objectInputStream.close();
        byteArrayInputStream.close();
        return statisticsEntity;
    }

    public void insertStatisticsRecord(StatisticsEntity statisticsEntity) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    String serializeUser = serializeUser(statisticsEntity);
                    openDatabase.beginTransaction();
                    String[] strArr = {statisticsEntity.getTitleName(), statisticsEntity.getMobile()};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_history_record where title = ? and mobile = ?", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_history_record where title = ? and mobile = ?", strArr);
                    }
                    Object[] objArr = {statisticsEntity.getMobile(), statisticsEntity.getTitleName(), statisticsEntity.getTime(), serializeUser.getBytes()};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_basic_history_record(mobile,title,time,record) values (?,?,?,?)", objArr);
                    } else {
                        openDatabase.execSQL("insert into unicommobile_basic_history_record(mobile,title,time,record) values (?,?,?,?)", objArr);
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
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (openDatabase != null && openDatabase.inTransaction()) {
                openDatabase.endTransaction();
            }
            databaseManager.closeDatabase();
        }
    }

    public void deleteStatisticsRecord(StatisticsEntity statisticsEntity) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    String[] strArr = {statisticsEntity.getTitleName(), statisticsEntity.getMobile()};
                    if (openDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_history_record where title = ? and mobile = ?", strArr);
                    } else {
                        openDatabase.execSQL("delete from unicommobile_basic_history_record where title = ? and mobile = ?", strArr);
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
            }
        } catch (Exception e3) {
            e3.printStackTrace();
            if (openDatabase != null && openDatabase.inTransaction()) {
                openDatabase.endTransaction();
            }
            databaseManager.closeDatabase();
        }
    }

    public void insertStatisticsRecord(List<StatisticsEntity> list) {
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        try {
            try {
                try {
                    openDatabase.beginTransaction();
                    for (StatisticsEntity statisticsEntity : list) {
                        String serializeUser = serializeUser(statisticsEntity);
                        String[] strArr = {statisticsEntity.getTitleName(), statisticsEntity.getMobile()};
                        if (openDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.execSQL(openDatabase, "delete from unicommobile_basic_history_record where title = ? and mobile = ?", strArr);
                        } else {
                            openDatabase.execSQL("delete from unicommobile_basic_history_record where title = ? and mobile = ?", strArr);
                        }
                        Object[] objArr = {statisticsEntity.getMobile(), statisticsEntity.getTitleName(), statisticsEntity.getTime(), serializeUser.getBytes()};
                        if (openDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.execSQL(openDatabase, "insert into unicommobile_basic_history_record(mobile,title,time,record) values (?,?,?,?)", objArr);
                        } else {
                            openDatabase.execSQL("insert into unicommobile_basic_history_record(mobile,title,time,record) values (?,?,?,?)", objArr);
                        }
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

    public List<StatisticsEntity> getAllStatisticsRecord(String str) {
        ArrayList arrayList = new ArrayList();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        int i = 6;
        Cursor cursor = null;
        try {
            try {
                try {
                    String str2 = "select record from unicommobile_basic_history_record where mobile = ?  order by time desc LIMIT " + (((SoulPermission.getInstance().getTopActivity() instanceof LookHistoryActivity) || (SoulPermission.getInstance().getTopActivity() instanceof WebDetailActivity)) ? 30 : 30);
                    String[] strArr = {str};
                    cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery(str2, strArr) : NBSSQLiteInstrumentation.rawQuery(openDatabase, str2, strArr);
                    while (cursor.moveToNext()) {
                        arrayList.add(deSerialization(new String(cursor.getBlob(cursor.getColumnIndex("record")))));
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
        return arrayList;
    }

    public List<StatisticsEntity> getAllStatisticsRecord(String str, int i) {
        ArrayList arrayList = new ArrayList();
        DatabaseManager databaseManager = DatabaseManager.getInstance();
        SQLiteDatabase openDatabase = databaseManager.openDatabase();
        Cursor cursor = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            try {
                String str2 = "select record from unicommobile_basic_history_record where mobile = ?  order by time desc LIMIT " + i;
                String[] strArr = {str};
                cursor = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery(str2, strArr) : NBSSQLiteInstrumentation.rawQuery(openDatabase, str2, strArr);
                while (cursor.moveToNext()) {
                    arrayList.add(deSerialization(new String(cursor.getBlob(cursor.getColumnIndex("record")))));
                }
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
            return arrayList;
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
