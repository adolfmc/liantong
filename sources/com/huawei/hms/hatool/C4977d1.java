package com.huawei.hms.hatool;

import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;

/* renamed from: com.huawei.hms.hatool.d1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4977d1 implements InterfaceRunnableC4984g {

    /* renamed from: a */
    private String f11373a;

    /* renamed from: b */
    private String f11374b;

    /* renamed from: c */
    private String f11375c;

    /* renamed from: d */
    private List<C4971b1> f11376d;

    public C4977d1(List<C4971b1> list, String str, String str2, String str3) {
        this.f11373a = str;
        this.f11374b = str2;
        this.f11375c = str3;
        this.f11376d = list;
    }

    /* renamed from: a */
    private void m14761a() {
        C4975d.m14766a(AbstractC5020q0.m14526i(), "backup_event", AbstractC5010n1.m14583a(this.f11373a, this.f11375c, this.f11374b));
    }

    @Override // java.lang.Runnable
    public void run() {
        List<C4971b1> list = this.f11376d;
        if (list == null || list.size() == 0) {
            C5029v.m14453d("hmsSdk", "failed events is empty");
            return;
        }
        if (C4973c0.m14778a(AbstractC5020q0.m14526i(), "cached_v2_1", AbstractC5020q0.m14524k() * 1048576)) {
            C5029v.m14452e("hmsSdk", "The cacheFile is full,Can not writing data! reqID:" + this.f11374b);
            return;
        }
        String m14584a = AbstractC5010n1.m14584a(this.f11373a, this.f11375c);
        List<C4971b1> list2 = C4974c1.m14771b(AbstractC5020q0.m14526i(), "cached_v2_1", m14584a).get(m14584a);
        if (list2 != null && list2.size() != 0) {
            this.f11376d.addAll(list2);
        }
        JSONArray jSONArray = new JSONArray();
        for (C4971b1 c4971b1 : this.f11376d) {
            try {
                jSONArray.put(c4971b1.m14786d());
            } catch (JSONException unused) {
                C5029v.m14452e("hmsSdk", "event to json error");
            }
        }
        String jSONArray2 = jSONArray.toString();
        if (jSONArray2.length() > AbstractC5020q0.m14528h() * 1048576) {
            C5029v.m14452e("hmsSdk", "this failed data is too long,can not writing it");
            this.f11376d = null;
            return;
        }
        C5029v.m14453d("hmsSdk", "data send failed, write to cache file...reqID:" + this.f11374b);
        C4975d.m14763b(AbstractC5020q0.m14526i(), "cached_v2_1", m14584a, jSONArray2);
        m14761a();
    }
}
