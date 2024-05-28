package com.baidu.cloud.framework;

import com.baidu.cloud.framework.frame.BaseFrame;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface Sink<T extends BaseFrame> {
    InPort<T> getInPort();

    OutPort<T> getOutPort();
}
