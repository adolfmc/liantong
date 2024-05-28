package org.apache.commons.codec.language.p451bm;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* renamed from: org.apache.commons.codec.language.bm.RuleType */
/* loaded from: E:\11617560_dexfile_execute.dex */
public enum RuleType {
    APPROX("approx"),
    EXACT("exact"),
    RULES("rules");
    
    private final String name;

    RuleType(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
