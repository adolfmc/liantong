package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import okio.ByteString;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class Layout extends Message<Layout, Builder> {
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
    public final Float height;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
    public final Float width;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)

    /* renamed from: x */
    public final Float f17698x;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)

    /* renamed from: y */
    public final Float f17699y;
    public static final ProtoAdapter<Layout> ADAPTER = new ProtoAdapter_Layout();
    public static final Float DEFAULT_X = Float.valueOf(0.0f);
    public static final Float DEFAULT_Y = Float.valueOf(0.0f);
    public static final Float DEFAULT_WIDTH = Float.valueOf(0.0f);
    public static final Float DEFAULT_HEIGHT = Float.valueOf(0.0f);

    public Layout(Float f, Float f2, Float f3, Float f4) {
        this(f, f2, f3, f4, ByteString.EMPTY);
    }

    public Layout(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
        super(ADAPTER, byteString);
        this.f17698x = f;
        this.f17699y = f2;
        this.width = f3;
        this.height = f4;
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<Layout, Builder> newBuilder2() {
        Builder builder = new Builder();
        builder.f17700x = this.f17698x;
        builder.f17701y = this.f17699y;
        builder.width = this.width;
        builder.height = this.height;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Layout) {
            Layout layout = (Layout) obj;
            return unknownFields().equals(layout.unknownFields()) && Internal.equals(this.f17698x, layout.f17698x) && Internal.equals(this.f17699y, layout.f17699y) && Internal.equals(this.width, layout.width) && Internal.equals(this.height, layout.height);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            int hashCode = unknownFields().hashCode() * 37;
            Float f = this.f17698x;
            int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
            Float f2 = this.f17699y;
            int hashCode3 = (hashCode2 + (f2 != null ? f2.hashCode() : 0)) * 37;
            Float f3 = this.width;
            int hashCode4 = (hashCode3 + (f3 != null ? f3.hashCode() : 0)) * 37;
            Float f4 = this.height;
            int hashCode5 = hashCode4 + (f4 != null ? f4.hashCode() : 0);
            this.hashCode = hashCode5;
            return hashCode5;
        }
        return i;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f17698x != null) {
            sb.append(", x=");
            sb.append(this.f17698x);
        }
        if (this.f17699y != null) {
            sb.append(", y=");
            sb.append(this.f17699y);
        }
        if (this.width != null) {
            sb.append(", width=");
            sb.append(this.width);
        }
        if (this.height != null) {
            sb.append(", height=");
            sb.append(this.height);
        }
        StringBuilder replace = sb.replace(0, 2, "Layout{");
        replace.append('}');
        return replace.toString();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends Message.Builder<Layout, Builder> {
        public Float height;
        public Float width;

        /* renamed from: x */
        public Float f17700x;

        /* renamed from: y */
        public Float f17701y;

        /* renamed from: x */
        public Builder m8364x(Float f) {
            this.f17700x = f;
            return this;
        }

        /* renamed from: y */
        public Builder m8363y(Float f) {
            this.f17701y = f;
            return this;
        }

        public Builder width(Float f) {
            this.width = f;
            return this;
        }

        public Builder height(Float f) {
            this.height = f;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public Layout build() {
            return new Layout(this.f17700x, this.f17701y, this.width, this.height, super.buildUnknownFields());
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ProtoAdapter_Layout extends ProtoAdapter<Layout> {
        ProtoAdapter_Layout() {
            super(FieldEncoding.LENGTH_DELIMITED, Layout.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(Layout layout) {
            return (layout.f17698x != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, layout.f17698x) : 0) + (layout.f17699y != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(2, layout.f17699y) : 0) + (layout.width != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(3, layout.width) : 0) + (layout.height != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(4, layout.height) : 0) + layout.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Layout layout) throws IOException {
            if (layout.f17698x != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, layout.f17698x);
            }
            if (layout.f17699y != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, layout.f17699y);
            }
            if (layout.width != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, layout.width);
            }
            if (layout.height != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, layout.height);
            }
            protoWriter.writeBytes(layout.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public Layout decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.m8364x(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 2:
                            builder.m8363y(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 3:
                            builder.width(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 4:
                            builder.height(ProtoAdapter.FLOAT.decode(protoReader));
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

        @Override // com.squareup.wire.ProtoAdapter
        public Layout redact(Layout layout) {
            Message.Builder<Layout, Builder> newBuilder2 = layout.newBuilder2();
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
