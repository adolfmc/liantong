package com.gmrz.appsdk.commlib;

import android.content.Context;
import android.content.Intent;
import com.gmrz.appsdk.commlib.api.FidoStatus;
import com.gmrz.appsdk.commlib.api.ICommunicationClient;
import com.gmrz.appsdk.commlib.api.ICommunicationClientResponse;
import com.gmrz.appsdk.util.Logger;
import java.lang.reflect.Method;

/* renamed from: com.gmrz.appsdk.commlib.f */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class UafLocalCommClient implements ICommunicationClient {

    /* renamed from: a */
    private final Context f10314a;

    public UafLocalCommClient(Context context) {
        this.f10314a = context;
    }

    /* renamed from: a */
    public static FidoStatus m15814a(short s) {
        switch (s) {
            case 0:
                return FidoStatus.SUCCESS;
            case 1:
                return FidoStatus.WAIT_USER_ACTION;
            case 3:
                return FidoStatus.CANCELED;
            case 5:
                return FidoStatus.NO_MATCH;
            case 6:
                return FidoStatus.PROTOCOL_ERROR;
            case 7:
                return FidoStatus.APP_NOT_FOUND;
            case 9:
            case 19:
                return FidoStatus.KEY_INVALID_PERMANENTLY;
            case 14:
                return FidoStatus.USER_PREFERRED_BIO_IRIS;
            case 18:
                return FidoStatus.GM_NEED_REGISTER;
            case 255:
                return FidoStatus.FAILED;
            default:
                Logger.m15757e("UafLocalCommClient", "Unexpected error code received from the client");
                return FidoStatus.PROTOCOL_ERROR;
        }
    }

    @Override // com.gmrz.appsdk.commlib.api.ICommunicationClient
    public long sendRequest(String str, Object obj, ICommunicationClientResponse iCommunicationClientResponse) {
        Intent intent = (Intent) obj;
        try {
            Class<?> cls = Class.forName("com.gmrz.android.uaf.framework.service.UafIntentProcessor");
            Object newInstance = cls.newInstance();
            Method method = cls.getMethod("processIntent", Intent.class, Context.class);
            Object[] objArr = {intent, this.f10314a};
            Logger.m15756i("UafLocalCommClient", "Response was returned");
            iCommunicationClientResponse.onResponse(UafIntentParser.m15815a((Intent) method.invoke(newInstance, objArr)));
            return 0L;
        } catch (Exception e) {
            Logger.m15757e("UafLocalCommClient", "Failed to receive Uaf response from service");
            e.printStackTrace();
            return -1L;
        }
    }
}
