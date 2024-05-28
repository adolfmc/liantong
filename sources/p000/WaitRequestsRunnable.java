package p000;

/*  JADX ERROR: Load error
    jadx.core.utils.exceptions.JadxRuntimeException: Error decode class: com.danikula.videocache.HttpProxyCacheServer.WaitRequestsRunnable
    	at jadx.core.dex.nodes.ClassNode.initialLoad(ClassNode.java:135)
    	at jadx.core.dex.nodes.ClassNode.<init>(ClassNode.java:106)
    	at jadx.core.dex.nodes.RootNode.lambda$loadClasses$0(RootNode.java:95)
    	at jadx.plugins.input.dex.DexReader.visitClasses(DexReader.java:35)
    	at jadx.plugins.input.dex.DexLoadResult.visitClasses(DexLoadResult.java:27)
    	at jadx.core.dex.nodes.RootNode.loadClasses(RootNode.java:93)
    Caused by: java.lang.IllegalArgumentException: newPosition > limit: (11163392 > 10762272)
    	at java.nio.Buffer.createPositionException(Buffer.java:269)
    	at java.nio.Buffer.position(Buffer.java:244)
    	at jadx.plugins.input.dex.sections.SectionReader.duplicate(SectionReader.java:60)
    	at jadx.plugins.input.dex.sections.SectionReader.<init>(SectionReader.java:34)
    	at jadx.plugins.input.dex.sections.SectionReader.<init>(SectionReader.java:38)
    	at jadx.plugins.input.dex.sections.SectionReader.copy(SectionReader.java:42)
    	at jadx.plugins.input.dex.sections.DexCodeReader.copy(DexCodeReader.java:36)
    	at jadx.plugins.input.dex.sections.DexCodeReader.copy(DexCodeReader.java:25)
    	at jadx.core.dex.nodes.MethodNode.<init>(MethodNode.java:93)
    	at jadx.core.dex.nodes.MethodNode.build(MethodNode.java:78)
    	at jadx.core.dex.nodes.ClassNode.lambda$initialLoad$1(ClassNode.java:117)
    */
/* renamed from: WaitRequestsRunnable */
final class WaitRequestsRunnable {
}
