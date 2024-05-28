package com.megvii.lv5;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.text.TextUtils;
import java.io.File;

/* compiled from: Proguard */
/* renamed from: com.megvii.lv5.w2 */
/* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
public class C5636w2 {

    /* renamed from: a */
    public MediaPlayer f13800a = new MediaPlayer();

    /* renamed from: b */
    public Context f13801b;

    /* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.w2$a */
    /* loaded from: E:\10762272_dexfile_execute.dex */
    public class C5637a implements MediaPlayer.OnPreparedListener {
        public C5637a() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            C5636w2.this.f13800a.start();
        }
    }

    /* compiled from: Proguard */
    /* renamed from: com.megvii.lv5.w2$b */
    /* loaded from: E:\10762272_dexfile_execute.dex.fixout.dex */
    public class C5638b implements MediaPlayer.OnCompletionListener {

        /* renamed from: a */
        public final /* synthetic */ int f13803a;

        /* renamed from: b */
        public final /* synthetic */ String f13804b;

        public C5638b(int i, String str) {
            this.f13803a = i;
            this.f13804b = str;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            try {
                C5636w2.this.m12942a(this.f13803a, this.f13804b);
                MediaPlayer mediaPlayer2 = C5636w2.this.f13800a;
                if (mediaPlayer2 == null) {
                    return;
                }
                mediaPlayer2.setOnCompletionListener(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public C5636w2(Context context) {
        this.f13801b = context;
    }

    /* renamed from: a */
    public void m12943a() {
        try {
            this.f13801b = null;
            MediaPlayer mediaPlayer = this.f13800a;
            if (mediaPlayer != null) {
                mediaPlayer.reset();
                this.f13800a.release();
                this.f13800a = null;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: a */
    public void m12942a(int i, String str) {
        String str2 = "doPlay: rawId = " + i;
        String str3 = "doPlay: rawPath = " + str;
        if (this.f13800a != null) {
            if (i > 0 || !TextUtils.isEmpty(str)) {
                try {
                    this.f13800a.reset();
                    File file = TextUtils.isEmpty(str) ? null : new File(str);
                    if (file != null && file.exists()) {
                        this.f13800a.setDataSource(str);
                    } else if (i > 0) {
                        AssetFileDescriptor openRawResourceFd = this.f13801b.getResources().openRawResourceFd(i);
                        this.f13800a.setDataSource(openRawResourceFd.getFileDescriptor(), openRawResourceFd.getStartOffset(), openRawResourceFd.getLength());
                        openRawResourceFd.close();
                    }
                    this.f13800a.setOnPreparedListener(new C5637a());
                    this.f13800a.prepareAsync();
                } catch (Throwable unused) {
                }
            }
        }
    }

    /* renamed from: b */
    public void m12941b() {
        try {
            MediaPlayer mediaPlayer = this.f13800a;
            if (mediaPlayer != null) {
                mediaPlayer.reset();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: b */
    public void m12940b(int i, String str) {
        MediaPlayer mediaPlayer = this.f13800a;
        if (mediaPlayer == null) {
            return;
        }
        mediaPlayer.setOnCompletionListener(new C5638b(i, str));
    }
}
