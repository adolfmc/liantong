package org.apache.commons.codec;

import java.util.Comparator;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
  E:\452516_dexfile_execute.dex
  E:\452516_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public class StringEncoderComparator implements Comparator {
    private final StringEncoder stringEncoder;

    public StringEncoderComparator() {
        this.stringEncoder = null;
    }

    public StringEncoderComparator(StringEncoder stringEncoder) {
        this.stringEncoder = stringEncoder;
    }

    @Override // java.util.Comparator
    public int compare(Object obj, Object obj2) {
        try {
            return ((Comparable) this.stringEncoder.encode(obj)).compareTo((Comparable) this.stringEncoder.encode(obj2));
        } catch (EncoderException unused) {
            return 0;
        }
    }
}
