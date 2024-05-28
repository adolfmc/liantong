package szcom.coremedia.iso.boxes;

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
public class SampleDependencyTypeBox extends AbstractFullBox {
    public static final String TYPE = "sdtp";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private List<Entry> entries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class Entry {
        private int value;

        public Entry(int i) {
            this.value = i;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return obj != null && getClass() == obj.getClass() && this.value == ((Entry) obj).value;
        }

        public int getIsLeading() {
            return (this.value >> 6) & 3;
        }

        public int getSampleDependsOn() {
            return (this.value >> 4) & 3;
        }

        public int getSampleHasRedundancy() {
            return this.value & 3;
        }

        public int getSampleIsDependentOn() {
            return (this.value >> 2) & 3;
        }

        public int hashCode() {
            return this.value;
        }

        public void setIsLeading(int i) {
            this.value = ((i & 3) << 6) | (this.value & 63);
        }

        public void setSampleDependsOn(int i) {
            this.value = ((i & 3) << 4) | (this.value & 207);
        }

        public void setSampleHasRedundancy(int i) {
            this.value = (i & 3) | (this.value & 252);
        }

        public void setSampleIsDependentOn(int i) {
            this.value = ((i & 3) << 2) | (this.value & 243);
        }

        public String toString() {
            return "Entry{isLeading=" + getIsLeading() + ", sampleDependsOn=" + getSampleDependsOn() + ", sampleIsDependentOn=" + getSampleIsDependentOn() + ", sampleHasRedundancy=" + getSampleHasRedundancy() + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public SampleDependencyTypeBox() {
        super(TYPE);
        this.entries = new ArrayList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("SampleDependencyTypeBox.java", SampleDependencyTypeBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.coremedia.iso.boxes.SampleDependencyTypeBox", "", "", "", "java.util.List"), 139);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.coremedia.iso.boxes.SampleDependencyTypeBox", "java.util.List", "entries", "", "void"), 143);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.coremedia.iso.boxes.SampleDependencyTypeBox", "", "", "", "java.lang.String"), 148);
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
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List<Entry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, list));
        this.entries = list;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "SampleDependencyTypeBox{entries=" + this.entries + '}';
    }
}
