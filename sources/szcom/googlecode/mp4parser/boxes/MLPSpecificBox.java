package szcom.googlecode.mp4parser.boxes;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitReaderBuffer;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BitWriterBuffer;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MLPSpecificBox extends AbstractBox {
    public static final String TYPE = "dmlp";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    int format_info;
    int peak_data_rate;
    int reserved;
    int reserved2;

    static {
        ajc$preClinit();
    }

    public MLPSpecificBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("MLPSpecificBox.java", MLPSpecificBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFormat_info", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "", "", "", "int"), 49);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFormat_info", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "int", "format_info", "", "void"), 53);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPeak_data_rate", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "", "", "", "int"), 57);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPeak_data_rate", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "int", "peak_data_rate", "", "void"), 61);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "", "", "", "int"), 65);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "int", "reserved", "", "void"), 69);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved2", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "", "", "", "int"), 73);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved2", "szcom.googlecode.mp4parser.boxes.MLPSpecificBox", "int", "reserved2", "", "void"), 77);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        BitReaderBuffer bitReaderBuffer = new BitReaderBuffer(byteBuffer);
        this.format_info = bitReaderBuffer.readBits(32);
        this.peak_data_rate = bitReaderBuffer.readBits(15);
        this.reserved = bitReaderBuffer.readBits(1);
        this.reserved2 = bitReaderBuffer.readBits(32);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        BitWriterBuffer bitWriterBuffer = new BitWriterBuffer(byteBuffer);
        bitWriterBuffer.writeBits(this.format_info, 32);
        bitWriterBuffer.writeBits(this.peak_data_rate, 15);
        bitWriterBuffer.writeBits(this.reserved, 1);
        bitWriterBuffer.writeBits(this.reserved2, 32);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 10L;
    }

    public int getFormat_info() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.format_info;
    }

    public int getPeak_data_rate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.peak_data_rate;
    }

    public int getReserved() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.reserved;
    }

    public int getReserved2() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.reserved2;
    }

    public void setFormat_info(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.format_info = i;
    }

    public void setPeak_data_rate(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.peak_data_rate = i;
    }

    public void setReserved(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.reserved = i;
    }

    public void setReserved2(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.reserved2 = i;
    }
}
