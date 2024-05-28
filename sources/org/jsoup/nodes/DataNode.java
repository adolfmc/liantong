package org.jsoup.nodes;

import org.jsoup.nodes.Document;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class DataNode extends Node {
    private static final String DATA_KEY = "data";

    @Override // org.jsoup.nodes.Node
    public String nodeName() {
        return "#data";
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlTail(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
    }

    public DataNode(String str, String str2) {
        super(str2);
        this.attributes.put("data", str);
    }

    public String getWholeData() {
        return this.attributes.get("data");
    }

    public DataNode setWholeData(String str) {
        this.attributes.put("data", str);
        return this;
    }

    @Override // org.jsoup.nodes.Node
    void outerHtmlHead(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
        sb.append(getWholeData());
    }

    @Override // org.jsoup.nodes.Node
    public String toString() {
        return outerHtml();
    }

    public static DataNode createFromEncoded(String str, String str2) {
        return new DataNode(Entities.unescape(str), str2);
    }
}
