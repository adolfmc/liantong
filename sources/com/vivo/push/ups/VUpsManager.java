package com.vivo.push.ups;

import android.content.Context;
import android.os.Bundle;
import com.vivo.push.PushClient;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class VUpsManager {
    public void onCommandResult(Context context, Bundle bundle) {
    }

    public static VUpsManager getInstance() {
        return C10983a.f21164a;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.vivo.push.ups.VUpsManager$a */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    static class C10983a {

        /* renamed from: a */
        private static VUpsManager f21164a = new VUpsManager();
    }

    public void registerToken(Context context, String str, String str2, String str3, UPSRegisterCallback uPSRegisterCallback) {
        PushClient.getInstance(context).turnOnPush(new C10984a(this, uPSRegisterCallback));
    }

    public void unRegisterToken(Context context, UPSRegisterCallback uPSRegisterCallback) {
        PushClient.getInstance(context).turnOffPush(new C10985b(this, uPSRegisterCallback));
    }

    public void turnOnPush(Context context, UPSTurnCallback uPSTurnCallback) {
        PushClient.getInstance(context).turnOnPush(new C10986c(this, uPSTurnCallback));
    }

    public void turnOffPush(Context context, UPSTurnCallback uPSTurnCallback) {
        PushClient.getInstance(context).turnOffPush(new C10987d(this, uPSTurnCallback));
    }
}
