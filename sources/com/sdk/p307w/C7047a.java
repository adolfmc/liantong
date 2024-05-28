package com.sdk.p307w;

import android.content.Context;
import android.util.Log;
import com.sdk.base.framework.bean.DataInfo;
import com.sdk.base.framework.utils.log.LogUtils;
import com.sdk.base.framework.utils.log.MobileLogManager;
import com.sdk.base.module.config.BaseConfig;
import com.sdk.p285a.C6958c;
import com.sdk.p285a.C6960d;
import com.sdk.p285a.C6962e;
import com.sdk.p288d.AbstractC6979c;
import com.sdk.p288d.RunnableC6990g;
import com.sdk.p289e.InterfaceC6991a;
import com.sdk.p291g.C7004a;
import com.sdk.p291g.C7005b;
import com.sdk.p293i.C7007a;
import com.sdk.p298n.C7014a;
import com.sdk.p300p.C7032f;
import com.sdk.p300p.C7033g;
import com.sdk.p302r.C7037a;
import com.sdk.p304t.C7039a;
import com.sdk.p309y.C7056a;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;
import java.util.concurrent.Executor;

/* renamed from: com.sdk.w.a */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C7047a {
    /* JADX WARN: Type inference failed for: r4v8, types: [Params[], java.lang.Object[]] */
    /* renamed from: a */
    public <T> C6958c<T> m8115a(Context context, int i, List<String> list, InterfaceC6991a<T> interfaceC6991a) {
        boolean z;
        StringBuffer stringBuffer;
        C7056a c7056a = new C7056a(context, list, interfaceC6991a);
        String str = "/dro/netm/v1.0/qc";
        DataInfo dataInfo = new DataInfo();
        StringBuffer stringBuffer2 = new StringBuffer();
        StringBuffer stringBuffer3 = new StringBuffer();
        if (list.size() != 0) {
            for (int i2 = 0; i2 < list.size(); i2++) {
                String str2 = list.get(i2);
                String substring = str2.substring(0, 3);
                try {
                    byte[] address = InetAddress.getByName(str2).getAddress();
                    if (str2.length() != 0) {
                        if (address.length == 4) {
                            if (!substring.equals("127") && !substring.equals("192")) {
                                stringBuffer2.append(str2);
                                stringBuffer = stringBuffer2;
                            }
                        } else if (!str2.contains("%") && !"::1".equals(str2)) {
                            stringBuffer3.append(str2);
                            stringBuffer = stringBuffer3;
                        }
                        stringBuffer.append("-");
                    }
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
            }
            if (stringBuffer2.length() != 0 && '-' == stringBuffer2.charAt(stringBuffer2.length() - 1)) {
                StringBuffer deleteCharAt = stringBuffer2.deleteCharAt(stringBuffer2.length() - 1);
                int min = Math.min(stringBuffer3.length(), 5);
                for (int i3 = 0; i3 < min; i3++) {
                    dataInfo.putData("privateIp", deleteCharAt);
                }
            }
            if (stringBuffer3.length() != 0 && '-' == stringBuffer3.charAt(stringBuffer3.length() - 1)) {
                StringBuffer deleteCharAt2 = stringBuffer3.deleteCharAt(stringBuffer3.length() - 1);
                int min2 = Math.min(stringBuffer3.length(), 5);
                for (int i4 = 0; i4 < min2; i4++) {
                    dataInfo.putData("privateIp_v6", deleteCharAt2);
                }
            }
        }
        dataInfo.putData("serviceType", Integer.valueOf(i));
        dataInfo.putData("newVersion", "11");
        if (C7039a.f18204f) {
            str = "/dro/netm/v2.0/qc";
            int m8146a = C7014a.m8146a(context);
            dataInfo.putData("operator", String.valueOf((m8146a > 3 || m8146a < 1) ? 0 : 0));
        }
        LogUtils.d_yl(C7056a.f18261l, "public HttpHandler<T> getAuthoriseCodeCucc : dataInfo \n" + dataInfo, 3);
        String str3 = c7056a.f18151g;
        C7004a c7004a = new C7004a(c7056a);
        C6958c<T> c6958c = null;
        if (C7037a.m8130a(str3).booleanValue()) {
            c7056a.m8167a(1, 101008, "未检测到域名");
            return null;
        }
        LogUtils.d_yl(C7005b.f18143j, "public HttpHandler<T> sendRequest() 开始", 0);
        try {
            TreeMap<String, Object> treeMap = new TreeMap<>();
            String m8161a = C7007a.m8161a(c7056a.f18150f, BaseConfig.apk);
            String[] strArr = {m8161a, C7039a.f18200b};
            int i5 = 0;
            while (true) {
                if (i5 >= 2) {
                    z = false;
                    break;
                }
                String str4 = strArr[i5];
                if ((str4 == null || str4.length() < 1) && C7037a.m8130a(str4).booleanValue()) {
                    z = true;
                    break;
                }
                i5++;
            }
            if (z) {
                c7056a.m8167a(1, 101004, "ApiKey或PublicKey不能为空");
                return null;
            }
            String str5 = C7039a.f18205g;
            if (C7037a.m8130a(str5).booleanValue()) {
                str5 = (m8161a + m8161a).substring(m8161a.length() - 16, m8161a.length() + 16);
                C7039a.f18205g = str5;
            }
            String substring2 = str5.substring(0, 16);
            String substring3 = str5.substring(16, 32);
            C6958c.f17992C.clear();
            C6958c.f17992C.put("InitTime", Long.valueOf(System.currentTimeMillis()));
            C6958c.f17992C.put("ifProtal", 0);
            String m8165a = c7056a.m8165a(dataInfo, substring2, substring3);
            try {
                C7032f m8136a = C7032f.m8136a();
                String mo8133a = m8136a.f18193a.mo8133a(m8136a.f18194b, substring2 + substring3);
                treeMap.put("apiKey", m8161a);
                treeMap.put("params", m8165a);
                treeMap.put("paramsKey", mo8133a);
                treeMap.put("l", Long.valueOf(System.currentTimeMillis()));
                String m8135a = C7033g.m8135a(m8161a, str, treeMap);
                HashMap<String, Object> hashMap = new HashMap<>(16);
                if (C7037a.m8129b(m8135a).booleanValue()) {
                    treeMap.put("sign", m8135a);
                    treeMap.put("sign_Type", C7039a.f18202d);
                    hashMap.put("sign", m8135a);
                    hashMap.put("api-protocol", "1.1");
                }
                C6962e c6962e = new C6962e();
                if (C7037a.m8129b("POST").booleanValue()) {
                    c6962e.f18040a = "POST";
                }
                c6962e.f18041b = str3 + str;
                c6962e.f18046g = c7004a;
                c6962e.f18045f = 0;
                c6962e.f18042c = treeMap;
                c6962e.f18043d = null;
                c6962e.f18044e = hashMap;
                C7014a.EnumC7021c enumC7021c = C7014a.EnumC7021c.f18177b;
                if (C7039a.f18204f) {
                    int intValue = Integer.valueOf((String) dataInfo.get("operator")).intValue();
                    String str6 = C7014a.f18170a;
                    if (intValue == 1) {
                        enumC7021c = C7014a.EnumC7021c.f18176a;
                    } else if (intValue == 2) {
                        enumC7021c = C7014a.EnumC7021c.f18178c;
                    }
                }
                c6962e.f18047h = enumC7021c;
                C6960d c6960d = new C6960d(c7056a.f18150f, c6962e);
                C6958c<T> c6958c2 = new C6958c<>(c6960d);
                try {
                    ?? r4 = {c6960d};
                    Executor executor = AbstractC6979c.f18098h;
                    if (c6958c2.f18103e) {
                        throw new IllegalStateException("Cannot execute task: the task is already executed.");
                    }
                    c6958c2.f18103e = true;
                    c6958c2.f18099a.f18109a = r4;
                    executor.execute(new RunnableC6990g(null, c6958c2.f18100b));
                    return c6958c2;
                } catch (Exception e2) {
                    e = e2;
                    c6958c = c6958c2;
                    MobileLogManager.status302002(e.toString());
                    c7056a.m8167a(1, 302002, "网络访问异常:" + e.getMessage());
                    LogUtils.m8186e(C7005b.f18143j + "BaseProtocol sendRequest", e.toString() + "，" + e.getMessage(), Boolean.valueOf(C7005b.f18144k));
                    return c6958c;
                }
            } catch (Exception e3) {
                c7056a.m8167a(1, 101006, "公钥出错");
                LogUtils.m8186e(C7005b.f18143j, "公钥出错：" + e3, Boolean.valueOf(C7005b.f18144k));
                Log.d("ZJW_LOG", "公钥出错: " + e3.getMessage());
                return null;
            }
        } catch (Exception e4) {
            e = e4;
        }
    }
}
