package com.example.asus.detectionandalign.utils;

import android.content.Context;
import android.media.MediaPlayer;
import android.net.Uri;
import android.util.Log;
import java.io.IOException;

/* renamed from: com.example.asus.detectionandalign.utils.a */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C4289a {

    /* renamed from: a */
    private static final String f10087a = "a";

    /* renamed from: b */
    private MediaPlayer f10088b = new MediaPlayer();

    /* renamed from: a */
    public void m15962a() {
        try {
            this.f10088b.stop();
            this.f10088b.release();
            this.f10088b = null;
        } catch (Exception unused) {
            Log.e("AudioModule", "fail to play audio type: ");
        }
    }

    /* renamed from: a */
    public void m15961a(Context context, String str) {
        String str2;
        String str3;
        String packageName = context.getPackageName();
        Log.e("AudioModule", packageName);
        Log.e("AudioModule", str);
        int identifier = context.getResources().getIdentifier(str, "raw", packageName);
        Uri parse = Uri.parse("android.resource://" + packageName + "/" + identifier);
        Log.e("AudioModule", String.valueOf(parse));
        try {
            if (this.f10088b != null && this.f10088b.isPlaying()) {
                this.f10088b.stop();
            }
            this.f10088b.reset();
            this.f10088b.setDataSource(context, parse);
            this.f10088b.prepare();
            this.f10088b.start();
        } catch (IOException unused) {
            str2 = "AudioModule";
            str3 = "fail to set data source for audio player";
            Log.e(str2, str3);
        } catch (IllegalStateException unused2) {
            str2 = "AudioModule";
            str3 = "fail to play audio type: ";
            Log.e(str2, str3);
        } catch (NullPointerException unused3) {
            str2 = "AudioModule";
            str3 = "NullPointerException";
            Log.e(str2, str3);
        }
    }
}
