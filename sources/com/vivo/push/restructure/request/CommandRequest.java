package com.vivo.push.restructure.request;

import com.vivo.push.restructure.request.p379a.p380a.JsonParcelable;
import com.vivo.push.util.LogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.restructure.request.b */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class CommandRequest<I extends JsonParcelable, O extends JsonParcelable> {

    /* renamed from: a */
    private BaseCommand<I, O> f21138a;

    /* renamed from: b */
    private ISendCallback<O> f21139b;

    /* renamed from: c */
    private long f21140c;

    private CommandRequest(BaseCommand<I, O> baseCommand) {
        this.f21140c = 5000L;
        this.f21138a = baseCommand;
        if (this.f21138a == null) {
            LogUtil.m5357a(8100, "Command object is null, please construct command first");
        }
    }

    private CommandRequest(BaseCommand<I, O> baseCommand, ISendCallback<O> iSendCallback) {
        this(baseCommand);
        this.f21139b = iSendCallback;
    }

    public CommandRequest(BaseCommand<I, O> baseCommand, ISendCallback<O> iSendCallback, byte b) {
        this(baseCommand, iSendCallback);
        this.f21140c = 20000L;
    }

    /* renamed from: a */
    public final BaseCommand m5504a() {
        return this.f21138a;
    }

    /* renamed from: b */
    public final ISendCallback m5503b() {
        return this.f21139b;
    }

    /* renamed from: c */
    public final long m5502c() {
        return this.f21140c;
    }
}
