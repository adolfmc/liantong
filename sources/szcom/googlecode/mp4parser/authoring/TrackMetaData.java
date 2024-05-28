package szcom.googlecode.mp4parser.authoring;

import java.util.Date;
import szcom.googlecode.mp4parser.util.Matrix;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class TrackMetaData implements Cloneable {
    private double height;
    int layer;
    private long timescale;
    private float volume;
    private double width;
    private String language = "eng";
    private Date modificationTime = new Date();
    private Date creationTime = new Date();
    private Matrix matrix = Matrix.ROTATE_0;
    private long trackId = 1;

    /* renamed from: group  reason: collision with root package name */
    private int f27876group = 0;

    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException unused) {
            return null;
        }
    }

    public Date getCreationTime() {
        return this.creationTime;
    }

    public int getGroup() {
        return this.f27876group;
    }

    public double getHeight() {
        return this.height;
    }

    public String getLanguage() {
        return this.language;
    }

    public int getLayer() {
        return this.layer;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public Date getModificationTime() {
        return this.modificationTime;
    }

    public long getTimescale() {
        return this.timescale;
    }

    public long getTrackId() {
        return this.trackId;
    }

    public float getVolume() {
        return this.volume;
    }

    public double getWidth() {
        return this.width;
    }

    public void setCreationTime(Date date) {
        this.creationTime = date;
    }

    public void setGroup(int i) {
        this.f27876group = i;
    }

    public void setHeight(double d) {
        this.height = d;
    }

    public void setLanguage(String str) {
        this.language = str;
    }

    public void setLayer(int i) {
        this.layer = i;
    }

    public void setMatrix(Matrix matrix) {
        this.matrix = matrix;
    }

    public void setModificationTime(Date date) {
        this.modificationTime = date;
    }

    public void setTimescale(long j) {
        this.timescale = j;
    }

    public void setTrackId(long j) {
        this.trackId = j;
    }

    public void setVolume(float f) {
        this.volume = f;
    }

    public void setWidth(double d) {
        this.width = d;
    }
}
