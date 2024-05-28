package szcom.mp4parser.streaming;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.IsoTypeWriter;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.DataEntryUrlBox;
import szcom.coremedia.iso.boxes.DataInformationBox;
import szcom.coremedia.iso.boxes.DataReferenceBox;
import szcom.coremedia.iso.boxes.FileTypeBox;
import szcom.coremedia.iso.boxes.HandlerBox;
import szcom.coremedia.iso.boxes.HintMediaHeaderBox;
import szcom.coremedia.iso.boxes.MediaBox;
import szcom.coremedia.iso.boxes.MediaHeaderBox;
import szcom.coremedia.iso.boxes.MediaInformationBox;
import szcom.coremedia.iso.boxes.MovieBox;
import szcom.coremedia.iso.boxes.MovieHeaderBox;
import szcom.coremedia.iso.boxes.NullMediaHeaderBox;
import szcom.coremedia.iso.boxes.SampleSizeBox;
import szcom.coremedia.iso.boxes.SampleTableBox;
import szcom.coremedia.iso.boxes.SampleToChunkBox;
import szcom.coremedia.iso.boxes.SoundMediaHeaderBox;
import szcom.coremedia.iso.boxes.StaticChunkOffsetBox;
import szcom.coremedia.iso.boxes.SubtitleMediaHeaderBox;
import szcom.coremedia.iso.boxes.TimeToSampleBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.coremedia.iso.boxes.VideoMediaHeaderBox;
import szcom.coremedia.iso.boxes.fragment.MovieExtendsBox;
import szcom.coremedia.iso.boxes.fragment.MovieExtendsHeaderBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentBox;
import szcom.coremedia.iso.boxes.fragment.MovieFragmentHeaderBox;
import szcom.coremedia.iso.boxes.fragment.SampleFlags;
import szcom.coremedia.iso.boxes.fragment.TrackExtendsBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentBaseMediaDecodeTimeBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentBox;
import szcom.coremedia.iso.boxes.fragment.TrackFragmentHeaderBox;
import szcom.coremedia.iso.boxes.fragment.TrackRunBox;
import szcom.coremedia.iso.boxes.mdat.MediaDataBox;
import szcom.coremedia.iso.boxes.sampleentry.VisualSampleEntry;
import szcom.googlecode.mp4parser.util.Math;
import szcom.googlecode.mp4parser.util.Mp4Arrays;
import szcom.mp4parser.streaming.extensions.CencEncryptTrackExtension;
import szcom.mp4parser.streaming.extensions.CompositionTimeSampleExtension;
import szcom.mp4parser.streaming.extensions.CompositionTimeTrackExtension;
import szcom.mp4parser.streaming.extensions.SampleFlagsSampleExtension;
import szcom.mp4parser.streaming.extensions.SampleFlagsTrackExtension;
import szcom.mp4parser.streaming.extensions.TrackIdTrackExtension;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MultiTrackFragmentedMp4Writer implements StreamingMp4Writer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    CompositionTimeTrackExtension compositionTimeTrackExtension;
    private final OutputStream outputStream;
    SampleFlagsTrackExtension sampleDependencyTrackExtension;
    StreamingTrack[] source;
    Map<StreamingTrack, List<StreamingSample>> fragmentBuffers = new HashMap();
    private long sequenceNumber = 1;
    private long currentFragmentStartTime = 0;
    private long currentTime = 0;
    Date creationTime = new Date();

    /* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
    class ConsumeSamplesCallable implements Callable {
        private StreamingTrack streamingTrack;

        public ConsumeSamplesCallable(StreamingTrack streamingTrack) {
            this.streamingTrack = streamingTrack;
        }

        @Override // java.util.concurrent.Callable
        public Object call() {
            StreamingSample poll;
            while (true) {
                try {
                    poll = this.streamingTrack.getSamples().poll(100L, TimeUnit.MILLISECONDS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if (poll != null) {
                    MultiTrackFragmentedMp4Writer.this.consumeSample(this.streamingTrack, poll);
                } else if (!this.streamingTrack.hasMoreSamples()) {
                    return null;
                }
            }
        }
    }

    public MultiTrackFragmentedMp4Writer(StreamingTrack[] streamingTrackArr, OutputStream outputStream) {
        this.source = streamingTrackArr;
        this.outputStream = outputStream;
        HashSet hashSet = new HashSet();
        for (StreamingTrack streamingTrack : streamingTrackArr) {
            if (streamingTrack.getTrackExtension(TrackIdTrackExtension.class) != null && hashSet.contains(Long.valueOf(((TrackIdTrackExtension) streamingTrack.getTrackExtension(TrackIdTrackExtension.class)).getTrackId()))) {
                throw new RuntimeException("There may not be two tracks with the same trackID within one file");
            }
        }
        for (StreamingTrack streamingTrack2 : streamingTrackArr) {
            if (streamingTrack2.getTrackExtension(TrackIdTrackExtension.class) != null) {
                ArrayList arrayList = new ArrayList(hashSet);
                Collections.sort(arrayList);
                streamingTrack2.addTrackExtension(new TrackIdTrackExtension(arrayList.size() > 0 ? ((Long) arrayList.get(arrayList.size() - 1)).longValue() + 1 : 1L));
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void consumeSample(StreamingTrack streamingTrack, StreamingSample streamingSample) {
        SampleExtension[] extensions;
        SampleFlagsSampleExtension sampleFlagsSampleExtension = null;
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            if (sampleExtension instanceof SampleFlagsSampleExtension) {
                sampleFlagsSampleExtension = (SampleFlagsSampleExtension) sampleExtension;
            } else if (sampleExtension instanceof CompositionTimeSampleExtension) {
                CompositionTimeSampleExtension compositionTimeSampleExtension = (CompositionTimeSampleExtension) sampleExtension;
            }
        }
        this.currentTime += streamingSample.getDuration();
        this.fragmentBuffers.get(streamingTrack).add(streamingSample);
        long j = this.currentTime;
        long j2 = this.currentFragmentStartTime;
        long timescale = streamingTrack.getTimescale();
        Long.signum(timescale);
        if (j > j2 + (timescale * 3) && this.fragmentBuffers.size() > 0 && (this.sampleDependencyTrackExtension == null || sampleFlagsSampleExtension == null || sampleFlagsSampleExtension.isSyncSample())) {
            WritableByteChannel newChannel = Channels.newChannel(this.outputStream);
            createMoof(streamingTrack).getBox(newChannel);
            createMdat(streamingTrack).getBox(newChannel);
            this.currentFragmentStartTime = this.currentTime;
            this.fragmentBuffers.clear();
        }
    }

    private Box createMdat(final StreamingTrack streamingTrack) {
        return new WriteOnlyBox(MediaDataBox.TYPE) { // from class: szcom.mp4parser.streaming.MultiTrackFragmentedMp4Writer.1
            @Override // szcom.coremedia.iso.boxes.Box
            public void getBox(WritableByteChannel writableByteChannel) {
                ArrayList arrayList = new ArrayList();
                long j = 8;
                for (StreamingSample streamingSample : MultiTrackFragmentedMp4Writer.this.fragmentBuffers.get(streamingTrack)) {
                    ByteBuffer content = streamingSample.getContent();
                    arrayList.add(content);
                    j += content.remaining();
                }
                ByteBuffer allocate = ByteBuffer.allocate(8);
                IsoTypeWriter.writeUInt32(allocate, j);
                allocate.put(IsoFile.fourCCtoBytes(getType()));
                writableByteChannel.write((ByteBuffer) allocate.rewind());
                Iterator it = arrayList.iterator();
                while (it.hasNext()) {
                    writableByteChannel.write((ByteBuffer) it.next());
                }
            }

            @Override // szcom.coremedia.iso.boxes.Box
            public long getSize() {
                long j = 8;
                for (StreamingSample streamingSample : MultiTrackFragmentedMp4Writer.this.fragmentBuffers.get(streamingTrack)) {
                    j += streamingSample.getContent().remaining();
                }
                return j;
            }
        };
    }

    private void createMfhd(long j, MovieFragmentBox movieFragmentBox) {
        MovieFragmentHeaderBox movieFragmentHeaderBox = new MovieFragmentHeaderBox();
        movieFragmentHeaderBox.setSequenceNumber(j);
        movieFragmentBox.addBox(movieFragmentHeaderBox);
    }

    private Box createMoof(StreamingTrack streamingTrack) {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        createMfhd(this.sequenceNumber, movieFragmentBox);
        createTraf(streamingTrack, movieFragmentBox);
        TrackRunBox trackRunBox = movieFragmentBox.getTrackRunBoxes().get(0);
        trackRunBox.setDataOffset(1);
        trackRunBox.setDataOffset((int) (movieFragmentBox.getSize() + 8));
        this.sequenceNumber++;
        return movieFragmentBox;
    }

    private void createTraf(StreamingTrack streamingTrack, MovieFragmentBox movieFragmentBox) {
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.addBox(trackFragmentBox);
        createTfhd(streamingTrack, trackFragmentBox);
        createTfdt(trackFragmentBox);
        createTrun(streamingTrack, trackFragmentBox);
        streamingTrack.getTrackExtension(CencEncryptTrackExtension.class);
    }

    @Override // szcom.mp4parser.streaming.StreamingMp4Writer, java.io.Closeable, java.lang.AutoCloseable
    public void close() {
    }

    protected DataInformationBox createDinf() {
        DataInformationBox dataInformationBox = new DataInformationBox();
        DataReferenceBox dataReferenceBox = new DataReferenceBox();
        dataInformationBox.addBox(dataReferenceBox);
        DataEntryUrlBox dataEntryUrlBox = new DataEntryUrlBox();
        dataEntryUrlBox.setFlags(1);
        dataReferenceBox.addBox(dataEntryUrlBox);
        return dataInformationBox;
    }

    public Box createFtyp() {
        LinkedList linkedList = new LinkedList();
        linkedList.add("isom");
        linkedList.add("iso6");
        linkedList.add(VisualSampleEntry.TYPE3);
        return new FileTypeBox("isom", 0L, linkedList);
    }

    protected Box createMdhd(StreamingTrack streamingTrack) {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(this.creationTime);
        mediaHeaderBox.setModificationTime(this.creationTime);
        mediaHeaderBox.setDuration(0L);
        mediaHeaderBox.setTimescale(streamingTrack.getTimescale());
        mediaHeaderBox.setLanguage(streamingTrack.getLanguage());
        return mediaHeaderBox;
    }

    protected Box createMdia(StreamingTrack streamingTrack) {
        MediaBox mediaBox = new MediaBox();
        mediaBox.addBox(createMdhd(streamingTrack));
        mediaBox.addBox(createMdiaHdlr(streamingTrack));
        mediaBox.addBox(createMinf(streamingTrack));
        return mediaBox;
    }

    protected Box createMdiaHdlr(StreamingTrack streamingTrack) {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.setHandlerType(streamingTrack.getHandler());
        return handlerBox;
    }

    protected Box createMinf(StreamingTrack streamingTrack) {
        Box nullMediaHeaderBox;
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (streamingTrack.getHandler().equals("vide")) {
            nullMediaHeaderBox = new VideoMediaHeaderBox();
        } else if (streamingTrack.getHandler().equals("soun")) {
            nullMediaHeaderBox = new SoundMediaHeaderBox();
        } else if (streamingTrack.getHandler().equals("text")) {
            nullMediaHeaderBox = new NullMediaHeaderBox();
        } else if (streamingTrack.getHandler().equals("subt")) {
            nullMediaHeaderBox = new SubtitleMediaHeaderBox();
        } else if (!streamingTrack.getHandler().equals("hint")) {
            if (streamingTrack.getHandler().equals("sbtl")) {
                nullMediaHeaderBox = new NullMediaHeaderBox();
            }
            mediaInformationBox.addBox(createDinf());
            mediaInformationBox.addBox(createStbl(streamingTrack));
            return mediaInformationBox;
        } else {
            nullMediaHeaderBox = new HintMediaHeaderBox();
        }
        mediaInformationBox.addBox(nullMediaHeaderBox);
        mediaInformationBox.addBox(createDinf());
        mediaInformationBox.addBox(createStbl(streamingTrack));
        return mediaInformationBox;
    }

    protected Box createMoov() {
        MovieBox movieBox = new MovieBox();
        movieBox.addBox(createMvhd());
        for (StreamingTrack streamingTrack : this.source) {
            movieBox.addBox(createTrak(streamingTrack));
        }
        movieBox.addBox(createMvex());
        return movieBox;
    }

    protected Box createMvex() {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.setVersion(1);
        movieExtendsHeaderBox.setFragmentDuration(0L);
        movieExtendsBox.addBox(movieExtendsHeaderBox);
        for (StreamingTrack streamingTrack : this.source) {
            movieExtendsBox.addBox(createTrex(streamingTrack));
        }
        return movieExtendsBox;
    }

    protected Box createMvhd() {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setVersion(1);
        movieHeaderBox.setCreationTime(this.creationTime);
        movieHeaderBox.setModificationTime(this.creationTime);
        movieHeaderBox.setDuration(0L);
        long[] jArr = new long[0];
        StreamingTrack[] streamingTrackArr = this.source;
        int length = streamingTrackArr.length;
        for (int i = 0; i < length; i++) {
            Mp4Arrays.copyOfAndAppend(jArr, streamingTrackArr[i].getTimescale());
        }
        movieHeaderBox.setTimescale(Math.lcm(jArr));
        movieHeaderBox.setNextTrackId(2L);
        return movieHeaderBox;
    }

    protected Box createStbl(StreamingTrack streamingTrack) {
        SampleTableBox sampleTableBox = new SampleTableBox();
        sampleTableBox.addBox(streamingTrack.getSampleDescriptionBox());
        sampleTableBox.addBox(new TimeToSampleBox());
        sampleTableBox.addBox(new SampleToChunkBox());
        sampleTableBox.addBox(new SampleSizeBox());
        sampleTableBox.addBox(new StaticChunkOffsetBox());
        return sampleTableBox;
    }

    protected void createTfdt(TrackFragmentBox trackFragmentBox) {
        TrackFragmentBaseMediaDecodeTimeBox trackFragmentBaseMediaDecodeTimeBox = new TrackFragmentBaseMediaDecodeTimeBox();
        trackFragmentBaseMediaDecodeTimeBox.setVersion(1);
        trackFragmentBaseMediaDecodeTimeBox.setBaseMediaDecodeTime(this.currentFragmentStartTime);
        trackFragmentBox.addBox(trackFragmentBaseMediaDecodeTimeBox);
    }

    protected void createTfhd(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        trackFragmentHeaderBox.setDefaultSampleFlags(new SampleFlags());
        trackFragmentHeaderBox.setBaseDataOffset(-1L);
        trackFragmentHeaderBox.setTrackId(((TrackIdTrackExtension) streamingTrack.getTrackExtension(TrackIdTrackExtension.class)).getTrackId());
        trackFragmentHeaderBox.setDefaultBaseIsMoof(true);
        trackFragmentBox.addBox(trackFragmentHeaderBox);
    }

    protected Box createTrak(StreamingTrack streamingTrack) {
        TrackBox trackBox = new TrackBox();
        trackBox.addBox(streamingTrack.getTrackHeaderBox());
        trackBox.addBox(streamingTrack.getTrackHeaderBox());
        trackBox.addBox(createMdia(streamingTrack));
        return trackBox;
    }

    protected Box createTrex(StreamingTrack streamingTrack) {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.setTrackId(streamingTrack.getTrackHeaderBox().getTrackId());
        trackExtendsBox.setDefaultSampleDescriptionIndex(1L);
        trackExtendsBox.setDefaultSampleDuration(0L);
        trackExtendsBox.setDefaultSampleSize(0L);
        SampleFlags sampleFlags = new SampleFlags();
        if ("soun".equals(streamingTrack.getHandler()) || "subt".equals(streamingTrack.getHandler())) {
            sampleFlags.setSampleDependsOn(2);
            sampleFlags.setSampleIsDependedOn(2);
        }
        trackExtendsBox.setDefaultSampleFlags(sampleFlags);
        return trackExtendsBox;
    }

    protected void createTrun(StreamingTrack streamingTrack, TrackFragmentBox trackFragmentBox) {
        TrackRunBox trackRunBox = new TrackRunBox();
        trackRunBox.setVersion(1);
        trackRunBox.setSampleDurationPresent(true);
        trackRunBox.setSampleSizePresent(true);
        ArrayList arrayList = new ArrayList(this.fragmentBuffers.size());
        trackRunBox.setSampleCompositionTimeOffsetPresent(streamingTrack.getTrackExtension(CompositionTimeTrackExtension.class) != null);
        boolean z = streamingTrack.getTrackExtension(SampleFlagsTrackExtension.class) != null;
        trackRunBox.setSampleFlagsPresent(z);
        for (StreamingSample streamingSample : this.fragmentBuffers.get(streamingTrack)) {
            TrackRunBox.Entry entry = new TrackRunBox.Entry();
            entry.setSampleSize(streamingSample.getContent().remaining());
            if (z) {
                SampleFlagsSampleExtension sampleFlagsSampleExtension = (SampleFlagsSampleExtension) StreamingSampleHelper.getSampleExtension(streamingSample, SampleFlagsSampleExtension.class);
                SampleFlags sampleFlags = new SampleFlags();
                sampleFlags.setIsLeading(sampleFlagsSampleExtension.getIsLeading());
                sampleFlags.setSampleIsDependedOn(sampleFlagsSampleExtension.getSampleIsDependedOn());
                sampleFlags.setSampleDependsOn(sampleFlagsSampleExtension.getSampleDependsOn());
                sampleFlags.setSampleHasRedundancy(sampleFlagsSampleExtension.getSampleHasRedundancy());
                sampleFlags.setSampleIsDifferenceSample(sampleFlagsSampleExtension.isSampleIsNonSyncSample());
                sampleFlags.setSamplePaddingValue(sampleFlagsSampleExtension.getSamplePaddingValue());
                sampleFlags.setSampleDegradationPriority(sampleFlagsSampleExtension.getSampleDegradationPriority());
                entry.setSampleFlags(sampleFlags);
            }
            entry.setSampleDuration(streamingSample.getDuration());
            if (trackRunBox.isSampleCompositionTimeOffsetPresent()) {
                entry.setSampleCompositionTimeOffset(((CompositionTimeSampleExtension) StreamingSampleHelper.getSampleExtension(streamingSample, CompositionTimeSampleExtension.class)).getCompositionTimeOffset());
            }
            arrayList.add(entry);
        }
        trackRunBox.setEntries(arrayList);
        trackFragmentBox.addBox(trackRunBox);
    }

    @Override // szcom.mp4parser.streaming.StreamingMp4Writer
    public void write() {
        WritableByteChannel newChannel = Channels.newChannel(this.outputStream);
        createFtyp().getBox(newChannel);
        createMoov().getBox(newChannel);
        ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(this.source.length);
        for (StreamingTrack streamingTrack : this.source) {
            newFixedThreadPool.submit(new ConsumeSamplesCallable(streamingTrack));
        }
    }
}
