package com.p210hp.hpl.sparta;

import java.io.IOException;
import java.io.Writer;
import java.util.Enumeration;

/* renamed from: com.hp.hpl.sparta.Text */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Text extends Node {
    private StringBuffer text_;

    public Text(String str) {
        this.text_ = new StringBuffer(str);
    }

    public Text(char c) {
        this.text_ = new StringBuffer();
        this.text_.append(c);
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Object clone() {
        return new Text(this.text_.toString());
    }

    public void appendData(String str) {
        this.text_.append(str);
        notifyObservers();
    }

    public void appendData(char c) {
        this.text_.append(c);
        notifyObservers();
    }

    public void appendData(char[] cArr, int i, int i2) {
        this.text_.append(cArr, i, i2);
        notifyObservers();
    }

    public String getData() {
        return this.text_.toString();
    }

    public void setData(String str) {
        this.text_ = new StringBuffer(str);
        notifyObservers();
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.p210hp.hpl.sparta.Node
    public void toXml(Writer writer) throws IOException {
        String stringBuffer = this.text_.toString();
        if (stringBuffer.length() < 50) {
            htmlEncode(writer, stringBuffer);
            return;
        }
        writer.write("<![CDATA[");
        writer.write(stringBuffer);
        writer.write("]]>");
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    @Override // com.p210hp.hpl.sparta.Node
    public void toString(Writer writer) throws IOException {
        writer.write(this.text_.toString());
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Enumeration xpathSelectElements(String str) {
        throw new Error("Sorry, not implemented");
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Enumeration xpathSelectStrings(String str) {
        throw new Error("Sorry, not implemented");
    }

    @Override // com.p210hp.hpl.sparta.Node
    public Element xpathSelectElement(String str) {
        throw new Error("Sorry, not implemented");
    }

    @Override // com.p210hp.hpl.sparta.Node
    public String xpathSelectString(String str) {
        throw new Error("Sorry, not implemented");
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instanceof Text) {
            return this.text_.toString().equals(((Text) obj).text_.toString());
        }
        return false;
    }

    @Override // com.p210hp.hpl.sparta.Node
    protected int computeHashCode() {
        return this.text_.toString().hashCode();
    }
}
