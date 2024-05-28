package cn.microdone.txcrypto;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.microdone.txcrypto.txcrypto */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class C1731txcrypto {
    private long mObj;

    static {
        System.loadLibrary("PassGuard");
    }

    public C1731txcrypto() {
        this.mObj = -1L;
        this.mObj = CreateMObj();
    }

    private native long CreateMObj();

    public native String Decrypt(String str);

    public native String DecryptLite(String str);

    public native byte[] DecryptLite2Byte(String str);

    public native void DestoryMObj();

    public native String Encrypt(String str);

    public native String EncryptLite(String str);

    public native String EncryptLiteByByte(byte[] bArr);

    public native String GenClientRandom();

    public native String GetClientRandom();

    public native String GetN();

    public native int SetClientRandom(String str);

    public native int SetGBKEnCoding(int i);

    public native int SetKeyInfo(String str);

    public native int SetLicense(String str, Object obj);

    public native int SetServerRandom(String str);

    public void finalize() {
        DestoryMObj();
        super.finalize();
    }

    public native String microdoneGenSM4Key();

    public native String microdoneSM2Decrypt(String str, String str2, int i, int i2, int i3);

    public native String microdoneSM2Encrypt(String str, String str2, String str3, int i, int i2, int i3, int i4);

    public native String microdoneSM2Sign(String str, String str2, int i);

    public native int microdoneSM2Verify(String str, String str2, String str3, String str4, int i);

    public native String microdoneSM3(String str, int i);

    public native String microdoneSM3HMAC(String str, String str2, int i);

    public native String microdoneSM4Decrypt(String str, String str2, String str3, int i, int i2, int i3);

    public native String microdoneSM4Encrypt(String str, String str2, String str3, int i, int i2, int i3);
}
