package szcom.mp4parser.iso23009.part1;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class EventMessageBox extends AbstractFullBox {
    public static final String TYPE = "emsg";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    long eventDuration;

    /* renamed from: id */
    long f27683id;
    byte[] messageData;
    long presentationTimeDelta;
    String schemeIdUri;
    long timescale;
    String value;

    static {
        ajc$preClinit();
    }

    public EventMessageBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("EventMessageBox.java", EventMessageBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSchemeIdUri", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "java.lang.String"), 59);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSchemeIdUri", "szcom.mp4parser.iso23009.part1.EventMessageBox", "java.lang.String", "schemeIdUri", "", "void"), 63);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getId", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "long"), 99);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setId", "szcom.mp4parser.iso23009.part1.EventMessageBox", "long", "id", "", "void"), 103);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMessageData", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "[B"), 107);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMessageData", "szcom.mp4parser.iso23009.part1.EventMessageBox", "[B", "messageData", "", "void"), 111);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getValue", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "java.lang.String"), 67);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setValue", "szcom.mp4parser.iso23009.part1.EventMessageBox", "java.lang.String", "value", "", "void"), 71);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTimescale", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "long"), 75);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTimescale", "szcom.mp4parser.iso23009.part1.EventMessageBox", "long", "timescale", "", "void"), 79);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPresentationTimeDelta", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "long"), 83);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPresentationTimeDelta", "szcom.mp4parser.iso23009.part1.EventMessageBox", "long", "presentationTimeDelta", "", "void"), 87);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEventDuration", "szcom.mp4parser.iso23009.part1.EventMessageBox", "", "", "", "long"), 91);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEventDuration", "szcom.mp4parser.iso23009.part1.EventMessageBox", "long", "eventDuration", "", "void"), 95);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.schemeIdUri = IsoTypeReader.readString(byteBuffer);
        this.value = IsoTypeReader.readString(byteBuffer);
        this.timescale = IsoTypeReader.readUInt32(byteBuffer);
        this.presentationTimeDelta = IsoTypeReader.readUInt32(byteBuffer);
        this.eventDuration = IsoTypeReader.readUInt32(byteBuffer);
        this.f27683id = IsoTypeReader.readUInt32(byteBuffer);
        this.messageData = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.messageData);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUtf8String(byteBuffer, this.schemeIdUri);
        IsoTypeWriter.writeUtf8String(byteBuffer, this.value);
        IsoTypeWriter.writeUInt32(byteBuffer, this.timescale);
        IsoTypeWriter.writeUInt32(byteBuffer, this.presentationTimeDelta);
        IsoTypeWriter.writeUInt32(byteBuffer, this.eventDuration);
        IsoTypeWriter.writeUInt32(byteBuffer, this.f27683id);
        byteBuffer.put(this.messageData);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.schemeIdUri) + 22 + Utf8.utf8StringLengthInBytes(this.value) + this.messageData.length;
    }

    public long getEventDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.eventDuration;
    }

    public long getId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.f27683id;
    }

    public byte[] getMessageData() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.messageData;
    }

    public long getPresentationTimeDelta() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.presentationTimeDelta;
    }

    public String getSchemeIdUri() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.schemeIdUri;
    }

    public long getTimescale() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.timescale;
    }

    public String getValue() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.value;
    }

    public void setEventDuration(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.longObject(j)));
        this.eventDuration = j;
    }

    public void setId(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.longObject(j)));
        this.f27683id = j;
    }

    public void setMessageData(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, bArr));
        this.messageData = bArr;
    }

    public void setPresentationTimeDelta(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.longObject(j)));
        this.presentationTimeDelta = j;
    }

    public void setSchemeIdUri(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.schemeIdUri = str;
    }

    public void setTimescale(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.longObject(j)));
        this.timescale = j;
    }

    public void setValue(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.value = str;
    }
}
