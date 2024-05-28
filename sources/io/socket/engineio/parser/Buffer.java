package io.socket.engineio.parser;

import java.nio.ByteBuffer;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Parser.java */
/* loaded from: E:\11617560_dexfile_execute.dex */
class Buffer {
    private Buffer() {
    }

    public static byte[] concat(byte[][] bArr) {
        int i = 0;
        for (byte[] bArr2 : bArr) {
            i += bArr2.length;
        }
        return concat(bArr, i);
    }

    public static byte[] concat(byte[][] bArr, int i) {
        if (bArr.length == 0) {
            return new byte[0];
        }
        if (bArr.length == 1) {
            return bArr[0];
        }
        ByteBuffer allocate = ByteBuffer.allocate(i);
        for (byte[] bArr2 : bArr) {
            allocate.put(bArr2);
        }
        return allocate.array();
    }
}
