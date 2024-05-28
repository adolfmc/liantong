package szcom.googlecode.mp4parser.authoring.tracks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import javax.crypto.SecretKey;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReaderVariable;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.OriginalFormatBox;
import szcom.coremedia.iso.boxes.ProtectionSchemeInformationBox;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SchemeInformationBox;
import szcom.coremedia.iso.boxes.SchemeTypeBox;
import szcom.coremedia.iso.boxes.SubSampleInformationBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.MemoryDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.Edit;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.TrackMetaData;
import szcom.googlecode.mp4parser.authoring.tracks.h264.H264NalUnitHeader;
import szcom.googlecode.mp4parser.authoring.tracks.h264.H264TrackImpl;
import szcom.googlecode.mp4parser.authoring.tracks.h265.H265NalUnitHeader;
import szcom.googlecode.mp4parser.authoring.tracks.h265.H265TrackImpl;
import szcom.googlecode.mp4parser.boxes.cenc.CencEncryptingSampleList;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.CencSampleEncryptionInformationGroupEntry;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.RangeStartMap;
import szcom.mp4parser.iso14496.part15.AvcConfigurationBox;
import szcom.mp4parser.iso14496.part15.HevcConfigurationBox;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import szcom.mp4parser.iso23001.part7.TrackEncryptionBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CencEncryptingTrackImpl implements CencEncryptedTrack {
    List<CencSampleAuxiliaryDataFormat> cencSampleAuxiliaryData;
    Object configurationBox;
    UUID defaultKeyId;
    boolean dummyIvs;
    private final String encryptionAlgo;
    RangeStartMap<Integer, SecretKey> indexToKey;
    Map<UUID, SecretKey> keys;
    Map<GroupEntry, long[]> sampleGroups;
    List<Sample> samples;
    Track source;
    SampleDescriptionBox stsd;
    boolean subSampleEncryption;

    public CencEncryptingTrackImpl(Track track, UUID uuid, Map<UUID, SecretKey> map, Map<CencSampleEncryptionInformationGroupEntry, long[]> map2, String str, boolean z) {
        this(track, uuid, map, map2, str, z, false);
    }

    public CencEncryptingTrackImpl(Track track, UUID uuid, Map<UUID, SecretKey> map, Map<CencSampleEncryptionInformationGroupEntry, long[]> map2, String str, boolean z, boolean z2) {
        int i;
        this.keys = new HashMap();
        int i2 = 0;
        this.dummyIvs = false;
        this.subSampleEncryption = false;
        SecretKey secretKey = null;
        this.stsd = null;
        this.source = track;
        this.keys = map;
        this.defaultKeyId = uuid;
        this.dummyIvs = z;
        this.encryptionAlgo = str;
        this.sampleGroups = new HashMap();
        for (Map.Entry<GroupEntry, long[]> entry : track.getSampleGroups().entrySet()) {
            if (!(entry.getKey() instanceof CencSampleEncryptionInformationGroupEntry)) {
                this.sampleGroups.put(entry.getKey(), entry.getValue());
            }
            i2 = 0;
            secretKey = null;
        }
        if (map2 != null) {
            for (Map.Entry<CencSampleEncryptionInformationGroupEntry, long[]> entry2 : map2.entrySet()) {
                this.sampleGroups.put(entry2.getKey(), entry2.getValue());
            }
        }
        this.sampleGroups = new HashMap<GroupEntry, long[]>(this.sampleGroups) { // from class: szcom.googlecode.mp4parser.authoring.tracks.CencEncryptingTrackImpl.1
            @Override // java.util.HashMap, java.util.AbstractMap, java.util.Map
            public long[] put(GroupEntry groupEntry, long[] jArr) {
                if (groupEntry instanceof CencSampleEncryptionInformationGroupEntry) {
                    throw new RuntimeException("Please supply CencSampleEncryptionInformationGroupEntries in the constructor");
                }
                return (long[]) super.put((C141331) groupEntry, (GroupEntry) jArr);
            }
        };
        this.samples = track.getSamples();
        this.cencSampleAuxiliaryData = new ArrayList();
        BigInteger bigInteger = new BigInteger("1");
        int i3 = 8;
        byte[] bArr = new byte[8];
        if (!z) {
            new SecureRandom().nextBytes(bArr);
        }
        BigInteger bigInteger2 = new BigInteger(1, bArr);
        ArrayList arrayList = new ArrayList();
        if (map2 != null) {
            arrayList.addAll(map2.keySet());
        }
        this.indexToKey = new RangeStartMap<>();
        int i4 = -1;
        int i5 = i2;
        int i6 = -1;
        while (i5 < track.getSamples().size()) {
            int i7 = i2;
            int i8 = i7;
            while (i7 < arrayList.size()) {
                BigInteger bigInteger3 = bigInteger2;
                if (Arrays.binarySearch(getSampleGroups().get((GroupEntry) arrayList.get(i7)), i5) >= 0) {
                    i8 = i7 + 1;
                }
                i7++;
                bigInteger2 = bigInteger3;
                secretKey = null;
                i3 = 8;
            }
            if (i6 != i8) {
                if (i8 == 0) {
                    this.indexToKey.put((RangeStartMap<Integer, SecretKey>) Integer.valueOf(i5), (Integer) map.get(uuid));
                } else {
                    int i9 = i8 - 1;
                    if (((CencSampleEncryptionInformationGroupEntry) arrayList.get(i9)).getKid() != null) {
                        SecretKey secretKey2 = map.get(((CencSampleEncryptionInformationGroupEntry) arrayList.get(i9)).getKid());
                        if (secretKey2 == null) {
                            throw new RuntimeException("Key " + ((CencSampleEncryptionInformationGroupEntry) arrayList.get(i9)).getKid() + " was not supplied for decryption");
                        }
                        this.indexToKey.put((RangeStartMap<Integer, SecretKey>) Integer.valueOf(i5), (Integer) secretKey2);
                    } else {
                        this.indexToKey.put((RangeStartMap<Integer, SecretKey>) Integer.valueOf(i5), (Integer) secretKey);
                    }
                }
                i6 = i8;
            }
            i5++;
            i2 = 0;
        }
        for (Box box : track.getSampleDescriptionBox().getSampleEntry().getBoxes()) {
            if (box instanceof AvcConfigurationBox) {
                this.configurationBox = box;
                this.subSampleEncryption = true;
                i4 = ((AvcConfigurationBox) box).getLengthSizeMinusOne() + 1;
            }
            if (box instanceof HevcConfigurationBox) {
                this.configurationBox = box;
                this.subSampleEncryption = true;
                i4 = ((HevcConfigurationBox) box).getLengthSizeMinusOne() + 1;
            }
        }
        for (int i10 = i2; i10 < this.samples.size(); i10++) {
            Sample sample = this.samples.get(i10);
            CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
            this.cencSampleAuxiliaryData.add(cencSampleAuxiliaryDataFormat);
            if (this.indexToKey.get(Integer.valueOf(i10)) != null) {
                byte[] byteArray = bigInteger2.toByteArray();
                byte[] bArr2 = new byte[i3];
                System.arraycopy(byteArray, byteArray.length - i3 > 0 ? byteArray.length - i3 : i2, bArr2, 8 - byteArray.length < 0 ? i2 : 8 - byteArray.length, byteArray.length > i3 ? i3 : byteArray.length);
                cencSampleAuxiliaryDataFormat.f27682iv = bArr2;
                ByteBuffer byteBuffer = (ByteBuffer) sample.asByteBuffer().rewind();
                if (this.subSampleEncryption) {
                    if (z2) {
                        CencSampleAuxiliaryDataFormat.Pair[] pairArr = new CencSampleAuxiliaryDataFormat.Pair[1];
                        pairArr[i2] = cencSampleAuxiliaryDataFormat.createPair(byteBuffer.remaining(), 0L);
                        cencSampleAuxiliaryDataFormat.pairs = pairArr;
                    } else {
                        ArrayList arrayList2 = new ArrayList(5);
                        while (byteBuffer.remaining() > 0) {
                            int l2i = CastUtils.l2i(IsoTypeReaderVariable.read(byteBuffer, i4));
                            int i11 = l2i + i4;
                            arrayList2.add(cencSampleAuxiliaryDataFormat.createPair((i11 < 112 || isClearNal(byteBuffer.duplicate())) ? i11 : (i11 % 16) + 96, i11 - i));
                            byteBuffer.position(byteBuffer.position() + l2i);
                        }
                        cencSampleAuxiliaryDataFormat.pairs = (CencSampleAuxiliaryDataFormat.Pair[]) arrayList2.toArray(new CencSampleAuxiliaryDataFormat.Pair[arrayList2.size()]);
                    }
                }
                bigInteger2 = bigInteger2.add(bigInteger);
            }
        }
    }

    public CencEncryptingTrackImpl(Track track, UUID uuid, SecretKey secretKey, boolean z) {
        this(track, uuid, Collections.singletonMap(uuid, secretKey), null, "cenc", z);
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        this.source.close();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<CompositionTimeToSample.Entry> getCompositionTimeEntries() {
        return this.source.getCompositionTimeEntries();
    }

    @Override // szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public UUID getDefaultKeyId() {
        return this.defaultKeyId;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long getDuration() {
        return this.source.getDuration();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Edit> getEdits() {
        return this.source.getEdits();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getHandler() {
        return this.source.getHandler();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "enc(" + this.source.getName() + ")";
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<SampleDependencyTypeBox.Entry> getSampleDependencies() {
        return this.source.getSampleDependencies();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public synchronized SampleDescriptionBox getSampleDescriptionBox() {
        if (this.stsd == null) {
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                this.source.getSampleDescriptionBox().getBox(Channels.newChannel(byteArrayOutputStream));
                int i = 0;
                this.stsd = (SampleDescriptionBox) new IsoFile(new MemoryDataSourceImpl(byteArrayOutputStream.toByteArray())).getBoxes().get(0);
                OriginalFormatBox originalFormatBox = new OriginalFormatBox();
                originalFormatBox.setDataFormat(this.stsd.getSampleEntry().getType());
                if (this.stsd.getSampleEntry() instanceof AudioSampleEntry) {
                    ((AudioSampleEntry) this.stsd.getSampleEntry()).setType(AudioSampleEntry.TYPE_ENCRYPTED);
                } else if (!(this.stsd.getSampleEntry() instanceof VisualSampleEntry)) {
                    throw new RuntimeException("I don't know how to cenc " + this.stsd.getSampleEntry().getType());
                } else {
                    ((VisualSampleEntry) this.stsd.getSampleEntry()).setType(VisualSampleEntry.TYPE_ENCRYPTED);
                }
                ProtectionSchemeInformationBox protectionSchemeInformationBox = new ProtectionSchemeInformationBox();
                protectionSchemeInformationBox.addBox(originalFormatBox);
                SchemeTypeBox schemeTypeBox = new SchemeTypeBox();
                schemeTypeBox.setSchemeType(this.encryptionAlgo);
                schemeTypeBox.setSchemeVersion(65536);
                protectionSchemeInformationBox.addBox(schemeTypeBox);
                SchemeInformationBox schemeInformationBox = new SchemeInformationBox();
                TrackEncryptionBox trackEncryptionBox = new TrackEncryptionBox();
                trackEncryptionBox.setDefaultIvSize(this.defaultKeyId == null ? 0 : 8);
                if (this.defaultKeyId != null) {
                    i = 1;
                }
                trackEncryptionBox.setDefaultAlgorithmId(i);
                trackEncryptionBox.setDefault_KID(this.defaultKeyId == null ? new UUID(0L, 0L) : this.defaultKeyId);
                schemeInformationBox.addBox(trackEncryptionBox);
                protectionSchemeInformationBox.addBox(schemeInformationBox);
                this.stsd.getSampleEntry().addBox(protectionSchemeInformationBox);
            } catch (IOException unused) {
                throw new RuntimeException("Dumping stsd to memory failed");
            }
        }
        return this.stsd;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSampleDurations() {
        return this.source.getSampleDurations();
    }

    @Override // szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public List<CencSampleAuxiliaryDataFormat> getSampleEncryptionEntries() {
        return this.cencSampleAuxiliaryData;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public Map<GroupEntry, long[]> getSampleGroups() {
        return this.sampleGroups;
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public List<Sample> getSamples() {
        return new CencEncryptingSampleList(this.indexToKey, this.source.getSamples(), this.cencSampleAuxiliaryData, this.encryptionAlgo);
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public SubSampleInformationBox getSubsampleInformationBox() {
        return this.source.getSubsampleInformationBox();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public long[] getSyncSamples() {
        return this.source.getSyncSamples();
    }

    @Override // szcom.googlecode.mp4parser.authoring.Track
    public TrackMetaData getTrackMetaData() {
        return this.source.getTrackMetaData();
    }

    @Override // szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public boolean hasSubSampleEncryption() {
        return this.subSampleEncryption;
    }

    public boolean isClearNal(ByteBuffer byteBuffer) {
        Object obj = this.configurationBox;
        if (!(obj instanceof HevcConfigurationBox)) {
            if (obj instanceof AvcConfigurationBox) {
                H264NalUnitHeader nalUnitHeader = H264TrackImpl.getNalUnitHeader(byteBuffer.slice());
                return (nalUnitHeader.nal_unit_type == 19 || nalUnitHeader.nal_unit_type == 2 || nalUnitHeader.nal_unit_type == 3 || nalUnitHeader.nal_unit_type == 4 || nalUnitHeader.nal_unit_type == 20 || nalUnitHeader.nal_unit_type == 5 || nalUnitHeader.nal_unit_type == 1) ? false : true;
            }
            throw new RuntimeException("Subsample encryption is activated but the CencEncryptingTrackImpl can't say if this sample is to be encrypted or not!");
        }
        H265NalUnitHeader nalUnitHeader2 = H265TrackImpl.getNalUnitHeader(byteBuffer.slice());
        if (nalUnitHeader2.nalUnitType < 0 || nalUnitHeader2.nalUnitType > 9) {
            if (nalUnitHeader2.nalUnitType < 16 || nalUnitHeader2.nalUnitType > 21) {
                return nalUnitHeader2.nalUnitType < 16 || nalUnitHeader2.nalUnitType > 21;
            }
            return false;
        }
        return false;
    }
}
