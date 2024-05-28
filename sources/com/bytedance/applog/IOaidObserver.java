package com.bytedance.applog;

import androidx.annotation.AnyThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface IOaidObserver {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class Oaid {
        @Nullable

        /* renamed from: id */
        public final String f8270id;

        public Oaid(@Nullable String str) {
            this.f8270id = str;
        }
    }

    @AnyThread
    void onOaidLoaded(@NonNull Oaid oaid);
}
