package com.sinovatech.unicom.common;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

@NBSInstrumented
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class DBOpenHelper extends SQLiteOpenHelper {
    public static final String DBName = "android_unicom_mobile_2.db";

    public DBOpenHelper(Context context) {
        super(context, DBName, (SQLiteDatabase.CursorFactory) null, 22);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_menudata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  version varchar(100) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_menudata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  version varchar(100) not null ,  jsoncontent BLOB not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_advertisedata (id integer primary key autoincrement,  provinceCode varchar(100) not null ,  cityCode varchar(100) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_advertisedata (id integer primary key autoincrement,  provinceCode varchar(100) not null ,  cityCode varchar(100) not null ,  jsoncontent BLOB not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_collectiondata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  menuid varchar(100) not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_collectiondata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  menuid varchar(100) not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_accountinfolistdata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  name varchar(100) not null ,  value varchar(100) not null ,  url varchar(300) not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_accountinfolistdata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  name varchar(100) not null ,  value varchar(100) not null ,  url varchar(300) not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_push_messagerecord (id integer primary key autoincrement,  message_id varchar(200) , message_title varchar(200) , message_content varchar(200) , message_url varchar(200) , message_type varchar(10) , message_usermobile varchar(20) , message_date varchar(20) ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_push_messagerecord (id integer primary key autoincrement,  message_id varchar(200) , message_title varchar(200) , message_content varchar(200) , message_url varchar(200) , message_type varchar(10) , message_usermobile varchar(20) , message_date varchar(20) ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_selectaccount (id integer primary key autoincrement,  accountname varchar(100) not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_selectaccount (id integer primary key autoincrement,  accountname varchar(100) not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_search_recommend_cache (id integer primary key autoincrement,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_search_recommend_cache (id integer primary key autoincrement,  jsoncontent BLOB not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS search_history (id integer primary key autoincrement, title varchar(255), url varchar(255), needLogin varchar(10),type varchar(30))");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS search_history (id integer primary key autoincrement, title varchar(255), url varchar(255), needLogin varchar(10),type varchar(30))");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_service_recommenddata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_service_recommenddata (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null ) ");
        }
        upgrade2(sQLiteDatabase);
        upgrade3(sQLiteDatabase);
        upgrade4(sQLiteDatabase);
        upgrade5(sQLiteDatabase);
        upgrade6(sQLiteDatabase);
        upgrade7(sQLiteDatabase);
        upgrade8(sQLiteDatabase);
        upgrade9(sQLiteDatabase);
        upgrade10(sQLiteDatabase);
        upgrade11(sQLiteDatabase);
        upgrade12(sQLiteDatabase);
        upgrade13(sQLiteDatabase);
        upgrade14(sQLiteDatabase);
        upgrade15(sQLiteDatabase);
        upgrade16(sQLiteDatabase);
        upgrade17(sQLiteDatabase);
        upgrade18(sQLiteDatabase);
        upgrade19(sQLiteDatabase);
        upgrade20(sQLiteDatabase);
        upgrade21(sQLiteDatabase);
        upgrade22(sQLiteDatabase);
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        while (i < i2) {
            switch (i) {
                case 1:
                    upgrade2(sQLiteDatabase);
                    break;
                case 2:
                    upgrade3(sQLiteDatabase);
                    break;
                case 3:
                    upgrade4(sQLiteDatabase);
                    break;
                case 4:
                    upgrade5(sQLiteDatabase);
                    break;
                case 5:
                    upgrade6(sQLiteDatabase);
                    break;
                case 6:
                    upgrade7(sQLiteDatabase);
                    break;
                case 7:
                    upgrade8(sQLiteDatabase);
                    break;
                case 8:
                    upgrade9(sQLiteDatabase);
                    break;
                case 9:
                    upgrade10(sQLiteDatabase);
                    break;
                case 10:
                    upgrade11(sQLiteDatabase);
                    break;
                case 11:
                    upgrade11(sQLiteDatabase);
                    upgrade12(sQLiteDatabase);
                    break;
                case 12:
                    upgrade13(sQLiteDatabase);
                    break;
                case 13:
                    upgrade14(sQLiteDatabase);
                    break;
                case 14:
                    upgrade15(sQLiteDatabase);
                    break;
                case 15:
                    upgrade16(sQLiteDatabase);
                    break;
                case 16:
                    upgrade17(sQLiteDatabase);
                    break;
                case 17:
                    upgrade18(sQLiteDatabase);
                    break;
                case 18:
                    upgrade19(sQLiteDatabase);
                    break;
                case 19:
                    upgrade20(sQLiteDatabase);
                    break;
                case 20:
                    upgrade21(sQLiteDatabase);
                    break;
                case 21:
                    upgrade22(sQLiteDatabase);
                    break;
            }
            i++;
        }
    }

    @Override // android.database.sqlite.SQLiteOpenHelper
    public void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_menudata");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_menudata");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_advertisedata");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_advertisedata");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_collectiondata");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_collectiondata");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_accountinfolistdata");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_accountinfolistdata");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_push_messagerecord");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_push_messagerecord");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_selectaccount");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_selectaccount");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_search_recommend_cache");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_search_recommend_cache");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS search_history");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS search_history");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_login_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_login_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_weather_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_weather_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_service_recommenddata");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_service_recommenddata");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_home_menu");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_home_menu");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_sharepresent_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_sharepresent_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_customskin_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_customskin_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_statistics_record");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_statistics_record");
        }
        onCreate(sQLiteDatabase);
    }

    private void upgrade2(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_login_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  date varchar(20) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_login_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  date varchar(20) not null ,  jsoncontent BLOB not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_home_menu (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_home_menu (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DELETE FROM unicommobile_basic_menudata");
        } else {
            sQLiteDatabase.execSQL("DELETE FROM unicommobile_basic_menudata");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_push_messagerecord add message_status varchar(10)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_push_messagerecord add message_status varchar(10)");
        }
    }

    private void upgrade3(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE unicommobile_basic_selectaccount");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE unicommobile_basic_selectaccount");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_selectaccount (id integer primary key autoincrement,  accountname varchar(100) not null, areaid varchar(20), accounttype varchar(10) ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_selectaccount (id integer primary key autoincrement,  accountname varchar(100) not null, areaid varchar(20), accounttype varchar(10) ) ");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_weather_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  date varchar(20) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_weather_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  date varchar(20) not null ,  jsoncontent BLOB not null ) ");
        }
    }

    private void upgrade4(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DELETE from unicommobile_basic_selectaccount");
        } else {
            sQLiteDatabase.execSQL("DELETE from unicommobile_basic_selectaccount");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add password varchar(50)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add password varchar(50)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add isbind varchar(10)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add isbind varchar(10)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add keyversion varchar(10)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add keyversion varchar(10)");
        }
    }

    private void upgrade5(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add iconurl varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add iconurl varchar(100)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_service_newitem (id integer primary key autoincrement,  menuid varchar(20) not null,  tag varchar(30) not null) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_service_newitem (id integer primary key autoincrement,  menuid varchar(20) not null,  tag varchar(30) not null) ");
        }
    }

    private void upgrade6(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_sharepresent_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_sharepresent_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_sharepresent_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  activityCode varchar(100) not null )");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_sharepresent_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  activityCode varchar(100) not null )");
        }
    }

    private void upgrade7(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_customskin_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_customskin_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_customskin_info (id integer primary key autoincrement,  skinid varchar(10) not null ,  type varchar(10),  status varchar(10),  url varchar(100))");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_customskin_info (id integer primary key autoincrement,  skinid varchar(10) not null ,  type varchar(10),  status varchar(10),  url varchar(100))");
        }
        for (int i = 0; i < 20; i++) {
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "INSERT INTO unicommobile_customskin_info (skinid) values ('0')");
            } else {
                sQLiteDatabase.execSQL("INSERT INTO unicommobile_customskin_info (skinid) values ('0')");
            }
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DELETE FROM unicommobile_customskin_info");
        } else {
            sQLiteDatabase.execSQL("DELETE FROM unicommobile_customskin_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_menudata add provincecode varchar(10)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_menudata add provincecode varchar(10)");
        }
    }

    private void upgrade8(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_statistics_record");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_statistics_record");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_statistics_record (id integer primary key autoincrement, recordId varchar(100) not null , record BLOB not null)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_statistics_record (id integer primary key autoincrement, recordId varchar(100) not null , record BLOB not null)");
        }
    }

    private void upgrade9(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS unicommobile_service_caidainewitem (id integer primary key autoincrement,  menuid varchar(20) not null,  tag varchar(30) not null) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS unicommobile_service_caidainewitem (id integer primary key autoincrement,  menuid varchar(20) not null,  tag varchar(30) not null) ");
        }
    }

    private void upgrade10(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_UserUnicomInfoDataCenter_info");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_UserUnicomInfoDataCenter_info");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_UserUnicomInfoDataCenter_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null, type varchar(100) not null )");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_UserUnicomInfoDataCenter_info (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null, type varchar(100) not null )");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_history_record");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_history_record");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_history_record (id integer primary key autoincrement,mobile varchar(100) not null , title varchar(100) not null ,time varchar(100) not null ,record BLOB not null)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_history_record (id integer primary key autoincrement,mobile varchar(100) not null , title varchar(100) not null ,time varchar(100) not null ,record BLOB not null)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_history_record2");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_history_record2");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_history_record2 (id integer primary key autoincrement,mobile varchar(100) not null ,title varchar(100) not null ,time varchar(100) not null, record BLOB not null)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_history_record2 (id integer primary key autoincrement,mobile varchar(100) not null ,title varchar(100) not null ,time varchar(100) not null, record BLOB not null)");
        }
    }

    private void upgrade11(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_history_record");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_history_record");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_history_record (id integer primary key autoincrement,mobile varchar(100) not null , title varchar(100) not null ,time varchar(100) not null ,record BLOB not null)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_history_record (id integer primary key autoincrement,mobile varchar(100) not null , title varchar(100) not null ,time varchar(100) not null ,record BLOB not null)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_basic_history_record2");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_basic_history_record2");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_basic_history_record2 (id integer primary key autoincrement,mobile varchar(100) not null ,title varchar(100) not null ,time varchar(100) not null, record BLOB not null)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_basic_history_record2 (id integer primary key autoincrement,mobile varchar(100) not null ,title varchar(100) not null ,time varchar(100) not null, record BLOB not null)");
        }
    }

    private void upgrade12(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DROP TABLE IF EXISTS unicommobile_baidu_history_record");
        } else {
            sQLiteDatabase.execSQL("DROP TABLE IF EXISTS unicommobile_baidu_history_record");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE unicommobile_baidu_history_record (id integer primary key autoincrement,recordId varchar(100) not null,mobile varchar(100) not null,record BLOB not null)");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE unicommobile_baidu_history_record (id integer primary key autoincrement,recordId varchar(100) not null,mobile varchar(100) not null,record BLOB not null)");
        }
    }

    private void upgrade13(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add cid varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add cid varchar(100)");
        }
    }

    private void upgrade14(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_search_recommend_cache add mobile varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_search_recommend_cache add mobile varchar(100)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add intact varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add intact varchar(100)");
        }
    }

    private void upgrade15(SQLiteDatabase sQLiteDatabase) {
        boolean z = sQLiteDatabase instanceof SQLiteDatabase;
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_basic_selectaccount add yw_code varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_basic_selectaccount add yw_code varchar(100)");
        }
        if (z) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS baidusearch_history (id integer primary key autoincrement, title varchar(255), url varchar(255), needLogin varchar(10),type varchar(30))");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS baidusearch_history (id integer primary key autoincrement, title varchar(255), url varchar(255), needLogin varchar(10),type varchar(30))");
        }
    }

    private void upgrade16(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE search_history add account varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE search_history add account varchar(100)");
        }
    }

    private void upgrade17(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE search_history add gametype varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE search_history add gametype varchar(100)");
        }
    }

    private void upgrade18(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "CREATE TABLE IF NOT EXISTS unicom_electronic_id_card (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null ) ");
        } else {
            sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS unicom_electronic_id_card (id integer primary key autoincrement,  usermobile varchar(100) not null ,  jsoncontent BLOB not null ) ");
        }
    }

    private void upgrade19(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_push_messagerecord add secondType varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_push_messagerecord add secondType varchar(100)");
        }
    }

    private void upgrade20(SQLiteDatabase sQLiteDatabase) {
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_push_messagerecord add message_endtime varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_push_messagerecord add message_endtime varchar(100)");
        }
    }

    private void upgrade21(SQLiteDatabase sQLiteDatabase) {
        boolean checkColumnExist1 = checkColumnExist1(sQLiteDatabase, "unicommobile_push_messagerecord", "message_msgtype");
        MsLogUtil.m7979d("数据库", "数据库中是否存在 message_msgtype字段 = " + checkColumnExist1);
        if (checkColumnExist1) {
            return;
        }
        MsLogUtil.m7979d("数据库", "添加message_msgtype字段");
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_push_messagerecord add message_msgtype varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_push_messagerecord add message_msgtype varchar(100)");
        }
    }

    private void upgrade22(SQLiteDatabase sQLiteDatabase) {
        boolean checkColumnExist1 = checkColumnExist1(sQLiteDatabase, "unicommobile_push_messagerecord", "message_serviceId");
        MsLogUtil.m7979d("数据库", "数据库中是否存在 message_serviceId字段 = " + checkColumnExist1);
        if (checkColumnExist1) {
            return;
        }
        MsLogUtil.m7979d("数据库", "添加message_serviceId字段");
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "ALTER TABLE unicommobile_push_messagerecord add message_serviceId varchar(100)");
        } else {
            sQLiteDatabase.execSQL("ALTER TABLE unicommobile_push_messagerecord add message_serviceId varchar(100)");
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0062, code lost:
        if (r0.isClosed() == false) goto L16;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private boolean checkColumnExist1(android.database.sqlite.SQLiteDatabase r5, java.lang.String r6, java.lang.String r7) {
        /*
            r4 = this;
            r0 = 0
            r1 = 0
            java.lang.StringBuilder r2 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r2.<init>()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r3 = "SELECT * FROM "
            r2.append(r3)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r2.append(r6)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r6 = " LIMIT 0"
            r2.append(r6)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            java.lang.String r6 = r2.toString()     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            boolean r2 = r5 instanceof android.database.sqlite.SQLiteDatabase     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            if (r2 != 0) goto L21
            android.database.Cursor r5 = r5.rawQuery(r6, r0)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            goto L27
        L21:
            android.database.sqlite.SQLiteDatabase r5 = (android.database.sqlite.SQLiteDatabase) r5     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            android.database.Cursor r5 = com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation.rawQuery(r5, r6, r0)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
        L27:
            r0 = r5
            if (r0 == 0) goto L33
            int r5 = r0.getColumnIndex(r7)     // Catch: java.lang.Throwable -> L3f java.lang.Exception -> L41
            r6 = -1
            if (r5 == r6) goto L33
            r5 = 1
            r1 = r5
        L33:
            if (r0 == 0) goto L65
            boolean r5 = r0.isClosed()
            if (r5 != 0) goto L65
        L3b:
            r0.close()
            goto L65
        L3f:
            r5 = move-exception
            goto L66
        L41:
            r5 = move-exception
            java.lang.String r6 = "DBOpenHeleper"
            java.lang.StringBuilder r7 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L3f
            r7.<init>()     // Catch: java.lang.Throwable -> L3f
            java.lang.String r2 = "checkColumnExists"
            r7.append(r2)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = r5.getMessage()     // Catch: java.lang.Throwable -> L3f
            r7.append(r5)     // Catch: java.lang.Throwable -> L3f
            java.lang.String r5 = r7.toString()     // Catch: java.lang.Throwable -> L3f
            com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil.m7977e(r6, r5)     // Catch: java.lang.Throwable -> L3f
            if (r0 == 0) goto L65
            boolean r5 = r0.isClosed()
            if (r5 != 0) goto L65
            goto L3b
        L65:
            return r1
        L66:
            if (r0 == 0) goto L71
            boolean r6 = r0.isClosed()
            if (r6 != 0) goto L71
            r0.close()
        L71:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.sinovatech.unicom.common.DBOpenHelper.checkColumnExist1(android.database.sqlite.SQLiteDatabase, java.lang.String, java.lang.String):boolean");
    }
}
