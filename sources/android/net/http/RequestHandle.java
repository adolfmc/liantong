package android.net.http;

import android.net.compatibility.WebAddress;
import android.webkit.CookieManager;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import org.apache.commons.codec.binary.Base64;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public class RequestHandle {
    private static final String AUTHORIZATION_HEADER = "Authorization";
    public static final int MAX_REDIRECT_COUNT = 16;
    private static final String PROXY_AUTHORIZATION_HEADER = "Proxy-Authorization";
    private int mBodyLength;
    private InputStream mBodyProvider;
    private Connection mConnection;
    private Map<String, String> mHeaders;
    private String mMethod;
    private int mRedirectCount;
    private Request mRequest;
    private RequestQueue mRequestQueue;
    private WebAddress mUri;
    private String mUrl;

    public RequestHandle(RequestQueue requestQueue, String str, WebAddress webAddress, String str2, Map<String, String> map, InputStream inputStream, int i, Request request) {
        this.mRedirectCount = 0;
        this.mHeaders = map == null ? new HashMap<>() : map;
        this.mBodyProvider = inputStream;
        this.mBodyLength = i;
        this.mMethod = str2 == null ? "GET" : str2;
        this.mUrl = str;
        this.mUri = webAddress;
        this.mRequestQueue = requestQueue;
        this.mRequest = request;
    }

    public RequestHandle(RequestQueue requestQueue, String str, WebAddress webAddress, String str2, Map<String, String> map, InputStream inputStream, int i, Request request, Connection connection) {
        this(requestQueue, str, webAddress, str2, map, inputStream, i, request);
        this.mConnection = connection;
    }

    public void cancel() {
        Request request = this.mRequest;
        if (request != null) {
            request.cancel();
        }
    }

    public void pauseRequest(boolean z) {
        Request request = this.mRequest;
        if (request != null) {
            request.setLoadingPaused(z);
        }
    }

    public void handleSslErrorResponse(boolean z) {
        Request request = this.mRequest;
        if (request != null) {
            request.handleSslErrorResponse(z);
        }
    }

    public boolean isRedirectMax() {
        return this.mRedirectCount >= 16;
    }

    public int getRedirectCount() {
        return this.mRedirectCount;
    }

    public void setRedirectCount(int i) {
        this.mRedirectCount = i;
    }

    public boolean setupRedirect(String str, int i, Map<String, String> map) {
        String str2;
        this.mHeaders.remove("Authorization");
        this.mHeaders.remove("Proxy-Authorization");
        int i2 = this.mRedirectCount + 1;
        this.mRedirectCount = i2;
        if (i2 == 16) {
            this.mRequest.error(-9, "The page contains too many server redirects.");
            return false;
        }
        if (this.mUrl.startsWith("https:") && str.startsWith("http:")) {
            this.mHeaders.remove("Referer");
        }
        this.mUrl = str;
        try {
            this.mUri = new WebAddress(this.mUrl);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        this.mHeaders.remove("Cookie");
        if (this.mUri == null) {
            str2 = null;
        } else {
            str2 = CookieManager.getInstance().getCookie(this.mUri.toString());
        }
        if (str2 != null && str2.length() > 0) {
            this.mHeaders.put("Cookie", str2);
        }
        if ((i == 302 || i == 303) && this.mMethod.equals("POST")) {
            this.mMethod = "GET";
        }
        if (i == 307) {
            try {
                InputStream inputStream = this.mBodyProvider;
                if (inputStream != null) {
                    inputStream.reset();
                }
            } catch (IOException e2) {
                return false;
            }
        } else {
            this.mHeaders.remove("Content-Type");
            this.mBodyProvider = null;
        }
        this.mHeaders.putAll(map);
        createAndQueueNewRequest();
        return true;
    }

    public void setupBasicAuthResponse(boolean z, String str, String str2) {
        this.mHeaders.put(authorizationHeader(z), "Basic " + computeBasicAuthResponse(str, str2));
        setupAuthResponse();
    }

    public void setupDigestAuthResponse(boolean z, String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        this.mHeaders.put(authorizationHeader(z), "Digest " + computeDigestAuthResponse(str, str2, str3, str4, str5, str6, str7));
        setupAuthResponse();
    }

    private void setupAuthResponse() {
        try {
            InputStream inputStream = this.mBodyProvider;
            if (inputStream != null) {
                inputStream.reset();
            }
        } catch (IOException e) {
        }
        createAndQueueNewRequest();
    }

    public String getMethod() {
        return this.mMethod;
    }

    public static String computeBasicAuthResponse(String str, String str2) {
        if (str == null) {
            throw new NullPointerException("username == null");
        }
        if (str2 == null) {
            throw new NullPointerException("password == null");
        }
        return new String(Base64.encodeBase64((str + ':' + str2).getBytes()));
    }

    public void waitUntilComplete() {
        this.mRequest.waitUntilComplete();
    }

    public void processRequest() {
        Connection connection = this.mConnection;
        if (connection != null) {
            connection.processRequests(this.mRequest);
        }
    }

    private String computeDigestAuthResponse(String str, String str2, String str3, String str4, String str5, String str6, String str7) {
        String computeCnonce;
        if (str == null) {
            throw new NullPointerException("username == null");
        }
        if (str2 == null) {
            throw new NullPointerException("password == null");
        }
        if (str3 == null) {
            throw new NullPointerException("realm == null");
        }
        String computeDigest = computeDigest(str + ":" + str3 + ":" + str2, this.mMethod + ":" + this.mUrl, str4, str5, "00000001", computeCnonce());
        String str8 = (((("username=" + doubleQuote(str) + ", ") + "realm=" + doubleQuote(str3) + ", ") + "nonce=" + doubleQuote(str4) + ", ") + "uri=" + doubleQuote(this.mUrl) + ", ") + "response=" + doubleQuote(computeDigest);
        if (str7 != null) {
            str8 = str8 + ", opaque=" + doubleQuote(str7);
        }
        if (str6 != null) {
            str8 = str8 + ", algorithm=" + str6;
        }
        if (str5 != null) {
            return str8 + ", qop=" + str5 + ", nc=00000001, cnonce=" + doubleQuote(computeCnonce);
        }
        return str8;
    }

    public static String authorizationHeader(boolean z) {
        if (!z) {
            return "Authorization";
        }
        return "Proxy-Authorization";
    }

    private String computeDigest(String str, String str2, String str3, String str4, String str5, String str6) {
        if (str4 == null) {
            return m22211KD(m22212H(str), str3 + ":" + m22212H(str2));
        }
        if (str4.equalsIgnoreCase("auth")) {
            return m22211KD(m22212H(str), str3 + ":" + str5 + ":" + str6 + ":" + str4 + ":" + m22212H(str2));
        }
        return null;
    }

    /* renamed from: KD */
    private String m22211KD(String str, String str2) {
        return m22212H(str + ":" + str2);
    }

    /* renamed from: H */
    private String m22212H(String str) {
        if (str != null) {
            try {
                byte[] digest = MessageDigest.getInstance("MD5").digest(str.getBytes());
                if (digest != null) {
                    return bufferToHex(digest);
                }
                return null;
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

    private String bufferToHex(byte[] bArr) {
        char[] cArr = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};
        if (bArr != null) {
            int length = bArr.length;
            if (length > 0) {
                StringBuilder sb = new StringBuilder(length * 2);
                for (byte b : bArr) {
                    sb.append(cArr[(byte) ((b & 240) >> 4)]);
                    sb.append(cArr[(byte) (b & 15)]);
                }
                return sb.toString();
            }
            return "";
        }
        return null;
    }

    private String computeCnonce() {
        int nextInt = new Random().nextInt();
        return Integer.toString(nextInt == Integer.MIN_VALUE ? Integer.MAX_VALUE : Math.abs(nextInt), 16);
    }

    private String doubleQuote(String str) {
        if (str != null) {
            return "\"" + str + "\"";
        }
        return null;
    }

    private void createAndQueueNewRequest() {
        if (this.mConnection != null) {
            RequestHandle queueSynchronousRequest = this.mRequestQueue.queueSynchronousRequest(this.mUrl, this.mUri, this.mMethod, this.mHeaders, this.mRequest.mEventHandler, this.mBodyProvider, this.mBodyLength);
            this.mRequest = queueSynchronousRequest.mRequest;
            this.mConnection = queueSynchronousRequest.mConnection;
            queueSynchronousRequest.processRequest();
            return;
        }
        this.mRequest = this.mRequestQueue.queueRequest(this.mUrl, this.mUri, this.mMethod, this.mHeaders, this.mRequest.mEventHandler, this.mBodyProvider, this.mBodyLength).mRequest;
    }
}
