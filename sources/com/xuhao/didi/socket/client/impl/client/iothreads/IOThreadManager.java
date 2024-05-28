package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.core.iocore.ReaderImpl;
import com.xuhao.didi.core.iocore.WriterImpl;
import com.xuhao.didi.core.iocore.interfaces.IReader;
import com.xuhao.didi.core.iocore.interfaces.ISendable;
import com.xuhao.didi.core.iocore.interfaces.IStateSender;
import com.xuhao.didi.core.iocore.interfaces.IWriter;
import com.xuhao.didi.core.protocol.IReaderProtocol;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.exceptions.ManuallyDisconnectException;
import com.xuhao.didi.socket.client.sdk.client.OkSocketOptions;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;
import com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager;
import java.io.InputStream;
import java.io.OutputStream;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class IOThreadManager implements IIOManager<OkSocketOptions> {
    private OkSocketOptions.IOThreadMode mCurrentThreadMode;
    private DuplexReadThread mDuplexReadThread;
    private DuplexWriteThread mDuplexWriteThread;
    private InputStream mInputStream;
    private volatile OkSocketOptions mOkOptions;
    private OutputStream mOutputStream;
    private IReader mReader;
    private IStateSender mSender;
    private AbsLoopThread mSimplexThread;
    private IWriter mWriter;

    public IOThreadManager(InputStream inputStream, OutputStream outputStream, OkSocketOptions okSocketOptions, IStateSender iStateSender) {
        this.mInputStream = inputStream;
        this.mOutputStream = outputStream;
        this.mOkOptions = okSocketOptions;
        this.mSender = iStateSender;
        initIO();
    }

    private void initIO() {
        assertHeaderProtocolNotEmpty();
        this.mReader = new ReaderImpl();
        this.mReader.initialize(this.mInputStream, this.mSender);
        this.mWriter = new WriterImpl();
        this.mWriter.initialize(this.mOutputStream, this.mSender);
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager
    public synchronized void startEngine() {
        this.mCurrentThreadMode = this.mOkOptions.getIOThreadMode();
        this.mReader.setOption(this.mOkOptions);
        this.mWriter.setOption(this.mOkOptions);
        switch (this.mOkOptions.getIOThreadMode()) {
            case DUPLEX:
                SLog.m2256w("DUPLEX is processing");
                duplex();
                break;
            case SIMPLEX:
                SLog.m2256w("SIMPLEX is processing");
                simplex();
                break;
            default:
                throw new RuntimeException("未定义的线程模式");
        }
    }

    private void duplex() {
        shutdownAllThread(null);
        this.mDuplexWriteThread = new DuplexWriteThread(this.mWriter, this.mSender);
        this.mDuplexReadThread = new DuplexReadThread(this.mReader, this.mSender);
        this.mDuplexWriteThread.start();
        this.mDuplexReadThread.start();
    }

    private void simplex() {
        shutdownAllThread(null);
        this.mSimplexThread = new SimplexIOThread(this.mReader, this.mWriter, this.mSender);
        this.mSimplexThread.start();
    }

    private void shutdownAllThread(Exception exc) {
        AbsLoopThread absLoopThread = this.mSimplexThread;
        if (absLoopThread != null) {
            absLoopThread.shutdown(exc);
            this.mSimplexThread = null;
        }
        DuplexReadThread duplexReadThread = this.mDuplexReadThread;
        if (duplexReadThread != null) {
            duplexReadThread.shutdown(exc);
            this.mDuplexReadThread = null;
        }
        DuplexWriteThread duplexWriteThread = this.mDuplexWriteThread;
        if (duplexWriteThread != null) {
            duplexWriteThread.shutdown(exc);
            this.mDuplexWriteThread = null;
        }
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager
    public synchronized void setOkOptions(OkSocketOptions okSocketOptions) {
        this.mOkOptions = okSocketOptions;
        if (this.mCurrentThreadMode == null) {
            this.mCurrentThreadMode = this.mOkOptions.getIOThreadMode();
        }
        assertTheThreadModeNotChanged();
        assertHeaderProtocolNotEmpty();
        this.mWriter.setOption(this.mOkOptions);
        this.mReader.setOption(this.mOkOptions);
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager
    public void send(ISendable iSendable) {
        this.mWriter.offer(iSendable);
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager
    public void close() {
        close(new ManuallyDisconnectException());
    }

    @Override // com.xuhao.didi.socket.common.interfaces.common_interfacies.IIOManager
    public synchronized void close(Exception exc) {
        shutdownAllThread(exc);
        this.mCurrentThreadMode = null;
    }

    private void assertHeaderProtocolNotEmpty() {
        IReaderProtocol readerProtocol = this.mOkOptions.getReaderProtocol();
        if (readerProtocol == null) {
            throw new IllegalArgumentException("The reader protocol can not be Null.");
        }
        if (readerProtocol.getHeaderLength() == 0) {
            throw new IllegalArgumentException("The header length can not be zero.");
        }
    }

    private void assertTheThreadModeNotChanged() {
        if (this.mOkOptions.getIOThreadMode() == this.mCurrentThreadMode) {
            return;
        }
        throw new IllegalArgumentException("can't hot change iothread mode from " + this.mCurrentThreadMode + " to " + this.mOkOptions.getIOThreadMode() + " in blocking io manager");
    }
}
