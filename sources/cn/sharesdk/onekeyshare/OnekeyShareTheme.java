package cn.sharesdk.onekeyshare;

import cn.sharesdk.onekeyshare.themes.classic.ClassicTheme;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\10201592_dexfile_execute.dex */
public enum OnekeyShareTheme {
    CLASSIC(0, new ClassicTheme());
    
    private final OnekeyShareThemeImpl impl;
    private final int value;

    OnekeyShareTheme(int i, OnekeyShareThemeImpl onekeyShareThemeImpl) {
        this.value = i;
        this.impl = onekeyShareThemeImpl;
    }

    public int getValue() {
        return this.value;
    }

    public OnekeyShareThemeImpl getImpl() {
        return this.impl;
    }

    public static OnekeyShareTheme fromValue(int i) {
        OnekeyShareTheme[] values;
        for (OnekeyShareTheme onekeyShareTheme : values()) {
            if (onekeyShareTheme.value == i) {
                return onekeyShareTheme;
            }
        }
        return CLASSIC;
    }
}
