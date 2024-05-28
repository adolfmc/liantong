package com.networkbench.agent.impl.plugin.p275f.p276a.p277a;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.IDN;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.plugin.f.a.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public final class C6574b {

    /* renamed from: a */
    public static EnumC6575a f16824a = EnumC6575a.NO_ERROR;

    /* renamed from: b */
    private static Map<Integer, String> f16825b = new HashMap();

    /* renamed from: a */
    public static byte[] m9339a(String str, int i) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(512);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        try {
            dataOutputStream.writeShort((short) i);
            dataOutputStream.writeShort((short) 256);
            dataOutputStream.writeShort(1);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.writeShort(0);
            dataOutputStream.flush();
            m9334b(byteArrayOutputStream, str);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            throw new AssertionError(e);
        }
    }

    /* renamed from: a */
    private static void m9340a(OutputStream outputStream, String str) throws IOException {
        for (String str2 : str.split("[.。．｡]")) {
            byte[] bytes = IDN.toASCII(str2).getBytes();
            outputStream.write(bytes.length);
            outputStream.write(bytes, 0, bytes.length);
        }
        outputStream.write(0);
    }

    /* renamed from: b */
    private static void m9334b(OutputStream outputStream, String str) throws IOException {
        DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
        m9340a(outputStream, str);
        dataOutputStream.writeShort(1);
        dataOutputStream.writeShort(1);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.networkbench.agent.impl.plugin.f.a.a.b$a */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum EnumC6575a {
        NO_ERROR(0),
        FORMAT_ERR(1),
        SERVER_FAIL(2),
        NX_DOMAIN(3),
        NO_IMP(4),
        REFUSED(5),
        YXDOMAIN(6),
        YXRRSET(7),
        NXRRSET(8),
        NOT_AUTH(9),
        NOT_ZONE(10),
        BADVERS_BADSIG(16),
        BADKEY(17),
        BADTIME(18),
        BADMODE(19),
        BADNAME(20),
        BADALG(21),
        BADTRUNC(22),
        BADCOOKIE(23);
        

        /* renamed from: t */
        private static final Map<Integer, EnumC6575a> f16845t = new HashMap(values().length);

        /* renamed from: u */
        private final byte f16847u;

        static {
            EnumC6575a[] values;
            for (EnumC6575a enumC6575a : values()) {
                f16845t.put(Integer.valueOf(enumC6575a.f16847u), enumC6575a);
            }
        }

        EnumC6575a(int i) {
            this.f16847u = (byte) i;
        }

        /* renamed from: a */
        public byte m9333a() {
            return this.f16847u;
        }

        /* renamed from: a */
        public static EnumC6575a m9332a(int i) throws IllegalArgumentException {
            if (i < 0 || i > 65535) {
                throw new IllegalArgumentException();
            }
            return f16845t.get(Integer.valueOf(i));
        }
    }

    static {
        f16825b.put(1, "Format Error");
        f16825b.put(2, "Server Failure");
        f16825b.put(3, "Non-Existent Domain");
        f16825b.put(4, "Not Implemented");
        f16825b.put(5, "Query Refused");
        f16825b.put(6, "Name Exists when it should not");
        f16825b.put(7, "RR Set Exists when it should not");
        f16825b.put(8, "RR Set that should exist does not");
        f16825b.put(9, "Not Authorized");
        f16825b.put(10, "Name not contained in zone");
        f16825b.put(11, "Bad OPT Version");
        f16825b.put(16, "Bad OPT Version or TSIG Signature Failure");
        f16825b.put(17, "Key not recognized");
        f16825b.put(18, "Signature out of time window");
        f16825b.put(19, "Bad TKEY Mode");
        f16825b.put(20, "Duplicate key name");
        f16825b.put(21, "Algorithm not supported");
        f16825b.put(22, "Bad Truncation");
        f16825b.put(23, "Bad cookie");
    }

    /* renamed from: a */
    public static String m9343a(int i) {
        return f16825b.get(Integer.valueOf(i)) == null ? "" : f16825b.get(Integer.valueOf(i));
    }

    /* renamed from: a */
    public static C6576c[] m9338a(byte[] bArr, int i, String str) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr));
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        if (readUnsignedShort != i) {
            throw new C6573a(str, "the answer id " + readUnsignedShort + " is not match " + i);
        }
        int readUnsignedShort2 = dataInputStream.readUnsignedShort();
        boolean z = ((readUnsignedShort2 >> 8) & 1) == 1;
        if (!(((readUnsignedShort2 >> 7) & 1) == 1) || !z) {
            throw new C6573a(str, "the dns server cant support recursion ");
        }
        f16824a = EnumC6575a.m9332a(readUnsignedShort2 & 15);
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        int readUnsignedShort4 = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        m9341a(dataInputStream, bArr, readUnsignedShort3);
        return m9335b(dataInputStream, bArr, readUnsignedShort4);
    }

    /* renamed from: a */
    private static String m9342a(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        int readUnsignedByte = dataInputStream.readUnsignedByte();
        if ((readUnsignedByte & 192) == 192) {
            int readUnsignedByte2 = ((readUnsignedByte & 63) << 8) + dataInputStream.readUnsignedByte();
            HashSet hashSet = new HashSet();
            hashSet.add(Integer.valueOf(readUnsignedByte2));
            return m9337a(bArr, readUnsignedByte2, hashSet);
        } else if (readUnsignedByte == 0) {
            return "";
        } else {
            byte[] bArr2 = new byte[readUnsignedByte];
            dataInputStream.readFully(bArr2);
            String unicode = IDN.toUnicode(new String(bArr2));
            String m9342a = m9342a(dataInputStream, bArr);
            if (m9342a.length() > 0) {
                return unicode + "." + m9342a;
            }
            return unicode;
        }
    }

    /* renamed from: a */
    private static String m9337a(byte[] bArr, int i, HashSet<Integer> hashSet) throws IOException {
        int i2 = bArr[i] & 255;
        if ((i2 & 192) == 192) {
            int i3 = ((i2 & 63) << 8) + (bArr[i + 1] & 255);
            if (hashSet.contains(Integer.valueOf(i3))) {
                throw new C6573a("", "Cyclic offsets detected.");
            }
            hashSet.add(Integer.valueOf(i3));
            return m9337a(bArr, i3, hashSet);
        } else if (i2 == 0) {
            return "";
        } else {
            int i4 = i + 1;
            String str = new String(bArr, i4, i2);
            String m9337a = m9337a(bArr, i4 + i2, hashSet);
            if (m9337a.length() > 0) {
                return str + "." + m9337a;
            }
            return str;
        }
    }

    /* renamed from: a */
    private static void m9341a(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        while (true) {
            int i2 = i - 1;
            if (i <= 0) {
                return;
            }
            m9342a(dataInputStream, bArr);
            dataInputStream.readUnsignedShort();
            dataInputStream.readUnsignedShort();
            i = i2;
        }
    }

    /* renamed from: b */
    private static C6576c[] m9335b(DataInputStream dataInputStream, byte[] bArr, int i) throws IOException {
        C6576c[] c6576cArr = new C6576c[i];
        int i2 = 0;
        while (true) {
            int i3 = i - 1;
            if (i <= 0) {
                return c6576cArr;
            }
            c6576cArr[i2] = m9336b(dataInputStream, bArr);
            i2++;
            i = i3;
        }
    }

    /* renamed from: b */
    private static C6576c m9336b(DataInputStream dataInputStream, byte[] bArr) throws IOException {
        String hostAddress;
        m9342a(dataInputStream, bArr);
        int readUnsignedShort = dataInputStream.readUnsignedShort();
        dataInputStream.readUnsignedShort();
        long readUnsignedShort2 = (dataInputStream.readUnsignedShort() << 16) + dataInputStream.readUnsignedShort();
        int readUnsignedShort3 = dataInputStream.readUnsignedShort();
        if (readUnsignedShort == 1) {
            byte[] bArr2 = new byte[4];
            dataInputStream.readFully(bArr2);
            hostAddress = InetAddress.getByAddress(bArr2).getHostAddress();
        } else if (readUnsignedShort != 5) {
            for (int i = 0; i < readUnsignedShort3; i++) {
                dataInputStream.readByte();
            }
            hostAddress = null;
        } else {
            hostAddress = m9342a(dataInputStream, bArr);
        }
        if (hostAddress == null) {
            throw new UnknownHostException("no record");
        }
        return new C6576c(hostAddress, readUnsignedShort, (int) readUnsignedShort2, System.currentTimeMillis() / 1000);
    }
}
