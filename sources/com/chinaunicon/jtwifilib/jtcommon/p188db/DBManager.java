package com.chinaunicon.jtwifilib.jtcommon.p188db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import com.chinaunicon.jtwifilib.core.utils.JtL;
import com.chinaunicon.jtwifilib.core.utils.SharedPreferencesHelp;
import com.chinaunicon.jtwifilib.jtcommon.model.JtLogModel;
import com.chinaunicon.jtwifilib.jtcommon.model.JtNetConfig;
import com.chinaunicon.jtwifilib.jtcommon.model.JtNetPremission;
import com.chinaunicon.jtwifilib.jtcommon.util.JtUploadLog;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* renamed from: com.chinaunicon.jtwifilib.jtcommon.db.DBManager */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class DBManager {
    private static final String ASSETS_NAME = "speed_config.db";
    private static final int BUFFER_SIZE = 1024;
    private static final String CITYCODE = "cityCode";
    private static final String DB_NAME = "speed_config.db";
    private static final String NAME = "name";
    private static final String PINYIN = "pinyin";
    private static final String TABLE_NAME = "city";
    private String DB_PATH;
    private int VERSION = 6;
    private Context mContext;

    public DBManager(Context context) {
        this.mContext = context;
        this.DB_PATH = File.separator + "data" + Environment.getDataDirectory().getAbsolutePath() + File.separator + context.getPackageName() + File.separator + "databases" + File.separator;
    }

    public void copyDBFile() {
        JtL.m16342e("数据库地址：" + this.DB_PATH);
        File file = new File(this.DB_PATH);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(this.DB_PATH + "speed_config.db");
        if (!file2.exists()) {
            JtL.m16342e("数据库不存在，写入");
            writeFile(file2);
            return;
        }
        if (this.VERSION != SharedPreferencesHelp.getInstance(this.mContext, "dbversion_file").getInt("dbVersion", 0)) {
            JtL.m16342e("数据库存在，数据库版本不符，删除后写入");
            file2.delete();
            writeFile(new File(this.DB_PATH + "speed_config.db"));
            SharedPreferencesHelp.getInstance(this.mContext, "dbversion_file").putInt("dbVersion", this.VERSION);
            return;
        }
        JtL.m16342e("数据库存在，数据库版本相符");
    }

    public void writeFile(File file) {
        try {
            InputStream open = this.mContext.getResources().getAssets().open("speed_config.db");
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = open.read(bArr, 0, bArr.length);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.flush();
                    fileOutputStream.close();
                    open.close();
                    return;
                }
            }
        } catch (IOException e) {
            JtUploadLog jtUploadLog = JtUploadLog.getInstance(this.mContext);
            jtUploadLog.updateData("1", "数据库写入异常：" + e.getMessage(), "db_exception");
        }
    }

    public SQLiteDatabase getSQLiteDatabase() {
        return SQLiteDatabase.openOrCreateDatabase(this.DB_PATH + "speed_config.db", (SQLiteDatabase.CursorFactory) null);
    }

    public boolean insertConfig(String str, String str2, String str3, String str4) {
        SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(this.DB_PATH + "speed_config.db", (SQLiteDatabase.CursorFactory) null);
        ContentValues contentValues = new ContentValues();
        contentValues.put("appID", str);
        contentValues.put("version", str2);
        contentValues.put("download", str3);
        contentValues.put("isLatest", str4);
        long insert = !(openOrCreateDatabase instanceof SQLiteDatabase) ? openOrCreateDatabase.insert("config", null, contentValues) : NBSSQLiteInstrumentation.insert(openOrCreateDatabase, "config", null, contentValues);
        openOrCreateDatabase.close();
        return insert != -1;
    }

    public boolean insertPremission(String str) {
        SQLiteDatabase openDatabase = DatabaseManager.getInstance().openDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        long insert = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.insert("premission", null, contentValues) : NBSSQLiteInstrumentation.insert(openDatabase, "premission", null, contentValues);
        DatabaseManager.getInstance().closeDatabase();
        return insert != -1;
    }

    public List<JtNetPremission> selectPremission() {
        SQLiteDatabase openDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor rawQuery = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select * from premission", null) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select * from premission", null);
        ArrayList arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            String string = rawQuery.getString(rawQuery.getColumnIndex("name"));
            JtNetPremission jtNetPremission = new JtNetPremission();
            jtNetPremission.setName(string);
            arrayList.add(jtNetPremission);
        }
        rawQuery.close();
        DatabaseManager.getInstance().closeDatabase();
        return arrayList;
    }

    public JtNetConfig selectConfig() {
        SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(this.DB_PATH + "speed_config.db", (SQLiteDatabase.CursorFactory) null);
        Cursor rawQuery = !(openOrCreateDatabase instanceof SQLiteDatabase) ? openOrCreateDatabase.rawQuery("select * from config", null) : NBSSQLiteInstrumentation.rawQuery(openOrCreateDatabase, "select * from config", null);
        ArrayList arrayList = new ArrayList();
        while (rawQuery.moveToNext()) {
            String string = rawQuery.getString(rawQuery.getColumnIndex("version"));
            String string2 = rawQuery.getString(rawQuery.getColumnIndex("download"));
            String string3 = rawQuery.getString(rawQuery.getColumnIndex("appID"));
            String string4 = rawQuery.getString(rawQuery.getColumnIndex("isLatest"));
            JtNetConfig jtNetConfig = new JtNetConfig();
            jtNetConfig.setIsSuceess(string3);
            jtNetConfig.setDownload(string2);
            jtNetConfig.setVersion(string);
            jtNetConfig.setIsLatest(string4);
            arrayList.add(jtNetConfig);
        }
        rawQuery.close();
        openOrCreateDatabase.close();
        if (arrayList.size() > 0) {
            return (JtNetConfig) arrayList.get(0);
        }
        return null;
    }

    public void dropTable() {
        SQLiteDatabase openOrCreateDatabase = SQLiteDatabase.openOrCreateDatabase(this.DB_PATH + "speed_config.db", (SQLiteDatabase.CursorFactory) null);
        boolean z = openOrCreateDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(openOrCreateDatabase, "delete from config");
        } else {
            openOrCreateDatabase.execSQL("delete from config");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(openOrCreateDatabase, "delete from premission");
        } else {
            openOrCreateDatabase.execSQL("delete from premission");
        }
        openOrCreateDatabase.close();
    }

    public boolean insertLog(JtLogModel jtLogModel) {
        if (new File(this.DB_PATH + "speed_config.db").exists()) {
            SQLiteDatabase openDatabase = DatabaseManager.getInstance().openDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("type", jtLogModel.getType());
            contentValues.put("user", jtLogModel.getUser());
            contentValues.put("message", jtLogModel.getMessage());
            contentValues.put("uuid", jtLogModel.getUuid());
            contentValues.put("version", jtLogModel.getVersion());
            contentValues.put("operType", jtLogModel.getOperType());
            contentValues.put("time", jtLogModel.getTime());
            contentValues.put("provinceCode", jtLogModel.getProvinceCode());
            contentValues.put("cityCode", jtLogModel.getCityCode());
            contentValues.put("province", jtLogModel.getProvince());
            contentValues.put("city", jtLogModel.getCity());
            contentValues.put("appId", jtLogModel.getAppId());
            contentValues.put("Location_X", jtLogModel.getLocation_X());
            contentValues.put("Location_Y", jtLogModel.getLocation_Y());
            contentValues.put("appKey", jtLogModel.getAppKey());
            contentValues.put("sdkVersion", jtLogModel.getSdkVersion());
            contentValues.put("platform", jtLogModel.getPlatform());
            contentValues.put("uniqueIdentification", jtLogModel.getUniqueIdentification());
            long insert = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.insert("log", null, contentValues) : NBSSQLiteInstrumentation.insert(openDatabase, "log", null, contentValues);
            DatabaseManager.getInstance().closeDatabase();
            return insert != -1;
        }
        return false;
    }

    public List<JtLogModel> selectLog(String str) {
        SQLiteDatabase openDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor query = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.query("log", null, null, null, null, null, null, str) : NBSSQLiteInstrumentation.query(openDatabase, "log", null, null, null, null, null, null, str);
        ArrayList arrayList = new ArrayList();
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("type"));
            String string2 = query.getString(query.getColumnIndex("message"));
            String string3 = query.getString(query.getColumnIndex("operType"));
            String string4 = query.getString(query.getColumnIndex("user"));
            String string5 = query.getString(query.getColumnIndex("uuid"));
            String string6 = query.getString(query.getColumnIndex("version"));
            String string7 = query.getString(query.getColumnIndex("time"));
            String string8 = query.getString(query.getColumnIndex("province"));
            String string9 = query.getString(query.getColumnIndex("city"));
            String string10 = query.getString(query.getColumnIndex("provinceCode"));
            String string11 = query.getString(query.getColumnIndex("cityCode"));
            String string12 = query.getString(query.getColumnIndex("appId"));
            String string13 = query.getString(query.getColumnIndex("Location_X"));
            String string14 = query.getString(query.getColumnIndex("Location_Y"));
            ArrayList arrayList2 = arrayList;
            String string15 = query.getString(query.getColumnIndex("appKey"));
            String string16 = query.getString(query.getColumnIndex("sdkVersion"));
            String string17 = query.getString(query.getColumnIndex("platform"));
            String string18 = query.getString(query.getColumnIndex("uniqueIdentification"));
            int i = query.getInt(query.getColumnIndex("id"));
            Cursor cursor = query;
            JtLogModel jtLogModel = new JtLogModel();
            jtLogModel.setId(i);
            jtLogModel.setMessage(string2);
            jtLogModel.setType(string);
            jtLogModel.setOperType(string3);
            jtLogModel.setUser(string4);
            jtLogModel.setUuid(string5);
            jtLogModel.setVersion(string6);
            jtLogModel.setTime(string7);
            jtLogModel.setProvince(string8);
            jtLogModel.setCity(string9);
            jtLogModel.setProvinceCode(string10);
            jtLogModel.setCityCode(string11);
            jtLogModel.setAppId(string12);
            jtLogModel.setLocation_X(string13);
            jtLogModel.setLocation_Y(string14);
            jtLogModel.setAppKey(string15);
            jtLogModel.setSdkVersion(string16);
            jtLogModel.setPlatform(string17);
            jtLogModel.setUniqueIdentification(string18);
            arrayList = arrayList2;
            arrayList.add(jtLogModel);
            query = cursor;
        }
        query.close();
        DatabaseManager.getInstance().closeDatabase();
        return arrayList;
    }

    public void drapTable(int i) {
        SQLiteDatabase openDatabase = DatabaseManager.getInstance().openDatabase();
        String str = "delete from log where id<=" + i;
        if (openDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(openDatabase, str);
        } else {
            openDatabase.execSQL(str);
        }
        DatabaseManager.getInstance().closeDatabase();
    }

    public int getCount() {
        SQLiteDatabase openDatabase = DatabaseManager.getInstance().openDatabase();
        Cursor rawQuery = !(openDatabase instanceof SQLiteDatabase) ? openDatabase.rawQuery("select count(id) from log", null) : NBSSQLiteInstrumentation.rawQuery(openDatabase, "select count(id) from log", null);
        rawQuery.moveToFirst();
        int i = rawQuery.getInt(0);
        DatabaseManager.getInstance().closeDatabase();
        return i;
    }
}
