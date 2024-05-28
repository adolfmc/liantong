package szcom.googlecode.mp4parser.boxes.dece;

import java.nio.ByteBuffer;
import java.util.LinkedHashMap;
import java.util.Map;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ContentInformationBox extends AbstractFullBox {
    public static final String TYPE = "cinf";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_12 = null;
    private static final JoinPoint.StaticPart ajc$tjp_13 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    Map<String, String> brandEntries;
    String codecs;
    Map<String, String> idEntries;
    String languages;
    String mimeSubtypeName;
    String profileLevelIdc;
    String protection;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class BrandEntry {
        String iso_brand;
        String version;

        public BrandEntry(String str, String str2) {
            this.iso_brand = str;
            this.version = str2;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            BrandEntry brandEntry = (BrandEntry) obj;
            String str = this.iso_brand;
            if (str == null ? brandEntry.iso_brand == null : str.equals(brandEntry.iso_brand)) {
                String str2 = this.version;
                return str2 == null ? brandEntry.version == null : str2.equals(brandEntry.version);
            }
            return false;
        }

        public int hashCode() {
            String str = this.iso_brand;
            int hashCode = (str != null ? str.hashCode() : 0) * 31;
            String str2 = this.version;
            return hashCode + (str2 != null ? str2.hashCode() : 0);
        }
    }

    static {
        ajc$preClinit();
    }

    public ContentInformationBox() {
        super(TYPE);
        this.brandEntries = new LinkedHashMap();
        this.idEntries = new LinkedHashMap();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("ContentInformationBox.java", ContentInformationBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getMimeSubtypeName", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 144);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setMimeSubtypeName", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "mimeSubtypeName", "", "void"), 148);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBrandEntries", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.util.Map"), 184);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBrandEntries", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.util.Map", "brandEntries", "", "void"), 188);
        ajc$tjp_12 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getIdEntries", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.util.Map"), 192);
        ajc$tjp_13 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setIdEntries", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.util.Map", "idEntries", "", "void"), 196);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProfileLevelIdc", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 152);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProfileLevelIdc", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "profileLevelIdc", "", "void"), 156);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getCodecs", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), C0567f.f1819h);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setCodecs", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "codecs", "", "void"), 164);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getProtection", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 168);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setProtection", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "protection", "", "void"), 172);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLanguages", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "", "", "", "java.lang.String"), 176);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLanguages", "szcom.googlecode.mp4parser.boxes.dece.ContentInformationBox", "java.lang.String", "languages", "", "void"), 180);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.mimeSubtypeName = IsoTypeReader.readString(byteBuffer);
        this.profileLevelIdc = IsoTypeReader.readString(byteBuffer);
        this.codecs = IsoTypeReader.readString(byteBuffer);
        this.protection = IsoTypeReader.readString(byteBuffer);
        this.languages = IsoTypeReader.readString(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        while (true) {
            int i = readUInt8 - 1;
            if (readUInt8 <= 0) {
                break;
            }
            this.brandEntries.put(IsoTypeReader.readString(byteBuffer), IsoTypeReader.readString(byteBuffer));
            readUInt8 = i;
        }
        int readUInt82 = IsoTypeReader.readUInt8(byteBuffer);
        while (true) {
            int i2 = readUInt82 - 1;
            if (readUInt82 <= 0) {
                return;
            }
            this.idEntries.put(IsoTypeReader.readString(byteBuffer), IsoTypeReader.readString(byteBuffer));
            readUInt82 = i2;
        }
    }

    public Map<String, String> getBrandEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, this, this));
        return this.brandEntries;
    }

    public String getCodecs() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.codecs;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.mimeSubtypeName);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.profileLevelIdc);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.codecs);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.protection);
        IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, this.languages);
        IsoTypeWriter.writeUInt8(byteBuffer, this.brandEntries.size());
        for (Map.Entry<String, String> entry : this.brandEntries.entrySet()) {
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, entry.getKey());
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, entry.getValue());
        }
        IsoTypeWriter.writeUInt8(byteBuffer, this.idEntries.size());
        for (Map.Entry<String, String> entry2 : this.idEntries.entrySet()) {
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, entry2.getKey());
            IsoTypeWriter.writeZeroTermUtf8String(byteBuffer, entry2.getValue());
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        long utf8StringLengthInBytes = Utf8.utf8StringLengthInBytes(this.mimeSubtypeName) + 1 + 4 + Utf8.utf8StringLengthInBytes(this.profileLevelIdc) + 1 + Utf8.utf8StringLengthInBytes(this.codecs) + 1 + Utf8.utf8StringLengthInBytes(this.protection) + 1 + Utf8.utf8StringLengthInBytes(this.languages) + 1 + 1;
        for (Map.Entry<String, String> entry : this.brandEntries.entrySet()) {
            utf8StringLengthInBytes = utf8StringLengthInBytes + Utf8.utf8StringLengthInBytes(entry.getKey()) + 1 + Utf8.utf8StringLengthInBytes(entry.getValue()) + 1;
        }
        long j = utf8StringLengthInBytes + 1;
        for (Map.Entry<String, String> entry2 : this.idEntries.entrySet()) {
            j = j + Utf8.utf8StringLengthInBytes(entry2.getKey()) + 1 + Utf8.utf8StringLengthInBytes(entry2.getValue()) + 1;
        }
        return j;
    }

    public Map<String, String> getIdEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_12, this, this));
        return this.idEntries;
    }

    public String getLanguages() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.languages;
    }

    public String getMimeSubtypeName() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.mimeSubtypeName;
    }

    public String getProfileLevelIdc() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.profileLevelIdc;
    }

    public String getProtection() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.protection;
    }

    public void setBrandEntries(Map<String, String> map) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, this, this, map));
        this.brandEntries = map;
    }

    public void setCodecs(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, str));
        this.codecs = str;
    }

    public void setIdEntries(Map<String, String> map) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_13, this, this, map));
        this.idEntries = map;
    }

    public void setLanguages(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, str));
        this.languages = str;
    }

    public void setMimeSubtypeName(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.mimeSubtypeName = str;
    }

    public void setProfileLevelIdc(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.profileLevelIdc = str;
    }

    public void setProtection(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, str));
        this.protection = str;
    }
}
