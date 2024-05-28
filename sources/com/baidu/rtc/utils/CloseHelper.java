package com.baidu.rtc.utils;

import android.database.Cursor;
import android.media.MediaMetadataRetriever;
import com.webrtc.Logging;
import java.io.Closeable;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\567196_dexfile_execute.dex */
public class CloseHelper {
    private static final String TAG = "CloseHelper";

    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (Throwable th) {
                Logging.m5304e("CloseHelper", th.getMessage());
            }
        }
    }

    public static void close(Cursor cursor) {
        if (cursor != null) {
            try {
                cursor.close();
            } catch (Exception e) {
                Logging.m5304e("CloseHelper", e.getMessage());
            }
        }
    }

    public static void close(MediaMetadataRetriever mediaMetadataRetriever) {
        if (mediaMetadataRetriever != null) {
            try {
                mediaMetadataRetriever.release();
            } catch (Exception e) {
                Logging.m5304e("CloseHelper", e.getMessage());
            }
        }
    }
}
