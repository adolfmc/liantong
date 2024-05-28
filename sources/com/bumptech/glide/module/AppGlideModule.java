package com.bumptech.glide.module;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.GlideBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AppGlideModule extends LibraryGlideModule implements AppliesOptions {
    public void applyOptions(@NonNull Context context, @NonNull GlideBuilder glideBuilder) {
    }

    public boolean isManifestParsingEnabled() {
        return true;
    }
}
