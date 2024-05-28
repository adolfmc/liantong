package com.p319ss.android.downloadlib.event;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSSQLiteInstrumentation;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.event.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9970ox {

    /* renamed from: ox */
    private static volatile C9970ox f19215ox;

    /* renamed from: mb */
    private SQLiteDatabase f19216mb;

    /* renamed from: mb */
    public static C9970ox m7289mb() {
        if (f19215ox == null) {
            synchronized (C9970ox.class) {
                if (f19215ox == null) {
                    f19215ox = new C9970ox();
                }
            }
        }
        return f19215ox;
    }

    private C9970ox() {
        try {
            this.f19216mb = new C9969mb(C9940x.getContext()).getWritableDatabase();
        } catch (Throwable th) {
            C9971b.m7285mb().mo7282mb(th, "ClickEventHelper");
        }
    }

    /* renamed from: ox */
    public boolean m7287ox() {
        return DownloadSetting.obtainGlobal().optInt("click_event_switch", 0) == 1;
    }

    /* renamed from: b */
    public boolean m7291b() {
        return DownloadSetting.obtainGlobal().optInt("click_event_switch", 0) == 2;
    }

    /* renamed from: mb */
    public void m7288mb(long j, String str) {
        String optString;
        SQLiteDatabase sQLiteDatabase = this.f19216mb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            optString = new JSONObject(str).optString("req_id");
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (TextUtils.isEmpty(optString)) {
            return;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("ad_id", Long.valueOf(j));
        contentValues.put("req_id", optString);
        contentValues.put("time", Long.valueOf(System.currentTimeMillis()));
        SQLiteDatabase sQLiteDatabase2 = this.f19216mb;
        if (sQLiteDatabase2 instanceof SQLiteDatabase) {
            NBSSQLiteInstrumentation.insert(sQLiteDatabase2, "click_event", null, contentValues);
        } else {
            sQLiteDatabase2.insert("click_event", null, contentValues);
        }
        m7290b(j, str);
    }

    /* renamed from: ox */
    public boolean m7286ox(long j, String str) {
        SQLiteDatabase sQLiteDatabase = this.f19216mb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return false;
        }
        Cursor cursor = null;
        try {
            try {
                String optString = new JSONObject(str).optString("req_id");
                if (TextUtils.isEmpty(optString)) {
                    return false;
                }
                String[] strArr = {String.valueOf(System.currentTimeMillis() - 1209600000), String.valueOf(j), optString};
                SQLiteDatabase sQLiteDatabase2 = this.f19216mb;
                String[] strArr2 = C9969mb.f19214mb;
                cursor = !(sQLiteDatabase2 instanceof SQLiteDatabase) ? sQLiteDatabase2.query("click_event", strArr2, "time > ? AND ad_id = ? AND req_id = ?", strArr, null, null, null, null) : NBSSQLiteInstrumentation.query(sQLiteDatabase2, "click_event", strArr2, "time > ? AND ad_id = ? AND req_id = ?", strArr, null, null, null, null);
                boolean z = cursor.getCount() > 0;
                if (cursor != null) {
                    cursor.close();
                }
                return z;
            } catch (Exception e) {
                e.printStackTrace();
                if (cursor != null) {
                    cursor.close();
                }
                return false;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* renamed from: b */
    private void m7290b(long j, String str) {
        SQLiteDatabase sQLiteDatabase = this.f19216mb;
        if (sQLiteDatabase == null || !sQLiteDatabase.isOpen() || j <= 0 || TextUtils.isEmpty(str)) {
            return;
        }
        try {
            String optString = new JSONObject(str).optString("req_id");
            if (TextUtils.isEmpty(optString)) {
                return;
            }
            String[] strArr = {String.valueOf(System.currentTimeMillis() - 1209600000), String.valueOf(j), optString};
            SQLiteDatabase sQLiteDatabase2 = this.f19216mb;
            if (sQLiteDatabase2 instanceof SQLiteDatabase) {
                NBSSQLiteInstrumentation.delete(sQLiteDatabase2, "click_event", "time < ? AND ad_id = ? AND req_id = ?", strArr);
            } else {
                sQLiteDatabase2.delete("click_event", "time < ? AND ad_id = ? AND req_id = ?", strArr);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
