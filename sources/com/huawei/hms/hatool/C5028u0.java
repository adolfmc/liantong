package com.huawei.hms.hatool;

import android.os.Build;
import android.text.TextUtils;
import java.util.HashMap;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.huawei.hms.hatool.u0 */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class C5028u0 {
    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static C4982f0 m14466a(String str, String str2, String str3, String str4) {
        C4982f0 c4982f0 = new C4982f0();
        c4982f0.m14643a(str);
        c4982f0.m14642b(AbstractC5020q0.m14532f());
        c4982f0.m14639e(str2);
        c4982f0.m14641c(str4);
        StringBuffer stringBuffer = new StringBuffer("hmshi");
        stringBuffer.append(str3);
        stringBuffer.append("qrt");
        c4982f0.m14640d(stringBuffer.toString());
        return c4982f0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static C5001l m14468a(String str, String str2) {
        C5001l c5001l = new C5001l();
        c5001l.m14476a(C4993j.m14673a().m14671a(str, str2));
        return c5001l;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: a */
    public static C5037y0 m14467a(String str, String str2, String str3) {
        C5037y0 c5037y0 = new C5037y0();
        c5037y0.m14473c(AbstractC5020q0.m14525j());
        c5037y0.m14471e(AbstractC5020q0.m14523l());
        c5037y0.m14475a(str3);
        c5037y0.m14474b(C4993j.m14673a().m14669b(str2, str));
        return c5037y0;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: b */
    public static C4989h1 m14465b(String str, String str2) {
        C5029v.m14455c("hmsSdk", "generate UploadData EventModelHandlerBase");
        C5006m1.m14597d().m14600a(str, str2);
        if (TextUtils.isEmpty(C5006m1.m14597d().m14602a())) {
            C5029v.m14451f("hmsSdk", "event chifer is empty");
            return null;
        }
        return new C4989h1(C5006m1.m14597d().m14598c());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: c */
    public static Map<String, String> m14464c(String str, String str2) {
        HashMap hashMap = new HashMap();
        hashMap.put("App-Id", AbstractC5020q0.m14532f());
        hashMap.put("App-Ver", AbstractC5020q0.m14530g());
        hashMap.put("Sdk-Name", "hianalytics");
        hashMap.put("Sdk-Ver", "2.2.0.313");
        hashMap.put("Device-Type", Build.MODEL);
        hashMap.put("servicetag", str);
        C5029v.m14460a("hmsSdk", "sendData RequestId : %s", str2);
        hashMap.put("Request-Id", str2);
        return hashMap;
    }
}
