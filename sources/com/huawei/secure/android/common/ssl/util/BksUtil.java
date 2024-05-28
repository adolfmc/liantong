package com.huawei.secure.android.common.ssl.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BksUtil {

    /* renamed from: a */
    private static final String f12087a = "BksUtil";

    /* renamed from: b */
    private static final String f12088b = "com.huawei.hwid";

    /* renamed from: c */
    private static final String f12089c = "com.huawei.hwid";

    /* renamed from: d */
    private static final String f12090d = "com.huawei.hms";

    /* renamed from: e */
    private static final String f12091e = "com.huawei.hwid.tv";

    /* renamed from: g */
    private static final String f12093g = "files/hmsrootcas.bks";

    /* renamed from: h */
    private static final String f12094h = "4.0.2.300";

    /* renamed from: i */
    private static final String f12095i = "aegis";

    /* renamed from: j */
    private static final String f12096j = "hmsrootcas.bks";

    /* renamed from: k */
    private static final long f12097k = 604800000;

    /* renamed from: l */
    private static final String f12098l = "last_update_time";

    /* renamed from: m */
    private static final String f12099m = "B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05";

    /* renamed from: n */
    private static final String f12100n = "3517262215D8D3008CBF888750B6418EDC4D562AC33ED6874E0D73ABA667BC3C";

    /* renamed from: o */
    private static final String f12101o = "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539";

    /* renamed from: q */
    private static final String f12103q = "";

    /* renamed from: r */
    private static final String f12104r = "bks_hash";

    /* renamed from: f */
    private static final Uri f12092f = Uri.parse("content://com.huawei.hwid");

    /* renamed from: p */
    private static final String[] f12102p = {"B92825C2BD5D6D6D1E7F39EECD17843B7D9016F611136B75441BC6F4D3F00F05", "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539"};

    private BksUtil() {
    }

    /* renamed from: a */
    private static void m13895a(InputStream inputStream, Context context) {
        if (inputStream == null || context == null) {
            return;
        }
        String m13897a = m13897a(context);
        if (!new File(m13897a).exists()) {
            m13894a(m13897a);
        }
        File file = new File(m13897a, "hmsrootcas.bks");
        if (file.exists()) {
            file.delete();
        }
        FileOutputStream fileOutputStream = null;
        try {
            C5118e.m13853c("BksUtil", "write output stream ");
            FileOutputStream fileOutputStream2 = new FileOutputStream(file);
            try {
                byte[] bArr = new byte[2048];
                while (true) {
                    int read = inputStream.read(bArr, 0, bArr.length);
                    if (read != -1) {
                        fileOutputStream2.write(bArr, 0, read);
                    } else {
                        AbstractC5117d.m13863a((OutputStream) fileOutputStream2);
                        return;
                    }
                }
            } catch (IOException unused) {
                fileOutputStream = fileOutputStream2;
                try {
                    C5118e.m13854b("BksUtil", " IOException");
                    AbstractC5117d.m13863a((OutputStream) fileOutputStream);
                } catch (Throwable th) {
                    th = th;
                    AbstractC5117d.m13863a((OutputStream) fileOutputStream);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                fileOutputStream = fileOutputStream2;
                AbstractC5117d.m13863a((OutputStream) fileOutputStream);
                throw th;
            }
        } catch (IOException unused2) {
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* renamed from: a */
    private static boolean m13898a(int i) {
        return i >= 40002300;
    }

    /* renamed from: b */
    private static String m13892b(Context context) {
        return m13897a(context) + File.separator + "hmsrootcas.bks";
    }

    /* renamed from: c */
    private static boolean m13888c(Context context) {
        return new File(m13897a(context) + File.separator + "hmsrootcas.bks").exists();
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v20 */
    /* JADX WARN: Type inference failed for: r2v7, types: [java.io.OutputStream] */
    public static synchronized InputStream getBksFromTss(Context context) {
        ByteArrayInputStream byteArrayInputStream;
        InputStream inputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        String m13844a;
        String m13889b;
        synchronized (BksUtil.class) {
            C5118e.m13853c("BksUtil", "get bks from tss begin");
            if (context != null) {
                ContextUtil.setContext(context);
            }
            Context contextUtil = ContextUtil.getInstance();
            ByteArrayInputStream byteArrayInputStream2 = null;
            if (contextUtil == null) {
                C5118e.m13854b("BksUtil", "context is null");
                return null;
            } else if (!m13890b(C5119f.m13850a("com.huawei.hwid")) && !m13890b(C5119f.m13850a("com.huawei.hms"))) {
                C5118e.m13854b("BksUtil", "hms version code is too low : " + C5119f.m13850a("com.huawei.hwid"));
                return null;
            } else {
                boolean m13887c = m13887c(contextUtil, "com.huawei.hwid");
                boolean z = m13887c;
                if (!m13887c) {
                    boolean m13891b = m13891b(contextUtil, "com.huawei.hms");
                    z = m13891b;
                    if (!m13891b) {
                        C5118e.m13854b("BksUtil", "hms sign error");
                        return null;
                    }
                }
                try {
                    ByteArrayOutputStream byteArrayOutputStream2 = new ByteArrayOutputStream();
                    try {
                        inputStream = contextUtil.getContentResolver().openInputStream(Uri.withAppendedPath(f12092f, "files/hmsrootcas.bks"));
                        try {
                            byte[] bArr = new byte[1024];
                            while (true) {
                                int read = inputStream.read(bArr);
                                if (read <= -1) {
                                    break;
                                }
                                byteArrayOutputStream2.write(bArr, 0, read);
                            }
                            byteArrayOutputStream2.flush();
                            byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream2.toByteArray());
                            try {
                                m13844a = C5120g.m13844a("bks_hash", "", contextUtil);
                                m13889b = m13889b(byteArrayOutputStream2.toByteArray());
                            } catch (Exception e) {
                                e = e;
                                byteArrayInputStream2 = byteArrayInputStream;
                                C5118e.m13854b("BksUtil", "Get bks from HMS_VERSION_CODE exception : No content provider" + e.getMessage());
                                AbstractC5117d.m13866a(inputStream);
                                AbstractC5117d.m13863a((OutputStream) byteArrayOutputStream2);
                                AbstractC5117d.m13866a((InputStream) byteArrayInputStream2);
                                return getFilesBksIS(contextUtil);
                            } catch (Throwable th) {
                                th = th;
                                byteArrayOutputStream = byteArrayOutputStream2;
                                AbstractC5117d.m13866a(inputStream);
                                AbstractC5117d.m13863a((OutputStream) byteArrayOutputStream);
                                AbstractC5117d.m13866a((InputStream) byteArrayInputStream);
                                throw th;
                            }
                        } catch (Exception e2) {
                            e = e2;
                        }
                    } catch (Exception e3) {
                        e = e3;
                        inputStream = null;
                    } catch (Throwable th2) {
                        th = th2;
                        inputStream = null;
                        byteArrayInputStream = null;
                        byteArrayOutputStream = byteArrayOutputStream2;
                    }
                    if (m13888c(contextUtil) && m13844a.equals(m13889b)) {
                        C5118e.m13853c("BksUtil", "bks not update");
                        AbstractC5117d.m13866a(inputStream);
                        AbstractC5117d.m13863a((OutputStream) byteArrayOutputStream2);
                        AbstractC5117d.m13866a((InputStream) byteArrayInputStream);
                        return getFilesBksIS(contextUtil);
                    }
                    C5118e.m13853c("BksUtil", "update bks and sp");
                    m13895a(byteArrayInputStream, contextUtil);
                    C5120g.m13840b("bks_hash", m13889b, contextUtil);
                    AbstractC5117d.m13866a(inputStream);
                    AbstractC5117d.m13863a((OutputStream) byteArrayOutputStream2);
                    AbstractC5117d.m13866a((InputStream) byteArrayInputStream);
                    return getFilesBksIS(contextUtil);
                } catch (Throwable th3) {
                    th = th3;
                    byteArrayInputStream = null;
                    byteArrayOutputStream = z;
                }
            }
        }
    }

    public static InputStream getFilesBksIS(Context context) {
        if (m13888c(context)) {
            C5118e.m13853c("BksUtil", "getFilesBksIS ");
            try {
                return new FileInputStream(m13892b(context));
            } catch (FileNotFoundException unused) {
                C5118e.m13854b("BksUtil", "FileNotFoundExceptio: ");
                return null;
            }
        }
        return null;
    }

    /* renamed from: b */
    private static boolean m13890b(String str) {
        int parseInt;
        if (TextUtils.isEmpty(str)) {
            return false;
        }
        C5118e.m13853c("BksUtil", "hms version code is : " + str);
        String[] split = str.split("\\.");
        String[] split2 = "4.0.2.300".split("\\.");
        int length = split.length;
        int length2 = split2.length;
        int max = Math.max(length, length2);
        int i = 0;
        while (i < max) {
            if (i < length) {
                try {
                    parseInt = Integer.parseInt(split[i]);
                } catch (Exception e) {
                    C5118e.m13854b("BksUtil", " exception : " + e.getMessage());
                    return i >= length2;
                }
            } else {
                parseInt = 0;
            }
            int parseInt2 = i < length2 ? Integer.parseInt(split2[i]) : 0;
            if (parseInt < parseInt2) {
                return false;
            }
            if (parseInt > parseInt2) {
                return true;
            }
            i++;
        }
        return true;
    }

    /* renamed from: c */
    private static boolean m13887c(Context context, String str) {
        byte[] m13896a = m13896a(context, str);
        for (String str2 : f12102p) {
            if (str2.equalsIgnoreCase(m13886c(m13896a))) {
                return true;
            }
        }
        return false;
    }

    /* renamed from: c */
    private static String m13886c(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        try {
            return m13893a(MessageDigest.getInstance("SHA-256").digest(bArr));
        } catch (NoSuchAlgorithmException e) {
            Log.e("BksUtil", "NoSuchAlgorithmException" + e.getMessage());
            return "";
        }
    }

    /* renamed from: b */
    private static boolean m13891b(Context context, String str) {
        return "E49D5C2C0E11B3B1B96CA56C6DE2A14EC7DAB5CCC3B5F300D03E5B4DBA44F539".equalsIgnoreCase(m13886c(m13896a(context, str)));
    }

    /* renamed from: a */
    private static int m13894a(String str) {
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        File file = new File(str);
        if (file.exists()) {
            C5118e.m13851e("BksUtil", "The directory  has already exists");
            return 1;
        } else if (file.mkdirs()) {
            C5118e.m13856a("BksUtil", "create directory  success");
            return 0;
        } else {
            C5118e.m13854b("BksUtil", "create directory  failed");
            return -1;
        }
    }

    /* renamed from: b */
    private static String m13889b(byte[] bArr) {
        if (bArr == null) {
            return "";
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return m13893a(messageDigest.digest());
        } catch (NoSuchAlgorithmException unused) {
            C5118e.m13854b("BksUtil", "inputstraem exception");
            return "";
        }
    }

    /* renamed from: a */
    private static String m13897a(Context context) {
        if (Build.VERSION.SDK_INT >= 24) {
            return context.createDeviceProtectedStorageContext().getFilesDir() + File.separator + "aegis";
        }
        return context.getApplicationContext().getFilesDir() + File.separator + "aegis";
    }

    /* renamed from: a */
    private static byte[] m13896a(Context context, String str) {
        PackageInfo packageInfo;
        if (context != null && !TextUtils.isEmpty(str)) {
            try {
                PackageManager packageManager = context.getPackageManager();
                if (packageManager != null && (packageInfo = packageManager.getPackageInfo(str, 64)) != null) {
                    return packageInfo.signatures[0].toByteArray();
                }
            } catch (PackageManager.NameNotFoundException e) {
                Log.e("BksUtil", "PackageManager.NameNotFoundException : " + e.getMessage());
            } catch (Exception e2) {
                Log.e("BksUtil", "get pm exception : " + e2.getMessage());
            }
            return new byte[0];
        }
        Log.e("BksUtil", "packageName is null or context is null");
        return new byte[0];
    }

    /* renamed from: a */
    private static String m13893a(byte[] bArr) {
        if (bArr == null || bArr.length == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append('0');
            }
            sb.append(hexString);
        }
        return sb.toString();
    }
}
