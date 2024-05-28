package p000;

import com.crb.jscard.entity.BalanceAndCardNumberEntity;
import com.crb.jscard.entity.CardResult;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.math.BigInteger;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\567196_dexfile_execute.dex.fixout.dex
  synthetic
 */
@NBSInstrumented
/* renamed from: c */
/* loaded from: E:\567196_dexfile_execute.dex */
public class GetBalanceAndCardNumberUtil {
    /* renamed from: a */
    public static String m22195a(String str) {
        String m111a = ApduUtil.m111a(str);
        ArrayList<String> arrayList = new ArrayList();
        arrayList.add(m111a);
        arrayList.add("00B095001E");
        arrayList.add("805C000204");
        ArrayList arrayList2 = new ArrayList();
        for (String str2 : arrayList) {
            CardResult m1869a = SmartCard.m1869a(str2);
            C14231v.m72b("GetBalanceAndCardNumberUtil", m1869a.toString());
            arrayList2.add(m1869a);
        }
        CardResult cardResult = (CardResult) arrayList2.get(1);
        CardResult cardResult2 = (CardResult) arrayList2.get(2);
        if (cardResult.getStatus() == 0 && !cardResult.getRapdu().equals("") && cardResult2.getStatus() == 0 && !cardResult2.getRapdu().equals("")) {
            String substring = cardResult.getRapdu().substring(21, 40);
            String substring2 = cardResult.getRapdu().substring(40, 48);
            String substring3 = cardResult.getRapdu().substring(48, 56);
            int intValue = new BigInteger(cardResult2.getRapdu(), 16).intValue();
            BalanceAndCardNumberEntity balanceAndCardNumberEntity = new BalanceAndCardNumberEntity();
            balanceAndCardNumberEntity.setCode(JSCardConfig.f4e);
            balanceAndCardNumberEntity.setInfo("获取余额卡号成功");
            balanceAndCardNumberEntity.setBalance(String.valueOf(intValue));
            balanceAndCardNumberEntity.setCardNumber(substring);
            balanceAndCardNumberEntity.setStartDate(substring2);
            balanceAndCardNumberEntity.setEndDate(substring3);
            C14231v.m72b("GetBalanceAndCardNumberUtil", balanceAndCardNumberEntity.toString());
            Gson gson = new Gson();
            String json = !(gson instanceof Gson) ? gson.toJson(balanceAndCardNumberEntity) : NBSGsonInstrumentation.toJson(gson, balanceAndCardNumberEntity);
            SmartCard.m1868b();
            return json;
        }
        BalanceAndCardNumberEntity balanceAndCardNumberEntity2 = new BalanceAndCardNumberEntity();
        balanceAndCardNumberEntity2.setCode(JSCardConfig.f5f);
        balanceAndCardNumberEntity2.setInfo("获取余额卡号失败");
        C14231v.m72b("GetBalanceAndCardNumberUtil", balanceAndCardNumberEntity2.toString());
        Gson gson2 = new Gson();
        String json2 = !(gson2 instanceof Gson) ? gson2.toJson(balanceAndCardNumberEntity2) : NBSGsonInstrumentation.toJson(gson2, balanceAndCardNumberEntity2);
        SmartCard.m1868b();
        return json2;
    }
}
