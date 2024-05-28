package com.loopj.android.http.cookie;

import android.util.Log;
import com.loopj.android.http.RequestCookie;
import com.loopj.android.http.cookie.cache.CookieCache;
import com.loopj.android.http.cookie.cache.IdentifiableCookie;
import com.loopj.android.http.cookie.persistence.CookiePersistor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.Cookie;
import okhttp3.HttpUrl;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class PersistentCookieJar implements ClearableCookieJar {
    private CookieCache cache;
    private CookiePersistor persistor;
    private RequestCookie requestCookie;

    public PersistentCookieJar(CookieCache cookieCache, CookiePersistor cookiePersistor, RequestCookie requestCookie) {
        this.cache = cookieCache;
        this.persistor = cookiePersistor;
        this.requestCookie = requestCookie;
        this.cache.addAll(cookiePersistor.loadAll());
    }

    @Override // okhttp3.CookieJar
    public synchronized void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
        try {
            this.cache.addAll(list);
            this.persistor.saveAll(list);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OKHttp3", "COOKIE管理saveFromResponse异常:" + e.getMessage());
        }
    }

    @Override // okhttp3.CookieJar
    public synchronized List<Cookie> loadForRequest(HttpUrl httpUrl) {
        ArrayList arrayList;
        ArrayList arrayList2 = new ArrayList();
        arrayList = new ArrayList();
        try {
            CopyOnWriteArrayList<IdentifiableCookie> cacheCookies = this.cache.getCacheCookies();
            for (int i = 0; i < cacheCookies.size(); i++) {
                Cookie cookie = cacheCookies.get(i).getCookie();
                if (isCookieExpired(cookie)) {
                    arrayList2.add(cookie);
                } else if (cookie.matches(httpUrl)) {
                    arrayList.add(cookie);
                }
            }
            this.cache.removeAll(arrayList2);
            this.persistor.removeAll(arrayList2);
            List<Cookie> cookiesForAddToReqeust = this.requestCookie.getCookiesForAddToReqeust();
            for (int i2 = 0; i2 < cookiesForAddToReqeust.size(); i2++) {
                arrayList.add(cookiesForAddToReqeust.get(i2));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OKHttp3", "COOKIE管理loadForRequest异常:" + e.getMessage());
        }
        return arrayList;
    }

    private static boolean isCookieExpired(Cookie cookie) {
        return cookie.expiresAt() < System.currentTimeMillis();
    }

    @Override // com.loopj.android.http.cookie.ClearableCookieJar
    public synchronized void clearSession() {
        try {
            this.cache.clear();
            this.persistor.clear();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OKHttp3", "COOKIE管理clearSession异常:" + e.getMessage());
        }
    }

    @Override // com.loopj.android.http.cookie.ClearableCookieJar
    public synchronized void clear() {
        try {
            this.cache.clear();
            this.persistor.clear();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OKHttp3", "COOKIE管理clear异常:" + e.getMessage());
        }
    }

    public ArrayList<Cookie> getOKHttpCacheCookies() {
        ArrayList<Cookie> arrayList = new ArrayList<>();
        try {
            CopyOnWriteArrayList<IdentifiableCookie> cacheCookies = this.cache.getCacheCookies();
            for (int i = 0; i < cacheCookies.size(); i++) {
                arrayList.add(cacheCookies.get(i).getCookie());
            }
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("OKHttp3", "COOKIE管理getOKHttpCacheCookies异常:" + e.getMessage());
        }
        return arrayList;
    }
}
