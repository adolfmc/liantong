package org.jsoup.nodes;

import org.jsoup.nodes.Document;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Comment extends Node {
    private static final String COMMENT_KEY = "comment";

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#comment";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
    }

    public Comment(String str, String str2) {
        super(str2);
        this.attributes.put("comment", str);
    }

    public String getData() {
        return this.attributes.get("comment");
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
        if (outputSettings.prettyPrint()) {
            indent(sb, i, outputSettings);
        }
        sb.append("<!--");
        sb.append(getData());
        sb.append("-->");
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }
}
