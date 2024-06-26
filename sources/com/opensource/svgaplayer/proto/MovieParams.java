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
public final class MovieParams extends Message<MovieParams, Builder> {
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 3)
    public final Integer fps;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#INT32", tag = 4)
    public final Integer frames;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)
    public final Float viewBoxHeight;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)
    public final Float viewBoxWidth;
    public static final ProtoAdapter<MovieParams> ADAPTER = new ProtoAdapter_MovieParams();
    public static final Float DEFAULT_VIEWBOXWIDTH = Float.valueOf(0.0f);
    public static final Float DEFAULT_VIEWBOXHEIGHT = Float.valueOf(0.0f);
    public static final Integer DEFAULT_FPS = 0;
    public static final Integer DEFAULT_FRAMES = 0;

    public MovieParams(Float f, Float f2, Integer num, Integer num2) {
        this(f, f2, num, num2, ByteString.EMPTY);
    }

    public MovieParams(Float f, Float f2, Integer num, Integer num2, ByteString byteString) {
        super(ADAPTER, byteString);
        this.viewBoxWidth = f;
        this.viewBoxHeight = f2;
        this.fps = num;
        this.frames = num2;
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<MovieParams, Builder> newBuilder2() {
        Builder builder = new Builder();
        builder.viewBoxWidth = this.viewBoxWidth;
        builder.viewBoxHeight = this.viewBoxHeight;
        builder.fps = this.fps;
        builder.frames = this.frames;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof MovieParams) {
            MovieParams movieParams = (MovieParams) obj;
            return unknownFields().equals(movieParams.unknownFields()) && Internal.equals(this.viewBoxWidth, movieParams.viewBoxWidth) && Internal.equals(this.viewBoxHeight, movieParams.viewBoxHeight) && Internal.equals(this.fps, movieParams.fps) && Internal.equals(this.frames, movieParams.frames);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            int hashCode = unknownFields().hashCode() * 37;
            Float f = this.viewBoxWidth;
            int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
            Float f2 = this.viewBoxHeight;
            int hashCode3 = (hashCode2 + (f2 != null ? f2.hashCode() : 0)) * 37;
            Integer num = this.fps;
            int hashCode4 = (hashCode3 + (num != null ? num.hashCode() : 0)) * 37;
            Integer num2 = this.frames;
            int hashCode5 = hashCode4 + (num2 != null ? num2.hashCode() : 0);
            this.hashCode = hashCode5;
            return hashCode5;
        }
        return i;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.viewBoxWidth != null) {
            sb.append(", viewBoxWidth=");
            sb.append(this.viewBoxWidth);
        }
        if (this.viewBoxHeight != null) {
            sb.append(", viewBoxHeight=");
            sb.append(this.viewBoxHeight);
        }
        if (this.fps != null) {
            sb.append(", fps=");
            sb.append(this.fps);
        }
        if (this.frames != null) {
            sb.append(", frames=");
            sb.append(this.frames);
        }
        StringBuilder replace = sb.replace(0, 2, "MovieParams{");
        replace.append('}');
        return replace.toString();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends Message.Builder<MovieParams, Builder> {
        public Integer fps;
        public Integer frames;
        public Float viewBoxHeight;
        public Float viewBoxWidth;

        public Builder viewBoxWidth(Float f) {
            this.viewBoxWidth = f;
            return this;
        }

        public Builder viewBoxHeight(Float f) {
            this.viewBoxHeight = f;
            return this;
        }

        public Builder fps(Integer num) {
            this.fps = num;
            return this;
        }

        public Builder frames(Integer num) {
            this.frames = num;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public MovieParams build() {
            return new MovieParams(this.viewBoxWidth, this.viewBoxHeight, this.fps, this.frames, super.buildUnknownFields());
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ProtoAdapter_MovieParams extends ProtoAdapter<MovieParams> {
        ProtoAdapter_MovieParams() {
            super(FieldEncoding.LENGTH_DELIMITED, MovieParams.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(MovieParams movieParams) {
            return (movieParams.viewBoxWidth != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, movieParams.viewBoxWidth) : 0) + (movieParams.viewBoxHeight != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(2, movieParams.viewBoxHeight) : 0) + (movieParams.fps != null ? ProtoAdapter.INT32.encodedSizeWithTag(3, movieParams.fps) : 0) + (movieParams.frames != null ? ProtoAdapter.INT32.encodedSizeWithTag(4, movieParams.frames) : 0) + movieParams.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, MovieParams movieParams) throws IOException {
            if (movieParams.viewBoxWidth != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, movieParams.viewBoxWidth);
            }
            if (movieParams.viewBoxHeight != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, movieParams.viewBoxHeight);
            }
            if (movieParams.fps != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 3, movieParams.fps);
            }
            if (movieParams.frames != null) {
                ProtoAdapter.INT32.encodeWithTag(protoWriter, 4, movieParams.frames);
            }
            protoWriter.writeBytes(movieParams.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public MovieParams decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.viewBoxWidth(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 2:
                            builder.viewBoxHeight(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 3:
                            builder.fps(ProtoAdapter.INT32.decode(protoReader));
                            break;
                        case 4:
                            builder.frames(ProtoAdapter.INT32.decode(protoReader));
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
        public MovieParams redact(MovieParams movieParams) {
            Message.Builder<MovieParams, Builder> newBuilder2 = movieParams.newBuilder2();
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
