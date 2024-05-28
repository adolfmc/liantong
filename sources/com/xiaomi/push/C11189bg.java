package com.xiaomi.push;

import android.text.TextUtils;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import org.json.JSONObject;

/* renamed from: com.xiaomi.push.bg */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11189bg extends JSONObject implements InterfaceC11187be {

    /* renamed from: a */
    private static final int f21595a = 2;

    /* renamed from: b */
    private static final int f21596b = 3;

    /* renamed from: a */
    private final LinkedHashMap<String, Integer> f21597a = new LinkedHashMap<>();

    @Override // com.xiaomi.push.InterfaceC11187be
    /* renamed from: a */
    public int mo4732a() {
        int i = f21595a;
        for (Integer num : this.f21597a.values()) {
            i += num.intValue();
        }
        return i + (length() - 1);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, int i) {
        if (!TextUtils.isEmpty(str)) {
            this.f21597a.put(str, Integer.valueOf(str.length() + String.valueOf(i).length() + f21596b));
        }
        return super.put(str, i);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, long j) {
        if (!TextUtils.isEmpty(str)) {
            this.f21597a.put(str, Integer.valueOf(str.length() + String.valueOf(j).length() + f21596b));
        }
        return super.put(str, j);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, double d) {
        if (!TextUtils.isEmpty(str)) {
            this.f21597a.put(str, Integer.valueOf(str.length() + String.valueOf(d).length() + f21596b));
        }
        return super.put(str, d);
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, Object obj) {
        JSONObject put = super.put(str, obj);
        if (!TextUtils.isEmpty(str) && obj != null) {
            if (obj instanceof InterfaceC11187be) {
                this.f21597a.put(str, Integer.valueOf(str.length() + ((InterfaceC11187be) obj).mo4732a() + f21596b));
            } else {
                this.f21597a.put(str, Integer.valueOf(str.length() + String.valueOf(obj).getBytes(StandardCharsets.UTF_8).length + f21596b + f21595a));
            }
        }
        return put;
    }

    @Override // org.json.JSONObject
    public JSONObject put(String str, boolean z) {
        if (!TextUtils.isEmpty(str)) {
            this.f21597a.put(str, Integer.valueOf(str.length() + String.valueOf(z).length() + f21596b));
        }
        return super.put(str, z);
    }

    @Override // org.json.JSONObject
    public Object remove(String str) {
        this.f21597a.remove(str);
        return super.remove(str);
    }
}
