package com.froad;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* compiled from: Proguard */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ErrorMessage {
    public static final String Code_bank_card_read_one_failed = "F30001";
    public static final String Code_bank_card_read_two_failed = "F30002";
    public static final String Code_read_card_activity_null = "F30003";
    public static final String Code_read_card_error_other = "F3F001";
    public static final String Code_read_card_nfc_not_open = "F30006";
    public static final String Code_read_card_nfc_not_support = "F30005";
    public static final String Code_read_card_system_version_low = "F30004";
    public static final String Code_read_card_type_error = "F30007";
    private static final String TAG = "ErrorMessage";
    public static final String bank_card_read_failed = "银行卡读取失败，请确认手机NFC已开启，IC卡正确贴附于手机NFC区域 [%1$s]。";

    public static String getShowVariableMsg(String str, Object... objArr) {
        return String.format(str, objArr);
    }
}
