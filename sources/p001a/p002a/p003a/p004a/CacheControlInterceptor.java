package p001a.p002a.p003a.p004a;

import android.text.TextUtils;
import com.baidu.license.LicenseManager;
import com.baidu.license.util.NetHelper;
import java.util.ArrayList;
import okhttp3.CacheControl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/* renamed from: a.a.a.a.nx */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class CacheControlInterceptor implements Interceptor {
    public CacheControlInterceptor() {
        ApiManager.f2067b = new ArrayList<>();
    }

    @Override // okhttp3.Interceptor
    public Response intercept(Interceptor.Chain chain) {
        Request request = chain.request();
        String header = request.header("cache_need_req:y".split(":")[0]);
        if (TextUtils.equals("GET", request.method()) && !TextUtils.isEmpty(header)) {
            String encodedPath = request.url().encodedPath();
            if (!NetHelper.isNetworkConnected(LicenseManager.sContext) || (TextUtils.equals("y", header) && !ApiManager.f2067b.contains(encodedPath))) {
                request = request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build();
                if (TextUtils.equals("y", header) && !ApiManager.f2067b.contains(encodedPath)) {
                    ApiManager.f2067b.add(encodedPath);
                }
            }
            Response.Builder removeHeader = chain.proceed(request).newBuilder().removeHeader("Pragma").removeHeader("Cache-Control");
            return removeHeader.header("Cache-Control", "public, only-if-cached, max-stale=604800").build();
        }
        return chain.proceed(request);
    }
}
