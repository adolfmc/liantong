package p000;

import com.crb.jscard.HomeActivity;
import com.crb.jscard.entity.CardResult;
import com.crb.jscard.entity.InstallAndPersonalEntity;
import com.crb.jscard.http.bean.Apdu;
import com.crb.jscard.http.bean.ApplicationOperationResp;
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
/* renamed from: e */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class InstallAndPersonalUtil {

    /* renamed from: a */
    public static String f24091a;

    /* renamed from: b */
    public static String f24092b;

    /* renamed from: c */
    public static String f24093c;

    /* renamed from: d */
    public static String f24094d;

    /* renamed from: e */
    public static HttpUtils f24095e;

    /* renamed from: f */
    public static String f24096f;

    /* renamed from: g */
    public static int f24097g;

    /* renamed from: h */
    public static Callback<ApplicationOperationResp> f24098h = new C11844a();

    /* renamed from: i */
    public static Callback<ApplyResp> f24099i = new C11845b();

    /* compiled from: InstallAndPersonalUtil.java */
    /* renamed from: e$a */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C11844a implements Callback<ApplicationOperationResp> {
        @Override // retrofit2.Callback
        public void onFailure(Call<ApplicationOperationResp> call, Throwable th) {
            String str = JSCardConfig.f5f;
            InstallAndPersonalUtil.m2074a(str, "应用下载安装，网络异常" + th.getMessage());
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ApplicationOperationResp> call, Response<ApplicationOperationResp> response) {
            if (response.isSuccessful()) {
                if (response.body().getCode().equals("0000")) {
                    InstallAndPersonalUtil.m2077a(response.body());
                    return;
                }
                String str = JSCardConfig.f5f;
                InstallAndPersonalUtil.m2074a(str, "应用下载安装，服务器返回异常，code：" + response.code() + ",info:" + response.body().getInfo());
                return;
            }
            String str2 = JSCardConfig.f5f;
            InstallAndPersonalUtil.m2074a(str2, "应用下载安装，服务器返回异常，code：" + response.code());
        }
    }

    /* compiled from: InstallAndPersonalUtil.java */
    /* renamed from: e$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public static class C11845b implements Callback<ApplyResp> {
        @Override // retrofit2.Callback
        public void onFailure(Call<ApplyResp> call, Throwable th) {
            String str = JSCardConfig.f5f;
            InstallAndPersonalUtil.m2074a(str, "个人化，网络异常" + th.getMessage());
        }

        @Override // retrofit2.Callback
        public void onResponse(Call<ApplyResp> call, Response<ApplyResp> response) {
            if (response.isSuccessful()) {
                if (response.body().getCode().equals("0000")) {
                    InstallAndPersonalUtil.m2076a(response.body());
                    return;
                }
                String str = JSCardConfig.f5f;
                InstallAndPersonalUtil.m2074a(str, "个人化，服务器返回异常，code：" + response.code() + ",info:" + response.body().getInfo());
                return;
            }
            String str2 = JSCardConfig.f5f;
            InstallAndPersonalUtil.m2074a(str2, "个人化，服务器返回异常，code：" + response.code());
        }
    }

    /* renamed from: a */
    public static void m2073a(HttpUtils httpUtils, String str, String str2, String str3) {
        C14231v.m72b("InstallAndPersonalUtils", "aid:" + str + "----cityid:" + str2 + "---order_id:" + str3);
        f24091a = str;
        f24092b = str2;
        f24093c = str3;
        f24095e = httpUtils;
        f24097g = 2;
        f24094d = JSCardConfig.f2c;
        m2075a(f24091a, f24097g, JSCardConfig.f3d, f24094d, str2, (String) null, (List<Apdu>) null);
    }

    /* renamed from: a */
    public static void m2075a(String str, int i, String str2, String str3, String str4, String str5, List<Apdu> list) {
        f24095e.m157a(str, i, str2, str3, str4, str5, list, f24098h);
    }

    /* renamed from: a */
    public static void m2077a(ApplicationOperationResp applicationOperationResp) {
        if (applicationOperationResp.getApdus() != null && applicationOperationResp.getApdus().size() != 0) {
            String task_id = applicationOperationResp.getTask_id();
            ArrayList arrayList = new ArrayList();
            Iterator<Apdu> it = applicationOperationResp.getApdus().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Apdu next = it.next();
                CardResult m1869a = SmartCard.m1869a(next.getApdu());
                if (m1869a.getStatus() == 0) {
                    String sw = m1869a.getSw();
                    if (Pattern.matches(next.getSw(), sw)) {
                        Apdu apdu = new Apdu();
                        apdu.setIndex(next.getIndex());
                        apdu.setSw(sw);
                        apdu.setApdu(m1869a.getRapdu());
                        arrayList.add(apdu);
                    } else {
                        Apdu apdu2 = new Apdu();
                        apdu2.setIndex(next.getIndex());
                        apdu2.setSw(sw);
                        if (m1869a.getRapdu() != null) {
                            apdu2.setApdu(m1869a.getRapdu());
                        } else {
                            apdu2.setApdu("");
                        }
                        arrayList.add(apdu2);
                    }
                }
            }
            m2075a(f24091a, f24097g, JSCardConfig.f3d, f24094d, f24092b, task_id, arrayList);
            return;
        }
        int i = f24097g;
        if (i == 1) {
            C14231v.m72b("InstallAndPersonalUtils", "应用下载成功，准备个人化");
            m2078a(0, JSCardConfig.f3d, f24092b, f24093c, (String) null, (String) null, (List<Command>) null);
        } else if (i != 2) {
        } else {
            C14231v.m72b("InstallAndPersonalUtils", "应用删除成功，准备个人化");
            f24097g = 1;
            m2075a(f24091a, 1, JSCardConfig.f3d, f24094d, f24092b, (String) null, (List<Apdu>) null);
        }
    }

    /* renamed from: a */
    public static void m2078a(int i, String str, String str2, String str3, String str4, String str5, List<Command> list) {
        f24095e.m158a(i, str, str2, str3, str4, str5, list, f24099i);
    }

    /* renamed from: a */
    public static void m2076a(ApplyResp applyResp) {
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
            m2078a(0, JSCardConfig.f3d, f24092b, f24093c, session, step, arrayList);
            return;
        }
        String str = JSCardConfig.f4e;
        m2074a(str, applyResp.getInfo() + ",应用下载，安装，个人化完成！");
    }

    /* renamed from: a */
    public static void m2074a(String str, String str2) {
        SmartCard.m1868b();
        InstallAndPersonalEntity installAndPersonalEntity = new InstallAndPersonalEntity();
        installAndPersonalEntity.setCode(str);
        installAndPersonalEntity.setInfo(str2);
        Gson gson = new Gson();
        String json = !(gson instanceof Gson) ? gson.toJson(installAndPersonalEntity) : NBSGsonInstrumentation.toJson(gson, installAndPersonalEntity);
        C14231v.m72b("InstallAndPersonalUtils", json);
        f24096f = json;
        new HomeActivity();
        NativeMethodCallJsUtil.refreshWebView(HomeActivity.f9727a, "cardOpeningResult", f24096f);
    }
}
