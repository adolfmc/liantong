package com.bytedance.pangle.p178f.p179a;

/* JADX WARN: Classes with same name are omitted:
  E:\10762272_dexfile_execute.dex.fixout.dex
 */
/* renamed from: com.bytedance.pangle.f.a.d */
/* loaded from: E:\10762272_dexfile_execute.dex */
public final class C3828d {
    /* renamed from: a */
    private static String m16858a(int i) {
        return (i >>> 24) == 1 ? "android:" : "";
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
    public static com.bytedance.pangle.p178f.p179a.C3829e m16856a(java.io.File r11) {
        /*
            Method dump skipped, instructions count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.bytedance.pangle.p178f.p179a.C3828d.m16856a(java.io.File):com.bytedance.pangle.f.a.e");
    }

    /* renamed from: a */
    private static String m16857a(C3825a c3825a, int i) {
        int m16870b = c3825a.m16870b(i);
        int m16868c = c3825a.m16868c(i);
        if (m16870b == 3) {
            return c3825a.m16867d(i);
        }
        return m16870b == 2 ? String.format("?%s%08X", m16858a(m16868c), Integer.valueOf(m16868c)) : (m16870b < 16 || m16870b > 31) ? String.format("<0x%X, type 0x%02X>", Integer.valueOf(m16868c), Integer.valueOf(m16870b)) : String.valueOf(m16868c);
    }
}
