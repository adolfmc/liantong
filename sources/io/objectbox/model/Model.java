package io.objectbox.model;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class Model extends Table {
    public static Model getRootAsModel(ByteBuffer byteBuffer) {
        return getRootAsModel(byteBuffer, new Model());
    }

    public static Model getRootAsModel(ByteBuffer byteBuffer, Model model) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return model.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.f10465bb = byteBuffer;
    }

    public Model __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    public long modelVersion() {
        int __offset = __offset(4);
        if (__offset != 0) {
            return this.f10465bb.getInt(__offset + this.bb_pos) & 4294967295L;
        }
        return 0L;
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

    public long version() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.f10465bb.getLong(__offset + this.bb_pos);
        }
        return 0L;
    }

    public ModelEntity entities(int i) {
        return entities(new ModelEntity(), i);
    }

    public ModelEntity entities(ModelEntity modelEntity, int i) {
        int __offset = __offset(10);
        if (__offset != 0) {
            return modelEntity.__assign(__indirect(__vector(__offset) + (i * 4)), this.f10465bb);
        }
        return null;
    }

    public int entitiesLength() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return __vector_len(__offset);
        }
        return 0;
    }

    public IdUid lastEntityId() {
        return lastEntityId(new IdUid());
    }

    public IdUid lastEntityId(IdUid idUid) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public IdUid lastIndexId() {
        return lastIndexId(new IdUid());
    }

    public IdUid lastIndexId(IdUid idUid) {
        int __offset = __offset(14);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public IdUid lastSequenceId() {
        return lastSequenceId(new IdUid());
    }

    public IdUid lastSequenceId(IdUid idUid) {
        int __offset = __offset(16);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public IdUid lastRelationId() {
        return lastRelationId(new IdUid());
    }

    public IdUid lastRelationId(IdUid idUid) {
        int __offset = __offset(18);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public static void startModel(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(8);
    }

    public static void addModelVersion(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(0, (int) j, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addVersion(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addLong(2, j, 0L);
    }

    public static void addEntities(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(3, i, 0);
    }

    public static int createEntitiesVector(FlatBufferBuilder flatBufferBuilder, int[] iArr) {
        flatBufferBuilder.startVector(4, iArr.length, 4);
        for (int length = iArr.length - 1; length >= 0; length--) {
            flatBufferBuilder.addOffset(iArr[length]);
        }
        return flatBufferBuilder.endVector();
    }

    public static void startEntitiesVector(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.startVector(4, i, 4);
    }

    public static void addLastEntityId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(4, i, 0);
    }

    public static void addLastIndexId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(5, i, 0);
    }

    public static void addLastSequenceId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(6, i, 0);
    }

    public static void addLastRelationId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(7, i, 0);
    }

    public static int endModel(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }

    public static void finishModelBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finish(i);
    }

    public static void finishSizePrefixedModelBuffer(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.finishSizePrefixed(i);
    }
}
