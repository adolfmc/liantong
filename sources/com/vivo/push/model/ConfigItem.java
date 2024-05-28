package com.vivo.push.model;

/* renamed from: com.vivo.push.model.a */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ConfigItem {

    /* renamed from: a */
    private String f21054a;

    /* renamed from: b */
    private String f21055b;

    public ConfigItem(String str, String str2) {
        this.f21054a = str;
        this.f21055b = str2;
    }

    /* renamed from: a */
    public final String m5604a() {
        return this.f21054a;
    }

    /* renamed from: b */
    public final String m5603b() {
        return this.f21055b;
    }

    public final int hashCode() {
        String str = this.f21054a;
        return (str == null ? 0 : str.hashCode()) + 31;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && getClass() == obj.getClass()) {
            ConfigItem configItem = (ConfigItem) obj;
            String str = this.f21054a;
            if (str == null) {
                if (configItem.f21054a != null) {
                    return false;
                }
            } else if (!str.equals(configItem.f21054a)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public final String toString() {
        return "ConfigItem{mKey='" + this.f21054a + "', mValue='" + this.f21055b + "'}";
    }
}
