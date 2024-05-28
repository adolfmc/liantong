package org.codehaus.jackson.node;

import java.util.Iterator;
import java.util.Map;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.JsonStreamContext;
import org.codehaus.jackson.JsonToken;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
abstract class NodeCursor extends JsonStreamContext {
    final NodeCursor _parent;

    public abstract boolean currentHasChildren();

    public abstract JsonNode currentNode();

    public abstract JsonToken endToken();

    @Override // org.codehaus.jackson.JsonStreamContext
    public abstract String getCurrentName();

    public abstract JsonToken nextToken();

    public abstract JsonToken nextValue();

    public NodeCursor(int i, NodeCursor nodeCursor) {
        this._type = i;
        this._index = -1;
        this._parent = nodeCursor;
    }

    @Override // org.codehaus.jackson.JsonStreamContext
    public final NodeCursor getParent() {
        return this._parent;
    }

    public final NodeCursor iterateChildren() {
        JsonNode currentNode = currentNode();
        if (currentNode == null) {
            throw new IllegalStateException("No current node");
        }
        if (currentNode.isArray()) {
            return new Array(currentNode, this);
        }
        if (currentNode.isObject()) {
            return new Object(currentNode, this);
        }
        throw new IllegalStateException("Current node of type " + currentNode.getClass().getName());
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class RootValue extends NodeCursor {
        protected boolean _done;
        JsonNode _node;

        @Override // org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return false;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        public RootValue(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(0, nodeCursor);
            this._done = false;
            this._node = jsonNode;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._done) {
                this._done = true;
                return this._node.asToken();
            }
            this._node = null;
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._node;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Array extends NodeCursor {
        Iterator<JsonNode> _contents;
        JsonNode _currentNode;

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            return null;
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        public Array(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(1, nodeCursor);
            this._contents = jsonNode.getElements();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (!this._contents.hasNext()) {
                this._currentNode = null;
                return null;
            }
            this._currentNode = this._contents.next();
            return this._currentNode.asToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            return nextToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_ARRAY;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            return this._currentNode;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static final class Object extends NodeCursor {
        Iterator<Map.Entry<String, JsonNode>> _contents;
        Map.Entry<String, JsonNode> _current;
        boolean _needEntry;

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public /* bridge */ /* synthetic */ JsonStreamContext getParent() {
            return super.getParent();
        }

        public Object(JsonNode jsonNode, NodeCursor nodeCursor) {
            super(2, nodeCursor);
            this._contents = ((ObjectNode) jsonNode).getFields();
            this._needEntry = true;
        }

        @Override // org.codehaus.jackson.node.NodeCursor, org.codehaus.jackson.JsonStreamContext
        public String getCurrentName() {
            Map.Entry<String, JsonNode> entry = this._current;
            if (entry == null) {
                return null;
            }
            return entry.getKey();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextToken() {
            if (this._needEntry) {
                if (!this._contents.hasNext()) {
                    this._current = null;
                    return null;
                }
                this._needEntry = false;
                this._current = this._contents.next();
                return JsonToken.FIELD_NAME;
            }
            this._needEntry = true;
            return this._current.getValue().asToken();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken nextValue() {
            JsonToken nextToken = nextToken();
            return nextToken == JsonToken.FIELD_NAME ? nextToken() : nextToken;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonToken endToken() {
            return JsonToken.END_OBJECT;
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public JsonNode currentNode() {
            Map.Entry<String, JsonNode> entry = this._current;
            if (entry == null) {
                return null;
            }
            return entry.getValue();
        }

        @Override // org.codehaus.jackson.node.NodeCursor
        public boolean currentHasChildren() {
            return ((ContainerNode) currentNode()).size() > 0;
        }
    }
}
