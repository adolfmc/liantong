package com.baidu.cloud.framework;

import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class Port<T> implements IPort<T> {
    protected PortDirection mDirection;
    private boolean mIsLinked;
    protected List<Port<T>> mPeers = new ArrayList();
    private String mPortName;

    public Port(PortDirection portDirection) {
        this.mDirection = portDirection;
    }

    public void setName(String str) {
        this.mPortName = str;
    }

    @Override // com.baidu.cloud.framework.IPort
    public synchronized void onPortLinked() {
        this.mIsLinked = true;
    }

    @Override // com.baidu.cloud.framework.IPort
    public synchronized void onPortUnlinked() {
        this.mIsLinked = false;
    }

    List<Port<T>> getPeers() {
        return this.mPeers;
    }

    public String getName() {
        return this.mPortName;
    }

    public synchronized boolean isPortLinked() {
        return this.mIsLinked;
    }

    public PortDirection getDirection() {
        return this.mDirection;
    }

    public String toString() {
        if (this.mDirection == PortDirection.OUT) {
            return "[out:" + this.mPortName + "]";
        } else if (this.mDirection == PortDirection.IN) {
            return "[" + this.mPortName + ":in]";
        } else {
            return "[" + this.mPortName + "]";
        }
    }
}
