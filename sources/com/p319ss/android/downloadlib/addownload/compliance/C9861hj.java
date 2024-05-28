package com.p319ss.android.downloadlib.addownload.compliance;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import com.p319ss.android.downloadlib.addownload.C9940x;
import com.p319ss.android.downloadlib.event.AdEventHandler;
import com.p319ss.android.downloadlib.exception.C9971b;
import com.p319ss.android.downloadlib.utils.C10050jb;
import com.p319ss.android.downloadlib.utils.Chain;
import com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager;
import com.p319ss.android.socialbase.downloader.network.IDownloadHttpConnection;
import com.p319ss.android.socialbase.downloader.utils.DownloadUtils;
import com.p319ss.android.socialbase.downloader.utils.LruCache;
import java.io.BufferedInputStream;
import java.io.Closeable;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

/* renamed from: com.ss.android.downloadlib.addownload.compliance.hj */
/* loaded from: E:\11480076_dexfile_execute.dex.fixout.dex */
public class C9861hj extends LruCache<Long, Bitmap> {

    /* renamed from: mb */
    private final Map<Long, SoftReference<InterfaceC9864mb>> f18943mb;

    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.compliance.hj$mb */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public interface InterfaceC9864mb {
        /* renamed from: mb */
        void mo7654mb(Bitmap bitmap);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.downloadlib.addownload.compliance.hj$ox */
    /* loaded from: E:\11480076_dexfile_execute.dex */
    public static class C9865ox {

        /* renamed from: mb */
        private static C9861hj f18950mb = new C9861hj();
    }

    /* renamed from: mb */
    public static C9861hj m7666mb() {
        return C9865ox.f18950mb;
    }

    private C9861hj() {
        super(8, 8);
        this.f18943mb = new HashMap();
    }

    /* renamed from: mb */
    public void m7663mb(long j, @NonNull InterfaceC9864mb interfaceC9864mb) {
        if (get(Long.valueOf(j)) != null) {
            interfaceC9864mb.mo7654mb((Bitmap) get(Long.valueOf(j)));
        } else {
            this.f18943mb.put(Long.valueOf(j), new SoftReference<>(interfaceC9864mb));
        }
    }

    /* renamed from: mb */
    public void m7664mb(final long j, final long j2, final String str) {
        if (get(Long.valueOf(j)) != null) {
            SoftReference<InterfaceC9864mb> remove = this.f18943mb.remove(Long.valueOf(j));
            if (remove == null || remove.get() == null) {
                return;
            }
            remove.get().mo7654mb((Bitmap) get(Long.valueOf(j)));
        } else if (TextUtils.isEmpty(str)) {
            C9860h.m7672mb(12, j2);
        } else {
            Chain.m7093mb((Chain.InterfaceC10046mb<Object, R>) new Chain.InterfaceC10046mb<Object, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.hj.2
                @Override // com.p319ss.android.downloadlib.utils.Chain.InterfaceC10046mb
                /* renamed from: mb */
                public Object mo7091mb(Object obj) {
                    Throwable th;
                    BufferedInputStream bufferedInputStream;
                    Closeable[] closeableArr;
                    IDownloadHttpConnection downloadWithConnection;
                    try {
                        try {
                            downloadWithConnection = DownloadComponentManager.downloadWithConnection(true, 0, str, null);
                        } catch (Exception e) {
                            e = e;
                            bufferedInputStream = null;
                        } catch (Throwable th2) {
                            th = th2;
                            DownloadUtils.safeClose(null);
                            throw th;
                        }
                        if (downloadWithConnection == null) {
                            DownloadUtils.safeClose(null);
                            return null;
                        }
                        bufferedInputStream = new BufferedInputStream(downloadWithConnection.getInputStream());
                        try {
                            bufferedInputStream.mark(bufferedInputStream.available());
                            BitmapFactory.Options options = new BitmapFactory.Options();
                            options.inJustDecodeBounds = true;
                            BitmapFactory.decodeStream(bufferedInputStream, null, options);
                            int i = options.outWidth;
                            int i2 = options.outHeight;
                            int m7054mb = C10050jb.m7054mb(C9940x.getContext(), 60.0f);
                            options.inSampleSize = C9861hj.m7661ox(m7054mb, m7054mb, options);
                            options.inJustDecodeBounds = false;
                            bufferedInputStream.reset();
                            Bitmap decodeStream = BitmapFactory.decodeStream(bufferedInputStream, null, options);
                            JSONObject jSONObject = new JSONObject();
                            try {
                                jSONObject.putOpt("ttdownloader_type", "load_bitmap");
                                jSONObject.putOpt("bm_original_w", Integer.valueOf(i));
                                jSONObject.putOpt("bm_original_h", Integer.valueOf(i2));
                                jSONObject.putOpt("bm_bytes", Integer.valueOf(decodeStream == null ? -1 : decodeStream.getByteCount()));
                            } catch (Exception e2) {
                                e2.printStackTrace();
                            }
                            AdEventHandler.m7315mb().m7299mb("ttd_pref_monitor", jSONObject, j2);
                            C9861hj.this.put(Long.valueOf(j), decodeStream);
                            closeableArr = new Closeable[]{bufferedInputStream};
                        } catch (Exception e3) {
                            e = e3;
                            C9971b.m7285mb().mo7282mb(e, "BitmapCache loadBitmap");
                            closeableArr = new Closeable[]{bufferedInputStream};
                            DownloadUtils.safeClose(closeableArr);
                            return null;
                        }
                        DownloadUtils.safeClose(closeableArr);
                        return null;
                    } catch (Throwable th3) {
                        th = th3;
                        DownloadUtils.safeClose(null);
                        throw th;
                    }
                }
            }, (Object) null).m7094mb(new Chain.InterfaceC10046mb<Object, Object>() { // from class: com.ss.android.downloadlib.addownload.compliance.hj.1
                @Override // com.p319ss.android.downloadlib.utils.Chain.InterfaceC10046mb
                /* renamed from: mb */
                public Object mo7091mb(Object obj) {
                    SoftReference softReference = (SoftReference) C9861hj.this.f18943mb.remove(Long.valueOf(j));
                    if (softReference == null || softReference.get() == null) {
                        return null;
                    }
                    ((InterfaceC9864mb) softReference.get()).mo7654mb((Bitmap) C9861hj.this.get(Long.valueOf(j)));
                    return null;
                }
            }).m7096mb();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* renamed from: ox */
    public static int m7661ox(int i, int i2, BitmapFactory.Options options) {
        if (options.outWidth > i || options.outHeight > i2) {
            return Math.min(Math.round(options.outWidth / i), Math.round(options.outHeight / i2));
        }
        return 1;
    }
}
