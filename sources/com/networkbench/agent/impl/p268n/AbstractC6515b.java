package com.networkbench.agent.impl.p268n;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.RequestMethodType;
import com.networkbench.agent.impl.harvest.type.HarvestableArray;
import com.networkbench.agent.impl.util.C6653u;
import com.networkbench.com.google.gson.JsonPrimitive;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.networkbench.agent.impl.n.b */
/* loaded from: E:\11480076_dexfile_execute.dex */
public abstract class AbstractC6515b extends HarvestableArray {

    /* renamed from: a */
    protected String f16603a;

    /* renamed from: b */
    protected String f16604b;

    /* renamed from: c */
    protected RequestMethodType f16605c;

    /* renamed from: d */
    private String f16606d;

    /* renamed from: b */
    public String m9574b(String str) {
        if (str == null) {
            return null;
        }
        this.f16603a = C6653u.m8726b(str);
        return this.f16603a;
    }

    /* renamed from: i */
    public String m9570i() {
        return this.f16606d;
    }

    /* renamed from: c */
    public void m9573c(String str) {
        this.f16606d = str;
    }

    /* renamed from: j */
    public String m9569j() {
        return this.f16604b;
    }

    /* renamed from: d */
    public void m9572d(String str) {
        this.f16604b = str;
    }

    /* renamed from: k */
    public RequestMethodType m9568k() {
        return this.f16605c;
    }

    /* renamed from: a */
    public void m9575a(RequestMethodType requestMethodType) {
        this.f16605c = requestMethodType;
    }

    /* renamed from: l */
    public String m9567l() {
        return this.f16603a;
    }

    /* renamed from: e */
    protected JsonPrimitive m9571e(String str) {
        if (TextUtils.isEmpty(str) || str.equalsIgnoreCase("null")) {
            return new JsonPrimitive("");
        }
        return new JsonPrimitive(str);
    }
}
