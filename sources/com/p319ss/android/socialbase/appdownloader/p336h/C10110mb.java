package com.p319ss.android.socialbase.appdownloader.p336h;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.p083v4.app.NotificationCompat;
import android.text.TextUtils;
import android.widget.RemoteViews;
import com.p319ss.android.socialbase.appdownloader.C10085b;
import com.p319ss.android.socialbase.appdownloader.C10102h;
import com.p319ss.android.socialbase.appdownloader.C10112hj;
import com.p319ss.android.socialbase.appdownloader.DownloadHandlerService;
import com.p319ss.android.socialbase.downloader.exception.BaseException;
import com.p319ss.android.socialbase.downloader.model.DownloadInfo;
import com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem;
import com.p319ss.android.socialbase.downloader.setting.DownloadSetting;

/*  JADX ERROR: NullPointerException in pass: ProcessKotlinInternals
    java.lang.NullPointerException
    */
/* renamed from: com.ss.android.socialbase.appdownloader.h.mb */
/* loaded from: E:\567196_dexfile_execute.dex.fixout.dex */
public class C10110mb extends AbsNotificationItem {

    /* renamed from: b */
    private String f19478b;

    /* renamed from: h */
    private String f19479h;

    /* renamed from: hj */
    private String f19480hj;

    /* renamed from: mb */
    private final Context f19481mb;

    /* renamed from: ox */
    private final Resources f19482ox;

