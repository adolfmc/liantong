package szcom.mp4parser.iso14496.part15;

import com.cjt2325.cameralibrary.CameraInterface;
import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TierInfoBox extends AbstractBox {
    public static final String TYPE = "tiri";
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
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    int constantFrameRate;
    int discardable;
    int frameRate;
    int levelIndication;
    int profileIndication;
    int profile_compatibility;
    int reserved1;
    int reserved2;
    int tierID;
    int visualHeight;
    int visualWidth;

    static {
        ajc$preClinit();
    }

    public TierInfoBox() {
        super(TYPE);
        this.reserved1 = 0;
        this.reserved2 = 0;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TierInfoBox.java", TierInfoBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getTierID", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 69);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setTierID", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "tierID", "", "void"), 73);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getVisualWidth", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 109);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setVisualWidth", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "visualWidth", "", "void"), 113);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getVisualHeight", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 117);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setVisualHeight", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "visualHeight", "", "void"), 121);
        ajc$tjp_14 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDiscardable", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 125);
        ajc$tjp_15 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDiscardable", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "discardable", "", "void"), 129);
        ajc$tjp_16 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getConstantFrameRate", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 133);
        ajc$tjp_17 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setConstantFrameRate", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "constantFrameRate", "", "void"), 137);
        ajc$tjp_18 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved2", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 141);
        ajc$tjp_19 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved2", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "reserved2", "", "void"), CameraInterface.TYPE_CAPTURE);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProfileIndication", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 77);
        ajc$tjp_20 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFrameRate", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 149);
        ajc$tjp_21 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFrameRate", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "frameRate", "", "void"), 153);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProfileIndication", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "profileIndication", "", "void"), 81);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProfile_compatibility", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 85);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProfile_compatibility", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "profile_compatibility", "", "void"), 89);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLevelIndication", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 93);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLevelIndication", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "levelIndication", "", "void"), 97);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getReserved1", "szcom.mp4parser.iso14496.part15.TierInfoBox", "", "", "", "int"), 101);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setReserved1", "szcom.mp4parser.iso14496.part15.TierInfoBox", "int", "reserved1", "", "void"), 105);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.tierID = IsoTypeReader.readUInt16(byteBuffer);
        this.profileIndication = IsoTypeReader.readUInt8(byteBuffer);
        this.profile_compatibility = IsoTypeReader.readUInt8(byteBuffer);
        this.levelIndication = IsoTypeReader.readUInt8(byteBuffer);
        this.reserved1 = IsoTypeReader.readUInt8(byteBuffer);
        this.visualWidth = IsoTypeReader.readUInt16(byteBuffer);
        this.visualHeight = IsoTypeReader.readUInt16(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.discardable = (readUInt8 & 192) >> 6;
        this.constantFrameRate = (readUInt8 & 48) >> 4;
        this.reserved2 = readUInt8 & 15;
        this.frameRate = IsoTypeReader.readUInt16(byteBuffer);
    }

    public int getConstantFrameRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_16, this, this));
        return this.constantFrameRate;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt16(byteBuffer, this.tierID);
        IsoTypeWriter.writeUInt8(byteBuffer, this.profileIndication);
        IsoTypeWriter.writeUInt8(byteBuffer, this.profile_compatibility);
        IsoTypeWriter.writeUInt8(byteBuffer, this.levelIndication);
        IsoTypeWriter.writeUInt8(byteBuffer, this.reserved1);
        IsoTypeWriter.writeUInt16(byteBuffer, this.visualWidth);
        IsoTypeWriter.writeUInt16(byteBuffer, this.visualHeight);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.discardable << 6) + (this.constantFrameRate << 4) + this.reserved2);
        IsoTypeWriter.writeUInt16(byteBuffer, this.frameRate);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 13L;
    }

    public int getDiscardable() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_14, this, this));
        return this.discardable;
    }

    public int getFrameRate() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_20, this, this));
        return this.frameRate;
    }

    public int getLevelIndication() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.levelIndication;
    }

    public int getProfileIndication() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.profileIndication;
    }

    public int getProfile_compatibility() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.profile_compatibility;
    }

    public int getReserved1() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.reserved1;
    }

    public int getReserved2() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_18, this, this));
        return this.reserved2;
    }

    public int getTierID() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.tierID;
    }

    public int getVisualHeight() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.visualHeight;
    }

    public int getVisualWidth() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.visualWidth;
    }

    public void setConstantFrameRate(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_17, this, this, Conversions.intObject(i)));
        this.constantFrameRate = i;
    }

    public void setDiscardable(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_15, this, this, Conversions.intObject(i)));
        this.discardable = i;
    }

    public void setFrameRate(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_21, this, this, Conversions.intObject(i)));
        this.frameRate = i;
    }

    public void setLevelIndication(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.levelIndication = i;
    }

    public void setProfileIndication(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.profileIndication = i;
    }

    public void setProfile_compatibility(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.profile_compatibility = i;
    }

    public void setReserved1(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, Conversions.intObject(i)));
        this.reserved1 = i;
    }

    public void setReserved2(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_19, this, this, Conversions.intObject(i)));
        this.reserved2 = i;
    }

    public void setTierID(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.tierID = i;
    }

    public void setVisualHeight(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, Conversions.intObject(i)));
        this.visualHeight = i;
    }

    public void setVisualWidth(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, Conversions.intObject(i)));
        this.visualWidth = i;
    }
}
