package p000;

import android.content.Context;
import android.os.Build;
import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.SimCardAndDeviceInfoEntity;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
  synthetic
 */
@NBSInstrumented
/* renamed from: h */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class SimCardAndDeviceInfoUtil {
    /* renamed from: a */
    public static String m2011a(Context context) {
        SimCardAndDeviceInfoEntity simCardAndDeviceInfoEntity = new SimCardAndDeviceInfoEntity();
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add("00A4040010A0000000871002FF86FFFF89FFFFFFFF");
        arrayList.add("01A4000C023F00");
        arrayList.add("01A4000C022FE2");
        arrayList.add("00B000000A");
        ArrayList arrayList2 = new ArrayList();
        for (String str : arrayList) {
            arrayList2.add(SmartCard.m1869a(str));
        }
        CardResult cardResult = (CardResult) arrayList2.get(arrayList2.size() - 1);
        if (cardResult.getStatus() == 0) {
            String m7a = ReverseUtil.m7a(cardResult.getRapdu());
            JSCardConfig.f3d = m7a;
            simCardAndDeviceInfoEntity.setIccid(m7a);
        }
        if (NfcUtil.m20a(context)) {
            simCardAndDeviceInfoEntity.setHasNfc(true);
        } else {
            simCardAndDeviceInfoEntity.setHasNfc(false);
        }
        if (NfcUtil.m19b(context)) {
            simCardAndDeviceInfoEntity.setEnableNfc(true);
        } else {
            simCardAndDeviceInfoEntity.setEnableNfc(false);
        }
        simCardAndDeviceInfoEntity.setPhoneNumber(JSCardConfig.f2c);
        simCardAndDeviceInfoEntity.setDevice_vendor(Build.BRAND);
        simCardAndDeviceInfoEntity.setDevice_model(Build.DEVICE);
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(simCardAndDeviceInfoEntity) : NBSGsonInstrumentation.toJson(gson, simCardAndDeviceInfoEntity);
        C14231v.m72b("SimCardAndDeviceInfoUtil", json);
        SmartCard.m1868b();
        return json;
    }
}