    /*  JADX ERROR: Failed to decode insn: 0x03FE: FILLED_NEW_ARRAY_RANGE , method: com.ss.android.socialbase.appdownloader.h.mb.mb(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification
        java.lang.NullPointerException
        	at jadx.core.dex.instructions.InsnDecoder.filledNewArray(InsnDecoder.java:549)
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:479)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:110)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x042A: UNKNOWN(0xCE3F), method: com.ss.android.socialbase.appdownloader.h.mb.mb(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x042A: UNKNOWN(0xCE3F)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:110)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x0469: UNKNOWN(0x04F2), method: com.ss.android.socialbase.appdownloader.h.mb.mb(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x0469: UNKNOWN(0x04F2)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:110)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /*  JADX ERROR: Failed to decode insn: 0x05AF: UNKNOWN(0x0942), method: com.ss.android.socialbase.appdownloader.h.mb.mb(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification
        jadx.core.utils.exceptions.DecodeException: Unknown instruction: '0x05AF: UNKNOWN(0x0942)'
        	at jadx.core.dex.instructions.InsnDecoder.decode(InsnDecoder.java:501)
        	at jadx.core.dex.instructions.InsnDecoder.lambda$process$0(InsnDecoder.java:53)
        	at jadx.plugins.input.dex.sections.DexCodeReader.visitInstructions(DexCodeReader.java:85)
        	at jadx.core.dex.instructions.InsnDecoder.process(InsnDecoder.java:48)
        	at jadx.core.dex.nodes.MethodNode.load(MethodNode.java:148)
        	at jadx.core.dex.nodes.ClassNode.load(ClassNode.java:409)
        	at jadx.core.ProcessClass.process(ProcessClass.java:67)
        	at jadx.core.ProcessClass.generateCode(ProcessClass.java:110)
        	at jadx.core.dex.nodes.ClassNode.decompile(ClassNode.java:383)
        	at jadx.core.dex.nodes.ClassNode.getCode(ClassNode.java:331)
        */
    /* renamed from: mb */
    private android.app.Notification m6829mb(com.p319ss.android.socialbase.downloader.exception.BaseException r23, boolean r24) {
        /*
            Method dump skipped, instructions count: 1485
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.appdownloader.p336h.C10110mb.m6829mb(com.ss.android.socialbase.downloader.exception.BaseException, boolean):android.app.Notification");
    }

    public C10110mb(Context context, int i, String str, String str2, String str3, String str4) {
        super(i, str);
        this.f19480hj = str2;
        this.f19478b = str3;
        this.f19479h = str4;
        this.f19481mb = context.getApplicationContext();
        this.f19482ox = this.f19481mb.getResources();
    }

    @Override // com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem
    public void updateNotificationItem(DownloadInfo downloadInfo) {
        super.updateNotificationItem(downloadInfo);
        this.f19480hj = downloadInfo.getSavePath();
        this.f19478b = downloadInfo.getName();
        this.f19479h = downloadInfo.getExtra();
    }

    @Override // com.p319ss.android.socialbase.downloader.notification.AbsNotificationItem
    public void updateNotification(BaseException baseException, boolean z) {
        if (this.f19481mb == null) {
            return;
        }
        try {
            this.notification = m6829mb(baseException, z);
            notify(this.notification);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* renamed from: mb */
    private boolean m6830mb(BaseException baseException, DownloadSetting downloadSetting, DownloadInfo downloadInfo) {
        return baseException != null && (baseException.getErrorCode() == 1013 || baseException.getErrorCode() == 1049) && downloadInfo != null && "application/vnd.android.package-archive".contains(downloadInfo.getMimeType()) && downloadSetting.optInt("notification_text_opt", 0) == 1;
    }

    /* renamed from: mb */
    private RemoteViews m6833mb() {
        RemoteViews remoteViews = new RemoteViews(this.f19481mb.getPackageName(), C10102h.m6852mb());
        if (Build.VERSION.SDK_INT > 20) {
            try {
                if (C10085b.m6919mb(this.f19481mb)) {
                    remoteViews.setInt(C10102h.m6848u(), "setBackgroundColor", this.f19481mb.getResources().getColor(C10102h.m6856l()));
                }
            } catch (Throwable unused) {
            }
        }
        return remoteViews;
    }

    /* renamed from: mb */
    private int m6831mb(int i, int i2) {
        if (DownloadSetting.obtain(i2).optInt("notification_opt_2") == 1) {
            return C10102h.m6866df();
        }
        if (i == 1 || i == 4) {
            return C10102h.m6863gm();
        }
        if (i == 2) {
            return C10102h.m6864g();
        }
        if (i == 3) {
            return C10102h.m6866df();
        }
        return 0;
    }

    /* renamed from: mb */
    private PendingIntent m6828mb(String str, int i, int i2) {
        Intent intent = new Intent(this.f19481mb, DownloadHandlerService.class);
        intent.setAction(str);
        intent.putExtra("extra_click_download_ids", i2);
        intent.putExtra("extra_click_download_type", i);
        intent.putExtra("extra_from_notification", true);
        return PendingIntent.getService(this.f19481mb, i2, intent, 201326592);
    }

    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:13:0x0044 -> B:15:0x004b). Please submit an issue!!! */
    /* renamed from: ox */
    private NotificationCompat.Builder m6827ox() {
        NotificationCompat.Builder builder;
        String m6815lz = C10112hj.m6786x().m6815lz();
        if (Build.VERSION.SDK_INT < 26) {
            return new NotificationCompat.Builder(this.f19481mb);
        }
        if (TextUtils.isEmpty(m6815lz)) {
            m6815lz = C10085b.m6896ox(this.f19481mb);
        }
        try {
            if (C10112hj.m6786x().m6820jb() != null) {
                builder = C10112hj.m6786x().m6820jb().m6872mb(this.f19481mb, m6815lz);
            } else {
                builder = new NotificationCompat.Builder(this.f19481mb, m6815lz);
            }
        } catch (NoSuchMethodError unused) {
            builder = new NotificationCompat.Builder(this.f19481mb);
        }
        return builder;
    }

    /* renamed from: mb */
    private int m6832mb(int i) {
        if (DownloadSetting.obtain(i).optInt("enable_notification_ui") >= 1) {
            return C10102h.m6847ww();
        }
        return C10102h.m6857ko();
    }
}
