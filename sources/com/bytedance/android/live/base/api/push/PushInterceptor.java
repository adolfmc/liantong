package com.bytedance.android.live.base.api.push;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface PushInterceptor {
    InterceptResult intercept();

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class InterceptResult {
        public final boolean intercept;
        public final String interceptReason;

        public InterceptResult(boolean z) {
            this(z, "");
        }

        public InterceptResult(boolean z, String str) {
            this.intercept = z;
            this.interceptReason = "";
        }
    }
}
