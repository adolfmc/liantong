package io.objectbox.model;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ModelRelation extends Table {
    public static ModelRelation getRootAsModelRelation(ByteBuffer byteBuffer) {
        return getRootAsModelRelation(byteBuffer, new ModelRelation());
    }

    public static ModelRelation getRootAsModelRelation(ByteBuffer byteBuffer, ModelRelation modelRelation) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return modelRelation.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.f10465bb = byteBuffer;
    }

    public ModelRelation __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    /* renamed from: id */
    public IdUid m1954id() {
        return m1953id(new IdUid());
    }

    /* renamed from: id */
    public IdUid m1953id(IdUid idUid) {
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

    public IdUid targetEntityId() {
        return targetEntityId(new IdUid());
    }

    public IdUid targetEntityId(IdUid idUid) {
        int __offset = __offset(8);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public static void startModelRelation(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(3);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addTargetEntityId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(2, i, 0);
    }

    public static int endModelRelation(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
