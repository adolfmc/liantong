package szcom.googlecode.mp4parser.boxes.dece;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class BaseLocationBox extends AbstractFullBox {
    public static final String TYPE = "bloc";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    String baseLocation;
    String purchaseLocation;

    static {
        ajc$preClinit();
    }

    public BaseLocationBox() {
        super(TYPE);
        this.baseLocation = "";
        this.purchaseLocation = "";
    }

    public BaseLocationBox(String str, String str2) {
        super(TYPE);
        this.baseLocation = "";
        this.purchaseLocation = "";
        this.baseLocation = str;
        this.purchaseLocation = str2;
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("BaseLocationBox.java", BaseLocationBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBaseLocation", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "java.lang.String"), 44);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBaseLocation", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "java.lang.String", "baseLocation", "", "void"), 48);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getPurchaseLocation", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "java.lang.String"), 52);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setPurchaseLocation", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "java.lang.String", "purchaseLocation", "", "void"), 56);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "equals", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "java.lang.Object", "o", "", "boolean"), 86);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hashCode", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "int"), 100);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.dece.BaseLocationBox", "", "", "", "java.lang.String"), 107);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.baseLocation = IsoTypeReader.readString(byteBuffer);
        byteBuffer.get(new byte[(256 - Utf8.utf8StringLengthInBytes(this.baseLocation)) - 1]);
        this.purchaseLocation = IsoTypeReader.readString(byteBuffer);
        byteBuffer.get(new byte[(256 - Utf8.utf8StringLengthInBytes(this.purchaseLocation)) - 1]);
        byteBuffer.get(new byte[512]);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        BaseLocationBox baseLocationBox = (BaseLocationBox) obj;
        String str = this.baseLocation;
        if (str == null ? baseLocationBox.baseLocation == null : str.equals(baseLocationBox.baseLocation)) {
            String str2 = this.purchaseLocation;
            return str2 == null ? baseLocationBox.purchaseLocation == null : str2.equals(baseLocationBox.purchaseLocation);
        }
        return false;
    }

    public String getBaseLocation() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.baseLocation;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(Utf8.convert(this.baseLocation));
        byteBuffer.put(new byte[256 - Utf8.utf8StringLengthInBytes(this.baseLocation)]);
        byteBuffer.put(Utf8.convert(this.purchaseLocation));
        byteBuffer.put(new byte[256 - Utf8.utf8StringLengthInBytes(this.purchaseLocation)]);
        byteBuffer.put(new byte[512]);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return 1028L;
    }

    public String getPurchaseLocation() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.purchaseLocation;
    }

    public int hashCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        String str = this.baseLocation;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.purchaseLocation;
        return hashCode + (str2 != null ? str2.hashCode() : 0);
    }

    public void setBaseLocation(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.baseLocation = str;
    }

    public void setPurchaseLocation(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, str));
        this.purchaseLocation = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return "BaseLocationBox{baseLocation='" + this.baseLocation + "', purchaseLocation='" + this.purchaseLocation + "'}";
    }
}
