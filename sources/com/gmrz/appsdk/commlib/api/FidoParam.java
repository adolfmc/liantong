package com.gmrz.appsdk.commlib.api;

import com.gmrz.appsdk.commlib.api.IAppSDK;
import com.gmrz.appsdk.util.Constant;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class FidoParam implements Cloneable {
    private static final String TAG = "FidoParam";
    private IAppSDK.ClientLocation location = null;

    /* renamed from: ui */
    private MatcherUI f10296ui = null;

    public Object clone() {
        return super.clone();
    }

    public void enableDeviceCheckRoot(boolean z) {
        Constant.CHECK_ROOT = z;
    }

    public void enableUseBioApiFingerprintUI(boolean z) {
        Constant.BIOMETRIC_UI = z;
    }

    public void enableUseService(boolean z) {
        Constant.USE_SERVICE = z;
    }

    public IAppSDK.ClientLocation getLocation() {
        return this.location;
    }

    public MatcherUI getUi() {
        return this.f10296ui;
    }

    public FidoParam setLocation(IAppSDK.ClientLocation clientLocation) {
        this.location = clientLocation;
        return this;
    }

    public FidoParam setUi(MatcherUI matcherUI) {
        this.f10296ui = matcherUI;
        return this;
    }
}
