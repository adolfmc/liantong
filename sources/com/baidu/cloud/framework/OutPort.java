package com.baidu.cloud.framework;

import com.baidu.cloud.framework.frame.BaseFrame;
import java.util.Iterator;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class OutPort<T extends BaseFrame> extends Port<T> {

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface Factory<T extends BaseFrame> {
        OutPort<T> createOutPort();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.baidu.cloud.framework.IPort
    public /* bridge */ /* synthetic */ void onFrame(Object obj) {
        onFrame((OutPort<T>) ((BaseFrame) obj));
    }

    public OutPort() {
        super(PortDirection.OUT);
    }

    public synchronized void link(InPort<T> inPort) {
        if (inPort != null) {
            if (inPort.getDirection() == PortDirection.IN) {
                if (!this.mPeers.contains(inPort)) {
                    this.mPeers.add(inPort);
                    onPortLinked();
                    inPort.onInPortLinked(this);
                }
            }
        }
    }

    public synchronized void unlink(InPort<T> inPort) {
        if (inPort != null) {
            if (inPort.getDirection() == PortDirection.IN) {
                inPort.onInPortUnlinked(this);
                this.mPeers.remove(inPort);
                if (this.mPeers.isEmpty()) {
                    onPortUnlinked();
                }
            }
        }
    }

    public synchronized void unlinkAll() {
        this.mPeers.clear();
        onPortUnlinked();
    }

    @Override // com.baidu.cloud.framework.IPort
    public synchronized void onConfigure(Object obj) {
        Iterator it = this.mPeers.iterator();
        while (it.hasNext()) {
            Port port = (Port) it.next();
            if (port != null) {
                port.onConfigure(obj);
            }
        }
    }

    public synchronized void onFrame(T t) {
        Iterator it = this.mPeers.iterator();
        while (it.hasNext()) {
            Port port = (Port) it.next();
            if (port != null) {
                port.onFrame(t);
            }
        }
    }
}
