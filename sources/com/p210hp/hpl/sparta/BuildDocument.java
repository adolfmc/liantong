package com.p210hp.hpl.sparta;

/* renamed from: com.hp.hpl.sparta.BuildDocument */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
class BuildDocument implements DocumentSource, ParseHandler {
    private Element currentElement_;
    private final Document doc_;
    private final ParseLog log_;
    private ParseSource parseSource_;

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void endDocument() {
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void startDocument() {
    }

    public BuildDocument() {
        this(null);
    }

    public BuildDocument(ParseLog parseLog) {
        this.currentElement_ = null;
        this.doc_ = new Document();
        this.parseSource_ = null;
        this.log_ = parseLog == null ? DEFAULT_LOG : parseLog;
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void setParseSource(ParseSource parseSource) {
        this.parseSource_ = parseSource;
        this.doc_.setSystemId(parseSource.toString());
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public ParseSource getParseSource() {
        return this.parseSource_;
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public String toString() {
        if (this.parseSource_ != null) {
            return "BuildDoc: " + this.parseSource_.toString();
        }
        return null;
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public String getSystemId() {
        ParseSource parseSource = this.parseSource_;
        if (parseSource != null) {
            return parseSource.getSystemId();
        }
        return null;
    }

    @Override // com.p210hp.hpl.sparta.ParseSource
    public int getLineNumber() {
        ParseSource parseSource = this.parseSource_;
        if (parseSource != null) {
            return parseSource.getLineNumber();
        }
        return -1;
    }

    @Override // com.p210hp.hpl.sparta.DocumentSource
    public Document getDocument() {
        return this.doc_;
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void startElement(Element element) {
        Element element2 = this.currentElement_;
        if (element2 == null) {
            this.doc_.setDocumentElement(element);
        } else {
            element2.appendChild(element);
        }
        this.currentElement_ = element;
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void endElement(Element element) {
        this.currentElement_ = this.currentElement_.getParentNode();
    }

    @Override // com.p210hp.hpl.sparta.ParseHandler
    public void characters(char[] cArr, int i, int i2) {
        Element element = this.currentElement_;
        if (element.getLastChild() instanceof Text) {
            ((Text) element.getLastChild()).appendData(cArr, i, i2);
        } else {
            element.appendChildNoChecking(new Text(new String(cArr, i, i2)));
        }
    }
}
