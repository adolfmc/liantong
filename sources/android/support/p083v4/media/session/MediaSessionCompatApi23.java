package android.support.p083v4.media.session;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.p083v4.media.session.MediaSessionCompatApi21;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(23)
/* renamed from: android.support.v4.media.session.MediaSessionCompatApi23 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class MediaSessionCompatApi23 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi23$Callback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Callback extends MediaSessionCompatApi21.Callback {
        void onPlayFromUri(Uri uri, Bundle bundle);
    }

    public static Object createCallback(Callback callback) {
        return new CallbackProxy(callback);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.session.MediaSessionCompatApi23$CallbackProxy */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class CallbackProxy<T extends Callback> extends MediaSessionCompatApi21.CallbackProxy<T> {
        public CallbackProxy(T t) {
            super(t);
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPlayFromUri(Uri uri, Bundle bundle) {
            MediaSessionCompat.ensureClassLoader(bundle);
            ((Callback) this.mCallback).onPlayFromUri(uri, bundle);
        }
    }

    private MediaSessionCompatApi23() {
    }
}
