package com.networkbench.agent.impl.harvest;

import android.text.TextUtils;
import com.networkbench.agent.impl.harvest.p260a.AbstractC6444i;
import com.networkbench.agent.impl.p254f.InterfaceC6393e;
import com.networkbench.agent.impl.p265k.C6489a;
import com.networkbench.agent.impl.util.C6638h;
import com.networkbench.agent.impl.util.C6647p;
import java.io.OutputStream;
import java.util.Map;

/* JADX WARN: Classes with same name are omitted:
  E:\11480076_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11480076_dexfile_execute.dex */
public class HarvestURLConnection implements HarvestConnectionInterface {
    private static final String X_TINGYUNAPPDATA_SIGN = "X-TingyunAppData-Sign";
    private InitUrlConnection initUrlConnection;
    private C6489a nbsTicToc = new C6489a();

    public void setInitUrlConnection(InitUrlConnection initUrlConnection) {
        this.initUrlConnection = initUrlConnection;
    }

    public HarvestURLConnection(String str, String str2, String str3) {
        this.initUrlConnection = new InitUrlConnection(str, str2, str3);
    }

    /*  JADX ERROR: JadxRuntimeException in pass: BlockProcessor
        jadx.core.utils.exceptions.JadxRuntimeException: Found unreachable blocks
        	at jadx.core.dex.visitors.blocks.DominatorTree.sortBlocks(DominatorTree.java:35)
        	at jadx.core.dex.visitors.blocks.DominatorTree.compute(DominatorTree.java:25)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.computeDominators(BlockProcessor.java:202)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.processBlocksTree(BlockProcessor.java:45)
        	at jadx.core.dex.visitors.blocks.BlockProcessor.visit(BlockProcessor.java:39)
        */
    @Override // com.networkbench.agent.impl.harvest.HarvestConnectionInterface
    public com.networkbench.agent.impl.harvest.HarvestResponse sendDataStr(java.lang.String r7, com.networkbench.agent.impl.harvest.p260a.AbstractC6444i r8) throws java.lang.Exception {
        /*
            Method dump skipped, instructions count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.harvest.HarvestURLConnection.sendDataStr(java.lang.String, com.networkbench.agent.impl.harvest.a.i):com.networkbench.agent.impl.harvest.HarvestResponse");
    }

    private byte[] checkoutContent(String str) {
        try {
            if (str != null) {
                return str.getBytes("utf-8");
            }
            return new byte[0];
        } catch (Throwable unused) {
            return new byte[0];
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:11:0x004c, code lost:
        r0.setResponseTime(r5.nbsTicToc.m9789b());
     */
    /* JADX WARN: Code restructure failed: missing block: B:12:0x0055, code lost:
        return r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:9:0x0049, code lost:
        if (r1 == null) goto L6;
     */
    @Override // com.networkbench.agent.impl.harvest.HarvestConnectionInterface
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public com.networkbench.agent.impl.harvest.HarvestResponse sendDataGet(com.networkbench.agent.impl.harvest.p260a.AbstractC6444i r6) throws java.lang.Exception {
        /*
            r5 = this;
            com.networkbench.agent.impl.harvest.HarvestResponse r0 = new com.networkbench.agent.impl.harvest.HarvestResponse
            r0.<init>()
            r1 = 0
            java.net.URL r2 = new java.net.URL     // Catch: java.lang.Throwable -> L40
            java.lang.String r3 = r6.mo9948a()     // Catch: java.lang.Throwable -> L40
            r2.<init>(r3)     // Catch: java.lang.Throwable -> L40
            java.net.URLConnection r2 = r2.openConnection()     // Catch: java.lang.Throwable -> L40
            java.net.HttpURLConnection r2 = (java.net.HttpURLConnection) r2     // Catch: java.lang.Throwable -> L40
            java.lang.String r3 = "X-License-Key"
            com.networkbench.agent.impl.util.h r4 = com.networkbench.agent.impl.util.C6638h.m8963w()     // Catch: java.lang.Throwable -> L40
            java.lang.String r4 = r4.m9086A()     // Catch: java.lang.Throwable -> L40
            r2.setRequestProperty(r3, r4)     // Catch: java.lang.Throwable -> L40
            java.io.InputStream r1 = r2.getInputStream()     // Catch: java.lang.Throwable -> L40
            java.lang.String r3 = com.networkbench.agent.impl.util.C6653u.m8746a(r1)     // Catch: java.lang.Throwable -> L40
            com.networkbench.agent.impl.harvest.HarvestResponse r0 = r6.mo9947a(r3, r0)     // Catch: java.lang.Throwable -> L40
            int r6 = r2.getResponseCode()     // Catch: java.lang.Throwable -> L40
            r0.setStatusCode(r6)     // Catch: java.lang.Throwable -> L40
            r2.disconnect()     // Catch: java.lang.Throwable -> L40
            if (r1 == 0) goto L4c
        L3a:
            r1.close()
            goto L4c
        L3e:
            r6 = move-exception
            goto L56
        L40:
            java.lang.String r6 = "error"
            r0.setStatus(r6)     // Catch: java.lang.Throwable -> L3e
            r6 = 1
            r0.setErrorCode(r6)     // Catch: java.lang.Throwable -> L3e
            if (r1 == 0) goto L4c
            goto L3a
        L4c:
            com.networkbench.agent.impl.k.a r6 = r5.nbsTicToc
            long r1 = r6.m9789b()
            r0.setResponseTime(r1)
            return r0
        L56:
            if (r1 == 0) goto L5b
            r1.close()
        L5b:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.networkbench.agent.impl.harvest.HarvestURLConnection.sendDataGet(com.networkbench.agent.impl.harvest.a.i):com.networkbench.agent.impl.harvest.HarvestResponse");
    }

    private byte[] createIfNull(byte[] bArr) {
        return bArr == null ? new byte[0] : bArr;
    }

    @Override // com.networkbench.agent.impl.harvest.HarvestConnectionInterface
    public HarvestResponse sendDataFile(Map<String, String[]> map, AbstractC6444i abstractC6444i) throws Exception {
        HarvestResponse harvestResponse = new HarvestResponse();
        this.nbsTicToc.m9790a();
        harvestResponse.setResponseTime(this.nbsTicToc.m9789b());
        return harvestResponse;
    }

    private byte[] composeByteData(byte[] bArr) throws Exception {
        byte[] bytes = this.initUrlConnection.composeHeadValue().getBytes("utf-8");
        byte[] bArr2 = new byte[0];
        if (!TextUtils.isEmpty(C6638h.m8963w().m9075L())) {
            InterfaceC6393e interfaceC6393e = C6638h.f17124y;
            interfaceC6393e.mo10122a("composeByteData token:" + C6638h.m8963w().m9075L());
            bArr2 = C6638h.m8963w().m9075L().getBytes("utf-8");
        }
        byte[] bArr3 = new byte[bArr.length + bytes.length + bArr2.length];
        System.arraycopy(bytes, 0, bArr3, 0, bytes.length);
        System.arraycopy(bArr, 0, bArr3, bytes.length, bArr.length);
        System.arraycopy(bArr2, 0, bArr3, bytes.length + bArr.length, bArr2.length);
        return bArr3;
    }

    private String calcuWithRSA(byte[] bArr) throws Exception {
        return C6638h.m8963w().m8982m().m9099c(calcuByteSHA256(bArr));
    }

    private String calcuByteSHA256(byte[] bArr) {
        return C6647p.m8786b(new String(bArr));
    }

    private void writeStream(OutputStream outputStream, byte[] bArr) throws Exception {
        outputStream.write(bArr);
        outputStream.flush();
        outputStream.close();
    }
}
