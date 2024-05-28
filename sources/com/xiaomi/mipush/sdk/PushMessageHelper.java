package com.xiaomi.mipush.sdk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.text.TextUtils;
import com.xiaomi.push.C11417gs;
import com.xiaomi.push.C11434hi;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class PushMessageHelper {
    public static final String ERROR_MESSAGE = "error_message";
    public static final String ERROR_TYPE = "error_type";
    public static final String ERROR_TYPE_NEED_PERMISSION = "error_lack_of_permission";
    public static final String KEY_COMMAND = "key_command";
    public static final String KEY_MESSAGE = "key_message";
    public static final int MESSAGE_COMMAND = 3;
    public static final int MESSAGE_ERROR = 5;
    public static final int MESSAGE_QUIT = 4;
    public static final int MESSAGE_RAW = 1;
    public static final int MESSAGE_SENDMESSAGE = 2;
    public static final String MESSAGE_TYPE = "message_type";
    public static final int PUSH_MODE_BROADCAST = 2;
    public static final int PUSH_MODE_CALLBACK = 1;
    private static int pushMode;

    private static boolean isIntentAvailable(Context context, Intent intent) {
        try {
            List<ResolveInfo> queryBroadcastReceivers = context.getPackageManager().queryBroadcastReceivers(intent, 32);
            if (queryBroadcastReceivers != null) {
                if (!queryBroadcastReceivers.isEmpty()) {
                    return true;
                }
            }
            return false;
        } catch (Exception unused) {
            return true;
        }
    }

    public static int getPushMode(Context context) {
        if (pushMode == 0) {
            if (isUseCallbackPushMode(context)) {
                setPushMode(1);
            } else {
                setPushMode(2);
            }
        }
        return pushMode;
    }

    private static void setPushMode(int i) {
        pushMode = i;
    }

    public static boolean isUseCallbackPushMode(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setClassName(context.getPackageName(), "com.xiaomi.mipush.sdk.PushServiceReceiver");
        return isIntentAvailable(context, intent);
    }

    public static void sendCommandMessageBroadcast(Context context, MiPushCommandMessage miPushCommandMessage) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 3);
        intent.putExtra("key_command", miPushCommandMessage);
        new PushServiceReceiver().onReceive(context, intent);
    }

    public static void sendQuitMessageBroadcast(Context context) {
        Intent intent = new Intent("com.xiaomi.mipush.RECEIVE_MESSAGE");
        intent.setPackage(context.getPackageName());
        intent.putExtra("message_type", 4);
        new PushServiceReceiver().onReceive(context, intent);
    }

    public static MiPushCommandMessage generateCommandMessage(String str, List<String> list, long j, String str2, String str3, List<String> list2) {
        MiPushCommandMessage miPushCommandMessage = new MiPushCommandMessage();
        miPushCommandMessage.setCommand(str);
        miPushCommandMessage.setCommandArguments(list);
        miPushCommandMessage.setResultCode(j);
        miPushCommandMessage.setReason(str2);
        miPushCommandMessage.setCategory(str3);
        miPushCommandMessage.setAutoMarkPkgs(list2);
        return miPushCommandMessage;
    }

    public static MiPushMessage generateMessage(C11434hi c11434hi, C11417gs c11417gs, boolean z) {
        MiPushMessage miPushMessage = new MiPushMessage();
        miPushMessage.setMessageId(c11434hi.m3213a());
        if (!TextUtils.isEmpty(c11434hi.m3203d())) {
            miPushMessage.setMessageType(1);
            miPushMessage.setAlias(c11434hi.m3203d());
        } else if (!TextUtils.isEmpty(c11434hi.m3205c())) {
            miPushMessage.setMessageType(2);
            miPushMessage.setTopic(c11434hi.m3205c());
        } else if (!TextUtils.isEmpty(c11434hi.m3199f())) {
            miPushMessage.setMessageType(3);
            miPushMessage.setUserAccount(c11434hi.m3199f());
        } else {
            miPushMessage.setMessageType(0);
        }
        miPushMessage.setCategory(c11434hi.m3201e());
        if (c11434hi.m3214a() != null) {
            miPushMessage.setContent(c11434hi.m3214a().m3581c());
        }
        if (c11417gs != null) {
            if (TextUtils.isEmpty(miPushMessage.getMessageId())) {
                miPushMessage.setMessageId(c11417gs.m3559a());
            }
            if (TextUtils.isEmpty(miPushMessage.getTopic())) {
                miPushMessage.setTopic(c11417gs.m3547b());
            }
            miPushMessage.setDescription(c11417gs.m3534d());
            miPushMessage.setTitle(c11417gs.m3539c());
            miPushMessage.setNotifyType(c11417gs.m3562a());
            miPushMessage.setNotifyId(c11417gs.m3540c());
            miPushMessage.setPassThrough(c11417gs.m3548b());
            miPushMessage.setExtra(c11417gs.m3558a());
        }
        miPushMessage.setNotified(z);
        return miPushMessage;
    }

    public static C11417gs generateMessage(MiPushMessage miPushMessage) {
        C11417gs c11417gs = new C11417gs();
        c11417gs.m3552a(miPushMessage.getMessageId());
        c11417gs.m3543b(miPushMessage.getTopic());
        c11417gs.m3532d(miPushMessage.getDescription());
        c11417gs.m3536c(miPushMessage.getTitle());
        c11417gs.m3537c(miPushMessage.getNotifyId());
        c11417gs.m3555a(miPushMessage.getNotifyType());
        c11417gs.m3544b(miPushMessage.getPassThrough());
        c11417gs.m3550a(miPushMessage.getExtra());
        return c11417gs;
    }
}
