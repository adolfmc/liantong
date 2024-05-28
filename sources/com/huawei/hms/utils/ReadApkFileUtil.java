package com.huawei.hms.utils;

import android.annotation.TargetApi;
import android.content.Context;
import android.text.TextUtils;
import android.util.AndroidException;
import android.util.Base64;
import com.huawei.hms.support.log.HMSLog;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ReadApkFileUtil {
    public static final String EMUI10_PK = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx4nUogUyMCmzHhaEb420yvpw9zBs+ETzE9Qm77bGxl1Iml9JEkBkNTsUWOstLgUBajNhV+BAMVBHKMEdzoQbL5kIHkTgUVM65yewd+5+BhrcB9OQ3LHp+0BN6aLKZh71T4WvsvHFhfhQpShuGWkRkSaVGLFTHxX70kpWLzeZ3RtqiEUNIufPR2SFCH6EmecJ+HdkmBOh603IblCpGxwSWse0fDI98wZBEmV88RFaiYEgyiezLlWvXzqIj6I/xuyd5nGAegjH2y3cmoDE6CubecoB1jf4KdgACXgdiQ4Oc63MfLGTor3l6RCqeUk4APAMtyhK83jc72W1sdXMd/sj2wIDAQAB";
    public static final String EMUI11_PK = "MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqq2eRTMYr2JHLtvuZzfgPrgU8oatD4Rar9fOD7E00es2VhtB3vTyaT2BvYPUPA/nbkHRPak3EZX77CfWj9tzLgSHJE8XLk9C+2ESkdrxCDA6z7I8X+cBDnA05OlCJeZFjnUbjYB8SP8M3BttdrvqtVPxTkEJhchC7UXnMLaJ3kQ3ZPjN7ubjYzO4rv7EtEpqr2bX+qjnSLIZZuUXraxqfdBuhGDIYq62dNsqiyrhX1mfvA3+43N4ZIs3BdfSYII8BNFmFxf+gyf1aoq386R2kAjHcrfOOhjAbZh+R1OAGLWPCqi3E9nB8EsZkeoTW/oIP6pJvgL3bnxq+1viT2dmZyipMgcx/3N6FJqkd67j/sPMtPlHJuq8/s0silzs13jAw1WBV6tWHFkLGpkWGs8jp50wQtndtY8cCPl2XPGmdPN72agH+zsHuKqr/HOB2TuzzaO8rKlGIDQlzZcCSHB28nnvOyBVN9xzLkbYiLnHfd6bTwzNPeqjWrTnPwKyH3BPAgMBAAE=";
    public static final String KEY_SIGNATURE = "Signature:";
    public static final String KEY_SIGNATURE2 = "Signature2:";
    public static final String KEY_SIGNATURE3 = "Signature3:";

    /* renamed from: a */
    private static final String f11844a = "ReadApkFileUtil";

    /* renamed from: c */
    private static String f11846c;

    /* renamed from: d */
    private static String f11847d;

    /* renamed from: e */
    private static String f11848e;

    /* renamed from: b */
    private static final Pattern f11845b = Pattern.compile("\\s*|\t|\r|\n");

    /* renamed from: f */
    private static String f11849f = null;

    /* renamed from: g */
    private static String f11850g = null;

    /* renamed from: a */
    private static byte[] m14039a(ZipFile zipFile) {
        return m14038a(zipFile, "META-INF/MANIFEST.MF");
    }

    @TargetApi(19)
    /* renamed from: b */
    private static void m14033b(byte[] bArr) {
        Throwable th;
        ByteArrayInputStream byteArrayInputStream;
        BufferedReader bufferedReader;
        if (bArr == null) {
            HMSLog.m14112e(f11844a, "manifest is null！");
            return;
        }
        StringBuffer stringBuffer = new StringBuffer();
        BufferedReader bufferedReader2 = null;
        f11846c = null;
        f11847d = null;
        f11848e = null;
        try {
            byteArrayInputStream = new ByteArrayInputStream(bArr);
            try {
                try {
                    bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8));
                    try {
                        String m14044a = m14044a(bufferedReader);
                        while (m14044a != null) {
                            if (m14044a.length() != 0) {
                                if (m14044a.startsWith("ApkHash:")) {
                                    f11849f = m14042a(m14044a.substring(m14044a.indexOf(":") + 1));
                                }
                                if (m14044a.startsWith("Signature:")) {
                                    f11846c = m14042a(m14044a.substring(m14044a.indexOf(":") + 1));
                                    m14044a = m14044a(bufferedReader);
                                } else if (m14044a.startsWith("Signature2:")) {
                                    f11847d = m14042a(m14044a.substring(m14044a.indexOf(":") + 1));
                                    m14044a = m14044a(bufferedReader);
                                } else if (m14044a.startsWith("Signature3:")) {
                                    f11848e = m14042a(m14044a.substring(m14044a.indexOf(":") + 1));
                                    m14044a = m14044a(bufferedReader);
                                } else {
                                    stringBuffer.append(m14044a);
                                    stringBuffer.append("\r\n");
                                }
                            }
                            m14044a = m14044a(bufferedReader);
                        }
                        f11850g = stringBuffer.toString();
                    } catch (Exception unused) {
                        bufferedReader2 = bufferedReader;
                        HMSLog.m14112e(f11844a, "loadApkCert Exception!");
                        bufferedReader = bufferedReader2;
                        IOUtils.closeQuietly((InputStream) byteArrayInputStream);
                        IOUtils.closeQuietly((Reader) bufferedReader);
                    } catch (Throwable th2) {
                        th = th2;
                        bufferedReader2 = bufferedReader;
                        IOUtils.closeQuietly((InputStream) byteArrayInputStream);
                        IOUtils.closeQuietly((Reader) bufferedReader2);
                        throw th;
                    }
                } catch (Throwable th3) {
                    th = th3;
                }
            } catch (Exception unused2) {
            }
        } catch (Exception unused3) {
            byteArrayInputStream = null;
        } catch (Throwable th4) {
            th = th4;
            byteArrayInputStream = null;
        }
        IOUtils.closeQuietly((InputStream) byteArrayInputStream);
        IOUtils.closeQuietly((Reader) bufferedReader);
    }

    public static String bytesToString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        char[] cArr2 = new char[bArr.length * 2];
        for (int i = 0; i < bArr.length; i++) {
            int i2 = bArr[i] & 255;
            int i3 = i * 2;
            cArr2[i3] = cArr[i2 >>> 4];
            cArr2[i3 + 1] = cArr[i2 & 15];
        }
        return String.valueOf(cArr2);
    }

    /* renamed from: c */
    private static boolean m14032c() {
        try {
        } catch (Exception e) {
            String str = f11844a;
            HMSLog.m14110i(str, "verifyMDMSignatureV3 MDM verify Exception!:" + e.getMessage());
        }
        if (m14036a(Base64.decode("MIIBojANBgkqhkiG9w0BAQEFAAOCAY8AMIIBigKCAYEAqq2eRTMYr2JHLtvuZzfgPrgU8oatD4Rar9fOD7E00es2VhtB3vTyaT2BvYPUPA/nbkHRPak3EZX77CfWj9tzLgSHJE8XLk9C+2ESkdrxCDA6z7I8X+cBDnA05OlCJeZFjnUbjYB8SP8M3BttdrvqtVPxTkEJhchC7UXnMLaJ3kQ3ZPjN7ubjYzO4rv7EtEpqr2bX+qjnSLIZZuUXraxqfdBuhGDIYq62dNsqiyrhX1mfvA3+43N4ZIs3BdfSYII8BNFmFxf+gyf1aoq386R2kAjHcrfOOhjAbZh+R1OAGLWPCqi3E9nB8EsZkeoTW/oIP6pJvgL3bnxq+1viT2dmZyipMgcx/3N6FJqkd67j/sPMtPlHJuq8/s0silzs13jAw1WBV6tWHFkLGpkWGs8jp50wQtndtY8cCPl2XPGmdPN72agH+zsHuKqr/HOB2TuzzaO8rKlGIDQlzZcCSHB28nnvOyBVN9xzLkbYiLnHfd6bTwzNPeqjWrTnPwKyH3BPAgMBAAE=", 0), m14041a(f11850g, "SHA-384"), m14034b(f11848e), "SHA384withRSA")) {
            HMSLog.m14110i(f11844a, "verifyMDMSignatureV3 verify successful!");
            return true;
        }
        HMSLog.m14110i(f11844a, "verifyMDMSignatureV3 verify failure!");
        return false;
    }

    public static boolean checkSignature() {
        if (f11848e != null) {
            return m14032c();
        }
        if (f11847d != null) {
            return m14035b();
        }
        if (f11846c != null) {
            return m14045a();
        }
        return false;
    }

    public static String getHmsPath(Context context) {
        try {
            return context.getPackageManager().getApplicationInfo("com.huawei.hwid", 128).sourceDir;
        } catch (AndroidException | RuntimeException unused) {
            HMSLog.m14112e(f11844a, "HMS is not found!");
            return null;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    @TargetApi(19)
    public static boolean isCertFound(String str) {
        ZipFile zipFile;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(str);
                try {
                    boolean z = zipFile.getEntry("META-INF/HUAWEI.CER") != null;
                    if (z) {
                        m14033b(m14038a(zipFile, "META-INF/HUAWEI.CER"));
                    }
                    try {
                        zipFile.close();
                        return z;
                    } catch (IOException e) {
                        String str2 = f11844a;
                        HMSLog.m14112e(str2, "zipFile.close Exception!" + e.getMessage());
                        return z;
                    }
                } catch (Exception e2) {
                    e = e2;
                    zipFile2 = zipFile;
                    String str3 = f11844a;
                    HMSLog.m14112e(str3, "isCertFound Exception!" + e.getMessage());
                    zipFile2 = zipFile2;
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                            zipFile2 = zipFile2;
                        } catch (IOException e3) {
                            String str4 = f11844a;
                            HMSLog.m14112e(str4, "zipFile.close Exception!" + e3.getMessage());
                            zipFile2 = str4;
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (IOException e4) {
                            String str5 = f11844a;
                            HMSLog.m14112e(str5, "zipFile.close Exception!" + e4.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Exception e5) {
                e = e5;
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = zipFile2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static boolean verifyApkHash(String str) {
        ZipFile zipFile;
        String str2 = null;
        ZipFile zipFile2 = null;
        try {
            try {
                zipFile = new ZipFile(str);
                try {
                    byte[] m14039a = m14039a(zipFile);
                    ArrayList<String> m14037a = m14037a(m14039a);
                    if (m14037a != null) {
                        m14039a = m14040a(m14037a);
                    }
                    MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
                    messageDigest.update(m14039a);
                    String bytesToString = bytesToString(messageDigest.digest());
                    String str3 = f11849f;
                    if (str3 != null) {
                        if (str3.equals(bytesToString)) {
                            try {
                                zipFile.close();
                            } catch (Exception e) {
                                String str4 = f11844a;
                                HMSLog.m14110i(str4, "close stream Exception!" + e.getMessage());
                            }
                            return true;
                        }
                    }
                    try {
                        zipFile.close();
                        return false;
                    } catch (Exception e2) {
                        String str5 = f11844a;
                        HMSLog.m14110i(str5, "close stream Exception!" + e2.getMessage());
                        return false;
                    }
                } catch (Exception e3) {
                    e = e3;
                    zipFile2 = zipFile;
                    String str6 = f11844a;
                    HMSLog.m14110i(str6, "verifyApkHash Exception!" + e.getMessage());
                    if (zipFile2 != null) {
                        try {
                            zipFile2.close();
                            return false;
                        } catch (Exception e4) {
                            str2 = f11844a;
                            HMSLog.m14110i(str2, "close stream Exception!" + e4.getMessage());
                            return false;
                        }
                    }
                    return false;
                } catch (Throwable th) {
                    th = th;
                    if (zipFile != null) {
                        try {
                            zipFile.close();
                        } catch (Exception e5) {
                            String str7 = f11844a;
                            HMSLog.m14110i(str7, "close stream Exception!" + e5.getMessage());
                        }
                    }
                    throw th;
                }
            } catch (Exception e6) {
                e = e6;
            }
        } catch (Throwable th2) {
            th = th2;
            zipFile = str2;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v2, types: [java.io.OutputStream] */
    /* JADX WARN: Type inference failed for: r4v4 */
    /* JADX WARN: Type inference failed for: r4v5, types: [java.io.OutputStream, java.io.BufferedOutputStream] */
    /* renamed from: a */
    private static byte[] m14038a(ZipFile zipFile, String str) {
        Throwable th;
        InputStream inputStream;
        Exception e;
        Throwable th2;
        BufferedInputStream bufferedInputStream;
        ByteArrayOutputStream byteArrayOutputStream;
        ?? r4;
        OutputStream outputStream;
        byte[] bArr;
        ZipEntry entry = zipFile.getEntry(str);
        OutputStream outputStream2 = null;
        if (entry == null) {
            return null;
        }
        try {
            inputStream = zipFile.getInputStream(entry);
            if (inputStream == null) {
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly((InputStream) null);
                IOUtils.closeQuietly((OutputStream) null);
                IOUtils.closeQuietly((OutputStream) null);
                return null;
            }
            try {
                bufferedInputStream = new BufferedInputStream(inputStream);
                try {
                    bArr = new byte[4096];
                    byteArrayOutputStream = new ByteArrayOutputStream();
                    try {
                        r4 = new BufferedOutputStream(byteArrayOutputStream);
                    } catch (Exception e2) {
                        e = e2;
                        r4 = 0;
                    } catch (Throwable th3) {
                        th2 = th3;
                    }
                } catch (Exception e3) {
                    e = e3;
                    byteArrayOutputStream = null;
                    r4 = byteArrayOutputStream;
                    try {
                        HMSLog.m14110i(f11844a, "getManifestBytes Exception!" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((InputStream) bufferedInputStream);
                        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                        IOUtils.closeQuietly((OutputStream) r4);
                        return null;
                    } catch (Throwable th4) {
                        th2 = th4;
                        outputStream = r4;
                        bufferedInputStream = bufferedInputStream;
                        outputStream2 = outputStream;
                        IOUtils.closeQuietly(inputStream);
                        IOUtils.closeQuietly((InputStream) bufferedInputStream);
                        IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                        IOUtils.closeQuietly(outputStream2);
                        throw th2;
                    }
                } catch (Throwable th5) {
                    th2 = th5;
                    byteArrayOutputStream = null;
                    outputStream = null;
                    bufferedInputStream = bufferedInputStream;
                    outputStream2 = outputStream;
                    IOUtils.closeQuietly(inputStream);
                    IOUtils.closeQuietly((InputStream) bufferedInputStream);
                    IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                    IOUtils.closeQuietly(outputStream2);
                    throw th2;
                }
            } catch (Exception e4) {
                e = e4;
                bufferedInputStream = null;
                byteArrayOutputStream = null;
                r4 = byteArrayOutputStream;
                HMSLog.m14110i(f11844a, "getManifestBytes Exception!" + e.getMessage());
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly((InputStream) bufferedInputStream);
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly((OutputStream) r4);
                return null;
            } catch (Throwable th6) {
                th = th6;
                th2 = th;
                bufferedInputStream = null;
                byteArrayOutputStream = null;
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly((InputStream) bufferedInputStream);
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly(outputStream2);
                throw th2;
            }
            try {
                for (int read = bufferedInputStream.read(bArr, 0, 4096); read > 0; read = bufferedInputStream.read(bArr, 0, 4096)) {
                    r4.write(bArr, 0, read);
                }
                r4.flush();
                byte[] byteArray = byteArrayOutputStream.toByteArray();
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly((InputStream) bufferedInputStream);
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly((OutputStream) r4);
                return byteArray;
            } catch (Exception e5) {
                e = e5;
                HMSLog.m14110i(f11844a, "getManifestBytes Exception!" + e.getMessage());
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly((InputStream) bufferedInputStream);
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly((OutputStream) r4);
                return null;
            } catch (Throwable th7) {
                th2 = th7;
                outputStream2 = r4;
                IOUtils.closeQuietly(inputStream);
                IOUtils.closeQuietly((InputStream) bufferedInputStream);
                IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
                IOUtils.closeQuietly(outputStream2);
                throw th2;
            }
        } catch (Exception e6) {
            e = e6;
            inputStream = null;
        } catch (Throwable th8) {
            th = th8;
            inputStream = null;
        }
    }

    /* renamed from: b */
    private static boolean m14035b() {
        try {
        } catch (Exception e) {
            String str = f11844a;
            HMSLog.m14110i(str, "verifyMDMSignatureV2 MDM verify Exception!:" + e.getMessage());
        }
        if (m14036a(Base64.decode("MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAx4nUogUyMCmzHhaEb420yvpw9zBs+ETzE9Qm77bGxl1Iml9JEkBkNTsUWOstLgUBajNhV+BAMVBHKMEdzoQbL5kIHkTgUVM65yewd+5+BhrcB9OQ3LHp+0BN6aLKZh71T4WvsvHFhfhQpShuGWkRkSaVGLFTHxX70kpWLzeZ3RtqiEUNIufPR2SFCH6EmecJ+HdkmBOh603IblCpGxwSWse0fDI98wZBEmV88RFaiYEgyiezLlWvXzqIj6I/xuyd5nGAegjH2y3cmoDE6CubecoB1jf4KdgACXgdiQ4Oc63MfLGTor3l6RCqeUk4APAMtyhK83jc72W1sdXMd/sj2wIDAQAB", 0), m14041a(f11850g, "SHA-256"), m14034b(f11847d), "SHA256withRSA")) {
            HMSLog.m14110i(f11844a, "verifyMDMSignatureV2 verify successful!");
            return true;
        }
        HMSLog.m14110i(f11844a, "verifyMDMSignatureV2 verify failure!");
        return false;
    }

    @TargetApi(19)
    /* renamed from: a */
    private static ArrayList<String> m14037a(byte[] bArr) {
        if (bArr == null) {
            HMSLog.m14112e(f11844a, "manifest is null！");
            return null;
        }
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(byteArrayInputStream, StandardCharsets.UTF_8));
            try {
                if (m14043a(bufferedReader, arrayList)) {
                    bufferedReader.close();
                    byteArrayInputStream.close();
                    return arrayList;
                }
                bufferedReader.close();
                byteArrayInputStream.close();
                return null;
            } catch (Throwable th) {
                try {
                    bufferedReader.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException unused) {
            HMSLog.m14112e(f11844a, "getManifestLinesArrary IOException!");
            return null;
        }
    }

    /* renamed from: b */
    private static byte[] m14034b(String str) {
        int i;
        if (TextUtils.isEmpty(str)) {
            return new byte[0];
        }
        int length = str.length();
        if (length % 2 == 0) {
            i = length / 2;
        } else {
            i = (length / 2) + 1;
        }
        byte[] bArr = new byte[i];
        for (int i2 = 0; i2 < length; i2 += 2) {
            int i3 = i2 + 1;
            if (i3 < length) {
                bArr[i2 / 2] = (byte) ((Character.digit(str.charAt(i2), 16) << 4) + Character.digit(str.charAt(i3), 16));
            } else {
                bArr[i2 / 2] = (byte) (Character.digit(str.charAt(i2), 16) << 4);
            }
        }
        return bArr;
    }

    @TargetApi(19)
    /* renamed from: a */
    private static byte[] m14040a(ArrayList<String> arrayList) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(byteArrayOutputStream, StandardCharsets.UTF_8));
        try {
            try {
                Collections.sort(arrayList);
                int size = arrayList.size();
                for (int i = 0; i < size; i++) {
                    String str = arrayList.get(i);
                    bufferedWriter.write(str, 0, str.length());
                    bufferedWriter.write("\r\n", 0, 2);
                }
                bufferedWriter.flush();
            } catch (Exception e) {
                HMSLog.m14110i(f11844a, "getManifestBytesbySorted Exception!" + e.getMessage());
            }
            IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
            IOUtils.closeQuietly((Writer) bufferedWriter);
            return byteArrayOutputStream.toByteArray();
        } catch (Throwable th) {
            IOUtils.closeQuietly((OutputStream) byteArrayOutputStream);
            IOUtils.closeQuietly((Writer) bufferedWriter);
            throw th;
        }
    }

    /* renamed from: a */
    private static boolean m14043a(BufferedReader bufferedReader, ArrayList<String> arrayList) throws IOException {
        String m14044a = m14044a(bufferedReader);
        boolean z = false;
        while (m14044a != null) {
            if (m14044a.equals("Name: META-INF/HUAWEI.CER")) {
                z = true;
                String m14044a2 = m14044a(bufferedReader);
                while (true) {
                    if (m14044a2 == null) {
                        break;
                    } else if (m14044a2.startsWith("Name:")) {
                        m14044a = m14044a2;
                        break;
                    } else {
                        m14044a2 = m14044a(bufferedReader);
                    }
                }
            }
            if (m14044a.length() != 0) {
                arrayList.add(m14044a);
            }
            m14044a = m14044a(bufferedReader);
        }
        return z;
    }

    /* renamed from: a */
    private static String m14044a(BufferedReader bufferedReader) throws IOException {
        int read;
        if (bufferedReader == null || (read = bufferedReader.read()) == -1) {
            return null;
        }
        StringBuilder sb = new StringBuilder(10);
        while (read != -1) {
            char c = (char) read;
            if (c == '\n') {
                break;
            } else if (sb.length() < 4096) {
                sb.append(c);
                read = bufferedReader.read();
            } else {
                throw new IOException("cert line is too long!");
            }
        }
        String sb2 = sb.toString();
        return (sb2.isEmpty() || !sb2.endsWith("\r")) ? sb2 : sb2.substring(0, sb2.length() - 1);
    }

    /* renamed from: a */
    private static boolean m14045a() {
        try {
            if (m14036a(m14034b("30820122300d06092a864886f70d01010105000382010f003082010a0282010100a3d269348ac59923f65e8111c337605e29a1d1bc54fa96c1445050dd14d8d63b10f9f0230bb87ef348183660bedcabfdec045e235ed96935799fcdb4af5c97717ff3b0954eaf1b723225b3a00f81cbd67ce6dc5a4c07f7741ad3bf1913a480c6e267ab1740f409edd2dc33c8b718a8e30e56d9a93f321723c1d0c9ea62115f996812ceef186954595e39a19b74245542c407f7dddb1d12e6eedcfc0bd7cd945ef7255ad0fc9e796258e0fb5e52a23013d15033a32b4071b65f3f924ae5c5761e22327b4d2ae60f4158a5eb15565ba079de29b81540f5fbb3be101a95357f367fc661d797074ff3826950029c52223e4594673a24a334cae62d63b838ba3df9770203010001"), m14041a(f11850g, "SHA-256"), m14034b(f11846c), "SHA256withRSA")) {
                HMSLog.m14110i(f11844a, "verifyMDMSignatureV1 verify successful!");
                return true;
            }
            HMSLog.m14110i(f11844a, "verifyMDMSignatureV1 verify failure!");
            return false;
        } catch (Exception e) {
            String str = f11844a;
            HMSLog.m14110i(str, "verifyMDMSignatureV1 MDM verify Exception!:" + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    private static boolean m14036a(byte[] bArr, byte[] bArr2, byte[] bArr3, String str) throws Exception {
        Signature signature = Signature.getInstance(str);
        signature.initVerify(KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(bArr)));
        signature.update(bArr2);
        return signature.verify(bArr3);
    }

    @TargetApi(19)
    /* renamed from: a */
    private static byte[] m14041a(String str, String str2) throws Exception {
        MessageDigest messageDigest = MessageDigest.getInstance(str2);
        messageDigest.update(str.getBytes(StandardCharsets.UTF_8.name()));
        return messageDigest.digest();
    }

    /* renamed from: a */
    private static String m14042a(String str) {
        return str != null ? f11845b.matcher(str).replaceAll("") : "";
    }
}
