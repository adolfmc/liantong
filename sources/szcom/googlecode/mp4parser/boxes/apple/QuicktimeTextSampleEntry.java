package szcom.googlecode.mp4parser.boxes.apple;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.List;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.util.CastUtils;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class QuicktimeTextSampleEntry extends AbstractSampleEntry {
    public static final String TYPE = "text";
    int backgroundB;
    int backgroundG;
    int backgroundR;
    int dataReferenceIndex;
    long defaultTextBox;
    int displayFlags;
    short fontFace;
    String fontName;
    short fontNumber;
    int foregroundB;
    int foregroundG;
    int foregroundR;
    long reserved1;
    byte reserved2;
    short reserved3;
    int textJustification;

    public QuicktimeTextSampleEntry() {
        super("text");
        this.foregroundR = 65535;
        this.foregroundG = 65535;
        this.foregroundB = 65535;
        this.fontName = "";
    }

    @Override // szcom.googlecode.mp4parser.BasicContainer
    public void addBox(Box box) {
        throw new RuntimeException("QuicktimeTextSampleEntries may not have child boxes");
    }

    public int getBackgroundB() {
        return this.backgroundB;
    }

    public int getBackgroundG() {
        return this.backgroundG;
    }

    public int getBackgroundR() {
        return this.backgroundR;
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        String str = this.fontName;
        ByteBuffer allocate = ByteBuffer.allocate((str != null ? str.length() : 0) + 52);
        allocate.position(6);
        IsoTypeWriter.writeUInt16(allocate, this.dataReferenceIndex);
        allocate.putInt(this.displayFlags);
        allocate.putInt(this.textJustification);
        IsoTypeWriter.writeUInt16(allocate, this.backgroundR);
        IsoTypeWriter.writeUInt16(allocate, this.backgroundG);
        IsoTypeWriter.writeUInt16(allocate, this.backgroundB);
        IsoTypeWriter.writeUInt64(allocate, this.defaultTextBox);
        IsoTypeWriter.writeUInt64(allocate, this.reserved1);
        allocate.putShort(this.fontNumber);
        allocate.putShort(this.fontFace);
        allocate.put(this.reserved2);
        allocate.putShort(this.reserved3);
        IsoTypeWriter.writeUInt16(allocate, this.foregroundR);
        IsoTypeWriter.writeUInt16(allocate, this.foregroundG);
        IsoTypeWriter.writeUInt16(allocate, this.foregroundB);
        String str2 = this.fontName;
        if (str2 != null) {
            IsoTypeWriter.writeUInt8(allocate, str2.length());
            allocate.put(this.fontName.getBytes());
        }
        writableByteChannel.write((ByteBuffer) allocate.rewind());
    }

    public long getDefaultTextBox() {
        return this.defaultTextBox;
    }

    public int getDisplayFlags() {
        return this.displayFlags;
    }

    public short getFontFace() {
        return this.fontFace;
    }

    public String getFontName() {
        return this.fontName;
    }

    public short getFontNumber() {
        return this.fontNumber;
    }

    public int getForegroundB() {
        return this.foregroundB;
    }

    public int getForegroundG() {
        return this.foregroundG;
    }

    public int getForegroundR() {
        return this.foregroundR;
    }

    public long getReserved1() {
        return this.reserved1;
    }

    public byte getReserved2() {
        return this.reserved2;
    }

    public short getReserved3() {
        return this.reserved3;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public long getSize() {
        long containerSize = getContainerSize() + 52;
        String str = this.fontName;
        long length = containerSize + (str != null ? str.length() : 0);
        return length + ((this.largeBox || 8 + length >= 4294967296L) ? 16 : 8);
    }

    public int getTextJustification() {
        return this.textJustification;
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        ByteBuffer allocate = ByteBuffer.allocate(CastUtils.l2i(j));
        dataSource.read(allocate);
        allocate.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(allocate);
        this.displayFlags = allocate.getInt();
        this.textJustification = allocate.getInt();
        this.backgroundR = IsoTypeReader.readUInt16(allocate);
        this.backgroundG = IsoTypeReader.readUInt16(allocate);
        this.backgroundB = IsoTypeReader.readUInt16(allocate);
        this.defaultTextBox = IsoTypeReader.readUInt64(allocate);
        this.reserved1 = IsoTypeReader.readUInt64(allocate);
        this.fontNumber = allocate.getShort();
        this.fontFace = allocate.getShort();
        this.reserved2 = allocate.get();
        this.reserved3 = allocate.getShort();
        this.foregroundR = IsoTypeReader.readUInt16(allocate);
        this.foregroundG = IsoTypeReader.readUInt16(allocate);
        this.foregroundB = IsoTypeReader.readUInt16(allocate);
        if (allocate.remaining() <= 0) {
            this.fontName = null;
            return;
        }
        byte[] bArr = new byte[IsoTypeReader.readUInt8(allocate)];
        allocate.get(bArr);
        this.fontName = new String(bArr);
    }

    public void setBackgroundB(int i) {
        this.backgroundB = i;
    }

    public void setBackgroundG(int i) {
        this.backgroundG = i;
    }

    public void setBackgroundR(int i) {
        this.backgroundR = i;
    }

    @Override // szcom.googlecode.mp4parser.BasicContainer, szcom.coremedia.iso.boxes.Container
    public void setBoxes(List<Box> list) {
        throw new RuntimeException("QuicktimeTextSampleEntries may not have child boxes");
    }

    public void setDefaultTextBox(long j) {
        this.defaultTextBox = j;
    }

    public void setDisplayFlags(int i) {
        this.displayFlags = i;
    }

    public void setFontFace(short s) {
        this.fontFace = s;
    }

    public void setFontName(String str) {
        this.fontName = str;
    }

    public void setFontNumber(short s) {
        this.fontNumber = s;
    }

    public void setForegroundB(int i) {
        this.foregroundB = i;
    }

    public void setForegroundG(int i) {
        this.foregroundG = i;
    }

    public void setForegroundR(int i) {
        this.foregroundR = i;
    }

    public void setReserved1(long j) {
        this.reserved1 = j;
    }

    public void setReserved2(byte b) {
        this.reserved2 = b;
    }

    public void setReserved3(short s) {
        this.reserved3 = s;
    }

    public void setTextJustification(int i) {
        this.textJustification = i;
    }
}
