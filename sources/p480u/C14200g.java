package p480u;

import android.os.Build;
import com.unicom.pay.C10546a;
import com.unicom.pay.common.bean.WPResult;
import com.unicom.pay.common.callback.DataCallback;
import java.util.HashMap;
import p478t.AbstractC14156a;
import p482w.C14255c;
import p482w.C14262f;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: u.g */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class C14200g extends AbstractC14156a {
    /* renamed from: a */
    public static void m76a(C14200g c14200g, String str, String str2, DataCallback dataCallback) {
        c14200g.getClass();
        WPResult wPResult = new WPResult();
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("authorType", str);
        hashMap.put("deviceId", C10546a.C10576i.f20125a.f20061k);
        hashMap.put("uafResponse", str2);
        hashMap.put("operaType", "00");
        hashMap.put("deviceName", Build.MANUFACTURER);
        C14255c.C14256a.f27777a.m30a(C14262f.f27801N, hashMap, new C14196e(c14200g, c14200g, wPResult, dataCallback, str));
    }
}
