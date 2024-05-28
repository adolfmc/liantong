package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import java.util.List;
import okio.ByteString;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class SpriteEntity extends Message<SpriteEntity, Builder> {
    public static final ProtoAdapter<SpriteEntity> ADAPTER = new ProtoAdapter_SpriteEntity();
    public static final String DEFAULT_IMAGEKEY = "";
    public static final String DEFAULT_MATTEKEY = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.opensource.svgaplayer.proto.FrameEntity#ADAPTER", label = WireField.Label.REPEATED, tag = 2)
    public final List<FrameEntity> frames;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)
    public final String imageKey;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 3)
    public final String matteKey;

    public SpriteEntity(String str, List<FrameEntity> list, String str2) {
        this(str, list, str2, ByteString.EMPTY);
    }

    public SpriteEntity(String str, List<FrameEntity> list, String str2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.imageKey = str;
        this.frames = Internal.immutableCopyOf("frames", list);
        this.matteKey = str2;
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<SpriteEntity, Builder> newBuilder2() {
        Builder builder = new Builder();
        builder.imageKey = this.imageKey;
        builder.frames = Internal.copyOf("frames", this.frames);
        builder.matteKey = this.matteKey;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof SpriteEntity) {
            SpriteEntity spriteEntity = (SpriteEntity) obj;
            return unknownFields().equals(spriteEntity.unknownFields()) && Internal.equals(this.imageKey, spriteEntity.imageKey) && this.frames.equals(spriteEntity.frames) && Internal.equals(this.matteKey, spriteEntity.matteKey);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            int hashCode = unknownFields().hashCode() * 37;
            String str = this.imageKey;
            int hashCode2 = (((hashCode + (str != null ? str.hashCode() : 0)) * 37) + this.frames.hashCode()) * 37;
            String str2 = this.matteKey;
            int hashCode3 = hashCode2 + (str2 != null ? str2.hashCode() : 0);
            this.hashCode = hashCode3;
            return hashCode3;
        }
        return i;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.imageKey != null) {
            sb.append(", imageKey=");
            sb.append(this.imageKey);
        }
        if (!this.frames.isEmpty()) {
            sb.append(", frames=");
            sb.append(this.frames);
        }
        if (this.matteKey != null) {
            sb.append(", matteKey=");
            sb.append(this.matteKey);
        }
        StringBuilder replace = sb.replace(0, 2, "SpriteEntity{");
        replace.append('}');
        return replace.toString();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends Message.Builder<SpriteEntity, Builder> {
        public List<FrameEntity> frames = Internal.newMutableList();
        public String imageKey;
        public String matteKey;

        public Builder imageKey(String str) {
            this.imageKey = str;
            return this;
        }

        public Builder frames(List<FrameEntity> list) {
            Internal.checkElementsNotNull(list);
            this.frames = list;
            return this;
        }

        public Builder matteKey(String str) {
            this.matteKey = str;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public SpriteEntity build() {
            return new SpriteEntity(this.imageKey, this.frames, this.matteKey, super.buildUnknownFields());
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ProtoAdapter_SpriteEntity extends ProtoAdapter<SpriteEntity> {
        ProtoAdapter_SpriteEntity() {
            super(FieldEncoding.LENGTH_DELIMITED, SpriteEntity.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(SpriteEntity spriteEntity) {
            return (spriteEntity.imageKey != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, spriteEntity.imageKey) : 0) + FrameEntity.ADAPTER.asRepeated().encodedSizeWithTag(2, spriteEntity.frames) + (spriteEntity.matteKey != null ? ProtoAdapter.STRING.encodedSizeWithTag(3, spriteEntity.matteKey) : 0) + spriteEntity.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, SpriteEntity spriteEntity) throws IOException {
            if (spriteEntity.imageKey != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, spriteEntity.imageKey);
            }
            FrameEntity.ADAPTER.asRepeated().encodeWithTag(protoWriter, 2, spriteEntity.frames);
            if (spriteEntity.matteKey != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 3, spriteEntity.matteKey);
            }
            protoWriter.writeBytes(spriteEntity.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public SpriteEntity decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.imageKey(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 2:
                            builder.frames.add(FrameEntity.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.matteKey(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        default:
                            FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                            builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                            break;
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v1, types: [com.opensource.svgaplayer.proto.SpriteEntity$Builder] */
        @Override // com.squareup.wire.ProtoAdapter
        public SpriteEntity redact(SpriteEntity spriteEntity) {
            ?? newBuilder2 = spriteEntity.newBuilder2();
            Internal.redactElements(newBuilder2.frames, FrameEntity.ADAPTER);
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
