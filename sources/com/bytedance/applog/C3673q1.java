package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.applog.C3730x2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.q1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3673q1 extends AbstractC3628m1 {

    /* renamed from: k */
    public String f8775k;

    /* renamed from: l */
    public String f8776l;

    /* renamed from: m */
    public String f8777m;

    /* renamed from: n */
    public String f8778n;

    /* renamed from: o */
    public long f8779o;

    /* renamed from: p */
    public long f8780p;

    public C3673q1() {
    }

    public C3673q1(String str, String str2, String str3, long j, long j2, String str4) {
        m17233a(0L);
        this.f8775k = str;
        this.f8776l = str2;
        this.f8777m = str3;
        this.f8779o = j;
        this.f8780p = j2;
        this.f8778n = str4;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        super.mo17041a(cursor);
        this.f8775k = cursor.getString(8);
        this.f8776l = cursor.getString(9);
        this.f8779o = cursor.getLong(10);
        this.f8780p = cursor.getLong(11);
        this.f8778n = cursor.getString(12);
        this.f8777m = cursor.getString(13);
        return 14;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        super.mo17040a(jSONObject);
        this.f8577c = jSONObject.optLong("tea_event_index", 0L);
        this.f8775k = jSONObject.optString("category", null);
        this.f8776l = jSONObject.optString("tag", null);
        this.f8779o = jSONObject.optLong("value", 0L);
        this.f8780p = jSONObject.optLong("ext_value", 0L);
        this.f8778n = jSONObject.optString("params", null);
        this.f8777m = jSONObject.optString("label", null);
        return this;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        List<String> mo17039b = super.mo17039b();
        ArrayList arrayList = new ArrayList(mo17039b.size());
        arrayList.addAll(mo17039b);
        arrayList.addAll(Arrays.asList("category", "varchar", "tag", "varchar", "value", "integer", "ext_value", "integer", "params", "varchar", "label", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        super.mo17038b(contentValues);
        contentValues.put("category", this.f8775k);
        contentValues.put("tag", this.f8776l);
        contentValues.put("value", Long.valueOf(this.f8779o));
        contentValues.put("ext_value", Long.valueOf(this.f8780p));
        contentValues.put("params", this.f8778n);
        contentValues.put("label", this.f8777m);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("tea_event_index", this.f8577c);
        jSONObject.put("category", this.f8775k);
        jSONObject.put("tag", this.f8776l);
        jSONObject.put("value", this.f8779o);
        jSONObject.put("ext_value", this.f8780p);
        jSONObject.put("params", this.f8778n);
        jSONObject.put("label", this.f8777m);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: c */
    public String mo17120c() {
        return this.f8778n;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        StringBuilder m17349a = C3535a.m17349a("");
        m17349a.append(this.f8776l);
        m17349a.append(", ");
        m17349a.append(this.f8777m);
        return m17349a.toString();
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "event";
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: h */
    public JSONObject mo17034h() {
        JSONObject jSONObject = !TextUtils.isEmpty(this.f8778n) ? new JSONObject(this.f8778n) : null;
        if (jSONObject == null) {
            jSONObject = new JSONObject();
        }
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("tea_event_index", this.f8577c);
        jSONObject.put("session_id", this.f8578d);
        long j = this.f8579e;
        if (j > 0) {
            jSONObject.put("user_id", j);
        }
        int i = this.f8582h;
        if (i != C3730x2.EnumC3731a.UNKNOWN.f8930a) {
            jSONObject.put("nt", i);
        }
        if (!TextUtils.isEmpty(this.f8580f)) {
            jSONObject.put("user_unique_id", this.f8580f);
        }
        jSONObject.put("category", this.f8775k);
        jSONObject.put("tag", this.f8776l);
        jSONObject.put("value", this.f8779o);
        jSONObject.put("ext_value", this.f8780p);
        jSONObject.put("label", this.f8777m);
        jSONObject.put("datetime", this.f8583i);
        if (!TextUtils.isEmpty(this.f8581g)) {
            jSONObject.put("ab_sdk_version", this.f8581g);
        }
        return jSONObject;
    }
}
