package cn.sharesdk.wework.model;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import cn.sharesdk.framework.utils.SSDKLog;
import cn.sharesdk.wework.model.WKMediaMessage;

/* JADX WARN: Classes with same name are omitted:
  E:\10201592_dexfile_execute.dex.fixout.dex
 */
/* renamed from: cn.sharesdk.wework.model.d */
/* loaded from: E:\10201592_dexfile_execute.dex */
public class WKMediaFile extends WKMediaMessage.AbstractC1886a {

    /* renamed from: j */
    public byte[] f3363j;

    /* renamed from: k */
    public String f3364k;

    /* renamed from: p */
    public String f3365p;

    /* renamed from: q */
    public String f3366q;

    /* renamed from: u */
    private int f3367u = 104857600;

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public boolean mo21167a() {
        String str;
        if (super.mo21167a()) {
            byte[] bArr = this.f3363j;
            if ((bArr == null || bArr.length == 0) && ((str = this.f3364k) == null || str.length() == 0)) {
                SSDKLog.m21740b().m21744a("checkArgs fail, both arguments is null", new Object[0]);
                return false;
            }
            byte[] bArr2 = this.f3363j;
            if (bArr2 != null && bArr2.length > this.f3367u) {
                SSDKLog.m21740b().m21744a("checkArgs fail, fileData is too large", new Object[0]);
                return false;
            }
            String str2 = this.f3364k;
            if (str2 == null || m21168c(str2) <= this.f3367u) {
                return true;
            }
            SSDKLog.m21740b().m21744a("checkArgs fail, fileSize is too large", new Object[0]);
            return false;
        }
        return false;
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21166a(Bundle bundle) {
        bundle.putByteArray("_wwfileobject_fileData", this.f3363j);
        bundle.putString("_wwfileobject_filePath", this.f3364k);
        bundle.putString("_wwfileobject_fileName", this.f3365p);
        String str = this.f3364k;
        if (str == null || !str.startsWith("content")) {
            this.f3366q = m21170a(this.f3351h, this.f3350g, this.f3364k);
        } else {
            this.f3366q = null;
        }
        bundle.putString("_wwfileobject_fileId", this.f3366q);
        super.mo21166a(bundle);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    /* renamed from: a */
    private java.lang.String m21170a(android.content.Context r8, java.lang.String r9, java.lang.String r10) {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            r2 = 0
            java.io.File r3 = new java.io.File     // Catch: java.lang.Throwable -> L86
            r3.<init>(r10)     // Catch: java.lang.Throwable -> L86
            android.net.Uri$Builder r4 = new android.net.Uri$Builder     // Catch: java.lang.Throwable -> L86
            r4.<init>()     // Catch: java.lang.Throwable -> L86
            java.lang.String r5 = "content"
            android.net.Uri$Builder r4 = r4.scheme(r5)     // Catch: java.lang.Throwable -> L86
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L86
            r5.<init>()     // Catch: java.lang.Throwable -> L86
            r5.append(r9)     // Catch: java.lang.Throwable -> L86
            java.lang.String r9 = ".wwapi"
            r5.append(r9)     // Catch: java.lang.Throwable -> L86
            java.lang.String r9 = r5.toString()     // Catch: java.lang.Throwable -> L86
            android.net.Uri$Builder r9 = r4.authority(r9)     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "file"
            android.net.Uri$Builder r9 = r9.appendPath(r4)     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "name"
            java.lang.String r5 = r3.getName()     // Catch: java.lang.Throwable -> L86
            android.net.Uri$Builder r9 = r9.appendQueryParameter(r4, r5)     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "filepath"
            android.net.Uri$Builder r9 = r9.appendQueryParameter(r4, r10)     // Catch: java.lang.Throwable -> L86
            android.net.Uri r9 = r9.build()     // Catch: java.lang.Throwable -> L86
            cn.sharesdk.framework.utils.SSDKLog r10 = cn.sharesdk.framework.utils.SSDKLog.m21740b()     // Catch: java.lang.Throwable -> L86
            java.lang.String r4 = "wwapi"
            java.lang.Object[] r5 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> L86
            java.lang.String r6 = r9.toString()     // Catch: java.lang.Throwable -> L86
            r5[r0] = r6     // Catch: java.lang.Throwable -> L86
            r10.m21744a(r4, r5)     // Catch: java.lang.Throwable -> L86
            android.content.ContentResolver r8 = r8.getContentResolver()     // Catch: java.lang.Throwable -> L86
            java.io.OutputStream r8 = r8.openOutputStream(r9)     // Catch: java.lang.Throwable -> L86
            java.io.FileInputStream r9 = new java.io.FileInputStream     // Catch: java.lang.Throwable -> L80
            r9.<init>(r3)     // Catch: java.lang.Throwable -> L80
            r10 = 1024(0x400, float:1.435E-42)
            byte[] r10 = new byte[r10]     // Catch: java.lang.Throwable -> L7c
        L65:
            int r4 = r9.read(r10)     // Catch: java.lang.Throwable -> L7c
            if (r4 <= 0) goto L6f
            r8.write(r10)     // Catch: java.lang.Throwable -> L7c
            goto L65
        L6f:
            java.lang.String r10 = r3.getName()     // Catch: java.lang.Throwable -> L7c
            r9.close()     // Catch: java.lang.Throwable -> L76
        L76:
            if (r8 == 0) goto L7b
            r8.close()     // Catch: java.lang.Throwable -> L7b
        L7b:
            return r10
        L7c:
            r10 = move-exception
            goto L89
        L7e:
            r10 = move-exception
            goto La8
        L80:
            r10 = move-exception
            r9 = r2
            goto L89
        L83:
            r10 = move-exception
            r8 = r2
            goto La8
        L86:
            r10 = move-exception
            r8 = r2
            r9 = r8
        L89:
            cn.sharesdk.framework.utils.SSDKLog r3 = cn.sharesdk.framework.utils.SSDKLog.m21740b()     // Catch: java.lang.Throwable -> La6
            java.lang.String r4 = "wwapi"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch: java.lang.Throwable -> La6
            java.lang.String r10 = r10.toString()     // Catch: java.lang.Throwable -> La6
            r1[r0] = r10     // Catch: java.lang.Throwable -> La6
            r3.m21744a(r4, r1)     // Catch: java.lang.Throwable -> La6
            if (r9 == 0) goto La0
            r9.close()     // Catch: java.lang.Throwable -> La0
        La0:
            if (r8 == 0) goto La5
            r8.close()     // Catch: java.lang.Throwable -> La5
        La5:
            return r2
        La6:
            r10 = move-exception
            r2 = r9
        La8:
            if (r2 == 0) goto Lad
            r2.close()     // Catch: java.lang.Throwable -> Lad
        Lad:
            if (r8 == 0) goto Lb2
            r8.close()     // Catch: java.lang.Throwable -> Lb2
        Lb2:
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.wework.model.WKMediaFile.m21170a(android.content.Context, java.lang.String, java.lang.String):java.lang.String");
    }

    @Override // cn.sharesdk.wework.model.WKMediaMessage, cn.sharesdk.wework.model.WKBaseMessage, cn.sharesdk.wework.model.BaseMessage
    /* renamed from: b */
    public void mo21165b(Bundle bundle) {
        this.f3363j = bundle.getByteArray("_wwfileobject_fileData");
        this.f3364k = bundle.getString("_wwfileobject_filePath");
        this.f3365p = bundle.getString("_wwfileobject_fileName");
        this.f3366q = bundle.getString("_wwfileobject_fileId");
        super.mo21165b(bundle);
    }

    @Override // cn.sharesdk.wework.model.BaseMessage
    /* renamed from: a */
    public void mo21169a(Intent intent, String str) {
        String str2 = this.f3364k;
        if (str2 == null || !str2.startsWith("content")) {
            return;
        }
        if (intent.getClipData() == null) {
            intent.setClipData(new ClipData("", new String[]{"*/*"}, new ClipData.Item(Uri.parse(this.f3364k))));
        } else {
            intent.getClipData().addItem(new ClipData.Item(Uri.parse(this.f3364k)));
        }
        intent.addFlags(1);
        this.f3366q = null;
    }
}
