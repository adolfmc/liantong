package com.mob.mgs.impl;

import com.mob.MobSDK;
import com.mob.tools.MobLog;
import com.mob.tools.utils.C6152DH;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* renamed from: com.mob.mgs.impl.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5959a {

    /* renamed from: a */
    private static C5959a f14672a = new C5959a();

    /* renamed from: b */
    private ExecutorService f14673b = Executors.newSingleThreadExecutor();

    /* renamed from: c */
    private ExecutorService f14674c = Executors.newSingleThreadExecutor();

    /* renamed from: d */
    private ConcurrentHashMap<String, LinkedBlockingQueue<Boolean>> f14675d = new ConcurrentHashMap<>();

    /* renamed from: e */
    private ConcurrentHashMap<Integer, String> f14676e = new ConcurrentHashMap<>();

    /* renamed from: f */
    private ConcurrentHashMap<Integer, SelectionKey> f14677f = new ConcurrentHashMap<>();

    /* renamed from: g */
    private Socket f14678g = null;

    /* renamed from: h */
    private int f14679h = 5;

    /* renamed from: i */
    private ScheduledExecutorService f14680i = Executors.newSingleThreadScheduledExecutor();

    /* renamed from: j */
    private ScheduledFuture f14681j;

    /* renamed from: g */
    static /* synthetic */ int m11928g(C5959a c5959a) {
        int i = c5959a.f14679h;
        c5959a.f14679h = i - 1;
        return i;
    }

    /* renamed from: a */
    public static C5959a m11949a() {
        return f14672a;
    }

    private C5959a() {
    }

    /* renamed from: a */
    public void m11940a(final BlockingQueue<Boolean> blockingQueue) {
        this.f14673b.execute(new Runnable() { // from class: com.mob.mgs.impl.a.1
            @Override // java.lang.Runnable
            public void run() {
                try {
                    ServerSocketChannel open = ServerSocketChannel.open();
                    open.configureBlocking(false);
                    open.socket().bind(new InetSocketAddress(59898));
                    C5994e.m11860a().m11859a("[GdCon] registerServerSocket success");
                    blockingQueue.offer(true);
                    Selector open2 = Selector.open();
                    open.register(open2, 16);
                    while (open2 != null) {
                        if (!open2.isOpen()) {
                            return;
                        }
                        if (open2.select() > 0) {
                            Iterator<SelectionKey> it = open2.selectedKeys().iterator();
                            while (it.hasNext()) {
                                SelectionKey next = it.next();
                                it.remove();
                                if (next.isValid() && next.isAcceptable()) {
                                    SocketChannel accept = ((ServerSocketChannel) next.channel()).accept();
                                    accept.configureBlocking(false);
                                    accept.register(open2, 1);
                                }
                                if (next.isValid() && next.isReadable()) {
                                    SocketChannel socketChannel = (SocketChannel) next.channel();
                                    ByteBuffer allocate = ByteBuffer.allocate(1024);
                                    int read = socketChannel.read(allocate);
                                    C5994e m11860a = C5994e.m11860a();
                                    m11860a.m11859a("[GdCon] serverSocket received bytes:" + read);
                                    if (read > 0) {
                                        String str = new String(allocate.array(), 0, read);
                                        C5994e m11860a2 = C5994e.m11860a();
                                        m11860a2.m11859a("[GdCon] serverSocket received msg:" + str);
                                        if (!"p".equals(str)) {
                                            if (str.startsWith("lg_")) {
                                                int port = ((InetSocketAddress) socketChannel.socket().getRemoteSocketAddress()).getPort();
                                                String substring = str.substring(3);
                                                C5959a.this.f14676e.put(Integer.valueOf(port), substring);
                                                C5959a.this.f14677f.put(Integer.valueOf(port), next);
                                                C5969c.m11922a().m11893b(substring);
                                            } else if (str.startsWith("chk_cb_")) {
                                                C5959a.this.m11943a(str.substring(7));
                                            }
                                        }
                                    } else {
                                        int port2 = ((InetSocketAddress) socketChannel.socket().getRemoteSocketAddress()).getPort();
                                        String str2 = (String) C5959a.this.f14676e.remove(Integer.valueOf(port2));
                                        C5959a.this.f14677f.remove(Integer.valueOf(port2));
                                        C5994e m11860a3 = C5994e.m11860a();
                                        m11860a3.m11859a("[GdCon] serverSocket received client disconnect pkg: " + str2);
                                        C5969c.m11922a().m11902a(str2, false);
                                        socketChannel.close();
                                    }
                                }
                            }
                        }
                    }
                } catch (Throwable th) {
                    C5994e m11860a4 = C5994e.m11860a();
                    m11860a4.m11859a("[GdCon] serverSocket exception: " + th.getMessage());
                    C5994e.m11860a().m11855b(th);
                    C5959a.this.m11936c();
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public void m11936c() {
        try {
            this.f14675d.clear();
            this.f14676e.clear();
            this.f14677f.clear();
        } catch (Throwable th) {
            C5994e.m11860a().m11855b(th);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11943a(String str) {
        LinkedBlockingQueue<Boolean> remove = this.f14675d.remove(str);
        if (remove != null) {
            remove.offer(true);
        }
    }

    /* renamed from: a */
    public int m11942a(String str, LinkedBlockingQueue linkedBlockingQueue) {
        int i = 0;
        for (Map.Entry<Integer, String> entry : this.f14676e.entrySet()) {
            if (entry.getValue().equals(str) && entry.getKey() != null) {
                this.f14675d.put(str, linkedBlockingQueue);
                SelectionKey selectionKey = this.f14677f.get(entry.getKey());
                if (selectionKey != null) {
                    i = m11941a(selectionKey) ? 1 : 2;
                }
            }
        }
        return i;
    }

    /* renamed from: a */
    private boolean m11941a(SelectionKey selectionKey) {
        try {
            if (selectionKey.isValid()) {
                ((SocketChannel) selectionKey.channel()).write(ByteBuffer.wrap("chk".getBytes("utf-8")));
                return true;
            }
            return false;
        } catch (Throwable th) {
            C5994e.m11860a().m11857a(th);
            return false;
        }
    }

    /* renamed from: b */
    public void m11938b() {
        this.f14673b.execute(new Runnable() { // from class: com.mob.mgs.impl.a.2
            @Override // java.lang.Runnable
            public void run() {
                try {
                    C6152DH.requester(MobSDK.getContext()).getIPAddress().request(new C6152DH.DHResponder() { // from class: com.mob.mgs.impl.a.2.1
                        @Override // com.mob.tools.utils.C6152DH.DHResponder
                        public void onResponse(C6152DH.DHResponse dHResponse) throws Throwable {
                            try {
                                if (C5959a.this.f14678g != null) {
                                    C5959a.this.f14678g.close();
                                    C5959a.this.f14678g = null;
                                }
                                C5959a.this.f14678g = new Socket(dHResponse.getIPAddress(), 59898);
                                if (C5959a.this.f14678g.isConnected()) {
                                    C5959a.this.f14679h = 5;
                                    C5994e.m11860a().m11859a("[GdCon] clientSocket connected");
                                    String packageName = MobSDK.getContext().getPackageName();
                                    OutputStream outputStream = C5959a.this.f14678g.getOutputStream();
                                    outputStream.write(("lg_" + packageName).getBytes("utf-8"));
                                    outputStream.flush();
                                    C5959a.this.m11934d();
                                    InputStream inputStream = C5959a.this.f14678g.getInputStream();
                                    while (C5959a.this.f14678g.isConnected() && !C5959a.this.f14678g.isClosed()) {
                                        byte[] bArr = new byte[1024];
                                        int read = inputStream.read(bArr);
                                        if (read == -1) {
                                            C5994e.m11860a().m11859a("[GdCon] client received server disconnect");
                                            C5959a.this.m11939a(false);
                                        } else {
                                            String str = new String(bArr, 0, read);
                                            C5994e m11860a = C5994e.m11860a();
                                            m11860a.m11859a("[GdCon] client received server msg: " + str);
                                            if ("chk".equals(str)) {
                                                String packageName2 = MobSDK.getContext().getPackageName();
                                                OutputStream outputStream2 = C5959a.this.f14678g.getOutputStream();
                                                outputStream2.write(("chk_cb_" + packageName2).getBytes("utf-8"));
                                                outputStream2.flush();
                                                C5994e m11860a2 = C5994e.m11860a();
                                                m11860a2.m11859a("[GdCon] client send alive check msg callback to server: chk_cb_" + packageName2);
                                            }
                                        }
                                    }
                                }
                            } catch (SocketException e) {
                                C5994e m11860a3 = C5994e.m11860a();
                                m11860a3.m11859a("[GdCon] client received socket exception: " + e.getMessage());
                                C5994e.m11860a().m11857a(e);
                                C5959a.this.m11939a(true);
                            }
                        }
                    });
                } catch (Throwable th) {
                    C5994e m11860a = C5994e.m11860a();
                    m11860a.m11859a("[GdCon] clientSocket exception: " + th.getMessage());
                    C5994e.m11860a().m11857a(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: a */
    public void m11939a(final boolean z) {
        C5994e m11860a = C5994e.m11860a();
        m11860a.m11859a("[GdCon] onServerDisconnect maxRegisterClientFailedCount: " + this.f14679h + ", isConnectException: " + z);
        m11930f();
        this.f14674c.execute(new Runnable() { // from class: com.mob.mgs.impl.a.3
            @Override // java.lang.Runnable
            public void run() {
                try {
                    if (C5969c.m11922a().m11892c()) {
                        LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
                        C5959a.this.m11940a(linkedBlockingQueue);
                        if (((Boolean) linkedBlockingQueue.take()).booleanValue()) {
                            C5969c.m11922a().m11902a((String) null, true);
                            return;
                        }
                    }
                    if (C5959a.this.f14679h > 0) {
                        if (z && C5959a.this.f14679h < 5) {
                            try {
                                Thread.sleep((5 - C5959a.this.f14679h) * 1000);
                            } catch (Throwable unused) {
                            }
                        }
                        C5959a.m11928g(C5959a.this);
                        C5959a.this.m11938b();
                    }
                } catch (Throwable th) {
                    C5994e.m11860a().m11857a(th);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public void m11934d() {
        try {
            m11932e();
            this.f14681j = this.f14680i.scheduleWithFixedDelay(new Runnable() { // from class: com.mob.mgs.impl.a.4
                @Override // java.lang.Runnable
                public void run() {
                    try {
                        C5994e m11860a = C5994e.m11860a();
                        m11860a.m11859a("[GdCon] p cli sct: " + C5959a.this.f14678g);
                        if (C5959a.this.f14678g == null || !C5959a.this.f14678g.isConnected()) {
                            return;
                        }
                        OutputStream outputStream = C5959a.this.f14678g.getOutputStream();
                        outputStream.write("p".getBytes());
                        outputStream.flush();
                    } catch (Throwable th) {
                        C5994e.m11860a().m11857a(th);
                    }
                }
            }, 0L, 240L, TimeUnit.SECONDS);
        } catch (Throwable th) {
            C5994e.m11860a().m11858a("[GdCon] HB timer error", th);
        }
    }

    /* renamed from: e */
    private boolean m11932e() {
        boolean z = false;
        try {
            if (this.f14681j != null) {
                z = this.f14681j.cancel(true);
                C5994e m11860a = C5994e.m11860a();
                m11860a.m11859a("[GdCon] HB restart, cancel: " + z);
                return z;
            }
            return false;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return z;
        }
    }

    /* renamed from: f */
    private void m11930f() {
        try {
            if (this.f14678g != null) {
                m11932e();
                this.f14678g.close();
                this.f14678g = null;
            }
        } catch (Throwable th) {
            C5994e.m11860a().m11855b(th);
        }
    }
}
