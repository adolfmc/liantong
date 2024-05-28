package com.huawei.hms.core.aidl;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class CodecLookup {
    private CodecLookup() {
    }

    public static MessageCodec find(int i) {
        if (i == 2) {
            return new MessageCodecV2();
        }
        return new MessageCodec();
    }
}
