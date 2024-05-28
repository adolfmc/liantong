package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ClassificationBox extends AbstractFullBox {
    public static final String TYPE = "clsf";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private String classificationEntity;
    private String classificationInfo;
    private int classificationTableIndex;
    private String language;

    static {
        ajc$preClinit();
    }

    public ClassificationBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("ClassificationBox.java", ClassificationBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLanguage", "szcom.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 44);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getClassificationEntity", "szcom.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 48);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getClassificationTableIndex", "szcom.coremedia.iso.boxes.ClassificationBox", "", "", "", "int"), 52);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getClassificationInfo", "szcom.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 56);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setClassificationEntity", "szcom.coremedia.iso.boxes.ClassificationBox", "java.lang.String", "classificationEntity", "", "void"), 60);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setClassificationTableIndex", "szcom.coremedia.iso.boxes.ClassificationBox", "int", "classificationTableIndex", "", "void"), 64);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLanguage", "szcom.coremedia.iso.boxes.ClassificationBox", "java.lang.String", "language", "", "void"), 68);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setClassificationInfo", "szcom.coremedia.iso.boxes.ClassificationBox", "java.lang.String", "classificationInfo", "", "void"), 72);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.ClassificationBox", "", "", "", "java.lang.String"), 101);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        byte[] bArr = new byte[4];
        byteBuffer.get(bArr);
        this.classificationEntity = IsoFile.bytesToFourCC(bArr);
        this.classificationTableIndex = IsoTypeReader.readUInt16(byteBuffer);
        this.language = IsoTypeReader.readIso639(byteBuffer);
        this.classificationInfo = IsoTypeReader.readString(byteBuffer);
    }

    public String getClassificationEntity() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.classificationEntity;
    }

    public String getClassificationInfo() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        return this.classificationInfo;
    }

    public int getClassificationTableIndex() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.classificationTableIndex;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.put(IsoFile.fourCCtoBytes(this.classificationEntity));
        IsoTypeWriter.writeUInt16(byteBuffer, this.classificationTableIndex);
        IsoTypeWriter.writeIso639(byteBuffer, this.language);
        byteBuffer.put(Utf8.convert(this.classificationInfo));
        byteBuffer.put((byte) 0);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.classificationInfo) + 8 + 1;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.language;
    }

    public void setClassificationEntity(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, str));
        this.classificationEntity = str;
    }

    public void setClassificationInfo(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, str));
        this.classificationInfo = str;
    }

    public void setClassificationTableIndex(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.classificationTableIndex = i;
    }

    public void setLanguage(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, str));
        this.language = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return "ClassificationBox[language=" + getLanguage() + "classificationEntity=" + getClassificationEntity() + ";classificationTableIndex=" + getClassificationTableIndex() + ";language=" + getLanguage() + ";classificationInfo=" + getClassificationInfo() + "]";
    }
}
