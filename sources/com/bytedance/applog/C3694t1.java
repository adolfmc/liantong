package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.t1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3694t1 extends AbstractC3628m1 {

    /* renamed from: k */
    public int f8816k;

    /* renamed from: l */
    public String f8817l;

    /* renamed from: m */
    public boolean f8818m;

    /* renamed from: n */
    public String f8819n;

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        super.mo17041a(cursor);
        this.f8817l = cursor.getString(8);
        this.f8816k = cursor.getInt(9);
        this.f8819n = cursor.getString(10);
        return 11;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
        return null;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        List<String> mo17039b = super.mo17039b();
        ArrayList arrayList = new ArrayList(mo17039b.size());
        arrayList.addAll(mo17039b);
        arrayList.addAll(Arrays.asList("ver_name", "varchar", "ver_code", "integer", "last_session", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        super.mo17038b(contentValues);
        contentValues.put("ver_name", this.f8817l);
        contentValues.put("ver_code", Integer.valueOf(this.f8816k));
        contentValues.put("last_session", this.f8819n);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        return this.f8818m ? "bg" : "fg";
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "launch";
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
        boolean z = this.f8818m;
        if (z) {
            jSONObject.put("is_background", z);
        }
        jSONObject.put("datetime", this.f8583i);
        if (!TextUtils.isEmpty(this.f8581g)) {
            jSONObject.put("ab_sdk_version", this.f8581g);
        }
        if (!TextUtils.isEmpty(this.f8819n)) {
            jSONObject.put("uuid_changed", true);
            jSONObject.put("original_session_id", this.f8819n);
        }
        return jSONObject;
    }
}
