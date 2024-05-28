package szcom.mp4parser.iso14496.part15;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class PriotityRangeBox extends AbstractBox {
    public static final String TYPE = "svpr";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    int max_priorityId;
    int min_priorityId;
    int reserved1;
    int reserved2;

    static {
        ajc$preClinit();
    }

    public PriotityRangeBox() {
        super(TYPE);
        this.reserved1 = 0;
        this.reserved2 = 0;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("PriotityRangeBox.java", PriotityRangeBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved1", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 45);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved1", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "int", "reserved1", "", "void"), 49);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMin_priorityId", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 53);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMin_priorityId", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "int", "min_priorityId", "", "void"), 57);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved2", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 61);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved2", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "int", "reserved2", "", "void"), 65);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMax_priorityId", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "", "", "", "int"), 69);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMax_priorityId", "szcom.mp4parser.iso14496.part15.PriotityRangeBox", "int", "max_priorityId", "", "void"), 73);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.min_priorityId = IsoTypeReader.readUInt8(byteBuffer);
        int i = this.min_priorityId;
        this.reserved1 = (i & 192) >> 6;
        this.min_priorityId = i & 63;
        this.max_priorityId = IsoTypeReader.readUInt8(byteBuffer);
        int i2 = this.max_priorityId;
        this.reserved2 = (i2 & 192) >> 6;
        this.max_priorityId = i2 & 63;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved1 << 6) + this.min_priorityId);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.reserved2 << 6) + this.max_priorityId);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 2L;
    }

    public int getMax_priorityId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.max_priorityId;
    }

    public int getMin_priorityId() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.min_priorityId;
    }

    public int getReserved1() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.reserved1;
    }

    public int getReserved2() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.reserved2;
    }

    public void setMax_priorityId(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.max_priorityId = i;
    }

    public void setMin_priorityId(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.min_priorityId = i;
    }

    public void setReserved1(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.reserved1 = i;
    }

    public void setReserved2(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.reserved2 = i;
    }
}
