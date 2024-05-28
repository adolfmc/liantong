package com.baidu.p120ar.arplay.representation;

import java.io.Serializable;
import java.util.concurrent.locks.ReentrantLock;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.arplay.representation.Renderable */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Renderable implements Serializable {
    private static final long serialVersionUID = 6701586807666461858L;
    protected boolean dirty = true;
    protected ReentrantLock lock = new ReentrantLock();

    public boolean dirty() {
        return this.dirty;
    }

    public void setClean() {
        this.dirty = false;
    }

    public void setDirty() {
        this.dirty = true;
    }

    public ReentrantLock getLock() {
        return this.lock;
    }
}
