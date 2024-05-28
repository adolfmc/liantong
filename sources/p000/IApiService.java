package p000;

import com.crb.jscard.http.bean.ApplicationOperationReq;
import com.crb.jscard.http.bean.ApplicationOperationResp;
import com.crb.jscard.http.bean.ApplyReq;
import com.crb.jscard.http.bean.ApplyResp;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: r */
/* loaded from: E:\9227576_dexfile_execute.dex */
public interface IApiService {
    @POST("app")
    /* renamed from: a */
    Call<ApplicationOperationResp> m131a(@Body ApplicationOperationReq applicationOperationReq);

    @POST("apply")
    /* renamed from: a */
    Call<ApplyResp> m130a(@Body ApplyReq applyReq);
}
