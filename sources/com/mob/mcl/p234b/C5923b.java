package com.mob.mcl.p234b;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* renamed from: com.mob.mcl.b.b */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5923b {

    /* renamed from: a */
    private HashMap<String, Object> f14578a;

    /* renamed from: b */
    private boolean f14579b;

    /* renamed from: a */
    public HashMap<String, Object> m12056a() {
        return this.f14578a;
    }

    public C5923b(HashMap<String, Object> hashMap) {
        this.f14578a = hashMap;
        this.f14579b = false;
    }

    public C5923b(HashMap<String, Object> hashMap, boolean z) {
        this.f14578a = hashMap;
        this.f14579b = z;
    }

    /* renamed from: b */
    public int m12054b() throws IOException {
        HashMap<String, Object> hashMap = this.f14578a;
        if (hashMap != null) {
            return ((Integer) hashMap.get("code")).intValue();
        }
        return -1;
    }

    /* renamed from: c */
    public InputStream m12053c() throws IOException {
        return new ByteArrayInputStream(m12050f());
    }

    /* renamed from: d */
    public InputStream m12052d() throws IOException {
        return new ByteArrayInputStream(m12050f());
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* renamed from: e */
    public Map<String, List<String>> m12051e() throws IOException {
        HashMap hashMap;
        HashMap hashMap2 = new HashMap();
        m12055a(hashMap2, "apc", String.valueOf(this.f14579b));
        HashMap<String, Object> hashMap3 = this.f14578a;
        if (hashMap3 != null && hashMap3.containsKey("headers") && (hashMap = (HashMap) this.f14578a.get("headers")) != null) {
            for (Map.Entry entry : hashMap.entrySet()) {
                if (entry.getValue() instanceof String) {
                    ArrayList arrayList = new ArrayList();
                    arrayList.add((String) entry.getValue());
                    hashMap2.put(entry.getKey(), arrayList);
                } else if (entry.getValue() instanceof List) {
                    hashMap2.put(entry.getKey(), (List) entry.getValue());
                }
            }
        }
        return hashMap2;
    }

    /* renamed from: f */
    private byte[] m12050f() {
        String str = "{}";
        HashMap<String, Object> hashMap = this.f14578a;
        if (hashMap != null && hashMap.containsKey("body")) {
            str = (String) this.f14578a.get("body");
        }
        return str.getBytes();
    }

    /* renamed from: a */
    private void m12055a(HashMap<String, List<String>> hashMap, String str, String str2) {
        ArrayList arrayList = new ArrayList();
        arrayList.add(str2);
        hashMap.put(str, arrayList);
    }
}
