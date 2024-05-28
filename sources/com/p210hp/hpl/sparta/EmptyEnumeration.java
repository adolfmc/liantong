package com.p210hp.hpl.sparta;

import java.util.Enumeration;
import java.util.NoSuchElementException;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Document.java */
/* renamed from: com.hp.hpl.sparta.EmptyEnumeration */
/* loaded from: E:\10762272_dexfile_execute.dex */
class EmptyEnumeration implements Enumeration {
    @Override // java.util.Enumeration
    public boolean hasMoreElements() {
        return false;
    }

    @Override // java.util.Enumeration
    public Object nextElement() {
        throw new NoSuchElementException();
    }
}
