package com.baidu.cloud.videocache;

import android.content.Context;
import android.os.Environment;
import java.io.File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.cloud.videocache.n */
/* loaded from: E:\10201592_dexfile_execute.dex */
final class C2578n {

    /* renamed from: a */
    private static final Logger f4899a = LoggerFactory.getLogger("StorageUtils");

    /* renamed from: a */
    public static File m19778a(Context context) {
        return new File(m19777a(context, true), "video-cache");
    }

    /* renamed from: a */
    private static File m19777a(Context context, boolean z) {
        String str;
        try {
            str = Environment.getExternalStorageState();
        } catch (NullPointerException unused) {
            str = "";
        }
        File m19776b = (z && "mounted".equals(str)) ? m19776b(context) : null;
        if (m19776b == null) {
            m19776b = context.getCacheDir();
        }
        if (m19776b == null) {
            String str2 = "/data/data/" + context.getPackageName() + "/cache/";
            f4899a.warn("Can't define system cache directory! '" + str2 + "%s' will be used.");
            return new File(str2);
        }
        return m19776b;
    }

    /* renamed from: b */
    private static File m19776b(Context context) {
        File file = new File(new File(new File(new File(Environment.getExternalStorageDirectory(), "Android"), "data"), context.getPackageName()), "cache");
        if (file.exists() || file.mkdirs()) {
            return file;
        }
        f4899a.warn("Unable to create external cache directory");
        return null;
    }
}
