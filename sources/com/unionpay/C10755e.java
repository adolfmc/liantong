package com.unionpay;

import java.util.Comparator;
import org.json.JSONObject;

/* renamed from: com.unionpay.e */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
final class C10755e implements Comparator {

    /* renamed from: a */
    String f20684a;

    /* JADX INFO: Access modifiers changed from: package-private */
    public C10755e(String str) {
        this.f20684a = "";
        this.f20684a = str;
    }

    @Override // java.util.Comparator
    public final /* synthetic */ int compare(Object obj, Object obj2) {
        int i = (((JSONObject) obj).optLong(this.f20684a) > ((JSONObject) obj2).optLong(this.f20684a) ? 1 : (((JSONObject) obj).optLong(this.f20684a) == ((JSONObject) obj2).optLong(this.f20684a) ? 0 : -1));
        if (i < 0) {
            return -1;
        }
        return i > 0 ? 1 : 0;
    }
}
