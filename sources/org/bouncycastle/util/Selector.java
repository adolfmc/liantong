package org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface Selector<T> extends Cloneable {
    Object clone();

    boolean match(T t);
}
