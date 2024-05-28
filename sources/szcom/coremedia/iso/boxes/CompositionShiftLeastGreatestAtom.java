package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CompositionShiftLeastGreatestAtom extends AbstractFullBox {
    public static final String TYPE = "cslg";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    int compositionOffsetToDisplayOffsetShift;
    int displayEndTime;
    int displayStartTime;
    int greatestDisplayOffset;
    int leastDisplayOffset;

    static {
        ajc$preClinit();
    }

    public CompositionShiftLeastGreatestAtom() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("CompositionShiftLeastGreatestAtom.java", CompositionShiftLeastGreatestAtom.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCompositionOffsetToDisplayOffsetShift", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 66);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCompositionOffsetToDisplayOffsetShift", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "compositionOffsetToDisplayOffsetShift", "", "void"), 70);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLeastDisplayOffset", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 74);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLeastDisplayOffset", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "leastDisplayOffset", "", "void"), 78);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGreatestDisplayOffset", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 82);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGreatestDisplayOffset", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "greatestDisplayOffset", "", "void"), 86);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDisplayStartTime", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 90);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDisplayStartTime", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "displayStartTime", "", "void"), 94);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDisplayEndTime", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "", "", "", "int"), 98);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDisplayEndTime", "szcom.coremedia.iso.boxes.CompositionShiftLeastGreatestAtom", "int", "displayEndTime", "", "void"), 102);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.compositionOffsetToDisplayOffsetShift = byteBuffer.getInt();
        this.leastDisplayOffset = byteBuffer.getInt();
        this.greatestDisplayOffset = byteBuffer.getInt();
        this.displayStartTime = byteBuffer.getInt();
        this.displayEndTime = byteBuffer.getInt();
    }

    public int getCompositionOffsetToDisplayOffsetShift() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.compositionOffsetToDisplayOffsetShift;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.putInt(this.compositionOffsetToDisplayOffsetShift);
        byteBuffer.putInt(this.leastDisplayOffset);
        byteBuffer.putInt(this.greatestDisplayOffset);
        byteBuffer.putInt(this.displayStartTime);
        byteBuffer.putInt(this.displayEndTime);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 24L;
    }

    public int getDisplayEndTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.displayEndTime;
    }

    public int getDisplayStartTime() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.displayStartTime;
    }

    public int getGreatestDisplayOffset() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.greatestDisplayOffset;
    }

    public int getLeastDisplayOffset() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.leastDisplayOffset;
    }

    public void setCompositionOffsetToDisplayOffsetShift(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.compositionOffsetToDisplayOffsetShift = i;
    }

    public void setDisplayEndTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.intObject(i)));
        this.displayEndTime = i;
    }

    public void setDisplayStartTime(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.displayStartTime = i;
    }

    public void setGreatestDisplayOffset(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.greatestDisplayOffset = i;
    }

    public void setLeastDisplayOffset(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.leastDisplayOffset = i;
    }
}
