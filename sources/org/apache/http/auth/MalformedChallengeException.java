package org.apache.http.auth;

import org.apache.http.ProtocolException;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class MalformedChallengeException extends ProtocolException {
    private static final long serialVersionUID = 814586927989932284L;

    public MalformedChallengeException() {
    }

    public MalformedChallengeException(String str) {
        super(str);
    }

    public MalformedChallengeException(String str, Throwable th) {
        super(str, th);
    }
}
