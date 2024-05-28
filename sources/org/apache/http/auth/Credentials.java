package org.apache.http.auth;

import java.security.Principal;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public interface Credentials {
    String getPassword();

    Principal getUserPrincipal();
}
