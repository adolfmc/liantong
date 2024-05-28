package org.codehaus.jackson.map.ser.std;

import java.lang.reflect.Type;
import java.util.Collection;
import org.codehaus.jackson.JsonNode;
import org.codehaus.jackson.map.BeanProperty;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.node.ObjectNode;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public abstract class StaticListSerializerBase<T extends Collection<?>> extends SerializerBase<T> {
    protected final BeanProperty _property;

    protected abstract JsonNode contentSchema();

    /* JADX INFO: Access modifiers changed from: protected */
    public StaticListSerializerBase(Class<?> cls, BeanProperty beanProperty) {
        super(cls, false);
        this._property = beanProperty;
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    public JsonNode getSchema(SerializerProvider serializerProvider, Type type) {
        ObjectNode createSchemaNode = createSchemaNode("array", true);
        createSchemaNode.put("items", contentSchema());
        return createSchemaNode;
    }
}
