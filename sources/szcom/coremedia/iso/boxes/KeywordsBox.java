package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class KeywordsBox extends AbstractFullBox {
    public static final String TYPE = "kywd";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private String[] keywords;
    private String language;

    static {
        ajc$preClinit();
    }

    public KeywordsBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("KeywordsBox.java", KeywordsBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLanguage", "szcom.coremedia.iso.boxes.KeywordsBox", "", "", "", "java.lang.String"), 40);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getKeywords", "szcom.coremedia.iso.boxes.KeywordsBox", "", "", "", "[Ljava.lang.String;"), 44);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLanguage", "szcom.coremedia.iso.boxes.KeywordsBox", "java.lang.String", "language", "", "void"), 48);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setKeywords", "szcom.coremedia.iso.boxes.KeywordsBox", "[Ljava.lang.String;", "keywords", "", "void"), 52);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.KeywordsBox", "", "", "", "java.lang.String"), 87);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.language = IsoTypeReader.readIso639(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.keywords = new String[readUInt8];
        for (int i = 0; i < readUInt8; i++) {
            IsoTypeReader.readUInt8(byteBuffer);
            this.keywords[i] = IsoTypeReader.readString(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        String[] strArr;
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeIso639(byteBuffer, this.language);
        IsoTypeWriter.writeUInt8(byteBuffer, this.keywords.length);
        for (String str : this.keywords) {
            IsoTypeWriter.writeUInt8(byteBuffer, Utf8.utf8StringLengthInBytes(str) + 1);
            byteBuffer.put(Utf8.convert(str));
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        long j = 7;
        for (String str : this.keywords) {
            j += Utf8.utf8StringLengthInBytes(str) + 1 + 1;
        }
        return j;
    }

    public String[] getKeywords() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.keywords;
    }

    public String getLanguage() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.language;
    }

    public void setKeywords(String[] strArr) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, (Object) this, (Object) this, (Object) strArr));
        this.keywords = strArr;
    }

    public void setLanguage(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, str));
        this.language = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("KeywordsBox[language=");
        stringBuffer.append(getLanguage());
        for (int i = 0; i < this.keywords.length; i++) {
            stringBuffer.append(";keyword");
            stringBuffer.append(i);
            stringBuffer.append("=");
            stringBuffer.append(this.keywords[i]);
        }
        stringBuffer.append("]");
        return stringBuffer.toString();
    }
}
