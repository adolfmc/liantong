package szcom.googlecode.mp4parser.boxes.apple;

import com.cjt2325.cameralibrary.CameraInterface;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.Collections;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.coremedia.iso.boxes.sampleentry.SampleEntry;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TimeCodeBox extends AbstractBox implements Container, SampleEntry {
    public static final String TYPE = "tmcd";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final JoinPoint.StaticPart ajc$tjp_14 = null;
    private static final JoinPoint.StaticPart ajc$tjp_15 = null;
    private static final JoinPoint.StaticPart ajc$tjp_16 = null;
    private static final JoinPoint.StaticPart ajc$tjp_17 = null;
    private static final JoinPoint.StaticPart ajc$tjp_18 = null;
    private static final JoinPoint.StaticPart ajc$tjp_19 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_20 = null;
    private static final JoinPoint.StaticPart ajc$tjp_21 = null;
    private static final JoinPoint.StaticPart ajc$tjp_22 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    int dataReferenceIndex;
    long flags;
    int frameDuration;
    int numberOfFrames;
    int reserved1;
    int reserved2;
    byte[] rest;
    int timeScale;

    static {
        ajc$preClinit();
    }

    public TimeCodeBox() {
        super(TYPE);
        this.rest = new byte[0];
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TimeCodeBox.java", TimeCodeBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDataReferenceIndex", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 88);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDataReferenceIndex", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "int", "dataReferenceIndex", "", "void"), 92);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved1", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "int", "reserved1", "", "void"), 137);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved2", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 141);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved2", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "int", "reserved2", "", "void"), CameraInterface.TYPE_CAPTURE);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFlags", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "long"), 149);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFlags", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "long", "flags", "", "void"), 153);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getRest", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "[B"), 157);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setRest", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "[B", "rest", "", "void"), 161);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBoxes", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "java.util.List"), 166);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBoxes", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "java.util.List", "boxes", "", "void"), 170);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBoxes", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "java.lang.Class", "clazz", "", "java.util.List"), 174);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "java.lang.String"), 98);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBoxes", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "java.lang.Class:boolean", "clazz:recursive", "", "java.util.List"), 178);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getByteBuffer", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "long:long", "start:size", "java.io.IOException", "java.nio.ByteBuffer"), 182);
        ajc$tjp_22 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "writeContainer", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "java.nio.channels.WritableByteChannel", "bb", "java.io.IOException", "void"), 186);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTimeScale", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 109);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTimeScale", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "int", "timeScale", "", "void"), 113);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFrameDuration", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 117);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFrameDuration", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "int", "frameDuration", "", "void"), 121);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getNumberOfFrames", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 125);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setNumberOfFrames", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "int", "numberOfFrames", "", "void"), 129);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved1", "szcom.googlecode.mp4parser.boxes.apple.TimeCodeBox", "", "", "", "int"), 133);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        byteBuffer.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(byteBuffer);
        this.reserved1 = byteBuffer.getInt();
        this.flags = IsoTypeReader.readUInt32(byteBuffer);
        this.timeScale = byteBuffer.getInt();
        this.frameDuration = byteBuffer.getInt();
        this.numberOfFrames = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved2 = IsoTypeReader.readUInt24(byteBuffer);
        this.rest = new byte[byteBuffer.remaining()];
        byteBuffer.get(this.rest);
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public List<Box> getBoxes() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this));
        return Collections.emptyList();
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public <T extends Box> List<T> getBoxes(Class<T> cls) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this, cls));
        return Collections.emptyList();
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public <T extends Box> List<T> getBoxes(Class<T> cls, boolean z) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this, cls, Conversions.booleanObject(z)));
        return Collections.emptyList();
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public ByteBuffer getByteBuffer(long j, long j2) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this, Conversions.longObject(j), Conversions.longObject(j2)));
        return null;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.put(new byte[6]);
        IsoTypeWriter.writeUInt16(byteBuffer, this.dataReferenceIndex);
        byteBuffer.putInt(this.reserved1);
        IsoTypeWriter.writeUInt32(byteBuffer, this.flags);
        byteBuffer.putInt(this.timeScale);
        byteBuffer.putInt(this.frameDuration);
        IsoTypeWriter.writeUInt8(byteBuffer, this.numberOfFrames);
        IsoTypeWriter.writeUInt24(byteBuffer, this.reserved2);
        byteBuffer.put(this.rest);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.rest.length + 28;
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.SampleEntry
    public int getDataReferenceIndex() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.dataReferenceIndex;
    }

    public long getFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this));
        return this.flags;
    }

    public int getFrameDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.frameDuration;
    }

    public int getNumberOfFrames() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        return this.numberOfFrames;
    }

    public int getReserved1() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this));
        return this.reserved1;
    }

    public int getReserved2() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this));
        return this.reserved2;
    }

    public byte[] getRest() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this));
        return this.rest;
    }

    public int getTimeScale() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.timeScale;
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public void setBoxes(List<Box> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this, list));
        throw new RuntimeException("Time Code Box doesn't accept any children");
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.SampleEntry
    public void setDataReferenceIndex(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.dataReferenceIndex = i;
    }

    public void setFlags(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this, Conversions.longObject(j)));
        this.flags = j;
    }

    public void setFrameDuration(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, Conversions.intObject(i)));
        this.frameDuration = i;
    }

    public void setNumberOfFrames(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, Conversions.intObject(i)));
        this.numberOfFrames = i;
    }

    public void setReserved1(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, Conversions.intObject(i)));
        this.reserved1 = i;
    }

    public void setReserved2(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this, Conversions.intObject(i)));
        this.reserved2 = i;
    }

    public void setRest(byte[] bArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this, bArr));
        this.rest = bArr;
    }

    public void setTimeScale(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, Conversions.intObject(i)));
        this.timeScale = i;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "TimeCodeBox{timeScale=" + this.timeScale + ", frameDuration=" + this.frameDuration + ", numberOfFrames=" + this.numberOfFrames + ", reserved1=" + this.reserved1 + ", reserved2=" + this.reserved2 + ", flags=" + this.flags + '}';
    }

    @Override // szcom.coremedia.iso.boxes.Container
    public void writeContainer(WritableByteChannel writableByteChannel) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_22, this, this, writableByteChannel));
    }
}
