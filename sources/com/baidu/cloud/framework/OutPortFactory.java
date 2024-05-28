package com.baidu.cloud.framework;

import com.baidu.cloud.framework.OutPort;
import com.baidu.cloud.framework.frame.BaseFrame;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OutPortFactory<T extends BaseFrame> implements OutPort.Factory<T> {
    @Override // com.baidu.cloud.framework.OutPort.Factory
    public OutPort<T> createOutPort() {
        return new OutPort<>();
    }
}
