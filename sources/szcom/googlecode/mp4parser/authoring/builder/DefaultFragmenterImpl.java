package szcom.googlecode.mp4parser.authoring.builder;

import java.util.Arrays;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.util.Mp4Arrays;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class DefaultFragmenterImpl implements Fragmenter {
    private double fragmentLength;

    public DefaultFragmenterImpl(double d) {
        this.fragmentLength = 2.0d;
        this.fragmentLength = d;
    }

    @Override // szcom.googlecode.mp4parser.authoring.builder.Fragmenter
    public long[] sampleNumbers(Track track) {
        long[] sampleDurations = track.getSampleDurations();
        long[] syncSamples = track.getSyncSamples();
        long timescale = track.getTrackMetaData().getTimescale();
        long[] jArr = {1};
        double d = 0.0d;
        for (int i = 0; i < sampleDurations.length; i++) {
            d += sampleDurations[i] / timescale;
            if (d >= this.fragmentLength && (syncSamples == null || Arrays.binarySearch(syncSamples, i + 1) >= 0)) {
                if (i > 0) {
                    jArr = Mp4Arrays.copyOfAndAppend(jArr, i + 1);
                }
                d = 0.0d;
            }
        }
        if (d >= this.fragmentLength || jArr.length <= 1) {
            return jArr;
        }
        long[] jArr2 = new long[jArr.length - 1];
        System.arraycopy(jArr, 0, jArr2, 0, jArr.length - 1);
        return jArr2;
    }
}
