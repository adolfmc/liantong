package com.bumptech.glide.load;

import android.content.Context;
import android.support.annotation.NonNull;
import com.bumptech.glide.load.engine.Resource;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Collection;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MultiTransformation<T> implements Transformation<T> {
    private final Collection<? extends Transformation<T>> transformations;

    @SafeVarargs
    public MultiTransformation(@NonNull Transformation<T>... transformationArr) {
        if (transformationArr.length == 0) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.transformations = Arrays.asList(transformationArr);
    }

    public MultiTransformation(@NonNull Collection<? extends Transformation<T>> collection) {
        if (collection.isEmpty()) {
            throw new IllegalArgumentException("MultiTransformation must contain at least one Transformation");
        }
        this.transformations = collection;
    }

    @Override // com.bumptech.glide.load.Transformation
    @NonNull
    public Resource<T> transform(@NonNull Context context, @NonNull Resource<T> resource, int i, int i2) {
        Resource<T> resource2 = resource;
        for (Transformation<T> transformation : this.transformations) {
            Resource<T> transform = transformation.transform(context, resource2, i, i2);
            if (resource2 != null && !resource2.equals(resource) && !resource2.equals(transform)) {
                resource2.recycle();
            }
            resource2 = transform;
        }
        return resource2;
    }

    @Override // com.bumptech.glide.load.Key
    public boolean equals(Object obj) {
        if (obj instanceof MultiTransformation) {
            return this.transformations.equals(((MultiTransformation) obj).transformations);
        }
        return false;
    }

    @Override // com.bumptech.glide.load.Key
    public int hashCode() {
        return this.transformations.hashCode();
    }

    @Override // com.bumptech.glide.load.Key
    public void updateDiskCacheKey(@NonNull MessageDigest messageDigest) {
        for (Transformation<T> transformation : this.transformations) {
            transformation.updateDiskCacheKey(messageDigest);
        }
    }
}
