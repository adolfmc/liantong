package io.socket.engineio.parser;

import io.socket.utf8.UTF8;
import io.socket.utf8.UTF8Exception;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class Parser {
    public static final int PROTOCOL = 3;
    private static Packet<String> err;
    private static UTF8.Options utf8Options;
    private static final int MAX_INT_CHAR_LENGTH = String.valueOf(Integer.MAX_VALUE).length();
    private static final Map<String, Integer> packets = new HashMap<String, Integer>() { // from class: io.socket.engineio.parser.Parser.1
        {
            put("open", 0);
            put("close", 1);
            put("ping", 2);
            put("pong", 3);
            put("message", 4);
            put("upgrade", 5);
            put("noop", 6);
        }
    };
    private static final Map<Integer, String> packetslist = new HashMap();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface DecodePayloadCallback<T> {
        boolean call(Packet<T> packet, int i, int i2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface EncodeCallback<T> {
        void call(T t);
    }

    static {
        for (Map.Entry<String, Integer> entry : packets.entrySet()) {
            packetslist.put(entry.getValue(), entry.getKey());
        }
        err = new Packet<>("error", "parser error");
        utf8Options = new UTF8.Options();
        utf8Options.strict = false;
    }

    private Parser() {
    }

    public static void encodePacket(Packet packet, EncodeCallback encodeCallback) throws UTF8Exception {
        encodePacket(packet, false, encodeCallback);
    }

    public static void encodePacket(Packet packet, boolean z, EncodeCallback encodeCallback) throws UTF8Exception {
        if (packet.data instanceof byte[]) {
            encodeByteArray(packet, encodeCallback);
            return;
        }
        String valueOf = String.valueOf(packets.get(packet.type));
        if (packet.data != 0) {
            StringBuilder sb = new StringBuilder();
            sb.append(valueOf);
            sb.append(z ? UTF8.encode(String.valueOf(packet.data), utf8Options) : String.valueOf(packet.data));
            valueOf = sb.toString();
        }
        encodeCallback.call(valueOf);
    }

    private static void encodeByteArray(Packet<byte[]> packet, EncodeCallback<byte[]> encodeCallback) {
        byte[] bArr = packet.data;
        byte[] bArr2 = new byte[bArr.length + 1];
        bArr2[0] = packets.get(packet.type).byteValue();
        System.arraycopy(bArr, 0, bArr2, 1, bArr.length);
        encodeCallback.call(bArr2);
    }

    public static Packet<String> decodePacket(String str) {
        return decodePacket(str, false);
    }

    public static Packet<String> decodePacket(String str, boolean z) {
        int i;
        if (str == null) {
            return err;
        }
        try {
            i = Character.getNumericValue(str.charAt(0));
        } catch (IndexOutOfBoundsException unused) {
            i = -1;
        }
        if (z) {
            try {
                str = UTF8.decode(str, utf8Options);
            } catch (UTF8Exception unused2) {
                return err;
            }
        }
        if (i < 0 || i >= packetslist.size()) {
            return err;
        }
        if (str.length() > 1) {
            return new Packet<>(packetslist.get(Integer.valueOf(i)), str.substring(1));
        }
        return new Packet<>(packetslist.get(Integer.valueOf(i)));
    }

    public static Packet<byte[]> decodePacket(byte[] bArr) {
        byte b = bArr[0];
        byte[] bArr2 = new byte[bArr.length - 1];
        System.arraycopy(bArr, 1, bArr2, 0, bArr2.length);
        return new Packet<>(packetslist.get(Integer.valueOf(b)), bArr2);
    }

    public static void encodePayload(Packet[] packetArr, EncodeCallback encodeCallback) throws UTF8Exception {
        for (Packet packet : packetArr) {
            if (packet.data instanceof byte[]) {
                encodePayloadAsBinary(packetArr, encodeCallback);
                return;
            }
        }
        if (packetArr.length == 0) {
            encodeCallback.call("0:");
            return;
        }
        final StringBuilder sb = new StringBuilder();
        for (Packet packet2 : packetArr) {
            encodePacket(packet2, false, new EncodeCallback() { // from class: io.socket.engineio.parser.Parser.2
                @Override // io.socket.engineio.parser.Parser.EncodeCallback
                public void call(Object obj) {
                    sb.append(Parser.setLengthHeader((String) obj));
                }
            });
        }
        encodeCallback.call(sb.toString());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static String setLengthHeader(String str) {
        return str.length() + ":" + str;
    }

    private static void encodePayloadAsBinary(Packet[] packetArr, EncodeCallback<byte[]> encodeCallback) throws UTF8Exception {
        if (packetArr.length == 0) {
            encodeCallback.call(new byte[0]);
            return;
        }
        final ArrayList arrayList = new ArrayList(packetArr.length);
        for (Packet packet : packetArr) {
            encodeOneBinaryPacket(packet, new EncodeCallback<byte[]>() { // from class: io.socket.engineio.parser.Parser.3
                @Override // io.socket.engineio.parser.Parser.EncodeCallback
                public void call(byte[] bArr) {
                    arrayList.add(bArr);
                }
            });
        }
        encodeCallback.call(Buffer.concat((byte[][]) arrayList.toArray(new byte[arrayList.size()])));
    }

    private static void encodeOneBinaryPacket(Packet packet, final EncodeCallback<byte[]> encodeCallback) throws UTF8Exception {
        encodePacket(packet, true, new EncodeCallback() { // from class: io.socket.engineio.parser.Parser.4
            @Override // io.socket.engineio.parser.Parser.EncodeCallback
            public void call(Object obj) {
                if (obj instanceof String) {
                    String str = (String) obj;
                    String valueOf = String.valueOf(str.length());
                    byte[] bArr = new byte[valueOf.length() + 2];
                    bArr[0] = 0;
                    int i = 0;
                    while (i < valueOf.length()) {
                        int i2 = i + 1;
                        bArr[i2] = (byte) Character.getNumericValue(valueOf.charAt(i));
                        i = i2;
                    }
                    bArr[bArr.length - 1] = -1;
                    EncodeCallback.this.call(Buffer.concat(new byte[][]{bArr, Parser.stringToByteArray(str)}));
                    return;
                }
                byte[] bArr2 = (byte[]) obj;
                String valueOf2 = String.valueOf(bArr2.length);
                byte[] bArr3 = new byte[valueOf2.length() + 2];
                bArr3[0] = 1;
                int i3 = 0;
                while (i3 < valueOf2.length()) {
                    int i4 = i3 + 1;
                    bArr3[i4] = (byte) Character.getNumericValue(valueOf2.charAt(i3));
                    i3 = i4;
                }
                bArr3[bArr3.length - 1] = -1;
                EncodeCallback.this.call(Buffer.concat(new byte[][]{bArr3, bArr2}));
            }
        });
    }

    public static void decodePayload(String str, DecodePayloadCallback<String> decodePayloadCallback) {
        if (str == null || str.length() == 0) {
            decodePayloadCallback.call(err, 0, 1);
            return;
        }
        StringBuilder sb = new StringBuilder();
        int length = str.length();
        StringBuilder sb2 = sb;
        int i = 0;
        while (i < length) {
            char charAt = str.charAt(i);
            if (':' != charAt) {
                sb2.append(charAt);
            } else {
                try {
                    int parseInt = Integer.parseInt(sb2.toString());
                    int i2 = i + 1;
                    try {
                        String substring = str.substring(i2, i2 + parseInt);
                        if (substring.length() != 0) {
                            Packet<String> decodePacket = decodePacket(substring, false);
                            if (err.type.equals(decodePacket.type) && err.data.equals(decodePacket.data)) {
                                decodePayloadCallback.call(err, 0, 1);
                                return;
                            } else if (!decodePayloadCallback.call(decodePacket, i + parseInt, length)) {
                                return;
                            }
                        }
                        i += parseInt;
                        sb2 = new StringBuilder();
                    } catch (IndexOutOfBoundsException unused) {
                        decodePayloadCallback.call(err, 0, 1);
                        return;
                    }
                } catch (NumberFormatException unused2) {
                    decodePayloadCallback.call(err, 0, 1);
                    return;
                }
            }
            i++;
        }
        if (sb2.length() > 0) {
            decodePayloadCallback.call(err, 0, 1);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:12:0x002a, code lost:
        r10.position(r1.length() + 1);
        r10 = r10.slice();
        r1 = java.lang.Integer.parseInt(r1.toString());
        r10.position(1);
        r1 = r1 + 1;
        r10.limit(r1);
        r2 = new byte[r10.remaining()];
        r10.get(r2);
     */
    /* JADX WARN: Code restructure failed: missing block: B:13:0x004e, code lost:
        if (r4 == false) goto L23;
     */
    /* JADX WARN: Code restructure failed: missing block: B:14:0x0050, code lost:
        r0.add(byteArrayToString(r2));
     */
    /* JADX WARN: Code restructure failed: missing block: B:15:0x0058, code lost:
        r0.add(r2);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void decodePayload(byte[] r10, io.socket.engineio.parser.Parser.DecodePayloadCallback r11) {
        /*
            java.nio.ByteBuffer r10 = java.nio.ByteBuffer.wrap(r10)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
        L9:
            int r1 = r10.capacity()
            r2 = 0
            r3 = 1
            if (r1 <= 0) goto L7a
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            byte r4 = r10.get(r2)
            r5 = 255(0xff, float:3.57E-43)
            r4 = r4 & r5
            if (r4 != 0) goto L21
            r4 = r3
            goto L22
        L21:
            r4 = r2
        L22:
            r6 = r3
        L23:
            byte r7 = r10.get(r6)
            r7 = r7 & r5
            if (r7 != r5) goto L66
            int r2 = r1.length()
            int r2 = r2 + r3
            r10.position(r2)
            java.nio.ByteBuffer r10 = r10.slice()
            java.lang.String r1 = r1.toString()
            int r1 = java.lang.Integer.parseInt(r1)
            r10.position(r3)
            int r1 = r1 + r3
            r10.limit(r1)
            int r2 = r10.remaining()
            byte[] r2 = new byte[r2]
            r10.get(r2)
            if (r4 == 0) goto L58
            java.lang.String r2 = byteArrayToString(r2)
            r0.add(r2)
            goto L5b
        L58:
            r0.add(r2)
        L5b:
            r10.clear()
            r10.position(r1)
            java.nio.ByteBuffer r10 = r10.slice()
            goto L9
        L66:
            int r8 = r1.length()
            int r9 = io.socket.engineio.parser.Parser.MAX_INT_CHAR_LENGTH
            if (r8 <= r9) goto L74
            io.socket.engineio.parser.Packet<java.lang.String> r10 = io.socket.engineio.parser.Parser.err
            r11.call(r10, r2, r3)
            return
        L74:
            r1.append(r7)
            int r6 = r6 + 1
            goto L23
        L7a:
            int r10 = r0.size()
        L7e:
            if (r2 >= r10) goto La2
            java.lang.Object r1 = r0.get(r2)
            boolean r4 = r1 instanceof java.lang.String
            if (r4 == 0) goto L92
            java.lang.String r1 = (java.lang.String) r1
            io.socket.engineio.parser.Packet r1 = decodePacket(r1, r3)
            r11.call(r1, r2, r10)
            goto L9f
        L92:
            boolean r4 = r1 instanceof byte[]
            if (r4 == 0) goto L9f
            byte[] r1 = (byte[]) r1
            io.socket.engineio.parser.Packet r1 = decodePacket(r1)
            r11.call(r1, r2, r10)
        L9f:
            int r2 = r2 + 1
            goto L7e
        La2:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: io.socket.engineio.parser.Parser.decodePayload(byte[], io.socket.engineio.parser.Parser$DecodePayloadCallback):void");
    }

    private static String byteArrayToString(byte[] bArr) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bArr) {
            sb.appendCodePoint(b & 255);
        }
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static byte[] stringToByteArray(String str) {
        int length = str.length();
        byte[] bArr = new byte[length];
        for (int i = 0; i < length; i++) {
            bArr[i] = (byte) Character.codePointAt(str, i);
        }
        return bArr;
    }
}
