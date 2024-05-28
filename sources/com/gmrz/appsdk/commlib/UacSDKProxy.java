package com.gmrz.appsdk.commlib;

import android.content.Context;
import android.util.Base64;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.FidoOut;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.IAppSDK;
import com.gmrz.appsdk.commlib.api.IGmrzAdapter;
import com.gmrz.appsdk.commlib.api.UACPlugin;
import com.gmrz.appsdk.commlib.api.UACType;
import com.gmrz.appsdk.util.Logger;
import java.util.Map;
import java.util.concurrent.Semaphore;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UacSDKProxy implements IAppSDK {
    private final String TAG = UacSDKProxy.class.getSimpleName() + "_uac";
    FidoOut outValue = new FidoOut();
    protected Semaphore semLock = new Semaphore(0, true);

    public void onResponse(byte[] bArr, String str) {
        if (bArr == null) {
            if (str != null) {
                Logger.m15757e(this.TAG + "get source error info: ", str);
            }
            FidoOut fidoOut = this.outValue;
            fidoOut.fidoStatus = FidoStatus.FAILED;
            fidoOut.fidoStatusMsg = str;
            this.semLock.release();
            return;
        }
        this.outValue.responseParams = Base64.encodeToString(bArr, 11);
        FidoOut fidoOut2 = this.outValue;
        fidoOut2.fidoStatus = FidoStatus.SUCCESS;
        fidoOut2.fidoStatusMsg = str;
        Logger.m15757e(this.TAG + "get source info: ", this.outValue.toString());
        this.semLock.release();
    }

    @Override // com.gmrz.appsdk.commlib.api.IAppSDK
    public FidoOut process(Context context, Object obj) {
        FidoIn fidoIn = (FidoIn) obj;
        if (fidoIn != null) {
            UACType uACType = fidoIn.uacType;
            if (uACType == UACType.REMOTEFACE) {
                Map<UACPlugin, IGmrzAdapter> authAbilty = fidoIn.getAuthAbilty();
                UACPlugin uACPlugin = UACPlugin.REMOTEFACEPLUGIN;
                if (authAbilty.get(uACPlugin) != null && (fidoIn.getAuthAbilty().get(uACPlugin) instanceof IGmrzAdapter)) {
                    fidoIn.getAuthAbilty().get(uACPlugin).setFaceListener(this, context, fidoIn.getCryptoKey(context.getPackageName()));
                } else {
                    FidoOut fidoOut = this.outValue;
                    fidoOut.fidoStatus = FidoStatus.NOT_INITFIDO;
                    return fidoOut;
                }
            } else if (uACType == UACType.REMOTEVOICE) {
                Map<UACPlugin, IGmrzAdapter> authAbilty2 = fidoIn.getAuthAbilty();
                UACPlugin uACPlugin2 = UACPlugin.REMOTEVOICEPLUGIN;
                if (authAbilty2.get(uACPlugin2) != null && (fidoIn.getAuthAbilty().get(uACPlugin2) instanceof IGmrzAdapter)) {
                    fidoIn.getAuthAbilty().get(uACPlugin2).setVoiceListener(this, context, fidoIn.getRequestVoiceData());
                } else {
                    FidoOut fidoOut2 = this.outValue;
                    fidoOut2.fidoStatus = FidoStatus.NOT_INITFIDO;
                    return fidoOut2;
                }
            } else if (uACType == UACType.REALNAME_EXT) {
                Map<UACPlugin, IGmrzAdapter> authAbilty3 = fidoIn.getAuthAbilty();
                UACPlugin uACPlugin3 = UACPlugin.REMOTEFACEPLUGIN;
                if (authAbilty3.get(uACPlugin3) != null && (fidoIn.getAuthAbilty().get(uACPlugin3) instanceof IGmrzAdapter)) {
                    fidoIn.getAuthAbilty().get(uACPlugin3).setFaceListener(this, context);
                } else {
                    FidoOut fidoOut3 = this.outValue;
                    fidoOut3.fidoStatus = FidoStatus.NOT_INITFIDO;
                    return fidoOut3;
                }
            }
            Logger.m15757e(this.TAG, "Complete waiting for response");
            boolean z = false;
            try {
                this.semLock.acquire();
            } catch (InterruptedException unused) {
                z = true;
            }
            if (z) {
                this.outValue.fidoStatus = FidoStatus.FAILED;
            }
        } else {
            this.outValue.fidoStatus = FidoStatus.FAILED;
        }
        Logger.m15757e(this.TAG + "uac outValue ", this.outValue.toString());
        return this.outValue;
    }
}
