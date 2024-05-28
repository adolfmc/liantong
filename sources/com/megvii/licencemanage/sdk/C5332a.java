package com.megvii.licencemanage.sdk;

import android.content.Context;
import com.megvii.licencemanage.sdk.jni.LicenceApi;
import java.util.regex.Pattern;

/* renamed from: com.megvii.licencemanage.sdk.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5332a {

    /* renamed from: a */
    private int f12352a = 0;

    /* renamed from: b */
    private Context f12353b;

    public C5332a(Context context) {
        this.f12353b = context;
    }

    /* renamed from: a */
    public final String m13621a(String str, int i, long[] jArr) {
        synchronized (C5332a.class) {
            this.f12352a = 0;
            if (this.f12353b == null) {
                this.f12352a = 1;
                return null;
            }
            String nativeGetLicense = LicenceApi.nativeGetLicense(this.f12353b, str, 30, jArr);
            if (Pattern.compile("[0-9]+").matcher(nativeGetLicense).matches()) {
                this.f12352a = Integer.parseInt(nativeGetLicense);
                return null;
            }
            return nativeGetLicense;
        }
    }

    /* renamed from: a */
    public final boolean m13622a(String str) {
        this.f12352a = 0;
        Context context = this.f12353b;
        if (context == null || str == null) {
            this.f12352a = 1;
            return false;
        }
        this.f12352a = LicenceApi.nativeSetLicense(context, str);
        return this.f12352a == 0;
    }

    /* renamed from: a */
    public final String m13623a() {
        int i = this.f12352a;
        switch (i) {
            case -1:
                return "MG_RETCODE_FAILED";
            case 0:
                return "MG_RETCODE_OK";
            case 1:
                return "MG_RETCODE_INVALID_ARGUMENT";
            case 2:
                return "MG_RETCODE_INVALID_HANDLE";
            case 3:
                return "MG_RETCODE_INDEX_OUT_OF_RANGE";
            default:
                switch (i) {
                    case 101:
                        return "MG_RETCODE_EXPIRE";
                    case 102:
                        return "MG_RETCODE_INVALID_BUNDLEID";
                    case 103:
                        return "MG_RETCODE_INVALID_LICENSE";
                    case 104:
                        return "MG_RETCODE_INVALID_MODEL";
                    default:
                        return null;
                }
        }
    }
}
