package org.codehaus.jackson.annotate;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public enum JsonMethod {
    GETTER,
    SETTER,
    CREATOR,
    FIELD,
    IS_GETTER,
    NONE,
    ALL;

    public boolean creatorEnabled() {
        return this == CREATOR || this == ALL;
    }

    public boolean getterEnabled() {
        return this == GETTER || this == ALL;
    }

    public boolean isGetterEnabled() {
        return this == IS_GETTER || this == ALL;
    }

    public boolean setterEnabled() {
        return this == SETTER || this == ALL;
    }

    public boolean fieldEnabled() {
        return this == FIELD || this == ALL;
    }
}
