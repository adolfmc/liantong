package com.p210hp.hpl.sparta.xpath;

import com.p210hp.hpl.sparta.Sparta;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.ElementTest */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class ElementTest extends NodeTest {
    private final String tagName_;

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public ElementTest(String str) {
        this.tagName_ = Sparta.intern(str);
    }

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public String getTagName() {
        return this.tagName_;
    }

    public String toString() {
        return this.tagName_;
    }
}
