package com.networkbench.agent.impl.socket.p278a;

import java.text.ParseException;
import java.util.HashMap;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.socket.a.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public enum EnumC6602b {
    OK(0, null),
    ASSERTION_ERROR(1, "java.lang.AssertionError"),
    BIND_EXCEPTION(2, "java.net.BindException"),
    CLASS_NOT_FOUND_EXCEPTION(3, "java.lang.ClassNotFoundException"),
    ERROR(4, "java.lang.Error"),
    IO_EXCEPTION(5, "java.io.IOException"),
    ILLEGAL_ARGUMENT_EXCEPTION(6, "java.lang.IllegalArgumentException"),
    ILLEGAL_STATE_EXCEPTION(7, "java.lang.IllegalStateException"),
    INDEX_OUT_OF_BOUNDS_EXCEPTION(8, "java.lang.IndexOutOfBoundsException"),
    MALFORMED_URL_EXCEPTION(9, "java.net.MalformedURLException"),
    NO_SUCH_PROVIDER_EXCEPTION(10, "java.security.NoSuchProviderException"),
    NULL_POINTER_EXCEPTION(11, "java.lang.NullPointerException"),
    PROTOCOL_EXCEPTION(12, "java.net.ProtocolException"),
    SECURITY_EXCEPTION(13, "java.lang.SecurityException"),
    SOCKET_EXCEPTION(14, "java.net.SocketException"),
    SOCKET_TIMEOUT_EXCEPTION(15, "java.net.SocketTimeoutException"),
    SSL_PEER_UNVERIFIED_EXCEPTION(16, "javax.net.ssl.SSLPeerUnverifiedException"),
    STRING_INDEX_OUT_OF_BOUNDS_EXCEPTION(17, "java.lang.StringIndexOutOfBoundsException"),
    UNKNOWN_HOST_EXCEPTION(18, "java.net.UnknownHostException"),
    UNKNOWN_SERVICE_EXCEPTION(19, "java.net.UnknownServiceException"),
    UNSUPPORTED_OPERATION_EXCEPTION(20, "java.lang.UnsupportedOperationException"),
    URI_SYNTAX_EXCEPTION(21, "java.net.URISyntaxException"),
    CONNECT_EXCEPTION(22, "java.net.ConnectException"),
    SSL_EXCEPTION(23, "javax.net.ssl.SSLException"),
    SSL_HANDSHAKE_EXCEPTION(24, "javax.net.ssl.SSLHandshakeException"),
    SSL_KEY_EXCEPTION(25, "javax.net.ssl.SSLKeyException"),
    SSL_PROTOCOL_EXCEPTION(26, "javax.net.ssl.SSLProtocolException"),
    UNDEFINED_EXCEPTION(-1, "__UNKNOWN__");
    

    /* renamed from: C */
    private static HashMap<String, EnumC6602b> f16923C;

    /* renamed from: D */
    private int f16951D;

    /* renamed from: E */
    private String f16952E;

    EnumC6602b(int i, String str) {
        this.f16951D = i;
        this.f16952E = str;
    }

    /* renamed from: a */
    public int m9275a() {
        return this.f16951D;
    }

    /* renamed from: b */
    public String m9272b() {
        return this.f16952E;
    }

    /* renamed from: c */
    private static synchronized void m9271c() {
        EnumC6602b[] values;
        synchronized (EnumC6602b.class) {
            if (f16923C != null) {
                return;
            }
            HashMap<String, EnumC6602b> hashMap = new HashMap<>();
            for (EnumC6602b enumC6602b : values()) {
                hashMap.put(enumC6602b.f16952E, enumC6602b);
            }
            f16923C = hashMap;
        }
    }

    /* renamed from: a */
    public static EnumC6602b m9273a(Throwable th) {
        if (f16923C == null) {
            m9271c();
        }
        EnumC6602b enumC6602b = f16923C.get(th != null ? th.getClass().getName() : null);
        return enumC6602b == null ? UNDEFINED_EXCEPTION : enumC6602b;
    }

    /* renamed from: a */
    public static EnumC6602b m9274a(int i) throws ParseException {
        EnumC6602b[] values;
        for (EnumC6602b enumC6602b : values()) {
            if (enumC6602b.m9275a() == i) {
                return enumC6602b;
            }
        }
        throw new ParseException("Unknown status code: " + Integer.toString(i), 0);
    }
}
