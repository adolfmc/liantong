package com.xiaomi.push;

import org.json.JSONArray;

/* renamed from: com.xiaomi.push.bf */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class C11188bf extends JSONArray implements InterfaceC11187be {

    /* renamed from: a */
    private int f21594a = 2;

    @Override // com.xiaomi.push.InterfaceC11187be
    /* renamed from: a */
    public int mo4732a() {
        return this.f21594a + (length() - 1);
    }

    @Override // org.json.JSONArray
    public JSONArray put(Object obj) {
        if (obj instanceof InterfaceC11187be) {
            this.f21594a += ((InterfaceC11187be) obj).mo4732a();
        }
        return super.put(obj);
    }
}
