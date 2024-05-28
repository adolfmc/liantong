package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.x1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3729x1 extends AbstractC3628m1 {

    /* renamed from: k */
    public long f8914k;

    /* renamed from: l */
    public long f8915l;

    /* renamed from: m */
    public String f8916m;

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
        return 0;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        return null;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        return String.valueOf(this.f8914k);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "terminate";
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: h */
    public JSONObject mo17034h() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("tea_event_index", this.f8577c);
        jSONObject.put("session_id", this.f8578d);
        jSONObject.put("stop_timestamp", this.f8915l / 1000);
        jSONObject.put("duration", this.f8914k / 1000);
        jSONObject.put("datetime", this.f8583i);
        long j = this.f8579e;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        if (!TextUtils.isEmpty(this.f8580f)) {
            jSONObject.put("user_unique_id", this.f8580f);
        }
        if (!TextUtils.isEmpty(this.f8581g)) {
            jSONObject.put("ab_sdk_version", this.f8581g);
        }
        if (!TextUtils.isEmpty(this.f8916m)) {
            jSONObject.put("uuid_changed", true);
            if (!TextUtils.equals(this.f8916m, this.f8578d)) {
                jSONObject.put("original_session_id", this.f8916m);
            }
        }
        return jSONObject;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
        return this;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
    }
}
