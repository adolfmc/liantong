package com.xiaomi.push;

import android.content.ContentValues;
import android.content.Context;
import com.xiaomi.push.C11213bw;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.bt */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11210bt extends C11213bw.C11220e {

    /* renamed from: a */
    private String f21637a;

    public C11210bt(String str, ContentValues contentValues, String str2) {
        super(str, contentValues);
        this.f21637a = "MessageInsertJob";
        this.f21637a = str2;
    }

    /* renamed from: a */
    public static C11210bt m4687a(Context context, String str, C11408gj c11408gj) {
        byte[] m3085a = C11441hp.m3085a(c11408gj);
        if (m3085a == null || m3085a.length <= 0) {
            return null;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put("status", (Integer) 0);
        contentValues.put("messageId", "");
        contentValues.put("messageItemId", c11408gj.m3651d());
        contentValues.put("messageItem", m3085a);
        contentValues.put("appId", C11201bn.m4703a(context).m4694b());
        contentValues.put("packageName", C11201bn.m4703a(context).m4705a());
        contentValues.put("createTimeStamp", Long.valueOf(System.currentTimeMillis()));
        contentValues.put("uploadTimestamp", (Integer) 0);
        return new C11210bt(str, contentValues, "a job build to insert message to db");
    }
}
