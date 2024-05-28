package com.liulishuo.okdownload.core.interceptor;

import android.support.annotation.NonNull;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import com.liulishuo.okdownload.core.download.DownloadChain;
import java.io.IOException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface Interceptor {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Connect {
        @NonNull
        DownloadConnection.Connected interceptConnect(DownloadChain downloadChain) throws IOException;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface Fetch {
        long interceptFetch(DownloadChain downloadChain) throws IOException;
    }
}
