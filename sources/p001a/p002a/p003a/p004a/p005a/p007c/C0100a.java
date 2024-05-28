package p001a.p002a.p003a.p004a.p005a.p007c;

import com.p201hb.omapi.union.sim.SmartCard;
import com.p201hb.omapi.union.sim.bean.ErrCode;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import kotlin.TypeCastException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.CharsKt;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import p001a.p002a.p003a.p004a.p005a.p006b.C0099c;
import p001a.p002a.p003a.p004a.p005a.p008d.C0110c;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.p010d.C0116a;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.c.a */
/* loaded from: E:\567196_dexfile_execute.dex */
public final class C0100a {
    @NotNull
    /* renamed from: a */
    public static final HashMap<String, Object> m24416a(@NotNull BaseSmartCard baseSmartCard) {
        byte[] mo24345a = baseSmartCard.mo24345a(new byte[]{(byte) 0, 42, 0, 0, 0});
        C0103d.m24388a(C0104e.m24368h(mo24345a));
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("CODE", Integer.valueOf(ErrCode.Companion.getErrCode(C0104e.m24369g(mo24345a))));
        if (C0104e.m24371e(mo24345a)) {
            int parseInt = Integer.parseInt(C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, 1, 2)), CharsKt.checkRadix(16)) + 2;
            String m24368h = C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, 2, parseInt));
            int i = parseInt + 1;
            int i2 = parseInt + 2;
            int parseInt2 = Integer.parseInt(C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, i, i2)), CharsKt.checkRadix(16)) + i2;
            String m24368h2 = C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, i2, parseInt2));
            int i3 = parseInt2 + 1;
            int i4 = parseInt2 + 2;
            String m24368h3 = C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, i4, Integer.parseInt(C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, i3, i4)), CharsKt.checkRadix(16)) + i4));
            C0103d.m24388a("iccid:" + m24368h + "--filp iccid" + m24408a(m24368h));
            C0103d.m24388a("imsi:" + m24368h2 + "--filp imsi" + m24408a(m24368h2));
            hashMap.put("ICCID", m24408a(m24368h));
            hashMap.put("IMSI", m24408a(m24368h2));
            hashMap.put("ISPSW", m24368h3);
            return hashMap;
        }
        return hashMap;
    }

    @NotNull
    /* renamed from: b */
    public static final String m24403b(@NotNull String str) {
        String substring = "1234567812345678888888881234567812345678".substring(8);
        Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
        return m24401c(C0110c.f95a.m24308b(substring, str));
    }

    /* renamed from: c */
    public static final int m24402c(@NotNull BaseSmartCard baseSmartCard, @NotNull String str) {
        C0103d.m24386c("setPin");
        Object obj = m24416a(baseSmartCard).get("ISPSW");
        if (obj != null) {
            if (!"00".equals((String) obj)) {
                return ErrCode.Companion.getError_Instruction_Cmd();
            }
            int m24399e = m24399e(baseSmartCard, "88888888");
            if (m24399e != ErrCode.Companion.getError_Success()) {
                return m24399e;
            }
            String random = SmartCard.Companion.getRandom(0, 8);
            Charset charset = StandardCharsets.US_ASCII;
            Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
            byte[] bytes = str.getBytes(charset);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            byte[] m24407a = m24407a(ArraysKt.plus(C0110c.f95a.m24309b(C0104e.m24368h(bytes)), C0104e.m24374c(random)));
            C0103d.m24388a("明文:" + C0104e.m24368h(m24407a));
            byte[] m24307c = C0110c.f95a.m24307c(baseSmartCard.mo24340c(), C0104e.m24368h(m24407a));
            byte[] mo24345a = baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, 33, 0, 0, (byte) (m24307c.length & 255)}, m24307c));
            baseSmartCard.mo24342b("");
            m24399e(baseSmartCard, str);
            C0103d.m24388a("set pin result:" + C0104e.m24368h(mo24345a));
            ErrCode.Companion companion = ErrCode.Companion;
            String m24369g = C0104e.m24369g(mo24345a);
            if (m24369g != null) {
                String lowerCase = m24369g.toLowerCase();
                Intrinsics.checkExpressionValueIsNotNull(lowerCase, "(this as java.lang.String).toLowerCase()");
                return companion.getErrCode(lowerCase);
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
    }

    /* renamed from: d */
    public static final int m24400d(@NotNull BaseSmartCard baseSmartCard, @NotNull String str) {
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        return ErrCode.Companion.getErrCode(C0104e.m24369g(baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, 32, 0, 0, 8}, ArraysKt.copyOfRange(C0110c.f95a.m24307c(C0104e.m24368h(ArraysKt.copyOfRange(C0110c.f95a.m24309b(C0104e.m24368h(bytes)), 0, 16)), SmartCard.Companion.getRandom(0, 8)), 0, 8)))));
    }

    /* renamed from: e */
    public static final int m24399e(@NotNull BaseSmartCard baseSmartCard, @NotNull String str) {
        C0103d.m24386c("verifyPin");
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        C0103d.m24388a("pin:" + C0104e.m24368h(bytes));
        byte[] copyOfRange = ArraysKt.copyOfRange(C0110c.f95a.m24309b(C0104e.m24368h(bytes)), 0, 16);
        String random = SmartCard.Companion.getRandom(0, 8);
        C0103d.m24388a("随机数：" + random);
        byte[] mo24345a = baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, 32, 0, 0, 8}, ArraysKt.copyOfRange(C0110c.f95a.m24307c(C0104e.m24368h(copyOfRange), random), 0, 8)));
        return !C0104e.m24371e(mo24345a) ? ErrCode.Companion.getErrCode(C0104e.m24369g(mo24345a)) : m24412a(baseSmartCard, str);
    }

    /* renamed from: b */
    public static final int m24405b(@NotNull BaseSmartCard baseSmartCard, byte b, byte b2, @NotNull String str) {
        byte[] m24374c = C0104e.m24374c(str);
        byte[] plus = ArraysKt.plus(ArraysKt.plus(new byte[]{b, b2}, (byte) (m24374c.length & 255)), m24374c);
        C0103d.m24388a("明文：" + C0104e.m24368h(plus));
        byte[] m24307c = C0110c.f95a.m24307c(baseSmartCard.mo24340c(), C0104e.m24368h(plus));
        return ErrCode.Companion.getErrCode(C0104e.m24369g(baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, (byte) 220, b, b2, (byte) (m24307c.length & 255)}, m24307c))));
    }

    @NotNull
    /* renamed from: b */
    public static final String m24404b(@NotNull BaseSmartCard baseSmartCard, @NotNull String str) {
        byte[] mo24345a = baseSmartCard.mo24345a(ArraysKt.plus(ArraysKt.plus(new byte[]{(byte) 128, (byte) 80, 0, 0, 8}, C0104e.m24374c(str)), (byte) 134));
        return C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, 0, mo24345a.length - 2));
    }

    @NotNull
    /* renamed from: b */
    public static final String m24406b(@NotNull BaseSmartCard baseSmartCard) {
        byte[] mo24345a = baseSmartCard.mo24345a(new byte[]{(byte) 0, (byte) 132, 0, 0, 8});
        return C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, 0, mo24345a.length - 2));
    }

    @NotNull
    /* renamed from: a */
    public static final String m24408a(@NotNull String str) {
        String str2 = "";
        for (int i = 0; i != str.length(); i += 2) {
            str2 = str2 + str.charAt(i + 1) + str.charAt(i);
        }
        return str2;
    }

    /* renamed from: a */
    public static final int m24412a(@NotNull BaseSmartCard baseSmartCard, @NotNull String str) {
        String random = SmartCard.Companion.getRandom(0, 8);
        List<String> m24381a = C0104e.m24381a(m24404b(baseSmartCard, random));
        if (!C0110c.f95a.m24312a("1234567812345678", m24381a.get(0), random, m24381a.get(1))) {
            return ErrCode.Companion.getError_SIM_VERSIGN();
        }
        C0116a c0116a = new C0116a();
        c0116a.m24252a();
        C0110c c0110c = C0110c.f95a;
        String m24419d = C0099c.f45l.m24419d();
        String m24420c = C0099c.f45l.m24420c();
        String m24403b = m24403b(C0099c.f45l.m24418e());
        String m24403b2 = m24403b(C0099c.f45l.m24417f());
        byte[] m24249b = c0116a.m24249b();
        Intrinsics.checkExpressionValueIsNotNull(m24249b, "sm2.priKey");
        String m24368h = C0104e.m24368h(m24249b);
        byte[] m24247c = c0116a.m24247c();
        Intrinsics.checkExpressionValueIsNotNull(m24247c, "sm2.pubKey");
        String m24311a = c0110c.m24311a(m24419d, m24420c, m24403b, m24403b2, m24368h, C0104e.m24368h(m24247c), m24403b(C0099c.f45l.m24421b()), m24381a.get(0));
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] m24309b = C0110c.f95a.m24309b(C0104e.m24368h(bytes));
        if (m24311a == null) {
            Intrinsics.throwNpe();
        }
        baseSmartCard.mo24342b(C0104e.m24368h(ArraysKt.copyOfRange(C0110c.f95a.m24309b(C0104e.m24368h(ArraysKt.plus(m24309b, ArraysKt.copyOfRange(C0104e.m24374c(m24311a), 0, 16)))), 0, 16)));
        StringBuilder sb = new StringBuilder();
        String str2 = m24381a.get(0);
        if (str2 != null) {
            String substring = str2.substring(2);
            Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.String).substring(startIndex)");
            sb.append(substring);
            sb.append(m24381a.get(1));
            String sb2 = sb.toString();
            C0110c c0110c2 = C0110c.f95a;
            byte[] m24249b2 = c0116a.m24249b();
            Intrinsics.checkExpressionValueIsNotNull(m24249b2, "sm2.priKey");
            String m24310a = c0110c2.m24310a("1234567812345678", m24249b2, sb2);
            System.out.println((Object) ("sign2:" + m24310a));
            byte[] m24247c2 = c0116a.m24247c();
            Intrinsics.checkExpressionValueIsNotNull(m24247c2, "sm2.pubKey");
            byte[] m24409a = m24409a(baseSmartCard, C0104e.m24374c(C0104e.m24380a(m24310a, m24247c2)));
            String m24368h2 = C0104e.m24368h(ArraysKt.copyOfRange(m24409a, 0, 8));
            if (C0104e.m24371e(m24409a)) {
                C0103d.m24388a("会话秘钥：" + baseSmartCard.mo24340c());
                if (StringsKt.contains$default((CharSequence) C0104e.m24368h(m24409a), (CharSequence) C0110c.f95a.m24313a(baseSmartCard.mo24340c(), m24368h2), false, 2, (Object) null)) {
                    return 0;
                }
                C0103d.m24387b("mac2 check error！");
                return 16;
            }
            return ErrCode.Companion.getErrCode(C0104e.m24369g(m24409a));
        }
        throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }

    @NotNull
    /* renamed from: c */
    public static final String m24401c(@NotNull String str) {
        int i;
        byte[] m24374c = C0104e.m24374c(str);
        int length = m24374c.length;
        while (true) {
            i = length - 1;
            if (!C0104e.m24368h(ArraysKt.copyOfRange(m24374c, i, length)).equals("00")) {
                break;
            }
            m24374c = ArraysKt.copyOfRange(m24374c, 0, i);
            length = m24374c.length;
        }
        if (C0104e.m24368h(ArraysKt.copyOfRange(m24374c, i, length)).equals("80")) {
            m24374c = ArraysKt.copyOfRange(m24374c, 0, i);
        }
        return C0104e.m24368h(m24374c);
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m24407a(@NotNull byte[] bArr) {
        int length = bArr.length % 16;
        if (length == 0) {
            return bArr;
        }
        byte[] bArr2 = bArr;
        int i = length;
        while (i <= 15) {
            bArr2 = i == length ? ArraysKt.plus(bArr2, new byte[]{(byte) 128}) : ArraysKt.plus(bArr2, new byte[]{0});
            i++;
        }
        return bArr2;
    }

    /* renamed from: a */
    public static final int m24411a(@NotNull BaseSmartCard baseSmartCard, @NotNull String str, @NotNull String str2) {
        int m24400d = m24400d(baseSmartCard, str);
        if (m24400d != ErrCode.Companion.getError_Success()) {
            return m24400d;
        }
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
        byte[] bytes = str2.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] m24309b = C0110c.f95a.m24309b(C0104e.m24368h(bytes));
        C0103d.m24388a("new pin:" + str2 + "----ASCLL-PIN:+" + C0104e.m24368h(bytes) + "\nhashnewpin:" + C0104e.m24368h(m24309b));
        Charset charset2 = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset2, "StandardCharsets.US_ASCII");
        byte[] bytes2 = str.getBytes(charset2);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        byte[] copyOfRange = ArraysKt.copyOfRange(C0110c.f95a.m24309b(C0104e.m24368h(bytes2)), 0, 16);
        C0103d.m24388a("old pin:" + str + "----ASCLL-PIN:+" + C0104e.m24368h(bytes2) + "\nhasholdpin:" + C0104e.m24368h(copyOfRange));
        C0110c c0110c = C0110c.f95a;
        String m24368h = C0104e.m24368h(copyOfRange);
        StringBuilder sb = new StringBuilder();
        sb.append(C0104e.m24368h(m24309b));
        sb.append(C0104e.m24368h(copyOfRange));
        byte[] mo24345a = baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, 36, 0, 0, 48}, c0110c.m24307c(m24368h, sb.toString())));
        if (!C0104e.m24371e(mo24345a)) {
            return ErrCode.Companion.getErrCode(C0104e.m24369g(mo24345a));
        }
        baseSmartCard.mo24342b("");
        return m24399e(baseSmartCard, str2);
    }

    /* renamed from: a */
    public static final int m24410a(@NotNull BaseSmartCard baseSmartCard, @NotNull String str, @NotNull byte[] bArr) {
        C0103d.m24386c("reloadPin");
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        byte[] m24309b = C0110c.f95a.m24309b(C0104e.m24368h(bytes));
        C0103d.m24388a("pin hash:" + C0104e.m24368h(m24309b));
        byte[] plus = ArraysKt.plus(bArr, m24309b);
        C0103d.m24388a("plaintext:" + C0104e.m24368h(plus));
        byte[] m24309b2 = C0110c.f95a.m24309b(SmartCard.Companion.getRandom(0, 8));
        C0103d.m24388a("randomHash:" + C0104e.m24368h(m24309b2));
        String m24368h = C0104e.m24368h(ArraysKt.copyOfRange(m24309b2, 0, 16));
        C0103d.m24388a("key:" + m24368h);
        byte[] mo24345a = baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, 44, 0, 0, 48}, C0110c.f95a.m24307c(m24368h, C0104e.m24368h(plus))));
        if (!C0104e.m24371e(mo24345a)) {
            ErrCode.Companion.getErrCode(C0104e.m24369g(mo24345a));
        }
        baseSmartCard.mo24342b("");
        return m24399e(baseSmartCard, str);
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m24415a(@NotNull BaseSmartCard baseSmartCard, byte b) {
        C0103d.m24386c("browseItem");
        StringBuffer stringBuffer = new StringBuffer();
        byte b2 = 0;
        while (true) {
            byte[] m24307c = C0110c.f95a.m24307c(baseSmartCard.mo24340c(), C0104e.m24368h(new byte[]{b2, b}));
            byte[] mo24345a = baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, (byte) 180, b2, b, (byte) (m24307c.length & 255)}, m24307c));
            if (C0104e.m24371e(mo24345a) || C0104e.m24373c(mo24345a)) {
                if (C0104e.m24371e(mo24345a)) {
                    if (mo24345a.length <= 2) {
                        stringBuffer.append(C0104e.m24368h(mo24345a));
                    } else {
                        stringBuffer.append(m24401c(C0110c.f95a.m24308b(baseSmartCard.mo24340c(), C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, 0, mo24345a.length - 2)))));
                        stringBuffer.append("9000");
                    }
                    C0103d.m24388a("browseItem:" + stringBuffer.toString());
                    String stringBuffer2 = stringBuffer.toString();
                    Intrinsics.checkExpressionValueIsNotNull(stringBuffer2, "(stringBuffer.toString())");
                    return C0104e.m24374c(stringBuffer2);
                }
                stringBuffer.append(m24401c(C0110c.f95a.m24308b(baseSmartCard.mo24340c(), C0104e.m24368h(ArraysKt.copyOfRange(mo24345a, 0, mo24345a.length - 2)))));
                b2 = 1;
            } else if (b2 != ((byte) 1)) {
                return mo24345a;
            } else {
                C0103d.m24386c("01报错,02查询");
                b2 = 2;
            }
        }
    }

    /* renamed from: a */
    public static final int m24413a(@NotNull BaseSmartCard baseSmartCard, byte b, @NotNull String str) {
        C0103d.m24386c("appendItem");
        byte[] plus = ArraysKt.plus(C0104e.m24374c(C0104e.m24384a()), C0104e.m24374c(str));
        byte[] plus2 = ArraysKt.plus(ArraysKt.plus(new byte[]{0, b}, (byte) (plus.length & 255)), plus);
        C0103d.m24388a("data:" + C0104e.m24368h(plus2));
        byte[] m24307c = C0110c.f95a.m24307c(baseSmartCard.mo24340c(), C0104e.m24368h(plus2));
        return ErrCode.Companion.getErrCode(C0104e.m24369g(baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 0, (byte) 218, 0, b, (byte) (m24307c.length & 255)}, m24307c))));
    }

    /* renamed from: a */
    public static final int m24414a(@NotNull BaseSmartCard baseSmartCard, byte b, byte b2, @NotNull String str) {
        byte[] m24307c = C0110c.f95a.m24307c(baseSmartCard.mo24340c(), C0104e.m24368h(ArraysKt.plus(new byte[]{b, b2}, C0104e.m24374c(str))));
        return ErrCode.Companion.getErrCode(C0104e.m24369g(baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, (byte) 222, b, b2, (byte) (m24307c.length & 255)}, m24307c))));
    }

    @NotNull
    /* renamed from: a */
    public static final byte[] m24409a(@NotNull BaseSmartCard baseSmartCard, @NotNull byte[] bArr) {
        return baseSmartCard.mo24345a(ArraysKt.plus(new byte[]{(byte) 128, (byte) 82, 0, 0, (byte) 134}, bArr));
    }
}
