package p000;

import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.SimCardAndDeviceInfoEntity;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
  synthetic
 */
/* renamed from: d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class GetDefaultCardUtil {
    /* renamed from: a */
    public static void m2119a() {
        new SimCardAndDeviceInfoEntity();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("00A4040009A00000015143525300");
        arrayList.add("80F240000F4F08A0000006320101055C039F704F00");
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            arrayList2.add(SmartCard.m1869a(str));
        }
        SmartCard.m1868b();
        C14231v.m72b("GetDefaultCardUtil", ((CardResult) arrayList2.get(arrayList2.size() - 1)).toString());
    }
}
