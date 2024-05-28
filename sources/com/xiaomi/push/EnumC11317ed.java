package com.xiaomi.push;

import android.text.TextUtils;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.xiaomi.push.ed */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum EnumC11317ed {
    COMMAND_REGISTER("register"),
    COMMAND_UNREGISTER("unregister"),
    COMMAND_SET_ALIAS("set-alias"),
    COMMAND_UNSET_ALIAS("unset-alias"),
    COMMAND_SET_ACCOUNT("set-account"),
    COMMAND_UNSET_ACCOUNT("unset-account"),
    COMMAND_SUBSCRIBE_TOPIC("subscribe-topic"),
    COMMAND_UNSUBSCRIBE_TOPIC("unsubscibe-topic"),
    COMMAND_SET_ACCEPT_TIME("accept-time"),
    COMMAND_CHK_VDEVID("check-vdeviceid");
    

    /* renamed from: a */
    public final String f22058a;

    EnumC11317ed(String str) {
        this.f22058a = str;
    }

    /* renamed from: a */
    public static int m4048a(String str) {
        EnumC11317ed[] values;
        int i = -1;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        for (EnumC11317ed enumC11317ed : values()) {
            if (enumC11317ed.f22058a.equals(str)) {
                i = C11303ds.m4120a(enumC11317ed);
            }
        }
        return i;
    }
}
