package szcom.mp4parser.streaming;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StreamingSampleHelper {
    /* JADX INFO: Access modifiers changed from: package-private */
    public static <B extends SampleExtension> B getSampleExtension(StreamingSample streamingSample, Class<B> cls) {
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            B b = (B) sampleExtension;
            if (cls.isAssignableFrom(b.getClass())) {
                return b;
            }
        }
        return null;
    }

    static boolean hasSampleExtension(StreamingSample streamingSample, Class<? extends SampleExtension> cls) {
        for (SampleExtension sampleExtension : streamingSample.getExtensions()) {
            if (cls.isAssignableFrom(sampleExtension.getClass())) {
                return true;
            }
        }
        return false;
    }
}
