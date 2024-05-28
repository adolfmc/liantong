package org.codehaus.jackson.map.ser.std;

import java.io.IOException;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.map.JsonSerializableWithType;
import org.codehaus.jackson.map.SerializerProvider;
import org.codehaus.jackson.map.TypeSerializer;
import org.codehaus.jackson.map.annotate.JacksonStdImpl;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
@JacksonStdImpl
/* loaded from: E:\9227576_dexfile_execute.dex */
public class SerializableWithTypeSerializer extends SerializerBase<JsonSerializableWithType> {
    public static final SerializableWithTypeSerializer instance = new SerializableWithTypeSerializer();

    /* JADX INFO: Access modifiers changed from: protected */
    public SerializableWithTypeSerializer() {
        super(JsonSerializableWithType.class);
    }

    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.map.JsonSerializer
    public void serialize(JsonSerializableWithType jsonSerializableWithType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException, JsonGenerationException {
        jsonSerializableWithType.serialize(jsonGenerator, serializerProvider);
    }

    @Override // org.codehaus.jackson.map.JsonSerializer
    public final void serializeWithType(JsonSerializableWithType jsonSerializableWithType, JsonGenerator jsonGenerator, SerializerProvider serializerProvider, TypeSerializer typeSerializer) throws IOException, JsonGenerationException {
        jsonSerializableWithType.serializeWithType(jsonGenerator, serializerProvider, typeSerializer);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x004c A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0068 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    @Override // org.codehaus.jackson.map.ser.std.SerializerBase, org.codehaus.jackson.schema.SchemaAware
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public org.codehaus.jackson.JsonNode getSchema(org.codehaus.jackson.map.SerializerProvider r6, java.lang.reflect.Type r7) throws org.codehaus.jackson.map.JsonMappingException {
        /*
            r5 = this;
            org.codehaus.jackson.node.ObjectNode r6 = r5.createObjectNode()
            java.lang.String r0 = "any"
            r1 = 0
            if (r7 == 0) goto L44
            java.lang.Class r7 = org.codehaus.jackson.map.type.TypeFactory.rawClass(r7)
            java.lang.Class<org.codehaus.jackson.schema.JsonSerializableSchema> r2 = org.codehaus.jackson.schema.JsonSerializableSchema.class
            boolean r2 = r7.isAnnotationPresent(r2)
            if (r2 == 0) goto L44
            java.lang.Class<org.codehaus.jackson.schema.JsonSerializableSchema> r0 = org.codehaus.jackson.schema.JsonSerializableSchema.class
            java.lang.annotation.Annotation r7 = r7.getAnnotation(r0)
            org.codehaus.jackson.schema.JsonSerializableSchema r7 = (org.codehaus.jackson.schema.JsonSerializableSchema) r7
            java.lang.String r0 = r7.schemaType()
            java.lang.String r2 = "##irrelevant"
            java.lang.String r3 = r7.schemaObjectPropertiesDefinition()
            boolean r2 = r2.equals(r3)
            if (r2 != 0) goto L32
            java.lang.String r2 = r7.schemaObjectPropertiesDefinition()
            goto L33
        L32:
            r2 = r1
        L33:
            java.lang.String r3 = "##irrelevant"
            java.lang.String r4 = r7.schemaItemDefinition()
            boolean r3 = r3.equals(r4)
            if (r3 != 0) goto L45
            java.lang.String r1 = r7.schemaItemDefinition()
            goto L45
        L44:
            r2 = r1
        L45:
            java.lang.String r7 = "type"
            r6.put(r7, r0)
            if (r2 == 0) goto L66
            java.lang.String r7 = "properties"
            org.codehaus.jackson.map.ObjectMapper r0 = new org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L5f
            r0.<init>()     // Catch: java.io.IOException -> L5f
            java.lang.Class<org.codehaus.jackson.JsonNode> r3 = org.codehaus.jackson.JsonNode.class
            java.lang.Object r0 = r0.readValue(r2, r3)     // Catch: java.io.IOException -> L5f
            org.codehaus.jackson.JsonNode r0 = (org.codehaus.jackson.JsonNode) r0     // Catch: java.io.IOException -> L5f
            r6.put(r7, r0)     // Catch: java.io.IOException -> L5f
            goto L66
        L5f:
            r6 = move-exception
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r6)
            throw r7
        L66:
            if (r1 == 0) goto L82
            java.lang.String r7 = "items"
            org.codehaus.jackson.map.ObjectMapper r0 = new org.codehaus.jackson.map.ObjectMapper     // Catch: java.io.IOException -> L7b
            r0.<init>()     // Catch: java.io.IOException -> L7b
            java.lang.Class<org.codehaus.jackson.JsonNode> r2 = org.codehaus.jackson.JsonNode.class
            java.lang.Object r0 = r0.readValue(r1, r2)     // Catch: java.io.IOException -> L7b
            org.codehaus.jackson.JsonNode r0 = (org.codehaus.jackson.JsonNode) r0     // Catch: java.io.IOException -> L7b
            r6.put(r7, r0)     // Catch: java.io.IOException -> L7b
            goto L82
        L7b:
            r6 = move-exception
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            r7.<init>(r6)
            throw r7
        L82:
            return r6
        */
        throw new UnsupportedOperationException("Method not decompiled: org.codehaus.jackson.map.ser.std.SerializableWithTypeSerializer.getSchema(org.codehaus.jackson.map.SerializerProvider, java.lang.reflect.Type):org.codehaus.jackson.JsonNode");
    }
}
