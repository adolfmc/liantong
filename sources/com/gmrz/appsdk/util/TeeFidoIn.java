package com.gmrz.appsdk.util;

import android.content.Context;
import com.gmrz.appsdk.FidoIn;
import com.gmrz.appsdk.commlib.UAFIntentType;
import com.gmrz.appsdk.commlib.api.FidoParam;
import com.gmrz.appsdk.commlib.api.IAppSDK;
import java.util.HashMap;
import java.util.Map;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class TeeFidoIn {
    public static TeeFidoIn Builder() {
        return new TeeFidoIn();
    }

    private Map<String, String> getChannelBindings() {
        HashMap hashMap = new HashMap();
        hashMap.put("serverEndPoint", null);
        hashMap.put("tlsServerCertificate", null);
        hashMap.put("cid_pubkey", null);
        hashMap.put("tlsUnique", null);
        return hashMap;
    }

    public FidoIn getTeeFidoIn(Context context) {
        return FidoIn.Builder().setCallerActivity(context).setFidoIn("[{\"header\":{\"upv\":{\"major\":1,\"minor\":0},\"op\":\"Reg\",\"appID\":\"\",\"serverData\":\"1\"},\"challenge\":\"1AM2yZY4-9SG4Ns7-hMdB8IV_FTDKFFiUqNJNVbsVoo\",\"username\":\"1\",\"policy\":{\"accepted\":[[{\"userVerification\":2,\"keyProtection\":6,\"authenticationAlgorithms\":[1,2,3,4,5,6],\"assertionSchemes\":[\"UAFV1TLV\"]}]]}}]").setCheckpolicy(true).setChannelBinding(getChannelBindings()).setUafIntent(UAFIntentType.CHECK_POLICY.toString()).setFidoParam(new FidoParam().setLocation(IAppSDK.ClientLocation.REMOTE_CLIENT));
    }
}
