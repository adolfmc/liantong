package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.v1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3711v1 extends AbstractC3628m1 {

    /* renamed from: k */
    public long f8859k;

    /* renamed from: l */
    public String f8860l;

    /* renamed from: m */
    public String f8861m;

    /* renamed from: n */
    public int f8862n;

    /* renamed from: o */
    public String f8863o;

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        super.mo17041a(cursor);
        this.f8861m = cursor.getString(8);
        this.f8860l = cursor.getString(9);
        this.f8859k = cursor.getLong(10);
        this.f8862n = cursor.getInt(11);
        this.f8863o = cursor.getString(12);
        return 13;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        super.mo17040a(jSONObject);
        this.f8861m = jSONObject.optString("page_key", null);
        this.f8860l = jSONObject.optString("refer_page_key", null);
        this.f8859k = jSONObject.optLong("duration", 0L);
        this.f8862n = jSONObject.optInt("is_back", 0);
        return this;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        List<String> mo17039b = super.mo17039b();
        ArrayList arrayList = new ArrayList(mo17039b.size());
        arrayList.addAll(mo17039b);
        arrayList.addAll(Arrays.asList("page_key", "varchar", "refer_page_key", "varchar", "duration", "integer", "is_back", "integer", "last_session", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        super.mo17038b(contentValues);
        contentValues.put("page_key", this.f8861m);
        contentValues.put("refer_page_key", this.f8860l);
        contentValues.put("duration", Long.valueOf(this.f8859k));
        contentValues.put("is_back", Integer.valueOf(this.f8862n));
        contentValues.put("last_session", this.f8863o);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("page_key", this.f8861m);
        jSONObject.put("refer_page_key", this.f8860l);
        jSONObject.put("duration", this.f8859k);
        jSONObject.put("is_back", this.f8862n);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        return this.f8861m + ", " + this.f8859k;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "page";
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: h */
    public JSONObject mo17034h() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("tea_event_index", this.f8577c);
        jSONObject.put("session_id", this.f8578d);
        long j = this.f8579e;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        if (!TextUtils.isEmpty(this.f8580f)) {
            jSONObject.put("user_unique_id", this.f8580f);
        }
        jSONObject.put("event", "bav2b_page");
        jSONObject.put("is_bav", 1);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("page_key", this.f8861m);
        jSONObject2.put("refer_page_key", this.f8860l);
        jSONObject2.put("is_back", this.f8862n);
        jSONObject2.put("duration", this.f8859k);
        jSONObject.put("params", jSONObject2);
        jSONObject.put("datetime", this.f8583i);
        return jSONObject;
    }

    /* renamed from: i */
    public boolean m17080i() {
        return this.f8859k == -1;
    }
}
