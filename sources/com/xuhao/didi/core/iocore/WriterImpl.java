package com.xuhao.didi.core.iocore;

import com.xuhao.didi.core.exceptions.WriteException;
import com.xuhao.didi.core.iocore.interfaces.IIOCoreOptions;
import com.xuhao.didi.core.iocore.interfaces.IPulseSendable;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.iocore.interfaces.IStateSender;
import com.xuhao.didi.core.iocore.interfaces.IWriter;
import com.xuhao.didi.core.utils.BytesUtils;
import com.xuhao.didi.core.utils.SLog;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.concurrent.LinkedBlockingQueue;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class WriterImpl implements IWriter<IIOCoreOptions> {
    private volatile IIOCoreOptions mOkOptions;
    private OutputStream mOutputStream;
    private LinkedBlockingQueue<ISendable> mQueue = new LinkedBlockingQueue<>();
    private IStateSender mStateSender;

    @Override // com.xuhao.didi.core.iocore.interfaces.IWriter
    public void initialize(OutputStream outputStream, IStateSender iStateSender) {
        this.mStateSender = iStateSender;
        this.mOutputStream = outputStream;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IWriter
    public boolean write() throws RuntimeException {
        ISendable iSendable;
        try {
            iSendable = this.mQueue.take();
        } catch (InterruptedException unused) {
            iSendable = null;
        }
        int i = 0;
        if (iSendable != null) {
            try {
                byte[] parse = iSendable.parse();
                int writePackageBytes = this.mOkOptions.getWritePackageBytes();
                int length = parse.length;
                ByteBuffer allocate = ByteBuffer.allocate(writePackageBytes);
                allocate.order(this.mOkOptions.getWriteByteOrder());
                while (length > 0) {
                    int min = Math.min(writePackageBytes, length);
                    allocate.clear();
                    allocate.rewind();
                    allocate.put(parse, i, min);
                    allocate.flip();
                    byte[] bArr = new byte[min];
                    allocate.get(bArr);
                    this.mOutputStream.write(bArr);
                    this.mOutputStream.flush();
                    if (SLog.isDebug()) {
                        byte[] copyOfRange = Arrays.copyOfRange(parse, i, i + min);
                        SLog.m2257i("write bytes: " + BytesUtils.toHexStringForLog(copyOfRange));
                        SLog.m2257i("bytes write length:" + min);
                    }
                    i += min;
                    length -= min;
                }
                if (iSendable instanceof IPulseSendable) {
                    this.mStateSender.sendBroadcast("action_pulse_request", iSendable);
                    return true;
                }
                this.mStateSender.sendBroadcast("action_write_complete", iSendable);
                return true;
            } catch (Exception e) {
                throw new WriteException(e);
            }
        }
        return false;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IWriter
    public void setOption(IIOCoreOptions iIOCoreOptions) {
        this.mOkOptions = iIOCoreOptions;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IWriter
    public void offer(ISendable iSendable) {
        this.mQueue.offer(iSendable);
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.IWriter
    public void close() {
        OutputStream outputStream = this.mOutputStream;
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException unused) {
            }
        }
    }
}
