package android.net.http;

import org.apache.http.HttpHost;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\452516_dexfile_execute.dex */
public interface RequestFeeder {
    Request getRequest();

    Request getRequest(HttpHost httpHost);

    boolean haveRequest(HttpHost httpHost);

    void requeueRequest(Request request);
}
