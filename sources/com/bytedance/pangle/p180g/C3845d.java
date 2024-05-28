package com.bytedance.pangle.p180g;

import android.content.pm.Signature;
import android.os.Build;
import android.support.annotation.RequiresApi;
import com.bytedance.pangle.p172b.p174b.C3779a;
import com.bytedance.pangle.p180g.C3841c;
import java.io.RandomAccessFile;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.security.cert.Certificate;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(api = 21)
/* renamed from: com.bytedance.pangle.g.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3845d {
    /* renamed from: a */
    public static C3860o m16841a(String str) {
        RandomAccessFile randomAccessFile;
        int[] iArr;
        Signature[] signatureArr = null;
        try {
            try {
                randomAccessFile = new RandomAccessFile(str, "r");
                try {
                    try {
                        C3847f.m16835a(str, randomAccessFile, -262969152, 1896449818);
                        try {
                            try {
                                try {
                                    C3858m c3858m = C3847f.f9185a.get(str).get(-262969152);
                                    if (c3858m != null) {
                                        C3841c.C3844c m16845a = C3841c.m16845a(randomAccessFile, c3858m);
                                        Signature[] m16840a = m16840a(new Certificate[][]{m16845a.f9182a});
                                        if (m16845a.f9183b != null) {
                                            signatureArr = new Signature[m16845a.f9183b.f9180a.size()];
                                            iArr = new int[m16845a.f9183b.f9181b.size()];
                                            for (int i = 0; i < signatureArr.length; i++) {
                                                signatureArr[i] = new Signature(m16845a.f9183b.f9180a.get(i).getEncoded());
                                                iArr[i] = m16845a.f9183b.f9181b.get(i).intValue();
                                            }
                                        } else {
                                            iArr = null;
                                        }
                                        C3860o c3860o = new C3860o(m16840a, 3, signatureArr, iArr);
                                        try {
                                            randomAccessFile.close();
                                        } catch (Exception unused) {
                                        }
                                        return c3860o;
                                    }
                                    throw new C3859n("findVerifiedSigner, No APK Signature Scheme v3 signature in package");
                                } catch (C3859n unused2) {
                                    C3858m c3858m2 = C3847f.f9185a.get(str).get(1896449818);
                                    if (c3858m2 != null) {
                                        C3860o c3860o2 = new C3860o(m16840a(C3839b.m16848a(randomAccessFile, c3858m2).f9178a));
                                        try {
                                            randomAccessFile.close();
                                        } catch (Exception unused3) {
                                        }
                                        return c3860o2;
                                    }
                                    throw new C3859n("findVerifiedSigner, No APK Signature Scheme v2 signature in package");
                                } catch (Exception e) {
                                    throw new C3862q(4, "Failed to collect certificates from " + str + " using APK Signature Scheme v3", e);
                                }
                            } catch (Exception e2) {
                                throw new C3862q(4, "Failed to collect certificates from " + str + " using APK Signature Scheme v2", e2);
                            }
                        } catch (C3859n unused4) {
                            C3860o m16850a = C3838a.m16850a(str);
                            try {
                                randomAccessFile.close();
                            } catch (Exception unused5) {
                            }
                            return m16850a;
                        }
                    } catch (Exception e3) {
                        throw new C3862q(4, "Failed to collect certificates from " + str + " when findSignatureInfo at once", e3);
                    }
                } catch (Throwable th) {
                    th = th;
                    if (randomAccessFile != null) {
                        try {
                            randomAccessFile.close();
                        } catch (Exception unused6) {
                        }
                    }
                    throw th;
                }
            } catch (Exception unused7) {
                throw new C3862q(6, "failed to read apk file, minSignatureSchemeVersion : 1, apkPath : ".concat(String.valueOf(str)));
            }
        } catch (Throwable th2) {
            th = th2;
            randomAccessFile = null;
        }
    }

    /* renamed from: a */
    public static Signature[] m16840a(Certificate[][] certificateArr) {
        Signature[] signatureArr = new Signature[certificateArr.length];
        for (int i = 0; i < certificateArr.length; i++) {
            if (Build.VERSION.SDK_INT >= 21 && Build.VERSION.SDK_INT <= 28) {
                Constructor m16964a = C3779a.m16964a(Signature.class, Certificate[].class);
                if (m16964a != null) {
                    m16964a.setAccessible(true);
                }
                if (m16964a != null && m16964a.isAccessible()) {
                    try {
                        signatureArr[i] = (Signature) m16964a.newInstance(certificateArr[i]);
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InstantiationException e2) {
                        e2.printStackTrace();
                    } catch (InvocationTargetException e3) {
                        e3.printStackTrace();
                    }
                }
            } else {
                signatureArr[i] = new Signature(certificateArr[i][0].getEncoded());
            }
        }
        return signatureArr;
    }
}
