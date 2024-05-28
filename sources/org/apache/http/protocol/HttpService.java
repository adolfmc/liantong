package org.apache.http.protocol;

import java.io.IOException;
import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpEntity;
import org.apache.http.HttpEntityEnclosingRequest;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.HttpResponseFactory;
import org.apache.http.HttpServerConnection;
import org.apache.http.HttpVersion;
import org.apache.http.MethodNotSupportedException;
import org.apache.http.ProtocolException;
import org.apache.http.ProtocolVersion;
import org.apache.http.UnsupportedHttpVersionException;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.params.DefaultedHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EncodingUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpService {
    private HttpParams params = null;
    private HttpProcessor processor = null;
    private HttpRequestHandlerResolver handlerResolver = null;
    private ConnectionReuseStrategy connStrategy = null;
    private HttpResponseFactory responseFactory = null;
    private HttpExpectationVerifier expectationVerifier = null;

    public HttpService(HttpProcessor httpProcessor, ConnectionReuseStrategy connectionReuseStrategy, HttpResponseFactory httpResponseFactory) {
        setHttpProcessor(httpProcessor);
        setConnReuseStrategy(connectionReuseStrategy);
        setResponseFactory(httpResponseFactory);
    }

    public void setHttpProcessor(HttpProcessor httpProcessor) {
        if (httpProcessor == null) {
            throw new IllegalArgumentException("HTTP processor may not be null.");
        }
        this.processor = httpProcessor;
    }

    public void setConnReuseStrategy(ConnectionReuseStrategy connectionReuseStrategy) {
        if (connectionReuseStrategy == null) {
            throw new IllegalArgumentException("Connection reuse strategy may not be null");
        }
        this.connStrategy = connectionReuseStrategy;
    }

    public void setResponseFactory(HttpResponseFactory httpResponseFactory) {
        if (httpResponseFactory == null) {
            throw new IllegalArgumentException("Response factory may not be null");
        }
        this.responseFactory = httpResponseFactory;
    }

    public void setHandlerResolver(HttpRequestHandlerResolver httpRequestHandlerResolver) {
        this.handlerResolver = httpRequestHandlerResolver;
    }

    public void setExpectationVerifier(HttpExpectationVerifier httpExpectationVerifier) {
        this.expectationVerifier = httpExpectationVerifier;
    }

    public HttpParams getParams() {
        return this.params;
    }

    public void setParams(HttpParams httpParams) {
        this.params = httpParams;
    }

    public void handleRequest(HttpServerConnection httpServerConnection, HttpContext httpContext) throws IOException, HttpException {
        HttpResponse newHttpResponse;
        HttpEntity entity;
        httpContext.setAttribute("http.connection", httpServerConnection);
        try {
            HttpRequest receiveRequestHeader = httpServerConnection.receiveRequestHeader();
            receiveRequestHeader.setParams(new DefaultedHttpParams(receiveRequestHeader.getParams(), this.params));
            ProtocolVersion protocolVersion = receiveRequestHeader.getRequestLine().getProtocolVersion();
            if (!protocolVersion.lessEquals(HttpVersion.HTTP_1_1)) {
                protocolVersion = HttpVersion.HTTP_1_1;
            }
            newHttpResponse = null;
            if (receiveRequestHeader instanceof HttpEntityEnclosingRequest) {
                if (((HttpEntityEnclosingRequest) receiveRequestHeader).expectContinue()) {
                    HttpResponse newHttpResponse2 = this.responseFactory.newHttpResponse(protocolVersion, 100, httpContext);
                    newHttpResponse2.setParams(new DefaultedHttpParams(newHttpResponse2.getParams(), this.params));
                    HttpExpectationVerifier httpExpectationVerifier = this.expectationVerifier;
                    if (httpExpectationVerifier != null) {
                        try {
                            httpExpectationVerifier.verify(receiveRequestHeader, newHttpResponse2, httpContext);
                        } catch (HttpException e) {
                            HttpResponse newHttpResponse3 = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, 500, httpContext);
                            newHttpResponse3.setParams(new DefaultedHttpParams(newHttpResponse3.getParams(), this.params));
                            handleException(e, newHttpResponse3);
                            newHttpResponse2 = newHttpResponse3;
                        }
                    }
                    if (newHttpResponse2.getStatusLine().getStatusCode() >= 200) {
                        newHttpResponse = newHttpResponse2;
                    } else {
                        httpServerConnection.sendResponseHeader(newHttpResponse2);
                        httpServerConnection.flush();
                        httpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest) receiveRequestHeader);
                    }
                } else {
                    httpServerConnection.receiveRequestEntity((HttpEntityEnclosingRequest) receiveRequestHeader);
                }
            }
            if (newHttpResponse == null) {
                newHttpResponse = this.responseFactory.newHttpResponse(protocolVersion, 200, httpContext);
                newHttpResponse.setParams(new DefaultedHttpParams(newHttpResponse.getParams(), this.params));
                httpContext.setAttribute("http.request", receiveRequestHeader);
                httpContext.setAttribute("http.response", newHttpResponse);
                this.processor.process(receiveRequestHeader, httpContext);
                doService(receiveRequestHeader, newHttpResponse, httpContext);
            }
            if ((receiveRequestHeader instanceof HttpEntityEnclosingRequest) && (entity = ((HttpEntityEnclosingRequest) receiveRequestHeader).getEntity()) != null) {
                entity.consumeContent();
            }
        } catch (HttpException e2) {
            newHttpResponse = this.responseFactory.newHttpResponse(HttpVersion.HTTP_1_0, 500, httpContext);
            newHttpResponse.setParams(new DefaultedHttpParams(newHttpResponse.getParams(), this.params));
            handleException(e2, newHttpResponse);
        }
        this.processor.process(newHttpResponse, httpContext);
        httpServerConnection.sendResponseHeader(newHttpResponse);
        httpServerConnection.sendResponseEntity(newHttpResponse);
        httpServerConnection.flush();
        if (!this.connStrategy.keepAlive(newHttpResponse, httpContext)) {
            httpServerConnection.close();
        }
    }

    protected void handleException(HttpException httpException, HttpResponse httpResponse) {
        if (httpException instanceof MethodNotSupportedException) {
            httpResponse.setStatusCode(501);
        } else if (httpException instanceof UnsupportedHttpVersionException) {
            httpResponse.setStatusCode(505);
        } else if (httpException instanceof ProtocolException) {
            httpResponse.setStatusCode(400);
        } else {
            httpResponse.setStatusCode(500);
        }
        ByteArrayEntity byteArrayEntity = new ByteArrayEntity(EncodingUtils.getAsciiBytes(httpException.getMessage()));
        byteArrayEntity.setContentType("text/plain; charset=US-ASCII");
        httpResponse.setEntity(byteArrayEntity);
    }

    protected void doService(HttpRequest httpRequest, HttpResponse httpResponse, HttpContext httpContext) throws HttpException, IOException {
        HttpRequestHandler httpRequestHandler;
        if (this.handlerResolver == null) {
            httpRequestHandler = null;
        } else {
            httpRequestHandler = this.handlerResolver.lookup(httpRequest.getRequestLine().getUri());
        }
        if (httpRequestHandler != null) {
            httpRequestHandler.handle(httpRequest, httpResponse, httpContext);
        } else {
            httpResponse.setStatusCode(501);
        }
    }
}
