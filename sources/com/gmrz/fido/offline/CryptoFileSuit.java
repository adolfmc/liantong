package com.gmrz.fido.offline;

import android.content.Context;
import com.gmrz.appsdk.util.Logger;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.regex.Pattern;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.gmrz.fido.offline.i */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CryptoFileSuit {
    /* renamed from: a */
    public static boolean m15722a(Context context, String str, String str2) {
        try {
            File file = new File(context.getFilesDir().getAbsoluteFile() + "/" + str);
            if (m15721a(file)) {
                m15720a(new ByteArrayInputStream(str2.getBytes()), file);
                return true;
            }
            Logger.m15757e("CryptoFileSuit", "file path validate failed");
            throw new IllegalArgumentException("file path validate failed");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /* renamed from: b */
    public static String m15719b(Context context, String str) {
        Throwable th;
        BufferedReader bufferedReader;
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(context.getFilesDir().getAbsoluteFile() + "/" + str);
            if (m15721a(file)) {
                bufferedReader = new BufferedReader(new FileReader(file));
                while (true) {
                    try {
                        String readLine = bufferedReader.readLine();
                        if (readLine != null) {
                            sb.append(readLine);
                        } else {
                            try {
                                break;
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception unused) {
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                        }
                        return null;
                    } catch (Throwable th2) {
                        th = th2;
                        if (bufferedReader != null) {
                            try {
                                bufferedReader.close();
                            } catch (Exception e3) {
                                e3.printStackTrace();
                            }
                        }
                        throw th;
                    }
                }
                bufferedReader.close();
                return sb.toString();
            }
            Logger.m15757e("CryptoFileSuit", "file path validate failed");
            throw new IllegalArgumentException("file path validate failed");
        } catch (Exception unused2) {
            bufferedReader = null;
        } catch (Throwable th3) {
            th = th3;
            bufferedReader = null;
        }
    }

    /* renamed from: a */
    public static boolean m15723a(Context context, String str) {
        return new File(context.getFilesDir().getAbsoluteFile() + "/" + str).delete();
    }

    /* renamed from: a */
    private static void m15720a(InputStream inputStream, File file) {
        try {
            if (!m15721a(file)) {
                Logger.m15757e("CryptoFileSuit", "file path validate failed");
                return;
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            byte[] bArr = new byte[1024];
            while (true) {
                int read = inputStream.read(bArr);
                if (read > 0) {
                    fileOutputStream.write(bArr, 0, read);
                } else {
                    fileOutputStream.close();
                    return;
                }
            }
        } finally {
            inputStream.close();
        }
    }

    /* renamed from: a */
    private static boolean m15721a(File file) {
        return Pattern.compile("^.{0,100}$", 2).matcher(file.getCanonicalPath()).matches();
    }
}
