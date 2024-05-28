package com.xiaomi.push.service;

import android.os.SystemClock;
import android.text.TextUtils;
import com.xiaomi.push.C11320eg;
import com.xiaomi.push.C11321eh;
import com.xiaomi.push.service.XMPushService;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* renamed from: com.xiaomi.push.service.at */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11563at {

    /* renamed from: a */
    private final ConcurrentHashMap<String, C11567c> f23619a = new ConcurrentHashMap<>();

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.at$b */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11566b {

        /* renamed from: a */
        private static final C11563at f23620a = new C11563at();
    }

    /* renamed from: a */
    public static C11563at m2639a() {
        return C11566b.f23620a;
    }

    /* renamed from: a */
    public void m2638a() {
        if (this.f23619a.isEmpty()) {
            return;
        }
        Iterator<Map.Entry<String, C11567c>> it = this.f23619a.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, C11567c> next = it.next();
            if (next == null || next.getValue() == null) {
                it.remove();
            } else {
                C11567c value = next.getValue();
                if (Math.abs(SystemClock.elapsedRealtime() - value.f23622b) > 10000) {
                    m2635a(next.getKey(), value);
                    it.remove();
                }
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.xiaomi.push.service.at$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class C11565a extends XMPushService.AbstractC11509j {
        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public String mo2375a() {
            return "RecordTimeManager clear";
        }

        public C11565a() {
            super(17);
        }

        @Override // com.xiaomi.push.service.XMPushService.AbstractC11509j
        /* renamed from: a */
        public void mo2374a() {
            C11563at.m2639a().m2638a();
        }
    }

    /* renamed from: a */
    public void m2636a(String str, long j, long j2) {
        C11567c c11567c = new C11567c();
        c11567c.f23621a = j2;
        c11567c.f23622b = j;
        this.f23619a.put(str, c11567c);
    }

    /* renamed from: a */
    public void m2637a(String str, long j) {
        C11567c c11567c = this.f23619a.get(str);
        if (c11567c != null) {
            c11567c.f23623c = j;
        }
    }

    /* renamed from: b */
    public void m2634b(String str, long j) {
        C11567c remove = this.f23619a.remove(str);
        if (remove != null) {
            remove.f23624d = j;
            m2635a(str, remove);
        }
    }

    /* renamed from: a */
    private void m2635a(String str, C11567c c11567c) {
        if (TextUtils.isEmpty(str) || c11567c == null) {
            return;
        }
        HashMap hashMap = new HashMap();
        hashMap.put("xmsfVC", Long.valueOf(c11567c.f23621a));
        hashMap.put("packetId", str);
        hashMap.put("pTime", Long.valueOf(c11567c.m2632a()));
        hashMap.put("bTime", Long.valueOf(c11567c.m2631b()));
        C11321eh.m4047a().mo4046a(new C11320eg("msg_process_time", hashMap));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: com.xiaomi.push.service.at$c */
    /* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
    public static class C11567c {

        /* renamed from: a */
        long f23621a;

        /* renamed from: b */
        long f23622b;

        /* renamed from: c */
        long f23623c;

        /* renamed from: d */
        long f23624d;

        private C11567c() {
        }

        /* renamed from: a */
        public long m2632a() {
            long j = this.f23623c;
            long j2 = this.f23622b;
            if (j > j2) {
                return j - j2;
            }
            return 0L;
        }

        /* renamed from: b */
        public long m2631b() {
            long j = this.f23624d;
            long j2 = this.f23623c;
            if (j > j2) {
                return j - j2;
            }
            return 0L;
        }
    }
}
