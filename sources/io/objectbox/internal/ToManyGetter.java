package io.objectbox.internal;

import io.objectbox.annotation.apihint.Internal;
import java.io.Serializable;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ToManyGetter<SOURCE> extends Serializable {
    <TARGET> List<TARGET> getToMany(SOURCE source);
}
