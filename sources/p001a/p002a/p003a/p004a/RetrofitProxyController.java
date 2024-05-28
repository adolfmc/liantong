package p001a.p002a.p003a.p004a;

import com.baidu.license.api.ApiService;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import retrofit2.Call;

/* renamed from: a.a.a.a.uiw */
/* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
public class RetrofitProxyController {

    /* renamed from: a */
    public static final HashMap<String, ArrayList<Call>> f2070a = new HashMap<>();

    /* compiled from: RetrofitProxyController.java */
    /* renamed from: a.a.a.a.uiw$oi */
    /* loaded from: E:\10201592_dexfile_execute.dex.fixout.dex */
    public static class C0699oi implements InvocationHandler {

        /* renamed from: a */
        public ApiService f2071a;

        public C0699oi(ApiService apiService) {
            this.f2071a = apiService;
        }

        @Override // java.lang.reflect.InvocationHandler
        public Object invoke(Object obj, Method method, Object[] objArr) {
            StackTraceElement[] stackTrace;
            ArrayList<Call> arrayList;
            Object invoke = method.invoke(this.f2071a, objArr);
            if ((invoke instanceof Call) && (stackTrace = new Throwable().getStackTrace()) != null && stackTrace.length > 3) {
                String className = stackTrace[3].getClassName();
                if (className.contains("$")) {
                    className = className.substring(0, className.indexOf("$"));
                }
                if (RetrofitProxyController.f2070a.get(className) != null) {
                    arrayList = RetrofitProxyController.f2070a.get(className);
                } else {
                    arrayList = new ArrayList<>();
                }
                arrayList.add((Call) invoke);
                RetrofitProxyController.f2070a.put(className, arrayList);
            }
            return invoke;
        }
    }

    /* renamed from: a */
    public ApiService m22362a(ApiService apiService) {
        if (apiService == null) {
            return null;
        }
        return (ApiService) Proxy.newProxyInstance(apiService.getClass().getClassLoader(), apiService.getClass().getInterfaces(), new C0699oi(apiService));
    }

    /* renamed from: a */
    public void m22361a(String str, Call call) {
        ArrayList<Call> arrayList = f2070a.get(str);
        if (arrayList == null || arrayList.size() <= 0) {
            return;
        }
        call.cancel();
        arrayList.remove(call);
        if (arrayList.size() == 0) {
            f2070a.remove(str);
        }
    }
}
