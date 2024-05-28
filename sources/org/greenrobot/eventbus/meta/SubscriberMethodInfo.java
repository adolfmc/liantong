package org.greenrobot.eventbus.meta;

import org.greenrobot.eventbus.ThreadMode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SubscriberMethodInfo {
    final Class<?> eventType;
    final String methodName;
    final int priority;
    final boolean sticky;
    final ThreadMode threadMode;

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode, int i, boolean z) {
        this.methodName = str;
        this.threadMode = threadMode;
        this.eventType = cls;
        this.priority = i;
        this.sticky = z;
    }

    public SubscriberMethodInfo(String str, Class<?> cls) {
        this(str, cls, ThreadMode.POSTING, 0, false);
    }

    public SubscriberMethodInfo(String str, Class<?> cls, ThreadMode threadMode) {
        this(str, cls, threadMode, 0, false);
    }
}
