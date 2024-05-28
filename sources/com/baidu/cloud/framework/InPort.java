package com.baidu.cloud.framework;

import com.baidu.cloud.framework.frame.BaseFrame;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class InPort<T extends BaseFrame> extends Port<T> {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Factory<T extends BaseFrame> {
        InPort<T> createInPort();
    }

    public InPort() {
        super(PortDirection.IN);
    }

    public synchronized void onInPortLinked(Port port) {
        if (port != null) {
            if (port.getDirection() == PortDirection.OUT) {
                if (!this.mPeers.contains(port)) {
                    this.mPeers.add(port);
                    onPortLinked();
                }
            }
        }
    }

    public synchronized void onInPortUnlinked(Port port) {
        if (port != null) {
            if (port.getDirection() == PortDirection.OUT) {
                this.mPeers.remove(port);
                if (this.mPeers.isEmpty()) {
                    onPortUnlinked();
                }
            }
        }
    }
}
