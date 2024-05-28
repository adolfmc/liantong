package android.net.http;

import android.content.Context;
import android.os.SystemClock;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import javax.net.ssl.SSLHandshakeException;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpHost;
import org.apache.http.HttpVersion;
import org.apache.http.ParseException;
import org.apache.http.ProtocolVersion;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class Connection {
    private static final int DONE = 3;
    private static final int DRAIN = 2;
    private static final String HTTP_CONNECTION = "http.connection";
    private static final int MAX_PIPE = 3;
    private static final int MIN_PIPE = 2;
    private static final int READ = 1;
    private static final int RETRY_REQUEST_LIMIT = 2;
    private static final int SEND = 0;
    static final int SOCKET_TIMEOUT = 60000;
    private byte[] mBuf;
    Context mContext;
    HttpHost mHost;
    RequestFeeder mRequestFeeder;
    private static final String[] states = {"SEND", "READ", "DRAIN", "DONE"};
    private static int STATE_NORMAL = 0;
    private static int STATE_CANCEL_REQUESTED = 1;
    AndroidHttpClientConnection mHttpClientConnection = null;
    SslCertificate mCertificate = null;
    private int mActive = STATE_NORMAL;
    private boolean mCanPersist = false;
    private HttpContext mHttpContext = new BasicHttpContext(null);

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void closeConnection();

    abstract String getScheme();

    abstract AndroidHttpClientConnection openConnection(Request request) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public Connection(Context context, HttpHost httpHost, RequestFeeder requestFeeder) {
        this.mContext = context;
        this.mHost = httpHost;
        this.mRequestFeeder = requestFeeder;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpHost getHost() {
        return this.mHost;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static Connection getConnection(Context context, HttpHost httpHost, HttpHost httpHost2, RequestFeeder requestFeeder) {
        if (httpHost.getSchemeName().equals("http")) {
            return new HttpConnection(context, httpHost, requestFeeder);
        }
        return new HttpsConnection(context, httpHost, httpHost2, requestFeeder);
    }

    SslCertificate getCertificate() {
        return this.mCertificate;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void cancel() {
        this.mActive = STATE_CANCEL_REQUESTED;
        closeConnection();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void processRequests(Request request) {
        Request request2;
        LinkedList<Request> linkedList = new LinkedList<>();
        char c = 2;
        Request request3 = request;
        int i = 2;
        IOException iOException = null;
        int i2 = 3;
        char c2 = 0;
        int i3 = 0;
        while (c2 != 3) {
            if (this.mActive == STATE_CANCEL_REQUESTED) {
                try {
                    Thread.sleep(100L);
                } catch (InterruptedException e) {
                }
                this.mActive = STATE_NORMAL;
            }
            switch (c2) {
                case 0:
                    if (linkedList.size() == i2) {
                        c2 = 1;
                        break;
                    } else {
                        if (request3 == null) {
                            request2 = request3;
                            request3 = this.mRequestFeeder.getRequest(this.mHost);
                        } else {
                            request2 = null;
                        }
                        if (request3 == null) {
                            request3 = request2;
                            c2 = 2;
                            break;
                        } else {
                            request3.setConnection(this);
                            if (request3.mCancelled) {
                                request3.complete();
                                request3 = request2;
                                break;
                            } else {
                                AndroidHttpClientConnection androidHttpClientConnection = this.mHttpClientConnection;
                                if ((androidHttpClientConnection != null && androidHttpClientConnection.isOpen()) || openHttpConnection(request3)) {
                                    request3.mEventHandler.certificate(this.mCertificate);
                                    try {
                                        request3.sendRequest(this.mHttpClientConnection);
                                    } catch (IOException e2) {
                                        iOException = e2;
                                        i3 = -7;
                                    } catch (IllegalStateException e3) {
                                        iOException = e3;
                                        i3 = -7;
                                    } catch (HttpException e4) {
                                        iOException = e4;
                                        i3 = -1;
                                    }
                                    if (iOException != null) {
                                        if (httpFailure(request3, i3, iOException) && !request3.mCancelled) {
                                            linkedList.addLast(request3);
                                        }
                                        c2 = clearPipe(linkedList) ? (char) 3 : (char) 0;
                                        request3 = request2;
                                        iOException = null;
                                        i2 = 1;
                                        i = 1;
                                        break;
                                    } else {
                                        linkedList.addLast(request3);
                                        request3 = request2;
                                        if (!this.mCanPersist) {
                                            c2 = 1;
                                            break;
                                        } else {
                                            break;
                                        }
                                    }
                                } else {
                                    request3 = request2;
                                    c2 = 3;
                                    break;
                                }
                            }
                        }
                    }
                    break;
                case 1:
                case 2:
                    boolean z = !this.mRequestFeeder.haveRequest(this.mHost);
                    int size = linkedList.size();
                    if (c2 != c && size < i && !z && this.mCanPersist) {
                        c2 = 0;
                        break;
                    } else if (size == 0) {
                        if (!z) {
                            c2 = 0;
                            break;
                        } else {
                            c2 = 3;
                            break;
                        }
                    } else {
                        Request removeFirst = linkedList.removeFirst();
                        try {
                            removeFirst.readResponse(this.mHttpClientConnection);
                        } catch (IOException e5) {
                            iOException = e5;
                            i3 = -7;
                        } catch (IllegalStateException e6) {
                            iOException = e6;
                            i3 = -7;
                        } catch (ParseException e7) {
                            iOException = e7;
                            i3 = -7;
                        }
                        if (iOException != null) {
                            if (httpFailure(removeFirst, i3, iOException) && !removeFirst.mCancelled) {
                                removeFirst.reset();
                                linkedList.addFirst(removeFirst);
                            }
                            this.mCanPersist = false;
                            iOException = null;
                        }
                        if (!this.mCanPersist) {
                            closeConnection();
                            this.mHttpContext.removeAttribute("http.connection");
                            clearPipe(linkedList);
                            c2 = 0;
                            i2 = 1;
                            i = 1;
                            break;
                        } else {
                            break;
                        }
                    }
            }
            c = 2;
        }
    }

    private boolean clearPipe(LinkedList<Request> linkedList) {
        boolean z;
        synchronized (this.mRequestFeeder) {
            z = true;
            while (!linkedList.isEmpty()) {
                this.mRequestFeeder.requeueRequest(linkedList.removeLast());
                z = false;
            }
            if (z) {
                z = !this.mRequestFeeder.haveRequest(this.mHost);
            }
        }
        return z;
    }

    private boolean openHttpConnection(Request request) {
        AndroidHttpClientConnection openConnection;
        SystemClock.uptimeMillis();
        int i = -6;
        Exception e = null;
        try {
            this.mCertificate = null;
            openConnection = openConnection(request);
            this.mHttpClientConnection = openConnection;
        } catch (SSLConnectionClosedByUserException e2) {
            request.mFailCount = 2;
            return false;
        } catch (IllegalArgumentException e3) {
            e = e3;
            request.mFailCount = 2;
        } catch (UnknownHostException e4) {
            e = e4;
            i = -2;
        } catch (SSLHandshakeException e5) {
            e = e5;
            request.mFailCount = 2;
            i = -11;
        } catch (IOException e6) {
            e = e6;
        }
        if (openConnection != null) {
            openConnection.setSocketTimeout(60000);
            this.mHttpContext.setAttribute("http.connection", this.mHttpClientConnection);
            i = 0;
            if (i == 0) {
                return true;
            }
            if (request.mFailCount < 2) {
                this.mRequestFeeder.requeueRequest(request);
                request.mFailCount++;
            } else {
                httpFailure(request, i, e);
            }
            return i == 0;
        }
        request.mFailCount = 2;
        return false;
    }

    private boolean httpFailure(Request request, int i, Exception exc) {
        String th;
        boolean z = true;
        int i2 = request.mFailCount + 1;
        request.mFailCount = i2;
        if (i2 >= 2) {
            z = false;
            if (i < 0) {
                th = getEventHandlerErrorString(i);
            } else {
                Throwable cause = exc.getCause();
                th = cause != null ? cause.toString() : exc.getMessage();
            }
            request.mEventHandler.error(i, th);
            request.complete();
        }
        closeConnection();
        this.mHttpContext.removeAttribute("http.connection");
        return z;
    }

    private static String getEventHandlerErrorString(int i) {
        switch (i) {
            case -15:
                return "TOO_MANY_REQUESTS_ERROR";
            case -14:
                return "FILE_NOT_FOUND_ERROR";
            case -13:
                return "FILE_ERROR";
            case -12:
                return "ERROR_BAD_URL";
            case -11:
                return "ERROR_FAILED_SSL_HANDSHAKE";
            case -10:
                return "ERROR_UNSUPPORTED_SCHEME";
            case -9:
                return "ERROR_REDIRECT_LOOP";
            case -8:
                return "ERROR_TIMEOUT";
            case -7:
                return "ERROR_IO";
            case -6:
                return "ERROR_CONNECT";
            case -5:
                return "ERROR_PROXYAUTH";
            case -4:
                return "ERROR_AUTH";
            case -3:
                return "ERROR_UNSUPPORTED_AUTH_SCHEME";
            case -2:
                return "ERROR_LOOKUP";
            case -1:
                return "ERROR";
            case 0:
                return "OK";
            default:
                return "UNKNOWN_ERROR";
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public HttpContext getHttpContext() {
        return this.mHttpContext;
    }

    private boolean keepAlive(HttpEntity httpEntity, ProtocolVersion protocolVersion, int i, HttpContext httpContext) {
        org.apache.http.HttpConnection httpConnection = (org.apache.http.HttpConnection) httpContext.getAttribute("http.connection");
        if (httpConnection != null && !httpConnection.isOpen()) {
            return false;
        }
        if ((httpEntity != null && httpEntity.getContentLength() < 0 && (!httpEntity.isChunked() || protocolVersion.lessEquals(HttpVersion.HTTP_1_0))) || i == 1) {
            return false;
        }
        if (i == 2) {
            return true;
        }
        return true ^ protocolVersion.lessEquals(HttpVersion.HTTP_1_0);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCanPersist(HttpEntity httpEntity, ProtocolVersion protocolVersion, int i) {
        this.mCanPersist = keepAlive(httpEntity, protocolVersion, i, this.mHttpContext);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setCanPersist(boolean z) {
        this.mCanPersist = z;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public boolean getCanPersist() {
        return this.mCanPersist;
    }

    public synchronized String toString() {
        return this.mHost.toString();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public byte[] getBuf() {
        if (this.mBuf == null) {
            this.mBuf = new byte[8192];
        }
        return this.mBuf;
    }
}
