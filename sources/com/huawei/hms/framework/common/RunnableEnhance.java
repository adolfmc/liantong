package com.huawei.hms.framework.common;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class RunnableEnhance implements Runnable {
    static final String TRANCELOGO = " -->";
    private String parentName = Thread.currentThread().getName();
    private Runnable proxy;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableEnhance(Runnable runnable) {
        this.proxy = runnable;
    }

    @Override // java.lang.Runnable
    public void run() {
        this.proxy.run();
    }

    public String getParentName() {
        return this.parentName;
    }
}
