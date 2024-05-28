package p475rx.schedulers;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* renamed from: rx.schedulers.Timestamped */
/* loaded from: E:\9227576_dexfile_execute.dex */
public final class Timestamped<T> {
    private final long timestampMillis;
    private final T value;

    public Timestamped(long j, T t) {
        this.value = t;
        this.timestampMillis = j;
    }

    public long getTimestampMillis() {
        return this.timestampMillis;
    }

    public T getValue() {
        return this.value;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj != null && (obj instanceof Timestamped)) {
            Timestamped timestamped = (Timestamped) obj;
            if (this.timestampMillis != timestamped.timestampMillis) {
                return false;
            }
            T t = this.value;
            if (t == null) {
                if (timestamped.value != null) {
                    return false;
                }
            } else if (!t.equals(timestamped.value)) {
                return false;
            }
            return true;
        }
        return false;
    }

    public int hashCode() {
        long j = this.timestampMillis;
        int i = (((int) (j ^ (j >>> 32))) + 31) * 31;
        T t = this.value;
        return i + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return String.format("Timestamped(timestampMillis = %d, value = %s)", Long.valueOf(this.timestampMillis), this.value.toString());
    }
}
