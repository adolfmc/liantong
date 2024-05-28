package com.sdk.p285a;

import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.p285a.C6960d;
import com.sdk.p289e.AbstractC6992b;
import com.sdk.p290f.C6998d;
import com.sdk.p298n.C7014a;
import com.sdk.p302r.C7037a;
import java.io.File;
import java.io.Serializable;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/* renamed from: com.sdk.a.e */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C6962e<T> implements Serializable {

    /* renamed from: i */
    public static final String f18038i = "com.sdk.a.e";

    /* renamed from: j */
    public static final Boolean f18039j = Boolean.valueOf(C6998d.f18135a);
    private static final long serialVersionUID = -8869881146515387822L;

    /* renamed from: b */
    public String f18041b;

    /* renamed from: c */
    public TreeMap<String, Object> f18042c;

    /* renamed from: d */
    public ArrayList<File> f18043d;

    /* renamed from: e */
    public HashMap<String, Object> f18044e;

    /* renamed from: g */
    public AbstractC6992b<T> f18046g;

    /* renamed from: h */
    public C7014a.EnumC7021c f18047h;

    /* renamed from: a */
    public String f18040a = C6960d.EnumC6961a.GET.toString();

    /* renamed from: f */
    public int f18045f = 0;

    /* renamed from: a */
    public AbstractC6992b<T> m8202a() {
        return this.f18046g;
    }

    /* renamed from: a */
    public String m8201a(TreeMap<String, Object> treeMap) {
        StringBuilder sb;
        if (treeMap != null) {
            try {
                sb = new StringBuilder();
                for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                    String key = entry.getKey();
                    Object value = entry.getValue();
                    if (value != null && C7037a.m8129b(key).booleanValue()) {
                        String encode = URLEncoder.encode(value.toString(), "UTF-8");
                        boolean z = C6998d.f18135a;
                        sb.append(key);
                        sb.append("=");
                        sb.append(encode);
                        sb.append("&");
                    }
                }
                sb.deleteCharAt(sb.length() - 1);
            } catch (Exception e) {
                LogUtils.m8186e(f18038i, e.getMessage(), f18039j);
                throw new Exception("http请求参数出错");
            }
        } else {
            sb = null;
        }
        if (sb == null) {
            return null;
        }
        return sb.toString();
    }

    /* renamed from: b */
    public String m8200b() {
        return this.f18040a;
    }

    /* renamed from: c */
    public int m8199c() {
        return this.f18045f;
    }

    /* renamed from: d */
    public String m8198d() {
        return this.f18041b;
    }
}
