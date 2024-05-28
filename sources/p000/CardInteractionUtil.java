package p000;

import com.crb.jscard.HomeActivity;
import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.TransmitAllEntity;
import com.crb.jscard.entity.TransmitEntity;
import com.crb.jscard.http.bean.Apdu;
import com.crb.jscard.p192js.NativeMethodCallJsUtil;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
  synthetic
 */
@NBSInstrumented
/* renamed from: b */
/* loaded from: E:\567196_dexfile_execute.dex */
public class CardInteractionUtil {
    /* renamed from: a */
    public static String m22199a(String str) {
        CardResult m1869a = SmartCard.m1869a(ApduUtil.m111a(str));
        Gson gson = new Gson();
        return !(gson instanceof Gson) ? gson.toJson(m1869a) : NBSGsonInstrumentation.toJson(gson, m1869a);
    }

    /* renamed from: b */
    public static void m22198b(String str) {
        new HomeActivity();
        NativeMethodCallJsUtil.refreshWebView(HomeActivity.f9727a, "transmitResult", str);
    }

    /* renamed from: c */
    public static void m22197c(String str) {
        Gson gson = new Gson();
        List<Apdu> list = ((TransmitEntity) (!(gson instanceof Gson) ? gson.fromJson(str, (Class<Object>) TransmitEntity.class) : NBSGsonInstrumentation.fromJson(gson, str, (Class<Object>) TransmitEntity.class))).getrApduList();
        ArrayList arrayList = new ArrayList();
        for (Apdu apdu : list) {
            CardResult m1869a = SmartCard.m1869a(apdu.getApdu());
            if (m1869a.getStatus() == 0) {
                Apdu apdu2 = new Apdu();
                apdu2.setIndex(apdu.getIndex());
                apdu2.setApdu(m1869a.getRapdu());
                apdu2.setSw(m1869a.getSw());
                arrayList.add(apdu2);
            } else {
                Apdu apdu3 = new Apdu();
                apdu3.setIndex(apdu.getIndex());
                apdu3.setApdu(m1869a.getRapdu());
                apdu3.setSw(m1869a.getSw());
                arrayList.add(apdu3);
                TransmitAllEntity transmitAllEntity = new TransmitAllEntity();
                transmitAllEntity.setCode(JSCardConfig.f4e);
                transmitAllEntity.setInfo("指令透传中断");
                transmitAllEntity.setcApduList(arrayList);
                Gson gson2 = new Gson();
                m22198b(!(gson2 instanceof Gson) ? gson2.toJson(transmitAllEntity) : NBSGsonInstrumentation.toJson(gson2, transmitAllEntity));
                return;
            }
        }
        TransmitAllEntity transmitAllEntity2 = new TransmitAllEntity();
        transmitAllEntity2.setCode(JSCardConfig.f4e);
        transmitAllEntity2.setInfo("指令透传成功");
        transmitAllEntity2.setcApduList(arrayList);
        Gson gson3 = new Gson();
        m22198b(!(gson3 instanceof Gson) ? gson3.toJson(transmitAllEntity2) : NBSGsonInstrumentation.toJson(gson3, transmitAllEntity2));
    }

    /* renamed from: a */
    public static void m22200a() {
        SmartCard.m1868b();
    }
}
