package szcom.coremedia.iso.boxes.dece;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class TrickPlayBox extends AbstractFullBox {
    public static final String TYPE = "trik";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private List<Entry> entries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        private int value;

        public Entry() {
        }

        public Entry(int i) {
            this.value = i;
        }

        public int getDependencyLevel() {
            return this.value & 63;
        }

        public int getPicType() {
            return (this.value >> 6) & 3;
        }

        public void setDependencyLevel(int i) {
            this.value = (i & 63) | this.value;
        }

        public void setPicType(int i) {
            this.value &= 31;
            this.value = ((i & 3) << 6) | this.value;
        }

        public String toString() {
            return "Entry{picType=" + getPicType() + ",dependencyLevel=" + getDependencyLevel() + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public TrickPlayBox() {
        super(TYPE);
        this.entries = new ArrayList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TrickPlayBox.java", TrickPlayBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.coremedia.iso.boxes.dece.TrickPlayBox", "java.util.List", "entries", "", "void"), 32);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.coremedia.iso.boxes.dece.TrickPlayBox", "", "", "", "java.util.List"), 36);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.dece.TrickPlayBox", "", "", "", "java.lang.String"), 103);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        while (byteBuffer.remaining() > 0) {
            this.entries.add(new Entry(IsoTypeReader.readUInt8(byteBuffer)));
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        for (Entry entry : this.entries) {
            IsoTypeWriter.writeUInt8(byteBuffer, entry.value);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return this.entries.size() + 4;
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.entries;
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this, list));
        this.entries = list;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "TrickPlayBox{entries=" + this.entries + '}';
    }
}
