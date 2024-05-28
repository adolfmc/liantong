package com.huawei.hms.opendevice;

import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.huawei.android.hms.openid.C4799R;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.utils.IOUtils;
import com.huawei.secure.android.common.encrypt.utils.BaseKeyUtil;
import com.huawei.secure.android.common.encrypt.utils.EncryptUtil;
import com.huawei.secure.android.common.encrypt.utils.RootKeyUtil;
import com.huawei.secure.android.common.encrypt.utils.WorkKeyCryptUtil;
import com.huawei.secure.android.common.util.IOUtil;
import com.sdk.p285a.C6960d;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.n */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class SecretUtil {

    /* renamed from: a */
    private static final String f11557a = "n";

    /* renamed from: b */
    private static Map<String, String> f11558b = new HashMap();

    /* renamed from: c */
    private static final Object f11559c = new Object();

    /* renamed from: a */
    private static String m14356a() {
        return "2A57086C86EF54970C1E6EB37BFC72B1";
    }

    /* renamed from: a */
    private static byte[] m14351a(String str, String str2, String str3, String str4) {
        if (Build.VERSION.SDK_INT >= 26) {
            return BaseKeyUtil.exportRootKey(str, str2, str3, str4, 32, true);
        }
        return BaseKeyUtil.exportRootKey(str, str2, str3, str4, 32, false);
    }

    /* renamed from: b */
    private static byte[] m14347b() {
        return m14351a(m14343d(), m14341e(), m14345c(), m14338g());
    }

    /* renamed from: c */
    public static void m14344c(Context context) {
        synchronized (f11559c) {
            m14342d(context.getApplicationContext());
            if (m14336i()) {
                HMSLog.m14110i(f11557a, "The local secret is already in separate file mode.");
                return;
            }
            File file = new File(CommFun.m14384c(context.getApplicationContext()) + "/shared_prefs/LocalAvengers.xml");
            if (file.exists()) {
                IOUtil.deleteSecure(file);
                HMSLog.m14110i(f11557a, "destroy C, delete file LocalAvengers.xml.");
            }
            byte[] generateSecureRandom = EncryptUtil.generateSecureRandom(32);
            byte[] generateSecureRandom2 = EncryptUtil.generateSecureRandom(32);
            byte[] generateSecureRandom3 = EncryptUtil.generateSecureRandom(32);
            byte[] generateSecureRandom4 = EncryptUtil.generateSecureRandom(32);
            String m14389a = BaseUtil.m14389a(generateSecureRandom);
            String m14389a2 = BaseUtil.m14389a(generateSecureRandom2);
            String m14389a3 = BaseUtil.m14389a(generateSecureRandom3);
            String m14389a4 = BaseUtil.m14389a(generateSecureRandom4);
            m14350a(m14389a, m14389a2, m14389a3, m14389a4, WorkKeyCryptUtil.encryptWorkKey(BaseUtil.m14389a(EncryptUtil.generateSecureRandom(32)), m14351a(m14389a, m14389a2, m14389a3, m14389a4)), context);
            HMSLog.m14110i(f11557a, "generate D.");
        }
    }

    /* renamed from: d */
    private static void m14342d(Context context) {
        if (m14336i()) {
            HMSLog.m14110i(f11557a, "secretKeyCache not empty.");
            return;
        }
        f11558b.clear();
        String m14384c = CommFun.m14384c(context);
        if (TextUtils.isEmpty(m14384c)) {
            return;
        }
        String m14332a = StreamUtil.m14332a(m14384c + "/files/math/m");
        String m14332a2 = StreamUtil.m14332a(m14384c + "/files/panda/p");
        String m14332a3 = StreamUtil.m14332a(m14384c + "/files/panda/d");
        String m14332a4 = StreamUtil.m14332a(m14384c + "/files/math/t");
        String m14332a5 = StreamUtil.m14332a(m14384c + "/files/s");
        if (StringUtils.m14330a(m14332a, m14332a2, m14332a3, m14332a4, m14332a5)) {
            f11558b.put("m", m14332a);
            f11558b.put("p", m14332a2);
            f11558b.put(C6960d.f18019d, m14332a3);
            f11558b.put("t", m14332a4);
            f11558b.put("s", m14332a5);
        }
    }

    /* renamed from: e */
    private static synchronized String m14340e(Context context) {
        synchronized (SecretUtil.class) {
            String decryptWorkKey = WorkKeyCryptUtil.decryptWorkKey(m14339f(), m14347b());
            if (StringUtils.m14330a(decryptWorkKey)) {
                HMSLog.m14110i(f11557a, "keyS has been upgraded, no require operate again.");
                return decryptWorkKey;
            }
            String decryptWorkKey2 = WorkKeyCryptUtil.decryptWorkKey(m14339f(), m14337h());
            if (StringUtils.m14330a(decryptWorkKey2)) {
                HMSLog.m14110i(f11557a, "keyS is encrypt by RootKeyUtil, upgrade encrypt mode.");
                m14353a(WorkKeyCryptUtil.encryptWorkKey(decryptWorkKey2, m14347b()), context);
                return decryptWorkKey2;
            }
            String decryptWorkKey3 = WorkKeyCryptUtil.decryptWorkKey(m14339f(), BaseKeyUtil.exportRootKey(m14343d(), m14341e(), m14345c(), m14338g(), 32, false));
            if (StringUtils.m14330a(decryptWorkKey3)) {
                HMSLog.m14110i(f11557a, "keyS is encrypt by ExportRootKey with sha1, upgrade encrypt mode to sha256.");
                m14353a(WorkKeyCryptUtil.encryptWorkKey(decryptWorkKey3, m14347b()), context);
                return decryptWorkKey3;
            }
            HMSLog.m14112e(f11557a, "all mode unable to decrypt root key.");
            return "";
        }
    }

    /* renamed from: f */
    private static String m14339f() {
        return m14354a("s");
    }

    /* renamed from: g */
    private static String m14338g() {
        return m14354a("t");
    }

    /* renamed from: h */
    private static RootKeyUtil m14337h() {
        return RootKeyUtil.newInstance(m14343d(), m14341e(), m14345c(), m14338g());
    }

    /* renamed from: i */
    private static boolean m14336i() {
        return !TextUtils.isEmpty(m14339f());
    }

    /* renamed from: b */
    public static String m14346b(Context context) {
        if (!m14336i()) {
            HMSLog.m14110i(f11557a, "work key is empty, execute init.");
            m14344c(context);
        }
        String decryptWorkKey = WorkKeyCryptUtil.decryptWorkKey(m14339f(), m14347b());
        return StringUtils.m14330a(decryptWorkKey) ? decryptWorkKey : m14340e(context);
    }

    /* renamed from: a */
    public static byte[] m14355a(Context context) {
        byte[] m14390a = BaseUtil.m14390a(context.getString(C4799R.string.push_cat_head));
        byte[] m14390a2 = BaseUtil.m14390a(context.getString(C4799R.string.push_cat_body));
        return m14349a(m14348a(m14348a(m14390a, m14390a2), BaseUtil.m14390a(m14356a())));
    }

    /* renamed from: a */
    private static byte[] m14348a(byte[] bArr, byte[] bArr2) {
        if (bArr != null && bArr2 != null && bArr.length != 0 && bArr2.length != 0) {
            int length = bArr.length;
            if (length != bArr2.length) {
                return new byte[0];
            }
            byte[] bArr3 = new byte[length];
            for (int i = 0; i < length; i++) {
                bArr3[i] = (byte) (bArr[i] ^ bArr2[i]);
            }
            return bArr3;
        }
        return new byte[0];
    }

    /* renamed from: d */
    private static String m14343d() {
        return m14354a("m");
    }

    /* renamed from: a */
    private static byte[] m14349a(byte[] bArr) {
        if (bArr != null && bArr.length != 0) {
            for (int i = 0; i < bArr.length; i++) {
                bArr[i] = (byte) (bArr[i] >> 2);
            }
            return bArr;
        }
        return new byte[0];
    }

    /* renamed from: e */
    private static String m14341e() {
        return m14354a("p");
    }

    /* renamed from: c */
    private static String m14345c() {
        return m14354a(C6960d.f18019d);
    }

    /* renamed from: a */
    private static void m14350a(String str, String str2, String str3, String str4, String str5, Context context) {
        String m14384c = CommFun.m14384c(context.getApplicationContext());
        if (TextUtils.isEmpty(m14384c)) {
            return;
        }
        try {
            m14352a("m", str, m14384c + "/files/math/m");
            m14352a("p", str2, m14384c + "/files/panda/p");
            m14352a(C6960d.f18019d, str3, m14384c + "/files/panda/d");
            m14352a("t", str4, m14384c + "/files/math/t");
            m14352a("s", str5, m14384c + "/files/s");
        } catch (IOException unused) {
            HMSLog.m14112e(f11557a, "save key IOException.");
        }
    }

    /* renamed from: a */
    private static void m14353a(String str, Context context) {
        String m14384c = CommFun.m14384c(context.getApplicationContext());
        if (TextUtils.isEmpty(m14384c)) {
            return;
        }
        try {
            m14352a("s", str, m14384c + "/files/s");
        } catch (IOException unused) {
            HMSLog.m14112e(f11557a, "save keyS IOException.");
        }
    }

    /* renamed from: a */
    private static void m14352a(String str, String str2, String str3) throws IOException {
        OutputStreamWriter outputStreamWriter;
        BufferedWriter bufferedWriter;
        HMSLog.m14110i(f11557a, "save local secret key.");
        BufferedWriter bufferedWriter2 = null;
        try {
            File file = new File(str3);
            StreamUtil.m14334a(file);
            outputStreamWriter = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            try {
                bufferedWriter = new BufferedWriter(outputStreamWriter);
            } catch (Throwable th) {
                th = th;
            }
        } catch (Throwable th2) {
            th = th2;
            outputStreamWriter = null;
        }
        try {
            bufferedWriter.write(str2);
            bufferedWriter.flush();
            f11558b.put(str, str2);
            IOUtils.closeQuietly((Writer) outputStreamWriter);
            IOUtils.closeQuietly((Writer) bufferedWriter);
        } catch (Throwable th3) {
            th = th3;
            bufferedWriter2 = bufferedWriter;
            IOUtils.closeQuietly((Writer) outputStreamWriter);
            IOUtils.closeQuietly((Writer) bufferedWriter2);
            throw th;
        }
    }

    /* renamed from: a */
    private static String m14354a(String str) {
        String str2 = f11558b.get(str);
        return TextUtils.isEmpty(str2) ? "" : str2;
    }
}
