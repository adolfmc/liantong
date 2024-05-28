package org.apache.http.conn.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ConnManagerParamBean extends HttpAbstractParamBean {
    public ConnManagerParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setTimeout(long j) {
        this.params.setLongParameter("http.conn-manager.timeout", j);
    }

    public void setMaxTotalConnections(int i) {
        this.params.setIntParameter("http.conn-manager.max-total", i);
    }

    public void setConnectionsPerRoute(ConnPerRouteBean connPerRouteBean) {
        this.params.setParameter("http.conn-manager.max-per-route", connPerRouteBean);
    }
}
