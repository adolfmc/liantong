package p000;

import com.crb.jscard.http.bean.Apdu;
import com.crb.jscard.http.bean.ApplicationOperationReq;
import com.crb.jscard.http.bean.ApplicationOperationResp;
import com.crb.jscard.http.bean.ApplyReq;
import com.crb.jscard.http.bean.ApplyResp;
import com.crb.jscard.http.bean.Command;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;

/* renamed from: q  reason: case insensitive filesystem */
/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class HttpUtils {

    /* renamed from: b */
    public static ApiBase f27512b;

    /* renamed from: c */
    public static IApiService f27513c;

    /* renamed from: a */
    public Call f27514a;

    /* renamed from: a */
    public static synchronized HttpUtils m159a() {
        HttpUtils httpUtils;
        synchronized (HttpUtils.class) {
            if (f27512b == null) {
                f27512b = new ApiBase();
            }
            if (f27513c == null) {
                f27513c = f27512b.m192b(JSCardConfig.f1b);
            }
            httpUtils = new HttpUtils();
        }
        return httpUtils;
    }

    /* renamed from: a */
    public void m157a(String str, int i, String str2, String str3, String str4, String str5, List<Apdu> list, Callback<ApplicationOperationResp> callback) {
        ApplicationOperationReq applicationOperationReq = new ApplicationOperationReq();
        applicationOperationReq.setApp_aid(str);
        applicationOperationReq.setType(i);
        applicationOperationReq.setIccid(str2);
        applicationOperationReq.setMsisdn(str3);
        applicationOperationReq.setCity_id(str4);
        applicationOperationReq.setTask_id(str5);
        applicationOperationReq.setApdus(list);
        Call<ApplicationOperationResp> m131a = f27513c.m131a(applicationOperationReq);
        this.f27514a = m131a;
        m131a.enqueue(callback);
    }

    /* renamed from: a */
    public void m158a(int i, String str, String str2, String str3, String str4, String str5, List<Command> list, Callback<ApplyResp> callback) {
        ApplyReq applyReq = new ApplyReq();
        applyReq.setType(i);
        applyReq.setIccid(str);
        applyReq.setCity_id(str2);
        applyReq.setOrder_id(str3);
        applyReq.setSession(str4);
        applyReq.setStep(str5);
        applyReq.setCommands(list);
        Call<ApplyResp> m130a = f27513c.m130a(applyReq);
        this.f27514a = m130a;
        m130a.enqueue(callback);
    }
}
