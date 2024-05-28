package android.support.p083v4.media.session;

import android.media.session.MediaController;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(23)
/* renamed from: android.support.v4.media.session.MediaControllerCompatApi23 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaControllerCompatApi23 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.session.MediaControllerCompatApi23$TransportControls */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class TransportControls {
        public static void playFromUri(Object obj, Uri uri, Bundle bundle) {
            ((MediaController.TransportControls) obj).playFromUri(uri, bundle);
        }

        private TransportControls() {
        }
    }

    private MediaControllerCompatApi23() {
    }
}
