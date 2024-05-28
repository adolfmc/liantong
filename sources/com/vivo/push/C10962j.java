package com.vivo.push;

import android.os.Handler;
import android.os.Message;
import com.vivo.push.util.LogUtil;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: Access modifiers changed from: package-private */
/* compiled from: IPCManager.java */
/* renamed from: com.vivo.push.j */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class C10962j implements Handler.Callback {

    /* renamed from: a */
    final /* synthetic */ IPCManager f21030a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10962j(IPCManager iPCManager) {
        this.f21030a = iPCManager;
    }

    @Override // android.os.Handler.Callback
    public final boolean handleMessage(Message message) {
        AtomicInteger atomicInteger;
        AtomicInteger atomicInteger2;
        if (message == null) {
            LogUtil.m5354a("AidlManager", "handleMessage error : msg is null");
            return false;
        }
        switch (message.what) {
            case 1:
                LogUtil.m5354a("AidlManager", "In connect, bind core service time out");
                atomicInteger = this.f21030a.f21025f;
                if (atomicInteger.get() == 2) {
                    this.f21030a.m5659a(1);
                    return true;
                }
                return true;
            case 2:
                atomicInteger2 = this.f21030a.f21025f;
                if (atomicInteger2.get() == 4) {
                    this.f21030a.m5649f();
                }
                this.f21030a.m5659a(1);
                return true;
            default:
                LogUtil.m5346b("AidlManager", "unknow msg what [" + message.what + "]");
                return true;
        }
    }
}
