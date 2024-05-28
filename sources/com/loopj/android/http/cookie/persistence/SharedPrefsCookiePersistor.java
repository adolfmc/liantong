package com.loopj.android.http.cookie.persistence;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@SuppressLint({"CommitPrefEdits"})
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SharedPrefsCookiePersistor implements CookiePersistor {
    private final SharedPreferences sharedPreferences;

    public SharedPrefsCookiePersistor(Context context) {
        this(context.getSharedPreferences("CookiePersistence", 0));
    }

    public SharedPrefsCookiePersistor(SharedPreferences sharedPreferences) {
        this.sharedPreferences = sharedPreferences;
    }

    @Override // com.loopj.android.http.cookie.persistence.CookiePersistor
    public List<Cookie> loadAll() {
        ArrayList arrayList = new ArrayList(this.sharedPreferences.getAll().size());
        for (Map.Entry<String, ?> entry : this.sharedPreferences.getAll().entrySet()) {
            Cookie decode = new SerializableCookie().decode((String) entry.getValue());
            if (decode != null) {
                arrayList.add(decode);
            }
        }
        return arrayList;
    }

    @Override // com.loopj.android.http.cookie.persistence.CookiePersistor
    public void saveAll(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (Cookie cookie : collection) {
            edit.putString(createCookieKey(cookie), new SerializableCookie().encode(cookie));
        }
        edit.commit();
    }

    @Override // com.loopj.android.http.cookie.persistence.CookiePersistor
    public void removeAll(Collection<Cookie> collection) {
        SharedPreferences.Editor edit = this.sharedPreferences.edit();
        for (Cookie cookie : collection) {
            edit.remove(createCookieKey(cookie));
        }
        edit.commit();
    }

    private static String createCookieKey(Cookie cookie) {
        StringBuilder sb = new StringBuilder();
        sb.append(cookie.secure() ? "https" : "http");
        sb.append("://");
        sb.append(cookie.domain());
        sb.append(cookie.path());
        sb.append("|");
        sb.append(cookie.name());
        return sb.toString();
    }

    @Override // com.loopj.android.http.cookie.persistence.CookiePersistor
    public void clear() {
        this.sharedPreferences.edit().clear().commit();
    }
}
