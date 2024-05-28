package com.alibaba.android.arouter.core;

import com.alibaba.android.arouter.base.UniqueKeyTreeMap;
import com.alibaba.android.arouter.facade.model.RouteMeta;
import com.alibaba.android.arouter.facade.template.IInterceptor;
import com.alibaba.android.arouter.facade.template.IProvider;
import com.alibaba.android.arouter.facade.template.IRouteGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class Warehouse {
    static Map<String, Class<? extends IRouteGroup>> groupsIndex = new HashMap();
    static Map<String, RouteMeta> routes = new HashMap();
    static Map<Class, IProvider> providers = new HashMap();
    static Map<String, RouteMeta> providersIndex = new HashMap();
    static Map<Integer, Class<? extends IInterceptor>> interceptorsIndex = new UniqueKeyTreeMap("More than one interceptors use same priority [%s]");
    static List<IInterceptor> interceptors = new ArrayList();

    Warehouse() {
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public static void clear() {
        routes.clear();
        groupsIndex.clear();
        providers.clear();
        providersIndex.clear();
        interceptors.clear();
        interceptorsIndex.clear();
    }
}
