package com.baidu.p120ar.auth;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.baidu.p120ar.utils.IoUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.X509EncodedKeySpec;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import javax.crypto.Cipher;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.auth.AuthLicense */
/* loaded from: E:\10201592_dexfile_execute.dex */
class AuthLicense {
    private static final String LICENSE_FILE = "dumixar.license";
    private static final int MAX_DECRYPT_BLOCK = 128;
    private static final String PUB_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDn6dzi813ZXXfMIeXrxJxtVekfpxksX9N5XPh9g4D94cOvZnYL93PngexbPfGW9T7DhGnPdgRxR6Ux1pGRdTfrL9yK8nR7uCa5Va9IXbNd4T5QPpbmJ5hvmk7qg8GY8BxcC/0M+a5ylVP8bUDq50Y9Si+7g844wOCbrOkzSe920wIDAQAB";

    AuthLicense() {
    }

    public static AuthSetting loadLicense(Context context) throws Exception {
        AuthSetting authSetting = new AuthSetting();
        byte[] readLicense = readLicense(context);
        if (readLicense != null) {
            readLicense(readLicense, authSetting);
        } else {
            authSetting.authType = 1;
            authSetting.ignoreNetError = false;
            authSetting.features = FeatureCodes.getAll();
        }
        return authSetting;
    }

    public static void readLicense(byte[] bArr, AuthSetting authSetting) throws Exception {
        JSONObject jSONObject = new JSONObject(new String(decrypt("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDn6dzi813ZXXfMIeXrxJxtVekfpxksX9N5XPh9g4D94cOvZnYL93PngexbPfGW9T7DhGnPdgRxR6Ux1pGRdTfrL9yK8nR7uCa5Va9IXbNd4T5QPpbmJ5hvmk7qg8GY8BxcC/0M+a5ylVP8bUDq50Y9Si+7g844wOCbrOkzSe920wIDAQAB".getBytes(), bArr)));
        authSetting.authType = jSONObject.getInt("authType");
        authSetting.ignoreNetError = jSONObject.getInt("ignoreNetError") == 1;
        authSetting.noAuthTip = jSONObject.getInt("noAuthTip") == 1;
        authSetting.respectAppId = jSONObject.optString("appId");
        authSetting.respectApiKey = jSONObject.optString("apiKey");
        authSetting.grantedAll = jSONObject.optInt("grantedAll", 0) == 1;
        if (jSONObject.has("expiredTime")) {
            authSetting.expiredTimestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(jSONObject.getString("expiredTime")).getTime();
        } else {
            authSetting.expiredTimestamp = 0L;
        }
        JSONArray optJSONArray = jSONObject.optJSONArray("pkgs");
        if (optJSONArray != null) {
            int length = optJSONArray.length();
            for (int i = 0; i < length; i++) {
                authSetting.respectPackageMd5s.add(optJSONArray.getString(i));
            }
        }
        String optString = jSONObject.optString("pkg");
        if (!TextUtils.isEmpty(optString) && !authSetting.respectPackageMd5s.contains(optString)) {
            authSetting.respectPackageMd5s.add(optString);
        }
        if (authSetting.grantedAll) {
            authSetting.features = FeatureCodes.getAll();
            return;
        }
        JSONArray jSONArray = jSONObject.getJSONArray("features");
        ArrayList arrayList = new ArrayList();
        int length2 = jSONArray.length();
        for (int i2 = 0; i2 < length2; i2++) {
            arrayList.add(Integer.valueOf(jSONArray.getInt(i2)));
        }
        authSetting.features = arrayList;
    }

    private static byte[] readLicense(Context context) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        InputStream inputStream;
        try {
            inputStream = context.getAssets().open("dumixar.license");
            try {
                byteArrayOutputStream = new ByteArrayOutputStream();
            } catch (IOException unused) {
                byteArrayOutputStream = null;
            } catch (Throwable th2) {
                byteArrayOutputStream = null;
                th = th2;
            }
            try {
                byte[] bArr = new byte[512];
                while (true) {
                    int read = inputStream.read(bArr);
                    if (read != -1) {
                        byteArrayOutputStream.write(bArr, 0, read);
                    } else {
                        byte[] byteArray = byteArrayOutputStream.toByteArray();
                        IoUtils.closeQuietly(byteArrayOutputStream);
                        IoUtils.closeQuietly(inputStream);
                        return byteArray;
                    }
                }
            } catch (IOException unused2) {
                IoUtils.closeQuietly(byteArrayOutputStream);
                IoUtils.closeQuietly(inputStream);
                return null;
            } catch (Throwable th3) {
                th = th3;
                IoUtils.closeQuietly(byteArrayOutputStream);
                IoUtils.closeQuietly(inputStream);
                throw th;
            }
        } catch (IOException unused3) {
            inputStream = null;
            byteArrayOutputStream = null;
        } catch (Throwable th4) {
            byteArrayOutputStream = null;
            th = th4;
            inputStream = null;
        }
    }

    private static byte[] decrypt(byte[] bArr, byte[] bArr2) throws Exception {
        int i = 0;
        PublicKey generatePublic = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.decode(bArr, 0)));
        Cipher cipher = Cipher.getInstance("RSA/None/PKCS1Padding");
        cipher.init(2, generatePublic);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int length = bArr2.length;
        while (length > i) {
            int i2 = length - i;
            if (i2 > 128) {
                i2 = 128;
            }
            byte[] doFinal = cipher.doFinal(bArr2, i, i2);
            i += i2;
            byteArrayOutputStream.write(doFinal);
        }
        return byteArrayOutputStream.toByteArray();
    }
}
