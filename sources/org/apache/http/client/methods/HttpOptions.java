package org.apache.http.client.methods;

import java.net.URI;
import java.util.HashSet;
import java.util.Set;
import org.apache.http.HeaderElement;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpResponse;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class HttpOptions extends HttpRequestBase {
    public static final String METHOD_NAME = "OPTIONS";

    public HttpOptions() {
    }

    public HttpOptions(URI uri) {
        setURI(uri);
    }

    public HttpOptions(String str) {
        setURI(URI.create(str));
    }

    @Override // org.apache.http.client.methods.HttpRequestBase, org.apache.http.client.methods.HttpUriRequest
    public String getMethod() {
        return "OPTIONS";
    }

    public Set<String> getAllowedMethods(HttpResponse httpResponse) {
        if (httpResponse == null) {
            throw new IllegalArgumentException("HTTP response may not be null");
        }
        HeaderIterator headerIterator = httpResponse.headerIterator("Allow");
        HashSet hashSet = new HashSet();
        while (headerIterator.hasNext()) {
            for (HeaderElement headerElement : headerIterator.nextHeader().getElements()) {
                hashSet.add(headerElement.getName());
            }
        }
        return hashSet;
    }
}
