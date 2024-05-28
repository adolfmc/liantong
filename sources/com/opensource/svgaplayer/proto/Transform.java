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
public final class Transform extends Message<Transform, Builder> {
    public static final ProtoAdapter<Transform> ADAPTER = new ProtoAdapter_Transform();
    public static final Float DEFAULT_A = Float.valueOf(0.0f);
    public static final Float DEFAULT_B = Float.valueOf(0.0f);
    public static final Float DEFAULT_C = Float.valueOf(0.0f);
    public static final Float DEFAULT_D = Float.valueOf(0.0f);
    public static final Float DEFAULT_TX = Float.valueOf(0.0f);
    public static final Float DEFAULT_TY = Float.valueOf(0.0f);
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)

    /* renamed from: a */
    public final Float f17720a;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)

    /* renamed from: b */
    public final Float f17721b;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)

    /* renamed from: c */
    public final Float f17722c;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)

    /* renamed from: d */
    public final Float f17723d;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 5)

    /* renamed from: tx */
    public final Float f17724tx;
    @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 6)

    /* renamed from: ty */
    public final Float f17725ty;

    public Transform(Float f, Float f2, Float f3, Float f4, Float f5, Float f6) {
        this(f, f2, f3, f4, f5, f6, ByteString.EMPTY);
    }

    public Transform(Float f, Float f2, Float f3, Float f4, Float f5, Float f6, ByteString byteString) {
        super(ADAPTER, byteString);
        this.f17720a = f;
        this.f17721b = f2;
        this.f17722c = f3;
        this.f17723d = f4;
        this.f17724tx = f5;
        this.f17725ty = f6;
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<Transform, Builder> newBuilder2() {
        Builder builder = new Builder();
        builder.f17726a = this.f17720a;
        builder.f17727b = this.f17721b;
        builder.f17728c = this.f17722c;
        builder.f17729d = this.f17723d;
        builder.f17730tx = this.f17724tx;
        builder.f17731ty = this.f17725ty;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Transform) {
            Transform transform = (Transform) obj;
            return unknownFields().equals(transform.unknownFields()) && Internal.equals(this.f17720a, transform.f17720a) && Internal.equals(this.f17721b, transform.f17721b) && Internal.equals(this.f17722c, transform.f17722c) && Internal.equals(this.f17723d, transform.f17723d) && Internal.equals(this.f17724tx, transform.f17724tx) && Internal.equals(this.f17725ty, transform.f17725ty);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            int hashCode = unknownFields().hashCode() * 37;
            Float f = this.f17720a;
            int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
            Float f2 = this.f17721b;
            int hashCode3 = (hashCode2 + (f2 != null ? f2.hashCode() : 0)) * 37;
            Float f3 = this.f17722c;
            int hashCode4 = (hashCode3 + (f3 != null ? f3.hashCode() : 0)) * 37;
            Float f4 = this.f17723d;
            int hashCode5 = (hashCode4 + (f4 != null ? f4.hashCode() : 0)) * 37;
            Float f5 = this.f17724tx;
            int hashCode6 = (hashCode5 + (f5 != null ? f5.hashCode() : 0)) * 37;
            Float f6 = this.f17725ty;
            int hashCode7 = hashCode6 + (f6 != null ? f6.hashCode() : 0);
            this.hashCode = hashCode7;
            return hashCode7;
        }
        return i;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.f17720a != null) {
            sb.append(", a=");
            sb.append(this.f17720a);
        }
        if (this.f17721b != null) {
            sb.append(", b=");
            sb.append(this.f17721b);
        }
        if (this.f17722c != null) {
            sb.append(", c=");
            sb.append(this.f17722c);
        }
        if (this.f17723d != null) {
            sb.append(", d=");
            sb.append(this.f17723d);
        }
        if (this.f17724tx != null) {
            sb.append(", tx=");
            sb.append(this.f17724tx);
        }
        if (this.f17725ty != null) {
            sb.append(", ty=");
            sb.append(this.f17725ty);
        }
        StringBuilder replace = sb.replace(0, 2, "Transform{");
        replace.append('}');
        return replace.toString();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends Message.Builder<Transform, Builder> {

        /* renamed from: a */
        public Float f17726a;

        /* renamed from: b */
        public Float f17727b;

        /* renamed from: c */
        public Float f17728c;

        /* renamed from: d */
        public Float f17729d;

        /* renamed from: tx */
        public Float f17730tx;

        /* renamed from: ty */
        public Float f17731ty;

        /* renamed from: a */
        public Builder m8353a(Float f) {
            this.f17726a = f;
            return this;
        }

        /* renamed from: b */
        public Builder m8352b(Float f) {
            this.f17727b = f;
            return this;
        }

        /* renamed from: c */
        public Builder m8351c(Float f) {
            this.f17728c = f;
            return this;
        }

        /* renamed from: d */
        public Builder m8350d(Float f) {
            this.f17729d = f;
            return this;
        }

        /* renamed from: tx */
        public Builder m8349tx(Float f) {
            this.f17730tx = f;
            return this;
        }

        /* renamed from: ty */
        public Builder m8348ty(Float f) {
            this.f17731ty = f;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public Transform build() {
            return new Transform(this.f17726a, this.f17727b, this.f17728c, this.f17729d, this.f17730tx, this.f17731ty, super.buildUnknownFields());
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ProtoAdapter_Transform extends ProtoAdapter<Transform> {
        ProtoAdapter_Transform() {
            super(FieldEncoding.LENGTH_DELIMITED, Transform.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(Transform transform) {
            return (transform.f17720a != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, transform.f17720a) : 0) + (transform.f17721b != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(2, transform.f17721b) : 0) + (transform.f17722c != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(3, transform.f17722c) : 0) + (transform.f17723d != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(4, transform.f17723d) : 0) + (transform.f17724tx != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(5, transform.f17724tx) : 0) + (transform.f17725ty != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(6, transform.f17725ty) : 0) + transform.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, Transform transform) throws IOException {
            if (transform.f17720a != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, transform.f17720a);
            }
            if (transform.f17721b != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, transform.f17721b);
            }
            if (transform.f17722c != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, transform.f17722c);
            }
            if (transform.f17723d != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, transform.f17723d);
            }
            if (transform.f17724tx != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 5, transform.f17724tx);
            }
            if (transform.f17725ty != null) {
                ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 6, transform.f17725ty);
            }
            protoWriter.writeBytes(transform.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public Transform decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            builder.m8353a(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 2:
                            builder.m8352b(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 3:
                            builder.m8351c(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 4:
                            builder.m8350d(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 5:
                            builder.m8349tx(ProtoAdapter.FLOAT.decode(protoReader));
                            break;
                        case 6:
                            builder.m8348ty(ProtoAdapter.FLOAT.decode(protoReader));
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
        public Transform redact(Transform transform) {
            Message.Builder<Transform, Builder> newBuilder2 = transform.newBuilder2();
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
