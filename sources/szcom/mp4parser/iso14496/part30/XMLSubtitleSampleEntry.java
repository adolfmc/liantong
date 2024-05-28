package szcom.mp4parser.iso14496.part30;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry;
import szcom.googlecode.mp4parser.DataSource;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class XMLSubtitleSampleEntry extends AbstractSampleEntry {
    public static final String TYPE = "stpp";
    private String auxiliaryMimeTypes;
    private String namespace;
    private String schemaLocation;

    public XMLSubtitleSampleEntry() {
        super(TYPE);
        this.namespace = "";
        this.schemaLocation = "";
        this.auxiliaryMimeTypes = "";
    }

    public String getAuxiliaryMimeTypes() {
        return this.auxiliaryMimeTypes;
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        writableByteChannel.write(getHeader());
        ByteBuffer allocate = ByteBuffer.allocate(this.namespace.length() + 8 + this.schemaLocation.length() + this.auxiliaryMimeTypes.length() + 3);
        allocate.position(6);
        IsoTypeWriter.writeUInt16(allocate, this.dataReferenceIndex);
        IsoTypeWriter.writeZeroTermUtf8String(allocate, this.namespace);
        IsoTypeWriter.writeZeroTermUtf8String(allocate, this.schemaLocation);
        IsoTypeWriter.writeZeroTermUtf8String(allocate, this.auxiliaryMimeTypes);
        writableByteChannel.write((ByteBuffer) allocate.rewind());
        writeContainer(writableByteChannel);
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getSchemaLocation() {
        return this.schemaLocation;
    }

    @Override // szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public long getSize() {
        int i = 8;
        long containerSize = getContainerSize() + this.namespace.length() + 8 + this.schemaLocation.length() + this.auxiliaryMimeTypes.length() + 3;
        return containerSize + ((this.largeBox || 8 + containerSize >= 4294967296L) ? 16 : 16);
    }

    @Override // szcom.coremedia.iso.boxes.sampleentry.AbstractSampleEntry, szcom.googlecode.mp4parser.AbstractContainerBox, szcom.coremedia.iso.boxes.Box
    public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        ByteBuffer allocate = ByteBuffer.allocate(8);
        dataSource.read((ByteBuffer) allocate.rewind());
        allocate.position(6);
        this.dataReferenceIndex = IsoTypeReader.readUInt16(allocate);
        long position = dataSource.position();
        ByteBuffer allocate2 = ByteBuffer.allocate(1024);
        dataSource.read((ByteBuffer) allocate2.rewind());
        this.namespace = IsoTypeReader.readString((ByteBuffer) allocate2.rewind());
        dataSource.position(this.namespace.length() + position + 1);
        dataSource.read((ByteBuffer) allocate2.rewind());
        this.schemaLocation = IsoTypeReader.readString((ByteBuffer) allocate2.rewind());
        dataSource.position(this.namespace.length() + position + this.schemaLocation.length() + 2);
        dataSource.read((ByteBuffer) allocate2.rewind());
        this.auxiliaryMimeTypes = IsoTypeReader.readString((ByteBuffer) allocate2.rewind());
        dataSource.position(position + this.namespace.length() + this.schemaLocation.length() + this.auxiliaryMimeTypes.length() + 3);
        initContainer(dataSource, j - ((((byteBuffer.remaining() + this.namespace.length()) + this.schemaLocation.length()) + this.auxiliaryMimeTypes.length()) + 3), boxParser);
    }

    public void setAuxiliaryMimeTypes(String str) {
        this.auxiliaryMimeTypes = str;
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }

    public void setSchemaLocation(String str) {
        this.schemaLocation = str;
    }
}
