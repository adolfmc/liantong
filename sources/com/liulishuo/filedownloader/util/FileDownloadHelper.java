package com.liulishuo.filedownloader.util;

import android.annotation.SuppressLint;
import android.content.Context;
import com.liulishuo.filedownloader.connection.FileDownloadConnection;
import com.liulishuo.filedownloader.database.FileDownloadDatabase;
import com.liulishuo.filedownloader.stream.FileDownloadOutputStream;
import java.io.File;
import java.io.IOException;
import okhttp3.OkHttpClient;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FileDownloadHelper {
    @SuppressLint({"StaticFieldLeak"})
    private static Context appContext;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ConnectionCountAdapter {
        int determineConnectionCount(int i, String str, String str2, long j);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface ConnectionCreator {
        FileDownloadConnection create(String str) throws IOException;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface DatabaseCustomMaker {
        FileDownloadDatabase customMake();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface IdGenerator {
        int generateId(String str, String str2, boolean z);

        int transOldId(int i, String str, String str2, boolean z);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OkHttpClientCustomMaker {
        OkHttpClient customMake();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public interface OutputStreamCreator {
        FileDownloadOutputStream create(File file) throws IOException;

        boolean supportSeek();
    }

    public static void holdContext(Context context) {
        appContext = context;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
