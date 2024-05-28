package com.loopj.android.http.cookie.persistence;

import java.util.Collection;
import java.util.List;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface CookiePersistor {
    void clear();

    List<Cookie> loadAll();

    void removeAll(Collection<Cookie> collection);

    void saveAll(Collection<Cookie> collection);
}
