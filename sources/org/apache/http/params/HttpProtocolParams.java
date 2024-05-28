package org.apache.http.params;

import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class HttpProtocolParams implements CoreProtocolPNames {
    private HttpProtocolParams() {
    }

    public static String getHttpElementCharset(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        String str = (String) httpParams.getParameter("http.protocol.element-charset");
        if (str == null) {
            return "US-ASCII";
        }
        return str;
    }

    public static void setHttpElementCharset(HttpParams httpParams, String str) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setParameter("http.protocol.element-charset", str);
    }

    public static String getContentCharset(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        String str = (String) httpParams.getParameter("http.protocol.content-charset");
        if (str == null) {
            return "ISO-8859-1";
        }
        return str;
    }

    public static void setContentCharset(HttpParams httpParams, String str) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setParameter("http.protocol.content-charset", str);
    }

    public static ProtocolVersion getVersion(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        Object parameter = httpParams.getParameter("http.protocol.version");
        if (parameter == null) {
            return HttpVersion.HTTP_1_1;
        }
        return (ProtocolVersion) parameter;
    }

    public static void setVersion(HttpParams httpParams, ProtocolVersion protocolVersion) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setParameter("http.protocol.version", protocolVersion);
    }

    public static String getUserAgent(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return (String) httpParams.getParameter("http.useragent");
    }

    public static void setUserAgent(HttpParams httpParams, String str) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setParameter("http.useragent", str);
    }

    public static boolean useExpectContinue(HttpParams httpParams) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        return httpParams.getBooleanParameter("http.protocol.expect-continue", false);
    }

    public static void setUseExpectContinue(HttpParams httpParams, boolean z) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        httpParams.setBooleanParameter("http.protocol.expect-continue", z);
    }
}
