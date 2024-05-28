package com.sinovatech.unicom.separatemodule.baidumap;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.sinovatech.unicom.common.DBOpenHelper;
import com.sinovatech.unicom.separatemodule.baidumap.entity.BaiduSearchEntity;
import java.util.ArrayList;
import java.util.List;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BaiduSearchDao {
    private String LOG = getClass().getSimpleName();
    private DBOpenHelper dbOpenHelper;

    public BaiduSearchDao(Context context) {
        this.dbOpenHelper = new DBOpenHelper(context);
    }

    public void insertHistory(String str) {
        SQLiteDatabase writableDatabase = this.dbOpenHelper.getWritableDatabase();
        try {
            try {
                try {
                    writableDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (writableDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(writableDatabase, "delete from baidusearch_history where title=? ", strArr);
                    } else {
                        writableDatabase.execSQL("delete from baidusearch_history where title=? ", strArr);
                    }
                    String[] strArr2 = {str};
                    if (writableDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(writableDatabase, "insert into baidusearch_history(title,url,needLogin,type) values(?,?,?,?)", strArr2);
                    } else {
                        writableDatabase.execSQL("insert into baidusearch_history(title,url,needLogin,type) values(?,?,?,?)", strArr2);
                    }
                    Cursor rawQuery = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("select title,url,needLogin,type from baidusearch_history order by id desc limit 100 offset 8", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "select title,url,needLogin,type from baidusearch_history order by id desc limit 100 offset 8", null);
                    while (rawQuery.moveToNext()) {
                        String[] strArr3 = {rawQuery.getString(rawQuery.getColumnIndex("title"))};
                        if (writableDatabase instanceof SQLiteDatabase) {
                            NBSSQLiteInstrumentation.execSQL(writableDatabase, "delete from baidusearch_history where title=? ", strArr3);
                        } else {
                            writableDatabase.execSQL("delete from baidusearch_history where title=? ", strArr3);
                        }
                    }
                    writableDatabase.setTransactionSuccessful();
                    if (writableDatabase == null || !writableDatabase.inTransaction()) {
                        return;
                    }
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    if (writableDatabase != null) {
                        try {
                            if (writableDatabase.inTransaction()) {
                                writableDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (writableDatabase == null || !writableDatabase.inTransaction()) {
                    return;
                }
                writableDatabase.endTransaction();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public void deleteHistory(String str) {
        SQLiteDatabase writableDatabase = this.dbOpenHelper.getWritableDatabase();
        try {
            try {
                try {
                    writableDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (writableDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(writableDatabase, "delete from baidusearch_history where title=? ", strArr);
                    } else {
                        writableDatabase.execSQL("delete from baidusearch_history where title=? ", strArr);
                    }
                    writableDatabase.setTransactionSuccessful();
                    if (writableDatabase == null || !writableDatabase.inTransaction()) {
                        return;
                    }
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    if (writableDatabase != null) {
                        try {
                            if (writableDatabase.inTransaction()) {
                                writableDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (writableDatabase == null || !writableDatabase.inTransaction()) {
                    return;
                }
                writableDatabase.endTransaction();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public List<BaiduSearchEntity> getHistory() {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase writableDatabase = this.dbOpenHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
            try {
                try {
                    cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("select title,url,needLogin,type from baidusearch_history order by id desc", null) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "select title,url,needLogin,type from baidusearch_history order by id desc", null);
                    while (cursor.moveToNext()) {
                        String string = cursor.getString(cursor.getColumnIndex("title"));
                        String string2 = cursor.getString(cursor.getColumnIndex("url"));
                        String string3 = cursor.getString(cursor.getColumnIndex("needLogin"));
                        String string4 = cursor.getString(cursor.getColumnIndex("type"));
                        BaiduSearchEntity baiduSearchEntity = new BaiduSearchEntity();
                        baiduSearchEntity.setTitle(string);
                        baiduSearchEntity.setUrl(string2);
                        baiduSearchEntity.setType(string4);
                        baiduSearchEntity.setNeedLogin(string3);
                        baiduSearchEntity.setSectionsIndex(0);
                        baiduSearchEntity.setSectionsTitle("历史搜索");
                        arrayList.add(baiduSearchEntity);
                    }
                    if (cursor != null && !cursor.isClosed()) {
                        cursor.close();
                    }
                } catch (Throwable th) {
                    if (cursor != null) {
                        try {
                            if (!cursor.isClosed()) {
                                cursor.close();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null && !cursor.isClosed()) {
                    cursor.close();
                }
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
        return arrayList;
    }

    public void updateRecommendJsonData(String str, String str2) {
        SQLiteDatabase writableDatabase = this.dbOpenHelper.getWritableDatabase();
        try {
            try {
                try {
                    writableDatabase.beginTransaction();
                    String[] strArr = {str};
                    if (writableDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(writableDatabase, "delete from unicommobile_search_recommend_cache where mobile=?", strArr);
                    } else {
                        writableDatabase.execSQL("delete from unicommobile_search_recommend_cache where mobile=?", strArr);
                    }
                    Object[] objArr = {str, str2.getBytes()};
                    if (writableDatabase instanceof SQLiteDatabase) {
                        NBSSQLiteInstrumentation.execSQL(writableDatabase, "insert into unicommobile_search_recommend_cache(mobile,jsoncontent)  values(?,?)", objArr);
                    } else {
                        writableDatabase.execSQL("insert into unicommobile_search_recommend_cache(mobile,jsoncontent)  values(?,?)", objArr);
                    }
                    writableDatabase.setTransactionSuccessful();
                    if (writableDatabase == null || !writableDatabase.inTransaction()) {
                        return;
                    }
                    writableDatabase.endTransaction();
                } catch (Throwable th) {
                    if (writableDatabase != null) {
                        try {
                            if (writableDatabase.inTransaction()) {
                                writableDatabase.endTransaction();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                if (writableDatabase == null || !writableDatabase.inTransaction()) {
                    return;
                }
                writableDatabase.endTransaction();
            }
        } catch (Exception e3) {
            e3.printStackTrace();
        }
    }

    public String getRecommendJsonData(String str) {
        String str2;
        str2 = "";
        SQLiteDatabase writableDatabase = this.dbOpenHelper.getWritableDatabase();
        Cursor cursor = null;
        try {
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            try {
                String[] strArr = {str};
                cursor = !(writableDatabase instanceof SQLiteDatabase) ? writableDatabase.rawQuery("select jsoncontent from unicommobile_search_recommend_cache where mobile=?", strArr) : NBSSQLiteInstrumentation.rawQuery(writableDatabase, "select jsoncontent from unicommobile_search_recommend_cache where mobile=?", strArr);
                str2 = cursor.moveToFirst() ? new String(cursor.getBlob(cursor.getColumnIndex("jsoncontent"))) : "";
            } catch (Exception e2) {
                e2.printStackTrace();
                if (cursor != null) {
                    if (!cursor.isClosed()) {
                        cursor.close();
                    }
                }
            }
            if (cursor != null) {
                if (!cursor.isClosed()) {
                    cursor.close();
                }
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
                }
            }
            throw th;
        }
    }
}
