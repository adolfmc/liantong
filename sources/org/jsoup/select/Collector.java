package org.jsoup.select;

import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class Collector {
    private Collector() {
    }

    public static Elements collect(Evaluator evaluator, Element element) {
        Elements elements = new Elements();
        new NodeTraversor(new Accumulator(element, elements, evaluator)).traverse(element);
        return elements;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Accumulator implements NodeVisitor {
        private final Elements elements;
        private final Evaluator eval;
        private final Element root;

        @Override // org.jsoup.select.NodeVisitor
        public void tail(Node node, int i) {
        }

        Accumulator(Element element, Elements elements, Evaluator evaluator) {
            this.root = element;
            this.elements = elements;
            this.eval = evaluator;
        }

        @Override // org.jsoup.select.NodeVisitor
        public void head(Node node, int i) {
            if (node instanceof Element) {
                Element element = (Element) node;
                if (this.eval.matches(this.root, element)) {
                    this.elements.add(element);
                }
            }
        }
    }
}
