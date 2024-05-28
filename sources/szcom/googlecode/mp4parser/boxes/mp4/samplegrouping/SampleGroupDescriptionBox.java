package szcom.googlecode.mp4parser.boxes.mp4.samplegrouping;

import java.nio.ByteBuffer;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.googlecode.mp4parser.AbstractFullBox;
import szcom.googlecode.mp4parser.RequiresParseDetailAspect;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.mp4parser.iso14496.part15.StepwiseTemporalLayerEntry;
import szcom.mp4parser.iso14496.part15.SyncSampleEntry;
import szcom.mp4parser.iso14496.part15.TemporalLayerSampleGroup;
import szcom.mp4parser.iso14496.part15.TemporalSubLayerSampleGroup;
import szorg.mp4parser.aspectj.lang.JoinPoint;
import szorg.mp4parser.aspectj.runtime.internal.Conversions;
import szorg.mp4parser.aspectj.runtime.reflect.Factory;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SampleGroupDescriptionBox extends AbstractFullBox {
    public static final String TYPE = "sgpd";
    private static final JoinPoint.StaticPart ajc$tjp_0 = null;
    private static final JoinPoint.StaticPart ajc$tjp_1 = null;
    private static final JoinPoint.StaticPart ajc$tjp_2 = null;
    private static final JoinPoint.StaticPart ajc$tjp_3 = null;
    private static final JoinPoint.StaticPart ajc$tjp_4 = null;
    private static final JoinPoint.StaticPart ajc$tjp_5 = null;
    private static final JoinPoint.StaticPart ajc$tjp_6 = null;
    private static final JoinPoint.StaticPart ajc$tjp_7 = null;
    private static final JoinPoint.StaticPart ajc$tjp_8 = null;
    private int defaultLength;
    private List<GroupEntry> groupEntries;
    private String groupingType;

    static {
        ajc$preClinit();
    }

    public SampleGroupDescriptionBox() {
        super(TYPE);
        this.groupEntries = new LinkedList();
        setVersion(1);
    }

    private static void ajc$preClinit() {
        Factory factory = new Factory("SampleGroupDescriptionBox.java", SampleGroupDescriptionBox.class);
        ajc$tjp_0 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGroupingType", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.lang.String"), 57);
        ajc$tjp_1 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGroupingType", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "java.lang.String", "groupingType", "", "void"), 61);
        ajc$tjp_2 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getDefaultLength", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "int"), 153);
        ajc$tjp_3 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setDefaultLength", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "int", "defaultLength", "", "void"), 157);
        ajc$tjp_4 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "getGroupEntries", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.util.List"), 161);
        ajc$tjp_5 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "setGroupEntries", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "java.util.List", "groupEntries", "", "void"), 165);
        ajc$tjp_6 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "equals", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "java.lang.Object", "o", "", "boolean"), 170);
        ajc$tjp_7 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "hashCode", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "int"), 191);
        ajc$tjp_8 = factory.makeSJP("method-execution", factory.makeMethodSig("1", "toString", "szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox", "", "", "", "java.lang.String"), 199);
    }

    private GroupEntry parseGroupEntry(ByteBuffer byteBuffer, String str) {
        GroupEntry rollRecoveryEntry = RollRecoveryEntry.TYPE.equals(str) ? new RollRecoveryEntry() : RateShareEntry.TYPE.equals(str) ? new RateShareEntry() : CencSampleEncryptionInformationGroupEntry.TYPE.equals(str) ? new CencSampleEncryptionInformationGroupEntry() : VisualRandomAccessEntry.TYPE.equals(str) ? new VisualRandomAccessEntry() : TemporalLevelEntry.TYPE.equals(str) ? new TemporalLevelEntry() : "sync".equals(str) ? new SyncSampleEntry() : TemporalLayerSampleGroup.TYPE.equals(str) ? new TemporalLayerSampleGroup() : TemporalSubLayerSampleGroup.TYPE.equals(str) ? new TemporalSubLayerSampleGroup() : StepwiseTemporalLayerEntry.TYPE.equals(str) ? new StepwiseTemporalLayerEntry() : new UnknownEntry(str);
        rollRecoveryEntry.parse(byteBuffer);
        return rollRecoveryEntry;
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void _parseDetails(ByteBuffer byteBuffer) {
        parseVersionAndFlags(byteBuffer);
        if (getVersion() != 1) {
            throw new RuntimeException("SampleGroupDescriptionBox are only supported in version 1");
        }
        this.groupingType = IsoTypeReader.read4cc(byteBuffer);
        if (getVersion() == 1) {
            this.defaultLength = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
        }
        long readUInt32 = IsoTypeReader.readUInt32(byteBuffer);
        while (true) {
            long j = readUInt32 - 1;
            if (readUInt32 <= 0) {
                return;
            }
            int i = this.defaultLength;
            if (getVersion() != 1) {
                throw new RuntimeException("This should be implemented");
            }
            if (this.defaultLength == 0) {
                i = CastUtils.l2i(IsoTypeReader.readUInt32(byteBuffer));
            }
            int position = byteBuffer.position() + i;
            ByteBuffer slice = byteBuffer.slice();
            slice.limit(i);
            this.groupEntries.add(parseGroupEntry(slice, this.groupingType));
            byteBuffer.position(position);
            readUInt32 = j;
        }
    }

    public boolean equals(Object obj) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_6, this, this, obj));
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        SampleGroupDescriptionBox sampleGroupDescriptionBox = (SampleGroupDescriptionBox) obj;
        if (this.defaultLength != sampleGroupDescriptionBox.defaultLength) {
            return false;
        }
        List<GroupEntry> list = this.groupEntries;
        return list == null ? sampleGroupDescriptionBox.groupEntries == null : list.equals(sampleGroupDescriptionBox.groupEntries);
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public void getContent(ByteBuffer byteBuffer) {
        writeVersionAndFlags(byteBuffer);
        byteBuffer.put(IsoFile.fourCCtoBytes(this.groupingType));
        if (getVersion() == 1) {
            IsoTypeWriter.writeUInt32(byteBuffer, this.defaultLength);
        }
        IsoTypeWriter.writeUInt32(byteBuffer, this.groupEntries.size());
        for (GroupEntry groupEntry : this.groupEntries) {
            if (getVersion() == 1 && this.defaultLength == 0) {
                IsoTypeWriter.writeUInt32(byteBuffer, groupEntry.get().limit());
            }
            byteBuffer.put(groupEntry.get());
        }
    }

    @Override // szcom.googlecode.mp4parser.AbstractBox
    public long getContentSize() {
        long j = (getVersion() == 1 ? 12L : 8L) + 4;
        for (GroupEntry groupEntry : this.groupEntries) {
            if (getVersion() == 1 && this.defaultLength == 0) {
                j += 4;
            }
            j += groupEntry.size();
        }
        return j;
    }

    public int getDefaultLength() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_2, this, this));
        return this.defaultLength;
    }

    public List<GroupEntry> getGroupEntries() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_4, this, this));
        return this.groupEntries;
    }

    public String getGroupingType() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_0, this, this));
        return this.groupingType;
    }

    public int hashCode() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_7, this, this));
        int i = (this.defaultLength + 0) * 31;
        List<GroupEntry> list = this.groupEntries;
        return i + (list != null ? list.hashCode() : 0);
    }

    public void setDefaultLength(int i) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_3, this, this, Conversions.intObject(i)));
        this.defaultLength = i;
    }

    public void setGroupEntries(List<GroupEntry> list) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_5, this, this, list));
        this.groupEntries = list;
    }

    public void setGroupingType(String str) {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_1, this, this, str));
        this.groupingType = str;
    }

    public String toString() {
        RequiresParseDetailAspect.aspectOf().before(Factory.makeJP(ajc$tjp_8, this, this));
        StringBuilder sb = new StringBuilder("SampleGroupDescriptionBox{groupingType='");
        sb.append(this.groupEntries.size() > 0 ? this.groupEntries.get(0).getType() : "????");
        sb.append('\'');
        sb.append(", defaultLength=");
        sb.append(this.defaultLength);
        sb.append(", groupEntries=");
        sb.append(this.groupEntries);
        sb.append('}');
        return sb.toString();
    }
}
