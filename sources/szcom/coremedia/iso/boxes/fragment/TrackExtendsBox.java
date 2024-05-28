package szcom.coremedia.iso.boxes.fragment;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrackExtendsBox extends AbstractFullBox {
    public static final String TYPE = "trex";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private long defaultSampleDescriptionIndex;
    private long defaultSampleDuration;
    private SampleFlags defaultSampleFlags;
    private long defaultSampleSize;
    private long trackId;

    static {
        ajc$preClinit();
    }

    public TrackExtendsBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrackExtendsBox.java", TrackExtendsBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTrackId", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 72);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleDescriptionIndex", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 76);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "szcom.coremedia.iso.boxes.fragment.SampleFlags", "defaultSampleFlags", "", "void"), 112);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleDuration", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 80);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleSize", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "long"), 84);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleFlags", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "szcom.coremedia.iso.boxes.fragment.SampleFlags"), 88);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleFlagsStr", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "", "", "", "java.lang.String"), 92);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTrackId", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "trackId", "", "void"), 96);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleDescriptionIndex", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "defaultSampleDescriptionIndex", "", "void"), 100);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleDuration", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "defaultSampleDuration", "", "void"), 104);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleSize", "szcom.coremedia.iso.boxes.fragment.TrackExtendsBox", "long", "defaultSampleSize", "", "void"), 108);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.trackId = IsoTypeReader.readUInt32(byteBuffer);
        this.defaultSampleDescriptionIndex = IsoTypeReader.readUInt32(byteBuffer);
        this.defaultSampleDuration = IsoTypeReader.readUInt32(byteBuffer);
        this.defaultSampleSize = IsoTypeReader.readUInt32(byteBuffer);
        this.defaultSampleFlags = new SampleFlags(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt32(byteBuffer, this.trackId);
        IsoTypeWriter.writeUInt32(byteBuffer, this.defaultSampleDescriptionIndex);
        IsoTypeWriter.writeUInt32(byteBuffer, this.defaultSampleDuration);
        IsoTypeWriter.writeUInt32(byteBuffer, this.defaultSampleSize);
        this.defaultSampleFlags.getContent(byteBuffer);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 24L;
    }

    public long getDefaultSampleDescriptionIndex() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.defaultSampleDescriptionIndex;
    }

    public long getDefaultSampleDuration() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.defaultSampleDuration;
    }

    public SampleFlags getDefaultSampleFlags() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.defaultSampleFlags;
    }

    public String getDefaultSampleFlagsStr() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.defaultSampleFlags.toString();
    }

    public long getDefaultSampleSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.defaultSampleSize;
    }

    public long getTrackId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.trackId;
    }

    public void setDefaultSampleDescriptionIndex(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.longObject(j)));
        this.defaultSampleDescriptionIndex = j;
    }

    public void setDefaultSampleDuration(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, Conversions.longObject(j)));
        this.defaultSampleDuration = j;
    }

    public void setDefaultSampleFlags(SampleFlags sampleFlags) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, sampleFlags));
        this.defaultSampleFlags = sampleFlags;
    }

    public void setDefaultSampleSize(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.longObject(j)));
        this.defaultSampleSize = j;
    }

    public void setTrackId(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, Conversions.longObject(j)));
        this.trackId = j;
    }
}
