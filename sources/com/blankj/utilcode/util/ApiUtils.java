package com.blankj.utilcode.util;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public final class ApiUtils {
    private static final String TAG = "ApiUtils";
    private Map<Class, BaseApi> mApiMap;
    private Map<Class, Class> mInjectApiImplMap;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    @Target({ElementType.TYPE})
    @Retention(RetentionPolicy.CLASS)
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public @interface Api {
        boolean isMock() default false;
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class BaseApi {
    }

    private void init() {
    }

    private ApiUtils() {
        this.mApiMap = new ConcurrentHashMap();
        this.mInjectApiImplMap = new HashMap();
        init();
    }

    private void registerImpl(Class cls) {
        this.mInjectApiImplMap.put(cls.getSuperclass(), cls);
    }

    @Nullable
    public static <T extends BaseApi> T getApi(@NonNull Class<T> cls) {
        if (cls == null) {
            throw new NullPointerException("Argument 'apiClass' of type Class<T> (#0 out of 1, zero-based) is marked by @android.support.annotation.NonNull but got null for it");
        }
        return (T) getInstance().getApiInner(cls);
    }

    public static void register(@Nullable Class<? extends BaseApi> cls) {
        if (cls == null) {
            return;
        }
        getInstance().registerImpl(cls);
    }

    @NonNull
    public static String toString_() {
        String apiUtils = getInstance().toString();
        if (apiUtils != null) {
            return apiUtils;
        }
        throw new NullPointerException("Detected an attempt to return null from a method com.blankj.utilcode.util.ApiUtils.toString_() marked by @android.support.annotation.NonNull");
    }

    public String toString() {
        return "ApiUtils: " + this.mInjectApiImplMap;
    }

    private static ApiUtils getInstance() {
        return LazyHolder.INSTANCE;
    }

    private <Result> Result getApiInner(Class cls) {
        Result result = (Result) this.mApiMap.get(cls);
        if (result != null) {
            return result;
        }
        synchronized (cls) {
            Result result2 = (Result) this.mApiMap.get(cls);
            if (result2 != null) {
                return result2;
            }
            Class cls2 = this.mInjectApiImplMap.get(cls);
            if (cls2 != null) {
                try {
                    Result result3 = (Result) ((BaseApi) cls2.newInstance());
                    this.mApiMap.put(cls, result3);
                    return result3;
                } catch (Exception unused) {
                    Log.e(TAG, "The <" + cls2 + "> has no parameterless constructor.");
                    return null;
                }
            }
            Log.e(TAG, "The <" + cls + "> doesn't implement.");
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public static class LazyHolder {
        private static final ApiUtils INSTANCE = new ApiUtils();

        private LazyHolder() {
        }
    }
}
