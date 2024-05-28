package com.mob;

import com.mob.commons.C5741aa;
import com.mob.tools.proguard.PublicMemberKeeper;
import com.mob.tools.utils.AbstractRunnableC6217h;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class MobStrategy implements PublicMemberKeeper {
    public static void setStrategy(final int i) {
        new Thread(new AbstractRunnableC6217h() { // from class: com.mob.MobStrategy.1
            @Override // com.mob.tools.utils.AbstractRunnableC6217h
            /* renamed from: a */
            public void mo10991a() {
                C5741aa.m12650a().m12649a(i);
            }
        }).start();
    }
}
