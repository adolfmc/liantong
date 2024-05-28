package com.fido.android.framework.p197tm.core.prov;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Base64;
import com.fido.android.framework.p197tm.core.inf.ICryptoModule;
import com.gmrz.android.client.asm.api.AsmError;
import com.gmrz.android.client.asm.api.AsmException;
import com.gmrz.android.client.utils.Charsets;
import com.gmrz.android.client.utils.Logger;
import com.utils.MetaDataUtils;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import p001a.p002a.p003a.p004a.p005a.p008d.C0108a;

/* renamed from: com.fido.android.framework.tm.core.prov.CryptoModule */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class CryptoModule implements ICryptoModule {
    private static final String TAG = "CryptoModule";
    private final Context mContext;
    private final String mFilePath;
    private byte[] mSecertKey = null;

    public CryptoModule(String str, Context context) {
        this.mFilePath = str;
        this.mContext = context;
        String str2 = TAG;
        Logger.m15889i(str2, ":::: mContext ====" + this.mContext);
        String str3 = TAG;
        Logger.m15889i(str3, ":::: mFilePath ====" + this.mFilePath);
    }

    private static byte[] hmacSha256(byte[] bArr, byte[] bArr2) throws AsmException {
        Logger.m15889i(TAG, "hmacSha256");
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(new SecretKeySpec(bArr, mac.getAlgorithm()));
            return mac.doFinal(bArr2);
        } catch (InvalidKeyException | NoSuchAlgorithmException e) {
            throw new AsmException(AsmError.FAILURE, e.getMessage());
        }
    }

    @Override // com.fido.android.framework.p197tm.core.inf.ICryptoModule
    public String bytesToHexString(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            String hexString = Integer.toHexString(b & 255);
            if (hexString.length() == 1) {
                sb.append("0");
            }
            sb.append(hexString);
        }
        return sb.toString();
    }

    public static ByteArrayOutputStream bytesToStream(byte[] bArr, ByteArrayOutputStream byteArrayOutputStream) {
        if (bArr == null) {
            return byteArrayOutputStream;
        }
        if (byteArrayOutputStream == null) {
            byteArrayOutputStream = new ByteArrayOutputStream();
        }
        int length = bArr.length;
        for (int i : bArr) {
            byteArrayOutputStream.write(i);
        }
        return byteArrayOutputStream;
    }

    @SuppressLint({"NewApi"})
    private ArrayList<byte[]> getAndroidId(byte[] bArr) throws IOException {
        char[] cArr = new char[26];
        for (int i = 0; i < 26; i++) {
            cArr[i] = (char) (((i * i) % 26) + 97);
        }
        ArrayList<byte[]> arrayList = new ArrayList<>();
        arrayList.add(getKey());
        arrayList.add(("android_id" + new String(cArr)).getBytes(Charsets.utf8Charset));
        arrayList.add(bArr);
        return arrayList;
    }

    private byte[] digestList(ArrayList<byte[]> arrayList) throws NoSuchAlgorithmException {
        MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
        if (MetaDataUtils.getHashSaltType(this.mContext) == MetaDataUtils.HASH_SALT_TYPE.FULL) {
            Logger.m15883w(TAG, "** CryptoModule digestList method calculate with full salt");
            StringBuilder sb = new StringBuilder();
            Iterator<byte[]> it = arrayList.iterator();
            while (it.hasNext()) {
                byte[] next = it.next();
                if (next != null && next.length != 0) {
                    sb.append(byteArr2Base64Str(next));
                }
            }
            messageDigest.update((((Object) sb) + byteArr2Base64Str(this.mSecertKey)).getBytes(Charsets.utf8Charset));
        } else {
            Iterator<byte[]> it2 = arrayList.iterator();
            while (it2.hasNext()) {
                byte[] next2 = it2.next();
                if (next2 != null) {
                    messageDigest.update(next2);
                }
            }
        }
        return messageDigest.digest();
    }

    private String byteArr2Base64Str(byte[] bArr) {
        return Base64.encodeToString(bArr, 25);
    }

    @Override // com.fido.android.framework.p197tm.core.inf.ICryptoModule
    @SuppressLint({"NewApi"})
    public byte[] encryptData(byte[] bArr, byte[] bArr2) throws AsmException {
        if (bArr == null) {
            throw new AsmException(AsmError.FAILURE, "encryptData: Invalid argument");
        }
        try {
            ArrayList<byte[]> androidId = getAndroidId(bArr2);
            androidId.add("enc".getBytes(Charsets.utf8Charset));
            byte[] digestList = digestList(androidId);
            androidId.add("hmac".getBytes(Charsets.utf8Charset));
            byte[] digestList2 = digestList(androidId);
            byte[] makeRandom = makeRandom();
            byte[] doCipher = doCipher(bArr, digestList, 1, makeRandom);
            return bytesToStream(doCipher, bytesToStream(makeRandom, bytesToStream(hmacSha256(digestList2, doCipher), null))).toByteArray();
        } catch (IOException unused) {
            throw new AsmException(AsmError.FAILURE, "Failed to get DB Key.");
        } catch (Exception e) {
            throw new AsmException(AsmError.FAILURE, "Failed to get DB Key", e);
        }
    }

    @Override // com.fido.android.framework.p197tm.core.inf.ICryptoModule
    @SuppressLint({"NewApi"})
    public byte[] decryptData(byte[] bArr, byte[] bArr2) throws AsmException {
        if (bArr == null || bArr.length <= 48) {
            throw new AsmException(AsmError.FAILURE, "decryptData: Invalid argument");
        }
        try {
            ArrayList<byte[]> androidId = getAndroidId(bArr2);
            androidId.add("enc".getBytes(Charsets.utf8Charset));
            byte[] digestList = digestList(androidId);
            androidId.add("hmac".getBytes(Charsets.utf8Charset));
            byte[] digestList2 = digestList(androidId);
            byte[] cutArray = cutArray(bArr, 0, 32);
            byte[] cutArray2 = cutArray(bArr, 32, 16);
            byte[] cutArray3 = cutArray(bArr, 48, (bArr.length - 32) - 16);
            if (!Arrays.equals(cutArray, hmacSha256(digestList2, cutArray3))) {
                throw new AsmException(AsmError.FAILURE, "Corrupted ciphertext");
            }
            return doCipher(cutArray3, digestList, 2, cutArray2);
        } catch (IOException unused) {
            throw new AsmException(AsmError.FAILURE, "encryptData. Failed to get DB Key");
        } catch (Exception e) {
            throw new AsmException(AsmError.FAILURE, "Failed to process HMAC key.", e);
        }
    }

    private static byte[] cutArray(byte[] bArr, int i, int i2) {
        byte[] bArr2 = new byte[i2];
        System.arraycopy(bArr, i, bArr2, 0, i2);
        return bArr2;
    }

    private static byte[] makeRandom() {
        byte[] bArr = new byte[16];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    private static byte[] doCipher(byte[] bArr, byte[] bArr2, int i, byte[] bArr3) throws Exception {
        if (bArr2 == null || bArr == null || bArr3 == null) {
            throw new Exception("Invalid argument");
        }
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(i, new SecretKeySpec(bArr2, 0, bArr2.length, C0108a.f85c), new IvParameterSpec(bArr3));
        return cipher.doFinal(bArr);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r7v1 */
    /* JADX WARN: Type inference failed for: r7v2, types: [java.io.FileInputStream] */
    /* JADX WARN: Type inference failed for: r7v5 */
    /* JADX WARN: Type inference failed for: r7v7 */
    /* JADX WARN: Type inference failed for: r7v8, types: [java.io.FileInputStream] */
    public byte[] createDatFilesWithKeys(String str) throws IOException {
        ?? r7;
        Logger.m15889i(TAG, "TMCore :: createDatFilesWithKeys");
        FileOutputStream fileOutputStream = null;
        try {
            File file = new File(this.mContext.getFilesDir(), str);
            if (file.exists()) {
                r7 = new FileInputStream(file);
                try {
                    byte[] bArr = new byte[(int) file.length()];
                    r7.read(bArr);
                    byte[] decode = Base64.decode(bArr, 2);
                    try {
                        r7.close();
                    } catch (IOException unused) {
                        Logger.m15892e(TAG, "Failed to close input stream.");
                    }
                    try {
                        r7.close();
                    } catch (IOException unused2) {
                        Logger.m15892e(TAG, "Failed to close input stream.");
                    }
                    return decode;
                } catch (Throwable th) {
                    th = th;
                }
            } else {
                byte[] randomKey = getRandomKey();
                FileOutputStream openFileOutput = this.mContext.openFileOutput(str, 32768);
                try {
                    openFileOutput.write(Base64.encode(randomKey, 2));
                    try {
                        openFileOutput.close();
                    } catch (IOException unused3) {
                        Logger.m15892e(TAG, "Failed to close output stream.");
                    }
                    if (openFileOutput != null) {
                        try {
                            openFileOutput.close();
                        } catch (IOException unused4) {
                            Logger.m15892e(TAG, "Failed to close output stream.");
                        }
                    }
                    return randomKey;
                } catch (Throwable th2) {
                    th = th2;
                    fileOutputStream = openFileOutput;
                    r7 = 0;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            r7 = fileOutputStream;
        }
        if (fileOutputStream != null) {
            try {
                fileOutputStream.close();
            } catch (IOException unused5) {
                Logger.m15892e(TAG, "Failed to close output stream.");
            }
        }
        if (r7 != 0) {
            try {
                r7.close();
            } catch (IOException unused6) {
                Logger.m15892e(TAG, "Failed to close input stream.");
            }
        }
        throw th;
    }

    public byte[] getRandomKey() {
        Logger.m15889i(TAG, "TMCore :: getRandomKey");
        byte[] bArr = new byte[100];
        new SecureRandom().nextBytes(bArr);
        return bArr;
    }

    public byte[] getKey() throws IOException {
        Logger.m15889i(TAG, "CryptoModule :: getKey");
        byte[] bArr = this.mSecertKey;
        if (bArr == null) {
            String str = this.mFilePath;
            if (str != null && str.contains(File.separator)) {
                str = this.mFilePath.substring(this.mFilePath.lastIndexOf(File.separator) + 1);
            }
            this.mSecertKey = createDatFilesWithKeys(str + ".dat");
            return this.mSecertKey;
        }
        return bArr;
    }

    public static byte[] sha256(byte[] bArr) throws AsmException {
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("SHA-256");
            messageDigest.update(bArr);
            return messageDigest.digest();
        } catch (NoSuchAlgorithmException e) {
            throw new AsmException(AsmError.FAILURE, "No such algorithm: SHA-256", e);
        }
    }
}
