package com.baidu.p120ar.pipeline;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pipeline.Pipeline */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Pipeline implements IPipeline {
    private List<AbstractChannelHandler<?, ?>> mTasks = new ArrayList();

    public static <T> IPipeline run(AbstractChannelHandler<Void, T> abstractChannelHandler) {
        abstractChannelHandler.execute(null);
        return new Pipeline(abstractChannelHandler);
    }

    public static <InT, OutT> IPipeline run(AbstractChannelHandler<InT, OutT> abstractChannelHandler, InT r1) {
        abstractChannelHandler.execute(r1);
        return new Pipeline(abstractChannelHandler);
    }

    public Pipeline(AbstractChannelHandler abstractChannelHandler) {
        next(abstractChannelHandler);
    }

    @Override // com.baidu.p120ar.pipeline.IPipeline
    public IPipeline next(AbstractChannelHandler<?, ?> abstractChannelHandler) {
        if (!this.mTasks.isEmpty()) {
            List<AbstractChannelHandler<?, ?>> list = this.mTasks;
            list.get(list.size() - 1).next(abstractChannelHandler);
        }
        this.mTasks.add(abstractChannelHandler);
        return this;
    }

    @Override // com.baidu.p120ar.pipeline.IPipeline
    public IPipeline fail(IErrorChannel iErrorChannel) {
        if (!this.mTasks.isEmpty()) {
            List<AbstractChannelHandler<?, ?>> list = this.mTasks;
            this.mTasks.add((AbstractChannelHandler) list.get(list.size() - 1).fail(iErrorChannel));
        }
        return this;
    }

    @Override // com.baidu.p120ar.callback.ICancellable
    public void cancel() {
        for (AbstractChannelHandler<?, ?> abstractChannelHandler : this.mTasks) {
            abstractChannelHandler.cancel();
        }
    }
}
