package com.bytedance.applog;

import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

/* renamed from: com.bytedance.applog.d1 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C3565d1 {

    /* renamed from: a */
    public String f8406a;

    /* renamed from: b */
    public List<C3567b> f8407b;

    /* renamed from: c */
    public C3566a f8408c;

    /* renamed from: d */
    public String f8409d;

    /* renamed from: com.bytedance.applog.d1$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3566a {

        /* renamed from: a */
        public int f8410a;

        /* renamed from: b */
        public int f8411b;

        /* renamed from: c */
        public int f8412c;

        /* renamed from: d */
        public int f8413d;

        public C3566a(int i, int i2, int i3, int i4) {
            this.f8410a = i;
            this.f8411b = i2;
            this.f8412c = i3;
            this.f8413d = i4;
        }

        /* renamed from: a */
        public JSONObject m17316a() {
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.put("x", this.f8410a);
                jSONObject.put("y", this.f8411b);
                jSONObject.put("width", this.f8412c);
                jSONObject.put("height", this.f8413d);
                return jSONObject;
            } catch (JSONException e) {
                e.printStackTrace();
                return null;
            }
        }

        public String toString() {
            StringBuilder m17349a = C3535a.m17349a("FrameModel{x=");
            m17349a.append(this.f8410a);
            m17349a.append(", y=");
            m17349a.append(this.f8411b);
            m17349a.append(", width=");
            m17349a.append(this.f8412c);
            m17349a.append(", height=");
            m17349a.append(this.f8413d);
            m17349a.append('}');
            return m17349a.toString();
        }
    }

    /* renamed from: com.bytedance.applog.d1$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C3567b {

        /* renamed from: a */
        public String f8414a;

        /* renamed from: b */
        public C3566a f8415b;

        /* renamed from: c */
        public String f8416c;

        /* renamed from: d */
        public String f8417d;

        /* renamed from: e */
        public List<String> f8418e;

        /* renamed from: f */
        public int f8419f;

        /* renamed from: g */
        public List<String> f8420g;

        /* renamed from: h */
        public List<C3567b> f8421h;

        /* renamed from: i */
        public String f8422i;

        /* renamed from: j */
        public boolean f8423j;

        /* renamed from: k */
        public List<String> f8424k;

        public C3567b(String str, C3566a c3566a, String str2, String str3, List<String> list, int i, List<String> list2, List<C3567b> list3, String str4, boolean z, List<String> list4) {
            this.f8414a = str;
            this.f8415b = c3566a;
            this.f8416c = str2;
            this.f8417d = str3;
            this.f8418e = list;
            this.f8419f = i;
            this.f8420g = list2;
            this.f8421h = list3;
            this.f8422i = str4;
            this.f8423j = z;
            this.f8424k = list4;
        }

        public String toString() {
            StringBuilder m17349a = C3535a.m17349a("InfoModel{nodeName='");
            m17349a.append(this.f8414a);
            m17349a.append('\'');
            m17349a.append(", frameModel=");
            m17349a.append(this.f8415b);
            m17349a.append(", elementPath='");
            m17349a.append(this.f8416c);
            m17349a.append('\'');
            m17349a.append(", elementPathV2='");
            m17349a.append(this.f8417d);
            m17349a.append('\'');
            m17349a.append(", positions=");
            m17349a.append(this.f8418e);
            m17349a.append(", zIndex=");
            m17349a.append(this.f8419f);
            m17349a.append(", texts=");
            m17349a.append(this.f8420g);
            m17349a.append(", children=");
            m17349a.append(this.f8421h);
            m17349a.append(", href='");
            m17349a.append(this.f8422i);
            m17349a.append('\'');
            m17349a.append(", checkList=");
            m17349a.append(this.f8423j);
            m17349a.append(", fuzzyPositions=");
            m17349a.append(this.f8424k);
            m17349a.append('}');
            return m17349a.toString();
        }
    }

    public String toString() {
        StringBuilder m17349a = C3535a.m17349a("WebInfoModel{page='");
        m17349a.append(this.f8406a);
        m17349a.append('\'');
        m17349a.append(", info=");
        m17349a.append(this.f8407b);
        m17349a.append('}');
        return m17349a.toString();
    }
}
