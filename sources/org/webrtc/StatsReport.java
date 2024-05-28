package org.webrtc;

/* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\9227576_dexfile_execute.dex */
public class StatsReport {

    /* renamed from: id */
    public final String f27435id;
    public final double timestamp;
    public final String type;
    public final Value[] values;

    /* JADX WARN: Classes with same name are omitted:
  E:\9227576_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\9227576_dexfile_execute.dex */
    public static class Value {
        public final String name;
        public final String value;

        @CalledByNative("Value")
        public Value(String str, String str2) {
            this.name = str;
            this.value = str2;
        }

        public String toString() {
            return "[" + this.name + ": " + this.value + "]";
        }
    }

    @CalledByNative
    public StatsReport(String str, String str2, double d, Value[] valueArr) {
        this.f27435id = str;
        this.type = str2;
        this.timestamp = d;
        this.values = valueArr;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id: ");
        sb.append(this.f27435id);
        sb.append(", type: ");
        sb.append(this.type);
        sb.append(", timestamp: ");
        sb.append(this.timestamp);
        sb.append(", values: ");
        int i = 0;
        while (true) {
            Value[] valueArr = this.values;
            if (i < valueArr.length) {
                sb.append(valueArr[i].toString());
                sb.append(", ");
                i++;
            } else {
                return sb.toString();
            }
        }
    }
}
