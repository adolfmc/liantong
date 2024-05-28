package com.heytap.mcssdk.p204b;

import android.annotation.TargetApi;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;
import com.heytap.mcssdk.C4710R;
import com.heytap.mcssdk.utils.C4747e;
import com.heytap.mcssdk.utils.C4750f;

/* renamed from: com.heytap.mcssdk.b.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4715a {

    /* renamed from: a */
    public static final String f10581a = "Heytap PUSH";

    /* renamed from: b */
    private static final String f10582b = "System Default Channel";

    /* renamed from: c */
    private static final int f10583c = 3;

    /* JADX INFO: Access modifiers changed from: private */
    @TargetApi(26)
    /* renamed from: a */
    public boolean m15576a(Context context, String str, String str2, int i) {
        NotificationManager notificationManager;
        if (context == null || (notificationManager = (NotificationManager) context.getSystemService("notification")) == null) {
            return false;
        }
        notificationManager.createNotificationChannel(new NotificationChannel(str, str2, i));
        return true;
    }

    /* renamed from: a */
    public void m15577a(final Context context) {
        if (Build.VERSION.SDK_INT < 26) {
            return;
        }
        C4750f.m15466a(new Runnable() { // from class: com.heytap.mcssdk.b.a.1
            @Override // java.lang.Runnable
            public void run() {
                if (C4747e.m15468c().m15473a()) {
                    return;
                }
                String string = context.getString(C4710R.string.system_default_channel);
                if (TextUtils.isEmpty(string)) {
                    string = C4715a.f10582b;
                }
                C4747e.m15468c().m15470a(C4715a.this.m15576a(context, C4715a.f10581a, string, 3));
            }
        });
    }
}
