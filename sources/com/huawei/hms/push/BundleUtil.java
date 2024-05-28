package com.huawei.hms.push;

import android.os.Bundle;
import com.huawei.hms.support.log.HMSLog;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.push.b */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class BundleUtil {
    /* renamed from: a */
    public static byte[] m14299a(Bundle bundle, String str) {
        try {
            byte[] byteArray = bundle.getByteArray(str);
            return byteArray == null ? new byte[0] : byteArray;
        } catch (Exception e) {
            HMSLog.m14110i("BundleUtil", "getByteArray exception" + e.getMessage());
            return new byte[0];
        }
    }

    /* renamed from: b */
    public static String m14298b(Bundle bundle, String str) {
        try {
            return bundle.getString(str);
        } catch (Exception e) {
            HMSLog.m14110i("BundleUtil", "getString exception" + e.getMessage());
            return null;
        }
    }

    /* renamed from: c */
    public static String m14297c(Bundle bundle, String str) {
        try {
            String string = bundle.getString(str);
            return string == null ? "" : string;
        } catch (Exception e) {
            HMSLog.m14110i("BundleUtil", "getString exception" + e.getMessage());
            return "";
        }
    }
}
