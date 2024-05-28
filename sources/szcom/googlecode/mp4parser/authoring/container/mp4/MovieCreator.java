package szcom.googlecode.mp4parser.authoring.container.mp4;

import java.io.File;
import szcom.coremedia.iso.IsoFile;
import szcom.coremedia.iso.boxes.SchemeTypeBox;
import szcom.coremedia.iso.boxes.TrackBox;
import szcom.googlecode.mp4parser.AbstractContainerBox;
import szcom.googlecode.mp4parser.DataSource;
import szcom.googlecode.mp4parser.FileDataSourceImpl;
import szcom.googlecode.mp4parser.authoring.CencMp4TrackImplImpl;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.Mp4TrackImpl;
import szcom.googlecode.mp4parser.util.Path;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class MovieCreator {
    public static Movie build(String str) {
        return build(new FileDataSourceImpl(new File(str)));
    }

    public static Movie build(DataSource dataSource) {
        IsoFile isoFile = new IsoFile(dataSource);
        Movie movie = new Movie();
        for (TrackBox trackBox : isoFile.getMovieBox().getBoxes(TrackBox.class)) {
            SchemeTypeBox schemeTypeBox = (SchemeTypeBox) Path.getPath((AbstractContainerBox) trackBox, "mdia[0]/minf[0]/stbl[0]/stsd[0]/enc.[0]/sinf[0]/schm[0]");
            movie.addTrack((schemeTypeBox == null || !(schemeTypeBox.getSchemeType().equals("cenc") || schemeTypeBox.getSchemeType().equals("cbc1"))) ? new Mp4TrackImpl(String.valueOf(dataSource.toString()) + "[" + trackBox.getTrackHeaderBox().getTrackId() + "]", trackBox, new IsoFile[0]) : new CencMp4TrackImplImpl(String.valueOf(dataSource.toString()) + "[" + trackBox.getTrackHeaderBox().getTrackId() + "]", trackBox, new IsoFile[0]));
        }
        movie.setMatrix(isoFile.getMovieBox().getMovieHeaderBox().getMatrix());
        return movie;
    }
}
