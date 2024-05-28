package szcom.googlecode.mp4parser.authoring.builder;

import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Logger;
import szcom.coremedia.iso.boxes.OriginalFormatBox;
import szcom.coremedia.iso.boxes.SampleDescriptionBox;
import szcom.coremedia.iso.boxes.sampleentry.AudioSampleEntry;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.util.Math;
import szcom.googlecode.mp4parser.util.Path;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SyncSampleIntersectFinderImpl implements Fragmenter {
    private static Logger LOG = Logger.getLogger(SyncSampleIntersectFinderImpl.class.getName());
    private final int minFragmentDurationSeconds;
    private Movie movie;
    private Track referenceTrack;

    public SyncSampleIntersectFinderImpl(Movie movie, Track track, int i) {
        this.movie = movie;
        this.referenceTrack = track;
        this.minFragmentDurationSeconds = i;
    }

    private static long calculateTracktimesScalingFactor(Movie movie, Track track) {
        long j = 1;
        for (Track track2 : movie.getTracks()) {
            if (track2.getHandler().equals(track.getHandler()) && track2.getTrackMetaData().getTimescale() != track.getTrackMetaData().getTimescale()) {
                j = Math.lcm(j, track2.getTrackMetaData().getTimescale());
            }
        }
        return j;
    }

    static String getFormat(Track track) {
        SampleDescriptionBox sampleDescriptionBox = track.getSampleDescriptionBox();
        OriginalFormatBox originalFormatBox = (OriginalFormatBox) Path.getPath((AbstractContainerBox) sampleDescriptionBox, "enc./sinf/frma");
        return originalFormatBox != null ? originalFormatBox.getDataFormat() : sampleDescriptionBox.getSampleEntry().getType();
    }

    public static List<long[]> getSyncSamplesTimestamps(Movie movie, Track track) {
        long[] syncSamples;
        LinkedList linkedList = new LinkedList();
        for (Track track2 : movie.getTracks()) {
            if (track2.getHandler().equals(track.getHandler()) && (syncSamples = track2.getSyncSamples()) != null && syncSamples.length > 0) {
                linkedList.add(getTimes(track2, movie));
            }
        }
        return linkedList;
    }

    private static long[] getTimes(Track track, Movie movie) {
        long[] syncSamples = track.getSyncSamples();
        long[] jArr = new long[syncSamples.length];
        long calculateTracktimesScalingFactor = calculateTracktimesScalingFactor(movie, track);
        int i = 0;
        long j = 0;
        int i2 = 1;
        while (true) {
            long j2 = i2;
            if (j2 > syncSamples[syncSamples.length - 1]) {
                return jArr;
            }
            if (j2 == syncSamples[i]) {
                jArr[i] = j * calculateTracktimesScalingFactor;
                i++;
            }
            j += track.getSampleDurations()[i2 - 1];
            i2++;
        }
    }

    public long[] getCommonIndices(long[] jArr, long[] jArr2, long j, long[]... jArr3) {
        LinkedList linkedList;
        LinkedList<Long> linkedList2 = new LinkedList();
        LinkedList linkedList3 = new LinkedList();
        for (int i = 0; i < jArr2.length; i++) {
            boolean z = true;
            for (long[] jArr4 : jArr3) {
                z &= Arrays.binarySearch(jArr4, jArr2[i]) >= 0;
            }
            if (z) {
                linkedList2.add(Long.valueOf(jArr[i]));
                linkedList3.add(Long.valueOf(jArr2[i]));
            }
        }
        if (linkedList2.size() < jArr.length * 0.25d) {
            String str = String.valueOf("") + String.format("%5d - Common:  [", Integer.valueOf(linkedList2.size()));
            for (Long l : linkedList2) {
                long longValue = l.longValue();
                str = String.valueOf(str) + String.format("%10d,", Long.valueOf(longValue));
            }
            LOG.warning(String.valueOf(str) + "]");
            String str2 = String.valueOf("") + String.format("%5d - In    :  [", Integer.valueOf(jArr.length));
            for (long j2 : jArr) {
                str2 = String.valueOf(str2) + String.format("%10d,", Long.valueOf(j2));
            }
            LOG.warning(String.valueOf(str2) + "]");
            LOG.warning("There are less than 25% of common sync samples in the given track.");
            throw new RuntimeException("There are less than 25% of common sync samples in the given track.");
        }
        if (linkedList2.size() < jArr.length * 0.5d) {
            LOG.fine("There are less than 50% of common sync samples in the given track. This is implausible but I'm ok to continue.");
        } else if (linkedList2.size() < jArr.length) {
            LOG.finest("Common SyncSample positions vs. this tracks SyncSample positions: " + linkedList2.size() + " vs. " + jArr.length);
        }
        LinkedList linkedList4 = new LinkedList();
        if (this.minFragmentDurationSeconds > 0) {
            Iterator it = linkedList2.iterator();
            Iterator it2 = linkedList3.iterator();
            long j3 = -1;
            long j4 = -1;
            while (it.hasNext() && it2.hasNext()) {
                long longValue2 = ((Long) it.next()).longValue();
                long longValue3 = ((Long) it2.next()).longValue();
                if (j4 == j3 || (longValue3 - j4) / j >= this.minFragmentDurationSeconds) {
                    linkedList4.add(Long.valueOf(longValue2));
                    j4 = longValue3;
                }
                j3 = -1;
            }
            linkedList = linkedList4;
        } else {
            linkedList = linkedList2;
        }
        long[] jArr5 = new long[linkedList.size()];
        for (int i2 = 0; i2 < jArr5.length; i2++) {
            jArr5[i2] = ((Long) linkedList.get(i2)).longValue();
        }
        return jArr5;
    }

    @Override // szcom.googlecode.mp4parser.authoring.builder.Fragmenter
    public long[] sampleNumbers(Track track) {
        if ("vide".equals(track.getHandler())) {
            if (track.getSyncSamples() == null || track.getSyncSamples().length <= 0) {
                throw new RuntimeException("Video Tracks need sync samples. Only tracks other than video may have no sync samples.");
            }
            List<long[]> syncSamplesTimestamps = getSyncSamplesTimestamps(this.movie, track);
            return getCommonIndices(track.getSyncSamples(), getTimes(track, this.movie), track.getTrackMetaData().getTimescale(), (long[][]) syncSamplesTimestamps.toArray(new long[syncSamplesTimestamps.size()]));
        }
        long j = 1;
        int i = 0;
        if (!"soun".equals(track.getHandler())) {
            for (Track track2 : this.movie.getTracks()) {
                if (track2.getSyncSamples() != null && track2.getSyncSamples().length > 0) {
                    long[] sampleNumbers = sampleNumbers(track2);
                    int size = track2.getSamples().size();
                    long[] jArr = new long[sampleNumbers.length];
                    double size2 = track.getSamples().size() / size;
                    while (i < jArr.length) {
                        jArr[i] = ((long) Math.ceil((sampleNumbers[i] - 1) * size2)) + 1;
                        i++;
                    }
                    return jArr;
                }
            }
            throw new RuntimeException("There was absolutely no Track with sync samples. I can't work with that!");
        }
        if (this.referenceTrack == null) {
            for (Track track3 : this.movie.getTracks()) {
                if (track3.getSyncSamples() != null && "vide".equals(track3.getHandler()) && track3.getSyncSamples().length > 0) {
                    this.referenceTrack = track3;
                }
            }
        }
        Track track4 = this.referenceTrack;
        if (track4 != null) {
            long[] sampleNumbers2 = sampleNumbers(track4);
            int size3 = this.referenceTrack.getSamples().size();
            long[] jArr2 = new long[sampleNumbers2.length];
            long j2 = 192000;
            Iterator<Track> it = this.movie.getTracks().iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                Track next = it.next();
                if (getFormat(track).equals(getFormat(next))) {
                    AudioSampleEntry audioSampleEntry = (AudioSampleEntry) next.getSampleDescriptionBox().getSampleEntry();
                    if (audioSampleEntry.getSampleRate() < 192000) {
                        long sampleRate = audioSampleEntry.getSampleRate();
                        double size4 = next.getSamples().size() / size3;
                        long j3 = next.getSampleDurations()[0];
                        int i2 = 0;
                        while (i2 < jArr2.length) {
                            jArr2[i2] = (long) Math.ceil((sampleNumbers2[i2] - j) * size4 * j3);
                            i2++;
                            j = 1;
                        }
                        j2 = sampleRate;
                    }
                }
            }
            long j4 = track.getSampleDurations()[0];
            double sampleRate2 = ((AudioSampleEntry) track.getSampleDescriptionBox().getSampleEntry()).getSampleRate() / j2;
            if (sampleRate2 == Math.rint(sampleRate2)) {
                while (i < jArr2.length) {
                    jArr2[i] = (long) (((jArr2[i] * sampleRate2) / j4) + 1.0d);
                    i++;
                }
                return jArr2;
            }
            throw new RuntimeException("Sample rates must be a multiple of the lowest sample rate to create a correct file!");
        }
        throw new RuntimeException("There was absolutely no Track with sync samples. I can't work with that!");
    }
}
