package szcom.coremedia.iso.boxes.mdat;

import java.util.AbstractList;
import java.util.List;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.boxes.Box;
import szcom.coremedia.iso.boxes.Container;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.coremedia.iso.boxes.fragment.MovieExtendsBox;
import szcom.googlecode.mp4parser.authoring.Sample;
import szcom.googlecode.mp4parser.authoring.samples.DefaultMp4SampleList;
import szcom.googlecode.mp4parser.authoring.samples.FragmentedMp4SampleList;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class SampleList extends AbstractList<Sample> {
    List<Sample> samples;

    public SampleList(TrackBox trackBox, IsoFile... isoFileArr) {
        Container parent = ((Box) trackBox.getParent()).getParent();
        if (!trackBox.getParent().getBoxes(MovieExtendsBox.class).isEmpty()) {
            this.samples = new FragmentedMp4SampleList(trackBox.getTrackHeaderBox().getTrackId(), parent, isoFileArr);
        } else if (isoFileArr.length > 0) {
            throw new RuntimeException("The TrackBox comes from a standard MP4 file. Only use the additionalFragments param if you are dealing with ( fragmented MP4 files AND additional fragments in standalone files )");
        } else {
            this.samples = new DefaultMp4SampleList(trackBox.getTrackHeaderBox().getTrackId(), parent);
        }
    }

    @Override // java.util.AbstractList, java.util.List
    public Sample get(int i) {
        return this.samples.get(i);
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.List
    public int size() {
        return this.samples.size();
    }
}
