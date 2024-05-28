package com.mob.tools;

import com.mob.commons.C5892y;
import com.mob.tools.p238b.C6120a;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.AbstractRunnableC6217h;
import java.util.ArrayList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MDP implements PublicMemberKeeper {

    /* renamed from: a */
    private static Object f14797a = new Object();

    public static Object get(String str, ArrayList<Object> arrayList) {
        return get(str, arrayList, false, 0);
    }

    public static Object get(String str, ArrayList<Object> arrayList, int i) {
        return get(str, arrayList, false, i);
    }

    public static Object get(String str, ArrayList<Object> arrayList, boolean z) {
        return get(str, arrayList, z, 0);
    }

    public static Object get(final String str, final ArrayList<Object> arrayList, boolean z, int i) {
        Object poll;
        if (z) {
            return C6120a.m11364a(str, arrayList);
        }
        final LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
        C5892y.f14527e.execute(new AbstractRunnableC6217h() { // from class: com.mob.tools.MDP.1
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                Object m11364a = C6120a.m11364a(str, arrayList);
                if (m11364a == null) {
                    m11364a = MDP.f14797a;
                }
                linkedBlockingQueue.offer(m11364a);
            }
        });
        try {
            if (i <= 0) {
                poll = m11830a(str, linkedBlockingQueue);
            } else {
                poll = linkedBlockingQueue.poll(i, TimeUnit.MILLISECONDS);
            }
            if (poll == f14797a) {
                return null;
            }
            return poll;
        } catch (Throwable th) {
            MobLog.getInstance().m11341d(th);
            return null;
        }
    }

    /* renamed from: a */
    private static Object m11830a(String str, BlockingQueue blockingQueue) throws InterruptedException {
        int i;
        if ("gia".equals(str) || "gal".equals(str) || "gsl".equals(str) || "giafce".equals(str)) {
            i = 30000;
        } else {
            i = "glctn".equals(str) ? 60000 : 3000;
        }
        return blockingQueue.poll(i, TimeUnit.MILLISECONDS);
    }
}
