package com.baidu.p120ar.arplay.core.engine.engine3d;

import com.baidu.p120ar.arplay.core.engine.IBaseLifeCycle;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.core.engine.engine3d.AbstractARPEngine3D */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class AbstractARPEngine3D implements IBaseLifeCycle, IARPEngine3D {
    protected boolean mIsActiveByARPlayVersionCase = false;

    public void setIsActiveByARPlayVersionCase(boolean z) {
        this.mIsActiveByARPlayVersionCase = z;
    }
}
