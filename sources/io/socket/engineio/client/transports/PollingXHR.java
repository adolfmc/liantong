package io.socket.engineio.client.transports;

import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import io.socket.emitter.Emitter;
import io.socket.engineio.client.Transport;
import io.socket.thread.EventThread;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class PollingXHR extends Polling {
    private static final Logger logger = Logger.getLogger(PollingXHR.class.getName());
    private static boolean LOGGABLE_FINE = logger.isLoggable(Level.FINE);

    public PollingXHR(Transport.Options options) {
        super(options);
    }

    protected Request request() {
        return request(null);
    }

    protected Request request(Request.Options options) {
        if (options == null) {
            options = new Request.Options();
        }
        options.uri = uri();
        options.callFactory = this.callFactory;
        Request request = new Request(options);
        request.m1930on("requestHeaders", new Emitter.Listener() { // from class: io.socket.engineio.client.transports.PollingXHR.2
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                this.emit("requestHeaders", objArr[0]);
            }
        }).m1930on("responseHeaders", new Emitter.Listener() { // from class: io.socket.engineio.client.transports.PollingXHR.1
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.transports.PollingXHR.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        this.emit("responseHeaders", objArr[0]);
                    }
                });
            }
        });
        return request;
    }

    @Override // io.socket.engineio.client.transports.Polling
    protected void doWrite(byte[] bArr, Runnable runnable) {
        doWrite((Object) bArr, runnable);
    }

    @Override // io.socket.engineio.client.transports.Polling
    protected void doWrite(String str, Runnable runnable) {
        doWrite((Object) str, runnable);
    }

    private void doWrite(Object obj, final Runnable runnable) {
        Request.Options options = new Request.Options();
        options.method = "POST";
        options.data = obj;
        Request request = request(options);
        request.m1930on("success", new Emitter.Listener() { // from class: io.socket.engineio.client.transports.PollingXHR.3
            @Override // io.socket.emitter.Emitter.Listener
            public void call(Object... objArr) {
                EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.transports.PollingXHR.3.1
                    @Override // java.lang.Runnable
                    public void run() {
                        runnable.run();
                    }
                });
            }
        });
        request.m1930on("error", new Emitter.Listener() { // from class: io.socket.engineio.client.transports.PollingXHR.4
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.transports.PollingXHR.4.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Object[] objArr2 = objArr;
                        this.onError("xhr post error", (objArr2.length <= 0 || !(objArr2[0] instanceof Exception)) ? null : (Exception) objArr2[0]);
                    }
                });
            }
        });
        request.create();
    }

    @Override // io.socket.engineio.client.transports.Polling
    protected void doPoll() {
        logger.fine("xhr poll");
        Request request = request();
        request.m1930on("data", new Emitter.Listener() { // from class: io.socket.engineio.client.transports.PollingXHR.5
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.transports.PollingXHR.5.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Object[] objArr2 = objArr;
                        String str = objArr2.length > 0 ? objArr2[0] : null;
                        if (str instanceof String) {
                            this.onData(str);
                        } else if (str instanceof byte[]) {
                            this.onData((byte[]) str);
                        }
                    }
                });
            }
        });
        request.m1930on("error", new Emitter.Listener() { // from class: io.socket.engineio.client.transports.PollingXHR.6
            @Override // io.socket.emitter.Emitter.Listener
            public void call(final Object... objArr) {
                EventThread.exec(new Runnable() { // from class: io.socket.engineio.client.transports.PollingXHR.6.1
                    @Override // java.lang.Runnable
                    public void run() {
                        Object[] objArr2 = objArr;
                        this.onError("xhr poll error", (objArr2.length <= 0 || !(objArr2[0] instanceof Exception)) ? null : (Exception) objArr2[0]);
                    }
                });
            }
        });
        request.create();
    }

    @NBSInstrumented
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class Request extends Emitter {
        private static final String BINARY_CONTENT_TYPE = "application/octet-stream";
        public static final String EVENT_DATA = "data";
        public static final String EVENT_ERROR = "error";
        public static final String EVENT_REQUEST_HEADERS = "requestHeaders";
        public static final String EVENT_RESPONSE_HEADERS = "responseHeaders";
        public static final String EVENT_SUCCESS = "success";
        private Call.Factory callFactory;
        private Object data;
        private String method;
        private Call requestCall;
        private Response response;
        private String uri;
        private static final MediaType BINARY_MEDIA_TYPE = MediaType.parse("application/octet-stream");
        private static final String TEXT_CONTENT_TYPE = "text/plain;charset=UTF-8";
        private static final MediaType TEXT_MEDIA_TYPE = MediaType.parse(TEXT_CONTENT_TYPE);

        /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11617560_dexfile_execute.dex */
        public static class Options {
            public Call.Factory callFactory;
            public Object data;
            public String method;
            public String uri;
        }

        public Request(Options options) {
            this.method = options.method != null ? options.method : "GET";
            this.uri = options.uri;
            this.data = options.data;
            this.callFactory = options.callFactory != null ? options.callFactory : NBSOkHttp3Instrumentation.init();
        }

        public void create() {
            if (PollingXHR.LOGGABLE_FINE) {
                PollingXHR.logger.fine(String.format("xhr open %s: %s", this.method, this.uri));
            }
            TreeMap treeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
            if ("POST".equals(this.method)) {
                if (this.data instanceof byte[]) {
                    treeMap.put("Content-type", new LinkedList(Collections.singletonList("application/octet-stream")));
                } else {
                    treeMap.put("Content-type", new LinkedList(Collections.singletonList(TEXT_CONTENT_TYPE)));
                }
            }
            treeMap.put("Accept", new LinkedList(Collections.singletonList("*/*")));
            onRequestHeaders(treeMap);
            if (PollingXHR.LOGGABLE_FINE) {
                Logger logger = PollingXHR.logger;
                Object[] objArr = new Object[2];
                objArr[0] = this.uri;
                Object obj = this.data;
                if (obj instanceof byte[]) {
                    obj = Arrays.toString((byte[]) obj);
                }
                objArr[1] = obj;
                logger.fine(String.format("sending xhr with url %s | data %s", objArr));
            }
            Request.Builder builder = new Request.Builder();
            for (Map.Entry<String, List<String>> entry : treeMap.entrySet()) {
                for (String str : entry.getValue()) {
                    builder.addHeader(entry.getKey(), str);
                }
            }
            RequestBody requestBody = null;
            Object obj2 = this.data;
            if (obj2 instanceof byte[]) {
                requestBody = RequestBody.create(BINARY_MEDIA_TYPE, (byte[]) obj2);
            } else if (obj2 instanceof String) {
                requestBody = RequestBody.create(TEXT_MEDIA_TYPE, (String) obj2);
            }
            this.requestCall = this.callFactory.newCall(builder.url(HttpUrl.parse(this.uri)).method(this.method, requestBody).build());
            this.requestCall.enqueue(new Callback() { // from class: io.socket.engineio.client.transports.PollingXHR.Request.1
                @Override // okhttp3.Callback
                public void onFailure(Call call, IOException iOException) {
                    this.onError(iOException);
                }

                @Override // okhttp3.Callback
                public void onResponse(Call call, Response response) throws IOException {
                    this.response = response;
                    this.onResponseHeaders(response.headers().toMultimap());
                    try {
                        if (response.isSuccessful()) {
                            this.onLoad();
                        } else {
                            this.onError(new IOException(Integer.toString(response.code())));
                        }
                    } finally {
                        response.close();
                    }
                }
            });
        }

        private void onSuccess() {
            emit("success", new Object[0]);
        }

        private void onData(String str) {
            emit("data", str);
            onSuccess();
        }

        private void onData(byte[] bArr) {
            emit("data", bArr);
            onSuccess();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onError(Exception exc) {
            emit("error", exc);
        }

        private void onRequestHeaders(Map<String, List<String>> map) {
            emit("requestHeaders", map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onResponseHeaders(Map<String, List<String>> map) {
            emit("responseHeaders", map);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void onLoad() {
            ResponseBody body = this.response.body();
            try {
                if ("application/octet-stream".equalsIgnoreCase(body.contentType().toString())) {
                    onData(body.bytes());
                } else {
                    onData(body.string());
                }
            } catch (IOException e) {
                onError(e);
            }
        }
    }
}
