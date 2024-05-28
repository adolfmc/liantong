package com.bytedance.pangle.p176d;

import android.app.Application;
import android.os.Environment;
import android.text.TextUtils;
import com.bytedance.pangle.C3837g;
import com.bytedance.pangle.GlobalParam;
import com.bytedance.pangle.Zeus;
import com.bytedance.pangle.p177e.C3805b;
import com.bytedance.pangle.util.C3950i;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.d.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3792c {

    /* renamed from: a */
    private static File f9080a;

    /* renamed from: b */
    private static File f9081b;

    /* renamed from: c */
    private static File f9082c;

    /* renamed from: d */
    private static void m16931d() {
        if (f9080a == null) {
            File filesDir = Zeus.getAppApplication().getFilesDir();
            File file = new File(filesDir, "pangle" + C3837g.f9174c);
            f9080a = file;
            m16939a(file);
        }
    }

    /* renamed from: a */
    private static String m16939a(File file) {
        if (file != null) {
            if (!file.exists()) {
                file.mkdirs();
            }
            return file.getPath();
        }
        return null;
    }

    /* renamed from: a */
    private static String m16936a(String... strArr) {
        m16931d();
        File file = f9080a;
        if (strArr.length > 0) {
            for (String str : strArr) {
                if (!TextUtils.isEmpty(str)) {
                    file = new File(file, str);
                }
            }
        }
        return m16939a(file);
    }

    /* renamed from: a */
    public static String m16940a() {
        Application appApplication = Zeus.getAppApplication();
        if (f9081b == null) {
            File downloadDir = GlobalParam.getInstance().getDownloadDir();
            if (downloadDir == null) {
                File filesDir = appApplication.getFilesDir();
                downloadDir = new File(filesDir, ".pangle" + C3837g.f9173b);
            }
            f9081b = downloadDir;
        }
        return m16939a(f9081b);
    }

    /* renamed from: b */
    public static String m16935b() {
        Application appApplication = Zeus.getAppApplication();
        if (f9082c == null) {
            File filesDir = appApplication.getFilesDir();
            f9082c = new File(filesDir, ".pangle" + C3837g.f9172a);
        }
        return m16939a(f9082c);
    }

    /* renamed from: c */
    public static String m16933c() {
        Application appApplication = Zeus.getAppApplication();
        try {
            if ("mounted".equals(Environment.getExternalStorageState())) {
                File externalFilesDir = appApplication.getExternalFilesDir(".pangle" + C3837g.f9173b);
                if (externalFilesDir != null) {
                    return m16939a(externalFilesDir);
                }
                return null;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m16937a(String str, int i) {
        m16931d();
        File file = f9080a;
        String[] strArr = {str, "version-".concat(String.valueOf(i))};
        for (int i2 = 0; i2 < 2; i2++) {
            String str2 = strArr[i2];
            if (!TextUtils.isEmpty(str2)) {
                file = new File(file, str2);
            }
        }
        if (file != null) {
            return file.getPath();
        }
        return null;
    }

    /* renamed from: a */
    public static String m16938a(String str) {
        return m16936a(str);
    }

    /* renamed from: b */
    public static String m16934b(String str, int i) {
        return new File(m16936a(str, "version-".concat(String.valueOf(i)), "apk"), "base-1.apk").getPath();
    }

    /* renamed from: c */
    public static String m16932c(String str, int i) {
        return C3950i.m16616h() ? m16936a(str, "version-".concat(String.valueOf(i)), "apk", "oat", C3805b.m16907a()) : m16936a(str, "version-".concat(String.valueOf(i)), "dalvik-cache");
    }

    /* renamed from: d */
    public static String m16930d(String str, int i) {
        return m16936a(str, "version-".concat(String.valueOf(i)), "lib");
    }

    /* renamed from: e */
    public static String m16929e(String str, int i) {
        return new File(m16936a(str, "version-".concat(String.valueOf(i)), "apk", "temp"), "base-1.apk").getPath();
    }

    /* renamed from: f */
    public static String m16928f(String str, int i) {
        return new File(m16936a(str, "version-".concat(String.valueOf(i)), "apk")).getPath();
    }

    /* renamed from: g */
    public static String m16927g(String str, int i) {
        return new File(m16936a(str, "version-".concat(String.valueOf(i)), "apk", "temp")).getPath();
    }

    /* renamed from: h */
    public static String m16926h(String str, int i) {
        return C3950i.m16616h() ? m16936a(str, "version-".concat(String.valueOf(i)), "apk", "temp", "oat", C3805b.m16907a()) : m16936a(str, "version-".concat(String.valueOf(i)), "dalvik-cache");
    }

    /* renamed from: i */
    public static String m16925i(String str, int i) {
        return m16936a(str, "version-".concat(String.valueOf(i)), "secondary-dexes");
    }
}
