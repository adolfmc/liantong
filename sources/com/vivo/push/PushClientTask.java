package com.vivo.push;

import android.content.Context;
import com.vivo.push.p368b.OnLogReceiveCommand;
import com.vivo.push.util.LogUtil;

/* renamed from: com.vivo.push.s */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public abstract class PushClientTask implements Runnable {

    /* renamed from: a */
    public Context f21149a;

    /* renamed from: b */
    private int f21150b;

    /* renamed from: c */
    private PushCommand f21151c;

    /* renamed from: a */
    protected abstract void mo5492a(PushCommand pushCommand);

    public PushClientTask(PushCommand pushCommand) {
        this.f21150b = -1;
        this.f21151c = pushCommand;
        this.f21150b = pushCommand.m5326b();
        if (this.f21150b < 0) {
            throw new IllegalArgumentException("PushTask need a > 0 task id.");
        }
        this.f21149a = PushClientManager.m5648a().m5614h();
    }

    /* renamed from: a */
    public final int m5493a() {
        return this.f21150b;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Context context = this.f21149a;
        if (context != null && !(this.f21151c instanceof OnLogReceiveCommand)) {
            LogUtil.m5356a(context, "[执行指令]" + this.f21151c);
        }
        mo5492a(this.f21151c);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append("{");
        PushCommand pushCommand = this.f21151c;
        sb.append(pushCommand == null ? "[null]" : pushCommand.toString());
        sb.append("}");
        return sb.toString();
    }
}
