package kotlinx.android.extensions;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import kotlin.Metadata;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: ContainerOptions.kt */
@Retention(RetentionPolicy.RUNTIME)
@Metadata(m1892bv = {1, 0, 3}, m1891d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0002\u0018\u00002\u00020\u0001B\n\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003R\u000f\u0010\u0002\u001a\u00020\u0003¢\u0006\u0006\u001a\u0004\b\u0002\u0010\u0004¨\u0006\u0005"}, m1890d2 = {"Lkotlinx/android/extensions/ContainerOptions;", "", "cache", "Lkotlinx/android/extensions/CacheImplementation;", "()Lkotlinx/android/extensions/CacheImplementation;", "kotlin-android-extensions-runtime"}, m1889k = 1, m1888mv = {1, 1, 16})
/* loaded from: E:\11617560_dexfile_execute.dex */
public @interface ContainerOptions {
    CacheImplementation cache() default CacheImplementation.HASH_MAP;
}
