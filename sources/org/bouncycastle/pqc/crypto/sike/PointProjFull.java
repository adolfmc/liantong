package org.bouncycastle.pqc.crypto.sike;

import java.lang.reflect.Array;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
class PointProjFull {

    /* renamed from: X */
    long[][] f27240X;

    /* renamed from: Y */
    long[][] f27241Y;

    /* renamed from: Z */
    long[][] f27242Z;

    /* JADX INFO: Access modifiers changed from: package-private */
    public PointProjFull(int i) {
        this.f27240X = (long[][]) Array.newInstance(long.class, 2, i);
        this.f27241Y = (long[][]) Array.newInstance(long.class, 2, i);
        this.f27242Z = (long[][]) Array.newInstance(long.class, 2, i);
    }
}
