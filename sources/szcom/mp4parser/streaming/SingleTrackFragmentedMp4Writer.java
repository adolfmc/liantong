package szcom.mp4parser.streaming;

import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.WritableByteChannel;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
import szcom.mp4parser.streaming.extensions.CencEncryptTrackExtension;
import szcom.mp4parser.streaming.extensions.CompositionTimeSampleExtension;
import szcom.mp4parser.streaming.extensions.CompositionTimeTrackExtension;
import szcom.mp4parser.streaming.extensions.SampleFlagsSampleExtension;
import szcom.mp4parser.streaming.extensions.SampleFlagsTrackExtension;
import szcom.mp4parser.streaming.extensions.TrackIdTrackExtension;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SingleTrackFragmentedMp4Writer implements StreamingMp4Writer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    CompositionTimeTrackExtension compositionTimeTrackExtension;
    private final OutputStream outputStream;
    SampleFlagsTrackExtension sampleDependencyTrackExtension;
    private long sequenceNumber;
    StreamingTrack source;
    List<StreamingSample> fragment = new ArrayList();
    private long currentFragmentStartTime = 0;
    private long currentTime = 0;
    Date creationTime = new Date();

    public SingleTrackFragmentedMp4Writer(StreamingTrack streamingTrack, OutputStream outputStream) {
        this.source = streamingTrack;
        this.outputStream = outputStream;
        this.compositionTimeTrackExtension = (CompositionTimeTrackExtension) streamingTrack.getTrackExtension(CompositionTimeTrackExtension.class);
        this.sampleDependencyTrackExtension = (SampleFlagsTrackExtension) streamingTrack.getTrackExtension(SampleFlagsTrackExtension.class);
    }

    private void consumeSample(StreamingSample streamingSample, WritableByteChannel writableByteChannel) {
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
        this.fragment.add(streamingSample);
        if (this.currentTime <= this.currentFragmentStartTime + (this.source.getTimescale() * 3) || this.fragment.size() <= 0) {
            return;
        }
        if (this.sampleDependencyTrackExtension == null || sampleFlagsSampleExtension == null || sampleFlagsSampleExtension.isSyncSample()) {
            createMoof().getBox(writableByteChannel);
            createMdat().getBox(writableByteChannel);
            this.currentFragmentStartTime = this.currentTime;
            this.fragment.clear();
        }
    }

    private Box createMdat() {
        return new WriteOnlyBox(MediaDataBox.TYPE) { // from class: szcom.mp4parser.streaming.SingleTrackFragmentedMp4Writer.1
            @Override // szcom.coremedia.iso.boxes.Box
            public void getBox(WritableByteChannel writableByteChannel) {
                ArrayList arrayList = new ArrayList();
                long j = 8;
                for (StreamingSample streamingSample : SingleTrackFragmentedMp4Writer.this.fragment) {
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
                for (StreamingSample streamingSample : SingleTrackFragmentedMp4Writer.this.fragment) {
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

    private Box createMoof() {
        MovieFragmentBox movieFragmentBox = new MovieFragmentBox();
        createMfhd(this.sequenceNumber, movieFragmentBox);
        createTraf(this.sequenceNumber, movieFragmentBox);
        TrackRunBox trackRunBox = movieFragmentBox.getTrackRunBoxes().get(0);
        trackRunBox.setDataOffset(1);
        trackRunBox.setDataOffset((int) (movieFragmentBox.getSize() + 8));
        return movieFragmentBox;
    }

    private void createTraf(long j, MovieFragmentBox movieFragmentBox) {
        TrackFragmentBox trackFragmentBox = new TrackFragmentBox();
        movieFragmentBox.addBox(trackFragmentBox);
        createTfhd(trackFragmentBox);
        createTfdt(trackFragmentBox);
        createTrun(trackFragmentBox);
        this.source.getTrackExtension(CencEncryptTrackExtension.class);
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

    protected Box createMdhd() {
        MediaHeaderBox mediaHeaderBox = new MediaHeaderBox();
        mediaHeaderBox.setCreationTime(this.creationTime);
        mediaHeaderBox.setModificationTime(this.creationTime);
        mediaHeaderBox.setDuration(0L);
        mediaHeaderBox.setTimescale(this.source.getTimescale());
        mediaHeaderBox.setLanguage(this.source.getLanguage());
        return mediaHeaderBox;
    }

    protected Box createMdia() {
        MediaBox mediaBox = new MediaBox();
        mediaBox.addBox(createMdhd());
        mediaBox.addBox(createMdiaHdlr());
        mediaBox.addBox(createMinf());
        return mediaBox;
    }

    protected Box createMdiaHdlr() {
        HandlerBox handlerBox = new HandlerBox();
        handlerBox.setHandlerType(this.source.getHandler());
        return handlerBox;
    }

    protected Box createMinf() {
        Box nullMediaHeaderBox;
        MediaInformationBox mediaInformationBox = new MediaInformationBox();
        if (this.source.getHandler().equals("vide")) {
            nullMediaHeaderBox = new VideoMediaHeaderBox();
        } else if (this.source.getHandler().equals("soun")) {
            nullMediaHeaderBox = new SoundMediaHeaderBox();
        } else if (this.source.getHandler().equals("text")) {
            nullMediaHeaderBox = new NullMediaHeaderBox();
        } else if (this.source.getHandler().equals("subt")) {
            nullMediaHeaderBox = new SubtitleMediaHeaderBox();
        } else if (!this.source.getHandler().equals("hint")) {
            if (this.source.getHandler().equals("sbtl")) {
                nullMediaHeaderBox = new NullMediaHeaderBox();
            }
            mediaInformationBox.addBox(createDinf());
            mediaInformationBox.addBox(createStbl());
            return mediaInformationBox;
        } else {
            nullMediaHeaderBox = new HintMediaHeaderBox();
        }
        mediaInformationBox.addBox(nullMediaHeaderBox);
        mediaInformationBox.addBox(createDinf());
        mediaInformationBox.addBox(createStbl());
        return mediaInformationBox;
    }

    protected Box createMoov() {
        MovieBox movieBox = new MovieBox();
        movieBox.addBox(createMvhd());
        movieBox.addBox(createTrak());
        movieBox.addBox(createMvex());
        return movieBox;
    }

    protected Box createMvex() {
        MovieExtendsBox movieExtendsBox = new MovieExtendsBox();
        MovieExtendsHeaderBox movieExtendsHeaderBox = new MovieExtendsHeaderBox();
        movieExtendsHeaderBox.setVersion(1);
        movieExtendsHeaderBox.setFragmentDuration(0L);
        movieExtendsBox.addBox(movieExtendsHeaderBox);
        movieExtendsBox.addBox(createTrex());
        return movieExtendsBox;
    }

    protected Box createMvhd() {
        MovieHeaderBox movieHeaderBox = new MovieHeaderBox();
        movieHeaderBox.setVersion(1);
        movieHeaderBox.setCreationTime(this.creationTime);
        movieHeaderBox.setModificationTime(this.creationTime);
        movieHeaderBox.setDuration(0L);
        movieHeaderBox.setTimescale(this.source.getTimescale());
        movieHeaderBox.setNextTrackId(2L);
        return movieHeaderBox;
    }

    protected Box createStbl() {
        SampleTableBox sampleTableBox = new SampleTableBox();
        sampleTableBox.addBox(this.source.getSampleDescriptionBox());
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

    protected void createTfhd(TrackFragmentBox trackFragmentBox) {
        TrackFragmentHeaderBox trackFragmentHeaderBox = new TrackFragmentHeaderBox();
        trackFragmentHeaderBox.setDefaultSampleFlags(new SampleFlags());
        trackFragmentHeaderBox.setBaseDataOffset(-1L);
        TrackIdTrackExtension trackIdTrackExtension = (TrackIdTrackExtension) this.source.getTrackExtension(TrackIdTrackExtension.class);
        trackFragmentHeaderBox.setTrackId(trackIdTrackExtension != null ? trackIdTrackExtension.getTrackId() : 1L);
        trackFragmentHeaderBox.setDefaultBaseIsMoof(true);
        trackFragmentBox.addBox(trackFragmentHeaderBox);
    }

    protected Box createTrak() {
        TrackBox trackBox = new TrackBox();
        trackBox.addBox(this.source.getTrackHeaderBox());
        trackBox.addBox(createMdia());
        return trackBox;
    }

    protected Box createTrex() {
        TrackExtendsBox trackExtendsBox = new TrackExtendsBox();
        trackExtendsBox.setTrackId(this.source.getTrackHeaderBox().getTrackId());
        trackExtendsBox.setDefaultSampleDescriptionIndex(1L);
        trackExtendsBox.setDefaultSampleDuration(0L);
        trackExtendsBox.setDefaultSampleSize(0L);
        SampleFlags sampleFlags = new SampleFlags();
        if ("soun".equals(this.source.getHandler()) || "subt".equals(this.source.getHandler())) {
            sampleFlags.setSampleDependsOn(2);
            sampleFlags.setSampleIsDependedOn(2);
        }
        trackExtendsBox.setDefaultSampleFlags(sampleFlags);
        return trackExtendsBox;
    }

    protected void createTrun(TrackFragmentBox trackFragmentBox) {
        TrackRunBox trackRunBox = new TrackRunBox();
        trackRunBox.setVersion(1);
        trackRunBox.setSampleDurationPresent(true);
        trackRunBox.setSampleSizePresent(true);
        ArrayList arrayList = new ArrayList(this.fragment.size());
        trackRunBox.setSampleCompositionTimeOffsetPresent(this.source.getTrackExtension(CompositionTimeTrackExtension.class) != null);
        boolean z = this.source.getTrackExtension(SampleFlagsTrackExtension.class) != null;
        trackRunBox.setSampleFlagsPresent(z);
        for (StreamingSample streamingSample : this.fragment) {
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
        StreamingSample poll;
        WritableByteChannel newChannel = Channels.newChannel(this.outputStream);
        createFtyp().getBox(newChannel);
        createMoov().getBox(newChannel);
        while (true) {
            try {
                poll = this.source.getSamples().poll(100L, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if (poll != null) {
                consumeSample(poll, newChannel);
            } else if (!this.source.hasMoreSamples()) {
                return;
            }
        }
    }
}
