package com.megvii.lv5;

import android.content.Context;
import android.text.TextUtils;
import android.util.Base64;
import com.megvii.lv5.C5402d;
import com.megvii.lv5.lib.jni.MegAuth;
import com.megvii.lv5.lib.jni.MegDelta;
import com.megvii.lv5.sdk.bean.MegliveLocalFileInfo;
import com.megvii.lv5.sdk.listener.GetConfigCallback;
import com.megvii.lv5.sdk.manager.MegLiveDetectConfig;
import com.megvii.lv5.sdk.manager.MegLiveDetectPrivateConfig;
import com.megvii.lv5.sdk.manager.MegLiveDetectPrivateListener;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.util.Timer;
import java.util.UUID;
import org.json.JSONException;
import org.json.JSONObject;

/* compiled from: Proguard */
@NBSInstrumented
/* renamed from: com.megvii.lv5.f1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5435f1 extends AbstractC5378a1 {

    /* renamed from: f */
    public static volatile boolean f12589f;

    /* renamed from: a */
    public Timer f12590a = null;

    /* renamed from: b */
    public Context f12591b;

    /* renamed from: c */
    public MegLiveDetectPrivateListener f12592c;

    /* renamed from: d */
    public GetConfigCallback f12593d;

    /* renamed from: e */
    public MegLiveDetectPrivateConfig f12594e;

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class RunnableC5436a implements Runnable {

        /* renamed from: a */
        public final /* synthetic */ JSONObject f12595a;

        public RunnableC5436a(JSONObject jSONObject) {
            this.f12595a = jSONObject;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                C5538q0.f13187a = C5538q0.m13192a(C5435f1.this.f12591b, C5538q0.m13187a(new JSONObject(new String(MegDelta.decodeParameter(false, "", Base64.decode("IWnDwzXQ9rQnx72IHCna2ZwRFIHLXem4aHEqcl7Tij76ikegCr96bYSotl40jFeC+Gs31yUkif2H\nW3XLdRZ1B3nIdFMo2S3uXte2s/HoWf31erdqGsBaU92ZuI9fXVn9keOmtIkaEdShQNVj88Av+sw4\ntifWxpt1R4DJ4o8puxVmRN2PQHJYxjHPJkw1/MQiJAifFu/Wq87gxUjsfmlqFC8FdJ3LBrj6NpG4\nwc0a6T6TrOY5NQ3cJnU+JctK00VFiZx/dSn0EBEe0T0QVgDdPquK+BoqeKqCx/+LwYpmy9iPSqM3\nOWtKwDbeJB8tJr0W/Dm0AmpuNEs87tnGGzGS9dzOWIarjDjGBVrlGJljmYrwSGfNlCXFbf4NT8ya\naI8EqKpyve506+h/FbShvtBw0Op2n5QK+xGXcC/hck5s6Uk5lDqMqIuzr3amanjVy4X/Omxi2ynD\nqDnpKVz8FfYJhM5JbnRGygKQSTYzDhq5szXJIC6U2QSXplZZ5IO0PO11zlJh7+LDIO+SmfEvGRSV\nhrrBhmPBbyhFQhqo5nzC7/LmMvcYvzxDRUEudo1XBGW9Ig5kQjxyY9io4a4+KBCQZQUpKiOo3DU0\n8n41wHdB960FiDfwAxNt2qKiQhB/T6dMi7VhoZ3CUw3N/lzDNbpSj2U6Nk4uS4uJEMdnvt1UoHyd\n5ucH6x7rof7+ZdMniFxVWDKCM+WaQUGEvUg38eGrZY9gTMtTtC4AgdmUJ6Z6INvk5zt7loLOEdX5\n5SrIoTGg0BfJnZ7n6azkqO3bIGZfGCwS6rAgjkR7hcDBLdLuRd+MAsQ1Nd1Q36Q2Mn8WlEBCMx49\nmJIkEmUWsFLZ+sYpYBu1MI6cUlFhA2yewP/5Q48tlmWoVfGGbRgFYdHcNj7T27smUiAVix8nMAau\nM9rSMWwHOEbBAmKtXtHsRgzK2HpgUJhpjCsm6FIz8NVT1n/ucULkDSKfCmGVVjdY862DXZ46zexs\nugqjl+SmnztUCmxFBbCLBTNATlFqPMQ47YxyJCvs111Av3wwMUVi5/HpnltUT99SR5+G06Q+6nug\nlLy24TNbxmRXdC+MUWs7RSdoNSaVfP7+i8BvVXUCvwkodMO0HHilRF6A0fygNrHXr4/03vMRWGO0\n5h7RN5tELZRKXl9TBZ05MbayBmja2xY2iAmt7rkLTJ9ttXvYGcU0CMNEn1Zg2hqk3lQVU5c3ptfz\nspcGCy2DoxohxalAY8CSSFOGOyTn8aYU/5x13JDdYL/x2cVhhiMV0/ls70Vd9nSlRxeRiPkD6GBy\nX+N6hOQp2sVsRk4QzETPsqF/MEJ3C1qv3XgHGx510xIn0UG/QL3qMuKZcvKtpPb7v9tW4ZyigGrd\nDdUWacxOU1VzC+XANP/jyOP0v+MmeHawSuSeZzBLNji7nrFsB3LjvADUzo2AG5r1ewNXCem2wdKp\n4oB2duceJ2OgAknMEm3eaaljWNRWcRdWUYg9lHBpoFrCkvzwhgpfEaf6ZbsUxE73JvBatYLk9GVB\n1DIMQDbgNjvvIQmMCQb5loNgZIg+8ukKe+fZiUgE8TbZB4LLuTKndrkHxWLw+mudlx69JyxpCyQS\nRswoUWYuyd+hu9WvvL9Cho34fJo/59NH3xNf/JTW2TKn+QJwjUqpg3jhaTKgqrS69pUbWnegsqbI\nKTZGmDR3I5rV1hxyBQxf22MaHIkkOyszkVx81vmyS9EO04hWAtwI22X1Nc7nQPyeeH1ld97FBmwX\noGoW2wnT7udjeriTlV/87bn5A5bCbiqTjmhaypfT5VyXWP8B0uoslmrFdyhCcO+f2vpWfmEQUAZM\n2u4w9NCOx++43ZAVKdjPfN2dKXa7QT9bKwlIa17IYxe11SgBXmILluqYquFIKPO2thesS/gflD9e\nDZ92Z9KMRQQ9M4jtedPkg9lh4VsE3bm0x2ga36ut2I9BsddwtI+Mc6DpE/oGEU3mjltQMUL/JMun\nzBxfxjkdpgx5yQ3a7ZclVwwF0IQsAsIScOqEYFZg4Yn4cZwGnUhVlXD3W4PIrAdp0mLJad0PKvWT\nAu4d71TuIYHYVBTGKE1brlwO9Cpl/AFezSB0QqvlL3ehV66y0UfcQHTzmt4HC4tpni0WRcL7R4Cy\nq/RCetGGz1siMt3PYTd5lOVtLytC+j0gu5ijhFuA/j3ChVkHTGCHSSu5fbbbHSJfGyViIIuVVQzZ\nLXwf+RbcNTUv112hEhIPm/hFC9LR5CHX91w6XHsdDb8HIPxvobSemGbZ7Boa7pmoPELi9N6kqJw3\nJBUkD8jFn9EGiHRsh+3CNL9nogcBp/QwDKQ2mvukKj826YsEJ1VycFLe8l51eErjnG9zaBdVP82A\nG5FYz/oHed+xU64Jt8Bt3sjj1dGlq7yv7j1HR1ys8ZRoApp30m1hBzpa74kPGOSrCt6cg7yJVnMz\nmQt6B6VHnuiosVwmKT0uBY3bP82TPa2kXaNvEAyh2kUaAocIZ7xwOmd1fsSnhzINzVJTY6tq5G5Q\nJff2CFqBMEAOrFTd+txtb7K8Z0FEZCOLoz0jcMcCGn5T/gOLm6C+1OyATLM0qakpPmCz0d7qyx6D\n8k1nBGvm2AeMLLWMNmBgPeHBB1C7ORVfG8cqpG2dR+K3S85iXnAq4E+g80/rQRpBwGPPjQiENp+E\n2u4hrqwkQDjhm+/zyAo5HSooSfTvMEwt0dCGs7A6Bq9mO0IAXMbJS4t0Ry+xOVpZ9Etf43W2YkEo\nZijE5CS8BkztAjVkyl/vkOlsDotvG/o9Bv42XleTc2GTRsFoCv10tx4qx5lRkwEoA3fsPgYjAk97\n4hYQnlpimafACXJHIm1fIcFTnd4KLfs2+Uqmax9amOUG3XfyrbG0wncuPCXxtpakv4JSGpsjLSgX\nGI87lV244Jkh3OBa3nswU2puYG3KgHfs4h64Kyb+TBRWkRi8MHGEJnypmCIhQxf8CENx0A7Ldh5H\nj4K540AY2s3O2cR0Xl4aIlkSMGVGAe4RCUApUmn2Cj3D35dIMbqTPHHTVbTygblovT1+hy547Uph\npU9vdDq6cde4S4WdFa09R7H4Fii4rfIlAXEL1OoX5AapelnLd9Zyadc4GAjE6Co8fr0IxJVIdPPy\nQ6mbRaEbtN920kmADkaEeIjzKTLC6mR1NmwodYA9WX4YVuhPRgipMgDe3HYO5WRTdySDpngqbSwJ\nsuykupvWocBPfkjw9iTI5fX2b9xRcu8o8qwfbai1+qfiTUqzwdbC0vzojCgGaFstP5/JrjSlc5yN\nP8wbZCrGLGCjaxaLIFgIJxvldVj02Dsfooc/mR46G4h4pfn2pJuzJk+qPx3Ty+Rhw5x+Es14Pgic\n3HdIrnFWcMGpQOrBPcr5OxKglVSlLeJQnwrmqOqH3DocPLMIjugEBboxHV573m5w+vNFpIygrZmR\nf2BiZtVkJM8qiN28FoegwXH9o6LbnczZ6pJjW+KxkpvV23LqVOdoEuwG5d4T85iCGKYFKApycwAt\nkEQ1T7Euaxr+7zsy+MZzQazzSJHdN7sICTJhzlBtdP7G21QrYiStrjUs86GJq5wH59oK9Af5CUKY\nJReLLCfUL6+L6jcAEaDq412eaCwbRmMOzJzU7EQwIyahVqFa7i8Ml/pe15SX1NO8pMHnMYOE+M/U\nUAgIgkJFPXXE0shGz0ExhAiLP4Qn5SmzdDHPv6S70CoDsFCwIZGVZw2fiJgYHz3Cd1AzmYnN9Zg6\newFcuZg1E6zcI7eH9f4Vq9vz+utHiio4q9l4CFudPAq7hIlf9itg/Hdo0xN5mi/8mC0EWEd3Wy2e\nEwXCRA11N2DpK6xeritUHvZlnKXaVrtPHMU7vB+wCIOyDU7GKv8sZ0cl6p78Miar+MIG4UIhV4jO\nyMkMDskc0YncBfhyg+Mk61pdzz+HJttbKJcqN2vZtfDC8nlrXkhMjIEy3//v1aMOjBx5CXTAMK1y\n8LJuqx/h1bw+fKxCPDechNOiFl5SFOv+kz/owIszZofCSw56Gx9B+D6tp7Nad4jBm/Jnp+92x0NJ\nV5Ds0h23lSXBBiLnJfSWQNoeWrOoSDlZZy53p+Mdv+MHLtxvLD/aXKCvZmnSm/KxLQruVz/BCqcn\nbGftYTZZAZFgYCrEMsFaE6tSndZ7frQWoDKRs0jMPIqyCbMoNJA611ilXZtvdVj4Ip22ByajDQ0L\npCbcWklFTAKM05Ummn2F3V76xpJY/8IDLhd+hMK88kU5WkxEhjOb2tWynyabMEGia2cSZDsSU5R/\n0m/urlbqYAYssXRBgmDGOqxgAMVytK+dORsSu9UJ0rAYCAxNiIqrN/bzrApVBevj1XLYbWd9s8FZ\nsiZLyklqY40g11M65xvuuwEETmyyHwyoDbyxzpzP0dd+uDVuKBwxNc222WcrSW/ruuGSUSQQF/Fk\ndZkM9VTOSm3JZPzx8YZhLPx4HSYJEgO3j640eIrrE6/4ANRL3B/a5NoCfMkDuvW4uc5iBZOFuo2m\nZvAtn3/wizRvUoxnqg6xIOnaQ3pxhOIyMlxkTK3H3MJmR5X6/MJrtSOXrtdocLmyzbteD+O8xLr4\n8dIS22F5QMC6CsMHKyxW5bjuE4DtBdwIldGcqfYz0lZehr1Fs4hq+zzH3nuPjiPUu7aPwOSKMvk+\nwIBnp/YIduinh3pBcvLLO6jLZnlIMCnUPjKZxZq2q55apBU4GhGnh3BfEG8n6QyvZp6jV5ZzQP3p\nkfsgPfYYOGkzKmwTY6+dXqQCFn71+FedKaMxnniT+dS+1aSUeSk899Nxcysh0uzFR1fe71ZHc8K1\nQFBpakvC5RpDvpOrUGvYGIonCVnwF/ehLCqyYIKhv5YtxUF+lzM9Yvwk2Vc75EgOLNqv+DD//1WN\nm65XfpQve3TSzuYlMdosDvTh87WwENqlEkEEFkLy8lQF008uQLUs9DmEfQPwcG71RvK6qx/FTvhH\nekyzcQUkUWz90niyBC+uM8qibfqBj9gBkVw+RzyhRDVcLJmPzR5ffR/OYHhrAwS7q1+MY/zN0XcQ\nOp8COyqR5gmdKsnIJSbHjmtoFSNGPf9ww/WsbxlngzBWgAGWHtPvvRL1JCC1dIovR4XIZzMIqUb3\nXhtDxkV3jczdfzIQMsPUvEjcGxD/bZQ+AIhC95btxUeyOBLwZtziEZKu06PkRBsdUOierREF+Yko\nxSz8RrZE6MAHGknEqmCJQXYAsCmREPzeIaMR8RkO7ZcBsDGTAg6Dv04udR2FVDYftRkIVjVyth3i\nQXbYb9lMMxZihVBO1BDYckqTjBbAQTIcfGG2ucS1utW6lC7dk+IHNOwV06O++WLjjmtKFKy05HOZ\nTmbKVshb76o+AfWa5D/jfCrHrADWFCrpOhK5eu9mwf010r2ebBcghc3/FLfB001guZ96PyXeD7W4\nI8okXO5el8BXKjRrGFnKVHtUNXiji3w02Jel7PTVFv2d58uCoTz5oO/VdWRr4331d5HhjgHSNvd+\nvwM1Nq9Yr4xB5WzfjisDGRxmXX56Rj2fT3+GxAaFlT09CmQRqjLCEj7mUJt5+KGMpicXLJT1hkm7\nEp4JMap+yQ4UKqhrfTFbO0AoWDBLTJbLlNffGQwXz7I65tcva8XU/6hzqT3dkOOqrlevRh+t9/9r\nRDGPSlhtNeerXcDdpXreKP19FuS36vBa5mNHUacWUqVd0Q7mnYrpJFo27+2lY3/0JIXKcs5NFYvY\nAosPDl9DvPsA883Ijw==\n".getBytes(), 0)))), this.f12595a));
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.f1$b */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static final class C5437b {

        /* renamed from: a */
        public static final C5435f1 f12597a = new C5435f1();
    }

    /* renamed from: a */
    public final void m13540a(int i, byte[] bArr) {
        EnumC5548r2 enumC5548r2;
        if (i != 400) {
            if (i >= 0 || i != -1001) {
                m13541a(1008, "INTERNAL_ERROR");
                return;
            } else {
                m13539a(EnumC5548r2.NETWORK_TIME_OUT);
                return;
            }
        }
        try {
            String optString = new JSONObject(new String(bArr)).optString("error", "");
            if ("BAD_ARGUMENTS: biz_token".equals(optString)) {
                enumC5548r2 = EnumC5548r2.BIZ_TOKEN_DENIED;
            } else if (!"BAD_ARGUMENTS: bundle_id".equals(optString) && !"BAD_ARGUMENTS: auth_msg".equals(optString)) {
                m13541a(1002, String.format("ILLEGAL_PARAMETER:{%s}", "request_data_error"));
                return;
            } else {
                enumC5548r2 = EnumC5548r2.INVALID_BUNDLE_ID;
            }
            m13539a(enumC5548r2);
        } catch (Throwable th) {
            th.printStackTrace();
            m13541a(1005, String.format("{%s}", th.getMessage()));
        }
    }

    /* renamed from: a */
    public final void m13537a(String str, String str2, MegLiveDetectConfig megLiveDetectConfig) {
        Context context;
        int i;
        Context context2;
        Boolean bool;
        Context context3;
        String str3;
        Context context4;
        Boolean bool2;
        Context context5;
        String str4;
        C5402d.f12429a = "liveness-sdk";
        C5402d c5402d = C5402d.C5403a.f12436a;
        C5399c3.m13606a(c5402d.m13599a("success_get_license_and_config", this.f12594e.getBiztoken(), 4));
        String str5 = "authAndConfig onSuccess: enter responseBody = " + str;
        try {
            String optString = new JSONObject(str).optString("result");
            if (optString == null || "".equals(optString)) {
                m13541a(1002, String.format("ILLEGAL_PARAMETER:{%s}", "response_result_is_null"));
                return;
            }
            String str6 = "authAndConfig onSuccess: responseBody org= " + optString;
            String str7 = new String(MegDelta.decodeParameter(true, str2, Base64.decode(optString, 0)));
            String str8 = "authAndConfig onSuccess: responseBody dst= " + str7;
            JSONObject jSONObject = new JSONObject(str7);
            String optString2 = jSONObject.optString("license");
            String str9 = "License::: 11111isUpdateLicense = 1";
            String optString3 = jSONObject.optString("bundle_id", "");
            String optString4 = jSONObject.optString("api_key", "");
            Context context6 = this.f12591b;
            C5527o2.m13235b(context6, "megvii_liveness_bundle_id", optString3);
            C5527o2.m13235b(context6, optString3, Long.valueOf(System.currentTimeMillis()));
            String optString5 = jSONObject.optString("biz_token", "");
            String str10 = "onSuccess: biztoken" + optString5;
            String str11 = "onSuccess: isUpdateLicense1";
            if (jSONObject.has("option_code")) {
                i = jSONObject.optInt("option_code", 0);
                context = this.f12591b;
            } else {
                context = this.f12591b;
                i = 0;
            }
            C5527o2.m13235b(context, "megvii_liveness_option_code", Integer.valueOf(i));
            if (!jSONObject.has("liveness_config")) {
                m13541a(1002, String.format("ILLEGAL_PARAMETER:{%s}", "missing_liveness_config"));
                return;
            }
            JSONObject jSONObject2 = jSONObject.getJSONObject("liveness_config");
            StringBuilder sb = new StringBuilder();
            sb.append("onSuccess: livenessConfig = ");
            sb.append(!(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
            sb.toString();
            JSONObject jSONObject3 = null;
            if (jSONObject2.has("device_risk_config")) {
                jSONObject3 = jSONObject2.optJSONObject("device_risk_config");
                C5527o2.m13230c(this.f12591b, !(jSONObject3 instanceof JSONObject) ? jSONObject3.toString() : NBSJSONObjectInstrumentation.toString(jSONObject3));
            }
            new Thread(new RunnableC5436a(jSONObject3)).start();
            int optInt = jSONObject2.has("liveness_type") ? jSONObject2.optInt("liveness_type") : 0;
            if (jSONObject2.has("vertical_shooting")) {
                megLiveDetectConfig.setVerticalDetection(jSONObject2.optInt("vertical_shooting", 3));
            }
            if (jSONObject2.has("liveness_change_voice")) {
                megLiveDetectConfig.setAutoAdjustVolume(jSONObject2.optInt("liveness_change_voice", 0) == 1);
            }
            if (jSONObject2.has("voice_lowest")) {
                megLiveDetectConfig.setSuggestVolume(jSONObject2.optInt("voice_lowest", 50));
            }
            if (jSONObject2.has("show_logo")) {
                megLiveDetectConfig.setShowLogo(jSONObject2.optInt("show_logo", 0) == 1);
            }
            if (jSONObject2.has("new_whitebalance_open")) {
                int optInt2 = jSONObject2.optInt("new_whitebalance_open", 0);
                context2 = this.f12591b;
                bool = Boolean.valueOf(optInt2 == 1);
            } else {
                context2 = this.f12591b;
                bool = Boolean.FALSE;
            }
            C5527o2.m13235b(context2, "white_balance_status", bool);
            if (jSONObject2.has("white_balance")) {
                context3 = this.f12591b;
                JSONObject optJSONObject = jSONObject2.optJSONObject("white_balance");
                str3 = !(optJSONObject instanceof JSONObject) ? optJSONObject.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject);
            } else {
                context3 = this.f12591b;
                str3 = "";
            }
            C5527o2.m13235b(context3, "white_balance_info", str3);
            if (jSONObject2.has("new_exposure_open")) {
                int optInt3 = jSONObject2.optInt("new_exposure_open", 0);
                context4 = this.f12591b;
                bool2 = Boolean.valueOf(optInt3 == 1);
            } else {
                context4 = this.f12591b;
                bool2 = Boolean.FALSE;
            }
            C5527o2.m13235b(context4, "exposure_status", bool2);
            if (jSONObject2.has("exposure")) {
                context5 = this.f12591b;
                JSONObject optJSONObject2 = jSONObject2.optJSONObject("exposure");
                str4 = !(optJSONObject2 instanceof JSONObject) ? optJSONObject2.toString() : NBSJSONObjectInstrumentation.toString(optJSONObject2);
            } else {
                context5 = this.f12591b;
                str4 = "";
            }
            C5527o2.m13235b(context5, "exposure_info", str4);
            String optString6 = jSONObject2.optString("encrypt_liveness_video_key", "");
            String str12 = "onSuccess: encrypt_liveness_video_key=" + optString6;
            C5527o2.m13235b(this.f12591b, "megvii_liveness_config", !(jSONObject2 instanceof JSONObject) ? jSONObject2.toString() : NBSJSONObjectInstrumentation.toString(jSONObject2));
            String str13 = "onSuccess: license=" + optString2;
            int nativeSetLicence = MegAuth.nativeSetLicence(optString2);
            if (nativeSetLicence == 0) {
                long nativeGetExpireTime = MegAuth.nativeGetExpireTime("MegLiveStill 3.0.0A");
                String str14 = "License::: 11111expireTime = " + nativeGetExpireTime;
                C5527o2.m13235b(this.f12591b, "megvii_liveness_expiretime", Long.valueOf(nativeGetExpireTime * 1000));
                if (C5538q0.m13180b(this.f12591b) <= 0) {
                    m13541a(1003, String.format("AUTHENTICATION_FAIL{%s}", "expire"));
                    return;
                }
                C5527o2.m13235b(this.f12591b, "megvii_liveness_bizToken", optString5);
                m13539a(EnumC5548r2.LIVENESS_FINISH);
                C5402d.f12429a = "liveness-sdk";
                C5399c3.m13606a(c5402d.m13599a("start_detect", this.f12594e.getBiztoken(), optInt));
                m13620a(this.f12591b, optString4, optString6, optInt, megLiveDetectConfig, this.f12594e);
                return;
            }
            Object[] objArr = new Object[1];
            String str15 = "";
            if (nativeSetLicence == 1) {
                str15 = "illegal_param";
            } else if (nativeSetLicence == 2) {
                str15 = "illegal_handle";
            } else if (nativeSetLicence == 3) {
                str15 = "illegal_index";
            } else if (nativeSetLicence == 101) {
                str15 = "expire";
            } else if (nativeSetLicence == 102) {
                str15 = "bundleid_error";
            } else if (nativeSetLicence == 103) {
                str15 = "license_error";
            } else if (nativeSetLicence == 104) {
                str15 = "model_error";
            } else if (nativeSetLicence == -1) {
                str15 = "algo_error";
            } else if (nativeSetLicence == 201) {
                str15 = "opengl_context_error";
            }
            objArr[0] = str15;
            m13541a(1003, String.format("AUTHENTICATION_FAIL{%s}", objArr));
        } catch (Throwable th) {
            th.printStackTrace();
            m13541a(1005, String.format("{%s}", th.getMessage()));
        }
    }

    @Override // com.megvii.lv5.AbstractC5378a1
    /* renamed from: a */
    public void mo13538a(EnumC5548r2 enumC5548r2, String str, MegliveLocalFileInfo megliveLocalFileInfo, byte[] bArr) {
        int i = enumC5548r2.f13245a;
        String str2 = enumC5548r2.f13246b;
        C5399c3.f12426c = null;
        f12589f = false;
        if (this.f12592c != null) {
            if (megliveLocalFileInfo != null) {
                String filePath = megliveLocalFileInfo.getFilePath();
                if (!TextUtils.isEmpty(filePath)) {
                    this.f12592c.onLivenessFileCallback(filePath);
                }
                if (!TextUtils.isEmpty(megliveLocalFileInfo.getScrrenFilePath())) {
                    this.f12592c.onLivenessLocalFileCallBack(megliveLocalFileInfo);
                }
            }
            this.f12592c.onDetectFinish(i, str2, str, bArr);
            this.f12592c = null;
        }
    }

    /* renamed from: a */
    public void m13541a(int i, String str) {
        JSONObject jSONObject;
        JSONObject jSONObject2;
        if (i == 1006) {
            this.f12592c.onPreDetectFinish(i, str);
            return;
        }
        if (i != 1000) {
            C5402d.f12429a = "liveness-sdk";
            String str2 = "fail_init:" + str;
            String biztoken = this.f12594e.getBiztoken();
            if (!C5402d.f12432d || str2.contains("fail_detect")) {
                try {
                    jSONObject2 = new JSONObject();
                    jSONObject2.put("type", "track");
                    jSONObject2.put("project", C5402d.f12429a);
                    jSONObject2.put("event_id", UUID.randomUUID().toString());
                    jSONObject2.put("time", System.currentTimeMillis());
                    jSONObject2.put("event", str2);
                    JSONObject jSONObject3 = new JSONObject();
                    jSONObject3.put("liveness", 4);
                    jSONObject3.put("biz_token", biztoken);
                    jSONObject3.put("try_times", 0);
                    int i2 = C5402d.f12431c + 1;
                    C5402d.f12431c = i2;
                    jSONObject3.put("index", i2);
                    jSONObject2.put("properties", jSONObject3);
                    C5402d.f12430b = str2;
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                C5399c3.m13606a(jSONObject2);
                f12589f = false;
            }
            jSONObject2 = null;
            C5399c3.m13606a(jSONObject2);
            f12589f = false;
        } else {
            C5402d.f12429a = "liveness-sdk";
            String biztoken2 = this.f12594e.getBiztoken();
            if (!C5402d.f12432d) {
                try {
                    jSONObject = new JSONObject();
                    jSONObject.put("type", "track");
                    jSONObject.put("project", C5402d.f12429a);
                    jSONObject.put("event_id", UUID.randomUUID().toString());
                    jSONObject.put("time", System.currentTimeMillis());
                    jSONObject.put("event", "pass_init");
                    JSONObject jSONObject4 = new JSONObject();
                    jSONObject4.put("liveness", 4);
                    jSONObject4.put("biz_token", biztoken2);
                    jSONObject4.put("try_times", 0);
                    int i3 = C5402d.f12431c + 1;
                    C5402d.f12431c = i3;
                    jSONObject4.put("index", i3);
                    jSONObject.put("properties", jSONObject4);
                    C5402d.f12430b = "pass_init";
                } catch (JSONException e2) {
                    e2.printStackTrace();
                }
                C5399c3.m13606a(jSONObject);
            }
            jSONObject = null;
            C5399c3.m13606a(jSONObject);
        }
        MegLiveDetectPrivateListener megLiveDetectPrivateListener = this.f12592c;
        if (megLiveDetectPrivateListener != null) {
            megLiveDetectPrivateListener.onPreDetectFinish(i, str);
            if (i != 1000) {
                this.f12592c = null;
            }
        }
    }

    /* renamed from: a */
    public void m13539a(EnumC5548r2 enumC5548r2) {
        m13541a(enumC5548r2.f13245a, enumC5548r2.f13246b);
    }
}
