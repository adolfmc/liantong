package com.sdk.base.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.module.config.BaseConfig;
import com.sdk.p286b.C6964a;
import com.sdk.p290f.C6993a;
import com.sdk.p293i.C7007a;
import com.sdk.p294j.C7008a;
import com.sdk.p298n.C7014a;
import com.sdk.p299o.C7026b;
import com.sdk.p300p.C7030d;
import com.sdk.p304t.C7039a;
import java.nio.charset.Charset;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class ToolUtils {
    public static String Base64_Decrypt(String str) {
        return new String(C7030d.m8137a(str), Charset.defaultCharset());
    }

    public static String RsaDecrypt(String str, String str2) {
        return C7026b.m8142a(str, str2);
    }

    public static void clearCache(Context context) {
        LogUtils.m8185i(C6964a.f18051a, "cache clear", C6964a.f18052b);
        String str = C7008a.f18156a;
        SharedPreferences sharedPreferences = context.getSharedPreferences("ZzxCache", 0);
        SharedPreferences.Editor edit = sharedPreferences.edit();
        for (String str2 : sharedPreferences.getAll().keySet()) {
            if (str2.startsWith("accessCode")) {
                edit.remove(str2);
            }
        }
        edit.commit();
    }

    public static String getAppDebugInfo(Context context, String str) {
        String str2;
        String m8161a = C7007a.m8161a(context, BaseConfig.apk);
        String str3 = C7039a.f18200b;
        StringBuilder sb = new StringBuilder();
        sb.append("\n-------------------");
        sb.append("\n网络类型：");
        switch (C7014a.m8145b(context).mo8144a()) {
            case -1:
                str2 = "无网络";
                break;
            case 0:
                str2 = "单WIFI";
                break;
            case 1:
                str2 = "单流量";
                break;
            case 2:
                str2 = "流量 + WIFI";
                break;
            default:
                str2 = "未知";
                break;
        }
        sb.append(str2);
        return ((sb.toString() + "\noperator：" + C7014a.m8146a(context) + "\n说明：0未知 1移动 2电信 3联通 \n") + "\n-------------------") + "\nrelease version:\n" + BaseConfig.f18083v + "\n\napp Info:\n" + C6993a.m8169a(context, str) + "\n(md5:)HashFinger: " + str + "\n\napiKey:\n" + m8161a + "\n\npublicKey:\n" + str3 + "\n";
    }

    public static String getAppMd5(Context context) {
        return C7007a.m8159b(context, "MD5");
    }

    public static String getAppSM3(Context context) {
        return C7007a.m8159b(context, "SM3");
    }
}
