package com.p201hb.oma.encryp;

import com.sinovatech.unicom.separatemodule.simbox.encryp.ResultExt;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: AESUtils.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0010\u001a\u00020\u0007J\u0018\u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\u000f\u001a\u00020\u00072\u0006\u0010\u0012\u001a\u00020\u0007J\u001a\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00072\n\u0010\u0016\u001a\u00060\u0017j\u0002`\u0018J\u000e\u0010\u0019\u001a\u00020\u00142\u0006\u0010\u001a\u001a\u00020\rR\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082D¢\u0006\u0002\n\u0000R\u0016\u0010\t\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, m1890d2 = {"Lcom/hb/oma/encryp/AESUtils;", "", "()V", "CHARSET_UTF8", "Ljava/nio/charset/Charset;", "kotlin.jvm.PlatformType", "CIPHER_ALGORITHM", "", "KEY_ALGORITHM", "TAG", "iv", "", "keyType", "", "decrypt", "secretKey", "dataStr", "encrypt", "data", "handleException", "", "methodName", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "setDataType", "type", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.hb.oma.encryp.AESUtils */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class AESUtils {
    public static final AESUtils INSTANCE = new AESUtils();
    private static final String TAG = AESUtils.class.getSimpleName();
    private static int keyType = 1;
    private static final String KEY_ALGORITHM = "AES";
    private static final Charset CHARSET_UTF8 = StandardCharsets.UTF_8;
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    /* renamed from: iv */
    private static final byte[] f10534iv = ResultExt.toBytes("00000000000000000000000000000000");

    private AESUtils() {
    }

    public final void setDataType(int i) {
        keyType = i;
    }

    @Nullable
    public final String encrypt(@NotNull String secretKey, @NotNull String data) {
        Intrinsics.checkParameterIsNotNull(secretKey, "secretKey");
        Intrinsics.checkParameterIsNotNull(data, "data");
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(ResultExt.toBytes(secretKey), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(1, secretKeySpec);
            byte[] encryptByte = cipher.doFinal(ResultExt.toBytes(data));
            if (keyType != 0) {
                Intrinsics.checkExpressionValueIsNotNull(encryptByte, "encryptByte");
                return ResultExt.toHex(encryptByte);
            }
            return null;
        } catch (Exception e) {
            handleException("encrypt", e);
            return null;
        }
    }

    @Nullable
    public final String decrypt(@NotNull String secretKey, @NotNull String dataStr) {
        Intrinsics.checkParameterIsNotNull(secretKey, "secretKey");
        Intrinsics.checkParameterIsNotNull(dataStr, "dataStr");
        try {
            byte[] bytes = ResultExt.toBytes(dataStr);
            SecretKeySpec secretKeySpec = new SecretKeySpec(ResultExt.toBytes(secretKey), KEY_ALGORITHM);
            Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
            cipher.init(2, secretKeySpec);
            byte[] result = cipher.doFinal(bytes);
            if (keyType == 0) {
                Intrinsics.checkExpressionValueIsNotNull(result, "result");
                Charset CHARSET_UTF82 = CHARSET_UTF8;
                Intrinsics.checkExpressionValueIsNotNull(CHARSET_UTF82, "CHARSET_UTF8");
                return new String(result, CHARSET_UTF82);
            }
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            return ResultExt.toHex(result);
        } catch (Exception e) {
            handleException("decrypt", e);
            return null;
        }
    }

    public final void handleException(@NotNull String methodName, @NotNull Exception e) {
        Intrinsics.checkParameterIsNotNull(methodName, "methodName");
        Intrinsics.checkParameterIsNotNull(e, "e");
        e.printStackTrace();
    }
}
