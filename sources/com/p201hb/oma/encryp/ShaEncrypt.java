package com.p201hb.oma.encryp;

import com.sinovatech.unicom.separatemodule.simbox.encryp.ResultExt;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* compiled from: ShaEncrypt.kt */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002J\u001a\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0006\u001a\u00020\u0004H\u0002J\u0010\u0010\u0007\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u0010\u0010\b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u0004J\u000e\u0010\t\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0004¨\u0006\n"}, m1890d2 = {"Lcom/hb/oma/encryp/ShaEncrypt;", "", "()V", "SHA", "", "strText", "strType", "SHA256", "SHA512", "getSecretkey", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 1, m1888mv = {1, 1, 16})
/* renamed from: com.hb.oma.encryp.ShaEncrypt */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class ShaEncrypt {
    public static final ShaEncrypt INSTANCE = new ShaEncrypt();

    private ShaEncrypt() {
    }

    @Nullable
    public final String SHA256(@NotNull String strText) {
        Intrinsics.checkParameterIsNotNull(strText, "strText");
        return SHA(strText, "SHA-256");
    }

    @NotNull
    public final String getSecretkey(@NotNull String strText) {
        Intrinsics.checkParameterIsNotNull(strText, "strText");
        byte[] bytes = ResultExt.toBytes(strText);
        byte[] copyOfRange = ArraysKt.copyOfRange(bytes, 0, 16);
        byte[] copyOfRange2 = ArraysKt.copyOfRange(bytes, 16, 32);
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (copyOfRange[i] ^ copyOfRange2[i]);
        }
        return ResultExt.toHex(bArr);
    }

    @Nullable
    public final String SHA512(@NotNull String strText) {
        Intrinsics.checkParameterIsNotNull(strText, "strText");
        return SHA(strText, "SHA-512");
    }

    private final String SHA(String str, String str2) {
        String str3 = null;
        if (str == null || str.length() <= 0) {
            return str3;
        }
        try {
            MessageDigest messageDigest = MessageDigest.getInstance(str2);
            byte[] bytes = str.getBytes(Charsets.UTF_8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            messageDigest.update(bytes);
            byte[] byteBuffer = messageDigest.digest();
            StringBuffer stringBuffer = new StringBuffer();
            Intrinsics.checkExpressionValueIsNotNull(byteBuffer, "byteBuffer");
            for (byte b : byteBuffer) {
                String hexString = Integer.toHexString(b & 255);
                if (hexString.length() == 1) {
                    stringBuffer.append('0');
                }
                stringBuffer.append(hexString);
            }
            return stringBuffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return str3;
        }
    }
}
