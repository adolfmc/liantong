package com.xiaomi.push.service;

import android.content.Context;
import android.content.Intent;
import android.util.Pair;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import com.xiaomi.push.C11368fi;
import com.xiaomi.push.C11480s;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.service.t */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11620t {

    /* renamed from: a */
    private static final Map<String, byte[]> f23753a = new HashMap();

    /* renamed from: a */
    private static ArrayList<Pair<String, byte[]>> f23752a = new ArrayList<>();

    /* renamed from: a */
    public static void m2403a(String str, byte[] bArr) {
        synchronized (f23753a) {
            AbstractC11049b.m5282a("pending registration request. " + str);
            f23753a.put(str, bArr);
        }
    }

    /* renamed from: a */
    public static void m2404a(XMPushService xMPushService, boolean z) {
        try {
            synchronized (f23753a) {
                for (String str : f23753a.keySet()) {
                    AbstractC11049b.m5282a("processing pending registration request. " + str);
                    C11632w.m2354a(xMPushService, str, f23753a.get(str));
                    if (z && !C11480s.m2924a()) {
                        try {
                            Thread.sleep(200L);
                        } catch (Exception unused) {
                        }
                    }
                }
                f23753a.clear();
            }
        } catch (C11368fi e) {
            AbstractC11049b.m5268d("fail to deal with pending register request. " + e);
            xMPushService.m2894a(10, e);
        }
    }

    /* renamed from: a */
    public static void m2407a(Context context, int i, String str) {
        synchronized (f23753a) {
            for (String str2 : f23753a.keySet()) {
                AbstractC11049b.m5282a("notify registration error. " + str2);
                m2406a(context, str2, f23753a.get(str2), i, str);
            }
            f23753a.clear();
        }
    }

    /* renamed from: a */
    public static void m2405a(XMPushService xMPushService) {
        ArrayList<Pair<String, byte[]>> arrayList;
        try {
            synchronized (f23752a) {
                arrayList = f23752a;
                f23752a = new ArrayList<>();
            }
            boolean m2924a = C11480s.m2924a();
            Iterator<Pair<String, byte[]>> it = arrayList.iterator();
            while (it.hasNext()) {
                Pair<String, byte[]> next = it.next();
                C11632w.m2354a(xMPushService, (String) next.first, (byte[]) next.second);
                if (!m2924a) {
                    try {
                        Thread.sleep(100L);
                    } catch (InterruptedException unused) {
                    }
                }
            }
        } catch (C11368fi e) {
            AbstractC11049b.m5268d("meet error when process pending message. " + e);
            xMPushService.m2894a(10, e);
        }
    }

    /* renamed from: b */
    public static void m2402b(String str, byte[] bArr) {
        synchronized (f23752a) {
            f23752a.add(new Pair<>(str, bArr));
            if (f23752a.size() > 50) {
                f23752a.remove(0);
            }
        }
    }

    /* renamed from: a */
    public static void m2406a(Context context, String str, byte[] bArr, int i, String str2) {
        Intent intent = new Intent("com.xiaomi.mipush.ERROR");
        intent.setPackage(str);
        intent.putExtra("mipush_payload", bArr);
        intent.putExtra("mipush_error_code", i);
        intent.putExtra("mipush_error_msg", str2);
        context.sendBroadcast(intent, C11632w.m2351a(str));
    }
}
