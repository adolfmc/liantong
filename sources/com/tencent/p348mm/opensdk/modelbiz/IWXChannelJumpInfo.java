package com.tencent.p348mm.opensdk.modelbiz;

import android.os.Bundle;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.tencent.mm.opensdk.modelbiz.IWXChannelJumpInfo */
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface IWXChannelJumpInfo {
    public static final int WX_CHANNEL_JUMP_TYPE_MINI_PROGRAM = 1;
    public static final int WX_CHANNEL_JUMP_TYPE_UNKNOWN = 0;
    public static final int WX_CHANNEL_JUMP_TYPE_URL = 2;

    boolean checkArgs();

    void serialize(Bundle bundle);

    int type();

    void unserialize(Bundle bundle);
}
