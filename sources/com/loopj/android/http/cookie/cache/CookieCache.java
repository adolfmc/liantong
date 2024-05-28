package com.loopj.android.http.cookie.cache;

import java.util.Collection;
import java.util.concurrent.CopyOnWriteArrayList;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface CookieCache {
    void addAll(Collection<Cookie> collection);

    void clear();

    CopyOnWriteArrayList<IdentifiableCookie> getCacheCookies();

    void removeAll(Collection<Cookie> collection);
}
