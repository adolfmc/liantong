package p001a.p002a.p003a.p055d;

import android.os.Environment;
import android.text.TextUtils;
import com.baidu.license.LicenseManager;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.security.MessageDigest;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import p001a.p002a.p003a.p004a.RequestParameterUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.d.nx */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class EffectInfoHelp {

    /* renamed from: a */
    public static final char[] f2076a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* renamed from: a */
    public static File m22360a() {
        File file = new File(Environment.getExternalStorageState().equals("mounted") ? LicenseManager.sContext.getExternalFilesDir(null) : null, "ugccapture");
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file.getAbsolutePath(), "aeffect");
        if (!file2.exists()) {
            file2.mkdirs();
        }
        return file2;
    }

    /* renamed from: b */
    public static boolean m22356b(String str) {
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        return RequestParameterUtil.m22363b(m22360a().getAbsolutePath() + File.separator + m22355c(str));
    }

    /* renamed from: c */
    public static String m22355c(String str) {
        if (str == null) {
            return null;
        }
        try {
            return m22357a(str.getBytes("UTF-8"));
        } catch (Exception unused) {
            return null;
        }
    }

    /* renamed from: a */
    public static String m22358a(String str) {
        return m22360a().getAbsolutePath() + File.separator + m22355c(str);
    }

    /* renamed from: a */
    public static String m22357a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            if (digest == null) {
                return null;
            }
            StringBuilder sb = new StringBuilder(digest.length * 2);
            for (int i = 0; i < digest.length; i++) {
                sb.append(f2076a[(digest[i] & 240) >>> 4]);
                sb.append(f2076a[digest[i] & 15]);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static void m22359a(File file, String str) {
        File file2 = new File(str);
        if (!file2.exists()) {
            file2.mkdirs();
        }
        String absolutePath = file2.getAbsolutePath();
        ZipFile zipFile = new ZipFile(file);
        Enumeration<? extends ZipEntry> entries = zipFile.entries();
        while (entries.hasMoreElements()) {
            ZipEntry nextElement = entries.nextElement();
            String name = nextElement.getName();
            if (!"./".equals(name) && !".".equals(name) && !name.endsWith("/")) {
                InputStream inputStream = zipFile.getInputStream(nextElement);
                File file3 = new File(absolutePath + File.separator + name);
                if (!file3.exists()) {
                    File parentFile = file3.getParentFile();
                    if (!parentFile.exists()) {
                        parentFile.mkdirs();
                    }
                    file3.createNewFile();
                }
                FileOutputStream fileOutputStream = new FileOutputStream(file3);
                byte[] bArr = new byte[10240];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read <= 0) {
                        break;
                    }
                    fileOutputStream.write(bArr, 0, read);
                }
                inputStream.close();
                fileOutputStream.close();
            }
        }
    }
}
