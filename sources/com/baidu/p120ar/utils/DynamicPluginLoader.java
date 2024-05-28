package com.baidu.p120ar.utils;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.baidu.ar.utils.DynamicPluginLoader */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class DynamicPluginLoader<T> {
    private volatile int mAvailTag = 0;
    private String mImplClassName;
    private volatile T mPluginInstance;

    public DynamicPluginLoader(String str) {
        this.mImplClassName = str;
    }

    public boolean isAvailable() {
        if (this.mAvailTag == 1) {
            return true;
        }
        boolean z = false;
        if (this.mAvailTag == -1) {
            return false;
        }
        try {
            Class.forName(this.mImplClassName);
            z = true;
        } catch (ClassNotFoundException unused) {
        }
        synchronized (this) {
            this.mAvailTag = z ? 1 : -1;
        }
        return z;
    }

    public T getPlugin() {
        if (this.mPluginInstance != null) {
            return this.mPluginInstance;
        }
        if (isAvailable()) {
            synchronized (this) {
                if (this.mPluginInstance == null) {
                    this.mPluginInstance = (T) ReflectionUtils.getNewInstance(this.mImplClassName);
                }
            }
        }
        return this.mPluginInstance;
    }

    public T getCurrentInstance() {
        return this.mPluginInstance;
    }

    public void release() {
        if (this.mPluginInstance != null) {
            this.mPluginInstance = null;
        }
    }
}
