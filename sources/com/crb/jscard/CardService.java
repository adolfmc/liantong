package com.crb.jscard;

import android.content.Context;
import java.util.concurrent.Executors;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class CardService {
    private static final String TAG = "CardService";
    public static Context context;
    private static HttpUtils httpUtils;

    /* renamed from: com.crb.jscard.CardService$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class RunnableC4180a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ String f9724a;

        /* renamed from: b */
        public final /* synthetic */ String f9725b;

        /* renamed from: c */
        public final /* synthetic */ String f9726c;

        public RunnableC4180a(String str, String str2, String str3) {
            this.f9724a = str;
            this.f9725b = str2;
            this.f9726c = str3;
        }

        @Override // java.lang.Runnable
        public void run() {
            InstallAndPersonalUtil.m2073a(CardService.httpUtils, this.f9724a, this.f9725b, this.f9726c);
        }
    }

    public static void CloseWebview() {
        new HomeActivity().m16298e();
    }

    public static void close() {
        CardInteractionUtil.m22200a();
    }

    public static void downloadPersonalizedData(String str, String str2, String str3) {
        Executors.newFixedThreadPool(5).execute(new RunnableC4180a(str, str2, str3));
    }

    public static String getBanlaceAndCardNumber(String str) {
        return GetBalanceAndCardNumberUtil.m22195a(str);
    }

    public static void getSetDefaultCardInfo() {
        GetDefaultCardUtil.m2119a();
    }

    public static String getSimCardAndDeviceInfo() {
        return SimCardAndDeviceInfoUtil.m2011a(context);
    }

    public static String getTransactionRecords(String str) {
        return TransactionRecordsUtil.m1976a(str);
    }

    public static void init(Context context2) {
        if (context == null) {
            context = context2;
        }
        httpUtils = HttpUtils.m159a();
    }

    public static String openChannel(String str) {
        return CardInteractionUtil.m22199a(str);
    }

    public static String setDefaultApplication(String str, String str2) {
        return SetDefaultCardUtil.m2027a(str, str2);
    }

    public static void toReCharge(String str, String str2) {
        ReChargeUtil.m2049a(httpUtils, str, str2);
    }

    public static void transmit(String str) {
        CardInteractionUtil.m22197c(str);
    }
}
