package com.bytedance.applog;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

/* renamed from: com.bytedance.applog.w1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3719w1 extends AbstractC3635n1 {

    /* renamed from: c */
    public final SharedPreferences f8889c;

    /* renamed from: d */
    public final SharedPreferences f8890d;

    public C3719w1(Context context, String str) {
        if (context == null) {
            throw new IllegalArgumentException("context can't be null");
        }
        this.f8889c = context.getSharedPreferences("snssdk_openudid", 0);
        this.f8890d = context.getSharedPreferences(str, 0);
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: a */
    public void mo17068a(String str) {
        SharedPreferences m17063d = m17063d(str);
        if (m17063d != null && m17063d.contains(str)) {
            m17063d(str).edit().remove(str).apply();
        }
        super.mo17068a(str);
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: a */
    public void mo17067a(String str, String str2) {
        if (TextUtils.isEmpty(str2)) {
            return;
        }
        SharedPreferences.Editor edit = m17063d(str).edit();
        edit.putString(str, str2);
        edit.apply();
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: b */
    public String mo17065b(String str) {
        return m17063d(str).getString(str, null);
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: c */
    public String[] mo17064c(String str) {
        String string = m17063d(str).getString(str, null);
        if (TextUtils.isEmpty(string)) {
            return null;
        }
        return string.split("\n");
    }

    /* renamed from: d */
    public final SharedPreferences m17063d(String str) {
        return "device_id".equals(str) ? this.f8890d : this.f8889c;
    }

    @Override // com.bytedance.applog.AbstractC3635n1
    /* renamed from: a */
    public void mo17066a(String str, String[] strArr) {
        if (str == null || strArr == null) {
            return;
        }
        String join = TextUtils.join("\n", strArr);
        if (TextUtils.isEmpty(join)) {
            return;
        }
        SharedPreferences.Editor edit = m17063d(str).edit();
        edit.putString(str, join);
        edit.apply();
    }
}
