package szcom.googlecode.mp4parser.boxes.mp4;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.logging.Level;
import java.util.logging.Logger;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ObjectDescriptorFactory;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class AbstractDescriptorBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static Logger log;
    protected ByteBuffer data;
    protected BaseDescriptor descriptor;

    static {
        ajc$preClinit();
        log = Logger.getLogger(AbstractDescriptorBox.class.getName());
    }

    public AbstractDescriptorBox(String str) {
        super(str);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AbstractDescriptorBox.java", AbstractDescriptorBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getData", "szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "", "", "", "java.nio.ByteBuffer"), 42);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setData", "szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "java.nio.ByteBuffer", "data", "", "void"), 46);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDescriptor", "szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "", "", "", "szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor"), 62);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDescriptor", "szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.BaseDescriptor", "descriptor", "", "void"), 66);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDescriptorAsString", "szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox", "", "", "", "java.lang.String"), 70);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        this.data = byteBuffer.slice();
        byteBuffer.position(byteBuffer.position() + byteBuffer.remaining());
        try {
            this.data.rewind();
            this.descriptor = ObjectDescriptorFactory.createFrom(-1, this.data.duplicate());
        } catch (IOException | IndexOutOfBoundsException e) {
            log.log(Level.WARNING, "Error parsing ObjectDescriptor", e);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        this.data.rewind();
        byteBuffer.put(this.data);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.data.limit() + 4;
    }

    public ByteBuffer getData() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.data;
    }

    public BaseDescriptor getDescriptor() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.descriptor;
    }

    public String getDescriptorAsString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.descriptor.toString();
    }

    public void setData(ByteBuffer byteBuffer) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, byteBuffer));
        this.data = byteBuffer;
    }

    public void setDescriptor(BaseDescriptor baseDescriptor) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, baseDescriptor));
        this.descriptor = baseDescriptor;
    }
}
