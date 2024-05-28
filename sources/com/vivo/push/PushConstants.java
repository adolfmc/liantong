package com.vivo.push;

import android.net.Uri;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.vivo.push.x */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class PushConstants {

    /* renamed from: a */
    public static final Uri f21248a = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/config");

    /* renamed from: b */
    public static final Uri f21249b = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/permission");

    /* renamed from: c */
    public static final Uri f21250c = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/clientState");

    /* renamed from: d */
    public static final Uri f21251d = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/debugInfo");

    /* renamed from: e */
    public static final Uri f21252e = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/agreePrivacyStatement");

    /* renamed from: f */
    public static final Uri f21253f = Uri.parse("content://com.vivo.push.sdk.service.SystemPushConfig/queryAppState");

    /* renamed from: a */
    public static String m5319a(int i) {
        switch (i) {
            case 2002:
                return "method_alias_bind";
            case 2003:
                return "method_alias_unbind";
            case 2004:
                return "method_tag_bind";
            case 2005:
                return "method_tag_unbind";
            case 2006:
                return "method_sdk_bind";
            case 2007:
                return "method_sdk_unbind";
            case 2008:
                return "method_stop";
            default:
                return null;
        }
    }
}
