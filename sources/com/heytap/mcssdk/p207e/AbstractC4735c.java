package com.heytap.mcssdk.p207e;

import android.content.Context;
import android.content.Intent;
import com.heytap.mcssdk.PushService;
import com.heytap.mcssdk.utils.C4744b;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.msp.push.mode.BaseMode;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.e.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class AbstractC4735c implements InterfaceC4736d {
    /* renamed from: a */
    public static List<BaseMode> m15523a(Context context, Intent intent) {
        BaseMode mo15521a;
        if (intent == null) {
            return null;
        }
        int i = 4096;
        try {
            i = Integer.parseInt(C4744b.m15504d(intent.getStringExtra("type")));
        } catch (Exception e) {
            C4746d.m15482e("MessageParser--getMessageByIntent--Exception:" + e.getMessage());
        }
        C4746d.m15494b("MessageParser--getMessageByIntent--type:" + i);
        ArrayList arrayList = new ArrayList();
        for (InterfaceC4736d interfaceC4736d : PushService.getInstance().getParsers()) {
            if (interfaceC4736d != null && (mo15521a = interfaceC4736d.mo15521a(context, i, intent)) != null) {
                arrayList.add(mo15521a);
            }
        }
        return arrayList;
    }

    /* renamed from: a */
    protected abstract BaseMode mo15522a(Intent intent, int i);
}
