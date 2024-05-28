package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.smartcard;

import android.os.Build;
import android.support.annotation.NonNull;
import com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.omapi.utils.Hex;
import java.util.Objects;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SmartCard {
    private BaseSmartCard smartCardInterface;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    static class SingletonHolder {
        private static final SmartCard instance = new SmartCard();

        private SingletonHolder() {
        }
    }

    public static SmartCard getInstance() {
        return SingletonHolder.instance;
    }

    private SmartCard() {
        if (Build.VERSION.SDK_INT >= 28) {
            this.smartCardInterface = new SmartCardByGoogle();
        } else {
            this.smartCardInterface = new SmartCardBySimalliance();
        }
    }

    public CardResult execute(String str) {
        return this.smartCardInterface.execute(str);
    }

    public CardResult execute(@NonNull byte[] bArr) {
        return execute((String) Objects.requireNonNull(Hex.bytesToHexString(bArr)));
    }

    public CardResult execute(@NonNull String str, @NonNull String str2) {
        CardResult execute = execute(str);
        if (execute.getStatus().equals("0000")) {
            boolean z = false;
            if (execute.getStatus().equals("0000")) {
                try {
                    z = execute.check(str2);
                } catch (Exception e) {
                    execute.setStatus("10");
                    execute.setMsg(e.getMessage());
                }
            }
            if (!z) {
                execute.setStatus("12");
                execute.setMsg("当前resSw结果与期望值sw不匹配");
            }
            return execute;
        }
        return execute;
    }

    public CardResult openChannel(String str) {
        return this.smartCardInterface.openChannel(str);
    }

    public CardResult closeChannel() {
        return this.smartCardInterface.closeChannel();
    }

    public CardResult closeService() {
        return this.smartCardInterface.closeService();
    }

    public EnumReaderType getmReaderType() {
        return this.smartCardInterface.getmReaderType();
    }

    public void setmReaderType(EnumReaderType enumReaderType) {
        this.smartCardInterface.setmReaderType(enumReaderType);
    }
}
