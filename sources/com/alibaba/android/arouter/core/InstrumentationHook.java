package com.alibaba.android.arouter.core;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import java.lang.reflect.Field;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
@Deprecated
/* loaded from: E:\10201592_dexfile_execute.dex */
public class InstrumentationHook extends Instrumentation {
    @Override // android.app.Instrumentation
    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        String[] stringArrayExtra;
        Class<?> loadClass = classLoader.loadClass(str);
        Object newInstance = loadClass.newInstance();
        if (ARouter.canAutoInject() && (stringArrayExtra = intent.getStringArrayExtra("wmHzgD4lOj5o4241")) != null && stringArrayExtra.length > 0) {
            for (String str2 : stringArrayExtra) {
                Object obj = intent.getExtras().get(TextUtils.getLeft(str2));
                if (obj != null) {
                    try {
                        Field declaredField = loadClass.getDeclaredField(TextUtils.getLeft(str2));
                        declaredField.setAccessible(true);
                        declaredField.set(newInstance, obj);
                    } catch (Exception e) {
                        ARouter.logger.error("ARouter::", "Inject values for activity error! [" + e.getMessage() + "]");
                    }
                }
            }
        }
        return (Activity) newInstance;
    }
}
