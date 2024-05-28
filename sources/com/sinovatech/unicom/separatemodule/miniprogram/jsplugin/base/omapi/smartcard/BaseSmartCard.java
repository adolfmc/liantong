package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class BaseSmartCard {
    public static final String STATUS_CODE_10 = "10";
    public static final String STATUS_CODE_11 = "11";
    public static final String STATUS_CODE_12 = "12";
    public static final String STATUS_CODE_SUCCESS = "0000";
    private EnumReaderType mReaderType;

    public abstract CardResult closeChannel();

    public abstract CardResult closeService();

    public abstract CardResult execute(String str);

    public abstract CardResult openChannel(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    public CardResult operFail(String str) {
        return new CardResult("10", str);
    }

    public EnumReaderType getmReaderType() {
        EnumReaderType enumReaderType = this.mReaderType;
        return enumReaderType == null ? EnumReaderType.READER_TYPE_SIM : enumReaderType;
    }

    public void setmReaderType(EnumReaderType enumReaderType) {
        this.mReaderType = enumReaderType;
    }
}
