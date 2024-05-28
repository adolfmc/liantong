package com.p189cn.froad.clouddecodingsdk.core;

import android.content.Context;
import com.eidlink.idocr.sdk.bean.EidlinkInitParams;

/* compiled from: Proguard */
/* renamed from: com.cn.froad.clouddecodingsdk.core.EidFFTInitParams */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class EidFFTInitParams extends EidlinkInitParams {
    private String encFileName;
    private String licName;

    public EidFFTInitParams(Context context, String str, String str2, int i, int i2) {
        super(context, str, str2, i, i2);
    }

    public EidFFTInitParams(Context context, String str, String str2, int i, int i2, String str3, String str4) {
        super(context, str, str2, i, i2);
        this.licName = str3;
        this.encFileName = str4;
    }

    public String getEncFileName() {
        return this.encFileName;
    }

    public String getLicName() {
        return this.licName;
    }

    public void setEncFileName(String str) {
        this.encFileName = str;
    }

    public void setLicName(String str) {
        this.licName = str;
    }

    @Override // com.eidlink.idocr.sdk.bean.EidlinkInitParams
    public String toString() {
        return "EidFFTInitParams{licName='" + this.licName + "', encFileName='" + this.encFileName + "', context=" + this.context + ", appid='" + this.appid + "', ip='" + this.f9735ip + "', port=" + this.port + ", envIdCode=" + this.envIdCode + '}';
    }
}
