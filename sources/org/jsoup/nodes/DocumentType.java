package org.jsoup.nodes;

import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DocumentType extends Node {
    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#doctype";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
    }

    public DocumentType(String str, String str2, String str3, String str4) {
        super(str4);
        Validate.notEmpty(str);
        attr("name", str);
        attr("publicId", str2);
        attr("systemId", str3);
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
        sb.append("<!DOCTYPE ");
        sb.append(attr("name"));
        if (!StringUtil.isBlank(attr("publicId"))) {
            sb.append(" PUBLIC \"");
            sb.append(attr("publicId"));
            sb.append("\"");
        }
        if (!StringUtil.isBlank(attr("systemId"))) {
            sb.append(" \"");
            sb.append(attr("systemId"));
            sb.append("\"");
        }
        sb.append('>');
    }
}
