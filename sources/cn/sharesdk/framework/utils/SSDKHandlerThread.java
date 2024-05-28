package cn.sharesdk.framework.utils;

import android.os.Handler;
import android.os.Message;
import com.mob.tools.MobHandlerThread;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.framework.utils.g */
/* loaded from: E:\10201592_dexfile_execute.dex */
public abstract class SSDKHandlerThread implements Handler.Callback {

    /* renamed from: a */
    protected final Handler f2969a = MobHandlerThread.newHandler(this);

    /* renamed from: a */
    protected void mo21690a(Message message) {
    }

    /* renamed from: b */
    protected abstract void mo21689b(Message message);

    /* renamed from: c */
    protected void mo21688c(Message message) {
    }

    /* renamed from: d */
    public void mo21687d() {
        m21691a(0, 0, null);
    }

    /* renamed from: a */
    public void m21691a(int i, int i2, Object obj) {
        Message message = new Message();
        message.what = -1;
        message.arg1 = i;
        message.arg2 = i2;
        message.obj = obj;
        this.f2969a.sendMessage(message);
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        switch (message.what) {
            case -2:
                mo21688c(message);
                return false;
            case -1:
                mo21690a(message);
                return false;
            default:
                mo21689b(message);
                return false;
        }
    }
}
