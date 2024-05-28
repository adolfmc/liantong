package com.baidu.mapsdkplatform.comapi.commonutils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.mapsdkplatform.comapi.commonutils.a */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C2888a {

    /* renamed from: a */
    private static final boolean f7139a;

    static {
        f7139a = Build.VERSION.SDK_INT >= 8;
    }

    /* renamed from: a */
    public static Bitmap m18473a(String str, Context context) {
        try {
            InputStream open = context.getAssets().open(str);
            if (open != null) {
                return BitmapFactory.decodeStream(open);
            }
            return null;
        } catch (Exception unused) {
            return BitmapFactory.decodeFile(m18472a("assets/" + str, str, context));
        }
    }

    /* renamed from: a */
    private static String m18472a(String str, String str2, Context context) {
        ZipFile zipFile;
        File file;
        File file2;
        ZipEntry entry;
        StringBuilder sb = new StringBuilder(context.getFilesDir().getAbsolutePath());
        ZipFile zipFile2 = null;
        try {
            try {
                try {
                    zipFile = new ZipFile(f7139a ? context.getPackageCodePath() : "");
                    try {
                        int lastIndexOf = str2.lastIndexOf("/");
                        if (lastIndexOf > 0) {
                            file = new File(context.getFilesDir().getAbsolutePath());
                            String substring = str2.substring(0, lastIndexOf);
                            String substring2 = str2.substring(lastIndexOf + 1, str2.length());
                            file2 = new File(file.getAbsolutePath() + "/" + substring, substring2);
                        } else {
                            file = new File(context.getFilesDir(), "assets");
                            file2 = new File(file.getAbsolutePath(), str2);
                        }
                        file.mkdirs();
                        entry = zipFile.getEntry(str);
                    } catch (Exception e) {
                        e = e;
                        zipFile2 = zipFile;
                        Log.e(C2888a.class.getSimpleName(), "copyAssetsError", e);
                        if (zipFile2 != null) {
                            zipFile2.close();
                        }
                        return sb.toString();
                    } catch (Throwable th) {
                        th = th;
                        if (zipFile != null) {
                            try {
                                zipFile.close();
                            } catch (IOException unused) {
                            }
                        }
                        throw th;
                    }
                } catch (Throwable th2) {
                    th = th2;
                    zipFile = zipFile2;
                }
            } catch (Exception e2) {
                e = e2;
            }
        } catch (IOException unused2) {
        }
        if (entry == null) {
            try {
                zipFile.close();
            } catch (IOException unused3) {
            }
            return null;
        }
        m18474a(zipFile.getInputStream(entry), new FileOutputStream(file2));
        sb.append("/");
        sb.append(str);
        zipFile.close();
        return sb.toString();
    }

    /* renamed from: a */
    private static void m18474a(InputStream inputStream, FileOutputStream fileOutputStream) throws IOException {
        byte[] bArr = new byte[4096];
        while (true) {
            try {
                int read = inputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                fileOutputStream.write(bArr, 0, read);
            } finally {
                try {
                    inputStream.close();
                } catch (IOException unused) {
                }
                try {
                    fileOutputStream.close();
                } catch (IOException unused2) {
                }
            }
        }
        fileOutputStream.flush();
        try {
            fileOutputStream.close();
        } catch (IOException unused3) {
        }
    }
}
