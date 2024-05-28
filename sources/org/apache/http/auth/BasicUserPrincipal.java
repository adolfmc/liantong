package org.apache.http.auth;

import java.security.Principal;
import org.apache.http.util.LangUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public final class BasicUserPrincipal implements Principal {
    private final String username;

    public BasicUserPrincipal(String str) {
        if (str == null) {
            throw new IllegalArgumentException("User name may not be null");
        }
        this.username = str;
    }

    @Override // java.security.Principal
    public String getName() {
        return this.username;
    }

    @Override // java.security.Principal
    public int hashCode() {
        return LangUtils.hashCode(17, this.username);
    }

    @Override // java.security.Principal
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof BasicUserPrincipal) || !LangUtils.equals(this.username, ((BasicUserPrincipal) obj).username)) {
            return false;
        }
        return true;
    }

    @Override // java.security.Principal
    public String toString() {
        return "[principal: " + this.username + "]";
    }
}
