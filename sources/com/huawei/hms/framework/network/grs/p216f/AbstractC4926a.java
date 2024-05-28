package com.huawei.hms.framework.network.grs.p216f;

import android.content.Context;
import android.text.TextUtils;
import com.huawei.hms.framework.common.Logger;
import com.huawei.hms.framework.common.StringUtils;
import com.huawei.hms.framework.network.grs.GrsApp;
import com.huawei.hms.framework.network.grs.GrsBaseInfo;
import com.huawei.hms.framework.network.grs.local.model.C4959a;
import com.huawei.hms.framework.network.grs.local.model.C4960b;
import com.huawei.hms.framework.network.grs.local.model.C4961c;
import com.huawei.hms.framework.network.grs.local.model.C4962d;
import com.huawei.hms.framework.network.grs.p215e.C4923a;
import com.huawei.hms.framework.network.grs.p220h.C4953c;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.huawei.hms.framework.network.grs.f.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class AbstractC4926a {

    /* renamed from: a */
    protected C4959a f11234a;

    /* renamed from: b */
    protected List<C4960b> f11235b;

    /* renamed from: c */
    protected Map<String, String> f11236c = new ConcurrentHashMap(16);

    /* renamed from: d */
    protected boolean f11237d = false;

    /* renamed from: e */
    protected boolean f11238e = false;

    /* renamed from: f */
    protected boolean f11239f = false;

    /* renamed from: g */
    protected Set<String> f11240g = new HashSet(16);

    /* renamed from: a */
    private Map<String, String> m14981a(List<C4960b> list, GrsBaseInfo grsBaseInfo, String str) {
        ConcurrentHashMap concurrentHashMap = new ConcurrentHashMap(16);
        concurrentHashMap.put("no_route_country", "no-country");
        for (C4960b c4960b : list) {
            if (c4960b.m14841a().contains(grsBaseInfo.getIssueCountry())) {
                concurrentHashMap.put(grsBaseInfo.getIssueCountry(), c4960b.m14838b());
            }
            if (c4960b.m14841a().contains(grsBaseInfo.getRegCountry())) {
                concurrentHashMap.put(grsBaseInfo.getRegCountry(), c4960b.m14838b());
            }
            if (c4960b.m14841a().contains(grsBaseInfo.getSerCountry())) {
                concurrentHashMap.put(grsBaseInfo.getSerCountry(), c4960b.m14838b());
            }
            if (c4960b.m14841a().contains(str)) {
                Logger.m15047v("AbstractLocalManager", "get countryGroupID from geoIp");
                concurrentHashMap.put(str, c4960b.m14838b());
            }
        }
        return concurrentHashMap;
    }

    /* renamed from: b */
    private int m14978b(String str, Context context) {
        if (m14971f(C4953c.m14857a(str, context)) == 0) {
            Logger.m15048i("AbstractLocalManager", "load APP_CONFIG_FILE success{%s}.", str);
            return 0;
        }
        return -1;
    }

    /* renamed from: f */
    private int m14971f(String str) {
        int mo14958b;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        if (!this.f11238e || (mo14958b = mo14958b(str)) == 0) {
            int mo14959a = mo14959a(str);
            return mo14959a != 0 ? mo14959a : mo14957e(str);
        }
        return mo14958b;
    }

    /* renamed from: g */
    private int m14970g(String str) {
        List<C4960b> list;
        int m14975c;
        if (TextUtils.isEmpty(str)) {
            return -1;
        }
        return (!this.f11238e || !((list = this.f11235b) == null || list.isEmpty()) || (m14975c = m14975c(str)) == 0) ? m14973d(str) : m14975c;
    }

    /* renamed from: a */
    public abstract int mo14959a(String str);

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: a */
    public int m14982a(String str, Context context) {
        StringBuilder sb = new StringBuilder();
        sb.append(GrsApp.getInstance().getBrand("/"));
        sb.append(str);
        return m14978b(sb.toString(), context) != 0 ? -1 : 0;
    }

    /* renamed from: a */
    public String m14986a(Context context, C4923a c4923a, GrsBaseInfo grsBaseInfo, String str, String str2, boolean z) {
        Map<String, String> m14985a = m14985a(context, c4923a, grsBaseInfo, str, z);
        if (m14985a == null) {
            Logger.m15043w("AbstractLocalManager", "addresses not found by routeby in local config{%s}", str);
            return null;
        }
        return m14985a.get(str2);
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0075 A[Catch: JSONException -> 0x0091, LOOP:1: B:24:0x006f->B:26:0x0075, LOOP_END, TryCatch #0 {JSONException -> 0x0091, blocks: (B:7:0x000b, B:8:0x0013, B:10:0x0019, B:13:0x0048, B:18:0x0060, B:20:0x0067, B:24:0x006f, B:26:0x0075, B:27:0x0081, B:28:0x008a, B:14:0x004e, B:17:0x0059), top: B:36:0x000b }] */
    /* renamed from: a */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List<com.huawei.hms.framework.network.grs.local.model.C4960b> m14980a(org.json.JSONArray r10) {
        /*
            r9 = this;
            if (r10 == 0) goto Lac
            int r0 = r10.length()
            if (r0 != 0) goto La
            goto Lac
        La:
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch: org.json.JSONException -> L91
            r2 = 16
            r1.<init>(r2)     // Catch: org.json.JSONException -> L91
            r3 = r0
        L13:
            int r4 = r10.length()     // Catch: org.json.JSONException -> L91
            if (r3 >= r4) goto L90
            org.json.JSONObject r4 = r10.getJSONObject(r3)     // Catch: org.json.JSONException -> L91
            com.huawei.hms.framework.network.grs.local.model.b r5 = new com.huawei.hms.framework.network.grs.local.model.b     // Catch: org.json.JSONException -> L91
            r5.<init>()     // Catch: org.json.JSONException -> L91
            java.lang.String r6 = "id"
            java.lang.String r6 = r4.getString(r6)     // Catch: org.json.JSONException -> L91
            r5.m14837b(r6)     // Catch: org.json.JSONException -> L91
            java.lang.String r6 = "name"
            java.lang.String r6 = r4.getString(r6)     // Catch: org.json.JSONException -> L91
            r5.m14836c(r6)     // Catch: org.json.JSONException -> L91
            java.lang.String r6 = "description"
            java.lang.String r6 = r4.getString(r6)     // Catch: org.json.JSONException -> L91
            r5.m14840a(r6)     // Catch: org.json.JSONException -> L91
            r6 = 0
            java.lang.String r7 = "countriesOrAreas"
            boolean r7 = r4.has(r7)     // Catch: org.json.JSONException -> L91
            if (r7 == 0) goto L4e
            java.lang.String r6 = "countriesOrAreas"
        L48:
            org.json.JSONArray r4 = r4.getJSONArray(r6)     // Catch: org.json.JSONException -> L91
            r6 = r4
            goto L60
        L4e:
            java.lang.String r7 = "countries"
            boolean r7 = r4.has(r7)     // Catch: org.json.JSONException -> L91
            if (r7 == 0) goto L59
            java.lang.String r6 = "countries"
            goto L48
        L59:
            java.lang.String r4 = "AbstractLocalManager"
            java.lang.String r7 = "current country or area group has not config countries or areas."
            com.huawei.hms.framework.common.Logger.m15045w(r4, r7)     // Catch: org.json.JSONException -> L91
        L60:
            java.util.HashSet r4 = new java.util.HashSet     // Catch: org.json.JSONException -> L91
            r4.<init>(r2)     // Catch: org.json.JSONException -> L91
            if (r6 == 0) goto L8a
            int r7 = r6.length()     // Catch: org.json.JSONException -> L91
            if (r7 != 0) goto L6e
            goto L8a
        L6e:
            r7 = r0
        L6f:
            int r8 = r6.length()     // Catch: org.json.JSONException -> L91
            if (r7 >= r8) goto L81
            java.lang.Object r8 = r6.get(r7)     // Catch: org.json.JSONException -> L91
            java.lang.String r8 = (java.lang.String) r8     // Catch: org.json.JSONException -> L91
            r4.add(r8)     // Catch: org.json.JSONException -> L91
            int r7 = r7 + 1
            goto L6f
        L81:
            r5.m14839a(r4)     // Catch: org.json.JSONException -> L91
            r1.add(r5)     // Catch: org.json.JSONException -> L91
            int r3 = r3 + 1
            goto L13
        L8a:
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch: org.json.JSONException -> L91
            r10.<init>()     // Catch: org.json.JSONException -> L91
            return r10
        L90:
            return r1
        L91:
            r10 = move-exception
            r1 = 1
            java.lang.Object[] r1 = new java.lang.Object[r1]
            java.lang.String r10 = r10.getMessage()
            java.lang.String r10 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r10)
            r1[r0] = r10
            java.lang.String r10 = "AbstractLocalManager"
            java.lang.String r0 = "parse countrygroup failed maybe json style is wrong. %s"
            com.huawei.hms.framework.common.Logger.m15043w(r10, r0, r1)
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            return r10
        Lac:
            java.util.ArrayList r10 = new java.util.ArrayList
            r10.<init>()
            return r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.AbstractC4926a.m14980a(org.json.JSONArray):java.util.List");
    }

    /* renamed from: a */
    public Map<String, String> m14985a(Context context, C4923a c4923a, GrsBaseInfo grsBaseInfo, String str, boolean z) {
        C4959a c4959a = this.f11234a;
        if (c4959a == null) {
            Logger.m15045w("AbstractLocalManager", "application data is null.");
            return null;
        }
        C4961c m14845a = c4959a.m14845a(str);
        if (m14845a == null) {
            Logger.m15043w("AbstractLocalManager", "service not found in local config{%s}", str);
            return null;
        }
        String m14955b = C4930e.m14955b(context, c4923a, m14845a.m14831b(), grsBaseInfo, z);
        if (m14955b == null) {
            Logger.m15043w("AbstractLocalManager", "country not found by routeby in local config{%s}", m14845a.m14831b());
            return null;
        }
        List<C4960b> m14835a = m14845a.m14835a();
        C4962d m14834a = m14845a.m14834a(((m14835a == null || m14835a.size() == 0) ? this.f11236c : m14981a(m14835a, grsBaseInfo, m14955b)).get(m14955b));
        if (m14834a == null) {
            return null;
        }
        return m14834a.m14828a();
    }

    /* renamed from: a */
    public void m14987a() {
        C4959a c4959a = this.f11234a;
        if (c4959a != null) {
            c4959a.m14847a();
            this.f11239f = true;
        }
    }

    /* renamed from: a */
    public void m14984a(Context context, List<String> list) {
        Object[] objArr;
        String str;
        String str2;
        if (list == null || list.size() <= 0) {
            return;
        }
        for (String str3 : list) {
            if (Pattern.matches("^grs_sdk_global_route_config_[a-zA-Z]+\\.json$", str3)) {
                if (m14970g(C4953c.m14857a(GrsApp.getInstance().getBrand("/") + str3, context)) == 0) {
                    objArr = new Object[]{str3};
                    str = "AbstractLocalManager";
                    str2 = "load SDK_CONFIG_FILE: %s, sucess.";
                } else {
                    objArr = new Object[]{str3};
                    str = "AbstractLocalManager";
                    str2 = "load SDK_CONFIG_FILE: %s, failure.";
                }
                Logger.m15048i(str, str2, objArr);
            }
        }
    }

    /* renamed from: a */
    public void m14983a(GrsBaseInfo grsBaseInfo) {
        this.f11236c.put("no_route_country", "no-country");
        List<C4960b> list = this.f11235b;
        if (list == null || list.isEmpty()) {
            return;
        }
        for (C4960b c4960b : this.f11235b) {
            if (c4960b.m14841a().contains(grsBaseInfo.getIssueCountry())) {
                this.f11236c.put(grsBaseInfo.getIssueCountry(), c4960b.m14838b());
            }
            if (c4960b.m14841a().contains(grsBaseInfo.getRegCountry())) {
                this.f11236c.put(grsBaseInfo.getRegCountry(), c4960b.m14838b());
            }
            if (c4960b.m14841a().contains(grsBaseInfo.getSerCountry())) {
                this.f11236c.put(grsBaseInfo.getSerCountry(), c4960b.m14838b());
            }
        }
        this.f11235b = null;
    }

    /* renamed from: b */
    public abstract int mo14958b(String str);

    /* renamed from: b */
    public C4959a m14979b() {
        return this.f11234a;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* JADX WARN: Removed duplicated region for block: B:27:0x0098  */
    /* JADX WARN: Removed duplicated region for block: B:43:0x00f0  */
    /* renamed from: b */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void m14977b(org.json.JSONArray r15) {
        /*
            Method dump skipped, instructions count: 257
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.AbstractC4926a.m14977b(org.json.JSONArray):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:12:0x0034 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:13:0x0035 A[Catch: JSONException -> 0x003f, TRY_LEAVE, TryCatch #0 {JSONException -> 0x003f, blocks: (B:3:0x000b, B:6:0x001b, B:13:0x0035, B:7:0x0020, B:10:0x002b), top: B:18:0x000b }] */
    /* renamed from: c */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public int m14975c(java.lang.String r5) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 16
            r0.<init>(r1)
            r4.f11235b = r0
            r0 = -1
            r1 = 0
            org.json.JSONObject r2 = new org.json.JSONObject     // Catch: org.json.JSONException -> L3f
            r2.<init>(r5)     // Catch: org.json.JSONException -> L3f
            r5 = 0
            java.lang.String r3 = "countryOrAreaGroups"
            boolean r3 = r2.has(r3)     // Catch: org.json.JSONException -> L3f
            if (r3 == 0) goto L20
            java.lang.String r5 = "countryOrAreaGroups"
        L1b:
            org.json.JSONArray r5 = r2.getJSONArray(r5)     // Catch: org.json.JSONException -> L3f
            goto L32
        L20:
            java.lang.String r3 = "countryGroups"
            boolean r3 = r2.has(r3)     // Catch: org.json.JSONException -> L3f
            if (r3 == 0) goto L2b
            java.lang.String r5 = "countryGroups"
            goto L1b
        L2b:
            java.lang.String r2 = "AbstractLocalManager"
            java.lang.String r3 = "maybe local config json is wrong because the default countryOrAreaGroups isn't config."
            com.huawei.hms.framework.common.Logger.m15052e(r2, r3)     // Catch: org.json.JSONException -> L3f
        L32:
            if (r5 != 0) goto L35
            return r0
        L35:
            java.util.List<com.huawei.hms.framework.network.grs.local.model.b> r2 = r4.f11235b     // Catch: org.json.JSONException -> L3f
            java.util.List r5 = r4.m14980a(r5)     // Catch: org.json.JSONException -> L3f
            r2.addAll(r5)     // Catch: org.json.JSONException -> L3f
            return r1
        L3f:
            r5 = move-exception
            r2 = 1
            java.lang.Object[] r2 = new java.lang.Object[r2]
            java.lang.String r5 = r5.getMessage()
            java.lang.String r5 = com.huawei.hms.framework.common.StringUtils.anonymizeMessage(r5)
            r2[r1] = r5
            java.lang.String r5 = "AbstractLocalManager"
            java.lang.String r1 = "parse countrygroup failed maybe json style is wrong. %s"
            com.huawei.hms.framework.common.Logger.m15043w(r5, r1, r2)
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.huawei.hms.framework.network.grs.p216f.AbstractC4926a.m14975c(java.lang.String):int");
    }

    /* renamed from: c */
    public Set<String> m14976c() {
        return this.f11240g;
    }

    /* renamed from: d */
    public int m14973d(String str) {
        try {
            m14977b(new JSONObject(str).getJSONArray("services"));
            return 0;
        } catch (JSONException e) {
            Logger.m15043w("AbstractLocalManager", "parse 2.0 services failed maybe because of json style.please check! %s", StringUtils.anonymizeMessage(e.getMessage()));
            return -1;
        }
    }

    /* renamed from: d */
    public boolean m14974d() {
        return this.f11239f;
    }

    /* renamed from: e */
    public abstract int mo14957e(String str);

    /* renamed from: e */
    public boolean m14972e() {
        return this.f11237d;
    }
}
