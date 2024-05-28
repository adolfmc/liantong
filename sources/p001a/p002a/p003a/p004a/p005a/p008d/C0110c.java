package p001a.p002a.p003a.p004a.p005a.p008d;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p001a.p002a.p003a.p004a.p005a.p007c.C0104e;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.C0114b;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.C0115c;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.p010d.C0116a;
import p001a.p002a.p003a.p004a.p005a.p008d.p012g.C0119b;
import p001a.p002a.p003a.p004a.p005a.p008d.p013h.C0121b;

/* renamed from: a.a.a.a.a.d.c */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public final class C0110c {

    /* renamed from: a */
    public static final C0110c f95a = new C0110c();

    @Nullable
    /* renamed from: a */
    public final String m24311a(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4, @NotNull String str5, @NotNull String str6, @NotNull String str7, @NotNull String str8) {
        C0116a c0116a = new C0116a();
        c0116a.m24250a(C0104e.m24374c(str3));
        c0116a.m24248b(C0104e.m24374c(str4));
        C0116a c0116a2 = new C0116a();
        c0116a2.m24248b(C0104e.m24374c(str7));
        C0114b m24262a = C0114b.m24262a();
        byte[] bArr = new byte[32];
        c0116a.m24251a(str, bArr);
        byte[] bArr2 = new byte[32];
        c0116a2.m24251a(str2, bArr2);
        C0109b c0109b = new C0109b();
        c0109b.m24317a(c0116a.m24249b(), str5, C0104e.m24374c(str6));
        return c0109b.m24320a(m24262a.f112g.m22621a(c0116a2.m24247c()), m24262a.f112g.m22621a(C0104e.m24374c(str8)), bArr, bArr2);
    }

    @NotNull
    /* renamed from: b */
    public final byte[] m24309b(@NotNull String str) {
        byte[] bArr = new byte[32];
        byte[] m24374c = C0104e.m24374c(str);
        C0119b c0119b = new C0119b();
        c0119b.m24221a(m24374c, 0, m24374c.length);
        c0119b.m24222a(bArr, 0);
        return bArr;
    }

    @NotNull
    /* renamed from: c */
    public final byte[] m24307c(@NotNull String str, @NotNull String str2) {
        String m24314a = m24314a(str2);
        C0121b c0121b = new C0121b();
        c0121b.f144a = str;
        c0121b.f146c = true;
        String cipherText = c0121b.m24198d(m24314a);
        Intrinsics.checkExpressionValueIsNotNull(cipherText, "cipherText");
        return C0104e.m24374c(cipherText);
    }

    @NotNull
    /* renamed from: b */
    public final String m24308b(@NotNull String str, @NotNull String str2) {
        C0121b c0121b = new C0121b();
        c0121b.f144a = str;
        c0121b.f146c = true;
        String plainText = c0121b.m24200b(str2);
        Intrinsics.checkExpressionValueIsNotNull(plainText, "plainText");
        return plainText;
    }

    /* renamed from: a */
    public final boolean m24312a(@NotNull String str, @NotNull String str2, @NotNull String str3, @NotNull String str4) {
        return C0115c.m24256a(str, C0112e.m24274f(str2), C0112e.m24274f(str3), C0112e.m24274f(str4));
    }

    @NotNull
    /* renamed from: a */
    public final String m24310a(@NotNull String str, @NotNull byte[] bArr, @NotNull String str2) {
        Charset charset = StandardCharsets.US_ASCII;
        Intrinsics.checkExpressionValueIsNotNull(charset, "StandardCharsets.US_ASCII");
        byte[] bytes = str.getBytes(charset);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        String m24254a = C0115c.m24254a(bytes, bArr, C0112e.m24274f(str2));
        Intrinsics.checkExpressionValueIsNotNull(m24254a, "SM2Utils.sign(\n         …tes(sourceData)\n        )");
        return m24254a;
    }

    @NotNull
    /* renamed from: a */
    public final String m24313a(@NotNull String str, @NotNull String str2) {
        String m24314a = m24314a(str2);
        C0121b c0121b = new C0121b();
        c0121b.f144a = str;
        c0121b.f146c = true;
        String m24199c = c0121b.m24199c(m24314a);
        Intrinsics.checkExpressionValueIsNotNull(m24199c, "sm4.encryptData_CBC(data)");
        byte[] m24374c = C0104e.m24374c(m24199c);
        return C0104e.m24368h(ArraysKt.copyOfRange(ArraysKt.copyOfRange(m24374c, m24374c.length - 16, m24374c.length), 0, 4));
    }

    @NotNull
    /* renamed from: a */
    public final String m24314a(@NotNull String str) {
        int i;
        int length = C0104e.m24374c(str).length % 16;
        if (length == 0 || 1 > (i = 16 - length)) {
            return str;
        }
        String str2 = str;
        int i2 = 1;
        while (true) {
            if (i2 == 1) {
                str2 = str2 + "80";
            } else {
                str2 = str2 + "00";
            }
            if (i2 == i) {
                return str2;
            }
            i2++;
        }
    }
}
