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
public final class FrameEntity extends Message<FrameEntity, Builder> {
    public static final ProtoAdapter<FrameEntity> ADAPTER = new ProtoAdapter_FrameEntity();
    public static final Float DEFAULT_ALPHA = Float.valueOf(0.0f);
    public static final String DEFAULT_CLIPPATH = "";
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float alpha;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 4)
    public final String clipPath;
    @WireField(adapter = "com.opensource.svgaplayer.proto.Layout#ADAPTER", tag = 2)
    public final Layout layout;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity#ADAPTER", label = WireField.Label.REPEATED, tag = 5)
    public final List<ShapeEntity> shapes;
    @WireField(adapter = "com.opensource.svgaplayer.proto.Transform#ADAPTER", tag = 3)
    public final Transform transform;

    public FrameEntity(Float f, Layout layout, Transform transform, String str, List<ShapeEntity> list) {
        this(f, layout, transform, str, list, ByteString.EMPTY);
    }

    public FrameEntity(Float f, Layout layout, Transform transform, String str, List<ShapeEntity> list, ByteString byteString) {
        super(ADAPTER, byteString);
        this.alpha = f;
        this.layout = layout;
        this.transform = transform;
        this.clipPath = str;
        this.shapes = Internal.immutableCopyOf("shapes", list);
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<FrameEntity, Builder> newBuilder2() {
        Builder builder = new Builder();
        builder.alpha = this.alpha;
        builder.layout = this.layout;
        builder.transform = this.transform;
        builder.clipPath = this.clipPath;
        builder.shapes = Internal.copyOf("shapes", this.shapes);
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof FrameEntity) {
            FrameEntity frameEntity = (FrameEntity) obj;
            return unknownFields().equals(frameEntity.unknownFields()) && Internal.equals(this.alpha, frameEntity.alpha) && Internal.equals(this.layout, frameEntity.layout) && Internal.equals(this.transform, frameEntity.transform) && Internal.equals(this.clipPath, frameEntity.clipPath) && this.shapes.equals(frameEntity.shapes);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            int hashCode = unknownFields().hashCode() * 37;
            Float f = this.alpha;
            int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
            Layout layout = this.layout;
            int hashCode3 = (hashCode2 + (layout != null ? layout.hashCode() : 0)) * 37;
            Transform transform = this.transform;
            int hashCode4 = (hashCode3 + (transform != null ? transform.hashCode() : 0)) * 37;
            String str = this.clipPath;
            int hashCode5 = ((hashCode4 + (str != null ? str.hashCode() : 0)) * 37) + this.shapes.hashCode();
            this.hashCode = hashCode5;
            return hashCode5;
        }
        return i;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.alpha != null) {
            sb.append(", alpha=");
            sb.append(this.alpha);
        }
        if (this.layout != null) {
            sb.append(", layout=");
            sb.append(this.layout);
        }
        if (this.transform != null) {
            sb.append(", transform=");
            sb.append(this.transform);
        }
        if (this.clipPath != null) {
            sb.append(", clipPath=");
            sb.append(this.clipPath);
        }
        if (!this.shapes.isEmpty()) {
            sb.append(", shapes=");
            sb.append(this.shapes);
        }
        StringBuilder replace = sb.replace(0, 2, "FrameEntity{");
        replace.append('}');
        return replace.toString();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends Message.Builder<FrameEntity, Builder> {
        public Float alpha;
        public String clipPath;
        public Layout layout;
        public List<ShapeEntity> shapes = Internal.newMutableList();
        public Transform transform;

        public Builder alpha(Float f) {
            this.alpha = f;
            return this;
        }

        public Builder layout(Layout layout) {
            this.layout = layout;
            return this;
        }

        public Builder transform(Transform transform) {
            this.transform = transform;
            return this;
        }

        public Builder clipPath(String str) {
            this.clipPath = str;
            return this;
        }

        public Builder shapes(List<ShapeEntity> list) {
            Internal.checkElementsNotNull(list);
            this.shapes = list;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public FrameEntity build() {
            return new FrameEntity(this.alpha, this.layout, this.transform, this.clipPath, this.shapes, super.buildUnknownFields());
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ProtoAdapter_FrameEntity extends ProtoAdapter<FrameEntity> {
        ProtoAdapter_FrameEntity() {
            super(FieldEncoding.LENGTH_DELIMITED, FrameEntity.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(FrameEntity frameEntity) {
            return (frameEntity.alpha != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, frameEntity.alpha) : 0) + (frameEntity.layout != null ? Layout.ADAPTER.encodedSizeWithTag(2, frameEntity.layout) : 0) + (frameEntity.transform != null ? Transform.ADAPTER.encodedSizeWithTag(3, frameEntity.transform) : 0) + (frameEntity.clipPath != null ? ProtoAdapter.STRING.encodedSizeWithTag(4, frameEntity.clipPath) : 0) + ShapeEntity.ADAPTER.asRepeated().encodedSizeWithTag(5, frameEntity.shapes) + frameEntity.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, FrameEntity frameEntity) throws IOException {
            if (frameEntity.alpha != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, frameEntity.alpha);
            }
            if (frameEntity.layout != null) {
                Layout.ADAPTER.encodeWithTag(protoWriter, 2, frameEntity.layout);
            }
            if (frameEntity.transform != null) {
                Transform.ADAPTER.encodeWithTag(protoWriter, 3, frameEntity.transform);
            }
            if (frameEntity.clipPath != null) {
                ProtoAdapter.STRING.encodeWithTag(protoWriter, 4, frameEntity.clipPath);
            }
            ShapeEntity.ADAPTER.asRepeated().encodeWithTag(protoWriter, 5, frameEntity.shapes);
            protoWriter.writeBytes(frameEntity.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public FrameEntity decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.alpha(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 2:
                            builder.layout(Layout.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.transform(Transform.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.clipPath(ProtoAdapter.STRING.decode(protoReader));
                            break;
                        case 5:
                            builder.shapes.add(ShapeEntity.ADAPTER.decode(protoReader));
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

        /* JADX WARN: Type inference failed for: r3v1, types: [com.opensource.svgaplayer.proto.FrameEntity$Builder] */
        @Override // com.squareup.wire.ProtoAdapter
        public FrameEntity redact(FrameEntity frameEntity) {
            ?? newBuilder2 = frameEntity.newBuilder2();
            if (newBuilder2.layout != null) {
                newBuilder2.layout = Layout.ADAPTER.redact(newBuilder2.layout);
            }
            if (newBuilder2.transform != null) {
                newBuilder2.transform = Transform.ADAPTER.redact(newBuilder2.transform);
            }
            Internal.redactElements(newBuilder2.shapes, ShapeEntity.ADAPTER);
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
