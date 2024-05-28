package com.p210hp.hpl.sparta.xpath;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.hp.hpl.sparta.xpath.NodeTestVisitor */
/* loaded from: E:\10762272_dexfile_execute.dex */
public interface NodeTestVisitor {
    void visit(AllElementTest allElementTest);

    void visit(AttrTest attrTest);

    void visit(ElementTest elementTest);

    void visit(ParentNodeTest parentNodeTest) throws XPathException;

    void visit(TextTest textTest);

    void visit(ThisNodeTest thisNodeTest);
}
