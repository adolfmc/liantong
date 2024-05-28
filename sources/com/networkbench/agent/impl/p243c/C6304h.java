package com.networkbench.agent.impl.p243c;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.com.google.gson.JsonArray;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.c.h */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class C6304h extends HarvestableArray {

    /* renamed from: d */
    private static final int f15802d = 1024;

    /* renamed from: a */
    private String f15803a = "";

    /* renamed from: b */
    private String f15804b = "";

    /* renamed from: c */
    private int f15805c = 0;

    /* renamed from: a */
    public void m10518a(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        if (str.length() > 1024) {
            this.f15803a = str.substring(0, 1024);
        } else {
            this.f15803a = str;
        }
    }

    /* renamed from: a */
    public String m10520a() {
        return this.f15803a;
    }

    /* renamed from: b */
    public void m10516b(String str) {
        this.f15804b = str;
    }

    /* renamed from: b */
    public String m10517b() {
        return this.f15804b;
    }

    /* renamed from: a */
    public void m10519a(int i) {
        this.f15805c = i;
    }

    /* renamed from: c */
    public int m10515c() {
        return this.f15805c;
    }

    @Override // com.networkbench.agent.impl.harvest.type.HarvestableArray, com.networkbench.agent.impl.harvest.type.BaseHarvestable, com.networkbench.agent.impl.harvest.type.Harvestable
    public JsonArray asJsonArray() {
        JsonArray jsonArray = new JsonArray();
        jsonArray.add(new JsonPrimitive(this.f15803a));
        jsonArray.add(new JsonPrimitive(this.f15804b));
        jsonArray.add(new JsonPrimitive((Number) Integer.valueOf(this.f15805c)));
        return jsonArray;
    }
}
