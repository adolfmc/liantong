package org.apache.http.client;

import org.apache.http.ProtocolException;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class RedirectException extends ProtocolException {
    private static final long serialVersionUID = 4418824536372559326L;

    public RedirectException() {
    }

    public RedirectException(String str) {
        super(str);
    }

    public RedirectException(String str, Throwable th) {
        super(str, th);
    }
}
