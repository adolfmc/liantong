package szcom.googlecode.mp4parser.boxes.piff;

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
public class TfrfBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    public List<Entry> entries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class Entry {
        long fragmentAbsoluteDuration;
        long fragmentAbsoluteTime;

        public Entry() {
        }

        public long getFragmentAbsoluteDuration() {
            return this.fragmentAbsoluteDuration;
        }

        public long getFragmentAbsoluteTime() {
            return this.fragmentAbsoluteTime;
        }

        public String toString() {
            return "Entry{fragmentAbsoluteTime=" + this.fragmentAbsoluteTime + ", fragmentAbsoluteDuration=" + this.fragmentAbsoluteDuration + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public TfrfBox() {
        super("uuid");
        this.entries = new ArrayList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("TfrfBox.java", TfrfBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getFragmentCount", "szcom.googlecode.mp4parser.boxes.piff.TfrfBox", "", "", "", "long"), 91);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.googlecode.mp4parser.boxes.piff.TfrfBox", "", "", "", "java.util.List"), 95);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.piff.TfrfBox", "", "", "", "java.lang.String"), 100);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        long readUInt32;
        parseVersionAndFlags(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        for (int i = 0; i < readUInt8; i++) {
            Entry entry = new Entry();
            if (getVersion() == 1) {
                entry.fragmentAbsoluteTime = IsoTypeReader.readUInt64(byteBuffer);
                readUInt32 = IsoTypeReader.readUInt64(byteBuffer);
            } else {
                entry.fragmentAbsoluteTime = IsoTypeReader.readUInt32(byteBuffer);
                readUInt32 = IsoTypeReader.readUInt32(byteBuffer);
            }
            entry.fragmentAbsoluteDuration = readUInt32;
            this.entries.add(entry);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt8(byteBuffer, this.entries.size());
        for (Entry entry : this.entries) {
            if (getVersion() == 1) {
                IsoTypeWriter.writeUInt64(byteBuffer, entry.fragmentAbsoluteTime);
                IsoTypeWriter.writeUInt64(byteBuffer, entry.fragmentAbsoluteDuration);
            } else {
                IsoTypeWriter.writeUInt32(byteBuffer, entry.fragmentAbsoluteTime);
                IsoTypeWriter.writeUInt32(byteBuffer, entry.fragmentAbsoluteDuration);
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        return (this.entries.size() * (getVersion() == 1 ? 16 : 8)) + 5;
    }

    public List<Entry> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.entries;
    }

    public long getFragmentCount() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.entries.size();
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public byte[] getUserType() {
        return new byte[]{-44, Byte.MIN_VALUE, 126, -14, -54, 57, 70, -107, -114, 84, 38, -53, -98, 70, -89, -97};
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return "TfrfBox{entries=" + this.entries + '}';
    }
}
