package com.p284qw.soul.permission.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.exception.ContainerStatusException */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ContainerStatusException extends IllegalStateException {
    public ContainerStatusException() {
        super(" activity did not existence, check your app status before use soulPermission");
    }
}
