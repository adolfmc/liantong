package org.jsoup.select;

import org.jsoup.nodes.Node;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NodeTraversor {
    private NodeVisitor visitor;

    public NodeTraversor(NodeVisitor nodeVisitor) {
        this.visitor = nodeVisitor;
    }

    public void traverse(Node node) {
        Node node2 = node;
        int i = 0;
        while (node2 != null) {
            this.visitor.head(node2, i);
            if (node2.childNodeSize() > 0) {
                node2 = node2.childNode(0);
                i++;
            } else {
                while (node2.nextSibling() == null && i > 0) {
                    this.visitor.tail(node2, i);
                    node2 = node2.parent();
                    i--;
                }
                this.visitor.tail(node2, i);
                if (node2 == node) {
                    return;
                }
                node2 = node2.nextSibling();
            }
        }
    }
}
