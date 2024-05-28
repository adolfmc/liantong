package com.mob.commons.p231cc;

import android.text.TextUtils;
import com.mob.commons.C5868q;
import com.mob.tools.MobLog;
import com.mob.tools.log.NLog;
import com.mob.tools.utils.Data;
import com.sdk.p285a.C6960d;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.mob.commons.cc.o */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5809o implements InterfaceC5812q<C5809o> {
    /* renamed from: a */
    public static void m12423a(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* renamed from: a */
    public String m12426a(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        try {
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bArr, 0, bArr.length);
            String m12429a = m12429a(byteArrayInputStream);
            byteArrayInputStream.close();
            return m12429a;
        } catch (Throwable unused) {
            return null;
        }
    }

    /* renamed from: a */
    public String m12429a(InputStream inputStream) {
        byte[] bArr = null;
        if (inputStream == null) {
            return null;
        }
        try {
            byte[] bArr2 = new byte[1024];
            MessageDigest messageDigest = MessageDigest.getInstance(C5868q.m12203b("003,gbejgh"));
            int read = inputStream.read(bArr2);
            while (read != -1) {
                messageDigest.update(bArr2, 0, read);
                read = inputStream.read(bArr2);
            }
            bArr = messageDigest.digest();
        } catch (Throwable unused) {
        }
        return m12421b(bArr);
    }

    /* renamed from: b */
    public String m12421b(byte[] bArr) {
        if (bArr == null) {
            return null;
        }
        int length = bArr.length;
        StringBuffer stringBuffer = new StringBuffer();
        for (int i = 0; i < length; i++) {
            stringBuffer.append(String.format("%02x", Byte.valueOf(bArr[i])));
        }
        return stringBuffer.toString();
    }

    /* renamed from: a */
    public ArrayList<HashMap<String, String>> m12427a(ArrayList<HashMap<String, String>> arrayList, ArrayList<HashMap<String, String>> arrayList2, String str) {
        ArrayList<HashMap<String, String>> arrayList3 = new ArrayList<>();
        Iterator<HashMap<String, String>> it = arrayList.iterator();
        while (it.hasNext()) {
            HashMap<String, String> next = it.next();
            String str2 = next.get(str);
            if (!TextUtils.isEmpty(str2)) {
                boolean z = false;
                Iterator<HashMap<String, String>> it2 = arrayList2.iterator();
                while (true) {
                    if (!it2.hasNext()) {
                        break;
                    } else if (str2.equals(it2.next().get(str))) {
                        z = true;
                        break;
                    }
                }
                if (!z) {
                    arrayList3.add(next);
                }
            }
        }
        return arrayList3;
    }

    /* renamed from: a */
    public byte[] m12428a(String str, String str2, byte[] bArr, byte[] bArr2) throws Throwable {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, C5868q.m12203b("0030dkffdi"));
        Cipher cipher = Data.getCipher(str, str2);
        cipher.init(1, secretKeySpec);
        byte[] bArr4 = new byte[cipher.getOutputSize(bArr2.length)];
        cipher.doFinal(bArr4, cipher.update(bArr2, 0, bArr2.length, bArr4, 0));
        return bArr4;
    }

    /* renamed from: b */
    public byte[] m12422b(String str, String str2, byte[] bArr, byte[] bArr2) throws Throwable {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, C5868q.m12203b("003Kdkffdi"));
        Cipher cipher = Data.getCipher(str, str2);
        cipher.init(2, secretKeySpec);
        byte[] bArr4 = new byte[cipher.getOutputSize(bArr2.length)];
        cipher.doFinal(bArr4, cipher.update(bArr2, 0, bArr2.length, bArr4, 0));
        return bArr4;
    }

    /* renamed from: c */
    public byte[] m12420c(String str, String str2, byte[] bArr, byte[] bArr2) throws Throwable {
        if (bArr == null || bArr2 == null) {
            return null;
        }
        byte[] bArr3 = new byte[16];
        System.arraycopy(bArr, 0, bArr3, 0, Math.min(bArr.length, 16));
        SecretKeySpec secretKeySpec = new SecretKeySpec(bArr3, C5868q.m12203b("003Fdkffdi"));
        Cipher cipher = Data.getCipher(str, str2);
        cipher.init(2, secretKeySpec);
        return cipher.doFinal(bArr2);
    }

    /* renamed from: a */
    public byte[] m12431a(int i, byte[] bArr, BigInteger bigInteger, BigInteger bigInteger2) throws Throwable {
        DataOutputStream dataOutputStream;
        int i2 = i / 8;
        int i3 = i2 - 11;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            dataOutputStream = new DataOutputStream(byteArrayOutputStream);
            int i4 = 0;
            while (bArr.length > i4) {
                try {
                    int min = Math.min(bArr.length - i4, i3);
                    byte[] m12424a = m12424a(bArr, i4, min, bigInteger, bigInteger2, i2);
                    dataOutputStream.writeInt(m12424a.length);
                    dataOutputStream.write(m12424a);
                    i4 += min;
                } catch (Throwable th) {
                    th = th;
                    m12423a(dataOutputStream);
                    throw th;
                }
            }
            byte[] byteArray = byteArrayOutputStream.toByteArray();
            m12423a(dataOutputStream);
            return byteArray;
        } catch (Throwable th2) {
            th = th2;
            dataOutputStream = null;
        }
    }

    /* renamed from: a */
    private byte[] m12424a(byte[] bArr, int i, int i2, BigInteger bigInteger, BigInteger bigInteger2, int i3) throws Throwable {
        if (bArr.length != i2 || i != 0) {
            byte[] bArr2 = new byte[i2];
            System.arraycopy(bArr, i, bArr2, 0, i2);
            bArr = bArr2;
        }
        BigInteger bigInteger3 = new BigInteger(m12425a(bArr, i3));
        if (bigInteger3.compareTo(bigInteger2) > 0) {
            throw new Throwable("the message must be smaller than the modulue");
        }
        return bigInteger3.modPow(bigInteger, bigInteger2).toByteArray();
    }

    /* renamed from: a */
    private byte[] m12425a(byte[] bArr, int i) throws Throwable {
        if (bArr.length > i - 1) {
            throw new Throwable("Message too large");
        }
        byte[] bArr2 = new byte[i];
        bArr2[0] = 1;
        int length = bArr.length;
        bArr2[1] = (byte) (length >> 24);
        bArr2[2] = (byte) (length >> 16);
        bArr2[3] = (byte) (length >> 8);
        bArr2[4] = (byte) length;
        System.arraycopy(bArr, 0, bArr2, i - length, length);
        return bArr2;
    }

    @Override // com.mob.commons.p231cc.InterfaceC5812q
    /* renamed from: a  reason: avoid collision after fix types in other method */
    public boolean mo12341a(C5809o c5809o, Class<C5809o> cls, String str, Object[] objArr, boolean[] zArr, Object[] objArr2, Throwable[] thArr) {
        if ("bm5".equals(str) && objArr.length == 1) {
            objArr2[0] = c5809o.m12426a((byte[]) objArr[0]);
            return true;
        } else if ("sm5".equals(str)) {
            objArr2[0] = c5809o.m12429a((InputStream) objArr[0]);
            return true;
        } else if ("thx".equals(str)) {
            objArr2[0] = c5809o.m12421b((byte[]) objArr[0]);
            return true;
        } else if ("fnil".equals(str) && objArr.length == 3) {
            objArr2[0] = c5809o.m12427a((ArrayList) objArr[0], (ArrayList) objArr[1], (String) objArr[2]);
            return true;
        } else if ("aesen".equals(str) && objArr.length == 4) {
            try {
                objArr2[0] = m12428a((String) objArr[0], (String) objArr[1], (byte[]) objArr[2], (byte[]) objArr[3]);
            } catch (Throwable th) {
                objArr2[0] = null;
                thArr[0] = th;
            }
            return true;
        } else if (C5868q.m12203b("005ceWegcbVe").equals(str) && objArr.length == 4) {
            try {
                objArr2[0] = m12422b((String) objArr[0], (String) objArr[1], (byte[]) objArr[2], (byte[]) objArr[3]);
            } catch (Throwable th2) {
                objArr2[0] = null;
                thArr[0] = th2;
            }
            return true;
        } else if (C5868q.m12203b("006ceVegcbee7i").equals(str) && objArr.length == 4) {
            try {
                objArr2[0] = m12420c((String) objArr[0], (String) objArr[1], (byte[]) objArr[2], (byte[]) objArr[3]);
            } catch (Throwable th3) {
                objArr2[0] = null;
                thArr[0] = th3;
            }
            return true;
        } else if ("enc".equals(str) && objArr.length == 4) {
            try {
                objArr2[0] = m12431a(((Integer) objArr[0]).intValue(), (byte[]) objArr[1], (BigInteger) objArr[2], (BigInteger) objArr[3]);
            } catch (Throwable th4) {
                objArr2[0] = null;
                thArr[0] = th4;
            }
            return true;
        } else if (C6960d.f18019d.equals(str)) {
            if (objArr.length == 1 && (objArr[0] instanceof String)) {
                NLog mobLog = MobLog.getInstance();
                mobLog.m11342d("%s", "[sasa] " + objArr[0]);
            } else if (objArr.length == 1 && (objArr[0] instanceof Throwable)) {
                MobLog.getInstance().m11340d((Throwable) objArr[0], "%s", "[sasa]");
            }
            return true;
        } else {
            return false;
        }
    }
}
