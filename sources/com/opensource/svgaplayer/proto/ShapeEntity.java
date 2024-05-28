package com.opensource.svgaplayer.proto;

import com.squareup.wire.FieldEncoding;
import com.squareup.wire.Message;
import com.squareup.wire.ProtoAdapter;
import com.squareup.wire.ProtoReader;
import com.squareup.wire.ProtoWriter;
import com.squareup.wire.WireEnum;
import com.squareup.wire.WireField;
import com.squareup.wire.internal.Internal;
import java.io.IOException;
import okio.ByteString;

/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public final class ShapeEntity extends Message<ShapeEntity, Builder> {
    public static final ProtoAdapter<ShapeEntity> ADAPTER = new ProtoAdapter_ShapeEntity();
    public static final ShapeType DEFAULT_TYPE = ShapeType.SHAPE;
    private static final long serialVersionUID = 0;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$EllipseArgs#ADAPTER", tag = 4)
    public final EllipseArgs ellipse;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$RectArgs#ADAPTER", tag = 3)
    public final RectArgs rect;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeArgs#ADAPTER", tag = 2)
    public final ShapeArgs shape;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle#ADAPTER", tag = 10)
    public final ShapeStyle styles;
    @WireField(adapter = "com.opensource.svgaplayer.proto.Transform#ADAPTER", tag = 11)
    public final Transform transform;
    @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeType#ADAPTER", tag = 1)
    public final ShapeType type;

    public ShapeEntity(ShapeType shapeType, ShapeStyle shapeStyle, Transform transform, ShapeArgs shapeArgs, RectArgs rectArgs, EllipseArgs ellipseArgs) {
        this(shapeType, shapeStyle, transform, shapeArgs, rectArgs, ellipseArgs, ByteString.EMPTY);
    }

    public ShapeEntity(ShapeType shapeType, ShapeStyle shapeStyle, Transform transform, ShapeArgs shapeArgs, RectArgs rectArgs, EllipseArgs ellipseArgs, ByteString byteString) {
        super(ADAPTER, byteString);
        if (Internal.countNonNull(shapeArgs, rectArgs, ellipseArgs) > 1) {
            throw new IllegalArgumentException("at most one of shape, rect, ellipse may be non-null");
        }
        this.type = shapeType;
        this.styles = shapeStyle;
        this.transform = transform;
        this.shape = shapeArgs;
        this.rect = rectArgs;
        this.ellipse = ellipseArgs;
    }

    @Override // com.squareup.wire.Message
    /* renamed from: newBuilder */
    public Message.Builder<ShapeEntity, Builder> newBuilder2() {
        Builder builder = new Builder();
        builder.type = this.type;
        builder.styles = this.styles;
        builder.transform = this.transform;
        builder.shape = this.shape;
        builder.rect = this.rect;
        builder.ellipse = this.ellipse;
        builder.addUnknownFields(unknownFields());
        return builder;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ShapeEntity) {
            ShapeEntity shapeEntity = (ShapeEntity) obj;
            return unknownFields().equals(shapeEntity.unknownFields()) && Internal.equals(this.type, shapeEntity.type) && Internal.equals(this.styles, shapeEntity.styles) && Internal.equals(this.transform, shapeEntity.transform) && Internal.equals(this.shape, shapeEntity.shape) && Internal.equals(this.rect, shapeEntity.rect) && Internal.equals(this.ellipse, shapeEntity.ellipse);
        }
        return false;
    }

    public int hashCode() {
        int i = this.hashCode;
        if (i == 0) {
            int hashCode = unknownFields().hashCode() * 37;
            ShapeType shapeType = this.type;
            int hashCode2 = (hashCode + (shapeType != null ? shapeType.hashCode() : 0)) * 37;
            ShapeStyle shapeStyle = this.styles;
            int hashCode3 = (hashCode2 + (shapeStyle != null ? shapeStyle.hashCode() : 0)) * 37;
            Transform transform = this.transform;
            int hashCode4 = (hashCode3 + (transform != null ? transform.hashCode() : 0)) * 37;
            ShapeArgs shapeArgs = this.shape;
            int hashCode5 = (hashCode4 + (shapeArgs != null ? shapeArgs.hashCode() : 0)) * 37;
            RectArgs rectArgs = this.rect;
            int hashCode6 = (hashCode5 + (rectArgs != null ? rectArgs.hashCode() : 0)) * 37;
            EllipseArgs ellipseArgs = this.ellipse;
            int hashCode7 = hashCode6 + (ellipseArgs != null ? ellipseArgs.hashCode() : 0);
            this.hashCode = hashCode7;
            return hashCode7;
        }
        return i;
    }

    @Override // com.squareup.wire.Message
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.type != null) {
            sb.append(", type=");
            sb.append(this.type);
        }
        if (this.styles != null) {
            sb.append(", styles=");
            sb.append(this.styles);
        }
        if (this.transform != null) {
            sb.append(", transform=");
            sb.append(this.transform);
        }
        if (this.shape != null) {
            sb.append(", shape=");
            sb.append(this.shape);
        }
        if (this.rect != null) {
            sb.append(", rect=");
            sb.append(this.rect);
        }
        if (this.ellipse != null) {
            sb.append(", ellipse=");
            sb.append(this.ellipse);
        }
        StringBuilder replace = sb.replace(0, 2, "ShapeEntity{");
        replace.append('}');
        return replace.toString();
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class Builder extends Message.Builder<ShapeEntity, Builder> {
        public EllipseArgs ellipse;
        public RectArgs rect;
        public ShapeArgs shape;
        public ShapeStyle styles;
        public Transform transform;
        public ShapeType type;

        public Builder type(ShapeType shapeType) {
            this.type = shapeType;
            return this;
        }

        public Builder styles(ShapeStyle shapeStyle) {
            this.styles = shapeStyle;
            return this;
        }

        public Builder transform(Transform transform) {
            this.transform = transform;
            return this;
        }

        public Builder shape(ShapeArgs shapeArgs) {
            this.shape = shapeArgs;
            this.rect = null;
            this.ellipse = null;
            return this;
        }

        public Builder rect(RectArgs rectArgs) {
            this.rect = rectArgs;
            this.shape = null;
            this.ellipse = null;
            return this;
        }

        public Builder ellipse(EllipseArgs ellipseArgs) {
            this.ellipse = ellipseArgs;
            this.shape = null;
            this.rect = null;
            return this;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.Message.Builder
        public ShapeEntity build() {
            return new ShapeEntity(this.type, this.styles, this.transform, this.shape, this.rect, this.ellipse, super.buildUnknownFields());
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public enum ShapeType implements WireEnum {
        SHAPE(0),
        RECT(1),
        ELLIPSE(2),
        KEEP(3);
        
        public static final ProtoAdapter<ShapeType> ADAPTER = ProtoAdapter.newEnumAdapter(ShapeType.class);
        private final int value;

        ShapeType(int i) {
            this.value = i;
        }

        public static ShapeType fromValue(int i) {
            switch (i) {
                case 0:
                    return SHAPE;
                case 1:
                    return RECT;
                case 2:
                    return ELLIPSE;
                case 3:
                    return KEEP;
                default:
                    return null;
            }
        }

        @Override // com.squareup.wire.WireEnum
        public int getValue() {
            return this.value;
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class ShapeArgs extends Message<ShapeArgs, Builder> {
        public static final ProtoAdapter<ShapeArgs> ADAPTER = new ProtoAdapter_ShapeArgs();
        public static final String DEFAULT_D = "";
        private static final long serialVersionUID = 0;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#STRING", tag = 1)

        /* renamed from: d */
        public final String f17710d;

        public ShapeArgs(String str) {
            this(str, ByteString.EMPTY);
        }

        public ShapeArgs(String str, ByteString byteString) {
            super(ADAPTER, byteString);
            this.f17710d = str;
        }

        @Override // com.squareup.wire.Message
        /* renamed from: newBuilder */
        public Message.Builder<ShapeArgs, Builder> newBuilder2() {
            Builder builder = new Builder();
            builder.f17711d = this.f17710d;
            builder.addUnknownFields(unknownFields());
            return builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ShapeArgs) {
                ShapeArgs shapeArgs = (ShapeArgs) obj;
                return unknownFields().equals(shapeArgs.unknownFields()) && Internal.equals(this.f17710d, shapeArgs.f17710d);
            }
            return false;
        }

        public int hashCode() {
            int i = this.hashCode;
            if (i == 0) {
                int hashCode = unknownFields().hashCode() * 37;
                String str = this.f17710d;
                int hashCode2 = hashCode + (str != null ? str.hashCode() : 0);
                this.hashCode = hashCode2;
                return hashCode2;
            }
            return i;
        }

        @Override // com.squareup.wire.Message
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.f17710d != null) {
                sb.append(", d=");
                sb.append(this.f17710d);
            }
            StringBuilder replace = sb.replace(0, 2, "ShapeArgs{");
            replace.append('}');
            return replace.toString();
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static final class Builder extends Message.Builder<ShapeArgs, Builder> {

            /* renamed from: d */
            public String f17711d;

            /* renamed from: d */
            public Builder m8358d(String str) {
                this.f17711d = str;
                return this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.Message.Builder
            public ShapeArgs build() {
                return new ShapeArgs(this.f17711d, super.buildUnknownFields());
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        static final class ProtoAdapter_ShapeArgs extends ProtoAdapter<ShapeArgs> {
            ProtoAdapter_ShapeArgs() {
                super(FieldEncoding.LENGTH_DELIMITED, ShapeArgs.class);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(ShapeArgs shapeArgs) {
                return (shapeArgs.f17710d != null ? ProtoAdapter.STRING.encodedSizeWithTag(1, shapeArgs.f17710d) : 0) + shapeArgs.unknownFields().size();
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, ShapeArgs shapeArgs) throws IOException {
                if (shapeArgs.f17710d != null) {
                    ProtoAdapter.STRING.encodeWithTag(protoWriter, 1, shapeArgs.f17710d);
                }
                protoWriter.writeBytes(shapeArgs.unknownFields());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public ShapeArgs decode(ProtoReader protoReader) throws IOException {
                Builder builder = new Builder();
                long beginMessage = protoReader.beginMessage();
                while (true) {
                    int nextTag = protoReader.nextTag();
                    if (nextTag == -1) {
                        protoReader.endMessage(beginMessage);
                        return builder.build();
                    } else if (nextTag == 1) {
                        builder.m8358d(ProtoAdapter.STRING.decode(protoReader));
                    } else {
                        FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                        builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                    }
                }
            }

            @Override // com.squareup.wire.ProtoAdapter
            public ShapeArgs redact(ShapeArgs shapeArgs) {
                Message.Builder<ShapeArgs, Builder> newBuilder2 = shapeArgs.newBuilder2();
                newBuilder2.clearUnknownFields();
                return newBuilder2.build();
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class RectArgs extends Message<RectArgs, Builder> {
        private static final long serialVersionUID = 0;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 5)
        public final Float cornerRadius;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
        public final Float height;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
        public final Float width;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)

        /* renamed from: x */
        public final Float f17706x;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)

        /* renamed from: y */
        public final Float f17707y;
        public static final ProtoAdapter<RectArgs> ADAPTER = new ProtoAdapter_RectArgs();
        public static final Float DEFAULT_X = Float.valueOf(0.0f);
        public static final Float DEFAULT_Y = Float.valueOf(0.0f);
        public static final Float DEFAULT_WIDTH = Float.valueOf(0.0f);
        public static final Float DEFAULT_HEIGHT = Float.valueOf(0.0f);
        public static final Float DEFAULT_CORNERRADIUS = Float.valueOf(0.0f);

        public RectArgs(Float f, Float f2, Float f3, Float f4, Float f5) {
            this(f, f2, f3, f4, f5, ByteString.EMPTY);
        }

        public RectArgs(Float f, Float f2, Float f3, Float f4, Float f5, ByteString byteString) {
            super(ADAPTER, byteString);
            this.f17706x = f;
            this.f17707y = f2;
            this.width = f3;
            this.height = f4;
            this.cornerRadius = f5;
        }

        @Override // com.squareup.wire.Message
        /* renamed from: newBuilder */
        public Message.Builder<RectArgs, Builder> newBuilder2() {
            Builder builder = new Builder();
            builder.f17708x = this.f17706x;
            builder.f17709y = this.f17707y;
            builder.width = this.width;
            builder.height = this.height;
            builder.cornerRadius = this.cornerRadius;
            builder.addUnknownFields(unknownFields());
            return builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof RectArgs) {
                RectArgs rectArgs = (RectArgs) obj;
                return unknownFields().equals(rectArgs.unknownFields()) && Internal.equals(this.f17706x, rectArgs.f17706x) && Internal.equals(this.f17707y, rectArgs.f17707y) && Internal.equals(this.width, rectArgs.width) && Internal.equals(this.height, rectArgs.height) && Internal.equals(this.cornerRadius, rectArgs.cornerRadius);
            }
            return false;
        }

        public int hashCode() {
            int i = this.hashCode;
            if (i == 0) {
                int hashCode = unknownFields().hashCode() * 37;
                Float f = this.f17706x;
                int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
                Float f2 = this.f17707y;
                int hashCode3 = (hashCode2 + (f2 != null ? f2.hashCode() : 0)) * 37;
                Float f3 = this.width;
                int hashCode4 = (hashCode3 + (f3 != null ? f3.hashCode() : 0)) * 37;
                Float f4 = this.height;
                int hashCode5 = (hashCode4 + (f4 != null ? f4.hashCode() : 0)) * 37;
                Float f5 = this.cornerRadius;
                int hashCode6 = hashCode5 + (f5 != null ? f5.hashCode() : 0);
                this.hashCode = hashCode6;
                return hashCode6;
            }
            return i;
        }

        @Override // com.squareup.wire.Message
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.f17706x != null) {
                sb.append(", x=");
                sb.append(this.f17706x);
            }
            if (this.f17707y != null) {
                sb.append(", y=");
                sb.append(this.f17707y);
            }
            if (this.width != null) {
                sb.append(", width=");
                sb.append(this.width);
            }
            if (this.height != null) {
                sb.append(", height=");
                sb.append(this.height);
            }
            if (this.cornerRadius != null) {
                sb.append(", cornerRadius=");
                sb.append(this.cornerRadius);
            }
            StringBuilder replace = sb.replace(0, 2, "RectArgs{");
            replace.append('}');
            return replace.toString();
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static final class Builder extends Message.Builder<RectArgs, Builder> {
            public Float cornerRadius;
            public Float height;
            public Float width;

            /* renamed from: x */
            public Float f17708x;

            /* renamed from: y */
            public Float f17709y;

            /* renamed from: x */
            public Builder m8360x(Float f) {
                this.f17708x = f;
                return this;
            }

            /* renamed from: y */
            public Builder m8359y(Float f) {
                this.f17709y = f;
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

            public Builder cornerRadius(Float f) {
                this.cornerRadius = f;
                return this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.Message.Builder
            public RectArgs build() {
                return new RectArgs(this.f17708x, this.f17709y, this.width, this.height, this.cornerRadius, super.buildUnknownFields());
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        static final class ProtoAdapter_RectArgs extends ProtoAdapter<RectArgs> {
            ProtoAdapter_RectArgs() {
                super(FieldEncoding.LENGTH_DELIMITED, RectArgs.class);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(RectArgs rectArgs) {
                return (rectArgs.f17706x != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, rectArgs.f17706x) : 0) + (rectArgs.f17707y != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(2, rectArgs.f17707y) : 0) + (rectArgs.width != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(3, rectArgs.width) : 0) + (rectArgs.height != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(4, rectArgs.height) : 0) + (rectArgs.cornerRadius != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(5, rectArgs.cornerRadius) : 0) + rectArgs.unknownFields().size();
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, RectArgs rectArgs) throws IOException {
                if (rectArgs.f17706x != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, rectArgs.f17706x);
                }
                if (rectArgs.f17707y != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, rectArgs.f17707y);
                }
                if (rectArgs.width != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, rectArgs.width);
                }
                if (rectArgs.height != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, rectArgs.height);
                }
                if (rectArgs.cornerRadius != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 5, rectArgs.cornerRadius);
                }
                protoWriter.writeBytes(rectArgs.unknownFields());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public RectArgs decode(ProtoReader protoReader) throws IOException {
                Builder builder = new Builder();
                long beginMessage = protoReader.beginMessage();
                while (true) {
                    int nextTag = protoReader.nextTag();
                    if (nextTag != -1) {
                        switch (nextTag) {
                            case 1:
                                builder.m8360x(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 2:
                                builder.m8359y(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 3:
                                builder.width(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 4:
                                builder.height(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 5:
                                builder.cornerRadius(ProtoAdapter.FLOAT.decode(protoReader));
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
            public RectArgs redact(RectArgs rectArgs) {
                Message.Builder<RectArgs, Builder> newBuilder2 = rectArgs.newBuilder2();
                newBuilder2.clearUnknownFields();
                return newBuilder2.build();
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class EllipseArgs extends Message<EllipseArgs, Builder> {
        private static final long serialVersionUID = 0;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
        public final Float radiusX;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)
        public final Float radiusY;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)

        /* renamed from: x */
        public final Float f17702x;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)

        /* renamed from: y */
        public final Float f17703y;
        public static final ProtoAdapter<EllipseArgs> ADAPTER = new ProtoAdapter_EllipseArgs();
        public static final Float DEFAULT_X = Float.valueOf(0.0f);
        public static final Float DEFAULT_Y = Float.valueOf(0.0f);
        public static final Float DEFAULT_RADIUSX = Float.valueOf(0.0f);
        public static final Float DEFAULT_RADIUSY = Float.valueOf(0.0f);

        public EllipseArgs(Float f, Float f2, Float f3, Float f4) {
            this(f, f2, f3, f4, ByteString.EMPTY);
        }

        public EllipseArgs(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
            super(ADAPTER, byteString);
            this.f17702x = f;
            this.f17703y = f2;
            this.radiusX = f3;
            this.radiusY = f4;
        }

        @Override // com.squareup.wire.Message
        /* renamed from: newBuilder */
        public Message.Builder<EllipseArgs, Builder> newBuilder2() {
            Builder builder = new Builder();
            builder.f17704x = this.f17702x;
            builder.f17705y = this.f17703y;
            builder.radiusX = this.radiusX;
            builder.radiusY = this.radiusY;
            builder.addUnknownFields(unknownFields());
            return builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof EllipseArgs) {
                EllipseArgs ellipseArgs = (EllipseArgs) obj;
                return unknownFields().equals(ellipseArgs.unknownFields()) && Internal.equals(this.f17702x, ellipseArgs.f17702x) && Internal.equals(this.f17703y, ellipseArgs.f17703y) && Internal.equals(this.radiusX, ellipseArgs.radiusX) && Internal.equals(this.radiusY, ellipseArgs.radiusY);
            }
            return false;
        }

        public int hashCode() {
            int i = this.hashCode;
            if (i == 0) {
                int hashCode = unknownFields().hashCode() * 37;
                Float f = this.f17702x;
                int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
                Float f2 = this.f17703y;
                int hashCode3 = (hashCode2 + (f2 != null ? f2.hashCode() : 0)) * 37;
                Float f3 = this.radiusX;
                int hashCode4 = (hashCode3 + (f3 != null ? f3.hashCode() : 0)) * 37;
                Float f4 = this.radiusY;
                int hashCode5 = hashCode4 + (f4 != null ? f4.hashCode() : 0);
                this.hashCode = hashCode5;
                return hashCode5;
            }
            return i;
        }

        @Override // com.squareup.wire.Message
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.f17702x != null) {
                sb.append(", x=");
                sb.append(this.f17702x);
            }
            if (this.f17703y != null) {
                sb.append(", y=");
                sb.append(this.f17703y);
            }
            if (this.radiusX != null) {
                sb.append(", radiusX=");
                sb.append(this.radiusX);
            }
            if (this.radiusY != null) {
                sb.append(", radiusY=");
                sb.append(this.radiusY);
            }
            StringBuilder replace = sb.replace(0, 2, "EllipseArgs{");
            replace.append('}');
            return replace.toString();
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static final class Builder extends Message.Builder<EllipseArgs, Builder> {
            public Float radiusX;
            public Float radiusY;

            /* renamed from: x */
            public Float f17704x;

            /* renamed from: y */
            public Float f17705y;

            /* renamed from: x */
            public Builder m8362x(Float f) {
                this.f17704x = f;
                return this;
            }

            /* renamed from: y */
            public Builder m8361y(Float f) {
                this.f17705y = f;
                return this;
            }

            public Builder radiusX(Float f) {
                this.radiusX = f;
                return this;
            }

            public Builder radiusY(Float f) {
                this.radiusY = f;
                return this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.Message.Builder
            public EllipseArgs build() {
                return new EllipseArgs(this.f17704x, this.f17705y, this.radiusX, this.radiusY, super.buildUnknownFields());
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        static final class ProtoAdapter_EllipseArgs extends ProtoAdapter<EllipseArgs> {
            ProtoAdapter_EllipseArgs() {
                super(FieldEncoding.LENGTH_DELIMITED, EllipseArgs.class);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(EllipseArgs ellipseArgs) {
                return (ellipseArgs.f17702x != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, ellipseArgs.f17702x) : 0) + (ellipseArgs.f17703y != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(2, ellipseArgs.f17703y) : 0) + (ellipseArgs.radiusX != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(3, ellipseArgs.radiusX) : 0) + (ellipseArgs.radiusY != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(4, ellipseArgs.radiusY) : 0) + ellipseArgs.unknownFields().size();
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, EllipseArgs ellipseArgs) throws IOException {
                if (ellipseArgs.f17702x != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, ellipseArgs.f17702x);
                }
                if (ellipseArgs.f17703y != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, ellipseArgs.f17703y);
                }
                if (ellipseArgs.radiusX != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, ellipseArgs.radiusX);
                }
                if (ellipseArgs.radiusY != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, ellipseArgs.radiusY);
                }
                protoWriter.writeBytes(ellipseArgs.unknownFields());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public EllipseArgs decode(ProtoReader protoReader) throws IOException {
                Builder builder = new Builder();
                long beginMessage = protoReader.beginMessage();
                while (true) {
                    int nextTag = protoReader.nextTag();
                    if (nextTag != -1) {
                        switch (nextTag) {
                            case 1:
                                builder.m8362x(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 2:
                                builder.m8361y(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 3:
                                builder.radiusX(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 4:
                                builder.radiusY(ProtoAdapter.FLOAT.decode(protoReader));
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
            public EllipseArgs redact(EllipseArgs ellipseArgs) {
                Message.Builder<EllipseArgs, Builder> newBuilder2 = ellipseArgs.newBuilder2();
                newBuilder2.clearUnknownFields();
                return newBuilder2.build();
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    public static final class ShapeStyle extends Message<ShapeStyle, Builder> {
        private static final long serialVersionUID = 0;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$RGBAColor#ADAPTER", tag = 1)
        public final RGBAColor fill;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$LineCap#ADAPTER", tag = 4)
        public final LineCap lineCap;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 7)
        public final Float lineDashI;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 8)
        public final Float lineDashII;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 9)
        public final Float lineDashIII;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$LineJoin#ADAPTER", tag = 5)
        public final LineJoin lineJoin;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 6)
        public final Float miterLimit;
        @WireField(adapter = "com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$RGBAColor#ADAPTER", tag = 2)
        public final RGBAColor stroke;
        @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)
        public final Float strokeWidth;
        public static final ProtoAdapter<ShapeStyle> ADAPTER = new ProtoAdapter_ShapeStyle();
        public static final Float DEFAULT_STROKEWIDTH = Float.valueOf(0.0f);
        public static final LineCap DEFAULT_LINECAP = LineCap.LineCap_BUTT;
        public static final LineJoin DEFAULT_LINEJOIN = LineJoin.LineJoin_MITER;
        public static final Float DEFAULT_MITERLIMIT = Float.valueOf(0.0f);
        public static final Float DEFAULT_LINEDASHI = Float.valueOf(0.0f);
        public static final Float DEFAULT_LINEDASHII = Float.valueOf(0.0f);
        public static final Float DEFAULT_LINEDASHIII = Float.valueOf(0.0f);

        public ShapeStyle(RGBAColor rGBAColor, RGBAColor rGBAColor2, Float f, LineCap lineCap, LineJoin lineJoin, Float f2, Float f3, Float f4, Float f5) {
            this(rGBAColor, rGBAColor2, f, lineCap, lineJoin, f2, f3, f4, f5, ByteString.EMPTY);
        }

        public ShapeStyle(RGBAColor rGBAColor, RGBAColor rGBAColor2, Float f, LineCap lineCap, LineJoin lineJoin, Float f2, Float f3, Float f4, Float f5, ByteString byteString) {
            super(ADAPTER, byteString);
            this.fill = rGBAColor;
            this.stroke = rGBAColor2;
            this.strokeWidth = f;
            this.lineCap = lineCap;
            this.lineJoin = lineJoin;
            this.miterLimit = f2;
            this.lineDashI = f3;
            this.lineDashII = f4;
            this.lineDashIII = f5;
        }

        @Override // com.squareup.wire.Message
        /* renamed from: newBuilder */
        public Message.Builder<ShapeStyle, Builder> newBuilder2() {
            Builder builder = new Builder();
            builder.fill = this.fill;
            builder.stroke = this.stroke;
            builder.strokeWidth = this.strokeWidth;
            builder.lineCap = this.lineCap;
            builder.lineJoin = this.lineJoin;
            builder.miterLimit = this.miterLimit;
            builder.lineDashI = this.lineDashI;
            builder.lineDashII = this.lineDashII;
            builder.lineDashIII = this.lineDashIII;
            builder.addUnknownFields(unknownFields());
            return builder;
        }

        public boolean equals(Object obj) {
            if (obj == this) {
                return true;
            }
            if (obj instanceof ShapeStyle) {
                ShapeStyle shapeStyle = (ShapeStyle) obj;
                return unknownFields().equals(shapeStyle.unknownFields()) && Internal.equals(this.fill, shapeStyle.fill) && Internal.equals(this.stroke, shapeStyle.stroke) && Internal.equals(this.strokeWidth, shapeStyle.strokeWidth) && Internal.equals(this.lineCap, shapeStyle.lineCap) && Internal.equals(this.lineJoin, shapeStyle.lineJoin) && Internal.equals(this.miterLimit, shapeStyle.miterLimit) && Internal.equals(this.lineDashI, shapeStyle.lineDashI) && Internal.equals(this.lineDashII, shapeStyle.lineDashII) && Internal.equals(this.lineDashIII, shapeStyle.lineDashIII);
            }
            return false;
        }

        public int hashCode() {
            int i = this.hashCode;
            if (i == 0) {
                int hashCode = unknownFields().hashCode() * 37;
                RGBAColor rGBAColor = this.fill;
                int hashCode2 = (hashCode + (rGBAColor != null ? rGBAColor.hashCode() : 0)) * 37;
                RGBAColor rGBAColor2 = this.stroke;
                int hashCode3 = (hashCode2 + (rGBAColor2 != null ? rGBAColor2.hashCode() : 0)) * 37;
                Float f = this.strokeWidth;
                int hashCode4 = (hashCode3 + (f != null ? f.hashCode() : 0)) * 37;
                LineCap lineCap = this.lineCap;
                int hashCode5 = (hashCode4 + (lineCap != null ? lineCap.hashCode() : 0)) * 37;
                LineJoin lineJoin = this.lineJoin;
                int hashCode6 = (hashCode5 + (lineJoin != null ? lineJoin.hashCode() : 0)) * 37;
                Float f2 = this.miterLimit;
                int hashCode7 = (hashCode6 + (f2 != null ? f2.hashCode() : 0)) * 37;
                Float f3 = this.lineDashI;
                int hashCode8 = (hashCode7 + (f3 != null ? f3.hashCode() : 0)) * 37;
                Float f4 = this.lineDashII;
                int hashCode9 = (hashCode8 + (f4 != null ? f4.hashCode() : 0)) * 37;
                Float f5 = this.lineDashIII;
                int hashCode10 = hashCode9 + (f5 != null ? f5.hashCode() : 0);
                this.hashCode = hashCode10;
                return hashCode10;
            }
            return i;
        }

        @Override // com.squareup.wire.Message
        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (this.fill != null) {
                sb.append(", fill=");
                sb.append(this.fill);
            }
            if (this.stroke != null) {
                sb.append(", stroke=");
                sb.append(this.stroke);
            }
            if (this.strokeWidth != null) {
                sb.append(", strokeWidth=");
                sb.append(this.strokeWidth);
            }
            if (this.lineCap != null) {
                sb.append(", lineCap=");
                sb.append(this.lineCap);
            }
            if (this.lineJoin != null) {
                sb.append(", lineJoin=");
                sb.append(this.lineJoin);
            }
            if (this.miterLimit != null) {
                sb.append(", miterLimit=");
                sb.append(this.miterLimit);
            }
            if (this.lineDashI != null) {
                sb.append(", lineDashI=");
                sb.append(this.lineDashI);
            }
            if (this.lineDashII != null) {
                sb.append(", lineDashII=");
                sb.append(this.lineDashII);
            }
            if (this.lineDashIII != null) {
                sb.append(", lineDashIII=");
                sb.append(this.lineDashIII);
            }
            StringBuilder replace = sb.replace(0, 2, "ShapeStyle{");
            replace.append('}');
            return replace.toString();
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static final class Builder extends Message.Builder<ShapeStyle, Builder> {
            public RGBAColor fill;
            public LineCap lineCap;
            public Float lineDashI;
            public Float lineDashII;
            public Float lineDashIII;
            public LineJoin lineJoin;
            public Float miterLimit;
            public RGBAColor stroke;
            public Float strokeWidth;

            public Builder fill(RGBAColor rGBAColor) {
                this.fill = rGBAColor;
                return this;
            }

            public Builder stroke(RGBAColor rGBAColor) {
                this.stroke = rGBAColor;
                return this;
            }

            public Builder strokeWidth(Float f) {
                this.strokeWidth = f;
                return this;
            }

            public Builder lineCap(LineCap lineCap) {
                this.lineCap = lineCap;
                return this;
            }

            public Builder lineJoin(LineJoin lineJoin) {
                this.lineJoin = lineJoin;
                return this;
            }

            public Builder miterLimit(Float f) {
                this.miterLimit = f;
                return this;
            }

            public Builder lineDashI(Float f) {
                this.lineDashI = f;
                return this;
            }

            public Builder lineDashII(Float f) {
                this.lineDashII = f;
                return this;
            }

            public Builder lineDashIII(Float f) {
                this.lineDashIII = f;
                return this;
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.Message.Builder
            public ShapeStyle build() {
                return new ShapeStyle(this.fill, this.stroke, this.strokeWidth, this.lineCap, this.lineJoin, this.miterLimit, this.lineDashI, this.lineDashII, this.lineDashIII, super.buildUnknownFields());
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        public static final class RGBAColor extends Message<RGBAColor, Builder> {
            private static final long serialVersionUID = 0;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 4)

            /* renamed from: a */
            public final Float f17712a;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 3)

            /* renamed from: b */
            public final Float f17713b;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 2)

            /* renamed from: g */
            public final Float f17714g;
            @WireField(adapter = "com.squareup.wire.ProtoAdapter#FLOAT", tag = 1)

            /* renamed from: r */
            public final Float f17715r;
            public static final ProtoAdapter<RGBAColor> ADAPTER = new ProtoAdapter_RGBAColor();
            public static final Float DEFAULT_R = Float.valueOf(0.0f);
            public static final Float DEFAULT_G = Float.valueOf(0.0f);
            public static final Float DEFAULT_B = Float.valueOf(0.0f);
            public static final Float DEFAULT_A = Float.valueOf(0.0f);

            public RGBAColor(Float f, Float f2, Float f3, Float f4) {
                this(f, f2, f3, f4, ByteString.EMPTY);
            }

            public RGBAColor(Float f, Float f2, Float f3, Float f4, ByteString byteString) {
                super(ADAPTER, byteString);
                this.f17715r = f;
                this.f17714g = f2;
                this.f17713b = f3;
                this.f17712a = f4;
            }

            @Override // com.squareup.wire.Message
            /* renamed from: newBuilder */
            public Message.Builder<RGBAColor, Builder> newBuilder2() {
                Builder builder = new Builder();
                builder.f17719r = this.f17715r;
                builder.f17718g = this.f17714g;
                builder.f17717b = this.f17713b;
                builder.f17716a = this.f17712a;
                builder.addUnknownFields(unknownFields());
                return builder;
            }

            public boolean equals(Object obj) {
                if (obj == this) {
                    return true;
                }
                if (obj instanceof RGBAColor) {
                    RGBAColor rGBAColor = (RGBAColor) obj;
                    return unknownFields().equals(rGBAColor.unknownFields()) && Internal.equals(this.f17715r, rGBAColor.f17715r) && Internal.equals(this.f17714g, rGBAColor.f17714g) && Internal.equals(this.f17713b, rGBAColor.f17713b) && Internal.equals(this.f17712a, rGBAColor.f17712a);
                }
                return false;
            }

            public int hashCode() {
                int i = this.hashCode;
                if (i == 0) {
                    int hashCode = unknownFields().hashCode() * 37;
                    Float f = this.f17715r;
                    int hashCode2 = (hashCode + (f != null ? f.hashCode() : 0)) * 37;
                    Float f2 = this.f17714g;
                    int hashCode3 = (hashCode2 + (f2 != null ? f2.hashCode() : 0)) * 37;
                    Float f3 = this.f17713b;
                    int hashCode4 = (hashCode3 + (f3 != null ? f3.hashCode() : 0)) * 37;
                    Float f4 = this.f17712a;
                    int hashCode5 = hashCode4 + (f4 != null ? f4.hashCode() : 0);
                    this.hashCode = hashCode5;
                    return hashCode5;
                }
                return i;
            }

            @Override // com.squareup.wire.Message
            public String toString() {
                StringBuilder sb = new StringBuilder();
                if (this.f17715r != null) {
                    sb.append(", r=");
                    sb.append(this.f17715r);
                }
                if (this.f17714g != null) {
                    sb.append(", g=");
                    sb.append(this.f17714g);
                }
                if (this.f17713b != null) {
                    sb.append(", b=");
                    sb.append(this.f17713b);
                }
                if (this.f17712a != null) {
                    sb.append(", a=");
                    sb.append(this.f17712a);
                }
                StringBuilder replace = sb.replace(0, 2, "RGBAColor{");
                replace.append('}');
                return replace.toString();
            }

            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            public static final class Builder extends Message.Builder<RGBAColor, Builder> {

                /* renamed from: a */
                public Float f17716a;

                /* renamed from: b */
                public Float f17717b;

                /* renamed from: g */
                public Float f17718g;

                /* renamed from: r */
                public Float f17719r;

                /* renamed from: r */
                public Builder m8354r(Float f) {
                    this.f17719r = f;
                    return this;
                }

                /* renamed from: g */
                public Builder m8355g(Float f) {
                    this.f17718g = f;
                    return this;
                }

                /* renamed from: b */
                public Builder m8356b(Float f) {
                    this.f17717b = f;
                    return this;
                }

                /* renamed from: a */
                public Builder m8357a(Float f) {
                    this.f17716a = f;
                    return this;
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.squareup.wire.Message.Builder
                public RGBAColor build() {
                    return new RGBAColor(this.f17719r, this.f17718g, this.f17717b, this.f17716a, super.buildUnknownFields());
                }
            }

            /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
            static final class ProtoAdapter_RGBAColor extends ProtoAdapter<RGBAColor> {
                ProtoAdapter_RGBAColor() {
                    super(FieldEncoding.LENGTH_DELIMITED, RGBAColor.class);
                }

                @Override // com.squareup.wire.ProtoAdapter
                public int encodedSize(RGBAColor rGBAColor) {
                    return (rGBAColor.f17715r != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(1, rGBAColor.f17715r) : 0) + (rGBAColor.f17714g != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(2, rGBAColor.f17714g) : 0) + (rGBAColor.f17713b != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(3, rGBAColor.f17713b) : 0) + (rGBAColor.f17712a != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(4, rGBAColor.f17712a) : 0) + rGBAColor.unknownFields().size();
                }

                @Override // com.squareup.wire.ProtoAdapter
                public void encode(ProtoWriter protoWriter, RGBAColor rGBAColor) throws IOException {
                    if (rGBAColor.f17715r != null) {
                        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 1, rGBAColor.f17715r);
                    }
                    if (rGBAColor.f17714g != null) {
                        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 2, rGBAColor.f17714g);
                    }
                    if (rGBAColor.f17713b != null) {
                        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, rGBAColor.f17713b);
                    }
                    if (rGBAColor.f17712a != null) {
                        ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 4, rGBAColor.f17712a);
                    }
                    protoWriter.writeBytes(rGBAColor.unknownFields());
                }

                /* JADX WARN: Can't rename method to resolve collision */
                @Override // com.squareup.wire.ProtoAdapter
                public RGBAColor decode(ProtoReader protoReader) throws IOException {
                    Builder builder = new Builder();
                    long beginMessage = protoReader.beginMessage();
                    while (true) {
                        int nextTag = protoReader.nextTag();
                        if (nextTag != -1) {
                            switch (nextTag) {
                                case 1:
                                    builder.m8354r(ProtoAdapter.FLOAT.decode(protoReader));
                                    break;
                                case 2:
                                    builder.m8355g(ProtoAdapter.FLOAT.decode(protoReader));
                                    break;
                                case 3:
                                    builder.m8356b(ProtoAdapter.FLOAT.decode(protoReader));
                                    break;
                                case 4:
                                    builder.m8357a(ProtoAdapter.FLOAT.decode(protoReader));
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
                public RGBAColor redact(RGBAColor rGBAColor) {
                    Message.Builder<RGBAColor, Builder> newBuilder2 = rGBAColor.newBuilder2();
                    newBuilder2.clearUnknownFields();
                    return newBuilder2.build();
                }
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum LineCap implements WireEnum {
            LineCap_BUTT(0),
            LineCap_ROUND(1),
            LineCap_SQUARE(2);
            
            public static final ProtoAdapter<LineCap> ADAPTER = ProtoAdapter.newEnumAdapter(LineCap.class);
            private final int value;

            LineCap(int i) {
                this.value = i;
            }

            public static LineCap fromValue(int i) {
                switch (i) {
                    case 0:
                        return LineCap_BUTT;
                    case 1:
                        return LineCap_ROUND;
                    case 2:
                        return LineCap_SQUARE;
                    default:
                        return null;
                }
            }

            @Override // com.squareup.wire.WireEnum
            public int getValue() {
                return this.value;
            }
        }

        /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
        /* loaded from: E:\11480076_dexfile_execute.dex */
        public enum LineJoin implements WireEnum {
            LineJoin_MITER(0),
            LineJoin_ROUND(1),
            LineJoin_BEVEL(2);
            
            public static final ProtoAdapter<LineJoin> ADAPTER = ProtoAdapter.newEnumAdapter(LineJoin.class);
            private final int value;

            LineJoin(int i) {
                this.value = i;
            }

            public static LineJoin fromValue(int i) {
                switch (i) {
                    case 0:
                        return LineJoin_MITER;
                    case 1:
                        return LineJoin_ROUND;
                    case 2:
                        return LineJoin_BEVEL;
                    default:
                        return null;
                }
            }

            @Override // com.squareup.wire.WireEnum
            public int getValue() {
                return this.value;
            }
        }

        /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
        static final class ProtoAdapter_ShapeStyle extends ProtoAdapter<ShapeStyle> {
            ProtoAdapter_ShapeStyle() {
                super(FieldEncoding.LENGTH_DELIMITED, ShapeStyle.class);
            }

            @Override // com.squareup.wire.ProtoAdapter
            public int encodedSize(ShapeStyle shapeStyle) {
                return (shapeStyle.fill != null ? RGBAColor.ADAPTER.encodedSizeWithTag(1, shapeStyle.fill) : 0) + (shapeStyle.stroke != null ? RGBAColor.ADAPTER.encodedSizeWithTag(2, shapeStyle.stroke) : 0) + (shapeStyle.strokeWidth != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(3, shapeStyle.strokeWidth) : 0) + (shapeStyle.lineCap != null ? LineCap.ADAPTER.encodedSizeWithTag(4, shapeStyle.lineCap) : 0) + (shapeStyle.lineJoin != null ? LineJoin.ADAPTER.encodedSizeWithTag(5, shapeStyle.lineJoin) : 0) + (shapeStyle.miterLimit != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(6, shapeStyle.miterLimit) : 0) + (shapeStyle.lineDashI != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(7, shapeStyle.lineDashI) : 0) + (shapeStyle.lineDashII != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(8, shapeStyle.lineDashII) : 0) + (shapeStyle.lineDashIII != null ? ProtoAdapter.FLOAT.encodedSizeWithTag(9, shapeStyle.lineDashIII) : 0) + shapeStyle.unknownFields().size();
            }

            @Override // com.squareup.wire.ProtoAdapter
            public void encode(ProtoWriter protoWriter, ShapeStyle shapeStyle) throws IOException {
                if (shapeStyle.fill != null) {
                    RGBAColor.ADAPTER.encodeWithTag(protoWriter, 1, shapeStyle.fill);
                }
                if (shapeStyle.stroke != null) {
                    RGBAColor.ADAPTER.encodeWithTag(protoWriter, 2, shapeStyle.stroke);
                }
                if (shapeStyle.strokeWidth != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 3, shapeStyle.strokeWidth);
                }
                if (shapeStyle.lineCap != null) {
                    LineCap.ADAPTER.encodeWithTag(protoWriter, 4, shapeStyle.lineCap);
                }
                if (shapeStyle.lineJoin != null) {
                    LineJoin.ADAPTER.encodeWithTag(protoWriter, 5, shapeStyle.lineJoin);
                }
                if (shapeStyle.miterLimit != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 6, shapeStyle.miterLimit);
                }
                if (shapeStyle.lineDashI != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 7, shapeStyle.lineDashI);
                }
                if (shapeStyle.lineDashII != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 8, shapeStyle.lineDashII);
                }
                if (shapeStyle.lineDashIII != null) {
                    ProtoAdapter.FLOAT.encodeWithTag(protoWriter, 9, shapeStyle.lineDashIII);
                }
                protoWriter.writeBytes(shapeStyle.unknownFields());
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.squareup.wire.ProtoAdapter
            public ShapeStyle decode(ProtoReader protoReader) throws IOException {
                Builder builder = new Builder();
                long beginMessage = protoReader.beginMessage();
                while (true) {
                    int nextTag = protoReader.nextTag();
                    if (nextTag != -1) {
                        switch (nextTag) {
                            case 1:
                                builder.fill(RGBAColor.ADAPTER.decode(protoReader));
                                break;
                            case 2:
                                builder.stroke(RGBAColor.ADAPTER.decode(protoReader));
                                break;
                            case 3:
                                builder.strokeWidth(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 4:
                                try {
                                    builder.lineCap(LineCap.ADAPTER.decode(protoReader));
                                    break;
                                } catch (ProtoAdapter.EnumConstantNotFoundException e) {
                                    builder.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf(e.value));
                                    break;
                                }
                            case 5:
                                try {
                                    builder.lineJoin(LineJoin.ADAPTER.decode(protoReader));
                                    break;
                                } catch (ProtoAdapter.EnumConstantNotFoundException e2) {
                                    builder.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf(e2.value));
                                    break;
                                }
                            case 6:
                                builder.miterLimit(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 7:
                                builder.lineDashI(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 8:
                                builder.lineDashII(ProtoAdapter.FLOAT.decode(protoReader));
                                break;
                            case 9:
                                builder.lineDashIII(ProtoAdapter.FLOAT.decode(protoReader));
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

            /* JADX WARN: Type inference failed for: r3v1, types: [com.opensource.svgaplayer.proto.ShapeEntity$ShapeStyle$Builder] */
            @Override // com.squareup.wire.ProtoAdapter
            public ShapeStyle redact(ShapeStyle shapeStyle) {
                ?? newBuilder2 = shapeStyle.newBuilder2();
                if (newBuilder2.fill != null) {
                    newBuilder2.fill = RGBAColor.ADAPTER.redact(newBuilder2.fill);
                }
                if (newBuilder2.stroke != null) {
                    newBuilder2.stroke = RGBAColor.ADAPTER.redact(newBuilder2.stroke);
                }
                newBuilder2.clearUnknownFields();
                return newBuilder2.build();
            }
        }
    }

    /* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
    static final class ProtoAdapter_ShapeEntity extends ProtoAdapter<ShapeEntity> {
        ProtoAdapter_ShapeEntity() {
            super(FieldEncoding.LENGTH_DELIMITED, ShapeEntity.class);
        }

        @Override // com.squareup.wire.ProtoAdapter
        public int encodedSize(ShapeEntity shapeEntity) {
            return (shapeEntity.type != null ? ShapeType.ADAPTER.encodedSizeWithTag(1, shapeEntity.type) : 0) + (shapeEntity.styles != null ? ShapeStyle.ADAPTER.encodedSizeWithTag(10, shapeEntity.styles) : 0) + (shapeEntity.transform != null ? Transform.ADAPTER.encodedSizeWithTag(11, shapeEntity.transform) : 0) + (shapeEntity.shape != null ? ShapeArgs.ADAPTER.encodedSizeWithTag(2, shapeEntity.shape) : 0) + (shapeEntity.rect != null ? RectArgs.ADAPTER.encodedSizeWithTag(3, shapeEntity.rect) : 0) + (shapeEntity.ellipse != null ? EllipseArgs.ADAPTER.encodedSizeWithTag(4, shapeEntity.ellipse) : 0) + shapeEntity.unknownFields().size();
        }

        @Override // com.squareup.wire.ProtoAdapter
        public void encode(ProtoWriter protoWriter, ShapeEntity shapeEntity) throws IOException {
            if (shapeEntity.type != null) {
                ShapeType.ADAPTER.encodeWithTag(protoWriter, 1, shapeEntity.type);
            }
            if (shapeEntity.styles != null) {
                ShapeStyle.ADAPTER.encodeWithTag(protoWriter, 10, shapeEntity.styles);
            }
            if (shapeEntity.transform != null) {
                Transform.ADAPTER.encodeWithTag(protoWriter, 11, shapeEntity.transform);
            }
            if (shapeEntity.shape != null) {
                ShapeArgs.ADAPTER.encodeWithTag(protoWriter, 2, shapeEntity.shape);
            }
            if (shapeEntity.rect != null) {
                RectArgs.ADAPTER.encodeWithTag(protoWriter, 3, shapeEntity.rect);
            }
            if (shapeEntity.ellipse != null) {
                EllipseArgs.ADAPTER.encodeWithTag(protoWriter, 4, shapeEntity.ellipse);
            }
            protoWriter.writeBytes(shapeEntity.unknownFields());
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.squareup.wire.ProtoAdapter
        public ShapeEntity decode(ProtoReader protoReader) throws IOException {
            Builder builder = new Builder();
            long beginMessage = protoReader.beginMessage();
            while (true) {
                int nextTag = protoReader.nextTag();
                if (nextTag != -1) {
                    switch (nextTag) {
                        case 1:
                            try {
                                builder.type(ShapeType.ADAPTER.decode(protoReader));
                                break;
                            } catch (ProtoAdapter.EnumConstantNotFoundException e) {
                                builder.addUnknownField(nextTag, FieldEncoding.VARINT, Long.valueOf(e.value));
                                break;
                            }
                        case 2:
                            builder.shape(ShapeArgs.ADAPTER.decode(protoReader));
                            break;
                        case 3:
                            builder.rect(RectArgs.ADAPTER.decode(protoReader));
                            break;
                        case 4:
                            builder.ellipse(EllipseArgs.ADAPTER.decode(protoReader));
                            break;
                        default:
                            switch (nextTag) {
                                case 10:
                                    builder.styles(ShapeStyle.ADAPTER.decode(protoReader));
                                    continue;
                                case 11:
                                    builder.transform(Transform.ADAPTER.decode(protoReader));
                                    continue;
                                default:
                                    FieldEncoding peekFieldEncoding = protoReader.peekFieldEncoding();
                                    builder.addUnknownField(nextTag, peekFieldEncoding, peekFieldEncoding.rawProtoAdapter().decode(protoReader));
                                    continue;
                            }
                    }
                } else {
                    protoReader.endMessage(beginMessage);
                    return builder.build();
                }
            }
        }

        /* JADX WARN: Type inference failed for: r3v1, types: [com.opensource.svgaplayer.proto.ShapeEntity$Builder] */
        @Override // com.squareup.wire.ProtoAdapter
        public ShapeEntity redact(ShapeEntity shapeEntity) {
            ?? newBuilder2 = shapeEntity.newBuilder2();
            if (newBuilder2.styles != null) {
                newBuilder2.styles = ShapeStyle.ADAPTER.redact(newBuilder2.styles);
            }
            if (newBuilder2.transform != null) {
                newBuilder2.transform = Transform.ADAPTER.redact(newBuilder2.transform);
            }
            if (newBuilder2.shape != null) {
                newBuilder2.shape = ShapeArgs.ADAPTER.redact(newBuilder2.shape);
            }
            if (newBuilder2.rect != null) {
                newBuilder2.rect = RectArgs.ADAPTER.redact(newBuilder2.rect);
            }
            if (newBuilder2.ellipse != null) {
                newBuilder2.ellipse = EllipseArgs.ADAPTER.redact(newBuilder2.ellipse);
            }
            newBuilder2.clearUnknownFields();
            return newBuilder2.build();
        }
    }
}
