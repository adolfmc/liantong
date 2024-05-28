package org.apache.commons.lang3.tuple;

import java.io.Serializable;
import java.util.Objects;
import org.apache.commons.lang3.builder.CompareToBuilder;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
/* loaded from: E:\11617560_dexfile_execute.dex */
public abstract class Triple<L, M, R> implements Serializable, Comparable<Triple<L, M, R>> {
    private static final long serialVersionUID = 1;

    public abstract L getLeft();

    public abstract M getMiddle();

    public abstract R getRight();

    @Override // java.lang.Comparable
    public /* bridge */ /* synthetic */ int compareTo(Object obj) {
        return compareTo((Triple) ((Triple) obj));
    }

    /* renamed from: of */
    public static <L, M, R> Triple<L, M, R> m367of(L l, M m, R r) {
        return new ImmutableTriple(l, m, r);
    }

    public int compareTo(Triple<L, M, R> triple) {
        return new CompareToBuilder().append(getLeft(), triple.getLeft()).append(getMiddle(), triple.getMiddle()).append(getRight(), triple.getRight()).toComparison();
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof Triple) {
            Triple triple = (Triple) obj;
            return Objects.equals(getLeft(), triple.getLeft()) && Objects.equals(getMiddle(), triple.getMiddle()) && Objects.equals(getRight(), triple.getRight());
        }
        return false;
    }

    public int hashCode() {
        return ((getLeft() == null ? 0 : getLeft().hashCode()) ^ (getMiddle() == null ? 0 : getMiddle().hashCode())) ^ (getRight() != null ? getRight().hashCode() : 0);
    }

    public String toString() {
        return "(" + getLeft() + "," + getMiddle() + "," + getRight() + ")";
    }

    public String toString(String str) {
        return String.format(str, getLeft(), getMiddle(), getRight());
    }
}
