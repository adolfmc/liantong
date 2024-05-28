package com.sinovatech.unicom.separatemodule.simbox.encryp;

import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\u001a\u0006\u0010\u0000\u001a\u00020\u0001\u001a\n\u0010\u0002\u001a\u00020\u0001*\u00020\u0001\u001a\u0012\u0010\u0003\u001a\u00020\u0001*\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005\u001a\n\u0010\u0006\u001a\u00020\u0005*\u00020\u0007\u001a\n\u0010\b\u001a\u00020\u0005*\u00020\u0007\u001a\n\u0010\t\u001a\u00020\u0005*\u00020\n\u001a\n\u0010\t\u001a\u00020\u0005*\u00020\u000b\u001a\n\u0010\f\u001a\u00020\u0005*\u00020\u0001\u001a\n\u0010\r\u001a\u00020\u0001*\u00020\u0005\u001a\n\u0010\u000e\u001a\u00020\u0007*\u00020\u0005¨\u0006\u000f"}, m1890d2 = {"getTimeToHex", "", "getTime", "setPukSign", "puk", "", "to2Bytes", "", "to2BytesLittleEndian", "to6Bytes", "Ljava/math/BigDecimal;", "", "toBytes", "toHex", "toInt", "unicom_gphone4x_trunk_Production_ABIS64Release"}, m1889k = 2, m1888mv = {1, 1, 16})
/* renamed from: com.sinovatech.unicom.separatemodule.simbox.encryp.ResultExtKt */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class ResultExt {
    @NotNull
    public static final String getTimeToHex() {
        String hexString = Integer.toHexString((int) (System.currentTimeMillis() / 1000));
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(timestamp.toInt())");
        return hexString;
    }

    @NotNull
    public static final String setPukSign(@NotNull String setPukSign, @NotNull byte[] puk) {
        String str;
        Intrinsics.checkParameterIsNotNull(setPukSign, "$this$setPukSign");
        Intrinsics.checkParameterIsNotNull(puk, "puk");
        if (puk.length > 64) {
            str = "7f4940" + toHex(ArraysKt.copyOfRange(puk, 1, puk.length));
        } else {
            str = "7f4940" + toHex(puk);
        }
        return str + ("5f3740" + setPukSign);
    }

    @NotNull
    public static final byte[] toBytes(@NotNull String toBytes) {
        Intrinsics.checkParameterIsNotNull(toBytes, "$this$toBytes");
        byte[] fromHex = ByteUtil.fromHex(toBytes);
        return fromHex != null ? fromHex : new byte[0];
    }

    @NotNull
    public static final String toHex(@NotNull byte[] toHex) {
        Intrinsics.checkParameterIsNotNull(toHex, "$this$toHex");
        String hex = ByteUtil.toHex(toHex);
        return hex != null ? hex : "";
    }

    @NotNull
    public static final String getTime(@NotNull String getTime) {
        Intrinsics.checkParameterIsNotNull(getTime, "$this$getTime");
        String format = new SimpleDateFormat(JtDateUtil.dateFormatYMDHM).format(Long.valueOf(Integer.parseInt(getTime, 16) * 1000));
        Intrinsics.checkExpressionValueIsNotNull(format, "format.format(time)");
        return format;
    }

    public static final int toInt(@NotNull byte[] toInt) {
        Intrinsics.checkParameterIsNotNull(toInt, "$this$toInt");
        return Integer.parseInt(toHex(toInt), 16);
    }

    @NotNull
    public static final byte[] to6Bytes(@NotNull BigDecimal to6Bytes) {
        Intrinsics.checkParameterIsNotNull(to6Bytes, "$this$to6Bytes");
        Object[] objArr = {to6Bytes.multiply(new BigDecimal("100")).toBigInteger()};
        String format = String.format("%012x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return toBytes(format);
    }

    @NotNull
    public static final byte[] to6Bytes(long j) {
        Object[] objArr = {Long.valueOf(j)};
        String format = String.format("%012x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return toBytes(format);
    }

    @NotNull
    public static final byte[] to2Bytes(int i) {
        Object[] objArr = {Integer.valueOf(i)};
        String format = String.format("%04x", Arrays.copyOf(objArr, objArr.length));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return toBytes(format);
    }

    @NotNull
    public static final byte[] to2BytesLittleEndian(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }
}
