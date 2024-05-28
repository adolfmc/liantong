package io.objectbox.model;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Table;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class ModelProperty extends Table {
    public static ModelProperty getRootAsModelProperty(ByteBuffer byteBuffer) {
        return getRootAsModelProperty(byteBuffer, new ModelProperty());
    }

    public static ModelProperty getRootAsModelProperty(ByteBuffer byteBuffer, ModelProperty modelProperty) {
        byteBuffer.order(ByteOrder.LITTLE_ENDIAN);
        return modelProperty.__assign(byteBuffer.getInt(byteBuffer.position()) + byteBuffer.position(), byteBuffer);
    }

    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.f10465bb = byteBuffer;
    }

    public ModelProperty __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    /* renamed from: id */
    public IdUid m1956id() {
        return m1955id(new IdUid());
    }

    /* renamed from: id */
    public IdUid m1955id(IdUid idUid) {
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

    public int type() {
        int __offset = __offset(8);
        if (__offset != 0) {
            return this.f10465bb.getShort(__offset + this.bb_pos) & 65535;
        }
        return 0;
    }

    public long flags() {
        int __offset = __offset(10);
        if (__offset != 0) {
            return this.f10465bb.getInt(__offset + this.bb_pos) & 4294967295L;
        }
        return 0L;
    }

    public IdUid indexId() {
        return indexId(new IdUid());
    }

    public IdUid indexId(IdUid idUid) {
        int __offset = __offset(12);
        if (__offset != 0) {
            return idUid.__assign(__offset + this.bb_pos, this.f10465bb);
        }
        return null;
    }

    public String targetEntity() {
        int __offset = __offset(14);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer targetEntityAsByteBuffer() {
        return __vector_as_bytebuffer(14, 1);
    }

    public ByteBuffer targetEntityInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 14, 1);
    }

    public String virtualTarget() {
        int __offset = __offset(16);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer virtualTargetAsByteBuffer() {
        return __vector_as_bytebuffer(16, 1);
    }

    public ByteBuffer virtualTargetInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 16, 1);
    }

    public String nameSecondary() {
        int __offset = __offset(18);
        if (__offset != 0) {
            return __string(__offset + this.bb_pos);
        }
        return null;
    }

    public ByteBuffer nameSecondaryAsByteBuffer() {
        return __vector_as_bytebuffer(18, 1);
    }

    public ByteBuffer nameSecondaryInByteBuffer(ByteBuffer byteBuffer) {
        return __vector_in_bytebuffer(byteBuffer, 18, 1);
    }

    public long maxIndexValueLength() {
        int __offset = __offset(20);
        if (__offset != 0) {
            return this.f10465bb.getInt(__offset + this.bb_pos) & 4294967295L;
        }
        return 0L;
    }

    public static void startModelProperty(FlatBufferBuilder flatBufferBuilder) {
        flatBufferBuilder.startObject(9);
    }

    public static void addId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(0, i, 0);
    }

    public static void addName(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(1, i, 0);
    }

    public static void addType(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addShort(2, (short) i, 0);
    }

    public static void addFlags(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(3, (int) j, 0);
    }

    public static void addIndexId(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addStruct(4, i, 0);
    }

    public static void addTargetEntity(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(5, i, 0);
    }

    public static void addVirtualTarget(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(6, i, 0);
    }

    public static void addNameSecondary(FlatBufferBuilder flatBufferBuilder, int i) {
        flatBufferBuilder.addOffset(7, i, 0);
    }

    public static void addMaxIndexValueLength(FlatBufferBuilder flatBufferBuilder, long j) {
        flatBufferBuilder.addInt(8, (int) j, 0);
    }

    public static int endModelProperty(FlatBufferBuilder flatBufferBuilder) {
        return flatBufferBuilder.endObject();
    }
}
