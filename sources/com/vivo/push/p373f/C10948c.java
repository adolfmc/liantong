package com.vivo.push.p373f;

import com.vivo.push.PushClientTask;
import com.vivo.push.PushCommand;
import com.vivo.push.cache.ClientConfigManagerImpl;
import com.vivo.push.util.LogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* compiled from: InitTask.java */
/* renamed from: com.vivo.push.f.c */
/* loaded from: E:\11617560_dexfile_execute.dex */
final class C10948c extends PushClientTask {
    /* JADX INFO: Access modifiers changed from: package-private */
    public C10948c(PushCommand pushCommand) {
        super(pushCommand);
    }

    @Override // com.vivo.push.PushClientTask
    /* renamed from: a */
    public final void mo5492a(PushCommand pushCommand) {
        LogUtil.m5350a(ClientConfigManagerImpl.getInstance(this.f21149a).isDebug());
    }
}
