package org.apache.http.cookie;

import java.util.Date;

/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\452516_dexfile_execute.dex */
public interface Cookie {
    String getComment();

    String getCommentURL();

    String getDomain();

    Date getExpiryDate();

    String getName();

    String getPath();

    int[] getPorts();

    String getValue();

    int getVersion();

    boolean isExpired(Date date);

    boolean isPersistent();

    boolean isSecure();
}
