package android.support.p083v4.p085os;

import android.os.Parcel;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* renamed from: android.support.v4.os.ParcelableCompatCreatorCallbacks */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface ParcelableCompatCreatorCallbacks<T> {
    T createFromParcel(Parcel parcel, ClassLoader classLoader);

    T[] newArray(int i);
}
