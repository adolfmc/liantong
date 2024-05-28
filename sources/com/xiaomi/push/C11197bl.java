package com.xiaomi.push;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.text.TextUtils;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.manager.C11053a;
import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.bl */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11197bl {
    /* renamed from: a */
    public static String m4719a() {
        return Build.VERSION.RELEASE + "-" + Build.VERSION.INCREMENTAL;
    }

    @TargetApi(9)
    /* renamed from: a */
    public static byte[] m4711a(String str) {
        byte[] copyOf = Arrays.copyOf(C11180ay.m4796a(str), 16);
        copyOf[0] = 68;
        copyOf[15] = 84;
        return copyOf;
    }

    /* renamed from: a */
    public static String m4718a(Context context) {
        String m4707a = C11200bm.m4710a(context).m4707a("sp_client_report_status", "sp_client_report_key", "");
        if (TextUtils.isEmpty(m4707a)) {
            String m4758a = C11184bb.m4758a(20);
            C11200bm.m4710a(context).m4706a("sp_client_report_status", "sp_client_report_key", m4758a);
            return m4758a;
        }
        return m4707a;
    }

    /* renamed from: a */
    public static boolean m4717a(Context context) {
        try {
            return context.getApplicationContext().getPackageManager().getPackageInfo("com.xiaomi.xmsf", 0).versionCode >= 108;
        } catch (PackageManager.NameNotFoundException unused) {
            return false;
        }
    }

    /* renamed from: a */
    public static void m4716a(Context context, String str) {
        Intent intent = new Intent("com.xiaomi.xmsf.push.XMSF_UPLOAD_ACTIVE");
        intent.putExtra("pkgname", context.getPackageName());
        intent.putExtra("category", "category_client_report_data");
        intent.putExtra("name", "quality_support");
        intent.putExtra("data", str);
        context.sendBroadcast(intent, "com.xiaomi.xmsf.permission.USE_XMSF_UPLOAD");
    }

    /* renamed from: a */
    public static void m4712a(Context context, List<String> list) {
        if (list == null || list.size() <= 0 || !m4717a(context)) {
            return;
        }
        for (String str : list) {
            if (!TextUtils.isEmpty(str)) {
                m4716a(context, str);
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:61:0x00f4, code lost:
        if (r7 == null) goto L56;
     */
    /* JADX WARN: Removed duplicated region for block: B:74:0x0112  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m4713a(android.content.Context r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 309
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.xiaomi.push.C11197bl.m4713a(android.content.Context, java.lang.String, java.lang.String):void");
    }

    /* renamed from: a */
    public static boolean m4715a(Context context, String str) {
        File file = new File(str);
        long maxFileLength = C11053a.m5261a(context).m5264a().getMaxFileLength();
        if (file.exists()) {
            try {
                return file.length() <= maxFileLength;
            } catch (Exception e) {
                AbstractC11049b.m5276a(e);
                return false;
            }
        }
        C11647w.m2272a(file);
        return true;
    }

    /* renamed from: a */
    public static File[] m4714a(Context context, String str) {
        return new File(context.getFilesDir(), str).listFiles(new FilenameFilter() { // from class: com.xiaomi.push.bl.2
            @Override // java.io.FilenameFilter
            public boolean accept(File file, String str2) {
                return (TextUtils.isEmpty(str2) || str2.toLowerCase().endsWith(".lock")) ? false : true;
            }
        });
    }
}
