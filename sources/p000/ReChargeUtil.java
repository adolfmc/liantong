package p000;

import com.crb.jscard.HomeActivity;
import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.ReChargeEntity;
import com.crb.jscard.http.bean.ApplyResp;
import com.crb.jscard.http.bean.Command;
import com.crb.jscard.p192js.NativeMethodCallJsUtil;
import com.google.gson.Gson;
import com.networkbench.agent.impl.instrumentation.NBSGsonInstrumentation;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
  synthetic
 */
@NBSInstrumented
/* renamed from: f */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ReChargeUtil {

    /* renamed from: a */
    public static String f24285a;

    /* renamed from: b */
    public static String f24286b;

    /* renamed from: c */
    public static HttpUtils f24287c;

    /* renamed from: d */
    public static int f24288d;

    /* renamed from: e */
    public static Callback<ApplyResp> f24289e = new C11923a();

    /* compiled from: ReChargeUtil.java */
    /* renamed from: f$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C11923a implements Callback<ApplyResp> {
        @Override // retrofit2.Callback
        public void onFailure(Call<ApplyResp> call, Throwable th) {
            String str = JSCardConfig.f5f;
            ReChargeUtil.m2050a(str, "充值操作服务器异常" + th.getMessage());
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ApplyResp> call, Response<ApplyResp> response) {
            if (response.isSuccessful()) {
                if (response.body().getCode().equals("0000")) {
                    ReChargeUtil.m2051a(response.body());
                    return;
                }
                String str = JSCardConfig.f5f;
                ReChargeUtil.m2050a(str, "充值操作异常" + response.code() + ",info:" + response.body().getInfo());
                return;
            }
            String str2 = JSCardConfig.f5f;
            ReChargeUtil.m2050a(str2, "充值操作异常" + response.code() + ",info:" + response.body().getInfo());
        }
    }

    /* renamed from: a */
    public static void m2049a(HttpUtils httpUtils, String str, String str2) {
        f24285a = str;
        f24286b = str2;
        f24287c = httpUtils;
        f24288d = 1;
        m2052a(1, JSCardConfig.f3d, f24285a, f24286b, null, null, null);
    }

    /* renamed from: a */
    public static void m2052a(int i, String str, String str2, String str3, String str4, String str5, List<Command> list) {
        f24287c.m158a(i, str, str2, str3, str4, str5, list, f24289e);
    }

    /* renamed from: a */
    public static void m2051a(ApplyResp applyResp) {
        if (!applyResp.getStep().equals("EOF")) {
            String session = applyResp.getSession();
            String step = applyResp.getStep();
            ArrayList arrayList = new ArrayList();
            Iterator<Command> it = applyResp.getCommands().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Command next = it.next();
                CardResult m1869a = SmartCard.m1869a(next.getCommand());
                if (m1869a.getStatus() == 0) {
                    String sw = m1869a.getSw();
                    if (Pattern.matches(next.getChecker(), sw)) {
                        Command command = new Command();
                        command.setIndex(next.getIndex());
                        command.setChecker(sw);
                        command.setCommand(next.getCommand());
                        command.setResult(m1869a.getRapdu() + sw);
                        arrayList.add(command);
                    } else {
                        Command command2 = new Command();
                        command2.setIndex(next.getIndex());
                        command2.setChecker(sw);
                        command2.setCommand(next.getCommand());
                        if (m1869a.getRapdu() != null) {
                            command2.setResult(m1869a.getRapdu() + sw);
                        } else {
                            command2.setResult("" + sw);
                        }
                        arrayList.add(command2);
                    }
                }
            }
            m2052a(f24288d, JSCardConfig.f3d, f24285a, f24286b, session, step, arrayList);
            return;
        }
        m2050a(JSCardConfig.f4e, "充值操作完成");
    }

    /* renamed from: a */
    public static void m2050a(String str, String str2) {
        SmartCard.m1868b();
        ReChargeEntity reChargeEntity = new ReChargeEntity();
        reChargeEntity.setCode(str);
        reChargeEntity.setInfo(str2);
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(reChargeEntity) : NBSGsonInstrumentation.toJson(gson, reChargeEntity);
        C14231v.m72b("ReChargeUtil", json);
        new HomeActivity();
        NativeMethodCallJsUtil.refreshWebView(HomeActivity.f9727a, "rechargeResult", json);
    }
}
