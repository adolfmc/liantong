package szcom.googlecode.mp4parser.boxes;

import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.annotations.DoNotParseDetail;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public abstract class AbstractSampleEncryptionBox extends AbstractFullBox {
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    protected int algorithmId;
    List<CencSampleAuxiliaryDataFormat> entries;
    protected int ivSize;
    protected byte[] kid;

    static {
        ajc$preClinit();
    }

    /* JADX INFO: Access modifiers changed from: protected */
    public AbstractSampleEncryptionBox(String str) {
        super(str);
        this.algorithmId = -1;
        this.ivSize = -1;
        this.kid = new byte[]{-1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1};
        this.entries = Collections.emptyList();
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("AbstractSampleEncryptionBox.java", AbstractSampleEncryptionBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getOffsetToFirstIV", "szcom.googlecode.mp4parser.boxes.AbstractSampleEncryptionBox", "", "", "", "int"), 29);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntries", "szcom.googlecode.mp4parser.boxes.AbstractSampleEncryptionBox", "", "", "", "java.util.List"), 89);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setEntries", "szcom.googlecode.mp4parser.boxes.AbstractSampleEncryptionBox", "java.util.List", "entries", "", "void"), 93);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "equals", "szcom.googlecode.mp4parser.boxes.AbstractSampleEncryptionBox", "java.lang.Object", "o", "", "boolean"), 173);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hashCode", "szcom.googlecode.mp4parser.boxes.AbstractSampleEncryptionBox", "", "", "", "int"), 200);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getEntrySizes", "szcom.googlecode.mp4parser.boxes.AbstractSampleEncryptionBox", "", "", "", "java.util.List"), 208);
    }

    private int getNonEmptyEntriesNum() {
        int i = 0;
        for (CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat : this.entries) {
            if (cencSampleAuxiliaryDataFormat.getSize() > 0) {
                i++;
            }
        }
        return i;
    }

    private List<CencSampleAuxiliaryDataFormat> parseEntries(ByteBuffer byteBuffer, long j, int i) {
        ArrayList arrayList = new ArrayList();
        while (true) {
            long j2 = j - 1;
            if (j <= 0) {
                return arrayList;
            }
            try {
                CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
                cencSampleAuxiliaryDataFormat.f27682iv = new byte[i];
                byteBuffer.get(cencSampleAuxiliaryDataFormat.f27682iv);
                if ((getFlags() & 2) > 0) {
                    cencSampleAuxiliaryDataFormat.pairs = new CencSampleAuxiliaryDataFormat.Pair[IsoTypeReader.readUInt16(byteBuffer)];
                    for (int i2 = 0; i2 < cencSampleAuxiliaryDataFormat.pairs.length; i2++) {
                        cencSampleAuxiliaryDataFormat.pairs[i2] = cencSampleAuxiliaryDataFormat.createPair(IsoTypeReader.readUInt16(byteBuffer), IsoTypeReader.readUInt32(byteBuffer));
                    }
                }
                arrayList.add(cencSampleAuxiliaryDataFormat);
                j = j2;
            } catch (BufferUnderflowException unused) {
                return null;
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        if ((getFlags() & 1) > 0) {
            this.algorithmId = IsoTypeReader.readUInt24(byteBuffer);
            this.ivSize = IsoTypeReader.readUInt8(byteBuffer);
            this.kid = new byte[16];
            byteBuffer.get(this.kid);
        }
        long readUInt32 = IsoTypeReader.readUInt32(byteBuffer);
        ByteBuffer duplicate = byteBuffer.duplicate();
        ByteBuffer duplicate2 = byteBuffer.duplicate();
        this.entries = parseEntries(duplicate, readUInt32, 8);
        if (this.entries == null) {
            this.entries = parseEntries(duplicate2, readUInt32, 16);
            byteBuffer.position((byteBuffer.position() + byteBuffer.remaining()) - duplicate2.remaining());
        } else {
            byteBuffer.position((byteBuffer.position() + byteBuffer.remaining()) - duplicate.remaining());
        }
        if (this.entries == null) {
            throw new RuntimeException("Cannot parse SampleEncryptionBox");
        }
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        AbstractSampleEncryptionBox abstractSampleEncryptionBox = (AbstractSampleEncryptionBox) obj;
        if (this.algorithmId == abstractSampleEncryptionBox.algorithmId && this.ivSize == abstractSampleEncryptionBox.ivSize) {
            List<CencSampleAuxiliaryDataFormat> list = this.entries;
            if (list == null ? abstractSampleEncryptionBox.entries == null : list.equals(abstractSampleEncryptionBox.entries)) {
                return Arrays.equals(this.kid, abstractSampleEncryptionBox.kid);
            }
            return false;
        }
        return false;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox, szcom.coremedia.iso.boxes.Box
    public void getBox(WritableByteChannel writableByteChannel) {
        super.getBox(writableByteChannel);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        CencSampleAuxiliaryDataFormat.Pair[] pairArr;
        writeVersionAndFlags(byteBuffer);
        if (isOverrideTrackEncryptionBoxParameters()) {
            IsoTypeWriter.writeUInt24(byteBuffer, this.algorithmId);
            IsoTypeWriter.writeUInt8(byteBuffer, this.ivSize);
            byteBuffer.put(this.kid);
        }
        IsoTypeWriter.writeUInt32(byteBuffer, getNonEmptyEntriesNum());
        for (CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat : this.entries) {
            if (cencSampleAuxiliaryDataFormat.getSize() > 0) {
                if (cencSampleAuxiliaryDataFormat.f27682iv.length != 8 && cencSampleAuxiliaryDataFormat.f27682iv.length != 16) {
                    throw new RuntimeException("IV must be either 8 or 16 bytes");
                }
                byteBuffer.put(cencSampleAuxiliaryDataFormat.f27682iv);
                if (isSubSampleEncryption()) {
                    IsoTypeWriter.writeUInt16(byteBuffer, cencSampleAuxiliaryDataFormat.pairs.length);
                    for (CencSampleAuxiliaryDataFormat.Pair pair : cencSampleAuxiliaryDataFormat.pairs) {
                        IsoTypeWriter.writeUInt16(byteBuffer, pair.clear());
                        IsoTypeWriter.writeUInt32(byteBuffer, pair.encrypted());
                    }
                }
            }
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        long length = (isOverrideTrackEncryptionBoxParameters() ? 8 + this.kid.length : 4L) + 4;
        for (CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat : this.entries) {
            length += cencSampleAuxiliaryDataFormat.getSize();
        }
        return length;
    }

    public List<CencSampleAuxiliaryDataFormat> getEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this));
        return this.entries;
    }

    public List<Short> getEntrySizes() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this));
        ArrayList arrayList = new ArrayList(this.entries.size());
        for (CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat : this.entries) {
            short length = (short) cencSampleAuxiliaryDataFormat.f27682iv.length;
            if (isSubSampleEncryption()) {
                length = (short) (((short) (length + 2)) + (cencSampleAuxiliaryDataFormat.pairs.length * 6));
            }
            arrayList.add(Short.valueOf(length));
        }
        return arrayList;
    }

    public int getOffsetToFirstIV() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return (getSize() > 4294967296L ? 16 : 8) + (isOverrideTrackEncryptionBoxParameters() ? this.kid.length + 4 : 0) + 4;
    }

    public int hashCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        int i = ((this.algorithmId * 31) + this.ivSize) * 31;
        byte[] bArr = this.kid;
        int hashCode = (i + (bArr != null ? Arrays.hashCode(bArr) : 0)) * 31;
        List<CencSampleAuxiliaryDataFormat> list = this.entries;
        return hashCode + (list != null ? list.hashCode() : 0);
    }

    @DoNotParseDetail
    protected boolean isOverrideTrackEncryptionBoxParameters() {
        return (getFlags() & 1) > 0;
    }

    @DoNotParseDetail
    public boolean isSubSampleEncryption() {
        return (getFlags() & 2) > 0;
    }

    public void setEntries(List<CencSampleAuxiliaryDataFormat> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this, list));
        this.entries = list;
    }

    @DoNotParseDetail
    public void setSubSampleEncryption(boolean z) {
        setFlags(z ? getFlags() | 2 : getFlags() & 16777213);
    }
}
