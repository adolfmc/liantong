package io.socket.client;

import io.socket.backo.Backoff;
import io.socket.client.C12145On;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Socket;
import io.socket.parser.IOParser;
import io.socket.parser.Packet;
import io.socket.parser.Parser;
import io.socket.thread.EventThread;
import java.net.URI;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Call;
import okhttp3.WebSocket;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Manager extends Emitter {
    public static final String EVENT_CLOSE = "close";
    public static final String EVENT_CONNECT_ERROR = "connect_error";
    public static final String EVENT_CONNECT_TIMEOUT = "connect_timeout";
    public static final String EVENT_ERROR = "error";
    public static final String EVENT_OPEN = "open";
    public static final String EVENT_PACKET = "packet";
    public static final String EVENT_PING = "ping";
    public static final String EVENT_PONG = "pong";
    public static final String EVENT_RECONNECT = "reconnect";
    public static final String EVENT_RECONNECTING = "reconnecting";
    public static final String EVENT_RECONNECT_ATTEMPT = "reconnect_attempt";
    public static final String EVENT_RECONNECT_ERROR = "reconnect_error";
    public static final String EVENT_RECONNECT_FAILED = "reconnect_failed";
    public static final String EVENT_TRANSPORT = "transport";
    static Call.Factory defaultCallFactory;
    static WebSocket.Factory defaultWebSocketFactory;
    private static final Logger logger = Logger.getLogger(Manager.class.getName());
    private double _randomizationFactor;
    private boolean _reconnection;
    private int _reconnectionAttempts;
    private long _reconnectionDelay;
    private long _reconnectionDelayMax;
    private long _timeout;
    private Backoff backoff;
    private Set<Socket> connecting;
    private Parser.Decoder decoder;
    private Parser.Encoder encoder;
    private boolean encoding;
    io.socket.engineio.client.Socket engine;
    private Date lastPing;
    ConcurrentHashMap<String, Socket> nsps;
    private Options opts;
    private List<Packet> packetBuffer;
    ReadyState readyState;
    private boolean reconnecting;
    private boolean skipReconnect;
    private Queue<C12145On.Handle> subs;
    private URI uri;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface OpenCallback {
        void call(Exception exc);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Options extends Socket.Options {
        public Parser.Decoder decoder;
        public Parser.Encoder encoder;
        public double randomizationFactor;
        public int reconnectionAttempts;
        public long reconnectionDelay;
        public long reconnectionDelayMax;
        public boolean reconnection = true;
        public long timeout = 20000;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public enum ReadyState {
        CLOSED,
        OPENING,
        OPEN
    }

    public Manager() {
        this(null, null);
    }

    public Manager(URI uri) {
        this(uri, null);
    }

    public Manager(Options options) {
        this(null, options);
    }

    public Manager(URI uri, Options options) {
        this.connecting = new HashSet();
        options = options == null ? new Options() : options;
        if (options.path == null) {
            options.path = "/socket.io";
        }
        if (options.webSocketFactory == null) {
            options.webSocketFactory = defaultWebSocketFactory;
        }
        if (options.callFactory == null) {
            options.callFactory = defaultCallFactory;
        }
        this.opts = options;
        this.nsps = new ConcurrentHashMap<>();
        this.subs = new LinkedList();
        reconnection(options.reconnection);
        reconnectionAttempts(options.reconnectionAttempts != 0 ? options.reconnectionAttempts : Integer.MAX_VALUE);
        reconnectionDelay(options.reconnectionDelay != 0 ? options.reconnectionDelay : 1000L);
        reconnectionDelayMax(options.reconnectionDelayMax != 0 ? options.reconnectionDelayMax : 5000L);
        randomizationFactor(options.randomizationFactor != 0.0d ? options.randomizationFactor : 0.5d);
        this.backoff = new Backoff().setMin(reconnectionDelay()).setMax(reconnectionDelayMax()).setJitter(randomizationFactor());
        timeout(options.timeout);
        this.readyState = ReadyState.CLOSED;
        this.uri = uri;
        this.encoding = false;
        this.packetBuffer = new ArrayList();
        this.encoder = options.encoder != null ? options.encoder : new IOParser.Encoder();
        this.decoder = options.decoder != null ? options.decoder : new IOParser.Decoder();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void emitAll(String str, Object... objArr) {
        emit(str, objArr);
        for (Socket socket : this.nsps.values()) {
            socket.emit(str, objArr);
        }
    }

    private void updateSocketIds() {
        for (Map.Entry<String, Socket> entry : this.nsps.entrySet()) {
            entry.getValue().f24814id = generateId(entry.getKey());
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String generateId(String str) {
        String str2;
        StringBuilder sb = new StringBuilder();
        if ("/".equals(str)) {
            str2 = "";
        } else {
            str2 = str + "#";
        }
        sb.append(str2);
        sb.append(this.engine.m1929id());
        return sb.toString();
    }

    public boolean reconnection() {
        return this._reconnection;
    }

    public Manager reconnection(boolean z) {
        this._reconnection = z;
        return this;
    }

    public int reconnectionAttempts() {
        return this._reconnectionAttempts;
    }

    public Manager reconnectionAttempts(int i) {
        this._reconnectionAttempts = i;
        return this;
    }

    public final long reconnectionDelay() {
        return this._reconnectionDelay;
    }

    public Manager reconnectionDelay(long j) {
        this._reconnectionDelay = j;
        Backoff backoff = this.backoff;
        if (backoff != null) {
            backoff.setMin(j);
        }
        return this;
    }

    public final double randomizationFactor() {
        return this._randomizationFactor;
    }

    public Manager randomizationFactor(double d) {
        this._randomizationFactor = d;
        Backoff backoff = this.backoff;
        if (backoff != null) {
            backoff.setJitter(d);
        }
        return this;
    }

    public final long reconnectionDelayMax() {
        return this._reconnectionDelayMax;
    }

    public Manager reconnectionDelayMax(long j) {
        this._reconnectionDelayMax = j;
        Backoff backoff = this.backoff;
        if (backoff != null) {
            backoff.setMax(j);
        }
        return this;
    }

    public long timeout() {
        return this._timeout;
    }

    public Manager timeout(long j) {
        this._timeout = j;
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void maybeReconnectOnOpen() {
        if (!this.reconnecting && this._reconnection && this.backoff.getAttempts() == 0) {
            reconnect();
        }
    }

    public Manager open() {
        return open(null);
    }

    public Manager open(final OpenCallback openCallback) {
        EventThread.exec(new Runnable() { // from class: io.socket.client.Manager.1
            @Override // java.lang.Runnable
            public void run() {
                if (Manager.logger.isLoggable(Level.FINE)) {
                    Manager.logger.fine(String.format("readyState %s", Manager.this.readyState));
                }
                if (Manager.this.readyState == ReadyState.OPEN || Manager.this.readyState == ReadyState.OPENING) {
                    return;
                }
                if (Manager.logger.isLoggable(Level.FINE)) {
                    Manager.logger.fine(String.format("opening %s", Manager.this.uri));
                }
                Manager manager = Manager.this;
                manager.engine = new Engine(manager.uri, Manager.this.opts);
                final io.socket.engineio.client.Socket socket = Manager.this.engine;
                final Manager manager2 = Manager.this;
                manager2.readyState = ReadyState.OPENING;
                Manager.this.skipReconnect = false;
                socket.m1930on("transport", new Emitter.Listener() { // from class: io.socket.client.Manager.1.1
                    @Override // io.socket.emitter.Emitter.Listener
                    public void call(Object... objArr) {
                        manager2.emit("transport", objArr);
                    }
                });
                final C12145On.Handle m1933on = C12145On.m1933on(socket, "open", new Emitter.Listener() { // from class: io.socket.client.Manager.1.2
                    @Override // io.socket.emitter.Emitter.Listener
                    public void call(Object... objArr) {
                        manager2.onopen();
                        if (openCallback != null) {
                            openCallback.call(null);
                        }
                    }
                });
                C12145On.Handle m1933on2 = C12145On.m1933on(socket, "error", new Emitter.Listener() { // from class: io.socket.client.Manager.1.3
                    @Override // io.socket.emitter.Emitter.Listener
                    public void call(Object... objArr) {
                        Exception exc = objArr.length > 0 ? objArr[0] : null;
                        Manager.logger.fine("connect_error");
                        manager2.cleanup();
                        manager2.readyState = ReadyState.CLOSED;
                        manager2.emitAll("connect_error", exc);
                        if (openCallback == null) {
                            manager2.maybeReconnectOnOpen();
                        } else {
                            openCallback.call(new SocketIOException("Connection error", exc instanceof Exception ? exc : null));
                        }
                    }
                });
                if (Manager.this._timeout >= 0) {
                    final long j = Manager.this._timeout;
                    Manager.logger.fine(String.format("connection attempt will timeout after %d", Long.valueOf(j)));
                    final Timer timer = new Timer();
                    timer.schedule(new TimerTask() { // from class: io.socket.client.Manager.1.4
                        @Override // java.util.TimerTask, java.lang.Runnable
                        public void run() {
                            EventThread.exec(new Runnable() { // from class: io.socket.client.Manager.1.4.1
                                @Override // java.lang.Runnable
                                public void run() {
                                    Manager.logger.fine(String.format("connect attempt timed out after %d", Long.valueOf(j)));
                                    m1933on.destroy();
                                    socket.close();
                                    socket.emit("error", new SocketIOException("timeout"));
                                    manager2.emitAll("connect_timeout", Long.valueOf(j));
                                }
                            });
                        }
                    }, j);
                    Manager.this.subs.add(new C12145On.Handle() { // from class: io.socket.client.Manager.1.5
                        @Override // io.socket.client.C12145On.Handle
                        public void destroy() {
                            timer.cancel();
                        }
                    });
                }
                Manager.this.subs.add(m1933on);
                Manager.this.subs.add(m1933on2);
                Manager.this.engine.open();
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onopen() {
        logger.fine("open");
        cleanup();
        this.readyState = ReadyState.OPEN;
        emit("open", new Object[0]);
        io.socket.engineio.client.Socket socket = this.engine;
        this.subs.add(C12145On.m1933on(socket, "data", new Emitter.Listener() { // from class: io.socket.client.Manager.2
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                Object obj = objArr[0];
                if (obj instanceof String) {
                    Manager.this.ondata((String) obj);
                } else if (obj instanceof byte[]) {
                    Manager.this.ondata((byte[]) obj);
                }
            }
        }));
        this.subs.add(C12145On.m1933on(socket, "ping", new Emitter.Listener() { // from class: io.socket.client.Manager.3
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                Manager.this.onping();
            }
        }));
        this.subs.add(C12145On.m1933on(socket, "pong", new Emitter.Listener() { // from class: io.socket.client.Manager.4
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                Manager.this.onpong();
            }
        }));
        this.subs.add(C12145On.m1933on(socket, "error", new Emitter.Listener() { // from class: io.socket.client.Manager.5
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                Manager.this.onerror((Exception) objArr[0]);
            }
        }));
        this.subs.add(C12145On.m1933on(socket, "close", new Emitter.Listener() { // from class: io.socket.client.Manager.6
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                Manager.this.onclose((String) objArr[0]);
            }
        }));
        this.decoder.onDecoded(new Parser.Decoder.Callback() { // from class: io.socket.client.Manager.7
            @Override // io.socket.parser.Parser.Decoder.Callback
            public void call(Packet packet) {
                Manager.this.ondecoded(packet);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onping() {
        this.lastPing = new Date();
        emitAll("ping", new Object[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onpong() {
        Object[] objArr = new Object[1];
        objArr[0] = Long.valueOf(this.lastPing != null ? new Date().getTime() - this.lastPing.getTime() : 0L);
        emitAll("pong", objArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ondata(String str) {
        this.decoder.add(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ondata(byte[] bArr) {
        this.decoder.add(bArr);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void ondecoded(Packet packet) {
        emit("packet", packet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onerror(Exception exc) {
        logger.log(Level.FINE, "error", (Throwable) exc);
        emitAll("error", exc);
    }

    public Socket socket(final String str, Options options) {
        Socket socket = this.nsps.get(str);
        if (socket == null) {
            final Socket socket2 = new Socket(this, str, options);
            Socket putIfAbsent = this.nsps.putIfAbsent(str, socket2);
            if (putIfAbsent != null) {
                return putIfAbsent;
            }
            socket2.m1930on(Socket.EVENT_CONNECTING, new Emitter.Listener() { // from class: io.socket.client.Manager.8
                @Override // io.socket.emitter.Emitter.Listener
                public void call(Object... objArr) {
                    this.connecting.add(socket2);
                }
            });
            socket2.m1930on("connect", new Emitter.Listener() { // from class: io.socket.client.Manager.9
                @Override // io.socket.emitter.Emitter.Listener
                public void call(Object... objArr) {
                    socket2.f24814id = this.generateId(str);
                }
            });
            return socket2;
        }
        return socket;
    }

    public Socket socket(String str) {
        return socket(str, null);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void destroy(Socket socket) {
        this.connecting.remove(socket);
        if (this.connecting.isEmpty()) {
            close();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void packet(Packet packet) {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("writing packet %s", packet));
        }
        if (packet.query != null && !packet.query.isEmpty() && packet.type == 0) {
            packet.nsp += "?" + packet.query;
        }
        if (!this.encoding) {
            this.encoding = true;
            this.encoder.encode(packet, new Parser.Encoder.Callback() { // from class: io.socket.client.Manager.10
                @Override // io.socket.parser.Parser.Encoder.Callback
                public void call(Object[] objArr) {
                    for (Object obj : objArr) {
                        if (obj instanceof String) {
                            this.engine.write((String) obj);
                        } else if (obj instanceof byte[]) {
                            this.engine.write((byte[]) obj);
                        }
                    }
                    this.encoding = false;
                    this.processPacketQueue();
                }
            });
            return;
        }
        this.packetBuffer.add(packet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void processPacketQueue() {
        if (this.packetBuffer.isEmpty() || this.encoding) {
            return;
        }
        packet(this.packetBuffer.remove(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void cleanup() {
        logger.fine("cleanup");
        while (true) {
            C12145On.Handle poll = this.subs.poll();
            if (poll == null) {
                this.decoder.onDecoded(null);
                this.packetBuffer.clear();
                this.encoding = false;
                this.lastPing = null;
                this.decoder.destroy();
                return;
            }
            poll.destroy();
        }
    }

    void close() {
        logger.fine(Socket.EVENT_DISCONNECT);
        this.skipReconnect = true;
        this.reconnecting = false;
        if (this.readyState != ReadyState.OPEN) {
            cleanup();
        }
        this.backoff.reset();
        this.readyState = ReadyState.CLOSED;
        io.socket.engineio.client.Socket socket = this.engine;
        if (socket != null) {
            socket.close();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onclose(String str) {
        logger.fine("onclose");
        cleanup();
        this.backoff.reset();
        this.readyState = ReadyState.CLOSED;
        emit("close", str);
        if (!this._reconnection || this.skipReconnect) {
            return;
        }
        reconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reconnect() {
        if (this.reconnecting || this.skipReconnect) {
            return;
        }
        if (this.backoff.getAttempts() >= this._reconnectionAttempts) {
            logger.fine("reconnect failed");
            this.backoff.reset();
            emitAll("reconnect_failed", new Object[0]);
            this.reconnecting = false;
            return;
        }
        long duration = this.backoff.duration();
        logger.fine(String.format("will wait %dms before reconnect attempt", Long.valueOf(duration)));
        this.reconnecting = true;
        final Timer timer = new Timer();
        timer.schedule(new C1213311(this), duration);
        this.subs.add(new C12145On.Handle() { // from class: io.socket.client.Manager.12
            @Override // io.socket.client.C12145On.Handle
            public void destroy() {
                timer.cancel();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: io.socket.client.Manager$11 */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public class C1213311 extends TimerTask {
        final /* synthetic */ Manager val$self;

        C1213311(Manager manager) {
            this.val$self = manager;
        }

        @Override // java.util.TimerTask, java.lang.Runnable
        public void run() {
            EventThread.exec(new Runnable() { // from class: io.socket.client.Manager.11.1
                @Override // java.lang.Runnable
                public void run() {
                    if (C1213311.this.val$self.skipReconnect) {
                        return;
                    }
                    Manager.logger.fine("attempting reconnect");
                    int attempts = C1213311.this.val$self.backoff.getAttempts();
                    C1213311.this.val$self.emitAll("reconnect_attempt", Integer.valueOf(attempts));
                    C1213311.this.val$self.emitAll("reconnecting", Integer.valueOf(attempts));
                    if (C1213311.this.val$self.skipReconnect) {
                        return;
                    }
                    C1213311.this.val$self.open(new OpenCallback() { // from class: io.socket.client.Manager.11.1.1
                        @Override // io.socket.client.Manager.OpenCallback
                        public void call(Exception exc) {
                            if (exc != null) {
                                Manager.logger.fine("reconnect attempt error");
                                C1213311.this.val$self.reconnecting = false;
                                C1213311.this.val$self.reconnect();
                                C1213311.this.val$self.emitAll("reconnect_error", exc);
                                return;
                            }
                            Manager.logger.fine("reconnect success");
                            C1213311.this.val$self.onreconnect();
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onreconnect() {
        int attempts = this.backoff.getAttempts();
        this.reconnecting = false;
        this.backoff.reset();
        updateSocketIds();
        emitAll("reconnect", Integer.valueOf(attempts));
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class Engine extends io.socket.engineio.client.Socket {
        Engine(URI uri, Socket.Options options) {
            super(uri, options);
        }
    }
}
