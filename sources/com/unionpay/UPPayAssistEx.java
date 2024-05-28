package com.unionpay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Base64;
import com.huawei.nfc.sdk.service.HwOpenPayTask;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.unionpay.p362a.C10739d;
import com.unionpay.p363b.C10744b;
import com.unionpay.p363b.C10749g;
import com.unionpay.utils.C10915b;
import com.unionpay.utils.C10922i;
import com.unionpay.utils.C10923j;
import com.unionpay.utils.UPUtils;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.Executors;
import org.json.JSONArray;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* loaded from: E:\11617560_dexfile_execute.dex */
public class UPPayAssistEx {

    /* renamed from: A */
    private static String f20571A = null;

    /* renamed from: B */
    private static String f20572B = null;

    /* renamed from: C */
    private static String f20573C = null;

    /* renamed from: D */
    private static String f20574D = null;

    /* renamed from: E */
    private static String f20575E = "";

    /* renamed from: F */
    private static String f20576F = "";

    /* renamed from: G */
    private static String f20577G = "";

    /* renamed from: H */
    private static String f20578H = "";

    /* renamed from: I */
    private static boolean f20579I = false;

    /* renamed from: J */
    private static int f20580J = 10;

    /* renamed from: K */
    private static WeakReference f20581K = null;

    /* renamed from: L */
    private static String f20582L = "";

    /* renamed from: M */
    private static String f20583M = null;

    /* renamed from: N */
    private static String f20584N = null;

    /* renamed from: O */
    private static String f20585O = "";

    /* renamed from: P */
    private static String f20586P = "";

    /* renamed from: Q */
    private static boolean f20587Q = false;

    /* renamed from: R */
    private static String f20588R = "";

    /* renamed from: S */
    private static int f20589S = 0;
    public static final String SDK_TYPE = "02";

    /* renamed from: T */
    private static boolean f20590T = false;

    /* renamed from: U */
    private static boolean f20591U = false;

    /* renamed from: V */
    private static C10739d f20592V = null;
    public static final String VERSION = "3.5.9";

    /* renamed from: W */
    private static Handler f20593W = null;

    /* renamed from: X */
    private static String f20594X = "[{\"package_info\":[{\"schema\":\"com.unionpay\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":101,\"version\":\".*\"},{\"schema\":\"com.unionpay.tsmservice\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":102,\"version\":\"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[7-9].*|^01\\\\.00\\\\.6[5-9].*\"},{\"schema\":\"com.unionpay.tsmservice.mi\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":103,\"version\":\"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[3-9].*|^01\\\\.00\\\\.2[8-9].*\"}],\"sort\":100,\"type\":\"app\"},{\"sort\":200,\"type\":\"wcd\",\"url\":\"https://appcashier.95516.com/app/api/redirectwapcashierdesk\"}]";

    /* renamed from: Y */
    private static String f20595Y = "[{\"package_info\":[{\"schema\":\"com.unionpay\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":101,\"version\":\".*\"},{\"schema\":\"com.unionpay.tsmservice\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":102,\"version\":\"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[7-9].*|^01\\\\.00\\\\.6[5-9].*\"},{\"schema\":\"com.unionpay.tsmservice.mi\",\"sign\":\"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\":103,\"version\":\"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[3-9].*|^01\\\\.00\\\\.2[8-9].*\"}],\"sort\":100,\"type\":\"app\"}]";

    /* renamed from: Z */
    private static String f20596Z = "[{\"package_info\": [{\"schema\": \"com.unionpay.tsmservice\",\"sign\": \"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\": 102,\"version\": \"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[2-9].*|^01\\\\.00\\\\.1[012789].*|^01\\\\.00\\\\.0[8-9].*\"},{\"schema\": \"com.unionpay.tsmservice.mi\",\"sign\": \"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\": 103,\"version\": \"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[1-9].*|^01\\\\.00\\\\.0[8-9].*\"}],\"sort\": 100,\"type\": \"app\"}]";

