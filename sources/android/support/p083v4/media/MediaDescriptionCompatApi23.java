package android.support.p083v4.media;

import android.media.MediaDescription;
import android.net.Uri;
import android.support.annotation.RequiresApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(23)
/* renamed from: android.support.v4.media.MediaDescriptionCompatApi23 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaDescriptionCompatApi23 {
    public static Uri getMediaUri(Object obj) {
        return ((MediaDescription) obj).getMediaUri();
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.MediaDescriptionCompatApi23$Builder */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class Builder {
        public static void setMediaUri(Object obj, Uri uri) {
            ((MediaDescription.Builder) obj).setMediaUri(uri);
        }

        private Builder() {
        }
    }

    private MediaDescriptionCompatApi23() {
    }
}
