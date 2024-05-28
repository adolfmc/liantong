package com.vivo.push.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Base64;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.vivo.push.util.c */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class BaseSharePreference {

    /* renamed from: a */
    protected Context f21202a;

    /* renamed from: b */
    private String f21203b;

    /* renamed from: c */
    private volatile SharedPreferences f21204c;

    /* renamed from: d */
    private HashMap<String, String> f21205d = new HashMap<>();

    /* renamed from: e */
    private HashMap<String, Long> f21206e = new HashMap<>();

    /* renamed from: f */
    private HashMap<String, Integer> f21207f = new HashMap<>();

    /* renamed from: g */
    private HashMap<String, Boolean> f21208g = new HashMap<>();

    /* renamed from: a */
    public final void m5422a(Context context, String str) {
        if (TextUtils.isEmpty(str)) {
            throw new RuntimeException("sharedFileName can't be null");
        }
        this.f21203b = str;
        this.f21204c = context.getSharedPreferences(this.f21203b, 0);
        this.f21202a = context;
        List<String> m5414b = m5414b("local_iv");
        if (m5414b == null || m5414b.size() < 4) {
            LogUtil.m5354a("BaseSharePreference", " initSecureCode error list is null ");
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("com.vivo.push.secure_sub_iv", m5414b.get(1));
        hashMap.put("com.vivo.push.secure_sub_key", m5414b.get(2));
        hashMap.put("com.vivo.push.secure_cache_iv", m5414b.get(3));
        hashMap.put("com.vivo.push.secure_cache_key", m5414b.get(0));
        m5416a(hashMap);
    }

    /* renamed from: a */
    public final void m5417a(String str, String str2) {
        this.f21205d.put(str, str2);
        m5415b();
        if (this.f21204c != null) {
            SharedPreferences.Editor edit = this.f21204c.edit();
            edit.putString(str, str2);
            m5421a(edit);
        }
    }

    /* renamed from: a */
    private void m5416a(Map<String, String> map) {
        if (map.size() > 0) {
            m5415b();
            if (this.f21204c != null) {
                SharedPreferences.Editor edit = this.f21204c.edit();
                for (String str : map.keySet()) {
                    String str2 = map.get(str);
                    this.f21205d.put(str, str2);
                    edit.putString(str, str2);
                }
                m5421a(edit);
            }
        }
    }

    /* renamed from: a */
    public final void m5419a(String str, int i) {
        this.f21207f.put(str, Integer.valueOf(i));
        m5415b();
        if (this.f21204c != null) {
            SharedPreferences.Editor edit = this.f21204c.edit();
            edit.putInt(str, i);
            m5421a(edit);
        }
    }

    /* renamed from: a */
    public final void m5418a(String str, long j) {
        this.f21206e.put(str, Long.valueOf(j));
        m5415b();
        if (this.f21204c != null) {
            SharedPreferences.Editor edit = this.f21204c.edit();
            edit.putLong(str, j);
            m5421a(edit);
        }
    }

    /* renamed from: b */
    public final String m5411b(String str, String str2) {
        String str3 = this.f21205d.get(str);
        if (str3 != null) {
            return str3;
        }
        m5415b();
        if (this.f21204c != null) {
            str3 = this.f21204c.getString(str, str2);
            if (!TextUtils.isEmpty(str3) && !str3.equals(str2)) {
                this.f21205d.put(str, str3);
            }
        }
        return str3;
    }

    /* renamed from: b */
    public final int m5413b(String str, int i) {
        Integer num = this.f21207f.get(str);
        if (num != null) {
            return num.intValue();
        }
        m5415b();
        if (this.f21204c != null) {
            num = Integer.valueOf(this.f21204c.getInt(str, i));
            if (!num.equals(Integer.valueOf(i))) {
                this.f21207f.put(str, num);
            }
        }
        return num.intValue();
    }

    /* renamed from: b */
    public final long m5412b(String str, long j) {
        Long l = this.f21206e.get(str);
        if (l != null) {
            return l.longValue();
        }
        m5415b();
        if (this.f21204c != null) {
            l = Long.valueOf(this.f21204c.getLong(str, j));
            if (!l.equals(Long.valueOf(j))) {
                this.f21206e.put(str, l);
            }
        }
        return l.longValue();
    }

    /* renamed from: a */
    public final void m5420a(String str) {
        this.f21206e.remove(str);
        this.f21207f.remove(str);
        this.f21208g.remove(str);
        this.f21205d.remove(str);
        m5415b();
        if (this.f21204c != null) {
            SharedPreferences.Editor edit = this.f21204c.edit();
            if (this.f21204c.contains(str)) {
                edit.remove(str);
                m5421a(edit);
            }
        }
    }

    /* renamed from: a */
    public static void m5421a(SharedPreferences.Editor editor) {
        if (editor == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            editor.apply();
        } else {
            editor.commit();
        }
    }

    /* renamed from: a */
    public final void m5423a() {
        this.f21206e.clear();
        this.f21207f.clear();
        this.f21208g.clear();
        this.f21205d.clear();
        m5415b();
        if (this.f21204c != null) {
            SharedPreferences.Editor edit = this.f21204c.edit();
            edit.clear();
            m5421a(edit);
        }
    }

    /* renamed from: b */
    private synchronized void m5415b() {
        if (this.f21204c == null) {
            if (this.f21202a == null) {
                throw new RuntimeException("SharedPreferences is not init", new Throwable());
            }
            this.f21204c = this.f21202a.getSharedPreferences(this.f21203b, 0);
        }
    }

    /* renamed from: b */
    private List<String> m5414b(String str) {
        Object m5445a;
        String[] split;
        if (this.f21202a == null) {
            LogUtil.m5342c("BaseSharePreference", " parsLocalIv error mContext is null ");
            return null;
        }
        ArrayList arrayList = new ArrayList();
        try {
            m5445a = Utility.m5445a(this.f21202a, this.f21202a.getPackageName(), str);
        } catch (Exception e) {
            LogUtil.m5342c("BaseSharePreference", " parsLocalIv error e =" + e.getMessage());
            e.printStackTrace();
        }
        if (m5445a == null) {
            return null;
        }
        String str2 = new String(Base64.decode(m5445a.toString(), 2));
        if (!TextUtils.isEmpty(str2) && (split = str2.split(",#@")) != null && split.length >= 4) {
            for (String str3 : split) {
                arrayList.add(str3.replace(",#@", ""));
            }
            if (arrayList.size() < 4) {
                return null;
            }
            return arrayList;
        }
        return null;
    }
}
