package p001a.p058b.p062b.p063a.p064a.p081k;

import com.baidu.uaq.agent.android.logging.InterfaceC3321a;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;
import p001a.p058b.p062b.p063a.p064a.p072d.C0749a;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: a.b.b.a.a.k.c */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C0771c {

    /* renamed from: b */
    public static final /* synthetic */ boolean f2386b = !C0771c.class.desiredAssertionStatus();

    /* renamed from: a */
    public static final InterfaceC3321a f2385a = C0749a.f2299a;

    /* renamed from: a */
    public static String m22240a(String str) {
        Cipher cipher;
        if (str == null) {
            f2385a.warning("AES warning: content is null");
            return "";
        }
        byte[] bArr = new byte[16];
        for (int i = 0; i < 16; i++) {
            int i2 = i * 2;
            bArr[i] = Integer.valueOf("587a0fb0c91ae061e66adbf2ec56f0b8".substring(i2, i2 + 2), 16).byteValue();
        }
        byte[] bytes = str.getBytes();
        try {
            cipher = Cipher.getInstance(C0108a.f85c);
            cipher.init(1, new SecretKeySpec(bArr, C0108a.f85c));
        } catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException e) {
            e.printStackTrace();
            cipher = null;
        }
        if (f2386b || cipher != null) {
            byte[] doFinal = cipher.doFinal(bytes);
            if (doFinal == null) {
                return "";
            }
            StringBuffer stringBuffer = new StringBuffer(doFinal.length * 2);
            for (byte b : doFinal) {
                stringBuffer.append("0123456789ABCDEF".charAt((b >> 4) & 15));
                stringBuffer.append("0123456789ABCDEF".charAt(b & 15));
            }
            return stringBuffer.toString();
        }
        throw new AssertionError();
    }
}
