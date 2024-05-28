package com.baidu.p120ar.pipeline;

import com.baidu.p120ar.callback.ICancellable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pipeline.IPipeline */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IPipeline extends ICancellable {
    IPipeline fail(IErrorChannel iErrorChannel);

    IPipeline next(AbstractChannelHandler<?, ?> abstractChannelHandler);
}
