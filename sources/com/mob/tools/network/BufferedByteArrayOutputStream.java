package com.mob.tools.network;

import com.mob.tools.proguard.PublicMemberKeeper;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

@Deprecated
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class BufferedByteArrayOutputStream extends ByteArrayOutputStream implements PublicMemberKeeper {
    public BufferedByteArrayOutputStream() {
    }

    public BufferedByteArrayOutputStream(int i) {
        super(i);
    }

    public boolean switchBuffer(byte[] bArr) {
        if (bArr == null || bArr.length != this.buf.length) {
            return false;
        }
        byte[] bArr2 = this.buf;
        this.buf = bArr;
        return true;
    }

    public byte[] getBuffer() {
        return this.buf;
    }

    public int getBufferSize() {
        return this.buf.length;
    }

    public void write(ByteBuffer byteBuffer) throws IOException {
        write(byteBuffer, byteBuffer.limit());
    }

    public void write(ByteBuffer byteBuffer, int i) throws IOException {
        if (this.buf.length - this.count >= i) {
            byteBuffer.get(this.buf, this.count, i);
            this.count += i;
            return;
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        write(bArr);
    }
}
