package com.bytedance.pangle.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import com.bytedance.pangle.p176d.C3794e;

/* renamed from: com.bytedance.pangle.receiver.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class C3895a extends BroadcastReceiver {
    @Override // android.content.BroadcastReceiver
    public final void onReceive(final Context context, final Intent intent) {
        if (C3903c.m16735a().f9287c.contains(Integer.valueOf(hashCode()))) {
            C3903c.m16735a().m16734a(context, intent);
        } else {
            C3794e.m16917b(new Runnable() { // from class: com.bytedance.pangle.receiver.a.1
                @Override // java.lang.Runnable
                public final void run() {
                    C3903c.m16735a().m16734a(context, intent);
                }
            });
        }
    }
}
