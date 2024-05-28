package com.baidu.p120ar.armdl.task;

import com.baidu.p120ar.armdl.ARMdlManager;
import com.baidu.p120ar.async.ARTask;
import com.baidu.p120ar.mdl.MdlConfig;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.armdl.task.MdlInitTask */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class MdlInitTask extends ARTask<MdlInitResult> {
    private static final String TAG = "MdlInitTask";
    private int mdlType;

    public abstract int executeInit(MdlConfig mdlConfig);

    @Override // com.baidu.p120ar.async.ARTask
    public abstract String getTag();

    public MdlInitTask(int i) {
        this.mdlType = i;
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.baidu.p120ar.async.ARTask
    public MdlInitResult execute() {
        int i;
        if (ARMdlManager.getInstance().checkMdlInit(this.mdlType)) {
            i = 0;
        } else {
            i = executeInit(ARMdlManager.getInstance().getMdlConfigByType(this.mdlType));
            if (i == 0) {
                ARMdlManager.getInstance().registerMdl(this.mdlType);
            }
        }
        return new MdlInitResult(this.mdlType, i);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.baidu.ar.armdl.task.MdlInitTask$MdlInitResult */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public class MdlInitResult {
        int result;
        int type;

        MdlInitResult(int i, int i2) {
            this.type = i;
            this.result = i2;
        }
    }
}
