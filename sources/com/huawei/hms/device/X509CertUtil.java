package com.huawei.hms.device;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import com.huawei.hms.support.log.common.Base64;
import com.huawei.hms.utils.HMSPackageManager;
import com.huawei.hms.utils.IOUtils;
import com.huawei.secure.android.common.ssl.util.C5121h;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import org.json.JSONArray;
import org.json.JSONException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.device.a */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class X509CertUtil {
    /* renamed from: a */
    public static boolean m15070a(X509Certificate x509Certificate, List<X509Certificate> list) {
        if (list == null || list.size() == 0) {
            return false;
        }
        if (x509Certificate == null) {
            HMSLog.m14112e("X509CertUtil", "rootCert is null,verify failed ");
            return false;
        }
        PublicKey publicKey = x509Certificate.getPublicKey();
        for (X509Certificate x509Certificate2 : list) {
            if (x509Certificate2 != null) {
                try {
                    x509Certificate2.checkValidity();
                    x509Certificate2.verify(publicKey);
                    publicKey = x509Certificate2.getPublicKey();
                } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchProviderException | SignatureException | CertificateException e) {
                    HMSLog.m14112e("X509CertUtil", "verify failed " + e.getMessage());
                }
            }
            return false;
        }
        return m15068a(list);
    }

    /* renamed from: b */
    public static List<X509Certificate> m15063b(List<String> list) {
        ArrayList arrayList = new ArrayList(list.size());
        for (String str : list) {
            arrayList.add(m15075a(str));
        }
        return arrayList;
    }

    /* renamed from: c */
    private static List<String> m15062c(String str) {
        try {
            JSONArray jSONArray = new JSONArray(str);
            if (jSONArray.length() <= 1) {
                return Collections.emptyList();
            }
            ArrayList arrayList = new ArrayList(jSONArray.length());
            for (int i = 0; i < jSONArray.length(); i++) {
                arrayList.add(jSONArray.getString(i));
            }
            return arrayList;
        } catch (JSONException e) {
            HMSLog.m14112e("X509CertUtil", "Failed to getCertChain: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    /* renamed from: b */
    public static List<X509Certificate> m15066b(String str) {
        return m15063b(m15062c(str));
    }

    /* renamed from: b */
    public static boolean m15064b(X509Certificate x509Certificate, String str, String str2) {
        if (x509Certificate == null || TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return false;
        }
        return str2.equals(m15074a(x509Certificate.getSubjectDN().getName(), str));
    }

    /* renamed from: b */
    public static boolean m15065b(X509Certificate x509Certificate, String str) {
        return m15064b(x509Certificate, "OU", str);
    }

    /* renamed from: a */
    public static X509Certificate m15075a(String str) {
        if (TextUtils.isEmpty(str)) {
            return null;
        }
        try {
            return m15067a(Base64.decode(str));
        } catch (IllegalArgumentException e) {
            HMSLog.m14112e("X509CertUtil", "getCert failed : " + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    public static X509Certificate m15067a(byte[] bArr) {
        try {
            return (X509Certificate) CertificateFactory.getInstance("X.509").generateCertificate(new ByteArrayInputStream(bArr));
        } catch (CertificateException e) {
            HMSLog.m14112e("X509CertUtil", "Failed to get cert: " + e.getMessage());
            return null;
        }
    }

    /* renamed from: a */
    private static String m15074a(String str, String str2) {
        String upperCase = str.toUpperCase(Locale.getDefault());
        int indexOf = upperCase.indexOf(str2 + "=");
        if (indexOf == -1) {
            return null;
        }
        int indexOf2 = str.indexOf(",", indexOf);
        if (indexOf2 != -1) {
            return str.substring(indexOf + str2.length() + 1, indexOf2);
        }
        return str.substring(indexOf + str2.length() + 1);
    }

    /* renamed from: a */
    public static boolean m15073a(X509Certificate x509Certificate) {
        if (x509Certificate == null || x509Certificate.getBasicConstraints() == -1) {
            return false;
        }
        boolean[] keyUsage = x509Certificate.getKeyUsage();
        if (5 < keyUsage.length) {
            return keyUsage[5];
        }
        return false;
    }

    /* renamed from: a */
    public static boolean m15068a(List<X509Certificate> list) {
        for (int i = 0; i < list.size() - 1; i++) {
            if (!m15073a(list.get(i))) {
                return false;
            }
        }
        return true;
    }

    /* renamed from: a */
    public static boolean m15072a(X509Certificate x509Certificate, String str) {
        return m15064b(x509Certificate, "CN", str);
    }

    /* renamed from: a */
    public static boolean m15071a(X509Certificate x509Certificate, String str, String str2) {
        try {
            return m15069a(x509Certificate, str.getBytes("UTF-8"), Base64.decode(str2));
        } catch (UnsupportedEncodingException | IllegalArgumentException e) {
            HMSLog.m14112e("X509CertUtil", " plainText exception: " + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static boolean m15069a(X509Certificate x509Certificate, byte[] bArr, byte[] bArr2) {
        try {
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            signature.initVerify(x509Certificate.getPublicKey());
            signature.update(bArr);
            return signature.verify(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException | SignatureException e) {
            HMSLog.m14112e("X509CertUtil", "failed checkSignature : " + e.getMessage());
            return false;
        }
    }

    /* renamed from: a */
    public static X509Certificate m15076a(Context context, String str) {
        InputStream inputStream;
        KeyStore keyStore;
        if (context != null) {
            try {
                if (!TextUtils.isEmpty(str)) {
                    try {
                        keyStore = KeyStore.getInstance(C5121h.f12122e);
                        inputStream = context.getAssets().open("hmsrootcas.bks");
                    } catch (IOException e) {
                        e = e;
                        inputStream = null;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (KeyStoreException e2) {
                        e = e2;
                        inputStream = null;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (NoSuchAlgorithmException e3) {
                        e = e3;
                        inputStream = null;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (CertificateException e4) {
                        e = e4;
                        inputStream = null;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (Throwable th) {
                        th = th;
                        IOUtils.closeQuietly((InputStream) null);
                        throw th;
                    }
                    try {
                        keyStore.load(inputStream, "".toCharArray());
                    } catch (IOException e5) {
                        e = e5;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (KeyStoreException e6) {
                        e = e6;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (NoSuchAlgorithmException e7) {
                        e = e7;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    } catch (CertificateException e8) {
                        e = e8;
                        HMSLog.m14112e("X509CertUtil", "exception:" + e.getMessage());
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    }
                    if (!keyStore.containsAlias(str)) {
                        HMSLog.m14112e("X509CertUtil", "Not include alias " + str);
                        HMSPackageManager.getInstance(context).setUseOldCertificate(true);
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    }
                    Certificate certificate = keyStore.getCertificate(str);
                    if (!(certificate instanceof X509Certificate)) {
                        IOUtils.closeQuietly(inputStream);
                        return null;
                    }
                    X509Certificate x509Certificate = (X509Certificate) certificate;
                    x509Certificate.checkValidity();
                    IOUtils.closeQuietly(inputStream);
                    return x509Certificate;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        }
        HMSLog.m14112e("X509CertUtil", "args are error");
        return null;
    }

    /* renamed from: a */
    public static X509Certificate m15077a(Context context) {
        return m15076a(context, C5121h.f12123f);
    }
}
