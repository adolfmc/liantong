package com.bytedance.applog;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.m1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC3628m1 implements Cloneable {

    /* renamed from: j */
    public static final SimpleDateFormat f8574j = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);

    /* renamed from: a */
    public long f8575a;

    /* renamed from: b */
    public long f8576b;

    /* renamed from: c */
    public long f8577c;

    /* renamed from: d */
    public String f8578d;

    /* renamed from: e */
    public long f8579e;

    /* renamed from: f */
    public String f8580f;

    /* renamed from: g */
    public String f8581g;

    /* renamed from: h */
    public int f8582h;

    /* renamed from: i */
    public String f8583i;

    public AbstractC3628m1() {
        m17233a(0L);
    }

    /* renamed from: a */
    public static AbstractC3628m1 m17231a(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            return C3659p1.f8700d.get(jSONObject.optString("k_cls", "")).m24467clone().mo17040a(jSONObject);
        } catch (Throwable th) {
            C3704u2.m17108a("U SHALL NOT PASS!", th);
            return null;
        }
    }

    /* renamed from: a */
    public int mo17041a(@NonNull Cursor cursor) {
        this.f8575a = cursor.getLong(0);
        this.f8576b = cursor.getLong(1);
        this.f8577c = cursor.getLong(2);
        this.f8582h = cursor.getInt(3);
        this.f8579e = cursor.getLong(4);
        this.f8578d = cursor.getString(5);
        this.f8580f = cursor.getString(6);
        this.f8581g = cursor.getString(7);
        return 8;
    }

    /* renamed from: a */
    public final ContentValues m17232a(@Nullable ContentValues contentValues) {
        if (contentValues == null) {
            contentValues = new ContentValues();
        } else {
            contentValues.clear();
        }
        mo17038b(contentValues);
        return contentValues;
    }

    /* renamed from: a */
    public AbstractC3628m1 mo17040a(@NonNull JSONObject jSONObject) {
        this.f8576b = jSONObject.optLong("local_time_ms", 0L);
        this.f8575a = 0L;
        this.f8577c = 0L;
        this.f8582h = 0;
        this.f8579e = 0L;
        this.f8578d = null;
        this.f8580f = null;
        this.f8581g = null;
        return this;
    }

    /* renamed from: a */
    public final String m17234a() {
        List<String> mo17039b = mo17039b();
        if (mo17039b != null) {
            StringBuilder sb = new StringBuilder(128);
            sb.append("create table if not exists ");
            sb.append(mo17035e());
            sb.append("(");
            for (int i = 0; i < mo17039b.size(); i += 2) {
                sb.append(mo17039b.get(i));
                sb.append(" ");
                sb.append(mo17039b.get(i + 1));
                sb.append(",");
            }
            sb.delete(sb.length() - 1, sb.length());
            sb.append(")");
            return sb.toString();
        }
        return null;
    }

    /* renamed from: a */
    public void m17233a(long j) {
        if (j == 0) {
            j = System.currentTimeMillis();
        }
        this.f8576b = j;
    }

    /* renamed from: b */
    public List<String> mo17039b() {
        return Arrays.asList("_id", "integer primary key autoincrement", "local_time_ms", "integer", "tea_event_index", "integer", "nt", "integer", "user_id", "integer", "session_id", "varchar", "user_unique_id", "varchar", "ab_sdk_version", "varchar");
    }

    /* renamed from: b */
    public void mo17038b(@NonNull ContentValues contentValues) {
        contentValues.put("local_time_ms", Long.valueOf(this.f8576b));
        contentValues.put("tea_event_index", Long.valueOf(this.f8577c));
        contentValues.put("nt", Integer.valueOf(this.f8582h));
        contentValues.put("user_id", Long.valueOf(this.f8579e));
        contentValues.put("session_id", this.f8578d);
        contentValues.put("user_unique_id", this.f8580f);
        contentValues.put("ab_sdk_version", this.f8581g);
    }

    /* renamed from: b */
    public void mo17037b(@NonNull JSONObject jSONObject) {
        jSONObject.put("local_time_ms", this.f8576b);
    }

    /* renamed from: c */
    public String mo17120c() {
        return null;
    }

    /* renamed from: clone */
    public AbstractC3628m1 m24467clone() {
        try {
            return (AbstractC3628m1) super.clone();
        } catch (CloneNotSupportedException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return null;
        }
    }

    /* renamed from: d */
    public String mo17036d() {
        StringBuilder m17349a = C3535a.m17349a("sid:");
        m17349a.append(this.f8578d);
        return m17349a.toString();
    }

    @NonNull
    /* renamed from: e */
    public abstract String mo17035e();

    @NonNull
    /* renamed from: f */
    public final JSONObject m17230f() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("k_cls", mo17035e());
            mo17037b(jSONObject);
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
        }
        return jSONObject;
    }

    @NonNull
    /* renamed from: g */
    public final JSONObject m17229g() {
        try {
            this.f8583i = f8574j.format(new Date(this.f8576b));
            return mo17034h();
        } catch (JSONException e) {
            C3704u2.m17108a("U SHALL NOT PASS!", e);
            return null;
        }
    }

    /* renamed from: h */
    public abstract JSONObject mo17034h();

    @NonNull
    public String toString() {
        String mo17035e = mo17035e();
        if (!getClass().getSimpleName().equalsIgnoreCase(mo17035e)) {
            mo17035e = mo17035e + ", " + getClass().getSimpleName();
        }
        String str = this.f8578d;
        if (str != null) {
            int indexOf = str.indexOf("-");
            if (indexOf >= 0) {
                str = str.substring(0, indexOf);
            }
        } else {
            str = "-";
        }
        return "{" + mo17035e + ", " + mo17036d() + ", " + str + ", " + this.f8576b + "}";
    }
}
