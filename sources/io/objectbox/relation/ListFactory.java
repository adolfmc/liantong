package io.objectbox.relation;

import io.objectbox.annotation.apihint.Experimental;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
@Experimental
/* loaded from: E:\11617560_dexfile_execute.dex */
public interface ListFactory extends Serializable {
    <T> List<T> createList();

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class ArrayListFactory implements ListFactory {
        private static final long serialVersionUID = 8247662514375611729L;

        @Override // io.objectbox.relation.ListFactory
        public <T> List<T> createList() {
            return new ArrayList();
        }
    }

    /* JADX WARN: Classes with same name are omitted:
  E:\11617560_dexfile_execute.dex.fixout.dex
 */
    /* loaded from: E:\11617560_dexfile_execute.dex */
    public static class CopyOnWriteArrayListFactory implements ListFactory {
        private static final long serialVersionUID = 1888039726372206411L;

        @Override // io.objectbox.relation.ListFactory
        public <T> List<T> createList() {
            return new CopyOnWriteArrayList();
        }
    }
}
