package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.clientreport.processor.IEventProcessor;
import com.xiaomi.clientreport.processor.IPerfProcessor;
import com.xiaomi.clientreport.processor.InterfaceC11064c;

/* renamed from: com.xiaomi.push.bk */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class RunnableC11196bk implements Runnable {

    /* renamed from: a */
    private Context f21609a;

    /* renamed from: a */
    private InterfaceC11064c f21610a;

    /* renamed from: a */
    public void m4720a(InterfaceC11064c interfaceC11064c) {
        this.f21610a = interfaceC11064c;
    }

    /* renamed from: a */
    public void m4721a(Context context) {
        this.f21609a = context;
    }

    @Override // java.lang.Runnable
    public void run() {
        try {
            if (this.f21610a != null) {
                this.f21610a.mo5226a();
            }
            AbstractC11049b.m5270c("begin read and send perf / event");
            if (this.f21610a instanceof IEventProcessor) {
                C11200bm.m4710a(this.f21609a).m4708a("sp_client_report_status", "event_last_upload_time", System.currentTimeMillis());
            } else if (this.f21610a instanceof IPerfProcessor) {
                C11200bm.m4710a(this.f21609a).m4708a("sp_client_report_status", "perf_last_upload_time", System.currentTimeMillis());
            }
        } catch (Exception e) {
            AbstractC11049b.m5276a(e);
        }
    }
}
