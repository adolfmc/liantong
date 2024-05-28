package szcom.mp4parser.iso14496.part12;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SampleAuxiliaryInformationOffsetsBox extends AbstractFullBox {
    public static final String TYPE = "saio";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private String auxInfoType;
    private String auxInfoTypeParameter;
    private long[] offsets;

    static {
        ajc$preClinit();
    }

    public SampleAuxiliaryInformationOffsetsBox() {
        super(TYPE);
        this.offsets = new long[0];
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("SampleAuxiliaryInformationOffsetsBox.java", SampleAuxiliaryInformationOffsetsBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAuxInfoType", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "", "", "", "java.lang.String"), 107);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAuxInfoType", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "java.lang.String", "auxInfoType", "", "void"), 111);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAuxInfoTypeParameter", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "", "", "", "java.lang.String"), 115);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAuxInfoTypeParameter", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "java.lang.String", "auxInfoTypeParameter", "", "void"), 119);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getOffsets", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "", "", "", "[J"), 123);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setOffsets", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox", "[J", "offsets", "", "void"), 127);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        if ((getFlags() & 1) == 1) {
            this.auxInfoType = IsoTypeReader.read4cc(byteBuffer);
            this.auxInfoTypeParameter = IsoTypeReader.read4cc(byteBuffer);
        }
        int l2i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        this.offsets = new long[l2i];
        for (int i = 0; i < l2i; i++) {
            if (getVersion() == 0) {
                this.offsets[i] = IsoTypeReader.readUInt32(byteBuffer);
            } else {
                this.offsets[i] = IsoTypeReader.readUInt64(byteBuffer);
            }
        }
    }

    public String getAuxInfoType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.auxInfoType;
    }

    public String getAuxInfoTypeParameter() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.auxInfoTypeParameter;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        if ((getFlags() & 1) == 1) {
            byteBuffer.put(IsoFile.fourCCtoBytes(this.auxInfoType));
            byteBuffer.put(IsoFile.fourCCtoBytes(this.auxInfoTypeParameter));
        }
        IsoTypeWriter.writeUInt32(byteBuffer, this.offsets.length);
        for (long j : this.offsets) {
            Long valueOf = Long.valueOf(j);
            if (getVersion() == 0) {
                IsoTypeWriter.writeUInt32(byteBuffer, valueOf.longValue());
            } else {
                IsoTypeWriter.writeUInt64(byteBuffer, valueOf.longValue());
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (getVersion() == 0 ? this.offsets.length * 4 : this.offsets.length * 8) + 8 + ((getFlags() & 1) != 1 ? 0 : 8);
    }

    public long[] getOffsets() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.offsets;
    }

    public void setAuxInfoType(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.auxInfoType = str;
    }

    public void setAuxInfoTypeParameter(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.auxInfoTypeParameter = str;
    }

    public void setOffsets(long[] jArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, jArr));
        this.offsets = jArr;
    }
}
