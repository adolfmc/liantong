package org.java_websocket;

import java.io.IOException;
import java.net.Socket;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import javax.net.ssl.SSLEngine;
import javax.net.ssl.SSLEngineResult;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SSLSocketChannel2 implements ByteChannel, WrappedByteChannel {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    protected static ByteBuffer emptybuffer = ByteBuffer.allocate(0);
    protected SSLEngineResult engineResult;
    private SSLEngineResult.Status engineStatus = SSLEngineResult.Status.BUFFER_UNDERFLOW;
    protected ExecutorService exec;
    protected ByteBuffer inCrypt;
    protected ByteBuffer inData;
    protected ByteBuffer outCrypt;
    protected SelectionKey selectionKey;
    protected SocketChannel socketChannel;
    protected SSLEngine sslEngine;
    protected List<Future<?>> tasks;

    public SSLSocketChannel2(SocketChannel socketChannel, SSLEngine sSLEngine, ExecutorService executorService, SelectionKey selectionKey) throws IOException {
        if (socketChannel == null || sSLEngine == null || executorService == null) {
            throw new IllegalArgumentException("parameter must not be null");
        }
        this.socketChannel = socketChannel;
        this.sslEngine = sSLEngine;
        this.exec = executorService;
        this.tasks = new ArrayList(3);
        if (selectionKey != null) {
            selectionKey.interestOps(selectionKey.interestOps() | 4);
            this.selectionKey = selectionKey;
        }
        createBuffers(sSLEngine.getSession());
        this.socketChannel.write(wrap(emptybuffer));
        processHandshake();
    }

    private void consumeFutureUninterruptible(Future<?> future) {
        boolean z = false;
        while (true) {
            try {
                try {
                    future.get();
                    break;
                } catch (ExecutionException e) {
                    throw new RuntimeException(e);
                }
            } catch (InterruptedException unused) {
                z = true;
            }
        }
        if (z) {
            Thread.currentThread().interrupt();
        }
    }

    private synchronized void processHandshake() throws IOException {
        if (this.engineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING) {
            return;
        }
        if (!this.tasks.isEmpty()) {
            Iterator<Future<?>> it = this.tasks.iterator();
            while (it.hasNext()) {
                Future<?> next = it.next();
                if (next.isDone()) {
                    it.remove();
                } else {
                    if (isBlocking()) {
                        consumeFutureUninterruptible(next);
                    }
                    return;
                }
            }
        }
        if (this.engineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_UNWRAP) {
            if (!isBlocking() || this.engineStatus == SSLEngineResult.Status.BUFFER_UNDERFLOW) {
                this.inCrypt.compact();
                if (this.socketChannel.read(this.inCrypt) == -1) {
                    throw new IOException("connection closed unexpectedly by peer");
                }
                this.inCrypt.flip();
            }
            this.inData.compact();
            unwrap();
            if (this.engineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                createBuffers(this.sslEngine.getSession());
                return;
            }
        }
        consumeDelegatedTasks();
        if (this.tasks.isEmpty() || this.engineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.NEED_WRAP) {
            this.socketChannel.write(wrap(emptybuffer));
            if (this.engineResult.getHandshakeStatus() == SSLEngineResult.HandshakeStatus.FINISHED) {
                createBuffers(this.sslEngine.getSession());
            }
        }
    }

    private synchronized ByteBuffer wrap(ByteBuffer byteBuffer) throws SSLException {
        this.outCrypt.compact();
        this.engineResult = this.sslEngine.wrap(byteBuffer, this.outCrypt);
        this.outCrypt.flip();
        return this.outCrypt;
    }

    private synchronized ByteBuffer unwrap() throws SSLException {
        while (true) {
            int remaining = this.inData.remaining();
            this.engineResult = this.sslEngine.unwrap(this.inCrypt, this.inData);
            this.engineStatus = this.engineResult.getStatus();
            if (this.engineStatus != SSLEngineResult.Status.OK || (remaining == this.inData.remaining() && this.engineResult.getHandshakeStatus() != SSLEngineResult.HandshakeStatus.NEED_UNWRAP)) {
                break;
            }
        }
        this.inData.flip();
        return this.inData;
    }

    protected void consumeDelegatedTasks() {
        while (true) {
            Runnable delegatedTask = this.sslEngine.getDelegatedTask();
            if (delegatedTask == null) {
                return;
            }
            this.tasks.add(this.exec.submit(delegatedTask));
        }
    }

    protected void createBuffers(SSLSession sSLSession) {
        int applicationBufferSize = sSLSession.getApplicationBufferSize();
        int packetBufferSize = sSLSession.getPacketBufferSize();
        ByteBuffer byteBuffer = this.inData;
        if (byteBuffer == null) {
            this.inData = ByteBuffer.allocate(applicationBufferSize);
            this.outCrypt = ByteBuffer.allocate(packetBufferSize);
            this.inCrypt = ByteBuffer.allocate(packetBufferSize);
        } else {
            if (byteBuffer.capacity() != applicationBufferSize) {
                this.inData = ByteBuffer.allocate(applicationBufferSize);
            }
            if (this.outCrypt.capacity() != packetBufferSize) {
                this.outCrypt = ByteBuffer.allocate(packetBufferSize);
            }
            if (this.inCrypt.capacity() != packetBufferSize) {
                this.inCrypt = ByteBuffer.allocate(packetBufferSize);
            }
        }
        this.inData.rewind();
        this.inData.flip();
        this.inCrypt.rewind();
        this.inCrypt.flip();
        this.outCrypt.rewind();
        this.outCrypt.flip();
    }

    @Override // java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        if (!isHandShakeComplete()) {
            processHandshake();
            return 0;
        }
        return this.socketChannel.write(wrap(byteBuffer));
    }

    @Override // java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.hasRemaining()) {
            if (!isHandShakeComplete()) {
                if (isBlocking()) {
                    while (!isHandShakeComplete()) {
                        processHandshake();
                    }
                } else {
                    processHandshake();
                    if (!isHandShakeComplete()) {
                        return 0;
                    }
                }
            }
            int readRemaining = readRemaining(byteBuffer);
            if (readRemaining != 0) {
                return readRemaining;
            }
            this.inData.clear();
            if (!this.inCrypt.hasRemaining()) {
                this.inCrypt.clear();
            } else {
                this.inCrypt.compact();
            }
            if (((isBlocking() && this.inCrypt.position() == 0) || this.engineStatus == SSLEngineResult.Status.BUFFER_UNDERFLOW) && this.socketChannel.read(this.inCrypt) == -1) {
                return -1;
            }
            this.inCrypt.flip();
            unwrap();
            int transfereTo = transfereTo(this.inData, byteBuffer);
            return (transfereTo == 0 && isBlocking()) ? read(byteBuffer) : transfereTo;
        }
        return 0;
    }

    private int readRemaining(ByteBuffer byteBuffer) throws SSLException {
        if (this.inData.hasRemaining()) {
            return transfereTo(this.inData, byteBuffer);
        }
        if (!this.inData.hasRemaining()) {
            this.inData.clear();
        }
        if (this.inCrypt.hasRemaining()) {
            unwrap();
            int transfereTo = transfereTo(this.inData, byteBuffer);
            if (transfereTo > 0) {
                return transfereTo;
            }
            return 0;
        }
        return 0;
    }

    public boolean isConnected() {
        return this.socketChannel.isConnected();
    }

    @Override // java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.sslEngine.closeOutbound();
        this.sslEngine.getSession().invalidate();
        if (this.socketChannel.isOpen()) {
            this.socketChannel.write(wrap(emptybuffer));
        }
        this.socketChannel.close();
    }

    private boolean isHandShakeComplete() {
        SSLEngineResult.HandshakeStatus handshakeStatus = this.engineResult.getHandshakeStatus();
        return handshakeStatus == SSLEngineResult.HandshakeStatus.FINISHED || handshakeStatus == SSLEngineResult.HandshakeStatus.NOT_HANDSHAKING;
    }

    public SelectableChannel configureBlocking(boolean z) throws IOException {
        return this.socketChannel.configureBlocking(z);
    }

    public boolean connect(SocketAddress socketAddress) throws IOException {
        return this.socketChannel.connect(socketAddress);
    }

    public boolean finishConnect() throws IOException {
        return this.socketChannel.finishConnect();
    }

    public Socket socket() {
        return this.socketChannel.socket();
    }

    public boolean isInboundDone() {
        return this.sslEngine.isInboundDone();
    }

    @Override // java.nio.channels.Channel
    public boolean isOpen() {
        return this.socketChannel.isOpen();
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isNeedWrite() {
        return this.outCrypt.hasRemaining() || !isHandShakeComplete();
    }

    @Override // org.java_websocket.WrappedByteChannel
    public void writeMore() throws IOException {
        write(this.outCrypt);
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isNeedRead() {
        return this.inData.hasRemaining() || (this.inCrypt.hasRemaining() && this.engineResult.getStatus() != SSLEngineResult.Status.BUFFER_UNDERFLOW);
    }

    @Override // org.java_websocket.WrappedByteChannel
    public int readMore(ByteBuffer byteBuffer) throws SSLException {
        return readRemaining(byteBuffer);
    }

    private int transfereTo(ByteBuffer byteBuffer, ByteBuffer byteBuffer2) {
        int remaining = byteBuffer.remaining();
        int remaining2 = byteBuffer2.remaining();
        if (remaining > remaining2) {
            int min = Math.min(remaining, remaining2);
            for (int i = 0; i < min; i++) {
                byteBuffer2.put(byteBuffer.get());
            }
            return min;
        }
        byteBuffer2.put(byteBuffer);
        return remaining;
    }

    @Override // org.java_websocket.WrappedByteChannel
    public boolean isBlocking() {
        return this.socketChannel.isBlocking();
    }
}
