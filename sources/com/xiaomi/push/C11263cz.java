package com.xiaomi.push;

import android.content.Context;
import com.xiaomi.channel.commonutils.logger.AbstractC11049b;
import java.io.File;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.cz */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class C11263cz {

    /* renamed from: a */
    private static InterfaceC11262cy f21814a;

    /* renamed from: a */
    public static File m4401a(Context context) {
        if (context == null) {
            AbstractC11049b.m5268d("ERROR: Context cannot be null.");
            return null;
        }
        InterfaceC11262cy interfaceC11262cy = f21814a;
        if (interfaceC11262cy != null) {
            return interfaceC11262cy.m4402a(context);
        }
        AbstractC11049b.m5268d("ERROR: XMSF not configure the instance of LogAgent.");
        return null;
    }
}
