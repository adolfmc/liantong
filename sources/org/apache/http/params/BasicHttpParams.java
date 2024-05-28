package org.apache.http.params;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class BasicHttpParams extends AbstractHttpParams implements Serializable, Cloneable {
    private static final long serialVersionUID = -7086398485908701455L;
    private HashMap parameters;

    @Override // org.apache.http.params.HttpParams
    public Object getParameter(String str) {
        HashMap hashMap = this.parameters;
        if (hashMap == null) {
            return null;
        }
        return hashMap.get(str);
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setParameter(String str, Object obj) {
        if (this.parameters == null) {
            this.parameters = new HashMap();
        }
        this.parameters.put(str, obj);
        return this;
    }

    @Override // org.apache.http.params.HttpParams
    public boolean removeParameter(String str) {
        HashMap hashMap = this.parameters;
        if (hashMap != null && hashMap.containsKey(str)) {
            this.parameters.remove(str);
            return true;
        }
        return false;
    }

    public void setParameters(String[] strArr, Object obj) {
        for (String str : strArr) {
            setParameter(str, obj);
        }
    }

    public boolean isParameterSet(String str) {
        return getParameter(str) != null;
    }

    public boolean isParameterSetLocally(String str) {
        HashMap hashMap = this.parameters;
        return (hashMap == null || hashMap.get(str) == null) ? false : true;
    }

    public void clear() {
        this.parameters = null;
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams copy() {
        BasicHttpParams basicHttpParams = new BasicHttpParams();
        copyParams(basicHttpParams);
        return basicHttpParams;
    }

    public Object clone() throws CloneNotSupportedException {
        BasicHttpParams basicHttpParams = (BasicHttpParams) super.clone();
        copyParams(basicHttpParams);
        return basicHttpParams;
    }

    protected void copyParams(HttpParams httpParams) {
        HashMap hashMap = this.parameters;
        if (hashMap == null) {
            return;
        }
        for (Map.Entry entry : hashMap.entrySet()) {
            if (entry.getKey() instanceof String) {
                httpParams.setParameter((String) entry.getKey(), entry.getValue());
            }
        }
    }
}
