package com.tencent.p348mm.sdk.constants;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.sdk.constants.Build */
/* loaded from: E:\11617560_dexfile_execute.dex */
public final class Build {
    public static final int EMOJI_SUPPORTED_SDK_INT = 553844737;
    public static final int FAVORITE_SUPPPORTED_SDK_INT = 570425345;
    public static final int MESSAGE_ACTION_SUPPPORTED_SDK_INT = 570490883;
    public static final int MIN_SDK_INT = 553713665;
    public static final int MUSIC_DATA_URL_SUPPORTED_SDK_INT = 553910273;
    public static final int OPENID_SUPPORTED_SDK_INT = 570425345;
    public static final int PAY_SUPPORTED_SDK_INT = 570425345;
    public static final int SDK_INT = 570490883;
    public static final String SDK_VERSION_NAME = "android 2.1.3";
    public static final int TIMELINE_SUPPORTED_SDK_INT = 553779201;

    private Build() {
        throw new RuntimeException(getClass().getSimpleName() + " should not be instantiated");
    }

    public static int getMajorVersion() {
        return 2;
    }

    public static int getMinorVersion() {
        return 1;
    }
}
