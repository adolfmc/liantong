package szcom.mp4parser.iso14496.part12;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SampleAuxiliaryInformationSizesBox extends AbstractFullBox {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    public static final String TYPE = "saiz";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    private String auxInfoType;
    private String auxInfoTypeParameter;
    private short defaultSampleInfoSize;
    private int sampleCount;
    private short[] sampleInfoSizes;

    static {
        ajc$preClinit();
    }

    public SampleAuxiliaryInformationSizesBox() {
        super(TYPE);
        this.sampleInfoSizes = new short[0];
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("SampleAuxiliaryInformationSizesBox.java", SampleAuxiliaryInformationSizesBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSize", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "int", "index", "", "short"), 57);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAuxInfoType", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "java.lang.String"), 106);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleCount", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "int", "sampleCount", "", "void"), 146);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "java.lang.String"), 151);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAuxInfoType", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "java.lang.String", "auxInfoType", "", "void"), 110);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getAuxInfoTypeParameter", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "java.lang.String"), 114);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setAuxInfoTypeParameter", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "java.lang.String", "auxInfoTypeParameter", "", "void"), 118);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultSampleInfoSize", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "int"), 122);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultSampleInfoSize", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "int", "defaultSampleInfoSize", "", "void"), 126);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleInfoSizes", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "[S"), 131);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setSampleInfoSizes", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "[S", "sampleInfoSizes", "", "void"), 137);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getSampleCount", "szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox", "", "", "", "int"), 142);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        if ((getFlags() & 1) == 1) {
            this.auxInfoType = IsoTypeReader.read4cc(byteBuffer);
            this.auxInfoTypeParameter = IsoTypeReader.read4cc(byteBuffer);
        }
        this.defaultSampleInfoSize = (short) IsoTypeReader.readUInt8(byteBuffer);
        this.sampleCount = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        if (this.defaultSampleInfoSize == 0) {
            this.sampleInfoSizes = new short[this.sampleCount];
            for (int i = 0; i < this.sampleCount; i++) {
                this.sampleInfoSizes[i] = (short) IsoTypeReader.readUInt8(byteBuffer);
            }
        }
    }

    public String getAuxInfoType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.auxInfoType;
    }

    public String getAuxInfoTypeParameter() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.auxInfoTypeParameter;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        if ((getFlags() & 1) == 1) {
            byteBuffer.put(IsoFile.fourCCtoBytes(this.auxInfoType));
            byteBuffer.put(IsoFile.fourCCtoBytes(this.auxInfoTypeParameter));
        }
        IsoTypeWriter.writeUInt8(byteBuffer, this.defaultSampleInfoSize);
        if (this.defaultSampleInfoSize != 0) {
            IsoTypeWriter.writeUInt32(byteBuffer, this.sampleCount);
            return;
        }
        IsoTypeWriter.writeUInt32(byteBuffer, this.sampleInfoSizes.length);
        for (short s : this.sampleInfoSizes) {
            IsoTypeWriter.writeUInt8(byteBuffer, s);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return ((getFlags() & 1) == 1 ? 12 : 4) + 5 + (this.defaultSampleInfoSize == 0 ? this.sampleInfoSizes.length : 0);
    }

    public int getDefaultSampleInfoSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        return this.defaultSampleInfoSize;
    }

    public int getSampleCount() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this));
        return this.sampleCount;
    }

    public short[] getSampleInfoSizes() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        short[] sArr = this.sampleInfoSizes;
        short[] sArr2 = new short[sArr.length];
        System.arraycopy(sArr, 0, sArr2, 0, sArr.length);
        return sArr2;
    }

    public short getSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this, Conversions.intObject(i)));
        return getDefaultSampleInfoSize() == 0 ? this.sampleInfoSizes[i] : this.defaultSampleInfoSize;
    }

    public void setAuxInfoType(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, str));
        this.auxInfoType = str;
    }

    public void setAuxInfoTypeParameter(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, str));
        this.auxInfoTypeParameter = str;
    }

    public void setDefaultSampleInfoSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, Conversions.intObject(i)));
        this.defaultSampleInfoSize = (short) i;
    }

    public void setSampleCount(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this, Conversions.intObject(i)));
        this.sampleCount = i;
    }

    public void setSampleInfoSizes(short[] sArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this, sArr));
        this.sampleInfoSizes = new short[sArr.length];
        System.arraycopy(sArr, 0, this.sampleInfoSizes, 0, sArr.length);
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this));
        return "SampleAuxiliaryInformationSizesBox{defaultSampleInfoSize=" + ((int) this.defaultSampleInfoSize) + ", sampleCount=" + this.sampleCount + ", auxInfoType='" + this.auxInfoType + "', auxInfoTypeParameter='" + this.auxInfoTypeParameter + "'}";
    }
}
