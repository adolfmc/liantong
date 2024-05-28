package com.sinovatech.unicom.separatemodule.miniprogram.jsplugin.base.socket;

import com.sinovatech.unicom.common.EncodeUtils;
import com.xuhao.didi.core.iocore.interfaces.ISendable;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SendData implements ISendable {
    private String content;

    public SendData(String str) {
        this.content = "";
        this.content = str;
    }

    @Override // com.xuhao.didi.core.iocore.interfaces.ISendable
    public byte[] parse() {
        return EncodeUtils.hexDecode(this.content);
    }
}
