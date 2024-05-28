package szcom.mp4parser.iso14496.part12;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public final class BitRateBox extends AbstractBox {
    public static final String TYPE = "btrt";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private long avgBitrate;
    private long bufferSizeDb;
    private long maxBitrate;

    static {
        ajc$preClinit();
    }

    public BitRateBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("BitRateBox.java", BitRateBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBufferSizeDb", "szcom.mp4parser.iso14496.part12.BitRateBox", "", "", "", "long"), 74);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBufferSizeDb", "szcom.mp4parser.iso14496.part12.BitRateBox", "long", "bufferSizeDb", "", "void"), 82);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMaxBitrate", "szcom.mp4parser.iso14496.part12.BitRateBox", "", "", "", "long"), 90);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMaxBitrate", "szcom.mp4parser.iso14496.part12.BitRateBox", "long", "maxBitrate", "", "void"), 98);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAvgBitrate", "szcom.mp4parser.iso14496.part12.BitRateBox", "", "", "", "long"), 106);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAvgBitrate", "szcom.mp4parser.iso14496.part12.BitRateBox", "long", "avgBitrate", "", "void"), 114);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.bufferSizeDb = IsoTypeReader.readUInt32(byteBuffer);
        this.maxBitrate = IsoTypeReader.readUInt32(byteBuffer);
        this.avgBitrate = IsoTypeReader.readUInt32(byteBuffer);
    }

    public long getAvgBitrate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.avgBitrate;
    }

    public long getBufferSizeDb() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.bufferSizeDb;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt32(byteBuffer, this.bufferSizeDb);
        IsoTypeWriter.writeUInt32(byteBuffer, this.maxBitrate);
        IsoTypeWriter.writeUInt32(byteBuffer, this.avgBitrate);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 12L;
    }

    public long getMaxBitrate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.maxBitrate;
    }

    public void setAvgBitrate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.longObject(j)));
        this.avgBitrate = j;
    }

    public void setBufferSizeDb(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.longObject(j)));
        this.bufferSizeDb = j;
    }

    public void setMaxBitrate(long j) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.longObject(j)));
        this.maxBitrate = j;
    }
}
