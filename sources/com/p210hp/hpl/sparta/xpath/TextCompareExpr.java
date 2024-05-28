package com.p210hp.hpl.sparta.xpath;

/* renamed from: com.hp.hpl.sparta.xpath.TextCompareExpr */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class TextCompareExpr extends BooleanExpr {
    private final String value_;

    /* JADX INFO: Access modifiers changed from: package-private */
    public TextCompareExpr(String str) {
        this.value_ = str;
    }

    public String getValue() {
        return this.value_;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public String toString(String str) {
        return "[text()" + str + "'" + this.value_ + "']";
    }
}
