package p001a.p002a.p003a.p004a.p005a.p007c;

import com.chinaunicon.jtwifilib.core.utils.JtDateUtil;
import com.p201hb.omapi.union.sim.bean.ErrCode;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.c.e */
/* loaded from: E:\567196_dexfile_execute.dex */
public final class C0104e {

    /* renamed from: b */
    public static final byte f57b = 0;

    /* renamed from: a */
    public static final byte f56a = (byte) 144;

    /* renamed from: c */
    public static final byte f58c = (byte) 16;

    /* renamed from: d */
    public static final byte f59d = (byte) 146;

    /* renamed from: e */
    public static final byte f60e = (byte) 99;

    @NotNull
    /* renamed from: a */
    public static final List<String> m24381a(@NotNull String str) {
        List split$default = StringsKt.split$default((CharSequence) StringsKt.split$default((CharSequence) str, new String[]{"7f4940"}, false, 0, 6, (Object) null).get(1), new String[]{"5f3740"}, false, 0, 6, (Object) null);
        ArrayList arrayList = new ArrayList();
        arrayList.add("04" + ((String) split$default.get(0)));
        arrayList.add(split$default.get(1));
        return arrayList;
    }

    @NotNull
    /* renamed from: b */
    public static final String m24376b(@NotNull String str) {
        String format = new SimpleDateFormat(JtDateUtil.dateFormatYMDHM).format(Long.valueOf(Integer.parseInt(str, 16) * 1000));
        Intrinsics.checkExpressionValueIsNotNull(format, "format.format(time)");
        return format;
    }

    @NotNull
    /* renamed from: c */
    public static final byte[] m24374c(@NotNull String str) {
        byte[] m24394a = C0102c.m24394a(str);
        return m24394a != null ? m24394a : new byte[0];
    }

    /* renamed from: d */
    public static final boolean m24372d(@NotNull byte[] bArr) {
        return bArr.length >= 2 && bArr[bArr.length - 2] == f59d && bArr[bArr.length - 1] == f57b;
    }

    /* renamed from: e */
    public static final boolean m24371e(@NotNull byte[] bArr) {
        return bArr.length >= 2 && bArr[bArr.length - 2] == f56a && bArr[bArr.length - 1] == f57b;
    }

    @NotNull
    /* renamed from: f */
    public static final byte[] m24370f(@NotNull byte[] bArr) {
        return bArr.length >= 2 ? ArraysKt.copyOfRange(bArr, bArr.length - 2, bArr.length) : m24374c("ffff");
    }

    @NotNull
    /* renamed from: g */
    public static final String m24369g(@NotNull byte[] bArr) {
        return bArr.length >= 2 ? m24368h(ArraysKt.copyOfRange(bArr, bArr.length - 2, bArr.length)) : "";
    }

    @NotNull
    /* renamed from: h */
    public static final String m24368h(@NotNull byte[] bArr) {
        String m24392a = C0102c.m24392a(bArr);
        return m24392a != null ? m24392a : "";
    }

    /* renamed from: i */
    public static final int m24367i(@NotNull byte[] bArr) {
        return Integer.parseInt(m24368h(bArr), 16);
    }

    /* renamed from: c */
    public static final boolean m24373c(@NotNull byte[] bArr) {
        return bArr.length >= 2 && bArr[bArr.length - 2] == f60e && bArr[bArr.length - 1] == f58c;
    }

    /* renamed from: b */
    public static final boolean m24375b(@NotNull byte[] bArr) {
        if (bArr.length < 2 || bArr[bArr.length - 2] != f60e) {
            return false;
        }
        ErrCode.Companion.set63cX(bArr[bArr.length - 1]);
        return true;
    }

    @NotNull
    /* renamed from: b */
    public static final byte[] m24377b(int i) {
        return new byte[]{(byte) (i & 255), (byte) ((i >> 8) & 255)};
    }

    @NotNull
    /* renamed from: a */
    public static final String m24384a() {
        String hexString = Integer.toHexString((int) (System.currentTimeMillis() / 1000));
        Intrinsics.checkExpressionValueIsNotNull(hexString, "Integer.toHexString(timestamp.toInt())");
        return hexString;
    }

    @NotNull
    /* renamed from: a */
    public static final String m24380a(@NotNull String str, @NotNull byte[] bArr) {
        String str2;
        if (bArr.length > 64) {
            str2 = "7f4940" + m24368h(ArraysKt.copyOfRange(bArr, 1, bArr.length));
        } else {
            str2 = "7f4940" + m24368h(bArr);
        }
        return str2 + ("5f3740" + str);
    }

    /* renamed from: a */
    public static final boolean m24378a(@NotNull byte[] bArr) {
        return bArr.length >= 2 && bArr[bArr.length - 2] == f56a;
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m24379a(@NotNull BigDecimal bigDecimal) {
        String format = String.format("%012x", Arrays.copyOf(new Object[]{bigDecimal.multiply(new BigDecimal("100")).toBigInteger()}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return m24374c(format);
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m24382a(long j) {
        String format = String.format("%012x", Arrays.copyOf(new Object[]{Long.valueOf(j)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return m24374c(format);
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m24383a(int i) {
        String format = String.format("%04x", Arrays.copyOf(new Object[]{Integer.valueOf(i)}, 1));
        Intrinsics.checkExpressionValueIsNotNull(format, "java.lang.String.format(this, *args)");
        return m24374c(format);
    }
}
