package com.p210hp.hpl.sparta;

import com.p210hp.hpl.sparta.xpath.AttrEqualsExpr;
import com.p210hp.hpl.sparta.xpath.AttrExistsExpr;
import com.p210hp.hpl.sparta.xpath.AttrGreaterExpr;
import com.p210hp.hpl.sparta.xpath.AttrLessExpr;
import com.p210hp.hpl.sparta.xpath.AttrNotEqualsExpr;
import com.p210hp.hpl.sparta.xpath.BooleanExprVisitor;
import com.p210hp.hpl.sparta.xpath.ElementTest;
import com.p210hp.hpl.sparta.xpath.NodeTest;
import com.p210hp.hpl.sparta.xpath.PositionEqualsExpr;
import com.p210hp.hpl.sparta.xpath.Step;
import com.p210hp.hpl.sparta.xpath.TextEqualsExpr;
import com.p210hp.hpl.sparta.xpath.TextExistsExpr;
import com.p210hp.hpl.sparta.xpath.TextNotEqualsExpr;
import com.p210hp.hpl.sparta.xpath.TrueExpr;
import com.p210hp.hpl.sparta.xpath.XPath;
import com.p210hp.hpl.sparta.xpath.XPathException;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Vector;

/* renamed from: com.hp.hpl.sparta.Node */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public abstract class Node {
    private Document doc_ = null;
    private Element parentNode_ = null;
    private Node previousSibling_ = null;
    private Node nextSibling_ = null;
    private Object annotation_ = null;
    private int hash_ = 0;

    public abstract Object clone();

    protected abstract int computeHashCode();

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void toString(Writer writer) throws IOException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public abstract void toXml(Writer writer) throws IOException;

    public abstract Element xpathSelectElement(String str) throws ParseException;

    public abstract Enumeration xpathSelectElements(String str) throws ParseException;

    public abstract String xpathSelectString(String str) throws ParseException;

    public abstract Enumeration xpathSelectStrings(String str) throws ParseException;

    /* JADX INFO: Access modifiers changed from: package-private */
    public void notifyObservers() {
        this.hash_ = 0;
        Document document = this.doc_;
        if (document != null) {
            document.notifyObservers();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setOwnerDocument(Document document) {
        this.doc_ = document;
    }

    public Document getOwnerDocument() {
        return this.doc_;
    }

    public Element getParentNode() {
        return this.parentNode_;
    }

    public Node getPreviousSibling() {
        return this.previousSibling_;
    }

    public Node getNextSibling() {
        return this.nextSibling_;
    }

    public Object getAnnotation() {
        return this.annotation_;
    }

    public void setAnnotation(Object obj) {
        this.annotation_ = obj;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void setParentNode(Element element) {
        this.parentNode_ = element;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void insertAtEndOfLinkedList(Node node) {
        this.previousSibling_ = node;
        if (node != null) {
            node.nextSibling_ = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void removeFromLinkedList() {
        Node node = this.previousSibling_;
        if (node != null) {
            node.nextSibling_ = this.nextSibling_;
        }
        Node node2 = this.nextSibling_;
        if (node2 != null) {
            node2.previousSibling_ = this.previousSibling_;
        }
        this.nextSibling_ = null;
        this.previousSibling_ = null;
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void replaceInLinkedList(Node node) {
        Node node2 = this.previousSibling_;
        if (node2 != null) {
            node2.nextSibling_ = node;
        }
        Node node3 = this.nextSibling_;
        if (node3 != null) {
            node3.previousSibling_ = node;
        }
        node.nextSibling_ = this.nextSibling_;
        node.previousSibling_ = this.previousSibling_;
        this.nextSibling_ = null;
        this.previousSibling_ = null;
    }

    public String toXml() throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
        toXml(outputStreamWriter);
        outputStreamWriter.flush();
        return new String(byteArrayOutputStream.toByteArray());
    }

    public boolean xpathSetStrings(String str, String str2) throws ParseException {
        try {
            int lastIndexOf = str.lastIndexOf(47);
            int i = lastIndexOf + 1;
            if (!str.substring(i).equals("text()") && str.charAt(i) != '@') {
                throw new ParseException("Last step of Xpath expression \"" + str + "\" is not \"text()\" and does not start with a '@'. It starts with a '" + str.charAt(i) + "'");
            }
            boolean z = false;
            String substring = str.substring(0, lastIndexOf);
            if (str.charAt(i) == '@') {
                String substring2 = str.substring(lastIndexOf + 2);
                if (substring2.length() == 0) {
                    throw new ParseException("Xpath expression \"" + str + "\" specifies zero-length attribute name\"");
                }
                Enumeration xpathSelectElements = xpathSelectElements(substring);
                while (xpathSelectElements.hasMoreElements()) {
                    Element element = (Element) xpathSelectElements.nextElement();
                    if (!str2.equals(element.getAttribute(substring2))) {
                        element.setAttribute(substring2, str2);
                        z = true;
                    }
                }
                return z;
            }
            Enumeration xpathSelectElements2 = xpathSelectElements(substring);
            boolean hasMoreElements = xpathSelectElements2.hasMoreElements();
            while (xpathSelectElements2.hasMoreElements()) {
                Element element2 = (Element) xpathSelectElements2.nextElement();
                Vector vector = new Vector();
                for (Node firstChild = element2.getFirstChild(); firstChild != null; firstChild = firstChild.getNextSibling()) {
                    if (firstChild instanceof Text) {
                        vector.addElement((Text) firstChild);
                    }
                }
                if (vector.size() == 0) {
                    Text text = new Text(str2);
                    if (text.getData().length() > 0) {
                        element2.appendChild(text);
                        hasMoreElements = true;
                    }
                } else {
                    Text text2 = (Text) vector.elementAt(0);
                    if (!text2.getData().equals(str2)) {
                        vector.removeElementAt(0);
                        text2.setData(str2);
                        hasMoreElements = true;
                    }
                    boolean z2 = hasMoreElements;
                    int i2 = 0;
                    while (i2 < vector.size()) {
                        element2.removeChild((Text) vector.elementAt(i2));
                        i2++;
                        z2 = true;
                    }
                    hasMoreElements = z2;
                }
            }
            return hasMoreElements;
        } catch (DOMException e) {
            throw new Error("Assertion failed " + e);
        } catch (IndexOutOfBoundsException unused) {
            throw new ParseException("Xpath expression \"" + str + "\" is not in the form \"xpathExpression/@attributeName\"");
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element makeMatching(final Element element, Step step, final String str) throws ParseException, XPathException {
        NodeTest nodeTest = step.getNodeTest();
        if (!(nodeTest instanceof ElementTest)) {
            throw new ParseException("\"" + nodeTest + "\" in \"" + str + "\" is not an element test");
        }
        final String tagName = ((ElementTest) nodeTest).getTagName();
        final Element element2 = new Element(tagName);
        step.getPredicate().accept(new BooleanExprVisitor() { // from class: com.hp.hpl.sparta.Node.1
            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(TrueExpr trueExpr) {
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(AttrExistsExpr attrExistsExpr) throws XPathException {
                element2.setAttribute(attrExistsExpr.getAttrName(), "something");
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(AttrEqualsExpr attrEqualsExpr) throws XPathException {
                element2.setAttribute(attrEqualsExpr.getAttrName(), attrEqualsExpr.getAttrValue());
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(AttrNotEqualsExpr attrNotEqualsExpr) throws XPathException {
                Element element3 = element2;
                String attrName = attrNotEqualsExpr.getAttrName();
                element3.setAttribute(attrName, "not " + attrNotEqualsExpr.getAttrValue());
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(AttrLessExpr attrLessExpr) throws XPathException {
                element2.setAttribute(attrLessExpr.getAttrName(), Long.toString(Long.MIN_VALUE));
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(AttrGreaterExpr attrGreaterExpr) throws XPathException {
                element2.setAttribute(attrGreaterExpr.getAttrName(), Long.toString(Long.MAX_VALUE));
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(TextExistsExpr textExistsExpr) throws XPathException {
                element2.appendChild(new Text("something"));
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(TextEqualsExpr textEqualsExpr) throws XPathException {
                element2.appendChild(new Text(textEqualsExpr.getValue()));
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(TextNotEqualsExpr textNotEqualsExpr) throws XPathException {
                Element element3 = element2;
                element3.appendChild(new Text("not " + textNotEqualsExpr.getValue()));
            }

            @Override // com.p210hp.hpl.sparta.xpath.BooleanExprVisitor
            public void visit(PositionEqualsExpr positionEqualsExpr) throws XPathException {
                int position = positionEqualsExpr.getPosition();
                if (element == null && position != 1) {
                    throw new XPathException(XPath.get(str), "Position of root node must be 1");
                }
                for (int i = 1; i < position; i++) {
                    element.appendChild(new Element(tagName));
                }
            }
        });
        return element2;
    }

    public String toString() {
        try {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(byteArrayOutputStream);
            toString(outputStreamWriter);
            outputStreamWriter.flush();
            return new String(byteArrayOutputStream.toByteArray());
        } catch (IOException unused) {
            return super.toString();
        }
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public static void htmlEncode(Writer writer, String str) throws IOException {
        String str2;
        int length = str.length();
        int i = 0;
        for (int i2 = 0; i2 < length; i2++) {
            char charAt = str.charAt(i2);
            if (charAt >= 128) {
                str2 = "&#" + ((int) charAt) + ";";
            } else if (charAt == '\"') {
                str2 = "&quot;";
            } else if (charAt == '<') {
                str2 = "&lt;";
            } else if (charAt != '>') {
                switch (charAt) {
                    case '&':
                        str2 = "&amp;";
                        break;
                    case '\'':
                        str2 = "&#39;";
                        break;
                    default:
                        str2 = null;
                        break;
                }
            } else {
                str2 = "&gt;";
            }
            if (str2 != null) {
                writer.write(str, i, i2 - i);
                writer.write(str2);
                i = i2 + 1;
            }
        }
        if (i < length) {
            writer.write(str, i, length - i);
        }
    }

    public int hashCode() {
        if (this.hash_ == 0) {
            this.hash_ = computeHashCode();
        }
        return this.hash_;
    }
}
