package szcom.googlecode.mp4parser.boxes.mp4;

import java.nio.ByteBuffer;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ESDescriptorBox extends AbstractDescriptorBox {
    public static final String TYPE = "esds";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;

    static {
        ajc$preClinit();
    }

    public ESDescriptorBox() {
        super(TYPE);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("ESDescriptorBox.java", ESDescriptorBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEsDescriptor", "szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", "", "", "", "szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor"), 35);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEsDescriptor", "szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", "szcom.googlecode.mp4parser.boxes.mp4.objectdescriptors.ESDescriptor", "esDescriptor", "", "void"), 39);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "equals", "szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", "java.lang.Object", "o", "", "boolean"), 44);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hashCode", "szcom.googlecode.mp4parser.boxes.mp4.ESDescriptorBox", "", "", "", "int"), 55);
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ESDescriptorBox eSDescriptorBox = (ESDescriptorBox) obj;
        return this.data == null ? eSDescriptorBox.data == null : this.data.equals(eSDescriptorBox.data);
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox, szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        ESDescriptor esDescriptor = getEsDescriptor();
        byteBuffer.put(esDescriptor != null ? (ByteBuffer) esDescriptor.serialize().rewind() : this.data.duplicate());
    }

    @Override // szcom.googlecode.mp4parser.boxes.mp4.AbstractDescriptorBox, szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        ESDescriptor esDescriptor = getEsDescriptor();
        return (esDescriptor != null ? esDescriptor.getSize() : this.data.remaining()) + 4;
    }

    public ESDescriptor getEsDescriptor() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return (ESDescriptor) super.getDescriptor();
    }

    public int hashCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this));
        if (this.data != null) {
            return this.data.hashCode();
        }
        return 0;
    }

    public void setEsDescriptor(ESDescriptor eSDescriptor) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, eSDescriptor));
        super.setDescriptor(eSDescriptor);
    }
}
