package org.codehaus.jackson.node;

import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonToken;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class ContainerNode extends BaseJsonNode {
    JsonNodeFactory _nodeFactory;

    @Override // org.codehaus.jackson.JsonNode
    public String asText() {
        return "";
    }

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract JsonToken asToken();

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract ObjectNode findParent(String str);

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract List<JsonNode> findParents(String str, List<JsonNode> list);

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract JsonNode findValue(String str);

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract List<JsonNode> findValues(String str, List<JsonNode> list);

    @Override // org.codehaus.jackson.node.BaseJsonNode, org.codehaus.jackson.JsonNode
    public abstract List<String> findValuesAsText(String str, List<String> list);

    @Override // org.codehaus.jackson.JsonNode
    public abstract JsonNode get(int i);

    @Override // org.codehaus.jackson.JsonNode
    public abstract JsonNode get(String str);

    @Override // org.codehaus.jackson.JsonNode
    public String getValueAsText() {
        return null;
    }

    @Override // org.codehaus.jackson.JsonNode
    public boolean isContainerNode() {
        return true;
    }

    public abstract ContainerNode removeAll();

    @Override // org.codehaus.jackson.JsonNode
    public abstract int size();

    /* JADX INFO: Access modifiers changed from: protected */
    public ContainerNode(JsonNodeFactory jsonNodeFactory) {
        this._nodeFactory = jsonNodeFactory;
    }

    public final ArrayNode arrayNode() {
        return this._nodeFactory.arrayNode();
    }

    public final ObjectNode objectNode() {
        return this._nodeFactory.objectNode();
    }

    public final NullNode nullNode() {
        return this._nodeFactory.nullNode();
    }

    public final BooleanNode booleanNode(boolean z) {
        return this._nodeFactory.booleanNode(z);
    }

    public final NumericNode numberNode(byte b) {
        return this._nodeFactory.numberNode(b);
    }

    public final NumericNode numberNode(short s) {
        return this._nodeFactory.numberNode(s);
    }

    public final NumericNode numberNode(int i) {
        return this._nodeFactory.numberNode(i);
    }

    public final NumericNode numberNode(long j) {
        return this._nodeFactory.numberNode(j);
    }

    public final NumericNode numberNode(float f) {
        return this._nodeFactory.numberNode(f);
    }

    public final NumericNode numberNode(double d) {
        return this._nodeFactory.numberNode(d);
    }

    public final NumericNode numberNode(BigDecimal bigDecimal) {
        return this._nodeFactory.numberNode(bigDecimal);
    }

    public final TextNode textNode(String str) {
        return this._nodeFactory.textNode(str);
    }

    public final BinaryNode binaryNode(byte[] bArr) {
        return this._nodeFactory.binaryNode(bArr);
    }

    public final BinaryNode binaryNode(byte[] bArr, int i, int i2) {
        return this._nodeFactory.binaryNode(bArr, i, i2);
    }

    public final POJONode POJONode(Object obj) {
        return this._nodeFactory.POJONode(obj);
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class NoNodesIterator implements Iterator<JsonNode> {
        static final NoNodesIterator instance = new NoNodesIterator();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        private NoNodesIterator() {
        }

        public static NoNodesIterator instance() {
            return instance;
        }

        @Override // java.util.Iterator
        public JsonNode next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class NoStringsIterator implements Iterator<String> {
        static final NoStringsIterator instance = new NoStringsIterator();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return false;
        }

        private NoStringsIterator() {
        }

        public static NoStringsIterator instance() {
            return instance;
        }

        @Override // java.util.Iterator
        public String next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new IllegalStateException();
        }
    }
}
