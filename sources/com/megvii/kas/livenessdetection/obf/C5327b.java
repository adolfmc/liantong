package com.megvii.kas.livenessdetection.obf;

import android.graphics.Rect;
import android.os.Build;
import com.megvii.kas.livenessdetection.bean.FaceInfo;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.megvii.kas.livenessdetection.obf.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5327b {

    /* renamed from: a */
    private float[] f12342a = new float[5];

    /* renamed from: b */
    private int f12343b = 0;

    /* renamed from: c */
    private boolean f12344c = false;

    /* renamed from: a */
    public static String m13641a(byte[] bArr) {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA");
            messageDigest.update(bArr);
            byte[] digest = messageDigest.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : digest) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 2) {
                    sb.append(hexString);
                } else {
                    sb.append("0");
                    sb.append(hexString);
                }
            }
            return sb.toString().toUpperCase();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    /* renamed from: a */
    public static JSONObject m13647a() {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("version", Build.VERSION.SDK_INT);
            jSONObject.put("release", Build.VERSION.RELEASE);
            jSONObject.put("model", Build.MODEL);
            jSONObject.put("brand", Build.BRAND);
            jSONObject.put("manufacturer", Build.MANUFACTURER);
            jSONObject.put("arch", Build.CPU_ABI);
            jSONObject.put("fingerprint", Build.FINGERPRINT);
            return jSONObject;
        } catch (JSONException unused) {
            return null;
        }
    }

    /* renamed from: a */
    private static void m13644a(InputStream inputStream) {
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException unused) {
            }
        }
    }

    /* renamed from: a */
    public static byte[] m13643a(String str) {
        FileInputStream fileInputStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byte[] bArr = new byte[1024];
        try {
            fileInputStream = new FileInputStream(str);
            while (true) {
                try {
                    int read = fileInputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byteArrayOutputStream.close();
                        m13644a(fileInputStream);
                        return byteArrayOutputStream.toByteArray();
                    }
                } catch (IOException unused) {
                    m13644a(fileInputStream);
                    return null;
                } catch (Throwable th) {
                    th = th;
                    m13644a(fileInputStream);
                    throw th;
                }
            }
        } catch (IOException unused2) {
            fileInputStream = null;
        } catch (Throwable th2) {
            th = th2;
            fileInputStream = null;
        }
    }

    /* renamed from: b */
    public static boolean m13640b(String str) {
        if (str == null) {
            return false;
        }
        try {
            System.load(str);
            return true;
        } catch (UnsatisfiedLinkError unused) {
            C5330d.m13628b("load dynamic library failed, please check library path");
            return false;
        }
    }

    /* renamed from: a */
    public static String m13646a(Rect rect) {
        return "242 " + rect.left + "," + rect.top + "," + rect.right + "," + rect.bottom;
    }

    /* renamed from: a */
    public synchronized void m13642a(boolean z) {
        this.f12344c = true;
        for (int i = 0; i < 5; i++) {
            this.f12342a[i] = 0.0f;
        }
        this.f12343b = 0;
    }

    /* renamed from: a */
    public void m13645a(FaceInfo faceInfo) {
        float[] fArr;
        if (faceInfo == null) {
            if (this.f12344c) {
                float[] fArr2 = this.f12342a;
                int i = this.f12343b;
                this.f12343b = i + 1;
                fArr2[i % 5] = 0.0f;
            }
        } else if (this.f12344c) {
            float[] fArr3 = this.f12342a;
            int i2 = this.f12343b;
            this.f12343b = i2 + 1;
            fArr3[i2 % 5] = faceInfo.faceQuality;
            float f = 100.0f;
            for (float f2 : this.f12342a) {
                if (f2 < f) {
                    f = f2;
                }
            }
            faceInfo.smoothQuality = f;
        } else {
            faceInfo.smoothQuality = faceInfo.faceQuality;
        }
    }
}
