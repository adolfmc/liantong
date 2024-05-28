package org.jsoup.nodes;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.jsoup.helper.StringUtil;
import org.jsoup.helper.Validate;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;
import org.jsoup.select.Elements;
import org.jsoup.select.NodeTraversor;
import org.jsoup.select.NodeVisitor;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class Node implements Cloneable {
    Attributes attributes;
    String baseUri;
    List<Node> childNodes;
    Node parentNode;
    int siblingIndex;

    public boolean equals(Object obj) {
        return this == obj;
    }

    public abstract String nodeName();

    abstract void outerHtmlHead(StringBuilder sb, int i, Document.OutputSettings outputSettings);

    abstract void outerHtmlTail(StringBuilder sb, int i, Document.OutputSettings outputSettings);

    /* JADX INFO: Access modifiers changed from: protected */
    public Node(String str, Attributes attributes) {
        Validate.notNull(str);
        Validate.notNull(attributes);
        this.childNodes = new ArrayList(4);
        this.baseUri = str.trim();
        this.attributes = attributes;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node(String str) {
        this(str, new Attributes());
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public Node() {
        this.childNodes = Collections.emptyList();
        this.attributes = null;
    }

    public String attr(String str) {
        Validate.notNull(str);
        if (this.attributes.hasKey(str)) {
            return this.attributes.get(str);
        }
        return str.toLowerCase().startsWith("abs:") ? absUrl(str.substring(4)) : "";
    }

    public Attributes attributes() {
        return this.attributes;
    }

    public Node attr(String str, String str2) {
        this.attributes.put(str, str2);
        return this;
    }

    public boolean hasAttr(String str) {
        Validate.notNull(str);
        if (str.toLowerCase().startsWith("abs:")) {
            String substring = str.substring(4);
            if (this.attributes.hasKey(substring) && !absUrl(substring).equals("")) {
                return true;
            }
        }
        return this.attributes.hasKey(str);
    }

    public Node removeAttr(String str) {
        Validate.notNull(str);
        this.attributes.remove(str);
        return this;
    }

    public String baseUri() {
        return this.baseUri;
    }

    public void setBaseUri(final String str) {
        Validate.notNull(str);
        traverse(new NodeVisitor() { // from class: org.jsoup.nodes.Node.1
            @Override // org.jsoup.select.NodeVisitor
            public void tail(Node node, int i) {
            }

            @Override // org.jsoup.select.NodeVisitor
            public void head(Node node, int i) {
                node.baseUri = str;
            }
        });
    }

    public String absUrl(String str) {
        Validate.notEmpty(str);
        String attr = attr(str);
        try {
            if (hasAttr(str)) {
                try {
                    URL url = new URL(this.baseUri);
                    if (attr.startsWith("?")) {
                        attr = url.getPath() + attr;
                    }
                    return new URL(url, attr).toExternalForm();
                } catch (MalformedURLException unused) {
                    return new URL(attr).toExternalForm();
                }
            }
            return "";
        } catch (MalformedURLException unused2) {
            return "";
        }
    }

    public Node childNode(int i) {
        return this.childNodes.get(i);
    }

    public List<Node> childNodes() {
        return Collections.unmodifiableList(this.childNodes);
    }

    public List<Node> childNodesCopy() {
        ArrayList arrayList = new ArrayList(this.childNodes.size());
        for (Node node : this.childNodes) {
            arrayList.add(node.clone());
        }
        return arrayList;
    }

    public final int childNodeSize() {
        return this.childNodes.size();
    }

    protected Node[] childNodesAsArray() {
        return (Node[]) this.childNodes.toArray(new Node[childNodeSize()]);
    }

    public Node parent() {
        return this.parentNode;
    }

    public Document ownerDocument() {
        if (this instanceof Document) {
            return (Document) this;
        }
        Node node = this.parentNode;
        if (node == null) {
            return null;
        }
        return node.ownerDocument();
    }

    public void remove() {
        Validate.notNull(this.parentNode);
        this.parentNode.removeChild(this);
    }

    public Node before(String str) {
        addSiblingHtml(siblingIndex(), str);
        return this;
    }

    public Node before(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(siblingIndex(), node);
        return this;
    }

    public Node after(String str) {
        addSiblingHtml(siblingIndex() + 1, str);
        return this;
    }

    public Node after(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.addChildren(siblingIndex() + 1, node);
        return this;
    }

    private void addSiblingHtml(int i, String str) {
        Validate.notNull(str);
        Validate.notNull(this.parentNode);
        List<Node> parseFragment = Parser.parseFragment(str, parent() instanceof Element ? (Element) parent() : null, baseUri());
        this.parentNode.addChildren(i, (Node[]) parseFragment.toArray(new Node[parseFragment.size()]));
    }

    public Node wrap(String str) {
        Validate.notEmpty(str);
        List<Node> parseFragment = Parser.parseFragment(str, parent() instanceof Element ? (Element) parent() : null, baseUri());
        Node node = parseFragment.get(0);
        if (node == null || !(node instanceof Element)) {
            return null;
        }
        Element element = (Element) node;
        Element deepChild = getDeepChild(element);
        this.parentNode.replaceChild(this, element);
        deepChild.addChildren(this);
        if (parseFragment.size() > 0) {
            for (int i = 0; i < parseFragment.size(); i++) {
                Node node2 = parseFragment.get(i);
                node2.parentNode.removeChild(node2);
                element.appendChild(node2);
            }
        }
        return this;
    }

    public Node unwrap() {
        Validate.notNull(this.parentNode);
        int i = this.siblingIndex;
        Node node = this.childNodes.size() > 0 ? this.childNodes.get(0) : null;
        this.parentNode.addChildren(i, childNodesAsArray());
        remove();
        return node;
    }

    private Element getDeepChild(Element element) {
        Elements children = element.children();
        return children.size() > 0 ? getDeepChild(children.get(0)) : element;
    }

    public void replaceWith(Node node) {
        Validate.notNull(node);
        Validate.notNull(this.parentNode);
        this.parentNode.replaceChild(this, node);
    }

    protected void setParentNode(Node node) {
        Node node2 = this.parentNode;
        if (node2 != null) {
            node2.removeChild(this);
        }
        this.parentNode = node;
    }

    protected void replaceChild(Node node, Node node2) {
        Validate.isTrue(node.parentNode == this);
        Validate.notNull(node2);
        Node node3 = node2.parentNode;
        if (node3 != null) {
            node3.removeChild(node2);
        }
        Integer valueOf = Integer.valueOf(node.siblingIndex());
        this.childNodes.set(valueOf.intValue(), node2);
        node2.parentNode = this;
        node2.setSiblingIndex(valueOf.intValue());
        node.parentNode = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void removeChild(Node node) {
        Validate.isTrue(node.parentNode == this);
        this.childNodes.remove(node.siblingIndex());
        reindexChildren();
        node.parentNode = null;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addChildren(Node... nodeArr) {
        for (Node node : nodeArr) {
            reparentChild(node);
            this.childNodes.add(node);
            node.setSiblingIndex(this.childNodes.size() - 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void addChildren(int i, Node... nodeArr) {
        Validate.noNullElements(nodeArr);
        for (int length = nodeArr.length - 1; length >= 0; length--) {
            Node node = nodeArr[length];
            reparentChild(node);
            this.childNodes.add(i, node);
        }
        reindexChildren();
    }

    private void reparentChild(Node node) {
        Node node2 = node.parentNode;
        if (node2 != null) {
            node2.removeChild(node);
        }
        node.setParentNode(this);
    }

    private void reindexChildren() {
        for (int i = 0; i < this.childNodes.size(); i++) {
            this.childNodes.get(i).setSiblingIndex(i);
        }
    }

    public List<Node> siblingNodes() {
        Node node = this.parentNode;
        if (node == null) {
            return Collections.emptyList();
        }
        List<Node> list = node.childNodes;
        ArrayList arrayList = new ArrayList(list.size() - 1);
        for (Node node2 : list) {
            if (node2 != this) {
                arrayList.add(node2);
            }
        }
        return arrayList;
    }

    public Node nextSibling() {
        Node node = this.parentNode;
        if (node == null) {
            return null;
        }
        List<Node> list = node.childNodes;
        Integer valueOf = Integer.valueOf(siblingIndex());
        Validate.notNull(valueOf);
        if (list.size() > valueOf.intValue() + 1) {
            return list.get(valueOf.intValue() + 1);
        }
        return null;
    }

    public Node previousSibling() {
        Node node = this.parentNode;
        if (node == null) {
            return null;
        }
        List<Node> list = node.childNodes;
        Integer valueOf = Integer.valueOf(siblingIndex());
        Validate.notNull(valueOf);
        if (valueOf.intValue() > 0) {
            return list.get(valueOf.intValue() - 1);
        }
        return null;
    }

    public int siblingIndex() {
        return this.siblingIndex;
    }

    protected void setSiblingIndex(int i) {
        this.siblingIndex = i;
    }

    public Node traverse(NodeVisitor nodeVisitor) {
        Validate.notNull(nodeVisitor);
        new NodeTraversor(nodeVisitor).traverse(this);
        return this;
    }

    public String outerHtml() {
        StringBuilder sb = new StringBuilder(128);
        outerHtml(sb);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void outerHtml(StringBuilder sb) {
        new NodeTraversor(new OuterHtmlVisitor(sb, getOutputSettings())).traverse(this);
    }

    private Document.OutputSettings getOutputSettings() {
        return (ownerDocument() != null ? ownerDocument() : new Document("")).outputSettings();
    }

    public String toString() {
        return outerHtml();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public void indent(StringBuilder sb, int i, Document.OutputSettings outputSettings) {
        sb.append("\n");
        sb.append(StringUtil.padding(i * outputSettings.indentAmount()));
    }

    public int hashCode() {
        Node node = this.parentNode;
        int hashCode = (node != null ? node.hashCode() : 0) * 31;
        Attributes attributes = this.attributes;
        return hashCode + (attributes != null ? attributes.hashCode() : 0);
    }

    @Override // 
    public Node clone() {
        return doClone(null);
    }

    protected Node doClone(Node node) {
        try {
            Node node2 = (Node) super.clone();
            node2.parentNode = node;
            node2.siblingIndex = node == null ? 0 : this.siblingIndex;
            Attributes attributes = this.attributes;
            node2.attributes = attributes != null ? attributes.clone() : null;
            node2.baseUri = this.baseUri;
            node2.childNodes = new ArrayList(this.childNodes.size());
            for (Node node3 : this.childNodes) {
                node2.childNodes.add(node3.doClone(node2));
            }
            return node2;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class OuterHtmlVisitor implements NodeVisitor {
        private StringBuilder accum;
        private Document.OutputSettings out;

        OuterHtmlVisitor(StringBuilder sb, Document.OutputSettings outputSettings) {
            this.accum = sb;
            this.out = outputSettings;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i) {
            node.outerHtmlHead(this.accum, i, this.out);
        }

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i) {
            if (node.nodeName().equals("#text")) {
                return;
            }
            node.outerHtmlTail(this.accum, i, this.out);
        }
    }
}