    /* renamed from: a */
    private static String f20597a = "SpId";

    /* renamed from: aa */
    private static String f20598aa = "[{\"package_info\": [{\"schema\": \"com.huawei.wallet\",\"sign\": \"9095F915D6C143A41CE029209AFECB87AB481DDD\",\"sort\": 101,\"version\": \"([0-9]\\\\d*)\\\\.([0-9]\\\\d*)\\\\.([0-9]\\\\d*)\\\\.([0-9]\\\\d*)\"},{\"schema\": \"com.huawei.wallet\",\"sign\": \"059e2480adf8c1c5b3d9ec007645ccfc442a23c5\",\"sort\": 102,\"version\": \"([0-9]\\\\d*)\\\\.([0-9]\\\\d*)\\\\.([0-9]\\\\d*)\\\\.([0-9]\\\\d*)\"},{\"schema\": \"com.unionpay.tsmservice\",\"sign\": \"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\": 103,\"version\": \"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[2-9].*|^01\\\\.00\\\\.1[012789].*|^01\\\\.00\\\\.0[8-9].*\"},{\"schema\": \"com.unionpay.tsmservice.mi\",\"sign\": \"536C79B93ACFBEA950AE365D8CE1AEF91FEA9535\",\"sort\": 104,\"version\": \"^[1-9].*|^0[2-9].*|^01\\\\.[1-9].*|^01\\\\.0[1-9].*|^01\\\\.00\\\\.[1-9].*|^01\\\\.00\\\\.0[8-9].*\"}],\"sort\": 100,\"type\": \"app\"}]";

    /* renamed from: ab */
    private static JSONArray f20599ab = null;

    /* renamed from: ac */
    private static final Handler.Callback f20600ac = new C10735a();

    /* renamed from: b */
    private static String f20601b = "paydata";

    /* renamed from: c */
    private static String f20602c = "pay_tn";

    /* renamed from: d */
    private static String f20603d = "SysProvide";

    /* renamed from: e */
    private static String f20604e = "UseTestMode";

    /* renamed from: f */
    private static String f20605f = "SecurityChipType";

    /* renamed from: g */
    private static String f20606g = "uppayuri";

    /* renamed from: h */
    private static String f20607h = "resultIntentAction";

    /* renamed from: i */
    private static String f20608i = "reqOriginalId";

    /* renamed from: j */
    private static String f20609j = "wapurl";

    /* renamed from: k */
    private static String f20610k = "actionType";

    /* renamed from: l */
    private static String f20611l = "dlgstyle";

    /* renamed from: m */
    private static String f20612m = "com.unionpay.uppay";

    /* renamed from: n */
    private static String f20613n = "com.unionpay.uppay.PayActivity";

    /* renamed from: o */
    private static String f20614o = "com.huawei.wallet";

    /* renamed from: p */
    private static String f20615p = "com.huawei.wallet.action.onlinepay.startpay";

    /* renamed from: q */
    private static String f20616q = "ex_mode";

    /* renamed from: r */
    private static String f20617r = "server";

    /* renamed from: s */
    private static String f20618s = "source";

    /* renamed from: t */
    private static String f20619t = "samsung_out";

    /* renamed from: u */
    private static String f20620u = "se_type";

    /* renamed from: v */
    private static String f20621v = "se_title_logo";

    /* renamed from: w */
    private static String f20622w = "se_loading_logo";

    /* renamed from: x */
    private static String f20623x = "se_title_bg_color";

    /* renamed from: y */
    private static String f20624y = "se_cancel_bg_color";

    /* renamed from: z */
    private static String f20625z = "02";

