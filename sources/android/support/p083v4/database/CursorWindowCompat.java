package android.support.p083v4.database;

import android.database.CursorWindow;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.database.CursorWindowCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class CursorWindowCompat {
    private CursorWindowCompat() {
    }

    @NonNull
    public static CursorWindow create(@Nullable String str, long j) {
        if (Build.VERSION.SDK_INT >= 28) {
            return new CursorWindow(str, j);
        }
        if (Build.VERSION.SDK_INT >= 15) {
            return new CursorWindow(str);
        }
        return new CursorWindow(false);
    }
}
