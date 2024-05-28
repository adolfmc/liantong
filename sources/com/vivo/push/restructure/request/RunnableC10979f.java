package com.vivo.push.restructure.request;

/* compiled from: RequestManager.java */
/* renamed from: com.vivo.push.restructure.request.f */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class RunnableC10979f implements Runnable {

    /* renamed from: a */
    final /* synthetic */ CommandRequest f21147a;

    /* renamed from: b */
    final /* synthetic */ HandlerC10978e f21148b;

    /* JADX INFO: Access modifiers changed from: package-private */
    public RunnableC10979f(HandlerC10978e handlerC10978e, CommandRequest commandRequest) {
        this.f21148b = handlerC10978e;
        this.f21147a = commandRequest;
    }

    @Override // java.lang.Runnable
    public final void run() {
        CommandRequest commandRequest = this.f21147a;
        if (commandRequest == null || commandRequest.m5503b() == null) {
            return;
        }
        this.f21147a.m5503b().mo5501a(1003);
    }
}
