package android.support.p083v4.app;

import android.app.Service;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RestrictTo;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: android.support.v4.app.ServiceCompat */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class ServiceCompat {
    public static final int START_STICKY = 1;
    public static final int STOP_FOREGROUND_DETACH = 2;
    public static final int STOP_FOREGROUND_REMOVE = 1;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP})
    /* renamed from: android.support.v4.app.ServiceCompat$StopForegroundFlags */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public @interface StopForegroundFlags {
    }

    private ServiceCompat() {
    }

    public static void stopForeground(@NonNull Service service, int i) {
        if (Build.VERSION.SDK_INT >= 24) {
            service.stopForeground(i);
        } else {
            service.stopForeground((i & 1) != 0);
        }
    }
}
