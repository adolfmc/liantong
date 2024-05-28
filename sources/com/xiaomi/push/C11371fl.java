package com.xiaomi.push;

import android.os.Bundle;
import android.os.Parcelable;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* renamed from: com.xiaomi.push.fl */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11371fl implements InterfaceC11376fp {

    /* renamed from: a */
    private String f22293a;

    /* renamed from: a */
    private List<C11371fl> f22294a;

    /* renamed from: a */
    private String[] f22295a;

    /* renamed from: b */
    private String f22296b;

    /* renamed from: b */
    private String[] f22297b;

    /* renamed from: c */
    private String f22298c;

    public C11371fl(String str, String str2, String[] strArr, String[] strArr2) {
        this.f22295a = null;
        this.f22297b = null;
        this.f22294a = null;
        this.f22293a = str;
        this.f22296b = str2;
        this.f22295a = strArr;
        this.f22297b = strArr2;
    }

    public C11371fl(String str, String str2, String[] strArr, String[] strArr2, String str3, List<C11371fl> list) {
        this.f22295a = null;
        this.f22297b = null;
        this.f22294a = null;
        this.f22293a = str;
        this.f22296b = str2;
        this.f22295a = strArr;
        this.f22297b = strArr2;
        this.f22298c = str3;
        this.f22294a = list;
    }

    /* renamed from: a */
    public String m3832a() {
        return this.f22293a;
    }

    /* renamed from: b */
    public String m3825b() {
        return this.f22296b;
    }

    /* renamed from: c */
    public String m3824c() {
        if (!TextUtils.isEmpty(this.f22298c)) {
            return C11389fx.m3745b(this.f22298c);
        }
        return this.f22298c;
    }

    public String toString() {
        return mo3777d();
    }

    @Override // com.xiaomi.push.InterfaceC11376fp
    /* renamed from: d */
    public String mo3777d() {
        StringBuilder sb = new StringBuilder();
        sb.append("<");
        sb.append(this.f22293a);
        if (!TextUtils.isEmpty(this.f22296b)) {
            sb.append(" ");
            sb.append("xmlns=");
            sb.append("\"");
            sb.append(this.f22296b);
            sb.append("\"");
        }
        String[] strArr = this.f22295a;
        if (strArr != null && strArr.length > 0) {
            for (int i = 0; i < this.f22295a.length; i++) {
                if (!TextUtils.isEmpty(this.f22297b[i])) {
                    sb.append(" ");
                    sb.append(this.f22295a[i]);
                    sb.append("=\"");
                    sb.append(C11389fx.m3748a(this.f22297b[i]));
                    sb.append("\"");
                }
            }
        }
        if (!TextUtils.isEmpty(this.f22298c)) {
            sb.append(">");
            sb.append(this.f22298c);
            sb.append("</");
            sb.append(this.f22293a);
            sb.append(">");
        } else {
            List<C11371fl> list = this.f22294a;
            if (list != null && list.size() > 0) {
                sb.append(">");
                for (C11371fl c11371fl : this.f22294a) {
                    sb.append(c11371fl.mo3777d());
                }
                sb.append("</");
                sb.append(this.f22293a);
                sb.append(">");
            } else {
                sb.append("/>");
            }
        }
        return sb.toString();
    }

    /* renamed from: a */
    public Bundle m3834a() {
        Bundle bundle = new Bundle();
        bundle.putString("ext_ele_name", this.f22293a);
        bundle.putString("ext_ns", this.f22296b);
        bundle.putString("ext_text", this.f22298c);
        Bundle bundle2 = new Bundle();
        String[] strArr = this.f22295a;
        if (strArr != null && strArr.length > 0) {
            int i = 0;
            while (true) {
                String[] strArr2 = this.f22295a;
                if (i >= strArr2.length) {
                    break;
                }
                bundle2.putString(strArr2[i], this.f22297b[i]);
                i++;
            }
        }
        bundle.putBundle("attributes", bundle2);
        List<C11371fl> list = this.f22294a;
        if (list != null && list.size() > 0) {
            bundle.putParcelableArray("children", m3827a(this.f22294a));
        }
        return bundle;
    }

    /* renamed from: a */
    public Parcelable m3833a() {
        return m3834a();
    }

    /* renamed from: a */
    public static Parcelable[] m3826a(C11371fl[] c11371flArr) {
        if (c11371flArr == null) {
            return null;
        }
        Parcelable[] parcelableArr = new Parcelable[c11371flArr.length];
        for (int i = 0; i < c11371flArr.length; i++) {
            parcelableArr[i] = c11371flArr[i].m3833a();
        }
        return parcelableArr;
    }

    /* renamed from: a */
    public static Parcelable[] m3827a(List<C11371fl> list) {
        return m3826a((C11371fl[]) list.toArray(new C11371fl[list.size()]));
    }

    /* renamed from: a */
    public static C11371fl m3831a(Bundle bundle) {
        ArrayList arrayList;
        String string = bundle.getString("ext_ele_name");
        String string2 = bundle.getString("ext_ns");
        String string3 = bundle.getString("ext_text");
        Bundle bundle2 = bundle.getBundle("attributes");
        Set<String> keySet = bundle2.keySet();
        String[] strArr = new String[keySet.size()];
        String[] strArr2 = new String[keySet.size()];
        int i = 0;
        for (String str : keySet) {
            strArr[i] = str;
            strArr2[i] = bundle2.getString(str);
            i++;
        }
        if (bundle.containsKey("children")) {
            Parcelable[] parcelableArray = bundle.getParcelableArray("children");
            ArrayList arrayList2 = new ArrayList(parcelableArray.length);
            for (Parcelable parcelable : parcelableArray) {
                arrayList2.add(m3831a((Bundle) parcelable));
            }
            arrayList = arrayList2;
        } else {
            arrayList = null;
        }
        return new C11371fl(string, string2, strArr, strArr2, string3, arrayList);
    }

    /* renamed from: a */
    public String m3829a(String str) {
        if (str == null) {
            throw new IllegalArgumentException();
        }
        if (this.f22295a == null) {
            return null;
        }
        int i = 0;
        while (true) {
            String[] strArr = this.f22295a;
            if (i >= strArr.length) {
                return null;
            }
            if (str.equals(strArr[i])) {
                return this.f22297b[i];
            }
            i++;
        }
    }

    /* renamed from: a */
    public void m3830a(C11371fl c11371fl) {
        if (this.f22294a == null) {
            this.f22294a = new ArrayList();
        }
        if (this.f22294a.contains(c11371fl)) {
            return;
        }
        this.f22294a.add(c11371fl);
    }

    /* renamed from: a */
    public void m3828a(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.f22298c = C11389fx.m3748a(str);
        } else {
            this.f22298c = str;
        }
    }
}
