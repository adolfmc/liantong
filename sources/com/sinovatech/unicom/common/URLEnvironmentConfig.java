package com.sinovatech.unicom.common;

import android.text.TextUtils;
import com.sinovatech.unicom.p318ui.App;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class URLEnvironmentConfig {
    private static String HttpPrefix = null;
    public static final String key = "switch_environment";

    /* renamed from: sp */
    public static SharePreferenceUtil f18451sp = App.getSharePreferenceUtil();
    public static String ProductionEnvironment = "Production";
    public static String PrepubEnvironment = "Prepub";
    public static String PrepubEnvironment2 = "Prepub2";
    public static String DevelopmentEnvironment = "Development";
    public static String Development144000 = "Development_14_4000";
    public static String DevelopmentTest = "Development_test";
    public static String Development146000 = "Development_14_6000";
    public static String Development114000 = "Development_11_4000";
    public static String Development116000 = "Development_11_6000";
    public static String PrepubEnvironment_shengfen = "PrepubEnvironment_shengfen";
    private static Map<String, String> environmentDomainMap = new HashMap();
    private static Map<String, String> environmentServerMap = new HashMap();
    private static Map<String, String> environmentSubTypeMap = new HashMap();
    private static Map<String, String> environmentTypeMap = new HashMap();

    public static String getPrepubCookie() {
        return "prepub=true";
    }

    static {
        if (!isForPublish()) {
            if (TextUtils.isEmpty(f18451sp.getString("switch_environment"))) {
                f18451sp.putString("switch_environment", "Production");
            }
            environmentDomainMap.put(ProductionEnvironment, "m.client.10010.com");
            environmentServerMap.put(ProductionEnvironment, "m.client.10010.com/mobileService");
            environmentSubTypeMap.put(ProductionEnvironment, "");
            Map<String, String> map = environmentTypeMap;
            String str = ProductionEnvironment;
            map.put(str, str);
            environmentDomainMap.put(PrepubEnvironment, "m.client.10010.com");
            environmentServerMap.put(PrepubEnvironment, "m.client.10010.com/mobileService");
            environmentSubTypeMap.put(PrepubEnvironment, "");
            Map<String, String> map2 = environmentTypeMap;
            String str2 = PrepubEnvironment;
            map2.put(str2, str2);
            environmentDomainMap.put(PrepubEnvironment2, "client.10010.com");
            environmentServerMap.put(PrepubEnvironment2, "client.10010.com/mobileService");
            environmentSubTypeMap.put(PrepubEnvironment2, "");
            Map<String, String> map3 = environmentTypeMap;
            String str3 = PrepubEnvironment2;
            map3.put(str3, str3);
            environmentDomainMap.put(DevelopmentTest, "ecstest2018.10010.com");
            environmentServerMap.put(DevelopmentTest, "ecstest2018.10010.com/mobileServiceClient-test");
            environmentSubTypeMap.put(DevelopmentTest, "Test");
            environmentTypeMap.put(DevelopmentTest, DevelopmentEnvironment);
            environmentDomainMap.put(Development144000, "ecstest2018.10010.com");
            environmentServerMap.put(Development144000, "ecstest2018.10010.com/mobileServiceClient-test");
            environmentSubTypeMap.put(Development144000, "14_4000");
            environmentTypeMap.put(Development144000, DevelopmentEnvironment);
            environmentDomainMap.put(Development146000, "ecstest2018.10010.com");
            environmentServerMap.put(Development146000, "ecstest2018.10010.com/mobileServiceClient-test1");
            environmentSubTypeMap.put(Development146000, "14_6000");
            environmentTypeMap.put(Development146000, DevelopmentEnvironment);
            environmentDomainMap.put(Development114000, "ecstest2018.10010.com");
            environmentServerMap.put(Development114000, "ecstest2018.10010.com/mobileServiceClient");
            environmentSubTypeMap.put(Development114000, "11_4000");
            environmentTypeMap.put(Development114000, DevelopmentEnvironment);
            environmentDomainMap.put(Development116000, "ecstest2018.10010.com");
            environmentServerMap.put(Development116000, "ecstest2018.10010.com/mobileServiceClient1");
            environmentSubTypeMap.put(Development116000, "11_6000");
            environmentTypeMap.put(Development116000, DevelopmentEnvironment);
            environmentDomainMap.put(PrepubEnvironment_shengfen, "m2.client.10010.com");
            environmentServerMap.put(PrepubEnvironment_shengfen, "m2.client.10010.com/mobile4exp");
            environmentSubTypeMap.put(PrepubEnvironment_shengfen, "sf_yfb");
            Map<String, String> map4 = environmentTypeMap;
            String str4 = PrepubEnvironment_shengfen;
            map4.put(str4, str4);
        }
        HttpPrefix = "https://";
    }

    public static void switchHttps(boolean z) {
        if (((z && !isPreMoxEnvironment()) || isPrepubEnvironment()) && !isDevelopmentEnvironment()) {
            HttpPrefix = "https://";
        } else {
            HttpPrefix = "https://";
        }
    }

    public static String getHttpPrefix() {
        return HttpPrefix;
    }

    public static boolean isForPublish() {
        return "YES".equals("YES");
    }

    public static boolean isForTYCJTest() {
        if (isForPublish()) {
            return false;
        }
        return "NO".equals("YES");
    }

    public static boolean isDevelopmentEnvironment() {
        if (isForPublish()) {
            return "Production".equals(DevelopmentEnvironment);
        }
        return DevelopmentEnvironment.equals(environmentTypeMap.get(f18451sp.getString("switch_environment")));
    }

    public static boolean isPrepubEnvironment() {
        if (isForPublish()) {
            return "Production".equals(PrepubEnvironment);
        }
        return PrepubEnvironment.equals(environmentTypeMap.get(f18451sp.getString("switch_environment"))) || isPrepubEnvironment2();
    }

    public static boolean isPrepubEnvironment2() {
        if (isForPublish()) {
            return "Production".equals(PrepubEnvironment2);
        }
        return PrepubEnvironment2.equals(environmentTypeMap.get(f18451sp.getString("switch_environment")));
    }

    public static boolean isProductionEnvironment() {
        if (isForPublish()) {
            return "Production".equals(ProductionEnvironment);
        }
        return ProductionEnvironment.equals(environmentTypeMap.get(f18451sp.getString("switch_environment")));
    }

    public static boolean isPreMoxEnvironment() {
        if (isForPublish()) {
            return "Production".equals(PrepubEnvironment_shengfen);
        }
        return PrepubEnvironment_shengfen.equals(environmentTypeMap.get(f18451sp.getString("switch_environment")));
    }

    public static String getAppDomainNoProtocol() {
        return isForPublish() ? "m.client.10010.com" : environmentDomainMap.get(f18451sp.getString("switch_environment"));
    }

    public static String getAppServerURLNoProtocol() {
        return isForPublish() ? "m.client.10010.com/mobileService" : environmentServerMap.get(f18451sp.getString("switch_environment"));
    }

    public static String getDevelopmentEnvironmentSubType() {
        return isForPublish() ? "" : environmentSubTypeMap.get(f18451sp.getString("switch_environment"));
    }
}
