package android.support.p083v4.media;

import android.media.browse.MediaBrowser;
import android.os.Parcel;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(23)
/* renamed from: android.support.v4.media.MediaBrowserCompatApi23 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaBrowserCompatApi23 {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.MediaBrowserCompatApi23$ItemCallback */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    interface ItemCallback {
        void onError(@NonNull String str);

        void onItemLoaded(Parcel parcel);
    }

    public static Object createItemCallback(ItemCallback itemCallback) {
        return new ItemCallbackProxy(itemCallback);
    }

    public static void getItem(Object obj, String str, Object obj2) {
        ((MediaBrowser) obj).getItem(str, (MediaBrowser.ItemCallback) obj2);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.MediaBrowserCompatApi23$ItemCallbackProxy */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    static class ItemCallbackProxy<T extends ItemCallback> extends MediaBrowser.ItemCallback {
        protected final T mItemCallback;

        public ItemCallbackProxy(T t) {
            this.mItemCallback = t;
        }

        @Override // android.media.browse.MediaBrowser.ItemCallback
        public void onItemLoaded(MediaBrowser.MediaItem mediaItem) {
            if (mediaItem == null) {
                this.mItemCallback.onItemLoaded(null);
                return;
            }
            Parcel obtain = Parcel.obtain();
            mediaItem.writeToParcel(obtain, 0);
            this.mItemCallback.onItemLoaded(obtain);
        }

        @Override // android.media.browse.MediaBrowser.ItemCallback
        public void onError(@NonNull String str) {
            this.mItemCallback.onError(str);
        }
    }

    private MediaBrowserCompatApi23() {
    }
}
