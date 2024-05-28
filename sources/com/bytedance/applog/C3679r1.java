package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import android.text.TextUtils;
import androidx.annotation.NonNull;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.r1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3679r1 extends AbstractC3628m1 {
    @NonNull

    /* renamed from: k */
    public String f8787k;
    @NonNull

    /* renamed from: l */
    public String f8788l;

    public C3679r1(@NonNull String str, @NonNull JSONObject jSONObject) {
        this.f8788l = str;
        this.f8787k = !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        super.mo17041a(cursor);
        this.f8787k = cursor.getString(8);
        this.f8788l = cursor.getString(9);
        return 10;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        super.mo17040a(jSONObject);
        this.f8787k = jSONObject.optString("params", null);
        this.f8788l = jSONObject.optString("log_type", null);
        return this;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        List<String> mo17039b = super.mo17039b();
        ArrayList arrayList = new ArrayList(mo17039b.size());
        arrayList.addAll(mo17039b);
        arrayList.addAll(Arrays.asList("params", "varchar", "log_type", "varchar"));
        return arrayList;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        super.mo17038b(contentValues);
        contentValues.put("params", this.f8787k);
        contentValues.put("log_type", this.f8788l);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        jSONObject.put("local_time_ms", this.f8576b);
        jSONObject.put("params", this.f8787k);
        jSONObject.put("log_type", this.f8788l);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: c */
    public String mo17120c() {
        return this.f8787k;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        StringBuilder m17349a = C3535a.m17349a("param:");
        m17349a.append(this.f8787k);
        m17349a.append(" logType:");
        m17349a.append(this.f8788l);
        return m17349a.toString();
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "event_misc";
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
        jSONObject.put("log_type", this.f8788l);
        try {
            JSONObject jSONObject2 = new JSONObject(this.f8787k);
            Iterator<String> keys = jSONObject2.keys();
            while (keys.hasNext()) {
                String next = keys.next();
                Object obj = jSONObject2.get(next);
                if (jSONObject.opt(next) != null) {
                    C3704u2.m17108a("misc事件存在重复的key", (Throwable) null);
                }
                jSONObject.put(next, obj);
            }
        } catch (Exception e) {
            C3704u2.m17108a("解析 event misc 失败", e);
        }
        return jSONObject;
    }
}
