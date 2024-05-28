package io.objectbox.model;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ModelEntity extends Table {
    public static ModelEntity getRootAsModelEntity(ByteBuffer byteBuffer) {
        return getRootAsModelEntity(byteBuffer, new ModelEntity());
    }

    public static ModelEntity getRootAsModelEntity(ByteBuffer byteBuffer, ModelEntity modelEntity) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return modelEntity.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.f10465bb = byteBuffer;
    }

    public ModelEntity __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    /* renamed from: id */
    public IdUid m1958id() {
        return m1957id(new IdUid());
    }

    /* renamed from: id */
    public IdUid m1957id(IdUid idUid) {
        int __offset = __offset(4);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public String name() {
        int __offset = __offset(6);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameAsByteBuffer() {
        return __vector_as_bytebuffer(6, 1);
    }

    public ByteBuffer nameInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 6, 1);
    }

    public ModelProperty properties(int i) {
        return properties(new ModelProperty(), i);
    }

    public ModelProperty properties(ModelProperty modelProperty, int i) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return modelProperty.__assign(__indirect(__vector(__offset) + (i * 4)), this.f10465bb);
        }
        return null;
    }

    public int propertiesLength() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IdUid lastPropertyId() {
        return lastPropertyId(new IdUid());
    }

    public IdUid lastPropertyId(IdUid idUid) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public ModelRelation relations(int i) {
        return relations(new ModelRelation(), i);
    }

    public ModelRelation relations(ModelRelation modelRelation, int i) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return modelRelation.__assign(__indirect(__vector(__offset) + (i * 4)), this.f10465bb);
        }
        return null;
    }

    public int relationsLength() {
        int __offset = __offset(12);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public long flags() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return this.f10465bb.getInt(__offset + this.bb_pos) & 4294967295L;
        }
        return 0L;
    }

    public String nameSecondary() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameSecondaryAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public ByteBuffer nameSecondaryInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public static void startModelEntity(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(7);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addProperties(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(2, i, 0);
    }

    public static int createPropertiesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startPropertiesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addLastPropertyId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(3, i, 0);
    }

    public static void addRelations(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(4, i, 0);
    }

    public static int createRelationsVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startRelationsVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addFlags(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(5, (int) j, 0);
    }

    public static void addNameSecondary(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static int endModelEntity(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
