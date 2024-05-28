package com.bumptech.glide.signature;

import android.support.annotation.NonNull;
import com.bumptech.glide.load.Key;
import java.security.MessageDigest;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class EmptySignature implements Key {
    private static final EmptySignature EMPTY_KEY = new EmptySignature();

    public String toString() {
        return "EmptySignature";
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
    }

    @NonNull
    public static EmptySignature obtain() {
        return EMPTY_KEY;
    }

    private EmptySignature() {
    }
}
