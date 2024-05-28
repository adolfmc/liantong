package com.alibaba.android.arouter.core;

import android.content.Context;
import android.net.Uri;
import com.alibaba.android.arouter.exception.HandlerException;
import com.alibaba.android.arouter.exception.NoRouteFoundException;
import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.enums.TypeKind;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptorGroup;
import com.alibaba.android.arouter.facade.template.ILogger;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IProviderGroup;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import com.alibaba.android.arouter.facade.template.IRouteRoot;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.MapUtils;
import com.alibaba.android.arouter.utils.TextUtils;
import java.util.Locale;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class LogisticsCenter {
    static ThreadPoolExecutor executor;
    private static Context mContext;
    private static boolean registerByPlugin;

    private static void loadRouterMap() {
        registerByPlugin = false;
    }

    private static void register(String str) {
        if (TextUtils.isEmpty(str)) {
            return;
        }
        try {
            Object newInstance = Class.forName(str).getConstructor(new Class[0]).newInstance(new Object[0]);
            if (newInstance instanceof IRouteRoot) {
                registerRouteRoot((IRouteRoot) newInstance);
            } else if (newInstance instanceof IProviderGroup) {
                registerProvider((IProviderGroup) newInstance);
            } else if (newInstance instanceof IInterceptorGroup) {
                registerInterceptor((IInterceptorGroup) newInstance);
            } else {
                ILogger iLogger = ARouter.logger;
                iLogger.info("ARouter::", "register failed, class name: " + str + " should implements one of IRouteRoot/IProviderGroup/IInterceptorGroup.");
            }
        } catch (Exception unused) {
            ILogger iLogger2 = ARouter.logger;
            iLogger2.error("ARouter::", "register class error:" + str);
        }
    }

    private static void registerRouteRoot(IRouteRoot iRouteRoot) {
        markRegisteredByPlugin();
        if (iRouteRoot != null) {
            iRouteRoot.loadInto(Warehouse.groupsIndex);
        }
    }

    private static void registerInterceptor(IInterceptorGroup iInterceptorGroup) {
        markRegisteredByPlugin();
        if (iInterceptorGroup != null) {
            iInterceptorGroup.loadInto(Warehouse.interceptorsIndex);
        }
    }

    private static void registerProvider(IProviderGroup iProviderGroup) {
        markRegisteredByPlugin();
        if (iProviderGroup != null) {
            iProviderGroup.loadInto(Warehouse.providersIndex);
        }
    }

    private static void markRegisteredByPlugin() {
        if (registerByPlugin) {
            return;
        }
        registerByPlugin = true;
    }

    /* JADX WARN: Removed duplicated region for block: B:21:0x00b4 A[Catch: Exception -> 0x0195, all -> 0x01b6, TryCatch #1 {Exception -> 0x0195, blocks: (B:5:0x0007, B:7:0x0013, B:30:0x0120, B:32:0x014a, B:33:0x0153, B:35:0x0159, B:8:0x001e, B:10:0x0024, B:13:0x002b, B:18:0x0078, B:19:0x00ae, B:21:0x00b4, B:23:0x00c2, B:24:0x00dc, B:26:0x00e4, B:27:0x00fe, B:29:0x0106, B:14:0x004b, B:16:0x0062, B:17:0x0075), top: B:45:0x0007, outer: #0 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static synchronized void init(android.content.Context r8, java.util.concurrent.ThreadPoolExecutor r9) throws com.alibaba.android.arouter.exception.HandlerException {
        /*
            Method dump skipped, instructions count: 441
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.alibaba.android.arouter.core.LogisticsCenter.init(android.content.Context, java.util.concurrent.ThreadPoolExecutor):void");
    }

    public static Postcard buildProvider(String str) {
        RouteMeta routeMeta = Warehouse.providersIndex.get(str);
        if (routeMeta == null) {
            return null;
        }
        return new Postcard(routeMeta.getPath(), routeMeta.getGroup());
    }

    public static synchronized void completion(Postcard postcard) {
        synchronized (LogisticsCenter.class) {
            if (postcard == null) {
                throw new NoRouteFoundException("ARouter::No postcard!");
            }
            RouteMeta routeMeta = Warehouse.routes.get(postcard.getPath());
            if (routeMeta == null) {
                Class<? extends IRouteGroup> cls = Warehouse.groupsIndex.get(postcard.getGroup());
                if (cls == null) {
                    throw new NoRouteFoundException("ARouter::There is no route match the path [" + postcard.getPath() + "], in group [" + postcard.getGroup() + "]");
                }
                try {
                    if (ARouter.debuggable()) {
                        ARouter.logger.debug("ARouter::", String.format(Locale.getDefault(), "The group [%s] starts loading, trigger by [%s]", postcard.getGroup(), postcard.getPath()));
                    }
                    cls.getConstructor(new Class[0]).newInstance(new Object[0]).loadInto(Warehouse.routes);
                    Warehouse.groupsIndex.remove(postcard.getGroup());
                    if (ARouter.debuggable()) {
                        ARouter.logger.debug("ARouter::", String.format(Locale.getDefault(), "The group [%s] has already been loaded, trigger by [%s]", postcard.getGroup(), postcard.getPath()));
                    }
                    completion(postcard);
                } catch (Exception e) {
                    throw new HandlerException("ARouter::Fatal exception when loading group meta. [" + e.getMessage() + "]");
                }
            } else {
                postcard.setDestination(routeMeta.getDestination());
                postcard.setType(routeMeta.getType());
                postcard.setPriority(routeMeta.getPriority());
                postcard.setExtra(routeMeta.getExtra());
                Uri uri = postcard.getUri();
                if (uri != null) {
                    Map<String, String> splitQueryParameters = TextUtils.splitQueryParameters(uri);
                    Map<String, Integer> paramsType = routeMeta.getParamsType();
                    if (MapUtils.isNotEmpty(paramsType)) {
                        for (Map.Entry<String, Integer> entry : paramsType.entrySet()) {
                            setValue(postcard, entry.getValue(), entry.getKey(), splitQueryParameters.get(entry.getKey()));
                        }
                        postcard.getExtras().putStringArray("wmHzgD4lOj5o4241", (String[]) paramsType.keySet().toArray(new String[0]));
                    }
                    postcard.withString("NTeRQWvye18AkPd6G", uri.toString());
                }
                switch (routeMeta.getType()) {
                    case PROVIDER:
                        Class<?> destination = routeMeta.getDestination();
                        IProvider iProvider = Warehouse.providers.get(destination);
                        if (iProvider == null) {
                            try {
                                iProvider = (IProvider) destination.getConstructor(new Class[0]).newInstance(new Object[0]);
                                iProvider.init(mContext);
                                Warehouse.providers.put(destination, iProvider);
                            } catch (Exception e2) {
                                throw new HandlerException("Init provider failed! " + e2.getMessage());
                            }
                        }
                        postcard.setProvider(iProvider);
                        postcard.greenChannel();
                        break;
                    case FRAGMENT:
                        postcard.greenChannel();
                        break;
                }
            }
        }
    }

    private static void setValue(Postcard postcard, Integer num, String str, String str2) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return;
        }
        try {
            if (num != null) {
                if (num.intValue() == TypeKind.BOOLEAN.ordinal()) {
                    postcard.withBoolean(str, Boolean.parseBoolean(str2));
                } else if (num.intValue() == TypeKind.BYTE.ordinal()) {
                    postcard.withByte(str, Byte.valueOf(str2).byteValue());
                } else if (num.intValue() == TypeKind.SHORT.ordinal()) {
                    postcard.withShort(str, Short.valueOf(str2).shortValue());
                } else if (num.intValue() == TypeKind.INT.ordinal()) {
                    postcard.withInt(str, Integer.valueOf(str2).intValue());
                } else if (num.intValue() == TypeKind.LONG.ordinal()) {
                    postcard.withLong(str, Long.valueOf(str2).longValue());
                } else if (num.intValue() == TypeKind.FLOAT.ordinal()) {
                    postcard.withFloat(str, Float.valueOf(str2).floatValue());
                } else if (num.intValue() == TypeKind.DOUBLE.ordinal()) {
                    postcard.withDouble(str, Double.valueOf(str2).doubleValue());
                } else if (num.intValue() == TypeKind.STRING.ordinal()) {
                    postcard.withString(str, str2);
                } else if (num.intValue() != TypeKind.PARCELABLE.ordinal()) {
                    if (num.intValue() == TypeKind.OBJECT.ordinal()) {
                        postcard.withString(str, str2);
                    } else {
                        postcard.withString(str, str2);
                    }
                }
            } else {
                postcard.withString(str, str2);
            }
        } catch (Throwable th) {
            ILogger iLogger = ARouter.logger;
            iLogger.warning("ARouter::", "LogisticsCenter setValue failed! " + th.getMessage());
        }
    }

    public static void suspend() {
        Warehouse.clear();
    }
}
