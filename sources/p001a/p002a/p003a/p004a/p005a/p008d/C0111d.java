package p001a.p002a.p003a.p004a.p005a.p008d;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.Charsets;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import p001a.p002a.p003a.p004a.p005a.p007c.C0103d;
import p001a.p002a.p003a.p004a.p005a.p007c.C0104e;

/* renamed from: a.a.a.a.a.d.d */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public final class C0111d {

    /* renamed from: a */
    public static final C0111d f96a = new C0111d();

    @Nullable
    /* renamed from: a */
    public final String m24306a(@NotNull String str) {
        return m24305a(str, "SHA-256");
    }

    @Nullable
    /* renamed from: b */
    public final String m24304b(@NotNull String str) {
        return m24305a(str, "SHA-512");
    }

    @NotNull
    /* renamed from: c */
    public final String m24303c(@NotNull String str) {
        byte[] m24374c = C0104e.m24374c(str);
        byte[] copyOfRange = ArraysKt.copyOfRange(m24374c, 0, 16);
        byte[] copyOfRange2 = ArraysKt.copyOfRange(m24374c, 16, 32);
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            bArr[i] = (byte) (copyOfRange[i] ^ copyOfRange2[i]);
        }
        return C0104e.m24368h(bArr);
    }

    /* renamed from: a */
    private final String m24305a(String str, String str2) {
        if (str != null && str.length() > 0) {
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
                String message = e.getMessage();
                if (message != null) {
                    C0103d.m24387b(message);
                }
                e.printStackTrace();
            }
        }
        return null;
    }
}
