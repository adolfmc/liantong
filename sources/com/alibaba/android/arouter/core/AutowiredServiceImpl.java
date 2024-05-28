package com.alibaba.android.arouter.core;

import android.content.Context;
import android.util.LruCache;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.facade.service.AutowiredService;
import com.alibaba.android.arouter.facade.template.ISyringe;
import java.util.ArrayList;
import java.util.List;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Route(path = "/arouter/service/autowired")
/* loaded from: E:\10201592_dexfile_execute.dex */
public class AutowiredServiceImpl implements AutowiredService {
    private List<String> blackList;
    private LruCache<String, ISyringe> classCache;

    @Override // com.alibaba.android.arouter.facade.template.IProvider
    public void init(Context context) {
        this.classCache = new LruCache<>(66);
        this.blackList = new ArrayList();
    }

    @Override // com.alibaba.android.arouter.facade.service.AutowiredService
    public void autowire(Object obj) {
        String name = obj.getClass().getName();
        try {
            if (this.blackList.contains(name)) {
                return;
            }
            ISyringe iSyringe = this.classCache.get(name);
            if (iSyringe == null) {
                iSyringe = (ISyringe) Class.forName(obj.getClass().getName() + "$$ARouter$$Autowired").getConstructor(new Class[0]).newInstance(new Object[0]);
            }
            iSyringe.inject(obj);
            this.classCache.put(name, iSyringe);
        } catch (Exception unused) {
            this.blackList.add(name);
        }
    }
}
