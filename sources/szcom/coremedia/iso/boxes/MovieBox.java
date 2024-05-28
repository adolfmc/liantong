package szcom.coremedia.iso.boxes;

import java.util.List;
import szcom.googlecode.mp4parser.AbstractContainerBox;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class MovieBox extends AbstractContainerBox {
    public static final String TYPE = "moov";

    public MovieBox() {
        super(TYPE);
    }

    public MovieHeaderBox getMovieHeaderBox() {
        for (Box box : getBoxes()) {
            if (box instanceof MovieHeaderBox) {
                return (MovieHeaderBox) box;
            }
        }
        return null;
    }

    public int getTrackCount() {
        return getBoxes(TrackBox.class).size();
    }

    public long[] getTrackNumbers() {
        List boxes = getBoxes(TrackBox.class);
        long[] jArr = new long[boxes.size()];
        for (int i = 0; i < boxes.size(); i++) {
            jArr[i] = ((TrackBox) boxes.get(i)).getTrackHeaderBox().getTrackId();
        }
        return jArr;
    }
}
