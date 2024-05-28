package com.liulishuo.okdownload;

import android.support.annotation.Nullable;
import com.liulishuo.okdownload.core.connection.DownloadConnection;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IRedirectHandler {
    @Nullable
    String getRedirectLocation();

    void handleRedirect(DownloadConnection downloadConnection, DownloadConnection.Connected connected, Map<String, List<String>> map) throws IOException;
}
