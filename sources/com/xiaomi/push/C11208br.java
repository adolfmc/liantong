package com.xiaomi.push;

import android.content.Context;
import android.database.Cursor;
import com.xiaomi.push.C11213bw;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.br */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11208br extends C11213bw.AbstractC11217b<Long> {

    /* renamed from: a */
    private long f21634a;

    /* renamed from: a */
    private String f21635a;

    public C11208br(String str, List<String> list, String str2, String[] strArr, String str3, String str4, String str5, int i, String str6) {
        super(str, list, str2, strArr, str3, str4, str5, i);
        this.f21634a = 0L;
        this.f21635a = str6;
    }

    /* renamed from: a */
    public static C11208br m4689a(String str) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("count(*)");
        return new C11208br(str, arrayList, null, null, null, null, null, 0, "job to get count of all message");
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.xiaomi.push.C11213bw.AbstractC11217b
    /* renamed from: a */
    public Long mo4667a(Context context, Cursor cursor) {
        return Long.valueOf(cursor.getLong(0));
    }

    @Override // com.xiaomi.push.C11213bw.AbstractC11217b
    /* renamed from: a */
    public void mo4666a(Context context, List<Long> list) {
        if (context == null || list == null || list.size() <= 0) {
            return;
        }
        this.f21634a = list.get(0).longValue();
    }

    @Override // com.xiaomi.push.C11213bw.AbstractRunnableC11215a
    /* renamed from: a */
    public Object mo4675a() {
        return Long.valueOf(this.f21634a);
    }
}
