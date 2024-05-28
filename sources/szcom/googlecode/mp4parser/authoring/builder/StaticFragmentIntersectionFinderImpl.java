package szcom.googlecode.mp4parser.authoring.builder;

import java.util.Map;
import szcom.googlecode.mp4parser.authoring.Track;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class StaticFragmentIntersectionFinderImpl implements Fragmenter {
    Map<Track, long[]> sampleNumbers;

    public StaticFragmentIntersectionFinderImpl(Map<Track, long[]> map) {
        this.sampleNumbers = map;
    }

    @Override // szcom.googlecode.mp4parser.authoring.builder.Fragmenter
    public long[] sampleNumbers(Track track) {
        return this.sampleNumbers.get(track);
    }
}
