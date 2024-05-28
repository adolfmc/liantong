package org.apache.http.impl.client;

import java.util.HashMap;
import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.client.CredentialsProvider;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public class BasicCredentialsProvider implements CredentialsProvider {
    private final HashMap<AuthScope, Credentials> credMap = new HashMap<>();

    @Override // org.apache.http.client.CredentialsProvider
    public synchronized void setCredentials(AuthScope authScope, Credentials credentials) {
        if (authScope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        this.credMap.put(authScope, credentials);
    }

    private static Credentials matchCredentials(HashMap<AuthScope, Credentials> hashMap, AuthScope authScope) {
        Credentials credentials = hashMap.get(authScope);
        if (credentials == null) {
            int i = -1;
            AuthScope authScope2 = null;
            for (AuthScope authScope3 : hashMap.keySet()) {
                int match = authScope.match(authScope3);
                if (match > i) {
                    authScope2 = authScope3;
                    i = match;
                }
            }
            if (authScope2 != null) {
                return hashMap.get(authScope2);
            }
            return credentials;
        }
        return credentials;
    }

    @Override // org.apache.http.client.CredentialsProvider
    public synchronized Credentials getCredentials(AuthScope authScope) {
        if (authScope == null) {
            throw new IllegalArgumentException("Authentication scope may not be null");
        }
        return matchCredentials(this.credMap, authScope);
    }

    public String toString() {
        return this.credMap.toString();
    }

    @Override // org.apache.http.client.CredentialsProvider
    public synchronized void clear() {
        this.credMap.clear();
    }
}
