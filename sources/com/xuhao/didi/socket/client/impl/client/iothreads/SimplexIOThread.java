package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.core.iocore.interfaces.IReader;
import com.xuhao.didi.core.iocore.interfaces.IStateSender;
import com.xuhao.didi.core.iocore.interfaces.IWriter;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.exceptions.ManuallyDisconnectException;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;
import java.io.IOException;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class SimplexIOThread extends AbsLoopThread {
    private boolean isWrite;
    private IReader mReader;
    private IStateSender mStateSender;
    private IWriter mWriter;

    public SimplexIOThread(IReader iReader, IWriter iWriter, IStateSender iStateSender) {
        super("client_simplex_io_thread");
        this.isWrite = false;
        this.mStateSender = iStateSender;
        this.mReader = iReader;
        this.mWriter = iWriter;
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public void beforeLoop() throws IOException {
        this.mStateSender.sendBroadcast("action_write_thread_start");
        this.mStateSender.sendBroadcast("action_read_thread_start");
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public void runInLoopThread() throws IOException {
        this.isWrite = this.mWriter.write();
        if (this.isWrite) {
            this.isWrite = false;
            this.mReader.read();
        }
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public synchronized void shutdown(Exception exc) {
        this.mReader.close();
        this.mWriter.close();
        super.shutdown(exc);
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public void loopFinish(Exception exc) {
        if (exc instanceof ManuallyDisconnectException) {
            exc = null;
        }
        if (exc != null) {
            SLog.m2258e("simplex error,thread is dead with exception:" + exc.getMessage());
        }
        this.mStateSender.sendBroadcast("action_write_thread_shutdown", exc);
        this.mStateSender.sendBroadcast("action_read_thread_shutdown", exc);
    }
}
