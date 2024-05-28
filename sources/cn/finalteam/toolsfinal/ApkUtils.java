package cn.finalteam.toolsfinal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.net.Uri;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class ApkUtils {
    public static void install(Context context, File file) {
        Intent intent = new Intent("android.intent.action.VIEW");
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        intent.setFlags(268435456);
        context.startActivity(intent);
    }

    public static void uninstall(Context context, String str) {
        context.startActivity(new Intent("android.intent.action.DELETE", Uri.parse("package:" + str)));
    }

    public static boolean isAvilible(Context context, String str) {
        List<PackageInfo> installedPackages = context.getPackageManager().getInstalledPackages(0);
        ArrayList arrayList = new ArrayList();
        if (installedPackages != null) {
            for (int i = 0; i < installedPackages.size(); i++) {
                arrayList.add(installedPackages.get(i).packageName);
            }
        }
        return arrayList.contains(str);
    }

    /* JADX WARN: Type inference failed for: r2v12, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r2v8, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v9 */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x003f -> B:40:0x0054). Please submit an issue!!! */
    public static String getChannelFromApk(Context context, String str) {
        ZipFile zipFile;
        String[] split;
        ?? hasMoreElements;
        String str2 = "META-INF/" + str;
        String str3 = "";
        ZipFile zipFile2 = null;
        try {
        } catch (IOException e) {
            e.printStackTrace();
            zipFile2 = zipFile2;
        }
        try {
            try {
                zipFile = new ZipFile(context.getApplicationInfo().sourceDir);
                try {
                    Enumeration<? extends ZipEntry> entries = zipFile.entries();
                    while (true) {
                        hasMoreElements = entries.hasMoreElements();
                        if (hasMoreElements == 0) {
                            break;
                        }
                        hasMoreElements = entries.nextElement().getName();
                        if (hasMoreElements.startsWith(str2)) {
                            str3 = hasMoreElements;
                            break;
                        }
                    }
                    zipFile.close();
                    zipFile2 = hasMoreElements;
                } catch (IOException e2) {
                    e = e2;
                    zipFile2 = zipFile;
                    e.printStackTrace();
                    if (zipFile2 != null) {
                        zipFile2.close();
                        zipFile2 = zipFile2;
                    }
                    split = str3.split(str);
                    return split == null ? "" : "";
                } catch (Throwable th) {
                    th = th;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e3) {
                            e3.printStackTrace();
                        }
                    }
                    throw th;
                }
            } catch (IOException e4) {
                e = e4;
            }
            split = str3.split(str);
            if (split == null && split.length >= 2) {
                return str3.substring(str2.length());
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = zipFile2;
        }
    }
}
