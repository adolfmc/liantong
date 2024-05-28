package com.bytedance.pangle.p177e;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import com.bytedance.pangle.util.C3950i;
import com.bytedance.pangle.util.MethodUtils;
import com.chinaunicon.jtwifilib.jtcommon.util.JtClient;
import java.io.File;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.e.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3805b {

    /* renamed from: a */
    public static int f9103a = 1;

    /* renamed from: b */
    public static int f9104b = 2;

    /* renamed from: a */
    public static SharedPreferences m16906a(Context context) {
        return context.getApplicationContext().getSharedPreferences("plugin_oat_info", 0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String[] m16903a(String str, String str2, int i) {
        ArrayList arrayList = new ArrayList();
        arrayList.add("dex2oat");
        if (Build.VERSION.SDK_INT >= 24) {
            arrayList.add("--runtime-arg");
            arrayList.add("-classpath");
            arrayList.add("--runtime-arg");
            arrayList.add("&");
        }
        arrayList.add("--instruction-set=" + m16907a());
        if (i == f9103a) {
            if (C3950i.m16616h()) {
                arrayList.add("--compiler-filter=quicken");
            } else {
                arrayList.add("--compiler-filter=interpret-only");
            }
        } else if (i == f9104b) {
            arrayList.add("--compiler-filter=speed");
        }
        arrayList.add("--dex-file=".concat(String.valueOf(str)));
        arrayList.add("--oat-file=".concat(String.valueOf(str2)));
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    /* renamed from: a */
    public static String m16907a() {
        try {
            return (String) MethodUtils.invokeStaticMethod(Class.forName("dalvik.system.VMRuntime"), "getCurrentInstructionSet", new Object[0]);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static String m16905a(String str) {
        String substring = str.substring(str.lastIndexOf("/") + 1);
        String substring2 = substring.substring(substring.lastIndexOf("."));
        String str2 = Build.VERSION.SDK_INT >= 26 ? ".odex" : ".dex";
        if (".dex".equals(substring2)) {
            return substring;
        }
        if (JtClient.UXUE_TEMP_FILE_SUFFIX.equals(substring2) || ".apk".equals(substring2)) {
            return substring.replace(substring2, str2);
        }
        return substring + str2;
    }

    /* renamed from: b */
    public static String m16900b(String str) {
        String substring = str.substring(str.lastIndexOf("."));
        String str2 = Build.VERSION.SDK_INT >= 26 ? ".odex" : ".dex";
        if (".dex".equals(substring)) {
            return substring;
        }
        if (JtClient.UXUE_TEMP_FILE_SUFFIX.equals(substring) || ".apk".equals(substring)) {
            return str2;
        }
        return str + str2;
    }

    /* renamed from: a */
    public static void m16904a(String str, String str2) {
        C3803a.m16908a(m16903a(str, str2, f9103a));
    }

    /* renamed from: a */
    public static boolean m16901a(String... strArr) {
        for (int i = 0; i <= 0; i++) {
            File file = new File(strArr[0]);
            if (!file.exists() || !C3817h.m16883a(file)) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m16902a(String str, String... strArr) {
        for (String str2 : strArr) {
            if (!new File(str + File.separator + m16905a(str2)).exists()) {
                return false;
            }
        }
        return true;
    }
}
