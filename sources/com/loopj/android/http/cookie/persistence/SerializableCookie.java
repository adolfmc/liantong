package com.loopj.android.http.cookie.persistence;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import okhttp3.Cookie;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class SerializableCookie implements Serializable {
    private static long NON_VALID_EXPIRES_AT = -1;
    private static final String TAG = "SerializableCookie";
    private static final long serialVersionUID = -8594045714036645534L;
    private transient Cookie cookie;

    public String encode(Cookie cookie) {
        ObjectOutputStream objectOutputStream;
        this.cookie = cookie;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream2 = null;
        try {
            objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        } catch (IOException unused) {
            objectOutputStream = null;
        } catch (Throwable th) {
            th = th;
        }
        try {
            objectOutputStream.writeObject(this);
            try {
                objectOutputStream.close();
            } catch (IOException unused2) {
            }
            return byteArrayToHexString(byteArrayOutputStream.toByteArray());
        } catch (IOException unused3) {
            if (objectOutputStream != null) {
                try {
                    objectOutputStream.close();
                } catch (IOException unused4) {
                }
            }
            return null;
        } catch (Throwable th2) {
            th = th2;
            objectOutputStream2 = objectOutputStream;
            if (objectOutputStream2 != null) {
                try {
                    objectOutputStream2.close();
                } catch (IOException unused5) {
                }
            }
            throw th;
        }
    }

    private static String byteArrayToHexString(byte[] bArr) {
        StringBuilder sb = new StringBuilder(bArr.length * 2);
        for (byte b : bArr) {
            int i = b & 255;
            if (i < 16) {
                sb.append('0');
            }
            sb.append(Integer.toHexString(i));
        }
        return sb.toString();
    }

    /* JADX WARN: Code restructure failed: missing block: B:15:0x0027, code lost:
        if (r1 == null) goto L11;
     */
    /* JADX WARN: Code restructure failed: missing block: B:18:0x002b, code lost:
        if (r1 == null) goto L11;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public okhttp3.Cookie decode(java.lang.String r3) {
        /*
            r2 = this;
            byte[] r3 = hexStringToByteArray(r3)
            java.io.ByteArrayInputStream r0 = new java.io.ByteArrayInputStream
            r0.<init>(r3)
            r3 = 0
            java.io.ObjectInputStream r1 = new java.io.ObjectInputStream     // Catch: java.lang.Throwable -> L1d java.lang.ClassNotFoundException -> L26 java.io.IOException -> L2a
            r1.<init>(r0)     // Catch: java.lang.Throwable -> L1d java.lang.ClassNotFoundException -> L26 java.io.IOException -> L2a
            java.lang.Object r0 = r1.readObject()     // Catch: java.lang.Throwable -> L1b java.lang.ClassNotFoundException -> L27 java.io.IOException -> L2b
            com.loopj.android.http.cookie.persistence.SerializableCookie r0 = (com.loopj.android.http.cookie.persistence.SerializableCookie) r0     // Catch: java.lang.Throwable -> L1b java.lang.ClassNotFoundException -> L27 java.io.IOException -> L2b
            okhttp3.Cookie r3 = r0.cookie     // Catch: java.lang.Throwable -> L1b java.lang.ClassNotFoundException -> L27 java.io.IOException -> L2b
        L17:
            r1.close()     // Catch: java.io.IOException -> L2e
            goto L2e
        L1b:
            r3 = move-exception
            goto L20
        L1d:
            r0 = move-exception
            r1 = r3
            r3 = r0
        L20:
            if (r1 == 0) goto L25
            r1.close()     // Catch: java.io.IOException -> L25
        L25:
            throw r3
        L26:
            r1 = r3
        L27:
            if (r1 == 0) goto L2e
            goto L17
        L2a:
            r1 = r3
        L2b:
            if (r1 == 0) goto L2e
            goto L17
        L2e:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.loopj.android.http.cookie.persistence.SerializableCookie.decode(java.lang.String):okhttp3.Cookie");
    }

    private static byte[] hexStringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length / 2];
        for (int i = 0; i < length; i += 2) {
            bArr[i / 2] = (byte) ((Character.digit(str.charAt(i), 16) << 4) + Character.digit(str.charAt(i + 1), 16));
        }
        return bArr;
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.writeObject(this.cookie.name());
        objectOutputStream.writeObject(this.cookie.value());
        objectOutputStream.writeLong(this.cookie.persistent() ? this.cookie.expiresAt() : NON_VALID_EXPIRES_AT);
        objectOutputStream.writeObject(this.cookie.domain());
        objectOutputStream.writeObject(this.cookie.path());
        objectOutputStream.writeBoolean(this.cookie.secure());
        objectOutputStream.writeBoolean(this.cookie.httpOnly());
        objectOutputStream.writeBoolean(this.cookie.hostOnly());
    }

    private void readObject(ObjectInputStream objectInputStream) throws IOException, ClassNotFoundException {
        Cookie.Builder builder = new Cookie.Builder();
        builder.name((String) objectInputStream.readObject());
        builder.value((String) objectInputStream.readObject());
        long readLong = objectInputStream.readLong();
        if (readLong != NON_VALID_EXPIRES_AT) {
            builder.expiresAt(readLong);
        }
        String str = (String) objectInputStream.readObject();
        builder.domain(str);
        builder.path((String) objectInputStream.readObject());
        if (objectInputStream.readBoolean()) {
            builder.secure();
        }
        if (objectInputStream.readBoolean()) {
            builder.httpOnly();
        }
        if (objectInputStream.readBoolean()) {
            builder.hostOnlyDomain(str);
        }
        this.cookie = builder.build();
    }
}
