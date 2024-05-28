package com.huawei.hms.opendevice;

import android.content.Context;
import com.huawei.hmf.tasks.Task;
import com.huawei.hms.api.Api;
import com.huawei.hms.common.HuaweiApi;
import com.huawei.hms.support.api.opendevice.OdidResult;
import com.huawei.hms.support.hianalytics.HiAnalyticsClient;
import com.huawei.hms.utils.JsonUtil;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class OpenDeviceClientImpl extends HuaweiApi<OpenDeviceOptions> implements OpenDeviceClient {

    /* renamed from: a */
    private static final OpenDeviceHmsClientBuilder f11532a = new OpenDeviceHmsClientBuilder();

    /* renamed from: b */
    private static final Api<OpenDeviceOptions> f11533b = new Api<>("HuaweiOpenDevice.API");

    /* renamed from: c */
    private static OpenDeviceOptions f11534c = new OpenDeviceOptions();

    /* JADX INFO: Access modifiers changed from: package-private */
    public OpenDeviceClientImpl(Context context) {
        super(context, f11533b, f11534c, f11532a);
        super.setKitSdkVersion(61100100);
    }

    @Override // com.huawei.hms.opendevice.OpenDeviceClient
    public Task<OdidResult> getOdid() {
        return doWrite(new OpenDeviceTaskApiCall("opendevice.getodid", JsonUtil.createJsonString(null), HiAnalyticsClient.reportEntry(getContext(), "opendevice.getodid", 61100100)));
    }
}
