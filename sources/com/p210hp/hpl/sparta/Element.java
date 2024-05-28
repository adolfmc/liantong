package com.p210hp.hpl.sparta;

import com.p210hp.hpl.sparta.xpath.Step;
import com.p210hp.hpl.sparta.xpath.XPath;
import com.p210hp.hpl.sparta.xpath.XPathException;
import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Vector;

/* renamed from: com.hp.hpl.sparta.Element */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Element extends Node {
    private static final boolean DEBUG = false;
    private Vector attributeNames_;
    private Hashtable attributes_;
    private Node firstChild_;
    private Node lastChild_;
    private String tagName_;

    private void checkInvariant() {
    }

    public Element(String str) {
        this.firstChild_ = null;
        this.lastChild_ = null;
        this.attributes_ = null;
        this.attributeNames_ = null;
        this.tagName_ = null;
        this.tagName_ = Sparta.intern(str);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public Element() {
        this.firstChild_ = null;
        this.lastChild_ = null;
        this.attributes_ = null;
        this.attributeNames_ = null;
        this.tagName_ = null;
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Object clone() {
        return cloneElement(true);
    }

    public Element cloneShallow() {
        return cloneElement(false);
    }

    public Element cloneElement(boolean z) {
        Element element = new Element(this.tagName_);
        Vector vector = this.attributeNames_;
        if (vector != null) {
            Enumeration elements = vector.elements();
            while (elements.hasMoreElements()) {
                String str = (String) elements.nextElement();
                element.setAttribute(str, (String) this.attributes_.get(str));
            }
        }
        if (z) {
            for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
                element.appendChild((Node) node.clone());
            }
        }
        return element;
    }

    public String getTagName() {
        return this.tagName_;
    }

    public void setTagName(String str) {
        this.tagName_ = Sparta.intern(str);
        notifyObservers();
    }

    public Node getFirstChild() {
        return this.firstChild_;
    }

    public Node getLastChild() {
        return this.lastChild_;
    }

    public Enumeration getAttributeNames() {
        Vector vector = this.attributeNames_;
        if (vector == null) {
            return Document.EMPTY;
        }
        return vector.elements();
    }

    public String getAttribute(String str) {
        Hashtable hashtable = this.attributes_;
        if (hashtable == null) {
            return null;
        }
        return (String) hashtable.get(str);
    }

    public void setAttribute(String str, String str2) {
        if (this.attributes_ == null) {
            this.attributes_ = new Hashtable();
            this.attributeNames_ = new Vector();
        }
        if (this.attributes_.get(str) == null) {
            this.attributeNames_.addElement(str);
        }
        this.attributes_.put(str, str2);
        notifyObservers();
    }

    public void removeAttribute(String str) {
        Hashtable hashtable = this.attributes_;
        if (hashtable == null) {
            return;
        }
        hashtable.remove(str);
        this.attributeNames_.removeElement(str);
        notifyObservers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    public void appendChildNoChecking(Node node) {
        Element parentNode = node.getParentNode();
        if (parentNode != null) {
            parentNode.removeChildNoChecking(node);
        }
        node.insertAtEndOfLinkedList(this.lastChild_);
        if (this.firstChild_ == null) {
            this.firstChild_ = node;
        }
        node.setParentNode(this);
        this.lastChild_ = node;
        node.setOwnerDocument(getOwnerDocument());
    }

    public void appendChild(Node node) {
        if (!canHaveAsDescendent(node)) {
            node = (Element) node.clone();
        }
        appendChildNoChecking(node);
        notifyObservers();
    }

    boolean canHaveAsDescendent(Node node) {
        if (node == this) {
            return false;
        }
        Element parentNode = getParentNode();
        if (parentNode == null) {
            return true;
        }
        return parentNode.canHaveAsDescendent(node);
    }

    private boolean removeChildNoChecking(Node node) {
        for (Node node2 = this.firstChild_; node2 != null; node2 = node2.getNextSibling()) {
            if (node2.equals(node)) {
                if (this.firstChild_ == node2) {
                    this.firstChild_ = node2.getNextSibling();
                }
                if (this.lastChild_ == node2) {
                    this.lastChild_ = node2.getPreviousSibling();
                }
                node2.removeFromLinkedList();
                node2.setParentNode(null);
                node2.setOwnerDocument(null);
                return true;
            }
        }
        return false;
    }

    public void removeChild(Node node) throws DOMException {
        if (!removeChildNoChecking(node)) {
            throw new DOMException((short) 8, "Cannot find " + node + " in " + this);
        }
        notifyObservers();
    }

    public void replaceChild(Element element, Node node) throws DOMException {
        replaceChild_(element, node);
        notifyObservers();
    }

    public void replaceChild(Text text, Node node) throws DOMException {
        replaceChild_(text, node);
        notifyObservers();
    }

    private void replaceChild_(Node node, Node node2) throws DOMException {
        for (Node node3 = this.firstChild_; node3 != null; node3 = node3.getNextSibling()) {
            if (node3 == node2) {
                if (this.firstChild_ == node2) {
                    this.firstChild_ = node;
                }
                if (this.lastChild_ == node2) {
                    this.lastChild_ = node;
                }
                node2.replaceInLinkedList(node);
                node.setParentNode(this);
                node2.setParentNode(null);
                return;
            }
        }
        throw new DOMException((short) 8, "Cannot find " + node2 + " in " + this);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.p210hp.hpl.sparta.Node
    public void toString(Writer writer) throws IOException {
        for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
            node.toString(writer);
        }
    }

    @Override // com.p210hp.hpl.sparta.Node
    public void toXml(Writer writer) throws IOException {
        writer.write("<" + this.tagName_);
        Vector vector = this.attributeNames_;
        if (vector != null) {
            Enumeration elements = vector.elements();
            while (elements.hasMoreElements()) {
                String str = (String) elements.nextElement();
                writer.write(" " + str + "=\"");
                htmlEncode(writer, (String) this.attributes_.get(str));
                writer.write("\"");
            }
        }
        if (this.firstChild_ == null) {
            writer.write("/>");
            return;
        }
        writer.write(">");
        for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
            node.toXml(writer);
        }
        writer.write("</" + this.tagName_ + ">");
    }

    private XPathVisitor visitor(String str, boolean z) throws XPathException {
        XPath xPath = XPath.get(str);
        if (xPath.isStringValue() != z) {
            String str2 = z ? "evaluates to element not string" : "evaluates to string not element";
            throw new XPathException(xPath, "\"" + xPath + "\" evaluates to " + str2);
        }
        return new XPathVisitor(this, xPath);
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Enumeration xpathSelectElements(String str) throws ParseException {
        try {
            return visitor(str, false).getResultEnumeration();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Enumeration xpathSelectStrings(String str) throws ParseException {
        try {
            return visitor(str, true).getResultEnumeration();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    public boolean xpathEnsure(String str) throws ParseException {
        Element xpathSelectElement;
        try {
            if (xpathSelectElement(str) != null) {
                return false;
            }
            XPath xPath = XPath.get(str);
            Enumeration steps = xPath.getSteps();
            int i = 0;
            while (steps.hasMoreElements()) {
                steps.nextElement();
                i++;
            }
            Step[] stepArr = new Step[i - 1];
            Enumeration steps2 = xPath.getSteps();
            for (int i2 = 0; i2 < stepArr.length; i2++) {
                stepArr[i2] = (Step) steps2.nextElement();
            }
            Step step = (Step) steps2.nextElement();
            if (stepArr.length == 0) {
                xpathSelectElement = this;
            } else {
                String xPath2 = XPath.get(xPath.isAbsolute(), stepArr).toString();
                xpathEnsure(xPath2.toString());
                xpathSelectElement = xpathSelectElement(xPath2);
            }
            xpathSelectElement.appendChildNoChecking(makeMatching(xpathSelectElement, step, str));
            return true;
        } catch (XPathException e) {
            throw new ParseException(str, e);
        }
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Element xpathSelectElement(String str) throws ParseException {
        try {
            return visitor(str, false).getFirstResultElement();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    @Override // com.p210hp.hpl.sparta.Node
    public String xpathSelectString(String str) throws ParseException {
        try {
            return visitor(str, true).getFirstResultString();
        } catch (XPathException e) {
            throw new ParseException("XPath problem", e);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Element) {
            Element element = (Element) obj;
            if (this.tagName_.equals(element.tagName_)) {
                Hashtable hashtable = this.attributes_;
                int size = hashtable == null ? 0 : hashtable.size();
                Hashtable hashtable2 = element.attributes_;
                if (size != (hashtable2 == null ? 0 : hashtable2.size())) {
                    return false;
                }
                Hashtable hashtable3 = this.attributes_;
                if (hashtable3 != null) {
                    Enumeration keys = hashtable3.keys();
                    while (keys.hasMoreElements()) {
                        String str = (String) keys.nextElement();
                        if (!((String) this.attributes_.get(str)).equals((String) element.attributes_.get(str))) {
                            return false;
                        }
                    }
                }
                Node node = this.firstChild_;
                Node node2 = element.firstChild_;
                while (node != null) {
                    if (!node.equals(node2)) {
                        return false;
                    }
                    node = node.getNextSibling();
                    node2 = node2.getNextSibling();
                }
                return true;
            }
            return false;
        }
        return false;
    }

    @Override // com.p210hp.hpl.sparta.Node
    protected int computeHashCode() {
        int hashCode = this.tagName_.hashCode();
        Hashtable hashtable = this.attributes_;
        if (hashtable != null) {
            Enumeration keys = hashtable.keys();
            while (keys.hasMoreElements()) {
                String str = (String) keys.nextElement();
                hashCode = (((hashCode * 31) + str.hashCode()) * 31) + ((String) this.attributes_.get(str)).hashCode();
            }
        }
        for (Node node = this.firstChild_; node != null; node = node.getNextSibling()) {
            hashCode = (hashCode * 31) + node.hashCode();
        }
        return hashCode;
    }
}
