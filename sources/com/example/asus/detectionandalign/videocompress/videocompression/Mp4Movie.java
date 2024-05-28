package com.example.asus.detectionandalign.videocompress.videocompression;

import android.annotation.TargetApi;
import android.media.MediaCodec;
import android.media.MediaFormat;
import java.io.File;
import java.util.ArrayList;
import szcom.googlecode.mp4parser.util.Matrix;

@TargetApi(16)
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class Mp4Movie {
    private File cacheFile;
    private int height;
    private Matrix matrix = Matrix.ROTATE_0;
    private ArrayList<Track> tracks = new ArrayList<>();
    private int width;

    public void addSample(int i, long j, MediaCodec.BufferInfo bufferInfo) {
        if (i < 0 || i >= this.tracks.size()) {
            return;
        }
        this.tracks.get(i).addSample(j, bufferInfo);
    }

    public int addTrack(MediaFormat mediaFormat, boolean z) {
        ArrayList<Track> arrayList = this.tracks;
        arrayList.add(new Track(arrayList.size(), mediaFormat, z));
        return this.tracks.size() - 1;
    }

    public File getCacheFile() {
        return this.cacheFile;
    }

    public int getHeight() {
        return this.height;
    }

    public Matrix getMatrix() {
        return this.matrix;
    }

    public ArrayList<Track> getTracks() {
        return this.tracks;
    }

    public int getWidth() {
        return this.width;
    }

    public void setCacheFile(File file) {
        this.cacheFile = file;
    }

    public void setRotation(int i) {
        Matrix matrix;
        if (i == 0) {
            matrix = Matrix.ROTATE_0;
        } else if (i == 90) {
            matrix = Matrix.ROTATE_90;
        } else if (i == 180) {
            matrix = Matrix.ROTATE_180;
        } else if (i != 270) {
            return;
        } else {
            matrix = Matrix.ROTATE_270;
        }
        this.matrix = matrix;
    }

    public void setSize(int i, int i2) {
        this.width = i;
        this.height = i2;
    }
}
