package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class HandlerBox extends AbstractFullBox {
    public static final String TYPE = "hdlr";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    public static final Map<String, String> readableTypes;

    /* renamed from: a */
    private long f27653a;

    /* renamed from: b */
    private long f27654b;

    /* renamed from: c */
    private long f27655c;
    private String handlerType;
    private String name;
    private long shouldBeZeroButAppleWritesHereSomeValue;
    private boolean zeroTerm;

    static {
        ajc$preClinit();
        HashMap hashMap = new HashMap();
        hashMap.put("odsm", "ObjectDescriptorStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("crsm", "ClockReferenceStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("sdsm", "SceneDescriptionStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("m7sm", "MPEG7Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("ocsm", "ObjectContentInfoStream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("ipsm", "IPMP Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("mjsm", "MPEG-J Stream - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        hashMap.put("mdir", "Apple Meta Data iTunes Reader");
        hashMap.put("mp7b", "MPEG-7 binary XML");
        hashMap.put("mp7t", "MPEG-7 XML");
        hashMap.put("vide", "Video Track");
        hashMap.put("soun", "Sound Track");
        hashMap.put("hint", "Hint Track");
        hashMap.put("appl", "Apple specific");
        hashMap.put("meta", "Timed Metadata track - defined in ISO/IEC JTC1/SC29/WG11 - CODING OF MOVING PICTURES AND AUDIO");
        readableTypes = Collections.unmodifiableMap(hashMap);
    }

    public HandlerBox() {
        super(TYPE);
        this.name = null;
        this.zeroTerm = true;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("HandlerBox.java", HandlerBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getHandlerType", "szcom.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 78);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setName", "szcom.coremedia.iso.boxes.HandlerBox", "java.lang.String", "name", "", "void"), 87);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setHandlerType", "szcom.coremedia.iso.boxes.HandlerBox", "java.lang.String", "handlerType", "", "void"), 91);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getName", "szcom.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 95);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getHumanReadableTrackType", "szcom.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 99);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.HandlerBox", "", "", "", "java.lang.String"), 149);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.shouldBeZeroButAppleWritesHereSomeValue = IsoTypeReader.readUInt32(byteBuffer);
        this.handlerType = IsoTypeReader.read4cc(byteBuffer);
        this.f27653a = IsoTypeReader.readUInt32(byteBuffer);
        this.f27654b = IsoTypeReader.readUInt32(byteBuffer);
        this.f27655c = IsoTypeReader.readUInt32(byteBuffer);
        if (byteBuffer.remaining() > 0) {
            this.name = IsoTypeReader.readString(byteBuffer, byteBuffer.remaining());
            if (this.name.endsWith("\u0000")) {
                String str = this.name;
                this.name = str.substring(0, str.length() - 1);
                this.zeroTerm = true;
                return;
            }
        }
        this.zeroTerm = false;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.shouldBeZeroButAppleWritesHereSomeValue);
        byteBuffer.put(IsoFile.fourCCtoBytes(this.handlerType));
        IsoTypeWriter.writeUInt32(byteBuffer, this.f27653a);
        IsoTypeWriter.writeUInt32(byteBuffer, this.f27654b);
        IsoTypeWriter.writeUInt32(byteBuffer, this.f27655c);
        String str = this.name;
        if (str != null) {
            byteBuffer.put(Utf8.convert(str));
        }
        if (this.zeroTerm) {
            byteBuffer.put((byte) 0);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.zeroTerm ? Utf8.utf8StringLengthInBytes(this.name) + 25 : Utf8.utf8StringLengthInBytes(this.name) + 24;
    }

    public String getHandlerType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.handlerType;
    }

    public String getHumanReadableTrackType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return readableTypes.get(this.handlerType) != null ? readableTypes.get(this.handlerType) : "Unknown Handler Type";
    }

    public String getName() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.name;
    }

    public void setHandlerType(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, str));
        this.handlerType = str;
    }

    public void setName(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.name = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return "HandlerBox[handlerType=" + getHandlerType() + ";name=" + getName() + "]";
    }
}
