package com.bumptech.glide;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.module.AppGlideModule;
import java.util.Set;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class GeneratedAppGlideModule extends AppGlideModule {
    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    public abstract Set<Class<?>> getExcludedModuleClasses();

    /* JADX INFO: Access modifiers changed from: package-private */
    @Nullable
    public RequestManagerRetriever.RequestManagerFactory getRequestManagerFactory() {
        return null;
    }
}
