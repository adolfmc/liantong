package com.xuhao.didi.socket.client.impl.client.iothreads;

import com.xuhao.didi.core.iocore.interfaces.IReader;
import com.xuhao.didi.core.iocore.interfaces.IStateSender;
import com.xuhao.didi.core.utils.SLog;
import com.xuhao.didi.socket.client.impl.exceptions.ManuallyDisconnectException;
import com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread;
import java.io.IOException;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DuplexReadThread extends AbsLoopThread {
    private IReader mReader;
    private IStateSender mStateSender;

    public DuplexReadThread(IReader iReader, IStateSender iStateSender) {
        super("client_duplex_read_thread");
        this.mStateSender = iStateSender;
        this.mReader = iReader;
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public void beforeLoop() {
        this.mStateSender.sendBroadcast("action_read_thread_start");
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public void runInLoopThread() throws IOException {
        this.mReader.read();
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public synchronized void shutdown(Exception exc) {
        this.mReader.close();
        super.shutdown(exc);
    }

    @Override // com.xuhao.didi.socket.common.interfaces.basic.AbsLoopThread
    public void loopFinish(Exception exc) {
        if (exc instanceof ManuallyDisconnectException) {
            exc = null;
        }
        if (exc != null) {
            SLog.m2258e("duplex read error,thread is dead with exception:" + exc.getMessage());
        }
        this.mStateSender.sendBroadcast("action_read_thread_shutdown", exc);
    }
}
