package com.networkbench.agent.impl.p243c.p247d;

import android.os.Handler;
import android.os.Looper;
import android.os.MessageQueue;
import com.networkbench.agent.impl.p243c.p248e.C6295m;
import com.networkbench.agent.impl.p254f.C6396h;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.d.d */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6272d implements MessageQueue.IdleHandler {

    /* renamed from: b */
    public String f15635b;

    /* renamed from: c */
    private boolean f15636c = false;

    /* renamed from: a */
    public List<InterfaceC6271c> f15634a = new ArrayList();

    public C6272d(String str) {
        this.f15635b = str;
    }

    /* renamed from: a */
    public void m10711a(InterfaceC6271c interfaceC6271c) {
        new Handler(Looper.getMainLooper()).postDelayed(new RunnableC6279k(this, interfaceC6271c), 6000L);
        if (interfaceC6271c != null) {
            this.f15634a.add(interfaceC6271c);
        }
    }

    /* renamed from: b */
    public void m10708b(InterfaceC6271c interfaceC6271c) {
        this.f15634a.remove(interfaceC6271c);
    }

    /* renamed from: a */
    public void m10710a(C6295m.EnumC6299d enumC6299d) {
        for (InterfaceC6271c interfaceC6271c : this.f15634a) {
            interfaceC6271c.mo10585a(enumC6299d);
        }
    }

    @Override // android.os.MessageQueue.IdleHandler
    public boolean queueIdle() {
        if (this.f15636c) {
            C6396h.m10129m("已经设置过加载结束时间!");
            return false;
        }
        m10710a(C6295m.EnumC6299d.queueIdle);
        C6396h.m10129m("NBSActivityIdleHandler queueIdle setPageLoadEndTime" + System.currentTimeMillis());
        return false;
    }

    /* renamed from: a */
    public void m10709a(String str) {
        C6396h.m10129m("NBSActivityIdleHandler queueIdle userQueueIdle  class name :" + str + " ; time" + System.currentTimeMillis());
        m10710a(C6295m.EnumC6299d.setPageLoadingEndTime);
        this.f15636c = true;
        C6274f.m10697a(str);
    }
}
