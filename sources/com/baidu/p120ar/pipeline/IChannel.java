package com.baidu.p120ar.pipeline;

import com.baidu.p120ar.callback.ICancellable;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.pipeline.IChannel */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IChannel<In, Out> extends ICancellable {
    IChannel<Out, Void> fail(IErrorChannel iErrorChannel);

    <T> IChannel<Out, T> next(AbstractChannelHandler<Out, T> abstractChannelHandler);
}
