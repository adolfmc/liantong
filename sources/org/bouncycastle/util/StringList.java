package org.bouncycastle.util;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface StringList extends Iterable<String> {
    boolean add(String str);

    String get(int i);

    int size();

    String[] toStringArray();

    String[] toStringArray(int i, int i2);
}
