package com.baidu.p120ar.armdl.task;

import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.async.ARTask;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.armdl.task.MdlDestroyTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class MdlDestroyTask extends ARTask<MdlDestroyResult> {
    private int mdlType;

    public abstract int executeDestroy();

    public MdlDestroyTask(int i) {
        this.mdlType = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.baidu.p120ar.async.ARTask
    public MdlDestroyResult execute() {
        int i;
        if (ARMdlManager.getInstance().checkMdlInit(this.mdlType)) {
            ARMdlManager.getInstance().unRegisterMdl(this.mdlType);
            i = executeDestroy();
        } else {
            i = 0;
        }
        return new MdlDestroyResult(this.mdlType, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.armdl.task.MdlDestroyTask$MdlDestroyResult */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public static class MdlDestroyResult {
        int result;
        int type;

        MdlDestroyResult(int i, int i2) {
            this.type = i;
            this.result = i2;
        }
    }
}
