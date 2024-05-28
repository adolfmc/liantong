package szcom.googlecode.mp4parser.authoring;

import java.util.LinkedList;
import java.util.List;
import szcom.googlecode.mp4parser.util.Matrix;

/* loaded from: E:\9227576_dexfile_execute.dex.fixout.dex */
public class Movie {
    Matrix matrix;
    List<Track> tracks;

    public Movie() {
        this.matrix = Matrix.ROTATE_0;
        this.tracks = new LinkedList();
    }

    public Movie(List<Track> list) {
        this.matrix = Matrix.ROTATE_0;
        this.tracks = new LinkedList();
        this.tracks = list;
    }

    public static long gcd(long j, long j2) {
        return j2 == 0 ? j : gcd(j2, j % j2);
    }

    public void addTrack(Track track) {
        if (getTrackByTrackId(track.getTrackMetaData().getTrackId()) != null) {
            track.getTrackMetaData().setTrackId(getNextTrackId());
        }
        this.tracks.add(track);
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public long getNextTrackId() {
        long j = 0;
        for (Track track : this.tracks) {
            if (j < track.getTrackMetaData().getTrackId()) {
                j = track.getTrackMetaData().getTrackId();
            }
        }
        return j + 1;
    }

    public long getTimescale() {
        long timescale = getTracks().iterator().next().getTrackMetaData().getTimescale();
        for (Track track : getTracks()) {
            timescale = gcd(track.getTrackMetaData().getTimescale(), timescale);
        }
        return timescale;
    }

    public Track getTrackByTrackId(long j) {
        for (Track track : this.tracks) {
            if (track.getTrackMetaData().getTrackId() == j) {
                return track;
            }
        }
        return null;
    }

    public List<Track> getTracks() {
        return this.tracks;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void setTracks(List<Track> list) {
        this.tracks = list;
    }

    public String toString() {
        String str = "Movie{ ";
        for (Track track : this.tracks) {
            str = String.valueOf(str) + "track_" + track.getTrackMetaData().getTrackId() + " (" + track.getHandler() + ") ";
        }
        return String.valueOf(str) + '}';
    }
}
