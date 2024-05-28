package com.p281qq.p282e.comm.p283pi;

import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.qq.e.comm.pi.IBidding */
/* loaded from: E:\11480076_dexfile_execute.dex */
public interface IBidding {
    public static final String ADN_ID = "adnId";
    public static final String EXPECT_COST_PRICE = "expectCostPrice";
    public static final String HIGHEST_LOSS_PRICE = "highestLossPrice";
    public static final String LOSS_REASON = "lossReason";
    public static final String WIN_PRICE = "winPrice";

    @Deprecated
    void sendLossNotification(int i, int i2, String str);

    void sendLossNotification(Map<String, Object> map);

    @Deprecated
    void sendWinNotification(int i);

    void sendWinNotification(Map<String, Object> map);

    void setBidECPM(int i);
}
