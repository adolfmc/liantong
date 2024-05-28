package android.support.p083v4.media;

import android.graphics.Bitmap;
import android.media.MediaMetadata;
import android.media.Rating;
import android.os.Parcel;
import android.support.annotation.RequiresApi;
import java.util.Set;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@RequiresApi(21)
/* renamed from: android.support.v4.media.MediaMetadataCompatApi21 */
/* loaded from: E:\10201592_dexfile_execute.dex */
class MediaMetadataCompatApi21 {
    public static Set<String> keySet(Object obj) {
        return ((MediaMetadata) obj).keySet();
    }

    public static Bitmap getBitmap(Object obj, String str) {
        return ((MediaMetadata) obj).getBitmap(str);
    }

    public static long getLong(Object obj, String str) {
        return ((MediaMetadata) obj).getLong(str);
    }

    public static Object getRating(Object obj, String str) {
        return ((MediaMetadata) obj).getRating(str);
    }

    public static CharSequence getText(Object obj, String str) {
        return ((MediaMetadata) obj).getText(str);
    }

    public static void writeToParcel(Object obj, Parcel parcel, int i) {
        ((MediaMetadata) obj).writeToParcel(parcel, i);
    }

    public static Object createFromParcel(Parcel parcel) {
        return MediaMetadata.CREATOR.createFromParcel(parcel);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: android.support.v4.media.MediaMetadataCompatApi21$Builder */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class Builder {
        public static Object newInstance() {
            return new MediaMetadata.Builder();
        }

        public static void putBitmap(Object obj, String str, Bitmap bitmap) {
            ((MediaMetadata.Builder) obj).putBitmap(str, bitmap);
        }

        public static void putLong(Object obj, String str, long j) {
            ((MediaMetadata.Builder) obj).putLong(str, j);
        }

        public static void putRating(Object obj, String str, Object obj2) {
            ((MediaMetadata.Builder) obj).putRating(str, (Rating) obj2);
        }

        public static void putText(Object obj, String str, CharSequence charSequence) {
            ((MediaMetadata.Builder) obj).putText(str, charSequence);
        }

        public static void putString(Object obj, String str, String str2) {
            ((MediaMetadata.Builder) obj).putString(str, str2);
        }

        public static Object build(Object obj) {
            return ((MediaMetadata.Builder) obj).build();
        }

        private Builder() {
        }
    }

    private MediaMetadataCompatApi21() {
    }
}
