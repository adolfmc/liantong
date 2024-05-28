package org.apache.http.params;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public abstract class AbstractHttpParams implements HttpParams {
    @Override // org.apache.http.params.HttpParams
    public long getLongParameter(String str, long j) {
        Object parameter = getParameter(str);
        if (parameter == null) {
            return j;
        }
        return ((Long) parameter).longValue();
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setLongParameter(String str, long j) {
        setParameter(str, new Long(j));
        return this;
    }

    @Override // org.apache.http.params.HttpParams
    public int getIntParameter(String str, int i) {
        Object parameter = getParameter(str);
        if (parameter == null) {
            return i;
        }
        return ((Integer) parameter).intValue();
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setIntParameter(String str, int i) {
        setParameter(str, new Integer(i));
        return this;
    }

    @Override // org.apache.http.params.HttpParams
    public double getDoubleParameter(String str, double d) {
        Object parameter = getParameter(str);
        if (parameter == null) {
            return d;
        }
        return ((Double) parameter).doubleValue();
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setDoubleParameter(String str, double d) {
        setParameter(str, new Double(d));
        return this;
    }

    @Override // org.apache.http.params.HttpParams
    public boolean getBooleanParameter(String str, boolean z) {
        Object parameter = getParameter(str);
        if (parameter == null) {
            return z;
        }
        return ((Boolean) parameter).booleanValue();
    }

    @Override // org.apache.http.params.HttpParams
    public HttpParams setBooleanParameter(String str, boolean z) {
        setParameter(str, z ? Boolean.TRUE : Boolean.FALSE);
        return this;
    }

    @Override // org.apache.http.params.HttpParams
    public boolean isParameterTrue(String str) {
        return getBooleanParameter(str, false);
    }

    @Override // org.apache.http.params.HttpParams
    public boolean isParameterFalse(String str) {
        return !getBooleanParameter(str, false);
    }
}
