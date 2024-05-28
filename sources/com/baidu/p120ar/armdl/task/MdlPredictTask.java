package com.baidu.p120ar.armdl.task;

import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.arplay.core.pixel.FramePixels;
import com.baidu.p120ar.async.ARTask;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.armdl.task.MdlPredictTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class MdlPredictTask<T> extends ARTask {
    FramePixels mFramePixels;
    private int mdlType;

    public abstract T predict(FramePixels framePixels);

    public MdlPredictTask(int i, FramePixels framePixels) {
        this.mdlType = i;
        this.mFramePixels = framePixels;
    }

    @Override // com.baidu.p120ar.async.ARTask
    public T execute() {
        if (ARMdlManager.getInstance().checkMdlInit(this.mdlType)) {
            try {
                ARMdlManager.getInstance().setMdlState(this.mdlType, true);
                return predict(this.mFramePixels);
            } finally {
                ARMdlManager.getInstance().setMdlState(this.mdlType, false);
            }
        }
        return null;
    }
}
