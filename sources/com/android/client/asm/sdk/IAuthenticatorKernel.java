package com.android.client.asm.sdk;

import android.app.Activity;
import com.android.uaf.asmcore.AKProcessor;
import com.gmrz.android.client.asm.api.uaf.json.Extension;
import java.util.List;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public interface IAuthenticatorKernel {
    public static final int statusCode = 0;

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public enum AKDataKeys {
        AAID,
        APPID,
        MATCHER_IN_PARAMS,
        DESCRIPTOR
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10201592_dexfile_execute.dex */
    public interface IExtAuthenticatorKernel {
        List<Extension> extExtract(AKProcessor.AKResponseParams aKResponseParams);

        void extInit(AKProcessor.AKRequestParams aKRequestParams);
    }

    IAKDigestMethod getDigestMethod();

    boolean postProcess();

    byte[] processRequest(byte[] bArr, Map<AKDataKeys, Object> map) throws AuthenticatorException;

    void setCallerActivity(Activity activity);
}
