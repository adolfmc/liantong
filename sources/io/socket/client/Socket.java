package io.socket.client;

import io.socket.client.C12145On;
import io.socket.client.Manager;
import io.socket.emitter.Emitter;
import io.socket.parser.Packet;
import io.socket.thread.EventThread;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class Socket extends Emitter {
    public static final String EVENT_CONNECT = "connect";
    public static final String EVENT_CONNECTING = "connecting";
    public static final String EVENT_CONNECT_ERROR = "connect_error";
    public static final String EVENT_CONNECT_TIMEOUT = "connect_timeout";
    public static final String EVENT_DISCONNECT = "disconnect";
    public static final String EVENT_ERROR = "error";
    public static final String EVENT_MESSAGE = "message";
    public static final String EVENT_PING = "ping";
    public static final String EVENT_PONG = "pong";
    public static final String EVENT_RECONNECT = "reconnect";
    public static final String EVENT_RECONNECTING = "reconnecting";
    public static final String EVENT_RECONNECT_ATTEMPT = "reconnect_attempt";
    public static final String EVENT_RECONNECT_ERROR = "reconnect_error";
    public static final String EVENT_RECONNECT_FAILED = "reconnect_failed";
    private volatile boolean connected;

    /* renamed from: id */
    String f24814id;
    private int ids;

    /* renamed from: io */
    private Manager f24815io;
    private String nsp;
    private String query;
    private Queue<C12145On.Handle> subs;
    private static final Logger logger = Logger.getLogger(Socket.class.getName());
    protected static Map<String, Integer> events = new HashMap<String, Integer>() { // from class: io.socket.client.Socket.1
        {
            put("connect", 1);
            put("connect_error", 1);
            put("connect_timeout", 1);
            put(Socket.EVENT_CONNECTING, 1);
            put(Socket.EVENT_DISCONNECT, 1);
            put("error", 1);
            put("reconnect", 1);
            put("reconnect_attempt", 1);
            put("reconnect_failed", 1);
            put("reconnect_error", 1);
            put("reconnecting", 1);
            put("ping", 1);
            put("pong", 1);
        }
    };
    private Map<Integer, Ack> acks = new HashMap();
    private final Queue<List<Object>> receiveBuffer = new LinkedList();
    private final Queue<Packet<JSONArray>> sendBuffer = new LinkedList();

    static /* synthetic */ int access$708(Socket socket) {
        int i = socket.ids;
        socket.ids = i + 1;
        return i;
    }

    public Socket(Manager manager, String str, Manager.Options options) {
        this.f24815io = manager;
        this.nsp = str;
        if (options != null) {
            this.query = options.query;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void subEvents() {
        if (this.subs != null) {
            return;
        }
        final Manager manager = this.f24815io;
        this.subs = new LinkedList<C12145On.Handle>() { // from class: io.socket.client.Socket.2
            {
                add(C12145On.m1933on(manager, "open", new Emitter.Listener() { // from class: io.socket.client.Socket.2.1
                    @Override // io.socket.emitter.Emitter.Listener
                    public void call(Object... objArr) {
                        Socket.this.onopen();
                    }
                }));
                add(C12145On.m1933on(manager, "packet", new Emitter.Listener() { // from class: io.socket.client.Socket.2.2
                    @Override // io.socket.emitter.Emitter.Listener
                    public void call(Object... objArr) {
                        Socket.this.onpacket((Packet) objArr[0]);
                    }
                }));
                add(C12145On.m1933on(manager, "close", new Emitter.Listener() { // from class: io.socket.client.Socket.2.3
                    @Override // io.socket.emitter.Emitter.Listener
                    public void call(Object... objArr) {
                        Socket.this.onclose(objArr.length > 0 ? (String) objArr[0] : null);
                    }
                }));
            }
        };
    }

    public Socket open() {
        EventThread.exec(new Runnable() { // from class: io.socket.client.Socket.3
            @Override // java.lang.Runnable
            public void run() {
                if (Socket.this.connected) {
                    return;
                }
                Socket.this.subEvents();
                Socket.this.f24815io.open();
                if (Manager.ReadyState.OPEN == Socket.this.f24815io.readyState) {
                    Socket.this.onopen();
                }
                Socket.this.emit(Socket.EVENT_CONNECTING, new Object[0]);
            }
        });
        return this;
    }

    public Socket connect() {
        return open();
    }

    public Socket send(final Object... objArr) {
        EventThread.exec(new Runnable() { // from class: io.socket.client.Socket.4
            @Override // java.lang.Runnable
            public void run() {
                Socket.this.emit("message", objArr);
            }
        });
        return this;
    }

    @Override // io.socket.emitter.Emitter
    public Emitter emit(final String str, final Object... objArr) {
        EventThread.exec(new Runnable() { // from class: io.socket.client.Socket.5
            @Override // java.lang.Runnable
            public void run() {
                Object[] objArr2;
                Ack ack;
                if (Socket.events.containsKey(str)) {
                    Socket.super.emit(str, objArr);
                    return;
                }
                Object[] objArr3 = objArr;
                int length = objArr3.length - 1;
                if (objArr3.length > 0 && (objArr3[length] instanceof Ack)) {
                    objArr2 = new Object[length];
                    for (int i = 0; i < length; i++) {
                        objArr2[i] = objArr[i];
                    }
                    ack = (Ack) objArr[length];
                } else {
                    objArr2 = objArr;
                    ack = null;
                }
                Socket.this.emit(str, objArr2, ack);
            }
        });
        return this;
    }

    public Emitter emit(final String str, final Object[] objArr, final Ack ack) {
        EventThread.exec(new Runnable() { // from class: io.socket.client.Socket.6
            @Override // java.lang.Runnable
            public void run() {
                JSONArray jSONArray = new JSONArray();
                jSONArray.put(str);
                Object[] objArr2 = objArr;
                if (objArr2 != null) {
                    for (Object obj : objArr2) {
                        jSONArray.put(obj);
                    }
                }
                Packet packet = new Packet(2, jSONArray);
                if (ack != null) {
                    Socket.logger.fine(String.format("emitting packet with ack id %d", Integer.valueOf(Socket.this.ids)));
                    Socket.this.acks.put(Integer.valueOf(Socket.this.ids), ack);
                    packet.f24819id = Socket.access$708(Socket.this);
                }
                if (Socket.this.connected) {
                    Socket.this.packet(packet);
                } else {
                    Socket.this.sendBuffer.add(packet);
                }
            }
        });
        return this;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void packet(Packet packet) {
        packet.nsp = this.nsp;
        this.f24815io.packet(packet);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onopen() {
        logger.fine("transport is open - connecting");
        if ("/".equals(this.nsp)) {
            return;
        }
        String str = this.query;
        if (str != null && !str.isEmpty()) {
            Packet packet = new Packet(0);
            packet.query = this.query;
            packet(packet);
            return;
        }
        packet(new Packet(0));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onclose(String str) {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("close (%s)", str));
        }
        this.connected = false;
        this.f24814id = null;
        emit(EVENT_DISCONNECT, str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onpacket(Packet<?> packet) {
        if (this.nsp.equals(packet.nsp)) {
            switch (packet.type) {
                case 0:
                    onconnect();
                    return;
                case 1:
                    ondisconnect();
                    return;
                case 2:
                    onevent(packet);
                    return;
                case 3:
                    onack(packet);
                    return;
                case 4:
                    emit("error", packet.data);
                    return;
                case 5:
                    onevent(packet);
                    return;
                case 6:
                    onack(packet);
                    return;
                default:
                    return;
            }
        }
    }

    private void onevent(Packet<JSONArray> packet) {
        ArrayList arrayList = new ArrayList(Arrays.asList(toArray(packet.data)));
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("emitting event %s", arrayList));
        }
        if (packet.f24819id >= 0) {
            logger.fine("attaching ack callback to event");
            arrayList.add(ack(packet.f24819id));
        }
        if (this.connected) {
            if (arrayList.isEmpty()) {
                return;
            }
            super.emit(arrayList.remove(0).toString(), arrayList.toArray());
            return;
        }
        this.receiveBuffer.add(arrayList);
    }

    private Ack ack(final int i) {
        final boolean[] zArr = {false};
        return new Ack() { // from class: io.socket.client.Socket.7
            @Override // io.socket.client.Ack
            public void call(final Object... objArr) {
                EventThread.exec(new Runnable() { // from class: io.socket.client.Socket.7.1
                    @Override // java.lang.Runnable
                    public void run() {
                        if (zArr[0]) {
                            return;
                        }
                        zArr[0] = true;
                        if (Socket.logger.isLoggable(Level.FINE)) {
                            Logger logger2 = Socket.logger;
                            Object[] objArr2 = objArr;
                            if (objArr2.length == 0) {
                                objArr2 = null;
                            }
                            logger2.fine(String.format("sending ack %s", objArr2));
                        }
                        JSONArray jSONArray = new JSONArray();
                        for (Object obj : objArr) {
                            jSONArray.put(obj);
                        }
                        Packet packet = new Packet(3, jSONArray);
                        packet.f24819id = i;
                        this.packet(packet);
                    }
                });
            }
        };
    }

    private void onack(Packet<JSONArray> packet) {
        Ack remove = this.acks.remove(Integer.valueOf(packet.f24819id));
        if (remove != null) {
            if (logger.isLoggable(Level.FINE)) {
                logger.fine(String.format("calling ack %s with %s", Integer.valueOf(packet.f24819id), packet.data));
            }
            remove.call(toArray(packet.data));
        } else if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("bad ack %s", Integer.valueOf(packet.f24819id)));
        }
    }

    private void onconnect() {
        this.connected = true;
        emit("connect", new Object[0]);
        emitBuffered();
    }

    private void emitBuffered() {
        while (true) {
            List<Object> poll = this.receiveBuffer.poll();
            if (poll == null) {
                break;
            }
            super.emit((String) poll.get(0), poll.toArray());
        }
        this.receiveBuffer.clear();
        while (true) {
            Packet<JSONArray> poll2 = this.sendBuffer.poll();
            if (poll2 != null) {
                packet(poll2);
            } else {
                this.sendBuffer.clear();
                return;
            }
        }
    }

    private void ondisconnect() {
        if (logger.isLoggable(Level.FINE)) {
            logger.fine(String.format("server disconnect (%s)", this.nsp));
        }
        destroy();
        onclose("io server disconnect");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void destroy() {
        Queue<C12145On.Handle> queue = this.subs;
        if (queue != null) {
            for (C12145On.Handle handle : queue) {
                handle.destroy();
            }
            this.subs = null;
        }
        this.f24815io.destroy(this);
    }

    public Socket close() {
        EventThread.exec(new Runnable() { // from class: io.socket.client.Socket.8
            @Override // java.lang.Runnable
            public void run() {
                if (Socket.this.connected) {
                    if (Socket.logger.isLoggable(Level.FINE)) {
                        Socket.logger.fine(String.format("performing disconnect (%s)", Socket.this.nsp));
                    }
                    Socket.this.packet(new Packet(1));
                }
                Socket.this.destroy();
                if (Socket.this.connected) {
                    Socket.this.onclose("io client disconnect");
                }
            }
        });
        return this;
    }

    public Socket disconnect() {
        return close();
    }

    /* renamed from: io */
    public Manager m1931io() {
        return this.f24815io;
    }

    public boolean connected() {
        return this.connected;
    }

    /* renamed from: id */
    public String m1932id() {
        return this.f24814id;
    }

    private static Object[] toArray(JSONArray jSONArray) {
        Object obj;
        int length = jSONArray.length();
        Object[] objArr = new Object[length];
        for (int i = 0; i < length; i++) {
            Object obj2 = null;
            try {
                obj = jSONArray.get(i);
            } catch (JSONException e) {
                logger.log(Level.WARNING, "An error occured while retrieving data from JSONArray", (Throwable) e);
                obj = null;
            }
            if (!JSONObject.NULL.equals(obj)) {
                obj2 = obj;
            }
            objArr[i] = obj2;
        }
        return objArr;
    }
}
