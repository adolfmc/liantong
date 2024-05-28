package com.xuhao.didi.socket.common.interfaces.default_protocol;

import com.xuhao.didi.core.protocol.IReaderProtocol;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DefaultNormalReaderProtocol implements IReaderProtocol {
    @Override // com.xuhao.didi.core.protocol.IReaderProtocol
    public int getHeaderLength() {
        return 4;
    }

    @Override // com.xuhao.didi.core.protocol.IReaderProtocol
    public int getBodyLength(byte[] bArr, ByteOrder byteOrder) {
        if (bArr == null || bArr.length < getHeaderLength()) {
            return 0;
        }
        ByteBuffer wrap = ByteBuffer.wrap(bArr);
        wrap.order(byteOrder);
        return wrap.getInt();
    }
}
