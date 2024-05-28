package android.support.p083v4.p085os;

import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.os.ParcelCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ParcelCompat {
    public static boolean readBoolean(Parcel parcel) {
        return parcel.readInt() != 0;
    }

    public static void writeBoolean(Parcel parcel, boolean z) {
        parcel.writeInt(z ? 1 : 0);
    }

    private ParcelCompat() {
    }
}
