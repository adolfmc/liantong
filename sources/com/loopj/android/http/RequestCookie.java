package com.loopj.android.http;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RequestCookie {
    private List<Cookie> cookiesForAddToReqeust = new ArrayList();

    public void addCookieForAddToReqeust(Cookie cookie) {
        Cookie cookie2 = null;
        try {
            Iterator<Cookie> it = this.cookiesForAddToReqeust.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Cookie next = it.next();
                if (next.domain().equals(cookie.domain()) && next.name().equals(cookie.name()) && next.path().equals(cookie.path())) {
                    cookie2 = next;
                    break;
                }
            }
            if (cookie2 != null) {
                this.cookiesForAddToReqeust.remove(cookie2);
            }
            this.cookiesForAddToReqeust.add(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cookie> getCookiesForAddToReqeust() {
        return this.cookiesForAddToReqeust;
    }

    public void clear() {
        this.cookiesForAddToReqeust = new ArrayList();
    }
}
