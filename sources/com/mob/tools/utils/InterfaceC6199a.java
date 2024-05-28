package com.mob.tools.utils;

import com.mob.tools.utils.ExecutorDispatcher;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.mob.tools.utils.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface InterfaceC6199a {
    <T extends ExecutorDispatcher.SafeRunnable> void executeDelayed(T t, long j);

    <T extends ExecutorDispatcher.SafeRunnable> void executeDuctile(T t);

    <T extends ExecutorDispatcher.SafeRunnable> void executeImmediately(T t);

    <T extends ExecutorDispatcher.SafeRunnable> void executeSerial(T t);
}
