package szcom.googlecode.mp4parser.boxes.threegpp26245;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.Utf8;
import szcom.googlecode.mp4parser.AbstractBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class FontTableBox extends AbstractBox {
    public static final String TYPE = "ftab";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    List<FontRecord> entries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public static class FontRecord {
        int fontId;
        String fontname;

        public FontRecord() {
        }

        public FontRecord(int i, String str) {
            this.fontId = i;
            this.fontname = str;
        }

        public void getContent(ByteBuffer byteBuffer) {
            IsoTypeWriter.writeUInt16(byteBuffer, this.fontId);
            IsoTypeWriter.writeUInt8(byteBuffer, this.fontname.length());
            byteBuffer.put(Utf8.convert(this.fontname));
        }

        public int getSize() {
            return Utf8.utf8StringLengthInBytes(this.fontname) + 3;
        }

        public void parse(ByteBuffer byteBuffer) {
            this.fontId = IsoTypeReader.readUInt16(byteBuffer);
            this.fontname = IsoTypeReader.readString(byteBuffer, IsoTypeReader.readUInt8(byteBuffer));
        }

        public String toString() {
            return "FontRecord{fontId=" + this.fontId + ", fontname='" + this.fontname + "'}";
        }
    }

    static {
        ajc$preClinit();
    }

    public FontTableBox() {
        super(TYPE);
        this.entries = new LinkedList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("FontTableBox.java", FontTableBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.googlecode.mp4parser.boxes.threegpp26245.FontTableBox", "", "", "", "java.util.List"), 52);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.googlecode.mp4parser.boxes.threegpp26245.FontTableBox", "java.util.List", "entries", "", "void"), 56);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        for (int i = 0; i < readUInt16; i++) {
            FontRecord fontRecord = new FontRecord();
            fontRecord.parse(byteBuffer);
            this.entries.add(fontRecord);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        IsoTypeWriter.writeUInt16(byteBuffer, this.entries.size());
        for (FontRecord fontRecord : this.entries) {
            fontRecord.getContent(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        int i = 2;
        for (FontRecord fontRecord : this.entries) {
            i += fontRecord.getSize();
        }
        return i;
    }

    public List<FontRecord> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.entries;
    }

    public void setEntries(List<FontRecord> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, list));
        this.entries = list;
    }
}
