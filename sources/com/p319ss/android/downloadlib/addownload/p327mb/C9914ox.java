package com.p319ss.android.downloadlib.addownload.p327mb;

import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSJSONObjectInstrumentation;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.addownload.model.C9921mb;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import org.json.JSONObject;

@NBSInstrumented
/* renamed from: com.ss.android.downloadlib.addownload.mb.ox */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
class C9914ox {
    /* JADX INFO: Access modifiers changed from: package-private */
    @NonNull
    /* renamed from: mb */
    public CopyOnWriteArrayList<C9921mb> m7503mb(String str, String str2) {
        CopyOnWriteArrayList<C9921mb> copyOnWriteArrayList = new CopyOnWriteArrayList<>();
        try {
            String string = C9940x.getContext().getSharedPreferences(str, 0).getString(str2, "");
            if (!TextUtils.isEmpty(string)) {
                JSONObject jSONObject = new JSONObject(string);
                Iterator<String> keys = jSONObject.keys();
                while (keys.hasNext()) {
                    C9921mb m7458mb = C9921mb.m7458mb(jSONObject.optJSONObject(keys.next()));
                    if (m7458mb != null) {
                        copyOnWriteArrayList.add(m7458mb);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return copyOnWriteArrayList;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: mb */
    public void m7502mb(String str, String str2, CopyOnWriteArrayList<C9921mb> copyOnWriteArrayList) {
        if (copyOnWriteArrayList == null) {
            return;
        }
        JSONObject jSONObject = new JSONObject();
        try {
            Iterator<C9921mb> it = copyOnWriteArrayList.iterator();
            while (it.hasNext()) {
                C9921mb next = it.next();
                if (next != null) {
                    jSONObject.put(String.valueOf(next.f19119ox), next.m7459mb());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        C9940x.getContext().getSharedPreferences(str, 0).edit().putString(str2, !(jSONObject instanceof JSONObject) ? jSONObject.toString() : NBSJSONObjectInstrumentation.toString(jSONObject)).apply();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* renamed from: ox */
    public void m7501ox(String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        C9940x.getContext().getSharedPreferences(str, 0).edit().putString(str2, "").apply();
    }
}
