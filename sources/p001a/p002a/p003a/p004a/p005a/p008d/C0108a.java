package p001a.p002a.p003a.p004a.p005a.p008d;

import android.util.Base64;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p001a.p002a.p003a.p004a.p005a.p007c.C0103d;
import p001a.p002a.p003a.p004a.p005a.p007c.C0104e;

/* renamed from: a.a.a.a.a.d.a */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public final class C0108a {

    /* renamed from: g */
    public static final C0108a f89g = new C0108a();

    /* renamed from: a */
    public static final String f83a = C0108a.class.getSimpleName();

    /* renamed from: b */
    public static int f84b = 1;

    /* renamed from: c */
    public static final String f85c = f85c;

    /* renamed from: c */
    public static final String f85c = f85c;

    /* renamed from: d */
    public static final Charset f86d = StandardCharsets.UTF_8;

    /* renamed from: e */
    public static final String f87e = f87e;

    /* renamed from: e */
    public static final String f87e = f87e;

    /* renamed from: f */
    public static final byte[] f88f = C0104e.m24374c("00000000000000000000000000000000");

    /* renamed from: a */
    public final void m24330a(int i) {
        f84b = i;
    }

    @Nullable
    /* renamed from: b */
    public final String m24325b(@NotNull String str, @NotNull String str2) {
        if (str.length() != 16) {
            C0103d.m24385d("Key长度不是16位");
            return null;
        }
        Charset CHARSET_UTF8 = f86d;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET_UTF8, "CHARSET_UTF8");
        byte[] bytes = str.getBytes(CHARSET_UTF8);
        Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
        SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, f85c);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(1, secretKeySpec, new IvParameterSpec(f88f));
        Charset CHARSET_UTF82 = f86d;
        Intrinsics.checkExpressionValueIsNotNull(CHARSET_UTF82, "CHARSET_UTF8");
        byte[] bytes2 = str2.getBytes(CHARSET_UTF82);
        Intrinsics.checkExpressionValueIsNotNull(bytes2, "(this as java.lang.String).getBytes(charset)");
        return m24326a(cipher.doFinal(bytes2));
    }

    @Nullable
    /* renamed from: c */
    public final String m24324c(@NotNull String str, @NotNull String str2) {
        byte[] m24374c;
        try {
            if (f84b == 0) {
                m24374c = m24329a(str2);
            } else {
                m24374c = C0104e.m24374c(str2);
            }
            SecretKeySpec secretKeySpec = new SecretKeySpec(C0104e.m24374c(str), f85c);
            Cipher cipher = Cipher.getInstance(f87e);
            cipher.init(2, secretKeySpec);
            byte[] result = cipher.doFinal(m24374c);
            if (f84b == 0) {
                Intrinsics.checkExpressionValueIsNotNull(result, "result");
                Charset CHARSET_UTF8 = f86d;
                Intrinsics.checkExpressionValueIsNotNull(CHARSET_UTF8, "CHARSET_UTF8");
                return new String(result, CHARSET_UTF8);
            }
            Intrinsics.checkExpressionValueIsNotNull(result, "result");
            return C0104e.m24368h(result);
        } catch (Exception e) {
            m24328a("decrypt", e);
            return null;
        }
    }

    @Nullable
    /* renamed from: d */
    public final String m24323d(@NotNull String str, @NotNull String str2) {
        try {
            SecretKeySpec secretKeySpec = new SecretKeySpec(C0104e.m24374c(str), f85c);
            Cipher cipher = Cipher.getInstance(f87e);
            cipher.init(1, secretKeySpec);
            byte[] encryptByte = cipher.doFinal(C0104e.m24374c(str2));
            if (f84b == 0) {
                return m24326a(encryptByte);
            }
            Intrinsics.checkExpressionValueIsNotNull(encryptByte, "encryptByte");
            return C0104e.m24368h(encryptByte);
        } catch (Exception e) {
            m24328a("encrypt", e);
            return null;
        }
    }

    @NotNull
    /* renamed from: a */
    public final String m24326a(@Nullable byte[] bArr) {
        String encodeToString = Base64.encodeToString(bArr, 2);
        Intrinsics.checkExpressionValueIsNotNull(encodeToString, "Base64.encodeToString(data, Base64.NO_WRAP)");
        return encodeToString;
    }

    @NotNull
    /* renamed from: a */
    public final byte[] m24329a(@Nullable String str) {
        byte[] decode = Base64.decode(str, 2);
        Intrinsics.checkExpressionValueIsNotNull(decode, "Base64.decode(data, Base64.NO_WRAP)");
        return decode;
    }

    /* renamed from: a */
    public final void m24328a(@NotNull String str, @NotNull Exception exc) {
        String message = exc.getMessage();
        if (message != null) {
            C0103d.m24387b(message);
        }
        exc.printStackTrace();
    }

    @Nullable
    /* renamed from: a */
    public final String m24327a(@NotNull String str, @NotNull String str2) {
        try {
            if (str.length() != 16) {
                C0103d.m24385d("Key长度不是16位");
                return null;
            }
            Charset CHARSET_UTF8 = f86d;
            Intrinsics.checkExpressionValueIsNotNull(CHARSET_UTF8, "CHARSET_UTF8");
            byte[] bytes = str.getBytes(CHARSET_UTF8);
            Intrinsics.checkExpressionValueIsNotNull(bytes, "(this as java.lang.String).getBytes(charset)");
            SecretKeySpec secretKeySpec = new SecretKeySpec(bytes, f85c);
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(2, secretKeySpec, new IvParameterSpec(f88f));
            try {
                byte[] original = cipher.doFinal(m24329a(str2));
                Intrinsics.checkExpressionValueIsNotNull(original, "original");
                return new String(original, Charsets.UTF_8);
            } catch (Exception e) {
                C0103d.m24387b(e.toString());
                return null;
            }
        } catch (Exception e2) {
            C0103d.m24387b(e2.toString());
            return null;
        }
    }
}
