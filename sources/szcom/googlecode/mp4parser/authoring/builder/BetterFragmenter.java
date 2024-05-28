package szcom.googlecode.mp4parser.authoring.builder;

import java.util.Arrays;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.util.Mp4Arrays;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class BetterFragmenter implements Fragmenter {
    private double targetDuration;

    public BetterFragmenter(double d) {
        this.targetDuration = d;
    }

    @Override // szcom.googlecode.mp4parser.authoring.builder.Fragmenter
    public long[] sampleNumbers(Track track) {
        double timescale = track.getTrackMetaData().getTimescale();
        long j = (long) (this.targetDuration * timescale);
        long[] jArr = new long[0];
        long[] syncSamples = track.getSyncSamples();
        long[] sampleDurations = track.getSampleDurations();
        long j2 = 2;
        if (syncSamples == null) {
            long[] jArr2 = {1};
            double d = 0.0d;
            for (int i = 1; i < sampleDurations.length; i++) {
                d += sampleDurations[i] / timescale;
                if (d >= this.targetDuration) {
                    if (i > 0) {
                        jArr2 = Mp4Arrays.copyOfAndAppend(jArr2, i + 1);
                    }
                    d = 0.0d;
                }
            }
            if (d < this.targetDuration && jArr2.length > 1) {
                jArr2[jArr2.length - 1] = jArr2[jArr2.length - 2] + (((sampleDurations.length + 1) - jArr2[jArr2.length - 2]) / 2);
            }
            return jArr2;
        }
        long[] jArr3 = new long[syncSamples.length];
        long duration = track.getDuration();
        long j3 = 0;
        int i2 = 0;
        long j4 = 0;
        while (i2 < sampleDurations.length) {
            int binarySearch = Arrays.binarySearch(syncSamples, i2 + 1);
            if (binarySearch >= 0) {
                jArr3[binarySearch] = j4;
            }
            j4 += sampleDurations[i2];
            i2++;
            j2 = 2;
        }
        int i3 = 0;
        while (i3 < jArr3.length - 1) {
            long j5 = jArr3[i3];
            int i4 = i3 + 1;
            long j6 = jArr3[i4];
            if (j3 <= j6 && Math.abs(j5 - j3) < Math.abs(j6 - j3)) {
                jArr = Mp4Arrays.copyOfAndAppend(jArr, syncSamples[i3]);
                j3 = jArr3[i3] + j;
            }
            i3 = i4;
        }
        return duration - jArr3[jArr3.length - 1] > j / j2 ? Mp4Arrays.copyOfAndAppend(jArr, syncSamples[jArr3.length - 1]) : jArr;
    }
}
