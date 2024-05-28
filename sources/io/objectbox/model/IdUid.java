package io.objectbox.model;

import com.google.flatbuffers.FlatBufferBuilder;
import com.google.flatbuffers.Struct;
import java.nio.ByteBuffer;

/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public final class IdUid extends Struct {
    public void __init(int i, ByteBuffer byteBuffer) {
        this.bb_pos = i;
        this.f10464bb = byteBuffer;
    }

    public IdUid __assign(int i, ByteBuffer byteBuffer) {
        __init(i, byteBuffer);
        return this;
    }

    /* renamed from: id */
    public long m1959id() {
        return this.f10464bb.getInt(this.bb_pos + 0) & 4294967295L;
    }

    public long uid() {
        return this.f10464bb.getLong(this.bb_pos + 8);
    }

    public static int createIdUid(FlatBufferBuilder flatBufferBuilder, long j, long j2) {
        flatBufferBuilder.prep(8, 16);
        flatBufferBuilder.putLong(j2);
        flatBufferBuilder.pad(4);
        flatBufferBuilder.putInt((int) j);
        return flatBufferBuilder.offset();
    }
}
