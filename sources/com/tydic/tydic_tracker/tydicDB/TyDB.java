package com.tydic.tydic_tracker.tydicDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.tydic.tydic_tracker.app.TYUtil;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class TyDB {
    private static String DB_NAME = "ty_tracker.db";
    private static int DB_VERSION = 3;
    private static String SQL_ANR = "create table ty_anr(id integer primary key autoincrement,ses_id text,anr_id text,st integer,at text,sd_raw text,ath text)";
    private static String SQL_BREAKDOWN = "create table ty_break(id integer primary key autoincrement,ses_id text,class_name text,method_name text,lineNum text,crash_type int,track_details text,target_name text,event_tag text,crash_name text,is_thread_break int,timestamp integer,collect_time integer,event_id text)";
    private static String SQL_EVENT = "create table ty_event(id integer primary key autoincrement,ses_id text,timestamp integer,event_type text,action_title text,event_tag text)";
    private static String SQL_HTTP = "create table ty_http(id integer primary key autoincrement,ses_id text,execute_time integer,url text,event_tag text,target_name text,response_time integer,is_error int,error_type text,error_code text,domain text,request_id text,http_method text,send integer,reveive integer)";
    private static String SQL_Resource = "create table ty_resource(id integer primary key autoincrement,ses_id text,timestamp integer,request_id text,link_url text,domain text,ft integer,uri text,rs integer,le integer,resource_time text)";
    private static String SQL_SESSION = "create table ty_session(id integer primary key autoincrement,ses_id text,st integer)";
    private static String SQL_STARTUPS = "create table ty_startups(id integer primary key autoincrement,ses_id text,start_milli integer,end_milli integer)";
    private static String SQL_VIEW = "create table ty_view(id integer primary key autoincrement,ses_id text,timestamp integer,page_name text,view_class text,view_event text,pre_cn text)";
    private static String SQL_WebHttp = "create table ty_webhttp(id integer primary key autoincrement,ses_id text,request_id text,timestamp integer,target_name text,sender_name text,link_url text,domain text,uri text,req_method text,req_time integer,is_err integer,res_time integer,req_url text)";
    SQLiteDatabase writableDatabase;

    @NBSInstrumented
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class UserDBOpenHelper extends SQLiteOpenHelper {
        private Context context;

        public UserDBOpenHelper(Context context) {
            super(context, TyDB.DB_NAME, (SQLiteDatabase.CursorFactory) null, TyDB.DB_VERSION);
            this.context = context;
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onCreate(SQLiteDatabase sQLiteDatabase) {
            String str = TyDB.SQL_WebHttp;
            boolean z = sQLiteDatabase instanceof SQLiteDatabase;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            String str2 = TyDB.SQL_Resource;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
            String str3 = TyDB.SQL_SESSION;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str3);
            } else {
                sQLiteDatabase.execSQL(str3);
            }
            String str4 = TyDB.SQL_STARTUPS;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str4);
            } else {
                sQLiteDatabase.execSQL(str4);
            }
            String str5 = TyDB.SQL_EVENT;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str5);
            } else {
                sQLiteDatabase.execSQL(str5);
            }
            String str6 = TyDB.SQL_HTTP;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str6);
            } else {
                sQLiteDatabase.execSQL(str6);
            }
            String str7 = TyDB.SQL_VIEW;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str7);
            } else {
                sQLiteDatabase.execSQL(str7);
            }
            String str8 = TyDB.SQL_BREAKDOWN;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str8);
            } else {
                sQLiteDatabase.execSQL(str8);
            }
            String str9 = TyDB.SQL_ANR;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str9);
            } else {
                sQLiteDatabase.execSQL(str9);
            }
        }

        @Override // android.database.sqlite.SQLiteOpenHelper
        public void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
            boolean z = sQLiteDatabase instanceof SQLiteDatabase;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_session");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_session");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_startups");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_startups");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_event");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_event");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_http");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_http");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_view");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_view");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_break");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_break");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_anr");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_anr");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_resource");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_resource");
            }
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "drop table if exists ty_webhttp");
            } else {
                sQLiteDatabase.execSQL("drop table if exists ty_webhttp");
            }
            String str = TyDB.SQL_SESSION;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str);
            } else {
                sQLiteDatabase.execSQL(str);
            }
            String str2 = TyDB.SQL_STARTUPS;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str2);
            } else {
                sQLiteDatabase.execSQL(str2);
            }
            String str3 = TyDB.SQL_EVENT;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str3);
            } else {
                sQLiteDatabase.execSQL(str3);
            }
            String str4 = TyDB.SQL_HTTP;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str4);
            } else {
                sQLiteDatabase.execSQL(str4);
            }
            String str5 = TyDB.SQL_VIEW;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str5);
            } else {
                sQLiteDatabase.execSQL(str5);
            }
            String str6 = TyDB.SQL_BREAKDOWN;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str6);
            } else {
                sQLiteDatabase.execSQL(str6);
            }
            String str7 = TyDB.SQL_ANR;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str7);
            } else {
                sQLiteDatabase.execSQL(str7);
            }
            String str8 = TyDB.SQL_WebHttp;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str8);
            } else {
                sQLiteDatabase.execSQL(str8);
            }
            String str9 = TyDB.SQL_Resource;
            if (z) {
                NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, str9);
            } else {
                sQLiteDatabase.execSQL(str9);
            }
        }
    }

    public synchronized void OpenUserDb(Context context) {
        this.writableDatabase = new UserDBOpenHelper(context).getWritableDatabase();
    }

    public JSONArray FindAllSession() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_session", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_session", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("st"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("st", string2);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertSessionData(TySessionEntity tySessionEntity) {
        String ses_id = tySessionEntity.getSes_id();
        long st = tySessionEntity.getSt();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("st", Long.valueOf(st));
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_session", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_session", null, contentValues);
    }

    public JSONArray FindAllResource() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_resource", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_resource", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("timestamp"));
            String string3 = query.getString(query.getColumnIndex("request_id"));
            String string4 = query.getString(query.getColumnIndex("link_url"));
            String string5 = query.getString(query.getColumnIndex("ft"));
            String string6 = query.getString(query.getColumnIndex("domain"));
            String string7 = query.getString(query.getColumnIndex("uri"));
            String string8 = query.getString(query.getColumnIndex("rs"));
            String string9 = query.getString(query.getColumnIndex("le"));
            String string10 = query.getString(query.getColumnIndex("resource_time"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("request_id", string3);
            jSONObject.put("timestamp", string2);
            jSONObject.put("link_url", string4);
            jSONObject.put("domain", string6);
            jSONObject.put("uri", string7);
            jSONObject.put("ft", string5);
            jSONObject.put("rs", string8);
            jSONObject.put("le", string9);
            jSONObject.put("resource_time", string10);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertAllResourceData(TyResourceEntity tyResourceEntity) {
        String ses_id = tyResourceEntity.getSes_id();
        long timestamp = tyResourceEntity.getTimestamp();
        String request_id = tyResourceEntity.getRequest_id();
        String link_url = tyResourceEntity.getLink_url();
        String domain = tyResourceEntity.getDomain();
        long ft = tyResourceEntity.getFt();
        String uri = tyResourceEntity.getUri();
        long rs = tyResourceEntity.getRs();
        long le = tyResourceEntity.getLe();
        String resource_time = tyResourceEntity.getResource_time();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("request_id", request_id);
        contentValues.put("timestamp", Long.valueOf(timestamp));
        contentValues.put("link_url", link_url);
        contentValues.put("domain", domain);
        contentValues.put("uri", uri);
        contentValues.put("ft", Long.valueOf(ft));
        contentValues.put("rs", Long.valueOf(rs));
        contentValues.put("le", Long.valueOf(le));
        contentValues.put("resource_time", resource_time);
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_resource", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_resource", null, contentValues);
    }

    public JSONArray FindAllWebHttp() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        for (Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_webhttp", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_webhttp", null, null, null, null, null, null, null); query.moveToNext(); query = query) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("request_id"));
            String string3 = query.getString(query.getColumnIndex("target_name"));
            String string4 = query.getString(query.getColumnIndex("timestamp"));
            String string5 = query.getString(query.getColumnIndex("sender_name"));
            String string6 = query.getString(query.getColumnIndex("link_url"));
            String string7 = query.getString(query.getColumnIndex("domain"));
            String string8 = query.getString(query.getColumnIndex("uri"));
            String string9 = query.getString(query.getColumnIndex("req_method"));
            String string10 = query.getString(query.getColumnIndex("req_time"));
            String string11 = query.getString(query.getColumnIndex("is_err"));
            String string12 = query.getString(query.getColumnIndex("res_time"));
            String string13 = query.getString(query.getColumnIndex("req_url"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("request_id", string2);
            jSONObject.put("target_name", string3);
            jSONObject.put("timestamp", string4);
            jSONObject.put("sender_name", string5);
            jSONObject.put("link_url", string6);
            jSONObject.put("domain", string7);
            jSONObject.put("uri", string8);
            jSONObject.put("req_method", string9);
            jSONObject.put("req_time", string10);
            jSONObject.put("is_err", string11);
            jSONObject.put("res_time", string12);
            jSONObject.put("req_url", string13);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertAllWebHttpData(TyWebHttpEntity tyWebHttpEntity) {
        String ses_id = tyWebHttpEntity.getSes_id();
        String request_id = tyWebHttpEntity.getRequest_id();
        long timestamp = tyWebHttpEntity.getTimestamp();
        String target_name = tyWebHttpEntity.getTarget_name();
        String sender_name = tyWebHttpEntity.getSender_name();
        String link_url = tyWebHttpEntity.getLink_url();
        String domain = tyWebHttpEntity.getDomain();
        String uri = tyWebHttpEntity.getUri();
        String req_method = tyWebHttpEntity.getReq_method();
        long req_time = tyWebHttpEntity.getReq_time();
        int is_err = tyWebHttpEntity.getIs_err();
        long res_time = tyWebHttpEntity.getRes_time();
        String req_url = tyWebHttpEntity.getReq_url();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("request_id", request_id);
        contentValues.put("timestamp", Long.valueOf(timestamp));
        contentValues.put("target_name", target_name);
        contentValues.put("sender_name", sender_name);
        contentValues.put("link_url", link_url);
        contentValues.put("domain", domain);
        contentValues.put("uri", uri);
        contentValues.put("req_method", req_method);
        contentValues.put("req_time", Long.valueOf(req_time));
        contentValues.put("is_err", Integer.valueOf(is_err));
        contentValues.put("res_time", Long.valueOf(res_time));
        contentValues.put("req_url", req_url);
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_webhttp", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_webhttp", null, contentValues);
    }

    public JSONArray FindAllStartup() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_startups", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_startups", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("start_milli"));
            String string3 = query.getString(query.getColumnIndex("end_milli"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("start_milli", string2);
            jSONObject.put("end_milli", string3);
            try {
                jSONObject.put("durationMicro", String.valueOf(Long.parseLong(string3) - Long.parseLong(string2)));
            } catch (Exception e) {
                e.printStackTrace();
            }
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertStartupsData(TyStartupsEntity tyStartupsEntity) {
        String ses_id = tyStartupsEntity.getSes_id();
        long start_milli = tyStartupsEntity.getStart_milli();
        long end_milli = tyStartupsEntity.getEnd_milli();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("start_milli", Long.valueOf(start_milli));
        contentValues.put("end_milli", Long.valueOf(end_milli));
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_startups", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_startups", null, contentValues);
    }

    public JSONArray FindAllEvent() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_event", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_event", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("timestamp"));
            String string3 = query.getString(query.getColumnIndex("event_type"));
            String string4 = query.getString(query.getColumnIndex("action_title"));
            String string5 = query.getString(query.getColumnIndex("event_tag"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("timestamp", string2);
            jSONObject.put("event_type", string3);
            jSONObject.put("action_title", string4);
            jSONObject.put("event_tag", string5);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertEventData(TyEventEntity tyEventEntity) {
        String ses_id = tyEventEntity.getSes_id();
        long timestamp = tyEventEntity.getTimestamp();
        String event_type = tyEventEntity.getEvent_type();
        String action_title = tyEventEntity.getAction_title();
        String event_tag = tyEventEntity.getEvent_tag();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("timestamp", Long.valueOf(timestamp));
        contentValues.put("event_type", event_type);
        contentValues.put("action_title", action_title);
        contentValues.put("event_tag", event_tag);
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_event", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_event", null, contentValues);
    }

    public JSONArray FindAllHttp() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_http", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_http", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("execute_time"));
            String string3 = query.getString(query.getColumnIndex("url"));
            String string4 = query.getString(query.getColumnIndex("target_name"));
            String string5 = query.getString(query.getColumnIndex("event_tag"));
            String string6 = query.getString(query.getColumnIndex("response_time"));
            int parseInt = Integer.parseInt(query.getString(query.getColumnIndex("is_error")));
            String string7 = query.getString(query.getColumnIndex("error_type"));
            String string8 = query.getString(query.getColumnIndex("error_code"));
            String string9 = query.getString(query.getColumnIndex("domain"));
            String string10 = query.getString(query.getColumnIndex("request_id"));
            String string11 = query.getString(query.getColumnIndex("http_method"));
            String string12 = query.getString(query.getColumnIndex("send"));
            String string13 = query.getString(query.getColumnIndex("reveive"));
            Cursor cursor = query;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("execute_time", string2);
            jSONObject.put("url", string3);
            jSONObject.put("target_name", string4);
            jSONObject.put("event_tag", string5);
            jSONObject.put("response_time", string6);
            jSONObject.put("is_error", parseInt);
            jSONObject.put("error_type", string7);
            jSONObject.put("error_code", string8);
            jSONObject.put("domain", string9);
            jSONObject.put("request_id", string10);
            jSONObject.put("http_method", string11);
            jSONObject.put("send", string12);
            jSONObject.put("reveive", string13);
            jSONArray = jSONArray;
            jSONArray.put(jSONObject);
            query = cursor;
        }
        return jSONArray;
    }

    public long insertHttpData(TyRequestEntity tyRequestEntity) {
        String ses_id = tyRequestEntity.getSes_id();
        long execute_time = tyRequestEntity.getExecute_time();
        String url = tyRequestEntity.getUrl();
        String target_name = tyRequestEntity.getTarget_name();
        String event_tag = tyRequestEntity.getEvent_tag();
        int response_time = tyRequestEntity.getResponse_time();
        int is_error = tyRequestEntity.getIs_error();
        String error_type = tyRequestEntity.getError_type();
        String error_code = tyRequestEntity.getError_code();
        String domain = tyRequestEntity.getDomain();
        String random = TYUtil.getRandom();
        String http_method = tyRequestEntity.getHttp_method();
        int send = tyRequestEntity.getSend();
        int reveive = tyRequestEntity.getReveive();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("execute_time", Long.valueOf(execute_time));
        contentValues.put("url", url);
        contentValues.put("target_name", target_name);
        contentValues.put("event_tag", event_tag);
        contentValues.put("response_time", Integer.valueOf(response_time));
        contentValues.put("is_error", Integer.valueOf(is_error));
        contentValues.put("error_type", error_type);
        contentValues.put("error_code", error_code);
        contentValues.put("domain", domain);
        contentValues.put("request_id", random);
        contentValues.put("http_method", http_method);
        contentValues.put("send", Integer.valueOf(send));
        contentValues.put("reveive", Integer.valueOf(reveive));
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_http", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_http", null, contentValues);
    }

    public JSONArray FindAllView() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_view", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_view", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("timestamp"));
            String string3 = query.getString(query.getColumnIndex("page_name"));
            String string4 = query.getString(query.getColumnIndex("view_class"));
            String string5 = query.getString(query.getColumnIndex("view_event"));
            String string6 = query.getString(query.getColumnIndex("pre_cn"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("timestamp", string2);
            jSONObject.put("page_name", string3);
            jSONObject.put("view_class", string4);
            jSONObject.put("view_event", string5);
            jSONObject.put("pre_cn", string6);
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertViewData(TyViewEntity tyViewEntity) {
        String ses_id = tyViewEntity.getSes_id();
        long timestamp = tyViewEntity.getTimestamp();
        String page_name = tyViewEntity.getPage_name();
        String view_class = tyViewEntity.getView_class();
        String view_event = tyViewEntity.getView_event();
        String pre_cn = tyViewEntity.getPre_cn();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("timestamp", Long.valueOf(timestamp));
        contentValues.put("page_name", page_name);
        contentValues.put("view_class", view_class);
        contentValues.put("view_event", view_event);
        contentValues.put("pre_cn", pre_cn);
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_view", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_view", null, contentValues);
    }

    public JSONArray FindAllBreakdown() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_break", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_break", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("class_name"));
            String string3 = query.getString(query.getColumnIndex("method_name"));
            String string4 = query.getString(query.getColumnIndex("lineNum"));
            String string5 = query.getString(query.getColumnIndex("track_details"));
            String string6 = query.getString(query.getColumnIndex("timestamp"));
            String string7 = query.getString(query.getColumnIndex("collect_time"));
            String string8 = query.getString(query.getColumnIndex("target_name"));
            String string9 = query.getString(query.getColumnIndex("crash_name"));
            String string10 = query.getString(query.getColumnIndex("event_id"));
            Cursor cursor = query;
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("crash_type", Integer.parseInt(query.getString(query.getColumnIndex("crash_type"))));
            jSONObject.put("class_name", string2);
            jSONObject.put("method_name", string3);
            jSONObject.put("lineNum", string4);
            jSONObject.put("track_details", string5);
            jSONObject.put("is_thread_break", Integer.parseInt(query.getString(query.getColumnIndex("is_thread_break"))));
            jSONObject.put("timestamp", string6);
            jSONObject.put("collect_time", string7);
            jSONObject.put("target_name", string8);
            jSONObject.put("crash_name", string9);
            jSONObject.put("event_id", string10);
            jSONArray = jSONArray;
            jSONArray.put(jSONObject);
            query = cursor;
        }
        return jSONArray;
    }

    public long insertBreakdownData(TyBreakdownsEntity tyBreakdownsEntity) {
        String ses_id = tyBreakdownsEntity.getSes_id();
        String class_name = tyBreakdownsEntity.getClass_name();
        String method_name = tyBreakdownsEntity.getMethod_name();
        String lineNum = tyBreakdownsEntity.getLineNum();
        int crash_type = tyBreakdownsEntity.getCrash_type();
        String track_details = tyBreakdownsEntity.getTrack_details();
        String target_name = tyBreakdownsEntity.getTarget_name();
        String event_tag = tyBreakdownsEntity.getEvent_tag();
        String crash_name = tyBreakdownsEntity.getCrash_name();
        int is_thread_break = tyBreakdownsEntity.getIs_thread_break();
        long timestamp = tyBreakdownsEntity.getTimestamp();
        long collect_time = tyBreakdownsEntity.getCollect_time();
        String event_id = tyBreakdownsEntity.getEvent_id();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("class_name", class_name);
        contentValues.put("method_name", method_name);
        contentValues.put("lineNum", lineNum);
        contentValues.put("crash_type", Integer.valueOf(crash_type));
        contentValues.put("track_details", track_details);
        contentValues.put("target_name", target_name);
        contentValues.put("event_tag", event_tag);
        contentValues.put("crash_name", crash_name);
        contentValues.put("is_thread_break", Integer.valueOf(is_thread_break));
        contentValues.put("timestamp", Long.valueOf(timestamp));
        contentValues.put("collect_time", Long.valueOf(collect_time));
        contentValues.put("event_id", event_id);
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_break", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_break", null, contentValues);
    }

    public JSONArray FindAllAnr() throws JSONException {
        JSONArray jSONArray = new JSONArray();
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        Cursor query = !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.query("ty_anr", null, null, null, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase, "ty_anr", null, null, null, null, null, null, null);
        while (query.moveToNext()) {
            String string = query.getString(query.getColumnIndex("ses_id"));
            String string2 = query.getString(query.getColumnIndex("st"));
            String string3 = query.getString(query.getColumnIndex("anr_id"));
            String string4 = query.getString(query.getColumnIndex("at"));
            String string5 = query.getString(query.getColumnIndex("sd_raw"));
            String string6 = query.getString(query.getColumnIndex("ath"));
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("ses_id", string);
            jSONObject.put("st", string2);
            jSONObject.put("anr_id", string3);
            jSONObject.put("at", string4);
            jSONObject.put("sd_raw", string5);
            jSONObject.put("ath", string6);
            jSONObject.put("alc", "");
            jSONObject.put("alt", "");
            jSONObject.put("atr", "");
            jSONArray.put(jSONObject);
        }
        return jSONArray;
    }

    public long insertAnrData(TyAnrEntity tyAnrEntity) {
        String ses_id = tyAnrEntity.getSes_id();
        String anr_id = tyAnrEntity.getAnr_id();
        long st = tyAnrEntity.getSt();
        String at = tyAnrEntity.getAt();
        String sd_raw = tyAnrEntity.getSd_raw();
        String ath = tyAnrEntity.getAth();
        ContentValues contentValues = new ContentValues();
        contentValues.put("ses_id", ses_id);
        contentValues.put("anr_id", anr_id);
        contentValues.put("st", Long.valueOf(st));
        contentValues.put("at", at);
        contentValues.put("sd_raw", sd_raw);
        contentValues.put("ath", ath);
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        return !(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.insert("ty_anr", null, contentValues) : NBSSQLiteInstrumentation.insert(sQLiteDatabase, "ty_anr", null, contentValues);
    }

    public boolean delete(String str) {
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        String[] strArr = {str};
        return (!(sQLiteDatabase instanceof SQLiteDatabase) ? sQLiteDatabase.delete("userinfo", "account = ?", strArr) : NBSSQLiteInstrumentation.delete(sQLiteDatabase, "userinfo", "account = ?", strArr)) > 0;
    }

    public void clearTable() {
        SQLiteDatabase sQLiteDatabase = this.writableDatabase;
        if (sQLiteDatabase instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase, "DELETE FROM ty_session");
        } else {
            sQLiteDatabase.execSQL("DELETE FROM ty_session");
        }
        SQLiteDatabase sQLiteDatabase2 = this.writableDatabase;
        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase2, "DELETE FROM ty_startups");
        } else {
            sQLiteDatabase2.execSQL("DELETE FROM ty_startups");
        }
        SQLiteDatabase sQLiteDatabase3 = this.writableDatabase;
        if (sQLiteDatabase3 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase3, "DELETE FROM ty_event");
        } else {
            sQLiteDatabase3.execSQL("DELETE FROM ty_event");
        }
        SQLiteDatabase sQLiteDatabase4 = this.writableDatabase;
        if (sQLiteDatabase4 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase4, "DELETE FROM ty_http");
        } else {
            sQLiteDatabase4.execSQL("DELETE FROM ty_http");
        }
        SQLiteDatabase sQLiteDatabase5 = this.writableDatabase;
        if (sQLiteDatabase5 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase5, "DELETE FROM ty_view");
        } else {
            sQLiteDatabase5.execSQL("DELETE FROM ty_view");
        }
        SQLiteDatabase sQLiteDatabase6 = this.writableDatabase;
        if (sQLiteDatabase6 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase6, "DELETE FROM ty_break");
        } else {
            sQLiteDatabase6.execSQL("DELETE FROM ty_break");
        }
        SQLiteDatabase sQLiteDatabase7 = this.writableDatabase;
        if (sQLiteDatabase7 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase7, "DELETE FROM ty_anr");
        } else {
            sQLiteDatabase7.execSQL("DELETE FROM ty_anr");
        }
        SQLiteDatabase sQLiteDatabase8 = this.writableDatabase;
        if (sQLiteDatabase8 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase8, "DELETE FROM ty_resource");
        } else {
            sQLiteDatabase8.execSQL("DELETE FROM ty_resource");
        }
        SQLiteDatabase sQLiteDatabase9 = this.writableDatabase;
        if (sQLiteDatabase9 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.execSQL(sQLiteDatabase9, "DELETE FROM ty_webhttp");
        } else {
            sQLiteDatabase9.execSQL("DELETE FROM ty_webhttp");
        }
    }
}
