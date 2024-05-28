package com.p319ss.android.socialbase.downloader.network;

import android.os.Handler;
import com.p319ss.android.socialbase.downloader.network.connectionpool.DownloadPreconnecter;
import com.p319ss.android.socialbase.downloader.thread.DownloadWatchDog;
import com.p319ss.android.socialbase.downloader.utils.LruCache;
import java.net.InetAddress;
import java.util.List;

/* renamed from: com.ss.android.socialbase.downloader.network.DownloadDnsManager */
/* loaded from: E:\11617560_dexfile_execute.dex.fixout.dex */
public class DownloadDnsManager {
    private final LruCache<String, DnsRecord> cache;
    private final Handler cpuHandler;
    private final Handler networkHandler;

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.network.DownloadDnsManager$Callback */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public interface Callback {
        void onDnsResolved(String str, List<InetAddress> list);
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.network.DownloadDnsManager$Holder */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class Holder {
        private static final DownloadDnsManager INSTANCE = new DownloadDnsManager();

        private Holder() {
        }
    }

    private DownloadDnsManager() {
        this.cache = new LruCache<>(4, 16, false);
        this.networkHandler = new Handler(DownloadPreconnecter.getLooper());
        this.cpuHandler = new Handler(DownloadWatchDog.getThreadLooper());
    }

    public static DownloadDnsManager getInstance() {
        return Holder.INSTANCE;
    }

    public void resolveDnsAsync(final String str, final Callback callback, final long j) {
        this.networkHandler.post(new Runnable() { // from class: com.ss.android.socialbase.downloader.network.DownloadDnsManager.1
            @Override // java.lang.Runnable
            public void run() {
                DownloadDnsManager.this.resolveDns(str, callback, j);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code restructure failed: missing block: B:34:0x0086, code lost:
        r10 = r2.value;
     */
    /* JADX WARN: Removed duplicated region for block: B:37:0x008f A[Catch: Throwable -> 0x0096, TRY_LEAVE, TryCatch #3 {Throwable -> 0x0096, blocks: (B:2:0x0000, B:3:0x000a, B:7:0x0016, B:10:0x0034, B:12:0x003a, B:14:0x0053, B:21:0x0064, B:29:0x0079, B:32:0x0080, B:35:0x0088, B:37:0x008f, B:34:0x0086, B:23:0x006a, B:4:0x000b, B:5:0x0013, B:16:0x0059, B:24:0x006e), top: B:45:0x0000 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void resolveDns(final java.lang.String r8, final com.p319ss.android.socialbase.downloader.network.DownloadDnsManager.Callback r9, long r10) {
        /*
            r7 = this;
            android.net.Uri r0 = android.net.Uri.parse(r8)     // Catch: java.lang.Throwable -> L96
            java.lang.String r0 = r0.getHost()     // Catch: java.lang.Throwable -> L96
            com.ss.android.socialbase.downloader.utils.LruCache<java.lang.String, com.ss.android.socialbase.downloader.network.DownloadDnsManager$DnsRecord> r1 = r7.cache     // Catch: java.lang.Throwable -> L96
            monitor-enter(r1)     // Catch: java.lang.Throwable -> L96
            com.ss.android.socialbase.downloader.utils.LruCache<java.lang.String, com.ss.android.socialbase.downloader.network.DownloadDnsManager$DnsRecord> r2 = r7.cache     // Catch: java.lang.Throwable -> L93
            java.lang.Object r2 = r2.get(r0)     // Catch: java.lang.Throwable -> L93
            com.ss.android.socialbase.downloader.network.DownloadDnsManager$DnsRecord r2 = (com.p319ss.android.socialbase.downloader.network.DownloadDnsManager.DnsRecord) r2     // Catch: java.lang.Throwable -> L93
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L93
            if (r2 == 0) goto L3a
            com.ss.android.socialbase.downloader.setting.DownloadSetting r1 = com.p319ss.android.socialbase.downloader.setting.DownloadSetting.obtainGlobal()     // Catch: java.lang.Throwable -> L96
            java.lang.String r3 = "dns_expire_min"
            r4 = 10
            int r1 = r1.optInt(r3, r4)     // Catch: java.lang.Throwable -> L96
            long r3 = java.lang.System.currentTimeMillis()     // Catch: java.lang.Throwable -> L96
            long r5 = r2.timestamp     // Catch: java.lang.Throwable -> L96
            long r3 = r3 - r5
            int r1 = r1 * 60
            int r1 = r1 * 1000
            long r5 = (long) r1     // Catch: java.lang.Throwable -> L96
            int r1 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r1 >= 0) goto L3a
            if (r9 == 0) goto L39
            java.util.List<java.net.InetAddress> r10 = r2.value     // Catch: java.lang.Throwable -> L96
            r9.onDnsResolved(r8, r10)     // Catch: java.lang.Throwable -> L96
        L39:
            return
        L3a:
            com.ss.android.socialbase.downloader.network.DownloadDnsManager$2 r1 = new com.ss.android.socialbase.downloader.network.DownloadDnsManager$2     // Catch: java.lang.Throwable -> L96
            r1.<init>()     // Catch: java.lang.Throwable -> L96
            android.os.Handler r3 = r7.cpuHandler     // Catch: java.lang.Throwable -> L96
            r3.postDelayed(r1, r10)     // Catch: java.lang.Throwable -> L96
            r10 = 0
            com.ss.android.socialbase.downloader.setting.DownloadSetting r11 = com.p319ss.android.socialbase.downloader.setting.DownloadSetting.obtainGlobal()     // Catch: java.lang.Throwable -> L96
            java.lang.String r3 = "use_host_dns"
            r4 = 1
            int r11 = r11.optInt(r3, r4)     // Catch: java.lang.Throwable -> L96
            if (r11 != r4) goto L62
            com.ss.android.socialbase.downloader.network.IDownloadDns r11 = com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.getDownloadDns()     // Catch: java.lang.Throwable -> L96
            if (r11 == 0) goto L62
            java.util.List r10 = r11.lookup(r0)     // Catch: java.lang.Throwable -> L5e
            goto L62
        L5e:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L96
        L62:
            if (r10 == 0) goto L6a
            boolean r11 = r10.isEmpty()     // Catch: java.lang.Throwable -> L96
            if (r11 == 0) goto L77
        L6a:
            com.ss.android.socialbase.downloader.network.IDownloadDns r11 = com.p319ss.android.socialbase.downloader.downloader.DownloadComponentManager.getDefaultDownloadDns()     // Catch: java.lang.Throwable -> L96
            java.util.List r10 = r11.lookup(r0)     // Catch: java.lang.Throwable -> L73
            goto L77
        L73:
            r11 = move-exception
            r11.printStackTrace()     // Catch: java.lang.Throwable -> L96
        L77:
            if (r10 == 0) goto L84
            boolean r11 = r10.isEmpty()     // Catch: java.lang.Throwable -> L96
            if (r11 == 0) goto L80
            goto L84
        L80:
            r7.updateIpAddressToCache(r0, r10)     // Catch: java.lang.Throwable -> L96
            goto L88
        L84:
            if (r2 == 0) goto L88
            java.util.List<java.net.InetAddress> r10 = r2.value     // Catch: java.lang.Throwable -> L96
        L88:
            android.os.Handler r11 = r7.cpuHandler     // Catch: java.lang.Throwable -> L96
            r11.removeCallbacks(r1)     // Catch: java.lang.Throwable -> L96
            if (r9 == 0) goto L9a
            r9.onDnsResolved(r8, r10)     // Catch: java.lang.Throwable -> L96
            goto L9a
        L93:
            r8 = move-exception
            monitor-exit(r1)     // Catch: java.lang.Throwable -> L93
            throw r8     // Catch: java.lang.Throwable -> L96
        L96:
            r8 = move-exception
            r8.printStackTrace()
        L9a:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.p319ss.android.socialbase.downloader.network.DownloadDnsManager.resolveDns(java.lang.String, com.ss.android.socialbase.downloader.network.DownloadDnsManager$Callback, long):void");
    }

    private void updateIpAddressToCache(String str, List<InetAddress> list) {
        synchronized (this.cache) {
            DnsRecord dnsRecord = this.cache.get(str);
            if (dnsRecord == null) {
                dnsRecord = new DnsRecord();
                this.cache.put(str, dnsRecord);
            }
            dnsRecord.value = list;
            dnsRecord.timestamp = System.currentTimeMillis();
        }
    }

    /* JADX INFO: Access modifiers changed from: package-private */
    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* renamed from: com.ss.android.socialbase.downloader.network.DownloadDnsManager$DnsRecord */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class DnsRecord {
        long timestamp;
        List<InetAddress> value;

        private DnsRecord() {
        }
    }
}
