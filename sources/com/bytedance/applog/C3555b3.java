package com.bytedance.applog;

import android.content.SharedPreferences;
import android.text.TextUtils;
import java.util.UUID;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.applog.b3 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3555b3 {

    /* renamed from: a */
    public static AbstractC3749z2<String> f8378a = new C3556a();

    /* renamed from: com.bytedance.applog.b3$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3556a extends AbstractC3749z2<String> {
        @Override // com.bytedance.applog.AbstractC3749z2
        /* renamed from: a */
        public String mo16989a(Object[] objArr) {
            SharedPreferences sharedPreferences = (SharedPreferences) objArr[0];
            String string = sharedPreferences.getString("cdid", "");
            if (TextUtils.isEmpty(string)) {
                String uuid = UUID.randomUUID().toString();
                C3535a.m17350a(sharedPreferences, "cdid", uuid);
                return uuid;
            }
            return string;
        }
    }
}
