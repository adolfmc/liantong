package com.loopj.android.http.cookie.cache;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SetCookieCache implements CookieCache {
    private CopyOnWriteArrayList<IdentifiableCookie> cookies = new CopyOnWriteArrayList<>();

    @Override // com.loopj.android.http.cookie.cache.CookieCache
    public void addAll(Collection<Cookie> collection) {
        for (IdentifiableCookie identifiableCookie : IdentifiableCookie.decorateAll(collection)) {
            this.cookies.remove(identifiableCookie);
            this.cookies.add(identifiableCookie);
        }
    }

    @Override // com.loopj.android.http.cookie.cache.CookieCache
    public void clear() {
        this.cookies.clear();
    }

    @Override // com.loopj.android.http.cookie.cache.CookieCache
    public CopyOnWriteArrayList<IdentifiableCookie> getCacheCookies() {
        return this.cookies;
    }

    @Override // com.loopj.android.http.cookie.cache.CookieCache
    public void removeAll(Collection<Cookie> collection) {
        for (IdentifiableCookie identifiableCookie : IdentifiableCookie.decorateAll(collection)) {
            this.cookies.remove(identifiableCookie);
        }
    }
}
