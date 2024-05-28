package com.sinovatech.unicom.separatemodule.videocenter.refresh;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public enum RefreshMode {
    DISABLED,
    PULL_FROM_START,
    PULL_FROM_END,
    BOTH;

    /* JADX INFO: Access modifiers changed from: package-private */
    public static RefreshMode getDefault() {
        return BOTH;
    }

    boolean permitsPullToRefresh() {
        return this != DISABLED;
    }

    boolean permitsPullFromStart() {
        return this == BOTH || this == PULL_FROM_START;
    }

    boolean permitsPullFromEnd() {
        return this == BOTH || this == PULL_FROM_END;
    }
}
