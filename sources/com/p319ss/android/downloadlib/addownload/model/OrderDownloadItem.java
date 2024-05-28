package com.p319ss.android.downloadlib.addownload.model;

import com.p319ss.android.downloadad.api.download.AdDownloadEventConfig;
import com.p319ss.android.downloadad.api.download.AdDownloadModel;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.ss.android.downloadlib.addownload.model.OrderDownloadItem */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class OrderDownloadItem {

    /* renamed from: b */
    public int f19084b;

    /* renamed from: h */
    public AdDownloadModel f19085h;

    /* renamed from: hj */
    public long f19086hj;

    /* renamed from: ko */
    public boolean f19087ko;

    /* renamed from: mb */
    public String f19088mb;

    /* renamed from: ox */
    public String f19089ox;

    /* renamed from: u */
    public AdDownloadEventConfig f19090u;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.ss.android.downloadlib.addownload.model.OrderDownloadItem$OrderCheckStatus */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface OrderCheckStatus {
        public static final int DELETE_LATE_ORDER = 5;
        public static final int INSTALLED = 2;
        public static final int NORMAL = 1;
        public static final int NO_WIFI_PARAM = 4;
        public static final int REPEAT_DOWNLOAD = 3;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    @Retention(RetentionPolicy.SOURCE)
    /* renamed from: com.ss.android.downloadlib.addownload.model.OrderDownloadItem$OrderStatus */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public @interface OrderStatus {
        public static final int HANDLE_FAILED = -2;
        public static final int ORDERING = 0;
        public static final int ORDER_CANCEL = 2;
        public static final int ORDER_OVERDUE = 3;
        public static final int QUERY_FAILED = -1;
        public static final int SHELVED = 1;
    }

    public String toString() {
        return "OrderDownloadItem{bizType='" + this.f19088mb + "', orderId='" + this.f19089ox + "', orderStatus=" + this.f19084b + ", nextRequestInterval=" + this.f19086hj + ", downloadModel=" + this.f19085h + ", eventConfig=" + this.f19090u + ", enableDownload=" + this.f19087ko + '}';
    }
}
