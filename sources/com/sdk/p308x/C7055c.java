package com.sdk.p308x;

import android.content.Context;
import android.util.Log;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.sdk.base.module.manager.SDKManager;
import com.sdk.p286b.C6964a;
import com.sdk.p289e.InterfaceC6991a;
import com.sdk.p294j.C7008a;
import com.sdk.p300p.C7028b;
import com.sdk.p302r.C7037a;
import com.sdk.p303s.C7038a;
import com.sdk.p304t.C7039a;
import org.json.JSONObject;

/* JADX INFO: Add missing generic type declarations: [T] */
@NBSInstrumented
/* renamed from: com.sdk.x.c */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7055c<T> implements InterfaceC6991a<T> {

    /* renamed from: a */
    public final /* synthetic */ int f18259a;

    /* renamed from: b */
    public final /* synthetic */ C7048a f18260b;

    public C7055c(C7048a c7048a, int i) {
        this.f18260b = c7048a;
        this.f18259a = i;
    }

    @Override // com.sdk.p289e.InterfaceC6991a
    /* renamed from: a */
    public void mo8108a(int i, int i2, String str) {
        this.f18260b.m8112a(i, i2, str);
        String str2 = C7048a.f18237h;
        Log.d(str2, "onFailure: " + i);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r10v0, types: [T, java.lang.Object] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v2, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v3, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.String] */
    @Override // com.sdk.p289e.InterfaceC6991a
    public void onSuccess(int i, String str, int i2, T t, String str2) {
        T t2;
        String obj;
        C7028b.m8140b(this.f18260b.f18243b);
        if (i == 0) {
            try {
                obj = t.toString();
                Context context = this.f18260b.f18243b;
                t = (T) C7038a.m8128a(String.valueOf((Object) t));
            } catch (Exception unused) {
            }
            if (t == 0) {
                this.f18260b.m8112a(1, 302001, "SDK解密异常");
                return;
            }
            if (SDKManager.useCache()) {
                Context context2 = this.f18260b.f18243b;
                int i3 = this.f18259a;
                String m8194a = C6964a.m8194a(str, obj, str2, C7039a.f18205g);
                if (C7037a.m8129b(m8194a).booleanValue()) {
                    String m8197a = C6964a.m8197a(i3, "CUCC");
                    if (C7037a.m8129b(m8197a).booleanValue()) {
                        C7008a.m8154b(context2, m8197a, m8194a);
                    }
                }
            }
            JSONObject jSONObject = new JSONObject((String) t);
            if (this.f18259a == 1) {
                jSONObject.remove("fakeMobile");
                t = !(jSONObject instanceof JSONObject) ? (T) jSONObject.toString() : (T) NBSJSONObjectInstrumentation.toString(jSONObject);
            }
            t2 = t;
        } else {
            t2 = t;
        }
        this.f18260b.m8111a(i, str, i2, t2, str2);
        String str3 = C7048a.f18237h;
        Log.d(str3, "getAuthoriseCode: " + i);
    }
}
