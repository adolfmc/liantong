package com.huawei.hms.utils;

import android.text.TextUtils;
import com.huawei.hms.support.log.HMSLog;
import java.sql.Timestamp;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class ResolutionFlagUtil {

    /* renamed from: a */
    private static volatile ResolutionFlagUtil f11851a;

    /* renamed from: b */
    private static final Map<String, Long> f11852b = new ConcurrentHashMap();

    /* renamed from: c */
    private static final Object f11853c = new Object();

    private ResolutionFlagUtil() {
    }

    /* renamed from: a */
    private void m14031a() {
        long time = new Timestamp(System.currentTimeMillis()).getTime() - 10800000;
        for (String str : f11852b.keySet()) {
            Map<String, Long> map = f11852b;
            Long l = map.get(str);
            if (l != null && l.longValue() != 0) {
                if (time >= l.longValue()) {
                    map.remove(str);
                    HMSLog.m14110i("ResolutionFlagUtil", "remove resolution flag because aging time: " + str);
                }
            } else {
                map.remove(str);
                HMSLog.m14110i("ResolutionFlagUtil", "remove resolution flag because the data in this pair was abnormal: " + str);
            }
        }
    }

    public static ResolutionFlagUtil getInstance() {
        if (f11851a != null) {
            return f11851a;
        }
        synchronized (f11853c) {
            if (f11851a == null) {
                f11851a = new ResolutionFlagUtil();
            }
        }
        return f11851a;
    }

    public long getResolutionFlag(String str) {
        if (str == null) {
            HMSLog.m14112e("ResolutionFlagUtil", "transactionId is null");
            return 0L;
        }
        Map<String, Long> map = f11852b;
        if (map.get(str) != null) {
            return map.get(str).longValue();
        }
        return 0L;
    }

    public void removeResolutionFlag(String str) {
        if (str == null) {
            HMSLog.m14112e("ResolutionFlagUtil", "transactionId is null");
            return;
        }
        f11852b.remove(str);
        HMSLog.m14110i("ResolutionFlagUtil", "remove resolution flag");
    }

    public void saveResolutionFlag(String str, long j) {
        if (!TextUtils.isEmpty(str) && j != 0) {
            m14030a(str, j);
            return;
        }
        HMSLog.m14112e("ResolutionFlagUtil", "saveResolutionFlag error, transactionId: " + str + ", timestamp: " + j);
    }

    /* renamed from: a */
    private void m14030a(String str, long j) {
        Map<String, Long> map = f11852b;
        synchronized (map) {
            m14031a();
            map.put(str, Long.valueOf(j));
            HMSLog.m14110i("ResolutionFlagUtil", "save resolution flag");
        }
    }
}
