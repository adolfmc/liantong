package com.example.asus.detectionandalign.utils;

import android.util.Log;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.util.LinkedList;
import java.util.List;
import szcom.coremedia.iso.boxes.Container;
import szcom.googlecode.mp4parser.authoring.Movie;
import szcom.googlecode.mp4parser.authoring.Track;
import szcom.googlecode.mp4parser.authoring.builder.DefaultMp4Builder;
import szcom.googlecode.mp4parser.authoring.container.mp4.MovieCreator;
import szcom.googlecode.mp4parser.authoring.tracks.CroppedTrack;

/* renamed from: com.example.asus.detectionandalign.utils.k */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4301k {

    /* renamed from: a */
    private String f10098a;

    /* renamed from: b */
    private String f10099b;

    /* renamed from: c */
    private String f10100c;

    /* renamed from: d */
    private double f10101d;

    /* renamed from: e */
    private double f10102e;

    /* renamed from: a */
    public synchronized void m15937a() {
        long j;
        try {
            Movie build = MovieCreator.build(this.f10098a);
            List<Track> tracks = build.getTracks();
            build.setTracks(new LinkedList());
            int i = 0;
            boolean z = false;
            for (Track track : tracks) {
                if (track.getSyncSamples() != null && track.getSyncSamples().length > 0) {
                    if (z) {
                        throw new RuntimeException("The startTime has already been corrected by another track with SyncSample. Not Supported.");
                    }
                    this.f10101d = C4302l.m15931a(track, this.f10101d, false);
                    this.f10102e = C4302l.m15931a(track, this.f10102e, true);
                    z = true;
                }
            }
            for (Track track2 : tracks) {
                int i2 = i;
                long j2 = 0;
                long j3 = -1;
                double d = -1.0d;
                double d2 = 0.0d;
                long j4 = -1;
                while (i2 < track2.getSampleDurations().length) {
                    long j5 = track2.getSampleDurations()[i2];
                    int i3 = (d2 > d ? 1 : (d2 == d ? 0 : -1));
                    if (i3 > 0) {
                        j = j4;
                        if (d2 <= this.f10101d) {
                            j3 = j2;
                        }
                    } else {
                        j = j4;
                    }
                    j4 = (i3 <= 0 || d2 > this.f10102e) ? j : j2;
                    j2++;
                    i2++;
                    d = d2;
                    d2 = (j5 / track2.getTrackMetaData().getTimescale()) + d2;
                }
                build.addTrack(new CroppedTrack(track2, j3, j4));
                i = 0;
            }
            Container build2 = new DefaultMp4Builder().build(build);
            File file = new File(this.f10099b);
            file.mkdirs();
            FileOutputStream fileOutputStream = new FileOutputStream(new File(file, this.f10100c));
            FileChannel channel = fileOutputStream.getChannel();
            build2.writeContainer(channel);
            channel.close();
            fileOutputStream.close();
        } catch (Exception e) {
            Log.e("===================================   ", "" + e);
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m15936a(double d) {
        this.f10102e = d;
    }

    /* renamed from: a */
    public void m15935a(String str) {
        this.f10098a = str;
    }

    /* renamed from: b */
    public void m15934b(double d) {
        this.f10101d = d;
    }

    /* renamed from: b */
    public void m15933b(String str) {
        this.f10099b = str;
    }

    /* renamed from: c */
    public void m15932c(String str) {
        this.f10100c = str;
    }
}
