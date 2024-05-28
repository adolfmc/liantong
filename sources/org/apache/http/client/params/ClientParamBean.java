package org.apache.http.client.params;

import java.util.Collection;
import org.apache.http.Header;
import org.apache.http.HttpHost;
import org.apache.http.conn.ClientConnectionManagerFactory;
import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ClientParamBean extends HttpAbstractParamBean {
    public ClientParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setConnectionManagerFactoryClassName(String str) {
        this.params.setParameter("http.connection-manager.factory-class-name", str);
    }

    public void setConnectionManagerFactory(ClientConnectionManagerFactory clientConnectionManagerFactory) {
        this.params.setParameter("http.connection-manager.factory-object", clientConnectionManagerFactory);
    }

    public void setHandleRedirects(boolean z) {
        this.params.setBooleanParameter("http.protocol.handle-redirects", z);
    }

    public void setRejectRelativeRedirect(boolean z) {
        this.params.setBooleanParameter("http.protocol.reject-relative-redirect", z);
    }

    public void setMaxRedirects(int i) {
        this.params.setIntParameter("http.protocol.max-redirects", i);
    }

    public void setAllowCircularRedirects(boolean z) {
        this.params.setBooleanParameter("http.protocol.allow-circular-redirects", z);
    }

    public void setHandleAuthentication(boolean z) {
        this.params.setBooleanParameter("http.protocol.handle-authentication", z);
    }

    public void setCookiePolicy(String str) {
        this.params.setParameter("http.protocol.cookie-policy", str);
    }

    public void setVirtualHost(HttpHost httpHost) {
        this.params.setParameter("http.virtual-host", httpHost);
    }

    public void setDefaultHeaders(Collection<Header> collection) {
        this.params.setParameter("http.default-headers", collection);
    }

    public void setDefaultHost(HttpHost httpHost) {
        this.params.setParameter("http.default-host", httpHost);
    }
}
