package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.AllElementTest */
/* loaded from: E:\10762272_dexfile_execute.dex */
public class AllElementTest extends NodeTest {
    static final AllElementTest INSTANCE = new AllElementTest();

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public boolean isStringValue() {
        return false;
    }

    public String toString() {
        return "*";
    }

    private AllElementTest() {
    }

    @Override // com.p210hp.hpl.sparta.xpath.NodeTest
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
