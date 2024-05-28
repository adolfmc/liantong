package android.net.http;

import android.content.Context;
import android.util.Log;
import com.android.org.conscrypt.FileClientSessionCache;
import com.android.org.conscrypt.SSLClientSessionCache;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.cert.X509Certificate;
import java.util.Locale;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.apache.http.Header;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.StatusLine;
import org.apache.http.message.BasicHttpRequest;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpsConnection extends Connection {
    private static SSLSocketFactory mSslSocketFactory = null;
    private boolean mAborted;
    private HttpHost mProxyHost;
    private Object mSuspendLock;
    private boolean mSuspended;

    static {
        initializeEngine(null);
    }

    public static void initializeEngine(File file) {
        SSLClientSessionCache usingDirectory;
        if (file == null) {
            usingDirectory = null;
        } else {
            try {
                Log.d("HttpsConnection", "Caching SSL sessions in " + file + ".");
                usingDirectory = FileClientSessionCache.usingDirectory(file);
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (KeyManagementException | NoSuchAlgorithmException | NoSuchProviderException e2) {
                throw new RuntimeException(e2);
            }
        }
        SSLContext sSLContext = SSLContext.getInstance("TLS", "AndroidOpenSSL");
        sSLContext.init(null, new TrustManager[]{new X509TrustManager() { // from class: android.net.http.HttpsConnection.1
            @Override // javax.net.ssl.X509TrustManager
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkClientTrusted(X509Certificate[] x509CertificateArr, String str) {
            }

            @Override // javax.net.ssl.X509TrustManager
            public void checkServerTrusted(X509Certificate[] x509CertificateArr, String str) {
            }
        }}, null);
        sSLContext.getClientSessionContext().setPersistentCache(usingDirectory);
        synchronized (HttpsConnection.class) {
            mSslSocketFactory = sSLContext.getSocketFactory();
        }
    }

    private static synchronized SSLSocketFactory getSocketFactory() {
        SSLSocketFactory sSLSocketFactory;
        synchronized (HttpsConnection.class) {
            sSLSocketFactory = mSslSocketFactory;
        }
        return sSLSocketFactory;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpsConnection(Context context, HttpHost httpHost, HttpHost httpHost2, RequestFeeder requestFeeder) {
        super(context, httpHost, requestFeeder);
        this.mSuspendLock = new Object();
        this.mSuspended = false;
        this.mAborted = false;
        this.mProxyHost = httpHost2;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCertificate(SslCertificate sslCertificate) {
        this.mCertificate = sslCertificate;
    }

    @Override // android.net.http.Connection
    AndroidHttpClientConnection openConnection(Request request) throws IOException {
        Socket socket;
        AndroidHttpClientConnection androidHttpClientConnection;
        Header[] allHeaders;
        StatusLine parseResponseHeader;
        int statusCode;
        SSLSocket sSLSocket;
        AndroidHttpClientConnection androidHttpClientConnection2 = null;
        SSLSocket sSLSocket2 = null;
        if (this.mProxyHost != null) {
            try {
                socket = new Socket(this.mProxyHost.getHostName(), this.mProxyHost.getPort());
                socket.setSoTimeout(60000);
                androidHttpClientConnection = new AndroidHttpClientConnection();
            } catch (IOException e) {
                e = e;
            }
            try {
                BasicHttpParams basicHttpParams = new BasicHttpParams();
                HttpConnectionParams.setSocketBufferSize(basicHttpParams, 8192);
                androidHttpClientConnection.bind(socket, basicHttpParams);
                Headers headers = new Headers();
                try {
                    BasicHttpRequest basicHttpRequest = new BasicHttpRequest("CONNECT", this.mHost.toHostString());
                    for (Header header : request.mHttpRequest.getAllHeaders()) {
                        String lowerCase = header.getName().toLowerCase(Locale.ROOT);
                        if (lowerCase.startsWith("proxy") || lowerCase.equals("keep-alive") || lowerCase.equals("host")) {
                            basicHttpRequest.addHeader(header);
                        }
                    }
                    androidHttpClientConnection.sendRequestHeader(basicHttpRequest);
                    androidHttpClientConnection.flush();
                    do {
                        parseResponseHeader = androidHttpClientConnection.parseResponseHeader(headers);
                        statusCode = parseResponseHeader.getStatusCode();
                    } while (statusCode < 200);
                    if (statusCode == 200) {
                        try {
                            sSLSocket = (SSLSocket) getSocketFactory().createSocket(socket, this.mHost.getHostName(), this.mHost.getPort(), true);
                        } catch (IOException e2) {
                            String message = e2.getMessage();
                            if (message == null) {
                                message = "failed to create an SSL socket";
                            }
                            throw new IOException(message);
                        }
                    } else {
                        ProtocolVersion protocolVersion = parseResponseHeader.getProtocolVersion();
                        request.mEventHandler.status(protocolVersion.getMajor(), protocolVersion.getMinor(), statusCode, parseResponseHeader.getReasonPhrase());
                        request.mEventHandler.headers(headers);
                        request.mEventHandler.endData();
                        androidHttpClientConnection.close();
                        return null;
                    }
                } catch (IOException e3) {
                    String message2 = e3.getMessage();
                    if (message2 == null) {
                        message2 = "failed to send a CONNECT request";
                    }
                    throw new IOException(message2);
                } catch (HttpException e4) {
                    String message3 = e4.getMessage();
                    if (message3 == null) {
                        message3 = "failed to send a CONNECT request";
                    }
                    throw new IOException(message3);
                } catch (ParseException e5) {
                    String message4 = e5.getMessage();
                    if (message4 == null) {
                        message4 = "failed to send a CONNECT request";
                    }
                    throw new IOException(message4);
                }
            } catch (IOException e6) {
                e = e6;
                androidHttpClientConnection2 = androidHttpClientConnection;
                if (androidHttpClientConnection2 != null) {
                    androidHttpClientConnection2.close();
                }
                String message5 = e.getMessage();
                if (message5 == null) {
                    message5 = "failed to establish a connection to the proxy";
                }
                throw new IOException(message5);
            }
        } else {
            try {
                sSLSocket = (SSLSocket) getSocketFactory().createSocket(this.mHost.getHostName(), this.mHost.getPort());
                try {
                    sSLSocket.setSoTimeout(60000);
                } catch (IOException e7) {
                    e = e7;
                    sSLSocket2 = sSLSocket;
                    if (sSLSocket2 != null) {
                        sSLSocket2.close();
                    }
                    String message6 = e.getMessage();
                    if (message6 == null) {
                        message6 = "failed to create an SSL socket";
                    }
                    throw new IOException(message6);
                }
            } catch (IOException e8) {
                e = e8;
            }
        }
        SslError doHandshakeAndValidateServerCertificates = CertificateChainValidator.getInstance().doHandshakeAndValidateServerCertificates(this, sSLSocket, this.mHost.getHostName());
        if (doHandshakeAndValidateServerCertificates != null) {
            synchronized (this.mSuspendLock) {
                this.mSuspended = true;
            }
            if (!request.getEventHandler().handleSslErrorRequest(doHandshakeAndValidateServerCertificates)) {
                throw new IOException("failed to handle " + doHandshakeAndValidateServerCertificates);
            }
            synchronized (this.mSuspendLock) {
                if (this.mSuspended) {
                    try {
                        this.mSuspendLock.wait(600000L);
                        if (this.mSuspended) {
                            this.mSuspended = false;
                            this.mAborted = true;
                        }
                    } catch (InterruptedException e9) {
                    }
                }
                if (this.mAborted) {
                    sSLSocket.close();
                    throw new SSLConnectionClosedByUserException("connection closed by the user");
                }
            }
        }
        AndroidHttpClientConnection androidHttpClientConnection3 = new AndroidHttpClientConnection();
        BasicHttpParams basicHttpParams2 = new BasicHttpParams();
        basicHttpParams2.setIntParameter("http.socket.buffer-size", 8192);
        androidHttpClientConnection3.bind(sSLSocket, basicHttpParams2);
        return androidHttpClientConnection3;
    }

    @Override // android.net.http.Connection
    void closeConnection() {
        if (this.mSuspended) {
            restartConnection(false);
        }
        try {
            if (this.mHttpClientConnection != null && this.mHttpClientConnection.isOpen()) {
                this.mHttpClientConnection.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void restartConnection(boolean z) {
        synchronized (this.mSuspendLock) {
            if (this.mSuspended) {
                this.mSuspended = false;
                this.mAborted = z ? false : true;
                this.mSuspendLock.notify();
            }
        }
    }

    @Override // android.net.http.Connection
    String getScheme() {
        return "https";
    }
}
