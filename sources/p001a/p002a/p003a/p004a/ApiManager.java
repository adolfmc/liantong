package p001a.p002a.p003a.p004a;

import android.text.TextUtils;
import com.baidu.license.LicenseManager;
import com.baidu.license.SDKHttpConfig;
import com.baidu.license.api.ApiService;
import com.networkbench.agent.impl.instrumentation.NBSInstrumented;
import com.networkbench.agent.impl.instrumentation.NBSOkHttp3Instrumentation;
import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@NBSInstrumented
/* renamed from: a.a.a.a.oi */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class ApiManager {

    /* renamed from: a */
    public static volatile ApiManager f2066a;

    /* renamed from: b */
    public static ArrayList<String> f2067b = new ArrayList<>();

    /* renamed from: c */
    public ApiService f2068c;

    /* renamed from: d */
    public RetrofitProxyController f2069d;

    /* renamed from: a */
    public static ApiService m22371a() {
        ApiManager m22369b = m22369b();
        if (m22369b.f2068c == null || m22369b.f2069d == null) {
            if (RestApiAdapter.f2065a == null) {
                OkHttpClient.Builder connectTimeout = new OkHttpClient.Builder().addInterceptor(new CacheControlInterceptor()).cache(new Cache(new File(LicenseManager.sContext.getFilesDir().getAbsolutePath() + File.separator + "httpcache"), 52428800L)).readTimeout(30L, TimeUnit.SECONDS).writeTimeout(30L, TimeUnit.SECONDS).connectTimeout(30L, TimeUnit.SECONDS);
                RestApiAdapter.f2065a = new Retrofit.Builder().client(!(connectTimeout instanceof OkHttpClient.Builder) ? connectTimeout.build() : NBSOkHttp3Instrumentation.builderInit(connectTimeout)).baseUrl(SDKHttpConfig.HOST).addCallAdapterFactory(RxJavaCallAdapterFactory.create()).addConverterFactory(GsonConverterFactory.create()).build();
            }
            m22369b.f2069d = new RetrofitProxyController();
            m22369b.f2068c = m22369b.f2069d.m22362a((ApiService) RestApiAdapter.f2065a.create(ApiService.class));
        }
        return m22369b.f2068c;
    }

    /* renamed from: b */
    public static ApiManager m22369b() {
        if (f2066a == null) {
            synchronized (ApiManager.class) {
                if (f2066a == null) {
                    f2066a = new ApiManager();
                }
            }
        }
        return f2066a;
    }

    /* renamed from: a */
    public void m22370a(String str, Call call) {
        if (this.f2069d == null || TextUtils.isEmpty(str)) {
            return;
        }
        if (str.contains("$")) {
            str = str.substring(0, str.indexOf("$"));
        }
        this.f2069d.m22361a(str, call);
    }
}
