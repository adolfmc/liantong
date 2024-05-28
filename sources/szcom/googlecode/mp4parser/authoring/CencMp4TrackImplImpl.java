package szcom.googlecode.mp4parser.authoring;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeReader;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.ChunkOffsetBox;
import szcom.coremedia.iso.boxes.Container;
import szcom.coremedia.iso.boxes.MovieBox;
import szcom.coremedia.iso.boxes.SchemeTypeBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.coremedia.iso.boxes.fragment.MovieExtendsBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentBox;
import szcom.coremedia.iso.boxes.fragment.TrackRunBox;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack;
import szcom.googlecode.mp4parser.util.Path;
import szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import szcom.mp4parser.iso23001.part7.TrackEncryptionBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class CencMp4TrackImplImpl extends Mp4TrackImpl implements CencEncryptedTrack {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private UUID defaultKeyId;
    private List<CencSampleAuxiliaryDataFormat> sampleEncryptionEntries;

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    class FindSaioSaizPair {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private Container container;
        private SampleAuxiliaryInformationOffsetsBox saio;
        private SampleAuxiliaryInformationSizesBox saiz;

        public FindSaioSaizPair(Container container) {
            this.container = container;
        }

        public SampleAuxiliaryInformationOffsetsBox getSaio() {
            return this.saio;
        }

        public SampleAuxiliaryInformationSizesBox getSaiz() {
            return this.saiz;
        }

        public FindSaioSaizPair invoke() {
            SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox;
            SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox;
            List boxes = this.container.getBoxes(SampleAuxiliaryInformationSizesBox.class);
            List boxes2 = this.container.getBoxes(SampleAuxiliaryInformationOffsetsBox.class);
            this.saiz = null;
            this.saio = null;
            for (int i = 0; i < boxes.size(); i++) {
                if ((this.saiz != null || ((SampleAuxiliaryInformationSizesBox) boxes.get(i)).getAuxInfoType() != null) && !"cenc".equals(((SampleAuxiliaryInformationSizesBox) boxes.get(i)).getAuxInfoType()) && ((sampleAuxiliaryInformationSizesBox = this.saiz) == null || sampleAuxiliaryInformationSizesBox.getAuxInfoType() != null || !"cenc".equals(((SampleAuxiliaryInformationSizesBox) boxes.get(i)).getAuxInfoType()))) {
                    throw new RuntimeException("Are there two cenc labeled saiz?");
                }
                this.saiz = (SampleAuxiliaryInformationSizesBox) boxes.get(i);
                if ((this.saio != null || ((SampleAuxiliaryInformationOffsetsBox) boxes2.get(i)).getAuxInfoType() != null) && !"cenc".equals(((SampleAuxiliaryInformationOffsetsBox) boxes2.get(i)).getAuxInfoType()) && ((sampleAuxiliaryInformationOffsetsBox = this.saio) == null || sampleAuxiliaryInformationOffsetsBox.getAuxInfoType() != null || !"cenc".equals(((SampleAuxiliaryInformationOffsetsBox) boxes2.get(i)).getAuxInfoType()))) {
                    throw new RuntimeException("Are there two cenc labeled saio?");
                }
                this.saio = (SampleAuxiliaryInformationOffsetsBox) boxes2.get(i);
            }
            return this;
        }
    }

    public CencMp4TrackImplImpl(String str, TrackBox trackBox, IsoFile... isoFileArr) {
        super(str, trackBox, isoFileArr);
        long j;
        int i;
        Container container;
        long j2;
        int i2;
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
        this.sampleEncryptionEntries = new ArrayList();
        long trackId = trackBox.getTrackHeaderBox().getTrackId();
        if (trackBox.getParent().getBoxes(MovieExtendsBox.class).size() <= 0) {
            TrackEncryptionBox trackEncryptionBox = (TrackEncryptionBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
            this.defaultKeyId = trackEncryptionBox.getDefault_KID();
            ChunkOffsetBox chunkOffsetBox = (ChunkOffsetBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stco[0]");
            long[] blowup = trackBox.getSampleTableBox().getSampleToChunkBox().blowup((chunkOffsetBox == null ? (ChunkOffsetBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/co64[0]") : chunkOffsetBox).getChunkOffsets().length);
            FindSaioSaizPair invoke = new FindSaioSaizPair((Container) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]")).invoke();
            SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = invoke.saio;
            SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = invoke.saiz;
            Container parent = ((MovieBox) trackBox.getParent()).getParent();
            if (sampleAuxiliaryInformationOffsetsBox.getOffsets().length == 1) {
                long j3 = sampleAuxiliaryInformationOffsetsBox.getOffsets()[0];
                if (sampleAuxiliaryInformationSizesBox.getDefaultSampleInfoSize() > 0) {
                    i = (sampleAuxiliaryInformationSizesBox.getSampleCount() * sampleAuxiliaryInformationSizesBox.getDefaultSampleInfoSize()) + 0;
                } else {
                    int i3 = 0;
                    for (int i4 = 0; i4 < sampleAuxiliaryInformationSizesBox.getSampleCount(); i4++) {
                        i3 += sampleAuxiliaryInformationSizesBox.getSampleInfoSizes()[i4];
                    }
                    i = i3;
                }
                ByteBuffer byteBuffer = parent.getByteBuffer(j3, i);
                for (int i5 = 0; i5 < sampleAuxiliaryInformationSizesBox.getSampleCount(); i5++) {
                    this.sampleEncryptionEntries.add(parseCencAuxDataFormat(trackEncryptionBox.getDefaultIvSize(), byteBuffer, sampleAuxiliaryInformationSizesBox.getSize(i5)));
                }
                return;
            } else if (sampleAuxiliaryInformationOffsetsBox.getOffsets().length != blowup.length) {
                throw new RuntimeException("Number of saio offsets must be either 1 or number of chunks");
            } else {
                int i6 = 0;
                for (int i7 = 0; i7 < blowup.length; i7++) {
                    long j4 = sampleAuxiliaryInformationOffsetsBox.getOffsets()[i7];
                    if (sampleAuxiliaryInformationSizesBox.getDefaultSampleInfoSize() > 0) {
                        j = (sampleAuxiliaryInformationSizesBox.getSampleCount() * blowup[i7]) + 0;
                    } else {
                        j = 0;
                        for (int i8 = 0; i8 < blowup[i7]; i8++) {
                            j += sampleAuxiliaryInformationSizesBox.getSize(i6 + i8);
                        }
                    }
                    ByteBuffer byteBuffer2 = parent.getByteBuffer(j4, j);
                    for (int i9 = 0; i9 < blowup[i7]; i9++) {
                        this.sampleEncryptionEntries.add(parseCencAuxDataFormat(trackEncryptionBox.getDefaultIvSize(), byteBuffer2, sampleAuxiliaryInformationSizesBox.getSize(i6 + i9)));
                    }
                    i6 = (int) (i6 + blowup[i7]);
                }
                return;
            }
        }
        Iterator it = ((Box) trackBox.getParent()).getParent().getBoxes(MovieFragmentBox.class).iterator();
        while (it.hasNext()) {
            MovieFragmentBox movieFragmentBox = (MovieFragmentBox) it.next();
            Iterator it2 = movieFragmentBox.getBoxes(TrackFragmentBox.class).iterator();
            while (it2.hasNext()) {
                TrackFragmentBox trackFragmentBox = (TrackFragmentBox) it2.next();
                if (trackFragmentBox.getTrackFragmentHeaderBox().getTrackId() == trackId) {
                    TrackEncryptionBox trackEncryptionBox2 = (TrackEncryptionBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schi[0]/tenc[0]");
                    this.defaultKeyId = trackEncryptionBox2.getDefault_KID();
                    if (trackFragmentBox.getTrackFragmentHeaderBox().hasBaseDataOffset()) {
                        container = ((Box) trackBox.getParent()).getParent();
                        j2 = trackFragmentBox.getTrackFragmentHeaderBox().getBaseDataOffset();
                    } else {
                        container = movieFragmentBox;
                        j2 = 0;
                    }
                    FindSaioSaizPair invoke2 = new FindSaioSaizPair(trackFragmentBox).invoke();
                    SampleAuxiliaryInformationOffsetsBox saio = invoke2.getSaio();
                    SampleAuxiliaryInformationSizesBox saiz = invoke2.getSaiz();
                    long[] offsets = saio.getOffsets();
                    List boxes = trackFragmentBox.getBoxes(TrackRunBox.class);
                    int i10 = 0;
                    int i11 = 0;
                    while (i10 < offsets.length) {
                        int size = ((TrackRunBox) boxes.get(i10)).getEntries().size();
                        long j5 = offsets[i10];
                        long j6 = trackId;
                        Iterator it3 = it;
                        long[] jArr = offsets;
                        int i12 = i11;
                        long j7 = 0;
                        while (true) {
                            i2 = i11 + size;
                            if (i12 >= i2) {
                                break;
                            }
                            j7 += saiz.getSize(i12);
                            i12++;
                            boxes = boxes;
                        }
                        List list = boxes;
                        ByteBuffer byteBuffer3 = container.getByteBuffer(j2 + j5, j7);
                        int i13 = i11;
                        while (i13 < i2) {
                            this.sampleEncryptionEntries.add(parseCencAuxDataFormat(trackEncryptionBox2.getDefaultIvSize(), byteBuffer3, saiz.getSize(i13)));
                            i13++;
                            movieFragmentBox = movieFragmentBox;
                            it2 = it2;
                        }
                        i10++;
                        it = it3;
                        i11 = i2;
                        trackId = j6;
                        offsets = jArr;
                        boxes = list;
                    }
                }
            }
        }
    }

    private CencSampleAuxiliaryDataFormat parseCencAuxDataFormat(int i, ByteBuffer byteBuffer, long j) {
        CencSampleAuxiliaryDataFormat cencSampleAuxiliaryDataFormat = new CencSampleAuxiliaryDataFormat();
        if (j > 0) {
            cencSampleAuxiliaryDataFormat.f27682iv = new byte[i];
            byteBuffer.get(cencSampleAuxiliaryDataFormat.f27682iv);
            if (j > i) {
                cencSampleAuxiliaryDataFormat.pairs = new CencSampleAuxiliaryDataFormat.Pair[IsoTypeReader.readUInt16(byteBuffer)];
                for (int i2 = 0; i2 < cencSampleAuxiliaryDataFormat.pairs.length; i2++) {
                    cencSampleAuxiliaryDataFormat.pairs[i2] = cencSampleAuxiliaryDataFormat.createPair(IsoTypeReader.readUInt16(byteBuffer), IsoTypeReader.readUInt32(byteBuffer));
                }
            }
        }
        return cencSampleAuxiliaryDataFormat;
    }

    @Override // szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public UUID getDefaultKeyId() {
        return this.defaultKeyId;
    }

    @Override // szcom.googlecode.mp4parser.authoring.AbstractTrack, szcom.googlecode.mp4parser.authoring.Track
    public String getName() {
        return "enc(" + super.getName() + ")";
    }

    @Override // szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public List<CencSampleAuxiliaryDataFormat> getSampleEncryptionEntries() {
        return this.sampleEncryptionEntries;
    }

    @Override // szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack
    public boolean hasSubSampleEncryption() {
        return false;
    }

    public String toString() {
        return "CencMp4TrackImpl{handler='" + getHandler() + "'}";
    }
}
