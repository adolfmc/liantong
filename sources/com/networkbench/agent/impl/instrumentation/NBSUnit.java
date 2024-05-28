package com.networkbench.agent.impl.instrumentation;

import android.annotation.TargetApi;
import android.os.Looper;
import com.networkbench.agent.impl.p254f.C6394f;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.tracing.TracingInactiveException;
import com.networkbench.agent.impl.util.C6653u;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentSkipListSet;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NBSUnit {

    /* renamed from: b */
    protected static final InterfaceC6393e f16379b = C6394f.m10150a();

    /* renamed from: a */
    protected volatile Set<UUID> f16380a;
    public UUID myUUID;
    public long threadId;
    public String threadName;
    public long entryTimestamp = 0;
    public long exitTimestamp = 0;
    public boolean isComplete = false;
    public UUID parentUUID = null;
    public String metricName = "";

    public void complete() throws TracingInactiveException {
    }

    public NBSUnit() {
        this.threadId = 0L;
        this.threadName = "main";
        initChildren();
        this.myUUID = new UUID(C6653u.m8757a().nextLong(), C6653u.m8757a().nextLong());
        this.threadId = Thread.currentThread().getId();
        this.threadName = Thread.currentThread().getName();
    }

    public void addChild(NBSUnit nBSUnit) {
        this.f16380a.add(nBSUnit.myUUID);
    }

    @TargetApi(9)
    private void initChildren() {
        if (this.f16380a == null) {
            synchronized (this) {
                if (this.f16380a == null) {
                    this.f16380a = new ConcurrentSkipListSet();
                }
            }
        }
    }

    public void setUnitThreadWithUIThread() {
        this.threadId = Looper.getMainLooper().getThread().getId();
        this.threadName = Looper.getMainLooper().getThread().getName();
    }

    public Set<UUID> getChildren() {
        return this.f16380a;
    }

    public boolean isComplete() {
        return this.isComplete;
    }

    public String toString() {
        return "NBSUnit{entryTimestamp=" + this.entryTimestamp + ", exitTimestamp=" + this.exitTimestamp + ", metricName='" + this.metricName + "', children=" + this.f16380a + ", isComplete=" + this.isComplete + ", parentUUID=" + this.parentUUID + ", myUUID=" + this.myUUID + ", threadId=" + this.threadId + ", threadName='" + this.threadName + "'}";
    }
}
