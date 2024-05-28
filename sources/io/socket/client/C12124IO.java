package io.socket.client;

import io.socket.client.Manager;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Call;
import okhttp3.WebSocket;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: io.socket.client.IO */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C12124IO {
    private static final Logger logger = Logger.getLogger(C12124IO.class.getName());
    private static final ConcurrentHashMap<String, Manager> managers = new ConcurrentHashMap<>();
    public static int protocol = 4;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: io.socket.client.IO$Options */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Options extends Manager.Options {
        public boolean forceNew;
        public boolean multiplex = true;
    }

    public static void setDefaultOkHttpWebSocketFactory(WebSocket.Factory factory) {
        Manager.defaultWebSocketFactory = factory;
    }

    public static void setDefaultOkHttpCallFactory(Call.Factory factory) {
        Manager.defaultCallFactory = factory;
    }

    private C12124IO() {
    }

    public static Socket socket(String str) throws URISyntaxException {
        return socket(str, (Options) null);
    }

    public static Socket socket(String str, Options options) throws URISyntaxException {
        return socket(new URI(str), options);
    }

    public static Socket socket(URI uri) {
        return socket(uri, (Options) null);
    }

    public static Socket socket(URI uri, Options options) {
        Manager manager;
        if (options == null) {
            options = new Options();
        }
        URL parse = Url.parse(uri);
        try {
            URI uri2 = parse.toURI();
            String extractId = Url.extractId(parse);
            if (options.forceNew || !options.multiplex || (managers.containsKey(extractId) && managers.get(extractId).nsps.containsKey(parse.getPath()))) {
                if (logger.isLoggable(Level.FINE)) {
                    logger.fine(String.format("ignoring socket cache for %s", uri2));
                }
                manager = new Manager(uri2, options);
            } else {
                if (!managers.containsKey(extractId)) {
                    if (logger.isLoggable(Level.FINE)) {
                        logger.fine(String.format("new io instance for %s", uri2));
                    }
                    managers.putIfAbsent(extractId, new Manager(uri2, options));
                }
                manager = managers.get(extractId);
            }
            String query = parse.getQuery();
            if (query != null && (options.query == null || options.query.isEmpty())) {
                options.query = query;
            }
            return manager.socket(parse.getPath(), options);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
