package szcom.googlecode.mp4parser.authoring.builder;

import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
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
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.SampleSizeBox;
import szcom.coremedia.iso.boxes.SampleTableBox;
import szcom.coremedia.iso.boxes.SampleToChunkBox;
import szcom.coremedia.iso.boxes.SchemeTypeBox;
import szcom.coremedia.iso.boxes.SoundMediaHeaderBox;
import szcom.coremedia.iso.boxes.StaticChunkOffsetBox;
import szcom.coremedia.iso.boxes.SubtitleMediaHeaderBox;
import szcom.coremedia.iso.boxes.TimeToSampleBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.coremedia.iso.boxes.TrackHeaderBox;
import szcom.coremedia.iso.boxes.VideoMediaHeaderBox;
import szcom.coremedia.iso.boxes.fragment.MovieExtendsBox;
import szcom.coremedia.iso.boxes.fragment.MovieExtendsHeaderBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentHeaderBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentRandomAccessBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentRandomAccessOffsetBox;
import szcom.coremedia.iso.boxes.fragment.SampleFlags;
import szcom.coremedia.iso.boxes.fragment.TrackExtendsBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentBaseMediaDecodeTimeBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentRandomAccessBox;
import szcom.coremedia.iso.boxes.fragment.TrackRunBox;
import szcom.coremedia.iso.boxes.mdat.MediaDataBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.AbstractContainerBox;
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
import szcom.googlecode.mp4parser.util.Path;
import szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationOffsetsBox;
import szcom.mp4parser.iso14496.part12.SampleAuxiliaryInformationSizesBox;
import szcom.mp4parser.iso23001.part7.CencSampleAuxiliaryDataFormat;
import szcom.mp4parser.iso23001.part7.TrackEncryptionBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class FragmentedMp4Builder implements Mp4Builder {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getLogger(FragmentedMp4Builder.class.getName());
    protected Fragmenter fragmenter;

    private long getTrackDuration(Movie movie, Track track) {
        return (track.getDuration() * movie.getTimescale()) / track.getTrackMetaData().getTimescale();
    }

    @Override // szcom.googlecode.mp4parser.authoring.builder.Mp4Builder
    public Container build(Movie movie) {
        Logger logger = LOG;
        logger.fine("Creating movie " + movie);
        if (this.fragmenter == null) {
            this.fragmenter = new BetterFragmenter(2.0d);
        }
        BasicContainer basicContainer = new BasicContainer();
        basicContainer.addBox(createFtyp(movie));
        basicContainer.addBox(createMoov(movie));
        for (Box box : createMoofMdat(movie)) {
            basicContainer.addBox(box);
        }
        basicContainer.addBox(createMfra(movie, basicContainer));
        return basicContainer;
    }

    protected DataInformationBox createDinf(Movie movie, Track track) {
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.addBox(dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.setFlags(1);
        dataReferenceBox.addBox(dataEntryUrlBox);
        return dataInformationBox;
    }

    protected Box createEdts(Track track, Movie movie) {
        if (track.getEdits() == null || track.getEdits().size() <= 0) {
            return null;
        }
        EditListBox editListBox = new EditListBox();
        editListBox.setVersion(1);
        ArrayList arrayList = new ArrayList();
        for (Edit edit : track.getEdits()) {
            arrayList.add(new EditListBox.Entry(editListBox, Math.round(edit.getSegmentDuration() * movie.getTimescale()), (edit.getMediaTime() * track.getTrackMetaData().getTimescale()) / edit.getTimeScale(), edit.getMediaRate()));
        }
        editListBox.setEntries(arrayList);
        EditBox editBox = new EditBox();
        editBox.addBox(editListBox);
        return editBox;
    }

    protected int createFragment(List<Box> list, Track track, long j, long j2, int i) {
        if (j != j2) {
            list.add(createMoof(j, j2, track, i));
            list.add(createMdat(j, j2, track, i));
        }
        return i;
    }

    public Box createFtyp(Movie movie) {
        LinkedList linkedList = new LinkedList();
        linkedList.add("mp42");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.TYPE3);
        linkedList.add("isom");
        return new FileTypeBox("iso6", 1L, linkedList);
    }

    protected Box createMdat(final long j, final long j2, final Track track, int i) {
        return new Box() { // from class: szcom.googlecode.mp4parser.authoring.builder.FragmentedMp4Builder.1Mdat
            Container parent;
            long size_ = -1;

            @Override // szcom.coremedia.iso.boxes.Box
            public void getBox(WritableByteChannel writableByteChannel) {
                ByteBuffer allocate = ByteBuffer.allocate(8);
                IsoTypeWriter.writeUInt32(allocate, CastUtils.l2i(getSize()));
                allocate.put(IsoFile.fourCCtoBytes(getType()));
                allocate.rewind();
                writableByteChannel.write(allocate);
                for (Sample sample : FragmentedMp4Builder.this.getSamples(j, j2, track)) {
                    sample.writeTo(writableByteChannel);
                }
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
                long j3 = this.size_;
                if (j3 != -1) {
                    return j3;
                }
                long j4 = 8;
                for (Sample sample : FragmentedMp4Builder.this.getSamples(j, j2, track)) {
                    j4 += sample.getSize();
                }
                this.size_ = j4;
                return j4;
            }

            @Override // szcom.coremedia.iso.boxes.Box
            public String getType() {
                return MediaDataBox.TYPE;
            }

            @Override // szcom.coremedia.iso.boxes.Box
            public void parse(DataSource dataSource, ByteBuffer byteBuffer, long j3, BoxParser boxParser) {
            }

            @Override // szcom.coremedia.iso.boxes.Box
            public void setParent(Container container) {
                this.parent = container;
            }
        };
    }

    protected Box createMdhd(Movie movie, Track track) {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(track.getTrackMetaData().getCreationTime());
        mediaHeaderBox.setModificationTime(getDate());
        mediaHeaderBox.setDuration(0L);
        mediaHeaderBox.setTimescale(track.getTrackMetaData().getTimescale());
        mediaHeaderBox.setLanguage(track.getTrackMetaData().getLanguage());
        return mediaHeaderBox;
    }

    protected Box createMdia(Track track, Movie movie) {
        MediaBox mediaBox = new MediaBox();
        mediaBox.addBox(createMdhd(movie, track));
        mediaBox.addBox(createMdiaHdlr(track, movie));
        mediaBox.addBox(createMinf(track, movie));
        return mediaBox;
    }

    protected Box createMdiaHdlr(Track track, Movie movie) {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.setHandlerType(track.getHandler());
        return handlerBox;
    }

    protected void createMfhd(long j, long j2, Track track, int i, MovieFragmentBox movieFragmentBox) {
        MovieFragmentHeaderBox movieFragmentHeaderBox = new MovieFragmentHeaderBox();
        movieFragmentHeaderBox.setSequenceNumber(i);
        movieFragmentBox.addBox(movieFragmentHeaderBox);
    }

    protected Box createMfra(Movie movie, Container container) {
        MovieFragmentRandomAccessBox movieFragmentRandomAccessBox = new MovieFragmentRandomAccessBox();
        for (Track track : movie.getTracks()) {
            movieFragmentRandomAccessBox.addBox(createTfra(track, container));
        }
        MovieFragmentRandomAccessOffsetBox movieFragmentRandomAccessOffsetBox = new MovieFragmentRandomAccessOffsetBox();
        movieFragmentRandomAccessBox.addBox(movieFragmentRandomAccessOffsetBox);
        movieFragmentRandomAccessOffsetBox.setMfraSize(movieFragmentRandomAccessBox.getSize());
        return movieFragmentRandomAccessBox;
    }

    protected Box createMinf(Track track, Movie movie) {
        Box nullMediaHeaderBox;
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
            mediaInformationBox.addBox(createDinf(movie, track));
            mediaInformationBox.addBox(createStbl(movie, track));
            return mediaInformationBox;
        } else {
            nullMediaHeaderBox = new HintMediaHeaderBox();
        }
        mediaInformationBox.addBox(nullMediaHeaderBox);
        mediaInformationBox.addBox(createDinf(movie, track));
        mediaInformationBox.addBox(createStbl(movie, track));
        return mediaInformationBox;
    }

    protected Box createMoof(long j, long j2, Track track, int i) {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        createMfhd(j, j2, track, i, movieFragmentBox);
        createTraf(j, j2, track, i, movieFragmentBox);
        TrackRunBox trackRunBox = movieFragmentBox.getTrackRunBoxes().get(0);
        trackRunBox.setDataOffset(1);
        trackRunBox.setDataOffset((int) (movieFragmentBox.getSize() + 8));
        return movieFragmentBox;
    }

    protected List<Box> createMoofMdat(Movie movie) {
        LinkedList linkedList = new LinkedList();
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        for (Track track : movie.getTracks()) {
            hashMap.put(track, this.fragmenter.sampleNumbers(track));
            hashMap2.put(track, Double.valueOf(0.0d));
        }
        int i = 1;
        int i2 = 1;
        while (!hashMap.isEmpty()) {
            double d = Double.MAX_VALUE;
            Track track2 = null;
            for (Map.Entry entry : hashMap2.entrySet()) {
                int i3 = i;
                int i4 = i2;
                if (((Double) entry.getValue()).doubleValue() < d) {
                    d = ((Double) entry.getValue()).doubleValue();
                    track2 = (Track) entry.getKey();
                }
                i = i3;
                i2 = i4;
            }
            long[] jArr = (long[]) hashMap.get(track2);
            long j = jArr[0];
            long size = jArr.length > i ? jArr[i] : track2.getSamples().size() + i;
            long[] sampleDurations = track2.getSampleDurations();
            int i5 = i2;
            long timescale = track2.getTrackMetaData().getTimescale();
            double d2 = d;
            long j2 = j;
            while (j2 < size) {
                d2 += sampleDurations[CastUtils.l2i(j2 - 1)] / timescale;
                j2++;
                j = j;
                size = size;
            }
            createFragment(linkedList, track2, j, size, i5);
            if (jArr.length == 1) {
                hashMap.remove(track2);
                hashMap2.remove(track2);
            } else {
                long[] jArr2 = new long[jArr.length - 1];
                System.arraycopy(jArr, 1, jArr2, 0, jArr2.length);
                hashMap.put(track2, jArr2);
                hashMap2.put(track2, Double.valueOf(d2));
            }
            i2 = i5 + 1;
            i = 1;
        }
        return linkedList;
    }

    protected Box createMoov(Movie movie) {
        MovieBox movieBox = new MovieBox();
        movieBox.addBox(createMvhd(movie));
        for (Track track : movie.getTracks()) {
            movieBox.addBox(createTrak(track, movie));
        }
        movieBox.addBox(createMvex(movie));
        return movieBox;
    }

    protected Box createMvex(Movie movie) {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.setVersion(1);
        for (Track track : movie.getTracks()) {
            long trackDuration = getTrackDuration(movie, track);
            if (movieExtendsHeaderBox.getFragmentDuration() < trackDuration) {
                movieExtendsHeaderBox.setFragmentDuration(trackDuration);
            }
        }
        movieExtendsBox.addBox(movieExtendsHeaderBox);
        for (Track track2 : movie.getTracks()) {
            movieExtendsBox.addBox(createTrex(movie, track2));
        }
        return movieExtendsBox;
    }

    protected Box createMvhd(Movie movie) {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setVersion(1);
        movieHeaderBox.setCreationTime(getDate());
        movieHeaderBox.setModificationTime(getDate());
        long j = 0;
        movieHeaderBox.setDuration(0L);
        movieHeaderBox.setTimescale(movie.getTimescale());
        for (Track track : movie.getTracks()) {
            if (j < track.getTrackMetaData().getTrackId()) {
                j = track.getTrackMetaData().getTrackId();
            }
        }
        movieHeaderBox.setNextTrackId(j + 1);
        return movieHeaderBox;
    }

    protected void createSaio(long j, long j2, CencEncryptedTrack cencEncryptedTrack, int i, TrackFragmentBox trackFragmentBox) {
        Box next;
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.getPath((AbstractContainerBox) cencEncryptedTrack.getSampleDescriptionBox(), "enc.[0]/sinf[0]/schm[0]");
        SampleAuxiliaryInformationOffsetsBox sampleAuxiliaryInformationOffsetsBox = new SampleAuxiliaryInformationOffsetsBox();
        trackFragmentBox.addBox(sampleAuxiliaryInformationOffsetsBox);
        sampleAuxiliaryInformationOffsetsBox.setAuxInfoType("cenc");
        sampleAuxiliaryInformationOffsetsBox.setFlags(1);
        long j3 = 8;
        Iterator<Box> it = trackFragmentBox.getBoxes().iterator();
        while (true) {
            if (!it.hasNext()) {
                break;
            }
            Box next2 = it.next();
            if (next2 instanceof SampleEncryptionBox) {
                j3 += ((SampleEncryptionBox) next2).getOffsetToFirstIV();
                break;
            }
            j3 += next2.getSize();
        }
        long j4 = j3 + 16;
        Iterator<Box> it2 = ((MovieFragmentBox) trackFragmentBox.getParent()).getBoxes().iterator();
        while (it2.hasNext() && (next = it2.next()) != trackFragmentBox) {
            j4 += next.getSize();
        }
        sampleAuxiliaryInformationOffsetsBox.setOffsets(new long[]{j4});
    }

    protected void createSaiz(long j, long j2, CencEncryptedTrack cencEncryptedTrack, int i, TrackFragmentBox trackFragmentBox) {
        SampleDescriptionBox sampleDescriptionBox = cencEncryptedTrack.getSampleDescriptionBox();
        SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.getPath((AbstractContainerBox) sampleDescriptionBox, "enc.[0]/sinf[0]/schm[0]");
        TrackEncryptionBox trackEncryptionBox = (TrackEncryptionBox) Path.getPath((AbstractContainerBox) sampleDescriptionBox, "enc.[0]/sinf[0]/schi[0]/tenc[0]");
        SampleAuxiliaryInformationSizesBox sampleAuxiliaryInformationSizesBox = new SampleAuxiliaryInformationSizesBox();
        sampleAuxiliaryInformationSizesBox.setAuxInfoType("cenc");
        sampleAuxiliaryInformationSizesBox.setFlags(1);
        if (cencEncryptedTrack.hasSubSampleEncryption()) {
            short[] sArr = new short[CastUtils.l2i(j2 - j)];
            List<CencSampleAuxiliaryDataFormat> subList = cencEncryptedTrack.getSampleEncryptionEntries().subList(CastUtils.l2i(j - 1), CastUtils.l2i(j2 - 1));
            for (int i2 = 0; i2 < sArr.length; i2++) {
                sArr[i2] = (short) subList.get(i2).getSize();
            }
            sampleAuxiliaryInformationSizesBox.setSampleInfoSizes(sArr);
        } else {
            sampleAuxiliaryInformationSizesBox.setDefaultSampleInfoSize(trackEncryptionBox.getDefaultIvSize());
            sampleAuxiliaryInformationSizesBox.setSampleCount(CastUtils.l2i(j2 - j));
        }
        trackFragmentBox.addBox(sampleAuxiliaryInformationSizesBox);
    }

    protected void createSenc(long j, long j2, CencEncryptedTrack cencEncryptedTrack, int i, TrackFragmentBox trackFragmentBox) {
        SampleEncryptionBox sampleEncryptionBox = new SampleEncryptionBox();
        sampleEncryptionBox.setSubSampleEncryption(cencEncryptedTrack.hasSubSampleEncryption());
        sampleEncryptionBox.setEntries(cencEncryptedTrack.getSampleEncryptionEntries().subList(CastUtils.l2i(j - 1), CastUtils.l2i(j2 - 1)));
        trackFragmentBox.addBox(sampleEncryptionBox);
    }

    protected Box createStbl(Movie movie, Track track) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        createStsd(track, sampleTableBox);
        sampleTableBox.addBox(new TimeToSampleBox());
        sampleTableBox.addBox(new SampleToChunkBox());
        sampleTableBox.addBox(new SampleSizeBox());
        sampleTableBox.addBox(new StaticChunkOffsetBox());
        return sampleTableBox;
    }

    protected void createStsd(Track track, SampleTableBox sampleTableBox) {
        sampleTableBox.addBox(track.getSampleDescriptionBox());
    }

    protected void createTfdt(long j, Track track, TrackFragmentBox trackFragmentBox) {
        TrackFragmentBaseMediaDecodeTimeBox trackFragmentBaseMediaDecodeTimeBox = new TrackFragmentBaseMediaDecodeTimeBox();
        trackFragmentBaseMediaDecodeTimeBox.setVersion(1);
        long[] sampleDurations = track.getSampleDurations();
        long j2 = 0;
        for (int i = 1; i < j; i++) {
            j2 += sampleDurations[i - 1];
        }
        trackFragmentBaseMediaDecodeTimeBox.setBaseMediaDecodeTime(j2);
        trackFragmentBox.addBox(trackFragmentBaseMediaDecodeTimeBox);
    }

    protected void createTfhd(long j, long j2, Track track, int i, TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        trackFragmentHeaderBox.setDefaultSampleFlags(new SampleFlags());
        trackFragmentHeaderBox.setBaseDataOffset(-1L);
        trackFragmentHeaderBox.setTrackId(track.getTrackMetaData().getTrackId());
        trackFragmentHeaderBox.setDefaultBaseIsMoof(true);
        trackFragmentBox.addBox(trackFragmentHeaderBox);
    }

    protected Box createTfra(Track track, Container container) {
        TrackFragmentRandomAccessBox trackFragmentRandomAccessBox;
        LinkedList linkedList;
        TrackExtendsBox trackExtendsBox;
        Iterator<Box> it;
        int i;
        int i2;
        int i3;
        List list;
        List list2;
        int i4;
        Box box;
        LinkedList linkedList2;
        TrackFragmentRandomAccessBox trackFragmentRandomAccessBox2 = new TrackFragmentRandomAccessBox();
        trackFragmentRandomAccessBox2.setVersion(1);
        LinkedList linkedList3 = new LinkedList();
        r4 = null;
        for (TrackExtendsBox trackExtendsBox2 : Path.getPaths(container, "moov/mvex/trex")) {
            TrackFragmentRandomAccessBox trackFragmentRandomAccessBox3 = trackFragmentRandomAccessBox2;
            LinkedList linkedList4 = linkedList3;
            TrackExtendsBox trackExtendsBox3 = trackExtendsBox2;
            if (trackExtendsBox2.getTrackId() == track.getTrackMetaData().getTrackId()) {
                trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox3;
                linkedList3 = linkedList4;
            } else {
                trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox3;
                linkedList3 = linkedList4;
                trackExtendsBox2 = trackExtendsBox3;
            }
        }
        Iterator<Box> it2 = container.getBoxes().iterator();
        long j = 0;
        long j2 = 0;
        while (it2.hasNext()) {
            Box next = it2.next();
            if (next instanceof MovieFragmentBox) {
                List boxes = ((MovieFragmentBox) next).getBoxes(TrackFragmentBox.class);
                int i5 = 0;
                int i6 = 0;
                while (i6 < boxes.size()) {
                    TrackFragmentBox trackFragmentBox = (TrackFragmentBox) boxes.get(i6);
                    if (trackFragmentBox.getTrackFragmentHeaderBox().getTrackId() == track.getTrackMetaData().getTrackId()) {
                        List boxes2 = trackFragmentBox.getBoxes(TrackRunBox.class);
                        int i7 = i5;
                        while (i7 < boxes2.size()) {
                            LinkedList linkedList5 = new LinkedList();
                            TrackRunBox trackRunBox = (TrackRunBox) boxes2.get(i7);
                            long j3 = j2;
                            int i8 = i5;
                            while (i8 < trackRunBox.getEntries().size()) {
                                TrackRunBox.Entry entry = trackRunBox.getEntries().get(i8);
                                SampleFlags firstSampleFlags = (i8 == 0 && trackRunBox.isFirstSampleFlagsPresent()) ? trackRunBox.getFirstSampleFlags() : trackRunBox.isSampleFlagsPresent() ? entry.getSampleFlags() : trackExtendsBox2.getDefaultSampleFlags();
                                if (firstSampleFlags == null && track.getHandler().equals("vide")) {
                                    throw new RuntimeException("Cannot find SampleFlags for video track but it's required to build tfra");
                                }
                                if (firstSampleFlags == null || firstSampleFlags.getSampleDependsOn() == 2) {
                                    trackFragmentRandomAccessBox = trackFragmentRandomAccessBox2;
                                    linkedList = linkedList3;
                                    trackExtendsBox = trackExtendsBox2;
                                    it = it2;
                                    i = i8;
                                    i2 = i7;
                                    i3 = i6;
                                    list = boxes2;
                                    list2 = boxes;
                                    i4 = 0;
                                    box = next;
                                    linkedList2 = linkedList5;
                                    linkedList2.add(new TrackFragmentRandomAccessBox.Entry(j3, j, i6 + 1, i7 + 1, i8 + 1));
                                } else {
                                    trackFragmentRandomAccessBox = trackFragmentRandomAccessBox2;
                                    linkedList = linkedList3;
                                    trackExtendsBox = trackExtendsBox2;
                                    it = it2;
                                    i = i8;
                                    i2 = i7;
                                    linkedList2 = linkedList5;
                                    i3 = i6;
                                    list = boxes2;
                                    list2 = boxes;
                                    box = next;
                                    i4 = 0;
                                }
                                j3 += entry.getSampleDuration();
                                i8 = i + 1;
                                boxes = list2;
                                linkedList5 = linkedList2;
                                trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox;
                                linkedList3 = linkedList;
                                trackExtendsBox2 = trackExtendsBox;
                                it2 = it;
                                next = box;
                                i7 = i2;
                                i6 = i3;
                                boxes2 = list;
                                i5 = i4;
                            }
                            if (linkedList5.size() != trackRunBox.getEntries().size() || trackRunBox.getEntries().size() <= 0) {
                                linkedList3.addAll(linkedList5);
                            } else {
                                linkedList3.add((TrackFragmentRandomAccessBox.Entry) linkedList5.get(i5));
                            }
                            i7++;
                            j2 = j3;
                        }
                        continue;
                    }
                    i6++;
                    boxes = boxes;
                    trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox2;
                    linkedList3 = linkedList3;
                    trackExtendsBox2 = trackExtendsBox2;
                    it2 = it2;
                    next = next;
                    i5 = i5;
                }
                continue;
            }
            j += next.getSize();
            trackFragmentRandomAccessBox2 = trackFragmentRandomAccessBox2;
            linkedList3 = linkedList3;
            trackExtendsBox2 = trackExtendsBox2;
            it2 = it2;
        }
        trackFragmentRandomAccessBox2.setEntries(linkedList3);
        trackFragmentRandomAccessBox2.setTrackId(track.getTrackMetaData().getTrackId());
        return trackFragmentRandomAccessBox2;
    }

    protected Box createTkhd(Movie movie, Track track) {
        TrackHeaderBox trackHeaderBox = new TrackHeaderBox();
        trackHeaderBox.setVersion(1);
        trackHeaderBox.setFlags(7);
        trackHeaderBox.setAlternateGroup(track.getTrackMetaData().getGroup());
        trackHeaderBox.setCreationTime(track.getTrackMetaData().getCreationTime());
        trackHeaderBox.setDuration(0L);
        trackHeaderBox.setHeight(track.getTrackMetaData().getHeight());
        trackHeaderBox.setWidth(track.getTrackMetaData().getWidth());
        trackHeaderBox.setLayer(track.getTrackMetaData().getLayer());
        trackHeaderBox.setModificationTime(getDate());
        trackHeaderBox.setTrackId(track.getTrackMetaData().getTrackId());
        trackHeaderBox.setVolume(track.getTrackMetaData().getVolume());
        return trackHeaderBox;
    }

    protected void createTraf(long j, long j2, Track track, int i, MovieFragmentBox movieFragmentBox) {
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.addBox(trackFragmentBox);
        createTfhd(j, j2, track, i, trackFragmentBox);
        createTfdt(j, track, trackFragmentBox);
        createTrun(j, j2, track, i, trackFragmentBox);
        if (track instanceof CencEncryptedTrack) {
            CencEncryptedTrack cencEncryptedTrack = (CencEncryptedTrack) track;
            createSaiz(j, j2, cencEncryptedTrack, i, trackFragmentBox);
            createSenc(j, j2, cencEncryptedTrack, i, trackFragmentBox);
            createSaio(j, j2, cencEncryptedTrack, i, trackFragmentBox);
        }
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
        Iterator it = hashMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry2 = (Map.Entry) it.next();
            SampleGroupDescriptionBox sampleGroupDescriptionBox = new SampleGroupDescriptionBox();
            String str = (String) entry2.getKey();
            sampleGroupDescriptionBox.setGroupEntries((List) entry2.getValue());
            sampleGroupDescriptionBox.setGroupingType(str);
            SampleToGroupBox sampleToGroupBox = new SampleToGroupBox();
            sampleToGroupBox.setGroupingType(str);
            long j3 = 1;
            SampleToGroupBox.Entry entry3 = null;
            for (int l2i = CastUtils.l2i(j - 1); l2i < CastUtils.l2i(j2 - j3); l2i++) {
                int i2 = 0;
                int i3 = 0;
                while (i2 < ((List) entry2.getValue()).size()) {
                    Iterator it2 = it;
                    i3 = Arrays.binarySearch(track.getSampleGroups().get((GroupEntry) ((List) entry2.getValue()).get(i2)), (long) l2i) >= 0 ? 65537 + i2 : i3;
                    i2++;
                    it = it2;
                    j3 = 1;
                }
                if (entry3 == null || entry3.getGroupDescriptionIndex() != i3) {
                    SampleToGroupBox.Entry entry4 = new SampleToGroupBox.Entry(j3, i3);
                    sampleToGroupBox.getEntries().add(entry4);
                    entry3 = entry4;
                } else {
                    entry3.setSampleCount(entry3.getSampleCount() + j3);
                }
            }
            trackFragmentBox.addBox(sampleGroupDescriptionBox);
            trackFragmentBox.addBox(sampleToGroupBox);
        }
    }

    protected Box createTrak(Track track, Movie movie) {
        Logger logger = LOG;
        logger.fine("Creating Track " + track);
        TrackBox trackBox = new TrackBox();
        trackBox.addBox(createTkhd(movie, track));
        Box createEdts = createEdts(track, movie);
        if (createEdts != null) {
            trackBox.addBox(createEdts);
        }
        trackBox.addBox(createMdia(track, movie));
        return trackBox;
    }

    protected Box createTrex(Movie movie, Track track) {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.setTrackId(track.getTrackMetaData().getTrackId());
        trackExtendsBox.setDefaultSampleDescriptionIndex(1L);
        trackExtendsBox.setDefaultSampleDuration(0L);
        trackExtendsBox.setDefaultSampleSize(0L);
        SampleFlags sampleFlags = new SampleFlags();
        if ("soun".equals(track.getHandler()) || "subt".equals(track.getHandler())) {
            sampleFlags.setSampleDependsOn(2);
            sampleFlags.setSampleIsDependedOn(2);
        }
        trackExtendsBox.setDefaultSampleFlags(sampleFlags);
        return trackExtendsBox;
    }

    protected void createTrun(long j, long j2, Track track, int i, TrackFragmentBox trackFragmentBox) {
        long[] jArr;
        int i2;
        long j3;
        boolean z;
        TrackRunBox trackRunBox = new TrackRunBox();
        boolean z2 = true;
        trackRunBox.setVersion(1);
        long[] sampleSizes = getSampleSizes(j, j2, track, i);
        trackRunBox.setSampleDurationPresent(true);
        trackRunBox.setSampleSizePresent(true);
        ArrayList arrayList = new ArrayList(CastUtils.l2i(j2 - j));
        List<CompositionTimeToSample.Entry> compositionTimeEntries = track.getCompositionTimeEntries();
        CompositionTimeToSample.Entry[] entryArr = (compositionTimeEntries == null || compositionTimeEntries.size() <= 0) ? null : (CompositionTimeToSample.Entry[]) compositionTimeEntries.toArray(new CompositionTimeToSample.Entry[compositionTimeEntries.size()]);
        long count = entryArr != null ? entryArr[0].getCount() : -1;
        trackRunBox.setSampleCompositionTimeOffsetPresent(count > 0);
        int i3 = 0;
        long j4 = count;
        long j5 = 1;
        while (j5 < j) {
            long[] jArr2 = sampleSizes;
            if (entryArr != null) {
                j4--;
                j3 = 0;
                if (j4 == 0) {
                    z = true;
                    if (entryArr.length - i3 > 1) {
                        i3++;
                        j4 = entryArr[i3].getCount();
                    }
                    j5++;
                    sampleSizes = jArr2;
                    z2 = z;
                }
            } else {
                j3 = 0;
            }
            z = true;
            j5++;
            sampleSizes = jArr2;
            z2 = z;
        }
        boolean z3 = ((track.getSampleDependencies() == null || track.getSampleDependencies().isEmpty()) && (track.getSyncSamples() == null || track.getSyncSamples().length == 0)) ? false : z2;
        trackRunBox.setSampleFlagsPresent(z3);
        int i4 = 0;
        while (i4 < sampleSizes.length) {
            TrackRunBox.Entry entry = new TrackRunBox.Entry();
            entry.setSampleSize(sampleSizes[i4]);
            if (z3) {
                SampleFlags sampleFlags = new SampleFlags();
                if (track.getSampleDependencies() != null && !track.getSampleDependencies().isEmpty()) {
                    SampleDependencyTypeBox.Entry entry2 = track.getSampleDependencies().get(i4);
                    sampleFlags.setSampleDependsOn(entry2.getSampleDependsOn());
                    sampleFlags.setSampleIsDependedOn(entry2.getSampleIsDependentOn());
                    sampleFlags.setSampleHasRedundancy(entry2.getSampleHasRedundancy());
                }
                if (track.getSyncSamples() == null || track.getSyncSamples().length <= 0) {
                    jArr = sampleSizes;
                } else {
                    jArr = sampleSizes;
                    if (Arrays.binarySearch(track.getSyncSamples(), j + i4) >= 0) {
                        sampleFlags.setSampleIsDifferenceSample(false);
                        i2 = 2;
                    } else {
                        i2 = 1;
                        sampleFlags.setSampleIsDifferenceSample(true);
                    }
                    sampleFlags.setSampleDependsOn(i2);
                }
                entry.setSampleFlags(sampleFlags);
            } else {
                jArr = sampleSizes;
            }
            entry.setSampleDuration(track.getSampleDurations()[CastUtils.l2i((j + i4) - 1)]);
            if (entryArr != null) {
                entry.setSampleCompositionTimeOffset(entryArr[i3].getOffset());
                j4--;
                if (j4 == 0 && entryArr.length - i3 > 1) {
                    i3++;
                    j4 = entryArr[i3].getCount();
                }
            }
            arrayList.add(entry);
            i4++;
            sampleSizes = jArr;
        }
        trackRunBox.setEntries(arrayList);
        trackFragmentBox.addBox(trackRunBox);
    }

    public Date getDate() {
        return new Date();
    }

    public Fragmenter getFragmenter() {
        return this.fragmenter;
    }

    protected long[] getSampleSizes(long j, long j2, Track track, int i) {
        List<Sample> samples = getSamples(j, j2, track);
        long[] jArr = new long[samples.size()];
        for (int i2 = 0; i2 < jArr.length; i2++) {
            jArr[i2] = samples.get(i2).getSize();
        }
        return jArr;
    }

    protected List<Sample> getSamples(long j, long j2, Track track) {
        return track.getSamples().subList(CastUtils.l2i(j) - 1, CastUtils.l2i(j2) - 1);
    }

    public void setFragmenter(Fragmenter fragmenter) {
        this.fragmenter = fragmenter;
    }

    protected List<Track> sortTracksInSequence(List<Track> list, final int i, final Map<Track, long[]> map) {
        LinkedList linkedList = new LinkedList(list);
        Collections.sort(linkedList, new Comparator<Track>() { // from class: szcom.googlecode.mp4parser.authoring.builder.FragmentedMp4Builder.1
            @Override // java.util.Comparator
            public int compare(Track track, Track track2) {
                long j = ((long[]) map.get(track))[i];
                long j2 = ((long[]) map.get(track2))[i];
                long[] sampleDurations = track.getSampleDurations();
                long[] sampleDurations2 = track2.getSampleDurations();
                long j3 = 0;
                for (int i2 = 1; i2 < j; i2++) {
                    j3 += sampleDurations[i2 - 1];
                }
                long j4 = 0;
                for (int i3 = 1; i3 < j2; i3++) {
                    j4 += sampleDurations2[i3 - 1];
                }
                return (int) (((j3 / track.getTrackMetaData().getTimescale()) - (j4 / track2.getTrackMetaData().getTimescale())) * 100.0d);
            }
        });
        return linkedList;
    }
}
