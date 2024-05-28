package org.apache.http.params;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class DefaultedHttpParams extends AbstractHttpParams {
    private final HttpParams defaults;
    private final HttpParams local;

    public DefaultedHttpParams(HttpParams httpParams, HttpParams httpParams2) {
        if (httpParams == null) {
            throw new IllegalArgumentException("HTTP parameters may not be null");
        }
        this.local = httpParams;
        this.defaults = httpParams2;
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams copy() {
        return new DefaultedHttpParams(this.local.copy(), this.defaults);
    }

    @Override // org.apache.http.params.HttpParams
    public Object getParameter(String str) {
        HttpParams httpParams;
        Object parameter = this.local.getParameter(str);
        if (parameter == null && (httpParams = this.defaults) != null) {
            return httpParams.getParameter(str);
        }
        return parameter;
    }

    @Override // org.apache.http.params.HttpParams
    public boolean removeParameter(String str) {
        return this.local.removeParameter(str);
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setParameter(String str, Object obj) {
        return this.local.setParameter(str, obj);
    }

    public HttpParams getDefaults() {
        return this.defaults;
    }
}
