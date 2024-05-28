package com.xiaomi.push;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* renamed from: com.xiaomi.push.o */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11474o {

    /* renamed from: a */
    private static volatile C11474o f23357a;

    /* renamed from: a */
    private Context f23358a;

    /* renamed from: a */
    private Handler f23359a = new Handler(Looper.getMainLooper());

    /* renamed from: a */
    private Map<String, Map<String, String>> f23360a = new HashMap();

    private C11474o(Context context) {
        this.f23358a = context;
    }

    /* renamed from: a */
    public static C11474o m2944a(Context context) {
        if (f23357a == null) {
            synchronized (C11474o.class) {
                if (f23357a == null) {
                    f23357a = new C11474o(context);
                }
            }
        }
        return f23357a;
    }

    /* renamed from: b */
    private synchronized void m2939b(String str, String str2, String str3) {
        if (this.f23360a == null) {
            this.f23360a = new HashMap();
        }
        Map<String, String> map = this.f23360a.get(str);
        if (map == null) {
            map = new HashMap<>();
        }
        map.put(str2, str3);
        this.f23360a.put(str, map);
    }

    /* renamed from: a */
    public synchronized void m2940a(final String str, final String str2, final String str3) {
        m2939b(str, str2, str3);
        this.f23359a.post(new Runnable() { // from class: com.xiaomi.push.o.1
            @Override // java.lang.Runnable
            public void run() {
                SharedPreferences.Editor edit = C11474o.this.f23358a.getSharedPreferences(str, 4).edit();
                edit.putString(str2, str3);
                edit.commit();
            }
        });
    }

    /* renamed from: a */
    private synchronized String m2942a(String str, String str2) {
        if (this.f23360a != null && !TextUtils.isEmpty(str)) {
            if (!TextUtils.isEmpty(str2)) {
                try {
                    Map<String, String> map = this.f23360a.get(str);
                    if (map != null) {
                        return map.get(str2);
                    }
                    return "";
                } catch (Throwable unused) {
                    return "";
                }
            }
        }
        return "";
    }

    /* renamed from: a */
    public synchronized String m2941a(String str, String str2, String str3) {
        String m2942a = m2942a(str, str2);
        if (TextUtils.isEmpty(m2942a)) {
            return this.f23358a.getSharedPreferences(str, 4).getString(str2, str3);
        }
        return m2942a;
    }
}
