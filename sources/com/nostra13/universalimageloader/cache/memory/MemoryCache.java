package com.nostra13.universalimageloader.cache.memory;

import android.graphics.Bitmap;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface MemoryCache {
    void clear();

    Bitmap get(String str);

    Collection<String> keys();

    boolean put(String str, Bitmap bitmap);

    Bitmap remove(String str);
}
