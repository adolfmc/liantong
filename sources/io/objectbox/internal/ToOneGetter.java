package io.objectbox.internal;

import io.objectbox.annotation.apihint.Internal;
import io.objectbox.relation.ToOne;
import java.io.Serializable;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Internal
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ToOneGetter<SOURCE> extends Serializable {
    <TARGET> ToOne<TARGET> getToOne(SOURCE source);
}
