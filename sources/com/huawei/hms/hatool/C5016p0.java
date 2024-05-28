package com.huawei.hms.hatool;

import java.util.Calendar;
import java.util.UUID;

/* renamed from: com.huawei.hms.hatool.p0 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5016p0 {

    /* renamed from: a */
    private long f11476a = 1800000;

    /* renamed from: b */
    private volatile boolean f11477b = false;

    /* renamed from: c */
    private C5017a f11478c = null;

    /* renamed from: com.huawei.hms.hatool.p0$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    class C5017a {

        /* renamed from: a */
        String f11479a = UUID.randomUUID().toString().replace("-", "");

        /* renamed from: b */
        boolean f11480b = true;

        /* renamed from: c */
        private long f11481c;

        C5017a(long j) {
            this.f11479a += "_" + j;
            this.f11481c = j;
            C5016p0.this.f11477b = false;
        }

        /* renamed from: a */
        private boolean m14553a(long j, long j2) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(j);
            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTimeInMillis(j2);
            return (calendar.get(1) == calendar2.get(1) && calendar.get(6) == calendar2.get(6)) ? false : true;
        }

        /* renamed from: b */
        private void m14552b(long j) {
            C5029v.m14455c("hmsSdk", "getNewSession() session is flush!");
            String uuid = UUID.randomUUID().toString();
            this.f11479a = uuid;
            this.f11479a = uuid.replace("-", "");
            this.f11479a += "_" + j;
            this.f11481c = j;
            this.f11480b = true;
        }

        /* renamed from: b */
        private boolean m14551b(long j, long j2) {
            return j2 - j >= C5016p0.this.f11476a;
        }

        /* renamed from: a */
        void m14554a(long j) {
            if (C5016p0.this.f11477b) {
                C5016p0.this.f11477b = false;
                m14552b(j);
            } else if (m14551b(this.f11481c, j) || m14553a(this.f11481c, j)) {
                m14552b(j);
            } else {
                this.f11481c = j;
                this.f11480b = false;
            }
        }
    }

    /* renamed from: a */
    public String m14560a() {
        C5017a c5017a = this.f11478c;
        if (c5017a == null) {
            C5029v.m14451f("hmsSdk", "getSessionName(): session not prepared. onEvent() must be called first.");
            return "";
        }
        return c5017a.f11479a;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public void m14559a(long j) {
        C5017a c5017a = this.f11478c;
        if (c5017a != null) {
            c5017a.m14554a(j);
            return;
        }
        C5029v.m14455c("hmsSdk", "Session is first flush");
        this.f11478c = new C5017a(j);
    }

    /* renamed from: b */
    public boolean m14556b() {
        C5017a c5017a = this.f11478c;
        if (c5017a == null) {
            C5029v.m14451f("hmsSdk", "isFirstEvent(): session not prepared. onEvent() must be called first.");
            return false;
        }
        return c5017a.f11480b;
    }
}
