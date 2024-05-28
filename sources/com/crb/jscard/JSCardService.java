package com.crb.jscard;

import android.content.Context;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class JSCardService {

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.crb.jscard.JSCardService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class RunnableC4181a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ SmartCard f9729a;

        public RunnableC4181a(SmartCard smartCard) {
            this.f9729a = smartCard;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.f9729a.m1870a();
        }
    }

    public static void initialize(Context context, String str) {
        PreconditionUtil.m13a(context, "Context cannot be null");
        PreconditionUtil.m13a(str, "phoneNumber cannot be null");
        CardService.init(context);
        JSCardConfig.f2c = str;
        new Thread(new RunnableC4181a(SmartCard.m1866d())).start();
    }
}
