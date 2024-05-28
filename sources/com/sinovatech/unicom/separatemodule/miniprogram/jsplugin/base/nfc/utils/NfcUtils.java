package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.nfc.utils;

import android.content.Context;
import android.nfc.NfcAdapter;
import android.nfc.NfcManager;
import com.sinovatech.unicom.separatemodule.miniprogram.utils.MsLogUtil;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class NfcUtils {
    public static boolean hasNfc(Context context) {
        if (context == null) {
            return false;
        }
        try {
            NfcAdapter defaultAdapter = ((NfcManager) context.getSystemService("nfc")).getDefaultAdapter();
            if (defaultAdapter != null) {
                return defaultAdapter.isEnabled();
            }
            return false;
        } catch (Exception e) {
            MsLogUtil.m7977e("NFC:", e.getMessage());
            return false;
        }
    }
}
