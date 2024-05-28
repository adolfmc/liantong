package com.bytedance.applog;

import com.bytedance.applog.profile.UserProfileCallback;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.bytedance.applog.f1 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C3581f1 {

    /* renamed from: a */
    public static final String[] f8446a = {"aid", "region", "os", "package", "app_version", "sdk_version", "os_version", "device_model", "resolution", "language", "timezone", "access", "display_name", "channel", "carrier", "app_language", "app_region", "tz_name", "tz_offset", "install_id", "openudid", "mcc_mnc", "rom", "manifest_version_code", "device_manufacturer", "clientudid", "sig_hash", "display_density", "os_api", "update_version_code", "density_dpi", "version_code", "sim_serial_number", "release_build", "udid", "cpu_abi", "google_aid"};

    /* renamed from: b */
    public static final String[] f8447b = {"setOnce", "synchronize"};

    /* renamed from: c */
    public static final int[] f8448c = {-1, -1};

    /* renamed from: d */
    public static final long[] f8449d = {-1, -1};

    @NBSInstrumented
    /* renamed from: com.bytedance.applog.f1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3582a implements UserProfileCallback {

        /* renamed from: a */
        public final /* synthetic */ int f8450a;

        /* renamed from: b */
        public final /* synthetic */ JSONObject f8451b;

        /* renamed from: c */
        public final /* synthetic */ UserProfileCallback f8452c;

        public C3582a(int i, JSONObject jSONObject, UserProfileCallback userProfileCallback) {
            this.f8450a = i;
            this.f8451b = jSONObject;
            this.f8452c = userProfileCallback;
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onFail(int i) {
            this.f8452c.onFail(i);
        }

        @Override // com.bytedance.applog.profile.UserProfileCallback
        public void onSuccess() {
            int[] iArr = C3581f1.f8448c;
            int i = this.f8450a;
            JSONObject jSONObject = this.f8451b;
            iArr[i] = (!(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).hashCode();
            C3581f1.f8449d[this.f8450a] = System.currentTimeMillis();
            this.f8452c.onSuccess();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x005d  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static void m17300a(com.bytedance.applog.C3591h r10, int r11, org.json.JSONObject r12, com.bytedance.applog.profile.UserProfileCallback r13, android.os.Handler r14, boolean r15) {
        /*
            Method dump skipped, instructions count: 363
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.applog.C3581f1.m17300a(com.bytedance.applog.h, int, org.json.JSONObject, com.bytedance.applog.profile.UserProfileCallback, android.os.Handler, boolean):void");
    }
}
