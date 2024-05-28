package android.support.p083v4.media.session;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(24)
/* renamed from: android.support.v4.media.session.MediaControllerCompatApi24 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaControllerCompatApi24 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.session.MediaControllerCompatApi24$TransportControls */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TransportControls {
        public static void prepare(Object obj) {
            ((MediaController.TransportControls) obj).prepare();
        }

        public static void prepareFromMediaId(Object obj, String str, Bundle bundle) {
            ((MediaController.TransportControls) obj).prepareFromMediaId(str, bundle);
        }

        public static void prepareFromSearch(Object obj, String str, Bundle bundle) {
            ((MediaController.TransportControls) obj).prepareFromSearch(str, bundle);
        }

        public static void prepareFromUri(Object obj, Uri uri, Bundle bundle) {
            ((MediaController.TransportControls) obj).prepareFromUri(uri, bundle);
        }

        private TransportControls() {
        }
    }

    private MediaControllerCompatApi24() {
    }
}
