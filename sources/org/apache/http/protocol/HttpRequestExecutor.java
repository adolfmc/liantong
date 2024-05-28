package org.apache.http.protocol;

import java.io.IOException;
import java.net.ProtocolException;
import org.apache.http.HttpClientConnection;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.ProtocolVersion;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpRequestExecutor {
    protected boolean canResponseHaveBody(HttpRequest httpRequest, HttpResponse httpResponse) {
        int statusCode;
        return ("HEAD".equalsIgnoreCase(httpRequest.getRequestLine().getMethod()) || (statusCode = httpResponse.getStatusLine().getStatusCode()) < 200 || statusCode == 204 || statusCode == 304 || statusCode == 205) ? false : true;
    }

    public HttpResponse execute(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws IOException, HttpException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpClientConnection == null) {
            throw new IllegalArgumentException("Client connection may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        try {
            HttpResponse doSendRequest = doSendRequest(httpRequest, httpClientConnection, httpContext);
            if (doSendRequest == null) {
                return doReceiveResponse(httpRequest, httpClientConnection, httpContext);
            }
            return doSendRequest;
        } catch (IOException e) {
            httpClientConnection.close();
            throw e;
        } catch (RuntimeException e2) {
            httpClientConnection.close();
            throw e2;
        } catch (HttpException e3) {
            httpClientConnection.close();
            throw e3;
        }
    }

    public void preProcess(HttpRequest httpRequest, HttpProcessor httpProcessor, HttpContext httpContext) throws HttpException, IOException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpProcessor == null) {
            throw new IllegalArgumentException("HTTP processor may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        httpProcessor.process(httpRequest, httpContext);
    }

    protected HttpResponse doSendRequest(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws IOException, HttpException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpClientConnection == null) {
            throw new IllegalArgumentException("HTTP connection may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        httpContext.setAttribute("http.request_sent", Boolean.FALSE);
        httpClientConnection.sendRequestHeader(httpRequest);
        HttpResponse httpResponse = null;
        if (httpRequest instanceof HttpEntityEnclosingRequest) {
            boolean z = true;
            ProtocolVersion protocolVersion = httpRequest.getRequestLine().getProtocolVersion();
            HttpEntityEnclosingRequest httpEntityEnclosingRequest = (HttpEntityEnclosingRequest) httpRequest;
            if (httpEntityEnclosingRequest.expectContinue() && !protocolVersion.lessEquals(HttpVersion.HTTP_1_0)) {
                httpClientConnection.flush();
                if (httpClientConnection.isResponseAvailable(httpRequest.getParams().getIntParameter("http.protocol.wait-for-continue", 2000))) {
                    HttpResponse receiveResponseHeader = httpClientConnection.receiveResponseHeader();
                    if (canResponseHaveBody(httpRequest, receiveResponseHeader)) {
                        httpClientConnection.receiveResponseEntity(receiveResponseHeader);
                    }
                    int statusCode = receiveResponseHeader.getStatusLine().getStatusCode();
                    if (statusCode < 200) {
                        if (statusCode != 100) {
                            throw new ProtocolException("Unexpected response: " + receiveResponseHeader.getStatusLine());
                        }
                    } else {
                        z = false;
                        httpResponse = receiveResponseHeader;
                    }
                }
            }
            if (z) {
                httpClientConnection.sendRequestEntity(httpEntityEnclosingRequest);
            }
        }
        httpClientConnection.flush();
        httpContext.setAttribute("http.request_sent", Boolean.TRUE);
        return httpResponse;
    }

    protected HttpResponse doReceiveResponse(HttpRequest httpRequest, HttpClientConnection httpClientConnection, HttpContext httpContext) throws HttpException, IOException {
        if (httpRequest == null) {
            throw new IllegalArgumentException("HTTP request may not be null");
        }
        if (httpClientConnection == null) {
            throw new IllegalArgumentException("HTTP connection may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        HttpResponse httpResponse = null;
        int i = 0;
        while (true) {
            if (httpResponse == null || i < 200) {
                httpResponse = httpClientConnection.receiveResponseHeader();
                if (canResponseHaveBody(httpRequest, httpResponse)) {
                    httpClientConnection.receiveResponseEntity(httpResponse);
                }
                i = httpResponse.getStatusLine().getStatusCode();
            } else {
                return httpResponse;
            }
        }
    }

    public void postProcess(HttpResponse httpResponse, HttpProcessor httpProcessor, HttpContext httpContext) throws HttpException, IOException {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        if (httpProcessor == null) {
            throw new IllegalArgumentException("HTTP processor may not be null");
        }
        if (httpContext == null) {
            throw new IllegalArgumentException("HTTP context may not be null");
        }
        httpProcessor.process(httpResponse, httpContext);
    }
}
