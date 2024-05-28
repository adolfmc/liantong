package com.bytedance.applog;

/* renamed from: com.bytedance.applog.g2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class RunnableC3589g2 implements Runnable {

    /* renamed from: a */
    public final /* synthetic */ C3596h2 f8457a;

    public RunnableC3589g2(C3596h2 c3596h2) {
        this.f8457a = c3596h2;
    }

    @Override // java.lang.Runnable
    public void run() {
        for (int i = 0; i < this.f8457a.f8491a.size(); i++) {
            try {
                for (String str : new String[]{"openudid", "clientudid", "serial_number", "sim_serial_number", "udid", "device_id"}) {
                    try {
                        this.f8457a.m17285a(this.f8457a.f8491a.get(i), str);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (Exception unused) {
                return;
            }
        }
    }
}
