package p001a.p002a.p003a.p004a.p005a.p008d.p009f;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import p001a.p002a.p003a.p004a.p005a.p007c.C0103d;
import p001a.p002a.p003a.p004a.p005a.p008d.C0110c;
import p001a.p002a.p003a.p004a.p005a.p008d.C0112e;
import p001a.p002a.p003a.p004a.p005a.p008d.p009f.p011e.C0117a;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.C0502b;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0595t;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0596u;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p047d.p048a.AbstractC0655f;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.a.a.a.a.d.f.c */
/* loaded from: E:\567196_dexfile_execute.dex */
public class C0115c {
    /* renamed from: a */
    public static HashMap<String, String> m24258a() {
        C0502b mo22888a = C0114b.m24262a().f116k.mo22888a();
        BigInteger m22758c = ((C0595t) mo22888a.m22974a()).m22758c();
        AbstractC0655f m22757c = ((C0596u) mo22888a.m22973b()).m22757c();
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("prik", C0112e.m24285b(m22758c.toByteArray()));
        hashMap.put("pubk", C0112e.m24285b(m22757c.m22579c()));
        return hashMap;
    }

    /* renamed from: b */
    public static String m24253b(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        byte[] bArr3 = new byte[bArr2.length];
        System.arraycopy(bArr2, 0, bArr3, 0, bArr2.length);
        C0113a c0113a = new C0113a();
        C0114b m24262a = C0114b.m24262a();
        AbstractC0655f m24268a = c0113a.m24268a(m24262a, m24262a.f112g.m22621a(bArr));
        c0113a.m24263c(bArr3);
        byte[] bArr4 = new byte[32];
        c0113a.m24264b(bArr4);
        return C0112e.m24285b(m24268a.m22579c()) + C0112e.m24285b(bArr3) + C0112e.m24285b(bArr4);
    }

    /* renamed from: a */
    public static byte[] m24255a(byte[] bArr, byte[] bArr2) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return null;
        }
        String m24285b = C0112e.m24285b(bArr2);
        byte[] m24272g = C0112e.m24272g(m24285b.substring(0, 130));
        int length = (bArr2.length - 97) * 2;
        int i = length + 130;
        byte[] m24272g2 = C0112e.m24272g(m24285b.substring(130, i));
        byte[] m24272g3 = C0112e.m24272g(m24285b.substring(i, length + 194));
        C0114b m24262a = C0114b.m24262a();
        BigInteger bigInteger = new BigInteger(1, bArr);
        AbstractC0655f m22621a = m24262a.f112g.m22621a(m24272g);
        C0113a c0113a = new C0113a();
        c0113a.m24267a(bigInteger, m22621a);
        c0113a.m24266a(m24272g2);
        c0113a.m24264b(m24272g3);
        return m24272g2;
    }

    /* renamed from: a */
    public static String m24254a(byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr2 == null || bArr2.length == 0 || bArr3 == null || bArr3.length == 0) {
            return null;
        }
        C0114b m24262a = C0114b.m24262a();
        BigInteger bigInteger = new BigInteger(C0112e.m24271g(bArr2), 16);
        AbstractC0655f m22581a = m24262a.f113h.m22581a(bigInteger);
        m24262a.m24261a(bArr, m22581a);
        byte[] m24309b = C0110c.f95a.m24309b(C0112e.m24271g(bArr3));
        C0117a c0117a = new C0117a();
        m24262a.m24259a(m24309b, bigInteger, m22581a, c0117a);
        String bigInteger2 = c0117a.f121a.toString(16);
        String bigInteger3 = c0117a.f122b.toString(16);
        C0103d.m24388a("R:" + bigInteger2 + " ---size:" + bigInteger2.length());
        C0103d.m24388a("S:" + bigInteger3 + " ---size:" + bigInteger3.length());
        String m24257a = m24257a(bigInteger2);
        String m24257a2 = m24257a(bigInteger3);
        return m24257a + m24257a2;
    }

    /* renamed from: a */
    public static String m24257a(String str) {
        if (str.length() == 64) {
            return str;
        }
        int length = 64 - str.length();
        for (int i = 1; i <= length; i++) {
            str = "0" + str;
        }
        return str;
    }

    /* renamed from: a */
    public static boolean m24256a(String str, byte[] bArr, byte[] bArr2, byte[] bArr3) {
        if (bArr == null || bArr.length == 0 || bArr2 == null || bArr2.length == 0) {
            return false;
        }
        C0114b m24262a = C0114b.m24262a();
        AbstractC0655f m22621a = m24262a.f112g.m22621a(bArr);
        m24262a.m24261a(str.getBytes(StandardCharsets.US_ASCII), m22621a);
        byte[] m24309b = C0110c.f95a.m24309b(C0112e.m24271g(bArr2));
        byte[] bArr4 = new byte[32];
        System.arraycopy(bArr3, 0, bArr4, 0, 32);
        byte[] bArr5 = new byte[32];
        System.arraycopy(bArr3, 32, bArr5, 0, 32);
        C0117a c0117a = new C0117a();
        c0117a.f121a = C0112e.m24293a(bArr4);
        BigInteger m24293a = C0112e.m24293a(bArr5);
        c0117a.f122b = m24293a;
        m24262a.m24260a(m24309b, m22621a, c0117a.f121a, m24293a, c0117a);
        return c0117a.f121a.equals(c0117a.f123c);
    }
}
