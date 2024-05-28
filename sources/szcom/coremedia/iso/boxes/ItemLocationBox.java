package szcom.coremedia.iso.boxes;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import p001a.p002a.p003a.p004a.p005a.p014e.p015a.p030c.p041m0.C0567f;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeReaderVariable;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.IsoTypeWriterVariable;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class ItemLocationBox extends AbstractFullBox {
    public static final String TYPE = "iloc";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_10 = null;
    private static final JoinPoint.StaticPart ajc$tjp_11 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private static final JoinPoint.StaticPart ajc$tjp_9 = null;
    public int baseOffsetSize;
    public int indexSize;
    public List<Item> items;
    public int lengthSize;
    public int offsetSize;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class Extent {
        public long extentIndex;
        public long extentLength;
        public long extentOffset;

        public Extent(long j, long j2, long j3) {
            this.extentOffset = j;
            this.extentLength = j2;
            this.extentIndex = j3;
        }

        public Extent(ByteBuffer byteBuffer) {
            if (ItemLocationBox.this.getVersion() == 1 && ItemLocationBox.this.indexSize > 0) {
                this.extentIndex = IsoTypeReaderVariable.read(byteBuffer, ItemLocationBox.this.indexSize);
            }
            this.extentOffset = IsoTypeReaderVariable.read(byteBuffer, ItemLocationBox.this.offsetSize);
            this.extentLength = IsoTypeReaderVariable.read(byteBuffer, ItemLocationBox.this.lengthSize);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Extent extent = (Extent) obj;
            return this.extentIndex == extent.extentIndex && this.extentLength == extent.extentLength && this.extentOffset == extent.extentOffset;
        }

        public void getContent(ByteBuffer byteBuffer) {
            if (ItemLocationBox.this.getVersion() == 1 && ItemLocationBox.this.indexSize > 0) {
                IsoTypeWriterVariable.write(this.extentIndex, byteBuffer, ItemLocationBox.this.indexSize);
            }
            IsoTypeWriterVariable.write(this.extentOffset, byteBuffer, ItemLocationBox.this.offsetSize);
            IsoTypeWriterVariable.write(this.extentLength, byteBuffer, ItemLocationBox.this.lengthSize);
        }

        public int getSize() {
            return (ItemLocationBox.this.indexSize > 0 ? ItemLocationBox.this.indexSize : 0) + ItemLocationBox.this.offsetSize + ItemLocationBox.this.lengthSize;
        }

        public int hashCode() {
            long j = this.extentOffset;
            long j2 = this.extentLength;
            long j3 = this.extentIndex;
            return (((((int) (j ^ (j >>> 32))) * 31) + ((int) (j2 ^ (j2 >>> 32)))) * 31) + ((int) ((j3 >>> 32) ^ j3));
        }

        public String toString() {
            return "Extent{extentOffset=" + this.extentOffset + ", extentLength=" + this.extentLength + ", extentIndex=" + this.extentIndex + '}';
        }
    }

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    public class Item {
        public long baseOffset;
        public int constructionMethod;
        public int dataReferenceIndex;
        public List<Extent> extents;
        public int itemId;

        public Item(int i, int i2, int i3, long j, List<Extent> list) {
            this.extents = new LinkedList();
            this.itemId = i;
            this.constructionMethod = i2;
            this.dataReferenceIndex = i3;
            this.baseOffset = j;
            this.extents = list;
        }

        public Item(ByteBuffer byteBuffer) {
            this.extents = new LinkedList();
            this.itemId = IsoTypeReader.readUInt16(byteBuffer);
            if (ItemLocationBox.this.getVersion() == 1) {
                this.constructionMethod = IsoTypeReader.readUInt16(byteBuffer) & 15;
            }
            this.dataReferenceIndex = IsoTypeReader.readUInt16(byteBuffer);
            this.baseOffset = ItemLocationBox.this.baseOffsetSize > 0 ? IsoTypeReaderVariable.read(byteBuffer, ItemLocationBox.this.baseOffsetSize) : 0L;
            int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
            for (int i = 0; i < readUInt16; i++) {
                this.extents.add(new Extent(byteBuffer));
            }
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }
            Item item = (Item) obj;
            if (this.baseOffset == item.baseOffset && this.constructionMethod == item.constructionMethod && this.dataReferenceIndex == item.dataReferenceIndex && this.itemId == item.itemId) {
                List<Extent> list = this.extents;
                return list == null ? item.extents == null : list.equals(item.extents);
            }
            return false;
        }

        public void getContent(ByteBuffer byteBuffer) {
            IsoTypeWriter.writeUInt16(byteBuffer, this.itemId);
            if (ItemLocationBox.this.getVersion() == 1) {
                IsoTypeWriter.writeUInt16(byteBuffer, this.constructionMethod);
            }
            IsoTypeWriter.writeUInt16(byteBuffer, this.dataReferenceIndex);
            if (ItemLocationBox.this.baseOffsetSize > 0) {
                IsoTypeWriterVariable.write(this.baseOffset, byteBuffer, ItemLocationBox.this.baseOffsetSize);
            }
            IsoTypeWriter.writeUInt16(byteBuffer, this.extents.size());
            for (Extent extent : this.extents) {
                extent.getContent(byteBuffer);
            }
        }

        public int getSize() {
            int i = (ItemLocationBox.this.getVersion() == 1 ? 4 : 2) + 2 + ItemLocationBox.this.baseOffsetSize + 2;
            for (Extent extent : this.extents) {
                i += extent.getSize();
            }
            return i;
        }

        public int hashCode() {
            long j = this.baseOffset;
            int i = ((((((this.itemId * 31) + this.constructionMethod) * 31) + this.dataReferenceIndex) * 31) + ((int) (j ^ (j >>> 32)))) * 31;
            List<Extent> list = this.extents;
            return i + (list != null ? list.hashCode() : 0);
        }

        public void setBaseOffset(long j) {
            this.baseOffset = j;
        }

        public String toString() {
            return "Item{baseOffset=" + this.baseOffset + ", itemId=" + this.itemId + ", constructionMethod=" + this.constructionMethod + ", dataReferenceIndex=" + this.dataReferenceIndex + ", extents=" + this.extents + '}';
        }
    }

    static {
        ajc$preClinit();
    }

    public ItemLocationBox() {
        super(TYPE);
        this.offsetSize = 8;
        this.lengthSize = 8;
        this.baseOffsetSize = 8;
        this.indexSize = 0;
        this.items = new LinkedList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("ItemLocationBox.java", ItemLocationBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getOffsetSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 119);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setOffsetSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "int", "offsetSize", "", "void"), 123);
        ajc$tjp_10 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "createItem", "szcom.coremedia.iso.boxes.ItemLocationBox", "int:int:int:long:java.util.List", "itemId:constructionMethod:dataReferenceIndex:baseOffset:extents", "", "szcom.coremedia.iso.boxes.ItemLocationBox$Item"), C0567f.f1819h);
        ajc$tjp_11 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "createExtent", "szcom.coremedia.iso.boxes.ItemLocationBox", "long:long:long", "extentOffset:extentLength:extentIndex", "", "szcom.coremedia.iso.boxes.ItemLocationBox$Extent"), 285);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getLengthSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 127);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setLengthSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "int", "lengthSize", "", "void"), 131);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getBaseOffsetSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 135);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setBaseOffsetSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "int", "baseOffsetSize", "", "void"), 139);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getIndexSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "", "", "", "int"), 143);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setIndexSize", "szcom.coremedia.iso.boxes.ItemLocationBox", "int", "indexSize", "", "void"), 147);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getItems", "szcom.coremedia.iso.boxes.ItemLocationBox", "", "", "", "java.util.List"), 151);
        ajc$tjp_9 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setItems", "szcom.coremedia.iso.boxes.ItemLocationBox", "java.util.List", "items", "", "void"), 155);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        int readUInt8 = IsoTypeReader.readUInt8(byteBuffer);
        this.offsetSize = readUInt8 >>> 4;
        this.lengthSize = readUInt8 & 15;
        int readUInt82 = IsoTypeReader.readUInt8(byteBuffer);
        this.baseOffsetSize = readUInt82 >>> 4;
        if (getVersion() == 1) {
            this.indexSize = readUInt82 & 15;
        }
        int readUInt16 = IsoTypeReader.readUInt16(byteBuffer);
        for (int i = 0; i < readUInt16; i++) {
            this.items.add(new Item(byteBuffer));
        }
    }

    public Extent createExtent(long j, long j2, long j3) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_11, (Object) this, (Object) this, new Object[]{Conversions.longObject(j), Conversions.longObject(j2), Conversions.longObject(j3)}));
        return new Extent(j, j2, j3);
    }

    Extent createExtent(ByteBuffer byteBuffer) {
        return new Extent(byteBuffer);
    }

    public Item createItem(int i, int i2, int i3, long j, List<Extent> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_10, (Object) this, (Object) this, new Object[]{Conversions.intObject(i), Conversions.intObject(i2), Conversions.intObject(i3), Conversions.longObject(j), list}));
        return new Item(i, i2, i3, j, list);
    }

    Item createItem(ByteBuffer byteBuffer) {
        return new Item(byteBuffer);
    }

    public int getBaseOffsetSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.baseOffsetSize;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        IsoTypeWriter.writeUInt8(byteBuffer, (this.offsetSize << 4) | this.lengthSize);
        IsoTypeWriter.writeUInt8(byteBuffer, getVersion() == 1 ? (this.baseOffsetSize << 4) | this.indexSize : this.baseOffsetSize << 4);
        IsoTypeWriter.writeUInt16(byteBuffer, this.items.size());
        for (Item item : this.items) {
            item.getContent(byteBuffer);
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        long j = 8;
        for (Item item : this.items) {
            j += item.getSize();
        }
        return j;
    }

    public int getIndexSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this));
        return this.indexSize;
    }

    public List<Item> getItems() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        return this.items;
    }

    public int getLengthSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.lengthSize;
    }

    public int getOffsetSize() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.offsetSize;
    }

    public void setBaseOffsetSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, Conversions.intObject(i)));
        this.baseOffsetSize = i;
    }

    public void setIndexSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this, Conversions.intObject(i)));
        this.indexSize = i;
    }

    public void setItems(List<Item> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_9, this, this, list));
        this.items = list;
    }

    public void setLengthSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.lengthSize = i;
    }

    public void setOffsetSize(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, Conversions.intObject(i)));
        this.offsetSize = i;
    }
}
