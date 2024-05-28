package szorg.mp4parser.aspectj.lang.reflect;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class NoSuchAdviceException extends Exception {
    private static final long serialVersionUID = 3256444698657634352L;
    private String name;

    public NoSuchAdviceException(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
