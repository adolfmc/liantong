package com.xuhao.didi.core.iocore;

import com.xuhao.didi.core.exceptions.ReadException;
import com.xuhao.didi.core.pojo.OriginalData;
import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.core.utils.BytesUtils;
import com.xuhao.didi.core.utils.SLog;
import java.io.IOException;
import java.nio.ByteBuffer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class ReaderImpl extends AbsReader {
    private ByteBuffer mRemainingBuf;

    @Override // com.xuhao.didi.core.iocore.interfaces.IReader
    public void read() throws RuntimeException {
        OriginalData originalData = new OriginalData();
        IReaderProtocol readerProtocol = this.mOkOptions.getReaderProtocol();
        int headerLength = readerProtocol.getHeaderLength();
        ByteBuffer allocate = ByteBuffer.allocate(headerLength);
        allocate.order(this.mOkOptions.getReadByteOrder());
        try {
            if (this.mRemainingBuf != null) {
                this.mRemainingBuf.flip();
                int min = Math.min(this.mRemainingBuf.remaining(), headerLength);
                allocate.put(this.mRemainingBuf.array(), 0, min);
                if (min < headerLength) {
                    this.mRemainingBuf = null;
                    readHeaderFromChannel(allocate, headerLength - min);
                } else {
                    this.mRemainingBuf.position(headerLength);
                }
            } else {
                readHeaderFromChannel(allocate, allocate.capacity());
            }
            originalData.setHeadBytes(allocate.array());
            if (SLog.isDebug()) {
                SLog.m2257i("read head: " + BytesUtils.toHexStringForLog(allocate.array()));
            }
            int bodyLength = readerProtocol.getBodyLength(originalData.getHeadBytes(), this.mOkOptions.getReadByteOrder());
            if (SLog.isDebug()) {
                SLog.m2257i("need read body length: " + bodyLength);
            }
            if (bodyLength > 0) {
                if (bodyLength > this.mOkOptions.getMaxReadDataMB() * 1024 * 1024) {
                    throw new ReadException("Need to follow the transmission protocol.\r\nPlease check the client/server code.\r\nAccording to the packet header data in the transport protocol, the package length is " + bodyLength + " Bytes.\r\nYou need check your <ReaderProtocol> definition");
                }
                ByteBuffer allocate2 = ByteBuffer.allocate(bodyLength);
                allocate2.order(this.mOkOptions.getReadByteOrder());
                if (this.mRemainingBuf != null) {
                    int position = this.mRemainingBuf.position();
                    int min2 = Math.min(this.mRemainingBuf.remaining(), bodyLength);
                    allocate2.put(this.mRemainingBuf.array(), position, min2);
                    this.mRemainingBuf.position(position + min2);
                    if (min2 == bodyLength) {
                        if (this.mRemainingBuf.remaining() > 0) {
                            ByteBuffer allocate3 = ByteBuffer.allocate(this.mRemainingBuf.remaining());
                            allocate3.order(this.mOkOptions.getReadByteOrder());
                            allocate3.put(this.mRemainingBuf.array(), this.mRemainingBuf.position(), this.mRemainingBuf.remaining());
                            this.mRemainingBuf = allocate3;
                        } else {
                            this.mRemainingBuf = null;
                        }
                        originalData.setBodyBytes(allocate2.array());
                        this.mStateSender.sendBroadcast("action_read_complete", originalData);
                        return;
                    }
                    this.mRemainingBuf = null;
                }
                readBodyFromChannel(allocate2);
                originalData.setBodyBytes(allocate2.array());
            } else if (bodyLength == 0) {
                originalData.setBodyBytes(new byte[0]);
                if (this.mRemainingBuf != null) {
                    if (this.mRemainingBuf.hasRemaining()) {
                        ByteBuffer allocate4 = ByteBuffer.allocate(this.mRemainingBuf.remaining());
                        allocate4.order(this.mOkOptions.getReadByteOrder());
                        allocate4.put(this.mRemainingBuf.array(), this.mRemainingBuf.position(), this.mRemainingBuf.remaining());
                        this.mRemainingBuf = allocate4;
                    } else {
                        this.mRemainingBuf = null;
                    }
                }
            } else if (bodyLength < 0) {
                throw new ReadException("read body is wrong,this socket input stream is end of file read " + bodyLength + " ,that mean this socket is disconnected by server");
            }
            this.mStateSender.sendBroadcast("action_read_complete", originalData);
        } catch (Exception e) {
            throw new ReadException(e);
        }
    }

    private void readHeaderFromChannel(ByteBuffer byteBuffer, int i) throws IOException {
        for (int i2 = 0; i2 < i; i2++) {
            byte[] bArr = new byte[1];
            int read = this.mInputStream.read(bArr);
            if (read == -1) {
                throw new ReadException("read head is wrong, this socket input stream is end of file read " + read + " ,that mean this socket is disconnected by server");
            }
            byteBuffer.put(bArr);
        }
    }

    private void readBodyFromChannel(ByteBuffer byteBuffer) throws IOException {
        while (byteBuffer.hasRemaining()) {
            try {
                byte[] bArr = new byte[this.mOkOptions.getReadPackageBytes()];
                int read = this.mInputStream.read(bArr);
                if (read == -1) {
                    break;
                }
                int remaining = byteBuffer.remaining();
                if (read > remaining) {
                    byteBuffer.put(bArr, 0, remaining);
                    int i = read - remaining;
                    this.mRemainingBuf = ByteBuffer.allocate(i);
                    this.mRemainingBuf.order(this.mOkOptions.getReadByteOrder());
                    this.mRemainingBuf.put(bArr, remaining, i);
                } else {
                    byteBuffer.put(bArr, 0, read);
                }
            } catch (Exception e) {
                throw e;
            }
        }
        if (SLog.isDebug()) {
            SLog.m2257i("read total bytes: " + BytesUtils.toHexStringForLog(byteBuffer.array()));
            SLog.m2257i("read total length:" + (byteBuffer.capacity() - byteBuffer.remaining()));
        }
    }
}
