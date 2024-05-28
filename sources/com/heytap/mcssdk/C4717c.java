package com.heytap.mcssdk;

import android.content.Context;
import android.content.Intent;
import com.heytap.mcssdk.p207e.AbstractC4735c;
import com.heytap.mcssdk.p208f.InterfaceC4741c;
import com.heytap.mcssdk.utils.C4746d;
import com.heytap.mcssdk.utils.C4750f;
import com.heytap.mcssdk.utils.Utils;
import com.heytap.msp.push.callback.IDataMessageCallBackService;
import com.heytap.msp.push.mode.BaseMode;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.heytap.mcssdk.c */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C4717c {
    /* renamed from: a */
    public static void m15574a(final Context context, final Intent intent, final IDataMessageCallBackService iDataMessageCallBackService) {
        if (context == null) {
            C4746d.m15482e("context is null , please check param of parseIntent()");
        } else if (intent == null) {
            C4746d.m15482e("intent is null , please check param of parseIntent()");
        } else if (iDataMessageCallBackService == null) {
            C4746d.m15482e("callback is null , please check param of parseIntent()");
        } else if (Utils.isSupportPushByClient(context)) {
            C4750f.m15466a(new Runnable() { // from class: com.heytap.mcssdk.c.1
                @Override // java.lang.Runnable
                public void run() {
                    List<BaseMode> m15523a = AbstractC4735c.m15523a(context, intent);
                    if (m15523a == null) {
                        return;
                    }
                    for (BaseMode baseMode : m15523a) {
                        if (baseMode != null) {
                            for (InterfaceC4741c interfaceC4741c : PushService.getInstance().getProcessors()) {
                                if (interfaceC4741c != null) {
                                    interfaceC4741c.mo15515a(context, baseMode, iDataMessageCallBackService);
                                }
                            }
                        }
                    }
                }
            });
        } else {
            C4746d.m15482e("push is null ,please check system has push");
        }
    }
}
