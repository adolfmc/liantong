package com.p284qw.soul.permission.exception;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qw.soul.permission.exception.InitException */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class InitException extends IllegalStateException {
    public InitException() {
        super("auto init failed ,you need invoke SoulPermission.init() in your application");
    }
}
