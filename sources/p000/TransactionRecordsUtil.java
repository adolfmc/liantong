package p000;

import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.TradeInfo;
import com.crb.jscard.entity.TransactionRecordsEntity;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.math.BigInteger;
import java.util.ArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
  synthetic
 */
@NBSInstrumented
/* renamed from: i */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class TransactionRecordsUtil {
    /* renamed from: a */
    public static String m1976a(String str) {
        CardResult m1869a = SmartCard.m1869a(ApduUtil.m111a(str));
        C14231v.m72b("TransactionRecordsUtil", "CardResult:" + m1869a);
        if (m1869a.getStatus() == 0 && m1869a.getSw().equalsIgnoreCase("9000")) {
            ArrayList arrayList = new ArrayList();
            for (int i = 1; i < 11; i++) {
                String str2 = "00B20" + Integer.toHexString(i) + "C417";
                C14231v.m72b("TransactionRecordsUtil", "查询指令：" + str2);
                CardResult m1869a2 = SmartCard.m1869a(str2);
                C14231v.m72b("TransactionRecordsUtil", "查询结果-CardResult:" + m1869a2);
                if (m1869a2.getStatus() == 0) {
                    if (m1869a2.getRapdu().equals("") || !m1869a2.getSw().equalsIgnoreCase("9000") || m1869a2.getRapdu().equalsIgnoreCase("0000000000000000000000000000000000000000000000")) {
                        break;
                    }
                    TradeInfo m1975b = m1975b(m1869a2.getRapdu());
                    C14231v.m72b("TransactionRecordsUtil", "tradeInfo:" + m1975b.toString());
                    arrayList.add(m1975b);
                }
            }
            TransactionRecordsEntity transactionRecordsEntity = new TransactionRecordsEntity();
            transactionRecordsEntity.setCode(JSCardConfig.f4e);
            transactionRecordsEntity.setInfo("获取交易记录成功");
            transactionRecordsEntity.setTradelist(arrayList);
            Gson gson = new Gson();
            String json = !(gson instanceof Gson) ? gson.toJson(transactionRecordsEntity) : NBSGsonInstrumentation.toJson(gson, transactionRecordsEntity);
            C14231v.m72b("TransactionRecordsUtil", json);
            SmartCard.m1868b();
            return json;
        }
        return null;
    }

    /* renamed from: b */
    public static TradeInfo m1975b(String str) {
        String substring = str.substring(0, 4);
        int intValue = new BigInteger(str.substring(10, 18), 16).intValue();
        String substring2 = str.substring(18, 20);
        String substring3 = str.substring(32, 40);
        String substring4 = str.substring(40, 46);
        String substring5 = str.substring(20, 32);
        TradeInfo tradeInfo = new TradeInfo();
        tradeInfo.setTradeNo(substring);
        tradeInfo.setTradeMoney(intValue);
        tradeInfo.setTradeDate(substring3);
        tradeInfo.setTradeTime(substring4);
        tradeInfo.setTradeType(substring2);
        tradeInfo.setPosId(substring5);
        return tradeInfo;
    }
}
