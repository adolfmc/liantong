package com.liulishuo.filedownloader.event;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class IDownloadEvent {
    @SuppressFBWarnings(justification = "Not so urgency", value = {"URF"})
    public Runnable callback = null;

    /* renamed from: id */
    protected final String f12209id;

    public IDownloadEvent(String str) {
        this.f12209id = str;
    }

    public IDownloadEvent(String str, boolean z) {
        this.f12209id = str;
    }

    public final String getId() {
        return this.f12209id;
    }
}
