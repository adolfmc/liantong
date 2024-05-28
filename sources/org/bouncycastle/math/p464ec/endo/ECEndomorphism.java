package org.bouncycastle.math.p464ec.endo;

import org.bouncycastle.math.p464ec.ECPointMap;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.bouncycastle.math.ec.endo.ECEndomorphism */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface ECEndomorphism {
    ECPointMap getPointMap();

    boolean hasEfficientPointMap();
}
