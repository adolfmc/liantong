package szcom.googlecode.mp4parser.authoring.builder;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import szcom.coremedia.iso.BoxParser;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.CompositionTimeToSample;
import szcom.coremedia.iso.boxes.Container;
import szcom.coremedia.iso.boxes.DataEntryUrlBox;
import szcom.coremedia.iso.boxes.DataInformationBox;
import szcom.coremedia.iso.boxes.DataReferenceBox;
import szcom.coremedia.iso.boxes.EditBox;
import szcom.coremedia.iso.boxes.EditListBox;
import szcom.coremedia.iso.boxes.FileTypeBox;
import szcom.coremedia.iso.boxes.HandlerBox;
import szcom.coremedia.iso.boxes.HintMediaHeaderBox;
import szcom.coremedia.iso.boxes.MediaBox;
import szcom.coremedia.iso.boxes.MediaHeaderBox;
import szcom.coremedia.iso.boxes.MediaInformationBox;
import szcom.coremedia.iso.boxes.MovieBox;
import szcom.coremedia.iso.boxes.MovieHeaderBox;
import szcom.coremedia.iso.boxes.NullMediaHeaderBox;
import szcom.coremedia.iso.boxes.SampleDependencyTypeBox;
import szcom.coremedia.iso.boxes.SampleSizeBox;
import szcom.coremedia.iso.boxes.SampleTableBox;
import szcom.coremedia.iso.boxes.SampleToChunkBox;
import szcom.coremedia.iso.boxes.SoundMediaHeaderBox;
import szcom.coremedia.iso.boxes.StaticChunkOffsetBox;
import szcom.coremedia.iso.boxes.SubtitleMediaHeaderBox;
import szcom.coremedia.iso.boxes.SyncSampleBox;
import szcom.coremedia.iso.boxes.TimeToSampleBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.coremedia.iso.boxes.TrackHeaderBox;
import szcom.coremedia.iso.boxes.VideoMediaHeaderBox;
import szcom.coremedia.iso.boxes.mdat.MediaDataBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.BasicContainer;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.authoring.Edit;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.tracks.CencEncryptedTrack;
import szcom.googlecode.mp4parser.boxes.dece.SampleEncryptionBox;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.GroupEntry;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleGroupDescriptionBox;
import szcom.googlecode.mp4parser.boxes.mp4.samplegrouping.SampleToGroupBox;
import szcom.googlecode.mp4parser.util.CastUtils;
import szcom.googlecode.mp4parser.util.Logger;
import szcom.googlecode.mp4parser.util.Math;
import szcom.googlecode.mp4parser.util.Mp4Arrays;
import szcom.googlecode.mp4parser.util.Path;
import szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DefaultMp4Builder implements Mp4Builder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static Logger LOG = Logger.getLogger(DefaultMp4Builder.class);
    private Fragmenter fragmenter;
    Map<Track, StaticChunkOffsetBox> chunkOffsetBoxes = new HashMap();
    Set<SampleAuxiliaryInformationOffsetsBox> sampleAuxiliaryInformationOffsetsBoxes = new HashSet();
    HashMap<Track, List<Sample>> track2Sample = new HashMap<>();
    HashMap<Track, long[]> track2SampleSizes = new HashMap<>();

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    class InterleaveChunkMdat implements Box {
        List<List<Sample>> chunkList;
        long contentSize;
        Container parent;
        List<Track> tracks;

        private InterleaveChunkMdat(Movie movie, Map<Track, int[]> map, long j) {
            int i;
            this.chunkList = new ArrayList();
            this.contentSize = j;
            this.tracks = movie.getTracks();
            ArrayList<Track> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList, new Comparator<Track>() { // from class: szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder.InterleaveChunkMdat.1
                @Override // java.util.Comparator
                public int compare(Track track, Track track2) {
                    return CastUtils.l2i(track.getTrackMetaData().getTrackId() - track2.getTrackMetaData().getTrackId());
                }
            });
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            for (Track track : arrayList) {
                hashMap.put(track, 0);
                hashMap2.put(track, 0);
                hashMap3.put(track, Double.valueOf(0.0d));
            }
            while (true) {
                Track track2 = null;
                for (Track track3 : arrayList) {
                    if (track2 == null || ((Double) hashMap3.get(track3)).doubleValue() < ((Double) hashMap3.get(track2)).doubleValue()) {
                        if (((Integer) hashMap.get(track3)).intValue() < map.get(track3).length) {
                            track2 = track3;
                        }
                    }
                }
                if (track2 == null) {
                    return;
                }
                int intValue = ((Integer) hashMap.get(track2)).intValue();
                int i2 = map.get(track2)[intValue];
                int intValue2 = ((Integer) hashMap2.get(track2)).intValue();
                double doubleValue = ((Double) hashMap3.get(track2)).doubleValue();
                int i3 = intValue2;
                while (true) {
                    i = intValue2 + i2;
                    if (i3 >= i) {
                        break;
                    }
                    doubleValue += track2.getSampleDurations()[i3] / track2.getTrackMetaData().getTimescale();
                    i3++;
                    i2 = i2;
                    intValue = intValue;
                }
                this.chunkList.add(track2.getSamples().subList(intValue2, i));
                hashMap.put(track2, Integer.valueOf(intValue + 1));
                hashMap2.put(track2, Integer.valueOf(i));
                hashMap3.put(track2, Double.valueOf(doubleValue));
            }
        }

        /* synthetic */ InterleaveChunkMdat(DefaultMp4Builder defaultMp4Builder, Movie movie, Map map, long j, InterleaveChunkMdat interleaveChunkMdat) {
            this(movie, map, j);
        }

        private boolean isSmallBox(long j) {
            return j + 8 < 4294967296L;
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public void getBox(WritableByteChannel writableByteChannel) {
            ByteBuffer allocate = ByteBuffer.allocate(16);
            long size = getSize();
            if (isSmallBox(size)) {
                IsoTypeWriter.writeUInt32(allocate, size);
            } else {
                IsoTypeWriter.writeUInt32(allocate, 1L);
            }
            allocate.put(IsoFile.fourCCtoBytes(MediaDataBox.TYPE));
            if (isSmallBox(size)) {
                allocate.put(new byte[8]);
            } else {
                IsoTypeWriter.writeUInt64(allocate, size);
            }
            allocate.rewind();
            writableByteChannel.write(allocate);
            DefaultMp4Builder.LOG.logDebug("About to write " + this.contentSize);
            long j = 0;
            long j2 = 0;
            for (List<Sample> list : this.chunkList) {
                for (Sample sample : list) {
                    sample.writeTo(writableByteChannel);
                    j2 += sample.getSize();
                    if (j2 > 1048576) {
                        j2 -= 1048576;
                        j++;
                        DefaultMp4Builder.LOG.logDebug("Written " + j + "MB");
                    }
                }
            }
        }

        public long getDataOffset() {
            Box next;
            long j = 16;
            Container container = this;
            while (container instanceof Box) {
                InterleaveChunkMdat interleaveChunkMdat = container;
                Iterator<Box> it = interleaveChunkMdat.getParent().getBoxes().iterator();
                while (it.hasNext() && container != (next = it.next())) {
                    j += next.getSize();
                }
                container = interleaveChunkMdat.getParent();
            }
            return j;
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public long getOffset() {
            throw new RuntimeException("Doesn't have any meaning for programmatically created boxes");
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public Container getParent() {
            return this.parent;
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public long getSize() {
            return this.contentSize + 16;
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public String getType() {
            return MediaDataBox.TYPE;
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j, BoxParser boxParser) {
        }

        @Override // szcom.coremedia.iso.boxes.Box
        public void setParent(Container container) {
            this.parent = container;
        }
    }

    public static long gcd(long j, long j2) {
        return j2 == 0 ? j : gcd(j2, j % j2);
    }

    private static long sum(int[] iArr) {
        long j = 0;
        for (int i : iArr) {
            j += i;
        }
        return j;
    }

    private static long sum(long[] jArr) {
        long j = 0;
        for (long j2 : jArr) {
            j += j2;
        }
        return j;
    }

    @Override // szcom.googlecode.mp4parser.authoring.builder.Mp4Builder
    public Container build(Movie movie) {
        Box next;
        if (this.fragmenter == null) {
            this.fragmenter = new BetterFragmenter(2.0d);
        }
        LOG.logDebug("Creating movie " + movie);
        Iterator<Track> it = movie.getTracks().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Track next2 = it.next();
            List<Sample> samples = next2.getSamples();
            putSamples(next2, samples);
            long[] jArr = new long[samples.size()];
            for (int i = 0; i < jArr.length; i++) {
                jArr[i] = samples.get(i).getSize();
            }
            this.track2SampleSizes.put(next2, jArr);
        }
        BasicContainer basicContainer = new BasicContainer();
        basicContainer.addBox(createFileTypeBox(movie));
        HashMap hashMap = new HashMap();
        for (Track track : movie.getTracks()) {
            hashMap.put(track, getChunkSizes(track));
        }
        MovieBox createMovieBox = createMovieBox(movie, hashMap);
        basicContainer.addBox(createMovieBox);
        long j = 0;
        for (SampleSizeBox sampleSizeBox : Path.getPaths((Box) createMovieBox, "trak/mdia/minf/stbl/stsz")) {
            j += sum(sampleSizeBox.getSampleSizes());
        }
        LOG.logDebug("About to create mdat");
        InterleaveChunkMdat interleaveChunkMdat = new InterleaveChunkMdat(this, movie, hashMap, j, null);
        basicContainer.addBox(interleaveChunkMdat);
        LOG.logDebug("mdat crated");
        long dataOffset = interleaveChunkMdat.getDataOffset();
        for (StaticChunkOffsetBox staticChunkOffsetBox : this.chunkOffsetBoxes.values()) {
            long[] chunkOffsets = staticChunkOffsetBox.getChunkOffsets();
            for (int i2 = 0; i2 < chunkOffsets.length; i2++) {
                chunkOffsets[i2] = chunkOffsets[i2] + dataOffset;
            }
        }
        for (SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox : this.sampleAuxiliaryInformationOffsetsBoxes) {
            long size = sampleAuxiliaryInformationOffsetsBox.getSize() + 44;
            Container container = sampleAuxiliaryInformationOffsetsBox;
            while (true) {
                Container parent = container.getParent();
                Iterator<Box> it2 = parent.getBoxes().iterator();
                while (it2.hasNext() && (next = it2.next()) != container) {
                    size += next.getSize();
                }
                if (!(parent instanceof Box)) {
                    break;
                }
                container = parent;
            }
            long[] offsets = sampleAuxiliaryInformationOffsetsBox.getOffsets();
            for (int i3 = 0; i3 < offsets.length; i3++) {
                offsets[i3] = offsets[i3] + size;
            }
            sampleAuxiliaryInformationOffsetsBox.setOffsets(offsets);
        }
        return basicContainer;
    }

    protected void createCencBoxes(CencEncryptedTrack cencEncryptedTrack, SampleTableBox sampleTableBox, int[] iArr) {
        SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = new SampleAuxiliaryInformationSizesBox();
        sampleAuxiliaryInformationSizesBox.setAuxInfoType("cenc");
        sampleAuxiliaryInformationSizesBox.setFlags(1);
        List<CencSampleAuxiliaryDataFormat> sampleEncryptionEntries = cencEncryptedTrack.getSampleEncryptionEntries();
        if (cencEncryptedTrack.hasSubSampleEncryption()) {
            short[] sArr = new short[sampleEncryptionEntries.size()];
            for (int i = 0; i < sArr.length; i++) {
                sArr[i] = (short) sampleEncryptionEntries.get(i).getSize();
            }
            sampleAuxiliaryInformationSizesBox.setSampleInfoSizes(sArr);
        } else {
            sampleAuxiliaryInformationSizesBox.setDefaultSampleInfoSize(8);
            sampleAuxiliaryInformationSizesBox.setSampleCount(cencEncryptedTrack.getSamples().size());
        }
        SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = new SampleAuxiliaryInformationOffsetsBox();
        SampleEncryptionBox sampleEncryptionBox = new SampleEncryptionBox();
        sampleEncryptionBox.setSubSampleEncryption(cencEncryptedTrack.hasSubSampleEncryption());
        sampleEncryptionBox.setEntries(sampleEncryptionEntries);
        long[] jArr = new long[iArr.length];
        long offsetToFirstIV = sampleEncryptionBox.getOffsetToFirstIV();
        int i2 = 0;
        int i3 = 0;
        while (i2 < iArr.length) {
            jArr[i2] = offsetToFirstIV;
            int i4 = i3;
            int i5 = 0;
            while (i5 < iArr[i2]) {
                offsetToFirstIV += sampleEncryptionEntries.get(i4).getSize();
                i5++;
                i4++;
                sampleEncryptionBox = sampleEncryptionBox;
            }
            i2++;
            i3 = i4;
        }
        sampleAuxiliaryInformationOffsetsBox.setOffsets(jArr);
        sampleTableBox.addBox(sampleAuxiliaryInformationSizesBox);
        sampleTableBox.addBox(sampleAuxiliaryInformationOffsetsBox);
        sampleTableBox.addBox(sampleEncryptionBox);
        this.sampleAuxiliaryInformationOffsetsBoxes.add(sampleAuxiliaryInformationOffsetsBox);
    }

    protected void createCtts(Track track, SampleTableBox sampleTableBox) {
        List<CompositionTimeToSample.Entry> compositionTimeEntries = track.getCompositionTimeEntries();
        if (compositionTimeEntries == null || compositionTimeEntries.isEmpty()) {
            return;
        }
        CompositionTimeToSample compositionTimeToSample = new CompositionTimeToSample();
        compositionTimeToSample.setEntries(compositionTimeEntries);
        sampleTableBox.addBox(compositionTimeToSample);
    }

    protected Box createEdts(Track track, Movie movie) {
        if (track.getEdits() == null || track.getEdits().size() <= 0) {
            return null;
        }
        EditListBox editListBox = new EditListBox();
        editListBox.setVersion(0);
        ArrayList arrayList = new ArrayList();
        for (Edit edit : track.getEdits()) {
            arrayList.add(new EditListBox.Entry(editListBox, Math.round(edit.getSegmentDuration() * movie.getTimescale()), (edit.getMediaTime() * track.getTrackMetaData().getTimescale()) / edit.getTimeScale(), edit.getMediaRate()));
        }
        editListBox.setEntries(arrayList);
        EditBox editBox = new EditBox();
        editBox.addBox(editListBox);
        return editBox;
    }

    protected FileTypeBox createFileTypeBox(Movie movie) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("mp42");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.TYPE3);
        linkedList.add("isom");
        return new FileTypeBox("iso6", 1L, linkedList);
    }

    protected MovieBox createMovieBox(Movie movie, Map<Track, int[]> map) {
        long duration;
        MovieBox movieBox = new MovieBox();
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setCreationTime(new Date());
        movieHeaderBox.setModificationTime(new Date());
        movieHeaderBox.setMatrix(movie.getMatrix());
        long timescale = getTimescale(movie);
        long j = 0;
        for (Track track : movie.getTracks()) {
            if (track.getEdits() == null || track.getEdits().isEmpty()) {
                duration = (track.getDuration() * timescale) / track.getTrackMetaData().getTimescale();
            } else {
                double d = 0.0d;
                for (Edit edit : track.getEdits()) {
                    d += (long) edit.getSegmentDuration();
                }
                duration = (long) (d * timescale);
            }
            if (duration > j) {
                j = duration;
            }
        }
        movieHeaderBox.setDuration(j);
        movieHeaderBox.setTimescale(timescale);
        long j2 = 0;
        for (Track track2 : movie.getTracks()) {
            if (j2 < track2.getTrackMetaData().getTrackId()) {
                j2 = track2.getTrackMetaData().getTrackId();
            }
        }
        movieHeaderBox.setNextTrackId(j2 + 1);
        movieBox.addBox(movieHeaderBox);
        for (Track track3 : movie.getTracks()) {
            movieBox.addBox(createTrackBox(track3, movie, map));
        }
        Box createUdta = createUdta(movie);
        if (createUdta != null) {
            movieBox.addBox(createUdta);
        }
        return movieBox;
    }

    protected void createSdtp(Track track, SampleTableBox sampleTableBox) {
        if (track.getSampleDependencies() == null || track.getSampleDependencies().isEmpty()) {
            return;
        }
        SampleDependencyTypeBox sampleDependencyTypeBox = new SampleDependencyTypeBox();
        sampleDependencyTypeBox.setEntries(track.getSampleDependencies());
        sampleTableBox.addBox(sampleDependencyTypeBox);
    }

    protected Box createStbl(Track track, Movie movie, Map<Track, int[]> map) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        createStsd(track, sampleTableBox);
        createStts(track, sampleTableBox);
        createCtts(track, sampleTableBox);
        createStss(track, sampleTableBox);
        createSdtp(track, sampleTableBox);
        createStsc(track, map, sampleTableBox);
        createStsz(track, sampleTableBox);
        createStco(track, movie, map, sampleTableBox);
        HashMap hashMap = new HashMap();
        for (Map.Entry<GroupEntry, long[]> entry : track.getSampleGroups().entrySet()) {
            String type = entry.getKey().getType();
            List list = (List) hashMap.get(type);
            if (list == null) {
                list = new ArrayList();
                hashMap.put(type, list);
            }
            list.add(entry.getKey());
        }
        for (Map.Entry entry2 : hashMap.entrySet()) {
            SampleGroupDescriptionBox sampleGroupDescriptionBox = new SampleGroupDescriptionBox();
            String str = (String) entry2.getKey();
            sampleGroupDescriptionBox.setGroupingType(str);
            sampleGroupDescriptionBox.setGroupEntries((List) entry2.getValue());
            SampleToGroupBox sampleToGroupBox = new SampleToGroupBox();
            sampleToGroupBox.setGroupingType(str);
            SampleToGroupBox.Entry entry3 = null;
            for (int i = 0; i < track.getSamples().size(); i++) {
                int i2 = 0;
                for (int i3 = 0; i3 < ((List) entry2.getValue()).size(); i3++) {
                    if (Arrays.binarySearch(track.getSampleGroups().get((GroupEntry) ((List) entry2.getValue()).get(i3)), i) >= 0) {
                        i2 = i3 + 1;
                    }
                }
                if (entry3 == null || entry3.getGroupDescriptionIndex() != i2) {
                    SampleToGroupBox.Entry entry4 = new SampleToGroupBox.Entry(1L, i2);
                    sampleToGroupBox.getEntries().add(entry4);
                    entry3 = entry4;
                } else {
                    entry3.setSampleCount(entry3.getSampleCount() + 1);
                }
            }
            sampleTableBox.addBox(sampleGroupDescriptionBox);
            sampleTableBox.addBox(sampleToGroupBox);
        }
        if (track instanceof CencEncryptedTrack) {
            createCencBoxes((CencEncryptedTrack) track, sampleTableBox, map.get(track));
        }
        createSubs(track, sampleTableBox);
        LOG.logDebug("done with stbl for track_" + track.getTrackMetaData().getTrackId());
        return sampleTableBox;
    }

    protected void createStco(Track track, Movie movie, Map<Track, int[]> map, SampleTableBox sampleTableBox) {
        char c;
        int i;
        if (this.chunkOffsetBoxes.get(track) == null) {
            LOG.logDebug("Calculating chunk offsets for track_" + track.getTrackMetaData().getTrackId());
            ArrayList<Track> arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList, new Comparator<Track>() { // from class: szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder.1
                @Override // java.util.Comparator
                public int compare(Track track2, Track track3) {
                    return CastUtils.l2i(track2.getTrackMetaData().getTrackId() - track3.getTrackMetaData().getTrackId());
                }
            });
            HashMap hashMap = new HashMap();
            HashMap hashMap2 = new HashMap();
            HashMap hashMap3 = new HashMap();
            Iterator it = arrayList.iterator();
            while (true) {
                c = 0;
                if (!it.hasNext()) {
                    break;
                }
                ArrayList arrayList2 = arrayList;
                Track track2 = (Track) it.next();
                hashMap.put(track2, 0);
                hashMap2.put(track2, 0);
                hashMap3.put(track2, Double.valueOf(0.0d));
                this.chunkOffsetBoxes.put(track2, new StaticChunkOffsetBox());
                arrayList = arrayList2;
            }
            long j = 0;
            while (true) {
                Track track3 = null;
                for (Track track4 : arrayList) {
                    ArrayList arrayList3 = arrayList;
                    if ((track3 == null || ((Double) hashMap3.get(track4)).doubleValue() < ((Double) hashMap3.get(track3)).doubleValue()) && ((Integer) hashMap.get(track4)).intValue() < map.get(track4).length) {
                        track3 = track4;
                    }
                    arrayList = arrayList3;
                    c = 0;
                }
                if (track3 == null) {
                    break;
                }
                StaticChunkOffsetBox staticChunkOffsetBox = this.chunkOffsetBoxes.get(track3);
                long[] chunkOffsets = staticChunkOffsetBox.getChunkOffsets();
                long[] jArr = new long[1];
                jArr[c] = j;
                staticChunkOffsetBox.setChunkOffsets(Mp4Arrays.copyOfAndAppend(chunkOffsets, jArr));
                int intValue = ((Integer) hashMap.get(track3)).intValue();
                int i2 = map.get(track3)[intValue];
                int intValue2 = ((Integer) hashMap2.get(track3)).intValue();
                double doubleValue = ((Double) hashMap3.get(track3)).doubleValue();
                long[] sampleDurations = track3.getSampleDurations();
                int i3 = intValue2;
                while (true) {
                    i = intValue2 + i2;
                    if (i3 >= i) {
                        break;
                    }
                    long j2 = j + this.track2SampleSizes.get(track3)[i3];
                    doubleValue += sampleDurations[i3] / track3.getTrackMetaData().getTimescale();
                    i3++;
                    intValue = intValue;
                    j = j2;
                    arrayList = arrayList;
                }
                hashMap.put(track3, Integer.valueOf(intValue + 1));
                hashMap2.put(track3, Integer.valueOf(i));
                hashMap3.put(track3, Double.valueOf(doubleValue));
                c = 0;
            }
        }
        sampleTableBox.addBox(this.chunkOffsetBoxes.get(track));
    }

    protected void createStsc(Track track, Map<Track, int[]> map, SampleTableBox sampleTableBox) {
        int[] iArr = map.get(track);
        SampleToChunkBox sampleToChunkBox = new SampleToChunkBox();
        sampleToChunkBox.setEntries(new LinkedList());
        long j = -2147483648L;
        for (int i = 0; i < iArr.length; i++) {
            if (j != iArr[i]) {
                sampleToChunkBox.getEntries().add(new SampleToChunkBox.Entry(i + 1, iArr[i], 1L));
                j = iArr[i];
            }
        }
        sampleTableBox.addBox(sampleToChunkBox);
    }

    protected void createStsd(Track track, SampleTableBox sampleTableBox) {
        sampleTableBox.addBox(track.getSampleDescriptionBox());
    }

    protected void createStss(Track track, SampleTableBox sampleTableBox) {
        long[] syncSamples = track.getSyncSamples();
        if (syncSamples == null || syncSamples.length <= 0) {
            return;
        }
        SyncSampleBox syncSampleBox = new SyncSampleBox();
        syncSampleBox.setSampleNumber(syncSamples);
        sampleTableBox.addBox(syncSampleBox);
    }

    protected void createStsz(Track track, SampleTableBox sampleTableBox) {
        SampleSizeBox sampleSizeBox = new SampleSizeBox();
        sampleSizeBox.setSampleSizes(this.track2SampleSizes.get(track));
        sampleTableBox.addBox(sampleSizeBox);
    }

    protected void createStts(Track track, SampleTableBox sampleTableBox) {
        long[] sampleDurations;
        ArrayList arrayList = new ArrayList();
        TimeToSampleBox.Entry entry = null;
        for (long j : track.getSampleDurations()) {
            if (entry == null || entry.getDelta() != j) {
                entry = new TimeToSampleBox.Entry(1L, j);
                arrayList.add(entry);
            } else {
                entry.setCount(entry.getCount() + 1);
            }
        }
        TimeToSampleBox timeToSampleBox = new TimeToSampleBox();
        timeToSampleBox.setEntries(arrayList);
        sampleTableBox.addBox(timeToSampleBox);
    }

    protected void createSubs(Track track, SampleTableBox sampleTableBox) {
        if (track.getSubsampleInformationBox() != null) {
            sampleTableBox.addBox(track.getSubsampleInformationBox());
        }
    }

    protected TrackBox createTrackBox(Track track, Movie movie, Map<Track, int[]> map) {
        long duration;
        Box nullMediaHeaderBox;
        TrackBox trackBox = new TrackBox();
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.setEnabled(true);
        trackHeaderBox.setInMovie(true);
        trackHeaderBox.setMatrix(track.getTrackMetaData().getMatrix());
        trackHeaderBox.setAlternateGroup(track.getTrackMetaData().getGroup());
        trackHeaderBox.setCreationTime(track.getTrackMetaData().getCreationTime());
        if (track.getEdits() == null || track.getEdits().isEmpty()) {
            duration = (track.getDuration() * getTimescale(movie)) / track.getTrackMetaData().getTimescale();
        } else {
            long j = 0;
            for (Edit edit : track.getEdits()) {
                j += (long) edit.getSegmentDuration();
            }
            duration = j * track.getTrackMetaData().getTimescale();
        }
        trackHeaderBox.setDuration(duration);
        trackHeaderBox.setHeight(track.getTrackMetaData().getHeight());
        trackHeaderBox.setWidth(track.getTrackMetaData().getWidth());
        trackHeaderBox.setLayer(track.getTrackMetaData().getLayer());
        trackHeaderBox.setModificationTime(new Date());
        trackHeaderBox.setTrackId(track.getTrackMetaData().getTrackId());
        trackHeaderBox.setVolume(track.getTrackMetaData().getVolume());
        trackBox.addBox(trackHeaderBox);
        trackBox.addBox(createEdts(track, movie));
        MediaBox mediaBox = new MediaBox();
        trackBox.addBox(mediaBox);
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(track.getTrackMetaData().getCreationTime());
        mediaHeaderBox.setDuration(track.getDuration());
        mediaHeaderBox.setTimescale(track.getTrackMetaData().getTimescale());
        mediaHeaderBox.setLanguage(track.getTrackMetaData().getLanguage());
        mediaBox.addBox(mediaHeaderBox);
        HandlerBox handlerBox = new HandlerBox();
        mediaBox.addBox(handlerBox);
        handlerBox.setHandlerType(track.getHandler());
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (track.getHandler().equals("vide")) {
            nullMediaHeaderBox = new VideoMediaHeaderBox();
        } else if (track.getHandler().equals("soun")) {
            nullMediaHeaderBox = new SoundMediaHeaderBox();
        } else if (track.getHandler().equals("text")) {
            nullMediaHeaderBox = new NullMediaHeaderBox();
        } else if (track.getHandler().equals("subt")) {
            nullMediaHeaderBox = new SubtitleMediaHeaderBox();
        } else if (!track.getHandler().equals("hint")) {
            if (track.getHandler().equals("sbtl")) {
                nullMediaHeaderBox = new NullMediaHeaderBox();
            }
            DataInformationBox dataInformationBox = new DataInformationBox();
            DataReferenceBox dataReferenceBox = new DataReferenceBox();
            dataInformationBox.addBox(dataReferenceBox);
            DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
            dataEntryUrlBox.setFlags(1);
            dataReferenceBox.addBox(dataEntryUrlBox);
            mediaInformationBox.addBox(dataInformationBox);
            mediaInformationBox.addBox(createStbl(track, movie, map));
            mediaBox.addBox(mediaInformationBox);
            Logger logger = LOG;
            logger.logDebug("done with trak for track_" + track.getTrackMetaData().getTrackId());
            return trackBox;
        } else {
            nullMediaHeaderBox = new HintMediaHeaderBox();
        }
        mediaInformationBox.addBox(nullMediaHeaderBox);
        DataInformationBox dataInformationBox2 = new DataInformationBox();
        DataReferenceBox dataReferenceBox2 = new DataReferenceBox();
        dataInformationBox2.addBox(dataReferenceBox2);
        DataEntryUrlBox dataEntryUrlBox2 = new DataEntryUrlBox();
        dataEntryUrlBox2.setFlags(1);
        dataReferenceBox2.addBox(dataEntryUrlBox2);
        mediaInformationBox.addBox(dataInformationBox2);
        mediaInformationBox.addBox(createStbl(track, movie, map));
        mediaBox.addBox(mediaInformationBox);
        Logger logger2 = LOG;
        logger2.logDebug("done with trak for track_" + track.getTrackMetaData().getTrackId());
        return trackBox;
    }

    protected Box createUdta(Movie movie) {
        return null;
    }

    int[] getChunkSizes(Track track) {
        long[] sampleNumbers = this.fragmenter.sampleNumbers(track);
        int[] iArr = new int[sampleNumbers.length];
        int i = 0;
        while (i < sampleNumbers.length) {
            int i2 = i + 1;
            iArr[i] = CastUtils.l2i((sampleNumbers.length == i2 ? track.getSamples().size() : sampleNumbers[i2] - 1) - (sampleNumbers[i] - 1));
            i = i2;
        }
        return iArr;
    }

    public long getTimescale(Movie movie) {
        long timescale = movie.getTracks().iterator().next().getTrackMetaData().getTimescale();
        for (Track track : movie.getTracks()) {
            timescale = Math.lcm(timescale, track.getTrackMetaData().getTimescale());
        }
        return timescale;
    }

    protected List<Sample> putSamples(Track track, List<Sample> list) {
        return this.track2Sample.put(track, list);
    }

    public void setFragmenter(Fragmenter fragmenter) {
        this.fragmenter = fragmenter;
    }
}
