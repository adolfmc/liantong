package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.bytedance.applog.C3730x2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.s1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3687s1 extends AbstractC3628m1 {

    /* renamed from: k */
    public String f8806k;

    /* renamed from: l */
    public boolean f8807l;

    /* renamed from: m */
    public String f8808m;

    public C3687s1(String str, boolean z, String str2) {
        this.f8808m = str;
        this.f8807l = z;
        this.f8806k = str2;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        super.mo17041a(cursor);
        this.f8808m = cursor.getString(8);
        this.f8806k = cursor.getString(9);
        this.f8807l = cursor.getInt(10) == 1;
        return 11;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        super.mo17040a(jSONObject);
        this.f8808m = jSONObject.optString("event", null);
        this.f8806k = jSONObject.optString("params", null);
        this.f8807l = jSONObject.optBoolean("is_bav", false);
        return this;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        List<String> mo17039b = super.mo17039b();
        ArrayList arrayList = new ArrayList(mo17039b.size());
        arrayList.addAll(mo17039b);
        arrayList.addAll(Arrays.asList("event", "varchar", "params", "varchar", "is_bav", "integer"));
        return arrayList;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        super.mo17038b(contentValues);
        contentValues.put("event", this.f8808m);
        if (this.f8807l && this.f8806k == null) {
            try {
                mo17119i();
            } catch (JSONException e) {
                C3704u2.m17108a("U SHALL NOT PASS!", e);
            }
        }
        contentValues.put("params", this.f8806k);
        contentValues.put("is_bav", Integer.valueOf(this.f8807l ? 1 : 0));
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: c */
    public String mo17120c() {
        return this.f8806k;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        return this.f8808m;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "eventv3";
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
        jSONObject.put("event", this.f8808m);
        if (this.f8807l) {
            jSONObject.put("is_bav", 1);
        }
        if (!TextUtils.isEmpty(this.f8806k)) {
            jSONObject.put("params", new JSONObject(this.f8806k));
        }
        int i = this.f8582h;
        if (i != C3730x2.EnumC3731a.UNKNOWN.f8930a) {
            jSONObject.put("nt", i);
        }
        jSONObject.put("datetime", this.f8583i);
        if (!TextUtils.isEmpty(this.f8581g)) {
            jSONObject.put("ab_sdk_version", this.f8581g);
        }
        return jSONObject;
    }

    /* renamed from: i */
    public void mo17119i() {
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("event", this.f8808m);
        if (this.f8807l && this.f8806k == null) {
            mo17119i();
        }
        jSONObject.put("params", this.f8806k);
        jSONObject.put("is_bav", this.f8807l);
    }
}
