package org.jsoup.nodes;

import org.jsoup.nodes.Document;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class XmlDeclaration extends Node {
    private static final String DECL_KEY = "declaration";
    private final boolean isProcessingInstruction;

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#declaration";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
    }

    public XmlDeclaration(String str, String str2, boolean z) {
        super(str2);
        this.attributes.put("declaration", str);
        this.isProcessingInstruction = z;
    }

    public String getWholeDeclaration() {
        return this.attributes.get("declaration");
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
        sb.append("<");
        sb.append(this.isProcessingInstruction ? "!" : "?");
        sb.append(getWholeDeclaration());
        sb.append(">");
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }
}
