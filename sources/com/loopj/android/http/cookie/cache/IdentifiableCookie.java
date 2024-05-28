package com.loopj.android.http.cookie.cache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class IdentifiableCookie {
    private Cookie cookie;

    public static List<IdentifiableCookie> decorateAll(Collection<Cookie> collection) {
        ArrayList arrayList = new ArrayList(collection.size());
        for (Cookie cookie : collection) {
            arrayList.add(new IdentifiableCookie(cookie));
        }
        return arrayList;
    }

    public IdentifiableCookie(Cookie cookie) {
        this.cookie = cookie;
    }

    public Cookie getCookie() {
        return this.cookie;
    }

    public boolean equals(Object obj) {
        if (obj instanceof IdentifiableCookie) {
            IdentifiableCookie identifiableCookie = (IdentifiableCookie) obj;
            return identifiableCookie.cookie.name().equals(this.cookie.name()) && identifiableCookie.cookie.domain().equals(this.cookie.domain()) && identifiableCookie.cookie.path().equals(this.cookie.path()) && identifiableCookie.cookie.secure() == this.cookie.secure() && identifiableCookie.cookie.hostOnly() == this.cookie.hostOnly();
        }
        return false;
    }

    public int hashCode() {
        return ((((((((527 + this.cookie.name().hashCode()) * 31) + this.cookie.domain().hashCode()) * 31) + this.cookie.path().hashCode()) * 31) + (!this.cookie.secure())) * 31) + (!this.cookie.hostOnly());
    }
}
