package p000;

import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.SetDefaultCardEntity;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
  synthetic
 */
@NBSInstrumented
/* renamed from: g */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class SetDefaultCardUtil {
    /* renamed from: a */
    public static String m2027a(String str, String str2) {
        if (StringUtil.m22227b(str)) {
            ArrayList<String> arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            arrayList.add("00A4040009A00000015143525300");
            String m22228a = StringUtil.m22228a(str2);
            String m22228a2 = StringUtil.m22228a("4F" + m22228a + str2);
            arrayList.add("80F00101" + m22228a2 + "4F" + m22228a + str2);
            for (String str3 : arrayList) {
                C14231v.m72b("SetDefaultCardUtil", str3);
                CardResult m1869a = SmartCard.m1869a(str3);
                C14231v.m72b("SetDefaultCardUtil", m1869a.toString());
                arrayList2.add(m1869a);
            }
            SetDefaultCardEntity setDefaultCardEntity = new SetDefaultCardEntity();
            C14231v.m72b("SetDefaultCardUtil", ((CardResult) arrayList2.get(1)).toString());
            if (((CardResult) arrayList2.get(1)).getStatus() == 0 && ((CardResult) arrayList2.get(1)).getSw().equals("9000")) {
                setDefaultCardEntity.setCode(JSCardConfig.f4e);
                setDefaultCardEntity.setInfo("设置默认卡成功");
            } else {
                setDefaultCardEntity.setCode(JSCardConfig.f5f);
                setDefaultCardEntity.setInfo("设置默认卡失败");
            }
            SmartCard.m1868b();
            Gson gson = new Gson();
            C14231v.m72b("SetDefaultCardUtil", !(gson instanceof Gson) ? gson.toJson(setDefaultCardEntity) : NBSGsonInstrumentation.toJson(gson, setDefaultCardEntity));
            Gson gson2 = new Gson();
            return !(gson2 instanceof Gson) ? gson2.toJson(setDefaultCardEntity) : NBSGsonInstrumentation.toJson(gson2, setDefaultCardEntity);
        }
        ArrayList<String> arrayList3 = new ArrayList();
        ArrayList arrayList4 = new ArrayList();
        arrayList3.add("00A4040009A00000015143525300");
        String m22228a3 = StringUtil.m22228a(str);
        String m22228a4 = StringUtil.m22228a("4F" + m22228a3 + str);
        arrayList3.add("80F00100" + m22228a4 + "4F" + m22228a3 + str);
        String m22228a5 = StringUtil.m22228a(str2);
        StringBuilder sb = new StringBuilder();
        sb.append("4F");
        sb.append(m22228a5);
        sb.append(str2);
        String m22228a6 = StringUtil.m22228a(sb.toString());
        arrayList3.add("80F00101" + m22228a6 + "4F" + m22228a5 + str2);
        for (String str4 : arrayList3) {
            C14231v.m72b("SetDefaultCardUtil------1", str4);
            CardResult m1869a2 = SmartCard.m1869a(str4);
            C14231v.m72b("SetDefaultCardUtil------2", m1869a2.toString());
            arrayList4.add(m1869a2);
        }
        SmartCard.m1868b();
        if (((CardResult) arrayList4.get(2)).getSw().equals("9000")) {
            SetDefaultCardEntity setDefaultCardEntity2 = new SetDefaultCardEntity();
            setDefaultCardEntity2.setCode(JSCardConfig.f4e);
            setDefaultCardEntity2.setInfo("设置默认卡成功");
            Gson gson3 = new Gson();
            C14231v.m72b("SetDefaultCardUtil------8", !(gson3 instanceof Gson) ? gson3.toJson(setDefaultCardEntity2) : NBSGsonInstrumentation.toJson(gson3, setDefaultCardEntity2));
            Gson gson4 = new Gson();
            return !(gson4 instanceof Gson) ? gson4.toJson(setDefaultCardEntity2) : NBSGsonInstrumentation.toJson(gson4, setDefaultCardEntity2);
        }
        SetDefaultCardEntity setDefaultCardEntity3 = new SetDefaultCardEntity();
        setDefaultCardEntity3.setCode(JSCardConfig.f5f);
        setDefaultCardEntity3.setInfo("设置默认卡失败");
        Gson gson5 = new Gson();
        C14231v.m72b("SetDefaultCardUtil------8", !(gson5 instanceof Gson) ? gson5.toJson(setDefaultCardEntity3) : NBSGsonInstrumentation.toJson(gson5, setDefaultCardEntity3));
        Gson gson6 = new Gson();
        return !(gson6 instanceof Gson) ? gson6.toJson(setDefaultCardEntity3) : NBSGsonInstrumentation.toJson(gson6, setDefaultCardEntity3);
    }
}
