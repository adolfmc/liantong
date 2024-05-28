package szcom.mp4parser.iso14496.part30;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class WebVTTConfigurationBox extends AbstractBox {
    public static final String TYPE = "vttC";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    String config;

    static {
        ajc$preClinit();
    }

    public WebVTTConfigurationBox() {
        super(TYPE);
        this.config = "";
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("WebVTTConfigurationBox.java", WebVTTConfigurationBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getConfig", "szcom.mp4parser.iso14496.part30.WebVTTConfigurationBox", "", "", "", "java.lang.String"), 36);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setConfig", "szcom.mp4parser.iso14496.part30.WebVTTConfigurationBox", "java.lang.String", "config", "", "void"), 40);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        this.config = IsoTypeReader.readString(byteBuffer, byteBuffer.remaining());
    }

    public String getConfig() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.config;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        byteBuffer.put(Utf8.convert(this.config));
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return Utf8.utf8StringLengthInBytes(this.config);
    }

    public void setConfig(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.config = str;
    }
}
