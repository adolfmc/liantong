package org.codehaus.jackson.map.util;

import java.lang.annotation.Annotation;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Annotations {
    <A extends Annotation> A get(Class<A> cls);

    int size();
}