    /* renamed from: a */
    private static int m6011a(Context context, String str, String str2, String str3, String str4, String str5, String str6) {
        f20581K = new WeakReference(context);
        f20582L = str3;
        f20583M = str;
        f20584N = str2;
        f20585O = str4;
        f20577G = str6;
        f20576F = str5;
        f20578H = TextUtils.isEmpty(str6) ? "0" : "1";
        f20571A = null;
        f20572B = null;
        f20573C = null;
        f20575E = str6;
        m5982p();
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static String m6013a(Context context) {
        return m6008a(context, true, "0");
    }

    /* JADX WARN: Removed duplicated region for block: B:29:0x00dd  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00e0  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0114 A[Catch: Exception -> 0x0156, TryCatch #0 {Exception -> 0x0156, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0033, B:35:0x0108, B:37:0x0114, B:38:0x0125, B:40:0x012b, B:41:0x0130, B:43:0x0136, B:44:0x013b, B:46:0x0141, B:47:0x0146, B:49:0x014c, B:34:0x0105, B:7:0x003c, B:11:0x0055, B:13:0x0066, B:15:0x006c, B:17:0x0079, B:19:0x009e, B:21:0x00a7, B:23:0x00b4, B:25:0x00c0, B:27:0x00cb, B:31:0x00e2), top: B:58:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:40:0x012b A[Catch: Exception -> 0x0156, TryCatch #0 {Exception -> 0x0156, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0033, B:35:0x0108, B:37:0x0114, B:38:0x0125, B:40:0x012b, B:41:0x0130, B:43:0x0136, B:44:0x013b, B:46:0x0141, B:47:0x0146, B:49:0x014c, B:34:0x0105, B:7:0x003c, B:11:0x0055, B:13:0x0066, B:15:0x006c, B:17:0x0079, B:19:0x009e, B:21:0x00a7, B:23:0x00b4, B:25:0x00c0, B:27:0x00cb, B:31:0x00e2), top: B:58:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:43:0x0136 A[Catch: Exception -> 0x0156, TryCatch #0 {Exception -> 0x0156, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0033, B:35:0x0108, B:37:0x0114, B:38:0x0125, B:40:0x012b, B:41:0x0130, B:43:0x0136, B:44:0x013b, B:46:0x0141, B:47:0x0146, B:49:0x014c, B:34:0x0105, B:7:0x003c, B:11:0x0055, B:13:0x0066, B:15:0x006c, B:17:0x0079, B:19:0x009e, B:21:0x00a7, B:23:0x00b4, B:25:0x00c0, B:27:0x00cb, B:31:0x00e2), top: B:58:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:46:0x0141 A[Catch: Exception -> 0x0156, TryCatch #0 {Exception -> 0x0156, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0033, B:35:0x0108, B:37:0x0114, B:38:0x0125, B:40:0x012b, B:41:0x0130, B:43:0x0136, B:44:0x013b, B:46:0x0141, B:47:0x0146, B:49:0x014c, B:34:0x0105, B:7:0x003c, B:11:0x0055, B:13:0x0066, B:15:0x006c, B:17:0x0079, B:19:0x009e, B:21:0x00a7, B:23:0x00b4, B:25:0x00c0, B:27:0x00cb, B:31:0x00e2), top: B:58:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x014c A[Catch: Exception -> 0x0156, TRY_LEAVE, TryCatch #0 {Exception -> 0x0156, blocks: (B:3:0x0005, B:5:0x0021, B:6:0x0033, B:35:0x0108, B:37:0x0114, B:38:0x0125, B:40:0x012b, B:41:0x0130, B:43:0x0136, B:44:0x013b, B:46:0x0141, B:47:0x0146, B:49:0x014c, B:34:0x0105, B:7:0x003c, B:11:0x0055, B:13:0x0066, B:15:0x006c, B:17:0x0079, B:19:0x009e, B:21:0x00a7, B:23:0x00b4, B:25:0x00c0, B:27:0x00cb, B:31:0x00e2), top: B:58:0x0005, inners: #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:55:0x015e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x0163  */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private static java.lang.String m6010a(android.content.Context r5, java.lang.String r6, java.lang.String r7, java.lang.String r8, java.lang.String r9, java.lang.String r10, java.lang.String r11, java.lang.String r12, java.lang.String r13) {
        /*
            Method dump skipped, instructions count: 362
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.unionpay.UPPayAssistEx.m6010a(android.content.Context, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String):java.lang.String");
    }

    /* renamed from: a */
    private static String m6008a(Context context, boolean z, String str) {
        return m6010a(context, f20585O, z ? null : f20582L, z ? "0" : null, str, f20578H, f20575E, null, null);
    }

    /* renamed from: a */
    private static String m6003a(String str, String str2, String str3) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2) || TextUtils.isEmpty(str3)) {
            return null;
        }
        try {
            JSONObject jSONObject = new JSONObject(str);
            String m5834a = C10922i.m5834a(jSONObject, "sign");
            String m5834a2 = C10922i.m5834a(jSONObject, "configs");
            if (TextUtils.isEmpty(m5834a) || TextUtils.isEmpty(m5834a2)) {
                return null;
            }
            String str4 = new String(Base64.decode(m5834a2, 2));
            String m5851b = C10915b.m5851b(UPUtils.m5868a(str4 + str3));
            String forConfig = UPUtils.forConfig(C10915b.m5856a(str2), m5834a);
            if (TextUtils.isEmpty(forConfig)) {
                return null;
            }
            if (forConfig.equals(m5851b)) {
                return str4;
            }
            return null;
        } catch (Exception unused) {
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static void m6014a() {
    }

    /* renamed from: a */
    private static void m6012a(Context context, String str, String str2) {
        String forScanUrl = UPUtils.forScanUrl(C10915b.m5856a(str));
        C10923j.m5830a("uppay", "url: " + forScanUrl);
        String m5862a = C10915b.m5862a();
        C10739d c10739d = new C10739d(forScanUrl);
        c10739d.m5961a(m6010a(context, str, null, null, null, null, null, m5862a, str2));
        Executors.newSingleThreadExecutor().execute(new RunnableC10754d(c10739d, context, str, m5862a));
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public static /* synthetic */ void m6009a(Context context, JSONArray jSONArray, int i) {
        String str;
        String str2;
        while (jSONArray != null && i < jSONArray.length()) {
            Object m5835a = C10922i.m5835a(jSONArray, i);
            if (m5835a == null) {
                return;
            }
            JSONObject jSONObject = (JSONObject) m5835a;
            String m5834a = C10922i.m5834a(jSONObject, "type");
            if ("app".equals(m5834a)) {
                JSONArray m5833b = C10922i.m5833b(jSONObject, "package_info");
                String m5834a2 = C10922i.m5834a(jSONObject, "app_server");
                JSONArray m5998b = m5998b(m5833b, "sort");
                boolean z = false;
                if (m5998b.length() > 0) {
                    int length = m5998b.length();
                    int i2 = 0;
                    while (true) {
                        if (i2 >= length) {
                            break;
                        }
                        Object m5835a2 = C10922i.m5835a(m5998b, i2);
                        if (m5835a2 != null) {
                            JSONObject jSONObject2 = (JSONObject) m5835a2;
                            String m5834a3 = C10922i.m5834a(jSONObject2, "schema");
                            String m5834a4 = C10922i.m5834a(jSONObject2, "sign");
                            String m5834a5 = C10922i.m5834a(jSONObject2, "version");
                            if (C10915b.m5860a(context, m5834a3) && m5834a4.equalsIgnoreCase(C10915b.m5853b(context, m5834a3)) && C10915b.m5850c(context, m5834a3).matches(m5834a5)) {
                                try {
                                    Bundle bundle = new Bundle();
                                    m6005a(f20582L, bundle, f20585O);
                                    bundle.putString(f20597a, f20583M);
                                    bundle.putString(f20603d, f20584N);
                                    bundle.putString(f20601b, f20582L);
                                    bundle.putString(f20618s, f20576F);
                                    bundle.putString(f20620u, f20577G);
                                    if (!TextUtils.isEmpty(f20577G)) {
                                        bundle.putString(f20621v, f20571A);
                                        bundle.putString(f20622w, f20572B);
                                        bundle.putString(f20623x, f20573C);
                                        bundle.putString(f20624y, f20574D);
                                    }
                                    bundle.putBoolean(f20611l, f20579I);
                                    bundle.putString(f20617r, m5834a2);
                                    bundle.putString(f20605f, null);
                                    bundle.putInt(f20608i, 0);
                                    Intent intent = new Intent();
                                    intent.putExtras(bundle);
                                    if (f20614o.equals(m5834a3)) {
                                        intent.setAction(f20615p);
                                        intent.setPackage(m5834a3);
                                    } else {
                                        intent.setClassName(m5834a3, f20613n);
                                    }
                                    try {
                                        Context m5981q = m5981q();
                                        if (m5981q != null) {
                                            if (m5981q instanceof Activity) {
                                                ((Activity) m5981q).startActivityForResult(intent, f20580J);
                                            } else {
                                                intent.addFlags(268435456);
                                                m5981q.startActivity(intent);
                                            }
                                        }
                                    } catch (Exception unused) {
                                    }
                                    z = true;
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                        i2++;
                    }
                }
                if (z) {
                    return;
                }
            } else if (!"wap".equals(m5834a)) {
                if ("link".equals(m5834a)) {
                    str = "";
                    try {
                        str = jSONObject.getString("url");
                    } catch (Exception unused2) {
                    }
                    str2 = "link";
                } else if ("wcd".equals(m5834a)) {
                    str = "";
                    try {
                        str = jSONObject.getString("url");
                    } catch (Exception unused3) {
                    }
                    str2 = "wcd";
                } else {
                    context = m5981q();
                }
                m6004a(str, str2);
                return;
            } else if (!f20619t.equals(f20576F)) {
                str = "";
                try {
                    str = (String) jSONObject.get("url");
                } catch (Exception unused4) {
                }
                str2 = "wap";
                m6004a(str, str2);
                return;
            }
            jSONArray = f20599ab;
            i = f20589S + 1;
            f20589S = i;
        }
    }

    /* renamed from: a */
    private static void m6005a(String str, Bundle bundle, String str2) {
        if (str == null || str.trim().length() <= 0) {
            return;
        }
        if (str.trim().charAt(0) != '<') {
            bundle.putString(f20616q, str2);
        } else if (str2 == null || !str2.trim().equalsIgnoreCase("00")) {
            bundle.putBoolean(f20604e, true);
        } else {
            bundle.putBoolean(f20604e, false);
        }
    }

    /* renamed from: a */
    private static void m6004a(String str, String str2) {
        Bundle bundle = new Bundle();
        if (!"link".equals(str2)) {
            m6005a(f20582L, bundle, f20585O);
            bundle.putString(f20597a, f20583M);
            bundle.putString(f20603d, f20584N);
            int i = 0;
            try {
                i = Integer.parseInt(f20585O);
            } catch (Exception unused) {
            }
            bundle.putString(f20601b, UPUtils.forWap(i, C10915b.m5851b(f20582L)));
            bundle.putString(f20602c, f20582L);
        }
        bundle.putString("magic_data", "949A1CC");
        bundle.putString(f20609j, str);
        bundle.putString(f20610k, str2);
        try {
            Context m5981q = m5981q();
            if (m5981q != null) {
                Intent intent = new Intent();
                intent.putExtras(bundle);
                intent.setClass(m5981q, UPPayWapActivity.class);
                if (m5981q instanceof Activity) {
                    ((Activity) m5981q).startActivityForResult(intent, f20580J);
                    return;
                }
                intent.addFlags(268435456);
                m5981q.startActivity(intent);
            }
        } catch (Exception unused2) {
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public static JSONArray m5998b(JSONArray jSONArray, String str) {
        ArrayList arrayList = new ArrayList();
        for (int i = 0; jSONArray != null && i < jSONArray.length(); i++) {
            arrayList.add(jSONArray.optJSONObject(i));
        }
        Collections.sort(arrayList, new C10755e(str));
        JSONArray jSONArray2 = new JSONArray();
        for (int i2 = 0; i2 < arrayList.size(); i2++) {
            jSONArray2.put((JSONObject) arrayList.get(i2));
        }
        return jSONArray2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: c */
    public static void m5996c(String str) {
        int i;
        try {
            i = Integer.parseInt(f20585O);
        } catch (Exception unused) {
            i = 0;
        }
        String forUrl = UPUtils.forUrl(i);
        C10923j.m5830a("uppay", "url: " + forUrl);
        f20592V = new C10739d(forUrl);
        f20592V.m5961a(m6008a(m5981q(), false, str));
        if (f20593W == null) {
            f20593W = new Handler(f20600ac);
        }
        Executors.newSingleThreadExecutor().execute(new RunnableC10742b());
    }

    public static boolean checkWalletInstalled(Context context) {
        return checkWalletInstalled(context, "00", null);
    }

    public static boolean checkWalletInstalled(Context context, String str, String str2) {
        if (context == null) {
            return false;
        }
        if (TextUtils.isEmpty(str)) {
            str = "00";
        }
        System.loadLibrary("entryexpro");
        String m6003a = m6003a(UPUtils.m5870a(context, "scan_configs"), UPUtils.m5870a(context, "scan_mode"), UPUtils.m5870a(context, "scan_random"));
        if (TextUtils.isEmpty(m6003a)) {
            m6003a = f20595Y;
        }
        if (!TextUtils.isEmpty(m6003a)) {
            try {
                JSONArray jSONArray = new JSONArray(m6003a);
                for (int i = 0; i < jSONArray.length(); i++) {
                    Object m5835a = C10922i.m5835a(jSONArray, i);
                    if (m5835a instanceof JSONObject) {
                        JSONObject jSONObject = (JSONObject) m5835a;
                        if ("app".equals(C10922i.m5834a(jSONObject, "type"))) {
                            JSONArray m5998b = m5998b(C10922i.m5833b(jSONObject, "package_info"), "sort");
                            for (int i2 = 0; i2 < m5998b.length(); i2++) {
                                Object m5835a2 = C10922i.m5835a(m5998b, i2);
                                if (m5835a2 instanceof JSONObject) {
                                    JSONObject jSONObject2 = (JSONObject) m5835a2;
                                    if ((context == null || jSONObject2 == null) ? false : C10915b.m5858a(context, C10922i.m5834a(jSONObject2, "schema"), C10922i.m5834a(jSONObject2, "sign"), C10922i.m5834a(jSONObject2, "version"))) {
                                        m6012a(context, str, str2);
                                        return true;
                                    }
                                }
                            }
                            continue;
                        } else {
                            continue;
                        }
                    }
                }
            } catch (Exception unused) {
            }
        }
        m6012a(context, str, str2);
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: d */
    public static void m5994d(String str) {
        try {
            JSONObject jSONObject = new JSONObject(str);
            f20571A = jSONObject.getString("titleLogo");
            f20572B = jSONObject.getString("loadingLogo");
            f20573C = jSONObject.getString("backGroundColor");
            f20574D = jSONObject.getString("textColor");
        } catch (Exception unused) {
        }
    }

    public static int getSEPayInfo(Context context, UPQuerySEPayInfoCallback uPQuerySEPayInfoCallback) {
        return C10915b.m5848d(context, "com.unionpay.tsmservice.mi") ? new C10749g(context, uPQuerySEPayInfoCallback).m5936a() : new C10744b(context, uPQuerySEPayInfoCallback).m5957a();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: j */
    public static /* synthetic */ boolean m5988j() {
        f20590T = true;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: m */
    public static /* synthetic */ boolean m5985m() {
        f20591U = true;
        return true;
    }

    /* renamed from: p */
    private static int m5982p() {
        JSONArray jSONArray;
        String str;
        int i;
        WeakReference weakReference = f20581K;
        if (weakReference == null || weakReference.get() == null) {
            return 1;
        }
        if (TextUtils.isEmpty(f20576F) && TextUtils.isEmpty(f20577G)) {
            f20579I = false;
        } else {
            f20579I = true;
            if (f20625z.equalsIgnoreCase(f20577G)) {
                f20576F = f20619t;
            }
        }
        f20589S = 0;
        f20590T = false;
        f20591U = false;
        System.loadLibrary("entryexpro");
        String m5870a = UPUtils.m5870a(m5981q(), "configs" + f20577G);
        String m5870a2 = UPUtils.m5870a(m5981q(), "mode" + f20577G);
        String m5870a3 = UPUtils.m5870a(m5981q(), "or" + f20577G);
        if (!TextUtils.isEmpty(m5870a) && !TextUtils.isEmpty(m5870a2) && !TextUtils.isEmpty(m5870a3)) {
            try {
                JSONObject jSONObject = new JSONObject(m5870a);
                String m5834a = C10922i.m5834a(jSONObject, "sign");
                try {
                    i = Integer.parseInt(m5870a2);
                } catch (Exception unused) {
                    i = 0;
                }
                String str2 = new String(Base64.decode(jSONObject.getString("configs"), 2));
                String str3 = jSONObject.has("sePayConf") ? new String(Base64.decode(jSONObject.getString("sePayConf"), 2)) : "";
                if (TextUtils.isEmpty(str3)) {
                    str3 = "";
                }
                String m5851b = C10915b.m5851b(UPUtils.m5868a(str2 + str3 + m5870a3));
                String forConfig = UPUtils.forConfig(i, m5834a);
                if (!TextUtils.isEmpty(forConfig) && forConfig.equals(m5851b)) {
                    if (TextUtils.isEmpty(f20577G)) {
                        f20594X = str2;
                    } else if ("04".equals(f20577G)) {
                        f20598aa = str2;
                    } else {
                        f20596Z = str2;
                    }
                    if (!TextUtils.isEmpty(f20575E)) {
                        String m5870a4 = UPUtils.m5870a(m5981q(), "se_configs" + f20575E);
                        if (!TextUtils.isEmpty(m5870a4)) {
                            m5994d(m5870a4);
                        }
                    }
                }
            } catch (Exception unused2) {
            }
        }
        try {
            if (TextUtils.isEmpty(f20577G)) {
                jSONArray = new JSONArray(f20594X);
                str = "sort";
            } else if ("04".equals(f20577G)) {
                jSONArray = new JSONArray(f20598aa);
                str = "sort";
            } else {
                jSONArray = new JSONArray(f20596Z);
                str = "sort";
            }
            f20599ab = m5998b(jSONArray, str);
        } catch (Exception unused3) {
        }
        f20593W = new Handler(f20600ac);
        if (TextUtils.isEmpty(f20577G) || !C10915b.m5854b()) {
            m5996c("0");
        } else {
            HwOpenPayTask hwOpenPayTask = new HwOpenPayTask(m5981q());
            f20593W.sendEmptyMessageDelayed(1004, 1000L);
            hwOpenPayTask.supportCapacity("UNIONONLINEPAY", new C10753c());
        }
        return 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: q */
    public static Context m5981q() {
        WeakReference weakReference = f20581K;
        if (weakReference != null) {
            return (Context) weakReference.get();
        }
        return null;
    }

    public static void releaseMemory() {
        f20581K = null;
    }

    public static int startPay(Context context, String str, String str2, String str3, String str4) {
        return m6011a(context, str, str2, str3, str4, "", "");
    }

    public static void startPayByJAR(Context context, Class cls, String str, String str2, String str3, String str4) {
        startPay(context, str, str2, str3, str4);
    }

    public static int startSEPay(Context context, String str, String str2, String str3, String str4, String str5) {
        return m6011a(context, str, str2, str3, str4, "", str5);
    }

    public static void startSamsungPay(Context context, Class cls, String str, String str2, String str3, String str4) {
        m6011a(context, str, str2, str3, str4, f20619t, f20625z);
    }
}
