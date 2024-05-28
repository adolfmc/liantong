package com.huawei.hms.hatool;

import com.huawei.secure.android.common.encrypt.hash.AbstractC5104PBKDF2;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.HexUtil;
import java.io.File;
import java.io.IOException;

/* renamed from: com.huawei.hms.hatool.x */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5034x {

    /* renamed from: a */
    private String f11524a = AbstractC5020q0.m14526i().getFilesDir().getPath();

    /* renamed from: a */
    private String m14438a(String str) {
        return this.f11524a + "/hms/component/".replace("component", str);
    }

    /* renamed from: a */
    private void m14437a(String str, String str2) {
        File file = new File(m14438a(str));
        String m14438a = m14438a(str);
        File file2 = new File(m14438a, "hianalytics_" + str);
        if (!file.exists() && file.mkdirs()) {
            C5029v.m14455c("hmsSdk", "file directory is mkdirs");
        }
        if (m14439a(file2)) {
            C5000k1.m14635a(file2, str2);
        } else {
            C5029v.m14451f("hmsSdk", "refreshComponent():file is not found,and file is create failed");
        }
    }

    /* renamed from: a */
    private boolean m14439a(File file) {
        if (file.exists()) {
            return true;
        }
        try {
            return file.createNewFile();
        } catch (IOException unused) {
            C5029v.m14451f("hmsSdk", "create new file error!");
            return false;
        }
    }

    /* renamed from: a */
    private char[] m14436a(String str, String str2, String str3, String str4) {
        byte[] hexStr2ByteArray = HexUtil.hexStr2ByteArray(str);
        byte[] hexStr2ByteArray2 = HexUtil.hexStr2ByteArray(str2);
        byte[] hexStr2ByteArray3 = HexUtil.hexStr2ByteArray(str3);
        byte[] hexStr2ByteArray4 = HexUtil.hexStr2ByteArray(str4);
        int length = hexStr2ByteArray.length;
        if (length > hexStr2ByteArray2.length) {
            length = hexStr2ByteArray2.length;
        }
        if (length > hexStr2ByteArray3.length) {
            length = hexStr2ByteArray3.length;
        }
        if (length > hexStr2ByteArray4.length) {
            length = hexStr2ByteArray4.length;
        }
        char[] cArr = new char[length];
        for (int i = 0; i < length; i++) {
            cArr[i] = (char) (((hexStr2ByteArray[i] ^ hexStr2ByteArray2[i]) ^ hexStr2ByteArray3[i]) ^ hexStr2ByteArray4[i]);
        }
        return cArr;
    }

    /* renamed from: b */
    private String m14433b(String str) {
        String m14438a = m14438a(str);
        File file = new File(m14438a, "hianalytics_" + str);
        if (m14439a(file)) {
            return C5000k1.m14636a(file);
        }
        String generateSecureRandomStr = EncryptUtil.generateSecureRandomStr(128);
        C5000k1.m14635a(file, generateSecureRandomStr);
        return generateSecureRandomStr;
    }

    /* renamed from: b */
    private boolean m14435b() {
        long m14768a = C4975d.m14768a(AbstractC5020q0.m14526i(), "Privacy_MY", "assemblyFlash", -1L);
        if (-1 != m14768a) {
            return System.currentTimeMillis() - m14768a > 31536000000L;
        }
        C5029v.m14455c("hmsSdk", "First init components");
        return true;
    }

    /* renamed from: b */
    private static boolean m14434b(File file) {
        File[] listFiles;
        if (file == null || !file.exists() || !file.isDirectory() || (listFiles = file.listFiles()) == null || listFiles.length == 0) {
            return false;
        }
        for (File file2 : listFiles) {
            if (file2.isFile()) {
                if (!file2.delete()) {
                    C5029v.m14455c("hmsSdk", "delete file failed : " + file2.getName());
                }
            } else if (file2.isDirectory()) {
                m14434b(file2);
            }
        }
        return file.delete();
    }

    /* renamed from: c */
    public static boolean m14432c() {
        return m14434b(new File(AbstractC5020q0.m14526i().getFilesDir().getPath() + "/hms"));
    }

    /* renamed from: d */
    private String m14431d() {
        return "f6040d0e807aaec325ecf44823765544e92905158169f694b282bf17388632cf95a83bae7d2d235c1f039b0df1dcca5fda619b6f7f459f2ff8d70ddb7b601592fe29fcae58c028f319b3b12495e67aa5390942a997a8cb572c8030b2df5c2b622608bea02b0c3e5d4dff3f72c9e3204049a45c0760cd3604af8d57f0e0c693cc";
    }

    /* renamed from: a */
    public String m14440a() {
        String m14433b;
        String m14433b2;
        String m14433b3;
        String m14433b4;
        String m14431d = m14431d();
        if (m14435b()) {
            C5029v.m14455c("hmsSdk", "refresh components");
            m14433b = EncryptUtil.generateSecureRandomStr(128);
            m14437a("aprpap", m14433b);
            m14433b2 = EncryptUtil.generateSecureRandomStr(128);
            m14437a("febdoc", m14433b2);
            m14433b3 = EncryptUtil.generateSecureRandomStr(128);
            m14437a("marfil", m14433b3);
            m14433b4 = EncryptUtil.generateSecureRandomStr(128);
            m14437a("maywnj", m14433b4);
            C4975d.m14764b(AbstractC5020q0.m14526i(), "Privacy_MY", "assemblyFlash", System.currentTimeMillis());
        } else {
            m14433b = m14433b("aprpap");
            m14433b2 = m14433b("febdoc");
            m14433b3 = m14433b("marfil");
            m14433b4 = m14433b("maywnj");
        }
        return HexUtil.byteArray2HexStr(AbstractC5104PBKDF2.pbkdf2(m14436a(m14433b, m14433b2, m14433b3, m14431d), HexUtil.hexStr2ByteArray(m14433b4), 10000, 16));
    }
}
