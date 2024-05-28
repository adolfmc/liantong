package com.nostra13.universalimageloader.cache.disc;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.utils.IoUtils;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface DiskCache {
    void clear();

    void close();

    File get(String str);

    File getDirectory();

    boolean remove(String str);

    boolean save(String str, Bitmap bitmap) throws IOException;

    boolean save(String str, InputStream inputStream, IoUtils.CopyListener copyListener) throws IOException;
}
