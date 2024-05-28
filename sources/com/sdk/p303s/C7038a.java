package com.sdk.p303s;

import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.framework.utils.log.MobileLogManager;
import com.sdk.p290f.C6998d;
import com.sdk.p299o.C7026b;
import com.sdk.p300p.C7032f;
import com.sdk.p302r.C7037a;
import com.sdk.p304t.C7039a;
import java.util.TreeMap;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.sdk.s.a */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C7038a {

    /* renamed from: a */
    public static final String f18197a;

    /* renamed from: b */
    public static Boolean f18198b;

    static {
        new TreeMap();
        f18197a = C7038a.class.getSimpleName();
        f18198b = Boolean.valueOf(C6998d.f18135a);
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    /* renamed from: a */
    public static String m8128a(String str) {
        char c;
        String str2 = C7039a.f18202d;
        switch (str2.hashCode()) {
            case 66:
                if (str2.equals("B")) {
                    c = 3;
                    break;
                }
                c = 65535;
                break;
            case 67:
                if (str2.equals("C")) {
                    c = 0;
                    break;
                }
                c = 65535;
                break;
            case 68:
                if (str2.equals("D")) {
                    c = 1;
                    break;
                }
                c = 65535;
                break;
            case 69:
                if (str2.equals("E")) {
                    c = 2;
                    break;
                }
                c = 65535;
                break;
            default:
                c = 65535;
                break;
        }
        if (c != 0 && c != 1 && c != 2) {
            try {
                JSONObject jSONObject = new JSONObject(str);
                String optString = jSONObject.optString("aesKey");
                return C7032f.m8136a().f18193a.mo8131b(C7026b.m8142a(optString, C7039a.f18200b), jSONObject.optString("data"));
            } catch (Throwable th) {
                MobileLogManager.status302001(th.toString());
                LogUtils.m8186e(f18197a, "SDK解密异常：" + th.toString(), f18198b);
            }
        } else {
            try {
                String str3 = C7039a.f18205g;
                if (C7037a.m8130a(str3).booleanValue()) {
                    return null;
                }
                String mo8131b = C7032f.m8136a().f18193a.mo8131b(str3, str.replaceAll("\n", ""));
                if (!C7037a.m8130a(mo8131b).booleanValue()) {
                    return mo8131b;
                }
            } catch (Throwable th2) {
                MobileLogManager.status302001(th2.toString());
                LogUtils.m8186e(f18197a, "SDK解密异常：" + th2.toString(), f18198b);
            }
        }
        return null;
    }
}
