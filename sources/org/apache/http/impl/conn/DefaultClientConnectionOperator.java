package org.apache.http.impl.conn;

import java.io.IOException;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.SocketException;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionOperator;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.OperatedClientConnection;
import org.apache.http.conn.scheme.LayeredSocketFactory;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class DefaultClientConnectionOperator implements ClientConnectionOperator {
    private static final PlainSocketFactory staticPlainSocketFactory = new PlainSocketFactory();
    protected SchemeRegistry schemeRegistry;

    public DefaultClientConnectionOperator(SchemeRegistry schemeRegistry) {
        if (schemeRegistry == null) {
            throw new IllegalArgumentException("Scheme registry must not be null.");
        }
        this.schemeRegistry = schemeRegistry;
    }

    @Override // org.apache.http.conn.ClientConnectionOperator
    public OperatedClientConnection createConnection() {
        return new DefaultClientConnection();
    }

    @Override // org.apache.http.conn.ClientConnectionOperator
    public void openConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, InetAddress inetAddress, HttpContext httpContext, HttpParams httpParams) throws IOException {
        LayeredSocketFactory layeredSocketFactory;
        PlainSocketFactory plainSocketFactory;
        int i;
        InetAddress[] inetAddressArr;
        SocketFactory socketFactory;
        int i2;
        int i3;
        InetAddress[] inetAddressArr2;
        ConnectException connectException;
        if (operatedClientConnection == null) {
            throw new IllegalArgumentException("Connection must not be null.");
        }
        if (httpHost == null) {
            throw new IllegalArgumentException("Target host must not be null.");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        if (operatedClientConnection.isOpen()) {
            throw new IllegalArgumentException("Connection must not be open.");
        }
        Scheme scheme = this.schemeRegistry.getScheme(httpHost.getSchemeName());
        SocketFactory socketFactory2 = scheme.getSocketFactory();
        if (socketFactory2 instanceof LayeredSocketFactory) {
            plainSocketFactory = staticPlainSocketFactory;
            layeredSocketFactory = (LayeredSocketFactory) socketFactory2;
        } else {
            layeredSocketFactory = null;
            plainSocketFactory = socketFactory2;
        }
        InetAddress[] allByName = InetAddress.getAllByName(httpHost.getHostName());
        int i4 = 0;
        while (i4 < allByName.length) {
            Socket createSocket = plainSocketFactory.createSocket();
            operatedClientConnection.opening(createSocket, httpHost);
            try {
                try {
                    try {
                        SocketFactory socketFactory3 = plainSocketFactory;
                        socketFactory = plainSocketFactory;
                        i2 = 1;
                        i3 = i4;
                        inetAddressArr2 = allByName;
                        try {
                            Socket connectSocket = socketFactory3.connectSocket(createSocket, allByName[i4].getHostAddress(), scheme.resolvePort(httpHost.getPort()), inetAddress, 0, httpParams);
                            if (createSocket != connectSocket) {
                                operatedClientConnection.opening(connectSocket, httpHost);
                                createSocket = connectSocket;
                            }
                            try {
                                prepareSocket(createSocket, httpContext, httpParams);
                                if (layeredSocketFactory != null) {
                                    Socket createSocket2 = layeredSocketFactory.createSocket(createSocket, httpHost.getHostName(), scheme.resolvePort(httpHost.getPort()), true);
                                    if (createSocket2 != createSocket) {
                                        operatedClientConnection.opening(createSocket2, httpHost);
                                    }
                                    operatedClientConnection.openCompleted(socketFactory2.isSecure(createSocket2), httpParams);
                                    return;
                                }
                                operatedClientConnection.openCompleted(socketFactory2.isSecure(createSocket), httpParams);
                                return;
                            } catch (SocketException e) {
                                e = e;
                                i = i3;
                                inetAddressArr = inetAddressArr2;
                                if (i != inetAddressArr.length - i2) {
                                    i4 = i + 1;
                                    allByName = inetAddressArr;
                                    plainSocketFactory = socketFactory;
                                } else {
                                    if (e instanceof ConnectException) {
                                        connectException = (ConnectException) e;
                                    } else {
                                        ConnectException connectException2 = new ConnectException(e.getMessage());
                                        connectException2.initCause(e);
                                        connectException = connectException2;
                                    }
                                    throw new HttpHostConnectException(httpHost, connectException);
                                }
                            } catch (ConnectTimeoutException e2) {
                                e = e2;
                                inetAddressArr = inetAddressArr2;
                                i = i3;
                                if (i != inetAddressArr.length - i2) {
                                    i4 = i + 1;
                                    allByName = inetAddressArr;
                                    plainSocketFactory = socketFactory;
                                } else {
                                    throw e;
                                }
                            }
                        } catch (SocketException e3) {
                            e = e3;
                        } catch (ConnectTimeoutException e4) {
                            e = e4;
                        }
                    } catch (SocketException e5) {
                        e = e5;
                        socketFactory = plainSocketFactory;
                        i2 = 1;
                        i = i4;
                        inetAddressArr = allByName;
                    }
                } catch (SocketException e6) {
                    e = e6;
                    i = i4;
                    inetAddressArr = allByName;
                    socketFactory = plainSocketFactory;
                    i2 = 1;
                }
            } catch (ConnectTimeoutException e7) {
                e = e7;
                i3 = i4;
                inetAddressArr2 = allByName;
                socketFactory = plainSocketFactory;
                i2 = 1;
            }
        }
    }

    @Override // org.apache.http.conn.ClientConnectionOperator
    public void updateSecureConnection(OperatedClientConnection operatedClientConnection, HttpHost httpHost, HttpContext httpContext, HttpParams httpParams) throws IOException {
        if (operatedClientConnection == null) {
            throw new IllegalArgumentException("Connection must not be null.");
        }
        if (httpHost == null) {
            throw new IllegalArgumentException("Target host must not be null.");
        }
        if (httpParams == null) {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
        if (!operatedClientConnection.isOpen()) {
            throw new IllegalArgumentException("Connection must be open.");
        }
        Scheme scheme = this.schemeRegistry.getScheme(httpHost.getSchemeName());
        if (!(scheme.getSocketFactory() instanceof LayeredSocketFactory)) {
            throw new IllegalArgumentException("Target scheme (" + scheme.getName() + ") must have layered socket factory.");
        }
        LayeredSocketFactory layeredSocketFactory = (LayeredSocketFactory) scheme.getSocketFactory();
        try {
            Socket createSocket = layeredSocketFactory.createSocket(operatedClientConnection.getSocket(), httpHost.getHostName(), scheme.resolvePort(httpHost.getPort()), true);
            prepareSocket(createSocket, httpContext, httpParams);
            operatedClientConnection.update(createSocket, httpHost, layeredSocketFactory.isSecure(createSocket), httpParams);
        } catch (ConnectException e) {
            throw new HttpHostConnectException(httpHost, e);
        }
    }

    protected void prepareSocket(Socket socket, HttpContext httpContext, HttpParams httpParams) throws IOException {
        socket.setTcpNoDelay(HttpConnectionParams.getTcpNoDelay(httpParams));
        socket.setSoTimeout(HttpConnectionParams.getSoTimeout(httpParams));
        int linger = HttpConnectionParams.getLinger(httpParams);
        if (linger >= 0) {
            socket.setSoLinger(linger > 0, linger);
        }
    }
}
