package p000;

import android.os.Build;
import android.os.Environment;
import android.text.TextUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: f0 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class RomUtils {
    /* renamed from: a */
    public static int m2044a() {
        if (m2042c()) {
            return 2;
        }
        if (m2040e()) {
            return 3;
        }
        if (m2041d()) {
            return 1;
        }
        return m2043b() ? 3 : 4;
    }

    /* renamed from: b */
    public static boolean m2043b() {
        return Build.VERSION.SDK_INT >= 23;
    }

    /* renamed from: c */
    public static boolean m2042c() {
        String str = Build.DISPLAY;
        if (!TextUtils.isEmpty(str) && str.contains("Flyme")) {
            for (String str2 : str.split(" ")) {
                if (str2.matches("^[4-9]\\.(\\d+\\.)+\\S*")) {
                    return true;
                }
            }
        }
        return false;
    }

    /* renamed from: d */
    public static boolean m2041d() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty("ro.miui.ui.version.code", null);
            if (property != null) {
                return Integer.parseInt(property) >= 4;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }

    /* renamed from: e */
    public static boolean m2040e() {
        try {
            Properties properties = new Properties();
            properties.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
            String property = properties.getProperty("ro.miui.ui.version.code", null);
            if (property != null) {
                return Integer.parseInt(property) >= 5;
            }
            return false;
        } catch (Exception unused) {
            return false;
        }
    }
}
