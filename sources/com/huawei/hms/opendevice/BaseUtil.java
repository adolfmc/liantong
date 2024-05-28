package com.huawei.hms.opendevice;

import com.huawei.secure.android.common.encrypt.utils.HexUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.opendevice.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public abstract class BaseUtil {
    /* renamed from: a */
    public static String m14389a(byte[] bArr) {
        return HexUtil.byteArray2HexStr(bArr);
    }

    /* renamed from: a */
    public static byte[] m14390a(String str) {
        return HexUtil.hexStr2ByteArray(str);
    }
}
