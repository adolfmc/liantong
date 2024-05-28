package org.codehaus.jackson.map.ext;

import java.io.StringReader;
import javax.xml.parsers.DocumentBuilderFactory;
import org.codehaus.jackson.map.DeserializationContext;
import org.codehaus.jackson.map.deser.std.FromStringDeserializer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.InputSource;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class DOMDeserializer<T> extends FromStringDeserializer<T> {
    static final DocumentBuilderFactory _parserFactory = DocumentBuilderFactory.newInstance();

    @Override // org.codehaus.jackson.map.deser.std.FromStringDeserializer
    public abstract T _deserialize(String str, DeserializationContext deserializationContext);

    static {
        _parserFactory.setNamespaceAware(true);
    }

    protected DOMDeserializer(Class<T> cls) {
        super(cls);
    }

    protected final Document parse(String str) throws IllegalArgumentException {
        try {
            return _parserFactory.newDocumentBuilder().parse(new InputSource(new StringReader(str)));
        } catch (Exception e) {
            throw new IllegalArgumentException("Failed to parse JSON String as XML: " + e.getMessage(), e);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class NodeDeserializer extends DOMDeserializer<Node> {
        public NodeDeserializer() {
            super(Node.class);
        }

        @Override // org.codehaus.jackson.map.ext.DOMDeserializer, org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Node _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return parse(str);
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class DocumentDeserializer extends DOMDeserializer<Document> {
        public DocumentDeserializer() {
            super(Document.class);
        }

        @Override // org.codehaus.jackson.map.ext.DOMDeserializer, org.codehaus.jackson.map.deser.std.FromStringDeserializer
        public Document _deserialize(String str, DeserializationContext deserializationContext) throws IllegalArgumentException {
            return parse(str);
        }
    }
}
