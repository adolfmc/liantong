package szcom.googlecode.mp4parser;

import java.nio.ByteBuffer;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.FullBox;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractFullBox extends AbstractBox implements FullBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private int flags;
    private int version;

    static {
        ajc$preClinit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFullBox(String str) {
        super(str);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractFullBox(String str, byte[] bArr) {
        super(str, bArr);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AbstractFullBox.java", AbstractFullBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setVersion", "szcom.googlecode.mp4parser.AbstractFullBox", "int", "version", "", "void"), 51);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setFlags", "szcom.googlecode.mp4parser.AbstractFullBox", "int", "flags", "", "void"), 64);
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    @DoNotParseDetail
    public int getFlags() {
        if (!this.isParsed) {
            parseDetails();
        }
        return this.flags;
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    @DoNotParseDetail
    public int getVersion() {
        if (!this.isParsed) {
            parseDetails();
        }
        return this.version;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final long parseVersionAndFlags(ByteBuffer byteBuffer) {
        this.version = IsoTypeReader.readUInt8(byteBuffer);
        this.flags = IsoTypeReader.readUInt24(byteBuffer);
        return 4L;
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    public void setFlags(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.flags = i;
    }

    @Override // szcom.coremedia.iso.boxes.FullBox
    public void setVersion(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this, Conversions.intObject(i)));
        this.version = i;
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public final void writeVersionAndFlags(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt8(byteBuffer, this.version);
        IsoTypeWriter.writeUInt24(byteBuffer, this.flags);
    }
}
