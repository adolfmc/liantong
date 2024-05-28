package com.alipay.security.mobile.module.p114d;

import com.alipay.security.mobile.module.http.p115v2.InterfaceC2109a;
import com.alipay.security.mobile.module.p110a.C2085b;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import org.json.JSONObject;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@NBSInstrumented
/* renamed from: com.alipay.security.mobile.module.d.b */
/* loaded from: E:\10201592_dexfile_execute.dex */
public final class C2097b {

    /* renamed from: a */
    private File f3999a;

    /* renamed from: b */
    private InterfaceC2109a f4000b;

    public C2097b(String str, InterfaceC2109a interfaceC2109a) {
        this.f3999a = null;
        this.f4000b = null;
        this.f3999a = new File(str);
        this.f4000b = interfaceC2109a;
    }

    /* renamed from: a */
    private static String m20482a(String str) {
        JSONObject jSONObject = new JSONObject();
        try {
            jSONObject.put("type", "id");
            jSONObject.put("error", str);
            return !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject);
        } catch (Exception unused) {
            return "";
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: b */
    public synchronized void m20481b() {
        if (this.f3999a == null) {
            return;
        }
        if (this.f3999a.exists() && this.f3999a.isDirectory() && this.f3999a.list().length != 0) {
            ArrayList arrayList = new ArrayList();
            for (String str : this.f3999a.list()) {
                arrayList.add(str);
            }
            Collections.sort(arrayList);
            String str2 = (String) arrayList.get(arrayList.size() - 1);
            int size = arrayList.size();
            if (str2.equals(new SimpleDateFormat("yyyyMMdd").format(Calendar.getInstance().getTime()) + ".log")) {
                if (arrayList.size() < 2) {
                    return;
                }
                str2 = (String) arrayList.get(arrayList.size() - 2);
                size--;
            }
            if (!this.f4000b.mo20460a(m20482a(C2085b.m20558a(this.f3999a.getAbsolutePath(), str2)))) {
                size--;
            }
            for (int i = 0; i < size; i++) {
                new File(this.f3999a, (String) arrayList.get(i)).delete();
            }
        }
    }

    /* renamed from: a */
    public final void m20484a() {
        new Thread(new RunnableC2098c(this)).start();
    }
}
