package org.apache.http.conn.params;

import org.apache.http.params.HttpAbstractParamBean;
import org.apache.http.params.HttpParams;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class ConnConnectionParamBean extends HttpAbstractParamBean {
    public ConnConnectionParamBean(HttpParams httpParams) {
        super(httpParams);
    }

    public void setMaxStatusLineGarbage(int i) {
        this.params.setIntParameter("http.connection.max-status-line-garbage", i);
    }
}
