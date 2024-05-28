package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;
import com.bytedance.applog.C3659p1;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.bytedance.applog.u1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3703u1 extends AbstractC3628m1 {

    /* renamed from: k */
    public byte[] f8830k;

    /* renamed from: l */
    public int f8831l;

    /* renamed from: m */
    public int f8832m;

    /* renamed from: n */
    public JSONArray f8833n;

    /* renamed from: o */
    public long f8834o;

    /* renamed from: p */
    public JSONArray f8835p;

    /* renamed from: q */
    public long f8836q;

    /* renamed from: r */
    public C3694t1 f8837r;

    /* renamed from: s */
    public JSONArray f8838s;

    /* renamed from: t */
    public C3729x1 f8839t;

    /* renamed from: u */
    public JSONObject f8840u;

    /* renamed from: v */
    public JSONArray f8841v;

    /* renamed from: w */
    public long f8842w;

    /* renamed from: x */
    public JSONArray f8843x;

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        this.f8575a = cursor.getLong(0);
        this.f8576b = cursor.getLong(1);
        this.f8830k = cursor.getBlob(2);
        this.f8831l = cursor.getInt(3);
        this.f8578d = "";
        this.f8840u = null;
        this.f8837r = null;
        this.f8839t = null;
        this.f8838s = null;
        this.f8833n = null;
        this.f8835p = null;
        this.f8841v = null;
        this.f8843x = null;
        return 4;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
        return null;
    }

    /* renamed from: a */
    public void m17111a(JSONObject jSONObject, C3694t1 c3694t1, C3729x1 c3729x1, JSONArray jSONArray, JSONArray[] jSONArrayArr, long[] jArr, JSONArray jSONArray2) {
        m17233a(0L);
        this.f8840u = jSONObject;
        this.f8837r = c3694t1;
        this.f8839t = c3729x1;
        this.f8838s = jSONArray;
        this.f8833n = jSONArrayArr[0];
        this.f8834o = jArr[0];
        this.f8835p = jSONArrayArr[1];
        this.f8836q = jArr[1];
        this.f8841v = jSONArrayArr[2];
        this.f8842w = jArr[2];
        this.f8843x = jSONArray2;
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public List<String> mo17039b() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "_data", "blob", "_fail", "integer");
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f8576b));
        contentValues.put("_data", m17110i());
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        C3704u2.m17108a("U SHALL NOT PASS!", (Throwable) null);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: d */
    public String mo17036d() {
        return String.valueOf(this.f8575a);
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    @NonNull
    /* renamed from: e */
    public String mo17035e() {
        return "pack";
    }

    @Override // com.bytedance.applog.AbstractC3628m1
    /* renamed from: h */
    public JSONObject mo17034h() {
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("magic_tag", "ss_app_log");
        jSONObject.put("header", this.f8840u);
        jSONObject.put("time_sync", C3607j1.f8522b);
        jSONObject.put("local_time", System.currentTimeMillis() / 1000);
        if (this.f8837r != null) {
            JSONArray jSONArray = new JSONArray();
            jSONArray.put(this.f8837r.m17229g());
            jSONObject.put("launch", jSONArray);
        }
        C3729x1 c3729x1 = this.f8839t;
        if (c3729x1 != null) {
            JSONObject m17229g = c3729x1.m17229g();
            JSONArray jSONArray2 = this.f8838s;
            int length = jSONArray2 != null ? jSONArray2.length() : 0;
            JSONArray jSONArray3 = new JSONArray();
            for (int i = 0; i < length; i++) {
                JSONArray jSONArray4 = new JSONArray();
                JSONObject jSONObject2 = new JSONObject(new JSONObject(this.f8838s.optString(i)).optString("params"));
                jSONArray4.put(0, jSONObject2.optString("page_key", ""));
                jSONArray4.put(1, (jSONObject2.optInt("duration", 0) + 999) / 1000);
                jSONArray3.put(jSONArray4);
            }
            if (length > 0) {
                m17229g.put("activites", jSONArray3);
            }
            int i2 = AppLog.sLaunchFrom;
            if (i2 > 0) {
                m17229g.put("launch_from", i2);
                AppLog.sLaunchFrom = 0;
            }
            JSONArray jSONArray5 = new JSONArray();
            jSONArray5.put(m17229g);
            jSONObject.put("terminate", jSONArray5);
        }
        JSONArray jSONArray6 = this.f8833n;
        int length2 = jSONArray6 != null ? jSONArray6.length() : 0;
        if (length2 > 0) {
            jSONObject.put("event", this.f8833n);
        }
        JSONArray jSONArray7 = this.f8838s;
        int length3 = jSONArray7 != null ? jSONArray7.length() : 0;
        if (C3591h.m17286d()) {
            if (this.f8835p == null) {
                this.f8835p = this.f8838s;
            } else if (length3 > 0) {
                for (int i3 = 0; i3 < length3; i3++) {
                    this.f8835p.put(this.f8838s.get(i3));
                }
            }
        }
        JSONArray jSONArray8 = this.f8835p;
        int length4 = jSONArray8 != null ? jSONArray8.length() : 0;
        if (length4 > 0) {
            jSONObject.put("event_v3", this.f8835p);
        }
        JSONArray jSONArray9 = this.f8841v;
        int length5 = jSONArray9 != null ? jSONArray9.length() : 0;
        if (length5 > 0) {
            jSONObject.put("log_data", this.f8841v);
        }
        JSONArray jSONArray10 = this.f8843x;
        int length6 = jSONArray10 != null ? jSONArray10.length() : 0;
        if (length6 > 0) {
            jSONObject.put("item_impression", this.f8843x);
        }
        StringBuilder sb = new StringBuilder("pack {");
        sb.append("ts:");
        sb.append(this.f8576b);
        sb.append(", la:");
        Object obj = this.f8837r;
        if (obj == null) {
            obj = "0";
        }
        sb.append(obj);
        sb.append(", te:");
        Object obj2 = this.f8839t;
        if (obj2 == null) {
            obj2 = "0";
        }
        sb.append(obj2);
        sb.append(", p:");
        sb.append(length3);
        sb.append(", v1:");
        sb.append(length2);
        sb.append(", v3:");
        sb.append(length4);
        sb.append(", m:");
        sb.append(length5);
        sb.append(", imp:");
        sb.append(length6);
        sb.append("}");
        C3704u2.m17108a(sb.toString(), (Throwable) null);
        return jSONObject;
    }

    /* renamed from: i */
    public byte[] m17110i() {
        this.f8830k = null;
        try {
            JSONObject m17229g = m17229g();
            this.f8830k = C3710v0.m17082d(!(m17229g instanceof JSONObject) ? m17229g.toString() : NBSJSONObjectInstrumentation.toString(m17229g));
            return this.f8830k;
        } catch (OutOfMemoryError e) {
            StringBuilder sb = new StringBuilder();
            int i = 0;
            while (true) {
                C3659p1.C3661b[] c3661bArr = C3659p1.f8702f;
                if (i >= c3661bArr.length) {
                    break;
                }
                if (c3661bArr[i] != null) {
                    sb.append(c3661bArr[i].toString());
                    sb.append(";");
                }
                i++;
            }
            throw new RuntimeException(sb.toString(), e);
        }
    }

    /* renamed from: a */
    public static byte[] m17112a(ArrayList<AbstractC3628m1> arrayList, JSONObject jSONObject) {
        JSONArray jSONArray;
        try {
            C3703u1 c3703u1 = new C3703u1();
            JSONArray[] jSONArrayArr = {new JSONArray(), new JSONArray(), null};
            long[] jArr = new long[3];
            Iterator<AbstractC3628m1> it = arrayList.iterator();
            while (it.hasNext()) {
                AbstractC3628m1 next = it.next();
                if ("event".equals(next.mo17035e())) {
                    jSONArray = jSONArrayArr[0];
                } else if ("eventv3".equals(next.mo17035e())) {
                    jSONArray = jSONArrayArr[1];
                }
                jSONArray.put(next.mo17034h());
            }
            c3703u1.m17111a(jSONObject, null, null, null, jSONArrayArr, jArr, null);
            JSONObject m17229g = c3703u1.m17229g();
            return (!(m17229g instanceof JSONObject) ? m17229g.toString() : NBSJSONObjectInstrumentation.toString(m17229g)).getBytes();
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return null;
        }
    }
}
