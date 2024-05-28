package com.tencent.p348mm.opensdk.channel;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import com.tencent.p348mm.opensdk.channel.p350a.C10372a;
import com.tencent.p348mm.opensdk.utils.C10384Log;
import com.tencent.p348mm.opensdk.utils.C10386b;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.channel.MMessageActV2 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class MMessageActV2 {
    public static final String DEFAULT_ENTRY_CLASS_NAME = ".wxapi.WXEntryActivity";
    public static final String MM_ENTRY_PACKAGE_NAME = "com.tencent.mm";
    public static final String MM_MSG_ENTRY_CLASS_NAME = "com.tencent.mm.plugin.base.stub.WXEntryActivity";
    private static final String TAG = "MicroMsg.SDK.MMessageAct";

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.tencent.mm.opensdk.channel.MMessageActV2$Args */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Args {
        public static final int INVALID_FLAGS = -1;
        public Bundle bundle;
        public String content;
        public int flags = -1;
        public int launchMode = 2;
        public String targetClassName;
        public String targetPkgName;
        public String token;

        public String toString() {
            return "targetPkgName:" + this.targetPkgName + ", targetClassName:" + this.targetClassName + ", content:" + this.content + ", flags:" + this.flags + ", bundle:" + this.bundle;
        }
    }

    public static boolean send(Context context, Args args) {
        if (context == null || args == null) {
            C10384Log.m6210e("MicroMsg.SDK.MMessageAct", "send fail, invalid argument");
            return false;
        } else if (C10386b.m6202b(args.targetPkgName)) {
            C10384Log.m6210e("MicroMsg.SDK.MMessageAct", "send fail, invalid targetPkgName, targetPkgName = " + args.targetPkgName);
            return false;
        } else {
            if (C10386b.m6202b(args.targetClassName)) {
                args.targetClassName = args.targetPkgName + ".wxapi.WXEntryActivity";
            }
            C10384Log.m6211d("MicroMsg.SDK.MMessageAct", "send, targetPkgName = " + args.targetPkgName + ", targetClassName = " + args.targetClassName + ", launchMode = " + args.launchMode);
            Intent intent = new Intent();
            intent.setClassName(args.targetPkgName, args.targetClassName);
            Bundle bundle = args.bundle;
            if (bundle != null) {
                intent.putExtras(bundle);
            }
            String packageName = context.getPackageName();
            intent.putExtra("_mmessage_sdkVersion", 638058496);
            intent.putExtra("_mmessage_appPackage", packageName);
            intent.putExtra("_mmessage_content", args.content);
            intent.putExtra("_mmessage_checksum", C10372a.m6223a(args.content, 638058496, packageName));
            intent.putExtra("_message_token", args.token);
            int i = args.flags;
            if (i == -1) {
                intent.addFlags(268435456).addFlags(134217728);
            } else {
                intent.setFlags(i);
            }
            try {
                if (Build.VERSION.SDK_INT < 29 || args.launchMode != 2) {
                    context.startActivity(intent);
                } else {
                    sendUsingPendingIntent(context, intent);
                }
                C10384Log.m6211d("MicroMsg.SDK.MMessageAct", "send mm message, intent=" + intent);
                return true;
            } catch (Exception e) {
                C10384Log.m6210e("MicroMsg.SDK.MMessageAct", "send fail, ex = " + e.getMessage());
                return false;
            }
        }
    }

    private static void sendUsingPendingIntent(Context context, Intent intent) {
        try {
            C10384Log.m6209i("MicroMsg.SDK.MMessageAct", "sendUsingPendingIntent");
            PendingIntent.getActivity(context, 3, intent, 134217728).send(context, 4, null, new PendingIntent.OnFinished() { // from class: com.tencent.mm.opensdk.channel.MMessageActV2.1
                @Override // android.app.PendingIntent.OnFinished
                public void onSendFinished(PendingIntent pendingIntent, Intent intent2, int i, String str, Bundle bundle) {
                    C10384Log.m6209i("MicroMsg.SDK.MMessageAct", "sendUsingPendingIntent onSendFinished resultCode: " + i + ", resultData: " + str);
                }
            }, null);
        } catch (Exception e) {
            C10384Log.m6210e("MicroMsg.SDK.MMessageAct", "sendUsingPendingIntent fail, ex = " + e.getMessage());
            context.startActivity(intent);
        }
    }
}
